package com.chiho.itchat.shell.model;

import com.alibaba.fastjson.annotation.JSONField;

public class UploadFileDO extends StatusResponseDO {

	private static final long serialVersionUID = -4746493724995212603L;
	@JSONField(name = "CDNThumbImgHeight")
	private Integer cDNThumbImgHeight;
	@JSONField(name = "CDNThumbImgWidth")
	private Integer cDNThumbImgWidth;
	@JSONField(name = "MediaId")
	private String mediaId;
	@JSONField(name = "StartPos")
	private Long startPos;

	public Integer getcDNThumbImgHeight() {
		return cDNThumbImgHeight;
	}

	public void setcDNThumbImgHeight( Integer cDNThumbImgHeight ) {
		this.cDNThumbImgHeight = cDNThumbImgHeight;
	}

	public Integer getcDNThumbImgWidth() {
		return cDNThumbImgWidth;
	}

	public void setcDNThumbImgWidth( Integer cDNThumbImgWidth ) {
		this.cDNThumbImgWidth = cDNThumbImgWidth;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId( String mediaId ) {
		this.mediaId = mediaId;
	}

	public Long getStartPos() {
		return startPos;
	}

	public void setStartPos( Long startPos ) {
		this.startPos = startPos;
	}
}
