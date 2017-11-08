package com.chiho.itchat4java.model;

import java.io.Serializable;

public class ShowMobileLoginDO implements Serializable{

	private static final long serialVersionUID = -538775993185659416L;
	private String msgId;
	private BaseResponseDO baseResponse;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId( String msgId ) {
		this.msgId = msgId;
	}

	public BaseResponseDO getBaseResponse() {
		return baseResponse;
	}

	public void setBaseResponse( BaseResponseDO baseResponse ) {
		this.baseResponse = baseResponse;
	}
}
