package com.ss.smartoffice.soauthservice.location;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.attendance.Attendance;


public interface AuthAttendanceRepository extends CrudRepository<Attendance, Integer> {
List<Attendance> findByCheckOut(LocalTime checkOut);
List<Attendance> findByEmployeeId(String employeeId);
}
