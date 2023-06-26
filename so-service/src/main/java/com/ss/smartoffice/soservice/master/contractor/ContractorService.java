package com.ss.smartoffice.soservice.master.contractor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.BankDetails;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.contractor.ContractorService;
import com.ss.smartoffice.soservice.master.employee.BankDetailsRepository;
import com.ss.smartoffice.soservice.master.employee.EmpCodeGeneration;


@RestController
@RequestMapping("master/contractors")
@Scope("prototype")
public class ContractorService {
	
	
	@Autowired
	ContractorRepository contractorRepository;
	
	@Autowired
	BankDetailsRepository bankDetailsRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	EmpCodeGeneration empCodeGeneration;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@GetMapping
	public Iterable<Contractor> getAllContractors()throws SmartOfficeException{
		return contractorRepository.findContractorSummaries();
	}
	@GetMapping("/{id}")
	public Contractor getContractorById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		Contractor contractorById= new Contractor();
		Contractor allContractors= contractorRepository.findById(id).get();
		if(allContractors.getEmpTypeCode().equals("CONTRACTOR")) {
			contractorById=allContractors;
		}else {
			throw new SmartOfficeException("No Contractor found");
		}
		return contractorById;
	}
	
	@PostMapping
	public Contractor addContractor(@RequestBody Contractor contractor)throws SmartOfficeException{
		contractor.setEmpTypeCode("CONTRACTOR");
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		System.out.println(year);
		contractor.setCreatedDt(now);
		contractor.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		contractor.setContractorCode(empCodeGeneration.contractorCodeGeneration(contractor));
		contractor.setInternalId(year+"-"+sequenceGenerationService.nextSequence("INTERNAL-CONTRACTOR-ID").getOutput());
		if(contractor.getInternalId()!=null&&!contractor.getInternalId().isEmpty()) {
			contractor = contractorRepository.save(contractor);
		}
		return contractor;
	}
	
	@PatchMapping("/{id}/update")
	public Contractor updateContractor(@RequestBody Contractor contractor,@PathVariable(value="id")Integer id)throws SmartOfficeException{
		Contractor newContractor = new Contractor();
		Contractor contractorById= contractorRepository.findById(id).get();
		if(!contractorById.getOfficeId().equals(contractor.getOfficeId())) {
			if(!contractorById.getCountryId().equals(contractor.getCountryId())) {
				contractor.setContractorCode(empCodeGeneration.contractorCodeGeneration(contractor));
			}
			else {
				contractor.setContractorCode(contractorById.getContractorCode());
			}
		}
		return contractorRepository.save(contractor);
	}
	@PatchMapping("/{id}/update-lines")
	public Contractor addOrUpdateContractorLines(@RequestBody Contractor contractor) throws SmartOfficeException {

		if (contractor.getBankDetails() != null && !contractor.getBankDetails().isEmpty())

			for (BankDetails bankDetail : contractor.getBankDetails()) {
               
                	  
				bankDetailsRepository.save(bankDetail);
  			}
		return contractor;
	}

	
	@DeleteMapping("/{id}")
	public void deleteContractor(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		contractorRepository.deleteById(id);
	}
	
	@DeleteMapping("/{id}/delete-lines")
	public void deleteContractorDetails(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		bankDetailsRepository.deleteById(id);
	}

}
