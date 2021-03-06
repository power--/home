package com.goparty.data.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userFriend")
@Entity
@IdClass(UserFriendPK.class)
@Table(name="gp_user_friend")
public class UserFriend {
 

	public final static String STATUS_NORMAL = "NORMAL";
	public final static String STATUS_BLACK = "BLACK"; 
	
	@Id
	private String userId;
	
	@Id 
	private String friendId;

	private String status;
	
	private Date updateTime;
	  
	private String remarkName;
	
	@Transient
	private List<Group> groups;
	
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

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
 
	
	
//	@PreUpdate
//    public void preUpdate() {
//		updateTime = new Date();
//    }
//	
//	@PrePersist
//    public void prePersist() {
//		updateTime = new Date();
//	}
}
