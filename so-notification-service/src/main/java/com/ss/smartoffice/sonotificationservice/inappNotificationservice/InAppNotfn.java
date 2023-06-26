package com.ss.smartoffice.sonotificationservice.inappNotificationservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
@Entity
@Table(name=" t_inapp_notfn")

@Scope("prototype")
public class InAppNotfn {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String userId;
	@Formula("(select user.first_name FROM	 auth_user user  where user.id=user_id)")
	private String userName;
	private String eventDesc;
	private String notfnDt;
	private String notfnExpDt;
	private String viewedNotfn;
	private String notfnViewedDt;
	private String hideNotfn;
	private String eventName;
	private String notfnMessage;
	public InAppNotfn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InAppNotfn(Integer id, String userId, String userName, String eventDesc, String notfnDt, String notfnExpDt,
			String viewedNotfn, String notfnViewedDt, String hideNotfn, String eventName, String notfnMessage) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.eventDesc = eventDesc;
		this.notfnDt = notfnDt;
		this.notfnExpDt = notfnExpDt;
		this.viewedNotfn = viewedNotfn;
		this.notfnViewedDt = notfnViewedDt;
		this.hideNotfn = hideNotfn;
		this.eventName = eventName;
		this.notfnMessage = notfnMessage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getNotfnDt() {
		return notfnDt;
	}
	public void setNotfnDt(String notfnDt) {
		this.notfnDt = notfnDt;
	}
	public String getNotfnExpDt() {
		return notfnExpDt;
	}
	public void setNotfnExpDt(String notfnExpDt) {
		this.notfnExpDt = notfnExpDt;
	}
	public String getViewedNotfn() {
		return viewedNotfn;
	}
	public void setViewedNotfn(String viewedNotfn) {
		this.viewedNotfn = viewedNotfn;
	}
	public String getNotfnViewedDt() {
		return notfnViewedDt;
	}
	public void setNotfnViewedDt(String notfnViewedDt) {
		this.notfnViewedDt = notfnViewedDt;
	}
	public String getHideNotfn() {
		return hideNotfn;
	}
	public void setHideNotfn(String hideNotfn) {
		this.hideNotfn = hideNotfn;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getNotfnMessage() {
		return notfnMessage;
	}
	public void setNotfnMessage(String notfnMessage) {
		this.notfnMessage = notfnMessage;
	}
	@Override
	public String toString() {
		return "InAppNotfn [id=" + id + ", userId=" + userId + ", userName=" + userName + ", eventDesc=" + eventDesc
				+ ", notfnDt=" + notfnDt + ", notfnExpDt=" + notfnExpDt + ", viewedNotfn=" + viewedNotfn
				+ ", notfnViewedDt=" + notfnViewedDt + ", hideNotfn=" + hideNotfn + ", eventName=" + eventName
				+ ", notfnMessage=" + notfnMessage + "]";
	}
	
	
}
