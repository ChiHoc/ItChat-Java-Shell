package com.chiho.itchat4java.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class StatusResponseDO implements Serializable{

	private static final long serialVersionUID = 268012006976653408L;
	@JSONField(name="BaseResponse")
	private BaseResponseDO baseResponse;

	public BaseResponseDO getBaseResponse() {
		return baseResponse;
	}

	public void setBaseResponse( BaseResponseDO baseResponse ) {
		this.baseResponse = baseResponse;
	}
}
