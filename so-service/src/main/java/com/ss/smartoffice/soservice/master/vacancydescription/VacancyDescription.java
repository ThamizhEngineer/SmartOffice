package com.ss.smartoffice.soservice.master.vacancydescription;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity

@Table(name="m_vacancy_description")
public class VacancyDescription {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String vcCode;
	private String summary;
	private String yearsOfExp;
	private String designation;
	private String location;
	private String salary;	
	private String role;
	private String responsibility;
	private String qualification;
	private String experience;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_vacancy_desc_id")
	private List<VacancyDescriptionSkill> vacancyDescriptionSkills;
	public VacancyDescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VacancyDescription(Integer id, String vcCode, String summary, String yearsOfExp, String designation,
			String location, String salary, String role, String responsibility, String qualification, String experience,
			String isEnabled, String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt,
			List<VacancyDescriptionSkill> vacancyDescriptionSkills) {
		super();
		this.id = id;
		this.vcCode = vcCode;
		this.summary = summary;
		this.yearsOfExp = yearsOfExp;
		this.designation = designation;
		this.location = location;
		this.salary = salary;
		this.role = role;
		this.responsibility = responsibility;
		this.qualification = qualification;
		this.experience = experience;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.vacancyDescriptionSkills = vacancyDescriptionSkills;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVcCode() {
		return vcCode;
	}
	public void setVcCode(String vcCode) {
		this.vcCode = vcCode;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getYearsOfExp() {
		return yearsOfExp;
	}
	public void setYearsOfExp(String yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
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
	public List<VacancyDescriptionSkill> getVacancyDescriptionSkills() {
		return vacancyDescriptionSkills;
	}
	public void setVacancyDescriptionSkills(List<VacancyDescriptionSkill> vacancyDescriptionSkills) {
		this.vacancyDescriptionSkills = vacancyDescriptionSkills;
	}
	@Override
	public String toString() {
		return "VacancyDescription [id=" + id + ", vcCode=" + vcCode + ", summary=" + summary + ", yearsOfExp="
				+ yearsOfExp + ", designation=" + designation + ", location=" + location + ", salary=" + salary
				+ ", role=" + role + ", responsibility=" + responsibility + ", qualification=" + qualification
				+ ", experience=" + experience + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", vacancyDescriptionSkills=" + vacancyDescriptionSkills + "]";
	}
	
	}
