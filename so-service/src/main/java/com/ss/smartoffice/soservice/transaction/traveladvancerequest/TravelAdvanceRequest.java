package com.ss.smartoffice.soservice.transaction.traveladvancerequest;


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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name="t_tvl_adv_req")
public class TravelAdvanceRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String tarCode;
	@Column(name="m_employee_id")
	private String employeeId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=m_employee_id)")
	private String employeeName;
	@Formula("(select emp.emp_code from m_employee emp where emp.id=m_employee_id)")
	private String employeeCode;
	private String jobOrCc;
	private String costCenterId;
	@Formula("(select costCenter.cc_code from m_cost_center costCenter where costCenter.id=cost_center_id)")
	private String costCenterCode;
	@Formula("(select costCenter.cc_name from m_cost_center costCenter where costCenter.id=cost_center_id)")
	private String costCenterName;
	private String jobId;
	@Formula("(select job.job_code from t_job job where job.id=job_id)")
	private String jobCode;
//	@Formula("(select job.job_code from temp_job job where job.id=job_id)")
//	private String jobCode;
	private String  dayRangeId;
	@Formula("(select dayRange.day_range_name from s_day_range dayRange where dayRange.id=day_range_id)")
	private String dayRangeName;
	private double reqAdvAmt;
	private String isAddlTravelInvolved;
	private String empRemarks;
	private String tarStatus;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tarSubmittedDt;
	private String stayExpenseAmount;
	private String travelExpenseAmount;
	@Column(name="n1_ad_amount")
	private String n1AdjustmentAmount;
	@Column(name="n1_adj_remarks")
	private String n1AdjustmentRemarks;
	@Column(name="n1_employee_id")
	private String n1EmployeeId;
	
	@Formula("(select emp.emp_code from m_employee emp where emp.id=n1_employee_id)")
	private String n1EmployeeCode;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=n1_employee_id)")
	private String n1EmployeeName;
	
	@Column(name="n1_decision")
	private String n1Decision;
    @Column(name="n1_remarks")
	private String n1Remarks;
    @Column(name="n1_decision_dt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime n1DecisionDt;
    @Column(name="n1_is_addl_travel_involved")
    private String n1IsAddlTravelInvolved;
    @Column(name="n1_total_adv_amt")
	private String n1TotalAdvAmt;
	private String companyToPayAmt;
	private String empToPayAmt;
	private String accEmpAdjAmount;
	@Column(name="acc2_adj_remarks")
	private String acc2AdjRemarks;
	private String netAdvanceAmount;
	@Column(name="acc2_user_group_id")
	private String acc2UserGroupId;
	@Formula("(select usergroup.user_group_name from s_user_group usergroup where usergroup.id=acc2_user_group_id)")
	private String acc2UserGroupName;
	@Column(name="acc2_employee_id")
	private String acc2EmployeeId;
	
	@Formula("(select emp.emp_code from m_employee emp where emp.id=acc2_employee_id)")
	private String acc2EmployeeCode;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=acc2_employee_id)")
	private String acc2EmployeeName;
	
	
	@Column(name="acc2_remarks")
	private String acc2Remarks;
	@Column(name="acc2_decision")
	private String acc2Decision;
	@Column(name="acc2_decision_dt")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime acc2DecisionDt;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Transient
	private Long count;
	
	
	public TravelAdvanceRequest() {
		super();
		
	}
	
	


	public TravelAdvanceRequest(Long count) {
		super();
		this.count = count;
	}




	public TravelAdvanceRequest(Integer id, String tarCode, String employeeId, String employeeName, String employeeCode,
			double reqAdvAmt, String n1EmployeeId, String n1EmployeeCode,String acc2UserGroupId,String acc2UserGroupName) {
		super();
		this.id = id;
		this.tarCode = tarCode;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.reqAdvAmt = reqAdvAmt;
		this.n1EmployeeId = n1EmployeeId;
		this.n1EmployeeCode = n1EmployeeCode;
		this.acc2UserGroupId=acc2UserGroupId;
		this.acc2UserGroupName=acc2UserGroupName;
	}
