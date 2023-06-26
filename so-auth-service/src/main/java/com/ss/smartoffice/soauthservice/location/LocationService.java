
package com.ss.smartoffice.soauthservice.location;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ss.smartoffice.shared.MapLocation.MapLocation;
import com.ss.smartoffice.shared.MapLocation.MapLocationRepository;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.TokenService;

import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.Location;
import com.ss.smartoffice.shared.model.attendance.Attendance;
import com.ss.smartoffice.soauthservice.auth.user.AuthUserService;
import com.ss.smartoffice.soauthservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soauthservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soauthservice.transaction.event.Event;

@RestController
@RequestMapping(path = "/locations")
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	MapLocationRepository mapLocationRepository;

	@Autowired
	AuthAttendanceRepository attendanceRepository;

	@Autowired
	EventPublisherService eventPublishService;
	
	 

	@Value("${map.Location.Id}")
	private String mapLocationId;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private CommonUtils commonUtils;
	
	private static final String key = "AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg";

	@PostMapping(path = "/check-in")
	public Location checkIn(@RequestBody(required = false) AuthUser authUser) throws Exception {

		Location location = new Location();
		Attendance attendance = new Attendance();
		AuthUser validatedUser = authUserService.getUserById(Integer.parseInt(commonUtils.getLoggedinUserId())).get();
		System.err.println(validatedUser);

		if (validatedUser != null) {
//			location.setId((int) CommonUtils.getNextNumber());
			location.setEmployeeId(validatedUser.getEmployeeId());
			location.setEmployeeCode(validatedUser.getEmpCode());
			location.setEmployeeFirstName(validatedUser.getFirstName());
			location.setEmployeeLastName(validatedUser.getLastName());
			location.setLatitude(authUser.getLatitude());
			location.setLongitude(authUser.getLongitude());
			location.setAppToken(authUser.getAppToken());
			location.setAppClientId(authUser.getAppClientId());
			location.setAttendanceStatus("created");
			location.setCity(authUser.getCity());
			location.setState(authUser.getState());
			location.setCountry(authUser.getCountry());
			location.setLogType(authUser.getLogType());
			
			attendance.setEmployeeId(validatedUser.getEmployeeId());
			attendance.setAttendanceDt(LocalDate.now());
			attendance.setAttendanceMonth(LocalDate.now().getMonthValue());
			attendance.setAttendanceStatus("WBL");
			attendance.setFirstSession("WBL");
			attendance.setAttendanceYear(LocalDate.now().getYear());
			attendance.setCheckIn(authUser.getCheckIn());
			attendance.setInLats(authUser.getLatitude());
			attendance.setInLongs(authUser.getLongitude());
			attendance.setInLocName(authUser.getCity());
			attendance.setStartDt(LocalDateTime.now());
			attendanceRepository.save(attendance);
			
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String sysDate = sdf.format(date);

			location.setDateTime(sysDate);
			location.setLocationStatus("Created");
			location = locationRepository.save(location);

			validatedUser.setLastCheckInDt(LocalDateTime.now());
			validatedUser.setIsCheckedIn("Y");
			authUserService.updateuserById(validatedUser);
			
			// temp fix need to look down the code

//			Event event = new Event();
//
//			event.setName(Event.EventTypes.CheckInEvent);
//			event.setCategory(Event.EventCategory.BuisnessEvent);
//			event.setAppToken(commonUtils.getLoggedinAppToken());
//			List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
//			BusinessKey businessKey = new BusinessKey();
//			businessKey.setEmployeeId(validatedUser.getEmployeeId());
//			businessKey.setLatitudes(authUser.getLatitude());
//			businessKey.setLongtitudes(authUser.getLongitude());
//			businessKey.setKey1(location.getId().toString());
//			businessKeys.add(businessKey);
//			System.out.println(businessKeys);
//			event.setBusinessKeys(businessKeys);
//			eventPublishService.pushEvent(event);
//			System.out.println("hi" + event);

		} else {
			throw new SmartOfficeException("AuthUser not found");
		}
		return location;

	}
