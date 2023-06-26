package com.ss.smartoffice.soservice.master.offices;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface OfficeRepository extends CrudRepository<Office, Integer>{

	
	@Query("select new com.ss.smartoffice.soservice.master.offices.Office(ofc.id,ofc.officeName,ofc.officeCode,ofc.description,ofc.countryCode,ofc.employeeId,ofc.empName,ofc.isEnabled,ofc.createdBy,ofc.modifiedBy,ofc.createdDt,ofc.modifiedDt) "
			+ "from Office ofc where ifnull(LOWER(ofc.countryCode),'') LIKE LOWER(CONCAT('%',ifnull(:countryCode,''),'%'))"
			+ "AND ifnull(LOWER(ofc.employeeId),'') LIKE LOWER(CONCAT('%',ifnull(:employeeId,''), '%')) "
			+ "AND ifnull(LOWER(ofc.officeName),'') LIKE LOWER(CONCAT('%',ifnull(:officeName,''), '%'))")
	List<Office>findBySummaries(@Param("countryCode")String countryCode,@Param("employeeId")String employeeId,@Param("officeName")String officeName);
	
	Office findByOfficeCode(String officeCode);
}

