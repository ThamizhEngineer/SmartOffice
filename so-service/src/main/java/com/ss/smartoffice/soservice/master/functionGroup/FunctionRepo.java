package com.ss.smartoffice.soservice.master.functionGroup;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FunctionRepo extends CrudRepository<Function, Integer> {

//	@Query( "select new com.ss.smartoffice.soservice.transaction.appraisalservice.GoalAnalysis(appGoal.appraisalHdrId,appGoal.metricId,appGoal.operator,appGoal.metricName,appGoal.finalTarget,appGoal.goalDesc) from AppraisalGoal appGoal  "
//			+ "left join AppraisalHdr hedr on hedr.id = appGoal.appraisalHdrId where hedr.fyId=:fyId and hedr.empId=:empId" )
	
	@Query("select new com.ss.smartoffice.soservice.master.functionGroup.DivisionLine(md.divisionName) from com.ss.smartoffice.soservice.master.businessUnit.DivisionService ser "
			+ "left join com.ss.smartoffice.soservice.master.functionGroup.Function mf on mf.id=ser.mServiceId "
			+ "left join com.ss.smartoffice.soservice.master.businessUnit.Division md on md.id=ser.mDivisionId where ser.mServiceId=:id")
	List<DivisionLine> divisionService(String id);

	@Query("select new com.ss.smartoffice.soservice.master.functionGroup.DivisionLine(md.divisionName) from com.ss.smartoffice.soservice.master.businessUnit.DivisionGood mdg "
			+ "left join com.ss.smartoffice.soservice.master.functionGroup.Function mf on mf.id=mdg.mGoodsId "
			+ "left join com.ss.smartoffice.soservice.master.businessUnit.Division md on md.id=mdg.mDivisionId where mdg.mGoodsId=:id")
	List<DivisionLine> divisionGoods(String id);
	
	
	List<Function> findByDeliveryType(String type);
	
}
