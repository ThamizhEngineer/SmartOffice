package com.ss.smartoffice.soservice.master.partnerservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.partner.Partner;
import com.ss.smartoffice.shared.model.partner.PartnerAccountInfo;
import com.ss.smartoffice.shared.model.partner.PartnerContact;
import com.ss.smartoffice.shared.model.partner.PartnerDocument;
import com.ss.smartoffice.shared.model.partner.PartnerEmployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;

@RestController
@RequestMapping(path="master/vendor")
@Scope("prototype")
public class VendorsService {
@Autowired
PartnerRepository partnerRepository;
@Autowired
PartnerAccountInfoRepository partnerAccountInfoRepository;

@Autowired
PartnerContactRepository partnerContactRepository;

@Autowired
PartnerDocumentRepository partnerDocumentRepository;

@Autowired
PartnerEmployeeRepository partnerEmployeeRepository;
@Autowired
SequenceGenerationService sequenceGenerationService;
@Autowired
PartnerHelper partnerHelper;



// @CrossOrigin(origins="*")
@GetMapping
public Iterable<Partner> getAllVendors()throws SmartOfficeException{
	List<Partner> partnerList= (List<Partner>) partnerRepository.findAll();
	List<Partner> vendorList= new ArrayList<Partner>();
//	System.out.println(partnerList);
	for(Partner partner:partnerList) {
		
		if(partner.getIsVendor().equals("Y")) {
			vendorList.add(partner);
//			System.out.println(vendorList);
		}

	}
		
	return vendorList;
		
	}

// @CrossOrigin(origins="*")
@GetMapping("/{id}")
public Optional<Partner> getVendorById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return partnerRepository.findById(id);	
}

@GetMapping("/vendor-emp/{id}")
public Optional<PartnerEmployee> findByVendorEmpById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
	return partnerEmployeeRepository.findById(id);
}

// @CrossOrigin(origins="*")
@PostMapping
public Partner addVendor(@RequestBody Partner partner)throws SmartOfficeException{
	HashMap<String, String> buisnessKeys = new HashMap<>();
	partner.setVendorCode(sequenceGenerationService.nextSeq("VENDOR-PROFILE", buisnessKeys));
	partner.setIsVendor("Y");
	PartnerEmployee partnerEmployee = new PartnerEmployee();
	partnerEmployee.setFirstName(partner.getPriFirstName());
	partnerEmployee.setLastName(partner.getPriLastName());
	partnerEmployee.setEmailId(partner.getEmailId());
	partnerEmployee.setMobileNo(partner.getMobileNo());
	partnerEmployee.setIsVendor("Y");
	List<PartnerEmployee> partnerEmpList = new ArrayList<PartnerEmployee>();
	partnerEmpList.add(partnerEmployee);
	partner.setPartnerEmployees(partnerEmpList);	
	partnerRepository.save(partner);
	partnerHelper.processAuthPartner(partnerEmpList);
	return partner;
}
// @CrossOrigin(origins="*")
@PatchMapping("/{id}")
public Partner updateVendor(@RequestBody Partner partner)throws SmartOfficeException{

	partner.setIsVendor("Y");
	partner.setIsClient("N");
	addOrUpdateLines(partner);
	return partnerRepository.save(partner);
	
}
//@CrossOrigin(origins = "*")
@PatchMapping("/{id}/lines")

public Partner addOrUpdateLines(@RequestBody Partner partner)throws SmartOfficeException{
	if(partner.getPartnerAccountInfos()!=null &&!partner.getPartnerAccountInfos().isEmpty()) {
		for(PartnerAccountInfo partnerAccountInfo:partner.getPartnerAccountInfos()) {
			partnerAccountInfoRepository.save(partnerAccountInfo);
		}
	}
	if(partner.getPartnerContacts()!=null&&!partner.getPartnerContacts().isEmpty()) {
		for(PartnerContact partnerContact:partner.getPartnerContacts()) {
			partnerContactRepository.save(partnerContact);
		}
	}
	if(partner.getPartnerDocuments()!=null&&!partner.getPartnerContacts().isEmpty()) {
		for(PartnerDocument partnerDocument:partner.getPartnerDocuments()) {
			if(partnerDocument.getIsAttached().equals("Y")) {
				partnerDocument.setAttachedDt(LocalDateTime.now());
			}
			partnerDocumentRepository.save(partnerDocument);
		}
	}
	if(partner.getPartnerEmployees()!=null&&!partner.getPartnerEmployees().isEmpty()) {
		for(PartnerEmployee partnerEmployee:partner.getPartnerEmployees()) {
			partnerEmployee.setIsVendor("Y");
			partnerEmployee.setIsClient("N");
			partnerEmployeeRepository.save(partnerEmployee);
		}
		partnerHelper.processAuthPartner(partner.getPartnerEmployees());
	}
	return partner;
	
}

@GetMapping("auth-vendor/{partnerEmpId}")
public List<PartnerEmployee> createAuthPartner(@PathVariable(value = "partnerEmpId")Integer partnerEmpId) {
	PartnerEmployee partnerEmployee = partnerEmployeeRepository.findById(partnerEmpId).get();
	partnerEmployee.setIsClient("N");
	partnerEmployee.setIsVendor("Y");
	List<PartnerEmployee> partnerEmployees = new ArrayList<PartnerEmployee>();
	partnerEmployees.add(partnerEmployee);
	partnerHelper.processAuthPartner(partnerEmployees);
	return partnerEmployees;
}

// @CrossOrigin(origins="*")
@DeleteMapping("/{id}")
public void deleteVendor(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	partnerRepository.deleteById(id);
}
//@CrossOrigin(origins = "*")
@Transactional

@DeleteMapping("/{id}/delete/lines")
public void deletePartnerLines(@PathVariable(value = "id") int id) throws SmartOfficeException {
	Partner partner = getVendorById(id).get();
	if (partner.getPartnerAccountInfos() != null && !partner.getPartnerAccountInfos().isEmpty()) {

		for (PartnerAccountInfo partnerAccountInfo : partner.getPartnerAccountInfos()) {

			partnerAccountInfoRepository.delete(partnerAccountInfo.getId());
		}
	}else if (partner.getPartnerContacts()!=null&&!partner.getPartnerContacts().isEmpty()) {
		for(PartnerContact partnerContact:partner.getPartnerContacts()) {
			partnerContactRepository.delete(partnerContact.getId());
		}
		
	}else if (partner.getPartnerDocuments()!=null&&!partner.getPartnerDocuments().isEmpty()) {
		
		for(PartnerDocument partnerDocument:partner.getPartnerDocuments()) {
			partnerDocumentRepository.delete(partnerDocument.getId());
		}
	}else if (partner.getPartnerEmployees()!=null&&!partner.getPartnerEmployees().isEmpty()) {
		for(PartnerEmployee partnerEmployee:partner.getPartnerEmployees()) {
			partnerEmployeeRepository.delete(partnerEmployee.getId());
		}
	}else {
		throw new SmartOfficeException("No Vendor Specific Details Present");
	}
}
@GetMapping("/advance-filters")
public List<Partner> advanceFetchQuery(
		@RequestParam (value="vendorCode",required=false)String vendorCode,
		@RequestParam (value="vendorName",required=false)String vendorName,
		@RequestParam (value="countryName",required=false)String countryName){
	return partnerRepository.fetchByAdvanceSearch(vendorCode, vendorName,countryName,"N","Y");
}
}
