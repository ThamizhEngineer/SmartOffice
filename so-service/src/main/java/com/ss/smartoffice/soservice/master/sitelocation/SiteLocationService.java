package com.ss.smartoffice.soservice.master.sitelocation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.job.JobsRepository;
import com.ss.smartoffice.soservice.transaction.model.Job;

@RestController
@RequestMapping(path = "/master/site-location")
public class SiteLocationService {
	
	@Autowired
	SiteLocationRepo siteLocationRepo;
	@Autowired
	JobsRepository jobsRepo;
		
	@CrossOrigin("*")
	@GetMapping()
	public Iterable<SiteLocation> fetchAllSiteLocations(
			@RequestParam(value = "siteName", required = false) String siteName,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "endClientName", required = false) String endClientName,
			@RequestParam(value = "contactName", required = false) String contactName,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
		return siteLocationRepo.fetchAll(siteName, country, endClientName, contactName, mobileNumber);
	}
	
	@GetMapping("/{id}")
	public Optional<SiteLocation> fetchById(@PathVariable(value = "id")Integer id) {
		return siteLocationRepo.findById(id);
	}
	
	@PostMapping
	public SiteLocation addSiteLocation(@RequestBody SiteLocation siteLocation) {
		return siteLocationRepo.save(siteLocation);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable(value="id") Integer id) {
		List<Job> jobs = jobsRepo.fetchBySiteLocationId(id.toString());
		if (jobs==null || jobs.isEmpty()) {
			siteLocationRepo.deleteByIdQuery(id);		
		}else {
			throw new SmartOfficeException("Refered in job, cannot be deleted");
		}
	}
	
}
