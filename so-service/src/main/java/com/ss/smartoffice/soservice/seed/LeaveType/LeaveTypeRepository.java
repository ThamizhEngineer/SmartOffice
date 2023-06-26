package com.ss.smartoffice.soservice.seed.LeaveType;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface LeaveTypeRepository extends CrudRepository<LeaveType, Integer> {

}
