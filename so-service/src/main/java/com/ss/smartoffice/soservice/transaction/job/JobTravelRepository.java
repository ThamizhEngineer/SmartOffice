package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobTravel;
@Scope("prototype")
public interface JobTravelRepository extends CrudRepository<JobTravel, Integer>{
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobTravel jobTravel where jobTravel.id= ?1")
	void delete(int id);
	
	@Query("select jobtravel from com.ss.smartoffice.soservice.transaction.model.JobTravel jobtravel where jobtravel.tJobId=:tJobId")
	List<JobTravel> findByTJobId(String tJobId);
}
