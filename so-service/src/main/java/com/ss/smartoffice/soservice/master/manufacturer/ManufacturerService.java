package com.ss.smartoffice.soservice.master.manufacturer;

import java.util.HashMap;

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

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;

@RestController
@RequestMapping("master/manufacturers")
@Scope("prototype")
public class ManufacturerService {
	
	@Autowired
	ManufacturerRepo manufacturerRepo;
	 @Autowired
	 SequenceGenerationService sequenceGenerationService;
	@Autowired
	ProductFamilyRepo productFamilyRepo;
	@Autowired
	CommonUtils commonUtils;
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<Manufacturer> getAllManufacturers(Model model, Pageable pageable) throws SmartOfficeException {
		
		Page<Manufacturer> pages = manufacturerDetail(pageable);
	       model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("manufacturerdetail", pages.getContent());
	        return pages;

	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Manufacturer getManufacturerById(@PathVariable (value = "id") int id )
			throws Exception {
		
		return manufacturerRepo.findById(id).get();
	}
	

	public Page<Manufacturer> manufacturerDetail(Pageable pageable){
//		boolean searchByProductFamilyName = false;d
		
//		if (productFamilyName != null)
//			searchByProductFamilyName = true;
		
	
//		 if (searchByProductFamilyName) {
//			return ManufacturerRepo.findByProductFamilyName(pageable, ProductFamilyName);
//		} 
        return manufacturerRepo.findAll(pageable);

	}

	//@CrossOrigin(origins = "*")
	@PostMapping
	public Manufacturer addManufacturer(@RequestBody Manufacturer manufacturer) throws SmartOfficeException {
		HashMap<String, String> buisnessKeys = new HashMap<>();
		manufacturer.setManufacturerCode(sequenceGenerationService.nextSeq("MANUFACTURER",buisnessKeys ));
//		manufacturer.setManufacturerCode(sequenceGenerationService.nextSequence("MANUFACTURER").getOutput());
		manufacturer.setIsEnabled("Y");
		manufacturer.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		manufacturerRepo.save(manufacturer); 
		if(manufacturer.getProductFamilies()!=null&&!manufacturer.getProductFamilies().isEmpty()) {
			for(ProductFamily productFamily:manufacturer.getProductFamilies()) {
				
				productFamily.setManufacturerId(manufacturer.getId());
				productFamily.setIsEnabled("Y");
				productFamily.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			}
		}
		
		return manufacturer; 
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public Manufacturer updateManufacturer(@RequestBody Manufacturer manufacturer) throws SmartOfficeException {
		manufacturer.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		return manufacturerRepo.save(manufacturer);

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteManufacturer(@PathVariable(value = "id") int id) throws SmartOfficeException {
		manufacturerRepo.deleteById(id);

	}
	
	
	
	@PatchMapping("/{id}/lines")
	
	public Manufacturer addManufacturerLines(@RequestBody Manufacturer manufacturer)throws SmartOfficeException{
		if(manufacturer.getProductFamilies()!=null&&!manufacturer.getProductFamilies().isEmpty()) {
			for(ProductFamily productFamily:manufacturer.getProductFamilies()) {
				productFamilyRepo.save(productFamily);
			}
		}
		return manufacturer;
	}



}
