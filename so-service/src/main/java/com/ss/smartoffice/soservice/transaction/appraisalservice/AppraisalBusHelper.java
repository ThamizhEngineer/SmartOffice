package com.ss.smartoffice.soservice.transaction.appraisalservice;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.EmployeeSkill;
import com.ss.smartoffice.soservice.master.employeeskill.EmployeeSkillRepo;

@Service
public class AppraisalBusHelper {
	@Autowired
	AppraisalDevRepo appraisalDevRepo;
	@Autowired
	AppraisalGoalRepo appraisalGoalRepo;
	@Autowired
	AppraisalHdrRepo appraisalHdrRepo;
	@Autowired
	AppraisalReviewRepo appraisalReviewRepo;
	@Autowired
	AppraisalEventGenerator appraisalEventGenerator;
	@Autowired
	ReviewAppraisalRepo reviewAppraisalRepo;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	EntityManager entityManager;
	@Autowired
	AppraisalSkillGoalRepo appraisalSkillGoalRepo;
	@Autowired
	EmployeeSkillRepo employeeSkillRepo;
	
	
	List<String>knownActions= new ArrayList(){{
		add("initiate");
		add("start");
		add("update");
		add("submit");
		add("n1-review");
		add("emp-accept");
		add("dir-approve");
		add("n2-review");
		add("n1-reject");
		add("emp-reject");
	}};
	
