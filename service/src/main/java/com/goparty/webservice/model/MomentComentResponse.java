package com.goparty.webservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.goparty.data.model.User;

@XmlRootElement(name = "response")
@JsonSerialize(include=Inclusion.NON_NULL)
public class MomentComentResponse {
	private String id;
	private String comment;
	private boolean likeit;
	private Date publishDate;
	private User sender;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
}
