package com.ss.smartoffice.shared.common;

import java.util.List;
import java.util.Map;

import com.ss.smartoffice.shared.model.AuthFeature;

public class BaseEntity {
	
	private String appSystemId;
	private String appUserId; 
	private Map<String,Object> attList;
	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	} 
	public BaseEntity(String appSystemId, String appUserId,
			Map<String, Object> attList) {
		super();
		this.appSystemId = appSystemId;
		this.appUserId = appUserId; 
		this.attList = attList;
	}
	public String getAppSystemId() {
		return appSystemId;
	}
	public void setAppSystemId(String appSystemId) {
		this.appSystemId = appSystemId;
	}
	public String getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}
	
	public Map<String, Object> getAttList() {
		return attList;
	}
	public void setAttList(Map<String, Object> messages) {
		this.attList = messages;
	}
	@Override
	public String toString() {
		return "BaseEntity [appSystemId=" + appSystemId + ", appUserId=" + appUserId  + ", attList=" + attList + "]";
	}
	

	
	

}
