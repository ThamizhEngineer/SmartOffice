package com.ss.smartoffice.soservice.master.AcademicType;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

@Scope("prototype")
public interface AcademicTypeRepository extends CrudRepository<AcademicType, Integer>{
}
