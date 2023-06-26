package com.ss.smartoffice.soservice.transaction.traveladvancerequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.Sequence;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.shared.sequence.SequenceRepository;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.seed.dayrange.DayRange;
import com.ss.smartoffice.soservice.seed.dayrange.DayRangeRepository;
import com.ss.smartoffice.soservice.transaction.cashbalance.EmpCashBalService;
import com.ss.smartoffice.soservice.transaction.cashbalance.EmployeeCashBalance;

@Service
public class TravelAdvanceRequestHelper {
	@Autowired
	CommonUtils commonutils;
	@Autowired
	TravelAdvanceRequestRepo travelAdvanceRequestRepo;
	@Autowired
	TravelAdvanceRequestEventGenerator travelAdvanceRequestEventGenerator;

	@Autowired
	DayRangeRepository dayRangeRepository;

	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@Autowired
	SequenceRepository sequenceRepository;
	

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmpCashBalService empCashBalService;

	@Autowired
	TravelAdvanceRequestService travelAdvanceRequestService;

	public Iterable<TravelAdvanceRequest> getAllTars() throws SmartOfficeException {
		return travelAdvanceRequestRepo.findAll();
	}

	public Optional<TravelAdvanceRequest> getTarById(@PathVariable(value = "id") Integer id)
			throws SmartOfficeException {
		return travelAdvanceRequestRepo.findById(id);
	}

	public TravelAdvanceRequest addTravelAdvReq(@RequestBody TravelAdvanceRequest travelAdvanceRequest)
			throws SmartOfficeException {
		travelAdvanceRequest.setCreatedBy(commonutils.getLoggedinEmployeeId());
		travelAdvanceRequest.setCreatedDt(LocalDateTime.now());
		return travelAdvanceRequestRepo.save(travelAdvanceRequest);
	}

	// createTar()

	public TravelAdvanceRequest createTar(@RequestBody TravelAdvanceRequest travelAdvanceRequest)
			throws SmartOfficeException {

		EmployeeCashBalance employeeCashBalance;
		try {
			employeeCashBalance = empCashBalService.getByEmployeeId().get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SmartOfficeException("Cashbalance not available. Please contact admin!");
		}
		System.out.println(employeeCashBalance);
		travelAdvanceRequest.setCompanyToPayAmt(employeeCashBalance.getPayToCompAmt());
		HashMap<String, String> buisnessKeys = new HashMap<>();
	
		
		
		Sequence sequenceObj = sequenceRepository.findBySequenceCode("TAR-CODE").get(0);
		
		travelAdvanceRequest.setEmpToPayAmt(employeeCashBalance.getPayToEmpAmt());
		travelAdvanceRequest.setCostCenterId(travelAdvanceRequest.getCostCenterId());
		travelAdvanceRequest.setJobId(travelAdvanceRequest.getJobId());
		if(travelAdvanceRequest.getId()==null) {
		travelAdvanceRequest.setTarCode(sequenceGenerationService.nextSeq("TAR-CODE", buisnessKeys));
		}
		travelAdvanceRequest.setJobOrCc(travelAdvanceRequest.getJobOrCc());
		travelAdvanceRequest.setDayRangeId(travelAdvanceRequest.getDayRangeId());

		travelAdvanceRequest.setEmpRemarks(travelAdvanceRequest.getEmpRemarks());
		travelAdvanceRequest.setReqAdvAmt(travelAdvanceRequest.getReqAdvAmt());
		if (travelAdvanceRequest.getIsAddlTravelInvolved().equals("Y")) {
			travelAdvanceRequest.setIsAddlTravelInvolved("Y");
		} else {
			travelAdvanceRequest.setIsAddlTravelInvolved("N");
		}
		     
		travelAdvanceRequest.setTarSubmittedDt(LocalDateTime.now());

		travelAdvanceRequest.setEmployeeId(commonutils.getLoggedinEmployeeId());
		travelAdvanceRequest.setTarStatus("CREATED");
		travelAdvanceRequest.setTarSubmittedDt(LocalDateTime.now());
		travelAdvanceRequest.setIsEnabled("Y");
		travelAdvanceRequest.setCreatedBy(commonutils.getLoggedinEmployeeId());
		travelAdvanceRequest.setCreatedDt(LocalDateTime.now());
		DayRange dayRangeById = dayRangeRepository.findById(Integer.valueOf(travelAdvanceRequest.getDayRangeId()))
				.get();
		System.out.println(dayRangeById);
		travelAdvanceRequest.setStayExpenseAmount(String.valueOf(dayRangeById.getStayExpenseAmount()));
		travelAdvanceRequest.setTravelExpenseAmount(String.valueOf(dayRangeById.getTravelExpenseAmount()));
		memployee employeeById = employeeService.getEmployeeById(Integer.valueOf(travelAdvanceRequest.getEmployeeId()))
				.get();
		travelAdvanceRequest.setN1EmployeeId(employeeById.getN1EmpId());
		travelAdvanceRequest.setAcc2UserGroupId(employeeById.getAcc2UsrGrpId());
		System.out.println(travelAdvanceRequest);
		return addTravelAdvReq(travelAdvanceRequest);

	}

