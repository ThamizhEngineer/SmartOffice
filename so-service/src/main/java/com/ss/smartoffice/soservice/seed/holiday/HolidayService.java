package com.ss.smartoffice.soservice.seed.holiday;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.seed.holiday.Holiday;
@RestController
@RequestMapping("seed/holidays")
@Scope("prototype")
public class HolidayService {
@Autowired
HolidayRepository holidayRepository;

//@CrossOrigin(origins="*")
@GetMapping
public Iterable<Holiday> getHolidays() throws SmartOfficeException{
	
		
	return holidayRepository.findAll();	
}

//@CrossOrigin(origins="*")
@GetMapping("/{id}")
public Optional<Holiday> getHolidayById(@PathVariable(value="id")int id)throws SmartOfficeException{
	return holidayRepository.findById(id);
}

//@CrossOrigin(origins="*")
@PostMapping
public Holiday addHoliday(@RequestBody Holiday holiday)throws SmartOfficeException{
	if(holiday.getFirstSession().equals("Y") || holiday.getSecondSession().equals("Y")) {
		return holidayRepository.save(holiday);
	}
	else {
		throw new SmartOfficeException("Invalid Request - atleast one session should be set as holiday");
	}
}

//@CrossOrigin(origins="*")
@PatchMapping("/{id}")
public Holiday updateHolidayById(@RequestBody Holiday holiday)throws SmartOfficeException{
	if(holiday.getFirstSession().equals("Y") || holiday.getSecondSession().equals("Y")) {
		return holidayRepository.save(holiday);
	}
	else {
		throw new SmartOfficeException("Invalid Request - atleast one session should be set as holiday");
	}	
}


//@CrossOrigin(origins="*")
@PatchMapping("/bulk-update")
public Iterable<Holiday> bulkAddandUpdate(@RequestBody List<Holiday> holidays)throws SmartOfficeException{
	List<Holiday> holidayList=new ArrayList<Holiday>();
	//System.out.println(holidays);
	for(Holiday holiday:holidays) {
		if(holiday.getId()>0) {
			Holiday holidayFromDB= holidayRepository.findById(holiday.getId()).orElse(new Holiday());
			//System.out.println(holidayFromDB);
			holidayFromDB=this.matchAndUpdateFields(holidayFromDB,holiday);
		}else {
			holiday=addingNewRecord(holiday);
		}
		holiday.setIsEnabled("Y");
		holidayList.add(holiday);
	}
	//System.out.println(holidayList);
	return (Iterable<Holiday>) holidayRepository.saveAll(holidayList);
	
}

private Holiday addingNewRecord(Holiday holiday) {
	//  this Service will add New Record if there is no existing Record
	Holiday newHoliday=new Holiday();
	newHoliday.setHolidayName(holiday.getHolidayName());
	newHoliday.setHolidayDt(holiday.getHolidayDt());
	newHoliday.setFirstSession(holiday.getFirstSession());
	newHoliday.setSecondSession(holiday.getSecondSession());
	newHoliday.setRestrictedHoliday(holiday.getRestrictedHoliday());
	newHoliday.setLocationId(holiday.getLocationId());
	newHoliday.setIsAdhoc(holiday.getIsAdhoc());
	return newHoliday;
}

private Holiday matchAndUpdateFields(Holiday holidayFromDB, Holiday holiday) {
	// only update relevant fields to the db-record
	holidayFromDB.setId(holiday.getId());
	holidayFromDB.setHolidayName(holiday.getHolidayName());
	holidayFromDB.setFirstSession(holiday.getFirstSession());
	holidayFromDB.setSecondSession(holiday.getSecondSession());
	holidayFromDB.setRestrictedHoliday(holiday.getRestrictedHoliday());
	holidayFromDB.setLocationId(holiday.getLocationId());
	holidayFromDB.setIsAdhoc(holiday.getIsAdhoc());
	return holidayFromDB;
}

//@CrossOrigin(origins="*")
@DeleteMapping("/{id}")
public void deleteHolidayById(@PathVariable (value="id")int id)throws SmartOfficeException{
	holidayRepository.deleteById(id);
}
	
}
