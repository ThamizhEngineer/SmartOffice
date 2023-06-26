package com.ss.smartoffice.soservice.transaction.event;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;


@RestController
@RequestMapping(path="transaction/event-structures")
@Scope("prototype")
public class EventService {
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	
	@Autowired
	NotificationKeyRepository notificationKeyRepository;
	
	@Autowired
	AttachmentRepository  attachmentRepository;
	
	@GetMapping
	public Iterable<Event> getALlEventStructure()throws SmartOfficeException{
		
	    return eventRepository.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Event> getEventStructureById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		
		return eventRepository.findById(id);
	
	}
	// helper function for event publish service
	@PostMapping
	public Event addEventStructure(@RequestBody Event event)throws SmartOfficeException{
		try {
//			if(commonUtils.getLoggedinAppToken()!=null||!commonUtils.getLoggedinAppToken().isEmpty()) {
//			event.setAppToken(commonUtils.getLoggedinAppToken());
//			}
//			event.setAppToken("38d5765b-0e31-4ec3-b971-be0510fd27fg");

			System.out.println("Event kh - "+event);	
			commonUtils.setAuthenticationContext(event.getContextAuthUserId(),"async");
//		event.setCreatedBy(commonUtils.getLoggedinEmployeeId());
//		event.setCreatedDt(LocalDateTime.now());
		Event savedEventStructure=eventRepository.save(event);
		if((event.getBusinessKeys()!=null&&!event.getBusinessKeys().isEmpty())||(event.getNotificationKeys()!=null&&!event.getNotificationKeys().isEmpty())||(event.getAttachments()!=null&&!event.getAttachments().isEmpty())) {
		event.setBusinessKeys(event.getBusinessKeys());
		event.setNotificationKeys(event.getNotificationKeys());
		event.setAttachments(event.getAttachments());
	
		for(BusinessKey businessKey:event.getBusinessKeys()) {
			businessKey.setEventId(String.valueOf(savedEventStructure.getId()));
			
			businessKeyRepository.save(businessKey);
		}
		if(event.getNotificationKeys()!=null&&!event.getNotificationKeys().isEmpty()) {
		for(NotificationKey notificationKey:event.getNotificationKeys()) {
			notificationKey.setEventId(String.valueOf(savedEventStructure.getId()));
			notificationKeyRepository.save(notificationKey);
		}
		}
		if(event.getAttachments()!=null&&!event.getAttachments().isEmpty()) {
		for(Attachment attachment:event.getAttachments()) {
			attachment.settEventId(String.valueOf(savedEventStructure.getId()));
			System.out.println(attachment);
			attachmentRepository.save(attachment);
		}
		}
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return event;
		
	}
	
	@PatchMapping("/{id}/update")
	public Event updateEventStructure(@PathVariable(value="id")Integer id,@RequestBody Event event)throws SmartOfficeException{
		Event savedEventStructure=eventRepository.save(event);
		event.setBusinessKeys(event.getBusinessKeys());
		event.setNotificationKeys(event.getNotificationKeys());
		event.setAttachments(event.getAttachments());
		for(BusinessKey businessKey:event.getBusinessKeys()) {
			businessKey.setEventId(String.valueOf(id));
			businessKeyRepository.save(businessKey);
		}
		
		for(NotificationKey notificationKey:event.getNotificationKeys()) {
			notificationKey.setEventId(String.valueOf(id));
			notificationKeyRepository.save(notificationKey);
		}
		
		for(Attachment attachment:event.getAttachments()) {
			attachment.settEventId(String.valueOf(id));
			attachmentRepository.save(attachment);
		}
		savedEventStructure.setBusinessKeys(event.getBusinessKeys());
		savedEventStructure.setNotificationKeys(event.getNotificationKeys());
		savedEventStructure.setAttachments(event.getAttachments());
		return savedEventStructure;
	
	}
}
