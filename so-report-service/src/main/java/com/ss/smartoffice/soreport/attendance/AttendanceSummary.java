package com.ss.smartoffice.soreport.attendance;

public class AttendanceSummary {

	private String empCode;
	private String empName;
	private String totalCalendarDays;
	private String totalWorkingDays;
	private String present;
	private String absent;
	private String leave;
	
	public AttendanceSummary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttendanceSummary(String empCode, String empName, String totalCalendarDays, String totalWorkingDays,
			String present, String absent, String leave) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.totalCalendarDays = totalCalendarDays;
		this.totalWorkingDays = totalWorkingDays;
		this.present = present;
		this.absent = absent;
		this.leave = leave;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTotalCalendarDays() {
		return totalCalendarDays;
	}

	public void setTotalCalendarDays(String totalCalendarDays) {
		this.totalCalendarDays = totalCalendarDays;
	}

	public String getTotalWorkingDays() {
		return totalWorkingDays;
	}

	public void setTotalWorkingDays(String totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public String getAbsent() {
		return absent;
	}

	public void setAbsent(String absent) {
		this.absent = absent;
	}

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	@Override
	public String toString() {
		return "AttendanceSummary [empCode=" + empCode + ", empName=" + empName + ", totalCalendarDays="
				+ totalCalendarDays + ", totalWorkingDays=" + totalWorkingDays + ", present=" + present + ", absent="
				+ absent + ", leave=" + leave + "]";
	}
}
