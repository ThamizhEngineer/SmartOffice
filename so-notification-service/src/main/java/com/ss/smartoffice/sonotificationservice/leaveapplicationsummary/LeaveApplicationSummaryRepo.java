package com.ss.smartoffice.sonotificationservice.leaveapplicationsummary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApplicationSummaryRepo extends CrudRepository<LeaveApplicationSummary, Integer>{

	Long countByN1EmpIdAndLeaveStatus(String n1EmpId, String leaveStatus);
	Long countByN2EmpIdAndLeaveStatus(String n2EmpId, String leaveStatus);
//	Long countByHr1UserGroupIdAndLeaveStatusIn(List<String> validateUserGroupId, String leaveStatus);
}
