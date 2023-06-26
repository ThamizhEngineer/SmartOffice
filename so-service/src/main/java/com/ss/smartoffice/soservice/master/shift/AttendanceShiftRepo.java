package com.ss.smartoffice.soservice.master.shift;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AttendanceShiftRepo extends CrudRepository<AttendanceShift, Integer> {
	
	@Query( "select shift from com.ss.smartoffice.soservice.master.shift.AttendanceShift shift where LOWER(shift.shiftCode)=LOWER(:code)" )
	AttendanceShift findDuplicate(@Param("code") String code);
}
