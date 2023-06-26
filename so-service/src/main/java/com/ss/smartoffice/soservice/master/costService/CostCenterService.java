package com.ss.smartoffice.soservice.master.costService;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;




@Service
@RestController
@RequestMapping("/master/cost-centers")
@Scope("prototype")
public class CostCenterService {
	
	@Autowired
	CostCenterRepository costCenterRepository;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	
	
	@GetMapping
	public Iterable<CostCenter> getCostCenters(Model model, Pageable pageable,
			@RequestParam(value = "deptId",required = false) Integer deptId) throws SmartOfficeException{
		
		Page<CostCenter> pages= costcenters(pageable,deptId);
		model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("materials", pages.getContent());
		return pages;
	}
	
	public Page<CostCenter> costcenters(Pageable pageable,Integer deptId){
		boolean searchByDeptId = false;
		if(deptId !=null)
			searchByDeptId =true;
		
		if(searchByDeptId) {
			return costCenterRepository.findByDeptId(pageable, deptId);
		}
		return costCenterRepository.findAll(pageable);
	}
	
	@PostMapping
	public CostCenter createCostCenter(@RequestBody CostCenter costCenter )throws SmartOfficeException {
	costCenter.setCcCode(sequenceGenerationService.nextSequence("COST-CENTER").getOutput());
	costCenter.setCreatedBy(commonUtils.getLoggedinEmployeeId());
	return costCenterRepository.save(costCenter);
	}

	@PatchMapping("/{id}")
	public CostCenter updateCostCenter(@RequestBody CostCenter costCenter) throws SmartOfficeException{
		costCenter.setModifiedBy(commonUtils.getLoggedinEmployeeId());
	return costCenterRepository.save(costCenter);
	}

	
	@GetMapping("/{id}")
	public Optional<CostCenter> getCostCenterById(@PathVariable(value="id")int id) throws SmartOfficeException{
		return costCenterRepository.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCostCenterById(@PathVariable(value="id")int id)throws SmartOfficeException {
		costCenterRepository.deleteById(id);
	}
	
}
