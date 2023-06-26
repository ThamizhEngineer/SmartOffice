package com.ss.smartoffice.soservice.transaction.cashbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@Service
public class EmployeeCashBalanceHelper {
	@Autowired
	  CommonUtils commonutils;
	@Autowired
      EmpCashBalRepository empcashbalrepository;
	
public Iterable<EmployeeCashBalance> findAll() throws SmartOfficeException {
		 return empcashbalrepository.findAll();
	     }	
//add
public EmployeeCashBalance addEmployeeCashBalance(@RequestBody EmployeeCashBalance  empcashbal) throws SmartOfficeException {
    empcashbal.setCreatedBy(commonutils.getLoggedinEmployeeId());     
	empcashbal.setIsEnabled("Y");
    return empcashbalrepository.save(empcashbal);
}
//update 
public EmployeeCashBalance updateEmployeeCashBalance(@RequestBody EmployeeCashBalance empcashbal) throws SmartOfficeException{
         empcashbal.setModifiedBy(commonutils.getLoggedinEmployeeId());
         empcashbal.setIsEnabled("Y");
         return empcashbalrepository.save(empcashbal);
}

//createupdate
public Iterable<EmployeeCashBalance>bulkAddandUpdate(@RequestBody List<EmployeeCashBalance> empcashbalinput) throws SmartOfficeException{
	List<EmployeeCashBalance> empcashballist=new ArrayList<EmployeeCashBalance>();
	System.out.println(empcashballist);
	for(EmployeeCashBalance empcasbal:empcashbalinput) {
		if(empcasbal.getId()>0) {
			EmployeeCashBalance empfromDB=empcashbalrepository.findById(empcasbal.getId()).orElse(new EmployeeCashBalance());
		       empfromDB=this.matchAndUpdateFields(empfromDB,empcasbal);
		}else {
			empcasbal=addingNewRecord(empcasbal);
		}
		empcasbal.setIsEnabled("Y");
		empcashballist.add(empcasbal);
	}
       System.out.println(empcashballist);
       return(Iterable<EmployeeCashBalance>)empcashbalrepository.saveAll(empcashballist);
}
  private EmployeeCashBalance addingNewRecord(EmployeeCashBalance empcashbal) {
	  EmployeeCashBalance newEmpbal= new EmployeeCashBalance();
	  newEmpbal.setCashBalImportId(empcashbal.getCashBalImportId());
	  newEmpbal.setCreatedBy(empcashbal.getCreatedBy());
	  newEmpbal.setCreatedDt(empcashbal.getCreatedDt());
	  newEmpbal.setEmployeeId(empcashbal.getEmployeeId());
	  newEmpbal.setId(empcashbal.getId());
	  newEmpbal.setImportDt(empcashbal.getImportDt());
	  newEmpbal.setIsEnabled(empcashbal.getIsEnabled());
	  newEmpbal.setModifiedBy(empcashbal.getModifiedBy());
	  newEmpbal.setModifiedDt(empcashbal.getModifiedDt());
	  newEmpbal.setPayToCompAmt(empcashbal.getPayToCompAmt());
	  newEmpbal.setPayToEmpAmt(empcashbal.getPayToEmpAmt());
 return newEmpbal;	  
	  }
  private EmployeeCashBalance matchAndUpdateFields(EmployeeCashBalance empfromDB,EmployeeCashBalance empcasbal) {
	  empfromDB.setCashBalImportId(empcasbal.getCashBalImportId());
	  empfromDB.setCreatedBy(empcasbal.getCreatedBy());
	  empfromDB.setCreatedDt(empcasbal.getCreatedDt());
	  empfromDB.setEmployeeId(empcasbal.getEmployeeId());
	  empfromDB.setId(empcasbal.getId());
	  empfromDB.setImportDt(empcasbal.getImportDt());
	  empfromDB.setIsEnabled(empcasbal.getIsEnabled());
	  empfromDB.setModifiedBy(empcasbal.getModifiedBy());
	  empfromDB.setModifiedDt(empcasbal.getModifiedDt());
	  empfromDB.setPayToCompAmt(empcasbal.getPayToCompAmt());
	  empfromDB.setPayToEmpAmt(empcasbal.getPayToEmpAmt());
      return empfromDB;
  }
  //findbyid
public Optional<EmployeeCashBalance> findById(Integer id) {
	return empcashbalrepository.findById(id);
    }
//deletebyid
public void deleteById(Integer id) {
    empcashbalrepository.deleteById(id);	
    }
//latest data record for all
public List<EmployeeCashBalance> findByMaxDate() {
	return empcashbalrepository.findByMaxDate();
    }
//latest data record forlogin employee id
public EmployeeCashBalance findLatestCbByEmployeeId(String employeeId) {
	try {
		List<EmployeeCashBalance> balances=empcashbalrepository.findByEmployeeId(employeeId);
		if(balances!=null && !balances.isEmpty())
			return empcashbalrepository.findByEmployeeId(employeeId).get(0);
		else
			throw new SmartOfficeException("No Cashbalance for the employee!");
	} catch (SmartOfficeException soe) {
		throw soe;
	}
	catch (Exception e) { 
		e.printStackTrace();
		throw new SmartOfficeException(e);
		
	}
}

}


