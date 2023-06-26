package com.ss.smartoffice.soservice.master.employee;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.employee.AcademicAcheiv;
@Scope("prototype")
public interface AcademicAcheivRepository extends CrudRepository<AcademicAcheiv, Integer>{
	
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.employee.AcademicAcheiv acaAcheiv where acaAcheiv.id= ?1")
	void delete(int id);

}