//	Changed

	@PostMapping(path = "/reporcess-check-in")
	public List<Location> processPendingCheckInEvents() {
		String todaysDt = LocalDate.now().toString();
		List<Location> unprocessed = locationRepository.fetchByAttendanceStatusAndDateTime("created", todaysDt);
		for (Location location : unprocessed) {
			triggerCheckInEvent(location);
		}
		return unprocessed;
	}
	
	@GetMapping("/emp-location")
	public List<Location> getLocationByEmployeeId(@RequestParam(value = "empId",required = false) String empId,
			@RequestParam(value = "city",required = false) String city,@RequestParam(value = "state",required = false) String state,@RequestParam(value = "country",required = false) String country) {
		if(empId!=null) {
			return locationRepository.findByEmployeeId(empId);
		}else if(city!=null) {
			return locationRepository.findByCity(city);
		}else if(state!=null) {
			return locationRepository.findByState(state);			
		}else if(country!=null) {
			return locationRepository.findByCountry(country);
		}
		throw new SmartOfficeException("Please Select any one of the searching Field");
	}
	@GetMapping("/loc-search")
	public Map<String,List<String>> getDistinctValues(){
		Map<String,List<String>> search = new HashMap<String, List<String>>();
		search.put("city", locationRepository.findByDistintCity());
		search.put("state", locationRepository.findByDistintState());
		search.put("country", locationRepository.findByDistintCountry());
		return search;
	}
	@GetMapping("/to-reprocess")
	public void triggerCheckInEvent(Location location) {
		Event event = new Event();
		event.setName(Event.EventTypes.CheckInEvent);
		event.setCategory(Event.EventCategory.BuisnessEvent);
		event.setAppToken(location.getAppToken());
				
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setEmployeeId(location.getEmployeeId());
		businessKey.setLatitudes(location.getLatitude());
		businessKey.setLongtitudes(location.getLongitude());
		businessKey.setKey1(location.getId().toString());
		businessKeys.add(businessKey);
		System.out.println(businessKeys);
		event.setBusinessKeys(businessKeys);
		eventPublishService.pushEvent(event);
		System.out.println("hi" + event);
	}
	

	@PostMapping(path = "/check-out")
	public Location checkOut(@RequestBody(required = false) AuthUser authUser) throws Exception {

		Location location = new Location();
		Attendance attendance = new Attendance();
		AuthUser validatedUser = authUserService.getUserById(Integer.parseInt(commonUtils.getLoggedinUserId())).get();
		if (validatedUser != null) {
//			location.setId((int) CommonUtils.getNextNumber());
			location.setEmployeeId(validatedUser.getEmployeeId());
			location.setEmployeeCode(validatedUser.getEmpCode());
			location.setEmployeeFirstName(validatedUser.getFirstName());
			location.setEmployeeLastName(validatedUser.getLastName());
			location.setLatitude(authUser.getLatitude());
			location.setLongitude(authUser.getLongitude());
			location.setAppToken(authUser.getAppToken());
			location.setAppClientId(authUser.getAppClientId());
			location.setCity(authUser.getCity());
			location.setState(authUser.getState());
			location.setCountry(authUser.getCountry());
			location.setLogType(authUser.getLogType());
			
			attendance=attendanceRepository.findById(Integer.parseInt(authUser.getAttendanceId())).get();
			attendance.setCheckOut(authUser.getCheckOut());
			attendance.setOutLats(authUser.getLatitude());
			attendance.setOutLongs(authUser.getLongitude());
			attendance.setOutLocName(authUser.getCity());
			attendance.setEndDt(LocalDateTime.now());
			attendance.setSecondSession("WBL");
			
			attendanceRepository.save(attendance);
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String sysDate = sdf.format(date);

			location.setDateTime(sysDate);
			location.setLocationStatus("Created");
			locationRepository.save(location);
			validatedUser.setLastCheckOutDt(LocalDateTime.now());
			validatedUser.setIsCheckedIn("N");
			
			// temp fix need to look down the code
			
//			Event event = new Event();
//
//			event.setName(Event.EventTypes.CheckOutEvent);
//			event.setCategory(Event.EventCategory.BuisnessEvent);
//			event.setAppToken(authUser.getAppToken());
//			List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
//			BusinessKey businessKey = new BusinessKey();
//			businessKey.setEmployeeId(validatedUser.getEmployeeId());
//			businessKey.setLatitudes(authUser.getLatitude());
//			businessKey.setLongtitudes(authUser.getLongitude());
//
//			businessKeys.add(businessKey);
//			event.setBusinessKeys(businessKeys);
//			eventPublishService.pushEvent(event);
//			System.out.println(event);
			authUserService.updateuserById(validatedUser);

		} else {

			throw new SmartOfficeException("AuthUser not found");
		}

		return location;

	}

	@PatchMapping(path = "/checkout-all")
	private Iterable<Attendance> checkoutAllEmployees() throws Exception {
		Location location = new Location();

		Iterable<Attendance> attendances = attendanceRepository.findAll();
		MapLocation mapLocation = mapLocationRepository.findById(Integer.parseInt(mapLocationId)).get();

		locationRepository.save(location);
		for (Attendance attendance2 : attendances) {

			if (attendance2.getCheckOut() == null) {

				System.out.println("Check Out Event Generated");
				attendance2.setDidSystemCheckOut("Y");

				location.setLatitude(String.valueOf(mapLocation.getLats()));
				location.setLongitude(String.valueOf(mapLocation.getLongs()));
				System.out.println("attendance2");
				System.out.println(attendance2);
				Event event = new Event();

				event.setName(Event.EventTypes.CheckOutEvent);
				event.setCategory(Event.EventCategory.BuisnessEvent);
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setEmployeeId(attendance2.getEmployeeId());
				businessKey.setLatitudes(String.valueOf(mapLocation.getLats()));
				businessKey.setLongtitudes(String.valueOf(mapLocation.getLongs()));
				System.out.println(event);
				event.setBusinessKeys(businessKeys);
				eventPublishService.pushEvent(event);

			} else {
				continue;
			}
			attendanceRepository.save(attendance2);
		}
		return attendances;

	}

	@PostMapping(path = "/proxy-check-in")
	public Location proxyCheckIn(@RequestBody(required = false) AuthUser authUser) throws Exception {

		Location location = new Location();
		AuthUser validatedUser = validatedUser(authUser);

		if (validatedUser != null) {
			location.setId((int) CommonUtils.getNextNumber());
			location.setEmployeeId(validatedUser.getEmployeeId());
			location.setEmployeeCode(validatedUser.getEmpCode());
			location.setEmployeeFirstName(validatedUser.getFirstName());
			location.setEmployeeLastName(validatedUser.getLastName());
			location.setLatitude(authUser.getLatitude());
			location.setLongitude(authUser.getLongitude());
			location.setAppToken(authUser.getAppToken());
			location.setAppClientId(authUser.getAppClientId());

			location.setCity(authUser.getCity());
			location.setState(authUser.getState());
			location.setCountry(authUser.getCountry());
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String sysDate = sdf.format(date);
			location.setDateTime(sysDate);
			location.setLocationStatus("Created");

			if (authUser.getProxyEmpId() != null) {
				AuthUser proxyUser = getProxyEmpDetails(authUser.getProxyEmpId());
				location.setProxyEmployeeId(proxyUser.getId().toString());
				location.setProxyEmployeeFirstName(proxyUser.getFirstName());
				location.setProxyEmployeeLastName(proxyUser.getLastName());

			} else {
				throw new SmartOfficeException("Proxy Emp id doesn't exist");
			}

			locationRepository.save(location);
			validatedUser.setLastCheckInDt(LocalDateTime.now());
			validatedUser.setIsCheckedIn("Y");

			authUserService.updateuserById(validatedUser);
		} else {

			throw new SmartOfficeException("AuthUser not found");
		}

		return location;

	}

	@PostMapping(path = "/proxy-check-out")
	public Location proxyCheckOut(@RequestBody(required = false) AuthUser authUser) throws Exception {

		Location location = new Location();
		AuthUser validatedUser = validatedUser(authUser);
		if (validatedUser != null) {
			location.setId((int) CommonUtils.getNextNumber());
			location.setEmployeeId(validatedUser.getEmployeeId());
			location.setEmployeeCode(validatedUser.getEmpCode());
			location.setEmployeeFirstName(validatedUser.getFirstName());
			location.setEmployeeLastName(validatedUser.getLastName());
			location.setLatitude(authUser.getLatitude());
			location.setLongitude(authUser.getLongitude());
			location.setAppToken(authUser.getAppToken());
			location.setAppClientId(authUser.getAppClientId());
			location.setCity(authUser.getCity());
			location.setState(authUser.getState());
			location.setCountry(authUser.getCountry());

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String sysDate = sdf.format(date);
			location.setDateTime(sysDate);
			location.setLocationStatus("Created");
			if (authUser.getProxyEmpId() != null) {
				AuthUser proxyUser = getProxyEmpDetails(authUser.getProxyEmpId());
				location.setProxyEmployeeId(proxyUser.getId().toString());
				location.setProxyEmployeeFirstName(proxyUser.getFirstName());
				location.setProxyEmployeeLastName(proxyUser.getLastName());
			} else {
				throw new SmartOfficeException("Proxy Emp id doesn't exist");
			}
			locationRepository.save(location);

			validatedUser.setLastCheckOutDt(LocalDateTime.now());
			validatedUser.setIsCheckedIn("N");
			authUserService.updateuserById(validatedUser);
		} else {

			throw new SmartOfficeException("AuthUser not found");
		}
		return location;

	}

	@PostMapping(path = "/log-location")
	public Location logLocation(@RequestBody(required = false) AuthUser authUser) throws Exception {
		if (authUser.getLatitude() != null && authUser.getLongitude() != null) {
			Location location = new Location();
			AuthUser validatedUser = validatedUser(authUser);
			if (validatedUser != null) {
				location.setId((int) CommonUtils.getNextNumber());
				validatedUser.setId((int) CommonUtils.getNextNumber());
				location.setEmployeeId(validatedUser.getEmployeeId());
				location.setEmployeeCode(validatedUser.getEmpCode());
				location.setEmployeeFirstName(validatedUser.getFirstName());
				location.setEmployeeLastName(validatedUser.getLastName());
				location.setLatitude(authUser.getLatitude());
				location.setLongitude(authUser.getLongitude());
				location.setAppToken(authUser.getAppToken());
				location.setAppClientId(authUser.getAppClientId());
				location.setCity(authUser.getCity());
				location.setState(authUser.getState());
				location.setCountry(authUser.getCountry());
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String sysDate = sdf.format(date);
				location.setDateTime(sysDate);
				location.setLocationStatus(authUser.getLocationStatus());
				location.setLocationStatus("Created");
				locationRepository.save(location);
			}
		} else {
			throw new SmartOfficeException("AuthUser not found");
		}
		return null;

	}

	private AuthUser validatedUser(AuthUser authUser) throws JsonProcessingException {
		return tokenService.internalValidation(authUser);
	}

	public AuthUser getProxyEmpDetails(String proxyEmpId) throws JsonProcessingException {
		return authUserService.getUserById(Integer.parseInt(proxyEmpId)).get();
	}

	public Map<String, String> convertCoordsToAddress(String latitude, String longitude, String key) throws Exception {
		String city = "";
		String state, country = "";
		Map<String, String> locationMap = new HashMap<String, String>();
		int responseCode = 0;
		String api = "https://maps.googleapis.com/maps/api/geocode/xml?latlng=" + latitude + "," + longitude + "&key="
				+ key + "&sensor=true/false";

		URL url = new URL(api);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect();
		responseCode = httpConnection.getResponseCode();

		if (responseCode == 200) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document document = builder.parse(httpConnection.getInputStream());

			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName("address_component");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (eElement.getElementsByTagName("type").item(0).getTextContent().equals("locality")) {
						city = eElement.getElementsByTagName("long_name").item(0).getTextContent();
						locationMap.put("city", city);
					}
					if (eElement.getElementsByTagName("type").item(0).getTextContent()
							.equals("administrative_area_level_1")) {
						state = eElement.getElementsByTagName("long_name").item(0).getTextContent();
						locationMap.put("state", state);
					}
					if (eElement.getElementsByTagName("type").item(0).getTextContent().equals("country")) {
						country = eElement.getElementsByTagName("long_name").item(0).getTextContent();
						locationMap.put("country", country);
					}
				}
			}
		}
		return locationMap;
	}

}
