package com.ss.smartoffice.sonotificationservice.seed.eventnotificationrule;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EventNotificationRuleRepository extends CrudRepository<EventNotificationRule, Integer>{
List<EventNotificationRule> findByEventName(String eventName);

//	@Query("select new com.ss.smartoffice.sonotificationservice.seed.eventnotificationrule.EventNotificationRule(event.id,event.eventName,event.entity,event.sendSms,event.sendEmail,event.sendInAppNotfn) from EventNotificationRule event"
//			+ " where event.eventName=:eventName AND event.entity=:entity AND event.sendSms=:sendSms AND event.sendEmail=:sendEmail AND event.sendInAppNotfn=:sendInAppNotfn")
	

@Query("select new com.ss.smartoffice.sonotificationservice.seed.eventnotificationrule.EventNotificationRule (event.id,event.eventName,event.entity,event.sendSms,event.sendEmail,event.sendInAppNotfn) from EventNotificationRule event "
		+ "where ifnull(LOWER(event.eventName),'') LIKE LOWER(CONCAT('%',ifnull(:eventName,''), '%')) "
		+ "AND ifnull(LOWER(event.entity),'') LIKE LOWER(CONCAT('%',ifnull(:entity,''), '%')) AND ifnull(LOWER(event.sendSms),'') LIKE LOWER(CONCAT('%',ifnull(:sendSms,''), '%')) "
		+ "AND ifnull(LOWER(event.sendEmail),'') LIKE LOWER(CONCAT('%',ifnull(:sendEmail,''), '%')) AND ifnull(LOWER(event.sendInAppNotfn),'') LIKE LOWER(CONCAT('%',ifnull(:sendInAppNotfn,''), '%'))")
	List<EventNotificationRule> fetchByFilters(@Param("eventName")String eventName,
			@Param("entity")String entity,@Param("sendSms")String sendSms,
			@Param("sendEmail")String sendEmail,@Param("sendInAppNotfn")String sendInAppNotfn);
}
