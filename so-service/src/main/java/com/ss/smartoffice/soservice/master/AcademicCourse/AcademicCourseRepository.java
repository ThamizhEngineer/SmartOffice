package com.ss.smartoffice.soservice.master.AcademicCourse;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface AcademicCourseRepository extends CrudRepository<AcademicCourse, Integer>{

}
