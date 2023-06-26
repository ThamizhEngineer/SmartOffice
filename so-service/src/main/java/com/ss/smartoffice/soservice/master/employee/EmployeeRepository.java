package com.ss.smartoffice.soservice.master.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest;

public interface EmployeeRepository extends CrudRepository<memployee, Integer> {

	
	 @Query("SELECT new com.ss.smartoffice.shared.model.employee.memployee(e.id,e.empName,e.empCode,e.loginUserId,e.permContactNo,e.emailId,"
	  		+ "e.dob,e.empStatus,e.gradeId,e.gradeName,e.designationId,e.gender,e.desigName,e.empTypeCode,e.startDt,e.endDt,"
	  		+ "e.isEnabled,e.deptId,e.deptName,e.empCategory,e.isSystemUser,e.legalPending) FROM memployee e where e.empTypeCode ='REGULAR' AND e.isSystemUser='N'") 
	 List<memployee> findRegEmpSummaries();
	  
	  
	 @Query("SELECT new com.ss.smartoffice.shared.model.employee.memployee(n1.id,n1.empName,n1.empCode,n1.loginUserId,n1.permContactNo,n1.emailId,"
			 + "n1.dob,n1.empStatus,n1.gradeId,n1.gradeName,n1.designationId,n1.gender,n1.desigName,n1.empTypeCode,n1.startDt,emp.endDt,"
			 + "n1.isEnabled,n1.deptId,n1.deptName,n1.empCategory,n1.isSystemUser,n1.legalPending) From memployee n1 join memployee emp on n1.id=emp.n1EmpId where n1.empTypeCode ='REGULAR' AND n1.isSystemUser='N'")
	 List<memployee> fetchN1Employees();
	 
//	 @Query( "select new com.ss.smartoffice.shared.model.BankAdviseData(emp.bankName,emp.ifscCode,emp.accNo,emp.accountName,expense.totalEntitledAmount,"
//	 		+ "expense.settleRemarks,expense.expensePurpose,expense.debitAccountNo) from ExpenseClaim as expense  left join  BankDetails as emp on expense.employeeId=emp.employeeId where emp.isDefault='Y' and expense.expenseClaimStatus='SETTLED'and expense.expensePurpose = :expensePurpose AND expense.settledDt " + 
//				"BETWEEN :fromDt and :endDt" )

	List<memployee> findByLoginUserId(String loginUserId);

	List<memployee> findByEmpCode(String empCode);

	Optional<memployee> findByDocId(String docId);

	@Query("select new com.ss.smartoffice.shared.model.employee.memployee(e.id, e.empName,e.empCode,e.n1EmpId,e.n2EmpId,e.hr1UsrGrpId,e.hr1UsrGrpName,e.hr2UsrGrpId,e.hr2UsrGrpName,e.acc1UsrGrpId,e.isAcc1UsrGrpName,e.acc2UsrGrpId,e.isAcc2UsrGrpName,e.empCategory,e.legalPending)"
			+ " from memployee e where e.n1EmpId=:n1EmpId OR e.n2EmpId=:n2EmpId")
	List<memployee> fetchManagerReportees(@Param("n1EmpId") String n1EmpId, @Param("n2EmpId") String n2EmpId);

	@Query("select new com.ss.smartoffice.shared.model.employee.memployee(e.id, e.empName,e.empCode,e.n1EmpId,e.n2EmpId,e.hr1UsrGrpId,e.hr1UsrGrpName,e.hr2UsrGrpId,e.hr2UsrGrpName,e.acc1UsrGrpId,e.isAcc1UsrGrpName,e.acc2UsrGrpId,e.isAcc2UsrGrpName,e.empCategory,e.legalPending) from memployee e where e.n1EmpId IS NULL OR e.n2EmpId IS NULL")
	List<memployee> fetchManager();

//	@Query("select new com.ss.smartoffice.shared.model.employee.memployee(e.id, e.empName,e.empCode,e.hr1Id,e.hr1Name,e.hr2Id,e.hr2Name)"
//			+ " from memployee e where ifnull(LOWER(e.hr1Id),'') LIKE LOWER(CONCAT('%',ifnull(:hr1Id,''),'%')) "
//		+ "OR ifnull(LOWER(e.hr2Id),'') LIKE LOWER(CONCAT('%',ifnull(:hr1Id,''), '%')) ")
//	List<memployee> fetchHrManagerReportees(@Param("hr1Id")String hr1Id);
//	
//	@Query("select new com.ss.smartoffice.shared.model.employee.memployee(e.id, e.empName,e.isAcc1Id,e.isAcc1Name,e.isAcc2Id,e.isAcc2Name)"
//			+ " from memployee e where ifnull(LOWER(e.isAcc1Id),'') LIKE LOWER(CONCAT('%',ifnull(:isAcc1Id,''),'%')) "
//		+ "OR ifnull(LOWER(e.isAcc2Id),'') LIKE LOWER(CONCAT('%',ifnull(:isAcc1Id,''), '%')) ")
//	List<memployee> fetchAccManagerReportees(@Param("isAcc1Id")String isAcc1Id);

