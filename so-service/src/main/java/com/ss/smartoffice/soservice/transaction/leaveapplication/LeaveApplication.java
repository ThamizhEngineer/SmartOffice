package com.ss.smartoffice.soservice.transaction.leaveapplication;
import java.time.LocalDate;
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
@Table(name="t_emp_leave_appln")


public class LeaveApplication {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String leaveCode;
	@Column(name="m_employee_id")
	private String employeeId;
	@Formula("(select emp.first_name from m_employee emp where emp.id=m_employee_id)")
	private String empFname;
	@Formula("(select emp.emp_code from m_employee emp where emp.id=m_employee_id)")
	private String employeeCode;
	@Formula("(select emp.last_name from m_employee emp where emp.id=m_employee_id)")
	private String empLname;
	@Column(name="s_leave_type_id")
	private String leaveTypeId;
	@Formula("(select leaveType.leave_type_name from s_leave_type leaveType where leaveType.id=s_leave_type_id)")
	private String leaveTypeName;
	@Formula("(select emp.shift_id from m_employee emp where emp.id=m_employee_id)")
	private String shiftId;
	@Formula("(SELECT shift.Shift_name from m_employee emp LEFT join m_attendance_shift shift on shift.id=emp.shift_id where emp.id=m_employee_id)")
	private String shiftName;
	@Formula("(SELECT shift.Shift_code from m_employee emp LEFT join m_attendance_shift shift on shift.id=emp.shift_id where emp.id=m_employee_id)")
	private String shiftCode;
	@Formula("(select leaveType.leave_type_code from s_leave_type leaveType where leaveType.id=s_leave_type_id)")
	private String leaveTypeCode;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDt;
	private String startSession;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate leaveAppliedDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDt;
	private String endSession;
	private String reason;
	private String leaveStatus;
	@Formula("(select status.status_code from s_status status where status.entity_code='LEAVE-APPLICATION'and status.status_code=leave_status)")
	private String leaveStatusName;
	private String eligibilityStatusCode;
	@Formula("(SELECT config.config_dtl_name from v_config config where config.config_header_code='LEAVE-ELIGLIBILITY-STATUS'and config.config_dtl_code=eligibility_status_code)")
	private String eligibilityStatusName;
	
