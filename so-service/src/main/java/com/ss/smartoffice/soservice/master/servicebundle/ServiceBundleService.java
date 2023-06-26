package com.ss.smartoffice.soservice.master.servicebundle;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
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
import com.ss.smartoffice.soservice.master.servicebundle.ServiceBundle;
import com.ss.smartoffice.soservice.master.servicebundle.ServiceBundleRepo;
import com.ss.smartoffice.soservice.master.servicebundle.ServiceBundleService;
import com.ss.smartoffice.shared.common.CommonUtils;

@RestController
@RequestMapping("master/service-bundles")
@Scope("prototype")
public class ServiceBundleService {
	
	@Autowired
	ServiceBundleRepo serviceBundleRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<ServiceBundle> getServiceBundle(Model model, Pageable pageable,
			@RequestParam(value = "sbName",required = false) String sbName,
			@RequestParam(value = "sacCode", required = false) String sacCode)
			throws Exception {
		
		Page<ServiceBundle> pages = bundledetails(pageable,sbName,sacCode);
	       model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("bundledetails", pages.getContent());
	        return pages;
	}
	

	public Page<ServiceBundle> bundledetails(Pageable pageable,String sbName,String sacCode){
		boolean searchBySbName = false, searchBySacCode = false,searchBySbNameAndSacCode=false;
		
		if (sbName != null)
			searchBySbName = true;
		if (sacCode != null && !sacCode.isEmpty() )
				
			searchBySacCode = true;
	
		if(sbName!=null&&!sbName.isEmpty()&&sacCode!=null&&!sacCode.isEmpty()) {
			searchBySbNameAndSacCode=true;	
		}
		if (searchBySacCode && searchBySbName) {
			return serviceBundleRepo.findBySbNameAndSacCode(pageable, sacCode, sbName);
		}  if (searchBySbName) {
			return serviceBundleRepo.findBySbName(pageable, sbName);
		} if (searchBySacCode) {
			return serviceBundleRepo.findBySacCode(pageable, sacCode);
		}
        return serviceBundleRepo.findAll(pageable);
    }

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<ServiceBundle> getServiceBundleById(@PathVariable(value = "id") int id) throws SmartOfficeException {
        return serviceBundleRepo.findById(id);

    }


	//@CrossOrigin(origins = "*")
	@PostMapping
	public ServiceBundle addServiceBundle(@RequestBody ServiceBundle serviceBundle) throws SmartOfficeException {
		serviceBundle.setCreatedBy(commonUtils.getAuthenticatedUser().getName());
		return 	serviceBundleRepo.save(serviceBundle); 
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public ServiceBundle updateServiceBundle(@RequestBody ServiceBundle serviceBundle) throws SmartOfficeException {
		serviceBundle.setModifiedBy(commonUtils.getAuthenticatedUser().getName());
		return 	serviceBundleRepo.save(serviceBundle);

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteServiceBundle(@PathVariable(value = "id") int id) throws SmartOfficeException {
		serviceBundleRepo.deleteById(id);

	}



}
