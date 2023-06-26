package com.ss.smartoffice.soservice.transaction.purchaseorder;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.PurchaseOrderLine;
@Scope("prototype")
public interface PurchaseOrderLineRepository extends CrudRepository<PurchaseOrderLine, Integer>{

}
