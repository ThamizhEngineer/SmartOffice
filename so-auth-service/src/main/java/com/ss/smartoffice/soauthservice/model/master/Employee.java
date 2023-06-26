package com.ss.smartoffice.soauthservice.model.master;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;



public class Employee extends BaseEntity{

	private int id;
	private String empName;
	private String empCode;
	private String loginUserId;
	private String tempContactNo;
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
	private String reference1;
	private String reference2;
	private String dob;
	
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
	private String defaultAttendanceCode;
	private String remarks;
	
	private Integer gradeId;
	private String gradeName;
	private Integer gradeOrder;
	private Integer designationId;
	private String gender;
	
	private String desigName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDt;
	private String isEnabled;;
	private String createdBy;
	private String modifiedBy; 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getReference1() {
		return reference1;
	}

	public void setReference1(String reference1) {
		this.reference1 = reference1;
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

	public String getDefaultAttendanceCode() {
		return defaultAttendanceCode;
	}

	public void setDefaultAttendanceCode(String defaultAttendanceCode) {
		this.defaultAttendanceCode = defaultAttendanceCode;
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

	public String getDesigName() {
		return desigName;
	}

	public void setDesigName(String desigName) {
		this.desigName = desigName;
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
		return "Employee [id=" + id + ", empName=" + empName + ", empCode=" + empCode + ", loginUserId=" + loginUserId
				+ ", tempContactNo=" + tempContactNo + ", permContactNo=" + permContactNo + ", emailId=" + emailId
				+ ", curAddress=" + curAddress + ", permAddress=" + permAddress + ", maritalStatus=" + maritalStatus
				+ ", spouseName=" + spouseName + ", spouseOccup=" + spouseOccup + ", noOfChildren=" + noOfChildren
				+ ", noOfDependant=" + noOfDependant + ", relInPodhigai=" + relInPodhigai + ", relName=" + relName
				+ ", relation=" + relation + ", jobOpening=" + jobOpening + ", hobbies=" + hobbies + ", offences="
				+ offences + ", reference1=" + reference1 + ", reference2=" + reference2 + ", dob=" + dob + ", height="
				+ height + ", weight=" + weight + ", bloodGroup=" + bloodGroup + ", eyePower=" + eyePower
				+ ", eyeLeftPower=" + eyeLeftPower + ", eyeRightPower=" + eyeRightPower + ", identifnMrk1="
				+ identifnMrk1 + ", identifnMrk2=" + identifnMrk2 + ", physicalChalng=" + physicalChalng + ", name="
				+ name + ", relations=" + relations + ", contactNo=" + contactNo + ", monthly=" + monthly
				+ ", annually=" + annually + ", benefits=" + benefits + ", gross=" + gross + ", grossOther="
				+ grossOther + ", takeHome=" + takeHome + ", nextIncr=" + nextIncr + ", panNo=" + panNo + ", epfNo="
				+ epfNo + ", passport=" + passport + ", drivingLicense=" + drivingLicense + ", esiNo=" + esiNo
				+ ", aadharNo=" + aadharNo + ", defaultAttendanceCode=" + defaultAttendanceCode + ", remarks=" + remarks
				+ ", gradeId=" + gradeId + ", gradeName=" + gradeName + ", gradeOrder=" + gradeOrder
				+ ", designationId=" + designationId + ", gender=" + gender + ", desigName=" + desigName + ", startDt="
				+ startDt + ", endDt=" + endDt + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	public Employee(int id, String empName, String empCode, String loginUserId, String tempContactNo,
			String permContactNo, String emailId, String curAddress, String permAddress, String maritalStatus,
			String spouseName, String spouseOccup, Integer noOfChildren, Integer noOfDependant, String relInPodhigai,
			String relName, String relation, String jobOpening, String hobbies, String offences, String reference1,
			String reference2, String dob, String height, String weight, String bloodGroup, String eyePower,
			String eyeLeftPower, String eyeRightPower, String identifnMrk1, String identifnMrk2, String physicalChalng,
			String name, String relations, String contactNo, String monthly, String annually, String benefits,
			String gross, String grossOther, String takeHome, String nextIncr, String panNo, String epfNo,
			String passport, String drivingLicense, String esiNo, String aadharNo, String defaultAttendanceCode,
			String remarks, Integer gradeId, String gradeName, Integer gradeOrder, Integer designationId, String gender,
			String desigName, LocalDateTime startDt, LocalDateTime endDt, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.empName = empName;
		this.empCode = empCode;
		this.loginUserId = loginUserId;
		this.tempContactNo = tempContactNo;
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
		this.reference1 = reference1;
		this.reference2 = reference2;
		this.dob = dob;
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
		this.defaultAttendanceCode = defaultAttendanceCode;
		this.remarks = remarks;
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.gradeOrder = gradeOrder;
		this.designationId = designationId;
		this.gender = gender;
		this.desigName = desigName;
		this.startDt = startDt;
		this.endDt = endDt;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	
	
	
}