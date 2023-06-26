package com.ss.smartoffice.soservice.transaction.incident;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.IncidentApplicant;

@RestController
@RequestMapping("transaction/incident/testreports")
public class IncidentTestReportService {

	@Autowired
	IncidentTestRepo incidentTestRepo;
	
	@Autowired
	IncidentTestTemplateRepo incTestTemplateRepo;
	
	@Autowired
	IncidentTestService incidentTestService;
	
	@Autowired
	IncidentApplicantRepo incApplicantRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	IncidentTestHistoryRepo incTestHistoryRepo;

	@GetMapping
	public Iterable<IncidentTest> fetchIncidentTest() {
		return incidentTestRepo.findAll();
	}

	@GetMapping("{id}")
	public Optional<IncidentTest> fetchIncidentTestById(@PathVariable(value = "id") Integer id) {
		return incidentTestRepo.findById(id);
	}
	
	@GetMapping("/BetweenScores/{startScore}/{endScore}")
	public List<IncidentTest> fetchBewtweenScores(@PathVariable(value = "startScore") String startScore,@PathVariable(value = "endScore") String endScore){
		return incidentTestRepo.fetchBetweenScores(startScore, endScore);
	}
	
	@GetMapping("/BetweenMark/{startMark}/{endMark}")
	public List<IncidentTest> fetchBewtweenMarks(@PathVariable(value = "startMark") String startMark,@PathVariable(value = "endMark") String endMark){
		return incidentTestRepo.fetchBetweenScores(startMark, endMark);
	}
	
	
	@GetMapping("/advanceFilters")
	public List<IncidentTest> advanceFetchQuery(
			@RequestParam (value="startScore",required=false)Integer startScore,
			@RequestParam (value="endScore",required=false)Integer endScore,
			@RequestParam (value="incidentId",required=false)Integer incidentId,
			@RequestParam (value="testResult",required=false)String testResult,
			@RequestParam (value="testStatus",required=false)String testStatus,
			@RequestParam (value="startMark",required=false)Integer startMark,
			@RequestParam (value="endMark",required=false)Integer endMark){
		System.out.println("startScore="+startScore+","+"endScore="+endScore);
		System.out.println("startMark="+startMark+","+"endMark="+endMark);
		return incidentTestRepo.fetchByAdvanceFilters(startScore, endScore,incidentId,testResult,testStatus,startMark,endMark);
	}
	

	@GetMapping("/incidents-for-applicants/{applicantId}")
	public List<IncidentTest> fetchByApplicantId(@PathVariable(value = "applicantId") Integer applicantId) {
		return incidentTestRepo.fetchByApplicantId(applicantId.toString());
	}
	
	@GetMapping("/fetch-by-incident-id/{incidentId}")
	public List<IncidentTest> fetchByIncidentId(@PathVariable(value="incidentId") Integer incidentId){
		return incidentTestRepo.fetchByIncidentId(incidentId);
	}
	
	@PatchMapping("evaluate")
	public IncidentTest intiateEvaluation(@RequestBody IncidentTest incidentTest) {

		Optional<IncidentTestTemplate> incidentTestTemplate= incTestTemplateRepo.findById(Integer.parseInt(incidentTest.getIncidentTtId()));
		System.out.println(incidentTestTemplate);
		if (incidentTestTemplate.isPresent()) {
			return incidentTest =evaluate(incidentTestTemplate.get(),incidentTest);
		} else {
			throw new SmartOfficeException("No data to evaluate");
		}
	}
	
