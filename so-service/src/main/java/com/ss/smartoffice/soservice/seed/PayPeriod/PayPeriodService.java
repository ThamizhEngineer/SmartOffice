package com.ss.smartoffice.soservice.seed.PayPeriod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("seed/pay-periods")
public class PayPeriodService {

	@Autowired
	PayPeriodRepository payPeriodRepository;
	
	
	//@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<PayPeriod> getPayPeriod()throws SmartOfficeException{
		return payPeriodRepository.findAll();
		
		
	}
	
	//@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<PayPeriod> getPayPeriodById(@PathVariable(value="id")int id) throws SmartOfficeException{
//		PayPeriod p = payPeriodRepository.findById(id).get();
//		p.setRemarks((new Date()).toString());
//		return Optional.of(payPeriodRepository.save(p));
	return payPeriodRepository.findById(id);
		
	}
	
	//@CrossOrigin(origins="*")
	@PostMapping
	public PayPeriod addPayPeriodById(@RequestBody PayPeriod payPeriod)throws SmartOfficeException{
		
		System.out.println(payPeriod);
		
	
		
		if(payPeriod.getPayTypeCode().equals("PAYROLL")||payPeriod.getPayTypeCode().equals("SITE_ALLOW")||payPeriod.getPayTypeCode().equals("REIMBURSEMENT")) {
			return payPeriodRepository.save(payPeriod);	
		}
		else {
		throw new SmartOfficeException("Invalid Request");	
		}
		
	}
	
	//@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public PayPeriod updatePayPeriodById(@RequestBody PayPeriod payPeriod) throws SmartOfficeException{
		return payPeriodRepository.save(payPeriod);
		
	}
	//@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deletePayPeriodById(@PathVariable(value="id")int id)throws SmartOfficeException{
		payPeriodRepository.deleteById(id);
	}
	
}
