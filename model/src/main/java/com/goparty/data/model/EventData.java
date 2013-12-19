package com.goparty.data.model;

import java.util.Date;
import java.util.List;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class EventData {
	 private String id;
	 private String title;
	 private Date startTime;
	 private Date endTime;
	 private List<String> attendeeIds;
	 private String ownerId;
	 private String description;
	 private String eventCategoryId;
	 private String eventStatusId;
	 private String visiblityCategoryId;
	 
	@Id 
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
	
	public List<String> getAttendeeIds() {
		return attendeeIds;
	}
	public void setAttendeeIds(List<String> attendeeIds) {
		this.attendeeIds = attendeeIds;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEventCategoryId() {
		return eventCategoryId;
	}
	public void setEventCategoryId(String eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}
	public String getEventStatusId() {
		return eventStatusId;
	}
	public void setEventStatusId(String eventStatusId) {
		this.eventStatusId = eventStatusId;
	}
	public String getVisiblityCategoryId() {
		return visiblityCategoryId;
	}
	public void setVisiblityCategoryId(String visiblityCategoryId) {
		this.visiblityCategoryId = visiblityCategoryId;
	}
}
