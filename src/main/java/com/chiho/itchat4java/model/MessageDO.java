package com.chiho.itchat4java.model;

import com.alibaba.fastjson.annotation.JSONField;

public class MessageDO extends StatusResponseDO {

	private static final long serialVersionUID = -3969948371201979060L;
	@JSONField(name = "MsgID")
	private String msgId;
	@JSONField(name = "LocalID")
	private String localId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId( String msgId ) {
		this.msgId = msgId;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId( String localId ) {
		this.localId = localId;
	}
}
