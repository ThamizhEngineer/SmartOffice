package com.ss.smartoffice.soservice.transaction.recruitmentservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.applicant.Applicant;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.recruitment.ApplicantRepository;
import com.ss.smartoffice.soservice.transaction.recruitment.ApplicantService;

@RestController
@RequestMapping("transaction/recruiment-service")
public class RecruitmentService {

	@Value("${sys-user.token}")
	private String sysUsrToken;
	@Value("${sys-user.authId}")
	private String sysUsrAuthId;

	@Autowired
	ApplicantRepository applicantRepository;
	@Autowired
	ApplicantService applicantService;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;
	@Autowired
	AuthUserRepository authUserRepository;

	@Autowired
	EventPublisherService eventPublisherService;

	@PostMapping("/new-login")
	public String createNewLogin(@RequestBody Applicant applicant) throws SmartOfficeException {
		try {
			List<Applicant> applicants = (List<Applicant>) applicantRepository.findAll();
			List<String> emails = applicants.stream().filter(x -> x.getContactEmailId() != null)
					.map(x -> x.getContactEmailId()).collect(Collectors.toList());
			System.out.println("emails" + emails);
			if (emails.contains(applicant.getContactEmailId())) {
				return "Email Already Exists";
			} else {
				applicant.setEmailId(applicant.getContactEmailId());
				applicant = applicantService.createApplicant(applicant);

				applicantService.createAuthUserFromApplicant(applicant);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return "A New User Has Been Created";
	}

	@PatchMapping("/forgot-username-pwd")
	public String forgotUserNamePwd(@RequestParam(value = "emailId") String emailId) throws SmartOfficeException {
		try {
			System.out.println(emailId);

			List<Applicant> applicants = (List<Applicant>) applicantRepository.findAll();
			List<String> emails = applicants.stream().filter(x -> x.getContactEmailId() != null)
					.map(x -> x.getContactEmailId()).collect(Collectors.toList());
			System.out.println("emails" + emails);
			if (emails.contains(emailId)) {
				Applicant applicant = applicantRepository.findByContactEmailId(emailId);
				System.out.println(applicant);
				triggerForgotPwdEvent(applicant);

			} else {
				return "EmailId Doesnot Exists";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Password Has been Sent to your email address.Please do check it";
	}
//------------------------------ForgotPasswordEventProcess--------------------------------------------------------//

	public void triggerForgotPwdEvent(Applicant applicant) throws SmartOfficeException {
///	String appTokwen=SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
		try {
			AuthUser loggedInUser = new AuthUser();
			loggedInUser.setEmployeeId(sysUsrAuthId);
			loggedInUser.setId(Integer.parseInt(sysUsrAuthId));
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			event.setName(Event.EventTypes.ApplicantForgotPwdEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);			
			Event savedEvent = eventRepository.save(event);
			System.out.println("sa" + savedEvent);
			List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
			BusinessKey businessKey = new BusinessKey();
			businessKey.setApplicantId(String.valueOf(applicant.getId()));
			businessKey.setEventId(savedEvent.getId().toString());
			businessKeyRepository.save(businessKey);
			businessKeys.add(businessKey);
			event.setBusinessKeys(businessKeys);
			List<EventKeyValue> eventKeyValues = new ArrayList<>();
			EventKeyValue eventKeyValue = new EventKeyValue();
			eventKeyValue.setEventId(savedEvent.getId().toString());
			eventKeyValue.setKeyPair("applicantName");
			eventKeyValue.setValue(applicant.getFirstName() + applicant.getLastName());
			eventKeyValueRepository.save(eventKeyValue);
			eventKeyValues.add(eventKeyValue);
			AuthUser authUser = authUserRepository.findByApplicantId(applicant.getId().toString());
			if (authUser.getPassword() == null || authUser.getPassword().isEmpty()) {
				throw new SmartOfficeException("Password Is Empty");
			} else {
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(savedEvent.getId().toString());
				eventKeyValue1.setKeyPair("password");
				eventKeyValue1.setValue(authUser.getPassword());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
			}
			event.setEventKeyValues(eventKeyValues);
			eventPublisherService.pushEvent(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
