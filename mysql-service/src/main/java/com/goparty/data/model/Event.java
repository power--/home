package com.goparty.data.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
 

@XmlRootElement(name = "event")
@Entity
@Table(name="gp_event")
public class Event {
	 @Id 
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private String id;
	 
	 private String title;
	 @Transient
	 private Location location;
	 
	 private Date startTime;
	 private Date endTime;
	 
	 @Transient
	 private List<User> attendees;
	 
	 @OneToOne
	 @JoinColumn(name="ownerId")
	 private User owner;
	 
	 private String description;
	 
	 @OneToOne
	 @JoinColumn(name="cateId")
	 private EventCategory eventCategory;
	 
	 private String status;
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
 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public VisibilityCategory getVisibilityCategory() {
		return visibilityCategory;
	}
	public void setVisibilityCategory(VisibilityCategory visibilityCategory) {
		this.visibilityCategory = visibilityCategory;
	}
 
}
