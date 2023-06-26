package com.ss.smartoffice.shared.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.interceptor.TokenService;
import com.ss.smartoffice.shared.model.AuthToken;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserRole;
import com.ss.smartoffice.shared.model.SimpleResponse;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.UserGroupEmployeeMapping;
import com.ss.smartoffice.shared.model.currency.ExchangeRateRepo;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.repos.AuthTokenRepo;
import com.ss.smartoffice.shared.repos.UserGroupEmployeeMappingRepository;
import com.ss.smartoffice.shared.util.AuthHelper;

@Controller
@Scope("prototype")
public class CommonUtils {
//	@Autowired
//	TokenService tokenService;

	@Autowired
	AuthUserRepository authUserRepository;

	@Autowired
	AuthTokenRepo authTokenRepo;
	
	@Autowired
	AuthHelper authHelper;

	@Autowired
	UserGroupEmployeeMappingRepository userGroupEmployeeMappingRepository;
	
	@Autowired
	ExchangeRateRepo exchangeRateRepo;

	
	@Value("${spring.mail.username}")
	private String senderEmail; 

	@Value("${docs.email.template.location}")
	private String templateLocation;
	private static final AtomicLong counter = new AtomicLong(0);

	public static long getNextNumber() {
		return counter.incrementAndGet();
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public String generateId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public List<Integer> randomize(List<Integer> inputList, int numberOfElements) {
		List<Integer> newList = new ArrayList<>();
		try {
			Random rand = new Random();
			for (int i = 0; i < numberOfElements; i++) {
				int randomIndex = rand.nextInt(inputList.size());
				newList.add(inputList.get(randomIndex));
				inputList.remove(randomIndex);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newList;
	}

	public List<Integer> shuffle(Integer[] inputList) {
		int n = inputList.length;
		Random r = new Random();
		for (int i = n - 1; i > 0; i--) {

			int j = r.nextInt(i);

			int temp = inputList[i];
			inputList[i] = inputList[j];
			inputList[j] = temp;
		}
		return convertArrayToList(inputList);
	}

	public List<Integer> convertArrayToList(Integer array[]) {
		List<Integer> list = new ArrayList<>();
		for (Integer t : array) {
			list.add(t);
		}
		return list;
	}

	// Median
	public static double findMedian(int a[], int n) {
		// First we sort the array
		Arrays.sort(a);

		// check for even case
		if (n % 2 != 0)
			return (double) a[n / 2];

		return (double) (a[(n - 1) / 2] + a[n / 2]) / 2.0;
	}

	public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		double distance = 0.0;
		int R = 6371; // km (change this constant to get miles)
		double dLat = (lat2 - lat1) * Math.PI / 180;
		double dLon = (lon2 - lon1) * Math.PI / 180;
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180)
				* Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		if (d > 1) {
			distance = Math.round(d);
		} else if (d <= 1) {
			distance = Math.round(d * 1000);
		}

		return distance;

	}

	public boolean isHr() {
		boolean isHrFlag = false;
		String loggedInEmployeeId = getLoggedinEmployeeId();
		List<UserGroupEmployeeMapping> allUserGroupsbyEmployeeId = userGroupEmployeeMappingRepository
				.findByEmployeeId(loggedInEmployeeId);
		for (UserGroupEmployeeMapping userGroupEmployeeMapping : allUserGroupsbyEmployeeId) {
			if ((userGroupEmployeeMapping.getIsHrL1() != null && userGroupEmployeeMapping.getIsHrL1().equals("Y"))
					|| (userGroupEmployeeMapping.getIsHrL2() != null
							&& userGroupEmployeeMapping.getIsHrL2().equals("Y"))) {
				isHrFlag = true;
				break;
			}
		}
		return isHrFlag;
	}

	public boolean isDir() {
		boolean isDir = false;
		String loggedInEmployeeId = getLoggedinEmployeeId();
		List<UserGroupEmployeeMapping> allUserGroupsbyEmployeeId = userGroupEmployeeMappingRepository
				.findByEmployeeId(loggedInEmployeeId);
		for (UserGroupEmployeeMapping userGroupEmployeeMapping : allUserGroupsbyEmployeeId) {
			if ((userGroupEmployeeMapping.getIsDir() != null && userGroupEmployeeMapping.getIsDir().equals("Y"))
					|| (userGroupEmployeeMapping.getIsDir() != null
							&& userGroupEmployeeMapping.getIsDir().equals("Y"))) {
				isDir = true;
				break;
			}
		}
		return isDir;

	}


	public boolean isFlagSet(String str) {
		if (str == null || str.trim().isEmpty() || str.trim().equalsIgnoreCase("N"))
			return false;
		return true;
	}
	
	public boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty())
			return false;
		return true;
	}

	public String nullEmptyCheck(String str) {
		if (str != null && !str.isEmpty())
			return str;
		return "";
	}

	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		// Add the Jackson Message converter
		messageConverters.add(new MappingJackson2HttpMessageConverter());

		// Add the message converters to the restTemplate
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

	public static SimpleResponse returnSuccess() {
		return new SimpleResponse("SUCCESS");
	}

	public static String monthConversion(String month) {

		if (month == null || month.trim().length() == 0) {
			month = "00";
		} else {
			month = month.trim();
			if (month.length() == 1) {
				month = "0" + month;
			}
		}
		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap.put("00", "Month Not Chosen");
		monthMap.put("01", "January");
		monthMap.put("02", "February");
		monthMap.put("03", "March");
		monthMap.put("04", "April");
		monthMap.put("05", "May");
		monthMap.put("06", "June");
		monthMap.put("07", "July");
		monthMap.put("08", "August");
		monthMap.put("09", "September");
		monthMap.put("10", "October");
		monthMap.put("11", "November");
		monthMap.put("12", "December");
		return monthMap.get(month).toString();
	}

	public static String printInLakhs(String inputNumber) {
		String numberInLakhs = "";
		try {
			if (inputNumber == null) {
				return numberInLakhs;
			} else {
				inputNumber = inputNumber.trim();
			}
			if (inputNumber.indexOf(".") > -1) {
				numberInLakhs = inputNumber.substring(inputNumber.indexOf("."));
				inputNumber = inputNumber.substring(0, inputNumber.indexOf("."));
			}
			int length = inputNumber.length();
			if (length <= 3) {
				numberInLakhs = inputNumber + numberInLakhs;
			} else {
				numberInLakhs = inputNumber.substring(length - 3, length) + numberInLakhs;
				if (length > 5) {
					numberInLakhs = inputNumber.substring(length - 5, length - 3) + "," + numberInLakhs;
					if (length > 7) {
						numberInLakhs = inputNumber.substring(length - 7, length - 5) + "," + numberInLakhs;
						if (length > 9) {
							numberInLakhs = inputNumber.substring(length - 9, length - 7) + "," + numberInLakhs;
							if (length > 11) {
								numberInLakhs = inputNumber.substring(length - 11, length - 9) + "," + numberInLakhs;
								if (length > 13) {
									numberInLakhs = inputNumber.substring(length - 13, length - 11) + ","
											+ numberInLakhs;
								} else {
									numberInLakhs = inputNumber.substring(0, length - 11) + "," + numberInLakhs;
								}
							} else {
								numberInLakhs = inputNumber.substring(0, length - 9) + "," + numberInLakhs;
							}
						} else {
							numberInLakhs = inputNumber.substring(0, length - 7) + "," + numberInLakhs;
						}
					} else {
						numberInLakhs = inputNumber.substring(0, length - 5) + "," + numberInLakhs;
					}
				} else {
					numberInLakhs = inputNumber.substring(0, length - 3) + "," + numberInLakhs;
				}
			}

		} catch (Exception e) {
			numberInLakhs = inputNumber;
		}
		return numberInLakhs;
	}

	public static String yearConversion(String year) {

		Map<String, String> yearMap = new HashMap<String, String>();
		yearMap.put("17", "2017");
		yearMap.put("18", "2018");
		yearMap.put("19", "2019");
		return yearMap.get(year).toString();
	}

