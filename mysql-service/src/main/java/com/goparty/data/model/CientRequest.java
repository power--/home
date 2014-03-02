package com.goparty.data.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cientRequest")
public class CientRequest {
	private String openId;
	
	private String tokenId;
	
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
	
	

}
