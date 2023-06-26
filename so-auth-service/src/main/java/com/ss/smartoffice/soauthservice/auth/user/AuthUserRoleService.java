package com.ss.smartoffice.soauthservice.auth.user;
import java.time.LocalDateTime;
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

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRoleRepository;
import com.ss.smartoffice.shared.model.AuthUserRole;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "auth/users-roles")
public class AuthUserRoleService {

	@Autowired
	AuthUserRoleRepository authUserRoleRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<AuthUserRole> getAuthUserRole(@RequestParam(value="authUserId",required=false)String authUserId)throws SmartOfficeException{
		boolean searchByAuthUserId=false;
		if(authUserId!=null) {
			searchByAuthUserId=true;
		}
		if(searchByAuthUserId) {
			return authUserRoleRepository.findByAuthUserId(authUserId);
		}
		return authUserRoleRepository.findAll();
		
	}
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<AuthUserRole> getAuthUserRoleById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return authUserRoleRepository.findById(id);
		
	}
	//@CrossOrigin(origins = "*")
	@PostMapping
	public Iterable<AuthUserRole> addAuthUserRole(@RequestBody List<AuthUserRole> authUserRoles)throws SmartOfficeException{
		List<AuthUserRole> changedUserRoles = new ArrayList<AuthUserRole>();
		for (AuthUserRole ur : authUserRoles) {
			ur.setIsEnabled("Y");
			ur.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			ur.setCreatedDt(LocalDateTime.now());
			changedUserRoles.add(ur);			
		}		
		return authUserRoleRepository.saveAll(changedUserRoles);
		
	}
	
	@PostMapping("/_internal")
	public AuthUserRole addAuthUserRoleInternal(@RequestBody AuthUserRole authUserRole)throws SmartOfficeException{
		return authUserRoleRepository.save(authUserRole);
	}
	
	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public Iterable<AuthUserRole> updateAuthUserRole(@RequestBody List<AuthUserRole> authUserRole,@PathVariable(value="id")Integer id )throws SmartOfficeException{
		
		for(AuthUserRole authUserRole2:authUserRole) {
			authUserRole2.setAuthUserId(String.valueOf(id));
			authUserRole2.setAuthRoleId(authUserRole2.getAuthRoleId());
			authUserRoleRepository.save(authUserRole2);
			
		}
		return authUserRoleRepository.saveAll(authUserRole);
		
	}
	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteAuthUserRoleById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		authUserRoleRepository.deleteById(id);
	}
	
	@DeleteMapping("/{id}/_internal")
	public void deleteAuthUserRoleByIdInternal(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		authUserRoleRepository.deleteById(id);
	}
	
	@GetMapping("/{id}/delete-role/_internal")
	public String deleteAuthUserRoleByAuthId(@PathVariable(value="id")String id)throws SmartOfficeException{
		authUserRoleRepository.deleteByAuthId(id);
		return "success";
	}
	
	@CrossOrigin(origins="*")
	@PatchMapping("/multi-update")
	public Iterable<AuthUserRole> bulkAddAndUpdate(@RequestBody List<AuthUserRole> authUserRoles)throws SmartOfficeException{
		List<AuthUserRole> authUserRolesList= new ArrayList<AuthUserRole>();
		for(AuthUserRole authUserRole:authUserRoles) {
			if(authUserRole.getId()>0) {
				AuthUserRole authUserRoleFromDB= authUserRoleRepository.findById(authUserRole.getId()).orElse(new AuthUserRole());
				authUserRole=this.matchAndUpdatefields(authUserRoleFromDB,authUserRole);
			}else {
				authUserRole=addingNewRecord(authUserRole);
			}
			authUserRole.setIsEnabled("Y");
			authUserRolesList.add(authUserRole);
		}
		return (Iterable<AuthUserRole>)authUserRoleRepository.saveAll(authUserRolesList);
	}
	private AuthUserRole addingNewRecord(AuthUserRole authUserRole) {
		// TODO Auto-generated method stub
		AuthUserRole newAuthUserRole= new AuthUserRole();
		newAuthUserRole.setAuthUserId(authUserRole.getAuthUserId());
		newAuthUserRole.setAuthRoleId(authUserRole.getAuthRoleId());
		return newAuthUserRole;
	}
	private AuthUserRole matchAndUpdatefields(AuthUserRole authUserRoleFromDB, AuthUserRole authUserRole) {
		// TODO Auto-generated method stub
		
		authUserRoleFromDB.setId(authUserRole.getId());
		authUserRoleFromDB.setAuthUserId(authUserRole.getAuthUserId());
		authUserRoleFromDB.setAuthRoleId(authUserRole.getAuthRoleId());
		return authUserRoleFromDB;
	}
	

}
