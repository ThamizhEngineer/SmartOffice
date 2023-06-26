package com.ss.smartoffice.soservice.transaction.payroll;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.model.PayrollHeader;




@RestController
@RequestMapping(path="transaction/payroll")
public class PayrollHeaderService  {

	 @Autowired
	 PayrollHeaderRepository payrollRepository;
	 //@CrossOrigin(origins = "*")
	 @GetMapping
	 public Iterable<PayrollHeader> getPayrollHeader() throws SmartOfficeException{
		
		 
		 return payrollRepository.findAll();
	             
	 }
	 //@CrossOrigin(origins = "*")
	 @GetMapping("/{id}")
	 public Optional<PayrollHeader> getPayrollHeaderById(@PathVariable(value = "id") int id) throws SmartOfficeException{
	     return payrollRepository.findById(id);
	             
	 }
	 //@CrossOrigin(origins = "*")
	 @PostMapping
	 public PayrollHeader addPayrollHeaderById(@RequestBody PayrollHeader payrollHeader) throws SmartOfficeException {
	     return payrollRepository.save(payrollHeader);
	             
	 }
	 //@CrossOrigin(origins = "*")
	 @PutMapping("/{id}")
	 public PayrollHeader updatePayrollHeaderById(@RequestBody PayrollHeader payrollHeader) throws SmartOfficeException {
	     return payrollRepository.save(payrollHeader);
	             
	 }
	
	 
//	List<PayrollHeader> findByUserName(@RequestParam("userName") String userName);
	
	 //@CrossOrigin(origins = "*")
	 @GetMapping("/_internal")
 public Iterable<PayrollHeader> getPayrollHeaderInternal() throws SmartOfficeException{
		
		 
		return getPayrollHeader();
	             
	 }
}
