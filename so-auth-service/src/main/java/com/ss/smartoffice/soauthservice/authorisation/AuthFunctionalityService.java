package com.ss.smartoffice.soauthservice.authorisation;

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
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.authorisation.functionality.AuthFunctionality;
import com.ss.smartoffice.shared.authorisation.functionality.AuthFunctionalityRepository;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path ="auth/functionality")
public class AuthFunctionalityService {
	@Autowired
	AuthFunctionalityRepository authFunctionalityRepository;

	
	//@CrossOrigin(origins = "*")
		@GetMapping
		public Iterable<AuthFunctionality> getAuthfun()throws SmartOfficeException{
			return authFunctionalityRepository.findAll();
			
		}
		
		//@CrossOrigin(origins = "*")
		@GetMapping("/{id}")
		
		public Optional<AuthFunctionality> getAuthfunById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
			return authFunctionalityRepository.findById(id);
			
		}
		
		//@CrossOrigin(origins = "*")
		@PostMapping
		
		public AuthFunctionality addAuthfun(@RequestBody AuthFunctionality authFunctionality)throws SmartOfficeException{
			return authFunctionalityRepository.save(authFunctionality);
			
		}
		
		//@CrossOrigin(origins = "*")
		@PatchMapping("/{id}")
		
		public AuthFunctionality updateAuthfun(@RequestBody AuthFunctionality authFunctionality)throws SmartOfficeException{
			return authFunctionalityRepository.save(authFunctionality);
			
		}
		
		@CrossOrigin(origins="*")
		@DeleteMapping("/{id}")
		public void deleteAuthfun(@PathVariable(value="id")Integer id)throws SmartOfficeException{
			authFunctionalityRepository.deleteById(id);
		}
}
