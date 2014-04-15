package com.goparty.data.model;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Photo {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Moment moment;
}
