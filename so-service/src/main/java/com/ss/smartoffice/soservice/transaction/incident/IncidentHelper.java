package com.ss.smartoffice.soservice.transaction.incident;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.applicant.Applicant;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthToken;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.shared.model.Question;
import com.ss.smartoffice.soservice.master.Questions.QuestionRepo;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.testtemplate.TestTemplateCatagory;
import com.ss.smartoffice.soservice.master.testtemplate.TestTemplateCatagoryRepo;
import com.ss.smartoffice.soservice.master.testtemplate.TestTemplateRepo;
import com.ss.smartoffice.soservice.seed.configs.ConfigService;
import com.ss.smartoffice.soservice.transaction.Interview.Interview;
import com.ss.smartoffice.soservice.transaction.Interview.InterviewHelper;
import com.ss.smartoffice.soservice.transaction.Interview.InterviewRepository;
import com.ss.smartoffice.soservice.transaction.certificatetracker.CertificateTracker;
import com.ss.smartoffice.soservice.transaction.certificatetracker.CertificateTrackerRepository;

@Service
public class IncidentHelper {

	private static Logger log = LoggerFactory.getLogger(IncidentAsyncHelper.class);

	@Value("${generate-certificate.url}")
	private String generateCertificateUrl;
	
	@Value("${certificate-expires-in}")
	private Integer certificateExpiresIn;
	@Autowired
	IncidentRepo incidentRepo;
	@Autowired
	IncidentApplicantRepo incidentApplicantRepo;
	@Autowired
	IncidentTestRepo incidentTestRepo;
	@Autowired
	ConfigService configService;
	@Autowired
	ParticipatingInstitueRepo participatingInstituteRepo;
	@Autowired
	CertificateTrackerRepository certificateTrackerRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@Autowired
	UserGroupEmployeeMappingService userGroupEmployeeMappingService;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	InterviewRepository interviewRepository;
	@Autowired
	TestTemplateRepo testTemplateRepo;
	@Autowired
	IncidentTestTemplateRepo incidentTestTemplateRepo;
	@Autowired
	QuestionRepo questionRepo;
	@Autowired
	IncidentAsyncHelper incidentAsyncHelper;
	@Autowired
	IncidentTestService incidentTestService;
	@Autowired
	IncidentEventGenerator incidentEventGenerator;
	@Autowired
	IncidentTestTemplateQuestionRepo incidentTestTemplateQuestionRepo;
	@Autowired
	IncidentTestQuestionRepo incTestQuerepo;
	@Autowired
	TestTemplateCatagoryRepo testTemplateCatagoryRepo;
	@Autowired
	IncidentTestHistoryRepo incidentTestHistoryRepo;
	@Autowired
	InterviewHelper interviewHelper;

	public Incident processIncident(String action, Incident incident) throws SmartOfficeException {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
//		
		Incident incidentFromDb = new Incident();
		if (!(action.equals("create"))) {
			incidentFromDb = incidentRepo.findById((incident.getId())).get();
			// clear any errors from previous ops
			incidentFromDb.setErrorCode(null);
			incidentFromDb.setErrorMessage(null);
		}

		switch (action) {
		case "create":
			incident = createIncident(incident);
			incident.setIncidentStatus("PENDING-APPROVAL");
			incidentRepo.save(incident);
			incidentEventGenerator.triggerNewIncidentEvent(incident.getId().toString(), incident, loggedInUser);
			break;
		case "approval":
			approve(action, incidentFromDb);
			incidentEventGenerator.triggerIncidentHrEvent(incident.getId().toString(), incidentFromDb, loggedInUser);
			break;
//			(String id,Incident incident,AuthUser loggedInUser)
		case "schedule":
			schedule(incidentFromDb, incident);
			break;
		case "add-applicant":
			addOrUpdateIncidentApplicants(incidentFromDb, incident);
			break;
		case "check-test-eligibility":
			testEligibility(incidentFromDb, incident, null);
			break;
		case "schedule-test":
			initiateTestSchedule(incidentFromDb, incident);
			break;
		case "interview-eligibility":
			interviewEligibility(incidentFromDb, incident, null);
			break;
		case "interview-schedule":
			interviewSchedule(incidentFromDb, incident);
//			incidentEventGenerator.triggerScheduleInterviewer(incidentFromDb,IncidentApplicant ,loggedInUser);
//			(Incident incident,IncidentApplicant applicantFromReq,AuthUser loggedInUser)
			break;
		case "attended":
			unAttended(incidentFromDb, incident);
			break;
		case "certificate-issue":
			issueCertificate(incidentFromDb, incident);
			break;
		case "reallocate-test":
			reAllocateTest(incidentFromDb, incident);
			break;
		case "generate-template":
			generateTemplate(incidentFromDb, incident);
			break;
		case "facility-update":
			updateFacilityDetail(incidentFromDb, incident);
			break;
		case "training-notification":
			sendTrainigNotification(incidentFromDb);
			break;
		default:
			break;
		}
		return incident;
	}

//	-------------Generate Template--------------------------------------------------------------------------------

	public Incident generateTemplate(Incident iFromDb, Incident iFromReq) {
		log.info("Template Generation Trigerred");
		iFromDb.setApproverEmpId(commonUtils.getLoggedinEmployeeId());
		iFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		iFromDb.setModifiedDt(LocalDateTime.now());
		incidentRepo.save(iFromDb);
		Optional<Incident> returns = processCofigTemplateForTest(iFromDb, iFromReq);
		return returns.get();
	}

