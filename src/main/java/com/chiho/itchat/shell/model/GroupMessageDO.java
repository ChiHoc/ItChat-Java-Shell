package com.chiho.itchat.shell.model;

public class GroupMessageDO extends MessageDO {

	private static final long serialVersionUID = 4827371960432422944L;
	private boolean isAt;
	private String actualNickName;
	private String text;

	public boolean isAt() {
		return isAt;
	}

	public void setAt( boolean at ) {
		isAt = at;
	}

	public String getActualNickName() {
		return actualNickName;
	}

	public void setActualNickName( String actualNickName ) {
		this.actualNickName = actualNickName;
	}

	public String getText() {
		return text;
	}

	public void setText( String text ) {
		this.text = text;
	}
}
