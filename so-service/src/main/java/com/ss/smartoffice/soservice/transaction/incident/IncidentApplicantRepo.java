package com.ss.smartoffice.soservice.transaction.incident;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.IncidentApplicant;
//import com.ss.smartoffice.shared.model.InterviewResults;
import com.ss.smartoffice.shared.model.InterviewResults;

public interface IncidentApplicantRepo extends CrudRepository<IncidentApplicant, Integer>{
	@Modifying
	@Query("delete from com.ss.smartoffice.shared.model.IncidentApplicant b where b.id=:id")
	void delete(@Param("id") int id);
	
	@Query("select ia from com.ss.smartoffice.shared.model.IncidentApplicant ia where "
			+ "ifnull(LOWER(ia.institute),'') LIKE LOWER(CONCAT('%',ifnull(:institute,''),'%')) AND  "
			+ "ifnull(LOWER(ia.passoutYear),'') LIKE LOWER(CONCAT('%',ifnull(:passoutYear,''),'%')) AND "
			+ "ifnull(LOWER(ia.course),'') LIKE LOWER(CONCAT('%',ifnull(:course,''),'%')) AND "
			+ "ifnull(LOWER(ia.degreeName),'') LIKE LOWER(CONCAT('%',ifnull(:degreeName,''),'%')) AND "
			+ "ifnull(LOWER(ia.marks),'')>=ifnull(:marks,'') AND "
			+ "ifnull(LOWER(ia.historyOfArrears),'') LIKE LOWER(CONCAT('%',ifnull(:historyOfArrears,''),'%')) AND "
			+ "ifnull(LOWER(ia.isEligibleForTest),'') LIKE LOWER(CONCAT('%',ifnull(:isEligibleForTest,''),'%'))")
	List<IncidentApplicant> findByAdvanceSearch(String institute, String course, String degreeName, String passoutYear,String marks,String historyOfArrears,String isEligibleForTest);
	
	@Query("select ia from com.ss.smartoffice.shared.model.IncidentApplicant ia where ia.employeeId = :empId and  ia.incidentId=:IncidentId")
	List<IncidentApplicant> findAlreadyExiste( String empId,String IncidentId);
	
//	List<IncidentApplicantView> findByIncidentId(String incidentId);

//	 @Query("SELECT new com.ss.smartoffice.shared.model.InterviewResults (ia.incidentId,ia.firstName,ia.lastName,ia.email,ia.mobileNumber,ia.expType,ia.isInterviewEligible,ia.finalInterviewStatus,iv.finalDecisionRemarks,\n" + 
//		 		"iv.firstInterviewerId,ir.decision   as round1Decision,ir.rating  as round1Rating,ir.remarks  as round1Remarks,ir.roundCompletedDt as round1completedDt,\n" + 
//		 		"iv.secondInterviewerId,ir2.decision as round2Decision,ir2.rating as round2Rating,ir2.remarks as round2Remarks,ir2.roundCompletedDt as round2completedDt,\n" + 
//		 		"iv.thirdInterviewerId,ir3.decision  as round3Decision,ir3.rating as round3Rating,ir3.remarks as round3Remarks,ir3.roundCompletedDt as round3completedDt\n)" + 
//		 		"from IncidentApplicant as ia LEFT JOIN Interview iv on ia.id = iv.applicant_id\n" + 
//		 		"LEFT JOIN InterviewRound as ir on iv.id=ir.interviewId AND iv.firstInterviewerId=ir.interviewEmpId\n" + 
//		 		"LEFT JOIN InterviewRound as ir2 on iv.id=ir2.interviewId AND iv.secondInterviewerId=ir2.interviewEmpId\n" + 
//		 		"LEFT JOIN InterviewRound as ir3 on iv.id=ir3.interviewId AND iv.thirdInterviewerId=ir3.interviewEmpId where ia.incidentId = :incidentId AND ia.finalInterviewStatus is NOT NULL") 
//	 
//	 List<InterviewResults> findByIDE(Integer incidentId);
	 
	 
	
	 
//	@Query("SELECT s from com.ss.smartoffice.soservice.transaction.incident.IncidentApplicant s where s.incidentId= :incidentId")
//	
//	List<OverallIncidentResultView>  findByOverall(String incidentId);
//	@Query("SELECT s from com.ss.smartoffice.soservice.transaction.incident.IncidentApplicant s where s.incidentId= :incidentId")
//	List<IncidentApplicantView> findByIncidentId(String incidentId);
	List<com.ss.smartoffice.shared.model.IncidentApplicant> findByIncidentId(String incidentId);
	
	
	
	
//	@Query("select ia from com.ss.smartoffice.shared.model.IncidentApplicant ia WHERE ia.finalTestStatus=:finalTestStatus OR ia.finalInterviewStatus=:finalInterviewStatus OR ia.finalDecision=:finalDecision") 
//	+ "LOWER(ia.contactMobileNo) LIKE LOWER(CONCAT('%',:contactMobileNo,'%')) AND "
//	+ "LOWER(ia.contactEmailId) LIKE LOWER(CONCAT('%',:contactEmailId,'%'))")
	@Query("select ia from com.ss.smartoffice.shared.model.IncidentApplicant ia where "
			+ "ifnull(LOWER(ia.finalTestStatus),'') LIKE LOWER(CONCAT('%',ifnull(:finalTestStatus,''),'%')) AND  "
			+ "ifnull(LOWER(ia.finalInterviewStatus),'') LIKE LOWER(CONCAT('%',ifnull(:finalInterviewStatus,''),'%')) AND "
			+ "ifnull(LOWER(ia.finalDecision),'') LIKE LOWER(CONCAT('%',ifnull(:finalDecision,''),'%'))")
Iterable<IncidentApplicant> fliter(String finalTestStatus, String finalInterviewStatus,String finalDecision);
}
