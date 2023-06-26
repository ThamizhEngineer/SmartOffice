package com.ss.smartoffice.soservice.seed.Grade;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface GradeRepository extends CrudRepository<Grade, Integer>{

}
