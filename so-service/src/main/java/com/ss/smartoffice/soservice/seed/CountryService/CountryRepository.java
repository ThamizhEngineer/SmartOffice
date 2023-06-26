package com.ss.smartoffice.soservice.seed.CountryService;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface CountryRepository extends CrudRepository<Country, Integer>{

}
