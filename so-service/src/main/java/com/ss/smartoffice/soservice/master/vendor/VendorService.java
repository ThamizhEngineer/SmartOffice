package com.ss.smartoffice.soservice.master.vendor;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.master.model.Vendor;
@RestController
@RequestMapping(path="master/vendors")
public class VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	@CrossOrigin(origins="*")
	 @GetMapping
	
	 public Iterable<Vendor> getVendor(@RequestParam(value="customerCode",required=false)String customerCode,@RequestParam(value="orgName",required=false)String orgName) throws SmartOfficeException{
		boolean searchBycustomerCode=false,searchByorgName=false;
		
		
		if(customerCode!=null&&!customerCode.isEmpty()) searchBycustomerCode=true;
		if(orgName!=null&&!orgName.isEmpty())  searchByorgName=true;
		if(searchBycustomerCode&&searchByorgName) {
			return vendorRepository.findByCustomerCodeAndOrgName(customerCode, orgName);
		} else if (searchBycustomerCode) {
			return vendorRepository.findByCustomerCode(customerCode);
			
		} else if (searchByorgName) {
			return vendorRepository.findByOrgName(orgName);
			
		}
		return vendorRepository.findAll();
				
	
	}
	
	 
	 @CrossOrigin(origins="*")
	 @GetMapping("/{id}")
	 
	 public Optional<Vendor> getVendorById(@PathVariable(value="id")int id) throws SmartOfficeException{
		return vendorRepository.findById(id);
		 
	 }
	 @CrossOrigin(origins="*")
	 @PostMapping
	 public Vendor addVendorById(@RequestBody Vendor vendor)throws SmartOfficeException{
		return vendorRepository.save(vendor);
		 
	 }
	 @CrossOrigin(origins="*")
	 @PatchMapping("/{id}")
	 public Vendor updateVendorById(@RequestBody Vendor vendor)throws SmartOfficeException{
		return vendorRepository.save(vendor);
		 
	 }
	 @CrossOrigin(origins="*")
	 @DeleteMapping("/{id}")
	 public void deleteVendorById(@PathVariable(value="id")int id)throws SmartOfficeException{
		 vendorRepository.deleteById(id);
	 }
	
 
}
