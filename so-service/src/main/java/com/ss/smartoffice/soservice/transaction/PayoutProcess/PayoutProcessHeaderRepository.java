package com.ss.smartoffice.soservice.transaction.PayoutProcess;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface PayoutProcessHeaderRepository extends CrudRepository<PayoutProcessHeader, Integer>{
	
	@Query("select pay from com.ss.smartoffice.soservice.transaction.PayoutProcess.PayoutProcessHeader pay ORDER BY pay.id DESC")
	Iterable<PayoutProcessHeader> payoutProcessHeaderByDesc();
}
