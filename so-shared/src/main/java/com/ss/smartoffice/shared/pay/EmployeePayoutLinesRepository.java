package com.ss.smartoffice.shared.pay;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.EmployeePayoutLine;
@Scope("prototype")
public interface EmployeePayoutLinesRepository extends CrudRepository<EmployeePayoutLine, Integer> {
	
@Query("Select x from com.ss.smartoffice.shared.model.EmployeePayoutLine x where x.empCode=:empCode and x.dPayTypeCode=:dPayTypeCode")	
List<EmployeePayoutLine> findByEmpCodeAndDPayTypeCode(@Param("empCode")String empCode,@Param("dPayTypeCode")String dPayTypeCode);

Iterable<EmployeePayoutLine> findByemployeePayoutId(String employeePayoutId);
}
