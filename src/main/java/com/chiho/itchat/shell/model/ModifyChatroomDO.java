package com.chiho.itchat.shell.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class ModifyChatroomDO extends StatusResponseDO {

	private static final long serialVersionUID = -8572163633378717007L;
	@JSONField(name="MemberCount")
	private Integer memberCount;
	@JSONField(name="MemberList")
	private List<ContactDO> memberList;

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount( Integer memberCount ) {
		this.memberCount = memberCount;
	}

	public List<ContactDO> getMemberList() {
		return memberList;
	}

	public void setMemberList( List<ContactDO> memberList ) {
		this.memberList = memberList;
	}
}
