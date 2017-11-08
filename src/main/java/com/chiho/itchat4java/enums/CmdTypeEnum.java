package com.chiho.itchat4java.enums;

public enum CmdTypeEnum {

	LOGIN("login", ""),
	GET_QRUUID("get_QRuuid", "String"),
	GET_QR("get_QR", "String"),
	CHECK_LOGIN("check_login", "String"),
	WEB_INIT("web_init", "com.chiho.itchat4java.model.WebInitDO"),
	SHOW_MOBILE_LOGIN("show_mobile_login", "com.chiho.itchat4java.model.ShowMobileLoginDO"),
	START_RECEIVING("start_receiving", "7"),
	GET_MSG("get_msg", "8"),
	LOGOUT("logout", "9"),
	UPDATE_CHATROOM("update_chatroom", "10"),
	UPDATE_FRIEND("update_friend", "11"),
	GET_CONTACT("get_contact", "12"),
	GET_FRIENDS("get_friends", "13"),
	GET_CHATROOMS("get_chatrooms", "14"),
	GET_MPS("get_mps", "15"),
	SET_ALIAS("set_alias", "16"),
	SET_PINNED("set_pinned", "17"),
	ADD_FRIEND("add_friend", "18"),
	GET_HEAD_IMG("get_head_img", "19"),
	CREATE_CHATROOM("create_chatroom", "20"),
	SET_CHATROOM_NAME("set_chatroom_name", "21"),
	DELETE_MEMBER_FROM_CHATROOM("delete_member_from_chatroom", "22"),
	ADD_MEMBER_INTO_CHATROOM("add_member_into_chatroom", "23"),
	SEND_RAW_MSG("send_raw_msg", "24"),
	SEND_MSG("send_msg", "25"),
	UPLOAD_FILE("upload_file", "26"),
	SEND_FILE("send_file", "27"),
	SEND_IMAGE("send_image", "28"),
	SEND_VIDEO("send_video", "29"),
	SEND("send", "30"),
	REVOKE("revoke", "31"),
	DUMP_LOGIN_STATUS("dump_login_status", ""),
	LOAD_LOGIN_STATUS("load_login_status", "BaseResponseDO"),
	AUTO_LOGIN("auto_login", ""),
	CONFIGURED_REPLY("configured_reply", "35"),
	MSG_REGISTER("msg_register", "36"),
	RUN("run", "37"),
	SEARCH_FRIENDS("search_friends", "38"),
	SEARCH_CHATROOMS("search_chatrooms", "39"),
	SEARCH_MPS("search_mps", "40"),
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
