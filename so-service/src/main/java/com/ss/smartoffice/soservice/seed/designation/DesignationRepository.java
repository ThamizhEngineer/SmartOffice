package com.ss.smartoffice.soservice.seed.designation;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface DesignationRepository extends CrudRepository<Designation, Integer>{
	Designation findByDesigName(String desigName);
}
