package com.ss.smartoffice.soservice.master.employeeprofile;

import java.util.HashMap;
import java.util.Optional;

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
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.shared.common.CommonUtils;

@RestController
@RequestMapping("master/profiles")
@Scope("prototype")
public class ProfileService {
	
	@Autowired
	ProfileRepo profileRepo;
	
	@Autowired
	ProfileLineRepo ProfileLineRepo;

	@Autowired
	CommonUtils commonUtils;
	

	@Autowired
	SequenceGenerationService sequenceGenerationService;

	
	//@CrossOrigin(origins = "*")
	
	@GetMapping
	public Iterable<Profile> getProfile() throws SmartOfficeException {
		return profileRepo.findAll();
	}

	
	@GetMapping("/")
	public Iterable<Profile> geProfile(Model model, Pageable pageable,
			@RequestParam(value = "id",required = false) String id,
			@RequestParam(value = "profileCode", required = false) String profileCode)
			throws Exception {
		
		Page<Profile> pages = profiledetails(pageable,id,profileCode);
	       model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("profiledetails", pages.getContent());
	        return pages;
	}
	

	public Page<Profile> profiledetails(Pageable pageable,String id,String profileCode){
		boolean searchById = false, searchByProfileCode = false,searchByIdAndProfileCode=false;
		
		if (id != null)
			searchById = true;
		if (profileCode != null && !profileCode.isEmpty() )
				
			searchByProfileCode = true;
	
		if(id!=null&&!id.isEmpty()&&profileCode!=null&&!profileCode.isEmpty()) {
			searchByIdAndProfileCode=true;	
		}
		if (searchByProfileCode && searchById) {
			return profileRepo.findByIdAndProfileCode(pageable, profileCode, id);
		}  if (searchById) {
			return profileRepo.findById(pageable, id);
		} if (searchByProfileCode) {
			return profileRepo.findByProfileCode(pageable, profileCode);
		}
        return profileRepo.findAll(pageable);
    }
	

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
public Optional<Profile>getProfileById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		
		return profileRepo.findById(id);	
	}
	
	//@CrossOrigin(origins = "*")
	@PostMapping
	public Profile addProfile(@RequestBody Profile profile) throws SmartOfficeException {
		HashMap<String, String> buisnessKeys = new HashMap<>();
		profile.setProfileCode(sequenceGenerationService.nextSeq("PROFILE",buisnessKeys ));
//		profile.setProfileCode(sequenceGenerationService.nextSequence("PROFILE").getOutput());
		profile.setCreatedBy(commonUtils.getAuthenticatedUser().getName());
		return profileRepo.save(profile); 
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public Profile updateProfile(@RequestBody Profile profile) throws SmartOfficeException {
		profile.setModifiedBy(commonUtils.getAuthenticatedUser().getName());
		return profileRepo.save(profile);

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteProfile(@PathVariable(value = "id") int id) throws SmartOfficeException {
		profileRepo.deleteById(id);

	}

}
