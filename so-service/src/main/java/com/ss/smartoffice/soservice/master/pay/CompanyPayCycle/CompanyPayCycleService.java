package com.ss.smartoffice.soservice.master.pay.CompanyPayCycle;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("master/pay/comp-pay-cycles")
@Scope("prototype")
public class CompanyPayCycleService {

	@Autowired
	CompanyPayCycleRepository companyPayCycleRepository;
	@Autowired
	CompanyPayCycleLinesRepository companyPayCycleLinesRepository;
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<CompanyPayCycle> getCompanyCycle(
			@RequestParam(value = "fiscalYearId", required = false) String fiscalYearId) throws SmartOfficeException {
		boolean searchByFiscalYearId = false;
		
	
		if (fiscalYearId != null)
			searchByFiscalYearId = true;
		if (searchByFiscalYearId) {
			return companyPayCycleRepository.findByFiscalYearId(fiscalYearId);
		}

		return companyPayCycleRepository.findAll();

	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<CompanyPayCycle> getCompanyCycleById(@PathVariable(value = "id") Integer id)
			throws SmartOfficeException {
		return companyPayCycleRepository.findById(id);

	}


	//@CrossOrigin(origins = "*")
	@PostMapping
	public CompanyPayCycle addCompanyCycleById(@RequestBody CompanyPayCycle companyPayCycle)
			throws SmartOfficeException {
		if (companyPayCycle.getPayCycleType().equals("REGULAR-SALARY")
				|| companyPayCycle.getPayCycleType().equals("SITE-ALLOWANCE")
				|| companyPayCycle.getPayCycleType().equals("EXPENSE-CLAIM")
				|| companyPayCycle.getPayCycleType().equals("BONUS")) {
			return companyPayCycleRepository.save(companyPayCycle);

		} else {
			throw new SmartOfficeException("PayCycleType Is Invalid");
		}

	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public CompanyPayCycle updateCompanyCycleById(@RequestBody CompanyPayCycle companyPayCycle)
			throws SmartOfficeException {
		return companyPayCycleRepository.save(companyPayCycle);

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteByCompanyCycleById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		companyPayCycleRepository.deleteById(id);
	}

	//@CrossOrigin(origins = "*")
	@PostMapping("/{id}/lines")
	public CompanyPayCycle addCompanyPayCycleLines(@RequestBody CompanyPayCycle companyPayCycle)
			throws SmartOfficeException {
		if (companyPayCycle.getCompanyPayCycleLines() != null) {
			for (CompanyPayCycleLines comPayLoop : companyPayCycle.getCompanyPayCycleLines()) {
				companyPayCycleLinesRepository.save(comPayLoop);
			}
		} else {
			throw new SmartOfficeException("CompanyPayCycleLine Is Empty ");
		}
		return companyPayCycle;
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/multi-update")
	public Iterable<CompanyPayCycle> bulkUpdate(@RequestBody List<CompanyPayCycle> companyPayCycleList)
			throws SmartOfficeException {
		return (Iterable<CompanyPayCycle>) companyPayCycleRepository.saveAll(companyPayCycleList);

	}

//	private CompanyPayCycleLines addingNewRecord(CompanyPayCycleLines cPayCycleLine) {
//		// This Service will Add New Record When There is no Existing Record
//
//		CompanyPayCycleLines companyPayCycleLines = new CompanyPayCycleLines();
//		companyPayCycleLines.setCompPayCycleId(cPayCycleLine.getCompPayCycleId());
//		companyPayCycleLines.setCompPayCycleLineCode(cPayCycleLine.getCompPayCycleLineCode());
//		companyPayCycleLines.setFromDt(cPayCycleLine.getFromDt());
//		companyPayCycleLines.setToDt(cPayCycleLine.getToDt());
//		companyPayCycleLines.setPayDt(cPayCycleLine.getPayDt());
//		companyPayCycleLines.setProcessedDate(cPayCycleLine.getProcessedDate());
//		return companyPayCycleLines;
//	}
//
//	private CompanyPayCycleLines matchAndUpdateFields(CompanyPayCycleLines companyPayCycleLineFromDB,
//			CompanyPayCycleLines cPayCycleLine) {
//		// only update relevant fields to the db-record
//
//		companyPayCycleLineFromDB.setId(cPayCycleLine.getId());
//		companyPayCycleLineFromDB.setCompPayCycleId(cPayCycleLine.getCompPayCycleId());
//		companyPayCycleLineFromDB.setCompPayCycleLineCode(cPayCycleLine.getCompPayCycleLineCode());
//		companyPayCycleLineFromDB.setFromDt(cPayCycleLine.getFromDt());
//		companyPayCycleLineFromDB.setToDt(cPayCycleLine.getToDt());
//		companyPayCycleLineFromDB.setPayDt(cPayCycleLine.getPayDt());
//		companyPayCycleLineFromDB.setProcessedDate(cPayCycleLine.getProcessedDate());
//
//		return companyPayCycleLineFromDB;
//	}
//	
//	
	
}
