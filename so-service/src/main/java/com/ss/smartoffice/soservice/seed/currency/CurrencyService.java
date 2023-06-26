package com.ss.smartoffice.soservice.seed.currency;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.currency.Currency;
import com.ss.smartoffice.shared.model.currency.CurrencyRepo;

@RestController
@RequestMapping("seed/currency")
public class CurrencyService {

	@Autowired
	CurrencyRepo currencyRepo;
	
	
	@GetMapping
	public Iterable<Currency> findAllCurrencys()throws SmartOfficeException{
		return currencyRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Currency> findCurrencyById(@PathVariable(value = "id")String id)throws SmartOfficeException{
		return currencyRepo.findById(id);
	}
	
	@PostMapping
	public Currency createCurrency(@RequestBody Currency currency)throws SmartOfficeException{
		return currencyRepo.save(currency);
	}
	
	@PatchMapping("/update")
	public Currency updateCurrency(@RequestBody Currency currency)throws SmartOfficeException{
		return currencyRepo.save(currency);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCurrencyById(@PathVariable(value = "id")String id)throws SmartOfficeException{
		currencyRepo.deleteById(id);
	}
}
