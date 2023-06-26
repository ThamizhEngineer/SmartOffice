package com.ss.smartoffice.soservice.transaction.appraisalservice;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.seed.fiscalYear.FiscalYear;
import com.ss.smartoffice.soservice.seed.fiscalYear.FiscalYearRepository;

@RestController
@RequestMapping("transaction/appraisal-setting")
@Scope("prototype")
public class AppraisalSettingService {
	
	@Autowired
	AppraisalSettingRepo appraisalSettingRepo;
	
	@Autowired
	FiscalYearRepository fiscalYearRepository;
	
	@GetMapping
	public Iterable<AppraisalSetting> getAllAppraisal() throws SmartOfficeException{
		return appraisalSettingRepo.findAll();
	}
	
//	@GetMapping("{fiscalYearId}/fiscal-year-id")
//	public List<AppraisalSetting> getAppraisalById(@PathVariable(value="fiscalYearId")String fiscalYearId) throws SmartOfficeException{
//		return appraisalSettingRepo.findByFiscalYearId(fiscalYearId);
//	}
	
	@GetMapping("{fiscalYearId}/fiscal-year-id")
	public Optional<AppraisalSetting> getAppraisalById(@PathVariable(value="fiscalYearId")String fiscalYearId) throws SmartOfficeException{
		return appraisalSettingRepo.findByFiscalYearId(fiscalYearId);
	}
	
	@PatchMapping("{id}/update")
	public AppraisalSetting updateByFiscId(@PathVariable(value="id") String id,
			@RequestBody AppraisalSetting appraisalSetting) throws SmartOfficeException{
		Optional<AppraisalSetting> appFromDb = appraisalSettingRepo.findById(appraisalSetting.getId());
		Optional<FiscalYear> fiscYearFromDb = fiscalYearRepository.findById(Integer.parseInt(appraisalSetting.getFiscalYearId()));
		if(appFromDb.isPresent()) {
			appFromDb.get().setFiscalCode(fiscYearFromDb.get().getFiscalCode());
			appFromDb.get().setMinOrgGoalsMm(appraisalSetting.getMinOrgGoalsMm());
			appFromDb.get().setMinOrgGoalsUm(appraisalSetting.getMinOrgGoalsUm());
			appFromDb.get().setMinOrgGoalsOthers(appraisalSetting.getMinOrgGoalsOthers());
		}
		return appraisalSettingRepo.save(appFromDb.get());
	}
	
	@PostMapping("/create")
	public AppraisalSetting createAppraisalSetting(@RequestBody AppraisalSetting appraisalSetting) throws SmartOfficeException{
		Optional<FiscalYear> fiscYearFromDb = fiscalYearRepository.findById(Integer.parseInt(appraisalSetting.getFiscalYearId()));
		if(appraisalSetting!=null) {
			appraisalSetting.setFiscalCode(fiscYearFromDb.get().getFiscalCode());
		}
		return appraisalSettingRepo.save(appraisalSetting);
	}

}
