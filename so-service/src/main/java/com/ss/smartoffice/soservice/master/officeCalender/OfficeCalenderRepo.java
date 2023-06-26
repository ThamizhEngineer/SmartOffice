package com.ss.smartoffice.soservice.master.officeCalender;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ss.smartoffice.shared.model.OfficeCalender;

public interface OfficeCalenderRepo extends CrudRepository<OfficeCalender, Integer> {

	List<OfficeCalender>findByCalYearIdAndOfficeIdAndDayType(String yearId,String officeId,String dayType);
	
	List<OfficeCalender> findByCalYearIdAndOfficeIdAndMonth(String yearId,String officeId,String month);
	
	List<OfficeCalender> findByCalYearIdAndOfficeId(String yearId,String officeId);
	
	List<OfficeCalender> findByYearAndMonthAndOfficeId(String year,String month,String officeId);
	
	OfficeCalender findByCalDateAndOfficeId(LocalDate calDate,String officeId);
	
	@Query("Select oc from com.ss.smartoffice.shared.model.OfficeCalender oc where oc.dayType IS NULL And oc.officeShiftCode=:ShiftCode")
	List<OfficeCalender> findByDayTypeIsNull(String ShiftCode);
	
	@Query("Select COUNT(oc) from com.ss.smartoffice.shared.model.OfficeCalender oc  where oc.calYearId=:yrId AND DATE_FORMAT(oc.calDate,'%m')=:MONTH AND oc.officeId=:offID AND oc.dayType='weekend'")
	String weekendCount(@Param("yrId") String yearId,@Param("MONTH") String month,@Param("offID") String officeId);
	
	@Query("Select COUNT(oc) from com.ss.smartoffice.shared.model.OfficeCalender oc  where oc.calYearId=:yrId AND DATE_FORMAT(oc.calDate,'%m')=:MONTH AND oc.officeId=:offID AND oc.dayType='weekday'")
	String weekdaysCount(@Param("yrId") String yearId,@Param("MONTH") String month,@Param("offID") String officeId);
	
	@Query("Select COUNT(oc) from com.ss.smartoffice.shared.model.OfficeCalender oc  where oc.calYearId=:yrId AND DATE_FORMAT(oc.calDate,'%m')=:MONTH AND oc.officeId=:offID AND oc.dayType='holiday'")
	String holidaysCount(@Param("yrId") String yearId,@Param("MONTH") String month,@Param("offID") String officeId);
	
	@Query("Select oc from com.ss.smartoffice.shared.model.OfficeCalender oc where oc.officeId=:offID And oc.month=:month AND oc.calYearId=:yearId")
	List<OfficeCalender> groupByMonth (@Param("offID")String officeId,@Param("month") String month,@Param("yearId")String yearId);
	
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.OfficeCalender oc where oc.officeId=:officeId AND oc.calYearId=:yrId")
	void deleteOfficeCalender(@Param("officeId") String officeId,@Param("yrId") String yearId);
}
