package com.ss.smartoffice.shared.model;

import java.util.List;

public class menu {

	private String id;
	private String name;
	private String state;
	private String type;
	private String icon;
	private String userId;
	private String featureId;
	private Integer order;
	private List<children> children;
	public menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public menu(String id, String name, String state, String type, String icon, String userId, String featureId,
			Integer order) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.type = type;
		this.icon = icon;
		this.userId = userId;
		this.featureId = featureId;
		this.order = order;
	}

	
	public menu(String id, String name, String state, String type, String icon, String userId, String featureId,
			Integer order, List<children> children) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.type = type;
		this.icon = icon;
		this.userId = userId;
		this.featureId = featureId;
		this.order = order;
		this.children = children;
	}

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
	public String getFeatureId() {
		return featureId;
	}
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<children> getChildren() {
		return children;
	}
	public void setChildren(List<children> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "menu [id=" + id + ", name=" + name + ", state=" + state + ", type=" + type + ", icon=" + icon
				+ ", userId=" + userId + ", featureId=" + featureId + ", order=" + order + ", children=" + children
				+ "]";
	}
	
	
}
