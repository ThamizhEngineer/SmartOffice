package com.ss.smartoffice.soreport.attendance;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.attendance.Attendance;

public interface AttendanceReportRepository extends CrudRepository<com.ss.smartoffice.shared.model.attendance.Attendance, Integer>  {

	
//	@Query("SELECT ta.empCode,ta.employeeName,ta.weekdays,ta.weekdays ,COUNT(case ta.attendanceStatus when 'WBL' then 1 when 'WNB' then 1 when 'IDL' then 1 else null end) as present, "
//			+ "COUNT(case ta.attendanceStatus when 'ABS' then 1 else null end) as absent,"
//			+ "COUNT(case ta.attendanceStatus when 'SL' then 1 else null end) as leaves "
//			+ "from  com.ss.smartoffice.shared.model.attendance.Attendance ta "
//			+ "where ta.attendanceMonth = 09 AND ta.attendanceYear =2020 GROUP By ta.employeeId")
//	List<AttendanceSummary> findBySummary();
	@Query("SELECT ta from  com.ss.smartoffice.shared.model.attendance.Attendance ta where ta.attendanceMonth =:month AND ta.attendanceYear =:year AND ta.empCode=:empCode ORDER BY ta.attendanceDt")
	List<com.ss.smartoffice.shared.model.attendance.Attendance> findByAttendanceMonthAndAttendanceYearAndEmpCode(Integer month,Integer year,String empCode);
	
}