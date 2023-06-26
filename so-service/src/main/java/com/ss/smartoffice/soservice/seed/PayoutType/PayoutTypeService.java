package com.ss.smartoffice.soservice.seed.PayoutType;
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
@RestController
@RequestMapping("seed/payout-types")
@Scope("prototype")
public class PayoutTypeService {
@Autowired
PayoutTypeRepository payoutTypeRepository;


//@CrossOrigin(origins="*")
@GetMapping
public Iterable<PayoutType> getPayoutType()throws SmartOfficeException{	
	return payoutTypeRepository.findAll();
	
}
//@CrossOrigin(origins="*")
@GetMapping("/{id}")
public Optional<PayoutType> getPayoutTypeById(@PathVariable(value="id")int id)throws SmartOfficeException{
	return payoutTypeRepository.findById(id);
	
	
}
//@CrossOrigin(origins="*")
@PostMapping

public PayoutType addPayoutType(@RequestBody PayoutType payoutType)throws SmartOfficeException{
	return payoutTypeRepository.save(payoutType);
	
}
//@CrossOrigin(origins="*")
@PatchMapping("/{id}")

public PayoutType updatePayout(@RequestBody PayoutType payoutType)throws SmartOfficeException{
	return payoutTypeRepository.save(payoutType);

}
//@CrossOrigin(origins="*")
@DeleteMapping("/{id}")
public void deletePayout(@PathVariable(value="id")int id)throws SmartOfficeException{
	payoutTypeRepository.deleteById(id);
}


}
