package com.ss.smartoffice.soservice.master.costService;

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
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_cost_center")
@Scope("prototype")
public class CostCenter {
	
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

private String ccCode;

private String ccName;
@Column(name="dept_id")
private int deptId;
@Formula("(select dept.dept_name from m_dept dept where dept.id=dept_id)")
private String deptName;

private int managerId;
@Formula("(select emp.emp_name from m_employee emp where emp.id=manager_id )")
private String managerName;

@Column(name="manager_2_id")
private int manager2Id;
@Formula("(select emp.emp_name from m_employee emp where emp.id=manager_2_id )")
private String manager2Name;

private String remarks;

private String createdBy;

private String modifiedBy;
private String isEnabled;
@CreationTimestamp
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
private LocalDateTime createdDt;

@UpdateTimestamp
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
private LocalDateTime modifiedDt;

public CostCenter() {
	super();
	// TODO Auto-generated constructor stub
}

public CostCenter(int id, String ccCode, String ccName, int deptId, String deptName, int managerId, String managerName,
		int manager2Id, String manager2Name, String remarks, String createdBy, String modifiedBy, String isEnabled,
		LocalDateTime createdDt, LocalDateTime modifiedDt) {
	super();
	this.id = id;
	this.ccCode = ccCode;
	this.ccName = ccName;
	this.deptId = deptId;
	this.deptName = deptName;
	this.managerId = managerId;
	this.managerName = managerName;
	this.manager2Id = manager2Id;
	this.manager2Name = manager2Name;
	this.remarks = remarks;
	this.createdBy = createdBy;
	this.modifiedBy = modifiedBy;
	this.isEnabled = isEnabled;
	this.createdDt = createdDt;
	this.modifiedDt = modifiedDt;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCcCode() {
	return ccCode;
}

public void setCcCode(String ccCode) {
	this.ccCode = ccCode;
}

public String getCcName() {
	return ccName;
}

public void setCcName(String ccName) {
	this.ccName = ccName;
}

public int getDeptId() {
	return deptId;
}

public void setDeptId(int deptId) {
	this.deptId = deptId;
}

public String getDeptName() {
	return deptName;
}

public void setDeptName(String deptName) {
	this.deptName = deptName;
}

public int getManagerId() {
	return managerId;
}

public void setManagerId(int managerId) {
	this.managerId = managerId;
}

public String getManagerName() {
	return managerName;
}

public void setManagerName(String managerName) {
	this.managerName = managerName;
}

public int getManager2Id() {
	return manager2Id;
}

public void setManager2Id(int manager2Id) {
	this.manager2Id = manager2Id;
}

public String getManager2Name() {
	return manager2Name;
}

public void setManager2Name(String manager2Name) {
	this.manager2Name = manager2Name;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
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

public LocalDateTime getModifiedDt() {
	return modifiedDt;
}

public void setModifiedDt(LocalDateTime modifiedDt) {
	this.modifiedDt = modifiedDt;
}

@Override
public String toString() {
	return "CostCenter [id=" + id + ", ccCode=" + ccCode + ", ccName=" + ccName + ", deptId=" + deptId + ", deptName="
			+ deptName + ", managerId=" + managerId + ", managerName=" + managerName + ", manager2Id=" + manager2Id
			+ ", manager2Name=" + manager2Name + ", remarks=" + remarks + ", createdBy=" + createdBy + ", modifiedBy="
			+ modifiedBy + ", isEnabled=" + isEnabled + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
}




}
