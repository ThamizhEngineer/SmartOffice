package com.ss.smartoffice.soservice.seed.IncomeTaxConfig;
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


import com.ss.smartoffice.shared.model.SmartOfficeException;
@RestController
@RequestMapping("seed/it-configs")
@Scope("prototype")
public class IncomeConfigService {
	@Autowired
	IncomeTaxRepository incomeTaxRepository;
	
	
	////@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<IncomeTax> getIncomeTax(@RequestParam(value="fiscalYearId" ,required=false)String fiscalYearId)throws SmartOfficeException{
		
		boolean searchByFiscalYearId=false;
		if(fiscalYearId!=null) {
			searchByFiscalYearId=true;
		}
		if(searchByFiscalYearId) {
			return incomeTaxRepository.findByFiscalYearId(fiscalYearId);
		}
		return incomeTaxRepository.findAll();
		
	}
	////@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<IncomeTax> getIncomeTaxById(@PathVariable(value="id")int id) throws SmartOfficeException{
		return incomeTaxRepository.findById(id);
		
	}
	////@CrossOrigin(origins="*")
	@PostMapping
	public IncomeTax addIncomeTax(@RequestBody IncomeTax incomeTax)throws SmartOfficeException{
		return incomeTaxRepository.save(incomeTax);
		
	}
	
	////@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public IncomeTax updateIncomeTaxById(@RequestBody IncomeTax incomeTax)throws SmartOfficeException{
		return incomeTaxRepository.save(incomeTax);
		
	}
	////@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteIncomeTaxById(@PathVariable(value="id")int id)throws SmartOfficeException{
		incomeTaxRepository.deleteById(id);
	}
	
	////@CrossOrigin(origins="*")
	@PatchMapping("/bulk-update")
	public Iterable<IncomeTax> bulkUpdate(@RequestBody List<IncomeTax> taxList) throws SmartOfficeException{
		List<IncomeTax> incomeTaxList = new ArrayList<IncomeTax>();
		System.out.println(incomeTaxList);
		for(IncomeTax incomeTax: taxList) {
			if(incomeTax.getId()>0) {
				IncomeTax incomeTaxFromDB= incomeTaxRepository.findById(incomeTax.getId()).orElse(new IncomeTax());
				System.out.println(incomeTaxFromDB);
				incomeTax = this.matchAndRelavantFields(incomeTaxFromDB,incomeTax);
			}else {
				incomeTax= identifyRelavantFields(incomeTax);
			}
			incomeTax.setIsEnabled("Y");
			incomeTaxList.add(incomeTax);
		}
		return (Iterable<IncomeTax>) incomeTaxRepository.saveAll(incomeTaxList) ;
		
	}
	private IncomeTax identifyRelavantFields(IncomeTax incomeTax) {
		// TODO Auto-generated method stub
	 IncomeTax newIncomeTax = new IncomeTax();
	 newIncomeTax.setAgeCode(incomeTax.getAgeCode());
	 newIncomeTax.setFromAmt(incomeTax.getFromAmt());
	 newIncomeTax.setToAmt(incomeTax.getToAmt());
     newIncomeTax.setTaxPercent(incomeTax.getTaxPercent());
     newIncomeTax.setEduCessPercent(incomeTax.getEduCessPercent());
     newIncomeTax.setSplEduCessPercent(incomeTax.getSplEduCessPercent());
     
	 return newIncomeTax;
	}
	private IncomeTax matchAndRelavantFields(IncomeTax incomeTaxFromDB, IncomeTax incomeTax) {
		// TODO Auto-generated method stub
		incomeTaxFromDB.setId(incomeTax.getId());
		incomeTaxFromDB.setAgeCode(incomeTax.getAgeCode());
		incomeTaxFromDB.setFromAmt(incomeTax.getFromAmt());
		incomeTaxFromDB.setToAmt(incomeTax.getToAmt());
		incomeTaxFromDB.setTaxPercent(incomeTax.getTaxPercent());
		incomeTaxFromDB.setEduCessPercent(incomeTax.getEduCessPercent());
		incomeTaxFromDB.setSplEduCessPercent(incomeTax.getSplEduCessPercent());
		return incomeTaxFromDB;
	}

}
