package com.ss.smartoffice.sochatservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="t_group_chat_log")
public class GroupChatLog {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "t_grp_chat_hdr_id")
	private Integer groupChatId;
	@Formula("(select g.group_name from t_chat_group_hdr g where g.id=t_grp_chat_hdr_id)")
	private String groupChatName;
	private Integer authUserId;
	@Formula("(select a.first_name from auth_user a where a.id=auth_user_id)")
	private String authUserName;
	private String messagePayload;
	private String messageType;
	private LocalDateTime sentDt;
	private String createdBy;
	private LocalDateTime createdDt;
	private String modifiedBy;
	private LocalDateTime modifiedDt;
	public GroupChatLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupChatLog(Integer id, Integer groupChatId, String groupChatName, Integer authUserId, String authUserName,
			String messagePayload, String messageType, LocalDateTime sentDt, String createdBy, LocalDateTime createdDt,
			String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.groupChatId = groupChatId;
		this.groupChatName = groupChatName;
		this.authUserId = authUserId;
		this.authUserName = authUserName;
		this.messagePayload = messagePayload;
		this.messageType = messageType;
		this.sentDt = sentDt;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "GroupChatLog [id=" + id + ", groupChatId=" + groupChatId + ", groupChatName=" + groupChatName
				+ ", authUserId=" + authUserId + ", authUserName=" + authUserName + ", messagePayload=" + messagePayload
				+ ", messageType=" + messageType + ", sentDt=" + sentDt + ", createdBy=" + createdBy + ", createdDt="
				+ createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupChatId() {
		return groupChatId;
	}
	public void setGroupChatId(Integer groupChatId) {
		this.groupChatId = groupChatId;
	}
	public String getGroupChatName() {
		return groupChatName;
	}
	public void setGroupChatName(String groupChatName) {
		this.groupChatName = groupChatName;
	}
	public Integer getAuthUserId() {
		return authUserId;
	}
	public void setAuthUserId(Integer authUserId) {
		this.authUserId = authUserId;
	}
	public String getAuthUserName() {
		return authUserName;
	}
	public void setAuthUserName(String authUserName) {
		this.authUserName = authUserName;
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
