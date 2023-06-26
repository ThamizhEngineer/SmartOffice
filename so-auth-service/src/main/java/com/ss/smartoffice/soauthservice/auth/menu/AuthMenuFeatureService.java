package com.ss.smartoffice.soauthservice.auth.menu;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ss.smartoffice.soauthservice.model.auth.AuthMenu;


@RestController
@RequestMapping(path ="auth/menu-items")
public class AuthMenuFeatureService {
	
	@Autowired
	AuthMenuFeatureRepository authMenuFeatureRepository;

	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<AuthMenu> getAuthMenu(@RequestParam(value="userId",required=false)String userId,@RequestParam(value="featureId",required=false)String featureId)throws SmartOfficeException{
		boolean searchByUserId=false, searchByUserIdAndFeatureId=false;
		if(userId!=null) {
			searchByUserId=true;
		}
		if(userId!=null&& featureId!=null) {
			searchByUserIdAndFeatureId=true;
		}
		if(searchByUserId) {
			return authMenuFeatureRepository.findByUserId(userId);
		}else if (searchByUserIdAndFeatureId) {
			return authMenuFeatureRepository.findByUserIdAndFeatureId(userId, featureId);
}
		return authMenuFeatureRepository.findAll();
		
	}
	
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<AuthMenu> getAuthMenuById(@PathVariable(value="id")String id)throws SmartOfficeException{
		return authMenuFeatureRepository.findById(id);
		
	}
	//@CrossOrigin(origins = "*")
	@PostMapping
	
	public AuthMenu addAuthMenu(@RequestBody AuthMenu authMenu)throws SmartOfficeException{
		return authMenuFeatureRepository.save(authMenu);
		
	}
	
	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	
	public AuthMenu updateAuthMenu(@RequestBody AuthMenu authMenu)throws SmartOfficeException{
		return authMenuFeatureRepository.save(authMenu);
		
	}
//	@CrossOrigin(origins = "*")
	@PatchMapping("/multi-update")
	
	public Iterable<AuthMenu> bulkAddAndUpdate(@RequestBody List<AuthMenu>authMenus)throws SmartOfficeException{
		List<AuthMenu> authMenuList = new ArrayList<AuthMenu>();
		for(AuthMenu authMenu:authMenus) {
			if(authMenu.getId()==null) {
				AuthMenu authMenufromDB= authMenuFeatureRepository.findById(authMenu.getId()).orElse(new AuthMenu());
				authMenu=this.matchAndUpdateFields(authMenufromDB,authMenu);
				
			}else {
				authMenu=addingNewRecord(authMenu);
			}
			authMenu.setIsEnabled("Y");
			authMenuList.add(authMenu);
		}
		
		
		return (Iterable<AuthMenu>)authMenuFeatureRepository.saveAll(authMenuList);
		
	}

	private AuthMenu addingNewRecord(AuthMenu authMenu) {
		// TODO Auto-generated method stub
		AuthMenu newAuthMenu= new AuthMenu();
		newAuthMenu.setName(authMenu.getName());
		newAuthMenu.setState(authMenu.getState());
		newAuthMenu.setType(authMenu.getType());
		newAuthMenu.setIcon(authMenu.getIcon());
		
		return newAuthMenu;
	}

	private AuthMenu matchAndUpdateFields(AuthMenu authMenufromDB, AuthMenu authMenu) {
		// TODO Auto-generated method stub
		
		authMenufromDB.setId(authMenu.getId());
		authMenufromDB.setName(authMenu.getName());
		authMenufromDB.setState(authMenu.getState());
		authMenufromDB.setType(authMenu.getType());
		authMenufromDB.setIcon(authMenu.getIcon());
		return authMenufromDB;
	}
	
	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	
	public void deleteAuthMenuById(@PathVariable(value="id")String id)throws SmartOfficeException{
		authMenuFeatureRepository.deleteById(id);
	}
	

}
