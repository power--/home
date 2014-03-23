package com.goparty.webservice.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.goparty.data.model.Group;

@XmlRootElement(name = "response")
public class FriendResponse {
	private String friendId;	
	private String remarkName;	
	private List<Group> groups;
	
	
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	 
}
