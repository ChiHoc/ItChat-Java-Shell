package com.chiho.itchat.shell.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class CreateChatroomDO extends StatusResponseDO {

	private static final long serialVersionUID = 2621330087494280150L;
	@JSONField(name="ChatRoomName")
	private String chatRoomName;
	@JSONField(name="MemberCount")
	private Integer memberCount;
	@JSONField(name="PYInitial")
	private String pYInitial;
	@JSONField(name="QuanPin")
	private String quanPin;
	@JSONField(name="MemberList")
	private List<ContactDO> memberList;
	@JSONField(name="Topic")
	private String topic;

	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName( String chatRoomName ) {
		this.chatRoomName = chatRoomName;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount( Integer memberCount ) {
		this.memberCount = memberCount;
	}

	public String getpYInitial() {
		return pYInitial;
	}

	public void setpYInitial( String pYInitial ) {
		this.pYInitial = pYInitial;
	}

	public String getQuanPin() {
		return quanPin;
	}

	public void setQuanPin( String quanPin ) {
		this.quanPin = quanPin;
	}

	public List<ContactDO> getMemberList() {
		return memberList;
	}

	public void setMemberList( List<ContactDO> memberList ) {
		this.memberList = memberList;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic( String topic ) {
		this.topic = topic;
	}
}
