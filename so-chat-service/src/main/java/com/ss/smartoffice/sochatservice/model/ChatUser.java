package com.ss.smartoffice.sochatservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_user")
public class ChatUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "id", insertable = false, updatable = false)
	private Integer chatId;
	private String firstName;
	private String lastName;
	@Column(name = "emp_code")
	private String employeeCode;
	private String chatAvailability;
	private String chatStatus;
	@Column(name = "chat_last_seen_dt")
	private LocalDateTime lastSeenDt;

	public ChatUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChatUser [id=" + id + ", chatId=" + chatId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", employeeCode=" + employeeCode + ", chatAvailability=" + chatAvailability + ", chatStatus="
				+ chatStatus + ", lastSeenDt=" + lastSeenDt + "]";
	}

	public ChatUser(Integer id, Integer chatId, String firstName, String lastName, String employeeCode,
			String chatAvailability, String chatStatus, LocalDateTime lastSeenDt) {
		super();
		this.id = id;
		this.chatId = chatId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeCode = employeeCode;
		this.chatAvailability = chatAvailability;
		this.chatStatus = chatStatus;
		this.lastSeenDt = lastSeenDt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getChatAvailability() {
		return chatAvailability;
	}

	public void setChatAvailability(String chatAvailability) {
		this.chatAvailability = chatAvailability;
	}

	public String getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(String chatStatus) {
		this.chatStatus = chatStatus;
	}

	public LocalDateTime getLastSeenDt() {
		return lastSeenDt;
	}

	public void setLastSeenDt(LocalDateTime lastSeenDt) {
		this.lastSeenDt = lastSeenDt;
	}

}
