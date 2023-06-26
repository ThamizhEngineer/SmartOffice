package com.ss.smartoffice.soservice.master.employee;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.employee.LanguagesKnown;
@Scope("prototype")
public interface LanguageRepository extends CrudRepository<LanguagesKnown, Integer> {

//
//List<LanguagesKnown> deleteById(int id);
//
////	void delete(LanguagesKnown deleted);
////void save(List<LanguagesKnown> employee1);
//void save(List<LanguagesKnown> employee1);
//public void delete(LanguagesKnown obj) ;
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.employee.LanguagesKnown language where language.id= ?1")
	void delete(int id);

}
