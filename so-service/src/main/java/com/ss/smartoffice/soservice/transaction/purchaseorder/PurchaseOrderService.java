package com.ss.smartoffice.soservice.transaction.purchaseorder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.Processlog.ProcessLog;
import com.ss.smartoffice.shared.Processlog.ProcessLogService;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.Sequence;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.Attachment;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.PurchaseOrder;
import com.ss.smartoffice.soservice.transaction.model.PurchaseOrderLine;
import com.ss.smartoffice.soservice.transaction.model.PurchaseOrderPayout;
@RestController
@RequestMapping(path="transaction/purchase-orders")
@Scope("prototype")
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	PurchaseOrderLineRepository purchaseOrderLineRepository;
	
	@Autowired
	PurchaseOrderPayoutRepository purchaseOrderPayoutRepository;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@Autowired
	DocumentManagementHelper documentManagementHelper;

	@Autowired
	ProcessLogService processLogService;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	EventPublisherService eventPublisherService;
//@CrossOrigin(origins="*")
@GetMapping()
public Iterable<PurchaseOrder> getPurchaseOrders(Model model, Pageable pageable,
		@RequestParam(value = "poDt",required = false) String poDt)
		throws Exception {
	
	Page<PurchaseOrder> pages = orderDetails(pageable,poDt);
       model.addAttribute("number", pages.getNumber());
       model.addAttribute("totalPages", pages.getTotalPages());
       model.addAttribute("totalElements", pages.getTotalElements());	
       model.addAttribute("size", pages.getSize());
       model.addAttribute("orderDetails", pages.getContent());
        return pages;
}


private Page<PurchaseOrder> orderDetails(Pageable pageable, String poDt) {
	// TODO Auto-generated method stub
	return purchaseOrderRepository.findAll(pageable);
}

@GetMapping("/summary")
public Page<PurchaseOrder> PurchaseOrders(Pageable pageable,@RequestParam(value="vendorId",required=false)Integer vendorId,@RequestParam(value="poDt",required=false)LocalDate poDt){
	boolean searchByvendorId=false , searchBypoDt=false;
	if(vendorId!=null ) searchByvendorId=true;
	Object poDtString = null;
	if(poDtString!=null) searchBypoDt=true;
	if(searchByvendorId&&searchBypoDt) {
		return (Page<PurchaseOrder>) purchaseOrderRepository.findByVendorIdAndPoDt(pageable,vendorId, poDt);
	}else if (searchByvendorId) {
		return (Page<PurchaseOrder>) purchaseOrderRepository.findByVendorId(pageable,vendorId);
		
	}else if (searchBypoDt) {
		return (Page<PurchaseOrder>) purchaseOrderRepository.findByPoDt(pageable,poDt);
	} 
	return (Page<PurchaseOrder>) purchaseOrderRepository.findAll();
}
	


//@CrossOrigin(origins="*")
@GetMapping("/{id}")
public Optional<PurchaseOrder> getPurchaseOrdersById(@PathVariable(value="id")int id) throws SmartOfficeException{
	return purchaseOrderRepository.findById(id);
	
}
//@CrossOrigin(origins="*")
@PostMapping
public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrder PurchaseOrder)throws SmartOfficeException{
	HashMap<String, String> buisnessKeys = new HashMap<>();
	PurchaseOrder.setPoCode(sequenceGenerationService.nextSeq("PURCHASE-ORDER",buisnessKeys));
	return purchaseOrderRepository.save(PurchaseOrder);
}

//@CrossOrigin(origins="*")
@PatchMapping("/{id}/lines")
public PurchaseOrder updatePurchaseOrderLines(@RequestBody PurchaseOrder purchaseOrder)throws SmartOfficeException{
	if(purchaseOrder.getPurchaseOrderLines()!=null&&!purchaseOrder.getPurchaseOrderLines().isEmpty()) {
		for(PurchaseOrderLine purchaseOrderLine:purchaseOrder.getPurchaseOrderLines()) {
			purchaseOrderLineRepository.save(purchaseOrderLine);
		}
	} if (purchaseOrder.getPurchaseOrderPayouts()!=null&&!purchaseOrder.getPurchaseOrderPayouts().isEmpty()) {
		for(PurchaseOrderPayout purchaseOrderPayout:purchaseOrder.getPurchaseOrderPayouts()) {
			purchaseOrderPayoutRepository.save(purchaseOrderPayout);
		}
	}
	return purchaseOrder;
}

