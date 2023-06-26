package com.ss.smartoffice.soservice.transaction.saleorder;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.SaleService;
@Scope("prototype")
public interface SaleServiceRepository extends CrudRepository<SaleService, Integer>{

}
