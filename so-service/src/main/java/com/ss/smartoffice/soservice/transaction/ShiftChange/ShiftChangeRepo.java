package com.ss.smartoffice.soservice.transaction.ShiftChange;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ShiftChangeRepo extends CrudRepository<ShiftChange, Integer>{

	List<ShiftChange> findByN1IdOrN2Id(String n1Id,String n2Id);
	
	List<ShiftChange> findByEmpId(String empId);
	
	@Query("SELECT shift from  com.ss.smartoffice.soservice.transaction.ShiftChange.ShiftChange shift where shift.hr1GroupId=:hr1Ids")
	List<ShiftChange> findByHr1GroupId(List<String> hr1Ids);
	
	@Query("SELECT COUNT(shift) from  com.ss.smartoffice.soservice.transaction.ShiftChange.ShiftChange shift where shift.fromDt<=:fromDt AND shift.toDt>=:toDt AND shift.empId=:empId And shift.status<>'REJECTED' AND shift.status<>'CANCELLED'")
	Integer checkDatePresent(@Param("fromDt")LocalDate fromDt,@Param("toDt")LocalDate toDt,@Param("empId")String empId);

	@Query("SELECT shift from  com.ss.smartoffice.soservice.transaction.ShiftChange.ShiftChange shift where shift.fromDt<=:fromDt AND shift.isComplete='N' And shift.status='APPROVED'")
	List<ShiftChange> shiftChangeRequest(@Param("fromDt")LocalDate fromDt);
	
}
