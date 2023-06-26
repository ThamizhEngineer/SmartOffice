package com.ss.smartoffice.soservice.transaction.vacancyrequest;

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
import com.ss.smartoffice.soservice.master.vacancydescription.VacancyDescription;

@Entity

@Table(name="t_vacancy_request")
public class VacancyRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String vrCode;
	private String vdId;
	private String summary;
	private String status;
	@Column(name="hr_1_usr_grp_id")
	private String hr1UsrGrpId;
	@Formula("(select userGroup.user_group_name from s_user_group userGroup where userGroup.id=hr_1_usr_grp_id)")
	private String hr1UsrGroupName;
	@Column(name="hr_2_usr_grp_id")
	private String hr2UsrGrpId;
	private String vacancyCount;
	@Formula("(select count(*) from t_incident_applicants ia where ia.m_employee_code is NOT NULL and ia.vc_id=id)")
	private String convertedEmployee;
	private String yearsOfExp;
	private String location;
	private String salary;
	@Formula("(select des.role from m_vacancy_description des where des.id=vd_id)")
	private String role;
	@Formula("(select des.experience from m_vacancy_description des where des.id=vd_id)")
	private String experience;
	private String remarks;
	private String approvalUserGroupId;
	@Formula("(select userGroup.user_group_name from s_user_group userGroup where userGroup.id=approval_user_group_id)")
	private String approvalUserGroupName;
	
	private String createEmpId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime vcCreatedDt;
	private String approvedEmpId;
	private String approverDecision;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime vcApprovedDt;
	private String totalApplnCount;
	private String shortlistedApplnCount;
	@Transient
	private VacancyDescription vacancyDescription;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	public VacancyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VacancyRequest(Integer id, String vrCode, String vdId, String summary, String status, String hr1UsrGrpId,
			String hr1UsrGroupName, String hr2UsrGrpId, String vacancyCount, String convertedEmployee,
			String yearsOfExp, String location, String salary, String role, String experience, String remarks,
			String approvalUserGroupId, String approvalUserGroupName, String createEmpId, LocalDateTime vcCreatedDt,
			String approvedEmpId, String approverDecision, LocalDateTime vcApprovedDt, String totalApplnCount,
			String shortlistedApplnCount, VacancyDescription vacancyDescription, String isEnabled, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.vrCode = vrCode;
		this.vdId = vdId;
		this.summary = summary;
		this.status = status;
		this.hr1UsrGrpId = hr1UsrGrpId;
		this.hr1UsrGroupName = hr1UsrGroupName;
		this.hr2UsrGrpId = hr2UsrGrpId;
		this.vacancyCount = vacancyCount;
		this.convertedEmployee = convertedEmployee;
		this.yearsOfExp = yearsOfExp;
		this.location = location;
		this.salary = salary;
		this.role = role;
		this.experience = experience;
		this.remarks = remarks;
		this.approvalUserGroupId = approvalUserGroupId;
		this.approvalUserGroupName = approvalUserGroupName;
		this.createEmpId = createEmpId;
		this.vcCreatedDt = vcCreatedDt;
		this.approvedEmpId = approvedEmpId;
		this.approverDecision = approverDecision;
		this.vcApprovedDt = vcApprovedDt;
		this.totalApplnCount = totalApplnCount;
		this.shortlistedApplnCount = shortlistedApplnCount;
		this.vacancyDescription = vacancyDescription;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVrCode() {
		return vrCode;
	}

	public void setVrCode(String vrCode) {
		this.vrCode = vrCode;
	}


	public String getVdId() {
		return vdId;
	}


	public void setVdId(String vdId) {
		this.vdId = vdId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHr1UsrGrpId() {
		return hr1UsrGrpId;
	}

	public void setHr1UsrGrpId(String hr1UsrGrpId) {
		this.hr1UsrGrpId = hr1UsrGrpId;
	}

	public String getHr1UsrGroupName() {
		return hr1UsrGroupName;
	}

	public void setHr1UsrGroupName(String hr1UsrGroupName) {
		this.hr1UsrGroupName = hr1UsrGroupName;
	}

	public String getHr2UsrGrpId() {
		return hr2UsrGrpId;
	}

	public void setHr2UsrGrpId(String hr2UsrGrpId) {
		this.hr2UsrGrpId = hr2UsrGrpId;
	}

	public String getConvertedEmployee() {
		return convertedEmployee;
	}

	public void setConvertedEmployee(String convertedEmployee) {
		this.convertedEmployee = convertedEmployee;
	}

	public String getVacancyCount() {
		return vacancyCount;
	}

	public void setVacancyCount(String vacancyCount) {
		this.vacancyCount = vacancyCount;
	}

	public String getYearsOfExp() {
		return yearsOfExp;
	}

	public void setYearsOfExp(String yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getApprovalUserGroupId() {
		return approvalUserGroupId;
	}

	public void setApprovalUserGroupId(String approvalUserGroupId) {
		this.approvalUserGroupId = approvalUserGroupId;
	}

	public String getApprovalUserGroupName() {
		return approvalUserGroupName;
	}

	public void setApprovalUserGroupName(String approvalUserGroupName) {
		this.approvalUserGroupName = approvalUserGroupName;
	}

	public String getCreateEmpId() {
		return createEmpId;
	}

	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
	}

	public LocalDateTime getVcCreatedDt() {
		return vcCreatedDt;
	}

	public void setVcCreatedDt(LocalDateTime vcCreatedDt) {
		this.vcCreatedDt = vcCreatedDt;
	}

	public String getApprovedEmpId() {
		return approvedEmpId;
	}

	public void setApprovedEmpId(String approvedEmpId) {
		this.approvedEmpId = approvedEmpId;
	}

	public String getApproverDecision() {
		return approverDecision;
	}

	public void setApproverDecision(String approverDecision) {
		this.approverDecision = approverDecision;
	}

	public LocalDateTime getVcApprovedDt() {
		return vcApprovedDt;
	}

	public void setVcApprovedDt(LocalDateTime vcApprovedDt) {
		this.vcApprovedDt = vcApprovedDt;
	}

	public String getTotalApplnCount() {
		return totalApplnCount;
	}

	public void setTotalApplnCount(String totalApplnCount) {
		this.totalApplnCount = totalApplnCount;
	}

	public String getShortlistedApplnCount() {
		return shortlistedApplnCount;
	}

	public void setShortlistedApplnCount(String shortlistedApplnCount) {
		this.shortlistedApplnCount = shortlistedApplnCount;
	}

	public VacancyDescription getVacancyDescription() {
		return vacancyDescription;
	}

	public void setVacancyDescription(VacancyDescription vacancyDescription) {
		this.vacancyDescription = vacancyDescription;
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

	@Override
	public String toString() {
		return "VacancyRequest [id=" + id + ", vrCode=" + vrCode + ", vdId=" + vdId + ", summary=" + summary
				+ ", status=" + status + ", hr1UsrGrpId=" + hr1UsrGrpId + ", hr1UsrGroupName=" + hr1UsrGroupName
				+ ", hr2UsrGrpId=" + hr2UsrGrpId + ", vacancyCount=" + vacancyCount + ", convertedEmployee="
				+ convertedEmployee + ", yearsOfExp=" + yearsOfExp + ", location=" + location + ", salary=" + salary
				+ ", role=" + role + ", experience=" + experience + ", remarks=" + remarks + ", approvalUserGroupId="
				+ approvalUserGroupId + ", approvalUserGroupName=" + approvalUserGroupName + ", createEmpId="
				+ createEmpId + ", vcCreatedDt=" + vcCreatedDt + ", approvedEmpId=" + approvedEmpId
				+ ", approverDecision=" + approverDecision + ", vcApprovedDt=" + vcApprovedDt + ", totalApplnCount="
				+ totalApplnCount + ", shortlistedApplnCount=" + shortlistedApplnCount + ", vacancyDescription="
				+ vacancyDescription + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", createdDt="
				+ createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}
	}
