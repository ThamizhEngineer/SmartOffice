package com.ss.smartoffice.soservice.transaction.skillmatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.employeeskill.EmployeeSkillRepo;
import com.ss.smartoffice.soservice.master.material.MaterialRepo;
import com.ss.smartoffice.soservice.transaction.bkProcess.BkProcessService;

@Service
public class SkillMatrixRequestHelper {
	private static Logger log = LoggerFactory.getLogger(SkillMatrixRequestHelper.class);

	@Autowired
	SkillMatrixRequestHdrRepo skillMatrixRequestHdrRepo;
	@Autowired
	SkillMatrixRequestEmpRepo skillMatrixRequestEmpRepo;
	@Autowired
	SkillMatrixRequestSkillsRepo skillMatrixRequestSkillsRepo;
	@Autowired
	EmployeeSkillRepo employeeSkillRepo;
	@Autowired
	SkillMatrixResultHelper skillMatrixResultHelper;
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	MaterialRepo materialRepo;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	BkProcessService bkProcessService;

	public void analizingRequestHdr(SkillMatrixRequestHdr skillMatrixRequestHdr, Integer bkProcessId)
			throws JsonParseException, JsonMappingException, IOException {
		String key = hdrRequestIntialSave(skillMatrixRequestHdr);
		log.info("Analizing - incoming request - " + key);

		if (bkProcessId != null) { // Only if bkProcessId is not null
				bkProcessService.startProgress(bkProcessId,key);
		}

		if (skillMatrixRequestHdr.getDeptId() != null) {
			if (skillMatrixRequestHdr.getIsSpecificSkillList() != null
					&& skillMatrixRequestHdr.getIsSpecificSkillList().equals("Y")) {
				specificRequest(skillMatrixRequestHdr, key, "dept",
						Integer.parseInt(skillMatrixRequestHdr.getDeptId())); // Expected count in hdr
			} else {
				otherRequests(skillMatrixRequestHdr, key, "dept", Integer.parseInt(skillMatrixRequestHdr.getDeptId()));
			}
		} else if (skillMatrixRequestHdr.getOfficeId() != null) {
			if (skillMatrixRequestHdr.getIsSpecificSkillList() != null
					&& skillMatrixRequestHdr.getIsSpecificSkillList().equals("Y")) {
				specificRequest(skillMatrixRequestHdr, key, "office",
						Integer.parseInt(skillMatrixRequestHdr.getOfficeId())); // Expected count in hdr
			} else {
				otherRequests(skillMatrixRequestHdr, key, "office",
						Integer.parseInt(skillMatrixRequestHdr.getOfficeId()));
			}
		} else {
			processDefaultRequest(skillMatrixRequestHdr, key);
		}
	}

	private void specificRequest(SkillMatrixRequestHdr skillMatrixRequestHdr, String key, String searchBy,
			Integer value) {
		try {
			updatingHdrStatus("status", key, "Specific request is being processed");
			List<Integer> empIds = fetchEmpIds(searchBy, value); // Returns empIds under deptId or officeId
			saveRequestEmp(null, key, empIds);
			if (skillMatrixRequestHdr.getManufactureId() != null) {
				saveRequestSkills(formProdServInputFromOutput(
						fetchSpecficProdsAndServs("manufacturer", skillMatrixRequestHdr.getManufactureId()),
						skillMatrixRequestHdr.getExpectedCount()), key);
			} else if (skillMatrixRequestHdr.getProductFamilyId() != null) {
				saveRequestSkills(formProdServInputFromOutput(
						fetchSpecficProdsAndServs("productfamily", skillMatrixRequestHdr.getProductFamilyId()),
						skillMatrixRequestHdr.getExpectedCount()), key);
			} else if (skillMatrixRequestHdr.getProductId() != null) {
				saveRequestSkills(formProdServInputFromOutput(
						fetchSpecficProdsAndServs("product", skillMatrixRequestHdr.getProductFamilyId()),
						skillMatrixRequestHdr.getExpectedCount()), key);
			}

			afterRequestIsSaved(skillMatrixRequestHdr, key);
		} catch (Exception e) {
			log.info("Error in specificRequest");
			e.printStackTrace();
			throw new SmartOfficeException("Error in specificRequest");
		}
	}

	public List<ProdAndServOutput> fetchSpecficProdsAndServs(String searchBy, String input) {
		List<String> x = new ArrayList<>();
		List<ProdAndServOutput> output = new ArrayList<ProdAndServOutput>();
		switch (searchBy) {
		case "manufacturer":
			x = materialRepo.fetchMatrialIdsByManufacturerId(input);
			output = employeeSkillRepo.availabeSkillsIn(x);
			break;
		case "productfamily":
			x = materialRepo.fetchMatrialIdsByProductFamilyId(input);
			output = employeeSkillRepo.availabeSkillsIn(x);
			break;
		case "product":
			output = employeeSkillRepo.availabeSkillsByProduct(Integer.parseInt(input));
			break;
		default:
			log.error("This is not a valid inputs to search: " + searchBy + " and " + input);
			break;
		}
		return output;
	}

	private List<ProductAndServiceInput> formProdServInputFromOutput(List<ProdAndServOutput> output,
			String expectedCount) {
		List<ProductAndServiceInput> res = new ArrayList<ProductAndServiceInput>();
		for (ProdAndServOutput x : output) {
			ProductAndServiceInput y = new ProductAndServiceInput();
			y.setServiceId(String.valueOf(x.getmProfileId()));
			y.setProductId(String.valueOf(x.getmProductId()));
			y.setExpectedCount(expectedCount);
			res.add(y);
		}
		return res;
	}

