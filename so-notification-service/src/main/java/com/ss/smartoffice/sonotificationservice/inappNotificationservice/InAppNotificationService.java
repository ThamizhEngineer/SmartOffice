package com.ss.smartoffice.sonotificationservice.inappNotificationservice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.EmployeePayout;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.pay.EmployeePayoutRepository;
import com.ss.smartoffice.sonotificationservice.payout.EmailService;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;
import com.ss.smartoffice.sonotificationservice.transaction.event.EventService;

@RestController
@RequestMapping(path="notfn/inapp-notfn")
@Scope("prototype")
public class InAppNotificationService {
@Autowired
InAppNotfnRepository inAppNotfnRepository;
@Autowired
EventService eventService;
@Value("${notfn.expiry.duration}")
private Integer notfnExpiryDuration;
@PersistenceContext
EntityManager entityManager;
@Autowired
CommonUtils commonUtils;

@Autowired
EmployeePayoutRepository employeePayoutRepository;

@Autowired
EmailService emailService;

private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";


@GetMapping("/{id}")
public EmployeePayout getEmployeePayoutById(@PathVariable(value = "id")Integer id) {
	EmployeePayout emp = employeePayoutRepository.findById(id).get();
	try {
		emailService.sendMailWithAttachment(emp);
	} catch (SmartOfficeException e) {
		e.printStackTrace();
	}  
	return emp;
}


@GetMapping
public List<InAppNotfn> getActiveNotifications()throws SmartOfficeException{
	TypedQuery<InAppNotfn> inAppNotfnQuery = entityManager.createQuery(
			"SELECT  new com.ss.smartoffice.sonotificationservice.inappNotificationservice.InAppNotfn(inAppNotfn.id,inAppNotfn.userId,inAppNotfn.userName,inAppNotfn.eventDesc,inAppNotfn.notfnDt,inAppNotfn.notfnExpDt,inAppNotfn.viewedNotfn,inAppNotfn.notfnViewedDt,"
					+ "inAppNotfn.hideNotfn,inAppNotfn.eventName,inAppNotfn.notfnMessage) from InAppNotfn inAppNotfn where inAppNotfn.hideNotfn='N' AND inAppNotfn.userId="+commonUtils.getLoggedinUserId(),
					
			InAppNotfn.class);

	List<InAppNotfn> inAppNotfnList = inAppNotfnQuery.getResultList();
	return inAppNotfnList; 
}

@GetMapping("/get-all")
public Iterable<InAppNotfn> getAllData(){
	return inAppNotfnRepository.findAll();
}



//@PostMapping("/create")
public InAppNotfn create(Event event)throws SmartOfficeException{
	
	InAppNotfn inAppNotfn = new InAppNotfn();
	inAppNotfn.setUserId(event.getNotificationKeys().get(0).getUserId());
	inAppNotfn.setEventName(event.getName().toString());
	inAppNotfn.setNotfnMessage(event.getNotificationKeys().get(0).getInAppNotfnMessage());
	inAppNotfn.setEventDesc(event.getEventDesc());
	LocalDateTime localDateTime = LocalDateTime.now(); //get current date time
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    String notfnDt = localDateTime.format(formatter);
	inAppNotfn.setNotfnDt(notfnDt);
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTER);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH, notfnExpiryDuration);
	String notfnExpDt = sdf.format(cal.getTime());
	System.out.println(notfnExpDt);
	inAppNotfn.setNotfnExpDt(notfnExpDt);
	inAppNotfn.setViewedNotfn("N");
	inAppNotfn.setHideNotfn("N");
	return inAppNotfnRepository.save(inAppNotfn);
	
}

@PatchMapping("/{id}/viewed")
public InAppNotfn notfnViewed(@RequestBody InAppNotfn inAppNotfn,@PathVariable(value="id")Integer id)throws SmartOfficeException{
	InAppNotfn inAppNotfnById=inAppNotfnRepository.findById(id).get();
	inAppNotfnById.setViewedNotfn(inAppNotfn.getViewedNotfn());
	LocalDateTime localDateTime = LocalDateTime.now(); //get current date time
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    String notfnViewedDt = localDateTime.format(formatter);
	inAppNotfnById.setNotfnViewedDt(notfnViewedDt);
	inAppNotfnById.setHideNotfn(inAppNotfn.getHideNotfn());
	return inAppNotfnRepository.save(inAppNotfnById);
}

	@PatchMapping("/expire-job")
	public List<InAppNotfn> notfnExpire() throws SmartOfficeException {
		List<InAppNotfn> expiredNotfnList = new ArrayList<InAppNotfn>();

		TypedQuery<InAppNotfn> inAppNotfnQuery = entityManager.createQuery(
				"SELECT  new com.ss.smartoffice.sonotificationservice.inappNotificationservice.InAppNotfn(inAppNotfn.id,inAppNotfn.userId,inAppNotfn.notfnDt,inAppNotfn.notfnExpDt,inAppNotfn.viewedNotfn,inAppNotfn.notfnViewedDt,"
						+ "inAppNotfn.hideNotfn,inAppNotfn.eventName,inAppNotfn.notfnMessage) from InAppNotfn inAppNotfn where inAppNotfn.hideNotfn=Y AND inAppNotfn.notfnExpDt"
						+ LocalDateTime.now(),
				InAppNotfn.class);

		List<InAppNotfn> updatedInAppNotfn = inAppNotfnQuery.getResultList();
		for (InAppNotfn inAppNotfn : updatedInAppNotfn) {
			inAppNotfn.setHideNotfn("Y");
			expiredNotfnList.add(inAppNotfn);
		}
		return expiredNotfnList;
	}



}
