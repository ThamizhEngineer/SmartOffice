package com.ss.smartoffice.sodocumentservice.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.sodocumentservice.model.InvoiceHdr;
import com.ss.smartoffice.sodocumentservice.model.PurchaseOrder;

@RestController
@RequestMapping(path="document/invoice-pdf")
public class InvoicePdfService {
	
	@Value("${invoice-pdf.url}")
	private String invoicePdfUrl;

	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	InvoicePdfGenerator invoicePdfGenerator;
	
	
	@GetMapping("/{id}/generate-pdf")
	public void generateInvoicePdf(@PathVariable(value="id")int id) throws SmartOfficeException{
		
		InvoiceHdr invoiceHdr = new InvoiceHdr();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",commonUtils.getLoggedinAppToken() ); 
		
		HttpEntity<InvoiceHdr> request = new HttpEntity<InvoiceHdr>(invoiceHdr,headers);
		ResponseEntity<InvoiceHdr> invoiceHdrEntity = commonUtils.getRestTemplate().exchange(invoicePdfUrl+ id,
				HttpMethod.GET, request,InvoiceHdr.class);
		InvoiceHdr invoiceHdrFromRest = invoiceHdrEntity.getBody();
		invoicePdfGenerator.generatePdf(invoiceHdrFromRest);
		
	}

	
}
