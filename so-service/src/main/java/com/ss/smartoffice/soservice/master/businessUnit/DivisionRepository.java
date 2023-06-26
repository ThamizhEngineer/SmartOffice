package com.ss.smartoffice.soservice.master.businessUnit;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DivisionRepository extends CrudRepository<Division, Integer>{
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.master.businessUnit.Division subUnit where subUnit.id= ?1")
	void delete(int id);
	@Query("SELECT new com.ss.smartoffice.soservice.master.businessUnit.Division (d.id,d.mBuId,d.mBuisnessName,d.divisionName) FROM Division as d ")
	List<Division>getAllDivision();
}
