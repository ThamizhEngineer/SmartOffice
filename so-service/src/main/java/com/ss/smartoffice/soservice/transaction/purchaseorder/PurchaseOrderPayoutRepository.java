package com.ss.smartoffice.soservice.transaction.purchaseorder;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.soservice.transaction.model.PurchaseOrderPayout;
@Scope("prototype")
public interface PurchaseOrderPayoutRepository extends CrudRepository<PurchaseOrderPayout, Integer>{

	List<PurchaseOrderPayout> findByVendorId(@Param("vendorId")String vendorId);
}
