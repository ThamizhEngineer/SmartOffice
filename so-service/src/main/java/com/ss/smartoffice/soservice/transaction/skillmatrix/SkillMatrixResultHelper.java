package com.ss.smartoffice.soservice.transaction.skillmatrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.EmployeeSkill;
import com.ss.smartoffice.soservice.master.employeeskill.EmployeeSkillRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillMatrixResultHelper {
	private static Logger log = LoggerFactory.getLogger(SkillMatrixResultHelper.class);
	
	@Value("${document.url}")
	private String docUrl;

	@Autowired
	SkillMatrixResultRepo skillMatrixResultRepo;
	@Autowired
	SkillMatrixRequestSkillsRepo skillMatrixReqSkillRepo;
	@Autowired
	SkillMatrixRequestHdrRepo skillMatrixRequestHdrRepo;
	@Autowired
	SkillMatrixRequestEmpRepo skillMatrixRequestEmpRepo;
	@Autowired
	EmployeeSkillRepo empSkillRepo;
	@Autowired
	CommonUtils commonUtils;
	
	public Iterable<SkillMatrixResult> poulateResult(String key) {
		List<SkillMatrixRequestEmp> empRs = skillMatrixRequestEmpRepo.fetchByKey(key);
		List<SkillMatrixRequestSkills> skillRs = skillMatrixReqSkillRepo.fetchByKey(key);
		
		List<SkillMatrixResult> result = new ArrayList<SkillMatrixResult>();
		for (SkillMatrixRequestEmp empR : empRs) {
			for (SkillMatrixRequestSkills skillR : skillRs) {
				List<EmployeeSkill> empSkills = empSkillRepo.fetchByProdSkillEmp(Integer.parseInt(empR.getEmployeeId()), Integer.parseInt(skillR.getProductId()), Integer.parseInt(skillR.getServiceId()));
				if (empSkills!=null && !empSkills.isEmpty()) {
					SkillMatrixResult skillMatrixResult = formingResult(empR, skillR, empSkills.get(0).getSkillLevelCode());
					result.add(skillMatrixResult);
				}else {
					SkillMatrixResult skillMatrixResult = formingResult(empR, skillR, "0");
					result.add(skillMatrixResult);

				}
			}
		}
		return skillMatrixResultRepo.saveAll(result);
	}
	
	private SkillMatrixResult formingResult(SkillMatrixRequestEmp empR, SkillMatrixRequestSkills skillR, String skillLevelCode) {
		SkillMatrixResult skillMatrixResult = new SkillMatrixResult();
		skillMatrixResult.setEmployeeId(empR.getEmployeeId());
		skillMatrixResult.setSkillMatrixHdrKey(empR.getSkillMatrixHdrKey());
		skillMatrixResult.setEmployeeCode("");
		skillMatrixResult.setEmployeeName(empR.getEmployeeName());
		skillMatrixResult.setDeptId(empR.getDepartmentId());
		skillMatrixResult.setDeptName(empR.getDeptName());
		skillMatrixResult.setProductId(skillR.getProductId());
		skillMatrixResult.setProductName(skillR.getProductName());
		skillMatrixResult.setServiceId(skillR.getServiceId());
		skillMatrixResult.setServiceName(skillR.getServiceName());
		skillMatrixResult.setSkillLevelCode(Integer.parseInt(skillLevelCode));
		skillMatrixResult.setType("");
		skillMatrixResult.setN1EmpId(empR.getN1EmpId());
		skillMatrixResult.setN2EmpId(empR.getN2EmpId());
		skillMatrixResult.setDesignationId(empR.getDesignationId());
		skillMatrixResult.setDesignationName(empR.getDesignationName());
		return skillMatrixResult;
	}

	public void calculateGap(String key) {
		log.info("Calculating gap");
		try {
			updatingHdrStatus("status", key, "Calculating gap and finalizing result");
			List<GapOutput> x = skillMatrixResultRepo.queryGrpByProdAndServ(key);
			log.info("gapOutPut",x);
			if(x.size()!=0) {
				for (GapOutput gapOutput : x) {
					try {
						SkillMatrixRequestSkills res = skillMatrixReqSkillRepo
								.fetchByProdServKey(gapOutput.getProductId(), gapOutput.getServiceId(), key).get(0);
						Integer gapCount = skillMatrixResultRepo.findAvailableCount(key, gapOutput.getProductId(), gapOutput.getServiceId());
						res.setAvailableCount(gapCount);
						res.setGapCount(res.getExpectedCount() - gapCount);
						skillMatrixReqSkillRepo.save(res);
					} catch (Exception e) {
						log.error(e.getLocalizedMessage());
						e.printStackTrace();
					}
				}
				populateExtras(key);
				populateRowSpan(key);
				updatingHdrStatus("status", key, "Completed");
			}else {
				log.error("Gap Calculation-Empty");
				updatingHdrStatus("Error", key, "Gap OutPut Empty or Null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			updatingHdrStatus("error", key, e.getMessage());
			throw new SmartOfficeException("Error while calculating gap");
		}
	}

	public void populateExtras(String key) {
		log.info("Populating extras: "+key);
		List<SkillMatrixResult> res = skillMatrixResultRepo.findBySkillMatrixHdrKey(key);
		List<SkillMatrixResult> outRes = new ArrayList<SkillMatrixResult>();
		String empId;
		Integer hasCount = 0;
		Integer trainingCount = 0;
		for (SkillMatrixResult skillMatrixResult : res) {			
			empId=skillMatrixResult.getEmployeeId();
			if(!empId.equals(skillMatrixResult.getEmployeeId())) {
				hasCount = 0;
				trainingCount = 0;
			}					
			if (skillMatrixResult.getSkillLevelCode() != null) {
				if (skillMatrixResult.getSkillLevelCode() > 1) {
					hasCount = hasCount + 1;
				}
				if (skillMatrixResult.getSkillLevelCode() <= 1) {
					trainingCount = trainingCount + 1;
				}
				skillMatrixResult.setHasCount(hasCount.toString());
				skillMatrixResult.setTrainingCount(trainingCount.toString());
				outRes.add(skillMatrixResult);
			}
		}
		populateRowSpan(key);
		skillMatrixResultRepo.saveAll(outRes);
	}

	public void populateRowSpan(String key) {
		log.info("Populating rowspans: "+key);
		List<RowSpanObject> x = skillMatrixResultRepo.fetchRowSpan(key);
		for (RowSpanObject rowSpanObject : x) {
			skillMatrixResultRepo.updateRowSpan(String.valueOf(rowSpanObject.getRowSpan()), rowSpanObject.getDeptId(),key);
		}
	}
	
	public void triggerPrint(String key, String type) {
		log.info("Triggering print: "+key);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",commonUtils.getLoggedinAppToken());   
			List<SkillMatrixResult> x = skillMatrixResultRepo.findBySkillMatrixHdrKey(key);
			HttpEntity<List<SkillMatrixResult>> request = new HttpEntity<List<SkillMatrixResult>>(x, headers);
			String url = docUrl+"/skill-matrix/print/"+type;
			log.info("PrintUrl: "+url);
			commonUtils.getRestTemplate().exchange(url,HttpMethod.POST, request, Boolean.class);
		} catch (RestClientException e) {
			log.error("Error: Trigger print: "+key);
			e.printStackTrace();
		}
	}

	private void updatingHdrStatus(String type, String key, String message) {
		List<SkillMatrixRequestHdr> hdrs = skillMatrixRequestHdrRepo.findByKey(key);
		if (hdrs.size() == 1) {
			for (SkillMatrixRequestHdr hdr : hdrs) {
				if (type.equals("error")) {
					hdr.setStatus("error");
					hdr.setIsError("Y");
					hdr.setErrorMessage(message);
					skillMatrixRequestHdrRepo.save(hdr);
				} else {
					hdr.setStatus(message);
					skillMatrixRequestHdrRepo.save(hdr);
				}
			}
		} else {
			throw new SmartOfficeException("Skill matrix request hdr cannot have duplicates");
		}
	}

}
