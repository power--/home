package com.goparty.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comment")
public class MomentCommentRequest {
	private String comment;
	private boolean likeit;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isLikeit() {
		return likeit;
	}
	public void setLikeit(boolean likeit) {
		this.likeit = likeit;
	}
}