	// update tar
	public TravelAdvanceRequest updateTravelAdvReq(@RequestBody TravelAdvanceRequest travelAdvanceRequest)
			throws SmartOfficeException {
		travelAdvanceRequest.setIsEnabled("Y");
		travelAdvanceRequest.setModifiedBy(commonutils.getLoggedinEmployeeId());
		travelAdvanceRequest.setModifiedDt(LocalDateTime.now());
		return travelAdvanceRequestRepo.save(travelAdvanceRequest);
	}

	// process tar
	public TravelAdvanceRequest processTar(Integer id, String action,
			@RequestBody TravelAdvanceRequest travelAdvanceRequest) throws SmartOfficeException {
		TravelAdvanceRequest tarFromDb = new TravelAdvanceRequest();
		if (!(action.equals("create")) && !(action.equals("apply"))) {
			tarFromDb = travelAdvanceRequestRepo.findById(id).get();
			System.out.println(tarFromDb);
		}

		AuthUser loggedInUser = (AuthUser) commonutils.getAuthenticatedUser().getDetails();
		

		switch (action) {
		case ("create"):
			System.out.println("hi");
			travelAdvanceRequest.setTarStatus("CREATED");
			TravelAdvanceRequest createTar = createTar(travelAdvanceRequest);
			createTar.setTarStatus("CREATED");
			updateTravelAdvReq(createTar);

			travelAdvanceRequestEventGenerator.triggerTarEvents(createTar, action, loggedInUser);

			// event
			break;
		case ("apply"):

			travelAdvanceRequest.setTarStatus("CREATED");
			TravelAdvanceRequest savedTar = createTar(travelAdvanceRequest);
			savedTar.setTarStatus("N1-APPROVAL-PENDING");
			updateTravelAdvReq(savedTar);

			travelAdvanceRequestEventGenerator.triggerTarEvents(savedTar, action, loggedInUser);

			// event
			break;

		case ("n1-approve"):
			Double totalAdvAmt=null;
			System.out.println(tarFromDb);
			if (tarFromDb.getTarStatus().equals("N1-APPROVAL-PENDING")) {
				if(!tarFromDb.getN1EmployeeId().equals(commonutils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("You Are Not N1 Manager For this Employee");
				}
				if (travelAdvanceRequest.getN1IsAddlTravelInvolved() != null) {
					tarFromDb.setN1IsAddlTravelInvolved(travelAdvanceRequest.getN1IsAddlTravelInvolved());
				}
				tarFromDb.setN1AdjustmentAmount(travelAdvanceRequest.getN1AdjustmentAmount());
				tarFromDb.setN1AdjustmentRemarks(travelAdvanceRequest.getN1AdjustmentRemarks());
				tarFromDb.setN1IsAddlTravelInvolved(travelAdvanceRequest.getN1IsAddlTravelInvolved());
				tarFromDb.setN1EmployeeId(commonutils.getLoggedinEmployeeId());
				tarFromDb.setN1Remarks(travelAdvanceRequest.getN1Remarks());
				tarFromDb.setN1DecisionDt(LocalDateTime.now());
				 if(tarFromDb.getN1AdjustmentAmount()!=null) {
				 totalAdvAmt=Double.valueOf(tarFromDb.getStayExpenseAmount())+Double.valueOf(tarFromDb.getN1AdjustmentAmount());
				 tarFromDb.setNetAdvanceAmount(totalAdvAmt.toString());
				 }else {
					 totalAdvAmt=Double.valueOf(tarFromDb.getStayExpenseAmount());
					 tarFromDb.setNetAdvanceAmount(totalAdvAmt.toString());
				 }
				 if(travelAdvanceRequest.getN1IsAddlTravelInvolved()!=null&&travelAdvanceRequest.getN1IsAddlTravelInvolved().equals("Y")) {
				 totalAdvAmt=totalAdvAmt+Double.valueOf(travelAdvanceRequest.getTravelExpenseAmount());
				 tarFromDb.setNetAdvanceAmount(totalAdvAmt.toString());
				 }
				

				tarFromDb.setTarStatus("N1-APPROVED");
				tarFromDb.setN1Decision("N1-APPROVED");
				TravelAdvanceRequest updateTar = updateTravelAdvReq(tarFromDb);
				updateTar.setTarStatus("ACC2-APPROVAL-PENDING");
				updateTravelAdvReq(updateTar);
				System.out.println(updateTar+action);
				travelAdvanceRequestEventGenerator.triggerTarEvents(tarFromDb, action, loggedInUser);

			} else {
				throw new SmartOfficeException("Travel advance request not in approval stage");

			}
			break;

		case ("acc2-approve"):

			if (tarFromDb.getTarStatus().equals("ACC2-APPROVAL-PENDING")) {
				List<String>userGroupIds=commonutils.findLoggedInUserGroups();
				if(!userGroupIds.contains(tarFromDb.getAcc2UserGroupId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");
			

			}else {
				EmployeeCashBalance employeeCashBalance = empCashBalService.getMaxDt().get(0);
				tarFromDb.setCompanyToPayAmt(employeeCashBalance.getPayToCompAmt());

				tarFromDb.setEmpToPayAmt(employeeCashBalance.getPayToEmpAmt());
				tarFromDb.setAccEmpAdjAmount(travelAdvanceRequest.getAccEmpAdjAmount());
				tarFromDb.setAcc2AdjRemarks(travelAdvanceRequest.getAcc2AdjRemarks());

				 totalAdvAmt = Double.valueOf(tarFromDb.getStayExpenseAmount())
						+ Double.valueOf(tarFromDb.getAccEmpAdjAmount());
				tarFromDb.setNetAdvanceAmount(totalAdvAmt.toString());

				if (travelAdvanceRequest.getN1IsAddlTravelInvolved().equals("Y")) {
					totalAdvAmt = totalAdvAmt + Double.valueOf(travelAdvanceRequest.getTravelExpenseAmount());
					tarFromDb.setNetAdvanceAmount(totalAdvAmt.toString());
				}

				// tarFromDb.setNetAdvanceAmount(travelAdvanceRequest.getNetAdvanceAmount());
				tarFromDb.setAcc2Remarks(travelAdvanceRequest.getAcc2Remarks());
				memployee employeeById = employeeService.getEmployeeById(Integer.valueOf(tarFromDb.getEmployeeId()))
						.get();
				tarFromDb.setAcc2UserGroupId(employeeById.getAcc2UsrGrpId());
				tarFromDb.setAcc2EmployeeId(commonutils.getLoggedinEmployeeId());
				tarFromDb.setTarStatus("ACC2-APPROVED");

				tarFromDb.setAcc2Decision("ACC2-APPROVED");
				tarFromDb.setAcc2DecisionDt(LocalDateTime.now());
				updateTravelAdvReq(tarFromDb);
				tarFromDb.setTarStatus("APPROVED");
				updateTravelAdvReq(tarFromDb);
				travelAdvanceRequestEventGenerator.triggerTarEvents(tarFromDb, action, loggedInUser);
			}
			}else {
				throw new SmartOfficeException("Travel advance request not in approval stage");

			}
			break;

		case ("n1-reject"):
			if(!tarFromDb.getN1EmployeeId().equals(commonutils.getLoggedinEmployeeId())) {
				throw new SmartOfficeException("You Are Not N1 Manager For this Employee");
			}
			tarFromDb.setN1Remarks(travelAdvanceRequest.getN1Remarks());
			tarFromDb.setTarStatus("N1-REJECTED");
			tarFromDb.setN1Decision("N1-REJECTED");
			tarFromDb.setN1EmployeeId(commonutils.getLoggedinEmployeeId());
			updateTravelAdvReq(tarFromDb);
			travelAdvanceRequestEventGenerator.triggerTarEvents(tarFromDb, action, loggedInUser);
			break;
		case ("acc2-reject"):
			List<String>userGroupIds=commonutils.findLoggedInUserGroups();
		if(!userGroupIds.contains(tarFromDb.getAcc2UserGroupId())) {
			throw new SmartOfficeException("Not a valid user to perform this action");			
		}else {
			tarFromDb.setAcc2Remarks(travelAdvanceRequest.getAcc2Remarks());
			tarFromDb.setTarStatus("ACC2-REJECTED");
			tarFromDb.setAcc2EmployeeId(commonutils.getLoggedinEmployeeId());
			tarFromDb.setAcc2Decision("ACC2-REJECTED");
			tarFromDb.setAcc2DecisionDt(LocalDateTime.now());
			updateTravelAdvReq(tarFromDb);
			travelAdvanceRequestEventGenerator.triggerTarEvents(tarFromDb, action, loggedInUser);
		}
			break;

		case ("cancel"):

			tarFromDb.setTarStatus("CANCELLED");
			tarFromDb.setEmployeeId(commonutils.getLoggedinEmployeeId());
			tarFromDb.setEmpRemarks(travelAdvanceRequest.getEmpRemarks());
			updateTravelAdvReq(tarFromDb);
			travelAdvanceRequestEventGenerator.triggerTarEvents(tarFromDb, action, loggedInUser);
		default:
			break;
		}
		return travelAdvanceRequest;
	}

}
