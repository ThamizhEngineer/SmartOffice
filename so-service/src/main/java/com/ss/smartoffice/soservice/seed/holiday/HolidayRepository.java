package com.ss.smartoffice.soservice.seed.holiday;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;
@Scope("prototype")
public interface HolidayRepository extends CrudRepository<Holiday, Integer> {

//	List<Holiday> findAll(Long yearFromDate);
	List<Holiday> findByHolidayYear(String holidayYear);
//	//  Extract year from date - Holiday 
//	@Query("SELECT new com.ss.smartoffice.soservice.seed.holiday.Holiday(h.id.h.holidayName,h.holidayDt,h.firstSession,h.restrictedHoliday,h.locationId,h.isEnabled,h.createdBy,h.modifiedBy,h.createdDt,h.modifiedDt,h.secondSession,h.isAdhoc,h.holidayYear)\n"
//			+"from Holiday h where ifnull(LOWER(h.holidayYear),'') LIKE LOWER(CONCAT('%',ifnull(:holidayYear,''),'%'))")
//	List<Holiday> holidaysFromCurrentYear(@Param("holidayYear")String holidayYear);

}
