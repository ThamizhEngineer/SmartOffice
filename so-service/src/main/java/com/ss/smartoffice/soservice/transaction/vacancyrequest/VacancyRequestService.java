package com.ss.smartoffice.soservice.transaction.vacancyrequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;

@RestController
@RequestMapping("transaction/vacancy-requests")
public class VacancyRequestService {
@Autowired
VacancyRequestHelper vacancyRequestHelper;
@Autowired
VacancyRequestRepo vacancyRequestRepo;
@Autowired
UserGroupEmployeeMappingService userGroupEmployeeMappingService;
ArrayList<String> knownActions = new ArrayList<String>( Arrays.asList("create","update","approve","freeze","unfreeze","close"));
	@GetMapping
	public Iterable<VacancyRequest> getAllJobRequests()throws SmartOfficeException{
		Map<Integer,VacancyRequest> vacancyReqMap = new HashMap<Integer, VacancyRequest>();
		List<String> hr1UsrGrpIds = userGroupEmployeeMappingService.getUserGroupHrId();
		List<String> hr2UsrGrpIds = userGroupEmployeeMappingService.getUserGroupHr2Id();
		//was trying to implement in by giving two list in repo query but one by giving 
		//or in in parameter query was getting error so implemented this way		
		
		for(String hr1UsrGrpId:hr1UsrGrpIds) {
			for (VacancyRequest vr : vacancyRequestRepo.findByHr1UsrGrpIdOrHr2UsrGrpId(hr1UsrGrpId,null)) {
				vacancyReqMap.put(vr.getId(), vr);
			} 
		}
		
		for(String hr2UsrGrpId:hr2UsrGrpIds) { 				
			for (VacancyRequest vr : vacancyRequestRepo.findByHr1UsrGrpIdOrHr2UsrGrpId(null,hr2UsrGrpId)) {
				vacancyReqMap.put(vr.getId(), vr);
			} 				
		}
		
		return new ArrayList<VacancyRequest>(vacancyReqMap.values());
	}
	
	@GetMapping("/dir-view")
	public Iterable<VacancyRequest> getJobRequestsForDir()throws SmartOfficeException{
		return vacancyRequestRepo.findByPendingStatus();
	}
	
	@GetMapping("/{id}")
	public Optional<VacancyRequest> getJobRequestById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return vacancyRequestHelper.getById(id);
	}
	@PostMapping
	public VacancyRequest addJobRequest(@RequestBody VacancyRequest vacancyRequest)throws SmartOfficeException{
	
				return vacancyRequestHelper.createJobRequest(vacancyRequest);
				
	}
				
	
	@PatchMapping("/{id}/{action}")
	public VacancyRequest processJobRequestApproval(@PathVariable(value="id")String id,@PathVariable(value="action")String action,@RequestBody VacancyRequest vacancyRequest)throws SmartOfficeException{

		if(knownActions.contains(action)) {
				return vacancyRequestHelper.processJobRequest(id, action, vacancyRequest);
		
		}else {
			throw new SmartOfficeException("Invalid Url");
		}
	}
	
	
	
}
