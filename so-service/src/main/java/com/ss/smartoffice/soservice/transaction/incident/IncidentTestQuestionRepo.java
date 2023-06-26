package com.ss.smartoffice.soservice.transaction.incident;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Scope("prototype")
public interface IncidentTestQuestionRepo extends CrudRepository<IncidentTestQuestion, Integer>{

	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.incident.IncidentTestQuestion itq where itq.id= ?1")
	void delete(int id);
}
