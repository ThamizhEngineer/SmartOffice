package com.ss.smartoffice.sonotificationservice.traveladvancerequestsummary;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TravelAdvanceRequestSummaryRepo extends CrudRepository<TravelAdvanceRequestSummary,Integer>{
	
	Long countByN1EmployeeIdAndTarStatus(String n1EmployeeId, String tarStatus);
	Long countByAcc2UserGroupIdInAndTarStatus(List<String> userGroupIds, String tarStatus);


}
