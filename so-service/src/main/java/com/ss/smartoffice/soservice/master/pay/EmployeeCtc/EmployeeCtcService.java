package com.ss.smartoffice.soservice.master.pay.EmployeeCtc;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.soservice.common.EvaluateString;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("master/pay/memployee-ctcs")
@Scope("prototype")
public class EmployeeCtcService {
	@Autowired
	EmployeeCtcRepository employeeCtcRepository;
	
	@Autowired
	EmployeeCtcLinesRepository employeeCtcLinesRepository;
	@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<EmployeeCtc> getEmployeeCtc(@RequestParam(value="employeeId",required=false)String employeeId)throws SmartOfficeException{
		
		boolean searchByEmployeeId=false;

	
		
		if(employeeId!=null) searchByEmployeeId=true;
		if(searchByEmployeeId) {
			return employeeCtcRepository.findByEmployeeId(employeeId);
		}
		return employeeCtcRepository.findAll();
		
	}
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<EmployeeCtc>getEmployeeCtcLinesById(@PathVariable(value="id")int id)throws SmartOfficeException{
		return employeeCtcRepository.findById(id);
		
	}
	@CrossOrigin(origins="*")
	@PostMapping
	public EmployeeCtc addEmployeeLinesById(@RequestBody EmployeeCtc employeeCtc)throws SmartOfficeException{
		
		
		return employeeCtcRepository.save(employeeCtc);
		
	}
	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public EmployeeCtc updateEmployeeCtcLinesById(@RequestBody EmployeeCtc employeeCtc)throws SmartOfficeException{
		return employeeCtcRepository.save(employeeCtc);
		
	}
	@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteEmployeeCtcById(@PathVariable(value="id")int id)throws SmartOfficeException{
		employeeCtcRepository.deleteById(id);
	}
	
	
	@CrossOrigin(origins="*")
	@PostMapping("/{id}/lines")
	public EmployeeCtc addEmployeeCtcLines(@RequestBody EmployeeCtc employeeCtc)throws SmartOfficeException{
		
			for(EmployeeCtcLine line:employeeCtc.getEmployeeCtcLines()) {
				if(employeeCtc.getEmployeeCtcLines()!=null) {
		     line= calculateFormula(line, "fb");
				employeeCtcLinesRepository.save(line);
			}else {
				throw new SmartOfficeException("memployee Ctc Is Empty");
			}
			}
		
	
	
		return employeeCtc;
			
	}
	
	public EmployeeCtcLine calculateFormula(EmployeeCtcLine employeeCtcLine,String fixedBenefits )throws SmartOfficeException{

		
		if(employeeCtcLine.getsPayoutTypeId()!=null && employeeCtcLine.getHead()!=null && !employeeCtcLine.getHead().isEmpty() && employeeCtcLine.getPayCycleId()!=null) {
		if(employeeCtcLine.getIsLumpSum().equals("N") && employeeCtcLine.getFormula()!=null) {
		 
			
			
			//  placeholder replacement
			//FIXME - remove this hardcoding
			 fixedBenefits= "25000";
				// execute formula
			
String formula  = (employeeCtcLine.getFormula().replace("[FB]",fixedBenefits));
	//System.out.println(formula);
	double result= EvaluateString.eval(formula);
   //System.out.println(result);
	String finalres=  new Double(result).toString();
			 
			// set result to calculated amount
		employeeCtcLine.setCalculatedAmt(finalres);	
		
		 }else if(employeeCtcLine.getIsLumpSum().equals("Y")) { 

			 
			// set unitCost to calculated amount
				double  results= 300.0;
				String finalresult = new Double(results).toString();
				
				employeeCtcLine.setCalculatedAmt(finalresult)  ;			
				}
				
		
	
	}else {
		throw new SmartOfficeException("Payout Type, Headvalue, PayCycleId Should be Present ");
	}
		return employeeCtcLine;
	}
	
}
	