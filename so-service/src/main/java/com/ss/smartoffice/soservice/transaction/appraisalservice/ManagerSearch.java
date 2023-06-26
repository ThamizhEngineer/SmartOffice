package com.ss.smartoffice.soservice.transaction.appraisalservice;

import javax.persistence.Column;

import org.hibernate.annotations.Formula;

public class ManagerSearch {
@Column(name="m_emp_id")
private String empId;
@Formula("(select m.emp_name from m_employee m where m.id=m_emp_id)")
private String empName;
@Column(name="fy_id")
private String fyId;
@Formula("(select fyYear.fiscal_code from s_fiscal_year fyYear WHERE fyYear.id=fy_id)")
private String fyCode;
public ManagerSearch() {
	super();
	// TODO Auto-generated constructor stub
}

public ManagerSearch(String empId, String fyId) {
	super();
	this.empId = empId;
	this.fyId = fyId;
}

public ManagerSearch(String empId, String empName, String fyId, String fyCode) {
	super();
	this.empId = empId;
	this.empName = empName;
	this.fyId = fyId;
	this.fyCode = fyCode;
}
public String getEmpId() {
	return empId;
}
public void setEmpId(String empId) {
	this.empId = empId;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public String getFyId() {
	return fyId;
}
public void setFyId(String fyId) {
	this.fyId = fyId;
}
public String getFyCode() {
	return fyCode;
}
public void setFyCode(String fyCode) {
	this.fyCode = fyCode;
}
@Override
public String toString() {
	return "ManagerSearch [empId=" + empId + ", empName=" + empName + ", fyId=" + fyId + ", fyCode=" + fyCode + "]";
}

}
