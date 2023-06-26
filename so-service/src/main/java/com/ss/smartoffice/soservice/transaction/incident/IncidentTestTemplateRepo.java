package com.ss.smartoffice.soservice.transaction.incident;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IncidentTestTemplateRepo extends CrudRepository<IncidentTestTemplate, Integer> {

	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.incident.IncidentTestTemplate itt where itt.id= ?1")
	void delete(int id);
}
