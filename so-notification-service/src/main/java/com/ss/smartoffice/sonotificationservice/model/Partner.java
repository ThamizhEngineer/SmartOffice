package com.ss.smartoffice.sonotificationservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Partner {
	private Integer id;
	private String clientCode;
	private String clientName;
	private String mobileNo;
	private String emailId;
	private String vendorCode;
	private String vendorAbbreviation;
	private String priContactName;
	private String country;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public Partner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Partner(Integer id, String clientCode, String clientName, String mobileNo, String emailId, String vendorCode,
			String vendorAbbreviation, String priContactName, String country, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.vendorCode = vendorCode;
		this.vendorAbbreviation = vendorAbbreviation;
		this.priContactName = priContactName;
		this.country = country;
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
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorAbbreviation() {
		return vendorAbbreviation;
	}
	public void setVendorAbbreviation(String vendorAbbreviation) {
		this.vendorAbbreviation = vendorAbbreviation;
	}
	public String getPriContactName() {
		return priContactName;
	}
	public void setPriContactName(String priContactName) {
		this.priContactName = priContactName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
		return "Partner [id=" + id + ", clientCode=" + clientCode + ", clientName=" + clientName + ", mobileNo="
				+ mobileNo + ", emailId=" + emailId + ", vendorCode=" + vendorCode + ", vendorAbbreviation="
				+ vendorAbbreviation + ", priContactName=" + priContactName + ", country=" + country + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
}
