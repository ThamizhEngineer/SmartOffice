package com.ss.smartoffice.soservice.master.abilityservice;

import java.time.LocalDateTime;
import java.util.HashMap;
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

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;

@RestController
@RequestMapping("master/abilities")
public class AbilityService {
@Autowired
AbilityRepo abilityRepo;

@Autowired
SequenceGenerationService sequenceGenerationService;

@Autowired
CommonUtils commonUtils;

@GetMapping
public Iterable<Ability>getAllAbility()throws SmartOfficeException{
	return abilityRepo.findAll();
}

@GetMapping("/{id}")
public Optional<Ability>getAbilityById(@PathVariable(value="id")int id)throws SmartOfficeException{
	return abilityRepo.findById(id);	
}

@PostMapping
public Ability addAbilityById(@RequestBody Ability ability) throws SmartOfficeException {
	HashMap<String, String> buisnessKeys = new HashMap<>();
	ability.setServiceCode(sequenceGenerationService.nextSeq("SERVICE",buisnessKeys));
	ability.setCreatedBy(commonUtils.getLoggedinEmployeeId());
	ability.setCreatedDt(LocalDateTime.now());	
	return abilityRepo.save(ability);
}

@PatchMapping("/{id}")
public Ability updateAbilityById(@RequestBody Ability ability) throws SmartOfficeException {
	ability.setModifiedBy(commonUtils.getLoggedinEmployeeId());
	ability.setModifiedDt(LocalDateTime.now());
	return abilityRepo.save(ability);
}

@DeleteMapping(value="/{id}")
public void deleteAbility(@PathVariable(value="id")Integer id)throws SmartOfficeException {
	abilityRepo.deleteById(id);
}

}
