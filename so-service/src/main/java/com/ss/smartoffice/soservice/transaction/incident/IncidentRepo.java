package com.ss.smartoffice.soservice.transaction.incident;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IncidentRepo extends CrudRepository<Incident, Integer>{

	@Query("select incident from com.ss.smartoffice.soservice.transaction.incident.Incident incident where incident.hr1UsrGrpId =:hr1UserGroupId or incident.hr2UsrGrpId= :hr2UserGroupId ORDER BY incident.id DESC")
	List<Incident> findByHr1UsrGrpIdOrHr2UsrGrpId(String hr1UserGroupId,String hr2UserGroupId);
	
	
	@Query("select incident from com.ss.smartoffice.soservice.transaction.incident.Incident incident where incident.incidentType =:incidentType AND incident.incidentStatus='OPEN' ORDER BY incident.id DESC")
	List<Incident> findByIncidentType(String incidentType);
}
