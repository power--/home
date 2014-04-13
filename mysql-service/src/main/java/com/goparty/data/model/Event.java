package com.goparty.data.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.goparty.data.constant.EventStatus;
import com.goparty.data.constant.EventVisibility;

@XmlRootElement(name = "event")
@Entity
@Table(name = "gp_event")
@Indexed
public class Event{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES )
	private String title;

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES )
	private Date startTime;

	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES )
	private Date endTime;

	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES )
	private String location;

	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES )
	private String description;

	// can't change to LAZY and add cascade=CascadeType.ALL
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "gp_event_attendee", joinColumns = @JoinColumn(name = "eventId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	@IndexedEmbedded
	private List<UserProfile> attendees;

	@OneToOne
	@JoinColumn(name = "ownerId")
	@IndexedEmbedded
	private UserProfile owner;

	@OneToOne
	@JoinColumn(name = "cateId")
	@IndexedEmbedded
	private EventCategory eventCategory;

	@Enumerated(EnumType.ORDINAL)
	private EventStatus eventStatus;

	@Enumerated(EnumType.ORDINAL)
	private EventVisibility visibility;

	private boolean locationShareable;

	@Transient
	private List<UserProfile> refusedAttendees;

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

//	@XmlElementWrapper(name = "attendees")
//	@XmlElement(name = "attendee")
	public List<UserProfile> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<UserProfile> attendees) {
		this.attendees = attendees;
	}

	public UserProfile getOwner() {
		return owner;
	}

	public void setOwner(UserProfile owner) {
		this.owner = owner;
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

	public EventVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(EventVisibility visibility) {
		this.visibility = visibility;
	}

	public boolean isLocationShareable() {
		return locationShareable;
	}

	public void setLocationShareable(boolean locationShareable) {
		this.locationShareable = locationShareable;
	}

//	@XmlElementWrapper(name = "refusedAttendees")
//	@XmlElement(name = "attendee")
	public List<UserProfile> getRefusedAttendees() {
		return refusedAttendees;
	}

	public void setRefusedAttendees(List<UserProfile> refusedAttendees) {
		this.refusedAttendees = refusedAttendees;
	}
}