	private String eligibleRemarks;
	private String n1EmpId;
	@Formula("(select emp.emp_code from m_employee emp where emp.id=n1emp_id)")
	private String n1EmpCode;
	@Formula("(select emp.first_name from m_employee emp where emp.id=n1emp_id)")
	private String n1EmpFname;
	@Formula("(select emp.last_name from m_employee emp where emp.id=n1emp_id)")
	private String n1EmpLname;
	private String n1Decision;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime n1DecisionDt;
	private String n1Remarks;
	private String needN2Approval;
	private String n2EmpId;
	@Formula("(select emp.emp_code from m_employee emp where emp.id=n2emp_id)")
	private String n2EmpCode;
	@Formula("(select emp.first_name from m_employee emp where emp.id=n2emp_id)")
	private String n2EmpFname;
	@Formula("(select emp.last_name from m_employee emp where emp.id=n2emp_id)")
	private String n2EmpLname;
	private String n2Decision;    
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime n2DecisionDt;
	private String n2Remarks;
	private String isSettled;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
     private LocalDateTime hr1SettlementDt;
	private String hr1UserGroupId;
	@Formula("(select userGroup.user_group_name from s_user_group userGroup where userGroup.id=hr1user_group_id)")
	private String hr1UserGroupName;
	private String hr1EmpId;
	@Formula("(select emp.first_name from m_employee emp where emp.id=hr1emp_id)")
	private String hr1EmpFname;
	@Formula("(select emp.last_name from m_employee emp where emp.id=hr1emp_id)")
	private String hr1EmpLname;
	@Transient
	private String eligible;
	@Transient
	private double eligibleDuration;
	private String jobAckgmt;
	private String emgncyAvailability;
	private String leaveDenialAckgmt;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDt;
	public LeaveApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LeaveApplication(Integer id, String leaveCode, String employeeId,String empFname,String empLname,String leaveTypeId,String leaveTypeName, String leaveTypeCode, LocalDate startDt,
			String startSession, LocalDate leaveAppliedDate, LocalDate endDt, String endSession, String leaveStatus,String leaveStatusName,
			String n1EmpId,String n1EmpCode, String n1EmpFname,
			String n1EmpLname, String n2EmpId,String n2EmpCode, String n2EmpFname,
			String n2EmpLname) {
		super();
		this.id = id;
		this.leaveCode = leaveCode;
		this.employeeId=employeeId;
		this.empFname = empFname;
		this.empLname = empLname;
		this.leaveTypeId = leaveTypeId;
		this.leaveTypeCode = leaveTypeCode;
		this.leaveTypeName = leaveTypeName;
		
		this.startDt = startDt;
		this.startSession = startSession;
		
		
		this.leaveAppliedDate = leaveAppliedDate;
		
		this.endDt = endDt;
		this.endSession = endSession;
		
		this.leaveStatus = leaveStatus;
		this.leaveStatusName = leaveStatusName;
		this.n1EmpId = n1EmpId;
		this.n1EmpCode = n1EmpCode;
		this.n1EmpFname = n1EmpFname;
		this.n1EmpLname = n1EmpLname;
		
		
		this.n2EmpId = n2EmpId;;
		this.n2EmpCode = n2EmpCode;
		this.n2EmpFname = n2EmpFname;
		this.n2EmpLname = n2EmpLname;
	}
	public LeaveApplication(Integer id, String leaveCode, String employeeId,String empFname,String empLname,String leaveTypeId,String leaveTypeName, String leaveTypeCode, LocalDate startDt,
			String startSession, LocalDate leaveAppliedDate, LocalDate endDt, String endSession, String leaveStatus,String leaveStatusName,
			String n1EmpId,String n1EmpCode, String n1EmpFname,
			String n1EmpLname, String n2EmpId,String n2EmpCode, String n2EmpFname,
			String n2EmpLname,String hr1EmpId, String hr1EmpFname, String hr1EmpLname,String isSettled) {
		super();
		this.id = id;
		this.leaveCode = leaveCode;
		this.employeeId=employeeId;
		this.empFname = empFname;
		this.empLname = empLname;
		this.leaveTypeId = leaveTypeId;
		this.leaveTypeCode = leaveTypeCode;
		this.leaveTypeName = leaveTypeName;
		
		this.startDt = startDt;
		this.startSession = startSession;
		
		
		this.leaveAppliedDate = leaveAppliedDate;
		
		this.endDt = endDt;
		this.endSession = endSession;
		
		this.leaveStatus = leaveStatus;
		this.leaveStatusName = leaveStatusName;
		this.n1EmpId = n1EmpId;
		this.n1EmpCode = n1EmpCode;
		this.n1EmpFname = n1EmpFname;
		this.n1EmpLname = n1EmpLname;
		
		
		this.n2EmpId = n2EmpId;;
		this.n2EmpCode = n2EmpCode;
		this.n2EmpFname = n2EmpFname;
		this.n2EmpLname = n2EmpLname;
		
		this.hr1EmpId = hr1EmpId;;
		
		this.hr1EmpFname = hr1EmpFname;
		this.hr1EmpLname = hr1EmpLname;
		this.isSettled = isSettled;
	}

