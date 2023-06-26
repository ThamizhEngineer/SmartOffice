package com.ss.smartoffice.soservice.master.AcademicDegree;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface AcademicDegreeRepository extends CrudRepository<AcademicDegree, Integer> {

}
