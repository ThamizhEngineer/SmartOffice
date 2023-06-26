package com.ss.smartoffice.soservice.master.AcademicCollege;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface AcademicCollegeRepository  extends CrudRepository<AcademicCollege, Integer>{

}
