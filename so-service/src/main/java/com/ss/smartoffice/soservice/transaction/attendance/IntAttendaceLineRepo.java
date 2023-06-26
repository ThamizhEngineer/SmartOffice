package com.ss.smartoffice.soservice.transaction.attendance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IntAttendaceLineRepo extends CrudRepository<IntAttendanceLine, Integer> {

	List<IntAttendanceLine> findByAttendanceHdrId(String hdrId);
}
