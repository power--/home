package com.goparty.webservice.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.goparty.data.constant.EventVisibility;
import com.goparty.data.model.User;

@XmlRootElement(name = "response")
@JsonSerialize(include=Inclusion.NON_NULL)
public class MomentRepsone {
	private String id;
	private String moment;
	private EventVisibility visibility;
	private List<PhotoInfo> photos;
	private User sender;
	private Date publishTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMoment() {
		return moment;
	}
	public void setMoment(String moment) {
		this.moment = moment;
	}
	public EventVisibility getVisibility() {
		return visibility;
	}
	public void setVisibility(EventVisibility visibility) {
		this.visibility = visibility;
	}
	public List<PhotoInfo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<PhotoInfo> photos) {
		this.photos = photos;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
