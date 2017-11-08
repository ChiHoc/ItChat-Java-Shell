package com.chiho.itchat4java.model;

import java.io.Serializable;

public class QrCodeDO implements Serializable {

	private static final long serialVersionUID = -8063306248325329749L;
	private String qrcode;
	private String uuid;
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
