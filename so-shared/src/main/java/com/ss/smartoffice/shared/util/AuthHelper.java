package com.ss.smartoffice.shared.util;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
 
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthToken;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.repos.AuthTokenRepo;

@Service
public class AuthHelper {

	private static Logger log = LoggerFactory.getLogger(AuthHelper.class);
	 
	
	@Autowired
	AuthTokenRepo authTokenRepo;

	@Autowired
	AuthUserRepository authUserRepository;
	


	public AuthToken handleAuthToken(Integer authUserId, String applicationCode, boolean createIfNotFound, boolean replaceAppToken) {
		return handleAuthToken(authUserRepository.findById(authUserId).get(),applicationCode, createIfNotFound, replaceAppToken);
	}
	
	public AuthToken handleAuthToken(AuthUser authUser, String applicationCode, boolean createIfNotFound, boolean replaceAppToken) {
		AuthToken authToken = null;
		try {
			if (authUser.getAuthTokens() != null && !(authUser.getAuthTokens().isEmpty())) {
				authToken = authUser.getAuthTokens().stream()
						.filter(line -> line.getApplicationCode().equals(applicationCode)).findAny().orElse(null);
			}

			if (authToken != null && !authToken.equals(null)) { 
				authToken = updateExistingAuthToken(authToken, replaceAppToken);
			} else {
				authToken = createNewAuthToken(authUser, applicationCode);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return authToken;
	}
	
	public AuthToken extendToken(AuthToken authToken) {
		authToken.setTokenValidityDt(allotingValdity(authToken.getApplicationCode())); 
		authToken.setDateTime(LocalDateTime.now());
		return authTokenRepo.save(authToken);
	}
	
	public String getToken(AuthUser authUser, String applicationCode) {
		String token = null;
		if (authUser.getAuthTokens() != null && !(authUser.getAuthTokens().isEmpty())) {
			AuthToken authToken = authUser.getAuthTokens().stream()
					.filter(line -> line.getApplicationCode().equals(applicationCode)).findAny().orElse(null);
			if(authToken!=null) {
				token = authToken.getAppToken();
			}
		}
		return token;
	}
	
	public AuthUser getUserByToken(String appToken) {
		try {
			return authUserRepository.findById(getAuthTokenByAppToken(appToken).getAuthUserId()).get() ; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public AuthUser getUserById(Integer authUserId) {
		try {
			return authUserRepository.findById(authUserId).get() ; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public AuthToken getAuthTokenByAppToken(String appToken) {
			return authTokenRepo.fetchByAuthToken(appToken);	
	}
	 
	
	// Creating authToken if it does not exist or is of type (Async)
	private AuthToken createNewAuthToken(AuthUser authUser, String applicationCode) {
		AuthToken authtoken = new AuthToken();
		authtoken.setAppToken(generateId());
		authtoken.setTokenValidityDt(allotingValdity(applicationCode));
		authtoken.setAuthUserId(authUser.getId());
		authtoken.setEmployeeId(authUser.getEmployeeId());
		authtoken.setDateTime(LocalDateTime.now());
		authtoken.setApplicationCode(applicationCode);
		return authTokenRepo.save(authtoken);
	}

	// Updating authToken if it already exists
	private AuthToken updateExistingAuthToken(AuthToken a, boolean replaceAppToken ) {
		a.setTokenValidityDt(allotingValdity(a.getApplicationCode()));
		if(replaceAppToken) {
			a.setAppToken(generateId());
		}
		a.setDateTime(LocalDateTime.now());
		return authTokenRepo.save(a);
	}
	
	private LocalDateTime allotingValdity(String applicationCode) {
		LocalDateTime ltd = null;
		switch (applicationCode) {
		case "smart-office":
			ltd = LocalDateTime.now().plusHours(1);
			break;
		case "smart-office-mobile":
			ltd = LocalDateTime.now().plusMonths(1);
			break;
		case "recruitment":
			ltd = LocalDateTime.now().plusHours(2);
			break;
		case "async":
			ltd = LocalDateTime.now().plusHours(2);
			break;
		default:
			ltd = LocalDateTime.now().plusHours(1);
			break;
		}
		return ltd;
	}
	
	private String generateId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
