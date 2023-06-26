package com.ss.smartoffice.soauthservice.model.master;


	import java.time.LocalDate;
	import java.time.LocalDateTime;
	import java.time.LocalTime;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;
	import org.hibernate.annotations.CreationTimestamp;
	import org.hibernate.annotations.Formula;
	import org.hibernate.annotations.UpdateTimestamp;
	import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
	@Entity
	@Table(name="t_attendance")
	
	@Component
	public class AttendanceAuth extends BaseEntity{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private String employeeId;
		@Formula("(select m.emp_name from m_employee m where m.id=employee_id)")
		private String employeeName;
		@JsonFormat(pattern="yyyy-MM-dd")
		private LocalDate attendanceDt;
		private Integer attendanceMonth;
		private String didSystemCheckOut;
		private Integer attendanceYear;
		private Integer totalTimeLogged;
		private String firstSession;
		@Formula("(select c.config_dtl_name from t_attendance a \n" + 
				"JOIN s_config_dtl c on a.first_session=c.config_dtl_code where a.id=id)")
		private String firstSessionCodeDesription;
		private String secondSession;
		@Formula("(select c.config_dtl_name from t_attendance a \n" + 
				"JOIN s_config_dtl c on a.second_session=c.config_dtl_code where a.id=id)")
		private String secondSessionCodeDesription;
		private String attendanceStatus;
		private Integer proxyEmployeeId;
		@Formula("(select emp.manager_id  from m_employee emp where emp.id=employee_id)")
		private String managerId;
		@Formula("(select emp.hr_1_id  from m_employee emp where emp.id=employee_id)")
		private String hr1Id;
		@Formula("(select emp.hr_2_id  from m_employee emp where emp.id=employee_id)")
		private String hr2Id;
		
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
		@JsonFormat(pattern=" HH:mm:ss")
		private LocalTime checkIn;
		@JsonFormat(pattern=" HH:mm:ss")
		private LocalTime checkOut;
		private String isEnabled;
		private String createdBy;
		private String modifiedBy;
		@CreationTimestamp
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createdDt;
		@UpdateTimestamp
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifiedDt;
		public AttendanceAuth() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
		
		public AttendanceAuth(int id, String employeeId, String employeeName, LocalDate attendanceDt,
				Integer attendanceMonth, String didSystemCheckOut, Integer attendanceYear, Integer totalTimeLogged,
				String firstSession, String firstSessionCodeDesription, String secondSession,
				String secondSessionCodeDesription, String attendanceStatus, Integer proxyEmployeeId, String managerId,
				String hr1Id, String hr2Id, String inLats, String inLongs, String outLats, String outLongs,
				String remarks, LocalDateTime startDt, String inLocName, String outLocName, LocalDateTime endDt,
				LocalTime checkIn, LocalTime checkOut, String isEnabled, String createdBy, String modifiedBy,
				LocalDateTime createdDt, LocalDateTime modifiedDt) {
			super();
			this.id = id;
			this.employeeId = employeeId;
			this.employeeName = employeeName;
			this.attendanceDt = attendanceDt;
			this.attendanceMonth = attendanceMonth;
			this.didSystemCheckOut = didSystemCheckOut;
			this.attendanceYear = attendanceYear;
			this.totalTimeLogged = totalTimeLogged;
			this.firstSession = firstSession;
			this.firstSessionCodeDesription = firstSessionCodeDesription;
			this.secondSession = secondSession;
			this.secondSessionCodeDesription = secondSessionCodeDesription;
			this.attendanceStatus = attendanceStatus;
			this.proxyEmployeeId = proxyEmployeeId;
			this.managerId = managerId;
			this.hr1Id = hr1Id;
			this.hr2Id = hr2Id;
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
			this.createdDt = createdDt;
			this.modifiedDt = modifiedDt;
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




		public String getDidSystemCheckOut() {
			return didSystemCheckOut;
		}




		public void setDidSystemCheckOut(String didSystemCheckOut) {
			this.didSystemCheckOut = didSystemCheckOut;
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




		public String getManagerId() {
			return managerId;
		}




		public void setManagerId(String managerId) {
			this.managerId = managerId;
		}




		public String getHr1Id() {
			return hr1Id;
		}




		public void setHr1Id(String hr1Id) {
			this.hr1Id = hr1Id;
		}




		public String getHr2Id() {
			return hr2Id;
		}




		public void setHr2Id(String hr2Id) {
			this.hr2Id = hr2Id;
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




		@Override
		public String toString() {
			return "AttendanceAuth [id=" + id + ", employeeId=" + employeeId + ", employeeName=" + employeeName
					+ ", attendanceDt=" + attendanceDt + ", attendanceMonth=" + attendanceMonth + ", didSystemCheckOut="
					+ didSystemCheckOut + ", attendanceYear=" + attendanceYear + ", totalTimeLogged=" + totalTimeLogged
					+ ", firstSession=" + firstSession + ", firstSessionCodeDesription=" + firstSessionCodeDesription
					+ ", secondSession=" + secondSession + ", secondSessionCodeDesription="
					+ secondSessionCodeDesription + ", attendanceStatus=" + attendanceStatus + ", proxyEmployeeId="
					+ proxyEmployeeId + ", managerId=" + managerId + ", hr1Id=" + hr1Id + ", hr2Id=" + hr2Id
					+ ", inLats=" + inLats + ", inLongs=" + inLongs + ", outLats=" + outLats + ", outLongs=" + outLongs
					+ ", remarks=" + remarks + ", startDt=" + startDt + ", inLocName=" + inLocName + ", outLocName="
					+ outLocName + ", endDt=" + endDt + ", checkIn=" + checkIn + ", checkOut=" + checkOut
					+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
					+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
		}




		
		
		
}
