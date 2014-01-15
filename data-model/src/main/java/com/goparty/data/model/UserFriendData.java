package com.goparty.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userFriends")
public class UserFriendData {
	
	private String id;
	
	private String userId;
	
	private String friendId;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
