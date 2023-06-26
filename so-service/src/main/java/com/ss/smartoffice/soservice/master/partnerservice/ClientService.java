package com.ss.smartoffice.soservice.master.partnerservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import com.ss.smartoffice.shared.model.partner.Partner;
import com.ss.smartoffice.shared.model.partner.PartnerAccountInfo;
import com.ss.smartoffice.shared.model.partner.PartnerContact;
import com.ss.smartoffice.shared.model.partner.PartnerDocument;
import com.ss.smartoffice.shared.model.partner.PartnerEmployee;
import com.ss.smartoffice.shared.model.partner.PartnerGst;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.item.Item;

@RestController
@RequestMapping("master/clients")
@Scope("prototype")
public class ClientService {
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
PartnerGstRepository partnerGstRepository;

@Autowired
SequenceGenerationService sequenceGenerationService;

@Autowired
PartnerHelper partnerHelper;


//@CrossOrigin(origins = "*")
@GetMapping
public Iterable<Partner> getAllClients()throws SmartOfficeException{
	List<Partner> partnerLists= partnerRepository.findBySummaries();

	for(Partner partner:partnerLists) {
		if(partner.getIsClient().equals("Y")) {
		return partnerRepository.findByIsClient(partner.getIsClient());
	}
	}
	return null;	
}

@GetMapping("/client-emp/{id}")
public Optional<PartnerEmployee> findByClientEmpById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
	return partnerEmployeeRepository.findById(id);
}

//@CrossOrigin(origins = "*")	
@GetMapping("/{id}")
public Optional<Partner> getClientById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return partnerRepository.findById(id);
	
}
@GetMapping("/_internal/{id}")
public Optional<Partner> getClientByInternal(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return partnerRepository.findById(id);
	
}



//@CrossOrigin(origins = "*")
@PostMapping
public Partner addClient(@RequestBody Partner partner)throws SmartOfficeException{
	HashMap<String, String> buisnessKeys = new HashMap<>();
//	partner.setClientCode(sequenceGenerationService.nextSequence("CLIENT-PROFILE").getOutput());
	
	List<Partner> existingPartnerList=partnerRepository.findByGstNoAndPriFirstNameAndPriLastName(partner.getGstNo(), partner.getPriFirstName(), partner.getPriLastName());
	List<Partner> gstList=partnerRepository.findByIsClient(partner.getIsClient());	
	
	for(Partner existingPartner:existingPartnerList) {
		
		if(existingPartner.getIsClient().equals("Y")) {
			throw new SmartOfficeException("Client already exists");
			
		}else if(existingPartner.getIsVendor().equals("N")){
			throw new SmartOfficeException("A Vendor with same gst number and manager name exists. Do you want to merge?");	
				} else {
					continue;
				}
		}

	for(Partner list:gstList) {
		if(list.getGstNo().equals(partner.getGstNo())) {
			throw new SmartOfficeException("Gst No already exists");
		}
	}
	PartnerEmployee partnerEmployee = new PartnerEmployee();
	partnerEmployee.setFirstName(partner.getPriFirstName());
	partnerEmployee.setLastName(partner.getPriLastName());
	partnerEmployee.setEmailId(partner.getEmailId());
	partnerEmployee.setMobileNo(partner.getMobileNo());
	partnerEmployee.setLandlineNo(partner.getNumber());
	partnerEmployee.setIsClient("Y");
	partnerEmployee.setIsVendor("N");
	partner.setIsClient("Y");
	partner.setIsVendor("N");
	List<PartnerEmployee> partnerEmpList = new ArrayList<PartnerEmployee>();
	partnerEmpList.add(partnerEmployee);
	partner.setPartnerEmployees(partnerEmpList);	
	partner.setClientCode(sequenceGenerationService.nextSeq("CLIENT-PROFILE", buisnessKeys));
	partnerRepository.save(partner);
	partnerHelper.processAuthPartner(partnerEmpList);

	return partner;
	
}



//@CrossOrigin(origins = "*")
@PatchMapping("/{id}")
public Partner updatePartner(@RequestBody Partner partner,@PathVariable(value="id")Integer id)throws SmartOfficeException{
	List<Partner> gstList=partnerRepository.findByIsClient(partner.getIsClient());
	Partner partnerById=getClientById(id).get();
	if(partnerById.getGstNo().equals(partner.getGstNo())) {
		partner.setGstNo(partnerById.getGstNo());
	}else {
	for(Partner list:gstList) {
		if(list.getGstNo().equals(partner.getGstNo())) {
			throw new SmartOfficeException("Gst No already exists");
		}
	}
	}
	partner.setIsClient("Y");
	partner.setIsVendor("N");
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
	if(partner.getPartnerDocuments()!=null&&!partner.getPartnerDocuments().isEmpty()) {
		System.out.println(partner.getPartnerDocuments());
		for(PartnerDocument partnerDocument:partner.getPartnerDocuments()) {
			if(partnerDocument.getIsAttached()!="N") {
				partnerDocument.setAttachedDt(LocalDateTime.now());
			}
			partnerDocumentRepository.save(partnerDocument);
		}
	}
	if(partner.getPartnerEmployees()!=null&&!partner.getPartnerEmployees().isEmpty()) {
		for(PartnerEmployee partnerEmployee:partner.getPartnerEmployees()) {
			partnerEmployee.setIsVendor("N");
			partnerEmployee.setIsClient("Y");
			partnerEmployeeRepository.save(partnerEmployee);
		}
		partnerHelper.processAuthPartner(partner.getPartnerEmployees());
	}
	
	 if (partner.getPartnerGsts()!=null&&!partner.getPartnerGsts().isEmpty()) {
		
			for(PartnerGst partnerGst:partner.getPartnerGsts()) {
				
				partnerGstRepository.save(partnerGst);
			}
		}
	return partner;	
}

@GetMapping("auth-client/{partnerEmpId}")
public List<PartnerEmployee> createAuthPartner(@PathVariable(value = "partnerEmpId")Integer partnerEmpId) {
	PartnerEmployee partnerEmployee = partnerEmployeeRepository.findById(partnerEmpId).get();
	partnerEmployee.setIsClient("Y");
	partnerEmployee.setIsVendor("N");
	List<PartnerEmployee> partnerEmployees = new ArrayList<PartnerEmployee>();
	partnerEmployees.add(partnerEmployee);
	partnerHelper.processAuthPartner(partnerEmployees);
	return partnerEmployees;
}

//@CrossOrigin(origins = "*")
@DeleteMapping("/{id}")
public void deletePartnerById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	partnerRepository.deleteById(id);
}

//@CrossOrigin(origins = "*")
@Transactional

@DeleteMapping("/{id}/delete/lines")
public void deletePartnerLines(@PathVariable(value = "id") int id) throws SmartOfficeException {
	Partner partner = getClientById(id).get();
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
	}else if (partner.getPartnerGsts()!=null&&!partner.getPartnerGsts().isEmpty()) {
		for(PartnerGst partnerGst:partner.getPartnerGsts()) {
			partnerGstRepository.delete(partnerGst.getId());
		}
	}
	else {
		throw new SmartOfficeException("No Client Specific Details Present");
	}
}

@GetMapping("/advance-filters")
public List<Partner> advanceFetchQuery(
		@RequestParam (value="clientCode",required=false)String clientCode,
		@RequestParam (value="companyName",required=false)String companyName,
		@RequestParam (value="countryName",required=false)String countryName){
	return partnerRepository.fetchByAdvanceFilter(clientCode, companyName,countryName);
}
}
