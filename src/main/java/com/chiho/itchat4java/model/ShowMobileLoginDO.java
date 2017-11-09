package com.chiho.itchat4java.model;

import com.alibaba.fastjson.annotation.JSONField;

public class ShowMobileLoginDO extends StatusResponseDO {

	private static final long serialVersionUID = -538775993185659416L;
	@JSONField( name = "MsgId" )
	private String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId( String msgId ) {
		this.msgId = msgId;
	}
}