	public LeaveApplication(Integer id, String leaveCode, String employeeId, String empFname, String employeeCode,
			String empLname, String leaveTypeId, String leaveTypeName, String shiftId, String shiftName,
			String shiftCode, String leaveTypeCode, LocalDate startDt, String startSession, LocalDate leaveAppliedDate,
			LocalDate endDt, String endSession, String reason, String leaveStatus, String leaveStatusName,
			String eligibilityStatusCode, String eligibilityStatusName, String eligibleRemarks, String n1EmpId,
			String n1EmpCode, String n1EmpFname, String n1EmpLname, String n1Decision, LocalDateTime n1DecisionDt,
			String n1Remarks, String needN2Approval, String n2EmpId, String n2EmpCode, String n2EmpFname,
			String n2EmpLname, String n2Decision, LocalDateTime n2DecisionDt, String n2Remarks, String isSettled,
			LocalDateTime hr1SettlementDt, String hr1UserGroupId, String hr1UserGroupName, String hr1EmpId,
			String hr1EmpFname, String hr1EmpLname, String eligible, double eligibleDuration, String jobAckgmt,
			String emgncyAvailability, String leaveDenialAckgmt, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.leaveCode = leaveCode;
		this.employeeId = employeeId;
		this.empFname = empFname;
		this.employeeCode = employeeCode;
		this.empLname = empLname;
		this.leaveTypeId = leaveTypeId;
		this.leaveTypeName = leaveTypeName;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.shiftCode = shiftCode;
		this.leaveTypeCode = leaveTypeCode;
		this.startDt = startDt;
		this.startSession = startSession;
		this.leaveAppliedDate = leaveAppliedDate;
		this.endDt = endDt;
		this.endSession = endSession;
		this.reason = reason;
		this.leaveStatus = leaveStatus;
		this.leaveStatusName = leaveStatusName;
		this.eligibilityStatusCode = eligibilityStatusCode;
		this.eligibilityStatusName = eligibilityStatusName;
		this.eligibleRemarks = eligibleRemarks;
		this.n1EmpId = n1EmpId;
		this.n1EmpCode = n1EmpCode;
		this.n1EmpFname = n1EmpFname;
		this.n1EmpLname = n1EmpLname;
		this.n1Decision = n1Decision;
		this.n1DecisionDt = n1DecisionDt;
		this.n1Remarks = n1Remarks;
		this.needN2Approval = needN2Approval;
		this.n2EmpId = n2EmpId;
		this.n2EmpCode = n2EmpCode;
		this.n2EmpFname = n2EmpFname;
		this.n2EmpLname = n2EmpLname;
		this.n2Decision = n2Decision;
		this.n2DecisionDt = n2DecisionDt;
		this.n2Remarks = n2Remarks;
		this.isSettled = isSettled;
		this.hr1SettlementDt = hr1SettlementDt;
		this.hr1UserGroupId = hr1UserGroupId;
		this.hr1UserGroupName = hr1UserGroupName;
		this.hr1EmpId = hr1EmpId;
		this.hr1EmpFname = hr1EmpFname;
		this.hr1EmpLname = hr1EmpLname;
		this.eligible = eligible;
		this.eligibleDuration = eligibleDuration;
		this.jobAckgmt = jobAckgmt;
		this.emgncyAvailability = emgncyAvailability;
		this.leaveDenialAckgmt = leaveDenialAckgmt;
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

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmpLname() {
		return empLname;
	}

	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}

	public String getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(String leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}

	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public LocalDate getStartDt() {
		return startDt;
	}

	public void setStartDt(LocalDate startDt) {
		this.startDt = startDt;
	}

	public String getStartSession() {
		return startSession;
	}

	public void setStartSession(String startSession) {
		this.startSession = startSession;
	}

	public LocalDate getLeaveAppliedDate() {
		return leaveAppliedDate;
	}

	public void setLeaveAppliedDate(LocalDate leaveAppliedDate) {
		this.leaveAppliedDate = leaveAppliedDate;
	}

	public LocalDate getEndDt() {
		return endDt;
	}

	public void setEndDt(LocalDate endDt) {
		this.endDt = endDt;
	}

	public String getEndSession() {
		return endSession;
	}

