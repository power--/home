package com.goparty.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "friendRequest")
public class FriendRequest {
	private String remarkName;
	
	private String groupId;
	
	private String groupName;

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	

}
