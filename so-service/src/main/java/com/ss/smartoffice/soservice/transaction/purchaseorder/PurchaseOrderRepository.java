package com.ss.smartoffice.soservice.transaction.purchaseorder;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.soservice.transaction.model.PurchaseOrder;
@Scope("prototype")
public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder, Integer>{

	Page<PurchaseOrder> findByVendorId(Pageable pageable,@Param("vendorId")Integer vendorId);
	Page<PurchaseOrder> findByPoDt(Pageable pageable,@Param("poDt") LocalDate poDt);
	Page<PurchaseOrder> findByVendorIdAndPoDt(Pageable pageable,@Param("vendorId") Integer vendorId,@Param("poDt")LocalDate podt);
	
	@Query("SELECT new com.ss.smartoffice.soservice.transaction.model.PurchaseOrder (purchase.id,purchase.vendorId,purchase.poCode,purchase.poDt,purchase.netPoAmt) FROM PurchaseOrder purchase ORDER By purchase.poDt")
	List<PurchaseOrder>findBySummaries();
}
