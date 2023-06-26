package com.ss.smartoffice.soservice.transaction.Interview;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.IncidentApplicant; 
import com.ss.smartoffice.soservice.transaction.incident.IncidentApplicantRepo; 
import com.ss.smartoffice.soservice.transaction.incident.IncidentEventGenerator;

@Service
public class InterviewHelper {
	private static Logger log = LoggerFactory.getLogger(InterviewHelper.class);

	@Autowired
	InterviewRepository interviewRepository;

	@Autowired
	InterviewRoundRepository interviewRoundRepository;

	@Autowired
	IncidentApplicantRepo incidentApplicantRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	IncidentEventGenerator incidentEventGenerator;

	public Interview roundAction(Integer interviewRoundId, String action, InterviewRound roundFromReq) throws SmartOfficeException {
		Interview interview = null;
		try {
			boolean savetoDb = false;
			AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
			InterviewRound interviewRoundDB = interviewRoundRepository.findById(interviewRoundId).get();
			if ((interviewRoundDB.getDecision()== null || interviewRoundDB.getDecision().equals("PENDING"))&& !action.equals("PENDING")) { 
				interviewRoundDB.setInterviewEmpId(commonUtils.getLoggedinEmployeeId());
				interviewRoundDB.setRoundCompletedDt(LocalDateTime.now());
				interviewRoundDB.setRating(roundFromReq.getRating());
				interviewRoundDB.setRemarks(roundFromReq.getRemarks());
				interviewRoundDB.setDecision(action); 
				interviewRoundDB.setModifiedBy(commonUtils.getLoggedinEmployeeId());					
				savetoDb = true; 		
			}
			else if((interviewRoundDB.getDecision()!= null && !interviewRoundDB.getDecision().equals("PENDING")) && action.equals("PENDING")) {
				interviewRoundDB.setRoundCompletedDt(null);
				interviewRoundDB.setRating("");
				interviewRoundDB.setRemarks(null);
				interviewRoundDB.setDecision(action); 
				interviewRoundDB.setModifiedBy(commonUtils.getLoggedinEmployeeId());					
				savetoDb = true;
				
			} 
			if(savetoDb ) {
				interviewRoundRepository.save(interviewRoundDB);
				interview = interviewRepository.findById(Integer.valueOf(interviewRoundDB.getInterviewId())).get();
//				incidentEventGenerator.triggerInterviewRoundEvent(action,action ,interviewRoundDB.getInterviewEmpId(), interview,loggedInUser);	
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e);
		}
		return interview;
	}

	public Interview finalDecision(Integer id, String action, Interview interview) throws SmartOfficeException {
		try {
			com.ss.smartoffice.shared.model.IncidentApplicant incidentApplicantFromDb = new IncidentApplicant();
			Interview interviewFromDb = new Interview();
			interviewFromDb = interviewRepository.findById(id).get();
			incidentApplicantFromDb = incidentApplicantRepo.findById(Integer.parseInt(interviewFromDb.getApplicantId())).get();
			AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
			if (interviewFromDb.getFinalDecision() == null) {
				switch (action) {
				case ("selected"):
					interviewFromDb.setFinalDecisionEmpId(commonUtils.getLoggedinEmployeeId());
					interviewFromDb.setFinalDecisionRemarks(interview.getFinalDecisionRemarks());
					interviewFromDb.setFinalDecision("SELECTED");
					incidentApplicantFromDb.setFinalInterviewStatus("SELECTED");
					incidentApplicantRepo.save(incidentApplicantFromDb);
					interviewRepository.save(interviewFromDb);
					incidentEventGenerator.triggerFinalRoundEventToHr(action, "final-decision",interviewFromDb.getFinalDecisionEmpId() ,interviewFromDb,loggedInUser);
					break;

				case ("rejected"):
					interviewFromDb.setFinalDecisionEmpId(commonUtils.getLoggedinEmployeeId());
					interviewFromDb.setFinalDecisionRemarks(interview.getFinalDecisionRemarks());
					interviewFromDb.setFinalDecision("REJECTED");
					incidentApplicantFromDb.setFinalInterviewStatus("REJECTED");
					incidentApplicantRepo.save(incidentApplicantFromDb);
					interviewRepository.save(interviewFromDb);
					incidentEventGenerator.triggerFinalRoundEventToHr(action, "final-decision", interviewFromDb.getFinalDecisionEmpId(),interviewFromDb,loggedInUser);
					break;
				default:
					break;
				}
			} else {
				throw new SmartOfficeException("Final Decision already made");
			}
		} catch (SmartOfficeException e) { 
			throw e;
		}  catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e);
		} 

		return interview;
	}
	
	public Interview createInterview(IncidentApplicant incAppln) throws SmartOfficeException {
		Interview interview = null; 
		InterviewRound intRound = null;
		try {
			
			// if interview exists update, else create new
			if(commonUtils.isNullOrEmpty(incAppln.getInterviewId()))
				interview = new Interview();
			else
				interview = interviewRepository.findById(Integer.parseInt(incAppln.getInterviewId())).get(); 
			interview.setApplicantId(incAppln.getId().toString());
			interview.setIncidentId(incAppln.getIncidentId());
			interview.setVcId(incAppln.getVcId()); 
			interview.setFirstInterviewerId(incAppln.getFirstInterviewerId());
			interview.setSecondInterviewerId(incAppln.getSecondInterviewerId());
			interview.setThirdInterviewerId(incAppln.getThirdInterviewerId());
			interview.setCreatedBy(commonUtils.getLoggedinUserId());
			interview = interviewRepository.save(interview);

			//delete any existing rounds
			for (InterviewRound _round : interview.getInterviewRound()) {
				interviewRoundRepository.delete(_round);
			} 
			// insert first interview Round 
			if(!commonUtils.isNullOrEmpty(interview.getFirstInterviewerId())) {
				intRound = new InterviewRound(interview.getId()+"", incAppln.getIncidentName()+"- First Round", incAppln.getInterviewScheduledDt(),interview.getFirstInterviewerId() ,1, commonUtils.getLoggedinUserId());
				interviewRoundRepository.save(intRound);
			}
			if(!commonUtils.isNullOrEmpty(interview.getSecondInterviewerId())) {
				intRound = new InterviewRound(interview.getId()+"", "Second Round", incAppln.getSecondInterviewDt(), interview.getSecondInterviewerId() ,2, commonUtils.getLoggedinUserId());
				interviewRoundRepository.save(intRound);				
			}
			if(!commonUtils.isNullOrEmpty(interview.getThirdInterviewerId())) {
				intRound = new InterviewRound(interview.getId()+"", "Third Round", incAppln.getThirdInterviewDt(), interview.getThirdInterviewerId() ,3, commonUtils.getLoggedinUserId());	
				interviewRoundRepository.save(intRound);			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e);
		}
		return interview;
		
	}

}
