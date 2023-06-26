package com.ss.smartoffice.shared.model.eventrules;

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

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path="seed/event-rules")
@Scope("prototype")
public class EventRuleService {
	
	@Autowired
	EventRuleRepository eventRuleRepository;
	
	@GetMapping
	public Iterable<EventRule> getAllEventRules()throws SmartOfficeException{
		return eventRuleRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<EventRule> getEventRuleById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return eventRuleRepository.findById(id);
	}
	
	@PostMapping
	public EventRule addEventRule(@RequestBody EventRule eventRules)throws SmartOfficeException{
		return eventRuleRepository.save(eventRules);	
	}
	
	@PatchMapping("/{id}/update")
	public EventRule updateEventRule(@RequestBody EventRule eventRules)throws SmartOfficeException{
		return eventRuleRepository.save(eventRules);
	}
	
	@DeleteMapping("/{id}/delete")
	public void deleteEventRule(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		eventRuleRepository.deleteById(id);
	}

}
