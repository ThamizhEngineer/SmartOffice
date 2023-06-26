package com.ss.smartoffice.soservice.transaction.saleorder;
 

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.job.JobsRepository;
import com.ss.smartoffice.soservice.transaction.model.SaleOrder;
@RestController
@RequestMapping(path="transaction/sale-orders")
@Scope("prototype")
public class SaleOrderService {
	

	 @Autowired
	 SaleOrderBusHelper saleOrderBusHelper;
	 
	 @Autowired
	 JobsRepository jobsRepository;
	
	 @GetMapping
	 public Iterable<SaleOrder> getSaleOrders(@RequestParam(value="partnerId",required=false)String partnerId,@RequestParam(value="projectId",required=false)String projectId)throws SmartOfficeException {
		return saleOrderBusHelper.getSaleOrders(partnerId,projectId);
		 	
	 }
	 
	 @GetMapping("so-jobs")
	 public Iterable<SaleOrder> getSaleOrdersWithJobs(@RequestParam(value="partnerId",required=false)String partnerId,@RequestParam(value="projectId",required=false)String projectId)throws SmartOfficeException {
		 Iterable<SaleOrder> saleOrder	= saleOrderBusHelper.getSaleOrders(partnerId,projectId);
		 for(SaleOrder so :saleOrder) {
			 so.setJobs(jobsRepository.getBySaleOrderId(so.getId().toString()));
		 }
			 return saleOrder;
		 }
	 
	 @GetMapping("/find-job/{id}")
	 public SaleOrder getSaleOderByPartnerId(@PathVariable(value = "id") int id)throws SmartOfficeException{
		 SaleOrder saleOrder = saleOrderBusHelper.getSaleOrderById(id).get();
		 saleOrder.setJobs(jobsRepository.getBySaleOrderId(saleOrder.getId().toString()));
		 return saleOrder;
	 }
	 
	 @GetMapping("/{id}")
	 public Optional<SaleOrder> getSaleOrderById(@PathVariable(value = "id") int id) {
		return saleOrderBusHelper.getSaleOrderById(id);
		 
	 }
	 
	 @GetMapping("/_internal/{id}")
	 public Optional<SaleOrder> getSaleOrderByInternal(@PathVariable(value = "id") int id) {
		return saleOrderBusHelper.getSaleOrderById(id);
		 
	 }
	 
	 

	 @PostMapping
	 public SaleOrder addSaleOrderById(@RequestBody SaleOrder saleOrder) {
		
		 return saleOrderBusHelper.createSaleOrder(saleOrder);
	 }
	 

	@PatchMapping("/{id}")
	 public SaleOrder updateSaleOrder(@RequestBody SaleOrder saleOrder)throws SmartOfficeException{
	
		if(saleOrder.getSaleOrderStatus().equals("CREATED")) {
			saleOrderBusHelper.updateSaleOrder(saleOrder);
		}
		
		else if(saleOrder.getStatus().equals("PENDING-APPROVAL")||saleOrder.getStatus().equals("APPROVED")) {
			throw new SmartOfficeException("Sale order cannot Be Updated since it is Pending Approval/Approved stage");
		}
		return saleOrderBusHelper.updateSaleOrder(saleOrder);
		 
	 }
	
	@PatchMapping("/{id}/submit-for-approval")
	public SaleOrder submitForApproval(@RequestBody SaleOrder saleOrder)throws SmartOfficeException{
		saleOrder.setStatus("PENDING-APPROVAL");
		saleOrder.setSubmittedDt(LocalDateTime.now());
		return saleOrderBusHelper.updateSaleOrder(saleOrder);
		
	}
	@PatchMapping("/{id}/approved")
	public SaleOrder approvalFunction(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		SaleOrder saleOrder=getSaleOrderById(id).get();
		saleOrder.setStatus("APPROVED");
		saleOrder.setApprovedDt(LocalDateTime.now());
		return saleOrderBusHelper.updateSaleOrder(saleOrder);
		
	}
	

	 @DeleteMapping("/{id}")
	 public void deleteSaleOrderById(@PathVariable(value="id")int id) throws SmartOfficeException{
		 saleOrderBusHelper.deleteSaleOrderById(id);
	 }
	 @DeleteMapping("/{id}/sale-goods")
	 public void deleteSaleGood(@PathVariable(value = "id") Integer id)throws SmartOfficeException{
		 saleOrderBusHelper.deleteSaleGood(id);
	 }
	 @DeleteMapping("/{id}/sale-services")
	 public void deleteSaleService(@PathVariable(value = "id") Integer id)throws SmartOfficeException{
		 saleOrderBusHelper.deleteSaleService(id);
	 }
	 	 
	 @PatchMapping("/{id}/order-acknowledgment-processor")
	 public SaleOrder sendOrderAcknowledgement(@PathVariable(value="id")Integer id,@RequestBody SaleOrder saleorder) throws SmartOfficeException {
		return saleOrderBusHelper.sendOrderAcknowledgement(id, saleorder); 
	 }
	 
}