	public Incident updateFacilityDetail(Incident iFromDb, Incident iFromReq) {
		iFromDb.setFaculty(iFromReq.getFaculty());
		iFromDb.setContactNo(iFromReq.getContactNo());
		iFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		iFromDb.setModifiedDt(LocalDateTime.now());
		return incidentRepo.save(iFromDb);
	}

	public Optional<Incident> processCofigTemplateForTest(Incident i, Incident iFromReq) {
		List<Question> qes = new ArrayList<Question>();

//		System.out.println("size-" + i.getIncidentTestTemplates().size());
		for (int j = 0; j < i.getIncidentTestTemplates().size(); j++) {
			IncidentTestTemplate itt = i.getIncidentTestTemplates().get(j);
			try {
				List<TestTemplateCatagory> ttcs = testTemplateCatagoryRepo
						.findByMTestTemplateId(itt.getTestTemplateId());

				for (TestTemplateCatagory ttc : ttcs) {
					qes = questionRepo.findByTestCategoryIdAndLevel(ttc.getDifficultyCode(),
							ttc.getmTestCatagoryId().toString());
					List<Integer> qIds = new ArrayList<Integer>();

//					System.out.println(qes.size());
					for (Question question : qes) {
						qIds.add(question.id);
					}
//					System.out.println(qIds);
					qIds = commonUtils.randomize(qIds, Integer.valueOf(ttc.getTotalQuestions()));
					itt = formQuestionForTemplate(qIds, qes, ttc, itt.getIncidentId(), itt.getId());
				}
				itt.setCreationStatus("Success");
				incidentTestTemplateRepo.save(itt);
			} catch (NumberFormatException e) {
				itt.setCreationStatus("ERROR");
				incidentTestTemplateRepo.save(itt);
				e.printStackTrace();
			}
		}
		return incidentRepo.findById(i.getId());
	}

	public IncidentTestTemplate formQuestionForTemplate(List<Integer> qIds, List<Question> qes,
			TestTemplateCatagory ttc, Integer iId, Integer ittId) {
		for (Integer qId : qIds) {
			IncidentTestTemplateQuestion ittQ = new IncidentTestTemplateQuestion();
			Question qs = qes.stream().filter(q -> q.getId().equals(qId)).collect(Collectors.toList()).get(0);
			ittQ.setIncidentId(iId);
			ittQ.setIncidentTestTemplateId(ittId);
			ittQ.setQuestionId(qId);
			ittQ.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			ittQ.setCreatedDt(LocalDateTime.now());
			ittQ.setDifficultyLevel(ttc.getDifficultyCode());
			ittQ.setTestCategoryId(ttc.getId());
			ittQ.setQuestion(qs.getQuestion());
			ittQ.setOption1(qs.getOption1());
			ittQ.setOption2(qs.getOption2());
			ittQ.setOption3(qs.getOption3());
			ittQ.setOption4(qs.getOption4());
			ittQ.setCorrectAnswer(qs.getIsCorrect());
			ittQ.setNegativeMark(ttc.getNegativeMarking());
			ittQ.setMarkPerQuestion(ttc.getMarksPerQuestion());
			ittQ.setPassPercentage(ttc.getPassPercentage());
			incidentTestTemplateQuestionRepo.save(ittQ);
		}
		return incidentTestTemplateRepo.findById(ittId).get();
	}

//	----------Reallocate Test ---------------------------------------

	public void reAllocateTest(Incident iFromDb, Incident iFromReq) {
		for (IncidentApplicant iAppFromReq : iFromReq.getIncidentApplicants()) {
			if (commonUtils.isNullOrEmpty(iAppFromReq.getIsReallocate()) == false
					&& commonUtils.isNullOrEmpty(iAppFromReq.getReallocateMessage()) == false) {
				if (iAppFromReq.getIsReallocate().equals("Y")
						&& iAppFromReq.getReallocateMessage().equals("not-started")
						|| iAppFromReq.getReallocateMessage().equals("re-trigger")) {
					iAppFromReq.setReallocateMessage("in-progress");
					incidentApplicantRepo.save(iAppFromReq);
//					System.out.println(iAppFromReq);
					List<IncidentTest> listByIncidentApplicant = iFromDb.getIncidentTests().stream()
							.filter(a -> a.getParticipantId().equals(iAppFromReq.getId())).collect(Collectors.toList());
					iFromDb = deleteTestByIncidentApplicant(iFromReq.getId(), listByIncidentApplicant);
//					System.out.println("iFromDb1=" + iFromDb);
					reAllocate(iFromDb, iFromReq);
				}
			}
		}
	}

	public Incident deleteTestByIncidentApplicant(Integer incidentId, List<IncidentTest> itList) {
		for (IncidentTest it : itList) {
			for (IncidentTestQuestion itq : it.getIncidentTestQuestions()) {
				incTestQuerepo.delete(itq.getId());
			}
			incidentTestRepo.delete(it.getId());
		}
		return incidentRepo.findById(incidentId).get();
	}

