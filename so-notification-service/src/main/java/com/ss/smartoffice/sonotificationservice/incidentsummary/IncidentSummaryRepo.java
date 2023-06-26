package com.ss.smartoffice.sonotificationservice.incidentsummary;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IncidentSummaryRepo extends CrudRepository<Incident, Integer> {
	@Query("select count(*) from Incident incident where incident.incidentType='Recruitment'and incident.incidentStatus='PENDING-APPROVAL'")
	Long incidentRecruitCount();
	@Query("select count(*) from Incident incident where incident.incidentType='Training'and incident.incidentStatus='PENDING-APPROVAL'")
	Long incidentTrainingCount();
}
