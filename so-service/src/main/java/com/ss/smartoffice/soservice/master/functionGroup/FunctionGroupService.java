package com.ss.smartoffice.soservice.master.functionGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;

@RestController
@RequestMapping(path="master/function-group")
@Scope("prototype")
public class FunctionGroupService {

	@Autowired
	FunctionGroupRepo functionGroupRepo;
	
	@Autowired
	FunctionRepo functionRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@GetMapping
	public Iterable<FunctionGroup> getAllFunctionGrp() throws SmartOfficeException{
		return functionGroupRepo.findAll();
	}
	@GetMapping("/{id}")
	public FunctionGroup getFunctionGroup(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		FunctionGroup funGrp = functionGroupRepo.findById(id).get();
		for(Function fun : funGrp.getFunctions()) {
			if(fun.getDeliveryType().equals("goods")) {
				fun.setDivisionLines(functionRepo.divisionGoods(fun.getId().toString()));	
			}else if(fun.getDeliveryType().equals("service")) {
				fun.setDivisionLines(functionRepo.divisionService(fun.getId().toString()));	
			}			
		}
		return funGrp;
	}
	
	@PostMapping
	public FunctionGroup createFunctionGroup(@RequestBody FunctionGroup functionGrp) throws SmartOfficeException{
		HashMap<String, String> buisnessKeys = new HashMap<>();
		functionGrp.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		functionGrp.setFunGrpCode(sequenceGenerationService.nextSeq("FUNCTION-GROUP",buisnessKeys));
		functionGrp.setCreatedDt(LocalDateTime.now());
		
		for(Function fun:functionGrp.getFunctions()) {
			HashMap<String, String> buisnessKeys2 = new HashMap<>();			
			fun.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			fun.setCreatedDt(LocalDateTime.now());
			fun.setFunCode(sequenceGenerationService.nextSeq("FUNCTION",buisnessKeys2));
		}
		
		
		return functionGroupRepo.save(functionGrp);
	}
	@PatchMapping("/{id}")
	public FunctionGroup updateFunctionGroup(@RequestBody FunctionGroup functionGroup) throws SmartOfficeException{
		functionGroup.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		functionGroup.setModifiedDt(LocalDateTime.now());
		for(Function fun:functionGroup.getFunctions()) {
			HashMap<String, String> buisnessKeys2 = new HashMap<>();
			if(fun.getId()==null) {
				fun.setFunCode(sequenceGenerationService.nextSeq("FUNCTION",buisnessKeys2));
				fun.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				fun.setCreatedDt(LocalDateTime.now());
			}else {
				fun.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				fun.setModifiedDt(LocalDateTime.now());
			}						
		}
		return functionGroupRepo.save(functionGroup);
	}
	
	@GetMapping("/deliver-type/{type}")
	public List<Function> findByDeliverType(@PathVariable(value = "type")String type) throws SmartOfficeException{
		return functionRepo.findByDeliveryType(type);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteFunctionGroup(@PathVariable(value="id")Integer id)throws SmartOfficeException {
		functionGroupRepo.deleteById(id);
	}
}
