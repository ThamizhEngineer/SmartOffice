package com.ss.smartoffice.soservice.transaction.attendance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.lang.String;
import java.time.LocalDate;
import java.time.ZoneId;
import java.lang.Integer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.Location;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.attendance.Attendance;
import com.ss.smartoffice.shared.model.attendance.AttendanceRepository;
import com.ss.smartoffice.shared.repos.LocationRepo;
import com.ss.smartoffice.soservice.transaction.incident.IncidentTest;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path = "transaction/attendance/direct")
@Scope("prototype")
public class DirectAttendanceService {
	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	LocationRepo locationRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<Attendance> getAttendance(@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "attendanceMonth", required = false) String attendanceMonth,
			@RequestParam(value = "attendanceYear", required = false) String attendanceYear,
			@RequestParam(value = "attendanceDt", required = false) String attendanceDtString,
			@RequestParam(value="n1EmpId",required=false)String n1EmpId,
			@RequestParam(value="hr1GrpId",required=false)String hr1GrpId,
			@RequestParam(value="hr2GrpId",required=false)String hr2GrpId)
		throws SmartOfficeException {
		
		boolean searchByEmployeeId = false, searchByMonthYear = false, 
				searchByAttendanceDt = false,searchByManagerIdAndAttendanceDt=false,
				searchByAttendanceDtAndHr1IdAndHr2Id=false,searchByAttendanceDtAndemployeeId=false;
	
		LocalDate attendanceDt = null;
		// map.addAttribute(Integer.parseInt(attendanceMonth));
		// map.addAttribute(Integer.parseInt(attendanceYear));
		if (employeeId != null)
			searchByEmployeeId = true;
		if (attendanceMonth != null && !attendanceMonth.isEmpty() && attendanceYear != null
				&& !attendanceYear.isEmpty())
			searchByMonthYear = true;
		// TODO need to implement dynamic query for comprehensive searching
		if (searchByEmployeeId && searchByMonthYear) {
			return attendanceRepository.findByAttendanceMonthAndAttendanceYearAndEmployeeId(
					Integer.parseInt(attendanceMonth), Integer.parseInt(attendanceYear), employeeId);
		} else if (searchByMonthYear) {
			return attendanceRepository.findByAttendanceMonthAndAttendanceYear(Integer.parseInt(attendanceMonth),
					Integer.parseInt(attendanceYear));
		} else if (searchByEmployeeId) {
			return attendanceRepository.findByEmployeeId((employeeId));
			
		}
		if (attendanceDtString != null && !attendanceDtString.isEmpty()) {
			searchByAttendanceDt = true;

			attendanceDt = LocalDate.parse(attendanceDtString);
		}
		if (attendanceDtString != null && !attendanceDtString.isEmpty()&&n1EmpId!=null&&!n1EmpId.isEmpty()) {
			

			attendanceDt = LocalDate.parse(attendanceDtString);
			searchByManagerIdAndAttendanceDt = true;
		}
		if(searchByManagerIdAndAttendanceDt) {
			   return attendanceRepository.findByN1EmpIdAndAttendanceDt(n1EmpId, attendanceDt);
		       }
				
		if (attendanceDtString != null && !attendanceDtString.isEmpty()&&hr1GrpId!=null&&!hr1GrpId.isEmpty()&&hr2GrpId!=null&&!hr2GrpId.isEmpty()) {
			searchByAttendanceDtAndHr1IdAndHr2Id = true;

			attendanceDt = LocalDate.parse(attendanceDtString);
		}
		if(searchByAttendanceDtAndHr1IdAndHr2Id) {
			return attendanceRepository.findByAttendanceDtAndHr1GrpIdAndHr2GrpId(attendanceDt, hr1GrpId,hr2GrpId);
		}
		
		if (attendanceDtString != null && !attendanceDtString.isEmpty()&&employeeId!=null) {
			

			attendanceDt = LocalDate.parse(attendanceDtString);
			searchByAttendanceDtAndemployeeId = true;
		}
		if(searchByAttendanceDtAndemployeeId){
		return attendanceRepository.findByAttendanceDtAndEmployeeId(attendanceDt, employeeId);	
		}
		
       
		if (searchByEmployeeId && searchByAttendanceDt) {
			return attendanceRepository.findByEmployeeIdAndAttendanceDt(employeeId, attendanceDt);
		} else if (searchByAttendanceDt) {
			return attendanceRepository.findByAttendanceDt(attendanceDt);
		}
		else {
		return attendanceRepository.findAll();
		}
	}
