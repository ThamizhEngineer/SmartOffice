package com.ss.smartoffice.soauthservice.auth.menu;
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
import com.ss.smartoffice.soauthservice.model.auth.AuthSubMenu;


@RestController
@RequestMapping(path ="auth/sub-menu-items")
public class AuthSubMenuFeatureService {
	@Autowired
	AuthSubMenuFeatureRepository authSubMenuFeatureRepository;
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<AuthSubMenu> getAuthSubMenu()throws SmartOfficeException{
		return authSubMenuFeatureRepository.findAll();
		
	}
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	
	public Optional<AuthSubMenu> getAuthSubMenuById(@PathVariable(value="id")String id)throws SmartOfficeException{
		return authSubMenuFeatureRepository.findById(id);
		
	}
	//@CrossOrigin(origins = "*")
	@PostMapping
	
	public AuthSubMenu addAuthSubMenu(@RequestBody AuthSubMenu authSubMenu)throws SmartOfficeException{
		return authSubMenuFeatureRepository.save(authSubMenu);
		
	}
	
	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	
	public AuthSubMenu updateAuthSubMenu(@RequestBody AuthSubMenu authSubMenu)throws SmartOfficeException{
		return authSubMenuFeatureRepository.save(authSubMenu);
		
	}
	
	@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteAuthSubMenu(@PathVariable(value="id")String id)throws SmartOfficeException{
		authSubMenuFeatureRepository.deleteById(id);
	}

}
