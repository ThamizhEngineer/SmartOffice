package com.ss.smartoffice.sonotificationservice.Announcement;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name="t_announcement")
public class Announcement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String announcementCode;
	private String subject;
	private String message;
	@Transient
	private List<Office> offices;
	private String createdBy;
	private String modifiedBy;
//	@CreationTimestamp
//	private LocalDateTime createdDt;
//	@UpdateTimestamp
//	private LocalDateTime modifiedDt;
	private String announcementCategory;
	public Announcement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Announcement(Integer id, String announcementCode, String subject, String message, List<Office> offices,
			String createdBy, String modifiedBy, String announcementCategory) {
		super();
		this.id = id;
		this.announcementCode = announcementCode;
		this.subject = subject;
		this.message = message;
		this.offices = offices;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.announcementCategory = announcementCategory;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAnnouncementCode() {
		return announcementCode;
	}
	public void setAnnouncementCode(String announcementCode) {
		this.announcementCode = announcementCode;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Office> getOffices() {
		return offices;
	}
	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getAnnouncementCategory() {
		return announcementCategory;
	}
	public void setAnnouncementCategory(String announcementCategory) {
		this.announcementCategory = announcementCategory;
	}
	@Override
	public String toString() {
		return "Announcement [id=" + id + ", announcementCode=" + announcementCode + ", subject=" + subject
				+ ", message=" + message + ", offices=" + offices + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", announcementCategory=" + announcementCategory + "]";
	}
	
	
}
