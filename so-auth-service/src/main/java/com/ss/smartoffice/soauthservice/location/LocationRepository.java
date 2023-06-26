package com.ss.smartoffice.soauthservice.location;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.Location;


public interface LocationRepository extends CrudRepository<Location, Integer>  {
	
	@Query("select i from com.ss.smartoffice.shared.model.Location i "
			+ "where ifnull(LOWER(i.attendanceStatus),'') LIKE LOWER(CONCAT('%',ifnull(:attendanceStatus,''), '%')) AND ifnull(LOWER(i.dateTime),'') LIKE LOWER(CONCAT('%',ifnull(:dateTime,''), '%'))  ")
	List<Location>fetchByAttendanceStatusAndDateTime(@Param("attendanceStatus")String attendanceStatus,@Param("dateTime")String dateTime);
	
	@Query("select i from com.ss.smartoffice.shared.model.Location i "
			+ "where i.employeeId=:employeeId ORDER BY i.dateTime DESC")
	List<Location>findByEmployeeId(@Param("employeeId")String employeeId);
	
	@Query("select i from com.ss.smartoffice.shared.model.Location i "
			+ "where i.city=:city ORDER BY i.dateTime DESC")
	List<Location>findByCity(@Param("city")String city);
	
	@Query("select i from com.ss.smartoffice.shared.model.Location i "
			+ "where i.state=:state ORDER BY i.dateTime DESC")
	List<Location>findByState(@Param("state")String state);
	
	@Query("select i from com.ss.smartoffice.shared.model.Location i "
			+ "where i.country=:country ORDER BY i.dateTime DESC")
	List<Location>findByCountry(@Param("country")String country);
	
	@Query("select DISTINCT(i.city) from com.ss.smartoffice.shared.model.Location i ")
	List<String> findByDistintCity();

	@Query("select DISTINCT(i.state) from com.ss.smartoffice.shared.model.Location i ")
	List<String>findByDistintState();
	
	@Query("select DISTINCT(i.country) from com.ss.smartoffice.shared.model.Location i ")
	List<String>findByDistintCountry();
}