//Travel Advance Request FindByEmployeeID
	public TravelAdvanceRequest(Integer id, String tarCode, String employeeId, String employeeName, String jobOrCc,
		String costCenterId, String costCenterCode, String jobId, String jobCode, String dayRangeName, double reqAdvAmt,
		String tarStatus,LocalDateTime tarSubmittedDt) {
	super();
	this.id = id;
	this.tarCode = tarCode;
	this.employeeId = employeeId;
	this.employeeName = employeeName;
	this.jobOrCc = jobOrCc;
	this.costCenterId = costCenterId;
	this.costCenterCode = costCenterCode;
	this.jobId = jobId;
	this.jobCode = jobCode;
	this.dayRangeName = dayRangeName;
	this.reqAdvAmt = reqAdvAmt;
	this.tarStatus = tarStatus;
	this.tarSubmittedDt=tarSubmittedDt;
}
	//
	public TravelAdvanceRequest(Integer id, String tarCode, String employeeId, String employeeName, String jobOrCc,
			String costCenterId, String costCenterCode, String jobId, String jobCode, String dayRangeName, double reqAdvAmt,
			String tarStatus,LocalDateTime tarSubmittedDt,String n1EmployeeId) {
		super();
		this.id = id;
		this.tarCode = tarCode;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.jobOrCc = jobOrCc;
		this.costCenterId = costCenterId;
		this.costCenterCode = costCenterCode;
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.dayRangeName = dayRangeName;
		this.reqAdvAmt = reqAdvAmt;
		this.tarStatus = tarStatus;
		this.tarSubmittedDt=tarSubmittedDt;
		this.n1EmployeeId=n1EmployeeId;
	}
	
	public TravelAdvanceRequest(Integer id, String tarCode, String employeeId, String employeeName, String jobOrCc,
			String costCenterId, String costCenterCode, String jobId, String jobCode, String dayRangeName, double reqAdvAmt,
			String tarStatus,LocalDateTime tarSubmittedDt,String n1EmployeeId,String acc2UserGroupId) {
		super();
		this.id = id;
		this.tarCode = tarCode;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.jobOrCc = jobOrCc;
		this.costCenterId = costCenterId;
		this.costCenterCode = costCenterCode;
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.dayRangeName = dayRangeName;
		this.reqAdvAmt = reqAdvAmt;
		this.tarStatus = tarStatus;
		this.tarSubmittedDt=tarSubmittedDt;
		this.n1EmployeeId=n1EmployeeId;
		this.acc2UserGroupId=acc2UserGroupId;
	}




	public TravelAdvanceRequest(Integer id, String tarCode, String employeeId, String employeeName, String employeeCode,
			String jobOrCc, String costCenterId, String costCenterCode, String costCenterName, String jobId,
			String jobCode, String dayRangeId, String dayRangeName, double reqAdvAmt, String isAddlTravelInvolved,
			String empRemarks, String tarStatus, LocalDateTime tarSubmittedDt, String stayExpenseAmount,
			String travelExpenseAmount, String n1AdjustmentAmount, String n1AdjustmentRemarks, String n1EmployeeId,
			String n1EmployeeCode, String n1EmployeeName, String n1Decision, String n1Remarks,
			LocalDateTime n1DecisionDt, String n1IsAddlTravelInvolved, String n1TotalAdvAmt, String companyToPayAmt,
			String empToPayAmt, String accEmpAdjAmount, String acc2AdjRemarks, String netAdvanceAmount,
			String acc2UserGroupId, String acc2UserGroupName, String acc2EmployeeId, String acc2EmployeeCode,
			String acc2EmployeeName, String acc2Remarks, String acc2Decision, LocalDateTime acc2DecisionDt,
			String isEnabled, String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt,
			Long count) {
		super();
		this.id = id;
		this.tarCode = tarCode;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.jobOrCc = jobOrCc;
		this.costCenterId = costCenterId;
		this.costCenterCode = costCenterCode;
		this.costCenterName = costCenterName;
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.dayRangeId = dayRangeId;
		this.dayRangeName = dayRangeName;
		this.reqAdvAmt = reqAdvAmt;
		this.isAddlTravelInvolved = isAddlTravelInvolved;
		this.empRemarks = empRemarks;
		this.tarStatus = tarStatus;
		this.tarSubmittedDt = tarSubmittedDt;
		this.stayExpenseAmount = stayExpenseAmount;
		this.travelExpenseAmount = travelExpenseAmount;
		this.n1AdjustmentAmount = n1AdjustmentAmount;
		this.n1AdjustmentRemarks = n1AdjustmentRemarks;
		this.n1EmployeeId = n1EmployeeId;
		this.n1EmployeeCode = n1EmployeeCode;
		this.n1EmployeeName = n1EmployeeName;
		this.n1Decision = n1Decision;
		this.n1Remarks = n1Remarks;
		this.n1DecisionDt = n1DecisionDt;
		this.n1IsAddlTravelInvolved = n1IsAddlTravelInvolved;
		this.n1TotalAdvAmt = n1TotalAdvAmt;
		this.companyToPayAmt = companyToPayAmt;
		this.empToPayAmt = empToPayAmt;
		this.accEmpAdjAmount = accEmpAdjAmount;
		this.acc2AdjRemarks = acc2AdjRemarks;
		this.netAdvanceAmount = netAdvanceAmount;
		this.acc2UserGroupId = acc2UserGroupId;
		this.acc2UserGroupName = acc2UserGroupName;
		this.acc2EmployeeId = acc2EmployeeId;
		this.acc2EmployeeCode = acc2EmployeeCode;
		this.acc2EmployeeName = acc2EmployeeName;
		this.acc2Remarks = acc2Remarks;
		this.acc2Decision = acc2Decision;
		this.acc2DecisionDt = acc2DecisionDt;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.count = count;
	}




	@Override
	public String toString() {
		return "TravelAdvanceRequest [id=" + id + ", tarCode=" + tarCode + ", employeeId=" + employeeId
				+ ", employeeName=" + employeeName + ", employeeCode=" + employeeCode + ", jobOrCc=" + jobOrCc
				+ ", costCenterId=" + costCenterId + ", costCenterCode=" + costCenterCode + ", costCenterName="
				+ costCenterName + ", jobId=" + jobId + ", jobCode=" + jobCode + ", dayRangeId=" + dayRangeId
				+ ", dayRangeName=" + dayRangeName + ", reqAdvAmt=" + reqAdvAmt + ", isAddlTravelInvolved="
				+ isAddlTravelInvolved + ", empRemarks=" + empRemarks + ", tarStatus=" + tarStatus + ", tarSubmittedDt="
				+ tarSubmittedDt + ", stayExpenseAmount=" + stayExpenseAmount + ", travelExpenseAmount="
				+ travelExpenseAmount + ", n1AdjustmentAmount=" + n1AdjustmentAmount + ", n1AdjustmentRemarks="
				+ n1AdjustmentRemarks + ", n1EmployeeId=" + n1EmployeeId + ", n1EmployeeCode=" + n1EmployeeCode
				+ ", n1EmployeeName=" + n1EmployeeName + ", n1Decision=" + n1Decision + ", n1Remarks=" + n1Remarks
				+ ", n1DecisionDt=" + n1DecisionDt + ", n1IsAddlTravelInvolved=" + n1IsAddlTravelInvolved
				+ ", n1TotalAdvAmt=" + n1TotalAdvAmt + ", companyToPayAmt=" + companyToPayAmt + ", empToPayAmt="
				+ empToPayAmt + ", accEmpAdjAmount=" + accEmpAdjAmount + ", acc2AdjRemarks=" + acc2AdjRemarks
				+ ", netAdvanceAmount=" + netAdvanceAmount + ", acc2UserGroupId=" + acc2UserGroupId
				+ ", acc2UserGroupName=" + acc2UserGroupName + ", acc2EmployeeId=" + acc2EmployeeId
				+ ", acc2EmployeeCode=" + acc2EmployeeCode + ", acc2EmployeeName=" + acc2EmployeeName + ", acc2Remarks="
				+ acc2Remarks + ", acc2Decision=" + acc2Decision + ", acc2DecisionDt=" + acc2DecisionDt + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + ", count=" + count + "]";
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getTarCode() {
		return tarCode;
	}




	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}




	public String getEmployeeId() {
		return employeeId;
	}




	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}




	public String getEmployeeName() {
		return employeeName;
	}




	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}




	public String getEmployeeCode() {
		return employeeCode;
	}




	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}




	public String getJobOrCc() {
		return jobOrCc;
	}




	public void setJobOrCc(String jobOrCc) {
		this.jobOrCc = jobOrCc;
	}




	public String getCostCenterId() {
		return costCenterId;
	}




	public void setCostCenterId(String costCenterId) {
		this.costCenterId = costCenterId;
	}




	public String getCostCenterCode() {
		return costCenterCode;
	}




	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}




	public String getCostCenterName() {
		return costCenterName;
	}




	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}




	public String getJobId() {
		return jobId;
	}




	public void setJobId(String jobId) {
		this.jobId = jobId;
	}




	public String getJobCode() {
		return jobCode;
	}




	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}




	public String getDayRangeId() {
		return dayRangeId;
	}




	public void setDayRangeId(String dayRangeId) {
		this.dayRangeId = dayRangeId;
	}




	public String getDayRangeName() {
		return dayRangeName;
	}




	public void setDayRangeName(String dayRangeName) {
		this.dayRangeName = dayRangeName;
	}




	public double getReqAdvAmt() {
		return reqAdvAmt;
	}




	public void setReqAdvAmt(double reqAdvAmt) {
		this.reqAdvAmt = reqAdvAmt;
	}




	public String getIsAddlTravelInvolved() {
		return isAddlTravelInvolved;
	}




	public void setIsAddlTravelInvolved(String isAddlTravelInvolved) {
		this.isAddlTravelInvolved = isAddlTravelInvolved;
	}




	public String getEmpRemarks() {
		return empRemarks;
	}




	public void setEmpRemarks(String empRemarks) {
		this.empRemarks = empRemarks;
	}




	public String getTarStatus() {
		return tarStatus;
	}




	public void setTarStatus(String tarStatus) {
		this.tarStatus = tarStatus;
	}




	public LocalDateTime getTarSubmittedDt() {
		return tarSubmittedDt;
	}




	public void setTarSubmittedDt(LocalDateTime tarSubmittedDt) {
		this.tarSubmittedDt = tarSubmittedDt;
	}




	public String getStayExpenseAmount() {
		return stayExpenseAmount;
	}




	public void setStayExpenseAmount(String stayExpenseAmount) {
		this.stayExpenseAmount = stayExpenseAmount;
	}




	public String getTravelExpenseAmount() {
		return travelExpenseAmount;
	}




	public void setTravelExpenseAmount(String travelExpenseAmount) {
		this.travelExpenseAmount = travelExpenseAmount;
	}




	public String getN1AdjustmentAmount() {
		return n1AdjustmentAmount;
	}




	public void setN1AdjustmentAmount(String n1AdjustmentAmount) {
		this.n1AdjustmentAmount = n1AdjustmentAmount;
	}




	public String getN1AdjustmentRemarks() {
		return n1AdjustmentRemarks;
	}




	public void setN1AdjustmentRemarks(String n1AdjustmentRemarks) {
		this.n1AdjustmentRemarks = n1AdjustmentRemarks;
	}




	public String getN1EmployeeId() {
		return n1EmployeeId;
	}




	public void setN1EmployeeId(String n1EmployeeId) {
		this.n1EmployeeId = n1EmployeeId;
	}




	public String getN1EmployeeCode() {
		return n1EmployeeCode;
	}




	public void setN1EmployeeCode(String n1EmployeeCode) {
		this.n1EmployeeCode = n1EmployeeCode;
	}




	public String getN1EmployeeName() {
		return n1EmployeeName;
	}




	public void setN1EmployeeName(String n1EmployeeName) {
		this.n1EmployeeName = n1EmployeeName;
	}




	public String getN1Decision() {
		return n1Decision;
	}




	public void setN1Decision(String n1Decision) {
		this.n1Decision = n1Decision;
	}




	public String getN1Remarks() {
		return n1Remarks;
	}




	public void setN1Remarks(String n1Remarks) {
		this.n1Remarks = n1Remarks;
	}




	public LocalDateTime getN1DecisionDt() {
		return n1DecisionDt;
	}




	public void setN1DecisionDt(LocalDateTime n1DecisionDt) {
		this.n1DecisionDt = n1DecisionDt;
	}




	public String getN1IsAddlTravelInvolved() {
		return n1IsAddlTravelInvolved;
	}




	public void setN1IsAddlTravelInvolved(String n1IsAddlTravelInvolved) {
		this.n1IsAddlTravelInvolved = n1IsAddlTravelInvolved;
	}




	public String getN1TotalAdvAmt() {
		return n1TotalAdvAmt;
	}




	public void setN1TotalAdvAmt(String n1TotalAdvAmt) {
		this.n1TotalAdvAmt = n1TotalAdvAmt;
	}




	public String getCompanyToPayAmt() {
		return companyToPayAmt;
	}




	public void setCompanyToPayAmt(String companyToPayAmt) {
		this.companyToPayAmt = companyToPayAmt;
	}




	public String getEmpToPayAmt() {
		return empToPayAmt;
	}




	public void setEmpToPayAmt(String empToPayAmt) {
		this.empToPayAmt = empToPayAmt;
	}




	public String getAccEmpAdjAmount() {
		return accEmpAdjAmount;
	}




	public void setAccEmpAdjAmount(String accEmpAdjAmount) {
		this.accEmpAdjAmount = accEmpAdjAmount;
	}




	public String getAcc2AdjRemarks() {
		return acc2AdjRemarks;
	}




	public void setAcc2AdjRemarks(String acc2AdjRemarks) {
		this.acc2AdjRemarks = acc2AdjRemarks;
	}




	public String getNetAdvanceAmount() {
		return netAdvanceAmount;
	}




	public void setNetAdvanceAmount(String netAdvanceAmount) {
		this.netAdvanceAmount = netAdvanceAmount;
	}




	public String getAcc2UserGroupId() {
		return acc2UserGroupId;
	}




	public void setAcc2UserGroupId(String acc2UserGroupId) {
		this.acc2UserGroupId = acc2UserGroupId;
	}




	public String getAcc2UserGroupName() {
		return acc2UserGroupName;
	}




	public void setAcc2UserGroupName(String acc2UserGroupName) {
		this.acc2UserGroupName = acc2UserGroupName;
	}




	public String getAcc2EmployeeId() {
		return acc2EmployeeId;
	}




	public void setAcc2EmployeeId(String acc2EmployeeId) {
		this.acc2EmployeeId = acc2EmployeeId;
	}




	public String getAcc2EmployeeCode() {
		return acc2EmployeeCode;
	}




	public void setAcc2EmployeeCode(String acc2EmployeeCode) {
		this.acc2EmployeeCode = acc2EmployeeCode;
	}




	public String getAcc2EmployeeName() {
		return acc2EmployeeName;
	}




	public void setAcc2EmployeeName(String acc2EmployeeName) {
		this.acc2EmployeeName = acc2EmployeeName;
	}




	public String getAcc2Remarks() {
		return acc2Remarks;
	}




	public void setAcc2Remarks(String acc2Remarks) {
		this.acc2Remarks = acc2Remarks;
	}




	public String getAcc2Decision() {
		return acc2Decision;
	}




	public void setAcc2Decision(String acc2Decision) {
		this.acc2Decision = acc2Decision;
	}




	public LocalDateTime getAcc2DecisionDt() {
		return acc2DecisionDt;
	}




	public void setAcc2DecisionDt(LocalDateTime acc2DecisionDt) {
		this.acc2DecisionDt = acc2DecisionDt;
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




	public LocalDateTime getCreatedDt() {
		return createdDt;
	}




	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}




	public String getModifiedBy() {
		return modifiedBy;
	}




	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}




	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}




	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}




	public Long getCount() {
		return count;
	}




	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
}
