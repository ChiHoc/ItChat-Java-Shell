package com.chiho.itchat4java.model;

import java.io.Serializable;
import java.util.List;

public class MpSubscribeMsgDO implements Serializable {

	private static final long serialVersionUID = -6911455745949980095L;
	private String userName;
	private List<MpArticleDO> mPArticleList;
	private String nickName;
	private Integer mPArticleCount;
	private Long time;

	public String getUserName() {
		return userName;
	}

	public void setUserName( String userName ) {
		this.userName = userName;
	}

	public List<MpArticleDO> getmPArticleList() {
		return mPArticleList;
	}

	public void setmPArticleList( List<MpArticleDO> mPArticleList ) {
		this.mPArticleList = mPArticleList;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName( String nickName ) {
		this.nickName = nickName;
	}

	public Integer getmPArticleCount() {
		return mPArticleCount;
	}

	public void setmPArticleCount( Integer mPArticleCount ) {
		this.mPArticleCount = mPArticleCount;
	}

	public Long getTime() {
		return time;
	}

	public void setTime( Long time ) {
		this.time = time;
	}

	public static class MpArticleDO implements Serializable {

		private static final long serialVersionUID = -67800471644316752L;
		private String url;
		private String cover;
		private String digest;
		private String title;

		public String getUrl() {
			return url;
		}

		public void setUrl( String url ) {
			this.url = url;
		}

		public String getCover() {
			return cover;
		}

		public void setCover( String cover ) {
			this.cover = cover;
		}

		public String getDigest() {
			return digest;
		}

		public void setDigest( String digest ) {
			this.digest = digest;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle( String title ) {
			this.title = title;
		}
	}
}
