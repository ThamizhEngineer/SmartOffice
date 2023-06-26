package com.ss.smartoffice.soservice.transaction.appraisalservice;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.soservice.master.metric.Metric;
import com.ss.smartoffice.soservice.master.metric.MetricService;

@RestController
@RequestMapping(path = "transaction/appraisals")
public class AppraisalService {
	@Autowired
	AppraisalDevRepo appraisalDevRepo;

	SmartOfficeException soe;
	@Autowired
	AppraisalGoalRepo appraisalGoalRepo;
	@Autowired
	AppraisalHdrRepo appraisalHdrRepo;
	@Autowired
	AppraisalReviewRepo appraisalReviewRepo;
	@Autowired
	AppraisalBusHelper appraisalBusHelper;
	@Autowired
	MetricService metricService;
	@Autowired
	ReviewAppraisalRepo reviewAppraisalRepo;                                                                                                                                                                                                     
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	CommonUtils commonUtils;

	
	@Autowired
	DocInfoRepository docInfoRepository;
	
	
	List<String> knownActions = new ArrayList() {
		{
			add("initiate");
			add("start");
			add("update");
			add("submit");
			add("n1-review");
			add("emp-accept");
			add("dir-approve");
			add("n2-review");
			add("n1-reject");
			add("emp-reject");
			add("priority");
			
		}
			
	};
	List<String>reviewActions= new ArrayList() {
		{
			add("initiate");
			add("start");
			add("submit");
			add("n1-update");
			add("dir-approve");
		}
	};
	
	

		

	@GetMapping("/{fyId}/fy-id")
	public List<GoalAnalysis> getOrgAnalysis(@PathVariable(value = "fyId") String fyId,
			@PathVariable(value = "empId") String empId) throws SmartOfficeException {
		System.out.println(fyId);
		System.out.println(empId);
		return appraisalGoalRepo.findByDetails(fyId, empId);
	}

	@GetMapping("/{fyId}/{empId}")
	public List<AppraisalHdr> getByFyIdAndEmpId(@PathVariable(value = "fyId") String fyId,
			@PathVariable(value = "empId") String empId) throws SmartOfficeException {
		return appraisalHdrRepo.findByFyIdAndEmpId(fyId, empId);
	}

	@GetMapping("/{fyId}/hdr")
	public List<AppraisalHdr> getFyId(@PathVariable(value = "fyId") String fyId) throws SmartOfficeException {
		return appraisalHdrRepo.findByFyId(fyId);
	}

	@GetMapping("/{n1EmpId}/advanced")
	public List<ManagerSearch> getByN1EmpId(@PathVariable(value = "n1EmpId") String n1EmpId)
			throws SmartOfficeException {
		return appraisalHdrRepo.findByManager(n1EmpId);
	}

	@GetMapping
	public Iterable<AppraisalHdr> getAll() throws SmartOfficeException {
		return appraisalHdrRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<AppraisalHdr> getById(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		return appraisalHdrRepo.findById(id);
	}

	@PostMapping
	public AppraisalHdr addAppraisalHdr(@RequestBody AppraisalHdr appraisalHdr) throws SmartOfficeException {
		return appraisalHdrRepo.save(appraisalHdr);
	}

//initiate
	@PostMapping("/{action}")
	public AppraisalHdr initiateAppraisalHdr(@PathVariable(value = "action") String action,
			@RequestBody AppraisalHdr appraisalHdr) throws SmartOfficeException {
		if (!knownActions.contains(action)) {
			throw new SmartOfficeException("Not a Known action");
		} else {
			return appraisalBusHelper.processAppraisal(null, action, appraisalHdr);
		}
	}

//start or update appraisal
	@PatchMapping("/{id}/{action}")
	public AppraisalHdr startOrUpdateAppraisal(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "action") String action, @RequestBody AppraisalHdr appraisalHdr)
			throws SmartOfficeException {
		if (!knownActions.contains(action)) {
			throw new SmartOfficeException("Not a known action");
		} else {
			return appraisalBusHelper.processAppraisal(id, action, appraisalHdr);
		}

		// appraisalList(serach)
		// n1serach(implicit)
		// orgGoalAnalysis//
	}
	@PatchMapping("/{id}/review/{action}")
	public List<ReviewAppraisal>updateReviews(@PathVariable(value = "id") Integer id,@PathVariable(value="action")String action,@RequestBody List<ReviewAppraisal>reviewAppraisals)throws SmartOfficeException{
		if(!reviewActions.contains(action)) {
			throw new SmartOfficeException("Not a known action");
		}else {
			return appraisalBusHelper.processReviews(id,action,reviewAppraisals);
		}
	}
	
	
	@PatchMapping("/{id}/add-update-or-lines")
	public AppraisalHdr addOrUpdateLine(@PathVariable(value = "id") Integer id, @RequestBody AppraisalHdr appraisalHdr)
			throws SmartOfficeException {
		AppraisalHdr appraisalHdrById = appraisalHdrRepo.findById(id).get();
		appraisalHdrRepo.save(appraisalHdrById);
		if (appraisalHdrById.getGoals() != null && !appraisalHdrById.getGoals().isEmpty()
				|| appraisalHdrById.getDevActions() != null && !appraisalHdrById.getDevActions().isEmpty()) {
			for (AppraisalGoal appraisalGoal : appraisalHdrById.getGoals()) {
				appraisalGoalRepo.save(appraisalGoal);
			}
			for (AppraisalDev appraisalDev : appraisalHdrById.getDevActions()) {
				appraisalDevRepo.save(appraisalDev);
			}

		} else {
			throw new SmartOfficeException("No value to be added");
		}
		return appraisalHdrById;
	}

