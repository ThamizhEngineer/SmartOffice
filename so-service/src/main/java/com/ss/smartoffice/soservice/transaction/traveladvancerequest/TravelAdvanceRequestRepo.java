package com.ss.smartoffice.soservice.transaction.traveladvancerequest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TravelAdvanceRequestRepo  extends CrudRepository<TravelAdvanceRequest,Integer>{
	@Query("select new com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest(req.id,req.tarCode,req.employeeId,"
			+ "req.employeeName,"
			+ "req.jobOrCc,req.costCenterId,req.costCenterCode,req.jobId,"
			+ "req.jobCode,req.dayRangeName,req.reqAdvAmt,req.tarStatus,req.tarSubmittedDt)" 
			+ " from TravelAdvanceRequest req "
			+ "where req.employeeId = :employeeId "
			+ "ORDER BY req.tarSubmittedDt")
	List<TravelAdvanceRequest> findByEmployeeId(String employeeId);


	@Query("select new com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest(req.id,req.tarCode,req.employeeId,"
			+ "req.employeeName,"
			+ "req.jobOrCc,req.costCenterId,req.costCenterCode,req.jobId,"
			+ "req.jobCode,req.dayRangeName,req.reqAdvAmt,req.tarStatus,req.tarSubmittedDt,req.n1EmployeeId)" 
			+ " from TravelAdvanceRequest req "
			+ "where req.n1EmployeeId = :n1EmployeeId "
			+ "and (req.tarStatus='N1-APPROVAL-PENDING' or req.n1Decision is not null) "
			+ "ORDER BY req.tarSubmittedDt,req.employeeId")
	List<TravelAdvanceRequest> findByN1EmployeeId(String n1EmployeeId);

	
	@Query( "select new com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest(req.id,req.tarCode,req.employeeId,req.employeeName,req.jobOrCc,"
			+ "req.costCenterId,req.costCenterCode,req.jobId,req.jobCode,req.dayRangeName,req.reqAdvAmt,"
			+ "req.tarStatus,req.tarSubmittedDt,req.n1EmployeeId,req.acc2UserGroupId) "
			+ "from TravelAdvanceRequest req "
			+ "where ifnull(req.acc2UserGroupId,'') in (:acc2UserGroupId) and ( req.tarStatus='ACC2-APPROVAL-PENDING' or req.acc2Decision is not null) "
			+ "ORDER BY req.tarSubmittedDt,req.employeeId" )
	List<TravelAdvanceRequest> findByAcc2UserGroupIdIn(List<String> acc2UserGroupId);
	
	@Query("select new com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest(req.id,req.tarCode,req.employeeId,"
			+ "req.employeeName,"
			+ "req.jobOrCc,req.costCenterId,req.costCenterCode,req.jobId,"
			+ "req.jobCode,req.dayRangeName,req.reqAdvAmt,req.tarStatus,req.tarSubmittedDt,req.n1EmployeeId,req.accEmpAdjAmount)" 
			+ " from TravelAdvanceRequest req "
			+"where  req.tarStatus='APPROVED' and req.accEmpAdjAmount !='0' ")
		List<TravelAdvanceRequest> findByApprovalAmountIn();
}
