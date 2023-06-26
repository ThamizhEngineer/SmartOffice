package com.ss.smartoffice.soservice.transaction.incident;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ss.smartoffice.shared.model.ExportTestResultView;
@Scope("prototype")
public interface IncidentTestRepo extends CrudRepository<IncidentTest, Integer>{
	
	List<FetchIncidentTestView> findByApplicantId(String applicantId);

	@Query("select i from com.ss.smartoffice.soservice.transaction.incident.IncidentTest i where i.applicantId =:applicantId")
	List<IncidentTest> fetchByApplicantId(String applicantId);
	
	@Query("select i from com.ss.smartoffice.soservice.transaction.incident.IncidentTest i where i.incidentId =:incidentId")
	List<IncidentTest> fetchByIncidentId(Integer incidentId);
	
	@Query("select i from com.ss.smartoffice.soservice.transaction.incident.IncidentTest i where ifnull(LOWER(i.score),'') BETWEEN :startScore AND :endScore")
	List<IncidentTest> fetchBetweenScores(@Param("startScore")String startScore,@Param("endScore")String endScore);
	
	@Query("select i from com.ss.smartoffice.soservice.transaction.incident.IncidentTest i where ifnull(LOWER(i.totalResult),'') BETWEEN :startMark AND :endMark")
	List<IncidentTest> fetchBetweenMarks(@Param("startMark")String startMark,@Param("endMark")String endMark);

	@Query("select i from com.ss.smartoffice.soservice.transaction.incident.IncidentTest i "
			+ "where i.incidentId=:incidentId AND ifnull(LOWER(i.testResult),'') LIKE LOWER(CONCAT('%',ifnull(:testResult,''), '%')) AND ifnull(LOWER(i.testStatus),'') LIKE LOWER(CONCAT('%',ifnull(:testStatus,''), '%'))  "
			+ "AND ifnull(LOWER(i.score),'') BETWEEN :startScore AND :endScore AND ifnull(LOWER(i.totalResult),'') BETWEEN :startMark AND :endMark")
	List<IncidentTest> fetchByAdvanceFilters(
			@Param("startScore")Integer startScore,@Param("endScore")Integer endScore,
			@Param("incidentId")Integer incidentId,@Param("testResult")String testResult,@Param("testStatus")String testStatus,
			@Param("startMark")Integer startMark,@Param("endMark")Integer endMark);
	
	@Query("select new com.ss.smartoffice.shared.model.ExportTestResultView (i.testTemplateName,i.applicantName,i.testStatus,i.totalCorrect,i.totalWrong,i.totalUnAttended,i.totalCorrectEasy,i.totalCorrectMedium,i.totalCorrectTough,i.score,i.testResult) from IncidentTest i where i.incidentId =:incidentId")
	List<com.ss.smartoffice.shared.model.ExportTestResultView> findByincidentsId(Integer incidentId);
	List<IncidentTest> findByincidentId(Integer incidentId);
	
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.incident.IncidentTest it where it.id= ?1")
	void delete(int id);
	
	@Query("SELECT it from com.ss.smartoffice.soservice.transaction.incident.IncidentTest it where it.participantId=:participantId And it.incidentId=:incidentId")
	List<IncidentTest> findByIncidentAndApplicantId(Integer incidentId,Integer participantId);
	
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.incident.IncidentTest it where it.participantId=:participantId And it.incidentId=:incidentId")
	void deleteTest(@Param("incidentId") Integer incidentId,@Param("participantId") Integer participantId);
	
	
}
