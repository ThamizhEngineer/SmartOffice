package com.ss.smartoffice.soservice.master.businessUnit;

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
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.Sequence;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path="master/business-units")
@Scope("prototype")
public class BusinessUnitService {
@Autowired
BusinessUnitRepository businessUnitRepository;
@Autowired
SequenceGenerationService sequenceGenerationService;
@Autowired
DivisionRepository divisionRepository;
@Autowired
DivisionGoodRepository divisionGoodRepository;
@Autowired
DivisionServiceRepository divisionServiceRepository;


//@Autowired
//CommonUtils commonUtils;




//@CrossOrigin(origins = "*")
@GetMapping

public Iterable<BusinessUnit>getBusinessUnits()throws SmartOfficeException{

	return businessUnitRepository.findAll();
	
}

//@CrossOrigin(origins = "*")
@GetMapping("/{id}")
public Optional<BusinessUnit>getBusinessUnitById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	

	return businessUnitRepository.findById(id);
	
	
}

//@CrossOrigin(origins = "*")
@PostMapping
public BusinessUnit addBusinessUnit(@RequestBody BusinessUnit businessUnit)throws SmartOfficeException{
//	System.out.println(businessUnit);
	HashMap<String, String> buisnessKeys = new HashMap<>();
	businessUnit.setBuCode(sequenceGenerationService.nextSeq("BUSINESS-UNIT",buisnessKeys ));
//	businessUnit.setBuCode(sequenceGenerationService.nextSequence("BUSINESS-UNIT").getOutput());
	System.out.println(businessUnit);

	return businessUnitRepository.save(businessUnit);
}

@GetMapping("/all-divisions")
public List<Division> getAllDivisions()throws SmartOfficeException{
	return divisionRepository.getAllDivision();
}

@GetMapping("/{id}/div")
public Optional<Division>getDivisionsById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return divisionRepository.findById(id);	
}

@PostMapping("/division")
public Division addDivision(@RequestBody Division division)throws SmartOfficeException{
	System.out.println(division);
//	businessUnit.setBuCode(sequenceGenerationService.nextSequence("BUSINESS-UNIT").getOutput());
	return divisionRepository.save(division);
}

@PatchMapping("/{id}/patch-division")
public Division updateDivision(@RequestBody Division division)throws SmartOfficeException{
	System.out.println("patch"+" "+division);
	return divisionRepository.save(division);	
}

@DeleteMapping("/{id}/delete-division")
public void deleteDivision(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	System.out.println(id);
	divisionRepository.deleteById(id);
}

//@CrossOrigin(origins = "*")
@PatchMapping("/{id}/lines")
public BusinessUnit addOrUpdateBusinessUnit(@RequestBody BusinessUnit businessUnit)throws SmartOfficeException{
	if(businessUnit.getDivisions()!=null&&!businessUnit.getDivisions().isEmpty()) {
		for(Division division:businessUnit.getDivisions()) {
			divisionRepository.save(division);
		}
	}else {
		throw new SmartOfficeException("Divisions Cannot be Empty");
	}
	
	return businessUnit;
	
}
//@CrossOrigin(origins = "*")
@Transactional

@DeleteMapping("/{id}/delete/lines")
public void deleteBusinessUnitLines(@PathVariable(value = "id") int id) throws SmartOfficeException {
	BusinessUnit businessUnit=getBusinessUnitById(id).get();
	if(businessUnit.getDivisions()!=null&&!businessUnit.getDivisions().isEmpty()) {
		for(Division division:businessUnit.getDivisions()) {
			divisionRepository.delete(division.getId());
		}
	}else {
		throw new SmartOfficeException("No Divisions Present ");
	}
}




//@CrossOrigin(origins = "*")
@PatchMapping("/{id}")
public BusinessUnit updateBusinessUnit(@RequestBody BusinessUnit businessUnit)throws SmartOfficeException{
	return businessUnitRepository.save(businessUnit);
	
}
//@CrossOrigin(origins = "*")
@DeleteMapping("/{id}")
public void deleteBusinessUnitById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	businessUnitRepository.deleteById(id);
}

@GetMapping("/get-divisionGood")
public Iterable<DivisionGood>getDivisionGood()throws SmartOfficeException{
	System.out.println("get"+" "+divisionGoodRepository);
	return divisionGoodRepository.findAll();
}

@GetMapping("/{id}/getall-divisionGood")
public Optional<DivisionGood>getDivisionGoodById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return divisionGoodRepository.findById(id);	
}

@PostMapping("/divisionGood")
public DivisionGood addDivisionGood(@RequestBody DivisionGood divisionGood)throws SmartOfficeException{
	System.out.println(divisionGood);
//	businessUnit.setBuCode(sequenceGenerationService.nextSequence("BUSINESS-UNIT").getOutput());
	return divisionGoodRepository.save(divisionGood);
}

@PatchMapping("/{id}/pacth-divisionGoods")
public DivisionGood updateDivisionGood(@RequestBody DivisionGood divisionGood)throws SmartOfficeException{
	System.out.println("patch"+" "+divisionGood);
	return divisionGoodRepository.save(divisionGood);	
}

@DeleteMapping("/{id}/delete-divisionGoods")
public void deleteDivisionGoods(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	System.out.println(id);
	divisionGoodRepository.deleteById(id);
}

@GetMapping("/get-DivisionService")
public Iterable<DivisionService>getDivisionService()throws SmartOfficeException{
	return divisionServiceRepository.findAll();	
}

@GetMapping("/{id}/getall-DivisionService")
public Optional<DivisionService>getDivisionServiceById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return divisionServiceRepository.findById(id);	
}

@PostMapping("/divisionService")
public DivisionService addDivisionService(@RequestBody DivisionService divisionService)throws SmartOfficeException{
	System.out.println(divisionService);
//	businessUnit.setBuCode(sequenceGenerationService.nextSequence("BUSINESS-UNIT").getOutput());
	return divisionServiceRepository.save(divisionService);
}

@PatchMapping("/{id}/patch-divisionService")
public DivisionService updateDivisionService(@RequestBody DivisionService divisionService)throws SmartOfficeException{
	System.out.println("patch"+" "+divisionService);
	return divisionServiceRepository.save(divisionService);	
}

@DeleteMapping("/{id}/delete-divisionService")
public void deleteDivisionService(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	System.out.println(id);
	divisionServiceRepository.deleteById(id);
}
}