package com.ss.smartoffice.soservice.transaction.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceHdrRepo extends CrudRepository<InvoiceHdr, Integer> {

	@Query("Select invoice from com.ss.smartoffice.soservice.transaction.invoice.InvoiceHdr invoice where invoice.clientId=:clientId And invoice.balanceAmt<>0")
	List<InvoiceHdr> getunPaidInvoice(@Param("clientId") String clientId);
	
	List<InvoiceHdr> findByClientId(String clientId);
}
