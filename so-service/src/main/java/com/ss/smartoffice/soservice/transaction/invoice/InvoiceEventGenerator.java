package com.ss.smartoffice.soservice.transaction.invoice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.event.NotificationKeyRepository;

@Service
public class InvoiceEventGenerator {

	@Autowired
	EventPublisherService eventPublisherService;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	
	@Autowired
	EventRepository eventRepository;

	@Autowired
	NotificationKeyRepository notificationKeyRepository;
	
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;
	
	@Autowired
	InvoiceHdrRepo invoiceHdrRepo;
	
	@Autowired
	InvoiceService invoiceService;
	
	
	public void InvoiceSubmitEvent(InvoiceHdr invoiceHdr,AuthUser loggedInUser)throws SmartOfficeException{
		try {			
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			Event savedEvent =new Event();
			
			List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
			BusinessKey busKey = new BusinessKey();
			EventKeyValue keyValue = new EventKeyValue();
			List<EventKeyValue> ekvs = new ArrayList<>();
			event.setName(Event.EventTypes.InvoiceSubmitEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);
			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setDirUserGroupId(invoiceHdr.getDirGroupId());
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "invoiceCode", invoiceHdr.getInvoiceCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "dirUsrGrpName", invoiceHdr.getDirGroupName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "saleOrderCode", invoiceHdr.getSaleOrderCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "invoiceCode", invoiceHdr.getInvoiceCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "buyerName", invoiceHdr.getBuyerName());
			ekvs.add(keyValue);
			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InvoiceApproveEvent(InvoiceHdr invoiceHdr,AuthUser loggedInUser)throws SmartOfficeException{
		try {			
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			Event savedEvent =new Event();
			
			List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
			BusinessKey busKey = new BusinessKey();
			EventKeyValue keyValue = new EventKeyValue();
			List<EventKeyValue> ekvs = new ArrayList<>();
			event.setName(Event.EventTypes.InvoiceApproveEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);
			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setDirUserGroupId(invoiceHdr.getDirGroupId());
			busKey.setEmployeeId(invoiceHdr.getInvoiceCreatedBy());
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "invoiceCode", invoiceHdr.getInvoiceCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "dirUsrGrpName", invoiceHdr.getDirGroupName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "saleOrderCode", invoiceHdr.getSaleOrderCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "invoiceCode", invoiceHdr.getInvoiceCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "createdEmpName", invoiceHdr.getInvoiceCreatedByEmpName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "approvedEmpName", invoiceHdr.getApprovedByEmpName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "buyerName", invoiceHdr.getBuyerName());
			ekvs.add(keyValue);
			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InvoiceVoidEvent(InvoiceHdr invoiceHdr,AuthUser loggedInUser)throws SmartOfficeException{
		try {			
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			Event savedEvent =new Event();
			
			List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
			BusinessKey busKey = new BusinessKey();
			EventKeyValue keyValue = new EventKeyValue();
			List<EventKeyValue> ekvs = new ArrayList<>();
			event.setName(Event.EventTypes.InvoiceVoidEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);
			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setDirUserGroupId(invoiceHdr.getDirGroupId());
			busKey.setEmployeeId(invoiceHdr.getInvoiceCreatedBy());
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "invoiceCode", invoiceHdr.getInvoiceCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "dirUsrGrpName", invoiceHdr.getDirGroupName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "saleOrderCode", invoiceHdr.getSaleOrderCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "invoiceCode", invoiceHdr.getInvoiceCode());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "createdEmpName", invoiceHdr.getInvoiceCreatedByEmpName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "voidedEmpName", invoiceHdr.getVoidedByEmpName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "buyerName", invoiceHdr.getBuyerName());
			ekvs.add(keyValue);
			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
