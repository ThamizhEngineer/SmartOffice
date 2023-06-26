package com.ss.smartoffice.soservice.event.attendance;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ss.smartoffice.shared.common.BaseEntity;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.attendance.Attendance;
import com.ss.smartoffice.shared.model.attendance.AttendanceRepository;
import com.ss.smartoffice.shared.model.employee.LocationSeed;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.transaction.attendance.DirectAttendanceService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.Event;
@Service
public class CheckOutEventProcessor extends BaseEntity {
	@Autowired
	EmployeeService employeeService;

	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Value("${default.attendancecode}")
	private String defaultAttendanceCode;
	@Value("${emp.attendance.dist-to-loc}")
	private double empAttendanceDistance;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	DirectAttendanceService directAttendanceService;
	private static final String key = "AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg";
	public Event process(Event event) throws Exception {
		String lats=null;
		String longtitude=null;
		try {
//			Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
			String jobLats, eventLats, jobLongs, eventLongs;
			Double distance ;
			
		for(BusinessKey businessKey:event.getBusinessKeys()) {
			lats=businessKey.getLatitudes();
			longtitude=businessKey.getLongtitudes();
		}
			
			
			Integer employeeId=Integer.parseInt(event.getBusinessKeys().get(0).getEmployeeId());
			
			List<Attendance> attendances= new ArrayList<>();
			attendances=attendanceRepository.findByAttendanceDtAndEmployeeId(LocalDate.now(), String.valueOf(employeeId));
			System.out.println(attendances);
			if(!attendances.isEmpty()) {
				for(Attendance attendance :attendances) {
			Attendance attendance1= new Attendance();
			attendance1.setId(attendance.getId());
			attendance1.setCheckIn(attendance.getCheckIn());
			attendance1.setCheckOut(LocalTime.now());
			attendance1.setFirstSession(attendance.getFirstSession());
			attendance1.setSecondSession(attendance.getSecondSession());
			attendance1.setAttendanceStatus(attendance.getAttendanceStatus());
			attendance1.setAttendanceDt(LocalDate.now());
			attendance1.setCreatedDt(attendance.getCreatedDt());
			attendance1.setStartDt(attendance.getStartDt());
			attendance1.setEndDt(LocalDateTime.now());
			Long totalLoggedHours = ChronoUnit.HOURS.between(attendance1.getStartDt(), attendance1.getEndDt());
			attendance1.setTotalTimeLogged(totalLoggedHours.intValue());
			attendance1.setEmployeeId(String.valueOf(employeeId));
			attendance1.setInLats(attendance.getInLats());
			attendance1.setInLongs(attendance.getInLongs());
			attendance1.setInLocName(attendance.getInLocName());
			memployee memployee= employeeService.getEmployeeById(Integer.parseInt(attendance.getEmployeeId())).get();
			 for(LocationSeed jobLocation:memployee.getEmployeeLocations()) {
				  
					jobLats = jobLocation.getLats();
					System.out.println(jobLats);
					jobLongs = jobLocation.getLongs();
					
					distance = commonUtils.calculateDistance(Double.parseDouble(jobLats), Double.parseDouble(jobLongs),
							Double.parseDouble(lats), Double.parseDouble(longtitude));
					double meter=1000;
					double distanceInMeter = ((double)distance)/meter;	
					System.out.println(distanceInMeter);
					
					
					if(distanceInMeter <= empAttendanceDistance) {
						
						String sameLocation =jobLocation.getLocationName();
						attendance1.setOutLocName(sameLocation);
						System.out.println(sameLocation+ "same");
						break;
						
					}else {
						
					Map<String, String> locationMap = convertCoordsToAddress(lats, longtitude, key);
					String diffLocation=locationMap.get("city") +","+ locationMap.get("country");
					attendance1.setOutLocName(diffLocation);
					System.out.println(diffLocation+ "diff");
					}break;
					}
			attendance1.setOutLats(lats);
			attendance1.setOutLongs(longtitude);
			System.out.println(attendance1);
			directAttendanceService.updateAttendance(attendance1);
			
			}
		} 
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
		
		
		return event;
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
	


