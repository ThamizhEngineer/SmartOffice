package com.ss.smartoffice.soservice.transaction.websiteapplicant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("transaction/web-site-applicants")
public class WebSiteApplicantService {
@Autowired
WebSiteApplicantRepository webSiteApplicantRepository;

@GetMapping
public Iterable<WebSiteApplicant>getAllWebSiteApplicants()throws SmartOfficeException{
	return webSiteApplicantRepository.findAll();
}

@GetMapping("/{id}")
public Optional<WebSiteApplicant> getWebSiteApplicants(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
	return webSiteApplicantRepository.findById(id);
}

@PostMapping
public WebSiteApplicant addWebSiteApplicant(@RequestBody WebSiteApplicant webSiteApplicant)throws SmartOfficeException{
	return webSiteApplicantRepository.save(webSiteApplicant);
}

@PatchMapping("/{id}/update")
public WebSiteApplicant updateWebSiteApplicant(@RequestBody WebSiteApplicant webSiteApplicant)throws SmartOfficeException{
	return webSiteApplicantRepository.save(webSiteApplicant);
}

@DeleteMapping("/{id}/delete")
public void deleteWebSiteApplicant(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	webSiteApplicantRepository.deleteById(id);
}


@PatchMapping("/bulkupdate")
public List<WebSiteApplicant> bulkUpdate(@RequestBody List<WebSiteApplicant> webSiteApplicantList)throws SmartOfficeException{
	for (WebSiteApplicant webSiteApplicant : webSiteApplicantList) {
		webSiteApplicantRepository.save(webSiteApplicant);
	}
	return webSiteApplicantList;
}

@GetMapping("/groupByJrId/{id}")
public List<WebSiteApplicant> getTempTest(@PathVariable(value="id")String id)throws SmartOfficeException{
	Iterable<WebSiteApplicant> webList = webSiteApplicantRepository.findAll();
	Iterator<WebSiteApplicant> iter = webList.iterator();
	List<WebSiteApplicant> copy = new ArrayList<WebSiteApplicant>();
	while (iter.hasNext())
	    copy.add(iter.next());
	
    Map<String, List<WebSiteApplicant>> output = copy.stream().collect(
            Collectors.groupingBy(WebSiteApplicant::getVcId));
	return output.get(id);
	}

}
