package com.chiho.itchat4java.enums;

/**
 * 性别
 */
public enum SexEnum {

	UNKNOWN(0, "未知"),
	MALE(1, "男"),
	FEMALE(2, "女");

	private Integer code;
	private String desc;

	SexEnum( Integer code, String desc ) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
