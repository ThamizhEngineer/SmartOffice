package com.ss.smartoffice.soservice.transaction.skillmatrix;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name = "t_skill_matrix_result")
public class SkillMatrixResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String skillMatrixHdrKey;
	private String employeeId;
	private String employeeCode;
	private String employeeName;
	private String deptId;
	private String deptName;
	private String productId;
	private String productName;
	private String serviceId;
	private String serviceName;
	private Integer skillLevelCode;
	private String type;
	private String isTraining;
	private String isError;
	private String errorMessage;
	private String bkProcessId;
	private String isEnabled;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String deptCode;
	private String n1EmpId;
	private String n2EmpId;
	private String designationId;
	private String designationName;
	private String hasCount;
	private String trainingCount;
	private String rowSpan;

	public SkillMatrixResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillMatrixResult(Integer id, String skillMatrixHdrKey, String employeeId, String employeeCode,
			String employeeName, String deptId, String deptName, String productId, String productName, String serviceId,
			String serviceName, Integer skillLevelCode, String type, String isTraining, String isError,
			String errorMessage, String bkProcessId, String isEnabled, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt, String deptCode, String n1EmpId, String n2EmpId, String designationId,
			String designationName, String hasCount, String trainingCount, String rowSpan) {
		super();
		this.id = id;
		this.skillMatrixHdrKey = skillMatrixHdrKey;
		this.employeeId = employeeId;
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.productId = productId;
		this.productName = productName;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.skillLevelCode = skillLevelCode;
		this.type = type;
		this.isTraining = isTraining;
		this.isError = isError;
		this.errorMessage = errorMessage;
		this.bkProcessId = bkProcessId;
		this.isEnabled = isEnabled;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.deptCode = deptCode;
		this.n1EmpId = n1EmpId;
		this.n2EmpId = n2EmpId;
		this.designationId = designationId;
		this.designationName = designationName;
		this.hasCount = hasCount;
		this.trainingCount = trainingCount;
		this.rowSpan = rowSpan;
	}

	@Override
	public String toString() {
		return "SkillMatrixResult [id=" + id + ", skillMatrixHdrKey=" + skillMatrixHdrKey + ", employeeId=" + employeeId
				+ ", employeeCode=" + employeeCode + ", employeeName=" + employeeName + ", deptId=" + deptId
				+ ", deptName=" + deptName + ", productId=" + productId + ", productName=" + productName
				+ ", serviceId=" + serviceId + ", serviceName=" + serviceName + ", skillLevelCode=" + skillLevelCode
				+ ", type=" + type + ", isTraining=" + isTraining + ", isError=" + isError + ", errorMessage="
				+ errorMessage + ", bkProcessId=" + bkProcessId + ", isEnabled=" + isEnabled + ", createdDt="
				+ createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + ", deptCode=" + deptCode
				+ ", n1EmpId=" + n1EmpId + ", n2EmpId=" + n2EmpId + ", designationId=" + designationId
				+ ", designationName=" + designationName + ", hasCount=" + hasCount + ", trainingCount=" + trainingCount
				+ ", rowSpan=" + rowSpan + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkillMatrixHdrKey() {
		return skillMatrixHdrKey;
	}

	public void setSkillMatrixHdrKey(String skillMatrixHdrKey) {
		this.skillMatrixHdrKey = skillMatrixHdrKey;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getSkillLevelCode() {
		return skillLevelCode;
	}

	public void setSkillLevelCode(Integer skillLevelCode) {
		this.skillLevelCode = skillLevelCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsTraining() {
		return isTraining;
	}

	public void setIsTraining(String isTraining) {
		this.isTraining = isTraining;
	}

	public String getIsError() {
		return isError;
	}

	public void setIsError(String isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getBkProcessId() {
		return bkProcessId;
	}

	public void setBkProcessId(String bkProcessId) {
		this.bkProcessId = bkProcessId;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getN1EmpId() {
		return n1EmpId;
	}

	public void setN1EmpId(String n1EmpId) {
		this.n1EmpId = n1EmpId;
	}

	public String getN2EmpId() {
		return n2EmpId;
	}

	public void setN2EmpId(String n2EmpId) {
		this.n2EmpId = n2EmpId;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getHasCount() {
		return hasCount;
	}

	public void setHasCount(String hasCount) {
		this.hasCount = hasCount;
	}

	public String getTrainingCount() {
		return trainingCount;
	}

	public void setTrainingCount(String trainingCount) {
		this.trainingCount = trainingCount;
	}

	public String getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(String rowSpan) {
		this.rowSpan = rowSpan;
	}

	
}