	private void otherRequests(SkillMatrixRequestHdr skillMatrixRequestHdr, String key, String searchBy,
			Integer value) {
		log.info("Procesing other request - key:  " + key);
		updatingHdrStatus("status", key, "Other request is being processed");
		List<Integer> empIds = fetchEmpIds(searchBy, value); // Returns empIds under deptId or officeId
		saveRequestEmp(null, key, empIds);
		saveRequestSkills(skillMatrixRequestHdr.getProductAndServices(), key);
		afterRequestIsSaved(skillMatrixRequestHdr, key);
	}

	private void processDefaultRequest(SkillMatrixRequestHdr skillMatrixRequestHdr, String key) {
		try {
			log.info("Procesing default request - key:  " + key);
			if (skillMatrixRequestHdr.getProcessType().equals("eng-assesment")) {
				saveRequestEmp(skillMatrixRequestHdr.getEmployeeIds(), key, null);
				saveRequestSkills(skillMatrixRequestHdr.getProductAndServices(), key);
			} else if (skillMatrixRequestHdr.getProcessType().equals("gap-analysis")) {
				List<Integer> empIds = employeeSkillRepo.fetchExistingEmpWithSkills(); // returns list of employeeIds
				saveRequestEmp(null, key, empIds);
				saveRequestSkills(skillMatrixRequestHdr.getProductAndServices(), key);
			} else {
				log.error("wrong process type");
				throw new SmartOfficeException("wrong process type");
			}
			afterRequestIsSaved(skillMatrixRequestHdr, key);
		} catch (Exception e) {
			log.error("Error: processing default request: " +key);
			updatingHdrStatus("error", key, "error");
			e.printStackTrace();
		}
	}

	private void afterRequestIsSaved(SkillMatrixRequestHdr skillMatrixRequestHdr, String key) {
		updatingHdrStatus("status", key, "All request saved. Now Processing");
		Iterable<SkillMatrixResult> result = skillMatrixResultHelper.poulateResult(key);
		System.out.println(result.iterator().hasNext());
		if (result.iterator().hasNext()) {
			// After result is saved successfully
			if (skillMatrixRequestHdr.getProcessType().equals("gap-analysis")) {
				skillMatrixResultHelper.calculateGap(key);
				skillMatrixResultHelper.triggerPrint(key, "GAP-ANALYSIS");
			}
			if(skillMatrixRequestHdr.getProcessType().equals("eng-assesment")) {
//				skillMatrixResultHelper.populateRowSpan(key);
				skillMatrixResultHelper.populateExtras(key);
				skillMatrixResultHelper.triggerPrint(key, "ENG-ASSESSMENT");
			}
		} else {
			log.error("Result returned empty - " + key);
			throw new SmartOfficeException("Result returned empty  for key -" + key);
		}
	}

	private void saveRequestEmp(List<EmployeeReqInput> stringList, String key, List<Integer> integerList) {
		log.info("Saving emp request - key:  " + key);
		try {
			updatingHdrStatus("status", key, "Saving emp-request");
			if (stringList != null && !stringList.isEmpty()) {
				log.info("Request emp string list: " + stringList);
				for (EmployeeReqInput x : stringList) {
					formAndSaveRequestEmpObject(x.getEmployeeId(), key);
				}
			} else if (integerList != null && !integerList.isEmpty()) {
				log.info("Request emp integer list: " + integerList);
				for (Integer x : integerList) {
					formAndSaveRequestEmpObject(x.toString(), key);
				}
			} else {
				log.error("Request emp list cannot be null or empty");
				throw new SmartOfficeException("Request emp list cannot be null or empty");
			}
		} catch (Exception e) {
			log.error("Error: Saving Emp request, key: " + key);
			e.printStackTrace();
		}
	}

	private void formAndSaveRequestEmpObject(String employeeId, String key) {
		SkillMatrixRequestEmp reqEmp = new SkillMatrixRequestEmp();
		reqEmp.setEmployeeId(employeeId);
		reqEmp.setSkillMatrixHdrKey(key);
		skillMatrixRequestEmpRepo.save(reqEmp);
	}

	private void saveRequestSkills(List<ProductAndServiceInput> list, String key) {
		log.info("Saving Skill request - key:  " + key);
		try {
			updatingHdrStatus("status", key, "Saving skill-request");
			for (ProductAndServiceInput x : list) {
				SkillMatrixRequestSkills reqSkill = new SkillMatrixRequestSkills();
				reqSkill.setProductId(x.getProductId());
				reqSkill.setServiceId(x.getServiceId());				
				if (x.getExpectedCount()!=null) {
					reqSkill.setExpectedCount(Integer.parseInt(x.getExpectedCount()));
				}
				reqSkill.setSkillMatrixHdrKey(key);
				skillMatrixRequestSkillsRepo.save(reqSkill);
			}
		} catch (NumberFormatException e) {
			log.error("Error: Saving Skill request, key: " + key);
			e.printStackTrace();
		}
	}

	// Once the process is initiated, it saves the process request and returns key (skillMaterixHdrKey)
	private String hdrRequestIntialSave(SkillMatrixRequestHdr hdrReq) {
		hdrReq.setStatus("Started");
		hdrReq.setKey(commonUtils.generateId());
		hdrReq = skillMatrixRequestHdrRepo.save(hdrReq);
		System.out.println(hdrReq);
		return hdrReq.getKey();
	}

	// Updates status & error during processing
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

	// Fetch employee ids based on searchBy and value
	public List<Integer> fetchEmpIds(String searchby, Integer value) {
		List<Integer> res = new ArrayList<Integer>();
		if (searchby.equals("dept")) {
			res = empRepo.fetchEmployeeIdsByDeptId(value);
		} else if (searchby.equals("office")) {
			res = empRepo.fetchEmployeeIdsByOfficeId(value);
		}
		return res;
	}

}
