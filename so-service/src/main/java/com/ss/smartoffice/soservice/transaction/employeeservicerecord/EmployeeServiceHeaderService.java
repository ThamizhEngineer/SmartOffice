package com.ss.smartoffice.soservice.transaction.employeeservicerecord;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.OrgHeader;
import com.ss.smartoffice.shared.model.OrgLine;
import com.ss.smartoffice.shared.model.employee.LanguagesKnown;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.transaction.incident.IncidentTest;



@Controller
@RestController
@RequestMapping(value="transaction/employeeServiceRecord")
@Scope("prototype")
public class EmployeeServiceHeaderService {
	
	@Autowired
	EmployeeServiceHeaderRepo employeeServiceHeaderRepo;
	
	@Autowired
	EmployeeServiceLineRepo employeeServiceLineRepo;

	@GetMapping
	public Iterable<EmployeeServiceHeader> getEmployeeService() throws SmartOfficeException{
		return employeeServiceHeaderRepo.findAll();
	}
	@GetMapping(value="/{id}")
	public Optional<EmployeeServiceHeader> getEmployeeServiceById(@PathVariable(value="id")Integer id) throws SmartOfficeException {
		
		return employeeServiceHeaderRepo.findById(id);
	}
	
	@PostMapping
	public EmployeeServiceHeader addEmployeeServiceById(@RequestBody EmployeeServiceHeader employeeServiceHeader)throws SmartOfficeException{
		return employeeServiceHeaderRepo.save(employeeServiceHeader);
		
	}

	@PatchMapping("/{id}")
	public EmployeeServiceHeader updateEmployeeServiceById(@RequestBody EmployeeServiceHeader employeeServiceHeader)throws SmartOfficeException{
		return employeeServiceHeaderRepo.save(employeeServiceHeader);
		
	}
	
	@PatchMapping("/add-update")
	public EmployeeServiceHeader addOrUpdateEmployeeServiceLines(@RequestBody EmployeeServiceHeader employeeServiceHeader) throws SmartOfficeException {
		if (employeeServiceHeader.getEmployeeServiceLine() != null && !employeeServiceHeader.getEmployeeServiceLine().isEmpty()) {
		    List<EmployeeServiceLine> lines = employeeServiceHeader.getEmployeeServiceLine();
			employeeServiceHeader.setEmployeeServiceLine(null);
			employeeServiceHeader = employeeServiceHeaderRepo.save(employeeServiceHeader);
			for (EmployeeServiceLine employeeServiceLine : lines) {
				employeeServiceLine.setEsrHdrId(employeeServiceHeader.getId().toString());
				employeeServiceLineRepo.save(employeeServiceLine);
  			}
		}
		return employeeServiceHeader;
	}
	
	
	@GetMapping("/advance-filters")
	public List<EmployeeServiceHeader> advanceFetchQuery(
			@RequestParam (value="internalId",required=false)String internalId,
			@RequestParam (value="empCode",required=false)String empCode,
			@RequestParam (value="empFname",required=false)String empFname,
			@RequestParam (value="officeName",required=false)String officeName,
			@RequestParam (value="n1EmpId",required=false)String n1EmpId){
//		System.out.println("startScore="+startScore+","+"endScore="+endScore);
//		System.out.println("startMark="+startMark+","+"endMark="+endMark);
		return employeeServiceHeaderRepo.fetchByAdvanceFilter(internalId, empCode,empFname,officeName,n1EmpId);
	}
	@DeleteMapping("/{id}/delete/header")
	public void deleteEmployeeServiceHeaderById(@PathVariable(value="id")int id)throws SmartOfficeException{
		employeeServiceHeaderRepo.deleteById(id);
	}
	
	@DeleteMapping("/{id}/delete/lines")
	public void deleteEmployeeServiceLinesById(@PathVariable(value="id")int id)throws SmartOfficeException{
		employeeServiceLineRepo.deleteById(id);
	}
}
