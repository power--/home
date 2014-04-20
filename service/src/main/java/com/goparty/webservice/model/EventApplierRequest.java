package com.goparty.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class EventApplierRequest {
	private String applierMessage;

	public String getApplierMessage() {
		return applierMessage;
	}

	public void setApplierMessage(String applierMessage) {
		this.applierMessage = applierMessage;
	}

}
