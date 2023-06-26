package com.ss.smartoffice.soservice.master.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.Config;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.contractor.Contractor;
import com.ss.smartoffice.soservice.master.contractor.ContractorRepository;
import com.ss.smartoffice.soservice.master.offices.Office;
import com.ss.smartoffice.soservice.master.offices.OfficeService;
import com.ss.smartoffice.soservice.seed.configs.ConfigService;

@Service
public class EmpCodeGeneration {
@Autowired
EmployeeRepository employeeRepository;
@Autowired
ContractorRepository contractorRepository;

@Autowired
OfficeService officeService;
@Autowired
ConfigService configService;
@Autowired
SequenceGenerationService sequenceGenerationService;

public String empCodeGeneration(@RequestBody memployee employee)throws SmartOfficeException{
	return sequenceGenerationService.generateEmployeeCode();
	
}

public String empCodeGenerationByOffice(@RequestBody memployee employee)throws SmartOfficeException{
	String autoEmpCode=null;
	
	if(employee.getOfficeId()!=null) {
		
		Office officeById=officeService.getOfficeById(employee.getOfficeId()).get();
		Integer lastEmpSequence=Integer.parseInt(officeById.getLastEmpSequence());
		Integer updatedLastEmpSequence=lastEmpSequence+1;
		String officeAbbreviation=officeById.getOfficeAbbreviation();
		autoEmpCode=officeAbbreviation+updatedLastEmpSequence;
		employee.setEmpCode(autoEmpCode);
		officeById.setLastEmpSequence(String.valueOf(updatedLastEmpSequence));
		officeService.updateOfficeById(officeById);
		employeeRepository.save(employee);
		
	}else {
		throw new SmartOfficeException("without Office Name Code cannot be generated");
	}
	//System.out.println(autoEmpCode);
	return autoEmpCode;
	
}

public String contractorCodeGeneration(@RequestBody Contractor contractor)throws SmartOfficeException{
	String autoEmpCode=null;
	if(contractor.getOfficeId()!=null) {
		
		Office officeById=officeService.getOfficeById(contractor.getOfficeId()).get();
		Integer lastEmpSequence=Integer.parseInt(officeById.getLastEmpSequence());
		Integer updatedLastEmpSequence=lastEmpSequence+1;
		String officeAbbreviation=officeById.getOfficeAbbreviation();
		autoEmpCode=officeAbbreviation+updatedLastEmpSequence;
		contractor.setContractorCode(autoEmpCode);
		officeById.setLastEmpSequence(String.valueOf(updatedLastEmpSequence));
		officeService.updateOfficeById(officeById);
		contractorRepository.save(contractor);
		
	}else {
		throw new SmartOfficeException("without Office Name Contractor Code cannot be generated");
	}
	//System.out.println(autoEmpCode);
	return autoEmpCode;
	
}

}
