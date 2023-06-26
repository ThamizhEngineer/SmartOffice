package com.ss.smartoffice.soservice.transaction.cashbalance;



import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;



@Controller
@RestController
@RequestMapping(value="/transactions/emp-cash-balance")
@Scope("prototype")
public class EmpCashBalService {
	@Autowired
	  EmpCashBalRepository empcashbalrepository;
	@Autowired
	 CommonUtils commonutils;
	@Autowired
	 EmployeeCashBalanceHelper employeecashbalancehelper;

@GetMapping
public Iterable<EmployeeCashBalance> getEmpCashBal() throws SmartOfficeException {
	 return employeecashbalancehelper.findAll();
     }
@GetMapping("/{id}")
public Optional<EmployeeCashBalance> getEmpCashById(@PathVariable(value="id")Integer id) throws SmartOfficeException{
	return employeecashbalancehelper.findById(id);
    }
@DeleteMapping(value="/{id}")
public void deleteEmpCashBal(@PathVariable Integer id) throws SmartOfficeException{
	employeecashbalancehelper.deleteById(id);
	}
@GetMapping(value="/get-latest")
public List<EmployeeCashBalance> getMaxDt() throws SmartOfficeException{
       return employeecashbalancehelper.findByMaxDate();
    }
@GetMapping(value="/get-latest/employee-id")
public Optional<EmployeeCashBalance> getByEmployeeId() throws SmartOfficeException{
	String employeeId=commonutils.getLoggedinEmployeeId();
	return Optional.of(employeecashbalancehelper.findLatestCbByEmployeeId(employeeId));
}
@PostMapping
public EmployeeCashBalance addEmployeeCashBalance(@RequestBody EmployeeCashBalance empcashbal)throws SmartOfficeException{
	empcashbal.setCreatedBy(commonutils.getLoggedinEmployeeId());
	empcashbal.setIsEnabled("Y");
	return employeecashbalancehelper.addEmployeeCashBalance(empcashbal);
}
@PatchMapping(value="/{id}/update")
public EmployeeCashBalance updateEmployeeCashBalance(@RequestBody EmployeeCashBalance empcashbal)throws SmartOfficeException{
	empcashbal.setModifiedBy(commonutils.getLoggedinEmployeeId());
	empcashbal.setIsEnabled("Y");
	return employeecashbalancehelper.updateEmployeeCashBalance(empcashbal);
}

     
}
