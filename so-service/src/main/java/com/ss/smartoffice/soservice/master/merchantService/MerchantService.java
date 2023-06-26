package com.ss.smartoffice.soservice.master.merchantService;

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
@RequestMapping("master/merchants")
@Scope("prototype")
public class MerchantService {

	@Autowired
	MerchantRepository merchantRepository;
	
	// @CrossOrigin(origins="*")
	@GetMapping
	public Iterable<Merchant> getMerchants() throws SmartOfficeException{
		
		return merchantRepository.findAll();
		
	}
	// @CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<Merchant> getMerchantById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return merchantRepository.findById(id);
		
	}
	
	// @CrossOrigin(origins="*")
	@PostMapping
	public Merchant addMerchant(@RequestBody Merchant merchant)throws SmartOfficeException{
		return merchantRepository.save(merchant);
		
	}
	// @CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public Merchant updateMerchant(@RequestBody Merchant merchant)throws SmartOfficeException{
		return merchantRepository.save(merchant);
		
	}
	// @CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteMerchant(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		merchantRepository.deleteById(id);
	}
	
	
	
}
