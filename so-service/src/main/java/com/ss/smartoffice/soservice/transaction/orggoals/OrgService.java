package com.ss.smartoffice.soservice.transaction.orggoals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.OrgHeader;
import com.ss.smartoffice.shared.model.OrgLine;
import com.ss.smartoffice.soservice.master.metric.Metric;
import com.ss.smartoffice.soservice.master.metric.MetricRepo;

@RestController
@RequestMapping("transaction/org-services")
public class OrgService {
@Autowired
OrgHeaderRepository orgHeaderRepository;
@Autowired
OrgLineRepository orgLineRepository;
@Autowired
MetricRepo metricRepo;
@PersistenceContext
EntityManager entityManager;
@GetMapping
public Iterable<OrgHeader>getAll()throws SmartOfficeException{
	
	
	return orgHeaderRepository.findAll();
}
@GetMapping("/emp-by-fiscal-year")
public OrgHeader getByFiscalYearAndEmpId(@RequestParam(value="fyYearId",required = false)String fyYearId)throws SmartOfficeException{
	boolean searchByFiscalYear=false;
	if(fyYearId!=null) {
		searchByFiscalYear=true;
	}
	if(searchByFiscalYear) {
		return orgHeaderRepository.findByFyYearId(fyYearId);
	}
	return null;
}
@GetMapping("/{id}")
public Optional<OrgHeader>getById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return orgHeaderRepository.findById(id);
}
@PostMapping
public OrgHeader addOrgHeader(@RequestBody OrgHeader orgHeader)throws SmartOfficeException{
	orgHeader.setStatus("ACTIVE");
	return orgHeaderRepository.save(orgHeader);
}

@PatchMapping("/{id}/add-update-lines")
public OrgHeader updateOrAddLines(@PathVariable(value="id")Integer id,@RequestBody OrgHeader orgHeader)throws SmartOfficeException{		
	OrgHeader orgHeader2=orgHeaderRepository.findById(id).get();
	orgHeader2.setEmpId(orgHeader.getEmpId());
	orgHeaderRepository.save(orgHeader2);
	if(orgHeader.getOrgLines()!=null&&!orgHeader.getOrgLines().isEmpty()) {
		for(OrgLine orgLine:orgHeader.getOrgLines()) {
			orgLine.setOrgObjHeaderId(String.valueOf(id));
			OrgLine savedOrgLine=orgLineRepository.save(orgLine);
			//since i want formula attribute from entity i choose native query
			TypedQuery<OrgLine> savedOrgQuery = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.shared.model.OrgLine s where id="
							+ savedOrgLine.getId(),
							OrgLine.class);
			List<OrgLine> orgLineById = savedOrgQuery.getResultList();
			String metricId=String.valueOf(orgLineById.get(0).getMetricId());
			Metric metric=metricRepo.findById(metricId).get();
			System.out.println(metric);
			String phrase=orgLineById.get(0).getMetricName() +"" + orgLineById.get(0).getOperator() +""+ savedOrgLine.getMetricValue() + metric.getUnitTypeSymbol();
			savedOrgLine.setMetricSummary(phrase);
			System.out.println(orgLineById.get(0).getMetricValue());
			System.out.println(phrase);
			orgLineRepository.save(savedOrgLine);
		}
		
	}
	return orgHeader;
}

@DeleteMapping("/{id}/delete-header")
public void deleteOrgHeader(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	orgHeaderRepository.deleteById(id);
}
@DeleteMapping("/{id}/delete-line")
public void deleteOrgLine(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	orgLineRepository.deleteById(id);
}
}
