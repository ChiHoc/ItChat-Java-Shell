import com.alibaba.fastjson.JSON;
import com.chiho.itchat.shell.Shell;
import com.chiho.itchat.shell.enums.AddFriendStatusEnum;
import com.chiho.itchat.shell.enums.MessageTypeEnum;
import com.chiho.itchat.shell.exceptions.ItChatException;
import com.chiho.itchat.shell.model.ContactDO;
import com.chiho.itchat.shell.model.CreateChatroomDO;
import com.chiho.itchat.shell.model.FetchMessageDO;
import com.chiho.itchat.shell.model.HeadImgDO;
import com.chiho.itchat.shell.model.ModifyChatroomDO;
import com.chiho.itchat.shell.model.RevokeDO;
import com.chiho.itchat.shell.model.SendMsgDO;
import com.chiho.itchat.shell.model.ShowMobileLoginDO;
import com.chiho.itchat.shell.model.StatusResponseDO;
import com.chiho.itchat.shell.model.UploadFileDO;
import com.chiho.itchat.shell.model.WebInitDO;
import com.chiho.itchat.shell.utils.QRCodeTools;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketTest {

	static Shell shell = Shell.getInstance();
	static String uuid;

	public static void main( String argv[] ) {
		shell.start();

		try {
			if ( !shell.isAlive() ) {
				shell.autoLogin(true, null, false, null, param -> QRCodeTools.showLoginCode(param.getQrcode()), () -> QRCodeTools.dismissLoginCode(), null);
			}
			setupCallback();
			Scanner input = new Scanner(System.in);
			String cmd;
			do {
				cmd = input.next();
				switchCmd(cmd);
			} while ( !cmd.equals("#") );
			input.close();
		} catch (ItChatException e) {
			e.printStackTrace();
		}
	}

	private static void setupCallback() {

		shell.addFriendMessageCallback(MessageTypeEnum.TEXT, param -> System.out.println(
			"Friend_MAP: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.MAP, param -> System.out.println(
			"Friend_MAP: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.CARD, param -> System.out.println(
			"Friend_CARD: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.NOTE, param -> System.out.println(
			"Friend_NOTE: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.SHARING, param -> System.out.println(
			"Friend_SHARING: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.PICTURE, param -> System.out.println(
			"Friend_PICTURE: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.RECORDING, param -> System.out.println(
			"Friend_RECORDING: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.ATTACHMENT, param -> System.out.println(
			"Friend_ATTACHMENT: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.VIDEO, param -> System.out.println(
			"Friend_VIDEO: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.FRIENDS, param -> System.out.println(
			"Friend_FRIENDS: " + JSON.toJSON(param)));
		shell.addFriendMessageCallback(MessageTypeEnum.SYSTEM, param -> System.out.println(
			"Friend_SYSTEM: " + JSON.toJSON(param)));

		shell.addMpMessageCallback(MessageTypeEnum.TEXT, param -> System.out.println(
			"Mp_TEXT: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.MAP, param -> System.out.println(
			"Mp_MAP: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.CARD, param -> System.out.println(
			"Mp_CARD: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.NOTE, param -> System.out.println(
			"Mp_NOTE: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.SHARING, param -> System.out.println(
			"Mp_SHARING: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.PICTURE, param -> System.out.println(
			"Mp_PICTURE: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.RECORDING, param -> System.out.println(
			"Mp_RECORDING: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.ATTACHMENT, param -> System.out.println(
			"Mp_ATTACHMENT: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.VIDEO, param -> System.out.println(
			"Mp_VIDEO: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.FRIENDS, param -> System.out.println(
			"Mp_FRIENDS: " + JSON.toJSON(param)));
		shell.addMpMessageCallback(MessageTypeEnum.SYSTEM, param -> System.out.println(
			"Mp_SYSTEM: " + JSON.toJSON(param)));

		shell.addGroupMessageCallback(MessageTypeEnum.TEXT, param -> System.out.println(
			"Group_TEXT: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.MAP, param -> System.out.println(
			"Group_MAP: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.CARD, param -> System.out.println(
			"Group_CARD: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.NOTE, param -> System.out.println(
			"Group_NOTE: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.SHARING, param -> System.out.println(
			"Group_SHARING: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.PICTURE, param -> System.out.println(
			"Group_PICTURE: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.RECORDING, param -> System.out.println(
			"Group_RECORDING: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.ATTACHMENT, param -> System.out.println(
			"Group_ATTACHMENT: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.VIDEO, param -> System.out.println(
			"Group_VIDEO: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.FRIENDS, param -> System.out.println(
			"Group_FRIENDS: " + JSON.toJSON(param)));
		shell.addGroupMessageCallback(MessageTypeEnum.SYSTEM, param -> System.out.println(
			"Group_SYSTEM: " + JSON.toJSON(param)));
	}

	private static void switchCmd( String cmd ) {
		try {
			switch ( cmd ) {
				case "login": {
					shell.login(false, null, qrCodeResponse -> {
						uuid = qrCodeResponse.getUuid();
						System.out.println(JSON.toJSON(qrCodeResponse));
					}, () -> System.out.println("loginCallback"), () -> System.out.println("exitCallback"));
				}
				break;
				case "getQRUuid": {
					uuid = shell.getQRUuid();
					System.out.println("uuid: " + uuid);
				}
				break;
				case "getQR": {
					shell.getQR(uuid, false, null, qrCodeResponse -> {
						uuid = qrCodeResponse.getUuid();
						System.out.println(JSON.toJSON(qrCodeResponse));
					});
				}
				break;
				case "checkLogin": {
					String statusCode = shell.checkLogin(uuid);
					System.out.println("statusCode: " + statusCode);
				}
				break;
				case "webInit": {
					WebInitDO webInitDO = shell.webInit();
					System.out.println(JSON.toJSON(webInitDO));
				}
				break;
				case "showMobileLogin": {
					ShowMobileLoginDO showMobileLoginDO = shell.showMobileLogin();
					System.out.println(JSON.toJSON(showMobileLoginDO));
				}
				break;
				case "startReceiving": {
					shell.startReceiving(() -> System.out.println("exitCallback"));
				}
				break;
				case "getMsg": {
					FetchMessageDO fetchMessageDO = shell.getMsg();
					System.out.println(JSON.toJSON(fetchMessageDO));
				}
				break;
				case "logout": {
					StatusResponseDO response = shell.logout();
					System.out.println(JSON.toJSON(response));
				}
				break;
				case "updateChatroom": {
					ContactDO contactDO = shell.updateChatroom("userName", true);
					System.out.println(JSON.toJSON(contactDO));
				}
				break;
				case "updateFriend": {
					ContactDO contactDO = shell.updateFriend("userName");
					System.out.println(JSON.toJSON(contactDO));
				}
				break;
				case "getContact": {
					ContactDO contactDO = shell.updateFriend("userName");
					System.out.println(JSON.toJSON(contactDO));
				}
				break;
				case "getFriends": {
					List<ContactDO> contactList = shell.getFriends(false);
					System.out.println(JSON.toJSON(contactList));
				}
				break;
				case "getChatrooms": {
					List<ContactDO> contactList = shell.getChatrooms(false, false);
					System.out.println(JSON.toJSON(contactList));
				}
				break;
				case "getMps": {
					List<ContactDO> contactList = shell.getMps(false);
					System.out.println(JSON.toJSON(contactList));
				}
				break;
				case "setAlias": {
					StatusResponseDO responseDO = shell.setAlias("filehelper", "白痴传输助手");
					System.out.println(JSON.toJSON(responseDO));
				}
				break;
				case "setPinned": {
					StatusResponseDO responseDO = shell.setPinned("filehelper", true);
					System.out.println(JSON.toJSON(responseDO));
				}
				break;
				case "addFriend": {
					StatusResponseDO responseDO = shell.addFriend("filehelper", AddFriendStatusEnum.ADD, "verifyContent", true);
					System.out.println(JSON.toJSON(responseDO));
				}
				break;
				case "getHeadImg": {
					HeadImgDO imgDO = shell.getHeadImg("filehelper", null, null);
					System.out.println(JSON.toJSON(imgDO));
				}
				break;
				case "createChatroom": {
					CreateChatroomDO createChatroomDO = shell.createChatroom(new ArrayList<ContactDO>() {{
						ContactDO contactDO = new ContactDO();
						contactDO.setUserName("@633cead849896f858ed14ae242748fb9");
						add(contactDO);
						contactDO = new ContactDO();
						contactDO.setUserName("filehelper");
						add(contactDO);
					}}, "topic");
					System.out.println(JSON.toJSON(createChatroomDO));
				}
				break;
				case "setChatroomName": {
					ModifyChatroomDO modifyChatroomDO = shell.setChatroomName("chatroomUserName", "name");
					System.out.println(JSON.toJSON(modifyChatroomDO));
				}
				break;
				case "deleteMemberFromChatroom": {
					ModifyChatroomDO modifyChatroomDO = shell.deleteMemberFromChatroom("@@bd79da9a86aa407fef4faf575dcff5cfde953b449e6af9a181fa5dcf0f7050db", new ArrayList<ContactDO>() {{
						ContactDO contactDO = new ContactDO();
						contactDO.setUserName("filehelper");
						add(contactDO);
					}});
					System.out.println(JSON.toJSON(modifyChatroomDO));
				}
				break;
				case "addMemberIntoChatroom": {
					ModifyChatroomDO modifyChatroomDO = shell.addMemberIntoChatroom("@@bd79da9a86aa407fef4faf575dcff5cfde953b449e6af9a181fa5dcf0f7050db", new ArrayList<ContactDO>() {{
						ContactDO contactDO = new ContactDO();
						contactDO.setUserName("filehelper");
						add(contactDO);
					}}, false);
					System.out.println(JSON.toJSON(modifyChatroomDO));
				}
				break;
				case "sendMsg": {
					SendMsgDO sendMsgDO = shell.sendMsg("msg", "@633cead849896f858ed14ae242748fb9");
					System.out.println(JSON.toJSON(sendMsgDO));
				}
				break;
				case "uploadFile": {
					UploadFileDO uploadFileDO = shell.uploadFile("src/main/resources/QR.png", true, false, "toUserName");
					System.out.println(JSON.toJSON(uploadFileDO));
				}
				break;
				case "sendFile": {
					SendMsgDO sendMsgDO = shell.sendFile("src/main/resources/QR.png", "toUserName", "mediaId");
					System.out.println(JSON.toJSON(sendMsgDO));
				}
				break;
				case "sendImage": {
					SendMsgDO sendMsgDO = shell.sendImage("src/main/resources/QR.png", "toUserName", "mediaId");
					System.out.println(JSON.toJSON(sendMsgDO));
				}
				break;
				case "sendVideo": {
					SendMsgDO sendMsgDO = shell.sendVideo("src/main/resources/QR.png", "toUserName", "mediaId");
					System.out.println(JSON.toJSON(sendMsgDO));
				}
				break;
				case "revoke": {
					RevokeDO revokeDO = shell.revoke("5961489659369359345", "filehelper", "15105439316990");
					System.out.println(JSON.toJSON(revokeDO));

				}
				break;
				case "dumpLoginStatus": {
					shell.dumpLoginStatus(null);
				}
				break;
				case "loadLoginStatus": {
					StatusResponseDO statusResponseDO = shell.loadLoginStatus(null, () -> {
						System.out.println("loginCallback");
					}, () -> {
						System.out.println("exitCallback");
					});
					System.out.println(JSON.toJSON(statusResponseDO));
				}
				break;
				case "autoLogin": {
					shell.autoLogin(true, null, false, null, param -> QRCodeTools.showLoginCode(param.getQrcode()), () -> QRCodeTools.dismissLoginCode(), null);
				}
				break;
				case "searchFriends": {
					List<ContactDO> contactList = shell.searchFriends("name", "userName", "remarkName", "wechatAccount");
					System.out.println(JSON.toJSON(contactList));
				}
				break;
				case "searchChatrooms": {
					List<ContactDO> contactList = shell.searchChatrooms("name", "userName");
					System.out.println(JSON.toJSON(contactList));
				}
				break;
				case "searchMps": {
					List<ContactDO> contactList = shell.searchMps("name", "userName");
					System.out.println(JSON.toJSON(contactList));
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
