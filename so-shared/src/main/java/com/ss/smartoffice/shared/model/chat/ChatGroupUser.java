package com.ss.smartoffice.shared.model.chat;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChatGroupUser {
	private Integer id;
	private Integer authUserId;
	private String authUserName;
	private String isAdmin;
	private Integer chatGroupId; 
	private String chatGroupName;
	private String createdBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public ChatGroupUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatGroupUser(Integer id, Integer authUserId, String authUserName, String isAdmin, Integer chatGroupId,
			String chatGroupName, String createdBy, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.authUserId = authUserId;
		this.authUserName = authUserName;
		this.isAdmin = isAdmin;
		this.chatGroupId = chatGroupId;
		this.chatGroupName = chatGroupName;
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
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getChatGroupId() {
		return chatGroupId;
	}
	public void setChatGroupId(Integer chatGroupId) {
		this.chatGroupId = chatGroupId;
	}
	public String getChatGroupName() {
		return chatGroupName;
	}
	public void setChatGroupName(String chatGroupName) {
		this.chatGroupName = chatGroupName;
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
	@Override
	public String toString() {
		return "ChatGroupUser [id=" + id + ", authUserId=" + authUserId + ", authUserName=" + authUserName
				+ ", isAdmin=" + isAdmin + ", chatGroupId=" + chatGroupId + ", chatGroupName=" + chatGroupName
				+ ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}
