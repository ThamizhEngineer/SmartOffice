package com.ss.smartoffice.soservice.master.customer;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.master.model.Customer;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path="master/customers")

public class CustomerService {
//	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	CustomerRepository customerRepository;


	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<Customer> getCustomer() throws SmartOfficeException{	
		return customerRepository.findAll();
		
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public Iterable<Customer> getCustomer(Model model, Pageable pageable,
			@RequestParam(value = "contactName",required = false) String contactName,
			@RequestParam(value = "customerCode", required = false) String customerCode)
			throws Exception {
		
		Page<Customer> pages = customerdetails(pageable,contactName,customerCode);
	       model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("customerdetails", pages.getContent());
	        return pages;
	}
	

	public Page<Customer> customerdetails(Pageable pageable,String contactName,String customerCode){
		boolean searchByContactName = false, searchByCustomerCode = false,searchByContactNameAndCustomerCode=false;
		
		if (contactName != null)
			searchByContactName = true;
		if (customerCode != null && !customerCode.isEmpty() )
				
			searchByCustomerCode = true;
	
		if(contactName!=null&&!contactName.isEmpty()&&customerCode!=null&&!customerCode.isEmpty()) {
			searchByContactNameAndCustomerCode=true;	
		}
		if (searchByCustomerCode && searchByContactName) {
			return customerRepository.findByContactNameAndCustomerCode(pageable, customerCode, contactName);
		}  if (searchByContactName) {
			return customerRepository.findByContactName(pageable, contactName);
		} if (searchByCustomerCode) {
			return customerRepository.findByCustomerCode(pageable, customerCode);
		}
        return customerRepository.findAll(pageable);
    }
	
	@CrossOrigin(origins = "*")
	//@CrossOrigin(origins = "*")
	 @GetMapping("/{id}")
	 public Optional<Customer> getCustomerById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		return customerRepository.findById(id);
		 
	 }
	 
	//@CrossOrigin(origins = "*")
	 @PostMapping
	 public Customer addcustomerById (@RequestBody Customer customer) throws SmartOfficeException {
		return customerRepository.save(customer);
		 
	 }
	//@CrossOrigin(origins = "*")
	 @PatchMapping("/{id}")
	 public Customer updatecustomerById (@RequestBody Customer customer) throws SmartOfficeException {
		return customerRepository.save(customer);
		 
	 }
	 
}
