package com.chiho.itchat.shell.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class MessageDO implements Serializable {

	private static final long serialVersionUID = 3179198859232501326L;
	@JSONField( name = "ImgWidth" )
	private Integer imgWidth;
	@JSONField( name = "FromUserName" )
	private String fromUserName;
	@JSONField( name = "PlayLegth" )
	private Integer playLegth;
	@JSONField( name = "OriContent" )
	private String oriContent;
	@JSONField( name = "RecommendInfo" )
	private RecommendInfoDO recommendInfo;
	@JSONField( name = "Content" )
	private String content;
	@JSONField( name = "StatusNotifyUserName" )
	private String statusNotifyUserName;
	@JSONField( name = "StatusNotifyCode" )
	private Integer statusNotifyCode;
	@JSONField( name = "NewMsgId" )
	private Long newMsgId;
	@JSONField( name = "Status" )
	private Integer status;
	@JSONField( name = "VoiceLength" )
	private Integer voiceLength;
	@JSONField( name = "ToUserName" )
	private String toUserName;
	@JSONField( name = "ForwardFlag" )
	private Integer forwardFlag;
	@JSONField( name = "AppMsgType" )
	private Integer appMsgType;
	@JSONField( name = "Ticket" )
	private String ticket;
	@JSONField( name = "AppInfo" )
	private AppInfoDO appInfo;
	@JSONField( name = "Url" )
	private String url;
	@JSONField( name = "ImgStatus" )
	private Integer imgStatus;
	@JSONField( name = "MsgType" )
	private Integer msgType;
	@JSONField( name = "ImgHeight" )
	private Integer imgHeight;
	@JSONField( name = "MediaId" )
	private String mediaId;
	@JSONField( name = "MsgId" )
	private String msgId;
	@JSONField( name = "FileName" )
	private String fileName;
	@JSONField( name = "HasProductId" )
	private Integer hasProductId;
	@JSONField( name = "FileSize" )
	private String fileSize;
	@JSONField( name = "CreateTime" )
	private Long createTime;
	@JSONField( name = "SubMsgType" )
	private Integer subMsgType;

	public Integer getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth( Integer imgWidth ) {
		this.imgWidth = imgWidth;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName( String fromUserName ) {
		this.fromUserName = fromUserName;
	}

	public Integer getPlayLegth() {
		return playLegth;
	}

	public void setPlayLegth( Integer playLegth ) {
		this.playLegth = playLegth;
	}

	public String getOriContent() {
		return oriContent;
	}

	public void setOriContent( String oriContent ) {
		this.oriContent = oriContent;
	}

	public RecommendInfoDO getRecommendInfo() {
		return recommendInfo;
	}

	public void setRecommendInfo( RecommendInfoDO recommendInfo ) {
		this.recommendInfo = recommendInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent( String content ) {
		this.content = content;
	}

	public String getStatusNotifyUserName() {
		return statusNotifyUserName;
	}

	public void setStatusNotifyUserName( String statusNotifyUserName ) {
		this.statusNotifyUserName = statusNotifyUserName;
	}

	public Integer getStatusNotifyCode() {
		return statusNotifyCode;
	}

	public void setStatusNotifyCode( Integer statusNotifyCode ) {
		this.statusNotifyCode = statusNotifyCode;
	}

	public Long getNewMsgId() {
		return newMsgId;
	}

	public void setNewMsgId( Long newMsgId ) {
		this.newMsgId = newMsgId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus( Integer status ) {
		this.status = status;
	}

	public Integer getVoiceLength() {
		return voiceLength;
	}

	public void setVoiceLength( Integer voiceLength ) {
		this.voiceLength = voiceLength;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName( String toUserName ) {
		this.toUserName = toUserName;
	}

	public Integer getForwardFlag() {
		return forwardFlag;
	}

	public void setForwardFlag( Integer forwardFlag ) {
		this.forwardFlag = forwardFlag;
	}

	public Integer getAppMsgType() {
		return appMsgType;
	}

	public void setAppMsgType( Integer appMsgType ) {
		this.appMsgType = appMsgType;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket( String ticket ) {
		this.ticket = ticket;
	}

	public AppInfoDO getAppInfo() {
		return appInfo;
	}

	public void setAppInfo( AppInfoDO appInfo ) {
		this.appInfo = appInfo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}

	public Integer getImgStatus() {
		return imgStatus;
	}

	public void setImgStatus( Integer imgStatus ) {
		this.imgStatus = imgStatus;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType( Integer msgType ) {
		this.msgType = msgType;
	}

	public Integer getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight( Integer imgHeight ) {
		this.imgHeight = imgHeight;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId( String mediaId ) {
		this.mediaId = mediaId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId( String msgId ) {
		this.msgId = msgId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName( String fileName ) {
		this.fileName = fileName;
	}

	public Integer getHasProductId() {
		return hasProductId;
	}

	public void setHasProductId( Integer hasProductId ) {
		this.hasProductId = hasProductId;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize( String fileSize ) {
		this.fileSize = fileSize;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime( Long createTime ) {
		this.createTime = createTime;
	}

	public Integer getSubMsgType() {
		return subMsgType;
	}

	public void setSubMsgType( Integer subMsgType ) {
		this.subMsgType = subMsgType;
	}

	public static class AppInfoDO implements Serializable {

		private static final long serialVersionUID = -758672354005441509L;

		@JSONField( name = "Type" )
		private Integer type;
		@JSONField( name = "AppId" )
		private String appId;

		public Integer getType() {
			return type;
		}

		public void setType( Integer type ) {
			this.type = type;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId( String appId ) {
			this.appId = appId;
		}
	}

	public static class RecommendInfoDO implements Serializable {

		private static final long serialVersionUID = -8147729736796507758L;

		@JSONField( name = "UserName" )
		private String userName;
		@JSONField( name = "Province" )
		private String province;
		@JSONField( name = "City" )
		private String city;
		@JSONField( name = "Scene" )
		private Integer scene;
		@JSONField( name = "QQNum" )
		private Integer qQNum;
		@JSONField( name = "Content" )
		private String content;
		@JSONField( name = "Alias" )
		private String alias;
		@JSONField( name = "OpCode" )
		private Integer opCode;
		@JSONField( name = "Signature" )
		private String signature;
		@JSONField( name = "Ticket" )
		private String ticket;
		@JSONField( name = "Sex" )
		private Integer sex;
		@JSONField( name = "NickName" )
		private String nickName;
		@JSONField( name = "AttrStatus" )
		private Long attrStatus;
		@JSONField( name = "VerifyFlag" )
		private Integer verifyFlag;

		public String getUserName() {
			return userName;
		}

		public void setUserName( String userName ) {
			this.userName = userName;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince( String province ) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity( String city ) {
			this.city = city;
		}

		public Integer getScene() {
			return scene;
		}

		public void setScene( Integer scene ) {
			this.scene = scene;
		}

		public Integer getqQNum() {
			return qQNum;
		}

		public void setqQNum( Integer qQNum ) {
			this.qQNum = qQNum;
		}

		public String getContent() {
			return content;
		}

		public void setContent( String content ) {
			this.content = content;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias( String alias ) {
			this.alias = alias;
		}

		public Integer getOpCode() {
			return opCode;
		}

		public void setOpCode( Integer opCode ) {
			this.opCode = opCode;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature( String signature ) {
			this.signature = signature;
		}

		public String getTicket() {
			return ticket;
		}

		public void setTicket( String ticket ) {
			this.ticket = ticket;
		}

		public Integer getSex() {
			return sex;
		}

		public void setSex( Integer sex ) {
			this.sex = sex;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName( String nickName ) {
			this.nickName = nickName;
		}

		public Long getAttrStatus() {
			return attrStatus;
		}

		public void setAttrStatus( Long attrStatus ) {
			this.attrStatus = attrStatus;
		}

		public Integer getVerifyFlag() {
			return verifyFlag;
		}

		public void setVerifyFlag( Integer verifyFlag ) {
			this.verifyFlag = verifyFlag;
		}
	}
}