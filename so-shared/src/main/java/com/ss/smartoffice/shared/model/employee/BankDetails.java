package com.ss.smartoffice.shared.model.employee;
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
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="m_emp_bank_details")

@Scope("prototype")
public class BankDetails extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_employee_id")
	private int employeeId;
	private String bankName;
	private String branchName;
	private String accNo;
	//changes as per req
	private String isDefault;
	private String accountName;
	private String isIcici;
	//
	private String ifscCode;
	private String bankAddress;
	private String bankCountry;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public BankDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankDetails(Integer id, int employeeId, String bankName, String branchName, String accNo, String isDefault,
			String accountName, String isIcici, String ifscCode, String bankAddress, String bankCountry,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accNo = accNo;
		this.isDefault = isDefault;
		this.accountName = accountName;
		this.isIcici = isIcici;
		this.ifscCode = ifscCode;
		this.bankAddress = bankAddress;
		this.bankCountry = bankCountry;
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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIsIcici() {
		return isIcici;
	}

	public void setIsIcici(String isIcici) {
		this.isIcici = isIcici;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankCountry() {
		return bankCountry;
	}

	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
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
		return "BankDetails [id=" + id + ", employeeId=" + employeeId + ", bankName=" + bankName + ", branchName="
				+ branchName + ", accNo=" + accNo + ", isDefault=" + isDefault + ", accountName=" + accountName
				+ ", isIcici=" + isIcici + ", ifscCode=" + ifscCode + ", bankAddress=" + bankAddress + ", bankCountry="
				+ bankCountry + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}


	

	
}
