package com.ss.smartoffice.shared.model.applicant;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="t_applicant_languages")

@Scope("prototype")
public class ApplicantLanguagesKnown {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_applicant_id")
	private int applicantId;
	private String language;
	private String langRead;
	private String langWrite;
	private String langSpeak;
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public ApplicantLanguagesKnown() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicantLanguagesKnown(Integer id, int applicantId, String language, String langRead, String langWrite,
			String langSpeak, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.applicantId = applicantId;
		this.language = language;
		this.langRead = langRead;
		this.langWrite = langWrite;
		this.langSpeak = langSpeak;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "ApplicantLanguagesKnown [id=" + id + ", applicantId=" + applicantId + ", language=" + language
				+ ", langRead=" + langRead + ", langWrite=" + langWrite + ", langSpeak=" + langSpeak + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLangRead() {
		return langRead;
	}

	public void setLangRead(String langRead) {
		this.langRead = langRead;
	}

	public String getLangWrite() {
		return langWrite;
	}

	public void setLangWrite(String langWrite) {
		this.langWrite = langWrite;
	}

	public String getLangSpeak() {
		return langSpeak;
	}

	public void setLangSpeak(String langSpeak) {
		this.langSpeak = langSpeak;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	
	
	
	
}
