package com.ss.smartoffice.sochatservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="t_ind_chat_log")
public class IndividualChatLog {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer fromAuthUserId;
	@Formula("(select a.first_name from auth_user a where a.id=from_auth_user_id)")
	private String fromAuthUserName;
	private Integer toAuthUserId;
	@Formula("(select a.first_name from auth_user a where a.id=to_auth_user_id)")
	private String toAuthUserName;
	private String messagePayload;
	private String messageType;
	private LocalDateTime sentDt;
	private String createdBy;
	private LocalDateTime createdDt;
	private String modifiedBy;
	private LocalDateTime modifiedDt;
	public IndividualChatLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "IndividualChatLog [id=" + id + ", fromAuthUserId=" + fromAuthUserId + ", fromAuthUserName="
				+ fromAuthUserName + ", toAuthUserId=" + toAuthUserId + ", toAuthUserName=" + toAuthUserName
				+ ", messagePayload=" + messagePayload + ", messageType=" + messageType + ", sentDt=" + sentDt
				+ ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	public IndividualChatLog(Integer id, Integer fromAuthUserId, String fromAuthUserName, Integer toAuthUserId,
			String toAuthUserName, String messagePayload, String messageType, LocalDateTime sentDt, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.fromAuthUserId = fromAuthUserId;
		this.fromAuthUserName = fromAuthUserName;
		this.toAuthUserId = toAuthUserId;
		this.toAuthUserName = toAuthUserName;
		this.messagePayload = messagePayload;
		this.messageType = messageType;
		this.sentDt = sentDt;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFromAuthUserId() {
		return fromAuthUserId;
	}
	public void setFromAuthUserId(Integer fromAuthUserId) {
		this.fromAuthUserId = fromAuthUserId;
	}
	public String getFromAuthUserName() {
		return fromAuthUserName;
	}
	public void setFromAuthUserName(String fromAuthUserName) {
		this.fromAuthUserName = fromAuthUserName;
	}
	public Integer getToAuthUserId() {
		return toAuthUserId;
	}
	public void setToAuthUserId(Integer toAuthUserId) {
		this.toAuthUserId = toAuthUserId;
	}
	public String getToAuthUserName() {
		return toAuthUserName;
	}
	public void setToAuthUserName(String toAuthUserName) {
		this.toAuthUserName = toAuthUserName;
	}
	public String getMessagePayload() {
		return messagePayload;
	}
	public void setMessagePayload(String messagePayload) {
		this.messagePayload = messagePayload;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public LocalDateTime getSentDt() {
		return sentDt;
	}
	public void setSentDt(LocalDateTime sentDt) {
		this.sentDt = sentDt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}
	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	
}
