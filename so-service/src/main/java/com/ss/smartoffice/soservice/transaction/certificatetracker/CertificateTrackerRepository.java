package com.ss.smartoffice.soservice.transaction.certificatetracker;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CertificateTrackerRepository extends CrudRepository<CertificateTracker, Integer>{
List<CertificateTracker> findByIncidentApplicantId(String incidentApplicantId);
}
