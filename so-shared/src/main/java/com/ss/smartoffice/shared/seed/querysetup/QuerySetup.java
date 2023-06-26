package com.ss.smartoffice.shared.seed.querysetup;

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
@Table(name = "s_query_setup")

public class QuerySetup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String purpose;
	private String entity;
	private String query;
	private String contextUrl;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	@Column(columnDefinition = "char")
	private String isEnabled;
	
	public QuerySetup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuerySetup(Integer id, String purpose, String entity, String query, String contextUrl, String createdBy,
			LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate, String isEnabled) {
		super();
		this.id = id;
		this.purpose = purpose;
		this.entity = entity;
		this.query = query;
		this.contextUrl = contextUrl;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "QuerySetup [id=" + id + ", purpose=" + purpose + ", entity=" + entity + ", query=" + query
				+ ", contextUrl=" + contextUrl + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", isEnabled=" + isEnabled + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getContextUrl() {
		return contextUrl;
	}

	public void setContextUrl(String contextUrl) {
		this.contextUrl = contextUrl;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}
