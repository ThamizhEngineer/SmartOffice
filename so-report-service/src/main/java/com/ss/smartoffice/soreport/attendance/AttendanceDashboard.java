package com.ss.smartoffice.soreport.attendance;

import org.hibernate.annotations.Formula;

public class AttendanceDashboard {

	@Formula("(SELECT count(me.id) from m_employee me where me.contractor_code is NULL)")
	private String totalUsr;
	private String present;
	private String absent;
	private String leave;
	private String checkIn;
	private String checkOut;
	private String yetToCheckIn;
	public AttendanceDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public AttendanceDashboard(String totalUsr, String present, String absent, String leave, String checkIn,
			String checkOut, String yetToCheckIn) {
		super();
		this.totalUsr = totalUsr;
		this.present = present;
		this.absent = absent;
		this.leave = leave;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.yetToCheckIn = yetToCheckIn;
	}

	public String getTotalUsr() {
		return totalUsr;
	}
	public void setTotalUsr(String totalUsr) {
		this.totalUsr = totalUsr;
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
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public String getYetToCheckIn() {
		return yetToCheckIn;
	}
	public void setYetToCheckIn(String yetToCheckIn) {
		this.yetToCheckIn = yetToCheckIn;
	}
	@Override
	public String toString() {
		return "AttendanceDashboard [totalUsr=" + totalUsr + ", present=" + present + ", absent=" + absent + ", leave="
				+ leave + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", yetToCheckIn=" + yetToCheckIn + "]";
	}
	
	
	
}
