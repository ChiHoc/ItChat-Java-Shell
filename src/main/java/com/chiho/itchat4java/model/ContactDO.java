package com.chiho.itchat4java.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.chiho.itchat4java.enums.SexEnum;
import java.io.Serializable;
import java.util.List;

public class ContactDO implements Serializable {

	private static final long serialVersionUID = -7818423109552121015L;

	@JSONField( name = "ChatRoomId" )
	private Integer chatRoomId;
	@JSONField( name = "Sex" )
	private SexEnum sex;
	@JSONField( name = "AttrStatus" )
	private Long attrStatus;
	@JSONField( name = "Statues" )
	private Integer statues;
	@JSONField( name = "PYQuanPin" )
	private String pYQuanPin;
	@JSONField( name = "EncryChatRoomId" )
	private String encryChatRoomId;
	@JSONField( name = "DisplayName" )
	private String displayName;
	@JSONField( name = "VerifyFlag" )
	private Integer verifyFlag;
	@JSONField( name = "UniFriend" )
	private Integer uniFriend;
	@JSONField( name = "ContactFlag" )
	private Integer contactFlag;
	@JSONField( name = "UserName" )
	private String userName;
	@JSONField( name = "MemberList" )
	private List<ContactDO> memberList;
	@JSONField( name = "StarFriend" )
	private Integer starFriend;
	@JSONField( name = "HeadImgUrl" )
	private String headImgUrl;
	@JSONField( name = "AppAccountFlag" )
	private Integer appAccountFlag;
	@JSONField( name = "MemberCount" )
	private Integer memberCount;
	@JSONField( name = "RemarkPYInitial" )
	private String remarkPYInitial;
	@JSONField( name = "City" )
	private String city;
	@JSONField( name = "NickName" )
	private String nickName;
	@JSONField( name = "Province" )
	private String province;
	@JSONField( name = "SnsFlag" )
	private Integer snsFlag;
	@JSONField( name = "Alias" )
	private String alias;
	@JSONField( name = "KeyWord" )
	private String keyWord;
	@JSONField( name = "HideInputBarFlag" )
	private Integer hideInputBarFlag;
	@JSONField( name = "Signature" )
	private String signature;
	@JSONField( name = "RemarkName" )
	private String remarkName;
	@JSONField( name = "RemarkPYQuanPin" )
	private String remarkPYQuanPin;
	@JSONField( name = "Uin" )
	private Long uin;
	@JSONField( name = "OwnerUin" )
	private Long ownerUin;
	@JSONField( name = "Integer" )
	private Integer Integer;
	@JSONField( name = "PYInitial" )
	private String pYInitial;
	@JSONField( name = "Self" )
	private ContactDO self;
	@JSONField( name = "IsOwner" )
	private Integer isOwner;
	@JSONField( name = "IsAdmin" )
	private String isAdmin;

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

	public Long getAttrStatus() {
		return attrStatus;
	}

	public void setAttrStatus( Long attrStatus ) {
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

	public List<ContactDO> getMemberList() {
		return memberList;
	}

	public void setMemberList( List<ContactDO> memberList ) {
		this.memberList = memberList;
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

	public ContactDO getSelf() {
		return self;
	}

	public void setSelf( ContactDO self ) {
		this.self = self;
	}

	public java.lang.Integer getIsOwner() {
		return isOwner;
	}

	public void setIsOwner( java.lang.Integer isOwner ) {
		this.isOwner = isOwner;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin( String isAdmin ) {
		this.isAdmin = isAdmin;
	}
}
