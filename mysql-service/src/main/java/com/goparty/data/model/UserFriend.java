package com.goparty.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userFriend")
@Entity
@IdClass(UserFriendPK.class)
@Table(name="gp_user_friend")
public class UserFriend {
 
	@Id
	private String userId;
	
	@Id 
	private String friendId;

	private String status;
	
	
	private Date updateTime;

	@Column(name = "remarkName", nullable = true)
	private String remarkName;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	
	@PreUpdate
    public void preUpdate() {
		updateTime = new Date();
    }
	
	@PrePersist
    public void prePersist() {
		updateTime = new Date();
	}
}
