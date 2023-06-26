package com.ss.smartoffice.soservice.transaction.skillmatrix;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name = "t_skill_matrix_hdr")
public class SkillMatrixRequestHdr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String status;
	@Column(name = "hdr_key")
	private String key;
	private String trigrredBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime trigrredDt;
	private String processType;
	private String bkProcessId;
	private String isError;
	private String errorMessage;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String deptId;
	private String officeId;
	private String isSpecificSkillList;
	private String productId;
	private String productFamilyId;
	private String manufactureId;
	@Transient
	private List<EmployeeReqInput> employeeIds;
	@Transient
	private List<ProductAndServiceInput> productAndServices;
	private String expectedCount;
	private String docId;

	public SkillMatrixRequestHdr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillMatrixRequestHdr(Integer id, String status, String key, String trigrredBy, LocalDateTime trigrredDt,
			String processType, String bkProcessId, String isError, String errorMessage, String isEnabled,
			String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt, String deptId,
			String officeId, String isSpecificSkillList, String productId, String productFamilyId, String manufactureId,
			List<EmployeeReqInput> employeeIds, List<ProductAndServiceInput> productAndServices, String expectedCount,
			String docId) {
		super();
		this.id = id;
		this.status = status;
		this.key = key;
		this.trigrredBy = trigrredBy;
		this.trigrredDt = trigrredDt;
		this.processType = processType;
		this.bkProcessId = bkProcessId;
		this.isError = isError;
		this.errorMessage = errorMessage;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.deptId = deptId;
		this.officeId = officeId;
		this.isSpecificSkillList = isSpecificSkillList;
		this.productId = productId;
		this.productFamilyId = productFamilyId;
		this.manufactureId = manufactureId;
		this.employeeIds = employeeIds;
		this.productAndServices = productAndServices;
		this.expectedCount = expectedCount;
		this.docId = docId;
	}

	@Override
	public String toString() {
		return "SkillMatrixRequestHdr [id=" + id + ", status=" + status + ", key=" + key + ", trigrredBy=" + trigrredBy
				+ ", trigrredDt=" + trigrredDt + ", processType=" + processType + ", bkProcessId=" + bkProcessId
				+ ", isError=" + isError + ", errorMessage=" + errorMessage + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + ", deptId=" + deptId + ", officeId=" + officeId
				+ ", isSpecificSkillList=" + isSpecificSkillList + ", productId=" + productId + ", productFamilyId="
				+ productFamilyId + ", manufactureId=" + manufactureId + ", employeeIds=" + employeeIds
				+ ", productAndServices=" + productAndServices + ", expectedCount=" + expectedCount + ", docId=" + docId
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTrigrredBy() {
		return trigrredBy;
	}

	public void setTrigrredBy(String trigrredBy) {
		this.trigrredBy = trigrredBy;
	}

	public LocalDateTime getTrigrredDt() {
		return trigrredDt;
	}

	public void setTrigrredDt(LocalDateTime trigrredDt) {
		this.trigrredDt = trigrredDt;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getBkProcessId() {
		return bkProcessId;
	}

	public void setBkProcessId(String bkProcessId) {
		this.bkProcessId = bkProcessId;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getIsSpecificSkillList() {
		return isSpecificSkillList;
	}

	public void setIsSpecificSkillList(String isSpecificSkillList) {
		this.isSpecificSkillList = isSpecificSkillList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductFamilyId() {
		return productFamilyId;
	}

	public void setProductFamilyId(String productFamilyId) {
		this.productFamilyId = productFamilyId;
	}

	public String getManufactureId() {
		return manufactureId;
	}

	public void setManufactureId(String manufactureId) {
		this.manufactureId = manufactureId;
	}

	public List<EmployeeReqInput> getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(List<EmployeeReqInput> employeeIds) {
		this.employeeIds = employeeIds;
	}

	public List<ProductAndServiceInput> getProductAndServices() {
		return productAndServices;
	}

	public void setProductAndServices(List<ProductAndServiceInput> productAndServices) {
		this.productAndServices = productAndServices;
	}

	public String getExpectedCount() {
		return expectedCount;
	}

	public void setExpectedCount(String expectedCount) {
		this.expectedCount = expectedCount;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}


	

}
