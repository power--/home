package com.goparty.data.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userFriend")
public class UserFriend {

	private String userId;
	
	private String friendId;



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
}
