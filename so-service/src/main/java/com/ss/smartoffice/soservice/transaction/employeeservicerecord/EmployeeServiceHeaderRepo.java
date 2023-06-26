package com.ss.smartoffice.soservice.transaction.employeeservicerecord;

import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import com.ss.smartoffice.soservice.transaction.incident.IncidentTest;

@Repository
@Scope("prototype")
public interface EmployeeServiceHeaderRepo extends CrudRepository<EmployeeServiceHeader,Integer>  {

	
	@Query("select i from com.ss.smartoffice.soservice.transaction.employeeservicerecord.EmployeeServiceHeader i "
			+ "where ifnull(LOWER(i.internalId),'') LIKE LOWER(CONCAT('%',ifnull(:internalId,''), '%'))  AND ifnull(LOWER(i.empCode),'') LIKE LOWER(CONCAT('%',ifnull(:empCode,''), '%')) AND ifnull(LOWER(i.empFname),'') LIKE LOWER(CONCAT('%',ifnull(:empFname,''), '%'))  "
			+ "AND ifnull(LOWER(i.officeName),'') LIKE LOWER(CONCAT('%',ifnull(:officeName,''), '%')) AND ifnull(LOWER(i.n1EmpId),'') LIKE LOWER(CONCAT('%',ifnull(:n1EmpId,''), '%'))")
	List<EmployeeServiceHeader> fetchByAdvanceFilter(
			@Param("internalId")String internalId,@Param("empCode")String empCode,
			@Param("empFname")String empFname,@Param("officeName")String officeName,@Param("n1EmpId")String n1EmpId);
}


//n1EmpId
