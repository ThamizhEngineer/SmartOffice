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
@Table(name="t_chat_group_user")
public class ChatGroupUser {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="auth_user_id")
	private Integer authUserId;
	@Formula("(select a.first_name from auth_user a where a.id=auth_user_id)")
	private String authUserName;
	@Column(name="is_admin",columnDefinition = "char")
	private String isAdmin;
	@Column(name = "t_chat_group_hdr_id")
	private Integer chatGroupId; 
	@Formula("(select g.group_name from t_chat_group_hdr g where g.id=t_chat_group_hdr_id)")
	private String chatGroupName;
	@Formula("(select g.group_id from t_chat_group_hdr g where g.id=t_chat_group_hdr_id)")
	private String groupId;
	private String createdBy;
	private LocalDateTime createdDt;
	private String modifiedBy;
	private LocalDateTime modifiedDt;
	public ChatGroupUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChatGroupUser(Integer id, Integer authUserId, String authUserName, String isAdmin, Integer chatGroupId,
			String chatGroupName, String groupId, String createdBy, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.authUserId = authUserId;
		this.authUserName = authUserName;
		this.isAdmin = isAdmin;
		this.chatGroupId = chatGroupId;
		this.chatGroupName = chatGroupName;
		this.groupId = groupId;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "ChatGroupUser [id=" + id + ", authUserId=" + authUserId + ", authUserName=" + authUserName
				+ ", isAdmin=" + isAdmin + ", chatGroupId=" + chatGroupId + ", chatGroupName=" + chatGroupName
				+ ", groupId=" + groupId + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + "]";
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
