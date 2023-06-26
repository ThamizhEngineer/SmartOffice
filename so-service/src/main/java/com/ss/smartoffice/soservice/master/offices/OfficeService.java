package com.ss.smartoffice.soservice.master.offices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@RequestMapping("master/offices")
@Scope("prototype")
public class OfficeService {
	@Autowired
	OfficeRepository officeRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@GetMapping
	public Iterable<Office> getOffice(Model model, Pageable pageable,
			@RequestParam(value = "countryCode",required = false) String countryCode,
			@RequestParam(value = "employeeId",required = false) String employeeId,
			@RequestParam(value = "officeName",required = false) String officeName) throws SmartOfficeException {
		try {
			return officeRepository.findBySummaries(countryCode, employeeId, officeName);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : officeService - getOfficeDetails");
		}
		
		
	}
	
	@GetMapping("/{id}")
	public Optional<Office> getOfficeById(@PathVariable(value = "id")int id)throws SmartOfficeException{
		return officeRepository.findById(id);
	}
	
	@PostMapping
	public Office addOfficeById(@RequestBody Office office) throws SmartOfficeException{
		if(office.getLastEmpSequence()!=null&&!office.getLastEmpSequence().isEmpty()&&office.getOfficeAbbreviation()!=null&&!office.getOfficeAbbreviation().isEmpty()) {
			office.setCreatedBy(commonUtils.getAuthenticatedUser().getName());
			HashMap<String, String> buisnessKeys = new HashMap<>();
			office.setOfficeCode(sequenceGenerationService.nextSeq("OFFICE-CODE",buisnessKeys));
			return officeRepository.save(office);	
		}else {
			throw new SmartOfficeException("Mandatory fields shoulb be given in order to create new office record");
		}
		
		
	}
	
	@PatchMapping("/{id}")
	public Office updateOfficeById(@RequestBody Office office)throws SmartOfficeException{
		office.setModifiedBy(commonUtils.getAuthenticatedUser().getName());
		return officeRepository.save(office);
	}
	
	@PatchMapping("/bulk-update")
	public Iterable<Office> bulkAddandUpdate(@RequestBody List<Office> offices) throws SmartOfficeException{
		List<Office> officeList = new ArrayList<Office>();
		for(Office list : offices) {
			if(list.getId() > 0) {
				Office officeFromDB = officeRepository.findById(list.getId()).orElse(new Office());
				officeFromDB = this.matchAndUpdateFields(officeFromDB,list);
			}else {
				list = addingNewRecord(list);
			}
			list.setModifiedBy(commonUtils.getAuthenticatedUser().getName());
			officeList.add(list);
		}
		return (Iterable<Office>)officeRepository.saveAll(officeList);
	}

	
	private Office addingNewRecord(Office office) {
		
		Office newOffice = new Office();
		newOffice.setId(office.getId());
		newOffice.setOfficeName(office.getOfficeName());
		newOffice.setDescription(office.getDescription());
		newOffice.setCountryCode(office.getCountryCode());
		newOffice.setEmployeeId(office.getEmployeeId());
		return newOffice;
	}
	
	
	private Office matchAndUpdateFields(Office officeFromDB,Office office) {
		
		officeFromDB.setId(office.getId());
		officeFromDB.setOfficeName(office.getOfficeName());
		officeFromDB.setDescription(office.getDescription());
		officeFromDB.setCountryCode(office.getCountryCode());
		officeFromDB.setEmployeeId(office.getEmployeeId());
		return officeFromDB;
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteOfficeById(@PathVariable(value ="id")int id) throws SmartOfficeException{
		officeRepository.deleteById(id);
	}
	
	
	
	
}
