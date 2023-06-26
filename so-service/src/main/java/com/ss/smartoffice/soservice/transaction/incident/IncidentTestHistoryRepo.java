package com.ss.smartoffice.soservice.transaction.incident;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface IncidentTestHistoryRepo extends CrudRepository<IncidentTestHistory, Integer> {
	

	@Query("select i from com.ss.smartoffice.soservice.transaction.incident.IncidentTestHistory i where i.incidentTestId =:incidentTestId")
	Optional<IncidentTestHistory> fetchByIncidentTestId(Integer incidentTestId);
	
	@Query("select i from com.ss.smartoffice.soservice.transaction.incident.IncidentTestHistory i where i.incidentId =:incidentId and i.participantId =:participantId and i.incidentTtId =:incidentTtId")
	List<IncidentTestHistory> fetchByIncidentId(Integer incidentId,Integer participantId,String incidentTtId);
	
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.incident.IncidentTestHistory i where i.incidentId =:incidentId")
	void deleteTestHistory(Integer incidentId);
}
