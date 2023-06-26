package com.ss.smartoffice.soservice.transaction.attendance;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.attendance.Attendance;
import com.ss.smartoffice.shared.model.attendance.AttendanceRepository;

@RestController
@RequestMapping(path = "transaction/attendance")
@Scope("prototype")
public class AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;
	
	@PostMapping
	public Attendance createAndUpdateAttendance(@RequestBody Attendance attendance) throws SmartOfficeException{
		
		if(!attendance.getCheckIn().equals(null)&&attendance.getCheckIn()!=null&& !attendance.getCheckOut().equals(null)&&attendance.getCheckOut()!=null) {
			Long totalLoggedHours = ChronoUnit.HOURS.between(attendance.getCheckIn(), attendance.getCheckOut());
			attendance.setTotalTimeLogged(totalLoggedHours.intValue());
		}	
		return attendanceRepository.save(attendance);
	}
	
	@GetMapping("/{id}")
	public Optional<Attendance> findById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		return attendanceRepository.findById(id);
	}
	
	@GetMapping("/find-emp/{empId}")
	public List<Attendance> findByEmpAndMonthAndYear(@PathVariable(value = "empId")String empId,@RequestParam(value = "month")int month,@RequestParam(value = "year")int year)throws SmartOfficeException{
		
		return attendanceRepository.findByAttendanceMonthAndAttendanceYearAndEmployeeId(month, year, empId);
	}
	
	
	@PatchMapping("/bulk-update")
	public List<Attendance> bulkUpdateAttendance(@RequestBody List<Attendance> attendances)throws SmartOfficeException{
		for(Attendance atten:attendances) {
			if(!atten.getCheckIn().equals(null)&& !atten.getCheckOut().equals(null)) {
				Long totalLoggedHours = ChronoUnit.HOURS.between(atten.getCheckIn(), atten.getCheckOut());
				atten.setTotalTimeLogged(totalLoggedHours.intValue());
			}			
		}
		return (List<Attendance>) attendanceRepository.saveAll(attendances);
	}
	
}
