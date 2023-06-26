package com.ss.smartoffice.soservice.transaction.cashbalance;




import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public interface EmpCashBalRepository extends CrudRepository<EmployeeCashBalance,Integer> {


	@Query("SELECT NEW com.ss.smartoffice.soservice.transaction.cashbalance.EmployeeCashBalance(e.id,e.employeeId,e.empName,e.importDt,e.payToCompAmt,e.payToEmpAmt,e.cashBalImportId,e.isEnabled,e.createdBy,e.modifiedBy,e.createdDt,e.modifiedDt) FROM EmployeeCashBalance e WHERE e.importDt=(SELECT MAX(e.importDt)FROM EmployeeCashBalance e) GROUP BY (e.employeeId) ORDER BY (e.employeeId)")
	List<EmployeeCashBalance> findByMaxDate();

    @Query("SELECT NEW com.ss.smartoffice.soservice.transaction.cashbalance.EmployeeCashBalance(e.id,e.employeeId,e.empName,e.importDt,e.payToCompAmt,e.payToEmpAmt,e.cashBalImportId,e.isEnabled,e.createdBy,e.modifiedBy,e.createdDt,e.modifiedDt)" 
    		+"FROM EmployeeCashBalance e WHERE e.employeeId = :employeeId order by e.importDt desc ") 
     List<EmployeeCashBalance> findByEmployeeId(@Param(value="employeeId")String employeeId);
	

}
