package com.goparty.webservice.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.goparty.data.model.Group;

@XmlRootElement(name = "request")
public class EventInvitationRequest {

	private String ignore;

	private String participance;

	private String inviteeMessage;

	public String getIgnore() {
		return ignore;
	}

	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}

	public String getParticipance() {
		return participance;
	}

	public void setParticipance(String participance) {
		this.participance = participance;
	}

	public String getInviteeMessage() {
		return inviteeMessage;
	}

	public void setInviteeMessage(String inviteeMessage) {
		this.inviteeMessage = inviteeMessage;
	}

}
