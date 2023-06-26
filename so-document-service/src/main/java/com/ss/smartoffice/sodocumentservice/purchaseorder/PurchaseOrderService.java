package com.ss.smartoffice.sodocumentservice.purchaseorder;


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
import com.ss.smartoffice.sodocumentservice.model.PurchaseOrder;


@RestController
@RequestMapping(path="document/purchase-orders")
public class PurchaseOrderService {
	
	@Autowired
	PurchaseOrderPdfGenerator purchaseOrderPdfGenerator;
	
	@Value("${purchaseorder.url}")
	private String purchaseOrderUrl;
	

	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping("/{id}/generate-pdf")
	public void generatePurchaseOrderPdf(@PathVariable(value="id")int id) throws SmartOfficeException{
		
		PurchaseOrder purchaseOrder =new PurchaseOrder();
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization",commonUtils.getLoggedinAppToken() ); 
		
		HttpEntity<PurchaseOrder> request = new HttpEntity<PurchaseOrder>(purchaseOrder, headers);
		ResponseEntity<PurchaseOrder> purchaseOrderEntity = commonUtils.getRestTemplate().exchange(purchaseOrderUrl+ id,
				HttpMethod.GET, request,PurchaseOrder.class);
		
		PurchaseOrder purchaseOrderFromRest =purchaseOrderEntity.getBody();
		 purchaseOrderPdfGenerator.generatePdf(purchaseOrderFromRest);
		
	}

}
