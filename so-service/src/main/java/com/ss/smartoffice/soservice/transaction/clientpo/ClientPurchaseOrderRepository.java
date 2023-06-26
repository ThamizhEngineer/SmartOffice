package com.ss.smartoffice.soservice.transaction.clientpo;


import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@Scope("prototype")
public interface ClientPurchaseOrderRepository extends PagingAndSortingRepository<ClientPurchaseOrder, Integer> {

	 Page<ClientPurchaseOrder> findByClientId(Pageable pageable,String clientId);
	 Page<ClientPurchaseOrder> findByPoDate(Pageable pageable,String poDate);
	 Page<ClientPurchaseOrder> findByClientIdAndPoDate(Pageable pageable,String clientId,String poDate);
}
