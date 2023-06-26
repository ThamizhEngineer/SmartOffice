package com.ss.smartoffice.soservice.transaction.maplocation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.MapLocation.MapLocation;
import com.ss.smartoffice.shared.MapLocation.MapLocationRepository;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.geoloc.GeoLocationHelper;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("map-locations")
public class MapLocationService {

@Autowired
MapLocationRepository mapLocationRepository;

@Autowired
CommonUtils commonUtils;

@Autowired
GeoLocationHelper  geoLocationHelper;

@GetMapping
private Iterable<MapLocation> getAllMapLocations(@RequestParam(value = "locName")String locName,@RequestParam(value = "address")String address,@RequestParam(value = "locType")String locType,@RequestParam(value = "country")String country)throws SmartOfficeException{	
	return mapLocationRepository.findByMapLocation(locName, address, locType, country);
	
}

@GetMapping("/all")
private Iterable<MapLocation> getMapLocations()throws SmartOfficeException{
	return mapLocationRepository.findAll();
	
}
@GetMapping("/{id}")
private Optional<MapLocation>getMapLocationById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return mapLocationRepository.findById(id);
}
@PostMapping
private List<MapLocation> addOrUpdateMapLocation(@RequestBody List<MapLocation> mapLocations)throws SmartOfficeException{
	for(MapLocation mapLocation:mapLocations) {
		List<MapLocation> mapLocationsCheck = mapLocationRepository.duplicateCheck(mapLocation.getLats(),mapLocation.getLongs(),mapLocation.getLocName().toLowerCase(),mapLocation.getAddress().toLowerCase());
		if(mapLocationsCheck.isEmpty()) {
			if(mapLocation.getId()!=null) {
				mapLocation.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				mapLocation.setCreatedDt(LocalDateTime.now());
				mapLocationRepository.save(mapLocation);
			}else {
				mapLocation.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				mapLocation.setModifiedDt(LocalDateTime.now());
				mapLocationRepository.save(mapLocation);
			}
		}else {
			throw new SmartOfficeException("Latitude & Longitude Have Already Created");
		}
		
	}
	return mapLocations;
}

@GetMapping("search-geoposition")
private List<MapLocation>forwardGeoPostioning(@RequestParam(value = "loc-name", required = true) String locName,@RequestParam(value = "address", required = true) String address,@RequestParam(value = "country-code", required = true) String countryCode, @RequestParam(value = "limit", required = true) int limit)throws SmartOfficeException{
	return geoLocationHelper.forwardGeoPostioning(locName, address, countryCode, limit);
}
}
