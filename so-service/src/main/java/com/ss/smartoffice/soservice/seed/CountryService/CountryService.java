package com.ss.smartoffice.soservice.seed.CountryService;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("seed/countries")
@Scope("prototype")
public class CountryService {
@Autowired
CountryRepository countryRepository;
@Autowired
CountryStateRepository countryStateRepository;
//@CrossOrigin(origins = "*")
@GetMapping
public Iterable<Country> getAllCountries()throws SmartOfficeException{
	return countryRepository.findAll();
	
}
//@CrossOrigin(origins = "*")
@GetMapping("/{id}")
public Optional<Country> getCountryById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return countryRepository.findById(id);
	
}
//@CrossOrigin(origins = "*")
@PostMapping
public Country addCountry(@RequestBody Country country)throws SmartOfficeException{
	if(country.getEmpAbbreviation()!=null&&!country.getEmpAbbreviation().isEmpty()&&country.getCountryCode()!=null&&!country.getCountryCode().isEmpty()) {
	return countryRepository.save(country);
	}else {
		throw new SmartOfficeException("Mandatory fields shoulb be given in order to create new country record");
	}
	
}
//@CrossOrigin(origins = "*")
@PatchMapping("/{id}")
public Country updateCountry(@RequestBody Country country)throws SmartOfficeException{
	return countryRepository.save(country);
	
}
//@CrossOrigin(origins = "*")
@PatchMapping("/{id}/lines")

public Country addOrUpdateLines(@RequestBody Country country)throws SmartOfficeException{
	if(country.getStates()!=null &&!country.getStates().isEmpty()) {
		for(CountryState countryState:country.getStates()) {
			countryStateRepository.save(countryState);
		}
	}
	return country;
	
}
//@CrossOrigin(origins = "*")
@DeleteMapping("/{id}")
public void deletePartnerById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	countryRepository.deleteById(id);
}
//@CrossOrigin(origins = "*")
@Transactional

@DeleteMapping("/{id}/delete/lines")
public void deletePartnerLines(@PathVariable(value = "id") int id) throws SmartOfficeException {
	Country country = getCountryById(id).get();
	if(country.getStates()!=null &&!country.getStates().isEmpty()) {
		for(CountryState countryState:country.getStates()) {

			countryStateRepository.delete(countryState.getId());
		}
	}else {
		throw new SmartOfficeException("No Client Specific Details Present");
	}
}
}
