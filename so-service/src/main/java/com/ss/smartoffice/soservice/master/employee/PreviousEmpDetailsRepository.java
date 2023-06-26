package com.ss.smartoffice.soservice.master.employee;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.employee.PreviousEmploymentDetails;
@Scope("prototype")
public interface PreviousEmpDetailsRepository extends CrudRepository<PreviousEmploymentDetails, Integer>{
	
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.employee.PreviousEmploymentDetails prevEmpDetails where prevEmpDetails.id= ?1")
	void delete(int id);

}
