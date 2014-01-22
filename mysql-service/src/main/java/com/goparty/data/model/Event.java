package com.goparty.data.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement(name = "event")
@Entity
public class Event {
	 @Id 
	 private String id;
	 private String title;
	 @Transient
	 private Location location;
	 private Date startTime;
	 private Date endTime;
	 @Transient
	 private List<User> attendees;
	 @Transient
	 private User owner;
	 
	 private String description;
	 @Transient
	 private EventCategory eventCategory;
	 @Transient
	 private EventStatus eventStatus;
	 @Transient
	 private VisibilityCategory visibilityCategory;
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
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
	
	@XmlElementWrapper
	public List<User> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public EventCategory getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
	public EventStatus getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}
	public VisibilityCategory getVisibilityCategory() {
		return visibilityCategory;
	}
	public void setVisibilityCategory(VisibilityCategory visibilityCategory) {
		this.visibilityCategory = visibilityCategory;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", location="
				+ location + ", startTime=" + startTime + ", endTime="
				+ endTime + ", attendees=" + attendees + ", owner=" + owner
				+ ", description=" + description + ", eventCategory="
				+ eventCategory + ", eventStatus=" + eventStatus
				+ ", visiblityCategory=" + visibilityCategory + "]";
	}
}