	public IncidentTest evaluate(IncidentTestTemplate incTestTemplate,IncidentTest incTest) {
		Integer totalCorrect=0; Integer totalUnattended=0; Integer totalWrong=0;
		Integer totalAttended=0; double totalNegativeMarkObtained=0;double totalPositiveMarkObtained=0;
		Integer totalQuestion=0; double totalMarks=0;
		Integer totalEasyCorrect=0;Integer totalMediumCorrect=0;Integer totalToughCorrect=0;
		
		for (IncidentTestQuestion incTestQuestion : incTest.getIncidentTestQuestions()) {
			totalQuestion=totalQuestion+1;
			if (incTestQuestion.getUserPicked()!=null) {
				totalAttended = totalAttended+1;
			}
			if (incTestQuestion.getUserPicked()==null) {
				totalUnattended = totalUnattended+1;
			}
			if (incTestQuestion.getCorrectOption().equals(incTestQuestion.getUserPicked())) {
				totalCorrect = totalCorrect+1;
				if(incTestQuestion.getDifficultyLevel().equals("EASY")) {
					totalEasyCorrect = totalEasyCorrect+1;
				}
				if(incTestQuestion.getDifficultyLevel().equals("MEDIUM")) {
					totalMediumCorrect = totalMediumCorrect+1;
				}
				if(incTestQuestion.getDifficultyLevel().equals("TOUGH")) {
					totalToughCorrect = totalToughCorrect+1;
				}
			}
			if (!incTestQuestion.getCorrectOption().equals(incTestQuestion.getUserPicked())) {
				totalWrong = totalWrong+1;
			}
//			if(CommonUtils.isNullOrEmpty(incTestQuestion.getPositiveMarkObtained())==false) {
//				totalPositiveMarkObtained = totalPositiveMarkObtained+1;
//			}
//			if(CommonUtils.isNullOrEmpty(incTestQuestion.getNegativeMarkObtained())==false) {
//				totalNegativeMarkObtained = totalNegativeMarkObtained+1;
//			}
			if(commonUtils.isNullOrEmpty(incTestQuestion.getPositiveMarkObtained())==false) {
				totalPositiveMarkObtained = totalPositiveMarkObtained+Integer.parseInt(incTestQuestion.getPositiveMarkObtained());
			}
			if(commonUtils.isNullOrEmpty(incTestQuestion.getNegativeMarkObtained())==false) {
				totalNegativeMarkObtained = totalNegativeMarkObtained+Integer.parseInt(incTestQuestion.getNegativeMarkObtained().replace("-",""));
			}
			if(commonUtils.isNullOrEmpty(incTestQuestion.getMarkPerQuestion())==false) {
				double markPerQue = Double.parseDouble(incTestQuestion.getMarkPerQuestion());
				totalMarks = totalMarks+markPerQue;
			}
			
		}
		double totalMarksObtained=0;double passPercentageObtained=0;

		totalMarksObtained = totalPositiveMarkObtained-totalNegativeMarkObtained;
		System.out.println("totalMarks="+totalMarks+"-totalPositiveMarkObtained="+totalPositiveMarkObtained+"-totalNegativeMarkObtained="+totalNegativeMarkObtained);
		
		passPercentageObtained = calculatePercentage(totalMarksObtained,totalMarks);
		System.out.println("passPercentageObtained="+passPercentageObtained);
		incTest.setTotalResult(String.valueOf(totalMarksObtained));
		incTest.setTotalCorrect(totalCorrect.toString());
		incTest.setTotalUnAttended(totalUnattended.toString());
		incTest.setTotalWrong(totalWrong.toString());
		incTest.setTotalPositiveMarkObtained(String.valueOf(totalPositiveMarkObtained));
		incTest.setTotalNegativeMarkObtained(String.valueOf(totalNegativeMarkObtained));
		incTest.setTotalCorrectEasy(totalEasyCorrect.toString());
		incTest.setTotalCorrectMedium(totalMediumCorrect.toString());
		incTest.setTotalCorrectTough(totalToughCorrect.toString());
		Long p=Math.round(passPercentageObtained);
		incTest.setScore(String.valueOf(p));
		
		System.out.println("totalCorrect="+totalCorrect+"-totalUnattended="+totalUnattended+"-totalWrong="+totalWrong+"-passPercentageObtained="+passPercentageObtained);
		incTest=incidentTestRepo.save(incTest);
		incidentTestService.calculateMedian(incTest.getIncidentId());
		return incTest;
	}
	
    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
	
