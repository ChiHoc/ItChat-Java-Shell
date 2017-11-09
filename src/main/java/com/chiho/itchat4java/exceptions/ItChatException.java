package com.chiho.itchat4java.exceptions;

import com.chiho.itchat4java.model.BaseResponseDO;

public class ItChatException extends Exception {

	public ItChatException() {
	}

	public ItChatException(BaseResponseDO response) {
		this.errMsg = response.getErrMsg();
		this.ret = response.getRet();
		this.rawMsg = response.getRawMsg();
	}

	private String errMsg;
	private Integer ret;
	private String rawMsg;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg( String errMsg ) {
		this.errMsg = errMsg;
	}

	public Integer getRet() {
		return ret;
	}

	public void setRet( Integer ret ) {
		this.ret = ret;
	}

	public String getRawMsg() {
		return rawMsg;
	}

	public void setRawMsg( String rawMsg ) {
		this.rawMsg = rawMsg;
	}
}
