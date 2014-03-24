package com.goparty.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class GroupRequest { 
	
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	
	 
	

}
