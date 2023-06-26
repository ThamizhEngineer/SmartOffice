package com.ss.smartoffice.soservice.master.employee;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.employee.EducationalInfo;
@Scope("prototype")
public interface EducationalInfoRepository extends CrudRepository<EducationalInfo, Integer> {
	

	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.employee.EducationalInfo eduInfo where eduInfo.id= ?1")
	void delete(int id);

}
