package com.ss.smartoffice.soservice.transaction.attendance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity

@Table(name = "i_attendance_line")
public class IntAttendanceLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String attendanceHdrId;
	private String empId;
	private String empCode;
	private String shiftId;
	private String shiftCode;
	private String  year;
	private String month;
	private String date;
	private String attendaceStatus;
	private String firstSession;
	private String secoundSession;
	private String checkIn;
	private String checkOut;
	private String inLat;
	private String inLong;
	private String outLat;
	private String outLong;
	private String remarks;
	private String proxyEmpId;
	private String proxyEmpCode;
	private String docId;
	public IntAttendanceLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IntAttendanceLine(Integer id, String attendanceHdrId, String empId, String empCode, String shiftId,
			String shiftCode, String year, String month, String date, String attendaceStatus, String firstSession,
			String secoundSession, String checkIn, String checkOut, String inLat, String inLong, String outLat,
			String outLong, String remarks, String proxyEmpId, String proxyEmpCode, String docId) {
		super();
		this.id = id;
		this.attendanceHdrId = attendanceHdrId;
		this.empId = empId;
		this.empCode = empCode;
		this.shiftId = shiftId;
		this.shiftCode = shiftCode;
		this.year = year;
		this.month = month;
		this.date = date;
		this.attendaceStatus = attendaceStatus;
		this.firstSession = firstSession;
		this.secoundSession = secoundSession;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.inLat = inLat;
		this.inLong = inLong;
		this.outLat = outLat;
		this.outLong = outLong;
		this.remarks = remarks;
		this.proxyEmpId = proxyEmpId;
		this.proxyEmpCode = proxyEmpCode;
		this.docId = docId;
	}

	@Override
	public String toString() {
		return "IntAttendanceLine [id=" + id + ", attendanceHdrId=" + attendanceHdrId + ", empId=" + empId
				+ ", empCode=" + empCode + ", shiftId=" + shiftId + ", shiftCode=" + shiftCode + ", year=" + year
				+ ", month=" + month + ", date=" + date + ", attendaceStatus=" + attendaceStatus + ", firstSession="
				+ firstSession + ", secoundSession=" + secoundSession + ", checkIn=" + checkIn + ", checkOut="
				+ checkOut + ", inLat=" + inLat + ", inLong=" + inLong + ", outLat=" + outLat + ", outLong=" + outLong
				+ ", remarks=" + remarks + ", proxyEmpId=" + proxyEmpId + ", proxyEmpCode=" + proxyEmpCode + ", docId="
				+ docId + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAttendanceHdrId() {
		return attendanceHdrId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getProxyEmpId() {
		return proxyEmpId;
	}

	public void setProxyEmpId(String proxyEmpId) {
		this.proxyEmpId = proxyEmpId;
	}

	public void setAttendanceHdrId(String attendanceHdrId) {
		this.attendanceHdrId = attendanceHdrId;
	}

	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAttendaceStatus() {
		return attendaceStatus;
	}
	public void setAttendaceStatus(String attendaceStatus) {
		this.attendaceStatus = attendaceStatus;
	}
	public String getFirstSession() {
		return firstSession;
	}
	public void setFirstSession(String firstSession) {
		this.firstSession = firstSession;
	}
	public String getSecoundSession() {
		return secoundSession;
	}
	public void setSecoundSession(String secoundSession) {
		this.secoundSession = secoundSession;
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
	public String getInLat() {
		return inLat;
	}
	public void setInLat(String inLat) {
		this.inLat = inLat;
	}
	public String getInLong() {
		return inLong;
	}
	public void setInLong(String inLong) {
		this.inLong = inLong;
	}
	public String getOutLat() {
		return outLat;
	}
	public void setOutLat(String outLat) {
		this.outLat = outLat;
	}
	public String getOutLong() {
		return outLong;
	}
	public void setOutLong(String outLong) {
		this.outLong = outLong;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getProxyEmpCode() {
		return proxyEmpCode;
	}
	public void setProxyEmpCode(String proxyEmpCode) {
		this.proxyEmpCode = proxyEmpCode;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	
}
