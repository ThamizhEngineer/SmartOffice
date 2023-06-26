package com.ss.smartoffice.shared.pay;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.EmployeePayout;
@Scope("prototype")
public interface EmployeePayoutRepository extends PagingAndSortingRepository<EmployeePayout, Integer>{
	String baseQuery="SELECT new com.ss.smartoffice.shared.model.EmployeePayout(e.id, e.employeeId,e.employeeName,e.employeeCode, e.grossSalary,e.payDt,e.netPayAmt, e.dSalaryForMonth,e.dSalaryForYear, e.payslipDocId) FROM EmployeePayout e \n";
List<EmployeePayout> findByEmployeeIdAndPayDt(String employeeId,LocalDate payDt);
	
	EmployeePayout findByPayoutProcessLineId(String id);
	
	List<EmployeePayout> findByPayDt(LocalDate payDt);

	@Query(baseQuery+ "  where e.employeeId=:employeeId AND ifnull(LOWER(e.dSalaryForMonth),'') LIKE LOWER(CONCAT('%',ifnull(:dSalaryForMonth,''), '%')) AND ifnull(LOWER(e.dSalaryForYear),'') LIKE LOWER(CONCAT('%',ifnull(:dSalaryForYear,''), '%'))")
	List<EmployeePayout> findByEmployeeIdAndDSalaryForMonthAndDSalaryForYear(@Param("employeeId")String employeeId, @Param("dSalaryForMonth")String dSalaryForMonth, @Param("dSalaryForYear")String dSalaryForYear );

	@Query(baseQuery+ "  where e.employeeId=:employeeId")
	List<EmployeePayout> findByEmployeeId(@Param("employeeId")String employeeId);
	

	@Query(baseQuery+ "  where ifnull(LOWER(e.dSalaryForMonth),'') LIKE LOWER(CONCAT('%',ifnull(:dSalaryForMonth,''), '%')) AND ifnull(LOWER(e.dSalaryForYear),'') LIKE LOWER(CONCAT('%',ifnull(:dSalaryForYear,''), '%'))")
	List<EmployeePayout> findByDSalaryForMonthAndDSalaryForYear(@Param("dSalaryForMonth")String dSalaryForMonth, @Param("dSalaryForYear")String dSalaryForYear);
	
	@Query(baseQuery+ "  where ifnull(LOWER(e.employeeCode),'') LIKE LOWER(CONCAT('%',ifnull(:employeeCode,''), '%')) AND ifnull(LOWER(e.dSalaryForMonth),'') LIKE LOWER(CONCAT('%',ifnull(:dSalaryForMonth,''), '%')) AND ifnull(LOWER(e.dSalaryForYear),'') LIKE LOWER(CONCAT('%',ifnull(:dSalaryForYear,''), '%'))")
	List<EmployeePayout> findByEmployeeCodeAndDSalaryForMonthAndDSalaryForYear(String employeeCode, String dSalaryForMonth, String dSalaryForYear );
	@Procedure
	void
	processEmpPayout(String employeeId, String compPayCycleId);
	
	  @Query(baseQuery) 
	    List<EmployeePayout> findEmpPayoutSummaries();

//	Page<EmployeePayout> findByDsalaryForYear(Pageable pageable, String dSalaryForYear);

	Page<EmployeePayout> findAll(Pageable pageable);

	Page<EmployeePayout> findByDSalaryForMonth(Pageable pageable, String dSalaryForMonth);

	Page<EmployeePayout> findByDSalaryForMonthAndDSalaryForYear(Pageable pageable, String dSalaryForYear,
			String dSalaryForMonth);

	Page<EmployeePayout> findByDSalaryForYear(Pageable pageable, String dSalaryForYear);

}


