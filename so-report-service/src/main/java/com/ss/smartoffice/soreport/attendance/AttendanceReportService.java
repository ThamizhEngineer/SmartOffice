package com.ss.smartoffice.soreport.attendance;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.attendance.Attendance;

@Controller
@RequestMapping("/reports/attendance")
public class AttendanceReportService {

	@Autowired
	AttendanceReportRepository attendanceReportRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	CommonUtils commonUtils; 
	
	
	@GetMapping()
	public List<AttendanceSummary> findAttendanceSummary(@RequestParam(value = "month")Integer month,@RequestParam(value = "year")Integer year,@RequestParam(value = "officeId")Integer officeId) throws SmartOfficeException{
		
		String sql = "SELECT DISTINCT me.emp_code as empCode, me.emp_name as empName ,COUNT(moc.id) as totalCalDays,null as workingDays ,COUNT(case ta.attendance_status when 'WBL' then 1 when 'WNB' then 1 when 'IDL' then 1 else null end) as present,"
				+ "COUNT(case ta.attendance_status when 'ABS' then 1 else null end) as absent,"
				+ "COUNT(case ta.attendance_status when 'SL' then 1 else null end) as leaves from  t_attendance ta left join m_employee me on ta.employee_id =me.id left JOIN m_office_cal moc on DATE_FORMAT(moc.cal_date, '%Y')=ta.attendance_year AND DATE_FORMAT(moc.cal_date, '%m')=ta.attendance_month AND moc .office_id = me.m_office_id "
				+ "where ta.attendance_month = "+month+" AND moc .office_id="+officeId+" AND DATE_FORMAT(moc.cal_date, '%m')= "+month+" AND DATE_FORMAT(moc.cal_date, '%Y')="+year+"  AND ta.attendance_year ="+year+"  GROUP By ta.employee_id,moc.id,me.id";
		List<AttendanceSummary> attendanceSummary =jdbcTemplate.query(sql, (rs, rowNum) -> new AttendanceSummary(rs.getString("empCode"),rs.getString("empName"),
				rs.getString("totalCalDays"),rs.getString("workingDays"),rs.getString("present"),rs.getString("absent"),rs.getString("leaves")));
		
		String sql3 ="SELECT COUNT(case moc.day_type when 'weekday' then 1 else null end) as weekdays from m_office_cal moc where DATE_FORMAT(moc.cal_date, '%Y')="+year+" AND moc.office_id ="+officeId+" AND DATE_FORMAT(moc.cal_date, '%m')="+month+"" ;
		List<Object> count = jdbcTemplate.query(sql3, (rs, rowNum) -> new String(rs.getString("weekdays")));
		String sql2 ="SELECT COUNT(moc.id) as totalCalDay from m_office_cal moc where DATE_FORMAT(moc.cal_date, '%Y')="+year+" AND moc.office_id ="+officeId+" AND DATE_FORMAT(moc.cal_date, '%m')="+month+"" ;
		List<Object> count2 = jdbcTemplate.query(sql2, (rs2, rowNum2) -> new String(rs2.getString("totalCalDay")));
		System.out.println(count);
			
		for(AttendanceSummary atten:attendanceSummary) {
			atten.setTotalCalendarDays(count2.get(0).toString());
			atten.setTotalWorkingDays(count.get(0).toString());
		}
		return attendanceSummary;
	}
	
	@GetMapping("/by-empid/{empCode}")
	public List<com.ss.smartoffice.shared.model.attendance.Attendance> findByEmpId(@RequestParam(value = "month")Integer month,@RequestParam(value = "year")Integer year,@PathVariable(value = "empCode")String empCode,Model model)throws SmartOfficeException{
		List<com.ss.smartoffice.shared.model.attendance.Attendance> attendances=attendanceReportRepository.findByAttendanceMonthAndAttendanceYearAndEmpCode(month, year, empCode);
		Map<String, String> printAttributes = new LinkedHashMap<String,String>();
		printAttributes.put("employeeCode", "empCode");
		printAttributes.put("employeeName", "employeeName");
		printAttributes.put("firstSession", "firstSession");
		printAttributes.put("secondSession", "secondSession");
		printAttributes.put("checkIn", "checkIn");
		printAttributes.put("checkOut", "checkOut");
		printAttributes.put("totalTimeLogged", "totalTimeLogged");		
		model.addAttribute("dataKeyName", "attendanceList");
		model.addAttribute("dataType", "Attendance");
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "export-attendance");
		
