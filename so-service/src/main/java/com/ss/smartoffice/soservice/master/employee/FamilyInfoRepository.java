package com.ss.smartoffice.soservice.master.employee;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.employee.FamilyInfo;
@Scope("prototype")
public interface FamilyInfoRepository extends CrudRepository<FamilyInfo, Integer> {
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.employee.FamilyInfo family where family.id= ?1")
	void delete(int id);


}
