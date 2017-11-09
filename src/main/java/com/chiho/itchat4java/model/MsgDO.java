package com.chiho.itchat4java.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.List;

public class MsgDO implements Serializable{

	private static final long serialVersionUID = 2753470998607402061L;
	@JSONField(name="AddMsgList")
	private List<MessageDO> addMsgList;
	@JSONField(name="ModContactList")
	private List<ContactDO> modContactList;

	public List<MessageDO> getAddMsgList() {
		return addMsgList;
	}

	public void setAddMsgList( List<MessageDO> addMsgList ) {
		this.addMsgList = addMsgList;
	}

	public List<ContactDO> getModContactList() {
		return modContactList;
	}

	public void setModContactList( List<ContactDO> modContactList ) {
		this.modContactList = modContactList;
	}
}
