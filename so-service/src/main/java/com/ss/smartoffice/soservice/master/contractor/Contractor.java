package com.ss.smartoffice.soservice.master.contractor;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.model.employee.BankDetails;
import com.ss.smartoffice.shared.model.employee.EmployeeSkill;



@Entity
@Table(name="m_employee")

public class Contractor {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String internalId;
	private String contractorCode;
	private String empTypeCode;
	private String isBillable;
	private String firstName ;
	private String lastName;
	private String bloodGroup;
	private String pfNo;
	private String preEsiNo;
	private String emailId;
	private String gender;
	private String maritalStatus;
	private String contactMobileNo;
	private String curAddress;
	private String permAddress;
	@Column(name="n1_emp_id")
	private String n1EmpId;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id= n1_emp_id)")
	private String managerName;
	private String doj;
	private String dob;
	@Column(name="m_office_id")
	private Integer officeId;
	
	@Column(name="m_country_id")
	private Integer countryId;
	
	private String docId;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<BankDetails> bankDetails;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<EmployeeSkill> employeeSkills;
		
	public Contractor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contractor(int id, String internalId, String contractorCode, String empTypeCode, String firstName,
			String lastName, String emailId, String contactMobileNo, String n1EmpId,String managerName) {
		super();
		this.id = id;
		this.internalId = internalId;
		this.contractorCode = contractorCode;
		this.empTypeCode = empTypeCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.contactMobileNo = contactMobileNo;
		this.n1EmpId = n1EmpId;
		this.managerName=managerName;
	}

	public Contractor(int id, String internalId, String contractorCode, String empTypeCode, String isBillable,
			String firstName, String lastName, String bloodGroup, String pfNo, String preEsiNo, String emailId,
			String gender, String maritalStatus, String contactMobileNo, String curAddress, String permAddress,
			String n1EmpId, String managerName, String doj, String dob, Integer officeId, Integer countryId,
			String docId, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt, List<BankDetails> bankDetails, List<EmployeeSkill> employeeSkills) {
		super();
		this.id = id;
		this.internalId = internalId;
		this.contractorCode = contractorCode;
		this.empTypeCode = empTypeCode;
		this.isBillable = isBillable;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bloodGroup = bloodGroup;
		this.pfNo = pfNo;
		this.preEsiNo = preEsiNo;
		this.emailId = emailId;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.contactMobileNo = contactMobileNo;
		this.curAddress = curAddress;
		this.permAddress = permAddress;
		this.n1EmpId = n1EmpId;
		this.managerName = managerName;
		this.doj = doj;
		this.dob = dob;
		this.officeId = officeId;
		this.countryId = countryId;
		this.docId = docId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.bankDetails = bankDetails;
		this.employeeSkills = employeeSkills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	public String getContractorCode() {
		return contractorCode;
	}

	public void setContractorCode(String contractorCode) {
		this.contractorCode = contractorCode;
	}

	public String getEmpTypeCode() {
		return empTypeCode;
	}

	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}

	public String getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
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

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPfNo() {
		return pfNo;
	}

	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}

	public String getPreEsiNo() {
		return preEsiNo;
	}

	public void setPreEsiNo(String preEsiNo) {
		this.preEsiNo = preEsiNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getContactMobileNo() {
		return contactMobileNo;
	}

	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}

	public String getCurAddress() {
		return curAddress;
	}

	public void setCurAddress(String curAddress) {
		this.curAddress = curAddress;
	}

	public String getPermAddress() {
		return permAddress;
	}

	public void setPermAddress(String permAddress) {
		this.permAddress = permAddress;
	}

	public String getN1EmpId() {
		return n1EmpId;
	}

	public void setN1EmpId(String n1EmpId) {
		this.n1EmpId = n1EmpId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
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

	public List<BankDetails> getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(List<BankDetails> bankDetails) {
		this.bankDetails = bankDetails;
	}

	public List<EmployeeSkill> getEmployeeSkills() {
		return employeeSkills;
	}

	public void setEmployeeSkills(List<EmployeeSkill> employeeSkills) {
		this.employeeSkills = employeeSkills;
	}

	@Override
	public String toString() {
		return "Contractor [id=" + id + ", internalId=" + internalId + ", contractorCode=" + contractorCode
				+ ", empTypeCode=" + empTypeCode + ", isBillable=" + isBillable + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", bloodGroup=" + bloodGroup + ", pfNo=" + pfNo + ", preEsiNo=" + preEsiNo
				+ ", emailId=" + emailId + ", gender=" + gender + ", maritalStatus=" + maritalStatus
				+ ", contactMobileNo=" + contactMobileNo + ", curAddress=" + curAddress + ", permAddress=" + permAddress
				+ ", n1EmpId=" + n1EmpId + ", managerName=" + managerName + ", doj=" + doj + ", dob=" + dob
				+ ", officeId=" + officeId + ", countryId=" + countryId + ", docId=" + docId + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + ", bankDetails=" + bankDetails + ", employeeSkills=" + employeeSkills
				+ "]";
	}

	
	

	
}
