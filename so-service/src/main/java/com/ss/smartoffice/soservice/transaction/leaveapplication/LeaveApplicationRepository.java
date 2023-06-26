package com.ss.smartoffice.soservice.transaction.leaveapplication;


import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;




public interface LeaveApplicationRepository extends CrudRepository<LeaveApplication, Integer>{
	
List<LeaveApplication> findByEmployeeIdAndLeaveTypeId(String employeeId,String leaveTypeId);
List<LeaveApplication> findByEmployeeId(String employeeId);
List<LeaveApplication> findByLeaveTypeId(String LeaveTypeId);

 
@Query("select new com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication(la.id,la.leaveCode,la.employeeId,la.empFname,la.empLname,la.leaveTypeId,"
		+"la.leaveTypeCode,la.leaveTypeName,la.startDt,la.startSession,la.leaveAppliedDate,la.endDt,"
		+"la.endSession,la.leaveStatus,la.leaveStatusName,la.n1EmpId,la.n1EmpCode,la.n1EmpFname,la.n1EmpLname,"
		+"la.n2EmpId ,la.n2EmpCode,la.n2EmpFname,la.n2EmpLname) " 
		+"from LeaveApplication  la where la.employeeId = :employeeId "
		+"ORDER BY la.leaveAppliedDate")
List<LeaveApplication> findByEmployeeIdByLeaveApplnDt(@Param("employeeId")String employeeId);
@Query("select new com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication(la.id,la.leaveCode,la.employeeId,la.empFname,la.empLname,la.leaveTypeId,"
		+"la.leaveTypeCode,la.leaveTypeName,la.startDt,la.startSession,la.leaveAppliedDate,la.endDt,"
		+"la.endSession,la.leaveStatus,la.leaveStatusName,la.n1EmpId,la.n1EmpCode,la.n1EmpFname,la.n1EmpLname,"
		+"la.n2EmpId,la.n2EmpCode,la.n2EmpFname,la.n2EmpFname)" 
		+"from LeaveApplication la where ( la.n1EmpId = :n1EmpId and (la.leaveStatus='N1-APPROVAL-PENDING' or la.n1Decision is not null) ) "
		+"or ( la.n2EmpId = :n2EmpId and (la.leaveStatus='N2-APPROVAL-PENDING' or la.n2Decision is not null) ) "
		+"ORDER BY la.leaveAppliedDate")
List<LeaveApplication> findByN1EmpIdOrN2EmpId(@Param("n1EmpId")String n1EmpId,@Param("n2EmpId")String n2EmpId);



@Query("select new com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication(la.id,la.leaveCode,la.employeeId,la.empFname,la.empLname,la.leaveTypeId,"
		+"la.leaveTypeCode,la.leaveTypeName,la.startDt,la.startSession,la.leaveAppliedDate,la.endDt,"
		+"la.endSession,la.leaveStatus,la.leaveStatusName,la.n1EmpId,la.n1EmpCode,la.n1EmpFname,la.n1EmpLname,"
		+"la.n2EmpId,la.n2EmpCode,la.n2EmpFname,la.n2EmpFname,la.hr1EmpId,la.hr1EmpFname,la.hr1EmpLname,la.isSettled)" 
		+"from LeaveApplication la where  ifnull(la.hr1UserGroupId,'') in (:hr1UserGroupIds)  AND la.leaveStatus='APPROVED' "
		+"ORDER BY la.leaveAppliedDate")
List<LeaveApplication> findByHr1EmpIdByIsSettledIn(List<String> hr1UserGroupIds);


@Query("select la from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication la where "
		+ "(la.leaveStatus='N1-APPROVAL-PENDING' OR la.leaveStatus='N2-APPROVAL-PENDING') AND la.startDt<=:date AND la.shiftCode=:shiftCode")
List<LeaveApplication> findUnApprovedLeave(LocalDate date,String shiftCode);


}
 

 	



