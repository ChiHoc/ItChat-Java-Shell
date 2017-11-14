package com.chiho.itchat.shell.enums;

/**
 * 确认添加好友Enum
 */
public enum AddFriendStatusEnum {

	ADD(2, "添加"),
	ACCEPT(3, "接受");

	private int code;
	private String desc;

	private AddFriendStatusEnum( int code, String desc ) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
