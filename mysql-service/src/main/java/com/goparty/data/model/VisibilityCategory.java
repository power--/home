package com.goparty.data.model;

import org.hibernate.search.annotations.Indexed;

public class VisibilityCategory {
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "VisiblityCategory [id=" + id + ", name=" + name + "]";
	}
}
