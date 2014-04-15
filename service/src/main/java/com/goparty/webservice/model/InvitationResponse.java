package com.goparty.webservice.model;

import java.util.Date;

public class InvitationResponse {
	private String invitationId;
	private UserResponse inviter;
	private String inviterMessage;
	
	private UserResponse invitee;
	private String inviteeMessage;
	private String status;
	private Date updateTime;
	
	public String getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}
	public UserResponse getInviter() {
		return inviter;
	}
	public void setInviter(UserResponse inviter) {
		this.inviter = inviter;
	}
	public String getInviterMessage() {
		return inviterMessage;
	}
	public void setInviterMessage(String inviterMessage) {
		this.inviterMessage = inviterMessage;
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
	public UserResponse getInvitee() {
		return invitee;
	}
	public void setInvitee(UserResponse invitee) {
		this.invitee = invitee;
	}
	public String getInviteeMessage() {
		return inviteeMessage;
	}
	public void setInviteeMessage(String inviteeMessage) {
		this.inviteeMessage = inviteeMessage;
	}
	
	

}
