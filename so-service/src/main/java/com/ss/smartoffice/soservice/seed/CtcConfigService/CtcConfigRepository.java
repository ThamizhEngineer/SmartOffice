package com.ss.smartoffice.soservice.seed.CtcConfigService;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CtcConfigRepository extends CrudRepository<CtcConfig, Integer>{
	List<CtcConfig> findByFiscalYearId(String fiscalYearId);
	List<CtcConfig> findByFiscalYearIdAndHeaderConfigCode(String fiscalYearId,String headerConfigCode);

}
