package com.ss.smartoffice.soservice.transaction.announcement;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.offices.Office;
import com.ss.smartoffice.soservice.master.offices.OfficeRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;



@Controller
@RestController
@RequestMapping(value="transaction/announcements")

public class AnnouncementService {
	@Autowired
	AnnouncementRepository announcementrepository;
	@Autowired
	 SequenceGenerationService sequenceGenerationService;
	@Autowired
	CommonUtils commonutils;
	@Autowired
	OfficeRepository officeRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	EventPublisherService eventPublisherService;

@GetMapping
public Iterable<Announcement> getAnnouncement() throws SmartOfficeException{
	return announcementrepository.findAll();
 }

@GetMapping(value="/{id}")
public Optional<Announcement>getByAnnouncementId(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return announcementrepository.findById(id);
 }



@PostMapping
public Announcement addAnnouncement(@RequestBody Announcement announcement)throws SmartOfficeException {
	String officeId=null;
	HashMap<String, String> buisnessKeys = new HashMap<>();
	announcement.setAnnouncementCode(sequenceGenerationService.nextSeq("ANNOUNCEMENT-CODE", buisnessKeys));
//	announcement.setAnnouncementCode(sequenceGenerationService.nextSequence("ANNOUNCEMENT-CODE").getOutput());

	
	AuthUser loggedInUser = (AuthUser) commonutils.getAuthenticatedUser().getDetails();
	
	
	if((announcement.getAnnouncementCategory().equals("All"))||(announcement.getAnnouncementCategory().equals("Manager"))||(announcement.getAnnouncementCategory().equals("SeniorManagement"))){
	
		announcement.setCreatedBy(commonutils.getLoggedinEmployeeId());
		announcement.setSubject(announcement.getSubject());
		announcement.setMessage(announcement.getMessage());
		for(Office office:announcement.getOffices()) {
		List<Office>offices = new ArrayList<Office>();
		offices.add(office);
		 officeId=String.valueOf(office.getId());
		announcement.setOffices(offices);
		}
		announcementrepository.save(announcement);
		Event event = new Event();
		event.setName(Event.EventTypes.AnnouncementEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		

		List<String> announcementStrings= Arrays.asList(announcement.getAnnouncementCode(),announcement.getAnnouncementCategory(),announcement.getSubject(),announcement.getMessage(),officeId);
		System.out.println(announcementStrings);
		event.setPayload(announcementStrings.toString());
	EventKeyValue eventKeyValue = new EventKeyValue();
	eventKeyValue.setKeyPair("announcement-id");
	eventKeyValue.setValue(announcement.getId().toString());
	List<EventKeyValue>eventKeyValues= new ArrayList<EventKeyValue>();
	eventKeyValues.add(eventKeyValue);
		event.setBusinessKeys(null);
		event.setEventKeyValues(eventKeyValues);
		Event savedEvent = eventRepository.save(event);
		
		eventPublisherService.pushEvent(savedEvent);
		 
		 
		}
	else {
		throw new SmartOfficeException("Does not exsists");
	}
	return announcement;
}
@PatchMapping(value= "/{id}/update")
public Announcement updateAnnouncement(@PathVariable(value="id")Integer id,@RequestBody Announcement announcementinput)throws SmartOfficeException{
	announcementinput.setModifiedBy(commonutils.getLoggedinEmployeeId());
	
	return announcementrepository.save(announcementinput);
}
@DeleteMapping(value="/{id}/delete")
public void deleteAnnouncement(@PathVariable(value="id")Integer id) throws SmartOfficeException{
	announcementrepository.deleteById(id);
}




}
