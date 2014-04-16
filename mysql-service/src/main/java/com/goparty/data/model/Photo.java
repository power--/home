package com.goparty.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gp_photo")
public class Photo {
	@Id
	private String id;
	
	@Column
	private String format;
	
	@Column 
	private String nickName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "momentId", nullable = false)
	private Moment moment;
	@Column
	private Date updateTime;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Moment getMoment() {
		return moment;
	}

	public void setMoment(Moment moment) {
		this.moment = moment;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
