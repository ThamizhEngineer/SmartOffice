package com.ss.smartoffice.soservice.transaction.invoiceinterface;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.InvoiceForUpload;

@RestController
@RequestMapping("/int/invoice")
public class IntInvoiceService {
	
	@Autowired
	IntInvoiceHdrRepo hdrRepo;
	@Autowired
	IntInvoiceLineRepo lineRepo;
	@Autowired
	IntInvoiceHelper helper;
	
	@GetMapping("/hdrs")
	public Iterable<IntInvoiceHdr> fetchAllHdrs(){
		return hdrRepo.findAll();
	}
	
	@GetMapping("/lines")
	public Iterable<IntInvoiceLine> fetchAllLines(){
		return lineRepo.findAll();
	}
	
	@PostMapping("/start")
	public List<InvoiceForUpload> postExtractedData(@RequestBody List<InvoiceForUpload> extarctedData){
		helper.start(extarctedData);
		return extarctedData;
	}
	
}
