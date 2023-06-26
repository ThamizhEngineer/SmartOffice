package com.ss.smartoffice.shared.model.employee;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
import com.ss.smartoffice.shared.model.employee.LocationSeed;
import lombok.Builder;

@Entity
@Table(name="m_employee")

@Component
@Builder
public class memployee extends BaseEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String empName;
	private String empCode;
	private String loginUserId;
	private String tempContactNo;

	
	private String attendEligibility;
	
	private String permContactNo;
	
	private String emailId;
	private String curAddress;
	private String permAddress;
	private String maritalStatus;
	private String spouseName;
	private String spouseOccup;
	private Integer noOfChildren;
	private Integer noOfDependant;
	private String relInPodhigai;
	private String relName;
	private String relation;
	private String jobOpening;
	private String hobbies;
	private String offences;
	
	private String reference2;
	private String dob;
	private String doj;
	private String height;
	private String weight;
	private String bloodGroup;
	private String eyePower;
	private String eyeLeftPower;
	private String eyeRightPower;
	private String identifnMrk1;
	private String identifnMrk2;
	private String physicalChalng;
	
	private String name;
	private String relations;
	private String contactNo;
	
	private String monthly;
	private String annually;
	private String benefits;
	private String gross;
	private String grossOther;
	private String takeHome;
	private String nextIncr;
	private String panNo;
	private String epfNo;
	private String passport;
	private String drivingLicense;
	private String esiNo;
	private	String aadharNo;
	@Column(name="n1_emp_id")
	private String n1EmpId;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id= n1_emp_id)")
	private String managerName;

	private String defaultAttendanceCode;
	@Column(name="n2_emp_id")
	private String n2EmpId;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id= n2_emp_id)")
	private String reviewManagerName;
	
	@Column(name="hr_1_usr_grp_id")
	private String hr1UsrGrpId;
	@Formula("(SELECT urgrp.user_group_name from s_user_group urgrp where urgrp.id= hr_1_usr_grp_id)")
	private String hr1UsrGrpName;
	

	
	
	@Column(name="hr_2_usr_grp_id")
	private String hr2UsrGrpId;
	@Formula("(SELECT urgrp.user_group_name from s_user_group urgrp where urgrp.id= hr_2_usr_grp_id)")
	private String hr2UsrGrpName;
	
	@Column(name="acc_1_usr_grp_id")
	private String acc1UsrGrpId;
	private String shiftId;
	@Formula("(select shift.shift_name from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftName;
	
	@Formula("(select shift.shift_code from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftCode;
	
	@Formula("(select shift.from_time from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftFromTime;
	
	@Formula("(select shift.to_time from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftToTime;
	
	@Column(name = "is_bonus")
	private String isBonus;
	@Column(name = "dir_usr_grp_id")
	private String dirUsrGrpId;
	
	@Formula("(SELECT urgrp.user_group_name from s_user_group urgrp where urgrp.id= dir_usr_grp_id)")
	private String dirUsrGrpName;

	@Formula("(SELECT urgrp.user_group_name from s_user_group urgrp where urgrp.id= acc_1_usr_grp_id)")
	private String isAcc1UsrGrpName;
	
	@Column(name="acc_2_usr_grp_id")
	private String acc2UsrGrpId;

	@Formula("(SELECT urgrp.user_group_name from s_user_group urgrp where urgrp.id= acc_2_usr_grp_id)")
	private String isAcc2UsrGrpName;
	
	
	private String createEmpId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime profileCreatedDt;
	private String profileCreateRemarks;
	private String onboardEmpId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime onboardedDt;
	private String onboardRemarks;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime profUpdationDt;
	private String profValidateEmpId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime profValidationDt;
	private String profValidateRemarks;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime skillUpdationDt;
	private String skillValidateEmpId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime skillValidationDt;
	private String skillValidateRemarks;
	private String empStatus;
	private String internalId;
	private String remarks;
	
	private Integer gradeId;
	@Formula("(SELECT grade.grade_name FROM s_grade grade WHERE grade.id = grade_id)")
	private String gradeName;
	@Formula("(SELECT grade.grade_order FROM s_grade grade WHERE grade.id= grade_id)")
	private Integer gradeOrder;
	private Integer designationId;
	private String gender;
	private String isBillable;
	private String firstName ;
	private String lastName;
	

	private String desigName;
 
	private String empTypeCode;
	private String docId;
	@Formula("(SELECT doc.doc_name from d_doc_info doc left join m_employee emp on emp.doc_id = doc.doc_id where emp.id= id)")
	private String docName;
	@Formula("(SELECT doc.doc_location from d_doc_info doc left join m_employee emp on emp.doc_id  = doc.doc_id where emp.id= id)")
	private String docLocation;
	
	private String panDocId;
	private String aadharDocId;
	private String passBookDocId;
	private String contractorCode;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDt;
	private String contactMobileNo;
	private String contactEmailId;
	@Column(name="m_office_id")
	private Integer officeId;
	@Formula("(SELECT ofice.office_name FROM m_office ofice WHERE ofice.id = m_office_id)")
	private String officeName;
	
	@Transient
	private String officeChange;
	
	@Column(name="m_country_id")
	private Integer countryId;
	private String isEnabled;;
	private String createdBy;
	private String modifiedBy;
	
	private Integer deptId;
	@Formula("(SELECT dept.dept_name FROM m_dept dept WHERE dept.id = dept_id)")
	private String deptName;
	@Formula("(SELECT dept.dept_code FROM m_dept dept WHERE dept.id = dept_id)")
	private String deptCode;
	private String empProfilePdfId;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<LanguagesKnown> languages;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<EmployeeSkill> employeeSkills;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<FamilyInfo> familyInfo;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<EducationalInfo> educationalInfo;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<AcademicAcheiv> academicAcheiv;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<PreviousEmploymentDetails> previousEmployDetails;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<EmployeeCc> employeecc;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_employee_id")
	private List<BankDetails> bankDetails;
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
    @JoinTable(name = "m_emp_loc", 
		    joinColumns = { @JoinColumn(name = "m_employee_id", referencedColumnName = "id") }, 
		    inverseJoinColumns = { @JoinColumn(name = "s_location_id" , referencedColumnName = "id")})
private Set<LocationSeed> employeeLocations = new HashSet<>();
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String pfNo;
	private String uanNo;
	@Column(name="rela_cont_no_2")
	private String relaContNo2;
	private String relaEmailId;
	private String prePfNo;
	private String preEsiNo;
	private String preUanNo;
	private String drivLicDocId;
	private String passDocId;
	private String addrProofDocId;
	private String panCheck;
	private String adCheck;
	private String addrPrCheck;
	private String passCheck;
	private String drivLicCheck;
	private String empCategory;
	private String isSystemUser;
	private String legalPending;
	// this for incident event
	@Transient
	private String isEmpAttend;

	public memployee() {
		super();
		// TODO Auto-generated constructor stub
	}
	//this constructor manager-swap
	public memployee(Integer id, String empName, String empCode, String n1EmpId, String n2EmpId, String hr1UsrGrpId, String hr1UsrGrpName, String hr2UsrGrpId,
			String hr2UsrGrpName,String acc1UsrGrpId, String isAcc1UsrGrpName, String acc2UsrGrpId,
			String isAcc2UsrGrpName,String empCategory,String legalPending) {
		super();
		this.id = id;
		this.empName = empName;
		this.empCode = empCode;
		this.n1EmpId = n1EmpId;
		this.n2EmpId = n2EmpId;
		this.hr1UsrGrpId = hr1UsrGrpId;
		this.hr1UsrGrpName = hr1UsrGrpName;
		this.hr2UsrGrpId = hr2UsrGrpId;
		this.hr2UsrGrpName = hr2UsrGrpName;
		this.acc1UsrGrpId = acc1UsrGrpId;
		this.isAcc1UsrGrpName = isAcc1UsrGrpName;
		this.acc2UsrGrpId = acc2UsrGrpId;
		this.isAcc2UsrGrpName = isAcc2UsrGrpName;
		this.empCategory=empCategory;
		this.legalPending = legalPending;
	}

	//this constructor hr manager swap
	
	
	
	
	//this constructor is referred in EmployeeRepository.findSummaries()
	public memployee(Integer id, String empName, String empCode, String loginUserId, String permContactNo,
			String emailId, String dob, String empStatus, Integer gradeId, String gradeName, Integer designationId,
			String gender, String desigName, String empTypeCode, LocalDateTime startDt, LocalDateTime endDt,
			String isEnabled, Integer deptId, String deptName,String empCategory, String isSystemUser,String legalPending) {
		super();
		this.id = id;
		this.empName = empName;
		this.empCode = empCode;
		this.loginUserId = loginUserId;
		this.permContactNo = permContactNo;
		this.emailId = emailId;
		this.dob = dob;
		this.empStatus = empStatus;
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.designationId = designationId;
		this.gender = gender;
		this.desigName = desigName;
		this.empTypeCode = empTypeCode;
		this.startDt = startDt;
		this.endDt = endDt;
		this.isEnabled = isEnabled;
		this.deptId = deptId;
		this.deptName = deptName;
		this.empCategory =empCategory;
		this.isSystemUser = isSystemUser;
		this.legalPending = legalPending;
	}
	
	
	public memployee(Integer id, String empName, String empCode, String loginUserId, String tempContactNo,
			String attendEligibility, String permContactNo, String emailId, String curAddress, String permAddress,
			String maritalStatus, String spouseName, String spouseOccup, Integer noOfChildren, Integer noOfDependant,
			String relInPodhigai, String relName, String relation, String jobOpening, String hobbies, String offences,
			String reference2, String dob, String doj, String height, String weight, String bloodGroup, String eyePower,
			String eyeLeftPower, String eyeRightPower, String identifnMrk1, String identifnMrk2, String physicalChalng,
			String name, String relations, String contactNo, String monthly, String annually, String benefits,
			String gross, String grossOther, String takeHome, String nextIncr, String panNo, String epfNo,
			String passport, String drivingLicense, String esiNo, String aadharNo, String n1EmpId, String managerName,
			String defaultAttendanceCode, String n2EmpId, String reviewManagerName, String hr1UsrGrpId,
			String hr1UsrGrpName, String hr2UsrGrpId, String hr2UsrGrpName, String acc1UsrGrpId, String shiftId,
			String shiftName, String shiftCode, String shiftFromTime, String shiftToTime, String isBonus,
			String dirUsrGrpId, String dirUsrGrpName, String isAcc1UsrGrpName, String acc2UsrGrpId,
			String isAcc2UsrGrpName, String createEmpId, LocalDateTime profileCreatedDt, String profileCreateRemarks,
			String onboardEmpId, LocalDateTime onboardedDt, String onboardRemarks, LocalDateTime profUpdationDt,
			String profValidateEmpId, LocalDateTime profValidationDt, String profValidateRemarks,
			LocalDateTime skillUpdationDt, String skillValidateEmpId, LocalDateTime skillValidationDt,
			String skillValidateRemarks, String empStatus, String internalId, String remarks, Integer gradeId,
			String gradeName, Integer gradeOrder, Integer designationId, String gender, String isBillable,
			String firstName, String lastName, String desigName, String empTypeCode, String docId, String docName,
			String docLocation, String panDocId, String aadharDocId, String passBookDocId, String contractorCode,
			LocalDateTime startDt, LocalDateTime endDt, String contactMobileNo, String contactEmailId, Integer officeId,
			String officeName, String officeChange, Integer countryId, String isEnabled, String createdBy,
			String modifiedBy, Integer deptId, String deptName, String deptCode, String empProfilePdfId,
			List<LanguagesKnown> languages, List<EmployeeSkill> employeeSkills, List<FamilyInfo> familyInfo,
			List<EducationalInfo> educationalInfo, List<AcademicAcheiv> academicAcheiv,
			List<PreviousEmploymentDetails> previousEmployDetails, List<EmployeeCc> employeecc,
			List<BankDetails> bankDetails, Set<LocationSeed> employeeLocations, LocalDateTime createdDt,
			LocalDateTime modifiedDt, String pfNo, String uanNo, String relaContNo2, String relaEmailId, String prePfNo,
			String preEsiNo, String preUanNo, String drivLicDocId, String passDocId, String addrProofDocId,
			String panCheck, String adCheck, String addrPrCheck, String passCheck, String drivLicCheck,
			String empCategory, String isSystemUser, String legalPending, String isEmpAttend) {
		super();
		this.id = id;
		this.empName = empName;
		this.empCode = empCode;
		this.loginUserId = loginUserId;
		this.tempContactNo = tempContactNo;
		this.attendEligibility = attendEligibility;
		this.permContactNo = permContactNo;
		this.emailId = emailId;
		this.curAddress = curAddress;
		this.permAddress = permAddress;
		this.maritalStatus = maritalStatus;
		this.spouseName = spouseName;
		this.spouseOccup = spouseOccup;
		this.noOfChildren = noOfChildren;
		this.noOfDependant = noOfDependant;
		this.relInPodhigai = relInPodhigai;
		this.relName = relName;
		this.relation = relation;
		this.jobOpening = jobOpening;
		this.hobbies = hobbies;
		this.offences = offences;
		this.reference2 = reference2;
		this.dob = dob;
		this.doj = doj;
		this.height = height;
		this.weight = weight;
		this.bloodGroup = bloodGroup;
		this.eyePower = eyePower;
		this.eyeLeftPower = eyeLeftPower;
		this.eyeRightPower = eyeRightPower;
		this.identifnMrk1 = identifnMrk1;
		this.identifnMrk2 = identifnMrk2;
		this.physicalChalng = physicalChalng;
		this.name = name;
		this.relations = relations;
		this.contactNo = contactNo;
		this.monthly = monthly;
		this.annually = annually;
		this.benefits = benefits;
		this.gross = gross;
		this.grossOther = grossOther;
		this.takeHome = takeHome;
		this.nextIncr = nextIncr;
		this.panNo = panNo;
		this.epfNo = epfNo;
		this.passport = passport;
		this.drivingLicense = drivingLicense;
		this.esiNo = esiNo;
		this.aadharNo = aadharNo;
		this.n1EmpId = n1EmpId;
		this.managerName = managerName;
		this.defaultAttendanceCode = defaultAttendanceCode;
		this.n2EmpId = n2EmpId;
		this.reviewManagerName = reviewManagerName;
		this.hr1UsrGrpId = hr1UsrGrpId;
		this.hr1UsrGrpName = hr1UsrGrpName;
		this.hr2UsrGrpId = hr2UsrGrpId;
		this.hr2UsrGrpName = hr2UsrGrpName;
		this.acc1UsrGrpId = acc1UsrGrpId;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.shiftCode = shiftCode;
		this.shiftFromTime = shiftFromTime;
		this.shiftToTime = shiftToTime;
		this.isBonus = isBonus;
		this.dirUsrGrpId = dirUsrGrpId;
		this.dirUsrGrpName = dirUsrGrpName;
		this.isAcc1UsrGrpName = isAcc1UsrGrpName;
		this.acc2UsrGrpId = acc2UsrGrpId;
		this.isAcc2UsrGrpName = isAcc2UsrGrpName;
		this.createEmpId = createEmpId;
		this.profileCreatedDt = profileCreatedDt;
		this.profileCreateRemarks = profileCreateRemarks;
		this.onboardEmpId = onboardEmpId;
		this.onboardedDt = onboardedDt;
		this.onboardRemarks = onboardRemarks;
		this.profUpdationDt = profUpdationDt;
		this.profValidateEmpId = profValidateEmpId;
		this.profValidationDt = profValidationDt;
		this.profValidateRemarks = profValidateRemarks;
		this.skillUpdationDt = skillUpdationDt;
		this.skillValidateEmpId = skillValidateEmpId;
		this.skillValidationDt = skillValidationDt;
		this.skillValidateRemarks = skillValidateRemarks;
		this.empStatus = empStatus;
		this.internalId = internalId;
		this.remarks = remarks;
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.gradeOrder = gradeOrder;
		this.designationId = designationId;
		this.gender = gender;
		this.isBillable = isBillable;
		this.firstName = firstName;
		this.lastName = lastName;
		this.desigName = desigName;
		this.empTypeCode = empTypeCode;
		this.docId = docId;
		this.docName = docName;
		this.docLocation = docLocation;
		this.panDocId = panDocId;
		this.aadharDocId = aadharDocId;
		this.passBookDocId = passBookDocId;
		this.contractorCode = contractorCode;
		this.startDt = startDt;
		this.endDt = endDt;
		this.contactMobileNo = contactMobileNo;
		this.contactEmailId = contactEmailId;
		this.officeId = officeId;
		this.officeName = officeName;
		this.officeChange = officeChange;
		this.countryId = countryId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptCode = deptCode;
		this.empProfilePdfId = empProfilePdfId;
		this.languages = languages;
		this.employeeSkills = employeeSkills;
		this.familyInfo = familyInfo;
		this.educationalInfo = educationalInfo;
		this.academicAcheiv = academicAcheiv;
		this.previousEmployDetails = previousEmployDetails;
		this.employeecc = employeecc;
		this.bankDetails = bankDetails;
		this.employeeLocations = employeeLocations;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.pfNo = pfNo;
		this.uanNo = uanNo;
		this.relaContNo2 = relaContNo2;
		this.relaEmailId = relaEmailId;
		this.prePfNo = prePfNo;
		this.preEsiNo = preEsiNo;
		this.preUanNo = preUanNo;
		this.drivLicDocId = drivLicDocId;
		this.passDocId = passDocId;
		this.addrProofDocId = addrProofDocId;
		this.panCheck = panCheck;
		this.adCheck = adCheck;
		this.addrPrCheck = addrPrCheck;
		this.passCheck = passCheck;
		this.drivLicCheck = drivLicCheck;
		this.empCategory = empCategory;
		this.isSystemUser = isSystemUser;
		this.legalPending = legalPending;
		this.isEmpAttend = isEmpAttend;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	
	public String getTempContactNo() {
		return tempContactNo;
	}
	public void setTempContactNo(String tempContactNo) {
		this.tempContactNo = tempContactNo;
	}
	public String getPermContactNo() {
		return permContactNo;
	}
	public void setPermContactNo(String permContactNo) {
		this.permContactNo = permContactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getSpouseOccup() {
		return spouseOccup;
	}
	public void setSpouseOccup(String spouseOccup) {
		this.spouseOccup = spouseOccup;
	}
	public Integer getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	public Integer getNoOfDependant() {
		return noOfDependant;
	}
	public void setNoOfDependant(Integer noOfDependant) {
		this.noOfDependant = noOfDependant;
	}
	public String getRelInPodhigai() {
		return relInPodhigai;
	}
	public void setRelInPodhigai(String relInPodhigai) {
		this.relInPodhigai = relInPodhigai;
	}
	public String getRelName() {
		return relName;
	}
	public void setRelName(String relName) {
		this.relName = relName;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getJobOpening() {
		return jobOpening;
	}
	public void setJobOpening(String jobOpening) {
		this.jobOpening = jobOpening;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getOffences() {
		return offences;
	}
	public void setOffences(String offences) {
		this.offences = offences;
	}
	public String getReference2() {
		return reference2;
	}
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getEyePower() {
		return eyePower;
	}
	public void setEyePower(String eyePower) {
		this.eyePower = eyePower;
	}
	public String getEyeLeftPower() {
		return eyeLeftPower;
	}
	public void setEyeLeftPower(String eyeLeftPower) {
		this.eyeLeftPower = eyeLeftPower;
	}
	public String getEyeRightPower() {
		return eyeRightPower;
	}
	public void setEyeRightPower(String eyeRightPower) {
		this.eyeRightPower = eyeRightPower;
	}
	public String getIdentifnMrk1() {
		return identifnMrk1;
	}
	public void setIdentifnMrk1(String identifnMrk1) {
		this.identifnMrk1 = identifnMrk1;
	}
	public String getIdentifnMrk2() {
		return identifnMrk2;
	}
	public void setIdentifnMrk2(String identifnMrk2) {
		this.identifnMrk2 = identifnMrk2;
	}
	public String getPhysicalChalng() {
		return physicalChalng;
	}
	public void setPhysicalChalng(String physicalChalng) {
		this.physicalChalng = physicalChalng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelations() {
		return relations;
	}
	public void setRelations(String relations) {
		this.relations = relations;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getMonthly() {
		return monthly;
	}
	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}
	public String getAnnually() {
		return annually;
	}
	public void setAnnually(String annually) {
		this.annually = annually;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public String getGross() {
		return gross;
	}
	public void setGross(String gross) {
		this.gross = gross;
	}
	public String getGrossOther() {
		return grossOther;
	}
	public void setGrossOther(String grossOther) {
		this.grossOther = grossOther;
	}
	public String getTakeHome() {
		return takeHome;
	}
	public void setTakeHome(String takeHome) {
		this.takeHome = takeHome;
	}
	public String getNextIncr() {
		return nextIncr;
	}
	public void setNextIncr(String nextIncr) {
		this.nextIncr = nextIncr;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getEpfNo() {
		return epfNo;
	}
	public void setEpfNo(String epfNo) {
		this.epfNo = epfNo;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public String getEsiNo() {
		return esiNo;
	}
	public void setEsiNo(String esiNo) {
		this.esiNo = esiNo;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
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
	public String getDefaultAttendanceCode() {
		return defaultAttendanceCode;
	}
	public void setDefaultAttendanceCode(String defaultAttendanceCode) {
		this.defaultAttendanceCode = defaultAttendanceCode;
	}
	public String getN2EmpId() {
		return n2EmpId;
	}
	public void setN2EmpId(String n2EmpId) {
		this.n2EmpId = n2EmpId;
	}
	public String getReviewManagerName() {
		return reviewManagerName;
	}
	public void setReviewManagerName(String reviewManagerName) {
		this.reviewManagerName = reviewManagerName;
	}
	public String getHr1UsrGrpId() {
		return hr1UsrGrpId;
	}
	public void setHr1UsrGrpId(String hr1UsrGrpId) {
		this.hr1UsrGrpId = hr1UsrGrpId;
	}
	public String getHr1UsrGrpName() {
		return hr1UsrGrpName;
	}
	public void setHr1UsrGrpName(String hr1UsrGrpName) {
		this.hr1UsrGrpName = hr1UsrGrpName;
	}
	public String getHr2UsrGrpId() {
		return hr2UsrGrpId;
	}
	public void setHr2UsrGrpId(String hr2UsrGrpId) {
		this.hr2UsrGrpId = hr2UsrGrpId;
	}
	public String getHr2UsrGrpName() {
		return hr2UsrGrpName;
	}
	public void setHr2UsrGrpName(String hr2UsrGrpName) {
		this.hr2UsrGrpName = hr2UsrGrpName;
	}
	public String getAcc1UsrGrpId() {
		return acc1UsrGrpId;
	}
	public void setAcc1UsrGrpId(String acc1UsrGrpId) {
		this.acc1UsrGrpId = acc1UsrGrpId;
	}
	public String getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(String isBonus) {
		this.isBonus = isBonus;
	}
	public String getDirUsrGrpId() {
		return dirUsrGrpId;
	}
	public void setDirUsrGrpId(String dirUsrGrpId) {
		this.dirUsrGrpId = dirUsrGrpId;
	}
	public String getDirUsrGrpName() {
		return dirUsrGrpName;
	}
	public void setDirUsrGrpName(String dirUsrGrpName) {
		this.dirUsrGrpName = dirUsrGrpName;
	}
	public String getIsAcc1UsrGrpName() {
		return isAcc1UsrGrpName;
	}
	public void setIsAcc1UsrGrpName(String isAcc1UsrGrpName) {
		this.isAcc1UsrGrpName = isAcc1UsrGrpName;
	}
	public String getAcc2UsrGrpId() {
		return acc2UsrGrpId;
	}
	public void setAcc2UsrGrpId(String acc2UsrGrpId) {
		this.acc2UsrGrpId = acc2UsrGrpId;
	}
	public String getIsAcc2UsrGrpName() {
		return isAcc2UsrGrpName;
	}
	public void setIsAcc2UsrGrpName(String isAcc2UsrGrpName) {
		this.isAcc2UsrGrpName = isAcc2UsrGrpName;
	}
	public String getCreateEmpId() {
		return createEmpId;
	}
	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId;
	}
	public LocalDateTime getProfileCreatedDt() {
		return profileCreatedDt;
	}
	public void setProfileCreatedDt(LocalDateTime profileCreatedDt) {
		this.profileCreatedDt = profileCreatedDt;
	}
	public String getProfileCreateRemarks() {
		return profileCreateRemarks;
	}
	public void setProfileCreateRemarks(String profileCreateRemarks) {
		this.profileCreateRemarks = profileCreateRemarks;
	}
	public String getOnboardEmpId() {
		return onboardEmpId;
	}
	public void setOnboardEmpId(String onboardEmpId) {
		this.onboardEmpId = onboardEmpId;
	}
	public LocalDateTime getOnboardedDt() {
		return onboardedDt;
	}
	public void setOnboardedDt(LocalDateTime onboardedDt) {
		this.onboardedDt = onboardedDt;
	}
	public String getOnboardRemarks() {
		return onboardRemarks;
	}
	public void setOnboardRemarks(String onboardRemarks) {
		this.onboardRemarks = onboardRemarks;
	}
	public LocalDateTime getProfUpdationDt() {
		return profUpdationDt;
	}
	public void setProfUpdationDt(LocalDateTime profUpdationDt) {
		this.profUpdationDt = profUpdationDt;
	}
	public String getProfValidateEmpId() {
		return profValidateEmpId;
	}
	public void setProfValidateEmpId(String profValidateEmpId) {
		this.profValidateEmpId = profValidateEmpId;
	}
	public LocalDateTime getProfValidationDt() {
		return profValidationDt;
	}
	public void setProfValidationDt(LocalDateTime profValidationDt) {
		this.profValidationDt = profValidationDt;
	}
	public String getProfValidateRemarks() {
		return profValidateRemarks;
	}
	public void setProfValidateRemarks(String profValidateRemarks) {
		this.profValidateRemarks = profValidateRemarks;
	}
	public LocalDateTime getSkillUpdationDt() {
		return skillUpdationDt;
	}
	public void setSkillUpdationDt(LocalDateTime skillUpdationDt) {
		this.skillUpdationDt = skillUpdationDt;
	}
	public String getSkillValidateEmpId() {
		return skillValidateEmpId;
	}
	public void setSkillValidateEmpId(String skillValidateEmpId) {
		this.skillValidateEmpId = skillValidateEmpId;
	}
	public LocalDateTime getSkillValidationDt() {
		return skillValidationDt;
	}
	public void setSkillValidationDt(LocalDateTime skillValidationDt) {
		this.skillValidationDt = skillValidationDt;
	}
	public String getSkillValidateRemarks() {
		return skillValidateRemarks;
	}
	public void setSkillValidateRemarks(String skillValidateRemarks) {
		this.skillValidateRemarks = skillValidateRemarks;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public String getInternalId() {
		return internalId;
	}
	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Integer getGradeOrder() {
		return gradeOrder;
	}
	public void setGradeOrder(Integer gradeOrder) {
		this.gradeOrder = gradeOrder;
	}
	public Integer getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getDesigName() {
		return desigName;
	}
	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocLocation() {
		return docLocation;
	}
	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}
	public String getPanDocId() {
		return panDocId;
	}
	public void setPanDocId(String panDocId) {
		this.panDocId = panDocId;
	}
	public String getAadharDocId() {
		return aadharDocId;
	}
	public void setAadharDocId(String aadharDocId) {
		this.aadharDocId = aadharDocId;
	}
	public String getPassBookDocId() {
		return passBookDocId;
	}
	public void setPassBookDocId(String passBookDocId) {
		this.passBookDocId = passBookDocId;
	}
	public String getContractorCode() {
		return contractorCode;
	}
	public void setContractorCode(String contractorCode) {
		this.contractorCode = contractorCode;
	}
	public LocalDateTime getStartDt() {
		return startDt;
	}
	public void setStartDt(LocalDateTime startDt) {
		this.startDt = startDt;
	}
	public LocalDateTime getEndDt() {
		return endDt;
	}
	public void setEndDt(LocalDateTime endDt) {
		this.endDt = endDt;
	}
	public String getContactMobileNo() {
		return contactMobileNo;
	}
	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}
	public String getContactEmailId() {
		return contactEmailId;
	}
	public void setContactEmailId(String contactEmailId) {
		this.contactEmailId = contactEmailId;
	}
	public Integer getOfficeId() {
		return officeId;
	}
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeChange() {
		return officeChange;
	}
	public void setOfficeChange(String officeChange) {
		this.officeChange = officeChange;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getEmpProfilePdfId() {
		return empProfilePdfId;
	}
	public void setEmpProfilePdfId(String empProfilePdfId) {
		this.empProfilePdfId = empProfilePdfId;
	}
	public List<LanguagesKnown> getLanguages() {
		return languages;
	}
	public void setLanguages(List<LanguagesKnown> languages) {
		this.languages = languages;
	}
	public List<EmployeeSkill> getEmployeeSkills() {
		return employeeSkills;
	}
	public void setEmployeeSkills(List<EmployeeSkill> employeeSkills) {
		this.employeeSkills = employeeSkills;
	}
	public List<FamilyInfo> getFamilyInfo() {
		return familyInfo;
	}
	public void setFamilyInfo(List<FamilyInfo> familyInfo) {
		this.familyInfo = familyInfo;
	}
	public List<EducationalInfo> getEducationalInfo() {
		return educationalInfo;
	}
	public void setEducationalInfo(List<EducationalInfo> educationalInfo) {
		this.educationalInfo = educationalInfo;
	}
	public List<AcademicAcheiv> getAcademicAcheiv() {
		return academicAcheiv;
	}
	public void setAcademicAcheiv(List<AcademicAcheiv> academicAcheiv) {
		this.academicAcheiv = academicAcheiv;
	}
	public List<PreviousEmploymentDetails> getPreviousEmployDetails() {
		return previousEmployDetails;
	}
	public void setPreviousEmployDetails(List<PreviousEmploymentDetails> previousEmployDetails) {
		this.previousEmployDetails = previousEmployDetails;
	}
	public List<EmployeeCc> getEmployeecc() {
		return employeecc;
	}
	public void setEmployeecc(List<EmployeeCc> employeecc) {
		this.employeecc = employeecc;
	}
	public List<BankDetails> getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(List<BankDetails> bankDetails) {
		this.bankDetails = bankDetails;
	}
	public Set<LocationSeed> getEmployeeLocations() {
		return employeeLocations;
	}
	public void setEmployeeLocations(Set<LocationSeed> employeeLocations) {
		this.employeeLocations = employeeLocations;
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
	public String getPfNo() {
		return pfNo;
	}
	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}
	public String getUanNo() {
		return uanNo;
	}
	public void setUanNo(String uanNo) {
		this.uanNo = uanNo;
	}
	public String getRelaContNo2() {
		return relaContNo2;
	}
	public void setRelaContNo2(String relaContNo2) {
		this.relaContNo2 = relaContNo2;
	}
	public String getRelaEmailId() {
		return relaEmailId;
	}
	public void setRelaEmailId(String relaEmailId) {
		this.relaEmailId = relaEmailId;
	}
	public String getPrePfNo() {
		return prePfNo;
	}
	public void setPrePfNo(String prePfNo) {
		this.prePfNo = prePfNo;
	}
	public String getPreEsiNo() {
		return preEsiNo;
	}
	public void setPreEsiNo(String preEsiNo) {
		this.preEsiNo = preEsiNo;
	}
	public String getPreUanNo() {
		return preUanNo;
	}
	public void setPreUanNo(String preUanNo) {
		this.preUanNo = preUanNo;
	}
	public String getDrivLicDocId() {
		return drivLicDocId;
	}
	public void setDrivLicDocId(String drivLicDocId) {
		this.drivLicDocId = drivLicDocId;
	}
	public String getPassDocId() {
		return passDocId;
	}
	public void setPassDocId(String passDocId) {
		this.passDocId = passDocId;
	}
	public String getAddrProofDocId() {
		return addrProofDocId;
	}
	public void setAddrProofDocId(String addrProofDocId) {
		this.addrProofDocId = addrProofDocId;
	}
	public String getPanCheck() {
		return panCheck;
	}
	public void setPanCheck(String panCheck) {
		this.panCheck = panCheck;
	}
	public String getAdCheck() {
		return adCheck;
	}
	public void setAdCheck(String adCheck) {
		this.adCheck = adCheck;
	}
	public String getAddrPrCheck() {
		return addrPrCheck;
	}
	public void setAddrPrCheck(String addrPrCheck) {
		this.addrPrCheck = addrPrCheck;
	}
	public String getPassCheck() {
		return passCheck;
	}
	public void setPassCheck(String passCheck) {
		this.passCheck = passCheck;
	}
	public String getDrivLicCheck() {
		return drivLicCheck;
	}
	public void setDrivLicCheck(String drivLicCheck) {
		this.drivLicCheck = drivLicCheck;
	}
	public String getEmpCategory() {
		return empCategory;
	}
	public void setEmpCategory(String empCategory) {
		this.empCategory = empCategory;
	}
	public String getIsSystemUser() {
		return isSystemUser;
	}
	public void setIsSystemUser(String isSystemUser) {
		this.isSystemUser = isSystemUser;
	}	
	public String getLegalPending() {
		return legalPending;
	}
	public void setLegalPending(String legalPending) {
		this.legalPending = legalPending;
	}
	public String getIsEmpAttend() {
		return isEmpAttend;
	}
	public void setIsEmpAttend(String isEmpAttend) {
		this.isEmpAttend = isEmpAttend;
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
	public String getShiftFromTime() {
		return shiftFromTime;
	}
	public void setShiftFromTime(String shiftFromTime) {
		this.shiftFromTime = shiftFromTime;
	}
	public String getShiftToTime() {
		return shiftToTime;
	}
	public void setShiftToTime(String shiftToTime) {
		this.shiftToTime = shiftToTime;
	}
	public String getAttendEligibility() {
		return attendEligibility;
	}
	public void setAttendEligibility(String attendEligibility) {
		this.attendEligibility = attendEligibility;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	
	@Override
	public String toString() {
		return "memployee [id=" + id + ", empName=" + empName + ", empCode=" + empCode + ", loginUserId=" + loginUserId
				+ ", tempContactNo=" + tempContactNo + ", attendEligibility=" + attendEligibility + ", permContactNo="
				+ permContactNo + ", emailId=" + emailId + ", curAddress=" + curAddress + ", permAddress=" + permAddress
				+ ", maritalStatus=" + maritalStatus + ", spouseName=" + spouseName + ", spouseOccup=" + spouseOccup
				+ ", noOfChildren=" + noOfChildren + ", noOfDependant=" + noOfDependant + ", relInPodhigai="
				+ relInPodhigai + ", relName=" + relName + ", relation=" + relation + ", jobOpening=" + jobOpening
				+ ", hobbies=" + hobbies + ", offences=" + offences + ", reference2=" + reference2 + ", dob=" + dob
				+ ", doj=" + doj + ", height=" + height + ", weight=" + weight + ", bloodGroup=" + bloodGroup
				+ ", eyePower=" + eyePower + ", eyeLeftPower=" + eyeLeftPower + ", eyeRightPower=" + eyeRightPower
				+ ", identifnMrk1=" + identifnMrk1 + ", identifnMrk2=" + identifnMrk2 + ", physicalChalng="
				+ physicalChalng + ", name=" + name + ", relations=" + relations + ", contactNo=" + contactNo
				+ ", monthly=" + monthly + ", annually=" + annually + ", benefits=" + benefits + ", gross=" + gross
				+ ", grossOther=" + grossOther + ", takeHome=" + takeHome + ", nextIncr=" + nextIncr + ", panNo="
				+ panNo + ", epfNo=" + epfNo + ", passport=" + passport + ", drivingLicense=" + drivingLicense
				+ ", esiNo=" + esiNo + ", aadharNo=" + aadharNo + ", n1EmpId=" + n1EmpId + ", managerName="
				+ managerName + ", defaultAttendanceCode=" + defaultAttendanceCode + ", n2EmpId=" + n2EmpId
				+ ", reviewManagerName=" + reviewManagerName + ", hr1UsrGrpId=" + hr1UsrGrpId + ", hr1UsrGrpName="
				+ hr1UsrGrpName + ", hr2UsrGrpId=" + hr2UsrGrpId + ", hr2UsrGrpName=" + hr2UsrGrpName
				+ ", acc1UsrGrpId=" + acc1UsrGrpId + ", shiftId=" + shiftId + ", shiftName=" + shiftName
				+ ", shiftCode=" + shiftCode + ", shiftFromTime=" + shiftFromTime + ", shiftToTime=" + shiftToTime
				+ ", isBonus=" + isBonus + ", dirUsrGrpId=" + dirUsrGrpId + ", dirUsrGrpName=" + dirUsrGrpName
				+ ", isAcc1UsrGrpName=" + isAcc1UsrGrpName + ", acc2UsrGrpId=" + acc2UsrGrpId + ", isAcc2UsrGrpName="
				+ isAcc2UsrGrpName + ", createEmpId=" + createEmpId + ", profileCreatedDt=" + profileCreatedDt
				+ ", profileCreateRemarks=" + profileCreateRemarks + ", onboardEmpId=" + onboardEmpId + ", onboardedDt="
				+ onboardedDt + ", onboardRemarks=" + onboardRemarks + ", profUpdationDt=" + profUpdationDt
				+ ", profValidateEmpId=" + profValidateEmpId + ", profValidationDt=" + profValidationDt
				+ ", profValidateRemarks=" + profValidateRemarks + ", skillUpdationDt=" + skillUpdationDt
				+ ", skillValidateEmpId=" + skillValidateEmpId + ", skillValidationDt=" + skillValidationDt
				+ ", skillValidateRemarks=" + skillValidateRemarks + ", empStatus=" + empStatus + ", internalId="
				+ internalId + ", remarks=" + remarks + ", gradeId=" + gradeId + ", gradeName=" + gradeName
				+ ", gradeOrder=" + gradeOrder + ", designationId=" + designationId + ", gender=" + gender
				+ ", isBillable=" + isBillable + ", firstName=" + firstName + ", lastName=" + lastName + ", desigName="
				+ desigName + ", empTypeCode=" + empTypeCode + ", docId=" + docId + ", docName=" + docName
				+ ", docLocation=" + docLocation + ", panDocId=" + panDocId + ", aadharDocId=" + aadharDocId
				+ ", passBookDocId=" + passBookDocId + ", contractorCode=" + contractorCode + ", startDt=" + startDt
				+ ", endDt=" + endDt + ", contactMobileNo=" + contactMobileNo + ", contactEmailId=" + contactEmailId
				+ ", officeId=" + officeId + ", officeName=" + officeName + ", officeChange=" + officeChange
				+ ", countryId=" + countryId + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", deptId=" + deptId + ", deptName=" + deptName + ", deptCode=" + deptCode
				+ ", empProfilePdfId=" + empProfilePdfId + ", languages=" + languages + ", employeeSkills="
				+ employeeSkills + ", familyInfo=" + familyInfo + ", educationalInfo=" + educationalInfo
				+ ", academicAcheiv=" + academicAcheiv + ", previousEmployDetails=" + previousEmployDetails
				+ ", employeecc=" + employeecc + ", bankDetails=" + bankDetails + ", employeeLocations="
				+ employeeLocations + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + ", pfNo=" + pfNo
				+ ", uanNo=" + uanNo + ", relaContNo2=" + relaContNo2 + ", relaEmailId=" + relaEmailId + ", prePfNo="
				+ prePfNo + ", preEsiNo=" + preEsiNo + ", preUanNo=" + preUanNo + ", drivLicDocId=" + drivLicDocId
				+ ", passDocId=" + passDocId + ", addrProofDocId=" + addrProofDocId + ", panCheck=" + panCheck
				+ ", adCheck=" + adCheck + ", addrPrCheck=" + addrPrCheck + ", passCheck=" + passCheck
				+ ", drivLicCheck=" + drivLicCheck + ", empCategory=" + empCategory + ", isSystemUser=" + isSystemUser
				+ ", legalPending=" + legalPending + ", isEmpAttend=" + isEmpAttend + "]";
	}
	
	
	
	
	
}