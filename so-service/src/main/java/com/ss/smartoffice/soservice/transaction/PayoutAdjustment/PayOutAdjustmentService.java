package com.ss.smartoffice.soservice.transaction.PayoutAdjustment;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("transaction/payOutAdj")
@Scope("prototype")
public class PayOutAdjustmentService {
	
	@Autowired
	PayOutAdjustmentRepository payOutAdjustmentRepository;

	
//	@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<PayOutAdjustment> getPayOutAdjustment()throws SmartOfficeException{				
		return payOutAdjustmentRepository.findAll();
		
	}
//	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<PayOutAdjustment> getPayOutAdjustmentById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return payOutAdjustmentRepository.findById(id);
		
	}
	
	@PatchMapping("/{id}")
	public PayOutAdjustment updatePayOutAdjustmentById(@RequestBody PayOutAdjustment payOutAdjustment)throws SmartOfficeException{
		return payOutAdjustmentRepository.save(payOutAdjustment);
		
	}
	
	@PostMapping
	public  PayOutAdjustment addSitesurvey(@RequestBody PayOutAdjustment payOutAdjustment)throws SmartOfficeException{
		return payOutAdjustmentRepository.save(payOutAdjustment);
	}
	
//	@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deletePayOutAdjustmentById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		payOutAdjustmentRepository.deleteById(id);
	}
	
	@GetMapping("/serach")
	public List<PayOutAdjustment> findByYearAndMonth(@RequestParam(value = "payMonth")String payMonth,@RequestParam(value = "payYear")String payYear){
		return payOutAdjustmentRepository.findByPayMonthAndPayYear(payMonth, payYear);
	}
}
