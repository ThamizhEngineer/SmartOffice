package com.ss.smartoffice.shared.model.attendance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

@Scope("prototype")
public interface AttendanceRepository extends CrudRepository<Attendance, Integer>{ 
	@Query("select a from com.ss.smartoffice.shared.model.attendance.Attendance a where a.attendanceMonth=:attendanceMonth AND a.attendanceYear=:attendanceYear AND a.employeeId=:employeeId ORDER BY a.attendanceDt")
	List<Attendance> findByAttendanceMonthAndAttendanceYearAndEmployeeId(int attendanceMonth,int attendanceYear,String employeeId);
	List<Attendance> findByAttendanceMonthAndAttendanceYearAndN1EmpId(int attendanceMonth,int attendanceYear,String n1EmpId);
	List<Attendance> findByAttendanceMonthAndAttendanceYear(int attendanceMonth,int attendanceYear);
	List<Attendance> findByEmployeeId(String employeeId); 
	List<Attendance> findByAttendanceDt(LocalDate attendanceDt);
	List<Attendance> findByEmployeeIdAndAttendanceDt(String employeeId,LocalDate attendanceDt);
	List<Attendance> findByN1EmpIdAndAttendanceDt(String n1EmpId,LocalDate attendanceDt);
	List<Attendance> findByAttendanceDtAndHr1GrpIdAndHr2GrpId(LocalDate attendanceDt,String hr1GrpId,String hr2GrpId);
	List<Attendance> findByAttendanceDtAndEmployeeId(LocalDate attendanceDt,String employeeId);
	Iterable<Attendance> findByAttendanceMonthAndAttendanceYearAndEmployeeId(String attendanceMonth,String attendanceYear, String employeeId);
	
//	@Query("select new com.ss.smartoffice.soservice.transaction.model.Attendance(a.id,a.employeeId,a.employeeName,a.attendanceDt,a.attendanceMonth,a.attendanceYear,a.totalTimeLogged,a.firstSession,a.firstSessionCodeDesription,a.secondSession,a.secondSessionCodeDesription,a.attendanceStatus,a.proxyEmployeeId,a.managerId,a.hrManagerId,a.inLats,a.inLongs,a.outLats,a.outLongs,a.remarks,a.startDt,a.inLocName,a.outLocName,a.endDt,a.checkIn,a.checkOut,a.isEnabled,a.createdBy,a.modifiedBy,a.createdDt,a.modifiedDt)\n" +
//		       "from Attendance a where ifnull(LOWER(a.attendanceMonth),'') LIKE LOWER(CONCAT('%',ifnull(:attendanceMonth,''),'%'))\n" +
//		       "AND ifnull(LOWER(a.attendanceYear),'') LIKE LOWER(CONCAT('%',ifnull(:attendanceYear,''), '%'))"
//		       + "AND ifnull(LOWER(a.employeeId),'') LIKE LOWER(CONCAT('%',ifnull(:employeeId,''), '%'))")
	
	
	@Query("select a from com.ss.smartoffice.shared.model.attendance.Attendance a" +
			" where ifnull(LOWER(a.attendanceMonth),'') LIKE LOWER(CONCAT('%',ifnull(:attendanceMonth,''),'%'))\n" +
		       "AND ifnull(LOWER(a.attendanceYear),'') LIKE LOWER(CONCAT('%',ifnull(:attendanceYear,''), '%'))"
             + "AND a.employeeId=:employeeId")

	Iterable<Attendance> getMyAttendances(@Param("attendanceMonth")String attendanceMonth,@Param ("attendanceYear")String attendanceYear, @Param("employeeId")String employeeId);
	
	@Query("select a from com.ss.smartoffice.shared.model.attendance.Attendance a" +
			" where ifnull(LOWER(a.attendanceMonth),'') LIKE LOWER(CONCAT('%',ifnull(:attendanceMonth,''),'%'))\n" +
		       "AND ifnull(LOWER(a.attendanceYear),'') LIKE LOWER(CONCAT('%',ifnull(:attendanceYear,''), '%'))"
		       +"AND ifnull(LOWER(a.n1EmpId),'') LIKE LOWER(CONCAT('%',ifnull(:n1EmpId,''), '%'))"
              + "AND a.employeeId=:employeeId")
	Iterable<Attendance> getAttendances(String attendanceMonth, String attendanceYear, String n1EmpId,
			String employeeId);
//	Iterable<Attendance> getAttendances(String attendanceMonth, String attendanceYear, String managerId); 
	
	
//	@Query("select a from com.ss.smartoffice.soservice.transaction.model.Attendance a where a.employeeId=:employeeId or a.n1EmpId=:n1EmpId or a.attendanceDt between :startDate and :endDate")
//	Iterable<Attendance> fetchByAdvance(
//			@Param("employeeId")String employeeId,@Param("n1EmpId")String n1EmpId,
//			@Param("startDate")LocalDate startDate,
//			@Param("endDate")LocalDate endDate);

	@Query("select i from com.ss.smartoffice.shared.model.attendance.Attendance i "
			+ "where ifnull(LOWER(i.employeeId),'') LIKE LOWER(CONCAT('%',ifnull(:employeeId,''), '%')) AND ifnull(LOWER(i.n1EmpId),'') LIKE LOWER(CONCAT('%',ifnull(:n1EmpId,''), '%'))  "
			+ "AND i.attendanceDt BETWEEN :startDate AND :endDate")
	Iterable<Attendance> fetchByAdvance(
	@Param("employeeId")String employeeId,@Param("n1EmpId")String n1EmpId,
	@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);
	
	
	

	
}
