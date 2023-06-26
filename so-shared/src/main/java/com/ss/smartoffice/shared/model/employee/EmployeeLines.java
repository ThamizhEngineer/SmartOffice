package com.ss.smartoffice.shared.model.employee;

import java.util.List;

import com.ss.smartoffice.shared.common.BaseEntity;

public class EmployeeLines extends BaseEntity{
	
	private List<LanguagesKnown> languages;
	private List<FamilyInfo> familyInfo;
	
	private List<EducationalInfo> educationalInfo;
	private List<AcademicAcheiv> academicAcheiv;
	
	private List<PreviousEmploymentDetails> previousEmployDetails;
	private List<BankDetails> bankDetails;
	public EmployeeLines() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeLines(List<LanguagesKnown> languages, List<FamilyInfo> familyInfo,
			List<EducationalInfo> educationalInfo, List<AcademicAcheiv> academicAcheiv,
			List<PreviousEmploymentDetails> previousEmployDetails, List<BankDetails> bankDetails) {
		super();
		this.languages = languages;
		this.familyInfo = familyInfo;
		this.educationalInfo = educationalInfo;
		this.academicAcheiv = academicAcheiv;
		this.previousEmployDetails = previousEmployDetails;
		this.bankDetails = bankDetails;
	}
	public List<LanguagesKnown> getLanguages() {
		return languages;
	}
	public void setLanguages(List<LanguagesKnown> languages) {
		this.languages = languages;
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
	public List<BankDetails> getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(List<BankDetails> bankDetails) {
		this.bankDetails = bankDetails;
	}
	@Override
	public String toString() {
		return "EmployeeLines [languages=" + languages + ", familyInfo=" + familyInfo + ", educationalInfo="
				+ educationalInfo + ", academicAcheiv=" + academicAcheiv + ", previousEmployDetails="
				+ previousEmployDetails + ", bankDetails=" + bankDetails + "]";
	}
	

}
