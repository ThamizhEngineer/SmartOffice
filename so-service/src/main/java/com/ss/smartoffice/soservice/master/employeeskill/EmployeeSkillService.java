package com.ss.smartoffice.soservice.master.employeeskill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
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
import com.ss.smartoffice.shared.model.employee.EmployeeSkill;


@RestController
@RequestMapping("master/memployee-skills")
@Scope("prototype")
public class EmployeeSkillService {
	
	@Autowired
	EmployeeSkillRepo EmployeeSkillRepo;
	
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<EmployeeSkill> getEmployeeSkill() throws SmartOfficeException {
		return 	EmployeeSkillRepo.findAll();

	}
	
	@GetMapping("/emp-with-skills")
	public List<Integer> fetchExistingEmpsWithSkills() throws SmartOfficeException {
		return 	EmployeeSkillRepo.fetchExistingEmpWithSkills();
	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/")
	public Iterable<EmployeeSkill> getEmployeeSkill(Model model, Pageable pageable,
			@RequestParam(value = "id",required = false) String id,
			@RequestParam(value = "skillLevelCode", required = false) String skillLevelCode)
			throws Exception {
		
		Page<EmployeeSkill> pages = employeeskills(pageable,id,skillLevelCode);
	       model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("bundledetails", pages.getContent());
	        return pages;
	}
	

	public Page<EmployeeSkill> employeeskills(Pageable pageable,String id,String skillLevelCode){
		boolean searchById = false, searchBySkillLevelCode = false,searchByIdAndSkillLevelCode=false;
		
		if (id != null)
			searchById = true;
		if (id != null && !skillLevelCode.isEmpty() )
				
			searchBySkillLevelCode = true;
	
		if(id!=null&&!skillLevelCode.isEmpty()&&skillLevelCode!=null&&!skillLevelCode.isEmpty()) {
			searchByIdAndSkillLevelCode=true;	
		}
		if (searchBySkillLevelCode && searchById) {
			return EmployeeSkillRepo.findByIdAndSkillLevelCode(pageable, skillLevelCode, id);
		}  if (searchById) {
			return EmployeeSkillRepo.findById(pageable, id);
		} if (searchBySkillLevelCode) {
			return EmployeeSkillRepo.findBySkillLevelCode(pageable, skillLevelCode);
		}
        return EmployeeSkillRepo.findAll(pageable);
    }


	//@CrossOrigin(origins = "*")
	@PostMapping
	public EmployeeSkill addEmployeeSkill(@RequestBody EmployeeSkill EmployeeSkill) throws SmartOfficeException {
		return 	EmployeeSkillRepo.save(EmployeeSkill); 
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public EmployeeSkill updateEmployeeSkill(@RequestBody EmployeeSkill EmployeeSkill) throws SmartOfficeException {
		return 	EmployeeSkillRepo.save(EmployeeSkill);

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteEmployeeSkill(@PathVariable(value = "id") int id) throws SmartOfficeException {
		EmployeeSkillRepo.deleteById(id);

	}




}
