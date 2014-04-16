package com.goparty.webservice.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "moment")
public class MomentRequest {
	private String moment;
	private String visibility;
	
	@XmlElementWrapper(name="photos")
	private List<PhotoInfo> photos;

	public String getMoment() {
		return moment;
	}

	public void setMoment(String moment) {
		this.moment = moment;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public List<PhotoInfo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoInfo> photos) {
		this.photos = photos;
	}
}
