package com.ss.smartoffice.sodocumentservice.documentmanager;

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
@Table(name = "d_doc_header")

public class DocHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(columnDefinition = "CHAR")
	private String readOnly;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String location;
	
	public DocHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocHeader(Integer id, String name, String readOnly, String createdBy, LocalDateTime createdDt,
			String modifiedBy, LocalDateTime modifiedDt, String location) {
		super();
		this.id = id;
		this.name = name;
		this.readOnly = readOnly;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.location = location;
	}

	@Override
	public String toString() {
		return "DocHeader [id=" + id + ", name=" + name + ", readOnly=" + readOnly + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", location=" + location + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
