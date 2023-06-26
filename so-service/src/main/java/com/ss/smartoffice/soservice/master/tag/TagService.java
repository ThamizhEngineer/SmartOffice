package com.ss.smartoffice.soservice.master.tag;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("/master/tag")
public class TagService {

	@Autowired
	TagRepo tagRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<MTag> findAllTag() throws SmartOfficeException{
		return tagRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<MTag> findTagByName(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		return tagRepo.findById(id);	
	}
	
	@PostMapping
	public MTag createMTag(@RequestBody MTag tag)throws SmartOfficeException{
		
		MTag tagFromDB = tagRepo.findByName(tag.getName());
		if(tagFromDB==null) {
			return tagRepo.save(tag);	
		}else {
			throw new SmartOfficeException("Tag Already present");
		}				
	}
	
	
	@DeleteMapping("{id}")
	public void deleteTag(@PathVariable(value = "id") Integer id)throws SmartOfficeException{
		tagRepo.deleteById(id);
	}

	
}
