package com.ss.smartoffice.shared.interceptor;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthToken;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.Metadata;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.menu;
import com.ss.smartoffice.shared.repos.AuthTokenRepo;
import com.ss.smartoffice.shared.util.AuthHelper;

import java.util.Base64;
import java.util.HashMap;

@RestController
@RequestMapping(path = "/tokens")
public class TokenService {
	
	@Value("${auth-menu.url}")
	private String authMenuUrl;
	
	@Autowired
	AuthUserRepository authUserRepository;

	@Autowired
	AuthTokenRepo authTokenRepo;

	@Autowired
	MetadataRepository metadataRepository;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	AuthHelper authHelper;

	private static SecretKeySpec secretKey;
	private static byte[] key;
	private String passKey = "sosmartoffice123";
	List<String> types = Arrays.asList("smart-office", "smart-office-mobile", "recruitment", "async");

	@PostMapping(path = "/login")
	public AuthUser loginToken(@RequestBody(required = false) AuthUser authUser) {
		authUser = loginToken(authUser, false); 
		return authUser;
	}
	
	@GetMapping(path = "/quick-login")
	public AuthUser quickLogin(@RequestParam String userName,@RequestParam String password,@RequestParam(required = false) String appClientId,@RequestParam(required = false) String applicationCode) {
		try {
			appClientId = appClientId==null?"pothigai-power":appClientId;
			applicationCode = applicationCode==null?"smart-office":applicationCode;
			AuthUser authUser = new AuthUser();
			authUser.setUserName(userName);
			authUser.setPassword(encrypt(password));
			authUser.setAppClientId(appClientId);
			authUser.setApplicationCode(encrypt(applicationCode));
			return loginToken(authUser, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e);
		} 
	}
	// this authenticates user-credentials and replies appToken
	@GetMapping(path = "/get-token/{userName}/{password}")
	public String getTokenEasyWay(@PathVariable String userName,@PathVariable String password) {
		return getToken(userName, password, null, null);
	}
	
