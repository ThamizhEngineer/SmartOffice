package com.ss.smartoffice.soauthservice.auth.role;
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
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soauthservice.model.auth.AuthRole;


@RestController
@RequestMapping(path ="auth/roles")
public class AuthRoleService {
	@Autowired
	AuthRoleRepository authRoleRepository;
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<AuthRole> getAuthRole()throws SmartOfficeException{
		return authRoleRepository.findAll();
		
	}
	
	@GetMapping("/code/{roleCode}")
	public Iterable<AuthRole> getAuthRoleByCode(@PathVariable(value="roleCode")String roleCode)throws SmartOfficeException{
		return authRoleRepository.findByRoleCode(roleCode);
		
	}
	
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
    public Optional<AuthRole> getAuthRoleById(@PathVariable(value="id")String id)throws SmartOfficeException{
		return authRoleRepository.findById(id);
		
	}
	//@CrossOrigin(origins = "*")
	@PostMapping
	public AuthRole addAuthRole(@RequestBody AuthRole authRole)throws SmartOfficeException{
		return authRoleRepository.save(authRole);
		
	}
//	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	
	public AuthRole updateAuthRole(@RequestBody AuthRole authRole)throws SmartOfficeException{
		return authRoleRepository.save(authRole);
		
	}
	
//	@CrossOrigin(origins="*")
	@PatchMapping("/multi-update")
	
	public Iterable<AuthRole> bulkAndUpdate(@RequestBody List<AuthRole> authRoleList)throws SmartOfficeException{
		return (Iterable<AuthRole>) authRoleRepository.saveAll(authRoleList);
		
		
	}
//	@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	
	public void deleteAuthRoleById(@PathVariable(value="id")String id) throws SmartOfficeException{
		authRoleRepository.deleteById(id);
	}
	
	
	

}
