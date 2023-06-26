package com.ss.smartoffice.soservice.seed.fiscalYear;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface FiscalYearRepository extends CrudRepository<FiscalYear, Integer>{
	

}
