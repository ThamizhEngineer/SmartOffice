package com.ss.smartoffice.soauthservice.auth.user.type;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.interceptor.AuthUserTypeRepo;
import com.ss.smartoffice.shared.model.AuthUserType;
import com.ss.smartoffice.shared.model.SmartOfficeException;


@RestController
@RequestMapping(path = "auth/users-type")
@Component
public class AuthUserTypeService {

	@Autowired
	AuthUserTypeRepo authUserTypeRepo;
	
	@GetMapping
	public Iterable<AuthUserType> getAllAuthUserType()throws SmartOfficeException{
		return authUserTypeRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<AuthUserType> getAuthUserTypeById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		return authUserTypeRepo.findById(id);
	}
	
	@PostMapping
	public AuthUserType createAuthUserType(@RequestBody AuthUserType authUserType)throws SmartOfficeException{
		return authUserTypeRepo.save(authUserType);
	}
	
	@PatchMapping("/{id}")
	public AuthUserType updateAuthUserType(@RequestBody AuthUserType authUserType)throws SmartOfficeException{
		return authUserTypeRepo.save(authUserType);
	}
	@DeleteMapping("/{id}")
	public void deleteAuthUserTypeById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		authUserTypeRepo.deleteById(id);
	}
}