	public AppraisalHdr initiateAppraisal(AppraisalHdr appraisalHdr)throws SmartOfficeException{
		try {
			//validation
			if(commonUtils.isHr()) {
			List<AppraisalHdr> existingAppraisal=appraisalHdrRepo.findByFyIdAndEmpId(appraisalHdr.getFyId(), appraisalHdr.getEmpId());
			if(existingAppraisal.isEmpty()) {
				appraisalHdr.setEmpId(appraisalHdr.getEmpId());
				appraisalHdr.setFyId(appraisalHdr.getFyId());
				appraisalHdr.setSettleUserGroupId(appraisalHdr.getSettleUserGroupId());
				appraisalHdr.setSettleUserGroup2Id(appraisalHdr.getSettleUserGroup2Id());
				appraisalHdr.setN1EmpId(appraisalHdr.getN1EmpId());
				appraisalHdr.setN2EmpId(appraisalHdr.getN2EmpId());
				appraisalHdr.setOfficeId(appraisalHdr.getOfficeId());
				appraisalHdr.setSettleEmpId(commonUtils.getLoggedinEmployeeId());
				appraisalHdr.setDesignationId(appraisalHdr.getDesignationId());
				appraisalHdr.setAppraisalTargetStatusCode("CREATED");
				appraisalHdrRepo.save(appraisalHdr);
				if(appraisalHdr.getGoals()!=null&&!appraisalHdr.getGoals().isEmpty()) {
					for(AppraisalGoal appraisalGoal:appraisalHdr.getGoals()) {
						appraisalGoalRepo.save(appraisalGoal);
					}
				}
				if(appraisalHdr.getDevActions()!=null&&!appraisalHdr.getDevActions().isEmpty()) {
					for(AppraisalDev appraisalDev:appraisalHdr.getDevActions()) {
						appraisalDevRepo.save(appraisalDev);
					}
				}
				
				
			
			}
			else if(commonUtils.isHr()&&!existingAppraisal.isEmpty()){
				List<String> status=existingAppraisal.stream().filter(x->x.getAppraisalTargetStatusCode()!=null).map(y->y.getAppraisalTargetStatusCode()).collect(Collectors.toList());
				System.out.println(status);
				if(!(status.contains("SELF-APPRAISAL-SUBMITTED")||status.contains("APPROVED"))) {
				appraisalHdr.setEmpId(appraisalHdr.getEmpId());
				appraisalHdr.setFyId(appraisalHdr.getFyId());
				appraisalHdr.setSettleUserGroupId(appraisalHdr.getSettleUserGroupId());
				appraisalHdr.setSettleUserGroup2Id(appraisalHdr.getSettleUserGroup2Id());
				appraisalHdr.setN1EmpId(appraisalHdr.getN1EmpId());
				appraisalHdr.setN2EmpId(appraisalHdr.getN2EmpId());
				appraisalHdr.setOfficeId(appraisalHdr.getOfficeId());
				appraisalHdr.setSettleEmpId(commonUtils.getLoggedinEmployeeId());
				appraisalHdr.setDesignationId(appraisalHdr.getDesignationId());
				appraisalHdr.setAppraisalTargetStatusCode("CREATED");
				appraisalHdrRepo.save(appraisalHdr);
				if(appraisalHdr.getGoals()!=null&&!appraisalHdr.getGoals().isEmpty()) {
					for(AppraisalGoal appraisalGoal:appraisalHdr.getGoals()) {
						appraisalGoalRepo.save(appraisalGoal);
					}
				}
				if(appraisalHdr.getDevActions()!=null&&!appraisalHdr.getDevActions().isEmpty()) {
					for(AppraisalDev appraisalDev:appraisalHdr.getDevActions()) {
						appraisalDevRepo.save(appraisalDev);
					}
				}
			}
			}
		}
			else {
				throw new SmartOfficeException("Only hr is allowed to this operation-"+commonUtils.getLoggedinEmployeeId());
			}
			
		}catch (SmartOfficeException e) {
			e.printStackTrace();
		}
		return appraisalHdr;
	}
	
	
	public AppraisalHdr processAppraisal(Integer id,String action, AppraisalHdr appraisalHdr) {

		AppraisalHdr appraisalFromDb= new AppraisalHdr();
		if (!(action.equals("initiate"))) {
			appraisalFromDb = appraisalHdrRepo.findById(id).get();

		}

		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		
		
		switch (action) {
		case "initiate":
			
			appraisalHdr=initiateAppraisal(appraisalHdr);
			
			appraisalEventGenerator.triggerAppraisalEvents(appraisalHdr, action, loggedInUser);
		break;
		case "start":
			if(appraisalFromDb.getEmpId().equals(commonUtils.getLoggedinEmployeeId())||commonUtils.isHr()&&appraisalFromDb.getAppraisalTargetStatusCode().equals("CREATED")) {
				
				appraisalFromDb.setN1EmpId(appraisalHdr.getN1EmpId());
				appraisalFromDb.setAppraisalTargetStatusCode("PENDING-SELF-APPRAISAL");
				appraisalFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				appraisalFromDb.setModifiedDt(LocalDateTime.now());
				UpdateSkillGoals(appraisalFromDb);
				appraisalHdrRepo.save(appraisalFromDb);
				
			}else {
				throw new SmartOfficeException("Initiated Employee is allowed to start his appraisal-"+commonUtils.getLoggedinEmployeeId());
			}
			break;
		case "update":
			//combination
		if((appraisalFromDb.getEmpId().equals(commonUtils.getLoggedinEmployeeId()) || appraisalFromDb.getN1EmpId().equals(commonUtils.getLoggedinEmployeeId()) || appraisalFromDb.getN2EmpId().equals(commonUtils.getLoggedinEmployeeId())||commonUtils.isHr())&&(appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-SELF-APPRAISAL")||appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N1-REVIEW")||appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N2-REVIEW"))) {
		
			appraisalFromDb.setN1EmpId(appraisalHdr.getN1EmpId());
			
			if(appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-SELF-APPRAISAL")) {
				appraisalFromDb.setEmpNxtPos1(appraisalHdr.getEmpNxtPos1());
				appraisalFromDb.setEmpNxtPos2(appraisalHdr.getEmpNxtPos2());
				appraisalFromDb.setEmpCareerMove1(appraisalHdr.getEmpCareerMove1());
				appraisalFromDb.setEmpCareerMove2(appraisalHdr.getEmpCareerMove2());
				appraisalFromDb.setEmpMobility(appraisalHdr.getEmpMobility());
				appraisalFromDb.setEmpAreaOfStrength(appraisalHdr.getEmpAreaOfStrength());
				appraisalFromDb.setEmpAreaOfDev(appraisalHdr.getEmpAreaOfDev());
			}

			if(appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N1-REVIEW") || appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N2-REVIEW") ) {
				appraisalFromDb.setN1NxtPos1(appraisalHdr.getN1NxtPos1());
				appraisalFromDb.setN1NxtPos2(appraisalHdr.getN1NxtPos2());
				appraisalFromDb.setN1CareerPos1(appraisalHdr.getN1CareerPos1());
				appraisalFromDb.setN1CareerPos2(appraisalHdr.getN1CareerPos2());
				appraisalFromDb.setN1Mobility(appraisalHdr.getN1Mobility());
				appraisalFromDb.setTechScoreCode(appraisalHdr.getTechScoreCode());
				appraisalFromDb.setBehaveScoreCode(appraisalHdr.getBehaveScoreCode());
				appraisalFromDb.setOveralScoreCode(appraisalHdr.getOveralScoreCode());
				appraisalFromDb.setN1AreaOfStrength(appraisalHdr.getN1AreaOfStrength());
				appraisalFromDb.setN1AreaOfDev(appraisalHdr.getN1AreaOfDev());		  
			}
			
			if(appraisalHdr.getGoals()!=null&&!appraisalHdr.getGoals().isEmpty()) {
				for(AppraisalGoal appraisalGoal:appraisalHdr.getGoals()) {
					
					appraisalGoal.setAppraisalHdrId(appraisalHdr.getId().toString());					
					appraisalGoal.setEmpAchvmt(appraisalGoal.getEmpAchvmt());
					appraisalGoal.setEmpAchvmtRemarks(appraisalGoal.getEmpAchvmtRemarks());
					appraisalGoal.setEmpOperator(appraisalGoal.getEmpOperator());
					appraisalGoal.setEmpScoreCode(appraisalGoal.getEmpScoreCode());
					appraisalGoal.setEmpTarget(appraisalGoal.getEmpTarget());
					appraisalGoal.setMetricId(appraisalGoal.getMetricId());
					appraisalGoal.setN1EmpAchvmt(appraisalGoal.getN1EmpAchvmt());
					appraisalGoal.setN1AchvmtRemarks(appraisalGoal.getN1AchvmtRemarks());
					appraisalGoal.setN1Operator(appraisalGoal.getN1Operator());
					appraisalGoal.setN1ScoreCode(appraisalGoal.getN1ScoreCode());
					appraisalGoal.setN1Target(appraisalGoal.getN1Target());
					appraisalGoalRepo.save(appraisalGoal);
				}
			}
			
			if(appraisalHdr.getSkillObjectives()!=null&&!appraisalHdr.getSkillObjectives().isEmpty()) {
				for(AppraisalSkillGoal skill:appraisalHdr.getSkillObjectives()) {
					if(skill.getId()==null) {
						skill.setCreatedBy(commonUtils.getLoggedinEmployeeId());
						skill.setCreatedDt(LocalDateTime.now());
					}else {
						skill.setModifiedBy(commonUtils.getLoggedinEmployeeId());
						skill.setModifiedDt(LocalDateTime.now());
					}
					appraisalSkillGoalRepo.save(skill);
				}
			}
			
			if(appraisalHdr.getDevActions()!=null&&!appraisalHdr.getDevActions().isEmpty()) {
				for(AppraisalDev appraisalDev:appraisalHdr.getDevActions()) {
					appraisalDev.setAppraisalHdrId(appraisalFromDb.getId().toString());
					appraisalDev.setModifiedBy(commonUtils.getLoggedinEmployeeId());
					appraisalDev.setModifiedDt(LocalDateTime.now());
					appraisalDev.setDevAction(appraisalDev.getDevAction());
					appraisalDev.setResultsAcheiv(appraisalDev.getResultsAcheiv());
					appraisalDev.setTargetDate(LocalDate.now());
					appraisalDevRepo.save(appraisalDev);
				}
			}
		
		}else {
			throw new SmartOfficeException("Initiated Employee is allowed to update his appraisal-"+commonUtils.getLoggedinEmployeeId());
		}
		
		break;
		
	case"submit":
		if(appraisalFromDb.getEmpId().equals(commonUtils.getLoggedinEmployeeId())&&appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-SELF-APPRAISAL")) {
		
			appraisalHdr.setEmpId(commonUtils.getLoggedinEmployeeId());
			appraisalHdr.setAppraisalTargetStatusCode("SELF-APPRAISAL-SUBMITTED");
			appraisalHdrRepo.save(appraisalFromDb);
			appraisalHdr.setAppraisalTargetStatusCode("PENDING-N1-REVIEW");
			appraisalHdrRepo.save(appraisalHdr);
		}else {
			throw new SmartOfficeException("Appraisal is not in submit stage");
		}
		
		break;
	
	case "n1-review":
		//review to 
		if(!appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N1-REVIEW")||commonUtils.isHr()) {
			throw new SmartOfficeException("Appraisal not in n1-review stage-"+commonUtils.getLoggedinEmployeeId());
		}else {
			
			if(appraisalHdr.getGoals()!=null&&!appraisalHdr.getGoals().isEmpty()) {
				for(AppraisalGoal appraisalGoal:appraisalHdr.getGoals()) {
					appraisalGoal.setN1AchvmtRemarks(appraisalGoal.getN1AchvmtRemarks());
					appraisalGoal.setN1EmpAchvmt(appraisalGoal.getN1EmpAchvmt());
					appraisalGoal.setN1Operator(appraisalGoal.getN1Operator());
					appraisalGoal.setN1ScoreCode(appraisalGoal.getN1ScoreCode());
					appraisalGoal.setN1Target(appraisalGoal.getN1Target());
					appraisalGoal.setN1TargetRemarks(appraisalGoal.getN1TargetRemarks());
					appraisalGoalRepo.save(appraisalGoal);
					if(appraisalGoal.getReviews()!=null&&!appraisalGoal.getReviews().isEmpty()) {
						for(AppraisalReview appraisalReview:appraisalGoal.getReviews()) {
							appraisalReview.setAppraisalGoalId(appraisalGoal.getId().toString());
							appraisalReviewRepo.save(appraisalReview);
						}
					}
				}
			}
			if(appraisalHdr.getDevActions()!=null&&!appraisalHdr.getDevActions().isEmpty()) {
				for(AppraisalDev appraisalDev:appraisalHdr.getDevActions()) {
					appraisalDev.setAppraisalHdrId(appraisalFromDb.getId().toString());
					appraisalDev.setModifiedBy(commonUtils.getLoggedinEmployeeId());
					appraisalDev.setModifiedDt(LocalDateTime.now());
					appraisalDev.setDevAction(appraisalDev.getDevAction());
					appraisalDev.setResultsAcheiv(appraisalDev.getResultsAcheiv());
					appraisalDev.setTargetDate(LocalDate.now());
					appraisalDevRepo.save(appraisalDev);
				}
			}
			
			appraisalFromDb.setAppraisalTargetStatusCode("N1-REVIEWED");
			appraisalHdrRepo.save(appraisalFromDb);
			appraisalFromDb.setAppraisalTargetStatusCode("PENDING-EMP-ACCEPTANCE");
			appraisalHdrRepo.save(appraisalFromDb);
		}
		break;
	case "n1-reject":
		if(!appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N1-REVIEW")||commonUtils.isHr()) {
			throw new SmartOfficeException("Appraisal not in n1-review stage-"+commonUtils.getLoggedinEmployeeId());
		}else {
			appraisalFromDb.setN1ReturnComment(appraisalHdr.getN1ReturnComment());
			appraisalHdrRepo.save(appraisalFromDb);			
			if(appraisalHdr.getGoals()!=null&&!appraisalHdr.getGoals().isEmpty()) {
				for(AppraisalGoal appraisalGoal:appraisalHdr.getGoals()) {
					appraisalGoal.setN1AchvmtRemarks(appraisalGoal.getN1AchvmtRemarks());
					appraisalGoal.setN1EmpAchvmt(appraisalGoal.getN1EmpAchvmt());
					appraisalGoal.setN1Operator(appraisalGoal.getN1Operator());
					appraisalGoal.setN1ScoreCode(appraisalGoal.getN1ScoreCode());
					appraisalGoal.setN1Target(appraisalGoal.getN1Target());
					appraisalGoal.setN1TargetRemarks(appraisalGoal.getN1TargetRemarks());
					appraisalGoalRepo.save(appraisalGoal);
					if(appraisalGoal.getReviews()!=null&&!appraisalGoal.getReviews().isEmpty()) {
						for(AppraisalReview appraisalReview:appraisalGoal.getReviews()) {
							appraisalReview.setAppraisalGoalId(appraisalGoal.getId().toString());
							appraisalReviewRepo.save(appraisalReview);
						}
					}
				}
			}
			if(appraisalHdr.getDevActions()!=null&&!appraisalHdr.getDevActions().isEmpty()) {
				for(AppraisalDev appraisalDev:appraisalHdr.getDevActions()) {
					appraisalDev.setAppraisalHdrId(appraisalFromDb.getId().toString());
					appraisalDev.setModifiedBy(commonUtils.getLoggedinEmployeeId());
					appraisalDev.setModifiedDt(LocalDateTime.now());
					appraisalDev.setDevAction(appraisalDev.getDevAction());
					appraisalDev.setResultsAcheiv(appraisalDev.getResultsAcheiv());
					appraisalDev.setTargetDate(LocalDate.now());
					appraisalDevRepo.save(appraisalDev);
				}
			}
			
			appraisalFromDb.setAppraisalTargetStatusCode("N1-REJECTED");
			appraisalHdrRepo.save(appraisalFromDb);
			appraisalFromDb.setAppraisalTargetStatusCode("PENDING-SELF-APPRAISAL");
			appraisalHdrRepo.save(appraisalFromDb);
		}
	break;
	case "emp-accept":
		int acceptCount=0;
		if(appraisalHdr.getAppraisalTargetStatusCode().equals("PENDING-EMP-ACCEPTANCE")||commonUtils.isHr()) {
		if(!appraisalHdr.getEmpId().equals(commonUtils.getLoggedinEmployeeId())) {
			throw new SmartOfficeException("Not a valid user to accept appraisal-"+commonUtils.getLoggedinEmployeeId());
		}else {
			
			if(appraisalHdr.getGoals()!=null&&!appraisalHdr.getGoals().isEmpty()) {
				for(AppraisalGoal appraisalGoal:appraisalHdr.getGoals()) {
					appraisalGoal.setIsEmpAccept(appraisalGoal.getIsEmpAccept());
					if(appraisalGoal.getIsEmpAccept().equals("Y")) {
						acceptCount++;
					}
					appraisalGoalRepo.save(appraisalGoal);
					if(acceptCount==appraisalHdr.getGoals().size()) {
						appraisalHdr.setAppraisalTargetStatusCode("EMP-ACCEPTANCE");
						appraisalHdrRepo.save(appraisalHdr);
					}else {
						appraisalHdr.setAppraisalTargetStatusCode("EMP-REJECTED");
						appraisalHdrRepo.save(appraisalHdr);
						appraisalHdr.setAppraisalTargetStatusCode("PENDING-N2-REVIEW");
						appraisalHdrRepo.save(appraisalHdr);
					}
				
				}
			}
		}
		}else {
			throw new SmartOfficeException("Appraisal not in emp acceptance stage");
		}
		
		break;
	case "emp-reject":
		int acceptCounts=0;
		if(appraisalHdr.getAppraisalTargetStatusCode().equals("PENDING-EMP-ACCEPTANCE")||commonUtils.isHr()) {
		if(!appraisalHdr.getEmpId().equals(commonUtils.getLoggedinEmployeeId())) {
			throw new SmartOfficeException("Not a valid user to accept appraisal-"+commonUtils.getLoggedinEmployeeId());
		}else {
			
			if(appraisalHdr.getGoals()!=null&&!appraisalHdr.getGoals().isEmpty()) {
				for(AppraisalGoal appraisalGoal:appraisalHdr.getGoals()) {
					appraisalGoal.setIsEmpAccept(appraisalGoal.getIsEmpAccept());
					if(appraisalGoal.getIsEmpAccept().equals("Y")) {
						acceptCounts++;
					}
					appraisalGoalRepo.save(appraisalGoal);
					if(acceptCounts==appraisalHdr.getGoals().size()) {
						appraisalHdr.setAppraisalTargetStatusCode("EMP-ACCEPTANCE");
						appraisalHdrRepo.save(appraisalHdr);
					}else {
						appraisalHdr.setEscalatReason(appraisalHdr.getEscalatReason());
						appraisalHdr.setAppraisalTargetStatusCode("EMP-REJECTED");
						appraisalHdrRepo.save(appraisalHdr);
						appraisalHdr.setAppraisalTargetStatusCode("PENDING-N2-REVIEW");
						appraisalHdrRepo.save(appraisalHdr);
					}
				
				}
			}
		}
		}else {
			throw new SmartOfficeException("Appraisal not in emp acceptance stage");
		}
		
		break;
		
	case "dir-approve":
		if(appraisalFromDb.getAppraisalTargetStatusCode().equals("EMP-ACCEPTANCE")||commonUtils.isHr()) {
			appraisalFromDb.setDirEmpId(commonUtils.getLoggedinEmployeeId());
			appraisalFromDb.setAppraisalTargetStatusCode("APPROVED");
			appraisalHdrRepo.save(appraisalFromDb);
		}else{
			throw new SmartOfficeException("Appraisal not in acceptance stage");
		}
		break;
	case "n2-review":
		if(!appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N2-REVIEW")||commonUtils.isHr()) {
			throw new SmartOfficeException("Appraisal not in n2-review stage-"+commonUtils.getLoggedinEmployeeId());
		}else {

			if(appraisalHdr.getGoals()!=null&&!appraisalHdr.getGoals().isEmpty()) {
				for(AppraisalGoal appraisalGoal:appraisalHdr.getGoals()) {
					appraisalGoal.setN2AchvmtRemarks(appraisalGoal.getN1AchvmtRemarks());
					appraisalGoal.setN2EmpAchvmt(appraisalGoal.getN1EmpAchvmt());
					appraisalGoal.setN2Operator(appraisalGoal.getN1Operator());
					appraisalGoal.setN2ScoreCode(appraisalGoal.getN1ScoreCode());
					appraisalGoal.setN2Target(appraisalGoal.getN1Target());
					appraisalGoal.setN2TargetRemarks(appraisalGoal.getN1TargetRemarks());
					appraisalGoalRepo.save(appraisalGoal);
					if(appraisalGoal.getReviews()!=null&&!appraisalGoal.getReviews().isEmpty()) {
						for(AppraisalReview appraisalReview:appraisalGoal.getReviews()) {
							appraisalReview.setAppraisalGoalId(appraisalGoal.getId().toString());
							appraisalReviewRepo.save(appraisalReview);
						}
					}
				}
			}
			if(appraisalHdr.getDevActions()!=null&&!appraisalHdr.getDevActions().isEmpty()) {
				for(AppraisalDev appraisalDev:appraisalHdr.getDevActions()) {
					appraisalDev.setAppraisalHdrId(appraisalFromDb.getId().toString());
					appraisalDev.setModifiedBy(commonUtils.getLoggedinEmployeeId());
					appraisalDev.setModifiedDt(LocalDateTime.now());
					appraisalDev.setDevAction(appraisalDev.getDevAction());
					appraisalDev.setResultsAcheiv(appraisalDev.getResultsAcheiv());
					appraisalDev.setTargetDate(LocalDate.now());
					appraisalDevRepo.save(appraisalDev);
				}
			}
			appraisalFromDb.setAppraisalTargetStatusCode("N2-RESOLVED");
			appraisalHdrRepo.save(appraisalFromDb);
			appraisalFromDb.setAppraisalTargetStatusCode("PENDING-EMP-ACCEPTANCE");
			appraisalHdrRepo.save(appraisalFromDb);
		}
		break;
	case "priority":
		System.out.println("priority-->"+appraisalFromDb);
		if(appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N1-REVIEW") || appraisalFromDb.getAppraisalTargetStatusCode().equals("PENDING-N2-REVIEW")) {
			if(appraisalHdr.getGoals()!=null && !appraisalHdr.getGoals().isEmpty()) {
				for(AppraisalGoal goal:appraisalHdr.getGoals()) {
					AppraisalGoal appraisalGoalFromDb = appraisalGoalRepo.findById(goal.getId()).get();
					appraisalGoalFromDb.setPriority(goal.getPriority());
					appraisalGoalRepo.save(appraisalGoalFromDb);
					System.out.println("save-->"+appraisalGoalFromDb);
				}
			}
		}
		break;
		default:
			break;
		}
		return appraisalHdr;
	}
	
	public Iterable<AppraisalSkillGoal> UpdateSkillGoals(AppraisalHdr appraisalFromDb){
		List<EmployeeSkill> empSkill = employeeSkillRepo.findByMEmployeeId(Integer.parseInt(appraisalFromDb.getEmpId()));		
		List<AppraisalSkillGoal> skillGolasList = new ArrayList<AppraisalSkillGoal>();
		if(!empSkill.isEmpty()) {
			for(EmployeeSkill skill:empSkill) {
				AppraisalSkillGoal skillGoal = new AppraisalSkillGoal();
				skillGoal.setProductId(Integer.toString(skill.getMProductId()));
				skillGoal.setAbilityId(Integer.toString(skill.getMProfileId()));
				skillGoal.setIsExistingSkill("Y");
				skillGoal.setAppraisalHdrId(appraisalFromDb.getId().toString());
				skillGoal.setCurrSkillLevel(Integer.parseInt(skill.getSkillLevelCode()));
				skillGolasList.add(skillGoal);
			}	
		}	
		return appraisalSkillGoalRepo.saveAll(skillGolasList);
	}

	public List<ReviewAppraisal> processReviews(Integer id,String action, List<ReviewAppraisal> reviewAppraisals) {
		// TODO Auto-generated method stub
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		
		switch (action) {
		
		case"initiate":
			if(!commonUtils.isHr()) {
			throw new SmartOfficeException("Only Hr is allowed to do this operation:Currently login user is not hr and logined user employee id is"+commonUtils.getLoggedinEmployeeId());
			}else {
				AppraisalHdr appraisalHdrById= new AppraisalHdr();
				appraisalHdrById=appraisalHdrRepo.findById(id).get();
				appraisalHdrById.setAppraisalAchvmtStatusCode("CREATED");
				appraisalHdrRepo.save(appraisalHdrById);							
			}
		break;
		
		case "start":
		AppraisalHdr appraisalHdrById= new AppraisalHdr();
		for(ReviewAppraisal reviewAppraisal:reviewAppraisals) {
			if(commonUtils.getLoggedinEmployeeId().equals(reviewAppraisal.getEmpId())&&reviewAppraisal.getReviewCompStatus().equals("CREATED")) {
			reviewAppraisal.setEmpAcheivedTarget(reviewAppraisal.getEmpAcheivedTarget());
			reviewAppraisal.setEmpRemarks(reviewAppraisal.getEmpRemarks());
			appraisalHdrById=appraisalHdrRepo.findById(Integer.parseInt(reviewAppraisal.getAppraisalHdrId())).get();
			
			appraisalHdrById.setAppraisalAchvmtStatusCode("PENDING-SELF-APPRAISAL");
			appraisalHdrRepo.save(appraisalHdrById);
			reviewAppraisal.setReviewCompStatus("PENDING-SELF-APPRAISAL");
			reviewAppraisalRepo.save(reviewAppraisal);
			
			}else {
				throw new SmartOfficeException("Only Initiated Employee is allowed start his/her appraisal : Currently login employee-id is:"+commonUtils.getLoggedinEmployeeId());
			}
		}
		break;
		case"submit":
			for(ReviewAppraisal reviewAppraisal:reviewAppraisals) {
				if(commonUtils.getLoggedinEmployeeId().equals(reviewAppraisal.getEmpId())&&reviewAppraisal.getReviewCompStatus().equals("PENDING-SELF-APPRAISAL")) {
				
				appraisalHdrById=appraisalHdrRepo.findById(Integer.parseInt(reviewAppraisal.getAppraisalHdrId())).get();
				
				appraisalHdrById.setAppraisalAchvmtStatusCode("SELF-APPRAISAL-SUBMITTED");
				appraisalHdrRepo.save(appraisalHdrById);
				reviewAppraisal.setReviewCompStatus("SELF-APPRAISAL-SUBMITTED");
				reviewAppraisalRepo.save(reviewAppraisal);
				appraisalHdrById.setAppraisalAchvmtStatusCode("PENDING-N1-COMPLETION");
				appraisalHdrRepo.save(appraisalHdrById);
				reviewAppraisal.setReviewCompStatus("PENDING-N1-COMPLETION");
				reviewAppraisalRepo.save(reviewAppraisal);
				
			}else {
				throw new SmartOfficeException("Only Initiated Employee is allowed submit his/her appraisal : Currently login employee-id is:"+commonUtils.getLoggedinEmployeeId());
			}
			}
		break;
		case "n1-update":
			for(ReviewAppraisal reviewAppraisal:reviewAppraisals) {
				
				reviewAppraisal.setComments(reviewAppraisal.getComments());
				reviewAppraisal.setReviewScoreCode(reviewAppraisal.getReviewScoreCode());
				appraisalHdrById=appraisalHdrRepo.findById(Integer.parseInt(reviewAppraisal.getAppraisalHdrId())).get();
				if(reviewAppraisal.getReviewCompStatus().equals("PENDING-N1-COMPLETION")
						&&commonUtils.getLoggedinEmployeeId().equals(appraisalHdrById.getN1EmpId())) {
				appraisalHdrById.setAppraisalAchvmtStatusCode("N1-COMPLETED");
				appraisalHdrRepo.save(appraisalHdrById);
				appraisalHdrById.setAppraisalAchvmtStatusCode("PENDING-DIR-APPROVAL");
				appraisalHdrRepo.save(appraisalHdrById);
				reviewAppraisal.setReviewCompStatus("N1-COMPLETED");
				reviewAppraisalRepo.save(reviewAppraisal);
				reviewAppraisal.setReviewCompStatus("PENDING-DIR-APPROVAL");
				reviewAppraisalRepo.save(reviewAppraisal);
				
			}else {
				throw new SmartOfficeException("Either Appraisal not in n1 complete stage or logged in employee id is not n1 for appraisal employee : currently login employee is :"+commonUtils.getLoggedinEmployeeId());
			}
			}
		break;
	
		case"dir-approve":
			for(ReviewAppraisal reviewAppraisal:reviewAppraisals) {
				appraisalHdrById=appraisalHdrRepo.findById(Integer.parseInt(reviewAppraisal.getAppraisalHdrId())).get();
				if(reviewAppraisal.getReviewCompStatus().equals("PENDING-DIR-APPROVAL")) {
				appraisalHdrById.setAppraisalAchvmtStatusCode("APPROVED");
				appraisalHdrRepo.save(appraisalHdrById);
				reviewAppraisal.setReviewCompStatus("APPROVED");
				reviewAppraisalRepo.save(reviewAppraisal);
			}else {
				throw new SmartOfficeException("Appraisal is not completed by n1 manager :currently logged in user is :"+commonUtils.getLoggedinEmployeeId());
			}
			}
		break;
		default:
			break;
		}
		return reviewAppraisals;
	}


	
	

	

	
	
	
}
