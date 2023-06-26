package com.ss.smartoffice.soservice.transaction.maplocation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.MapLocation.MapLocation;
import com.ss.smartoffice.shared.MapLocation.MapLocationParameter;
import com.ss.smartoffice.shared.MapLocation.MapLocationParameterRepository;
import com.ss.smartoffice.shared.model.SmartOfficeException;
@RestController
@RequestMapping("transaction/map-location-parameter")
@Scope("prototype")
public class MapLocationParameterService  {

	@Autowired
	MapLocationParameterRepository mapLocationParameterRepository;
	
	@GetMapping
	private Iterable<MapLocationParameter> getMapLocationParameters()throws SmartOfficeException{
		return mapLocationParameterRepository.findAll();
		
	}
	@GetMapping("/{id}")
	private MapLocationParameter getMapLocationParameterById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return mapLocationParameterRepository.findById(id).get();
	}
	
	@GetMapping("/search")
	private MapLocationParameter getByAddressAndCountryCodeAndStatusAndLocName(@RequestParam(value = "loc-name", required = true) String locName,@RequestParam(value = "address", required = true) String address,@RequestParam(value = "country-code", required = true) String countryCode, @RequestParam(value = "limit", required = false) int limit,@RequestParam(value = "status", required = true) String status)throws SmartOfficeException{
		return mapLocationParameterRepository.findByAddressAndCountryCodeAndStatusAndLocName(address, countryCode, status, locName);
	}
	@PostMapping
	private MapLocationParameter addMapLocationParameter(@RequestBody MapLocationParameter mapLocationParameter)throws SmartOfficeException{
		return mapLocationParameterRepository.save(mapLocationParameter);
	}
	
	@PatchMapping("/{id}")
	private MapLocationParameter updateMapLocationParameter(@RequestBody MapLocationParameter mapLocationParameter)throws SmartOfficeException{
		return mapLocationParameterRepository.save(mapLocationParameter);
	}
	
}
