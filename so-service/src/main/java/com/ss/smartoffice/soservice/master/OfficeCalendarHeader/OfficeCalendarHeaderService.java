package com.ss.smartoffice.soservice.master.OfficeCalendarHeader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.time.DayOfWeek;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.OfficeCalendarHeader;
import com.ss.smartoffice.shared.model.OfficeCalender;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.master.officeCalender.OfficeCalenderRepo;
import com.ss.smartoffice.soservice.seed.calenderYear.CalenderYear;
import com.ss.smartoffice.soservice.seed.calenderYear.CalenderYearRepo;


@RestController
@RequestMapping("master/office-calendar-headers")
public class OfficeCalendarHeaderService {

	@Autowired
	OfficeCalendarHeaderRepo officeCalendarHeaderRepo;
	
	@Autowired
	CalenderYearRepo calenderYearRepo;
	
	@Autowired
	OfficeCalenderRepo officeCalenderRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<OfficeCalendarHeader> getAllcalenderHeader() throws SmartOfficeException{
		return officeCalendarHeaderRepo.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Optional<OfficeCalendarHeader> findById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		return officeCalendarHeaderRepo.findById(id);
	}
	
	@GetMapping("/{yearId}")
	public Iterable<OfficeCalendarHeader> fetchByFiscalYear(@PathVariable(value="yearId")String yearId) throws SmartOfficeException{
		return officeCalendarHeaderRepo.findByCalYearId(yearId);
	}
	
	

	@PatchMapping("/calendar-setup/{officeId}/{yearId}")
	public String setup(@RequestBody HashMap<String, String> setup,@PathVariable(value="officeId")String officeId,@PathVariable(value="yearId")String yearId){						
		 triggerCalenderEvent(setup, officeId, yearId);	
		return "in-Progress";
	}
	
	
	@Async("asyncThreadPoolTaskExecutor")
	public void triggerCalenderEvent(HashMap<String, String> setup,String officeId,String yearId ) {
		OfficeCalendarHeader officeCalendarHeader = new OfficeCalendarHeader();
		List<OfficeCalender> officeCalenderList = new ArrayList<OfficeCalender>();
		
		officeCalendarHeader =  officeCalendarHeaderRepo.findByCalYearIdAndOfficeId(yearId, officeId);
		
		if(officeCalendarHeader==null){
			officeCalendarHeader = new OfficeCalendarHeader();
			officeCalendarHeader.setCalStatus("creation-Inprogress");
			officeCalendarHeader.setOfficeId(officeId);
			officeCalendarHeader.setCalYearId(yearId);			
			officeCalendarHeader.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			officeCalendarHeader.setCreatedDt(LocalDateTime.now());
			officeCalendarHeaderRepo.save(officeCalendarHeader);
		}else {
			officeCalendarHeader.setModifiedBy(commonUtils.getLoggedinEmployeeId());
			officeCalendarHeader.setModifiedDt(LocalDateTime.now());
			officeCalendarHeaderRepo.save(officeCalendarHeader);
		}
		
		
		officeCalenderList = officeCalenderRepo.findByCalYearIdAndOfficeId(yearId, officeId);
		if(officeCalenderList!=null) {
			officeCalenderRepo.deleteOfficeCalender(officeId, yearId);			
		}
		officeCalenderList = new ArrayList<OfficeCalender>();		
		CalenderYear calYear = calenderYearRepo.findById(Integer.parseInt(yearId)).get();
		
		for (LocalDate date =calYear.getFromDt() ; date.isBefore(calYear.getToDt().plusDays(1)); date = date.plusDays(1)) {
			
			OfficeCalender calender = new OfficeCalender();
			calender.setCalDate(date);
			calender.setCalYearId(yearId);
			calender.setOfficeId(officeId);	
			calender.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			calender.setCreatedDt(LocalDateTime.now());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
			DateTimeFormatter week = DateTimeFormatter.ofPattern("W");
			String formattedString = date.format(formatter);
			
			String weeks = date.format(week);
			int reminder = Integer.parseInt(weeks) % 2;
		
		switch (setup.get("weekend-rule")) {
		case "regular-weekends":
			if(formattedString.equals("Sunday") || formattedString.equals("Saturday")){
				calender.setDayType("weekend");	
				}else {
				calender.setDayType("weekday");	
				}		
		break;
		case "second-saturdays-leave":
			if(formattedString.equals("Sunday") || formattedString.equals("Saturday") && reminder==0 ){
				calender.setDayType("weekend");	
				}else {
					calender.setDayType("weekday");	
				}	
			break;
		case "saturdays-working":
			if(formattedString.equals("Sunday")){
				calender.setDayType("weekend");	
				}else {
				calender.setDayType("weekday");	
				}	
			break;
		case "Middleeast-regular-weekend":
			if(formattedString.equals("Friday") || formattedString.equals("Saturday")){
				calender.setDayType("weekend");	
				}else {
				calender.setDayType("weekday");	
				}
			break;
		case "Middleeast-second-saturdays-leave":
			if(formattedString.equals("Friday") || formattedString.equals("Saturday") && reminder==0){
				calender.setDayType("weekend");	
				}else {
				calender.setDayType("weekday");	
				}
			break;

		default:			
			 new SmartOfficeException("Unknow weekend-rule");			
			break;
		}
		
		
		officeCalenderList.add(calender);
		}
		officeCalendarHeader.setCalStatus("setup-inprogress");
		officeCalendarHeaderRepo.save(officeCalendarHeader);
		officeCalenderRepo.saveAll(officeCalenderList);
	}
	
	@PatchMapping("/complete-calender/{id}")
	public OfficeCalendarHeader completeCalender(@RequestBody OfficeCalendarHeader officeCalender, @PathVariable(value = "id")Integer id)throws SmartOfficeException{
		officeCalender = new OfficeCalendarHeader();
		officeCalender = officeCalendarHeaderRepo.findById(id).get();
		if(officeCalender.getCalStatus().equals("setup-inprogress")) {
			officeCalender.setCalStatus("completed");
		}		
		return officeCalendarHeaderRepo.save(officeCalender);
	}
	
	
}
