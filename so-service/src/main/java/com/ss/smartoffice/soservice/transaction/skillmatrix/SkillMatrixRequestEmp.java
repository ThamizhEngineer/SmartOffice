package com.ss.smartoffice.soservice.transaction.skillmatrix;

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

@Table(name = "t_skill_matrix_req_emp")
public class SkillMatrixRequestEmp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String skillMatrixHdrKey;
	@Column(name = "employee_id")
	private String employeeId;
	@Formula("(select x.dept_id from m_employee x where x.id=employee_id)")
	private String departmentId;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Formula("(select me.emp_name from m_employee me where me.id = employee_id)")
	private String employeeName;
	@Formula("(select me.designation_id from m_employee me where me.id = employee_id)")
	private String designationId;
	@Formula("(select me.desig_name from m_employee me where me.id = employee_id)")
	private String designationName;
	@Formula("(select me.n1_emp_id from m_employee me where me.id = employee_id)")
	private String n1EmpId;
	@Formula("(select me.n2_emp_id from m_employee me where me.id = employee_id)")
	private String n2EmpId;
	@Formula("(select de.dept_name from m_employee xe join m_dept de on xe.dept_id = de.id where xe.id = employee_id)")
	private String deptName;
	@Formula("(select de.dept_code from m_employee xe join m_dept de on xe.dept_id=de.id where xe.id = employee_id)")
	private String deptCode;

	public SkillMatrixRequestEmp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillMatrixRequestEmp(Integer id, String skillMatrixHdrKey, String employeeId, String departmentId,
			String isEnabled, String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt,
			String employeeName, String designationId, String designationName, String n1EmpId, String n2EmpId,
			String deptName, String deptCode) {
		super();
		this.id = id;
		this.skillMatrixHdrKey = skillMatrixHdrKey;
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.employeeName = employeeName;
		this.designationId = designationId;
		this.designationName = designationName;
		this.n1EmpId = n1EmpId;
		this.n2EmpId = n2EmpId;
		this.deptName = deptName;
		this.deptCode = deptCode;
	}

	@Override
	public String toString() {
		return "SkillMatrixRequestEmp [id=" + id + ", skillMatrixHdrKey=" + skillMatrixHdrKey + ", employeeId="
				+ employeeId + ", departmentId=" + departmentId + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", employeeName=" + employeeName + ", designationId=" + designationId + ", designationName="
				+ designationName + ", n1EmpId=" + n1EmpId + ", n2EmpId=" + n2EmpId + ", deptName=" + deptName
				+ ", deptCode=" + deptCode + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkillMatrixHdrKey() {
		return skillMatrixHdrKey;
	}

	public void setSkillMatrixHdrKey(String skillMatrixHdrKey) {
		this.skillMatrixHdrKey = skillMatrixHdrKey;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getN1EmpId() {
		return n1EmpId;
	}

	public void setN1EmpId(String n1EmpId) {
		this.n1EmpId = n1EmpId;
	}

	public String getN2EmpId() {
		return n2EmpId;
	}

	public void setN2EmpId(String n2EmpId) {
		this.n2EmpId = n2EmpId;
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

	

	
}
