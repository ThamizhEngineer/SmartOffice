package com.ss.smartoffice.soservice.temp.jobemployee;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempJobRepo extends CrudRepository<TempJob,Integer> {

  
	
}
