package com.ss.smartoffice.shared.model.chat;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


public class ChatGroup {
	private Integer id;
	private String groupType;
	private Integer groupId;
	private String groupName;
	private String groupDesc;
	private String createdBy;
	private LocalDateTime createdDt;
	private String modifiedBy;
	private LocalDateTime modifiedDt;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_chat_group_hdr_id")
	private List<ChatGroupUser> chatGroupUsers;
	public ChatGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatGroup(Integer id, String groupType, Integer groupId, String groupName, String groupDesc,
			String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt,
			List<ChatGroupUser> chatGroupUsers) {
		super();
		this.id = id;
		this.groupType = groupType;
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupDesc = groupDesc;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.chatGroupUsers = chatGroupUsers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
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
	public List<ChatGroupUser> getChatGroupUsers() {
		return chatGroupUsers;
	}
	public void setChatGroupUsers(List<ChatGroupUser> chatGroupUsers) {
		this.chatGroupUsers = chatGroupUsers;
	}
	@Override
	public String toString() {
		return "ChatGroup [id=" + id + ", groupType=" + groupType + ", groupId=" + groupId + ", groupName=" + groupName
				+ ", groupDesc=" + groupDesc + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + ", chatGroupUsers=" + chatGroupUsers + "]";
	}
	
	
}
