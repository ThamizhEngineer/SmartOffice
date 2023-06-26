package com.ss.smartoffice.soservice.transaction.PayoutProcess;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.EmployeePayout;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.pay.EmployeePayoutRepository;
@RestController
@RequestMapping("transaction/process-payouts")
@Scope("prototype")
public class PayoutProcessService {
@Autowired
PayoutProcessHeaderRepository payoutProcessHeaderRepository;
@Autowired 
PayoutProcessLineRepository payoutProcessLineRepository;
@Autowired
EmployeePayoutRepository employeePayoutRepository;
@PersistenceContext
private EntityManager entityManager;

@Value("${print-payout.url}")
private String printpayoutUrl;
@Autowired
CommonUtils commonUtils;

@GetMapping
public Iterable<PayoutProcessHeader> getPayout()throws SmartOfficeException{
	return payoutProcessHeaderRepository.payoutProcessHeaderByDesc();
}


@GetMapping("/{id}")
public Optional<PayoutProcessHeader> getPayoutById(@PathVariable(value="id")int id)throws SmartOfficeException{
	return payoutProcessHeaderRepository.findById(id);
	
}

@PostMapping
public PayoutProcessHeader addPayoutProcess(@RequestBody PayoutProcessHeader payoutProcessHeader)throws SmartOfficeException{
	return payoutProcessHeaderRepository.save(payoutProcessHeader);
	
}


@PatchMapping("/{id}")
public PayoutProcessHeader updatePayoutProcess(@RequestBody PayoutProcessHeader payoutProcessHeader)throws SmartOfficeException{
	return payoutProcessHeaderRepository.save(payoutProcessHeader);
	
}

@DeleteMapping("/{id}")
public void deletePayoutProcessById(@PathVariable(value="id")int id)throws SmartOfficeException{
	payoutProcessHeaderRepository.deleteById(id);
}
//@CrossOrigin(origins = "*")
@PostMapping("/lines")
public PayoutProcessHeader addPayoutLine(@RequestBody PayoutProcessHeader payoutProcessHeader)throws SmartOfficeException{
	if(payoutProcessHeader.getPayoutProcessLines()!=null) {
		for(PayoutProcessLine payoutProcessLine:payoutProcessHeader.getPayoutProcessLines()) {
			
			payoutProcessLineRepository.save(payoutProcessLine);
		}
	}else {
		throw new SmartOfficeException("Payout Process Line Is Empty");
	}
	return payoutProcessHeader;
	
}


@PatchMapping("/{id}/lines")
public PayoutProcessHeader updatePayoutLine(@RequestBody PayoutProcessHeader payoutProcessHeader)throws SmartOfficeException{
	if(payoutProcessHeader.getPayoutProcessLines()!=null) {
		for(PayoutProcessLine payoutProcessLine:payoutProcessHeader.getPayoutProcessLines()) {
			payoutProcessLineRepository.save(payoutProcessLine);
		}
	}else {
		throw new SmartOfficeException("Payout Process Line Is Empty");
	}
	
	return payoutProcessHeader;
	
}



@Transactional
@DeleteMapping("/{id}/delete/lines")
public void deleteEmployeeLines(@PathVariable(value = "id") int id) throws SmartOfficeException {
	PayoutProcessHeader payoutProcessHeader=getPayoutById(id).get();
	if(payoutProcessHeader.getPayoutProcessLines()!=null) {
		for(PayoutProcessLine payoutProcessLine:payoutProcessHeader.getPayoutProcessLines()) {
			payoutProcessLineRepository.delete(payoutProcessLine.getId());
		}
	}else {
		throw new SmartOfficeException("Payout Line Is Empty");
	}
}


@PatchMapping("/{id}/start")
public String payoutstartProcessById(@PathVariable(value="id")int id)throws SmartOfficeException{
	

	
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("payout_startProcessById");

    //Declare the parameters in the same order
    query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
    query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
    query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
    query.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);
  
    
    //Pass the parameter values
    query.setParameter(1, id);
    query.setParameter(2, "");
    //Execute query
    query.execute();
  //Get output parameters
    
     query.getOutputParameterValue(3);
    query.getOutputParameterValue(4);
	return (String) query.getOutputParameterValue(3);

    
	
	
}


@PatchMapping("/create")
public String payoutCreateProcess(@RequestBody PayoutProcessHeader payoutProcessHeader) throws SmartOfficeException{
		if(payoutProcessHeader.getPayCycleLineId()==null || payoutProcessHeader.getPayCycleLineId().trim().isEmpty()) {
			throw new SmartOfficeException("payCycleLineId is mandatory");
		}
		
	 StoredProcedureQuery query = entityManager.createStoredProcedureQuery("payout_createProcess");
	 
	//Declare the parameters in the same order
	 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
	 query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
	 query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
	 query.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);
	 
	//Pass the parameter values
	 query.setParameter(1, payoutProcessHeader.getPayCycleLineId());
	 query.setParameter(2, payoutProcessHeader.getEmployeeId());
	 
	//Execute query
	 query.execute();
	 
	 //Get output parameters
	    
     query.getOutputParameterValue(3);
    query.getOutputParameterValue(4);
	return (String) query.getOutputParameterValue(3);
}



@PatchMapping("/process-salaries")
public String payoutCreateProcessByMonthAndYear(@RequestBody PayoutProcessHeader payoutProcessHeader) throws SmartOfficeException{		
		
	 StoredProcedureQuery payout = entityManager.createStoredProcedureQuery("payout_processByMonthAndYear");
	 
	 payout.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
	 payout.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
	 payout.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
	 payout.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);
	 
	 payout.setParameter(1, payoutProcessHeader.getMonth());
	 payout.setParameter(2, payoutProcessHeader.getYear());
	 
	 payout.execute();
	    
	 payout.getOutputParameterValue(3);
	 payout.getOutputParameterValue(4);
	 
	 Integer processHdrId = Integer.parseInt((String) payout.getOutputParameterValue(4));
	 System.out.println(processHdrId);
	 List<Integer> ids =  payoutProcessLineRepository.findByPayoutProcessHdrId(processHdrId);
	createPdfAndSendMail(ids);
	return (String) payout.getOutputParameterValue(3);
}

@Async("asyncThreadPoolTaskExecutor")
public void createPdfAndSendMail(@RequestBody List<Integer> ids) throws SmartOfficeException{
	HttpHeaders headers = new HttpHeaders();
	headers.set("Authorization",commonUtils.getLoggedinAppToken());	
	for(Integer id:ids) {
		EmployeePayout empPayouts= employeePayoutRepository.findByPayoutProcessLineId(id.toString());
		HttpEntity<EmployeePayout> requestPayout = new HttpEntity<EmployeePayout>(empPayouts, headers);
		String applicantUrlForPatch = printpayoutUrl + empPayouts.getId();
		System.out.println(applicantUrlForPatch);
		commonUtils.getRestTemplate().exchange(applicantUrlForPatch, HttpMethod.GET, requestPayout,EmployeePayout.class);
	}
}

}
