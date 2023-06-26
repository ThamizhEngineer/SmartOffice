package com.ss.smartoffice.soservice.seed.CountryService;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface CountryStateRepository extends CrudRepository<CountryState, Integer> {
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.seed.CountryService.CountryState countryState where countryState.id= ?1")
	void delete(int id);
}
