package com.goparty.data.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.goparty.data.constant.ParticipanceType;
@Entity
@Table(name = "gp_event_member")  
public class EventMember {
	@EmbeddedId	 
	private EventMemberId id = new EventMemberId();  
	
	@Enumerated(EnumType.ORDINAL)  
	private ParticipanceType participance;
	
	private String admin;
	
	private Date updateTime; 
	
	
	public ParticipanceType getParticipance() {
		return participance;
	}
	public void setParticipance(ParticipanceType participance) {
		this.participance = participance;
	}
	public String getAdmin() {
		return admin;
	}
	
	public boolean isAdmin(){
		return admin.equals("Y");
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public EventMemberId getId() {
		return id;
	}

	public void setId(EventMemberId id) {
		this.id = id;
	} 

}
