package com.ss.smartoffice.soauthservice.model.auth;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;


public class Notification extends BaseEntity{
	
	private int id;
	private String notificationType;
	private String message;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime notificationDateTime;
	private int userId;
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(int id, String notificationType, String message, LocalDateTime notificationDateTime,
			int userId) {
		super();
		this.id = id;
		this.notificationType = notificationType;
		this.message = message;
		this.notificationDateTime = notificationDateTime;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", notificationType=" + notificationType + ", message=" + message
				+ ", notificationDateTime=" + notificationDateTime + ", userId=" + userId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getNotificationDateTime() {
		return notificationDateTime;
	}

	public void setNotificationDateTime(LocalDateTime notificationDateTime) {
		this.notificationDateTime = notificationDateTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
	

}
