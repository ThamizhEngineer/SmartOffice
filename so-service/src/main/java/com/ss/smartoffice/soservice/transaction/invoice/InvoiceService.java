package com.ss.smartoffice.soservice.transaction.invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.transaction.invoice.invoicePayment.InvoicePaymentLine;

@RestController
@RequestMapping("/transaction/invoice")
public class InvoiceService {
	
	@Autowired
	InvoiceHdrRepo hdrRepo;
	
	@Autowired
	InvoiceLineRepo lineRepo;
	
	@Autowired
	InvoiceGenerator invoiceGnerator;
	
	@Autowired 
	InvoiceEventGenerator invoiceEventGenerator;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	InvoiceHelperService invoiceHelperService;
	
	ArrayList<String> knownActions = new ArrayList<String>(Arrays.asList("create", "submit", "dir-approve","void"));
	
	@GetMapping
	public Iterable<InvoiceHdr> fetchAll(){
		return hdrRepo.findAll();
	}
	
	@GetMapping("/lines")
	public Iterable<InvoiceLine> fetchInvoiceLInes(){
		return lineRepo.findAll();
	}
	
	@PostMapping("/create-invoice")
	public InvoiceHdr createInvoice(@RequestBody InvoiceHdr invoiceHdr) throws SmartOfficeException {
		invoiceHelperService.processInvoice("create", invoiceHdr);
		return invoiceHdr;		
	}
	
	@PatchMapping("/{id}/{action}")
	public InvoiceHdr updateInvoice(@PathVariable(value = "id")Integer id,@PathVariable(value = "action")String action,@RequestBody InvoiceHdr invoiceHdr) throws SmartOfficeException {
		if (knownActions.contains(action)) {
			invoiceHelperService.processInvoice(action, invoiceHdr);
			return invoiceHdr;
		}else {
			throw new SmartOfficeException("Invalid Url");
		}			
	}
	
	@GetMapping("/exchange-rate/{fromCurrId}/{toCurrId}")
	public float findExchangeRate(@PathVariable(value = "fromCurrId")String fromCurrId,@PathVariable(value = "toCurrId")String toCurrId) {
		return commonUtils.findExchangeRate(fromCurrId, toCurrId);
	}
	
	
	@GetMapping("/{id}")
	public InvoiceHdr fetchById(@PathVariable(value = "id") Integer id){
		InvoiceHdr hdr = hdrRepo.findById(id).get();
		List<InvoiceLine> lines = lineRepo.findByInvoiceHdrId(hdr.getId().toString());
		if (lines!=null && !lines.isEmpty()) {
			hdr.setInvoiceLines(lines);
		} else {
			throw new SmartOfficeException("Empty line");
		}
		return hdr;
	}
	
	@GetMapping("/_internal/{id}")
	public InvoiceHdr fetchByIdInternal(@PathVariable(value = "id") Integer id){
		InvoiceHdr hdr = hdrRepo.findById(id).get();
		List<InvoiceLine> lines = lineRepo.findByInvoiceHdrId(hdr.getId().toString());
		if (lines!=null && !lines.isEmpty()) {
			hdr.setInvoiceLines(lines);
		} else {
			throw new SmartOfficeException("Empty line");
		}
		return hdr;
	}
	
	@PostMapping("/_internal/{id}/doc-id")
	public InvoiceHdr updateInvoicePdf(@PathVariable(value="id")Integer id,@RequestBody InvoiceHdr invoiceHdr) throws SmartOfficeException{		
		InvoiceHdr invoiceHdrFromDB = hdrRepo.findById(id).get();
		invoiceHdrFromDB.setPdfDocId(invoiceHdr.getPdfDocId());
		return hdrRepo.save(invoiceHdrFromDB);
	}
	
	@PatchMapping("/update-doc/{id}")
	public InvoiceHdr updateInvoiceDocId(@PathVariable(value="id")Integer id,@RequestBody InvoiceHdr invoiceHdr) throws SmartOfficeException{		
		InvoiceHdr invoiceHdrFromDB = hdrRepo.findById(id).get();
		invoiceHdrFromDB.setDocId(invoiceHdr.getDocId());
		invoiceHdrFromDB.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		invoiceHdrFromDB.setModifiedDate(LocalDateTime.now());
		return hdrRepo.save(invoiceHdrFromDB);
	}
	
	@GetMapping("/by-client/{clientId}")
	public List<InvoiceHdr> fetchByCientId(@PathVariable(value = "clientId") String clientId){
		return hdrRepo.findByClientId(clientId);
	}
	
	@GetMapping("/line/{id}")
	public Optional<InvoiceLine> fetchBylineId(@PathVariable(value = "id") Integer id){
		return lineRepo.findById(id);
	}
	
	@GetMapping("/{clientId}/checkBalance")
	public Iterable<InvoiceHdr> checkBalance(@PathVariable(value = "clientId") String clientId) throws SmartOfficeException{
		return hdrRepo.getunPaidInvoice(clientId);
	}
	@PatchMapping("/refresh")
	public Iterable<InvoiceHdr> updateBill(@RequestBody List<InvoicePaymentLine> paymentLine) throws SmartOfficeException{
		List<InvoiceHdr> invoiceHdrs = new ArrayList<InvoiceHdr>();
		for(InvoicePaymentLine payment:paymentLine) {
			InvoiceHdr invoiceHdr = new InvoiceHdr();
			invoiceHdr=hdrRepo.findById(Integer.parseInt(payment.getInvoiceHdrId())).get();
			invoiceHdr.setPaidAmt(invoiceHdr.getPaidAmt().add(invoiceGnerator.toBigDecimal(payment.getPaidAmt().toString())));
			invoiceHdr.setBalanceAmt(invoiceHdr.getBalanceAmt().subtract(BigDecimal.valueOf(payment.getPaidAmt())));
			invoiceHdrs.add(invoiceHdr);
		}
		return hdrRepo.saveAll(invoiceHdrs);
	}

}