	@PostMapping("/add-review")
	public AppraisalReview addReview(@RequestBody AppraisalReview appraisalReview) throws SmartOfficeException {
		return appraisalReviewRepo.save(appraisalReview);
	}

	@PatchMapping("/{id}/update-review")
	public AppraisalReview addReview(@PathVariable(value = "id") Integer id,
			@RequestBody AppraisalReview appraisalReview) throws SmartOfficeException {
		return appraisalReviewRepo.save(appraisalReview);
	}

	@DeleteMapping("/id/delete-hdr")
	public void deleteHdr(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		appraisalHdrRepo.deleteById(id);
	}

	@DeleteMapping("/id/delete-goals")
	public void deleteGoal(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		appraisalGoalRepo.deleteById(id);
	}

	@DeleteMapping("/id/delete-dev")
	public void deleteDev(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		appraisalDevRepo.deleteById(id);
	}

	@DeleteMapping("/id/delete-review")
	public void deleteReview(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		appraisalReviewRepo.deleteById(id);
	}
	
	public List<AppraisalHdr>getByFyId(String fyId)throws SmartOfficeException{
		List<AppraisalHdr>appraisalHdrs=appraisalHdrRepo.findByFyId(fyId);
		List<AppraisalHdr>filterapp= new ArrayList<>();
		for(AppraisalHdr appraisalHdr:appraisalHdrs) {
			if(appraisalHdr.getAppraisalTargetStatusCode().equals("EMP-ACCEPTED")||appraisalHdr.getAppraisalTargetStatusCode().equals("APPROVED")||
					appraisalHdr.getAppraisalTargetStatusCode().equals("DIRECTOR-APPROVAL-PENDING")||appraisalHdr.getAppraisalTargetStatusCode().equals("DIRECTOR-APPROVED")||appraisalHdr.getAppraisalTargetStatusCode().equals("EMP-ACCEPTANCE")) {
				filterapp.add(appraisalHdr);
			}
		}
		return filterapp;
	}
	
