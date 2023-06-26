package com.ss.smartoffice.soservice.seed.IncomeTaxConfig;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface IncomeTaxRepository extends CrudRepository<IncomeTax , Integer>{
	List<IncomeTax> findByFiscalYearId(String fiscalYearId);

}