	public void reAllocate(Incident iFromDb, Incident iFromReq) {
		for (IncidentApplicant ia : iFromReq.getIncidentApplicants()) {
			ia.setTestScheduledStatus("not-scheduled");
			ia.setIsTestScheduled("Y");
			ia.setReallocateMessage("completed");
			incidentApplicantRepo.save(ia);
//			System.out.println("ia1=" + ia);
		}
		iFromDb = incidentRepo.findById(iFromDb.getId()).get();
		initiateTestSchedule(iFromDb, iFromDb);
	}

//	---------------no-show------------------------
	public Incident unAttended(Incident incidentFromDb, Incident incidentFromReq) {
		try {
			AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();

			for (IncidentApplicant applicantFromReq : incidentFromReq.getIncidentApplicants()) {
				if (applicantFromReq.getIsAttended().equals("N")) {
					Optional<IncidentApplicant> ia = incidentApplicantRepo.findById(applicantFromReq.getId());
					if (ia.isPresent()) {
						ia.get().setIsAttended("N");
						incidentApplicantRepo.save(ia.get());
					}
				}
				if (applicantFromReq.getIsAttended().equals("Y")) {
					Optional<IncidentApplicant> ia = incidentApplicantRepo.findById(applicantFromReq.getId());
					if (ia.isPresent()) {
						ia.get().setIsAttended("Y");
						ia.get().setTraineeStatus("in-progress");
						incidentApplicantRepo.save(ia.get());
					}
				}
			}
			incidentAsyncHelper.triggerTestForTrainees(incidentFromReq, loggedInUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			incidentFromDb.setErrorCode("Error");
			incidentFromDb.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incidentFromDb);
			e.printStackTrace();
		}
		return incidentFromDb;
	}

//	------------------certificate-issue-----------------------
	public Incident issueCertificate(Incident incidentFromDb, Incident incidentFromReq) {
		try {
			HashMap<String, String> buisnessKeys = new HashMap<>();
			for (IncidentApplicant applicantFromReq : incidentFromReq.getIncidentApplicants()) {
				if (applicantFromReq.getCertificateIssued() != null
						&& applicantFromReq.getCertificateIssued().equals("Y")) {
					Optional<IncidentApplicant> ia = incidentApplicantRepo.findById(applicantFromReq.getId());
					if (ia.isPresent()) {
						ia.get().setCertificateIssued("Y");
						incidentApplicantRepo.save(ia.get());
						List<CertificateTracker> certificateTrackers = certificateTrackerRepository
								.findByIncidentApplicantId(ia.get().getId().toString());
						if (!certificateTrackers.isEmpty()) {
							for (CertificateTracker certificateTracker : certificateTrackers) {
								certificateTrackerRepository.save(certificateTracker);
							}
						} else {
							CertificateTracker certificate = new CertificateTracker();
							certificate.setIncidentApplicantId(ia.get().getId().toString());

							certificate.setCertificateCode(
									sequenceGenerationService.nextSeq("CERTIFICATE-CODE", buisnessKeys));
							certificate.setFname(ia.get().getFirstName());
							certificate.setLname(ia.get().getLastName());
							certificate.setLocation(ia.get().getCurrLocation());
							certificate.setScore(ia.get().getScore());
							certificate.setMobNum(ia.get().getMobileNumber());
							certificate.setEmail(ia.get().getEmail());
							certificate.setIssuedDt(LocalDateTime.now()); 

							certificate.setExpiryDate(certificate.getIssuedDt().plusYears(certificateExpiresIn));
							certificate.setIsEnabled("Y");
							certificate.setCreatedBy(commonUtils.getLoggedinEmployeeId());
							certificateTrackerRepository.save(certificate);
							HttpHeaders headers = new HttpHeaders();
							headers.set("Authorization", commonUtils.getLoggedinAppToken());

							HttpEntity<CertificateTracker> request = new HttpEntity<CertificateTracker>(certificate,
									headers);
							ResponseEntity<String> certificates = commonUtils.getRestTemplate()
									.exchange(generateCertificateUrl + certificate.getIncidentApplicantId()
											+ "/generate-certificate/", HttpMethod.GET, request, String.class);
//			
//					commonUtils.getRestTemplate().getForEntity("localhost:6004/so-document-service/document/prints/certificates/"+certificate.getIncidentApplicantId()+"/generate-certificate/", request,String.class);
						}
					}
				}
				if (applicantFromReq.getCertificateIssued().equals("N")) {
					Optional<IncidentApplicant> ia = incidentApplicantRepo.findById(applicantFromReq.getId());
					if (ia.isPresent()) {
						ia.get().setCertificateIssued("N");
						incidentApplicantRepo.save(ia.get());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			incidentFromDb.setErrorCode("Error");
			incidentFromDb.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incidentFromDb);
		}
		return incidentFromDb;
	}

//	---------------Employee conversion ---------------------------------------

	public Integer statusManupulationForEmpConversion(Map<Integer, String> map, Integer id) {
		map.entrySet().stream().forEach(e -> {
			if (e.getValue() != null) {
				if (e.getValue().equals("Approved")) {
					Optional<IncidentApplicant> ia = incidentApplicantRepo.findById(e.getKey());
					if (ia.isPresent()) {
						if (ia.get().getFinalDecision() == null) {
							ia.get().setFinalDecision("Approved");
							incidentApplicantRepo.save(ia.get());
						} else if (!ia.get().getFinalDecision().equals("Approved")) {
							ia.get().setFinalDecision("Approved");
							incidentApplicantRepo.save(ia.get());
						}
					} else {
						throw new SmartOfficeException("No incident Applicant");
					}
				}

				if (e.getValue().equals("Rejected")) {
					Optional<IncidentApplicant> ia = incidentApplicantRepo.findById(e.getKey());

					if (ia.isPresent()) {
						ia.get().setFinalDecision("Rejected");
						incidentApplicantRepo.save(ia.get());
					} else {
						throw new SmartOfficeException("No incident Applicant");
					}
				}

				if (e.getValue().equals("on-hold")) {
					Optional<IncidentApplicant> ia = incidentApplicantRepo.findById(e.getKey());
					if (ia.isPresent()) {
						ia.get().setFinalDecision("on-hold");
						incidentApplicantRepo.save(ia.get());
					} else {
						throw new SmartOfficeException("No incident Applicant");
					}
				}

			}

		});

		return id;
	}

	public void onBoardEmployee(Integer id) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		CompletableFuture<List<IncidentApplicant>> onBoardedApplicants = incidentAsyncHelper.employeeConversion(id,
				loggedInUser);
		log.info("On boarded Applicants - {}", onBoardedApplicants);
	}

//-------------------Schedule Test---------------------------------------------------------------

	public Incident initiateTestSchedule(Incident incidentFromDb, Incident incidentFromReq) {
		try {
			for (IncidentApplicant applicantFromReq : incidentFromReq.getIncidentApplicants()) {
				try {
					log.info("Intiate Schedule");
					log.info("isTestSchedule - {}", isNullOrEmpty(applicantFromReq.getIsTestScheduled()));
					if (isNullOrEmpty(applicantFromReq.getIsTestScheduled()) == false) {
						if (applicantFromReq.getIsTestScheduled().equals("Y")
								&& applicantFromReq.getTestScheduledStatus().equals("not-scheduled")) {
							List<IncidentApplicant> incAFromDb = incidentFromDb.getIncidentApplicants().stream().filter(
									incidentApplicants -> incidentApplicants.getId().equals(applicantFromReq.getId()))
									.collect(Collectors.toList());
							testScheduleCreation(incAFromDb, applicantFromReq);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			incidentFromDb = incidentRepo.findById(incidentFromReq.getId()).get();
			callCreateTestAsync(incidentFromDb);
		} catch (Exception e) {
			e.printStackTrace();
			incidentFromDb.setErrorCode("Error");
			incidentFromDb.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incidentFromDb);
		}
		return incidentFromDb;
	}

	public IncidentApplicant testScheduleCreation(List<IncidentApplicant> applicantsListFromDb,
			IncidentApplicant applicantFromReq) {
		IncidentApplicant applicantFromDb = applicantsListFromDb.get(0);
		if (commonUtils.isNullOrEmpty(applicantFromDb.getTestScheduledStatus()) == false
				&& applicantFromDb.getTestScheduledStatus().equals("not-scheduled")) {
			applicantFromDb.setIsTestScheduled("Y");
			applicantFromDb.setTestScheduledDt(applicantFromReq.getTestScheduledDt());
			applicantFromDb.setTestScheduledStatus("in-progress");
			applicantFromDb.setTestEligibleEmpId(commonUtils.getLoggedinEmployeeId());
			applicantFromDb.setModifiedBy(commonUtils.getLoggedinUserId());
			applicantFromDb.setModifiedDt(LocalDateTime.now());
			incidentApplicantRepo.save(applicantFromDb);
		}
		return applicantFromDb;
	}

	public void callCreateTestAsync(Incident incidentFromDb) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		CompletableFuture<Incident> afterTestCreation = incidentAsyncHelper.testCreation(incidentFromDb, loggedInUser);
		log.info("After creating test asynchronously {}", afterTestCreation);
	}

//----------------------------------Interview Eligibility-------------------------------------------------------

	public List<IncidentApplicant> interviewEligibility(Incident incidentFromDb, Incident incidentFromReq,
			AuthUser loggedInUser) {

		if (loggedInUser == null) {
			loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		}
		List<IncidentApplicant> eligibleIncidentApplicants = new ArrayList<IncidentApplicant>();
		for (IncidentApplicant applicantFromReq : incidentFromReq.getIncidentApplicants()) {
			IncidentApplicant incApplicant = new IncidentApplicant();
			try {
				if (isNullOrEmpty(applicantFromReq.getIsInterviewEligible()) == false) {
					if (applicantFromReq.getIsInterviewEligible().equals("Y")) {
						incApplicant = (IncidentApplicant) incidentFromDb.getIncidentApplicants().stream()
								.filter(incA -> incA.getId().equals(applicantFromReq.getId()))
								.collect(Collectors.toList()).get(0);
						updateIntEligibityInDB(incApplicant);
						eligibleIncidentApplicants.add(incApplicant);
					}
				}
			} catch (Exception e) {
				incApplicant.setErrorCode("ERROR");
				incApplicant.setErrorMessage(e.getMessage());
				incApplicant.setInterviewEligibleStatus("ERROR");
				incidentApplicantRepo.save(incApplicant);
				e.printStackTrace();
			}
		}
		// asynchronous call
		CompletableFuture<List<IncidentApplicant>> eligibleIncidentApplicantsComp = incidentAsyncHelper
				.generateApplicants(eligibleIncidentApplicants, loggedInUser);
		log.info("Final eligible after completion of async func {}", eligibleIncidentApplicantsComp);
		return eligibleIncidentApplicants;

	}

	public void updateIntEligibityInDB(IncidentApplicant applicantFromDb) {
		if (applicantFromDb.getInterviewEligibleStatus() == null) {
			applicantFromDb.setIsInterviewEligible("Y");
			applicantFromDb.setInterviewEligibleDt(LocalDateTime.now());
			applicantFromDb.setInterviewEligibleStatus("IE-IN-PROGRESS");
			applicantFromDb.setInterviewEligibleEmpId(commonUtils.getLoggedinEmployeeId());
			applicantFromDb.setModifiedBy(commonUtils.getLoggedinUserId());
			applicantFromDb.setModifiedDt(LocalDateTime.now());
			incidentApplicantRepo.save(applicantFromDb);
		}
	}

//----------------------------------Interview Schedule-------------------------------------------------------
	public Incident interviewSchedule(Incident incidentFromDb, Incident incidentFromReq) { 

		int scheduleCount = 0;
		try {
			log.debug("Interview Scheduling started for incident --> "+incidentFromDb.getIncidentName());
			for (IncidentApplicant applicantFromReq : incidentFromReq.getIncidentApplicants()) {  
				if (commonUtils.isFlagSet(applicantFromReq.getIsInterviewEligible())) {
					IncidentApplicant incApplnFromDB = incidentFromDb.getIncidentApplicants().stream().filter(
							incidentApplicants -> incidentApplicants.getId().equals(applicantFromReq.getId()))
							.collect(Collectors.toList()).get(0);  
					if (incApplnFromDB.getInterviewScheduledStatus() != null) {
						throw new Exception("Interview Schedule already set for applicant("+incApplnFromDB.getFirstName()+" "+incApplnFromDB.getFirstName()+")");
					}
					log.debug("Update Schedule information in applicant record");
					incApplnFromDB = updateIntApplntWithSchedule(incApplnFromDB,applicantFromReq);
					log.debug("Create interview and interview rounds"); 
					interviewHelper.createInterview(incApplnFromDB);
					log.debug("Update InterviewScheduledStatus in applicant");
					incApplnFromDB.setInterviewScheduledStatus("InterviewSchedule-Created"); 
					incidentApplicantRepo.save(incApplnFromDB);
					incidentEventGenerator.triggerScheduleInterview(incidentFromDb, applicantFromReq, commonUtils.getLoggedInUser());
					scheduleCount++;
				} 
			}
			incidentFromDb = incidentRepo.findById(incidentFromReq.getId()).get();
		} catch (Exception e) { 
			e.printStackTrace(); 
			incidentFromDb.setErrorCode("Error");
			incidentFromDb.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incidentFromDb);
		}
		log.info(scheduleCount+" Applicants were scheduled for interview in the incident("+incidentFromDb.getIncidentName()+")");
		return incidentFromDb;
	}

	private IncidentApplicant updateIntApplntWithSchedule( IncidentApplicant incApplnFromDB,
			IncidentApplicant applicantFromReq) { 
		try {
			incApplnFromDB.setIsInterviewScheduled("Y");
			incApplnFromDB.setScheduledInterviewDt(LocalDateTime.now());
			incApplnFromDB.setFirstInterviewerId(applicantFromReq.getFirstInterviewerId());
			incApplnFromDB.setSecondInterviewerId(applicantFromReq.getSecondInterviewerId());
			incApplnFromDB.setThirdInterviewerId(applicantFromReq.getThirdInterviewerId());
			incApplnFromDB.setInterviewScheduledDt(applicantFromReq.getInterviewScheduledDt()); 
			incApplnFromDB.setSecondInterviewDt(applicantFromReq.getSecondInterviewDt());
			incApplnFromDB.setThirdInterviewDt(applicantFromReq.getThirdInterviewDt());
			incApplnFromDB.setInterviewScheduledStatus("InterviewSchedule-Created");
			incApplnFromDB.setInterviewScheduleEmpid(commonUtils.getLoggedinEmployeeId());
			incApplnFromDB.setModifiedBy(commonUtils.getLoggedinUserId());
			incApplnFromDB.setModifiedDt(LocalDateTime.now());
			applicantFromReq.setErrorCode("");
			applicantFromReq.setErrorMessage("");
			incidentApplicantRepo.save(incApplnFromDB); 
		} catch (Exception e) { 
			applicantFromReq.setErrorCode("Error");
			applicantFromReq.setErrorMessage(e.getLocalizedMessage());
			incidentApplicantRepo.save(applicantFromReq);
			e.printStackTrace();
			log.error("Error updating incidentApplicant("+applicantFromReq.getId()+")"+e.getLocalizedMessage());
			throw e;
		}
		return incApplnFromDB;
	}

//------------------------Add or Update incident Applicants----------------------------------------------------------------------------------------
	public Incident addOrUpdateIncidentApplicants(Incident incidentFromDb, Incident incident) {
		try {
			AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
			if (incidentFromDb.getIncidentStatus().equals("OPEN")
					|| incidentFromDb.getIncidentType().equals("Training")) {
				if (!commonUtils.isHr()) {
					incident.setErrorCode("ERROR");
					incident.setErrorCode("Not valid user");
					incidentRepo.save(incident);
					throw new SmartOfficeException("Not a valid user to perform this action");
				} else {
					for (IncidentApplicant applicant : incident.getIncidentApplicants()) {
						applicant.setIncidentId(incident.getId().toString());
						applicant.setVcId(incident.getVcId());
						incidentApplicantRepo.save(applicant);
						if (incidentFromDb.getIncidentType().equals("Training")) {
							if (applicant.getIncidentId() == null && applicant.getEmployeeId() != null) {
								incidentEventGenerator.triggerAssignTrainingEvent(incidentFromDb, applicant,
										loggedInUser);
							}
						}
					}
					if (incident.getApplicantIdsToDelete() != null && incident.getApplicantIdsToDelete().size() > 0) {
						incident = deleteApplicant(incident);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			incidentFromDb.setErrorCode("Error");
			incidentFromDb.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incidentFromDb);
		}
		return incident;
	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty())
			return false;
		return true;
	}

	@Transactional
	private Incident deleteApplicant(Incident incident) {
		for (String id : incident.getApplicantIdsToDelete()) {
			incidentApplicantRepo.delete(Integer.parseInt(id));
		}
		incident = incidentRepo.findById((incident.getId())).get();
		return incident;
	}

	public Incident createIncident(@RequestBody Incident incident) throws SmartOfficeException {
		Incident incidentToDb = new Incident();
		HashMap<String, String> buisnessKeys = new HashMap<>();
		List<IncidentApplicant> incidentAppToDb = new ArrayList<IncidentApplicant>();
		try {
			if (!commonUtils.isHr()) {
				throw new SmartOfficeException("Not a valid user to perform this action");
			} else {
				incidentToDb = formIncident(incidentToDb, incident);
				incidentToDb = incidentRepo.save(incidentToDb);
				if (incident.getIncidentType().equals("Recruitment")) {
					incidentToDb.setIncidentCode(sequenceGenerationService.nextSeq("RECRUITMENT-EVENT", buisnessKeys));
				} else if (incident.getIncidentType().equals("Training")) {
					incidentToDb.setIncidentCode(sequenceGenerationService.nextSeq("TRAINING-EVENT", buisnessKeys));
				}
				incidentToDb.setIncidentTestTemplates(incident.getIncidentTestTemplates());
				incidentToDb.setIncidentApplicants(incidentAppToDb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			incidentToDb.setErrorCode("ERROR");
			incidentToDb.setErrorMessage(e.getMessage());
			incidentToDb = incidentRepo.save(incidentToDb);
		}
		return incidentToDb;
	}

	public Incident formIncident(Incident iDb, Incident iReq) {
		try {
			memployee empById = employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId()))
					.get();
			iDb.setIncidentName(iReq.getIncidentName());
			iDb.setIncidentDesc(iReq.getIncidentDesc());
			iDb.setIncidentCreatedEmpId(commonUtils.getLoggedinEmployeeId());
			iDb.setIncidentCreatedDt(LocalDateTime.now());
			iDb.setIncidentDate(LocalDate.now()); // IncidentDate is not used yet
			iDb.setCreatedBy(commonUtils.getLoggedinUserId());
			iDb.setIsEnabled("Y");
			iDb.setHr1UsrGrpId(empById.getHr1UsrGrpId());
			iDb.setHr2UsrGrpId(empById.getHr2UsrGrpId());
			iDb.setApproveUsrGrpId(empById.getDirUsrGrpId());
			iDb.setIncidentStatus("CREATED");
			iDb.setVcId(iReq.getVcId());
			iDb.setFirstInterviewerId(iReq.getFirstInterviewerId());
			iDb.setSecondInterviewerId(iReq.getSecondInterviewerId());
			iDb.setThirdInterviewerId(iReq.getThirdInterviewerId());
			iDb.setIsEntryLevel(iReq.getIsEntryLevel());
			iDb.setIsProfessional(iReq.getIsProfessional());
			iDb.setHasTest(iReq.getHasTest());
			iDb.setHasInterview(iReq.getHasInterview());
			iDb.setHandlingGroupId(iReq.getHandlingGroupId());
			iDb.setTrStartDt(iReq.getTrStartDt());
			iDb.setTrEndDt(iReq.getTrEndDt());
			iDb.setShowScore(iReq.getShowScore());
//		iDb.setTrainingName(iReq.getTrainingName());
			iDb.setNoOfDays(iReq.getNoOfDays());
			iDb.setFaculty(iReq.getFaculty());
			iDb.setContactNo(iReq.getContactNo());
			iDb.setSkillsAqd(iReq.getSkillsAqd());
			iDb.setMapLocId(iReq.getMapLocId());
			iDb.setIncidentType(iReq.getIncidentType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			iDb.setErrorCode("Error");
			iDb.setErrorMessage(e.getLocalizedMessage());
		}
		return iDb;
	}

	public void approve(String action, Incident incidentFromDb) {
		log.info("Approval Starts");
		try {
			if (incidentFromDb.getIncidentStatus().equals("PENDING-APPROVAL")) {
				List<String> userGroupIds = commonUtils.findLoggedInUserGroups();
				if (!userGroupIds.contains(incidentFromDb.getApproveUsrGrpId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");
				} else {
					incidentFromDb.setIncidentStatus("OPEN");
					incidentFromDb.setApproverEmpId(commonUtils.getLoggedinEmployeeId());
					incidentFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
					incidentFromDb.setModifiedDt(LocalDateTime.now());
					incidentRepo.save(incidentFromDb);
				}
			}
		} catch (Exception e) {
			incidentFromDb.setErrorCode("ERROR");
			incidentFromDb.setErrorMessage(e.getMessage());
			incidentRepo.save(incidentFromDb);
			e.printStackTrace();
		}
	}

	private void schedule(Incident incidentFromDb, Incident incident) {
		try {
			if (incidentFromDb.getIncidentStatus().equals("OPEN")) {
				Interview interviewTracker = new Interview();
				List<IncidentApplicant> incidentApplicants = new ArrayList<IncidentApplicant>();
				incidentApplicants = incident.getIncidentApplicants();
				for (IncidentApplicant applicant : incidentApplicants) {
					try {
						if (applicant.getIsApplicantScheduled().equals("Y")) {
							interviewTracker.setApplicantId(applicant.getId().toString());
							interviewTracker.setVcId(applicant.getVcId()); 
							interviewTracker.setFirstInterviewerId(applicant.getFirstInterviewerId());
							interviewTracker.setFirstInterviewerStatus("OPEN");
							interviewTracker.setSecondInterviewerId(applicant.getSecondInterviewerId());
							interviewTracker.setSecondInterviewerStatus("OPEN");
							interviewTracker.setThirdInterviewerId(applicant.getThirdInterviewerId());
							interviewTracker.setThirdInterviewerStatus("OPEN");
							interviewRepository.save(interviewTracker);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				incident.setIsScheduled("Y");
				incident.setScheduleEmpId(incident.getScheduleEmpId());
				incident.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				incident.setModifiedDt(LocalDateTime.now());
				incidentRepo.save(incident);
			}
		} catch (Exception e) {
			incident.setErrorCode("ERROR");
			incident.setErrorMessage(e.getMessage());
			incidentRepo.save(incident);
			e.printStackTrace();
		}
	}

	public void createIncidentTestTemplate(Integer incidentId, List<Integer> testTemplateIds) {
		try {
			for (Integer testTemplateId : testTemplateIds) {
				IncidentTestTemplate incidentTestTemplate = new IncidentTestTemplate();
				incidentTestTemplate.setIncidentId(incidentId);
				incidentTestTemplate.setTestTemplateId(testTemplateId);
				incidentTestTemplate.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				incidentTestTemplate.setCreatedDt(LocalDateTime.now());
				incidentTestTemplate = incidentTestTemplateRepo.save(incidentTestTemplate);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Incident incident = incidentRepo.findById(incidentId).get();
			incident.setErrorCode("ERROR");
			incident.setErrorMessage(e.getMessage());
			incidentRepo.save(incident);
		}
	}

	public List<Integer> formQuestionForTestConfig(Integer testCategoryId, Integer numberOfQuestions, String level) {
		log.info("formQuestionForTestConfig");
		List<Question> questionsByCategoryId = questionRepo.findByTestCategoryIdAndLevel(testCategoryId.toString(),
				level);
		List<Integer> listOfQuestionIds = new ArrayList<Integer>();
		for (Question question : questionsByCategoryId) {
			listOfQuestionIds.add(question.id);
		}
		log.info("listOfQuestionIds - " + listOfQuestionIds + "and Number of questions - " + numberOfQuestions);
		listOfQuestionIds = commonUtils.randomize(listOfQuestionIds, numberOfQuestions);
		return listOfQuestionIds;
	}

	public List<IncidentApplicant> testEligibility(Incident incidentFromDb, Incident incidentFromReq,
			AuthUser loggedInUser) {
		if (loggedInUser == null) {
			loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		}
		List<IncidentApplicant> eligibleIncidentApplicants = new ArrayList<IncidentApplicant>();
		for (IncidentApplicant incidentApplicant : incidentFromReq.getIncidentApplicants()) {
			IncidentApplicant incApplicant = new IncidentApplicant();
			try {
				if (incidentApplicant.getIsEligibleForTest() != null
						&& incidentApplicant.getIsEligibleForTest().equals("Y")) {
					incApplicant = (IncidentApplicant) incidentFromDb.getIncidentApplicants().stream()
							.filter(incA -> incA.getId().equals(incidentApplicant.getId())).collect(Collectors.toList())
							.get(0);
					incApplicant = makingTestEligible(incApplicant);
					eligibleIncidentApplicants.add(incApplicant);
				}
			} catch (Exception e) {
				incApplicant.setErrorCode("ERROR");
				incApplicant.setErrorMessage(e.getMessage());
				incApplicant.setTestEligibilityStatus("ERROR");
				incidentApplicantRepo.save(incApplicant);
				e.printStackTrace();
			}
		}
		// asynchronous call
		CompletableFuture<List<IncidentApplicant>> eligibleIncidentApplicantsComp = incidentAsyncHelper
				.generateApplicants(eligibleIncidentApplicants, loggedInUser);
		log.info("Final eligible after completion of async func {}", eligibleIncidentApplicantsComp);
		return eligibleIncidentApplicants;
	}

	public IncidentApplicant makingTestEligible(IncidentApplicant incApp) {
		try {
			incApp.setIsEligibleForTest("Y");
			incApp.setTestEligibilityStatus("TE-IN-PROGRESS");
			incidentApplicantRepo.save(incApp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			incApp.setErrorCode("Error");
			incApp.setErrorMessage(e.getLocalizedMessage());
			incidentApplicantRepo.save(incApp);
		}
		return incApp;
	}

	public CompletableFuture<Incident> triggerRegenration(Integer id, Incident i) {
		AuthUser loggedInUser;
		loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		try {

			log.info("Regeneration Async process trigerred");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			i.setErrorCode("Error");
			i.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(i);
		}
		return incidentAsyncHelper.regenerate(id, i, loggedInUser);
	}

// -----------Add Training Applicant--------// 

	public String trainingEventApplicant(String incidentId, List<IncidentApplicant> incAppFromReq) {
		String result = "";
		try {
			for (IncidentApplicant incApp : incAppFromReq) {
				IncidentApplicant incidentapplicant = new IncidentApplicant();
				memployee employee = new memployee();
				List<IncidentApplicant> incidentapplicantList = new ArrayList<IncidentApplicant>();
				incidentapplicantList = incidentApplicantRepo.findAlreadyExiste(incApp.getEmployeeId(), incidentId);
				if (incidentapplicantList.size() == 0) {
					employee = employeeRepository.findById(Integer.parseInt(incApp.getEmployeeId())).get();
					incidentapplicant.setFirstName(employee.getFirstName());
					incidentapplicant.setLastName(employee.getLastName());
					incidentapplicant.setMobileNumber(employee.getContactMobileNo());
					incidentapplicant.setEmail(employee.getContactEmailId());
					incidentapplicant.setDob(employee.getDob());
					incidentapplicant.setIncidentId(incidentId);
					incidentapplicant.setEmployeeId(incApp.getEmployeeId());
					incidentApplicantRepo.save(incidentapplicant);
				}
			}
			result = "Success";

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}

		return result;
	}

//	-------Delete--------

	public String deleteByType(String type, Incident i) {
		String result = "";
		try {
			switch (type) {
			case "test":
				result = deleteByTypeTest(i);
				break;

			case "template":
				result = deleteByTypeTemplate(i);
				break;

			case "test-and-template":
				deleteAllTemplatesAndTests(i);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void deleteTest(Integer id, Integer applicantId) {
		List<IncidentTest> incidentTest = incidentTestRepo.findByIncidentAndApplicantId(id, applicantId);
		Boolean isTestPresent = false;
		for (IncidentTest test : incidentTest) {
			if (test.getTestStatus() != null) {
				isTestPresent = true;
			}
		}
		if (isTestPresent == false) {
			IncidentApplicant incidentApplicantFromDb = incidentApplicantRepo.findById(applicantId).get();
			incidentApplicantFromDb.setIsTestScheduled("N");
			incidentApplicantFromDb.setTestScheduledStatus("not-scheduled");
			incidentApplicantFromDb.setTestScheduledDt(null);
			incidentApplicantRepo.save(incidentApplicantFromDb);
			incidentTestRepo.deleteTest(id, applicantId);
		} else {
			throw new SmartOfficeException("Applicant Have Attend the Test, Cant Able to unassigned now");
		}
	}

	public String deleteByTypeTemplate(Incident i) {
		String result = "";
		boolean isTestExist = i.getIncidentTests().isEmpty();
		if (isTestExist == false) {
			result = "Test exists, Delete test before deleting template";
		} else {
			for (IncidentTestTemplate itt : i.getIncidentTestTemplates()) {
				try {
					for (IncidentTestTemplateQuestion ittq : itt.getIncidentApplicantTemplateQuestions()) {
						incidentTestTemplateQuestionRepo.delete(ittq.getId());
					}
					incidentTestTemplateRepo.delete(itt.getId());
					result = "Template has been deleted";
				} catch (Exception e) {
					e.printStackTrace();
					result = e.getMessage();
				}
			}

			Incident is = incidentRepo.findById(i.getId()).get();
			is.setIncidentStatus("PENDING-APPROVAL");
			incidentRepo.save(is);
		}

		return result;
	}

	public Incident sendTrainigNotification(Incident incidentFromDb) {
		try {
			AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
			for (IncidentApplicant applicant : incidentFromDb.getIncidentApplicants()) {
				if (incidentFromDb.getIncidentType().equals("Training")) {
					incidentEventGenerator.triggerAssignTrainingEvent(incidentFromDb, applicant, loggedInUser);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			incidentFromDb.setErrorCode("Error");
			incidentFromDb.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incidentFromDb);
		}
		return incidentFromDb;
	}

	public String deleteByTypeTest(Incident i) {
		String result = "";

		for (IncidentTest it : i.getIncidentTests()) {
			try {
				UpdateApplicant(it.getParticipantId());
				for (IncidentTestQuestion itq : it.getIncidentTestQuestions()) {
					incTestQuerepo.delete(itq.getId());
				}
				incidentTestRepo.delete(it.getId());
				result = "Test has been deleted";
			} catch (Exception e) {
				e.printStackTrace();
				result = e.getMessage();
			}
		}
		try {
			incidentTestHistoryRepo.deleteTestHistory(i.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	public String UpdateApplicant(Integer id) {
		String result = "";
		try {
			IncidentApplicant incidentApplicantFromDb = incidentApplicantRepo.findById(id).get();
			incidentApplicantFromDb.setIsTestScheduled("N");
			incidentApplicantFromDb.setTestScheduledStatus("not-scheduled");
			incidentApplicantFromDb.setTestScheduledDt(null);
			incidentApplicantRepo.save(incidentApplicantFromDb);
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	public void deleteAllTemplatesAndTests(Incident i) {
		for (IncidentTest it : i.getIncidentTests()) {
			try {
				for (IncidentTestQuestion itq : it.getIncidentTestQuestions()) {
					incTestQuerepo.delete(itq.getId());
				}
				incidentTestRepo.delete(it.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (IncidentTestTemplate itt : i.getIncidentTestTemplates()) {
			try {
				for (IncidentTestTemplateQuestion ittq : itt.getIncidentApplicantTemplateQuestions()) {
					incidentTestTemplateQuestionRepo.delete(ittq.getId());
				}
				incidentTestTemplateRepo.delete(itt.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Incident is = incidentRepo.findById(i.getId()).get();
		is.setIncidentStatus("PENDING-APPROVAL");
		incidentRepo.save(is);
	}

}
