package com.ss.smartoffice.soservice.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name="t_payroll_hdr")

public class PayrollHeader extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
    @Column(name="t_payroll_batch_id")
	private int payrollBatchId;
    @Column(name="t_emp_pay_adj_id")
	private int empPayAdjId;
    @Column(name="m_emp_id")
	private int empId;
    @Column(name="m_emp_pay_structure_id")
	private int empPayStructureId;
    @Column(name="month")
	private int month;
    @Column(name="year")
	private int year;
    @Column(name="emp_code")
	private String empCode;
    @Column(name="emp_fname")
	private String empFirstName;
    @Column(name="emp_lname")
	private String empLastName;
    @Column(name="pf_no")
	private String pfNo;
    @Column(name="bank_account_no")
	private String bankAccountNumber;
    @Column(name="emp_grade")
	private String empGrade;
    @Column(name="designation")
	private String designation;
    @Column(name="dob")
	private LocalDateTime dob;
    @Column(name="payment_mode")
	private String paymentMode;
    @Column(name="bank_ifsc_code")
	private String bankIfscCode;
    @Column(name="bank_name")
	private String bankName;
    @Column(name="bank_branch_name")
	private String bankBranchName;
    @Column(name="total_ctc")
	private BigDecimal totalCtc;
    @Column(name="total_earnings")
	private BigDecimal totalEarnings;
    @Column(name="total_deductions")
	private BigDecimal totalDeductions;
    @Column(name="net_salary")
	private BigDecimal netSalary;
    @Column(name="is_enabled")
	private String isEnabled;
    @Column(name="created_by")
	private String createdBy;
    @Column(name="modified_by")
	private String modifiedBy;
    @Column(name="remarks")
	private String remarks;
    @Column(name="created_dt")
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
    @Column(name="modified_dt")
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="t_payroll_hdr_id")
    private List<PayrollDetail> payrollDetails;
	
	public PayrollHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PayrollHeader [id=" + id + ", payrollBatchId=" + payrollBatchId + ", empPayAdjId=" + empPayAdjId
				+ ", empId=" + empId + ", empPayStructureId=" + empPayStructureId + ", month=" + month + ", year="
				+ year + ", empCode=" + empCode + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", pfNo=" + pfNo + ", bankAccountNumber=" + bankAccountNumber + ", empGrade=" + empGrade
				+ ", designation=" + designation + ", dob=" + dob + ", paymentMode=" + paymentMode + ", bankIfscCode="
				+ bankIfscCode + ", bankName=" + bankName + ", bankBranchName=" + bankBranchName + ", totalCtc="
				+ totalCtc + ", totalEarnings=" + totalEarnings + ", totalDeductions=" + totalDeductions
				+ ", netSalary=" + netSalary + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", remarks=" + remarks + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", payrollDetails=" + payrollDetails + "]";
	}

	public PayrollHeader(int id, int payrollBatchId, int empPayAdjId, int empId, int empPayStructureId, int month,
			int year, String empCode, String empFirstName, String empLastName, String pfNo, String bankAccountNumber,
			String empGrade, String designation, LocalDateTime dob, String paymentMode, String bankIfscCode,
			String bankName, String bankBranchName, BigDecimal totalCtc, BigDecimal totalEarnings,
			BigDecimal totalDeductions, BigDecimal netSalary, String isEnabled, String createdBy, String modifiedBy,
			String remarks, LocalDateTime createdDt, LocalDateTime modifiedDt, List<PayrollDetail> payrollDetails) {
		super();
		this.id = id;
		this.payrollBatchId = payrollBatchId;
		this.empPayAdjId = empPayAdjId;
		this.empId = empId;
		this.empPayStructureId = empPayStructureId;
		this.month = month;
		this.year = year;
		this.empCode = empCode;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.pfNo = pfNo;
		this.bankAccountNumber = bankAccountNumber;
		this.empGrade = empGrade;
		this.designation = designation;
		this.dob = dob;
		this.paymentMode = paymentMode;
		this.bankIfscCode = bankIfscCode;
		this.bankName = bankName;
		this.bankBranchName = bankBranchName;
		this.totalCtc = totalCtc;
		this.totalEarnings = totalEarnings;
		this.totalDeductions = totalDeductions;
		this.netSalary = netSalary;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.remarks = remarks;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.payrollDetails = payrollDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPayrollBatchId() {
		return payrollBatchId;
	}

	public void setPayrollBatchId(int payrollBatchId) {
		this.payrollBatchId = payrollBatchId;
	}

	public int getEmpPayAdjId() {
		return empPayAdjId;
	}

	public void setEmpPayAdjId(int empPayAdjId) {
		this.empPayAdjId = empPayAdjId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEmpPayStructureId() {
		return empPayStructureId;
	}

	public void setEmpPayStructureId(int empPayStructureId) {
		this.empPayStructureId = empPayStructureId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getPfNo() {
		return pfNo;
	}

	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getEmpGrade() {
		return empGrade;
	}

	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getBankIfscCode() {
		return bankIfscCode;
	}

	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
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

	public BigDecimal getTotalCtc() {
		return totalCtc;
	}

	public void setTotalCtc(BigDecimal totalCtc) {
		this.totalCtc = totalCtc;
	}

	public BigDecimal getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(BigDecimal totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public BigDecimal getTotalDeductions() {
		return totalDeductions;
	}

	public void setTotalDeductions(BigDecimal totalDeductions) {
		this.totalDeductions = totalDeductions;
	}

	public BigDecimal getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
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

	public List<PayrollDetail> getPayrollDetails() {
		return payrollDetails;
	}

	public void setPayrollDetails(List<PayrollDetail> payrollDetails) {
		this.payrollDetails = payrollDetails;
	}

	

}
