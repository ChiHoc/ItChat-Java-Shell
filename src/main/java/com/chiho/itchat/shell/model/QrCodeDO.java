package com.chiho.itchat.shell.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class QrCodeDO implements Serializable {

	private static final long serialVersionUID = -8063306248325329749L;
	@JSONField(name="qrcode")
	private String qrcode;
	@JSONField(name="uuid")
	private String uuid;
	@JSONField(name="status")
	private Integer status;

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode( String qrcode ) {
		this.qrcode = qrcode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid( String uuid ) {
		this.uuid = uuid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus( Integer status ) {
		this.status = status;
	}
}
