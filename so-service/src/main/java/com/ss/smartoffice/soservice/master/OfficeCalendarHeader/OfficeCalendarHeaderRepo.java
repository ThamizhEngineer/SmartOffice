package com.ss.smartoffice.soservice.master.OfficeCalendarHeader;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ss.smartoffice.shared.model.OfficeCalendarHeader;

public interface OfficeCalendarHeaderRepo extends CrudRepository<OfficeCalendarHeader, Integer> {
	
	List<OfficeCalendarHeader> findByCalYearId(String yearId);
	
	OfficeCalendarHeader findByCalYearIdAndOfficeId(String calYearId,String officeId);

}
