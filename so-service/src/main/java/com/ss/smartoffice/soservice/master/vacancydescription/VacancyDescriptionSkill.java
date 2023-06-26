package com.ss.smartoffice.soservice.master.vacancydescription;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name="m_vd_skill")
public class VacancyDescriptionSkill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_vacancy_desc_id")
	private String vacancyDescId;
	private String type;
	private String name;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public VacancyDescriptionSkill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VacancyDescriptionSkill(Integer id, String vacancyDescId, String type, String name, String isEnabled,
			String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.vacancyDescId = vacancyDescId;
		this.type = type;
		this.name = name;
		this.isEnabled = isEnabled;
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
	public String getVacancyDescId() {
		return vacancyDescId;
	}
	public void setVacancyDescId(String vacancyDescId) {
		this.vacancyDescId = vacancyDescId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
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
		return "VacancyDescriptionSkill [id=" + id + ", vacancyDescId=" + vacancyDescId + ", type=" + type + ", name="
				+ name + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}
	
		
}
