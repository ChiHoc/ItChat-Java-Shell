package com.chiho.itchat4java.model;

import java.io.Serializable;
import java.util.List;

public class WebInitDO implements Serializable {

	private static final long serialVersionUID = 4177198637499777765L;
	private Integer count;
	private SyncKeyDO syncKey;
	private Long systemTime;
	private String sKey;
	private Long clientVersion;
	private BaseResponseDO baseResponse;
	private Integer mPSubscribeMsgCount;
	private Integer grayScale;
	private String chatSet;
	private Integer inviteStartCount;
	private ContactDO user;
	private List<MpSubscribeMsgDO> mPSubscribeMsgList;
	private List<ContactDO> contactList;
	private Integer clickReportInterval;

	public Integer getCount() {
		return count;
	}

	public void setCount( Integer count ) {
		this.count = count;
	}

	public SyncKeyDO getSyncKey() {
		return syncKey;
	}

	public void setSyncKey( SyncKeyDO syncKey ) {
		this.syncKey = syncKey;
	}

	public Long getSystemTime() {
		return systemTime;
	}

	public void setSystemTime( Long systemTime ) {
		this.systemTime = systemTime;
	}

	public String getsKey() {
		return sKey;
	}

	public void setsKey( String sKey ) {
		this.sKey = sKey;
	}

	public Long getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion( Long clientVersion ) {
		this.clientVersion = clientVersion;
	}

	public BaseResponseDO getBaseResponse() {
		return baseResponse;
	}

	public void setBaseResponse( BaseResponseDO baseResponse ) {
		this.baseResponse = baseResponse;
	}

	public Integer getmPSubscribeMsgCount() {
		return mPSubscribeMsgCount;
	}

	public void setmPSubscribeMsgCount( Integer mPSubscribeMsgCount ) {
		this.mPSubscribeMsgCount = mPSubscribeMsgCount;
	}

	public Integer getGrayScale() {
		return grayScale;
	}

	public void setGrayScale( Integer grayScale ) {
		this.grayScale = grayScale;
	}

	public String getChatSet() {
		return chatSet;
	}

	public void setChatSet( String chatSet ) {
		this.chatSet = chatSet;
	}

	public Integer getInviteStartCount() {
		return inviteStartCount;
	}

	public void setInviteStartCount( Integer inviteStartCount ) {
		this.inviteStartCount = inviteStartCount;
	}

	public ContactDO getUser() {
		return user;
	}

	public void setUser( ContactDO user ) {
		this.user = user;
	}

	public List<MpSubscribeMsgDO> getmPSubscribeMsgList() {
		return mPSubscribeMsgList;
	}

	public void setmPSubscribeMsgList( List<MpSubscribeMsgDO> mPSubscribeMsgList ) {
		this.mPSubscribeMsgList = mPSubscribeMsgList;
	}

	public List<ContactDO> getContactList() {
		return contactList;
	}

	public void setContactList( List<ContactDO> contactList ) {
		this.contactList = contactList;
	}

	public Integer getClickReportInterval() {
		return clickReportInterval;
	}

	public void setClickReportInterval( Integer clickReportInterval ) {
		this.clickReportInterval = clickReportInterval;
	}

	public static class SyncKeyDO implements Serializable{

		private static final long serialVersionUID = 8137699321024686196L;
		private Integer count;
		private List<SyncKeyPair> list;

		public Integer getCount() {
			return count;
		}

		public void setCount( Integer count ) {
			this.count = count;
		}

		public List<SyncKeyPair> getList() {
			return list;
		}

		public void setList( List<SyncKeyPair> list ) {
			this.list = list;
		}

		public static class SyncKeyPair  implements Serializable{

			private static final long serialVersionUID = -382066930873129172L;
			private Integer val;
			private Integer key;

			public Integer getVal() {
				return val;
			}

			public void setVal( Integer val ) {
				this.val = val;
			}

			public Integer getKey() {
				return key;
			}

			public void setKey( Integer key ) {
				this.key = key;
			}
		}
	}
}
