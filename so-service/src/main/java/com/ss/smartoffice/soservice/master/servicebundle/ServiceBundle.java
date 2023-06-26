package com.ss.smartoffice.soservice.master.servicebundle;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;


@Entity
@Table(name="m_service_bundle")

@Scope("prototype")

public class ServiceBundle extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String sbName;
	private String sacCode;
	private String internalRemarks;
	private String sbDesc;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public ServiceBundle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ServiceBundle(int id, String sbName, String sacCode, String internalRemarks, String sbDesc, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.sbName = sbName;
		this.sacCode = sacCode;
		this.internalRemarks = internalRemarks;
		this.sbDesc = sbDesc;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSbName() {
		return sbName;
	}
	public void setSbName(String sbName) {
		this.sbName = sbName;
	}
	public String getSacCode() {
		return sacCode;
	}
	public void setSacCode(String sacCode) {
		this.sacCode = sacCode;
	}
	public String getInternalRemarks() {
		return internalRemarks;
	}
	public void setInternalRemarks(String internalRemarks) {
		this.internalRemarks = internalRemarks;
	}
	public String getSbDesc() {
		return sbDesc;
	}
	public void setSbDesc(String sbDesc) {
		this.sbDesc = sbDesc;
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
	@Override
	public String toString() {
		return "ServiceBundle [id=" + id + ", sbName=" + sbName + ", sacCode=" + sacCode + ", internalRemarks="
				+ internalRemarks + ", sbDesc=" + sbDesc + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
}
