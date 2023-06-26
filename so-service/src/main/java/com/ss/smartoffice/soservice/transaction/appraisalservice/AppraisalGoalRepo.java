package com.ss.smartoffice.soservice.transaction.appraisalservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface AppraisalGoalRepo extends CrudRepository<AppraisalGoal, Integer>{
	@Query( "select new com.ss.smartoffice.soservice.transaction.appraisalservice.GoalAnalysis(appGoal.appraisalHdrId,appGoal.metricId,appGoal.operator,appGoal.metricName,appGoal.finalTarget,appGoal.goalDesc) from AppraisalGoal appGoal  "
			+ "left join AppraisalHdr hedr on hedr.id = appGoal.appraisalHdrId where hedr.fyId=:fyId and hedr.empId=:empId" )
	
	List<GoalAnalysis> findByDetails(String fyId,String empId);
	List<AppraisalGoal> findByMetricId(String metricId);
	List<AppraisalGoal> findByAppraisalHdrId(String appraisalHdrId);
}
