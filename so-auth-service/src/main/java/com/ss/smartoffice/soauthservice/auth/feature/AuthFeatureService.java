package com.ss.smartoffice.soauthservice.auth.feature;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soauthservice.model.auth.AuthFeature;


@RestController
@RequestMapping(path ="auth/features")
public class AuthFeatureService {
	@Autowired
	AuthFeatureRepository authFeatureRepository;
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<AuthFeature> getAuthFeature()throws SmartOfficeException{
		return authFeatureRepository.findAll();
		
	}
	
	

	@GetMapping("/{id}")
	public Optional<AuthFeature> getAuthFeatureById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return authFeatureRepository.findById(id);
		
	}
	
	@GetMapping("/{id}/_internal")
	public List<AuthFeature> getAuthFeatureByIdInternal(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return authFeatureRepository.getById(id);
		
	}
	
	

	@PostMapping
	public AuthFeature addAuthFeature(@RequestBody AuthFeature authFeature)throws SmartOfficeException{
		return authFeatureRepository.save(authFeature);
		
	}
	

	@PatchMapping("/{id}")
	
	public AuthFeature updateAuthFeature(@RequestBody AuthFeature authFeature)throws SmartOfficeException{
		return authFeatureRepository.save(authFeature);
		
	}

	@DeleteMapping("/{id}")
	
	public void deleteAuthFeature(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		authFeatureRepository.deleteById(id);
	}

	@PatchMapping("/multi-update")
	
	public Iterable<AuthFeature> bulkAndUpdate(@RequestBody List<AuthFeature> authFeatures)throws SmartOfficeException{
		List<AuthFeature> authFeaturesList= new ArrayList<AuthFeature>();
		for(AuthFeature authLoop: authFeatures) {
			if(authLoop.getId()!=null) {
				AuthFeature authFeatureFromDB= authFeatureRepository.findById(authLoop.getId()).orElse(new AuthFeature());
				authLoop= this.matchAndUpdateFields(authFeatureFromDB,authLoop);
			}else {
				authLoop= addingNewRecord(authLoop);
			}
			authLoop.setIsEnabled("Y");
			authFeaturesList.add(authLoop);
			
		}
		
		return (Iterable<AuthFeature>)authFeatureRepository.saveAll(authFeaturesList);
		
	}


	private AuthFeature addingNewRecord(AuthFeature authLoop) {
		// TODO Auto-generated method stub
		AuthFeature authFeature = new AuthFeature();
		authFeature.setFunctionalityCode(authLoop.getFunctionalityCode());
		authFeature.setFeatureCode(authLoop.getFeatureCode());
		authFeature.setDescription(authLoop.getDescription());
		return authFeature;
	}

	private AuthFeature matchAndUpdateFields(AuthFeature authFeatureFromDB, AuthFeature authLoop) {
		// TODO Auto-generated method stub
		
		authFeatureFromDB.setId(authLoop.getId());
		authFeatureFromDB.setFunctionalityCode(authLoop.getFunctionalityCode());
		authFeatureFromDB.setFeatureCode(authLoop.getFeatureCode());
		authFeatureFromDB.setDescription(authLoop.getDescription());
		
		return authFeatureFromDB;
	}

}
