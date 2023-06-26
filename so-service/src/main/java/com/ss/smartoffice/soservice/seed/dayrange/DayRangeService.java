package com.ss.smartoffice.soservice.seed.dayrange;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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



@Controller
@RestController
@RequestMapping(value="/seed/day-range")
@Scope("prototype")
public class DayRangeService {
@Autowired
 DayRangeRepository dayrangerepository;
@Autowired
 CommonUtils commonutils;
@GetMapping
public Iterable<DayRange> getDayRanges() throws SmartOfficeException{
	return dayrangerepository.findAll();
}
@GetMapping(value="/{id}")
public Optional<DayRange> getDayRangeById(@PathVariable(value="id")Integer id) throws SmartOfficeException {
	
	return dayrangerepository.findById(id);
}
@PostMapping
public DayRange addDayRange(@RequestBody DayRange dayrange)throws SmartOfficeException{
	dayrange.setCreatedBy(commonutils.getLoggedinEmployeeId());
	dayrange.setIsEnabled("Y");
    return dayrangerepository.save(dayrange);
}
@PatchMapping("/{id}/update")
public DayRange  addOrUpdateLines(@RequestBody DayRange dayrange,@PathVariable(value = "id") Integer id)throws SmartOfficeException{
	dayrange.setModifiedBy(commonutils.getLoggedinEmployeeId());
	dayrange.setIsEnabled("Y");
	return dayrangerepository.save(dayrange);
}
@DeleteMapping("/{id}/delete")
public void deleteDayRange(@PathVariable(value="id") Integer id) throws SmartOfficeException{
	    dayrangerepository.deleteById(id);
}


}
