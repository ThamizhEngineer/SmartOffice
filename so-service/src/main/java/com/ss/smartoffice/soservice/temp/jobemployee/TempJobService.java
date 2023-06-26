package com.ss.smartoffice.soservice.temp.jobemployee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
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


@Controller
@RestController
@RequestMapping(value="/temp/temp-job")
@Scope("prototype")
public class TempJobService {
	@Autowired
	   TempJobRepo tempjobrepo;
	@Autowired
	   TempJobEmpRepo tempjobemprepo;
	@Autowired
	   EntityManager entitymanager;
	@Autowired
	CommonUtils commonutils;
	
@GetMapping
public Iterable<TempJob> getTempJob() throws SmartOfficeException{
		return tempjobrepo.findAll();
		}

@GetMapping(value="/job/{id}")
public Optional<TempJob> getTempJobById(@PathVariable(value="id")Integer id) throws SmartOfficeException{
	return tempjobrepo.findById(id);
	}
@GetMapping(value="/byEmp")
public List<TempJobEmp> getTempJobByEmpId(@RequestParam("employeeId")Integer employeeId)throws SmartOfficeException{
	
	return tempjobemprepo.findByEmployeeId(employeeId);
}
@PostMapping
public TempJob addTempJob(@RequestBody TempJob tempjob) throws SmartOfficeException{
	TempJobEmp savedTempJobEmp= new TempJobEmp();
	for(TempJobEmp tempJobEmp:tempjob.getTempJobEmps()) {
		LocalDate d1 = tempJobEmp.getFromDt();
	    LocalDate d2 = tempJobEmp.getToDt();
	    System.out.println(d1+"d1");
	    System.out.println(d2+"d2");
	    if (d1.compareTo(d2) < 0) { 
	    	System.out.println("FromDt is after ToDt"); 
	    	
	    	 savedTempJobEmp= tempjobemprepo.save(tempJobEmp);
	    }else {
	    	  throw new SmartOfficeException("From Dt is greater then to Dt");	
	    }
	  
	}
	tempjob.setCreatedBy(commonutils.getLoggedinEmployeeId());
	tempjob.setIsEnabled("Y");
	TempJob savedTempJob=tempjobrepo.save(tempjob);
	savedTempJobEmp.setJobId(savedTempJob.getId());
	savedTempJobEmp= tempjobemprepo.save(savedTempJobEmp);
    return savedTempJob;
	
    }
@PatchMapping(value="/{id}/update")
public TempJob updateTempJob(@RequestBody TempJob tempjob,@PathVariable(value = "id") Integer id)throws SmartOfficeException{
	tempjob.setModifiedBy(commonutils.getLoggedinEmployeeId());
	tempjob.setIsEnabled("Y");
	return tempjobrepo.save(tempjob);
}
@DeleteMapping(value="/{id}/delete")
public void deleteTempJob(@PathVariable(value="id") Integer id)throws SmartOfficeException{
          tempjobrepo.deleteById(id);
}
@GetMapping("/employee-assigned-to-job")
public List<TempJobEmp> searchByFromDtAndToDtAndEmployeeId(@RequestParam(value="employeeId")Integer employeeId,@RequestParam(value="fromDt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDt,@RequestParam(value = "toDt")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDt)throws SmartOfficeException{
System.out.println(toDt);
   
	return tempjobemprepo.findByJobsBtwDates( fromDt, toDt,employeeId);
}

}



