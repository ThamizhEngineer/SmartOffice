package com.ss.smartoffice.soservice.transaction.recruitment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bouncycastle.jce.provider.JDKDSASigner.noneDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.applicant.Applicant;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.event.NotificationKey;
import com.ss.smartoffice.soservice.transaction.event.NotificationKeyRepository;
import com.ss.smartoffice.soservice.transaction.incident.IncidentApplicantRepo;


@RestController
@RequestMapping(path="transaction/recruitments/applicants")
@Scope("prototype")
public class ApplicantService {

	@Value("${sys-user.authId}")
	private String sysUsrAuthId;
	
	@Autowired
	ApplicantRepository applicantRepository;
	
	@Autowired
	EventRepository eventRepository;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	EventPublisherService eventPublisherService;
	
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	
@Autowired
EventKeyValueRepository eventKeyValueRepository;
	
	@Autowired
	AuthUserRepository authUserRepository;
	
	@Autowired
	IncidentApplicantRepo incidentApplicantRepo;
			
	@GetMapping
	public List<Applicant> getApplicant() throws SmartOfficeException{
		
		
		return applicantRepository.findSummaries();
		
	}
	
	@GetMapping("/summaries")
	public List<Applicant> getApplicants(@RequestParam(value="collegeName")String collegeName,@RequestParam(value="degreeName")String degreeName,@RequestParam(value="courseName")String courseName,@RequestParam(value="passedOut")String passedOut) throws SmartOfficeException{
		boolean searchBycollegeNameAndDegreeNameandCourseNameandPassedOut=false;
		if(collegeName!=null&&!collegeName.isEmpty()) {
			searchBycollegeNameAndDegreeNameandCourseNameandPassedOut=true;
		}
		if(searchBycollegeNameAndDegreeNameandCourseNameandPassedOut) {
			List<Applicant> applicants=applicantRepository.findBycollegeNameAndDegreeNameAndCourseNameAndPassedOut(collegeName, degreeName, courseName, passedOut);

			return applicants;
		}
		return null;
	
		
	}
	
	@GetMapping("/advance")
	public Iterable<Applicant> advanceSearch
			(@RequestParam(value = "contactMobileNo", required = false) String contactMobileNo,
			@RequestParam(value = "contactEmailId", required = false) String contactEmailId)
			throws SmartOfficeException {
		return applicantRepository.fliter(contactMobileNo,contactEmailId);
	}
	
	
//-----------------------------------------------------------------------------------------------------------	
	@PostMapping("/create-applicant")
	public List<Applicant> createApplicants(@RequestBody List<com.ss.smartoffice.shared.model.IncidentApplicant> incidentApplicants) {
		List<Applicant> listOfApplicant = new ArrayList<Applicant>();
		for (com.ss.smartoffice.shared.model.IncidentApplicant incidentApplicant : incidentApplicants) {
			listOfApplicant.add(this.createApplicant(incidentApplicant));
		}
		return listOfApplicant;
	}
	
	
	public Applicant createApplicant(@RequestBody com.ss.smartoffice.shared.model.IncidentApplicant incidentApplicant)  {
			Applicant applicant = new Applicant();
			applicant.setApplicantName(incidentApplicant.getFirstName());
			applicant.setEmailId(incidentApplicant.getEmail());
			applicant.setContactMobileNo(incidentApplicant.getMobileNumber());
			applicant.setFirstName(incidentApplicant.getFirstName());
			applicant.setLastName(incidentApplicant.getLastName());
			applicant.setPassedOut(incidentApplicant.getPassoutYear());
			applicant.setDob(incidentApplicant.getDob());
			applicant.setCollegeName(incidentApplicant.getInstitute());
			applicant.setCourseName(incidentApplicant.getCourse());
			applicant.setContactEmailId(incidentApplicant.getEmail());
			applicant.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			applicant.setStartDt(LocalDateTime.now());
			return applicantRepository.save(applicant);
	}
	
