package com.ss.smartoffice.soservice.transaction.appraisalservice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppraisalHdrRepo extends CrudRepository<AppraisalHdr, Integer>{

	
	List<AppraisalHdr> findByFyId(String fyId);
	
	
	@Query("select new com.ss.smartoffice.soservice.transaction.appraisalservice.ManagerSearch(header.fyId,header.fyCode,header.empId,header.empName)from AppraisalHdr header where header.n1EmpId=:n1EmpId")
	List<ManagerSearch>findByManager(String n1EmpId);
	List<AppraisalHdr> findByFyIdAndEmpId(String fyId,String empId);
//	@Query("select new com.ss.smartoffice.soservice.transaction.appraisalservice.ReviewAppraisal(emp.id,emp.fyId,emp.fyCode,emp.empId,emp.empName,emp.goals)from AppraisalHdr as header left join  ReviewAppraisal as emp  on emp.id = header.id where header.fyId=:fyId")
//	List<ReviewAppraisal>findBySummaries(String fyId);
	
}
