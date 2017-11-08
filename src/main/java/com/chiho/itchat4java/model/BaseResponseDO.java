package com.chiho.itchat4java.model;

import java.io.Serializable;

public class BaseResponseDO implements Serializable {

	private static final long serialVersionUID = 2484476236430464645L;
	private String errMsg;
	private String ret;
	private String rawMsg;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg( String errMsg ) {
		this.errMsg = errMsg;
	}

	public String getRet() {
		return ret;
	}

	public void setRet( String ret ) {
		this.ret = ret;
	}

	public String getRawMsg() {
		return rawMsg;
	}

	public void setRawMsg( String rawMsg ) {
		this.rawMsg = rawMsg;
	}
}
