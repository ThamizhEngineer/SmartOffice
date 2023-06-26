package com.ss.smartoffice.soservice.transaction.exitInterview;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface ExitInterviewRepository extends CrudRepository<ExitInterview, Integer> {

	@Query("select exitInterview from com.ss.smartoffice.soservice.transaction.exitInterview.ExitInterview exitInterview where (exitInterview.status='PENDING-ACCOUNT-CLEARENCE') and ifnull(exitInterview.acc2GroupId,'') in (:acc2Ids)")
	List<ExitInterview> findByAcc2(List<String> acc2Ids);
	
	@Query("select exitInterview from com.ss.smartoffice.soservice.transaction.exitInterview.ExitInterview exitInterview where (exitInterview.status='PENDING-HR-CLEARENCE') and ifnull(exitInterview.hr2GroupId,'') in (:hr2Ids)")
	List<ExitInterview> findByHr2(List<String> hr2Ids);
	
	@Query("select exitInterview from com.ss.smartoffice.soservice.transaction.exitInterview.ExitInterview exitInterview where (exitInterview.status='PENDING-N1-CLEARENCE') and exitInterview.n1ManagerId=:n1Id")
	List<ExitInterview> findByN1(String n1Id);
	
}
