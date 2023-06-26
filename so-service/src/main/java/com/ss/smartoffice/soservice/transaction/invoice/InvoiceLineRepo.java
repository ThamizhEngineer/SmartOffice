package com.ss.smartoffice.soservice.transaction.invoice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface InvoiceLineRepo extends CrudRepository<InvoiceLine, Integer> {
	
	List<InvoiceLine> findByInvoiceHdrId(String invoiceHdrId);
	

}
