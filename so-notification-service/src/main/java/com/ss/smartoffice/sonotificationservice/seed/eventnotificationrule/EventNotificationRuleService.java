package com.ss.smartoffice.sonotificationservice.seed.eventnotificationrule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path="seed/event-ntfn-rules")
@Scope("prototype")
public class EventNotificationRuleService {

	@Autowired
	EventNotificationRuleRepository eventNotificationRuleRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
@GetMapping
public Iterable<EventNotificationRule>getAllNotificationRules()throws SmartOfficeException{
	return eventNotificationRuleRepository.findAll();
}
@GetMapping("/{id}")
public Optional<EventNotificationRule>getNotificationRuleById(@PathVariable (value="id")Integer id)throws SmartOfficeException{
	return eventNotificationRuleRepository.findById(id);
}

@GetMapping("/fetch-by-filters")
public List<EventNotificationRule> getEventByFilters(
		@RequestParam(value = "eventName",required = false) String eventName,
		@RequestParam(value = "entity",required = false) String entity,
		@RequestParam(value = "sendSms",required = false) String sendSms,
		@RequestParam(value = "sendEmail",required = false) String sendEmail,
		@RequestParam(value = "sendInAppNotfn",required = false) String sendInAppNotfn) throws SmartOfficeException {
		System.out.println(eventName);
		return eventNotificationRuleRepository.fetchByFilters(eventName, entity, sendSms, sendEmail, sendInAppNotfn);
}

@PostMapping
public EventNotificationRule addEventNotificationRule(@RequestBody EventNotificationRule eventNotificationRule)throws SmartOfficeException{
	eventNotificationRule.setIsEnabled("Y");
	eventNotificationRule.setCreatedBy(commonUtils.getLoggedinEmployeeId());
	return eventNotificationRuleRepository.save(eventNotificationRule);
}

@PatchMapping("/{id}/update")
public EventNotificationRule updateEventNotificationRule(@PathVariable(value="id")Integer id,@RequestBody EventNotificationRule eventNotificationRule)throws SmartOfficeException{
	eventNotificationRule.setIsEnabled("Y");
	eventNotificationRule.setModifiedBy(commonUtils.getLoggedinEmployeeId());
	return eventNotificationRuleRepository.save(eventNotificationRule);
}

@CrossOrigin
@PatchMapping("/update-event-list")
public List<EventNotificationRule> updateEventFromUi(@RequestBody List<EventNotificationRule> eventNotificationList) throws SmartOfficeException{
	EventNotificationRule eventNotifiFromDb = new EventNotificationRule();
	for(EventNotificationRule eventLoop:eventNotificationList) {
		Optional<EventNotificationRule> eventObj = eventNotificationRuleRepository.findById(eventLoop.getId());
		eventNotifiFromDb = eventObj.get();
		eventNotifiFromDb.setEntity(eventLoop.getEntity());
		eventNotifiFromDb.setEventName(eventLoop.getEventName());
		eventNotifiFromDb.setSendSms(eventLoop.getSendSms());
		eventNotifiFromDb.setSendEmail(eventLoop.getSendEmail());
		eventNotifiFromDb.setSendInAppNotfn(eventLoop.getSendInAppNotfn());
		eventNotificationRuleRepository.save(eventNotifiFromDb);
	}
	return eventNotificationList;
}


@DeleteMapping("/{id}/delete")
public void deleteEventNotificationRule(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	eventNotificationRuleRepository.deleteById(id);
}
}
