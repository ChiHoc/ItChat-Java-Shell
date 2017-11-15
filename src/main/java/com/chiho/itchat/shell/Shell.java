package com.chiho.itchat.shell;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chiho.itchat.shell.enums.CmdTypeEnum;
import com.chiho.itchat.shell.enums.MessageTypeEnum;
import com.chiho.itchat.shell.model.ContactDO;
import com.chiho.itchat.shell.model.GroupMessageDO;
import com.chiho.itchat.shell.model.MessageDO;
import com.chiho.itchat.shell.model.WebInitDO;
import com.chiho.itchat.shell.enums.AddFriendStatusEnum;
import com.chiho.itchat.shell.exceptions.ItChatException;
import com.chiho.itchat.shell.interfaces.Callback;
import com.chiho.itchat.shell.model.CreateChatroomDO;
import com.chiho.itchat.shell.model.FetchMessageDO;
import com.chiho.itchat.shell.model.HeadImgDO;
import com.chiho.itchat.shell.model.SendMsgDO;
import com.chiho.itchat.shell.model.ModifyChatroomDO;
import com.chiho.itchat.shell.model.QrCodeDO;
import com.chiho.itchat.shell.model.RevokeDO;
import com.chiho.itchat.shell.model.ShowMobileLoginDO;
import com.chiho.itchat.shell.model.StatusResponseDO;
import com.chiho.itchat.shell.model.UploadFileDO;
import com.sun.istack.internal.NotNull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import javafx.util.Pair;

public class Shell {

	private static Shell instance;
	private Client client;
	private Server server;
	private ConcurrentMap<String, Callback<Object>> callbackMapper;

	private Shell() {

	}

	public static Shell getInstance() {
		if ( instance == null ) {
			synchronized (Shell.class) {
				instance = new Shell();
				instance.server = new Server("src/main/resources/pyserver.py");
				instance.client = new Client("127.0.0.1", 8001);
				instance.callbackMapper = new ConcurrentHashMap<>();
			}
		}
		return instance;
	}

	public boolean start() {
		return runService() && runClient();
//		return runClient();
	}

	private boolean runService() {
		return server.start();
	}

