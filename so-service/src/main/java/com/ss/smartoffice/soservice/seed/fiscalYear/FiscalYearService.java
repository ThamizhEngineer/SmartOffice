package com.ss.smartoffice.soservice.seed.fiscalYear;

import java.util.HashMap;
import java.util.Optional;

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
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.pay.CompanyPayCycle.CompanyPayCycleRepository;
import com.ss.smartoffice.soservice.master.pay.CompanyPayCycle.CompanyPayCycleService;

@RestController
@RequestMapping("seed/fiscal-years")
@Scope("prototype")
public class FiscalYearService {
	@Autowired
	FiscalYearRepository fiscalYearRepository;
	@Autowired
	CompanyPayCycleRepository companyPayCycleRepository;
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@Autowired
	CompanyPayCycleService companyPayCycleService;

	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<FiscalYear> getFiscalYear() throws SmartOfficeException {
		return fiscalYearRepository.findAll();

	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<FiscalYear> getFiscalYearById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		return fiscalYearRepository.findById(id);

	}

	//@CrossOrigin(origins = "*")
	@PostMapping
	public FiscalYear addFiscalYear(@RequestBody FiscalYear fiscalYear) throws SmartOfficeException {
//		HashMap<String, String> buisnessKeys = new HashMap<>();
//		fiscalYear.setFiscalCode(sequenceGenerationService.nextSequence("FiscalYear-Code").getOutput());
//		fiscalYear.setFiscalCode(sequenceGenerationService.nextSeq("FISCAL-YEAR-CODE", buisnessKeys));
		System.out.println(fiscalYear);
		return fiscalYearRepository.save(fiscalYear); 
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public FiscalYear updateFiscalYear(@RequestBody FiscalYear fiscalYear) throws SmartOfficeException {
		return fiscalYearRepository.save(fiscalYear);

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteFiscalYear(@PathVariable(value = "id") int id) throws SmartOfficeException {
		fiscalYearRepository.deleteById(id);

	}

}
