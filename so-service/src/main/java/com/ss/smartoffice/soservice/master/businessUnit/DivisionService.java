package com.ss.smartoffice.soservice.master.businessUnit;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_division_service")

@Scope("prototype")
public class DivisionService {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String mServiceId; 
	//add formulaa-based - AbilityMaster.serviceCode, serviceName
	@Formula("(select mf.service_code from m_service mf where mf.id = m_service_id)")
	private String serviceCode;
	@Formula("(select mf.service_name from m_service mf where mf.id = m_service_id)")
	private String serviceName;
	@Column(name="m_division_id")
	private String mDivisionId;
	private String divisionName;
	@Formula("(select COUNT(*) from m_service service where service.id=m_service_id)")
	private String serviceHandled;
	private String remarks;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public DivisionService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public DivisionService(Integer id, String mServiceId, String serviceCode, String serviceName, String mDivisionId,
			String divisionName, String serviceHandled, String remarks, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.mServiceId = mServiceId;
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.mDivisionId = mDivisionId;
		this.divisionName = divisionName;
		this.serviceHandled = serviceHandled;
		this.remarks = remarks;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getmServiceId() {
		return mServiceId;
	}
	public void setmServiceId(String mServiceId) {
		this.mServiceId = mServiceId;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getmDivisionId() {
		return mDivisionId;
	}
	public void setmDivisionId(String mDivisionId) {
		this.mDivisionId = mDivisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getServiceHandled() {
		return serviceHandled;
	}
	public void setServiceHandled(String serviceHandled) {
		this.serviceHandled = serviceHandled;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "DivisionService [id=" + id + ", mServiceId=" + mServiceId + ", serviceCode=" + serviceCode
				+ ", serviceName=" + serviceName + ", mDivisionId=" + mDivisionId + ", divisionName=" + divisionName
				+ ", serviceHandled=" + serviceHandled + ", remarks=" + remarks + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
}
