package com.ss.smartoffice.soservice.master.pay.CompanyPayCycle;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface CompanyPayCycleRepository extends CrudRepository<CompanyPayCycle, Integer>{
	List<CompanyPayCycle> findByFiscalYearId(String fiscalYearId);

}
