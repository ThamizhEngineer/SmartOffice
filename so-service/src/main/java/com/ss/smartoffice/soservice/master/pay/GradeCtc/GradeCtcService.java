package com.ss.smartoffice.soservice.master.pay.GradeCtc;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ss.smartoffice.soservice.common.EvaluateString;
import com.ss.smartoffice.shared.model.SmartOfficeException;
@RestController
@RequestMapping("master/pay/grade-ctcs")
public class GradeCtcService {
	@Autowired
	GradeCtcRepository gradeCtcRepository;
	@Autowired
	GradeCtcLinesRepository gradeCtcLinesRepository;
	
	@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<GradeCtc> getGradeCtc(@RequestParam(value="gradeId",required=false)String gradeId)throws SmartOfficeException{
		boolean searchByGradeId=false;
		
		
		if(gradeId!=null)
			searchByGradeId=true;
		if(searchByGradeId) {
			return gradeCtcRepository.findByGradeId(gradeId);
		}
		return gradeCtcRepository.findAll();
		
	}
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<GradeCtc> getGradeCtcById(@PathVariable(value="id")int id )throws SmartOfficeException{
		return gradeCtcRepository.findById(id);
		
	}
	@CrossOrigin(origins="*")
	@PostMapping
	public GradeCtc addGradeCtc(@RequestBody GradeCtc gradeCtc)throws SmartOfficeException{
		return gradeCtcRepository.save(gradeCtc);
		
	}
	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public GradeCtc updateGradeCtc(@RequestBody GradeCtc gradeCtc)throws SmartOfficeException{
		return gradeCtcRepository.save(gradeCtc);
		
	}
	@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteGradeCtc(@PathVariable(value="id")int id)throws SmartOfficeException{
		gradeCtcRepository.deleteById(id);
	}
	@CrossOrigin(origins="*")
	@PostMapping("/{id}/lines")
	public GradeCtc addGradeCtcLinesById(@RequestBody GradeCtc gradeCtc)throws SmartOfficeException{
	
	if(gradeCtc.getGradeCtcLines()!=null) {
	for(GradeCtcLine GradeCtcLine:gradeCtc.getGradeCtcLines()) {
		GradeCtcLine= calculateFormula(GradeCtcLine,"fb");
		gradeCtcLinesRepository.save(GradeCtcLine);
	}

}else {
	throw new SmartOfficeException("GradeCtcLine Is Empty");
}
	return gradeCtc;
	}



	public GradeCtcLine calculateFormula(GradeCtcLine gradeCtcLine ,String fixedBenefits)throws SmartOfficeException{
		
		if(gradeCtcLine.getHead()!=null && gradeCtcLine.getPayCycleId()!=null) {
		if(gradeCtcLine.getIsLumpSum().equals("N") && gradeCtcLine.getFormula()!=null) { 

//			double TotalCalculatedAmt;
			//PlaceHolder Replacement
			//FIXME - remove this hardcoding
			 fixedBenefits= "25000";
			 
			 
		//execute Formula
      String formula = (gradeCtcLine.getFormula().replace("[FB]", fixedBenefits));
			 
			System.out.println(formula);
				double result=EvaluateString.eval(formula);
				System.out.println(result);
				String finalres=  new Double(result).toString();
				
				// set result to calculated amount
        		gradeCtcLine.setCalculatedAmt(finalres);

//				
		}else if (gradeCtcLine.getIsLumpSum().equals("Y")) {
			double  results= 300.0;

			String finalresult = new Double(results).toString();
			gradeCtcLine.setCalculatedAmt(finalresult);

			
			
		}
		
		
	}else {
		throw new SmartOfficeException("Head value And PayCycleId Should be Present");
	}
		return gradeCtcLine;
	}



	
}













