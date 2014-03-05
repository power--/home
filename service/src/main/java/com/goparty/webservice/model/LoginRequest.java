package com.goparty.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cientRequest")
public class LoginRequest {
	private String openId;
	
	private String tokenId;
	
	private String sessionId;
	
	private String mobile;

	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	

}
