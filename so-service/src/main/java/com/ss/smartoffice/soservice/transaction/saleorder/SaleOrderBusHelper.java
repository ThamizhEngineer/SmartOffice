package com.ss.smartoffice.soservice.transaction.saleorder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.NotificationKey;
//import com.ss.smartoffice.shared.model.Event;
//import com.ss.smartoffice.soservice.event.EventGenerator;
import com.ss.smartoffice.soservice.transaction.model.SaleGood;
import com.ss.smartoffice.soservice.transaction.model.SaleOrder;
import com.ss.smartoffice.soservice.transaction.service.SmsService;



@Component
@Scope("prototype")
public class SaleOrderBusHelper {

	@Autowired
	SequenceGenerationService sequenceGenerationService;

	@Autowired
	SaleOrderRepository saleOrderRepository;

	@Autowired
	SaleGoodRepository saleGoodRepository;

	@Autowired
	SaleServiceRepository saleServiceRepository;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	SmsService smsService;

	@Autowired
	EventPublisherService eventPublisherService;

	public Iterable<SaleOrder> getSaleOrders(String partnerId, String projectId) {
		try {
			return saleOrderRepository.findBySummaries(partnerId, projectId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
	}

	public Optional<SaleOrder> getSaleOrderById(Integer id) {
		try {
			return saleOrderRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
	}

	public SaleOrder createSaleOrder(SaleOrder soFromReq) {

		try {
			HashMap<String, String> buisnessKeys = new HashMap<>();
			if (soFromReq.getVirtualPoNum() != null && !soFromReq.getVirtualPoNum().isEmpty()) {
				soFromReq.setSaleOrderCode(sequenceGenerationService.nextSequence("VIRTUAL-SALE-ORDER").getOutput());

			} else {
				soFromReq.setSaleOrderCode(sequenceGenerationService.nextSeq("SALE-ORDER", buisnessKeys));
			}
			soFromReq.setSaleOrderStatus("CREATED");

			soFromReq.setCreatedBy(commonUtils.getLoggedinUserId());
			soFromReq.setIsEnabled("Y");
			
			return saleOrderRepository.save(soFromReq);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
	}

	public SaleOrder updateSaleOrder(SaleOrder soFromReq) {
		

		try {
			soFromReq.setModifiedBy(commonUtils.getLoggedinUserId());
			soFromReq.setModifiedDt(LocalDateTime.now());
			soFromReq.setIsEnabled("Y");
			//added for checkbox
           if(soFromReq.getNeedsGoods() == "false") {
        	   soFromReq.setNeedsGoods(null);
        	   }
           if(soFromReq.getNeedsServices() == "false") {
        	   soFromReq.setNeedsServices(null);
           }

			return saleOrderRepository.save(soFromReq);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
	}

	public SaleOrder sendOrderAcknowledgement(@PathVariable(value = "id") Integer id, @RequestBody SaleOrder saleorder)
			throws SmartOfficeException {


		SaleOrder saleOrderById = saleOrderRepository.findById(id).get();
		try {

			saleOrderById.setNotificationStatus("SENT");
			saleOrderRepository.save(saleOrderById);
			System.out.println(saleOrderById);
			Event event = new Event();

			event.setName(Event.EventTypes.OrderAcknowlegmentProcessorEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			List<BusinessKey> buisnessKeies = new ArrayList<BusinessKey>();
			BusinessKey businessEventKeyValueStructure = new BusinessKey();
			businessEventKeyValueStructure.setCustomerId(saleorder.getPartnerId());
			businessEventKeyValueStructure.setSaleOrderId(Integer.toString(saleorder.getId()));
			businessEventKeyValueStructure.setKey1(saleorder.getPurchaseOrderId());
			businessEventKeyValueStructure.setKey2(String.valueOf(saleOrderById.getId()));
			businessEventKeyValueStructure.setKey3(saleorder.getSaleOrderCode());
			event.setAppToken(commonUtils.getLoggedinAppToken());
			buisnessKeies.add(businessEventKeyValueStructure);
			event.setBusinessKeys(buisnessKeies);
			eventPublisherService.pushEvent(event);

		} catch (Exception e) {
			e.printStackTrace();
			saleOrderById.setNotificationStatus("NOT SENT");
			saleOrderRepository.save(saleOrderById);
			System.out.println(saleOrderById);
			new SmartOfficeException(e.getMessage());
		}

		return saleorder;
	}

	public void deleteSaleOrderById(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		saleOrderRepository.deleteById(id);
	}

	public void deleteSaleGood(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		saleGoodRepository.deleteById(id);
	}

	public void deleteSaleService(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		saleServiceRepository.deleteById(id);
	}

}
