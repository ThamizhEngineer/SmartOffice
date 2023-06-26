package com.ss.smartoffice.soservice.transaction.certificatetracker;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim;
@RestController
@RequestMapping(path ="/transaction/certificate-trackers")
public class CertificateTrackerService {
@Autowired
CertificateTrackerRepository certificateTrackerRepository;

@GetMapping
public Iterable<CertificateTracker>getCertificateTrackers()throws SmartOfficeException{
	return certificateTrackerRepository.findAll();
}
@GetMapping("/{id}")
public Optional<CertificateTracker> getCertificateTrackerById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return certificateTrackerRepository.findById(id);
}
@GetMapping("/{incidentApplicantId}/incident-applicant")
public List<CertificateTracker> getCertificateByApplicant(@PathVariable(value="incidentApplicantId")String incidentApplicantId)throws SmartOfficeException{
	return certificateTrackerRepository.findByIncidentApplicantId(incidentApplicantId);
	
}
//@PatchMapping("/{id}/generate-certificate")

@PostMapping("/_internal/{id}/certificate-pdf")
public CertificateTracker updateApplicantByPdfId(@RequestBody CertificateTracker certificateTracker) throws SmartOfficeException {
	CertificateTracker certificateTrackerFromDb =getCertificateTrackerById(certificateTracker.getId()).get();
	certificateTrackerFromDb.setCertificateDocId(certificateTracker.getCertificateDocId());
	return certificateTrackerRepository.save(certificateTrackerFromDb);
	
}




}
