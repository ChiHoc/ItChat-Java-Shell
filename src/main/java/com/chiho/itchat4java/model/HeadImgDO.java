package com.chiho.itchat4java.model;

import com.alibaba.fastjson.annotation.JSONField;

public class HeadImgDO extends BaseResponseDO {

	private static final long serialVersionUID = -3990163333336466724L;

	@JSONField( name = "PostFix" )
	private String postFix;

	public String getPostFix() {
		return postFix;
	}

	public void setPostFix( String postFix ) {
		this.postFix = postFix;
	}
}