	List<memployee> findByN2EmpId(String n2EmpId);

	List<memployee> findByEmpCategory(String empCategory);

	@Query("select emp from com.ss.smartoffice.shared.model.employee.memployee emp where emp.empTypeCode ='REGULAR' AND emp.isSystemUser='N' and ifnull(emp.hr1UsrGrpId,'') in (:hr1UserGroupIds) and "
			+ "ifnull(LOWER(emp.empName),'') LIKE LOWER(CONCAT('%',ifnull(:empName,''),'%')) and ifnull(LOWER(emp.desigName),'') LIKE LOWER(CONCAT('%',ifnull(:desigName,''),'%')) and "
			+ "ifnull(LOWER(emp.emailId),'') LIKE LOWER(CONCAT('%',ifnull(:emailId,''),'%')) and ifnull(LOWER(emp.contactMobileNo),'') LIKE LOWER(CONCAT('%',ifnull(:contactMobileNo,''),'%')) and "
			+ "ifnull(LOWER(emp.officeName),'') LIKE LOWER(CONCAT('%',ifnull(:officeName,''),'%')) and ifnull(LOWER(emp.empCode),'') LIKE LOWER(CONCAT('%',ifnull(:empCode,''),'%')) and "
			+ "ifnull(LOWER(emp.empStatus),'') LIKE LOWER(CONCAT('%',ifnull(:empStatus,''),'%')) and ifnull(LOWER(emp.id),'') LIKE LOWER(CONCAT('%',ifnull(:id,''),'%')) and ifnull(LOWER(emp.n1EmpId),'') LIKE LOWER(CONCAT('%',ifnull(:n1EmpId,''),'%')) and ifnull(LOWER(emp.n2EmpId),'') LIKE LOWER(CONCAT('%',ifnull(:n2EmpId,''),'%'))"
			+ "ORDER BY emp.createdDt")
	List<memployee> findByHr1UserGroupIdIn(List<String> hr1UserGroupIds, @Param("empName") String empName,
			@Param("desigName") String desigName, @Param("emailId") String emailId,
			@Param("contactMobileNo") String contactMobileNo, @Param("officeName") String officeName,
			@Param("empCode") String empCode, @Param("empStatus") String empStatus, @Param("id") String id,
			@Param("n1EmpId") String n1EmpId, @Param("n2EmpId") String n2EmpId);

	@Query("select emp from com.ss.smartoffice.shared.model.employee.memployee emp where emp.empTypeCode ='REGULAR' AND emp.isSystemUser='N' and ifnull(emp.acc1UsrGrpId,'') in (:acc1UserGroupIds)"
			+ "ORDER BY emp.createdDt")
	List<memployee> findByAcc1UserGroupIdIn(List<String> acc1UserGroupIds);

	@Query("select emp from com.ss.smartoffice.shared.model.employee.memployee emp where emp.n1EmpId = :n1EmpId "
			+ "ORDER BY emp.createdDt")
	List<memployee> findByN1EmpId(String n1EmpId);

	@Query("select emp from com.ss.smartoffice.shared.model.employee.memployee emp where emp.id = :id and ( emp.empStatus='ONBOARDED') "
			+ "ORDER BY emp.createdDt")

	List<memployee> findBynewJoineeEmpId(Integer id);

	memployee findByContactEmailId(String emailId);

	@Query("SELECT count(*)from com.ss.smartoffice.shared.model.employee.memployee emp  where emp.empTypeCode ='REGULAR' AND emp.isSystemUser='N'")
	int countAll();
	
	@Query("SELECT emp from com.ss.smartoffice.shared.model.employee.memployee emp  where emp.attendEligibility ='N' AND emp.isSystemUser='N'")
	List<memployee> findByAttendEligibility();
	
	@Query("SELECT emp from com.ss.smartoffice.shared.model.employee.memployee emp  where emp.attendEligibility ='N' AND emp.shiftCode=:shiftCode ")
	List<memployee> findByShiftCode(String shiftCode);
	
	
//	--------------------------------------------------------------
	
	@Query("SELECT DISTINCT x.id from com.ss.smartoffice.shared.model.employee.memployee x where x.deptId=:deptId")
	List<Integer> fetchEmployeeIdsByDeptId(@Param("deptId") Integer deptId);
	
	@Query("SELECT DISTINCT x.id from com.ss.smartoffice.shared.model.employee.memployee x where x.officeId=:officeId")
	List<Integer> fetchEmployeeIdsByOfficeId(@Param("officeId") Integer officeId);
}
