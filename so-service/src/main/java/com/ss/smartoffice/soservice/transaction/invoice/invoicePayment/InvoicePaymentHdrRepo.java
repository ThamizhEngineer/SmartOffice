package com.ss.smartoffice.soservice.transaction.invoice.invoicePayment;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface InvoicePaymentHdrRepo extends CrudRepository<InvoicePaymentHdr, Integer> {

	List<InvoicePaymentHdr> findByClientId(String clientId);
	
}
