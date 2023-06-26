package com.ss.smartoffice.soservice.seed.location;
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
import org.springframework.web.bind.annotation.RestController;


import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.LocationSeed;
@RestController
@RequestMapping(path="seed/locations")


public class LocationSeedService {
	@Autowired
	LocationSeedRepository locationSeedRepository;
	
	
	//@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<LocationSeed> getLocation() throws SmartOfficeException{	
		return locationSeedRepository.findAll();
		
	}
	//@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	
	public Optional<LocationSeed> getLocationById(@PathVariable (value="id")int id) throws SmartOfficeException{
		return locationSeedRepository.findById(id);
		
	}
	//@CrossOrigin(origins="*")
	@PostMapping
	
	public LocationSeed addLocationById(@RequestBody LocationSeed locationSeed) throws SmartOfficeException{
		return locationSeedRepository.save(locationSeed);
		
	}
	//@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	
	public LocationSeed updateLocationById(@RequestBody LocationSeed locationSeed)throws SmartOfficeException{
		return locationSeedRepository.save(locationSeed);
		
	}
	//@CrossOrigin(origins="*")
	@DeleteMapping("{/id}")
	
	public void deleteLocationById(@PathVariable(value="id")int id) throws SmartOfficeException{
		locationSeedRepository.deleteById(id);
	}
	

	
}
