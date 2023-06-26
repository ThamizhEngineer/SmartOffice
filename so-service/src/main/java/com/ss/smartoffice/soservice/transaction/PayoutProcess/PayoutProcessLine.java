package com.ss.smartoffice.soservice.transaction.PayoutProcess;
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
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name = "t_payout_process_line")

@Scope("prototype")
public class PayoutProcessLine extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="t_payout_process_hdr_id")
	private Integer payoutProcessHdrId;
	@Column(name="employee_id")
	private Integer employeeId;
	@Formula("(SELECT emp.emp_code FROM m_employee emp WHERE emp.id= employee_id)")
	private String empCode;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id= employee_id)")
	private String empName;
	@Column(name="result_code")
	private String resultCode;
	@Column(name="result_desc")
	private String resultDesc;
	@Column(name = "is_enabled")
	private String isEnabled;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "created_dt")
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@Column(name = "modified_dt")
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public PayoutProcessLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PayoutProcessLine(int id, Integer payoutProcessHdrId, Integer employeeId, String empCode, String empName,
			String resultCode, String resultDesc, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.payoutProcessHdrId = payoutProcessHdrId;
		this.employeeId = employeeId;
		this.empCode = empCode;
		this.empName = empName;
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
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
	public Integer getPayoutProcessHdrId() {
		return payoutProcessHdrId;
	}
	public void setPayoutProcessHdrId(Integer payoutProcessHdrId) {
		this.payoutProcessHdrId = payoutProcessHdrId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
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
		return "PayoutProcessLine [id=" + id + ", payoutProcessHdrId=" + payoutProcessHdrId + ", employeeId="
				+ employeeId + ", empCode=" + empCode + ", empName=" + empName + ", resultCode=" + resultCode
				+ ", resultDesc=" + resultDesc + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
	
}
