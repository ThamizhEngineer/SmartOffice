package com.ss.smartoffice.soservice.transaction.incident;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.applicant.Applicant;
import com.ss.smartoffice.soservice.master.Questions.QuestionRepo;
import com.ss.smartoffice.soservice.master.employee.EmployeeProfileBusHelper;
import com.ss.smartoffice.soservice.master.testtemplate.TestTemplateCatagoryRepo;
import com.ss.smartoffice.soservice.master.testtemplate.TestTemplateRepo;
import com.ss.smartoffice.soservice.transaction.recruitment.ApplicantRepository;
import com.ss.smartoffice.soservice.transaction.recruitment.ApplicantService;

@Service
@Lazy
public class IncidentAsyncHelper {
	
	@Autowired
	ApplicantRepository applicantRepo;
	@Autowired
	ApplicantService applicantService;
	@Autowired
	IncidentRepo incidentRepo;
	@Autowired
	IncidentApplicantRepo incidentApplicantRepo;
	@Autowired
	IncidentTestRepo incidentTestRepo;
	@Autowired
	IncidentTestQuestionRepo incTestQuestionRepo;
	@Autowired
	QuestionRepo questionrepo;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	TestTemplateRepo testTemplateRepo;
	
	@Autowired
	TestTemplateCatagoryRepo testTemplateCatagoryRepo;
	@Autowired
	EmployeeProfileBusHelper empProfileBusHelper;
	@Autowired
	IncidentHelper incHelper;
	@Autowired
	IncidentEventGenerator incidentEventGenerator;
	@Autowired
	IncidentTestTemplateRepo incidentTestTemplateRepo;
	@Autowired
	IncidentTestTemplateQuestionRepo incidentTestTemplateQuestionRepo;
	@Autowired
	AuthUserRepository authUserRepo;



	private static Logger log = LoggerFactory.getLogger(IncidentAsyncHelper.class);
	
//	Create Applicants and Auth user entries --------------------------------------------------------------------------------------
	@Lazy
	@Async("asyncThreadPoolTaskExecutor")
	public CompletableFuture<List<IncidentApplicant>> generateApplicants(List<IncidentApplicant> listOfEligibleIncidentApplicants,AuthUser loggedInUser) {	
		log.info("Generate Applicant - Async starts");
		log.info(loggedInUser+"");
		commonUtils.setAuthenticationContext(loggedInUser,"async");

		log.info(commonUtils.getLoggedinEmployeeId());
		checkIfApplicantOrAuthExists(listOfEligibleIncidentApplicants);
		return CompletableFuture.completedFuture(listOfEligibleIncidentApplicants);
	}
	
