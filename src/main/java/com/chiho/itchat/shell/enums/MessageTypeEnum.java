package com.chiho.itchat.shell.enums;

public enum MessageTypeEnum {

	TEXT("Text"),
	MAP("Map"),
	CARD("Card"),
	NOTE("Note"),
	SHARING("Sharing"),
	PICTURE("Picture"),
	RECORDING("Recording"),
	ATTACHMENT("Attachment"),
	VIDEO("Video"),
	FRIENDS("Friends"),
	SYSTEM("System");

	private String type;

	MessageTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