	public void setEndSession(String endSession) {
		this.endSession = endSession;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getLeaveStatusName() {
		return leaveStatusName;
	}

	public void setLeaveStatusName(String leaveStatusName) {
		this.leaveStatusName = leaveStatusName;
	}

	public String getEligibilityStatusCode() {
		return eligibilityStatusCode;
	}

	public void setEligibilityStatusCode(String eligibilityStatusCode) {
		this.eligibilityStatusCode = eligibilityStatusCode;
	}

	public String getEligibilityStatusName() {
		return eligibilityStatusName;
	}

	public void setEligibilityStatusName(String eligibilityStatusName) {
		this.eligibilityStatusName = eligibilityStatusName;
	}

	public String getEligibleRemarks() {
		return eligibleRemarks;
	}

	public void setEligibleRemarks(String eligibleRemarks) {
		this.eligibleRemarks = eligibleRemarks;
	}

	public String getN1EmpId() {
		return n1EmpId;
	}

	public void setN1EmpId(String n1EmpId) {
		this.n1EmpId = n1EmpId;
	}

	public String getN1EmpCode() {
		return n1EmpCode;
	}

	public void setN1EmpCode(String n1EmpCode) {
		this.n1EmpCode = n1EmpCode;
	}

	public String getN1EmpFname() {
		return n1EmpFname;
	}

	public void setN1EmpFname(String n1EmpFname) {
		this.n1EmpFname = n1EmpFname;
	}

	public String getN1EmpLname() {
		return n1EmpLname;
	}

	public void setN1EmpLname(String n1EmpLname) {
		this.n1EmpLname = n1EmpLname;
	}

	public String getN1Decision() {
		return n1Decision;
	}

	public void setN1Decision(String n1Decision) {
		this.n1Decision = n1Decision;
	}

	public LocalDateTime getN1DecisionDt() {
		return n1DecisionDt;
	}

	public void setN1DecisionDt(LocalDateTime n1DecisionDt) {
		this.n1DecisionDt = n1DecisionDt;
	}

	public String getN1Remarks() {
		return n1Remarks;
	}

	public void setN1Remarks(String n1Remarks) {
		this.n1Remarks = n1Remarks;
	}

	public String getNeedN2Approval() {
		return needN2Approval;
	}

	public void setNeedN2Approval(String needN2Approval) {
		this.needN2Approval = needN2Approval;
	}

	public String getN2EmpId() {
		return n2EmpId;
	}

	public void setN2EmpId(String n2EmpId) {
		this.n2EmpId = n2EmpId;
	}

	public String getN2EmpCode() {
		return n2EmpCode;
	}

	public void setN2EmpCode(String n2EmpCode) {
		this.n2EmpCode = n2EmpCode;
	}

	public String getN2EmpFname() {
		return n2EmpFname;
	}

	public void setN2EmpFname(String n2EmpFname) {
		this.n2EmpFname = n2EmpFname;
	}

	public String getN2EmpLname() {
		return n2EmpLname;
	}

	public void setN2EmpLname(String n2EmpLname) {
		this.n2EmpLname = n2EmpLname;
	}

	public String getN2Decision() {
		return n2Decision;
	}

	public void setN2Decision(String n2Decision) {
		this.n2Decision = n2Decision;
	}

	public LocalDateTime getN2DecisionDt() {
		return n2DecisionDt;
	}

	public void setN2DecisionDt(LocalDateTime n2DecisionDt) {
		this.n2DecisionDt = n2DecisionDt;
	}

	public String getN2Remarks() {
		return n2Remarks;
	}

	public void setN2Remarks(String n2Remarks) {
		this.n2Remarks = n2Remarks;
	}

	public String getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(String isSettled) {
		this.isSettled = isSettled;
	}

	public LocalDateTime getHr1SettlementDt() {
		return hr1SettlementDt;
	}

	public void setHr1SettlementDt(LocalDateTime hr1SettlementDt) {
		this.hr1SettlementDt = hr1SettlementDt;
	}

	public String getHr1UserGroupId() {
		return hr1UserGroupId;
	}

	public void setHr1UserGroupId(String hr1UserGroupId) {
		this.hr1UserGroupId = hr1UserGroupId;
	}

	public String getHr1UserGroupName() {
		return hr1UserGroupName;
	}

	public void setHr1UserGroupName(String hr1UserGroupName) {
		this.hr1UserGroupName = hr1UserGroupName;
	}

	public String getHr1EmpId() {
		return hr1EmpId;
	}

	public void setHr1EmpId(String hr1EmpId) {
		this.hr1EmpId = hr1EmpId;
	}

	public String getHr1EmpFname() {
		return hr1EmpFname;
	}

	public void setHr1EmpFname(String hr1EmpFname) {
		this.hr1EmpFname = hr1EmpFname;
	}

	public String getHr1EmpLname() {
		return hr1EmpLname;
	}

	public void setHr1EmpLname(String hr1EmpLname) {
		this.hr1EmpLname = hr1EmpLname;
	}

	public String getEligible() {
		return eligible;
	}

	public void setEligible(String eligible) {
		this.eligible = eligible;
	}

	public double getEligibleDuration() {
		return eligibleDuration;
	}

	public void setEligibleDuration(double eligibleDuration) {
		this.eligibleDuration = eligibleDuration;
	}

	public String getJobAckgmt() {
		return jobAckgmt;
	}

	public void setJobAckgmt(String jobAckgmt) {
		this.jobAckgmt = jobAckgmt;
	}

	public String getEmgncyAvailability() {
		return emgncyAvailability;
	}

	public void setEmgncyAvailability(String emgncyAvailability) {
		this.emgncyAvailability = emgncyAvailability;
	}

	public String getLeaveDenialAckgmt() {
		return leaveDenialAckgmt;
	}

	public void setLeaveDenialAckgmt(String leaveDenialAckgmt) {
		this.leaveDenialAckgmt = leaveDenialAckgmt;
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
		return "LeaveApplication [id=" + id + ", leaveCode=" + leaveCode + ", employeeId=" + employeeId + ", empFname="
				+ empFname + ", employeeCode=" + employeeCode + ", empLname=" + empLname + ", leaveTypeId="
				+ leaveTypeId + ", leaveTypeName=" + leaveTypeName + ", shiftId=" + shiftId + ", shiftName=" + shiftName
				+ ", leaveTypeCode=" + leaveTypeCode + ", startDt=" + startDt + ", startSession=" + startSession
				+ ", leaveAppliedDate=" + leaveAppliedDate + ", endDt=" + endDt + ", endSession=" + endSession
				+ ", reason=" + reason + ", leaveStatus=" + leaveStatus + ", leaveStatusName=" + leaveStatusName
				+ ", eligibilityStatusCode=" + eligibilityStatusCode + ", eligibilityStatusName="
				+ eligibilityStatusName + ", eligibleRemarks=" + eligibleRemarks + ", n1EmpId=" + n1EmpId
				+ ", n1EmpCode=" + n1EmpCode + ", n1EmpFname=" + n1EmpFname + ", n1EmpLname=" + n1EmpLname
				+ ", n1Decision=" + n1Decision + ", n1DecisionDt=" + n1DecisionDt + ", n1Remarks=" + n1Remarks
				+ ", needN2Approval=" + needN2Approval + ", n2EmpId=" + n2EmpId + ", n2EmpCode=" + n2EmpCode
				+ ", n2EmpFname=" + n2EmpFname + ", n2EmpLname=" + n2EmpLname + ", n2Decision=" + n2Decision
				+ ", n2DecisionDt=" + n2DecisionDt + ", n2Remarks=" + n2Remarks + ", isSettled=" + isSettled
				+ ", hr1SettlementDt=" + hr1SettlementDt + ", hr1UserGroupId=" + hr1UserGroupId + ", hr1UserGroupName="
				+ hr1UserGroupName + ", hr1EmpId=" + hr1EmpId + ", hr1EmpFname=" + hr1EmpFname + ", hr1EmpLname="
				+ hr1EmpLname + ", eligible=" + eligible + ", eligibleDuration=" + eligibleDuration + ", jobAckgmt="
				+ jobAckgmt + ", emgncyAvailability=" + emgncyAvailability + ", leaveDenialAckgmt=" + leaveDenialAckgmt
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	

	

	
}
