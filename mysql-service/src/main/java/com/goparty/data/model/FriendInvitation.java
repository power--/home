package com.goparty.data.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Indexed;

import com.goparty.data.constant.InvitationAcceptance;
import com.goparty.data.constant.InvitationStatus;

@XmlRootElement(name = "friendInvitation")
@Entity
@Table(name="gp_friend_invitation")
@Indexed
public class FriendInvitation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String inviterId;
	private String inviterMessage;
	
	private String inviteeId;
	private String inviteeMessage;
	
	@Enumerated(EnumType.STRING)
	private InvitationAcceptance acceptance;
	
	@Enumerated(EnumType.STRING)
	private InvitationStatus status;
	
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInviterId() {
		return inviterId;
	}

	public void setInviterId(String inviterId) {
		this.inviterId = inviterId;
	}

	public String getInviterMessage() {
		return inviterMessage;
	}

	public void setInviterMessage(String inviterMessage) {
		this.inviterMessage = inviterMessage;
	}

	public String getInviteeId() {
		return inviteeId;
	}

	public void setInviteeId(String inviteeId) {
		this.inviteeId = inviteeId;
	}

	public String getInviteeMessage() {
		return inviteeMessage;
	}

	public void setInviteeMessage(String inviteeMessage) {
		this.inviteeMessage = inviteeMessage;
	}

	public InvitationAcceptance getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(InvitationAcceptance acceptance) {
		this.acceptance = acceptance;
	}

	public InvitationStatus getStatus() {
		return status;
	}

	public void setStatus(InvitationStatus status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	 
	
	
}
