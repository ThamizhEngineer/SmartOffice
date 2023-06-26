package com.ss.smartoffice.soservice.transaction.vacancyrequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.transaction.incident.IncidentEventGenerator;

@Component
public class VacancyRequestHelper {
	@Autowired
	VacancyRequestRepo vacancyRequestRepo;
	@Autowired
	UserGroupEmployeeMappingService userGroupEmployeeMappingService;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	IncidentEventGenerator incidentEventGenerator;
	@Autowired
	SequenceGenerationService sequenceGenerationService;

	public Iterable<VacancyRequest> getAll() throws SmartOfficeException {
		return vacancyRequestRepo.findAll();
	}

	public Optional<VacancyRequest> getById(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		return vacancyRequestRepo.findById(id);
		}

	public VacancyRequest createJobRequest(@RequestBody VacancyRequest vacancyRequest) throws SmartOfficeException {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		HashMap<String, String> buisnessKeys = new HashMap<>();
		memployee empById = employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
		vacancyRequest.setHr1UsrGrpId(empById.getHr1UsrGrpId());
		vacancyRequest.setHr2UsrGrpId(empById.getHr2UsrGrpId());
		if (vacancyRequest.getVrCode()== null || vacancyRequest.getVrCode().equals("")) {
			vacancyRequest.setVrCode(
					sequenceGenerationService.nextSeq("JOB-REQUEST", buisnessKeys));
		}
		vacancyRequest.setStatus("PENDING-APPROVAL");
		incidentEventGenerator.triggerJobRequestApprovalEvent(vacancyRequest, loggedInUser);
		return vacancyRequestRepo.save(vacancyRequest);
		}

	public VacancyRequest processJobRequest(String id, String action, @RequestBody VacancyRequest vacancyRequest)
			throws SmartOfficeException {
		VacancyRequest jobRequestFromDb = new VacancyRequest();
		if (!(action.equals("create"))) {
			jobRequestFromDb = vacancyRequestRepo.findById(Integer.parseInt(id)).get();
		}

		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		
		switch (action) {
		case "create":
			createJobRequest(jobRequestFromDb);
			break;
		case "update":
			updateJobRequest(jobRequestFromDb,vacancyRequest);
			break;
		case "approve":
			if (jobRequestFromDb.getStatus().equals("PENDING-APPROVAL")) {
				approveJobRequest(jobRequestFromDb);
			}
			break;
		case "freeze":
			FreezeJobRequest(jobRequestFromDb,vacancyRequest);
			break;
		case "unfreeze":
			unfreezeJobRequest(jobRequestFromDb,vacancyRequest);
			break;
		case "close":
			closeJobRequest(jobRequestFromDb,vacancyRequest);
			break;
		default:
			break;
		}
		return vacancyRequest;
	}

	// For Approval
	private VacancyRequest approveJobRequest(VacancyRequest jobRequestFromDb) {
		jobRequestFromDb.setStatus("OPEN");
		jobRequestFromDb.setApprovedEmpId(commonUtils.getLoggedinEmployeeId());
		jobRequestFromDb.setApproverDecision("OPEN");
		jobRequestFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		jobRequestFromDb.setModifiedDt(LocalDateTime.now());
		jobRequestFromDb.setVcApprovedDt(LocalDateTime.now());
		vacancyRequestRepo.save(jobRequestFromDb);
		return jobRequestFromDb;
	}

//	// For Update
	private VacancyRequest updateJobRequest(VacancyRequest jobRequestFromDb,VacancyRequest vacancyRequest) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		if(jobRequestFromDb.getStatus().equals("PENDING-APPROVAL")) {
			vacancyRequest.setStatus("PENDING-APPROVAL");
			memployee empById = employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
			vacancyRequest.setApprovalUserGroupId(empById.getDirUsrGrpId());
			vacancyRequest.setModifiedBy(commonUtils.getLoggedinEmployeeId());
			vacancyRequest.setModifiedDt(LocalDateTime.now());
			vacancyRequestRepo.save(vacancyRequest);
			incidentEventGenerator.triggerJobRequestHrApprovalEvent(jobRequestFromDb, loggedInUser);
			return jobRequestFromDb;	
		}else {
			throw new SmartOfficeException("You can't update If job Request is Open");
		}
	}
	
//Freeze JR
	
	private VacancyRequest FreezeJobRequest(VacancyRequest jobRequestFromDb,VacancyRequest vacancyRequest)throws SmartOfficeException{
		if(jobRequestFromDb.getStatus().equals("OPEN")) {
			jobRequestFromDb.setStatus("FREEZE");
			return vacancyRequestRepo.save(jobRequestFromDb);
		}else {
			throw new SmartOfficeException("Job Request cant be freeze at "+jobRequestFromDb.getStatus()+" status");
		}						
	}
	
	//UnFreeze JR
	
	private VacancyRequest unfreezeJobRequest(VacancyRequest jobRequestFromDb,VacancyRequest vacancyRequest)throws SmartOfficeException{
		if(jobRequestFromDb.getStatus().equals("FREEZE")) {
			jobRequestFromDb.setStatus("OPEN");
			jobRequestFromDb.setVcApprovedDt(LocalDateTime.now());
			return vacancyRequestRepo.save(jobRequestFromDb);
		}else {
			throw new SmartOfficeException("Job Request cant be open at "+jobRequestFromDb.getStatus()+" status");
		}						
	}
	
	//Close JR
	
	private VacancyRequest closeJobRequest(VacancyRequest jobRequestFromDb,VacancyRequest vacancyRequest)throws SmartOfficeException{		
		jobRequestFromDb.setStatus("CLOSED");			
			return vacancyRequestRepo.save(jobRequestFromDb);
		}							
	
}