	@PostMapping("/create-auth-applicant")
	public void createAuthUserFromApplicants(@RequestBody List<Applicant> applicants) {
		for (Applicant applicant : applicants) {
			this.createAuthUserFromApplicant(applicant);
		}
	} 
	
	
	public Applicant updateApplicant(Applicant applicant) {
		applicant.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		applicant.setStartDt(LocalDateTime.now());
		return applicantRepository.save(applicant);
	}
	public Applicant updateApplicantFromIA(IncidentApplicant incidentApplicant)  {
		try {
			Applicant applicant = applicantRepository.findById(Integer.parseInt(incidentApplicant.getApplicantId())).get();
			applicant.setApplicantName(incidentApplicant.getFirstName());
			applicant.setEmailId(incidentApplicant.getEmail());
			applicant.setContactMobileNo(incidentApplicant.getMobileNumber());
			applicant.setFirstName(incidentApplicant.getFirstName());
			applicant.setLastName(incidentApplicant.getLastName());
			applicant.setPassedOut(incidentApplicant.getPassoutYear());
			applicant.setDob(incidentApplicant.getDob());
			applicant.setGender(incidentApplicant.getGender());
			applicant.setCollegeName(incidentApplicant.getInstitute());
			applicant.setCourseName(incidentApplicant.getCourse());
			applicant.setContactEmailId(incidentApplicant.getEmail());
			applicant.setModifiedBy(commonUtils.getLoggedinEmployeeId());
			applicant.setStartDt(LocalDateTime.now());
			return applicantRepository.save(applicant);
			 
		} catch (Exception e) { 
			e.printStackTrace();
			throw new SmartOfficeException(e);
		}
	}
	 
	
	public Applicant createApplicant(Applicant applicant) {
		applicant.setFirstName(applicant.getFirstName());
		applicant.setLastName(applicant.getLastName());
		applicant.setContactEmailId(applicant.getContactEmailId());
		applicant.setContactMobileNo(applicant.getContactMobileNo());
		applicant.setIsExperienced(applicant.getIsExperienced());
		applicant.setDob(applicant.getDob());
		applicant.setGender(applicant.getGender());
		return applicantRepository.save(applicant);
	}
	
	public AuthUser createAuthUserFromApplicant(Applicant applicant) {
		AuthUser loggedInUser = new AuthUser();
		loggedInUser.setEmployeeId(sysUsrAuthId);
		loggedInUser.setId(Integer.parseInt(sysUsrAuthId));		
		commonUtils.setAuthenticationContext(Integer.parseInt(sysUsrAuthId),"async");
		AuthUser authUser = new AuthUser();
		Event event = new Event();
		event.setName(Event.EventTypes.NewApplicantEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
//		event.setAppToken(commonUtils.getLoggedinAppToken());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey>businessKeys= new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setApplicantId(String.valueOf(applicant.getId()));
		businessKey.setEventId(savedEvent.getId().toString());
		businessKeyRepository.save(businessKey);
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		
		
		authUser.setApplicantId(String.valueOf(applicant.getId()));
		authUser.setFirstName(applicant.getFirstName());
		authUser.setAppClientId("pothigai-power");
		authUser.setGroupId("1");
		authUser.setUserAccessDt(LocalDateTime.now());
		authUser.setUserType("APPLICANT");
		
		authUser.setAcceptedAgmt("N");
		authUser.setUserStatus("ACTIVE");
		authUser.setLastName(applicant.getLastName());
		authUser.setUserName(applicant.getEmailId());
		authUser.setPassword(String.valueOf(commonUtils.getPassword()));		
		authUser.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		authUser.setCreatedDt(LocalDateTime.now()); 
		authUser.setEmailId(applicant.getEmailId());
		authUserRepository.save(authUser);
		
		List<EventKeyValue> eventKeyValues = new ArrayList<>();
		
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("applicantName");
		eventKeyValue.setValue(applicant.getFirstName()+applicant.getLastName());
		eventKeyValueRepository.save(eventKeyValue);
		eventKeyValues.add(eventKeyValue);
		
		EventKeyValue eventKeyValue1 = new EventKeyValue();
		eventKeyValue1.setEventId(savedEvent.getId().toString());
		eventKeyValue1.setKeyPair("userName");
		eventKeyValue1.setValue(authUser.getUserName());
		eventKeyValueRepository.save(eventKeyValue1);
		eventKeyValues.add(eventKeyValue1);

		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("password");
		eventKeyValue2.setValue(authUser.getPassword());
		eventKeyValueRepository.save(eventKeyValue2);
		eventKeyValues.add(eventKeyValue2);
		event.setEventKeyValues(eventKeyValues);
		eventPublisherService.pushEvent(savedEvent);
		  return authUser;
	} 
	
	
	
	@GetMapping("/{id}")
	
	public Optional<Applicant> getApplicantById(@PathVariable(value="id")int id) throws SmartOfficeException{
		return applicantRepository.findById(id);
		
	}
	
	@PutMapping("/{id}")
    public Applicant updateApplicantById(@RequestBody Applicant applicant) throws SmartOfficeException {
		return this.updateApplicant(applicant);
		
	}
	@DeleteMapping("/{id}/delete")
	public void deleteApplicantById(@PathVariable(value="id")Integer id)throws SmartOfficeException{		
		authUserRepository.deleteApplicant(id.toString());
		applicantRepository.deleteById(id);
	}
	
	@PostMapping("/_internal/{id}/app-pdf")
    public Applicant updateApplicantByPdfId(@RequestBody Applicant applicant) throws SmartOfficeException {
		Applicant applicantFromDb=getApplicantById(applicant.getId()).get();
		applicantFromDb.setAppPdfId(applicant.getAppPdfId());
		return applicantRepository.save(applicantFromDb);
		
	}
	
	@GetMapping("/_internal/{id}")
    public Applicant getApplicantByIdInternal(@PathVariable(value="id")int id) throws SmartOfficeException {
		return getApplicantById(id).get();
	}
	
	
}