	public void checkIfApplicantOrAuthExists(List<IncidentApplicant> listOfEligibleIncidentApplicants) {
		try {
			for (IncidentApplicant eligibleIncidentApplicant : listOfEligibleIncidentApplicants) {
				if(eligibleIncidentApplicant.getIsEligibleForTest().equals("Y") || eligibleIncidentApplicant.getIsInterviewEligible().equals("Y")) {
					try {
						Applicant applicant = applicantRepo.findByEmailId(eligibleIncidentApplicant.getEmail());
						log.info("Checking/creating auth and applicant");
						log.info(applicant+"");
						if (applicant==null) {
							log.info("In if - "+eligibleIncidentApplicant.getEmail()); 
							// create a new applicant
							applicant = applicantService.createApplicant(eligibleIncidentApplicant);
						}
						Optional<AuthUser> a = authUserRepo.findByEmailId(eligibleIncidentApplicant.getEmail());
						boolean isAuthPresent = a.isPresent();
						if (isAuthPresent==false) {
							//create a new auth user
							AuthUser authUser = applicantService.createAuthUserFromApplicant(applicant);
							applicant.setLoginUserId(authUser.getId()+"");
							applicant = applicantService.updateApplicant(applicant);	
						}

						if(eligibleIncidentApplicant.getIsInterviewEligible().equals("Y")) {
							eligibleIncidentApplicant.setInterviewEligibleStatus("IE-COMPLETED");
						}
						if(eligibleIncidentApplicant.getIsEligibleForTest().equals("Y")) {
							eligibleIncidentApplicant.setTestEligibilityStatus("TE-COMPLETED");	
						}
						
						eligibleIncidentApplicant.setApplicantId(String.valueOf(applicant.getId()));
						incidentApplicantRepo.save(eligibleIncidentApplicant);
						
					} catch (Exception e) {
						if(eligibleIncidentApplicant.getIsInterviewEligible().equals("Y")) {
							eligibleIncidentApplicant.setInterviewEligibleStatus("IE-ERROR");
							eligibleIncidentApplicant.setErrorCode("ERROR");
							eligibleIncidentApplicant.setErrorMessage(e.getMessage());
							incidentApplicantRepo.save(eligibleIncidentApplicant);
							log.info("Error");
							e.printStackTrace();
						}
						if(eligibleIncidentApplicant.getIsEligibleForTest().equals("Y")) {
							eligibleIncidentApplicant.setTestEligibilityStatus("TE-ERROR");
							eligibleIncidentApplicant.setErrorCode("ERROR");
							eligibleIncidentApplicant.setErrorMessage(e.getMessage());
							incidentApplicantRepo.save(eligibleIncidentApplicant);
							log.info("Error");
							e.printStackTrace();	
						}
						
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//------------------------Create incident test--------------------------------------------------------------------------------------------------------------	
	
	@Lazy
	@Async("asyncThreadPoolTaskExecutor")
	public CompletableFuture<Incident> testCreation(Incident incidentFromDb,AuthUser loggedInUser) {	
		try {
			log.info("Test-creation-Async-starts");
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			log.info("0-1");
			List<IncidentApplicant> applicantsSchduledForTest = incidentFromDb.getIncidentApplicants().stream().filter(
					a -> a.getIsTestScheduled().equals("Y") && a.getTestScheduledStatus().equals("in-progress")).collect(Collectors.toList());
			log.info("0-2");
			log.info(applicantsSchduledForTest+"");
			applicantsSchduledForTest.forEach((applicant) -> { 
				createTestForApplicant(incidentFromDb,applicant);
				});
		} catch (Exception e) {
			incidentFromDb.setErrorCode("ERROR");
			incidentFromDb.setErrorMessage(e.getMessage());
			incidentRepo.save(incidentFromDb);
			e.printStackTrace();
		}		
		return CompletableFuture.completedFuture(incidentFromDb);
	}
	
	public void createTestForApplicant(Incident incidentFromDb,IncidentApplicant applicant) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		try {
			log.info("createTestForApplicant");
			log.info("0-3");
			incidentFromDb.getIncidentTestTemplates().forEach((incidentTestTemplate) -> { 
				IncidentTest incidentTest = new IncidentTest();
				incidentTest.setCreatedBy(commonUtils.getLoggedinUserId());
				incidentTest.setIncidentId(incidentTestTemplate.getIncidentId());
				log.info("Incident Id from incidentTestTemplate to incidentTest="+incidentTestTemplate.getIncidentId());
				incidentTest.setDuration(incidentTestTemplate.getDuration());
				incidentTest.setPassPercentage(incidentTestTemplate.getPassPercentage());
				incidentTest.setParticipantId(applicant.getId());
				incidentTest.setIncidentTtId(String.valueOf(incidentTestTemplate.getId()));
				log.info("incidentTestTemplate Id from incidentTestTemplate to incidentTest="+incidentTestTemplate.getId());

				incidentTest.setApplicantId(applicant.getApplicantId());
				List<IncidentTestTemplateQuestion> beforeShuffle = incidentTestTemplate.getIncidentApplicantTemplateQuestions();
				incidentTest.setNoOfQuestions(commonUtils.nullEmptyCheck(incidentTestTemplate.getTotalQuestions()));
				incidentTest = incidentTestRepo.save(incidentTest);
				List<Integer> shuffledOrder = shuffledArray(beforeShuffle.size());
				for (int i = 0; i < beforeShuffle.size(); i++) {						
					IncidentTestTemplateQuestion incidentTestTemplateQuestions = beforeShuffle.get(i);
					IncidentTestQuestion incTestQuestion = new IncidentTestQuestion();
					incTestQuestion.setTIncidentTestId(incidentTest.getId());
					incTestQuestion.setCreatedBy(commonUtils.getLoggedInUser().toString());
					incTestQuestion.setQuestionId(incidentTestTemplateQuestions.getQuestionId());
					incTestQuestion.setQuestionOrder(shuffledOrder.get(i));
					incTestQuestion.setTotalQuestions(commonUtils.nullEmptyCheck(incidentTestTemplate.getTotalQuestions()));
					incTestQuestion = populateQuestionsForApplicants(incidentTestTemplateQuestions,incTestQuestion);
					log.info("Incident-test-question-before-save {}", incTestQuestion);
					incTestQuestionRepo.save(incTestQuestion);
				}
			});
			applicant.setTestScheduledStatus("COMPLETED");
			incidentApplicantRepo.save(applicant);
			incidentEventGenerator.triggerTestRound(incidentFromDb,applicant,loggedInUser);
		} catch (Exception e) {
			applicant.setTestScheduledStatus("ERROR");
			applicant.setErrorCode("ERROR");
			applicant.setErrorMessage(e.getMessage());
			incidentApplicantRepo.save(applicant);
			e.printStackTrace();
		}
	}
	
	//Currently used for shuffling
	public List<Integer> shuffledArray(int size) {
		List<Integer> arrayNum = new ArrayList<>();
		for(int start=0;start < size;start++) {
			arrayNum.add(start+1);
		}
		Collections.shuffle(arrayNum);
		return arrayNum;
	}
	
	public List<Integer> questionOrder(Integer length) {
		List<Integer> orders = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			orders.add(i);
		}
		Collections.shuffle(orders, new Random()); 
		return orders;
	}
	
	public IncidentTestQuestion populateQuestionsForApplicants(IncidentTestTemplateQuestion ittQ,IncidentTestQuestion incQ) {
		incQ.setQuestion(ittQ.getQuestion());
		incQ.setOption1(ittQ.getOption1());
		incQ.setOption2(ittQ.getOption2());
		incQ.setOption3(ittQ.getOption3());
		incQ.setOption4(ittQ.getOption4());
		incQ.setCreatedBy(commonUtils.getLoggedinUserId());
		incQ.setDifficultyLevel(ittQ.getDifficultyLevel());
		incQ.setCorrectOption(ittQ.getCorrectAnswer());
		incQ.setNegativeMarks(ittQ.getNegativeMark());
		incQ.setCategoryId(ittQ.getTestCategoryId().toString());
		incQ.setMarkPerQuestion(ittQ.getMarkPerQuestion());
		return incQ;
	}
	

	
//	-----------Employee creation---------------------------------

	@Async("asyncThreadPoolTaskExecutor")
	public CompletableFuture<List<IncidentApplicant>> employeeConversion(Integer id,AuthUser loggedInUser) {
		commonUtils.setAuthenticationContext(loggedInUser,"async");

		Optional<Incident> i = incidentRepo.findById(id);
		List<IncidentApplicant> incAppList = new ArrayList<IncidentApplicant>(); 
		
		if (i.isPresent()) {
			for (IncidentApplicant incidentApplicant : i.get().getIncidentApplicants()) {
				if (incidentApplicant.getEmpConversionFlag()!=null&&incidentApplicant.getEmpConversionFlag().equals("Y")) {
					incAppList.add(incidentApplicant);
				}
			}
			empProfileBusHelper.convertApplicant(incAppList,i.get());
			return CompletableFuture.completedFuture(incAppList);
		} 
		else {
			throw new SmartOfficeException("No incident");
		}
	}
	
	
//	-----------------------Trigger test for trainees-------------------------------
	@Lazy
	@Async("asyncThreadPoolTaskExecutor")
	public CompletableFuture<Incident> triggerTestForTrainees(Incident incFromReq,AuthUser loggedInUser) {
		commonUtils.setAuthenticationContext(loggedInUser,"async");		
		Optional<Incident> incFromDb = incidentRepo.findById(incFromReq.getId());
		try {
			
			if (!incFromReq.equals(null)) {
				for (IncidentApplicant ia : incFromReq.getIncidentApplicants()) {
					try {
							if (ia.getIsAttended().equals("Y")) {
								ia.setIsEligibleForTest("Y");								
								incidentApplicantRepo.save(ia);
							}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				log.info("1");
				log.info(incFromReq+"");
				triggerCalls(incFromReq,loggedInUser);
				
				
			} else {throw new SmartOfficeException("Event data not found");}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			incFromReq.setErrorCode("Error");
			incFromReq.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incFromReq);
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(incFromDb.get());
	}
	
	public void triggerCalls(Incident incFromReq,AuthUser loggedInUser) {
		try {
			Incident incFromDb = new Incident();
			incFromDb=incidentRepo.findById(incFromReq.getId()).get();	
		//	incHelper.approve("approve", incFromDb.get());
			log.info("Final Out Put before");
			log.info(incFromDb+"");
			incHelper.testEligibility(incFromDb, incFromDb,loggedInUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			incFromReq.setErrorCode("Error");
			incFromReq.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incFromReq);
			e.printStackTrace();
		}
	}

	
//	------------Regenerate-------------------------------------------------------------------------
	
	@Async("asyncThreadPoolTaskExecutor")
	@Transactional
	public CompletableFuture<Incident> regenerate(Integer id,Incident i,AuthUser loggedInUser) {
		commonUtils.setAuthenticationContext(loggedInUser,"async");		
		try {
			for (IncidentTest it : i.getIncidentTests()) {
				for (IncidentTestQuestion itq : it.getIncidentTestQuestions()) {
					incTestQuestionRepo.delete(itq.getId());
				}
				incidentTestRepo.delete(it.getId());
			}
			
			for (IncidentTestTemplate itt : i.getIncidentTestTemplates()) {
				for (IncidentTestTemplateQuestion ittq : itt.getIncidentApplicantTemplateQuestions()) {
					incidentTestTemplateQuestionRepo.delete(ittq.getId());
				}
				incidentTestTemplateRepo.delete(itt.getId());
			}
			
			i=incidentRepo.findById(i.getId()).get();
				i.setIncidentStatus("PENDING-APPROVAL");
				incidentRepo.save(i);
				incHelper.approve("regenerate-call",i);
		} catch (Exception e) {
			i.setRegenerationStatus("ERROR");
			incidentRepo.save(i);
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(i);
	}

}