//    @CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<Attendance> getAttendanceById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
    	return attendanceRepository.findById(id);
    }

	@PostMapping
	public Attendance addAttendanceById(@RequestBody Attendance attendance) throws SmartOfficeException {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		attendance.setAttendanceMonth(month);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		attendance.setAttendanceYear(calendar.get(Calendar.YEAR));
		if(attendance.getCheckIn()==null) {
			attendance.setFirstSessionCodeDesription("leave");
		}
		return attendanceRepository.save(attendance);
	}
	
	public void processAddAttendanceById(Attendance attendance) {
		try {
			attendance = addAttendanceById(attendance);
			Optional<Location> location = locationRepo.findById(Integer.parseInt(attendance.getLocationId()));
			if (location.isPresent()) {
				location.get().setAttendanceStatus("processed");
				locationRepo.save(location.get());
			}
		} catch (SmartOfficeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PatchMapping("/{id}")
	public Attendance updateAttendance(@RequestBody Attendance attendance) throws SmartOfficeException {

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		attendance.setAttendanceMonth(month);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		attendance.setAttendanceYear(calendar.get(Calendar.YEAR));

		return attendanceRepository.save(attendance);

	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/bulk")
	public Iterable<Attendance> bulkAttendanceUpdate(@RequestBody List<Attendance> attendances)
			throws SmartOfficeException {
		List<Attendance> cleansedAttendances = new ArrayList<Attendance>();
		System.out.println(attendances);
		for (Attendance attendance : attendances) {
			if (attendance.getId() > 0) {
				// fetch object from db and update
				Attendance attendanceFromDB = attendanceRepository.findById(attendance.getId())
						.orElse(new Attendance());
				System.out.println(attendanceFromDB);
				attendance = this.matchAndUpdateRelevantFields(attendanceFromDB, attendance);
			} else {
				attendance = identifyRelevantFields(attendance);
			}
			attendance.setAttendanceStatus("FINAL");
			attendance.setAttendanceMonth(null);
			attendance.setAttendanceYear(null);
			attendance.setIsEnabled("Y");
			cleansedAttendances.add(attendance);
		}
		System.out.println(cleansedAttendances);
		return (Iterable<Attendance>) attendanceRepository.saveAll(cleansedAttendances);
	}

	// this service is meant to update specific fields only. hence this function
	// will ignore irrelevant fields
	private Attendance identifyRelevantFields(Attendance attendance) {
		Attendance newAttendance = new Attendance();
		newAttendance.setAttendanceDt(attendance.getAttendanceDt());
		newAttendance.setFirstSession(attendance.getFirstSession());
		newAttendance.setSecondSession(attendance.getSecondSession());
		newAttendance.setEmployeeId(attendance.getEmployeeId());
		newAttendance.setProxyEmployeeId(attendance.getProxyEmployeeId());
		return newAttendance;
	}

	// only update relevant fields to the db-record
	private Attendance matchAndUpdateRelevantFields(Attendance attendanceFromDB, Attendance attendance) {
		attendanceFromDB.setId(attendance.getId());
		attendanceFromDB.setAttendanceDt(attendance.getAttendanceDt());
		attendanceFromDB.setFirstSession(attendance.getFirstSession());
		attendanceFromDB.setSecondSession(attendance.getSecondSession());
		attendanceFromDB.setEmployeeId(attendance.getEmployeeId());
		attendanceFromDB.setProxyEmployeeId(attendance.getProxyEmployeeId());
		return attendanceFromDB;
	}
	
	@GetMapping("/advanceFilters")
	public Iterable<Attendance> advanceFetchQuery(
			@RequestParam (value="employeeId",required=false)String employeeId,
			@RequestParam (value="n1EmpId",required=false)String n1EmpId,
			@RequestParam (value="startDate",required=false) String startDate,
			@RequestParam (value="endDate",required=false) String endDate){
		LocalDate s = null;
		LocalDate e = null;
		if (commonUtils.isNullOrEmpty(startDate)==false && commonUtils.isNullOrEmpty(endDate)==false) {
			s=LocalDate.parse(startDate);
			e=LocalDate.parse(endDate);
		}else {
			s=LocalDate.now();
			s=s.minusYears(50);
			e=LocalDate.now();
		}
		return attendanceRepository.fetchByAdvance(employeeId,n1EmpId,s,e);
	}


	}


