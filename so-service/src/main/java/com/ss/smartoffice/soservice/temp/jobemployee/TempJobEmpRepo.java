package com.ss.smartoffice.soservice.temp.jobemployee;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempJobEmpRepo extends CrudRepository<TempJobEmp,Integer> {

	List<TempJobEmp> findByEmployeeId(Integer employeeId);
	@Query("SELECT s from com.ss.smartoffice.soservice.temp.jobemployee.TempJobEmp s where s.fromDt >= :fromDt and s.toDt <= :toDt and s.employeeId= :employeeId")
List<TempJobEmp> findByJobsBtwDates(LocalDate fromDt,LocalDate toDt,Integer employeeId);
	
	 
}
