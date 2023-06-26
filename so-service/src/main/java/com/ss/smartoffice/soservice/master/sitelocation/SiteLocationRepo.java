package com.ss.smartoffice.soservice.master.sitelocation;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SiteLocationRepo extends CrudRepository<SiteLocation, Integer> {

	@Query("select sl from com.ss.smartoffice.soservice.master.sitelocation.SiteLocation sl where "
			+ "ifnull(LOWER(sl.siteName),'') LIKE LOWER(CONCAT('%',ifnull(:siteName,''),'%')) AND "
			+ "ifnull(LOWER(sl.country),'') LIKE LOWER(CONCAT('%',ifnull(:country,''),'%')) AND "
			+ "ifnull(LOWER(sl.endClientName),'') LIKE LOWER(CONCAT('%',ifnull(:endClientName,''),'%')) AND "
			+ "ifnull(LOWER(sl.contactName),'') LIKE LOWER(CONCAT('%',ifnull(:contactName,''),'%')) AND "
			+ "ifnull(LOWER(sl.mobileNumber),'') LIKE LOWER(CONCAT('%',ifnull(:mobileNumber,''),'%'))")
	List<SiteLocation> fetchAll(String siteName, String country, String endClientName, String contactName, String mobileNumber);
	
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.master.sitelocation.SiteLocation sl where sl.id=:id")
	void deleteByIdQuery(@Param("id") Integer id);
}
