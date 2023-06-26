package com.ss.smartoffice.soservice.seed.calenderYear;

import org.springframework.data.repository.CrudRepository;

public interface CalenderYearRepo extends CrudRepository<CalenderYear, Integer>{

	CalenderYear findByYear(String year);
	
}
