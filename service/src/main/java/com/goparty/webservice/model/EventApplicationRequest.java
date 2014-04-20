package com.goparty.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class EventApplicationRequest {

	private String ignore;

	private String approval;

	private String approverMessage;

	public String getIgnore() {
		return ignore;
	}

	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getApproverMessage() {
		return approverMessage;
	}

	public void setApproverMessage(String approverMessage) {
		this.approverMessage = approverMessage;
	}

}
