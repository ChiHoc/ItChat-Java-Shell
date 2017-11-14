package com.chiho.itchat4java.model;

import com.alibaba.fastjson.annotation.JSONField;

public class RevokeDO extends StatusResponseDO {

	private static final long serialVersionUID = 3503891519685752416L;
	@JSONField(name = "Introduction")
	private String introduction;
	@JSONField(name = "SysWording")
	private String sysWording;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction( String introduction ) {
		this.introduction = introduction;
	}

	public String getSysWording() {
		return sysWording;
	}

	public void setSysWording( String sysWording ) {
		this.sysWording = sysWording;
	}
}
