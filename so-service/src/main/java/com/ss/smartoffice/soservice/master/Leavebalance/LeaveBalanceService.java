package com.ss.smartoffice.soservice.master.Leavebalance;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
@RestController
@RequestMapping("/master/leave-balances")
@Scope("prototype")
public class LeaveBalanceService {
@Autowired
LeaveBalanceRepository leaveBalanceRepository;

@Autowired
CommonUtils commonUtils;

@GetMapping
public Iterable<LeaveBalance> getLeaveBalances(@RequestParam(value="employeeId",required=false)String employeeId,@RequestParam(value="fiscalYearId",required=false)String fiscalYearId) throws SmartOfficeException{
	boolean searchByEmployeeIdAndFiscalYearId=false, searchByEmployeeId=false, searchByFiscalYearId=false;
	
	
	if( fiscalYearId!=null&&!fiscalYearId.isEmpty()) {
		searchByFiscalYearId=true;
	}
	if(employeeId!=null&& !employeeId.isEmpty()) {
		searchByEmployeeId=true;
	}
	if(employeeId!=null&&!employeeId.isEmpty()&& fiscalYearId!=null&&!fiscalYearId.isEmpty()) {
		searchByEmployeeIdAndFiscalYearId=true;
	}
	if(searchByEmployeeIdAndFiscalYearId) {
		return leaveBalanceRepository.findByEmployeeIdAndFiscalYearId(employeeId, fiscalYearId);
	}
	if(searchByFiscalYearId) {
		return leaveBalanceRepository.findByFiscalYearId(fiscalYearId);
	}
	if(searchByEmployeeId) {
		return leaveBalanceRepository.findByEmployeeId(employeeId);
	}
	return leaveBalanceRepository.findAll();
	
}

@GetMapping("/{id}")
public Optional<LeaveBalance> getLeaveBalanceById(@PathVariable(value="id")int id)throws SmartOfficeException{
	return leaveBalanceRepository.findById(id);
	
}

@PostMapping

public LeaveBalance addLeaveBalance(@RequestBody LeaveBalance leaveBalance)throws SmartOfficeException{
	return leaveBalanceRepository.save(leaveBalance);
	
}



@PatchMapping("/bulk-update")
public Iterable<LeaveBalance> bulkaddAndupdate(@RequestBody List<LeaveBalance> leaveBalances) throws SmartOfficeException{
	
	List<LeaveBalance> leaveBalanceList=new ArrayList<LeaveBalance>();
	System.out.println(leaveBalances);
	for(LeaveBalance leaveBalance:leaveBalances) {
		if(leaveBalance.getId()>0) {
			LeaveBalance leaveBalanceFromDB=leaveBalanceRepository.findById(leaveBalance.getId()).orElse(new LeaveBalance());
			System.out.println(leaveBalanceFromDB);
			leaveBalance=this.matchAndRelavantFields(leaveBalanceFromDB,leaveBalance);	
		}else {
			leaveBalance=addingNewRecord(leaveBalance);
		}
			leaveBalance.setIsEnabled("Y");
			leaveBalanceList.add(leaveBalance);
	}
		System.out.println(leaveBalanceList);
		return(Iterable<LeaveBalance>) leaveBalanceRepository.saveAll(leaveBalanceList);
	}
	
	

private LeaveBalance addingNewRecord(LeaveBalance leaveBalance) {
	// this service will add new Record if there is no existing Record
	LeaveBalance newleaveBalance=new LeaveBalance();
	newleaveBalance.setEmployeeId(leaveBalance.getEmployeeId());
	newleaveBalance.setFiscalYearId(leaveBalance.getFiscalYearId());
	newleaveBalance.setLeaveTypeId(leaveBalance.getLeaveTypeId());
	newleaveBalance.setRemarks(leaveBalance.getRemarks());
	newleaveBalance.setLeaveBalance(leaveBalance.getLeaveBalance());
	newleaveBalance.setCarriedOver(leaveBalance.getCarriedOver());
	newleaveBalance.setIsEnabled("Y");
	newleaveBalance.setCreatedBy(commonUtils.getLoggedinEmployeeId());
	newleaveBalance.setCreatedDt(LocalDateTime.now());
	return newleaveBalance;
}
private LeaveBalance matchAndRelavantFields(LeaveBalance leaveBalanceFromDB, LeaveBalance leaveBalance) {
	//only update relevant fields to the db-record
	leaveBalanceFromDB.setId(leaveBalance.getId());
	leaveBalanceFromDB.setEmployeeId(leaveBalance.getEmployeeId());
	leaveBalanceFromDB.setLeaveTypeId(leaveBalance.getLeaveTypeId());
	leaveBalanceFromDB.setRemarks(leaveBalance.getRemarks());
	leaveBalanceFromDB.setFiscalYearId(leaveBalance.getFiscalYearId());
	leaveBalanceFromDB.setLeaveBalance(leaveBalance.getLeaveBalance());
	leaveBalanceFromDB.setCarriedOver(leaveBalance.getCarriedOver());
	return leaveBalanceFromDB;
}



@DeleteMapping("/{id}")
public void deleteLeaveBalance(@PathVariable(value="id")int id)throws SmartOfficeException{
	leaveBalanceRepository.deleteById(id);
}


}
