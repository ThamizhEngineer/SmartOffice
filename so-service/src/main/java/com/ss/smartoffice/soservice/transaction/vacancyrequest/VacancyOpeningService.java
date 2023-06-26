package com.ss.smartoffice.soservice.transaction.vacancyrequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.websiteapplicant.WebSiteApplicant;
import com.ss.smartoffice.soservice.transaction.websiteapplicant.WebSiteApplicantRepository;

@RestController
@RequestMapping("transaction/vacancy-openings")
public class VacancyOpeningService {
	@Autowired
	VacancyRequestRepo vacancyRequestRepo;
	
	@Autowired
	WebSiteApplicantRepository webSiteApplicantRepository;
	
	
	
	@GetMapping
	public List<VacancyRequest> getAllJobRequests()throws SmartOfficeException{
		return vacancyRequestRepo.findByOpenStatus();
	}
	
	@GetMapping("/email")
	public List<WebSiteApplicant> getApplicantByEmail(@RequestParam(value = "email", required = false) String email,@RequestParam(value = "vcId", required = false) String vcId,@RequestParam(value = "phNo", required = false) String phNo) throws SmartOfficeException{
		List<WebSiteApplicant> wsSiteAppsFromDb = webSiteApplicantRepository.findByemailAndVcIdAndMobNum(email,vcId,phNo);				
		return wsSiteAppsFromDb;
	}
	
	@PatchMapping
	public WebSiteApplicant updateJob(@RequestBody WebSiteApplicant webSiteApplicant)throws SmartOfficeException{									
		return webSiteApplicantRepository.save(webSiteApplicant); 
	}
	
	@PostMapping
	public WebSiteApplicant applyJob(@RequestBody WebSiteApplicant webSiteApplicant)throws SmartOfficeException{				
		List<WebSiteApplicant> wsSiteAppsFromDb = webSiteApplicantRepository.findByemailAndVcIdAndMobNum(webSiteApplicant.getEmail(), webSiteApplicant.getVcId(),webSiteApplicant.getMobNum());
		if(wsSiteAppsFromDb.isEmpty()) {
			webSiteApplicantRepository.save(webSiteApplicant);
		}else {
			throw new SmartOfficeException("you have Already Register for this Job Request");
		}				
		return webSiteApplicant; 
	}
}