//@CrossOrigin(origins="*")
@DeleteMapping("/{id}")
public void deletePurchaseOrderById(@PathVariable(value="id")int id) throws SmartOfficeException{
	purchaseOrderRepository.deleteById(id);
}
//@CrossOrigin(origins="*")
@PatchMapping("/{id}")
public PurchaseOrder updatePurchaseOrder(@PathVariable(value="id")int id,@RequestBody PurchaseOrder purchaseOrder)throws SmartOfficeException{
	PurchaseOrder purchaseOrderFromDb = purchaseOrderRepository.findById(id).get();
	
	purchaseOrderFromDb.setVendorId(purchaseOrder.getVendorId());
	purchaseOrderFromDb.setPhoneNumber(purchaseOrder.getPhoneNumber());
	purchaseOrderFromDb.setEmailId(purchaseOrder.getEmailId());
	purchaseOrderFromDb.setPoRefNumber(purchaseOrder.getPoRefNumber());
	purchaseOrderFromDb.setAddress(purchaseOrder.getAddress());
	purchaseOrderFromDb.setPoDt(purchaseOrder.getPoDt());
	purchaseOrderFromDb.setBillingRemarks(purchaseOrder.getBillingRemarks());
	
	purchaseOrderFromDb.setGrossPoAmt(purchaseOrder.getGrossPoAmt());
	purchaseOrderFromDb.setTotalTaxAmt(purchaseOrder.getTotalTaxAmt());
	purchaseOrderFromDb.setTotalDiscountAmt(purchaseOrder.getTotalDiscountAmt());
	purchaseOrderFromDb.setNetPoAmt(purchaseOrder.getNetPoAmt());
	purchaseOrderFromDb.setTotalPaidAmt(purchaseOrder.getTotalPaidAmt());
	purchaseOrderFromDb.setTotalDueAmt(purchaseOrder.getTotalDueAmt());
	purchaseOrderFromDb.setIgst(purchaseOrder.getIgst());
	purchaseOrderFromDb.setCgst(purchaseOrder.getCgst());
	purchaseOrderFromDb.setSgst(purchaseOrder.getSgst());
	purchaseOrderFromDb.setTotalShippingAmt(purchaseOrder.getTotalShippingAmt());
	purchaseOrderFromDb.setOtherAmt(purchaseOrder.getOtherAmt());
	purchaseOrderFromDb.setSupplierRefNumber(purchaseOrder.getSupplierRefNumber());
	purchaseOrderFromDb.setOtherRefNumber(purchaseOrder.getOtherRefNumber());
	purchaseOrderFromDb.setDespatchThrough(purchaseOrder.getDespatchThrough());
	purchaseOrderFromDb.setDestination(purchaseOrder.getDestination());
	purchaseOrderFromDb.setDeliveryTerms(purchaseOrder.getDeliveryTerms());
	purchaseOrderFromDb.setDocId(purchaseOrder.getDocId());
	
	return purchaseOrderRepository.save(purchaseOrderFromDb);
	
}

@PatchMapping("/{id}/add-payment")
public List<PurchaseOrderPayout> addPayment(@PathVariable(value="id")int id,@RequestBody PurchaseOrder purchaseOrder)throws SmartOfficeException{
	List<DocMetadata> docMetadataList = new ArrayList<>();
	List<DocInfo> docInfos = new ArrayList<>();
	List<PurchaseOrderPayout> purchaseOrderPayoutList = new ArrayList<PurchaseOrderPayout>();
    try {
	if((purchaseOrder.getPurchaseOrderPayouts()!=null)&& !purchaseOrder.getPurchaseOrderPayouts().isEmpty()) {
		for(PurchaseOrderPayout purchaseOrderPayout:purchaseOrder.getPurchaseOrderPayouts()) {
			purchaseOrderPayout.setPurchaseOrderId(id);
			purchaseOrderPayout =purchaseOrderPayoutRepository.save(purchaseOrderPayout);
			purchaseOrderPayoutList.add(purchaseOrderPayout);
			if(purchaseOrderPayout.getDocId()!=null) {
				DocInfo docInfo = new DocInfo(); 
				docInfo.setDocId(purchaseOrderPayout.getDocId());
				DocMetadata docMetadata = new DocMetadata();
				docMetadata.setMdCode("vendor-id");
				docMetadata.setMdValue(purchaseOrder.getVendorId().toString());
				docMetadataList.add(docMetadata);
				DocMetadata docMetadataMonthYear = new DocMetadata();
				docMetadataMonthYear.setMdCode("month-year");
				docMetadataMonthYear.setMdValue(purchaseOrder.getPoDt().toString());
				docMetadataList.add(docMetadataMonthYear);
				docInfo.setMetadataList(docMetadataList);
			
				docInfos.add(docInfo);
				documentManagementHelper.checkInDocs(docInfos);
				ProcessLog processLog= new ProcessLog(null,"PURCHASE ORDER SERVICE" , purchaseOrder.getId().toString(), "VENDOR-INVOICE ", "VENDOR-INVOICE", null, purchaseOrder.getId().toString(), commonUtils.getLoggedinEmployeeId(), purchaseOrder.getVendorId().toString(), null, null, null, null);
				processLogService.addLog(processLog);
			}
			
		}
		
	}
	
	
	

	}catch (Exception e) {
		// TODO: handle exception
		e.getLocalizedMessage();
		throw new SmartOfficeException("Exception Occured : PURCHASE ORDER SERVICE - Add Payment");
	}
	return purchaseOrderPayoutList;
	
}

