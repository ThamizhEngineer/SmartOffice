package com.ss.smartoffice.soservice.transaction.appraisalservice;


import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface AppraisalSettingRepo extends CrudRepository<AppraisalSetting, Integer>{
	
//	List<AppraisalSetting> findByFiscalYearId(String fiscalYearId);
	Optional<AppraisalSetting> findByFiscalYearId(String fiscalYearId);

}
