package com.ss.smartoffice.soservice.transaction.websiteapplicant;

import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name="t_ws_applicant")
public class WebSiteApplicant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String vcId;
	@Formula("(select vacancy.vr_code from t_vacancy_request vacancy where vacancy.id=vc_id)")
	private String vrCode;
	@Formula("(select vacancy.summary from t_vacancy_request vacancy where vacancy.id=vc_id)")
	private String vrSummary;
	@Formula("(select vacancy.status from t_vacancy_request vacancy where vacancy.id=vc_id)")
	private String vrStatus;
	@Formula("(SELECT COUNT(ws.vc_id) from t_ws_applicant ws where ws.vc_id=vc_id)")
	private String totalApplnCount;
	@Formula("(SELECT COUNT(ws.vc_id) from t_ws_applicant ws where ws.is_validate='Y' AND ws.vc_id=vc_id)")
	private String validatedCount;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate wsDate;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dob;
	@Column(name="hr_1_usr_grp_id")
	private String hr1UsrGrpId;
	@Column(name="hr_2_usr_grp_id")
	private String hr2UsrGrpId;
	private String status;
	private String email;
	private String gender;
	private String mobNum;
	private String coverLetterNum;
	private String resumeDocId;
	private String isValidate;
	private String validateEmpId;
	@Column(name="validate_dt")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate validateDt;
	private String institute;
	private String course;
	private String passoutYear;
	private String historyOfArrears;
	private String marks;
	private String currCompany;
	private String currDesignation;
	private String currExperience;
	private String currLocation;
	private String currSalary;
	private String degreeName;
	private String expType;
	private String otherCollegeName;
	private String otherCourseName;
	private String otherDegreeName;
	private String applicantId;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public WebSiteApplicant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WebSiteApplicant(Integer id, String vcId, String vrCode, String vrSummary, String vrStatus,
			String totalApplnCount, String validatedCount, LocalDate wsDate, String firstName, String lastName,
			LocalDate dob, String hr1UsrGrpId, String hr2UsrGrpId, String status, String email, String gender,
			String mobNum, String coverLetterNum, String resumeDocId, String isValidate, String validateEmpId,
			LocalDate validateDt, String institute, String course, String passoutYear, String historyOfArrears,
			String marks, String currCompany, String currDesignation, String currExperience, String currLocation,
			String currSalary, String degreeName, String expType, String otherCollegeName, String otherCourseName,
			String otherDegreeName, String applicantId, String isEnabled, String createdBy, LocalDateTime createdDt,
			String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.vcId = vcId;
		this.vrCode = vrCode;
		this.vrSummary = vrSummary;
		this.vrStatus = vrStatus;
		this.totalApplnCount = totalApplnCount;
		this.validatedCount = validatedCount;
		this.wsDate = wsDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.hr1UsrGrpId = hr1UsrGrpId;
		this.hr2UsrGrpId = hr2UsrGrpId;
		this.status = status;
		this.email = email;
		this.gender = gender;
		this.mobNum = mobNum;
		this.coverLetterNum = coverLetterNum;
		this.resumeDocId = resumeDocId;
		this.isValidate = isValidate;
		this.validateEmpId = validateEmpId;
		this.validateDt = validateDt;
		this.institute = institute;
		this.course = course;
		this.passoutYear = passoutYear;
		this.historyOfArrears = historyOfArrears;
		this.marks = marks;
		this.currCompany = currCompany;
		this.currDesignation = currDesignation;
		this.currExperience = currExperience;
		this.currLocation = currLocation;
		this.currSalary = currSalary;
		this.degreeName = degreeName;
		this.expType = expType;
		this.otherCollegeName = otherCollegeName;
		this.otherCourseName = otherCourseName;
		this.otherDegreeName = otherDegreeName;
		this.applicantId = applicantId;
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
	public String getVcId() {
		return vcId;
	}
	public void setVcId(String vcId) {
		this.vcId = vcId;
	}
	public String getVrCode() {
		return vrCode;
	}
	public void setVrCode(String vrCode) {
		this.vrCode = vrCode;
	}
	public String getVrSummary() {
		return vrSummary;
	}
	public void setVrSummary(String vrSummary) {
		this.vrSummary = vrSummary;
	}
	public String getVrStatus() {
		return vrStatus;
	}
	public void setVrStatus(String vrStatus) {
		this.vrStatus = vrStatus;
	}
	public String getTotalApplnCount() {
		return totalApplnCount;
	}
	public void setTotalApplnCount(String totalApplnCount) {
		this.totalApplnCount = totalApplnCount;
	}
	public String getValidatedCount() {
		return validatedCount;
	}
	public void setValidatedCount(String validatedCount) {
		this.validatedCount = validatedCount;
	}
	public LocalDate getWsDate() {
		return wsDate;
	}
	public void setWsDate(LocalDate wsDate) {
		this.wsDate = wsDate;
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHr1UsrGrpId() {
		return hr1UsrGrpId;
	}
	public void setHr1UsrGrpId(String hr1UsrGrpId) {
		this.hr1UsrGrpId = hr1UsrGrpId;
	}
	public String getHr2UsrGrpId() {
		return hr2UsrGrpId;
	}
	public void setHr2UsrGrpId(String hr2UsrGrpId) {
		this.hr2UsrGrpId = hr2UsrGrpId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobNum() {
		return mobNum;
	}
	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}
	public String getCoverLetterNum() {
		return coverLetterNum;
	}
	public void setCoverLetterNum(String coverLetterNum) {
		this.coverLetterNum = coverLetterNum;
	}
	public String getResumeDocId() {
		return resumeDocId;
	}
	public void setResumeDocId(String resumeDocId) {
		this.resumeDocId = resumeDocId;
	}
	public String getIsValidate() {
		return isValidate;
	}
	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	public String getValidateEmpId() {
		return validateEmpId;
	}
	public void setValidateEmpId(String validateEmpId) {
		this.validateEmpId = validateEmpId;
	}
	public LocalDate getValidateDt() {
		return validateDt;
	}
	public void setValidateDt(LocalDate validateDt) {
		this.validateDt = validateDt;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getPassoutYear() {
		return passoutYear;
	}
	public void setPassoutYear(String passoutYear) {
		this.passoutYear = passoutYear;
	}
	public String getHistoryOfArrears() {
		return historyOfArrears;
	}
	public void setHistoryOfArrears(String historyOfArrears) {
		this.historyOfArrears = historyOfArrears;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getCurrCompany() {
		return currCompany;
	}
	public void setCurrCompany(String currCompany) {
		this.currCompany = currCompany;
	}
	public String getCurrDesignation() {
		return currDesignation;
	}
	public void setCurrDesignation(String currDesignation) {
		this.currDesignation = currDesignation;
	}
	public String getCurrExperience() {
		return currExperience;
	}
	public void setCurrExperience(String currExperience) {
		this.currExperience = currExperience;
	}
	public String getCurrLocation() {
		return currLocation;
	}
	public void setCurrLocation(String currLocation) {
		this.currLocation = currLocation;
	}
	public String getCurrSalary() {
		return currSalary;
	}
	public void setCurrSalary(String currSalary) {
		this.currSalary = currSalary;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String getOtherCollegeName() {
		return otherCollegeName;
	}
	public void setOtherCollegeName(String otherCollegeName) {
		this.otherCollegeName = otherCollegeName;
	}
	public String getOtherCourseName() {
		return otherCourseName;
	}
	public void setOtherCourseName(String otherCourseName) {
		this.otherCourseName = otherCourseName;
	}
	public String getOtherDegreeName() {
		return otherDegreeName;
	}
	public void setOtherDegreeName(String otherDegreeName) {
		this.otherDegreeName = otherDegreeName;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
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
		return "WebSiteApplicant [id=" + id + ", vcId=" + vcId + ", vrCode=" + vrCode + ", vrSummary=" + vrSummary
				+ ", vrStatus=" + vrStatus + ", totalApplnCount=" + totalApplnCount + ", validatedCount="
				+ validatedCount + ", wsDate=" + wsDate + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dob=" + dob + ", hr1UsrGrpId=" + hr1UsrGrpId + ", hr2UsrGrpId=" + hr2UsrGrpId + ", status="
				+ status + ", email=" + email + ", gender=" + gender + ", mobNum=" + mobNum + ", coverLetterNum="
				+ coverLetterNum + ", resumeDocId=" + resumeDocId + ", isValidate=" + isValidate + ", validateEmpId="
				+ validateEmpId + ", validateDt=" + validateDt + ", institute=" + institute + ", course=" + course
				+ ", passoutYear=" + passoutYear + ", historyOfArrears=" + historyOfArrears + ", marks=" + marks
				+ ", currCompany=" + currCompany + ", currDesignation=" + currDesignation + ", currExperience="
				+ currExperience + ", currLocation=" + currLocation + ", currSalary=" + currSalary + ", degreeName="
				+ degreeName + ", expType=" + expType + ", otherCollegeName=" + otherCollegeName + ", otherCourseName="
				+ otherCourseName + ", otherDegreeName=" + otherDegreeName + ", applicantId=" + applicantId
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}	
}
