package com.ss.smartoffice.soservice.transaction.invoiceinterface;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IntInvoiceLineRepo extends CrudRepository<IntInvoiceLine, Integer> {

	//List<IntInvoiceLine> findByIInvoiceHdrId(String iInvoiceHdrId);
	
	@Query("select x from com.ss.smartoffice.soservice.transaction.invoiceinterface.IntInvoiceLine x where x.iInvoiceHdrId=:iInvoiceHdrId and x.isValid='Y'")
	List<IntInvoiceLine> fetchValidRecordsByIinvoiceHdrId(@Param("iInvoiceHdrId") String iInvoiceHdrId);
	
}
