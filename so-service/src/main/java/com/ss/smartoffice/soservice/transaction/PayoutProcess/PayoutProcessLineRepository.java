package com.ss.smartoffice.soservice.transaction.PayoutProcess;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface PayoutProcessLineRepository extends CrudRepository<PayoutProcessLine, Integer> {
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.PayoutProcess.PayoutProcessLine payLine where payLine.id= ?1")
	void delete(int id);
	
	@Query("select  payLine.id from com.ss.smartoffice.soservice.transaction.PayoutProcess.PayoutProcessLine payLine where payLine.payoutProcessHdrId=:payoutProcessHdrId")
	List<Integer> findByPayoutProcessHdrId(Integer payoutProcessHdrId);

}
