package com.ss.smartoffice.soservice.master.officeCalender;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.OfficeCalender;

@RestController
@RequestMapping("master/office-calendars")
public class OfficeCalenderService {
	
	@Autowired
	OfficeCalenderRepo officeCalenderRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<OfficeCalender> getAllCalenderDetail()throws SmartOfficeException{
		return officeCalenderRepo.findAll();
	}
	
	@GetMapping("/serach")
	public List<OfficeCalender> findByYearAndMonthAndOffice(@RequestParam(value = "officeId")String officeId,@RequestParam(value = "year")String year,@RequestParam(value = "month")String month){
		return officeCalenderRepo.findByYearAndMonthAndOfficeId(year, month, officeId);
	}

	@PostMapping
	public List<OfficeCalender> multiCreateCalender(@RequestBody List<OfficeCalender> calenders) throws SmartOfficeException{
		
		try {
			for(OfficeCalender cal:calenders) {
				cal.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				cal.setCreatedDt(LocalDateTime.now());
				officeCalenderRepo.save(cal);
			}		
			return calenders;
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : Office-calender Multi create");
		}	
	}
	
	@PatchMapping
	public List<OfficeCalender> multiUpdateCalender(@RequestBody List<OfficeCalender> calenders) throws SmartOfficeException{
		
		try {
			for(OfficeCalender cal:calenders) {
				cal.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				cal.setModifiedDt(LocalDateTime.now());
				officeCalenderRepo.save(cal);
			}		
			return calenders;
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : Office-calender Multi create");
		}	
	}
	
	@PostMapping("/holiday/_internal")
	public List<OfficeCalender> CreateHoliday(@RequestBody List<OfficeCalender> calenders) throws SmartOfficeException{
		
		try {
			
			for(OfficeCalender oc:calenders) {
			OfficeCalender offCalender =  new OfficeCalender();
			offCalender = officeCalenderRepo.findByCalDateAndOfficeId(oc.getCalDate(),oc.getOfficeId());
			if(offCalender==null) {
				officeCalenderRepo.save(oc);
			}else {				
				offCalender.setCalDate(oc.getCalDate());
				offCalender.setHolidayName(oc.getHolidayName());
				offCalender.setIsRestrictedHoliday(oc.getIsRestrictedHoliday());
				offCalender.setRemarks(oc.getRemarks());
				offCalender.setDayType("holiday");
				offCalender.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				offCalender.setModifiedDt(LocalDateTime.now());			
				officeCalenderRepo.save(offCalender);
			}						
			}
			
//			officeCalenderRepo.saveAll(calenders);
			return calenders;
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : Office-calender Multi Update");
		}	
	}
	
	@DeleteMapping
	public void multiDeleteCalender(@RequestBody List<OfficeCalender> calenders) throws SmartOfficeException{
	try {
		for(String id:calenders.get(0).getMultiDelete()) {
			officeCalenderRepo.deleteById(Integer.parseInt(id));
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.getLocalizedMessage();
		throw new SmartOfficeException("Exception Occured : Office-calender Multi Update");
	}			
	}
	
	@GetMapping("/month/{officeId}/{yearId}/{month}")
	public List<OfficeCalender> getByMonth(@PathVariable(value = "officeId")String officeId,@PathVariable(value = "yearId")String yearId,@PathVariable(value = "month")String month){
		return officeCalenderRepo.findByCalYearIdAndOfficeIdAndMonth(yearId, officeId, month);
	}
	
	@GetMapping("/group-by-month/{officeId}/{yearId}")
	public List<OfficeCalender> groupByMonth(@PathVariable(value = "officeId")String officeId,@PathVariable(value = "yearId")String yearId)throws SmartOfficeException{
	
		List<OfficeCalender> groupByMonth = new ArrayList<OfficeCalender>(); 
		
		ArrayList<String> month = new ArrayList<String>();
		month.add("01");
		month.add("02");
		month.add("03");
		month.add("04");
		month.add("05");
		month.add("06");
		month.add("07");
		month.add("08");
		month.add("09");
		month.add("10");
		month.add("11");
		month.add("12");
					
		for (Integer i = 1; i <= 12; i++) {			
			System.out.println(month);
			groupByMonth.add(officeCalenderRepo.groupByMonth(officeId,month.get(i-1),yearId).get(0));
		}
		for(OfficeCalender calender:groupByMonth) {
			calender.setWeekdayCount(officeCalenderRepo.weekdaysCount(yearId, calender.getMonth(), officeId));
			calender.setWeekendCount(officeCalenderRepo.weekendCount(yearId, calender.getMonth(), officeId));
			calender.setHolidayCount(officeCalenderRepo.holidaysCount(yearId, calender.getMonth(), officeId));
		}
		return groupByMonth;
	}
	
	@GetMapping("/summary")
	public Iterable<OfficeCalender> fetchCalendarSummary(@RequestParam(value="officeId")String officeId,@RequestParam(value="yearId")String yearId,@RequestParam(value="dayType")String dayType)throws SmartOfficeException{
		try {
//			return null;
			
			Iterable<OfficeCalender> officList = new ArrayList<OfficeCalender>();
			officList = officeCalenderRepo.findByCalYearIdAndOfficeIdAndDayType(yearId, officeId,dayType);
			
			for(OfficeCalender sum:officList) {
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMMM");
				String month = sum.getCalDate().format(formatter);
				sum.setHolidayCount(officeCalenderRepo.holidaysCount(yearId, month, officeId));
				sum.setSummaryMonth(sum.getCalDate().format(formatter2));
				sum.setWeekdayCount(officeCalenderRepo.weekdaysCount(yearId, month, officeId));
				sum.setWeekendCount(officeCalenderRepo.weekendCount(yearId, month, officeId));
			}
			
			return officList;
//			return officeCalenderRepo.findByFiscalYearIdAndOfficeId(yearId, officeId);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : Office-calender Calender Summary");
		}
	}
	
	@PatchMapping("/{id}/{officeId}/{calyearid}")
	public OfficeCalender updateCalendars(@RequestBody OfficeCalender officeCalender ,@PathVariable(value="id")Integer id,@PathVariable(value="officeId")String officeId,@PathVariable(value="calyearid")String calyearid)throws SmartOfficeException{
		OfficeCalender officeCalenderFromDb = new OfficeCalender();
		officeCalenderFromDb = officeCalenderRepo.findByCalDateAndOfficeId(officeCalender.getCalDate(),officeId);
		
		if(officeCalenderFromDb==null) {
			officeCalender.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			officeCalender.setCreatedDt(LocalDateTime.now());
			officeCalenderRepo.save(officeCalender);
		}else {
			officeCalenderFromDb.setCalDate(officeCalender.getCalDate());
			officeCalenderFromDb.setDayType(officeCalender.getDayType());
			officeCalenderFromDb.setUpdateReason(officeCalender.getUpdateReason());
			officeCalenderFromDb.setHolidayName(officeCalender.getHolidayName());
			officeCalenderFromDb.setIsRestrictedHoliday(officeCalender.getIsRestrictedHoliday());
			officeCalenderFromDb.setRemarks(officeCalender.getRemarks());
			officeCalenderFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
			officeCalenderFromDb.setModifiedDt(LocalDateTime.now());
			officeCalenderRepo.save(officeCalenderFromDb);
		}			
		return officeCalender;
	
	}
	
	
	
	
}