	@PatchMapping("/finalDecision/{id}/{participantId}/{decision}")
	public IncidentApplicant finalDecision(@PathVariable(value="id")Integer id,@PathVariable(value="participantId")Integer participantId,@PathVariable(value="decision")String decision) {
		Optional<IncidentApplicant> ia = incApplicantRepo.findById(participantId);
		Optional<IncidentTest> it = incidentTestRepo.findById(id);
		if (ia.isPresent()) {
			if (it.isPresent()) {
				if (decision.equals("pass")) {
					ia.get().setFinalTestStatus("pass");
					ia.get().setFinalTestDt(LocalDateTime.now());
					ia.get().setFinalTestEmpId(commonUtils.getLoggedinEmployeeId());
					it.get().setTestResult("pass");
				}
				if (decision.equals("fail")) {
					ia.get().setFinalTestStatus("fail");
					ia.get().setFinalTestDt(LocalDateTime.now());
					ia.get().setFinalTestEmpId(commonUtils.getLoggedinEmployeeId());
					it.get().setTestResult("fail");
				}
				if (decision.equals("in-hold")) {
					ia.get().setFinalTestStatus("in-hold");
					ia.get().setFinalTestDt(LocalDateTime.now());
					ia.get().setFinalTestEmpId(commonUtils.getLoggedinEmployeeId());
					it.get().setTestResult("in-hold");
				}
				incidentTestRepo.save(it.get());
				validateForTestHistory(it.get());
				return incApplicantRepo.save(ia.get());
			}else{throw  new SmartOfficeException("No data in incident test");}
		} else {throw  new SmartOfficeException("No data in incident applicant");}		
	}
	
	public void validateForTestHistory(IncidentTest iTest) {
		Optional<IncidentTestHistory> ith = incTestHistoryRepo.fetchByIncidentTestId(iTest.getId());
		if (ith.isPresent()) {
			System.out.println("if history for same test id exists");
			System.out.println(ith.get());
			IncidentTestHistory forUpdation = formTestHistoryObject(iTest);
			forUpdation.setId(ith.get().getId());
			forUpdation.setModifiedBy(commonUtils.getLoggedinEmployeeId());
			forUpdation.setModifiedDt(LocalDateTime.now());
			incTestHistoryRepo.save(forUpdation);
		}else {
			IncidentTestHistory forCreation = formTestHistoryObject(iTest);
			forCreation.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			forCreation.setCreatedDt(LocalDateTime.now());
			incTestHistoryRepo.save(forCreation);
		}
	}
	
	public IncidentTestHistory formTestHistoryObject(IncidentTest iTest) {
		return IncidentTestHistory.builder()
				.endTime(iTest.getEndTime())
				.invigilatorComments(iTest.getInvigilatorComments())
				.invigilatorId(iTest.getInvigilatorId())
				.isEnabled(iTest.getIsEnabled())
				.noOfQuestions(iTest.getNoOfQuestions())
				.scoreMedian(iTest.getScoreMedian())
				.participantComment(iTest.getParticipantComment())
				.participantId(iTest.getParticipantId())
				.passPercentage(iTest.getPassPercentage())
				.startTime(iTest.getStartTime())
				.testDate(iTest.getTestDate())
				.testStatus(iTest.getTestStatus())
				.totalCorrect(iTest.getTotalCorrect())
				.totalUnAttended(iTest.getTotalUnAttended())
				.totalWrong(iTest.getTotalWrong())
				.totalResult(iTest.getTotalResult())
				.score(iTest.getScore())
				.incidentId(iTest.getIncidentId())
				.duration(iTest.getDuration())
				.incidentTtId(iTest.getIncidentTtId())
				.testResult(iTest.getTestResult())
				.totalPositiveMarkObtained(iTest.getTotalPositiveMarkObtained())
				.totalNegativeMarkObtained(iTest.getTotalNegativeMarkObtained())
				.totalCorrectEasy(iTest.getTotalCorrectEasy())
				.totalCorrectMedium(iTest.getTotalCorrectMedium())
				.totalCorrectTough(iTest.getTotalCorrectTough())
				.incidentTestId(iTest.getId())
				.build();
	}
	
	
	@GetMapping("/fetchHistoryByTestId/{incidentTestId}")
	public Optional<IncidentTestHistory> fetchByttid(@PathVariable(value = "incidentTestId") Integer incidentTestId) {
		return incTestHistoryRepo.fetchByIncidentTestId(incidentTestId);
	}
	
	@GetMapping("/fetchHistoryByIncidentId/{incidentId}/{parId}/{ttId}")
	public List<IncidentTestHistory> fetchTestHisByIncidentid(
			@PathVariable(value = "incidentId") Integer incidentId,
			@PathVariable(value = "parId") Integer parId,
			@PathVariable(value = "ttId") String ttId) {
		return incTestHistoryRepo.fetchByIncidentId(incidentId,parId,ttId);
	}
}
