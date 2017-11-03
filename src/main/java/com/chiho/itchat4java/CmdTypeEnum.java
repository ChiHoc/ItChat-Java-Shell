package com.chiho.itchat4java;

public enum CmdTypeEnum {

	LOGIN("login", "1"),
	AUTO_LOGIN("auto_login", "2"),
	GET_QRUUID("get_QRuuid", "3"),
	GET_QR("get_QR", "4"),
	CHECK_LOGIN("check_login", "5"),
	WEB_INIT("web_init", "6"),
	SHOW_MOBILE_LOGIN("show_mobile_login", "7"),
	START_RECEIVING("start_receiving", "8"),
	GET_MSG("get_msg", "9"),
	LOGOUT("logout", "10"),
	UPDATE_CHATROOM("update_chatroom", "11"),
	UPDATE_FRIEND("update_friend", "12"),
	GET_CONTACT("get_contact", "13"),
	GET_FRIENDS("get_friends", "14"),
	GET_CHATROOMS("get_chatrooms", "15"),
	GET_MPS("get_mps", "16"),
	SET_ALIAS("set_alias", "17"),
	SET_PINNED("set_pinned", "18"),
	ADD_FRIEND("add_friend", "19"),
	GET_HEAD_IMG("get_head_img", "20"),
	CREATE_CHATROOM("create_chatroom", "21"),
	SET_CHATROOM_NAME("set_chatroom_name", "22"),
	DELETE_MEMBER_FROM_CHATROOM("delete_member_from_chatroom", "23"),
	ADD_MEMBER_INTO_CHATROOM("add_member_into_chatroom", "24"),
	SEND_RAW_MSG("send_raw_msg", "25"),
	SEND_MSG("send_msg", "26"),
	UPLOAD_FILE("upload_file", "27"),
	SEND_FILE("send_file", "28"),
	SEND_IMAGE("send_image", "29"),
	SEND_VIDEO("send_video", "30"),
	SEND("send", "31"),
	REVOKE("revoke", "32"),
	DUMP_LOGIN_STATUS("dump_login_status", "33"),
	LOAD_LOGIN_STATUS("load_login_status", "34"),
	CONFIGURED_REPLY("configured_reply", "35"),
	MSG_REGISTER("msg_register", "36"),
	RUN("run", "37"),
	SEARCH_FRIENDS("search_friends", "38"),
	SEARCH_CHATROOMS("search_chatrooms", "39"),
	SEARCH_MPS("search_mps", "40");

	private String type;
	private String code;

	CmdTypeEnum(String type, String code) {
		this.type = type;
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public String getCode() {
		return code;
	}
}
