package com.chiho.itchat4java.model;

import com.chiho.itchat4java.enums.SexEnum;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDO implements Serializable {

	private static final long serialVersionUID = -7818423109552121015L;

	private Integer chatRoomId;
	private SexEnum sex;
	private Integer attrStatus;
	private Integer statues;
	private String pYQuanPin;
	private String encryChatRoomId;
	private String displayName;
	private Integer verifyFlag;
	private Integer uniFriend;
	private Integer contactFlag;
	private String userName;
	private Map<String, ContactDO> memberList;
	private Integer starFriend;
	private String headImgUrl;
	private Integer appAccountFlag;
	private Integer memberCount;
	private String remarkPYInitial;
	private String city;
	private String nickName;
	private String province;
	private Integer snsFlag;
	private String alias;
	private String keyWord;
	private Integer hideInputBarFlag;
	private String signature;
	private String remarkName;
	private String remarkPYQuanPin;
	private Long uin;
	private Long ownerUin;
	private Integer Integer;
	private String pYInitial;

	public Integer getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId( Integer chatRoomId ) {
		this.chatRoomId = chatRoomId;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex( SexEnum sex ) {
		this.sex = sex;
	}

	public Integer getAttrStatus() {
		return attrStatus;
	}

	public void setAttrStatus( Integer attrStatus ) {
		this.attrStatus = attrStatus;
	}

	public Integer getStatues() {
		return statues;
	}

	public void setStatues( Integer statues ) {
		this.statues = statues;
	}

	public String getpYQuanPin() {
		return pYQuanPin;
	}

	public void setpYQuanPin( String pYQuanPin ) {
		this.pYQuanPin = pYQuanPin;
	}

	public String getEncryChatRoomId() {
		return encryChatRoomId;
	}

	public void setEncryChatRoomId( String encryChatRoomId ) {
		this.encryChatRoomId = encryChatRoomId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName( String displayName ) {
		this.displayName = displayName;
	}

	public Integer getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag( Integer verifyFlag ) {
		this.verifyFlag = verifyFlag;
	}

	public Integer getUniFriend() {
		return uniFriend;
	}

	public void setUniFriend( Integer uniFriend ) {
		this.uniFriend = uniFriend;
	}

	public Integer getContactFlag() {
		return contactFlag;
	}

	public void setContactFlag( Integer contactFlag ) {
		this.contactFlag = contactFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName( String userName ) {
		this.userName = userName;
	}

	public Map<String, ContactDO> getMemberList() {
		return memberList;
	}

	public void setMemberList( List<ContactDO> memberList ) {
		this.memberList = new HashMap<>();
		memberList.forEach(contactDO -> {
			this.memberList.put(contactDO.getUserName(), contactDO);
		});
	}

	public Integer getStarFriend() {
		return starFriend;
	}

	public void setStarFriend( Integer starFriend ) {
		this.starFriend = starFriend;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl( String headImgUrl ) {
		this.headImgUrl = headImgUrl;
	}

	public Integer getAppAccountFlag() {
		return appAccountFlag;
	}

	public void setAppAccountFlag( Integer appAccountFlag ) {
		this.appAccountFlag = appAccountFlag;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount( Integer memberCount ) {
		this.memberCount = memberCount;
	}

	public String getRemarkPYInitial() {
		return remarkPYInitial;
	}

	public void setRemarkPYInitial( String remarkPYInitial ) {
		this.remarkPYInitial = remarkPYInitial;
	}

	public String getCity() {
		return city;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName( String nickName ) {
		this.nickName = nickName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince( String province ) {
		this.province = province;
	}

	public Integer getSnsFlag() {
		return snsFlag;
	}

	public void setSnsFlag( Integer snsFlag ) {
		this.snsFlag = snsFlag;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias( String alias ) {
		this.alias = alias;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord( String keyWord ) {
		this.keyWord = keyWord;
	}

	public Integer getHideInputBarFlag() {
		return hideInputBarFlag;
	}

	public void setHideInputBarFlag( Integer hideInputBarFlag ) {
		this.hideInputBarFlag = hideInputBarFlag;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature( String signature ) {
		this.signature = signature;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName( String remarkName ) {
		this.remarkName = remarkName;
	}

	public String getRemarkPYQuanPin() {
		return remarkPYQuanPin;
	}

	public void setRemarkPYQuanPin( String remarkPYQuanPin ) {
		this.remarkPYQuanPin = remarkPYQuanPin;
	}

	public Long getUin() {
		return uin;
	}

	public void setUin( Long uin ) {
		this.uin = uin;
	}

	public Long getOwnerUin() {
		return ownerUin;
	}

	public void setOwnerUin( Long ownerUin ) {
		this.ownerUin = ownerUin;
	}

	public Integer getInteger() {
		return Integer;
	}

	public void setInteger( Integer integer ) {
		Integer = integer;
	}

	public String getpYInitial() {
		return pYInitial;
	}

	public void setpYInitial( String pYInitial ) {
		this.pYInitial = pYInitial;
	}
}
