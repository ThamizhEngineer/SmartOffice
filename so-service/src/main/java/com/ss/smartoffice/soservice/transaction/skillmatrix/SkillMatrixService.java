package com.ss.smartoffice.soservice.transaction.skillmatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ss.smartoffice.soservice.master.employeeskill.EmployeeSkillRepo;
import com.ss.smartoffice.soservice.master.material.MaterialRepo;

@RestController
@RequestMapping("skill-matrix/")
public class SkillMatrixService {
	private static Logger log = LoggerFactory.getLogger(SkillMatrixService.class);

	@Autowired
	SkillMatrixRequestHdrRepo hdrRepo;
	@Autowired
	SkillMatrixResultRepo skillMatrixResultRepo;
	@Autowired
	SkillMatrixRequestHelper skillMatrixRequestHelper;
	@Autowired
	SkillMatrixRequestEmpRepo skillMatrixRequestEmpRepo;
	@Autowired
	SkillMatrixRequestSkillsRepo skillMatrixSkillsRepo;
	@Autowired
	EmployeeSkillRepo empSkillRepo;
	@Autowired
	MaterialRepo materialRepo;
	@Autowired
	SkillMatrixResultHelper resultHelper;

	@GetMapping("hdrs")
	public Iterable<SkillMatrixRequestHdr> fetchAllHdrs() {
		return hdrRepo.findAll();
	}

	@PostMapping("init-request")
	@Transactional
	public void initRequest(@RequestBody SkillMatrixRequestHdr skillMatrixRequestHdr)
			throws JsonParseException, JsonMappingException, IOException {
		log.info("Api - Request init");
		skillMatrixRequestHelper.analizingRequestHdr(skillMatrixRequestHdr,null);
	}

	@PostMapping("inter-init")
	public SkillMatrixRequestHdr initRequestInternal(@RequestBody String inputPayload,Integer bkProcessId)throws JsonParseException, JsonMappingException, IOException {
		log.info("Bkprocess - Request init");
		JSONObject x = new JSONObject(inputPayload);
		SkillMatrixRequestHdr skillMatrixRequestHdr = formingHdrObj(x);
		skillMatrixRequestHelper.analizingRequestHdr(skillMatrixRequestHdr, bkProcessId);
		return skillMatrixRequestHdr;
	}

	private SkillMatrixRequestHdr formingHdrObj(JSONObject hdrJson) {
		SkillMatrixRequestHdr skillMatrixRequestHdr = new SkillMatrixRequestHdr();
		skillMatrixRequestHdr.setProcessType(hdrJson.getString("processType"));

		List<EmployeeReqInput> empReqs = new ArrayList<EmployeeReqInput>();
		if (hdrJson.getJSONArray("employeeIds").length() > 0) {
			for (int i = 0; i < hdrJson.getJSONArray("employeeIds").length(); i++) {
				JSONObject empJson = hdrJson.getJSONArray("employeeIds").getJSONObject(i);
				EmployeeReqInput empInput = new EmployeeReqInput();
				empInput.setEmployeeId(empJson.getString("employeeId"));
				empReqs.add(empInput);
				skillMatrixRequestHdr.setEmployeeIds(empReqs);
			};
		}
		List<ProductAndServiceInput> prodServsReqs = new ArrayList<ProductAndServiceInput>();
		for (int i = 0; i < hdrJson.getJSONArray("productAndServices").length(); i++) {
			JSONObject prodServJson = hdrJson.getJSONArray("productAndServices").getJSONObject(i);
			ProductAndServiceInput prodServInput = new ProductAndServiceInput();
			prodServInput.setProductId(prodServJson.getString("productId"));
			prodServInput.setServiceId(prodServJson.getString("serviceId"));
			if (prodServJson.has("expectedCount")) {
				prodServInput.setExpectedCount(prodServJson.getString("expectedCount"));
			}
			prodServsReqs.add(prodServInput);
			skillMatrixRequestHdr.setProductAndServices(prodServsReqs);
		};
		return skillMatrixRequestHdr;
	}

	@PostMapping("result-hdr")
	public List<SkillMatrixRequestSkills> fetchReqSkillsByKey(@RequestBody String skillMatrixHdrKey) {
		String resultKey = skillMatrixHdrKey.replaceAll("^\"+|\"+$", "");
		log.info("Api - result-hdr: "+resultKey);
		List<SkillMatrixRequestSkills> res = skillMatrixSkillsRepo.fetchByKey(resultKey);
		return res;
	}

	@PostMapping("result")
	public List<SkillMatrixResult> fetchResultByKey(@RequestBody String skillMatrixHdrKey) {
		return skillMatrixResultRepo.findBySkillMatrixHdrKey(skillMatrixHdrKey);
	}
	
	@PostMapping("print/update")
	public Boolean updateDocId(@RequestBody Map<String, String> input) {
		try {
			log.info("Updating docId in main request hdr");
			List<SkillMatrixRequestHdr> hdr = hdrRepo.findByKey(input.get("key"));
			hdr.get(0).setDocId(input.get("docId"));
			hdrRepo.save(hdr.get(0));
			return true;
		} catch (Exception e) {
			log.error("Error: DocId updation in main request hdr");
			e.printStackTrace();
			return false;
		}
	}
	

//	-------------------<Below - Only for Testing>----------------------------------------------------


	@GetMapping("reqemps") // Testing purpose
	public List<SkillMatrixRequestEmp> reqEmps(@RequestBody String key) {
		log.debug("Api- Request emp");
		return skillMatrixRequestEmpRepo.fetchByKey(key);
	}
	
	@GetMapping("reqskills") // Testing purpose
	public List<SkillMatrixRequestSkills> reQskills(@RequestBody String key) {
		log.debug("Api- Request skill");
		return skillMatrixSkillsRepo.fetchByKey(key);
	}

	@GetMapping("gaps") // Testing purpose
	public List<SkillMatrixRequestSkills> queryToFindGap(@RequestBody String SkillMatrixHdrKey) {
		List<GapOutput> x = skillMatrixResultRepo.queryGrpByProdAndServ(SkillMatrixHdrKey);
		System.out.println(x);
		List<SkillMatrixRequestSkills> reqSkills = new ArrayList<SkillMatrixRequestSkills>();
		for (GapOutput gapOutput : x) {
			SkillMatrixRequestSkills res = skillMatrixSkillsRepo
					.fetchByProdServKey(gapOutput.getProductId(), gapOutput.getServiceId(), SkillMatrixHdrKey).get(0);
			System.out.println(res);
			Integer gapCount = ((Number) gapOutput.getGapCount()).intValue();
			res.setAvailableCount(gapCount);
			res.setGapCount(res.getExpectedCount() - gapCount);
			reqSkills.add(res);
		}
		return reqSkills;
	}

	@GetMapping("empIds/{searchby}/{value}") // Testing purpose
	public List<Integer> fetchEmpIds(@PathVariable(value = "searchby") String searchby,
			@PathVariable(value = "value") Integer value) {
		List<Integer> res = skillMatrixRequestHelper.fetchEmpIds(searchby, value);
		return res;
	}
	
	@PostMapping("takeprint/{type}")
	public void takePrint(@RequestBody String key, @PathVariable(value = "type") String type) {
		resultHelper.triggerPrint(key, type);
	}
	
}
