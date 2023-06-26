package com.ss.smartoffice.soservice.master.Leavebalance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication;
@Scope("prototype")
public interface LeaveBalanceRepository extends PagingAndSortingRepository<LeaveBalance, Integer>{
	List<LeaveBalance> findByFiscalYearId(String fiscalYearId);
	List<LeaveBalance> findByEmployeeId(String employeeId);
	List<LeaveBalance> findByEmployeeIdAndFiscalYearId(String employeeId,String fiscalYearId);
	LeaveBalance findByleavesTypeAndEmployeeId(String leavesType,String employeeId);
	List<LeaveBalance>findByleaveTypeIdAndEmployeeId(String leavesTypeId,String employeeId);
	

}
