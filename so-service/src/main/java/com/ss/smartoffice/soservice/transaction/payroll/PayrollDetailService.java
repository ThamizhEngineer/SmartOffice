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
import com.ss.smartoffice.soservice.transaction.model.PayrollDetail;


@RestController
@RequestMapping(path="transaction/payroll-details")
public class PayrollDetailService {
	
	 @Autowired
	 PayrollDetailRepository payrollDetailRepository;
	 
	 
	 //@CrossOrigin(origins = "*")
	 @GetMapping
	 public Iterable<PayrollDetail> getPayrollDetail() throws SmartOfficeException {
		 
		 return payrollDetailRepository.findAll();
	             
	 }
	 //@CrossOrigin(origins = "*")
	 @GetMapping("/{id}")
	 public Optional<PayrollDetail> getPayrollDetailById(@PathVariable(value = "id") int id) throws SmartOfficeException {
	     return payrollDetailRepository.findById(id);
	             
	 }
	 //@CrossOrigin(origins = "*")
	 @PostMapping
	 public PayrollDetail addPayrollDetailById(@RequestBody PayrollDetail payrollDetail) throws SmartOfficeException {
	     return payrollDetailRepository.save(payrollDetail);
	             
	 }
	 //@CrossOrigin(origins = "*")
	 @PutMapping("/{id}")
	 public PayrollDetail updatePayrollDetailById(@RequestBody PayrollDetail payrollDetail) throws SmartOfficeException {
	     return payrollDetailRepository.save(payrollDetail);
	             
	 }
	
	

}
