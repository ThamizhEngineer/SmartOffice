package com.ss.smartoffice.soservice.transaction.invoice.invoicePayment;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.transaction.invoice.InvoiceService;

@RestController
@RequestMapping("/transaction/invoice-payment")
public class InvoicePaymentService {

	@Autowired
	InvoicePaymentHdrRepo invoicePaymentHdrRepo;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	CommonUtils commonUtils; 
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@GetMapping
	public Iterable<InvoicePaymentHdr> getAllInvoicePayment() throws SmartOfficeException{
		return invoicePaymentHdrRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<InvoicePaymentHdr> getInvoicePaymetById(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		return invoicePaymentHdrRepo.findById(id);
	}
	
	@GetMapping("/by-client/{clientId}")
	public List<InvoicePaymentHdr> fetchByClientId(@PathVariable(value = "clientId")String clientId){
		return invoicePaymentHdrRepo.findByClientId(clientId);
	}
	
	@PostMapping
	public InvoicePaymentHdr createInvoicePayment(@RequestBody InvoicePaymentHdr paymenthdr) throws SmartOfficeException{
		HashMap<String, String> buisnessKeys = new HashMap<>();
		paymenthdr.setPaymentCode(sequenceGenerationService.nextSeq("INVOICE-PAYMENT", buisnessKeys)); 
		paymenthdr.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		paymenthdr.setCreatedDt(LocalDateTime.now());
		invoiceService.updateBill(paymenthdr.getPaymentLine());
		return invoicePaymentHdrRepo.save(paymenthdr);
	}
	
	@PatchMapping("/{id}")
	public InvoicePaymentHdr updateInvoicePayment(@RequestBody InvoicePaymentHdr paymenthdr) throws SmartOfficeException{
		paymenthdr.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		paymenthdr.setModifiedDt(LocalDateTime.now());
		invoiceService.updateBill(paymenthdr.getPaymentLine());
		return invoicePaymentHdrRepo.save(paymenthdr);
	}
	
	@DeleteMapping("/{id}")
	public void deleteInvoicePayment(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		invoicePaymentHdrRepo.deleteById(id);
	}
	
}
