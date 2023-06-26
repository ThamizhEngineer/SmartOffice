package com.ss.smartoffice.sodocumentservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseClaim {
	private Integer id;
	private String expenseClaimCode;
	private String isBillable;
	private String isJobBased;
	private String jobId;
	private String jobCode;
	private String jobName;
	private String partnerId;
	private String partnerName;
	private String costCenterId;
	private String costCenterCode;
	private String costCenterName;
	private String expenseClaimStatus;
	private String expenseClaimStatusName;
	private String employeeId;
	private String employeeCode;
	private  String employeeFName;
	private  String employeeLName;
	private double expenseClaimAmount;
	private double totalEntitledAmount;
	private String empRemarks;
	private String appliedEmpId;
	private String appliedEmpCode;
	private  String appliedEmpFName;
	private  String appliedEmpLName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate appliedDt;
	private String validateUserGroupId;
	private String expPdfId;
	private String validateUserGroupName;
	private String validateDecision;
	private String validateRemarks;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime validatedDt;
	private String validateEmpId;
	
	private String validateEmpFName;

	private  String validateEmpLName;
	
	
	private String n1Decision;
	
	private String n1ApprovedDt;

	private String n1Remarks;
	
	private String n1EmpId;
	
	private  String n1EmpCode;
	
	private  String n1EmpFName;
	
	private  String n1EmpLName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime n1DecisionDt;
	
	private String needN2Approval;
	
	private String n2Decision;	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime n2ApprovedDt;	
	
	private String n2EmpId;
	
	private  String n2EmpCode;
	
	private  String n2EmpFName;

	private  String n2EmpLName;

	private String n2Remarks;
	
	private String settleUserGroupId;

	private String settleUserGroupName;
	private String settleDecision;
	private String inputCreditAmount;
	
	private  String settleEmpId;

	private  String settleEmpName;
	private String settleRemarks;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate settledDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime cancelledDt;
	private String cancelRemarks;
	private String cancelEmpId;
	
	private  String cancelEmpFName;
	
	private  String cancelEmpLName;
	
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	private List<ExpenseClaimBill> expenseClaimBills;

	public ExpenseClaim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExpenseClaim(Integer id, String expenseClaimCode, String isBillable, String isJobBased, String jobId,
			String jobCode, String jobName, String partnerId, String partnerName, String costCenterId,
			String costCenterCode, String costCenterName, String expenseClaimStatus, String expenseClaimStatusName,
			String employeeId, String employeeCode, String employeeFName, String employeeLName,
			double expenseClaimAmount, double totalEntitledAmount, String empRemarks, String appliedEmpId,
			String appliedEmpCode, String appliedEmpFName, String appliedEmpLName, LocalDate appliedDt,
			String validateUserGroupId, String expPdfId, String validateUserGroupName, String validateDecision,
			String validateRemarks, LocalDateTime validatedDt, String validateEmpId, String validateEmpFName,
			String validateEmpLName, String n1Decision, String n1ApprovedDt, String n1Remarks, String n1EmpId,
			String n1EmpCode, String n1EmpFName, String n1EmpLName, LocalDateTime n1DecisionDt, String needN2Approval,
			String n2Decision, LocalDateTime n2ApprovedDt, String n2EmpId, String n2EmpCode, String n2EmpFName,
			String n2EmpLName, String n2Remarks, String settleUserGroupId, String settleUserGroupName,
			String settleDecision, String inputCreditAmount, String settleEmpId, String settleEmpName,
			String settleRemarks, LocalDate settledDt, LocalDateTime cancelledDt, String cancelRemarks,
			String cancelEmpId, String cancelEmpFName, String cancelEmpLName, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt,
			List<ExpenseClaimBill> expenseClaimBills) {
		super();
		this.id = id;
		this.expenseClaimCode = expenseClaimCode;
		this.isBillable = isBillable;
		this.isJobBased = isJobBased;
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.partnerId = partnerId;
		this.partnerName = partnerName;
		this.costCenterId = costCenterId;
		this.costCenterCode = costCenterCode;
		this.costCenterName = costCenterName;
		this.expenseClaimStatus = expenseClaimStatus;
		this.expenseClaimStatusName = expenseClaimStatusName;
		this.employeeId = employeeId;
		this.employeeCode = employeeCode;
		this.employeeFName = employeeFName;
		this.employeeLName = employeeLName;
		this.expenseClaimAmount = expenseClaimAmount;
		this.totalEntitledAmount = totalEntitledAmount;
		this.empRemarks = empRemarks;
		this.appliedEmpId = appliedEmpId;
		this.appliedEmpCode = appliedEmpCode;
		this.appliedEmpFName = appliedEmpFName;
		this.appliedEmpLName = appliedEmpLName;
		this.appliedDt = appliedDt;
		this.validateUserGroupId = validateUserGroupId;
		this.expPdfId = expPdfId;
		this.validateUserGroupName = validateUserGroupName;
		this.validateDecision = validateDecision;
		this.validateRemarks = validateRemarks;
		this.validatedDt = validatedDt;
		this.validateEmpId = validateEmpId;
		this.validateEmpFName = validateEmpFName;
		this.validateEmpLName = validateEmpLName;
		this.n1Decision = n1Decision;
		this.n1ApprovedDt = n1ApprovedDt;
		this.n1Remarks = n1Remarks;
		this.n1EmpId = n1EmpId;
		this.n1EmpCode = n1EmpCode;
		this.n1EmpFName = n1EmpFName;
		this.n1EmpLName = n1EmpLName;
		this.n1DecisionDt = n1DecisionDt;
		this.needN2Approval = needN2Approval;
		this.n2Decision = n2Decision;
		this.n2ApprovedDt = n2ApprovedDt;
		this.n2EmpId = n2EmpId;
		this.n2EmpCode = n2EmpCode;
		this.n2EmpFName = n2EmpFName;
		this.n2EmpLName = n2EmpLName;
		this.n2Remarks = n2Remarks;
		this.settleUserGroupId = settleUserGroupId;
		this.settleUserGroupName = settleUserGroupName;
		this.settleDecision = settleDecision;
		this.inputCreditAmount = inputCreditAmount;
		this.settleEmpId = settleEmpId;
		this.settleEmpName = settleEmpName;
		this.settleRemarks = settleRemarks;
		this.settledDt = settledDt;
		this.cancelledDt = cancelledDt;
		this.cancelRemarks = cancelRemarks;
		this.cancelEmpId = cancelEmpId;
		this.cancelEmpFName = cancelEmpFName;
		this.cancelEmpLName = cancelEmpLName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.expenseClaimBills = expenseClaimBills;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpenseClaimCode() {
		return expenseClaimCode;
	}

	public void setExpenseClaimCode(String expenseClaimCode) {
		this.expenseClaimCode = expenseClaimCode;
	}

	public String getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}

	public String getIsJobBased() {
		return isJobBased;
	}

	public void setIsJobBased(String isJobBased) {
		this.isJobBased = isJobBased;
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
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

	public String getExpenseClaimStatus() {
		return expenseClaimStatus;
	}

	public void setExpenseClaimStatus(String expenseClaimStatus) {
		this.expenseClaimStatus = expenseClaimStatus;
	}

	public String getExpenseClaimStatusName() {
		return expenseClaimStatusName;
	}

	public void setExpenseClaimStatusName(String expenseClaimStatusName) {
		this.expenseClaimStatusName = expenseClaimStatusName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeFName() {
		return employeeFName;
	}

	public void setEmployeeFName(String employeeFName) {
		this.employeeFName = employeeFName;
	}

	public String getEmployeeLName() {
		return employeeLName;
	}

	public void setEmployeeLName(String employeeLName) {
		this.employeeLName = employeeLName;
	}

	public double getExpenseClaimAmount() {
		return expenseClaimAmount;
	}

	public void setExpenseClaimAmount(double expenseClaimAmount) {
		this.expenseClaimAmount = expenseClaimAmount;
	}

	public double getTotalEntitledAmount() {
		return totalEntitledAmount;
	}

	public void setTotalEntitledAmount(double totalEntitledAmount) {
		this.totalEntitledAmount = totalEntitledAmount;
	}

	public String getEmpRemarks() {
		return empRemarks;
	}

	public void setEmpRemarks(String empRemarks) {
		this.empRemarks = empRemarks;
	}

	public String getAppliedEmpId() {
		return appliedEmpId;
	}

	public void setAppliedEmpId(String appliedEmpId) {
		this.appliedEmpId = appliedEmpId;
	}

	public String getAppliedEmpCode() {
		return appliedEmpCode;
	}

	public void setAppliedEmpCode(String appliedEmpCode) {
		this.appliedEmpCode = appliedEmpCode;
	}

	public String getAppliedEmpFName() {
		return appliedEmpFName;
	}

	public void setAppliedEmpFName(String appliedEmpFName) {
		this.appliedEmpFName = appliedEmpFName;
	}

	public String getAppliedEmpLName() {
		return appliedEmpLName;
	}

	public void setAppliedEmpLName(String appliedEmpLName) {
		this.appliedEmpLName = appliedEmpLName;
	}

	public LocalDate getAppliedDt() {
		return appliedDt;
	}

	public void setAppliedDt(LocalDate appliedDt) {
		this.appliedDt = appliedDt;
	}

	public String getValidateUserGroupId() {
		return validateUserGroupId;
	}

	public void setValidateUserGroupId(String validateUserGroupId) {
		this.validateUserGroupId = validateUserGroupId;
	}

	public String getExpPdfId() {
		return expPdfId;
	}

	public void setExpPdfId(String expPdfId) {
		this.expPdfId = expPdfId;
	}

	public String getValidateUserGroupName() {
		return validateUserGroupName;
	}

	public void setValidateUserGroupName(String validateUserGroupName) {
		this.validateUserGroupName = validateUserGroupName;
	}

	public String getValidateDecision() {
		return validateDecision;
	}

	public void setValidateDecision(String validateDecision) {
		this.validateDecision = validateDecision;
	}

	public String getValidateRemarks() {
		return validateRemarks;
	}

	public void setValidateRemarks(String validateRemarks) {
		this.validateRemarks = validateRemarks;
	}

	public LocalDateTime getValidatedDt() {
		return validatedDt;
	}

	public void setValidatedDt(LocalDateTime validatedDt) {
		this.validatedDt = validatedDt;
	}

	public String getValidateEmpId() {
		return validateEmpId;
	}
	
	public void setValidateEmpId(String validateEmpId) {
		this.validateEmpId = validateEmpId;
	}

	public String getValidateEmpFName() {
		return validateEmpFName;
	}

	public void setValidateEmpFName(String validateEmpFName) {
		this.validateEmpFName = validateEmpFName;
	}

	public String getValidateEmpLName() {
		return validateEmpLName;
	}

	public void setValidateEmpLName(String validateEmpLName) {
		this.validateEmpLName = validateEmpLName;
	}

	public String getN1Decision() {
		return n1Decision;
	}

	public void setN1Decision(String n1Decision) {
		this.n1Decision = n1Decision;
	}

	public String getN1ApprovedDt() {
		return n1ApprovedDt;
	}

	public void setN1ApprovedDt(String n1ApprovedDt) {
		this.n1ApprovedDt = n1ApprovedDt;
	}

	public String getN1Remarks() {
		return n1Remarks;
	}

	public void setN1Remarks(String n1Remarks) {
		this.n1Remarks = n1Remarks;
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

	public String getN1EmpFName() {
		return n1EmpFName;
	}

	public void setN1EmpFName(String n1EmpFName) {
		this.n1EmpFName = n1EmpFName;
	}

	public String getN1EmpLName() {
		return n1EmpLName;
	}

	public void setN1EmpLName(String n1EmpLName) {
		this.n1EmpLName = n1EmpLName;
	}

	public LocalDateTime getN1DecisionDt() {
		return n1DecisionDt;
	}

	public void setN1DecisionDt(LocalDateTime n1DecisionDt) {
		this.n1DecisionDt = n1DecisionDt;
	}

	public String getNeedN2Approval() {
		return needN2Approval;
	}

	public void setNeedN2Approval(String needN2Approval) {
		this.needN2Approval = needN2Approval;
	}

	public String getN2Decision() {
		return n2Decision;
	}

	public void setN2Decision(String n2Decision) {
		this.n2Decision = n2Decision;
	}

	public LocalDateTime getN2ApprovedDt() {
		return n2ApprovedDt;
	}

	public void setN2ApprovedDt(LocalDateTime n2ApprovedDt) {
		this.n2ApprovedDt = n2ApprovedDt;
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
	
	public String getN2EmpFName() {
		return n2EmpFName;
	}

	public void setN2EmpFName(String n2EmpFName) {
		this.n2EmpFName = n2EmpFName;
	}

	public String getN2EmpLName() {
		return n2EmpLName;
	}

	public void setN2EmpLName(String n2EmpLName) {
		this.n2EmpLName = n2EmpLName;
	}

	public String getN2Remarks() {
		return n2Remarks;
	}

	public void setN2Remarks(String n2Remarks) {
		this.n2Remarks = n2Remarks;
	}

	public String getSettleUserGroupId() {
		return settleUserGroupId;
	}

	public void setSettleUserGroupId(String settleUserGroupId) {
		this.settleUserGroupId = settleUserGroupId;
	}

	public String getSettleUserGroupName() {
		return settleUserGroupName;
	}

	public void setSettleUserGroupName(String settleUserGroupName) {
		this.settleUserGroupName = settleUserGroupName;
	}

	public String getSettleDecision() {
		return settleDecision;
	}

	public void setSettleDecision(String settleDecision) {
		this.settleDecision = settleDecision;
	}

	public String getInputCreditAmount() {
		return inputCreditAmount;
	}

	public void setInputCreditAmount(String inputCreditAmount) {
		this.inputCreditAmount = inputCreditAmount;
	}

	public String getSettleEmpId() {
		return settleEmpId;
	}

	public void setSettleEmpId(String settleEmpId) {
		this.settleEmpId = settleEmpId;
	}

	public String getSettleEmpName() {
		return settleEmpName;
	}

	public void setSettleEmpName(String settleEmpName) {
		this.settleEmpName = settleEmpName;
	}

	public String getSettleRemarks() {
		return settleRemarks;
	}

	public void setSettleRemarks(String settleRemarks) {
		this.settleRemarks = settleRemarks;
	}

	public LocalDate getSettledDt() {
		return settledDt;
	}

	public void setSettledDt(LocalDate settledDt) {
		this.settledDt = settledDt;
	}

	public LocalDateTime getCancelledDt() {
		return cancelledDt;
	}

	public void setCancelledDt(LocalDateTime cancelledDt) {
		this.cancelledDt = cancelledDt;
	}

	public String getCancelRemarks() {
		return cancelRemarks;
	}

	public void setCancelRemarks(String cancelRemarks) {
		this.cancelRemarks = cancelRemarks;
	}

	public String getCancelEmpId() {
		return cancelEmpId;
	}

	public void setCancelEmpId(String cancelEmpId) {
		this.cancelEmpId = cancelEmpId;
	}

	public String getCancelEmpFName() {
		return cancelEmpFName;
	}

	public void setCancelEmpFName(String cancelEmpFName) {
		this.cancelEmpFName = cancelEmpFName;
	}

	public String getCancelEmpLName() {
		return cancelEmpLName;
	}

	public void setCancelEmpLName(String cancelEmpLName) {
		this.cancelEmpLName = cancelEmpLName;
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

	public List<ExpenseClaimBill> getExpenseClaimBills() {
		return expenseClaimBills;
	}

	public void setExpenseClaimBills(List<ExpenseClaimBill> expenseClaimBills) {
		this.expenseClaimBills = expenseClaimBills;
	}

	@Override
	public String toString() {
		return "ExpenseClaim [id=" + id + ", expenseClaimCode=" + expenseClaimCode + ", isBillable=" + isBillable
				+ ", isJobBased=" + isJobBased + ", jobId=" + jobId + ", jobCode=" + jobCode + ", jobName=" + jobName
				+ ", partnerId=" + partnerId + ", partnerName=" + partnerName + ", costCenterId=" + costCenterId
				+ ", costCenterCode=" + costCenterCode + ", costCenterName=" + costCenterName + ", expenseClaimStatus="
				+ expenseClaimStatus + ", expenseClaimStatusName=" + expenseClaimStatusName + ", employeeId="
				+ employeeId + ", employeeCode=" + employeeCode + ", employeeFName=" + employeeFName
				+ ", employeeLName=" + employeeLName + ", expenseClaimAmount=" + expenseClaimAmount
				+ ", totalEntitledAmount=" + totalEntitledAmount + ", empRemarks=" + empRemarks + ", appliedEmpId="
				+ appliedEmpId + ", appliedEmpCode=" + appliedEmpCode + ", appliedEmpFName=" + appliedEmpFName
				+ ", appliedEmpLName=" + appliedEmpLName + ", appliedDt=" + appliedDt + ", validateUserGroupId="
				+ validateUserGroupId + ", expPdfId=" + expPdfId + ", validateUserGroupName=" + validateUserGroupName
				+ ", validateDecision=" + validateDecision + ", validateRemarks=" + validateRemarks + ", validatedDt="
				+ validatedDt + ", validateEmpId=" + validateEmpId + ", validateEmpFName=" + validateEmpFName
				+ ", validateEmpLName=" + validateEmpLName + ", n1Decision=" + n1Decision + ", n1ApprovedDt="
				+ n1ApprovedDt + ", n1Remarks=" + n1Remarks + ", n1EmpId=" + n1EmpId + ", n1EmpCode=" + n1EmpCode
				+ ", n1EmpFName=" + n1EmpFName + ", n1EmpLName=" + n1EmpLName + ", n1DecisionDt=" + n1DecisionDt
				+ ", needN2Approval=" + needN2Approval + ", n2Decision=" + n2Decision + ", n2ApprovedDt=" + n2ApprovedDt
				+ ", n2EmpId=" + n2EmpId + ", n2EmpCode=" + n2EmpCode + ", n2EmpFName=" + n2EmpFName + ", n2EmpLName="
				+ n2EmpLName + ", n2Remarks=" + n2Remarks + ", settleUserGroupId=" + settleUserGroupId
				+ ", settleUserGroupName=" + settleUserGroupName + ", settleDecision=" + settleDecision
				+ ", inputCreditAmount=" + inputCreditAmount + ", settleEmpId=" + settleEmpId + ", settleEmpName="
				+ settleEmpName + ", settleRemarks=" + settleRemarks + ", settledDt=" + settledDt + ", cancelledDt="
				+ cancelledDt + ", cancelRemarks=" + cancelRemarks + ", cancelEmpId=" + cancelEmpId
				+ ", cancelEmpFName=" + cancelEmpFName + ", cancelEmpLName=" + cancelEmpLName + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + ", expenseClaimBills=" + expenseClaimBills + "]";
	}
		
}
