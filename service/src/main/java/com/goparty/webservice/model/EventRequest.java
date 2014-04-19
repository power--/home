package com.goparty.webservice.model;

import java.util.Date;
import java.util.List;

import com.goparty.data.constant.EventStatus;
import com.goparty.data.constant.EventVisibility;
import com.goparty.data.model.Category;
import com.goparty.data.model.User;

public class EventRequest { 
	private String id;
 
	private String title; 
	
	private Date startTime;
 
	private Date endTime;
 
	private String location;
 
	private String description;

	private User owner; 

	private boolean locationShareable;
 
	private EventStatus status;
 
	private EventVisibility visibility;
	
	private List<User> members; 
  
	private List<Category> categories;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

 

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public boolean isLocationShareable() {
		return locationShareable;
	}

	public void setLocationShareable(boolean locationShareable) {
		this.locationShareable = locationShareable;
	}

	 
 

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}

	public EventVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(EventVisibility visibility) {
		this.visibility = visibility;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	


}
