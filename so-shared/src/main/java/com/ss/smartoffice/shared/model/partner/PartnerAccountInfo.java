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
@Table(name="m_partner_account_info")

@Scope("prototype")
public class PartnerAccountInfo {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String bankAccountNo;
	private String bankAccountName;
	private String bankName;
	private String bankBranchName;
	private String ifscCode;
	@Column(name="partner_id")
	private String partnerId;
	private String others;
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
	public PartnerAccountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PartnerAccountInfo(Integer id, String bankAccountNo, String bankAccountName, String bankName,
			String bankBranchName, String ifscCode, String partnerId, String others, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.bankAccountNo = bankAccountNo;
		this.bankAccountName = bankAccountName;
		this.bankName = bankName;
		this.bankBranchName = bankBranchName;
		this.ifscCode = ifscCode;
		this.partnerId = partnerId;
		this.others = others;
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
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
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
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
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
		return "PartnerAccountInfo [id=" + id + ", bankAccountNo=" + bankAccountNo + ", bankAccountName="
				+ bankAccountName + ", bankName=" + bankName + ", bankBranchName=" + bankBranchName + ", ifscCode="
				+ ifscCode + ", partnerId=" + partnerId + ", others=" + others + ", isClient=" + isClient
				+ ", isVendor=" + isVendor + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}
