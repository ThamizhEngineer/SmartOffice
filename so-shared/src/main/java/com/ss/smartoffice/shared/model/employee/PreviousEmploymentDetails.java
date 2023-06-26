package com.ss.smartoffice.shared.model.employee;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="m_emp_prev_employ_details")

@Scope("prototype")
public class PreviousEmploymentDetails extends BaseEntity{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@Column(name="m_employee_id")
private Integer employeeId;
private String orgName;
private String orgPosition;
@JsonFormat(pattern="yyyy-MM-dd")
private LocalDate fromDt;
@JsonFormat(pattern="yyyy-MM-dd")
private LocalDate toDt;
private String jobDesc;
private String isEnabled;
private String createdBy;
private String modifiedBy;
@CreationTimestamp
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
private LocalDateTime createdDt;
@UpdateTimestamp
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
private LocalDateTime modifiedDt;
public PreviousEmploymentDetails() {
	super();
	// TODO Auto-generated constructor stub
}
public PreviousEmploymentDetails(Integer id, Integer employeeId, String orgName, String orgPosition, LocalDate fromDt,
		LocalDate toDt, String jobDesc, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
		LocalDateTime modifiedDt) {
	super();
	this.id = id;
	this.employeeId = employeeId;
	this.orgName = orgName;
	this.orgPosition = orgPosition;
	this.fromDt = fromDt;
	this.toDt = toDt;
	this.jobDesc = jobDesc;
	this.isEnabled = isEnabled;
	this.createdBy = createdBy;
	this.modifiedBy = modifiedBy;
	this.createdDt = createdDt;
	this.modifiedDt = modifiedDt;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(Integer employeeId) {
	this.employeeId = employeeId;
}
public String getOrgName() {
	return orgName;
}
public void setOrgName(String orgName) {
	this.orgName = orgName;
}
public String getOrgPosition() {
	return orgPosition;
}
public void setOrgPosition(String orgPosition) {
	this.orgPosition = orgPosition;
}
public LocalDate getFromDt() {
	return fromDt;
}
public void setFromDt(LocalDate fromDt) {
	this.fromDt = fromDt;
}
public LocalDate getToDt() {
	return toDt;
}
public void setToDt(LocalDate toDt) {
	this.toDt = toDt;
}
public String getJobDesc() {
	return jobDesc;
}
public void setJobDesc(String jobDesc) {
	this.jobDesc = jobDesc;
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
	return "PreviousEmploymentDetails [id=" + id + ", employeeId=" + employeeId + ", orgName=" + orgName
			+ ", orgPosition=" + orgPosition + ", fromDt=" + fromDt + ", toDt=" + toDt + ", jobDesc=" + jobDesc
			+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt="
			+ createdDt + ", modifiedDt=" + modifiedDt + "]";
}

}
