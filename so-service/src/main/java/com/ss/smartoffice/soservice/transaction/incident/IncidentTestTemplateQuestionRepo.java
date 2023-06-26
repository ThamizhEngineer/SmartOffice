package com.ss.smartoffice.soservice.transaction.incident;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IncidentTestTemplateQuestionRepo extends CrudRepository<IncidentTestTemplateQuestion, Integer>{
	@Modifying
	@Query("delete from com.ss.smartoffice.soservice.transaction.incident.IncidentTestTemplateQuestion ittq where ittq.id=:id")
	void delete(@Param("id") int id);
}
