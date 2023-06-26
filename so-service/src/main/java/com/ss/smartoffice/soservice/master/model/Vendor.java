package com.ss.smartoffice.soservice.master.model;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name="m_vendor")

public class Vendor extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String customerCode;
	private String vendorCode;
	private String orgName;
	private String contactName;
	private String contactEmail;
	private int contactPhone;
	private String headOfficeAddressLine1;
	private String headOfficeAddressLine2;
	private String headOfficeCity;
	private String headOfficeState;
	private int headOfficePincode;
	private String headOfficeCountry;
	private int headOfficeNumber;
	private int bankAccNo;
	private String bankAccName;
	private String bankAccType;
	private String bankName;
	private String bankBranchName;
	private String bankIfscCode;
	private String bankOtherInfo;
	private String gstNo;
	private String tanNo;
	private String panNo;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private String remarks;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vendor(int id, String customerCode, String vendorCode, String orgName, String contactName,
			String contactEmail, int contactPhone, String headOfficeAddressLine1, String headOfficeAddressLine2,
			String headOfficeCity, String headOfficeState, int headOfficePincode, String headOfficeCountry,
			int headOfficeNumber, int bankAccNo, String bankAccName, String bankAccType, String bankName,
			String bankBranchName, String bankIfscCode, String bankOtherInfo, String gstNo, String tanNo, String panNo,
			String isEnabled, String createdBy, String modifiedBy, String remarks, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.customerCode = customerCode;
		this.vendorCode = vendorCode;
		this.orgName = orgName;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.headOfficeAddressLine1 = headOfficeAddressLine1;
		this.headOfficeAddressLine2 = headOfficeAddressLine2;
		this.headOfficeCity = headOfficeCity;
		this.headOfficeState = headOfficeState;
		this.headOfficePincode = headOfficePincode;
		this.headOfficeCountry = headOfficeCountry;
		this.headOfficeNumber = headOfficeNumber;
		this.bankAccNo = bankAccNo;
		this.bankAccName = bankAccName;
		this.bankAccType = bankAccType;
		this.bankName = bankName;
		this.bankBranchName = bankBranchName;
		this.bankIfscCode = bankIfscCode;
		this.bankOtherInfo = bankOtherInfo;
		this.gstNo = gstNo;
		this.tanNo = tanNo;
		this.panNo = panNo;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.remarks = remarks;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public int getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(int contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getHeadOfficeAddressLine1() {
		return headOfficeAddressLine1;
	}
	public void setHeadOfficeAddressLine1(String headOfficeAddressLine1) {
		this.headOfficeAddressLine1 = headOfficeAddressLine1;
	}
	public String getHeadOfficeAddressLine2() {
		return headOfficeAddressLine2;
	}
	public void setHeadOfficeAddressLine2(String headOfficeAddressLine2) {
		this.headOfficeAddressLine2 = headOfficeAddressLine2;
	}
	public String getHeadOfficeCity() {
		return headOfficeCity;
	}
	public void setHeadOfficeCity(String headOfficeCity) {
		this.headOfficeCity = headOfficeCity;
	}
	public String getHeadOfficeState() {
		return headOfficeState;
	}
	public void setHeadOfficeState(String headOfficeState) {
		this.headOfficeState = headOfficeState;
	}
	public int getHeadOfficePincode() {
		return headOfficePincode;
	}
	public void setHeadOfficePincode(int headOfficePincode) {
		this.headOfficePincode = headOfficePincode;
	}
	public String getHeadOfficeCountry() {
		return headOfficeCountry;
	}
	public void setHeadOfficeCountry(String headOfficeCountry) {
		this.headOfficeCountry = headOfficeCountry;
	}
	public int getHeadOfficeNumber() {
		return headOfficeNumber;
	}
	public void setHeadOfficeNumber(int headOfficeNumber) {
		this.headOfficeNumber = headOfficeNumber;
	}
	public int getBankAccNo() {
		return bankAccNo;
	}
	public void setBankAccNo(int bankAccNo) {
		this.bankAccNo = bankAccNo;
	}
	public String getBankAccName() {
		return bankAccName;
	}
	public void setBankAccName(String bankAccName) {
		this.bankAccName = bankAccName;
	}
	public String getBankAccType() {
		return bankAccType;
	}
	public void setBankAccType(String bankAccType) {
		this.bankAccType = bankAccType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankIfscCode() {
		return bankIfscCode;
	}
	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}
	public String getBankOtherInfo() {
		return bankOtherInfo;
	}
	public void setBankOtherInfo(String bankOtherInfo) {
		this.bankOtherInfo = bankOtherInfo;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getTanNo() {
		return tanNo;
	}
	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "Vendor [id=" + id + ", customerCode=" + customerCode + ", vendorCode=" + vendorCode + ", orgName="
				+ orgName + ", contactName=" + contactName + ", contactEmail=" + contactEmail + ", contactPhone="
				+ contactPhone + ", headOfficeAddressLine1=" + headOfficeAddressLine1 + ", headOfficeAddressLine2="
				+ headOfficeAddressLine2 + ", headOfficeCity=" + headOfficeCity + ", headOfficeState=" + headOfficeState
				+ ", headOfficePincode=" + headOfficePincode + ", headOfficeCountry=" + headOfficeCountry
				+ ", headOfficeNumber=" + headOfficeNumber + ", bankAccNo=" + bankAccNo + ", bankAccName=" + bankAccName
				+ ", bankAccType=" + bankAccType + ", bankName=" + bankName + ", bankBranchName=" + bankBranchName
				+ ", bankIfscCode=" + bankIfscCode + ", bankOtherInfo=" + bankOtherInfo + ", gstNo=" + gstNo
				+ ", tanNo=" + tanNo + ", panNo=" + panNo + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", remarks=" + remarks + ", createdDt=" + createdDt + ", modifiedDt="
				+ modifiedDt + "]";
	}
	


}
