package com.goparty.data.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class StringResponse {
	
	private String result = "success";
	
	private String message;
	
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
