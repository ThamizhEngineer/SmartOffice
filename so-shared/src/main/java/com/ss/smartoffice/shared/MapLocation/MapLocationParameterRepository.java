package com.ss.smartoffice.shared.MapLocation;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface MapLocationParameterRepository extends CrudRepository<MapLocationParameter, Integer> {
	
	MapLocationParameter findByAddressAndCountryCodeAndStatusAndLocName(String address,String countryCode,String status,String locName);

}