	// this authenticates user-credentials and replies appToken
	@GetMapping(path = "/get-token")
	public String getToken(@RequestParam String userName,@RequestParam String password,@RequestParam(required = false) String appClientId,@RequestParam(required = false) String applicationCode) {
		try {
			appClientId = appClientId==null?"pothigai-power":appClientId;
			applicationCode = applicationCode==null?"smart-office":applicationCode;
			AuthUser authUser = new AuthUser();
			authUser.setUserName(userName);
			authUser.setPassword(encrypt(password));
			authUser.setAppClientId(appClientId);
			authUser.setApplicationCode(encrypt(applicationCode));
//			return loginToken(authUser, true).getAppToken();
			authUser =  loginToken(authUser, true);
			return authUser.getAppToken();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e);
		} 
	}
	
	@PostMapping(path = "/refresh")
	public AuthUser refreshToken(@RequestBody(required = false) AuthUser authUser) {
		authUser = loginToken(authUser, true); 
		return authUser;
	}
	
    private AuthUser loginToken(AuthUser authUser, boolean refreshToken) {
	    boolean loginSuccess = false;
	    boolean replaceAppToken = false;
		try {
			if (!isValidInput(authUser)) {
				throw new SmartOfficeException("Missing inputs!!");
			}			
			String password = decrypt(authUser.getPassword());
			String applicationCode = decrypt(authUser.getApplicationCode());
			List<AuthUser> userList = authUserRepository.findByUserNameAndAppClientId(authUser.getUserName(),
					authUser.getAppClientId()); 
	
			if (userList == null || userList.isEmpty()) {
				System.out.println("User doesnt exist");
				throw new SmartOfficeException("User doesnt exist");
			}
			AuthUser userFromDb = userList.get(0); 
	
			if (userFromDb.getPassword()==null || authUser.getPassword().trim().isEmpty() || !userFromDb.getPassword().equals(password)) { 
				throw new SmartOfficeException("Password does not match or No Password is set");
			}  
			if (userFromDb.getUserStatus().equals("INACTIVE")) {
				throw new SmartOfficeException("User is inactive");
			}
	
			if(applicantionCodeCheck(applicationCode, userFromDb)) {
				loginSuccess = true; 
			}
			else {
				throw new SmartOfficeException("Permission denied for the application - "+applicationCode);
			}
			
			AuthToken authToken = null;
			
			if(loginSuccess) {
				//update last logged date in auth-user 
				userFromDb.setLastLoggedDt(LocalDateTime.now());
				authUserRepository.save(userFromDb);
				if(refreshToken)
	                replaceAppToken = false;
	            else
	                replaceAppToken = true;
	            // create token or replace existing token
				authToken = authHelper.handleAuthToken(userFromDb, applicationCode, true, replaceAppToken);
				
				userFromDb = authHelper.getUserById(userFromDb.getId());
				if(!applicationCode.equals("async")) {
					authUser=hideSensitiveInfo(userFromDb);
				}
				else {
					authUser=userFromDb;
				}
	
	            authUser = setTokenDetails(authUser, authToken);
				if(!refreshToken && applicationCode.equals("smart-office")) {
					authUser.setMenu(getMenu(authToken.getAppToken(),applicationCode));
				}		
			}
			setResult(authUser,loginSuccess,null);
		} catch (SmartOfficeException soe) {
			loginSuccess = false;
			setResult(authUser,loginSuccess,soe.getMessage());
		} catch (Exception e) {
			loginSuccess = false;
			System.out.println("localhised"+e.getLocalizedMessage());
			System.out.println("error"+e.getMessage());
			e.printStackTrace();
			setResult(authUser,loginSuccess,"System Error -- "+e.getLocalizedMessage());
		}
		return authUser;
	}
	private boolean isValidInput(AuthUser authUser){
		boolean result = false;
		if(authUser.getPassword()== null || authUser.getPassword().trim().isEmpty() 
				|| authUser.getApplicationCode()== null || authUser.getApplicationCode().trim().isEmpty()
				|| authUser.getApplicantId()== null || authUser.getApplicantId().trim().isEmpty()
				|| authUser.getUserName()== null || authUser.getUserName().trim().isEmpty()){
			result = true;
		} 
		return result;
	}
	private void setResult(AuthUser authUser, Boolean loginSuccess, String errorMsg){
		Map<String, Object> map = new HashMap<String, Object>();
		if(loginSuccess) {
			map.put("loginSuccess", "true");	
		}else {
			map.put("loginSuccess", "false");
			map.put("errorMessage", errorMsg); 
		}
		authUser.setAttList(map);
	}
	
	private List<menu> getMenu(String appToken, String applicationCode) {
		List<menu> userMenuList = new ArrayList<menu>(); 
		if((!applicationCode.equals("smart-office"))|| appToken == null || appToken.isEmpty()) {
			return userMenuList;
		}
		HttpHeaders headers = new HttpHeaders();		
		headers.set("Authorization", appToken);
		menu authMenu = new menu();
		HttpEntity<menu> menurequest = new HttpEntity<menu>(authMenu, headers);
		ResponseEntity<menu[]> menuList = commonUtils.getRestTemplate().exchange(authMenuUrl,
				HttpMethod.GET, menurequest, menu[].class);
		userMenuList = Arrays.asList(menuList.getBody());
		
		return userMenuList;
	}
 
 
 
	public boolean applicantionCodeCheck(String applicationCode, AuthUser authUser) {
		boolean allow = false;
		if (authUser.getIsEnabled().equals("Y")) {
			if (applicationCode.equals("smart-office") || applicationCode.equals("smart-office-mobile")) {
				if (isPartner(authUser.getUserType(), applicationCode)) {
					allow = true;
				} else {
					if (authUser.getEmpCode() == null || authUser.getEmpCode().isEmpty()) {
						// Not an employee
						allow = false;
					} else {
						// Is an employee
						allow = true;
					}
				}
			} else if (applicationCode.equals("recruitment")) {
				if (authUser.getEmpCode() != null && !authUser.getEmpCode().isEmpty()) {
					// Not an applicant
					allow = false;
				} else {
					// Is an applicant
					allow = true;
				}
			} else if (applicationCode.equals("async")) {
				// If async
				allow = false;
			}
		} else {
			allow = false;
		}
		return allow;
	}

	public boolean isPartner(String input, String applicationCode) {
		if (applicationCode.equals("smart-office") && (input.equals("VENDOR") || input.equals("CLIENT"))) {
			return true;
		} else {
			return false;
		}
	}

	private String encrypt(String plainText)  throws InvalidKeyException, NoSuchAlgorithmException,
	NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException  {
		System.out.println(new String(Base64.getEncoder().encodeToString(plainText.getBytes(StandardCharsets.UTF_8))));
		String encryptedString = "";
		setKey(passKey);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
		return encryptedString;
	}

	private String decrypt(String encryptedString) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String dcryptedString = "";
		setKey(passKey);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		dcryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedString)));
		System.out.println(dcryptedString);
		return dcryptedString;
	}

	public static void setKey(String myKey) {
		try {
			key = myKey.getBytes("UTF-8");
			secretKey = new SecretKeySpec(key, "AES");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(path = "/logout")
	public String logoutToken(@RequestBody(required = false) AuthUser authUser) {
		String result = "";
		try {
			AuthToken matchingAuthTokenFromDb = authTokenRepo.fetchByAuthToken(authUser.getAppToken());
			if(matchingAuthTokenFromDb==null) {
				throw new SmartOfficeException("Auth token mismatch or does not exist");
			}
			String applicationCode = matchingAuthTokenFromDb.getApplicationCode();

			if (applicationCode.equals("async")) { 
				throw new SmartOfficeException("Cannot logout async token");
			}		 
			//delete the token 
			authTokenRepo.deleteById(matchingAuthTokenFromDb.getId()); 
			result = "success";
		} catch (SmartOfficeException soe) {
			result = soe.getMessage();
		}catch (Exception e) { 
			e.printStackTrace();
			result = e.getMessage();
		}
		 
		return result;
	}

	@PostMapping(path = "/validate")
	public AuthUser validateToken(@RequestBody(required = false) AuthUser authUser) {
		return validateToken(authUser, "N");
	}

	public AuthUser validateToken(AuthUser authUser, String isAsync) {
		AuthUser _user = internalValidation(authUser, isAsync);
		return hideSensitiveInfo(_user);
	}

	public AuthUser internalValidation(AuthUser authUser) {
		return internalValidation(authUser, "N");
	}

	public AuthUser internalValidation(AuthUser authUser, String isAsync) { 
		try {
			AuthToken matchingAuthTokenFromDb = authTokenRepo.fetchByAuthToken(authUser.getAppToken());
			if(matchingAuthTokenFromDb==null) {
				throw new SmartOfficeException("Auth token mismatch or does not exist");
			}
			String applicationCode = matchingAuthTokenFromDb.getApplicationCode();

			if (applicationCode.equals("async")) {
				//if async extend the validity time
				matchingAuthTokenFromDb = authHelper.extendToken(matchingAuthTokenFromDb);
			}		 
			//calculate remaining minutes and update the database 
			LocalDateTime cdt = LocalDateTime.now();
			long minutes = checkRemaniningMinutes(matchingAuthTokenFromDb, cdt);
			int minutesInt = (int) minutes;
			int hoursCal = minutesInt / 60;
			int minutesCal = minutesInt % 60;
			if (minutes <= 0) {
				throw new SmartOfficeException("Token Expired!");
			} else {
				matchingAuthTokenFromDb.setDuration(Integer.toString(hoursCal) + ":" + Integer.toString(minutesCal));
				matchingAuthTokenFromDb.setLastLoggedDt(cdt);
				authTokenRepo.save(matchingAuthTokenFromDb);
			}
			authUser = authHelper.getUserById(matchingAuthTokenFromDb.getAuthUserId());
			authUser = setTokenDetails(authUser,matchingAuthTokenFromDb);
			return authUser;
		} catch (SmartOfficeException soe) {
			throw soe;
		}catch (Exception e) { 
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		
	}
	
	private AuthUser setTokenDetails(AuthUser authUser, AuthToken authToken ) {
		authUser.setApplicationCode(authToken.getApplicationCode());
		authUser.setAppToken(authToken.getAppToken());
		authUser.setTokenValidityDt(authToken.getTokenValidityDt());
		authUser.setLastLoggedDt(authToken.getLastLoggedDt());
		return authUser;
	}

	public long checkRemaniningMinutes(AuthToken a, LocalDateTime fromDateTime) {
		if (a.getApplicationCode() != null && a.getTokenValidityDt() != null) {
			long minutes = fromDateTime.until(a.getTokenValidityDt(), ChronoUnit.MINUTES);
			return minutes;
		} else
			return 0;
	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty())
			return false;
		return true;
	}

	private AuthUser hideSensitiveInfo(AuthUser authUser) {
		if (authUser != null) {
			authUser.setPassword(null);
		}
		return authUser;
	}

	@PostMapping(path = "/tokenCheck/{contextUserId}")
	public String tokenCheck(@PathVariable(value = "contextUserId") Integer contextUserId) {
		String token = commonUtils.setAuthenticationContext(contextUserId,"async");
		return token;
	} 
//	-------------------------------------Mobile login/Logout (Currently not in use))------------------------------------------------------------------------------------------------

	@GetMapping
	public Iterable<Metadata> getAllMetadata(@RequestParam(value = "authUserId") String authUserId)
			throws SmartOfficeException {
		boolean searchByAuthUserId = false;
		if (authUserId != null && !authUserId.isEmpty()) {
			searchByAuthUserId = true;
		}
		if (searchByAuthUserId) {
			metadataRepository.findByauthUserId(authUserId);
		} else {
			throw new SmartOfficeException("AuthUserId Does not Exist");
		}
		return metadataRepository.findAll();
	}

	@PostMapping(path = "/mobile-login")
	public AuthUser mobileLogin(@RequestBody(required = false) AuthUser authUser) throws SmartOfficeException {
		Iterable<Metadata> metadataList = getAllMetadata(authUser.getId().toString());
		for (Metadata metadata1 : metadataList) {
			if (authUser.getMetadata() != null && !authUser.getMetadata().isEmpty()) {
				for (Metadata metadata : authUser.getMetadata()) {
					if (metadata1.getImei().equals(metadata.getImei())) {
						authUser = loginToken(authUser);
					} else {
						throw new SmartOfficeException("Use your mobile for login ");
					}
				}
			} else {
				throw new SmartOfficeException("Metadata List Is Empty");
			}
		}
		return authUser;
	}

	@PostMapping(path = "/mobile-logout")
	public String mobileLogout(@RequestBody(required = false) AuthUser authUser) throws SmartOfficeException {
		Iterable<Metadata> metadataList = getAllMetadata(authUser.getId().toString());
		for (Metadata metadata1 : metadataList) {
			if (authUser.getMetadata() != null && !authUser.getMetadata().isEmpty()) {
				for (Metadata metadata : authUser.getMetadata()) {
					if (metadata1.getImei().equals(metadata.getImei())) {
						logoutToken(authUser);
					} else {
						throw new SmartOfficeException("Use your mobile for logout ");
					}
				}
			} else {
				throw new SmartOfficeException("Metadata List Is Empty");
			}
		}
		return "success";
	}

}
