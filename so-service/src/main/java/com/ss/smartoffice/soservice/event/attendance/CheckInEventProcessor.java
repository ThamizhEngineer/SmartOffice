package com.ss.smartoffice.soservice.event.attendance;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ss.smartoffice.shared.common.CommonUtils;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.attendance.Attendance;
import com.ss.smartoffice.shared.model.attendance.AttendanceRepository;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.model.employee.LocationSeed;

import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.transaction.job.JobService;
import com.ss.smartoffice.soservice.transaction.attendance.DirectAttendanceService;
import com.ss.smartoffice.soservice.transaction.event.Event;

@Service
public class CheckInEventProcessor {
	@Autowired
	JobService jobService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	DirectAttendanceService directAttendanceService;

	// @Autowired
	// UserRepository userRepository;

	@Value("${ignore.jobdistance}")
	private String ignoreJobDistance;

	@Value("${default.attendancecode}")
	private String defaultAttendanceCode;

	@Value("${maximum.location.distance}")
	private String maximumLocationDistance;
	@Value("${emp.attendance.dist-to-loc}")
	private double empAttendanceDistance;
	private static final String key = "AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg";

	public Event process(Event event) throws Exception {
		try {
			String lats = event.getBusinessKeys().get(0).getLatitudes();
			String longtitude = event.getBusinessKeys().get(0).getLongtitudes();
			String locationId = event.getBusinessKeys().get(0).getKey1();
			if (event.getBusinessKeys() != null) {
				List<Attendance> attendances = new ArrayList<Attendance>();
				Integer employeeId = Integer.parseInt(event.getBusinessKeys().get(0).getEmployeeId());
				memployee memployee = employeeService.getEmployeeById(employeeId).get();

				if (ignoreJobDistance.equals("Y")) {
					attendances = attendanceRepository.findByEmployeeIdAndAttendanceDt(
							event.getBusinessKeys().get(0).getEmployeeId(), LocalDate.now());
					if (!attendances.isEmpty() && attendances != null && memployee != null) {

						// checking if an attendance record already exists for that memployee on the //
						// same // date
						Attendance existingattendance = new Attendance();

						// If Exist Updating only the coordinates

						for (Attendance attendance : attendances) {
							existingattendance.setId(attendance.getId());
							existingattendance.setAttendanceDt(LocalDate.now());
							existingattendance.setStartDt(LocalDateTime.now());
							existingattendance.setInLats(lats);
							existingattendance.setInLongs(longtitude);
							existingattendance.setEmployeeId(String.valueOf(employeeId));
							existingattendance.setCheckIn(LocalTime.now());
							existingattendance.setFirstSession(defaultAttendanceCode);
							existingattendance.setSecondSession(defaultAttendanceCode);
							existingattendance.setAttendanceStatus("FINAL");
							existingattendance = checkDistance(memployee, existingattendance, lats, longtitude);
							directAttendanceService.updateAttendance(existingattendance);
						}
					}
					// Creating new Attendance Record For an memployee
					else {
						Attendance newattendance = formAttendance(memployee, lats, longtitude, locationId, employeeId);
						directAttendanceService.processAddAttendanceById(newattendance);
						System.out.println(newattendance);
					}
				}
			} else {
				throw new SmartOfficeException("key values in empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		return event;
	}

	public Attendance formAttendance(memployee memployee, String lats, String longtitude, String locationId,
			Integer employeeId) throws Exception {
		Attendance newattendance = new Attendance();
		newattendance.setStartDt(LocalDateTime.now());
		newattendance.setAttendanceDt(LocalDate.now());
		newattendance.setInLats(lats);
		newattendance.setInLongs(longtitude);
		newattendance.setCheckIn(LocalTime.now());
		newattendance = checkDistance(memployee, newattendance, lats, longtitude);
		newattendance.setEmployeeId(String.valueOf(employeeId));
		newattendance.setFirstSession(defaultAttendanceCode);
		newattendance.setSecondSession(defaultAttendanceCode);
		newattendance.setAttendanceStatus("CREATED");
		newattendance.setLocationId(locationId);
		return newattendance;
	}

	public Attendance checkDistance(memployee memployee, Attendance newattendance, String lats, String longtitude)
			throws Exception {
		Double distance;
		String jobLats, jobLongs;
		for (LocationSeed jobLocation : memployee.getEmployeeLocations()) {
			jobLats = jobLocation.getLats();
			jobLongs = jobLocation.getLongs();
			distance = CommonUtils.calculateDistance(Double.parseDouble(jobLats), Double.parseDouble(jobLongs),
					Double.parseDouble(lats), Double.parseDouble(longtitude));
			double meter = 1000;
			double distanceInMeter = ((double) distance) / meter;
			if (distanceInMeter <= empAttendanceDistance) {
				String sameLocation;
				sameLocation = jobLocation.getLocationName();
				newattendance.setInLocName(sameLocation);
				break;
			} else {
				Map<String, String> locationMap = convertCoordsToAddress(lats, longtitude, key);
				String diffLocation = locationMap.get("city") + "," + locationMap.get("country");
				newattendance.setInLocName(diffLocation);
				break;
			}
		}
		return newattendance;
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
