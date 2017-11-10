package com.chiho.itchat4java.enums;

public enum CmdTypeEnum {

	LOGIN("login", ""),
	GET_QRUUID("get_QRuuid", "java.lang.String"),
	GET_QR("get_QR", "java.lang.String"),
	CHECK_LOGIN("check_login", "java.lang.String"),
	WEB_INIT("web_init", "com.chiho.itchat4java.model.WebInitDO"),
	SHOW_MOBILE_LOGIN("show_mobile_login", "com.chiho.itchat4java.model.ShowMobileLoginDO"),
	START_RECEIVING("start_receiving", ""),
	GET_MSG("get_msg", "com.chiho.itchat4java.model.MsgDO"),
	LOGOUT("logout", "com.chiho.itchat4java.model.StatusResponseDO"),
	UPDATE_CHATROOM("update_chatroom", "com.chiho.itchat4java.model.ContactDO"),
	UPDATE_FRIEND("update_friend", "com.chiho.itchat4java.model.ContactDO"),
	GET_CONTACT("get_contact", "com.chiho.itchat4java.model.ContactDO"),
	GET_FRIENDS("get_friends", "com.chiho.itchat4java.model.ContactDO"),
	GET_CHATROOMS("get_chatrooms", "com.chiho.itchat4java.model.ContactDO"),
	GET_MPS("get_mps", "com.chiho.itchat4java.model.ContactDO"),
	SET_ALIAS("set_alias", "com.chiho.itchat4java.model.StatusResponseDO"),
	SET_PINNED("set_pinned", "com.chiho.itchat4java.model.StatusResponseDO"),
	ADD_FRIEND("add_friend", "com.chiho.itchat4java.model.StatusResponseDO"),
	GET_HEAD_IMG("get_head_img", "com.chiho.itchat4java.model.HeadImgDO"),
	CREATE_CHATROOM("create_chatroom", "com.chiho.itchat4java.model.CreateChatroomDO"),
	SET_CHATROOM_NAME("set_chatroom_name", "com.chiho.itchat4java.model.ModifyChatroomDO"),
	DELETE_MEMBER_FROM_CHATROOM("delete_member_from_chatroom", "com.chiho.itchat4java.model.ModifyChatroomDO"),
	ADD_MEMBER_INTO_CHATROOM("add_member_into_chatroom", "com.chiho.itchat4java.model.ModifyChatroomDO"),
	SEND_MSG("send_msg", "com.chiho.itchat4java.model.MessageDO"),
	UPLOAD_FILE("upload_file", "com.chiho.itchat4java.model.UploadFileDO"),
	SEND_FILE("send_file", "com.chiho.itchat4java.model.MessageDO"),
	SEND_IMAGE("send_image", "com.chiho.itchat4java.model.MessageDO"),
	SEND_VIDEO("send_video", "com.chiho.itchat4java.model.MessageDO"),
	SEND("send", ""),
	REVOKE("revoke", ""),
	DUMP_LOGIN_STATUS("dump_login_status", ""),
	LOAD_LOGIN_STATUS("load_login_status", "BaseResponseDO"),
	AUTO_LOGIN("auto_login", ""),
	CONFIGURED_REPLY("configured_reply", ""),
	MSG_REGISTER("msg_register", ""),
	RUN("run", ""),
	SEARCH_FRIENDS("search_friends", ""),
	SEARCH_CHATROOMS("search_chatrooms", ""),
	SEARCH_MPS("search_mps", ""),
	LOGIN_QRCALLBACK("login_qrCallback", "com.chiho.itchat4java.model.QrCodeDO"),
	LOGIN_LOGINCALLBACK("login_loginCallback", ""),
	LOGIN_EXITCALLBACK("login_exitCallback", ""),
	LOGINSTATUS_LOGINCALLBACK("loginStatus_loginCallback", ""),
	LOGINSTATUS_EXITCALLBACK("loginStatus_exitCallback", "");

	private String type;
	private String respType;

	CmdTypeEnum(String type, String respType) {
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
