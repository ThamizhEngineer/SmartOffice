
package com.ss.smartoffice.soservice.seed.designation;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("seed/designations")
@Scope("prototype")
public class DesignationService {
	@Autowired
	DesignationRepository designationRepository;
	
	@GetMapping
	public Iterable<Designation> getDesignation() throws SmartOfficeException{
	
		return designationRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Optional<Designation> getDesignationById(@PathVariable (value="id")int id)throws SmartOfficeException{
		return designationRepository.findById(id);
		
	}

	@PostMapping
	public Designation addDesignationById(@RequestBody Designation designation)throws SmartOfficeException{
		return designationRepository.save(designation);
		
	}

	@PatchMapping("/{id}")
	public Designation updateDesignationById(@RequestBody Designation designation)throws SmartOfficeException{
		return designationRepository.save(designation);
		
	}
	
	

	@PatchMapping("/bulk-update")
	public Iterable<Designation> bulkAddandUpdate(@RequestBody List<Designation> designations)throws SmartOfficeException{
		List<Designation> designationList=new ArrayList<Designation>();
		System.out.println(designationList);
		for(Designation designation:designations) {
			if(designation.getId()>0) {
				Designation designationFromDB= designationRepository.findById(designation.getId()).orElse(new Designation());
				designationFromDB=this.matchAndUpdateFields(designationFromDB,designation);
			}else {
				designation=addingNewRecord(designation);
			}
			designation.setIsEnabled(designation.getIsEnabled());
			designationList.add(designation);
		}
		System.out.println(designationList);
		
		return (Iterable<Designation>)designationRepository.saveAll(designationList);
		
	}
	
	
	private Designation addingNewRecord(Designation designation) {
		// this service will add new add record if there is no existing Record
		Designation newDesignation=new Designation();
		newDesignation.setGradeId(designation.getGradeId());
		newDesignation.setDesigName(designation.getDesigName());
		return newDesignation;
	}

	private Designation matchAndUpdateFields(Designation designationFromDB, Designation designation) {
	// only update relevant fields to the db-record
    designationFromDB.setId(designation.getId());
    designationFromDB.setGradeId(designation.getGradeId());
    designationFromDB.setDesigName(designation.getDesigName());
		return designationFromDB;
	}

	@DeleteMapping("/{id}")
	public void deleteDesignationById(@PathVariable(value="id")int id)throws SmartOfficeException{
		designationRepository.deleteById(id);
	}
	
	
	
	
}
