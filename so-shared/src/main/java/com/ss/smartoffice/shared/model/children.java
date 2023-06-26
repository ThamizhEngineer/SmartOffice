package com.ss.smartoffice.shared.model;

public class children {

	private String authMenuId;
	private String name;
	private String state;
	private String subState;
	private String type;
	private String authFeatureId;
	private String purpose;
	private Integer order;
	public children() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public children(String authMenuId, String name, String state, String subState, String type, String authFeatureId,
			String purpose, Integer order) {
		super();
		this.authMenuId = authMenuId;
		this.name = name;
		this.state = state;
		this.subState = subState;
		this.type = type;
		this.authFeatureId = authFeatureId;
		this.purpose = purpose;
		this.order = order;
	}

	public String getAuthMenuId() {
		return authMenuId;
	}
	public void setAuthMenuId(String authMenuId) {
		this.authMenuId = authMenuId;
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
	public String getSubState() {
		return subState;
	}
	public void setSubState(String subState) {
		this.subState = subState;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthFeatureId() {
		return authFeatureId;
	}
	public void setAuthFeatureId(String authFeatureId) {
		this.authFeatureId = authFeatureId;
	}
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "children [authMenuId=" + authMenuId + ", name=" + name + ", state=" + state + ", subState=" + subState
				+ ", type=" + type + ", authFeatureId=" + authFeatureId + ", purpose=" + purpose + ", order=" + order
				+ "]";
	}
	
	
	
}