	private boolean runClient() {
		return client.start(message -> {
			JSONObject msgObj = JSON.parseObject(message);

			if ( callbackMapper.containsKey(msgObj.getString("Cmd")) ) {
				Callback<Object> callback = callbackMapper.get(msgObj.getString("Cmd"));
				if ( !msgObj.containsKey("Args") ) {
					callback.call(null);
					return;
				}
				CmdTypeEnum typeEnum = CmdTypeEnum.valueOf(msgObj.getString("Cmd").toUpperCase());
				switch ( typeEnum.getRespType() ) {
					case "String": {
						callback.call(msgObj.getString("Args"));
					}
					break;
					default: {
						try {
							String argStr = msgObj.getString("Args");
							try {
								if ( argStr.startsWith("[") ) {
									callback.call(JSON.parseArray(argStr, Class.forName(typeEnum.getRespType())));
								} else {
									callback.call(JSON.parseObject(argStr, Class.forName(typeEnum.getRespType())));
								}
							} catch (JSONException e) {
								if ( argStr.contains("BaseResponse") ) {
									callback.call(JSON.parseObject(argStr, StatusResponseDO.class));
								} else {
									e.printStackTrace();
									callback.call(null);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							callback.call(null);
						}
					}
				}
			}
		});
	}

	@SafeVarargs
	private final String genMessage( CmdTypeEnum cmdTypeEnum, Pair<String, String>... args ) {
		StringBuilder cmdBuilder = new StringBuilder("{\"Cmd\":\"").append(cmdTypeEnum.getType()).append("\",\"Args\":{");
		if ( args != null && args.length != 0 ) {
			for ( Pair<String, String> arg : args ) {
				cmdBuilder.append("\"").append(arg.getKey()).append("\":\"").append(arg.getValue()).append("\",");
			}
			cmdBuilder.deleteCharAt(cmdBuilder.length() - 1);
		}
		cmdBuilder.append("}}");
		return cmdBuilder.toString();
	}

	@SafeVarargs
	private final Object sendRequest( CmdTypeEnum cmd, Pair<String, String>... args )
		throws ItChatException {
		String message = genMessage(cmd, args);
		final Semaphore semaphore = new Semaphore(0);
		final Object[] respObj = new Object[ 1 ];
		try {
			callbackMapper.put(cmd.getType(), param -> {
				semaphore.release();
				respObj[ 0 ] = param;
			});
			client.sendString(message);
			semaphore.acquire();

			callbackMapper.remove(cmd.getType());
			if ( respObj[ 0 ] instanceof StatusResponseDO ) {
				StatusResponseDO statusResponseDO = (StatusResponseDO)respObj[ 0 ];
				if ( statusResponseDO.getBaseResponse().getRet() != 0 ) {
					throw new ItChatException(statusResponseDO.getBaseResponse());
				}
			}
			return respObj[ 0 ];
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * get itchat is alive
	 *
	 * @return isAlive
	 */
	public Boolean isAlive() throws ItChatException {
		return (Boolean)sendRequest(CmdTypeEnum.IS_ALIVE);
	}

	/**
	 * log in like web wechat does
	 *
	 * @param enableCmdQR   show qrcode in command line
	 * @param picDir        place for storing qrcode, if not set, it will store in resources dir
	 * @param qrCallback    method that should accept uuid, status, qrcode
	 * @param loginCallback callback after successfully logged in, if not set, screen is cleared and
	 *                      qrcode is deleted
	 * @param exitCallback  callback after logged out, it contains calling of logout
	 *
	 * @return return
	 */
	public void login( Boolean enableCmdQR, String picDir, Callback<QrCodeDO> qrCallback, Runnable loginCallback, Runnable exitCallback )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( enableCmdQR != null ) {
			pairs.add(new Pair<>("enableCmdQR", enableCmdQR ? "True" : "False"));
		}
		if ( picDir != null ) {
			pairs.add(new Pair<>("picDir", "u'" + picDir + "'"));
		} else {
			pairs.add(new Pair<>("picDir",
				"u'" + new File("src/main/resources/QR.png").getAbsolutePath() + "'"));
		}
		if ( qrCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGIN_QRCALLBACK.getType(), param -> qrCallback.call((QrCodeDO)param));
			pairs.add(new Pair<>("qrCallback", "login_qrCallback"));
		} else {
			callbackMapper.remove("login_qrCallback");
		}
		if ( loginCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGIN_LOGINCALLBACK.getType(), param -> loginCallback.run());
			pairs.add(new Pair<>("loginCallback", "login_loginCallback"));
		} else {
			callbackMapper.remove("login_loginCallback");
		}
		if ( exitCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGIN_EXITCALLBACK.getType(), param -> exitCallback.run());
			pairs.add(new Pair<>("exitCallback", "login_exitCallback"));
		} else {
			callbackMapper.remove("login_exitCallback");
		}
		sendRequest(CmdTypeEnum.LOGIN, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * get uuid for qrcode
	 *
	 * uuid is the symbol of qrcode
	 *
	 * - for logging in, you need to get a uuid first
	 *
	 * - for downloading qrcode, you need to pass uuid to it
	 *
	 * - for checking login status, uuid is also required
	 *
	 * if uuid has timed out, just get another
	 *
	 * @return uuid
	 */
	public String getQRUuid() throws ItChatException {
		return (String)sendRequest(CmdTypeEnum.GET_QRUUID);
	}

	/**
	 * download and show qrcode
	 *
	 * @param uuid        if uuid is not set, latest uuid you fetched will be used
	 * @param enableCmdQR show qrcode in cmd
	 * @param picDir      place for storing qrcode, if not set, it will store in resources dir
	 * @param qrCallback  method that should accept uuid, status, qrcode
	 *
	 * @return return
	 */
	public String getQR( String uuid, Boolean enableCmdQR, String picDir, Callback<QrCodeDO> qrCallback )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( uuid != null ) {
			pairs.add(new Pair<>("uuid", "u'" + uuid + "'"));
		}
		if ( enableCmdQR != null ) {
			pairs.add(new Pair<>("enableCmdQR", enableCmdQR ? "True" : "False"));
		}
		if ( picDir != null ) {
			pairs.add(new Pair<>("picDir", "u'" + picDir + "'"));
		} else {
			pairs.add(new Pair<>("picDir",
				"u'" + new File("src/main/resources/QR.png").getAbsolutePath() + "'"));
		}
		if ( qrCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGIN_QRCALLBACK.getType(), param -> qrCallback.call((QrCodeDO)param));
			pairs.add(new Pair<>("qrCallback", "login_qrCallback"));
		} else {
			callbackMapper.remove("login_qrCallback");
		}
		return (String)sendRequest(CmdTypeEnum.GET_QR, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * check login status
	 *
	 * @param uuid if uuid is not set, latest uuid you fetched will be used
	 *
	 * @return return
	 */
	public String checkLogin( String uuid ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( uuid != null ) {
			pairs.add(new Pair<>("uuid", uuid));
		}
		return (String)sendRequest(CmdTypeEnum.CHECK_LOGIN, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * get info necessary for initializing
	 *
	 * for processing:
	 *
	 * - own account info is set
	 *
	 * - inviteStartCount is set
	 *
	 * - syncKey is set
	 *
	 * - part of contact is fetched
	 *
	 * @return return
	 */
	public WebInitDO webInit() throws ItChatException {
		return (WebInitDO)sendRequest(CmdTypeEnum.WEB_INIT);
	}

	/**
	 * show web wechat login sign
	 *
	 * the sign is on the top of mobile phone wechat
	 *
	 * sign will be added after sometime even without calling this function
	 *
	 * @return return
	 */
	public ShowMobileLoginDO showMobileLogin() throws ItChatException {
		return (ShowMobileLoginDO)sendRequest(CmdTypeEnum.SHOW_MOBILE_LOGIN);
	}

	/**
	 * open a thread for heart loop and receiving messages
	 *
	 * for processing:
	 *
	 * - messages: msgs are formatted and passed on to registered fns
	 *
	 * - contact : chatrooms are updated when related info is received
	 *
	 * @param exitCallback callback after logged out, it contains calling of logout
	 */
	public void startReceiving( Runnable exitCallback ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( exitCallback != null ) {
			callbackMapper.put(CmdTypeEnum.STARTRECEIVING_EXITCALLBACK.getType(), param -> exitCallback.run());
			pairs.add(new Pair<>("exitCallback", "startReceiving_exitCallback"));
		} else {
			callbackMapper.remove("startReceiving_exitCallback");
		}
		sendRequest(CmdTypeEnum.START_RECEIVING, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * fetch messages
	 *
	 * for fetching
	 *
	 * - method blocks for sometime util
	 *
	 * - new messages are to be received
	 *
	 * - or anytime they like
	 *
	 * - synckey is updated with returned synccheckkey
	 *
	 * @return return
	 */
	public FetchMessageDO getMsg() throws ItChatException {
		return (FetchMessageDO)sendRequest(CmdTypeEnum.GET_MSG);
	}

	/**
	 * logout
	 *
	 * if core is now alive
	 *
	 * logout will tell wechat backstage to logout
	 *
	 * and core gets ready for another login
	 *
	 * @return return
	 */
	public StatusResponseDO logout() throws ItChatException {
		return (StatusResponseDO)sendRequest(CmdTypeEnum.LOGOUT);
	}

	/**
	 * update chatroom
	 *
	 * for chatroom contact
	 *
	 * - a chatroom contact need updating to be detailed
	 *
	 * - detailed means members, encryid, etc
	 *
	 * - auto updating of heart loop is a more detailed updating
	 *
	 * - member uin will also be filled
	 *
	 * - once called, updated info will be stored
	 *
	 * @param userName       'UserName' key of chatroom or a list of it
	 * @param detailedMember whether to get members of contact
	 *
	 * @return return
	 */
	public ContactDO updateChatroom( @NotNull String userName, Boolean detailedMember )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		if ( detailedMember != null ) {
			pairs.add(new Pair<>("detailedMember", detailedMember ? "True" : "False"));
		}
		return (ContactDO)sendRequest(CmdTypeEnum.UPDATE_CHATROOM, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * update chatroom
	 *
	 * for friend contact
	 *
	 * - once called, updated info will be stored
	 *
	 * @param userName 'UserName' key of a friend or a list of it
	 *
	 * @return return
	 */
	public ContactDO updateFriend( @NotNull String userName ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		return (ContactDO)sendRequest(CmdTypeEnum.UPDATE_FRIEND, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * fetch part of contact
	 *
	 * for part
	 *
	 * - all the massive platforms and friends are fetched
	 *
	 * - if update, only starred chatrooms are fetched
	 *
	 * @param update if not set, local value will be returned
	 *
	 * @return chatroomList will be returned
	 */
	public List<ContactDO> getContact( Boolean update ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( update != null ) {
			pairs.add(new Pair<>("update", update ? "True" : "False"));
		}
		return (List<ContactDO>)sendRequest(CmdTypeEnum.GET_CONTACT, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * fetch friends list
	 *
	 * @param update if not set, local value will be returned
	 *
	 * @return a list of friends' info dicts will be returned
	 */
	public List<ContactDO> getFriends( Boolean update ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( update != null ) {
			pairs.add(new Pair<>("update", update ? "True" : "False"));
		}
		return (List<ContactDO>)sendRequest(CmdTypeEnum.GET_FRIENDS, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * fetch chatrooms list
	 *
	 * @param update      if not set, local value will be returned
	 * @param contactOnly if set, only starred chatrooms will be returned
	 *
	 * @return a list of chatrooms' info dicts will be returned
	 */
	public List<ContactDO> getChatrooms( Boolean update, Boolean contactOnly )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( update != null ) {
			pairs.add(new Pair<>("update", update ? "True" : "False"));
		}
		if ( contactOnly != null ) {
			pairs.add(new Pair<>("contactOnly", contactOnly ? "True" : "False"));
		}
		return (List<ContactDO>)sendRequest(CmdTypeEnum.GET_CHATROOMS, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * fetch massive platforms list
	 *
	 * @param update if not set, local value will be returned
	 *
	 * @return a list of platforms' info dicts will be returned
	 */
	public List<ContactDO> getMps( Boolean update ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( update != null ) {
			pairs.add(new Pair<>("update", update ? "True" : "False"));
		}
		return (List<ContactDO>)sendRequest(CmdTypeEnum.GET_MPS, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * set remark for a friend
	 *
	 * @param userName 'UserName' key of info dict
	 * @param remark   new remark
	 *
	 * @return return
	 */
	public StatusResponseDO setAlias( @NotNull String userName, @NotNull String remark )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		if ( remark != null ) {
			pairs.add(new Pair<>("alias", "u'" + remark + "'"));
		}
		return (StatusResponseDO)sendRequest(CmdTypeEnum.SET_ALIAS, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * set pinned for a friend or a chatroom
	 *
	 * @param userName 'UserName' key of info dict
	 * @param isPinned whether to pin
	 *
	 * @return return
	 */
	public StatusResponseDO setPinned( @NotNull String userName, Boolean isPinned )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		if ( isPinned != null ) {
			pairs.add(new Pair<>("isPinned", isPinned ? "True" : "False"));
		}
		return (StatusResponseDO)sendRequest(CmdTypeEnum.SET_PINNED, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * add a friend or accept a friend
	 *
	 * @param userName      'UserName' for friend's info dict
	 * @param status        for adding status should be 2, for accepting status should be 3
	 * @param verifyContent verify content
	 * @param autoUpdate    whether auto update
	 *
	 * @return return
	 */
	public StatusResponseDO addFriend( @NotNull String userName, AddFriendStatusEnum status, String verifyContent, Boolean autoUpdate )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		if ( status != null ) {
			pairs.add(new Pair<>("status", status.getCode()));
		}
		if ( verifyContent != null ) {
			pairs.add(new Pair<>("verifyContent", "u'" + verifyContent + "'"));
		}
		if ( autoUpdate != null ) {
			pairs.add(new Pair<>("autoUpdate", autoUpdate ? "True" : "False"));
		}
		return (StatusResponseDO)sendRequest(CmdTypeEnum.ADD_FRIEND, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * place for docs
	 *
	 * for options
	 *
	 * - if you want to get chatroom header: only set chatroomUserName
	 *
	 * - if you want to get friend header: only set userName
	 *
	 * - if you want to get chatroom member header: set both
	 *
	 * @param userName         'UserName' for friend's info dict
	 * @param chatroomUserName 'UserName' key of chatroom info dict
	 * @param picDir           place for storing qrcode, if not set, it will store in resources dir
	 *
	 * @return return
	 */
	public HeadImgDO getHeadImg( String userName, String chatroomUserName, String picDir )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		if ( chatroomUserName != null ) {
			pairs.add(new Pair<>("chatroomUserName", "u'" + chatroomUserName + "'"));
		}
		if ( picDir != null ) {
			pairs.add(new Pair<>("picDir", "u'" + picDir + "'"));
		} else {
			pairs.add(new Pair<>("picDir",
				"u'" + new File("src/main/resources/headImg.png").getAbsolutePath() + "'"));
		}
		return (HeadImgDO)sendRequest(CmdTypeEnum.GET_HEAD_IMG, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * create a chatroom
	 *
	 * for creating
	 *
	 * - its calling frequency is strictly limited
	 *
	 * @param memberList list of member info dict
	 * @param topic      topic of new chatroom
	 *
	 * @return return
	 */
	public CreateChatroomDO createChatroom( @NotNull List<ContactDO> memberList, String topic )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( memberList != null ) {
			pairs.add(new Pair<>("memberList", JSON.toJSONString(memberList).replace("\"", "'")));
		}
		if ( topic != null ) {
			pairs.add(new Pair<>("topic", "u'" + topic + "'"));
		}
		return (CreateChatroomDO)sendRequest(CmdTypeEnum.CREATE_CHATROOM, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * set chatroom name
	 *
	 * for setting
	 *
	 * - it makes an updating of chatroom
	 *
	 * - which means detailed info will be returned in heart loop
	 *
	 * @param chatroomUserName 'UserName' key of chatroom info dict
	 * @param name             new chatroom name
	 *
	 * @return return
	 */
	public ModifyChatroomDO setChatroomName( @NotNull String chatroomUserName, @NotNull String name )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( chatroomUserName != null ) {
			pairs.add(new Pair<>("chatroomUserName", "u'" + chatroomUserName + "'"));
		}
		if ( name != null ) {
			pairs.add(new Pair<>("name", "u'" + name + "'"));
		}
		return (ModifyChatroomDO)sendRequest(CmdTypeEnum.SET_CHATROOM_NAME, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * deletes members from chatroom
	 *
	 * for deleting
	 *
	 * - you can't delete yourself
	 *
	 * - if so, no one will be deleted
	 *
	 * - strict-limited frequency
	 *
	 * @param chatroomUserName 'UserName' key of chatroom info dict
	 * @param memberList       list of members' info dict
	 *
	 * @return return
	 */
	public ModifyChatroomDO deleteMemberFromChatroom( @NotNull String chatroomUserName, @NotNull List<ContactDO> memberList )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( chatroomUserName != null ) {
			pairs.add(new Pair<>("chatroomUserName", "u'" + chatroomUserName + "'"));
		}
		if ( memberList != null ) {
			pairs.add(new Pair<>("memberList", JSON.toJSONString(memberList).replace("\"", "'")));
		}
		return (ModifyChatroomDO)sendRequest(CmdTypeEnum.DELETE_MEMBER_FROM_CHATROOM, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * add members into chatroom
	 *
	 * for adding
	 *
	 * - you can't add yourself or member already in chatroom
	 *
	 * - if so, no one will be added
	 *
	 * - if member will over 40 after adding, invitation must be used
	 *
	 * - strict-limited frequency
	 *
	 * @param chatroomUserName 'UserName' key of chatroom info dict
	 * @param memberList       list of members' info dict
	 * @param useInvitation    if invitation is not required, set this to use
	 *
	 * @return return
	 */
	public ModifyChatroomDO addMemberIntoChatroom( @NotNull String chatroomUserName, @NotNull List<ContactDO> memberList, Boolean useInvitation )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( chatroomUserName != null ) {
			pairs.add(new Pair<>("chatroomUserName", "u'" + chatroomUserName + "'"));
		}
		if ( memberList != null ) {
			pairs.add(new Pair<>("memberList", JSON.toJSONString(memberList).replace("\"", "'")));
		}
		if ( useInvitation != null ) {
			pairs.add(new Pair<>("useInvitation", useInvitation ? "True" : "False"));
		}
		return (ModifyChatroomDO)sendRequest(CmdTypeEnum.ADD_MEMBER_INTO_CHATROOM, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * send plain text message
	 *
	 * @param msg        should be unicode if there's non-ascii words in msg
	 * @param toUserName 'UserName' key of friend dict
	 *
	 * @return return
	 */
	public SendMsgDO sendMsg( String msg, @NotNull String toUserName ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( msg != null ) {
			pairs.add(new Pair<>("msg", "u'" + msg + "'"));
		}
		if ( toUserName != null ) {
			pairs.add(new Pair<>("toUserName", "u'" + toUserName + "'"));
		}
		return (SendMsgDO)sendRequest(CmdTypeEnum.SEND_MSG, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * upload file to server and get mediaId
	 *
	 * @param fileDir    dir for file ready for upload
	 * @param isPicture  whether file is a picture
	 * @param isVideo    whether file is a video
	 * @param toUserName 'UserName' key of friend dict
	 *
	 * @return return
	 */
	public UploadFileDO uploadFile( @NotNull String fileDir, Boolean isPicture, Boolean isVideo, @NotNull String toUserName )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( fileDir != null ) {
			pairs.add(new Pair<>("fileDir", "u'" + fileDir + "'"));
		}
		if ( isPicture != null ) {
			pairs.add(new Pair<>("isPicture", isPicture ? "True" : "False"));
		}
		if ( isVideo != null ) {
			pairs.add(new Pair<>("isVideo", isVideo ? "True" : "False"));
		}
		if ( toUserName != null ) {
			pairs.add(new Pair<>("toUserName", "u'" + toUserName + "'"));
		}
		return (UploadFileDO)sendRequest(CmdTypeEnum.UPLOAD_FILE, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * send attachment
	 *
	 * @param fileDir    dir for file ready for upload
	 * @param toUserName 'UserName' key of friend dict
	 * @param mediaId    mediaId for file
	 *
	 * @return return
	 */
	public SendMsgDO sendFile( @NotNull String fileDir, @NotNull String toUserName, String mediaId )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( fileDir != null ) {
			pairs.add(new Pair<>("fileDir", "u'" + fileDir + "'"));
		}
		if ( toUserName != null ) {
			pairs.add(new Pair<>("toUserName", "u'" + toUserName + "'"));
		}
		if ( mediaId != null ) {
			pairs.add(new Pair<>("mediaId", "u'" + mediaId + "'"));
		}
		return (SendMsgDO)sendRequest(CmdTypeEnum.SEND_FILE, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * send image
	 *
	 * @param fileDir    dir for file ready for upload, if it's a gif, name it like 'xx.gif'
	 * @param toUserName 'UserName' key of friend dict
	 * @param mediaId    mediaId for file, if set, file will not be uploaded twice
	 *
	 * @return return
	 */
	public SendMsgDO sendImage( @NotNull String fileDir, @NotNull String toUserName, String mediaId )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( fileDir != null ) {
			pairs.add(new Pair<>("fileDir", "u'" + fileDir + "'"));
		}
		if ( toUserName != null ) {
			pairs.add(new Pair<>("toUserName", "u'" + toUserName + "'"));
		}
		if ( mediaId != null ) {
			pairs.add(new Pair<>("mediaId", "u'" + mediaId + "'"));
		}
		return (SendMsgDO)sendRequest(CmdTypeEnum.SEND_IMAGE, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * send video
	 *
	 * @param fileDir    dir for file ready for upload
	 * @param toUserName 'UserName' key of friend dict
	 * @param mediaId    mediaId for file, if set, file will not be uploaded twice
	 *
	 * @return return
	 */
	public SendMsgDO sendVideo( @NotNull String fileDir, @NotNull String toUserName, String mediaId )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( fileDir != null ) {
			pairs.add(new Pair<>("fileDir", "u'" + fileDir + "'"));
		}
		if ( toUserName != null ) {
			pairs.add(new Pair<>("toUserName", "u'" + toUserName + "'"));
		}
		if ( mediaId != null ) {
			pairs.add(new Pair<>("mediaId", "u'" + mediaId + "'"));
		}
		return (SendMsgDO)sendRequest(CmdTypeEnum.SEND_VIDEO, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * revoke message with its and msgId
	 *
	 * @param msgId      message Id on server
	 * @param toUserName 'UserName' key of friend dict
	 * @param localId    message Id at local (optional)
	 *
	 * @return return
	 */
	public RevokeDO revoke( @NotNull String msgId, @NotNull String toUserName, @NotNull String localId )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( msgId != null ) {
			pairs.add(new Pair<>("msgId", "u'" + msgId + "'"));
		}
		if ( toUserName != null ) {
			pairs.add(new Pair<>("toUserName", "u'" + toUserName + "'"));
		}
		if ( localId != null ) {
			pairs.add(new Pair<>("localId", "u'" + localId + "'"));
		}
		return (RevokeDO)sendRequest(CmdTypeEnum.REVOKE, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * dump login status to a specific file
	 *
	 * @param fileDir dir for dumping login status
	 *
	 * @return return
	 */
	public void dumpLoginStatus( String fileDir ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( fileDir != null ) {
			pairs.add(new Pair<>("fileDir", "u'" + fileDir + "'"));
		} else {
			pairs.add(new Pair<>("fileDir",
				"u'" + new File("src/main/resources/login.sav").getAbsolutePath() + "'"));
		}
		sendRequest(CmdTypeEnum.DUMP_LOGIN_STATUS, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * load login status from a specific file
	 *
	 * @param fileDir       file for loading login status
	 * @param loginCallback callback after successfully logged in
	 *
	 *                      - if not set, screen is cleared and qrcode is deleted
	 * @param exitCallback  callback after logged out
	 *
	 *                      - it contains calling of logout
	 *
	 * @return return
	 */
	public StatusResponseDO loadLoginStatus( String fileDir, Runnable loginCallback, Runnable exitCallback )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( fileDir != null ) {
			pairs.add(new Pair<>("fileDir", "u'" + fileDir + "'"));
		} else {
			pairs.add(new Pair<>("fileDir",
				"u'" + new File("src/main/resources/login.sav").getAbsolutePath() + "'"));
		}
		if ( loginCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGINSTATUS_LOGINCALLBACK.getType(), param -> loginCallback.run());
			pairs.add(new Pair<>("loginCallback", "loginStatus_loginCallback"));
		} else {
			callbackMapper.remove("loginStatus_loginCallback");
		}
		if ( exitCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGINSTATUS_EXITCALLBACK.getType(), param -> exitCallback.run());
			pairs.add(new Pair<>("exitCallback", "loginStatus_exitCallback"));
		} else {
			callbackMapper.remove("loginStatus_exitCallback");
		}
		return (StatusResponseDO)sendRequest(CmdTypeEnum.LOAD_LOGIN_STATUS, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * log in like web wechat does
	 *
	 * @param hotReload        enable hot reload
	 * @param statusStorageDir dir for storing log in status
	 * @param enableCmdQR      show qrcode in command line
	 * @param picDir           place for storing qrcode, if not set, it will store in resources dir
	 * @param qrCallback       method that should accept uuid, status, qrcode
	 * @param loginCallback    callback after successfully logged in, if not set, screen is cleared
	 *                         and qrcode is deleted
	 * @param exitCallback     callback after logged out, it contains calling of logout
	 *
	 * @return return
	 */
	public void autoLogin( Boolean hotReload, String statusStorageDir, Boolean enableCmdQR, String picDir, Callback<QrCodeDO> qrCallback, Runnable loginCallback, Runnable exitCallback )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( hotReload != null ) {
			pairs.add(new Pair<>("hotReload", hotReload ? "True" : "False"));
		}
		if ( statusStorageDir != null ) {
			pairs.add(new Pair<>("statusStorageDir", "u'" + statusStorageDir + "'"));
		} else {
			pairs.add(new Pair<>("statusStorageDir",
				"u'" + new File("src/main/resources/login.sav").getAbsolutePath() + "'"));
		}
		if ( enableCmdQR != null ) {
			pairs.add(new Pair<>("enableCmdQR", enableCmdQR ? "True" : "False"));
		}
		if ( picDir != null ) {
			pairs.add(new Pair<>("picDir", "u'" + picDir + "'"));
		} else {
			pairs.add(new Pair<>("picDir",
				"u'" + new File("src/main/resources/QR.png").getAbsolutePath() + "'"));
		}
		if ( qrCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGIN_QRCALLBACK.getType(), param -> qrCallback.call((QrCodeDO)param));
			pairs.add(new Pair<>("qrCallback", "login_qrCallback"));
		} else {
			callbackMapper.remove("login_qrCallback");
		}
		if ( loginCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGIN_LOGINCALLBACK.getType(), param -> loginCallback.run());
			pairs.add(new Pair<>("loginCallback", "login_loginCallback"));
		} else {
			callbackMapper.remove("login_loginCallback");
		}
		if ( exitCallback != null ) {
			callbackMapper.put(CmdTypeEnum.LOGIN_EXITCALLBACK.getType(), param -> exitCallback.run());
			pairs.add(new Pair<>("exitCallback", "login_exitCallback"));
		} else {
			callbackMapper.remove("login_exitCallback");
		}
		sendRequest(CmdTypeEnum.AUTO_LOGIN, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * search friend with key
	 *
	 * @param name          nickname
	 * @param userName      'UserName' key of friend dict
	 * @param remarkName    remark name
	 * @param wechatAccount alias
	 *
	 * @return
	 *
	 * @throws ItChatException
	 */
	public List<ContactDO> searchFriends( String name, String userName, String remarkName, String wechatAccount )
		throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( name != null ) {
			pairs.add(new Pair<>("name", "u'" + name + "'"));
		}
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		if ( remarkName != null ) {
			pairs.add(new Pair<>("remarkName", "u'" + remarkName + "'"));
		}
		if ( wechatAccount != null ) {
			pairs.add(new Pair<>("wechatAccount", "u'" + wechatAccount + "'"));
		}
		return (List<ContactDO>)sendRequest(CmdTypeEnum.SEARCH_FRIENDS, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * search chatroom with key
	 *
	 * @param name     nickname
	 * @param userName 'UserName' key of friend dict
	 *
	 * @return
	 *
	 * @throws ItChatException
	 */
	public List<ContactDO> searchChatrooms( String name, String userName ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( name != null ) {
			pairs.add(new Pair<>("name", "u'" + name + "'"));
		}
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		return (List<ContactDO>)sendRequest(CmdTypeEnum.SEARCH_CHATROOMS, pairs.toArray(new Pair[ 0 ]));
	}

	/**
	 * search media platform with key
	 *
	 * @param name     nickname
	 * @param userName 'UserName' key of friend dict
	 *
	 * @return
	 *
	 * @throws ItChatException
	 */
	public List<ContactDO> searchMps( String name, String userName ) throws ItChatException {
		List<Pair> pairs = new ArrayList<>();
		if ( name != null ) {
			pairs.add(new Pair<>("name", "u'" + name + "'"));
		}
		if ( userName != null ) {
			pairs.add(new Pair<>("userName", "u'" + userName + "'"));
		}
		return (List<ContactDO>)sendRequest(CmdTypeEnum.SEARCH_MPS, pairs.toArray(new Pair[ 0 ]));
	}

	public void addFriendMessageCallback(MessageTypeEnum messageType, Callback<MessageDO> callback) {
		callbackMapper.put("friend_" + messageType.getType() + "Callback",  param -> callback.call((MessageDO)param));
	}

	public void addMpMessageCallback(MessageTypeEnum messageType, Callback<MessageDO> callback) {
		callbackMapper.put("mp_" + messageType.getType() + "Callback",  param -> callback.call((MessageDO)param));
	}

	public void addGroupMessageCallback(MessageTypeEnum messageType, Callback<GroupMessageDO> callback) {
		callbackMapper.put("group_" + messageType.getType() + "Callback",  param -> callback.call((GroupMessageDO)param));
	}
}
