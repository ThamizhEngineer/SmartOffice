package com.ss.smartoffice.soservice.transaction.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.attendance.Attendance;
import com.ss.smartoffice.shared.model.attendance.AttendanceRepository;
import com.ss.smartoffice.soservice.transaction.invoice.InvoiceGenerator;

@Service
public class AttendanceGenerator {
	private static Logger log = LoggerFactory.getLogger(InvoiceGenerator.class);
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	IntAttendaceLineRepo intAttendaceLineRepo;
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	public void start(String iAttendanceHdrId) {
		log.info("Attendance generation sarted for: " + iAttendanceHdrId);
		List<IntAttendanceLine> validRecords =intAttendaceLineRepo.findByAttendanceHdrId(iAttendanceHdrId);
		List<Attendance> attendances = new ArrayList<Attendance>();
		for(IntAttendanceLine attendanceLine:validRecords) {
			Attendance attendance = formAttendance(attendanceLine);
			attendances.add(attendance);
		}
		attendanceRepository.saveAll(attendances);
	}
	
	public Attendance formAttendance(IntAttendanceLine i) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Attendance attendance = new Attendance();
		List<Attendance> Attendances = attendanceRepository.findByAttendanceDtAndEmployeeId(LocalDate.parse(i.getDate()), i.getEmpId());
		
		if(Attendances.size()==0) {
			attendance= new Attendance();
		}else {
			attendance=Attendances.get(0);
		}
		attendance.setEmployeeId(i.getEmpId());
		attendance.setProxyEmployeeId(Integer.parseInt(i.getProxyEmpId()));
		attendance.setShiftId(i.getShiftId());
		attendance.setAttendanceMonth(Integer.parseInt(i.getMonth()));
		attendance.setAttendanceYear(Integer.parseInt(i.getYear()));
		attendance.setAttendanceDt(LocalDate.parse(i.getDate()));
		attendance.setCheckIn(LocalTime.parse(i.getCheckIn()));
		attendance.setCheckOut(LocalTime.parse(i.getCheckOut()));
		attendance.setAttendanceStatus(i.getAttendaceStatus());
		attendance.setFirstSession(i.getFirstSession());
		attendance.setSecondSession(i.getSecoundSession());
		attendance.setInLats(i.getInLat());
		attendance.setInLongs(i.getInLong());
		attendance.setOutLats(i.getOutLat());
		attendance.setOutLongs(i.getOutLong());
		attendance.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		attendance.setCreatedDt(LocalDateTime.now());
		attendance.setStartDt(LocalDateTime.parse(i.getDate()+" "+i.getCheckIn(), dateTimeFormatter));
		attendance.setEndDt(LocalDateTime.parse(i.getDate()+" "+i.getCheckOut(), dateTimeFormatter));
		attendance.setRemarks(i.getRemarks());
		Long totalLoggedHours = ChronoUnit.HOURS.between(attendance.getCheckIn(), attendance.getCheckOut());
		attendance.setTotalTimeLogged(totalLoggedHours.intValue());
	return attendance;	
	}
}