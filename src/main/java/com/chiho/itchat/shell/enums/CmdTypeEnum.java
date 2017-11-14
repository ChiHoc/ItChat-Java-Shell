package com.chiho.itchat.shell.enums;

public enum CmdTypeEnum {

	LOGIN("login", ""),
	IS_ALIVE("is_alive", "java.lang.Boolean"),
	GET_QRUUID("get_QRuuid", "java.lang.String"),
	GET_QR("get_QR", "java.lang.String"),
	CHECK_LOGIN("check_login", "java.lang.String"),
	WEB_INIT("web_init", "com.chiho.itchat.shell.model.WebInitDO"),
	SHOW_MOBILE_LOGIN("show_mobile_login", "com.chiho.itchat.shell.model.ShowMobileLoginDO"),
	START_RECEIVING("start_receiving", ""),
	GET_MSG("get_msg", "com.chiho.itchat.shell.model.FetchMessageDO"),
	LOGOUT("logout", "com.chiho.itchat.shell.model.StatusResponseDO"),
	UPDATE_CHATROOM("update_chatroom", "com.chiho.itchat.shell.model.ContactDO"),
	UPDATE_FRIEND("update_friend", "com.chiho.itchat.shell.model.ContactDO"),
	GET_CONTACT("get_contact", "com.chiho.itchat.shell.model.ContactDO"),
	GET_FRIENDS("get_friends", "com.chiho.itchat.shell.model.ContactDO"),
	GET_CHATROOMS("get_chatrooms", "com.chiho.itchat.shell.model.ContactDO"),
	GET_MPS("get_mps", "com.chiho.itchat.shell.model.ContactDO"),
	SET_ALIAS("set_alias", "com.chiho.itchat.shell.model.StatusResponseDO"),
	SET_PINNED("set_pinned", "com.chiho.itchat.shell.model.StatusResponseDO"),
	ADD_FRIEND("add_friend", "com.chiho.itchat.shell.model.StatusResponseDO"),
	GET_HEAD_IMG("get_head_img", "com.chiho.itchat.shell.model.HeadImgDO"),
	CREATE_CHATROOM("create_chatroom", "com.chiho.itchat.shell.model.CreateChatroomDO"),
	SET_CHATROOM_NAME("set_chatroom_name", "com.chiho.itchat.shell.model.ModifyChatroomDO"),
	DELETE_MEMBER_FROM_CHATROOM("delete_member_from_chatroom", "com.chiho.itchat.shell.model.ModifyChatroomDO"),
	ADD_MEMBER_INTO_CHATROOM("add_member_into_chatroom", "com.chiho.itchat.shell.model.ModifyChatroomDO"),
	SEND_MSG("send_msg", "com.chiho.itchat.shell.model.SendMsgDO"),
	UPLOAD_FILE("upload_file", "com.chiho.itchat.shell.model.UploadFileDO"),
	SEND_FILE("send_file", "com.chiho.itchat.shell.model.SendMsgDO"),
	SEND_IMAGE("send_image", "com.chiho.itchat.shell.model.SendMsgDO"),
	SEND_VIDEO("send_video", "com.chiho.itchat.shell.model.SendMsgDO"),
	SEND("send", "com.chiho.itchat.shell.model.SendMsgDO"),
	REVOKE("revoke", "com.chiho.itchat.shell.model.RevokeDO"),
	DUMP_LOGIN_STATUS("dump_login_status", ""),
	LOAD_LOGIN_STATUS("load_login_status", "com.chiho.itchat.shell.model.StatusResponseDO"),
	AUTO_LOGIN("auto_login", ""),
	SEARCH_FRIENDS("search_friends", "com.chiho.itchat.shell.model.ContactDO"),
	SEARCH_CHATROOMS("search_chatrooms", "com.chiho.itchat.shell.model.ContactDO"),
	SEARCH_MPS("search_mps", "com.chiho.itchat.shell.model.ContactDO"),
	// Callback
	LOGIN_QRCALLBACK("login_qrCallback", "com.chiho.itchat.shell.model.QrCodeDO"),
	LOGIN_LOGINCALLBACK("login_loginCallback", ""),
	LOGIN_EXITCALLBACK("login_exitCallback", ""),
	STARTRECEIVING_EXITCALLBACK("startReceiving_exitCallback", ""),
	LOGINSTATUS_LOGINCALLBACK("loginStatus_loginCallback", ""),
	LOGINSTATUS_EXITCALLBACK("loginStatus_exitCallback", ""),
	// Listener
	// friend
	FRIEND_TEXTCALLBACK("friend_textCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_MAPCALLBACK("friend_mapCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_CARDCALLBACK("friend_cardCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_NOTECALLBACK("friend_noteCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_SHARINGCALLBACK("friend_sharingCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_PICTURECALLBACK("friend_pictureCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_RECORDINGCALLBACK("friend_recordingCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_ATTACHMENTCALLBACK("friend_attachmentCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_VIDEOCALLBACK("friend_videoCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_FRIENDSCALLBACK("friend_friendsCallback", "com.chiho.itchat.shell.model.MessageDO"),
	FRIEND_SYSTEMCALLBACK("friend_systemCallback", "com.chiho.itchat.shell.model.MessageDO"),
	// mp
	MP_TEXTCALLBACK("mp_textCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_MAPCALLBACK("mp_mapCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_CARDCALLBACK("mp_cardCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_NOTECALLBACK("mp_noteCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_SHARINGCALLBACK("mp_sharingCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_PICTURECALLBACK("mp_pictureCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_RECORDINGCALLBACK("mp_recordingCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_ATTACHMENTCALLBACK("mp_attachmentCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_VIDEOCALLBACK("mp_videoCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_FRIENDSCALLBACK("mp_friendsCallback", "com.chiho.itchat.shell.model.MessageDO"),
	MP_SYSTEMCALLBACK("mp_systemCallback", "com.chiho.itchat.shell.model.MessageDO"),
	// group
	GROUP_TEXTCALLBACK("group_textCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_MAPCALLBACK("group_mapCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_CARDCALLBACK("group_cardCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_NOTECALLBACK("group_noteCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_SHARINGCALLBACK("group_sharingCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_PICTURECALLBACK("group_pictureCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_RECORDINGCALLBACK("group_recordingCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_ATTACHMENTCALLBACK("group_attachmentCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_VIDEOCALLBACK("group_videoCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_FRIENDSCALLBACK("group_friendsCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),
	GROUP_SYSTEMCALLBACK("group_systemCallback", "com.chiho.itchat.shell.model.GroupMessageDO"),;

	private String type;
	private String respType;

	CmdTypeEnum( String type, String respType ) {
		this.type = type;
		this.respType = respType;
	}

	public String getType() {
		return type;
	}

	public String getRespType() {
		return respType;
	}
}