// Replace PlaceHolder
	public static String replacePlaceHolders(String message, Map<String, String> keyValues) {
		String finalStr = message;
		try {
			for (String key : keyValues.keySet()) {

				try {
					if (finalStr.indexOf("${" + key + "}") > -1) {
						finalStr = finalStr.replace("${" + key + "}", keyValues.get(key));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalStr;
	}

	public static List<String> getPlaceHolderKeys(String inputStr) {
		List<String> keys = new ArrayList<String>();
		String regexStr = "\\{(.*?)\\}";
		try {
			Pattern pattern = Pattern.compile(regexStr);
			Matcher matcher = pattern.matcher(inputStr);
			while (matcher.find()) {
				try {
					keys.add(inputStr.substring(matcher.start() + 1, matcher.end() - 1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keys;
	}

	public String getHtmlContent(String filePath) {
		filePath = templateLocation;
		System.out.println(templateLocation);
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(templateLocation), StandardCharsets.UTF_8)) {

		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

/// Reflection
	public void setValue(String value, Object target, String targetAttrib) {
		Field targetField = ReflectionUtils.findField(target.getClass(), targetAttrib);
		ReflectionUtils.makeAccessible(targetField);
		ReflectionUtils.setField(targetField, target, value);
	}

	public void setValue(int value, Object target, String targetAttrib) {
		Field targetField = ReflectionUtils.findField(target.getClass(), targetAttrib);
		ReflectionUtils.makeAccessible(targetField);
		ReflectionUtils.setField(targetField, target, value);
	}

	public static String getValue(Object source, String sourceAttrib) {
		try {
			Field sourceField = ReflectionUtils.findField(source.getClass(), sourceAttrib);
			if (sourceField != null) {
				ReflectionUtils.makeAccessible(sourceField);
			}
//   	System.out.println("sourceField"+sourceField);
//    	System.out.println("source"+source);
//    	System.out.println("sourceField.get(source)"+sourceField.get(source));
//    	System.out.println("source"+source);
			if (sourceField == null || sourceField.get(source) == null) {
				return null;
			} else {
				return sourceField.get(source).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getLoggedInApplicantId() {
		try {
			return ((AuthUser) SecurityContextHolder.getContext().getAuthentication().getDetails()).getApplicantId()
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Applicant not logged in");
		}
	}

	public String getLoggedinAppToken() {
		try {

			return SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("User not logged in");
		}
	}

	public int getPassword() {
		int n = 100000 + new Random().nextInt(900000);
		return n;
	}

	public String getLoggedinEmployeeId() {
		try {

			return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("User not logged in");
		}
	}

	public String getLoggedinUserId() {
		try {

			return SecurityContextHolder.getContext().getAuthentication().getName();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("User not logged in");
		}
	}

	public Authentication getAuthenticatedUser() {
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth.getName());
			return auth;
		} catch (Exception e) {
			e.printStackTrace();
			// temporary workaround until we figure out the problem of "context not having
			// authentication object"
			return getAdminObj();
		}
	}

	public Authentication getAuthObject(AuthUser authUser, String applicationCode) {
		Authentication auth1 = new Authentication() {

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return authUser.getId().toString();
			}

			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
				// TODO Auto-generated method stub
				isAuthenticated = true;

			}

			@Override
			public boolean isAuthenticated() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public Object getPrincipal() {
				// TODO Auto-generated method stub
				if (authUser.getEmployeeId() != null) {
					return authUser.getEmployeeId().toString();
				} else if(authUser.getApplicantId() != null){
					return authUser.getApplicantId().toString();
				}else{
					return authUser.getPartnerId().toString();
				}

			}

			@Override
			public Object getDetails() {
				return authUser;
			}

			@Override
			public Object getCredentials() { 
				String authToken = authHelper.getToken(authUser, applicationCode);
//				if(authToken == null) {
//					authToken = authHelper.updateOrCreateAuthToken(authUser, applicationCode).getAppToken();
//				}
				return authToken;
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
						.getContext().getAuthentication().getAuthorities();
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ANOTHER");
				List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
				updatedAuthorities.add(authority);
				updatedAuthorities.addAll(oldAuthorities);
				return updatedAuthorities;
			}
		};
		return auth1;
	}

	public Authentication getAdminObj() {
		Authentication auth1 = new Authentication() {

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "Admin";
			}

			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
				// TODO Auto-generated method stub
				isAuthenticated = true;

			}

			@Override
			public boolean isAuthenticated() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public Object getPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getDetails() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getCredentials() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
						.getContext().getAuthentication().getAuthorities();
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ANOTHER");
				List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
				updatedAuthorities.add(authority);
				updatedAuthorities.addAll(oldAuthorities);
				return updatedAuthorities;
			}
		};
		return auth1;
	}

//	Changed
	public void setAuthenticationContext(String appToken) {
		AuthToken authToken = authHelper.getAuthTokenByAppToken(appToken);
		if(authToken == null) {
			throw new SmartOfficeException("The appToken("+appToken + ") is not part of the system");
		}
		setAuthenticationContext(authHelper.getUserByToken(appToken), authToken.getApplicationCode());
	}

	public String setAuthenticationContext(Integer authUserId, String applicationCode) {
		boolean createIfNotFound = false;
		boolean replaceAppToken = false;
		if(applicationCode.equals("async")) {
			createIfNotFound = true;			
		}
		AuthToken a = authHelper.handleAuthToken(authUserId, applicationCode,createIfNotFound,replaceAppToken);
		setAuthenticationContext(authHelper.getUserById(authUserId), applicationCode);
		return a.getAppToken();
	}

	public void setAuthenticationContext(AuthUser authUser, String applicationCode) { 
		SecurityContextHolder.getContext().setAuthentication(this.getAuthObject(authUser, applicationCode));		
	}
	
		 
	
	public String getFileExtension(String fileName) {
		int dot = fileName.lastIndexOf('.');
		String base = (dot == -1) ? fileName : fileName.substring(0, dot);
		String extension = (dot == -1) ? "" : fileName.substring(dot + 1);
		return extension;
	}

	// returns entire AuthUser object
	public AuthUser getLoggedInUser() {
		// TODO Auto-generated method stub
		return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
	}

	public boolean isSuperUser() {
		try {
			boolean isSuperUser = false;
			for (AuthUserRole authUserRole : getLoggedInUser().getAuthUserRoles()) { // TODO - use streams filter
				if (authUserRole.getAuthRoleCode() != null && authUserRole.getAuthRoleCode().equalsIgnoreCase("SU")) {
					isSuperUser = true;
					break;
				}
			}
			return isSuperUser;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isAdminUser() {
		try {
			boolean isAdminUser = false;
			for (AuthUserRole authUserRole : getLoggedInUser().getAuthUserRoles()) { // TODO - use streams filter
				if (authUserRole.getAuthRoleCode() != null && authUserRole.getAuthRoleCode().equalsIgnoreCase("ADMIN")) {
					isAdminUser = true;
					break;
				}
			}
			return isAdminUser;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<String> findLoggedInUserGroups() {
		List<UserGroupEmployeeMapping> userGroupMappingList = new ArrayList<>();
		userGroupMappingList.addAll(getLoggedInUser().getUserGroupMappings());
		System.out.println(userGroupMappingList);
		List<String> userGroupIds = userGroupMappingList.stream()
				.filter(userGroupId -> userGroupId.getUserGroupId() != null).map(id -> id.getUserGroupId())
				.collect(Collectors.toList());
		System.out.println(userGroupIds);
		return userGroupIds;
	}
 

	public void checkAndCreateDir(String fullFilePath) {
		try {
			String dirName = fullFilePath.substring(0, fullFilePath.lastIndexOf('/'));
			File directory = new File(dirName);
			if (!directory.exists()) {
				directory.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
	}

	public String getRoleId(String roleCode) {
		return getRoleIds().get(roleCode);
	}

	public Map<String, String> getRoleIds() {
		Map<String, String> roleIds = new HashMap<String, String>() {
			{
				put("E", "6");
				put("N1", "4");
				put("N2", "9");
				put("HR1", "67");
				put("HR2", "68");
				put("ACC1", "65");
				put("ACC2", "66");
				put("D", "2");
				put("SU", "1");
			}
		};
		return roleIds;
	}

	public List<String> findAssociatedEmployees(memployee e) {
		List<String> associatedEmployees = new ArrayList<String>();
		if (e.getN1EmpId() != null && !e.getN1EmpId().isEmpty())
			associatedEmployees.add(e.getN1EmpId());
		if (e.getN1EmpId() != null && !e.getN1EmpId().isEmpty())
			associatedEmployees.add(e.getN1EmpId());
		return associatedEmployees;
	}
	
	public LocalDateTime toLocalDt(String dateTimeStr) {
		LocalDateTime localDt = null; 
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
			localDt = LocalDateTime.parse(dateTimeStr, formatter);
		} catch (Exception e) { 
			e.printStackTrace();
			throw new SmartOfficeException(e);
		}
		return localDt;
	}
	
	public float findExchangeRate(String fromCurrId,String toCurrId)throws SmartOfficeException{				
		return exchangeRateRepo.findByFromCurrIdAndToCurrId(fromCurrId, toCurrId);
	}

}
