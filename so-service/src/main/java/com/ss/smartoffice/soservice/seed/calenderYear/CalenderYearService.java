package com.ss.smartoffice.soservice.seed.calenderYear;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("seed/cal-years")
@Scope("prototype")
public class CalenderYearService {

	@Autowired
	CalenderYearRepo calenderYearRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<CalenderYear> getAllCalender()throws SmartOfficeException{
		
		return calenderYearRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<CalenderYear> getCalenderYearById(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		return calenderYearRepo.findById(id);
	}
	
	@GetMapping("/year/{year}")
	public CalenderYear getCalenderYearByYear(@PathVariable(value = "year")String year)throws SmartOfficeException{
		
		CalenderYear calenderYear = new CalenderYear();
		calenderYear=calenderYearRepo.findByYear(year);
		if(calenderYear==null) {
			calenderYear = new CalenderYear();
			calenderYear.setCalCode("please Create calender For Year "+year);
		}
		
		return calenderYear;
	}
	
	@PostMapping
	public CalenderYear createCalenderYear(@RequestBody CalenderYear calenderYear)throws SmartOfficeException{
	
		DateTimeFormatter year = DateTimeFormatter.ofPattern("Y");		
		CalenderYear calenderYearFromDB = calenderYearRepo.findByYear(calenderYear.getFromDt().format(year));
		if(calenderYearFromDB==null) {
			calenderYear.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			calenderYear.setCreatedDt(LocalDateTime.now());
			return calenderYearRepo.save(calenderYear);
		}else {
			throw new SmartOfficeException("Calender already Created for this year");
		}
		
		
	}
	
	@PatchMapping("/update")
	public CalenderYear updateCalenderYear (@RequestBody CalenderYear calenderYear)throws SmartOfficeException{
		calenderYear.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		calenderYear.setModifiedDt(LocalDateTime.now());
		return calenderYearRepo.save(calenderYear);
	}
	
	@DeleteMapping("{id}")
	public void DeleteCalenderYearById(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		calenderYearRepo.deleteById(id);
	}
}
