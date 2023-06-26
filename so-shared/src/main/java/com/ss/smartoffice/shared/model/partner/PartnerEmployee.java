package com.ss.smartoffice.shared.model.partner;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_partner_employee")

@Scope("prototype")
public class PartnerEmployee {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="partner_id")
	private String partnerId;
	private String addressLine1;
	private String addressLine2;
	private String empCity;
	private String empState;
	private String empCountry;
	private String pinCode;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String landlineNo;
	private String emailId;
	private String managerId;
	@Transient
	@Formula("(SELECT partner.first_name FROM m_partner_employee partner WHERE partner.id= manager_id)")
	private String managerName;
	private String isClient;
	private String isVendor;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public PartnerEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PartnerEmployee(Integer id, String partnerId, String addressLine1, String addressLine2, String empCity,
			String empState, String empCountry, String pinCode, String firstName, String lastName, String mobileNo,
			String landlineNo, String emailId, String managerId, String managerName, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.partnerId = partnerId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.empCity = empCity;
		this.empState = empState;
		this.empCountry = empCountry;
		this.pinCode = pinCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.landlineNo = landlineNo;
		this.emailId = emailId;
		this.managerId = managerId;
		this.managerName = managerName;
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
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getEmpCity() {
		return empCity;
	}
	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}
	public String getEmpState() {
		return empState;
	}
	public void setEmpState(String empState) {
		this.empState = empState;
	}
	public String getEmpCountry() {
		return empCountry;
	}
	public void setEmpCountry(String empCountry) {
		this.empCountry = empCountry;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
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
	@Override
	public String toString() {
		return "PartnerEmployee [id=" + id + ", partnerId=" + partnerId + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", empCity=" + empCity + ", empState=" + empState + ", empCountry="
				+ empCountry + ", pinCode=" + pinCode + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNo=" + mobileNo + ", landlineNo=" + landlineNo + ", emailId=" + emailId + ", managerId="
				+ managerId + ", managerName=" + managerName + ", isClient=" + isClient + ", isVendor=" + isVendor
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}
