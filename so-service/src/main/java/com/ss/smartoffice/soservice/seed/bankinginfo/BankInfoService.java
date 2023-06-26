package com.ss.smartoffice.soservice.seed.bankinginfo;

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
@RequestMapping(value="seed/bankinfo")
@Scope("prototype")
public class BankInfoService {
	@Autowired
	 BankInfoRepo bankinforepo;
	@Autowired
	CommonUtils commonutils;
	
@GetMapping
public Iterable<BankInfo> getBankInfo() throws SmartOfficeException{
	return bankinforepo.findAll();
}
@GetMapping(value="/{id}")
public Optional<BankInfo> getBankInfoById(@PathVariable(value="id")Integer id) throws SmartOfficeException{
	return bankinforepo.findById(id);
}
@PostMapping
public BankInfo addBankInfo(@RequestBody BankInfo bankinfoinput)throws SmartOfficeException{
	bankinfoinput.setCreatedBy(commonutils.getLoggedinEmployeeId());
	bankinfoinput.setIsEnabled("Y");
	return bankinforepo.save(bankinfoinput);
}
@PatchMapping(value="/{id}/update")
public BankInfo updateBankInfo(@RequestBody BankInfo bankinfoinput,@PathVariable(value="id")Integer id)throws SmartOfficeException{
	bankinfoinput.setModifiedBy(commonutils.getLoggedinEmployeeId());
    bankinfoinput.setIsEnabled("Y");
	return bankinforepo.save(bankinfoinput);
}
@DeleteMapping(value="/{id}/delete")
public void deleteBankInfo(@PathVariable(value="id")Integer id)throws SmartOfficeException {
	     bankinforepo.deleteById(id);
}
	
	

}
