package com.ss.smartoffice.soservice.transaction.leaveapplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.temp.jobemployee.TempJobEmp;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.model.JobPlanEmp;

@Service

public class LeaveApplicationBusHelper {

	@Autowired
	LeaveApplicationRepository leaveApplicationRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	LeaveApplicationService leaveApplicationService;
	@Autowired
	EventPublisherService eventPublisherService;
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@Autowired
	EventRepository eventRepository;
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	@Autowired
	LeaveApplicationEventGenerator leaveApplicationEventGenerator;

	public LeaveApplication createLeaveApplication(LeaveApplication leaveApplication) throws SmartOfficeException {
		try {

			leaveApplication.setLeaveTypeId(leaveApplication.getLeaveTypeId());
			leaveApplication.setStartDt(leaveApplication.getStartDt());
			leaveApplication.setStartSession(leaveApplication.getStartSession());
			leaveApplication.setEndDt(leaveApplication.getEndDt());
			leaveApplication.setEndSession(leaveApplication.getEndSession());
			leaveApplication.setEmployeeId(commonUtils.getLoggedinEmployeeId());
			leaveApplication.setReason(leaveApplication.getReason());
			System.out.println(leaveApplication);

			leaveApplicationService.checkLeaveValidity(leaveApplication);
			memployee employeeById = employeeService
					.getEmployeeById(Integer.valueOf(commonUtils.getLoggedinEmployeeId())).get();

			leaveApplication.setN1EmpId(employeeById.getN1EmpId());
			leaveApplication.setN2EmpId(employeeById.getN2EmpId());
			leaveApplication.setHr1UserGroupId(employeeById.getHr1UsrGrpId());

			HashMap<String, String> buisnessKeys = new HashMap<>();
			leaveApplication.setLeaveCode(sequenceGenerationService.nextSeq("LA-", buisnessKeys));

//			leaveApplication.setLeaveCode(sequenceGenerationService.nextSequence("LA-").getOutput() + "-"
//					+ leaveApplication.getLeaveTypeCode());
			leaveApplication.setLeaveAppliedDate(LocalDate.now());
			leaveApplication.setIsSettled("N");
			leaveApplication.setIsEnabled("Y");
			leaveApplication.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			leaveApplication.setCreatedDt(LocalDateTime.now());
			System.out.println(leaveApplication.getLeaveCode());
			leaveApplicationRepository.save(leaveApplication);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return leaveApplication;
	}

	public LeaveApplication updateLeaveApplication(LeaveApplication leaveApplication) throws SmartOfficeException {
		try {
			System.out.println("hi in update");
			leaveApplication.setModifiedBy(commonUtils.getLoggedinEmployeeId());
			leaveApplication.setModifiedDt(LocalDateTime.now());
			return leaveApplicationRepository.save(leaveApplication);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return leaveApplication;
	}

	public LeaveApplication processLeave(String action, @RequestBody LeaveApplication leaveApplication)
			throws SmartOfficeException {

		LeaveApplication leaveFromDb = new LeaveApplication();
		if (!(action.equals("create")) && !(action.equals("apply"))) {
			leaveFromDb = leaveApplicationRepository.findById(leaveApplication.getId()).get();
		}

		AuthUser loggedInUser =   (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		
		switch (action) {
		case ("create"):
			leaveApplication.setLeaveStatus("CREATED");
			System.out.println(leaveApplication);
			createLeaveApplication(leaveApplication);

			break;

		case ("apply"):
			leaveApplication.setLeaveStatus("APPLIED");
			if (leaveApplication.getId() == null) {
				leaveApplication = createLeaveApplication(leaveApplication);
				leaveApplication.setLeaveStatus("N1-APPROVAL-PENDING");
				List<JobPlanEmp> empJobList= leaveApplicationService.jobsInleave(Integer.parseInt(leaveApplication.getEmployeeId()), leaveApplication.getStartDt(), leaveApplication.getEndDt());
				if(!empJobList.isEmpty()) {
					leaveApplication.setJobAckgmt("Y");
					leaveApplication.setEmgncyAvailability(leaveApplication.getEmgncyAvailability());
					leaveApplication.setLeaveDenialAckgmt(leaveApplication.getLeaveDenialAckgmt());
					if(leaveApplication.getLeaveDenialAckgmt().equals("N")) {
						break;
					}
				}
				if(leaveApplication.getLeaveDenialAckgmt().equals("Y")) {
				leaveApplication = updateLeaveApplication(leaveApplication);
				System.out.println(leaveApplication);
				leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);
				}
			} else {
				leaveApplication.setLeaveStatus("N1-APPROVAL-PENDING");
				leaveApplication = updateLeaveApplication(leaveApplication);
				List<JobPlanEmp> empJobList= leaveApplicationService.jobsInleave(Integer.parseInt(leaveApplication.getEmployeeId()), leaveApplication.getStartDt(), leaveApplication.getEndDt());
				if(!empJobList.isEmpty()) {
					leaveApplication.setJobAckgmt("Y");
					leaveApplication.setEmgncyAvailability(leaveApplication.getEmgncyAvailability());
					leaveApplication.setLeaveDenialAckgmt(leaveApplication.getLeaveDenialAckgmt());
					if(leaveApplication.getLeaveDenialAckgmt().equals("N")) {
						throw new SmartOfficeException("Leave cannot be applied");
					}
				}
				if(leaveApplication.getLeaveDenialAckgmt().equals("Y")) {
				
				leaveApplication = updateLeaveApplication(leaveApplication);
				System.out.println(leaveApplication);
				leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);
				}
			}
			break;
		case ("n1-approve"):
			if (!leaveFromDb.getLeaveStatus().equals("N1-APPROVAL-PENDING")) {
				throw new SmartOfficeException("Travel advance request not in approval stage");
			} else if (!leaveFromDb.getN1EmpId().equals(commonUtils.getLoggedinEmployeeId())) {
				throw new SmartOfficeException("You Are Not N1 Manager For this Employee");
			} else {
				System.out.println("hi");
				leaveFromDb.setN1Remarks(leaveApplication.getN1Remarks());
				leaveFromDb.setNeedN2Approval(leaveApplication.getNeedN2Approval());
				leaveFromDb.setN1EmpId(commonUtils.getLoggedinEmployeeId());
				leaveFromDb.setLeaveStatus("N1-APPROVED");
				leaveFromDb.setN1Decision("N1-APPROVED");
				leaveFromDb.setN1DecisionDt(LocalDateTime.now());
				leaveFromDb = updateLeaveApplication(leaveFromDb);
				leaveFromDb.setLeaveStatus("APPROVED");
				leaveFromDb = updateLeaveApplication(leaveFromDb);
				if (leaveFromDb.getNeedN2Approval().equals("Y")) {
					leaveFromDb.setLeaveStatus("N2-APPROVAL-PENDING");
					leaveFromDb = updateLeaveApplication(leaveFromDb);
				}
				System.out.println("trigger");
				leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);
			}
			break;
		case ("n1-reject"):
			if (!leaveFromDb.getLeaveStatus().equals("N1-APPROVAL-PENDING")) {
				throw new SmartOfficeException("Travel advance request not in approval stage");
			} else if (!leaveFromDb.getN1EmpId().equals(commonUtils.getLoggedinEmployeeId())) {
				throw new SmartOfficeException("You Are Not N1 Manager For this Employee");
			} else {
				leaveFromDb.setN1Remarks(leaveApplication.getN1Remarks());
				leaveFromDb.setN1EmpId(commonUtils.getLoggedinEmployeeId());
				leaveFromDb.setLeaveStatus("N1-REJECTED");
				leaveFromDb.setN1Decision("N1-REJECTED");
				leaveFromDb.setN1DecisionDt(LocalDateTime.now());
				leaveFromDb = updateLeaveApplication(leaveFromDb);
			}
			leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);
			break;
		case ("n2-approve"):
			if (!leaveFromDb.getLeaveStatus().equals("N2-APPROVAL-PENDING")) {
				throw new SmartOfficeException("Travel advance request not in approval stage");
			} else if (!leaveFromDb.getN2EmpId().equals(commonUtils.getLoggedinEmployeeId())) {
				throw new SmartOfficeException("You Are Not N2 Manager For this Employee");
			} else {
				leaveFromDb.setN2Remarks(leaveApplication.getN2Remarks());
				leaveFromDb.setN2EmpId(commonUtils.getLoggedinEmployeeId());

				leaveFromDb.setLeaveStatus("N2-APPROVED");
				leaveFromDb.setN2Decision("N2-APPROVED");
				leaveFromDb.setN2DecisionDt(LocalDateTime.now());
				updateLeaveApplication(leaveFromDb);
				leaveFromDb.setLeaveStatus("APPROVED");
				leaveFromDb = updateLeaveApplication(leaveFromDb);
			}
			leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);
			break;
		case ("n2-reject"):
			if (!leaveFromDb.getLeaveStatus().equals("N2-APPROVAL-PENDING")) {
				throw new SmartOfficeException("Travel advance request not in approval stage");
			} else if (!leaveFromDb.getN2EmpId().equals(commonUtils.getLoggedinEmployeeId())) {
				throw new SmartOfficeException("You Are Not N1 Manager For this Employee");
			} else {
				leaveFromDb.setN2Remarks(leaveApplication.getN2Remarks());
				leaveFromDb.setN2EmpId(commonUtils.getLoggedinEmployeeId());
				leaveFromDb.setLeaveStatus("N2-REJECTED");
				leaveFromDb.setN2Decision("N2-REJECTED");
				leaveFromDb.setN2DecisionDt(LocalDateTime.now());
				leaveFromDb = updateLeaveApplication(leaveFromDb);
			}
			leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);
			break;
		case ("cancel"):
			if (leaveFromDb.getLeaveStatus().equals("CREATED") || leaveFromDb.getLeaveStatus().equals("N1-APPROVAL-PENDING")) {
				leaveFromDb.setReason(leaveApplication.getReason());
				leaveFromDb.setLeaveStatus("CANCELLED");
				leaveFromDb = updateLeaveApplication(leaveFromDb);
			} else {
				throw new SmartOfficeException("Travel advance request cannot in cancelled at this stage");	
			}
			leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);
			break;
		case ("hr1-settle"):
			if (leaveFromDb.getLeaveStatus().equals("APPROVED")) {
				leaveFromDb.setIsSettled("Y");
				leaveFromDb = updateLeaveApplication(leaveFromDb);
			} else {
				throw new SmartOfficeException("HR Aleady Settled");	
			}
			leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);
			break;
			
		case ("update"):
			if (!leaveFromDb.getLeaveStatus().equals("CREATED")) {
				throw new SmartOfficeException("Travel advance request cannot in updated at this stage");
			} else {
				leaveFromDb.setLeaveTypeId(leaveApplication.getLeaveTypeId());
				leaveFromDb.setStartDt(leaveApplication.getStartDt());
				leaveFromDb.setStartSession(leaveApplication.getStartSession());
				leaveFromDb.setEndDt(leaveApplication.getEndDt());
				leaveFromDb.setEndSession(leaveApplication.getEndSession());
				leaveFromDb.setEmployeeId(commonUtils.getLoggedinEmployeeId());
				leaveFromDb.setReason(leaveApplication.getReason());
				leaveApplicationService.checkLeaveValidity(leaveFromDb);
				memployee employeeById = employeeService
						.getEmployeeById(Integer.valueOf(commonUtils.getLoggedinEmployeeId())).get();

				leaveFromDb.setN1EmpId(employeeById.getN1EmpId());
				leaveFromDb.setN2EmpId(employeeById.getN2EmpId());
				leaveFromDb.setHr1UserGroupId(employeeById.getHr1UsrGrpId());
				leaveFromDb = updateLeaveApplication(leaveFromDb);
			}
			leaveApplicationEventGenerator.triggerLeaveEvents(leaveApplication, action, loggedInUser);

		default:
			break;
		}

		return leaveApplication;
	}

}
