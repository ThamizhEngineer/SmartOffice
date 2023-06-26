
package com.ss.smartoffice.soauthservice.auth.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.interceptor.TokenService;
import com.ss.smartoffice.shared.model.AuthApplicant;
import com.ss.smartoffice.shared.model.AuthClient;
import com.ss.smartoffice.shared.model.AuthEmployee;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthVendor;
import com.ss.smartoffice.shared.repos.AuthTokenRepo;
import com.ss.smartoffice.soauthservice.model.master.Employee;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "auth/users")
@Component
public class AuthUserService {

	@Autowired
	AuthUserRepository authUserRepository;
	
	@Autowired
	AuthTokenRepo authTokenRepo;
	
	@Autowired
	TokenService tokenService;
	
	
	

	@Autowired
	private CommonUtils commonUtils;
	
	@Value("${employee.url}")
	private String employeeUrl;
	

	@CrossOrigin(origins="*")
	@GetMapping	
	public Iterable<AuthUser> getAuthUsers() throws SmartOfficeException{				
	return authUserRepository.findAll();		
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/auth-user-employee")
	public Iterable<AuthUser> getAuthUsersOfEmployee() throws SmartOfficeException{				
	return authUserRepository.findBySummary();		
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/_internal")
	public Iterable<AuthUser> getAuthUsersInternal()throws SmartOfficeException{
		return getAuthUsers();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/auth-employee")
	public List<AuthEmployee> getAuthEmployees()throws SmartOfficeException{
		return authUserRepository.fetchAllEmployeeUsers();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/auth-applicant")
	public List<AuthApplicant> getAuthApplicants()throws SmartOfficeException{
		return authUserRepository.fetchAllApplicantUsers();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/auth-client")
	public List<AuthClient> getAuthClients(@RequestParam(value = "partnerID",required=false)String partnerID)throws SmartOfficeException{
		if(partnerID==null) {
			return authUserRepository.fetchAllClientUsers();
		}else {
			return authUserRepository.authClientUserByPartnerID(partnerID);
		}
		
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/auth-vendor")
	public List<AuthVendor> getAuthVendors(@RequestParam(value = "partnerID",required=false)String partnerID)throws SmartOfficeException{
		if(partnerID==null) {
			return authUserRepository.fetchAllVendorUsers();	
		}else {
			return authUserRepository.authVendorUserByPartnerID(partnerID);
		}
		
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/getByEmpId/{empId}/_internal")
	public Iterable<AuthUser> getAuthUserByEmpId(@PathVariable(value = "empId") String empId)throws SmartOfficeException{
		return authUserRepository.findByEmployeeId(empId);
	}
	
	//@CrossOrigin(origins = "*")
	@PostMapping
	public AuthUser addUser(@RequestBody(required = false) AuthUser authUser) throws Exception {
		// user.setId(commonUtils.generateId());
//		System.out.println("AuthUser POST - "+authUser);
		authUser.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		return authUserRepository.save(authUser);

	}
	
	@PostMapping("/_internal")
	public AuthUser addUserInternal(@RequestBody(required = false) AuthUser authUser) throws Exception {
		// user.setId(commonUtils.generateId());
//		System.out.println("AuthUser POST - "+authUser);
		authUser.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		return authUserRepository.save(authUser);

	}


	
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<AuthUser> getUserById(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		return authUserRepository.findById(id);

	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public AuthUser updateuserById(@RequestBody AuthUser authUser) throws SmartOfficeException {

//		System.out.println("AuthUser PATCH - "+user);
		authUser.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		return authUserRepository.save(authUser);
	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		authUserRepository.deleteById(id);
	}


	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}/fetch-password")
	public String fetchPassword(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		if(commonUtils.isAdminUser() || commonUtils.isSuperUser()) {
			return authUserRepository.findById(id).get().getPassword(); // TODO - This needs to be encrypted.	
		}
		else {
			throw new SmartOfficeException("Access Denied!");
		}

	}
	
	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}/change-password")
	public Object changePasswordById(@PathVariable(value = "id") Integer id, @RequestBody AuthUser authUser)
			throws SmartOfficeException {

		if (authUser.getPassword() == null || authUser.getPassword().isEmpty()) {
			throw new SmartOfficeException("Password cannot be empty");
		}
		AuthUser currUser = getUserById(id).get();
		currUser.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		currUser.setPassword(authUser.getPassword());

		authUserRepository.save(currUser);
		
		return CommonUtils.returnSuccess();
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/reset-password")
	public Object resetPasswordById(@RequestBody AuthUser authUser) throws SmartOfficeException {
		if (authUser != null) {

			List<AuthUser> authUsers = new ArrayList<AuthUser>();
			authUsers = authUserRepository.findByUserName(authUser.getUserName());

			if (authUsers != null && !authUsers.isEmpty()) {
				authUser = authUsers.get(0);
				String employeeId = authUser.getEmployeeId();
				Employee employee = commonUtils.getRestTemplate().getForObject(employeeUrl + "/" + employeeId, Employee.class);
				authUser.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				authUser.setPassword(employee.getDob());
				authUserRepository.save(authUser);
			} else {
				throw new SmartOfficeException("Given UserName doesn't exist in the system");
			}
		}
		return CommonUtils.returnSuccess();
	}
	
	@PatchMapping("/login-access/{id}/{access}")
	public String changeLoginAccess(@PathVariable(value = "id")Integer id,@PathVariable(value = "access")String access) {
		AuthUser authFromDb = authUserRepository.findById(id).get();
		if(access.equals("ACTIVE") || access.equals("INACTIVE")) {
			authFromDb.setUserStatus(access);
			authFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
			authFromDb.setModifiedDt(LocalDateTime.now());
			authUserRepository.save(authFromDb);
			return "success";
		}else {
			return "Unknow access Type";
		}
		
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/accept-terms")
	public Object acceptTerms()
			throws SmartOfficeException {
		System.out.println("kh - get-current-logged-inuser"+commonUtils.getLoggedinUserId());
		System.out.println("kh - get-current-logged-employeeId"+commonUtils.getLoggedinEmployeeId());
		
		AuthUser currUser = getUserById(Integer.parseInt(commonUtils.getLoggedinUserId())).get();
		currUser.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		currUser.setAcceptedAgmt("Y");
		currUser.setAcceptedAgmtDt(LocalDateTime.now());
		currUser.setAcceptedAgmtLocation(currUser.getAcceptedAgmtLocation());
		authUserRepository.save(currUser);
		return CommonUtils.returnSuccess();
	}

	
	@CrossOrigin(origins="*")
	@PatchMapping("/multi-update")
	public Iterable<AuthUser> bulkAndUpdate(@RequestBody List<AuthUser> authUsers)throws SmartOfficeException{
		for(AuthUser authUser:authUsers ) {
			if(authUser.getId()!=null) {
				Iterable<AuthUser> authusers= authUserRepository.saveAll(authUsers);
			}else {
				throw new SmartOfficeException("Id value not Present");
			}
		}
		return authUsers;
		
	}
	
	
	
	 
}
	 
	 
	 
	 
	 
		
		
	

