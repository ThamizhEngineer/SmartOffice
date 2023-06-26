package com.ss.smartoffice.shared.model.partner;

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
@Table(name="m_partner_contact")

@Scope("prototype")
public class PartnerContact {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String number;
	private String mobileNo;
	private String landlineNo;
	private String managerId;
	private String emailId;
	private String isClient;
	private String isVendor;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@Column(name="partner_id")
	private String partnerId;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public PartnerContact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PartnerContact(Integer id, String name, String number, String mobileNo, String landlineNo, String managerId,
			String emailId, String isClient, String isVendor, String isEnabled, String createdBy, String modifiedBy,
			String partnerId, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.mobileNo = mobileNo;
		this.landlineNo = landlineNo;
		this.managerId = managerId;
		this.emailId = emailId;
		this.isClient = isClient;
		this.isVendor = isVendor;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.partnerId = partnerId;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLandlineNo() {
		return landlineNo;
	}
	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getIsClient() {
		return isClient;
	}
	public void setIsClient(String isClient) {
		this.isClient = isClient;
	}
	public String getIsVendor() {
		return isVendor;
	}
	public void setIsVendor(String isVendor) {
		this.isVendor = isVendor;
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
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
		return "PartnerContact [id=" + id + ", name=" + name + ", number=" + number + ", mobileNo=" + mobileNo
				+ ", landlineNo=" + landlineNo + ", managerId=" + managerId + ", emailId=" + emailId + ", isClient="
				+ isClient + ", isVendor=" + isVendor + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", partnerId=" + partnerId + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}



}