	@GetMapping("/{fyId}/refresh")
	public List<DocInfo> refresh(@PathVariable(value = "fyId") String fyId, HttpServletRequest request,
			HttpServletResponse response) throws SmartOfficeException, IOException {
		int technicalCount=0;
		int nonTechnicalCount=0;
		List<AppraisalHdr> appraisals = getByFyId(fyId);
		
		
	for(AppraisalHdr appHdr:appraisals) {
		for(AppraisalGoal appraisalGoal:appHdr.getGoals()) {
				if(appraisalGoal.getIsTechnical().equals("Y")) {
					technicalCount++;
				}else {
					nonTechnicalCount++;
				}
				if(appraisalGoal.getStrategy().equals("")) {
					
				}
	}
	}
		List<ResultAnalysis> resultAnalysisList = new ArrayList<ResultAnalysis>();
		List<ResultAnalysisByEmp> resultAnalysisByEmps = new ArrayList<ResultAnalysisByEmp>();
		List<DocInfo> docInfos = new ArrayList<DocInfo>();
		List<AppraisalGoal> goals = new ArrayList<>();
		List<AppraisalGoal> metrics = new ArrayList<>();
		Metric metricById = new Metric();
		int teamTarget = 0;
		int acheivTarget = 0;
		String hdrId = "";
		String fyCode="";
		Integer threshold = 0;
		Integer empTarget = 0;
		Integer finalTarget = 0;
		int i=0;
		for (AppraisalHdr appraisal : appraisals) {
			ResultAnalysis resultAnalysis = new ResultAnalysis();
			ResultAnalysisByEmp resultAnalysisByEmp = new ResultAnalysisByEmp();
			System.out.println(appraisal+"appraisal in filter");
			hdrId = appraisal.getId().toString();
			System.out.println(hdrId);
			fyCode=appraisal.getFyCode();
			resultAnalysis.setId(appraisal.getId().toString());
			resultAnalysisByEmp.setResultAnalysisId(appraisal.getId().toString());
			resultAnalysis.setFyName(appraisal.getFyName());
			resultAnalysisByEmp.setEmployeeName(appraisal.getEmpName());
			resultAnalysisByEmp.setManagerName(appraisal.getN1EmpFName());
			resultAnalysisByEmp.setDesignationName(appraisal.getDesignationName());
			resultAnalysisByEmp.setOfficeName(appraisal.getOfficeName());
			goals = appraisalGoalRepo.findByAppraisalHdrId(hdrId);
			if (goals != null && !goals.isEmpty()) {
				for (AppraisalGoal goal : goals) {
					if (hdrId.equals(goal.getAppraisalHdrId())) {
						resultAnalysis.setGoalDesc(goal.getGoalDesc());
						resultAnalysis.setTarget(goal.getFinalTarget());
						resultAnalysis.setStrategy(goal.getStrategy());
						resultAnalysis.setIsTechnical(goal.getIsTechnical());
						resultAnalysis.setOperatorName(goal.getOperator());
						resultAnalysis.setScoreCode(goal.getEmpScoreCode());
						resultAnalysisByEmp.setManagerScoreCode(goal.getN1ScoreCode());
						metrics = appraisalGoalRepo.findByMetricId(goal.getMetricId());
						metricById = metricService.getMetricById(goal.getMetricId()).get();
						threshold = Integer.parseInt(metricById.getThreshold());
					}
				}
				for (AppraisalGoal metric : metrics) {
					empTarget = Integer.parseInt(metric.getEmpTarget());
					if (hdrId.equals(metric.getAppraisalHdrId())) {
						threshold = Integer.parseInt(metricById.getThreshold());
						empTarget = Integer.parseInt(metric.getEmpTarget());
						finalTarget = Integer.parseInt(metric.getFinalTarget());
						resultAnalysisByEmp.setTargetEmp(String.valueOf(teamTarget));
						resultAnalysis.setMetricId(metric.getMetricId());
						resultAnalysis.setMetricName(metric.getMetricName());
						resultAnalysis.setEmpTarget(metric.getEmpTarget());
						resultAnalysisByEmp.setMetricSymbol(metricById.getUnitTypeSymbol());
						resultAnalysisByEmp.setEmpAcheiv(String.valueOf(empTarget));
						resultAnalysisByEmp.setManagerScore(metric.getN1ScoreName());
						resultAnalysisByEmp.setManagerRemarks(metric.getN1AchvmtRemarks());				
			}
				}
				if (empTarget > threshold) {
					resultAnalysis.setAcheivVerdictName("EXCEED");
					resultAnalysis.setIsAcheived("Y");
				} else if (empTarget < threshold) {
					resultAnalysis.setAcheivVerdictName("NOT-MET");
					resultAnalysis.setIsAcheived("N");
				} else if (empTarget == threshold) {
					resultAnalysis.setAcheivVerdictName("MET");
					resultAnalysis.setIsAcheived("Y");
				}
				teamTarget += empTarget;
				acheivTarget += finalTarget;
				resultAnalysis.setTriggerDt(LocalDateTime.now());
				resultAnalysis.setFyId(fyId);
				resultAnalysis.setThreshold(metricById.getThreshold());
				resultAnalysis.setTeamTarget(String.valueOf(teamTarget));
				resultAnalysis.setResltsAcheivedByTeam(String.valueOf(acheivTarget));
				resultAnalysis.setIsEnabled("Y");
				resultAnalysis.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				resultAnalysis.setHttpResponseStatus(String.valueOf(response.getStatus()));
				resultAnalysisList.add(resultAnalysis);
				resultAnalysisByEmps.add(resultAnalysisByEmp);
				resultAnalysis.setResultAnalysisByEmps(resultAnalysisByEmps);
				
			}else {
				throw new SmartOfficeException("Appraisal Header without goal is a orphan record so it cannot to written into json file");
			}
		}
		System.out.println("resultAnalysisList"+resultAnalysisList);
		if (empTarget > threshold) {
			docInfos = formingJson(request, response,resultAnalysisList,fyCode);
		} else if (empTarget < threshold) {
			docInfos = formingJson(request, response,resultAnalysisList,fyCode);
		} else if (empTarget == threshold) {
			docInfos = formingJson(request, response,resultAnalysisList,fyCode);
		}
		System.out.println(resultAnalysisList);
	
		return docInfos;
	}

	
	@Async("asyncThreadPoolTaskExecutor")
	public List<DocInfo> formingJson(HttpServletRequest request, HttpServletResponse response,List<ResultAnalysis>resultAnalysisList,String fyCode) throws SmartOfficeException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String check = "";
		List<DocInfo> docInfos = new ArrayList<DocInfo>();

		
		System.out.println(resultAnalysisList);
		String result = statusCheck(String.valueOf(response.getStatus()));
		if (result != null && !result.isEmpty()) {

			List<String> checks = new ArrayList<>();
			if (result.equals("Error in service")) {
				checks.add(result);
				checks.add(request.getRequestURL() + "-" + "Error in this url");

				checks.add(soe.getLocalizedMessage());

			} else if (result.equals("Processing")) {
				checks.add(result);

			} else if (result.equals("success")) {
				checks.add(result);
			}
			checks.add(commonUtils.getLoggedinEmployeeId());
			checks.add(fyCode);
			
			check = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(checks);
			
		}
		try (FileWriter file = new FileWriter("/opt/so/data/docs/Refresh-Org-Analysis/02-2020/status-check.json")) {
			file.write(check);
			file.flush();
			
			List<DocInfo> exDocInfo= docInfoRepository.findByDocName("status-check.json");
			System.out.println(exDocInfo);
			System.out.println(commonUtils.generateId());
			System.out.println(commonUtils.generateId());
			if(exDocInfo==null||exDocInfo.isEmpty()) {
				DocInfo docInfo = new DocInfo();
			docInfo.setDocLocation("/opt/so/data/docs/Refresh-Org-Analysis/02-2020/");
			docInfo.setDocId("status-check");
			docInfo.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			docInfo.setCreatedDt(LocalDateTime.now());
			docInfo.setDocTypeId("80");
			docInfo.setDocExtension("json");
			docInfo.setDocName("status-check.json");
			docInfoRepository.save(docInfo);
			docInfos.add(docInfo);
			}else {
				docInfos.addAll(exDocInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String preetyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultAnalysisList);
		List<DocInfo> exDocInfos= docInfoRepository.findByDocName("refresh.json");
		System.out.println(exDocInfos);
		try (FileWriter file = new FileWriter("/opt/so/data/docs/Refresh-Org-Analysis/02-2020/refresh.json")) {
			file.write(preetyJson);
			file.flush();
			
			if(exDocInfos==null||exDocInfos.isEmpty()) {
				DocInfo docs= new DocInfo();
			docs.setDocLocation("/opt/so/data/docs/Refresh-Org-Analysis/02-2020/");
			docs.setDocId("refresh-org-analysis");
			docs.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			docs.setCreatedDt(LocalDateTime.now());
			docs.setDocTypeId("80");
			docs.setDocExtension("json");
			docs.setDocName("refresh.json");
			docInfoRepository.save(docs);
			docInfos.add(docs);
			}else {
				docInfos.addAll(exDocInfos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docInfos;
	}
	@Async("asyncThreadPoolTaskExecutor")
	public String statusCheck(String response) throws SmartOfficeException {
		String status = "";
		if (response.equals("200")) {
			status = "success";
		} else if (response.equals("500")) {
			status = "Error in service";
		} else if (response.equals("404")) {
			status = "Error in service";
		} else if (response.equals("102")) {
			status = "Processing";
		}
		return status;
	}
	
	@GetMapping("/reviews")
	public Iterable<ReviewAppraisal>getReviewAppraisals()throws SmartOfficeException{
		return reviewAppraisalRepo.findAll();
	}
	
}
