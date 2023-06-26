package com.ss.smartoffice.sodocumentservice.model;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Applicant {
	

	
	private Integer id;
	private String applicantName;
	private String applicantCode;
	private String docId;
	private String docLocation;
	private String docName;
	private String firstName;
	private String lastName;

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
	
	private String monthlyRemun;
	private String annualRemun;
	private String benefitsRemun;
	private String grossRemun;
	private String grossOtherRemun;
	private String takeHomeRemun;
	private String nextIncrRemun;
	private String panNo;
	private String epfNo;
	private String passport;
	private String drivingLicense;
	private String esiNo;
	private	String aadharNo;	
	private String hr1Id;
	private String hrManager1Name;
	private String hr2Id;
	private String hrManager2Name;
	private String remarks;	
	private String gender;	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDt;
	private String contactMobileNo;
	private String contactEmailId;
	private String isEnabled;;
	private String createdBy;
	private String modifiedBy;
	private String loginUserId;
	private String appPdfId;
	

	private List<ApplicantLanguagesKnown> languages;

	private List<ApplicantFamilyInfo> familyInfo;
	
	private List<ApplicantEducationalInfo> educationalInfo;

	private List<ApplicantAcademicAcheiv> academicAcheiv;

	private List<ApplicantPreviousEmploymentDetails> previousEmployDetails;
	
	public Applicant() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	

	public Applicant(Integer id, String applicantName, String firstName,String lastName,String applicantCode, String contactNo, LocalDateTime startDt,
			LocalDateTime endDt, String isEnabled, String loginUserId) {
		super();
		this.id = id;
		this.applicantName = applicantName;
		this.firstName=firstName;
		this.lastName=lastName;
		this.applicantCode = applicantCode;
		this.contactNo = contactNo;
		this.startDt = startDt;
		this.endDt = endDt;
		this.isEnabled = isEnabled;
		this.loginUserId = loginUserId;
	}









	public Applicant(Integer id, String applicantName, String applicantCode, String docId, String docLocation,
			String docName, String firstName, String lastName, String curAddress, String permAddress,
			String maritalStatus, String spouseName, String spouseOccup, Integer noOfChildren, Integer noOfDependant,
			String relInPodhigai, String relName, String relation, String jobOpening, String hobbies, String offences,
			String reference1, String reference2, String dob, String height, String weight, String bloodGroup,
			String eyePower, String eyeLeftPower, String eyeRightPower, String identifnMrk1, String identifnMrk2,
			String physicalChalng, String name, String relations, String contactNo, String monthlyRemun,
			String annualRemun, String benefitsRemun, String grossRemun, String grossOtherRemun, String takeHomeRemun,
			String nextIncrRemun, String panNo, String epfNo, String passport, String drivingLicense, String esiNo,
			String aadharNo, String hr1Id, String hrManager1Name, String hr2Id, String hrManager2Name, String remarks,
			String gender, LocalDateTime startDt, LocalDateTime endDt, String contactMobileNo, String contactEmailId,
			String isEnabled, String createdBy, String modifiedBy, String loginUserId, String appPdfId,
			List<ApplicantLanguagesKnown> languages, List<ApplicantFamilyInfo> familyInfo,
			List<ApplicantEducationalInfo> educationalInfo, List<ApplicantAcademicAcheiv> academicAcheiv,
			List<ApplicantPreviousEmploymentDetails> previousEmployDetails) {
		super();
		this.id = id;
		this.applicantName = applicantName;
		this.applicantCode = applicantCode;
		this.docId = docId;
		this.docLocation = docLocation;
		this.docName = docName;
		this.firstName = firstName;
		this.lastName = lastName;
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
		this.monthlyRemun = monthlyRemun;
		this.annualRemun = annualRemun;
		this.benefitsRemun = benefitsRemun;
		this.grossRemun = grossRemun;
		this.grossOtherRemun = grossOtherRemun;
		this.takeHomeRemun = takeHomeRemun;
		this.nextIncrRemun = nextIncrRemun;
		this.panNo = panNo;
		this.epfNo = epfNo;
		this.passport = passport;
		this.drivingLicense = drivingLicense;
		this.esiNo = esiNo;
		this.aadharNo = aadharNo;
		this.hr1Id = hr1Id;
		this.hrManager1Name = hrManager1Name;
		this.hr2Id = hr2Id;
		this.hrManager2Name = hrManager2Name;
		this.remarks = remarks;
		this.gender = gender;
		this.startDt = startDt;
		this.endDt = endDt;
		this.contactMobileNo = contactMobileNo;
		this.contactEmailId = contactEmailId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.loginUserId = loginUserId;
		this.appPdfId = appPdfId;
		this.languages = languages;
		this.familyInfo = familyInfo;
		this.educationalInfo = educationalInfo;
		this.academicAcheiv = academicAcheiv;
		this.previousEmployDetails = previousEmployDetails;
	}



	@Override
	public String toString() {
		return "Applicant [id=" + id + ", applicantName=" + applicantName + ", applicantCode=" + applicantCode
				+ ", docId=" + docId + ", docLocation=" + docLocation + ", docName=" + docName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", curAddress=" + curAddress + ", permAddress=" + permAddress
				+ ", maritalStatus=" + maritalStatus + ", spouseName=" + spouseName + ", spouseOccup=" + spouseOccup
				+ ", noOfChildren=" + noOfChildren + ", noOfDependant=" + noOfDependant + ", relInPodhigai="
				+ relInPodhigai + ", relName=" + relName + ", relation=" + relation + ", jobOpening=" + jobOpening
				+ ", hobbies=" + hobbies + ", offences=" + offences + ", reference1=" + reference1 + ", reference2="
				+ reference2 + ", dob=" + dob + ", height=" + height + ", weight=" + weight + ", bloodGroup="
				+ bloodGroup + ", eyePower=" + eyePower + ", eyeLeftPower=" + eyeLeftPower + ", eyeRightPower="
				+ eyeRightPower + ", identifnMrk1=" + identifnMrk1 + ", identifnMrk2=" + identifnMrk2
				+ ", physicalChalng=" + physicalChalng + ", name=" + name + ", relations=" + relations + ", contactNo="
				+ contactNo + ", monthlyRemun=" + monthlyRemun + ", annualRemun=" + annualRemun + ", benefitsRemun="
				+ benefitsRemun + ", grossRemun=" + grossRemun + ", grossOtherRemun=" + grossOtherRemun
				+ ", takeHomeRemun=" + takeHomeRemun + ", nextIncrRemun=" + nextIncrRemun + ", panNo=" + panNo
				+ ", epfNo=" + epfNo + ", passport=" + passport + ", drivingLicense=" + drivingLicense + ", esiNo="
				+ esiNo + ", aadharNo=" + aadharNo + ", hr1Id=" + hr1Id + ", hrManager1Name=" + hrManager1Name
				+ ", hr2Id=" + hr2Id + ", hrManager2Name=" + hrManager2Name + ", remarks=" + remarks + ", gender="
				+ gender + ", startDt=" + startDt + ", endDt=" + endDt + ", contactMobileNo=" + contactMobileNo
				+ ", contactEmailId=" + contactEmailId + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", loginUserId=" + loginUserId + ", appPdfId=" + appPdfId
				+ ", languages=" + languages + ", familyInfo=" + familyInfo + ", educationalInfo=" + educationalInfo
				+ ", academicAcheiv=" + academicAcheiv + ", previousEmployDetails=" + previousEmployDetails + "]";
	}





	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public String getApplicantName() {
		return applicantName;
	}





	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}





	public String getApplicantCode() {
		return applicantCode;
	}





	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}





	public String getDocId() {
		return docId;
	}





	public void setDocId(String docId) {
		this.docId = docId;
	}





	public String getDocLocation() {
		return docLocation;
	}





	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}





	public String getDocName() {
		return docName;
	}





	public void setDocName(String docName) {
		this.docName = docName;
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





	public String getMonthlyRemun() {
		return monthlyRemun;
	}





	public void setMonthlyRemun(String monthlyRemun) {
		this.monthlyRemun = monthlyRemun;
	}





	public String getAnnualRemun() {
		return annualRemun;
	}





	public void setAnnualRemun(String annualRemun) {
		this.annualRemun = annualRemun;
	}





	public String getBenefitsRemun() {
		return benefitsRemun;
	}





	public void setBenefitsRemun(String benefitsRemun) {
		this.benefitsRemun = benefitsRemun;
	}





	public String getGrossRemun() {
		return grossRemun;
	}





	public void setGrossRemun(String grossRemun) {
		this.grossRemun = grossRemun;
	}





	public String getGrossOtherRemun() {
		return grossOtherRemun;
	}





	public void setGrossOtherRemun(String grossOtherRemun) {
		this.grossOtherRemun = grossOtherRemun;
	}





	public String getTakeHomeRemun() {
		return takeHomeRemun;
	}





	public void setTakeHomeRemun(String takeHomeRemun) {
		this.takeHomeRemun = takeHomeRemun;
	}





	public String getNextIncrRemun() {
		return nextIncrRemun;
	}





	public void setNextIncrRemun(String nextIncrRemun) {
		this.nextIncrRemun = nextIncrRemun;
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





	public String getHr1Id() {
		return hr1Id;
	}





	public void setHr1Id(String hr1Id) {
		this.hr1Id = hr1Id;
	}





	public String getHrManager1Name() {
		return hrManager1Name;
	}





	public void setHrManager1Name(String hrManager1Name) {
		this.hrManager1Name = hrManager1Name;
	}





	public String getHr2Id() {
		return hr2Id;
	}





	public void setHr2Id(String hr2Id) {
		this.hr2Id = hr2Id;
	}





	public String getHrManager2Name() {
		return hrManager2Name;
	}





	public void setHrManager2Name(String hrManager2Name) {
		this.hrManager2Name = hrManager2Name;
	}





	public String getRemarks() {
		return remarks;
	}





	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}





	public String getGender() {
		return gender;
	}





	public void setGender(String gender) {
		this.gender = gender;
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





	public String getLoginUserId() {
		return loginUserId;
	}





	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}





	public String getAppPdfId() {
		return appPdfId;
	}





	public void setAppPdfId(String appPdfId) {
		this.appPdfId = appPdfId;
	}





	public List<ApplicantLanguagesKnown> getLanguages() {
		return languages;
	}





	public void setLanguages(List<ApplicantLanguagesKnown> languages) {
		this.languages = languages;
	}





	public List<ApplicantFamilyInfo> getFamilyInfo() {
		return familyInfo;
	}





	public void setFamilyInfo(List<ApplicantFamilyInfo> familyInfo) {
		this.familyInfo = familyInfo;
	}





	public List<ApplicantEducationalInfo> getEducationalInfo() {
		return educationalInfo;
	}





	public void setEducationalInfo(List<ApplicantEducationalInfo> educationalInfo) {
		this.educationalInfo = educationalInfo;
	}





	public List<ApplicantAcademicAcheiv> getAcademicAcheiv() {
		return academicAcheiv;
	}





	public void setAcademicAcheiv(List<ApplicantAcademicAcheiv> academicAcheiv) {
		this.academicAcheiv = academicAcheiv;
	}





	public List<ApplicantPreviousEmploymentDetails> getPreviousEmployDetails() {
		return previousEmployDetails;
	}





	public void setPreviousEmployDetails(List<ApplicantPreviousEmploymentDetails> previousEmployDetails) {
		this.previousEmployDetails = previousEmployDetails;
	}




}



	

