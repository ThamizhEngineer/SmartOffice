package com.ss.smartoffice.shared.model;

import com.ss.smartoffice.shared.common.BaseEntity;

public class MenuItems extends BaseEntity {
private Integer id;
	
	private String name;
	private String state;
	private String type;
	private String icon;
	
	private String userId;
	
	
	public MenuItems() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MenuItems(Integer id, String name, String state, String type, String icon, String userId) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.type = type;
		this.icon = icon;
		this.userId = userId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "MenuItems [id=" + id + ", name=" + name + ", state=" + state + ", type=" + type + ", icon=" + icon
				+ ", userId=" + userId + "]";
	}

	
	
	
}