@PatchMapping("/{id}/received-product")
public List<PurchaseOrderLine> receivedProduct(@PathVariable(value="id")int id,@RequestBody PurchaseOrder purchaseOrder)throws SmartOfficeException{

	List<PurchaseOrderLine> purchaseOrderLineList = new ArrayList<PurchaseOrderLine>();
	if((purchaseOrder.getPurchaseOrderLines()!=null)&& !purchaseOrder.getPurchaseOrderLines().isEmpty()) {
		for(PurchaseOrderLine purchaseOrderLine:purchaseOrder.getPurchaseOrderLines()) {
			
			if(purchaseOrderLine.getId()!=null) {
				PurchaseOrderLine purchaseOrderLinefromDb =purchaseOrderLineRepository.findById(purchaseOrderLine.getId()).get();
				purchaseOrderLinefromDb.setReceivedQty(purchaseOrderLine.getReceivedQty());
				purchaseOrderLinefromDb =purchaseOrderLineRepository.save(purchaseOrderLinefromDb);
				purchaseOrderLineList.add(purchaseOrderLinefromDb);
			}			
		}	
	}
	
	return purchaseOrderLineList;
	
}


@PatchMapping("/{id}/return-purchase")
public List<PurchaseOrderLine> returnPurchase(@PathVariable(value="id")int id,@RequestBody PurchaseOrder purchaseOrder)throws SmartOfficeException{

	List<PurchaseOrderLine> purchaseOrderLineList = new ArrayList<PurchaseOrderLine>();
	if((purchaseOrder.getPurchaseOrderLines()!=null)&& !purchaseOrder.getPurchaseOrderLines().isEmpty()) {
		for(PurchaseOrderLine purchaseOrderLine:purchaseOrder.getPurchaseOrderLines()) {
			
			if(purchaseOrderLine.getId()!=null) {
				PurchaseOrderLine purchaseOrderLinefromDb =purchaseOrderLineRepository.findById(purchaseOrderLine.getId()).get();
				purchaseOrderLinefromDb.setReturnedQty(purchaseOrderLine.getReturnedQty());
				purchaseOrderLinefromDb =purchaseOrderLineRepository.save(purchaseOrderLinefromDb);
				purchaseOrderLineList.add(purchaseOrderLinefromDb);
			}			
		}	
	}
	
	return purchaseOrderLineList;
	
}

@GetMapping("/_internal/{id}")
public Optional<PurchaseOrder> getPurchaseOrderInternalById(@PathVariable(value="id")Integer id) throws SmartOfficeException {
	return getPurchaseOrdersById(id);

}

@PostMapping("/_internal/{id}/doc-id")
public PurchaseOrder purchaseOrderDocIdUpdateInternal(@PathVariable(value="id")Integer id,@RequestBody PurchaseOrder purchaseOrder) throws SmartOfficeException {
PurchaseOrder purchaseOrderFromDb = purchaseOrderRepository.findById(id).get();
	

	purchaseOrderFromDb.setDocId(purchaseOrder.getDocId());
	
	return purchaseOrderRepository.save(purchaseOrderFromDb);
	
}

@GetMapping("/find-payout/{vendorId}")
public List<PurchaseOrderPayout> findPayoutByVendorId(@PathVariable(value = "vendorId")String vendorId){
	return purchaseOrderPayoutRepository.findByVendorId(vendorId);
}

@PatchMapping("/{id}/send-email")
public void sendEmailPurchaseOrder(@PathVariable(value="id")Integer id,@RequestBody PurchaseOrder purchaseOrder) throws SmartOfficeException {

	Event event = new Event();
	
	event.setName(Event.EventTypes.VendorPoEmailEvent);
	event.setCategory(Event.EventCategory.NotificationEvent);
	List<Attachment>attachments = new ArrayList<Attachment>();
	Attachment attachment = new Attachment();
//	attachment.setEmailBody( purchaseOrder.getEmailBody());
//	attachment.setEmailSubject(purchaseOrder.getEmailSubject());
//	attachment.setEmailId(purchaseOrder.getEmailId());
//	attachment.setPurchaseOrderId(purchaseOrder.getId().toString());
//	attachment.setVendorName(purchaseOrder.getVendorName());
	attachment.setDocId1(purchaseOrder.getDocId());
	attachments.add(attachment);
	event.setAttachments(attachments);
	eventPublisherService.pushEvent(event);
	
}
	
}