		return attendances;
	}
	
	@GetMapping("/export-attendance")
	public List<AttendanceSummary> getAllOverallAttendance(@RequestParam(value = "month")Integer month,@RequestParam(value = "year")Integer year,@RequestParam(value = "officeId")Integer officeId,Model model) throws SmartOfficeException{
		List<AttendanceSummary> attendanceSummary = findAttendanceSummary(month,year,officeId);
		Map<String, String> printAttributes = new LinkedHashMap<String,String>();
		printAttributes.put("employeeCode", "empCode");
		printAttributes.put("employeeName", "empName");
		printAttributes.put("totalCalenderDay", "totalCalendarDays");
		printAttributes.put("totalWorkingDay", "totalWorkingDays");
		printAttributes.put("present", "present");
		printAttributes.put("absent", "absent");
		printAttributes.put("leave", "leave");		
		model.addAttribute("dataKeyName", "attendanceSummaryList");
		model.addAttribute("dataType", "AttendanceSummary");
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "export-attendance");
		return attendanceSummary;
	}
	
	@GetMapping("/dashboard/attendance")
	public AttendanceDashboard dashboardAttendaceDetail(@RequestParam(value = "isManager")String isManager) throws SmartOfficeException{

		String date = LocalDate.now().toString();	 
		String dashboardSql ="SELECT null as totalEmp, COUNT(CASE ta.attendance_status when 'WBL'then 1 when 'WNB' then 1 when 'IDL' then 1  else null end) as present,"
				+ "COUNT(CASE ta.attendance_status when 'SL'then 1 when 'HSL' then 1 when 'WAL' then 1  else null end) as leaves,"
				+ "COUNT(CASE ta.attendance_status when 'ABS'then 1 when 'WKND' then 1 else null end) as holiday,"
				+ "ifnull(SUM(CASE when (ta.check_out IS NULL) then 1 else 0 end),0) as checkin,"
				+ "ifnull(SUM(CASE when (ta.check_out IS NOT NULL) then 1 else 0 end),0) as checkout"
				+ " from  t_attendance ta where ta.attendance_dt = '"+date.toString()+"'" ;
		Integer commas = 1;
		AttendanceDashboard attendanceDashboard = new AttendanceDashboard();
		try {
			if(isManager.equals("Y")) {			
				String getManagerEmps ="SELECT ifnull(group_concat(me.id separator ','),'') AS empIds from m_employee me where n1_emp_id ="+commonUtils.getLoggedinEmployeeId()+"";
				String empIds =jdbcTemplate.query(getManagerEmps, (rs, rowNum) -> new String(rs.getString("empIds"))).get(0);
				System.out.println(empIds);
				if(empIds!= null && !empIds.isEmpty()) { 
					dashboardSql +=" AND ta.employee_id in ("+empIds+")";
				}
				for(int i = 0; i < empIds.length(); i++) {
					if(empIds.charAt(i) == ',') commas++;
				}
			}
			
			attendanceDashboard =jdbcTemplate.query(dashboardSql, (rs, rowNum) -> new AttendanceDashboard(null,rs.getString("present"),rs.getString("leaves"),rs.getString("holiday"),rs.getString("checkin"),rs.getString("checkout"),null)).get(0);
			
			String TotalEmp = "SELECT count(me.id) as totalEmp from m_employee me where me.contractor_code is NULL";
			String count = jdbcTemplate.query(TotalEmp, (rs, rowNum) -> new String(rs.getString("totalEmp"))).get(0);
			if(isManager.equals("Y")) {
				
				attendanceDashboard.setTotalUsr(commas.toString());
			}else {
				attendanceDashboard.setTotalUsr(count);	
			}
			
			Integer Total = Integer.parseInt(attendanceDashboard.getTotalUsr())-Integer.parseInt(attendanceDashboard.getCheckIn())+Integer.parseInt(attendanceDashboard.getCheckOut());
			attendanceDashboard.setYetToCheckIn(Total.toString());
			return attendanceDashboard;
		} catch (Exception e) {
			System.out.println("dashboardSql-->"+dashboardSql);
			e.printStackTrace();
			return attendanceDashboard;
		}  
	}
	
}
