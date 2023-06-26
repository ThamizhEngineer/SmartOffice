package com.ss.smartoffice.soservice.transaction.skillmatrix;

public class EmployeeReqInput {

	private String employeeId;
	private String deptId;

	public EmployeeReqInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeReqInput(String employeeId, String deptId) {
		super();
		this.employeeId = employeeId;
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "EmployeeReqInput [employeeId=" + employeeId + ", deptId=" + deptId + "]";
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}
