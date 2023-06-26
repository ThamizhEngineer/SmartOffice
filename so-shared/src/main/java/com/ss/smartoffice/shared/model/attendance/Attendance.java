package com.ss.smartoffice.shared.model.attendance;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

import lombok.Data;
@Entity
@Table(name="t_attendance")

@Component
@Scope("prototype")
public class Attendance extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String employeeId;
	@Formula("(select m.emp_name from m_employee m where m.id=employee_id)")
	private String employeeName;
	@Formula("(select emp.emp_code from m_employee emp where emp.id=employee_id)")
	private String empCode;
	@Formula("(select emp.first_name from m_employee emp where emp.id=employee_id)")
	private String firstName ;
	@Formula("(select emp.last_name from m_employee emp where emp.id=employee_id)")
	private String lastName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate attendanceDt;
	@Formula("(SELECT DATE_FORMAT(atten.attendance_dt, '%W') from t_attendance atten where atten.id=id)")
	private String day;
	private Integer attendanceMonth;
	private Integer attendanceYear;
	private Integer totalTimeLogged;
	private String firstSession;
	@Formula("(select c.config_dtl_name from t_attendance a \n" + 
			"JOIN s_config_dtl c on a.first_session=c.config_dtl_value where a.id=id)")
	private String firstSessionCodeDesription;
	private String secondSession;
	@Formula("(select c.config_dtl_name from t_attendance a \n" + 
			"JOIN s_config_dtl c on a.second_session=c.config_dtl_value where a.id=id)")
	private String secondSessionCodeDesription;
	private String attendanceStatus;
	private String didSystemCheckOut;
	private Integer proxyEmployeeId;
	@Formula("(select emp.n1_emp_id  from m_employee emp where emp.id=employee_id)")
	private String n1EmpId;
	@Formula("(select emp.emp_name from m_employee emp left join m_employee emp1 on emp.id=emp1.n1_emp_id where emp1.id=employee_id)")
	private String managerName;
	@Formula("(select emp.emp_code from m_employee emp left join m_employee emp1 on emp.id=emp1.n1_emp_id where emp1.id=employee_id)")
	private  String n1EmpCode;
	@Formula("(select emp.first_name from m_employee emp left join m_employee emp1 on emp.id=emp1.n1_emp_id where emp1.id=employee_id)")
	private  String n1EmpFName;
	@Formula("(select emp.last_name from m_employee emp left join m_employee emp1 on emp.id=emp1.n1_emp_id where emp1.id=employee_id)")
	private  String n1EmpLName;
	@Formula("(select emp.n1_emp_id  from m_employee emp where emp.id=employee_id)")
	private String hr1GrpId;
	@Formula("(select emp.hr_2_usr_grp_id  from m_employee emp where emp.id=employee_id)")
	private String hr2GrpId;
	@Column(name = "office_id")
	@Formula("(select emp.m_office_id from m_employee emp where emp.id=employee_id)")
	private String officeId;
	@Formula("(select off.office_name from m_office off left join m_employee emp on off.id=emp.m_office_id where emp.id=employee_id)")
	private String officeName;
	@Transient
	private String weekdays;
	@Transient
	private String weekends;
	private String inLats;
	private String inLongs;
	private String outLats;
	private String outLongs;
	private String remarks;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDt;
	private String inLocName;
	private String outLocName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDt;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime checkIn;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime checkOut;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private String shiftId;
	@Formula("(select shift.shift_code from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftCode;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String locationId;
	
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(int id, String employeeId, String employeeName, String empCode, String firstName, String lastName,
			LocalDate attendanceDt, String day, Integer attendanceMonth, Integer attendanceYear,
			Integer totalTimeLogged, String firstSession, String firstSessionCodeDesription, String secondSession,
			String secondSessionCodeDesription, String attendanceStatus, String didSystemCheckOut,
			Integer proxyEmployeeId, String n1EmpId, String managerName, String n1EmpCode, String n1EmpFName,
			String n1EmpLName, String hr1GrpId, String hr2GrpId, String officeId, String officeName, String weekdays,
			String weekends, String inLats, String inLongs, String outLats, String outLongs, String remarks,
			LocalDateTime startDt, String inLocName, String outLocName, LocalDateTime endDt, LocalTime checkIn,
			LocalTime checkOut, String isEnabled, String createdBy, String modifiedBy, String shiftId, String shiftCode,
			LocalDateTime createdDt, LocalDateTime modifiedDt, String locationId) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.empCode = empCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.attendanceDt = attendanceDt;
		this.day = day;
		this.attendanceMonth = attendanceMonth;
		this.attendanceYear = attendanceYear;
		this.totalTimeLogged = totalTimeLogged;
		this.firstSession = firstSession;
		this.firstSessionCodeDesription = firstSessionCodeDesription;
		this.secondSession = secondSession;
		this.secondSessionCodeDesription = secondSessionCodeDesription;
		this.attendanceStatus = attendanceStatus;
		this.didSystemCheckOut = didSystemCheckOut;
		this.proxyEmployeeId = proxyEmployeeId;
		this.n1EmpId = n1EmpId;
		this.managerName = managerName;
		this.n1EmpCode = n1EmpCode;
		this.n1EmpFName = n1EmpFName;
		this.n1EmpLName = n1EmpLName;
		this.hr1GrpId = hr1GrpId;
		this.hr2GrpId = hr2GrpId;
		this.officeId = officeId;
		this.officeName = officeName;
		this.weekdays = weekdays;
		this.weekends = weekends;
		this.inLats = inLats;
		this.inLongs = inLongs;
		this.outLats = outLats;
		this.outLongs = outLongs;
		this.remarks = remarks;
		this.startDt = startDt;
		this.inLocName = inLocName;
		this.outLocName = outLocName;
		this.endDt = endDt;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.shiftId = shiftId;
		this.shiftCode = shiftCode;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.locationId = locationId;
	}


	@Override
	public String toString() {
		return "Attendance [id=" + id + ", employeeId=" + employeeId + ", employeeName=" + employeeName + ", empCode="
				+ empCode + ", firstName=" + firstName + ", lastName=" + lastName + ", attendanceDt=" + attendanceDt
				+ ", day=" + day + ", attendanceMonth=" + attendanceMonth + ", attendanceYear=" + attendanceYear
				+ ", totalTimeLogged=" + totalTimeLogged + ", firstSession=" + firstSession
				+ ", firstSessionCodeDesription=" + firstSessionCodeDesription + ", secondSession=" + secondSession
				+ ", secondSessionCodeDesription=" + secondSessionCodeDesription + ", attendanceStatus="
				+ attendanceStatus + ", didSystemCheckOut=" + didSystemCheckOut + ", proxyEmployeeId=" + proxyEmployeeId
				+ ", n1EmpId=" + n1EmpId + ", managerName=" + managerName + ", n1EmpCode=" + n1EmpCode + ", n1EmpFName="
				+ n1EmpFName + ", n1EmpLName=" + n1EmpLName + ", hr1GrpId=" + hr1GrpId + ", hr2GrpId=" + hr2GrpId
				+ ", officeId=" + officeId + ", officeName=" + officeName + ", weekdays=" + weekdays + ", weekends="
				+ weekends + ", inLats=" + inLats + ", inLongs=" + inLongs + ", outLats=" + outLats + ", outLongs="
				+ outLongs + ", remarks=" + remarks + ", startDt=" + startDt + ", inLocName=" + inLocName
				+ ", outLocName=" + outLocName + ", endDt=" + endDt + ", checkIn=" + checkIn + ", checkOut=" + checkOut
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", shiftId="
				+ shiftId + ", shiftCode=" + shiftCode + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", locationId=" + locationId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}
	
	public String getWeekends() {
		return weekends;
	}

	public void setWeekends(String weekends) {
		this.weekends = weekends;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getAttendanceDt() {
		return attendanceDt;
	}

	public void setAttendanceDt(LocalDate attendanceDt) {
		this.attendanceDt = attendanceDt;
	}

	public Integer getAttendanceMonth() {
		return attendanceMonth;
	}

	public void setAttendanceMonth(Integer attendanceMonth) {
		this.attendanceMonth = attendanceMonth;
	}

	public Integer getAttendanceYear() {
		return attendanceYear;
	}

	public void setAttendanceYear(Integer attendanceYear) {
		this.attendanceYear = attendanceYear;
	}

	public Integer getTotalTimeLogged() {
		return totalTimeLogged;
	}

	public void setTotalTimeLogged(Integer totalTimeLogged) {
		this.totalTimeLogged = totalTimeLogged;
	}

	public String getFirstSession() {
		return firstSession;
	}

	public void setFirstSession(String firstSession) {
		this.firstSession = firstSession;
	}

	public String getDidSystemCheckOut() {
		return didSystemCheckOut;
	}

	public void setDidSystemCheckOut(String didSystemCheckOut) {
		this.didSystemCheckOut = didSystemCheckOut;
	}

	public String getFirstSessionCodeDesription() {
		return firstSessionCodeDesription;
	}

	public void setFirstSessionCodeDesription(String firstSessionCodeDesription) {
		this.firstSessionCodeDesription = firstSessionCodeDesription;
	}

	public String getSecondSession() {
		return secondSession;
	}

	public void setSecondSession(String secondSession) {
		this.secondSession = secondSession;
	}

	public String getSecondSessionCodeDesription() {
		return secondSessionCodeDesription;
	}

	public void setSecondSessionCodeDesription(String secondSessionCodeDesription) {
		this.secondSessionCodeDesription = secondSessionCodeDesription;
	}
	
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public Integer getProxyEmployeeId() {
		return proxyEmployeeId;
	}

	public void setProxyEmployeeId(Integer proxyEmployeeId) {
		this.proxyEmployeeId = proxyEmployeeId;
	}

	public String getN1EmpId() {
		return n1EmpId;
	}

	public void setN1EmpId(String n1EmpId) {
		this.n1EmpId = n1EmpId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getN1EmpCode() {
		return n1EmpCode;
	}

	public void setN1EmpCode(String n1EmpCode) {
		this.n1EmpCode = n1EmpCode;
	}

	public String getN1EmpFName() {
		return n1EmpFName;
	}

	public void setN1EmpFName(String n1EmpFName) {
		this.n1EmpFName = n1EmpFName;
	}

	public String getN1EmpLName() {
		return n1EmpLName;
	}

	public void setN1EmpLName(String n1EmpLName) {
		this.n1EmpLName = n1EmpLName;
	}

	public String getHr1GrpId() {
		return hr1GrpId;
	}

	public void setHr1GrpId(String hr1GrpId) {
		this.hr1GrpId = hr1GrpId;
	}

	public String getHr2GrpId() {
		return hr2GrpId;
	}

	public void setHr2GrpId(String hr2GrpId) {
		this.hr2GrpId = hr2GrpId;
	}

	public String getInLats() {
		return inLats;
	}

	public void setInLats(String inLats) {
		this.inLats = inLats;
	}

	public String getInLongs() {
		return inLongs;
	}

	public void setInLongs(String inLongs) {
		this.inLongs = inLongs;
	}

	public String getOutLats() {
		return outLats;
	}

	public void setOutLats(String outLats) {
		this.outLats = outLats;
	}

	public String getOutLongs() {
		return outLongs;
	}

	public void setOutLongs(String outLongs) {
		this.outLongs = outLongs;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getStartDt() {
		return startDt;
	}

	public void setStartDt(LocalDateTime startDt) {
		this.startDt = startDt;
	}

	public String getInLocName() {
		return inLocName;
	}

	public void setInLocName(String inLocName) {
		this.inLocName = inLocName;
	}

	public String getOutLocName() {
		return outLocName;
	}

	public void setOutLocName(String outLocName) {
		this.outLocName = outLocName;
	}

	public LocalDateTime getEndDt() {
		return endDt;
	}

	public void setEndDt(LocalDateTime endDt) {
		this.endDt = endDt;
	}

	public LocalTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalTime checkIn) {
		this.checkIn = checkIn;
	}

	public LocalTime getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalTime checkOut) {
		this.checkOut = checkOut;
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

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	

		
}
