package com.ss.smartoffice.soauthservice.auth.role;
import java.util.ArrayList;
import java.util.List;
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

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soauthservice.model.auth.AuthRoleFeature;


@RestController
@RequestMapping(path ="auth/roles-features")
public class AuthRoleFeatureService {
	@Autowired
	AuthRoleFeatureRepository authRoleFeatureRepository;
	
	//@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<AuthRoleFeature> getAuthRoleFeatures(@RequestParam(value = "roleId",required = false)Integer roleId,@RequestParam(value = "featureId",required = false)String featureId)throws SmartOfficeException{
		if(roleId!=null && featureId!=null) {
			return authRoleFeatureRepository.findByAuthRoleIdAndAuthFeatureId(roleId, featureId);	
		}else  if(roleId!=null){
			return authRoleFeatureRepository.findByAuthRoleId(roleId);	
		}else if(featureId!=null) {
			return authRoleFeatureRepository.findByAuthFeatureId(featureId);	
		}else{
			return authRoleFeatureRepository.findAll();	
		}
			
	}

	//@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<AuthRoleFeature> getAuthRoleFeatureById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return authRoleFeatureRepository.findById(id);
	}
	
	@GetMapping("/by-authrole-id/{authRoleId}/_internal")
	public List<AuthRoleFeature> getByAuthRoleId(@PathVariable(value="authRoleId")Integer authRoleId)throws SmartOfficeException{
		return authRoleFeatureRepository.findByAuthRoleId(authRoleId);
		
	}
	
	//@CrossOrigin(origins="*")
	@PostMapping
	
	public AuthRoleFeature addAuthRoleFeature(@RequestBody AuthRoleFeature authRoleFeature)throws SmartOfficeException{
		return authRoleFeatureRepository.save(authRoleFeature);
		
	}
	
	//@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public AuthRoleFeature updateAuthRoleFeature(@RequestBody AuthRoleFeature authRoleFeature) throws SmartOfficeException{
		return authRoleFeatureRepository.save(authRoleFeature);
		
	}
	//@CrossOrigin(origins="*")
	@PatchMapping("/multi-update")
	
	public Iterable<AuthRoleFeature> bulkAndUpdate(@RequestBody List<AuthRoleFeature> authRoleFeatures)throws SmartOfficeException{
	
		List<AuthRoleFeature> authRoleFeaturesList= new ArrayList<>();
		for(AuthRoleFeature authRoleFeature:authRoleFeatures) {
			if(authRoleFeature.getId()>0) {
				AuthRoleFeature authRoleFeatureFromDB= authRoleFeatureRepository.findById(authRoleFeature.getId()).orElse(new AuthRoleFeature());
				authRoleFeatureFromDB=this.matchAndUpdateFields(authRoleFeatureFromDB,authRoleFeature);
			}else {
				authRoleFeature=addingNewRecord(authRoleFeature);
			}
			authRoleFeature.setIsEnabled("Y");
			authRoleFeaturesList.add(authRoleFeature);
		}
		
		return (Iterable<AuthRoleFeature>)authRoleFeatureRepository.saveAll(authRoleFeaturesList);
		
	}

	private AuthRoleFeature addingNewRecord(AuthRoleFeature authRoleFeature) {
		// TODO Auto-generated method stub
		AuthRoleFeature newauthRoleFeature = new AuthRoleFeature();
		newauthRoleFeature.setAuthFeatureId(authRoleFeature.getAuthFeatureId());
		newauthRoleFeature.setAuthRoleId(authRoleFeature.getAuthRoleId());
		
		return newauthRoleFeature;
	}

	private AuthRoleFeature matchAndUpdateFields(AuthRoleFeature authRoleFeatureFromDB,
			AuthRoleFeature authRoleFeature) {
		// TODO Auto-generated method stub
		authRoleFeatureFromDB.setId(authRoleFeature.getId());
		authRoleFeatureFromDB.setAuthFeatureId(authRoleFeature.getAuthFeatureId());
		authRoleFeatureFromDB.setAuthRoleId(authRoleFeature.getAuthRoleId());
		
		return authRoleFeatureFromDB;
	}
	
	
	//@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteAuthRoleFeature(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		authRoleFeatureRepository.deleteById(id);
	}
	
}
