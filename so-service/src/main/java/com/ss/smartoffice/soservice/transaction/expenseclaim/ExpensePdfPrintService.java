package com.ss.smartoffice.soservice.transaction.expenseclaim;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@Service
@RestController
@RequestMapping("/transaction/expense")
public class ExpensePdfPrintService {
	
	@Autowired
	ExpenseClaimRepository expenseClaimRepository;

	
	
	@GetMapping("/{id}")
	public Optional<ExpenseClaim>getExpClaimById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		return expenseClaimRepository.findById(id);
	}
	
	@PostMapping("/{id}/exp-pdf")
    public ExpenseClaim updateApplicantByPdfId(@RequestBody ExpenseClaim expenseClaim) throws SmartOfficeException {
		ExpenseClaim expenseFromDb =getExpClaimById(expenseClaim.getId()).get();
		expenseFromDb.setExpPdfId(expenseClaim.getExpPdfId());
		return expenseClaimRepository.save(expenseFromDb);
		
	}
}
