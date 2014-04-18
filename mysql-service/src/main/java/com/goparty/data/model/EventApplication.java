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

@XmlRootElement(name = "eventApplication")
@Entity
@Table(name = "gp_event_application")
@Indexed
public class EventApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String eventId;

	private String applicantId;
	private String applicantMessage;
	
	private String adminIds;

	private String approverId;
	private String approverMessage;

	@Enumerated(EnumType.STRING)
	private InvitationAcceptance approval;

	@Enumerated(EnumType.STRING)
	private InvitationStatus status;

	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicantMessage() {
		return applicantMessage;
	}

	public void setApplicantMessage(String applicantMessage) {
		this.applicantMessage = applicantMessage;
	}

	public String getAdminIds() {
		return adminIds;
	}

	public void setAdminIds(String adminIds) {
		this.adminIds = adminIds;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getApproverMessage() {
		return approverMessage;
	}

	public void setApproverMessage(String approverMessage) {
		this.approverMessage = approverMessage;
	}

	public InvitationAcceptance getApproval() {
		return approval;
	}

	public void setApproval(InvitationAcceptance approval) {
		this.approval = approval;
	}

}
