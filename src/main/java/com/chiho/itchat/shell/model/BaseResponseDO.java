package com.chiho.itchat.shell.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class BaseResponseDO implements Serializable {

	private static final long serialVersionUID = 2484476236430464645L;

	@JSONField( name = "ErrMsg" )
	private String errMsg;
	@JSONField( name = "Ret" )
	private Integer ret;
	@JSONField( name = "RawMsg" )
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
