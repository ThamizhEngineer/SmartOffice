package com.ss.smartoffice.soservice.transaction.saleorder;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.SaleGood;
@Scope("prototype")
public interface SaleGoodRepository extends CrudRepository<SaleGood, Integer> {

}
