package com.ss.smartoffice.sodocumentservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApplicantLanguagesKnown {
	
	private Integer id;
	private int applicantId;
	private String language;
	private String langRead;
	private String langWrite;
	private String langSpeak;
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
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
	
	
	
