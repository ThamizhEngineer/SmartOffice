package com.ss.smartoffice.soservice.transaction.incident;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("transaction/incident/test")
public class IncidentTestService {
	
	@Autowired
	IncidentTestRepo incidentTestRepo;
	
	@Autowired
	IncidentRepo incidentRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	IncidentTestQuestionRepo incTestQueRepo;
	
	@Autowired
	IncidentTestReportService incTestReportService;
	
	
	@GetMapping("/{applicantId}/fetchByApplicantId")
	public List<FetchIncidentTestView> fetchByApplicantId(@PathVariable(value="applicantId")String applicantId) {
		List<FetchIncidentTestView> i = incidentTestRepo.findByApplicantId(applicantId);
		return i;
	}
	
	@GetMapping("/fetchAllTest")
	public Iterable<IncidentTest> fetchAll() {
		return incidentTestRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<IncidentTest> getIncidetTestById(@PathVariable(value="id")Integer id ) throws SmartOfficeException{
		return incidentTestRepo.findById(id);
	}
	
	@PatchMapping("/{id}/start-test")
	public IncidentTestQuestion startTest(@PathVariable(value="id")Integer id) {
		Optional<IncidentTest> incidentTest = incidentTestRepo.findById(id);
		incidentTest.ifPresent(incTest ->{
			incTest.setStartTime(LocalDateTime.now().toString());
			incTest.setTestStatus("In-Progress");
			incTest.setRemainingTime(calculateRemainingTime(incTest.getStartTime()));
			incidentTestRepo.save(incTest);
		});
	return nextQuestion(id,0);
	}
	
	@PatchMapping("/{id}/continue-test")
	public IncidentTestQuestion continueTest(@PathVariable(value="id")Integer id) {
		Optional<IncidentTest> incidentTest = incidentTestRepo.findById(id);
		if (incidentTest.isPresent()) {
			if(incidentTest.get().getTestStatus().equals("In-Progress")) {
				return nextQuestion(id,0);
			}else {
			 	throw new SmartOfficeException("Status Not In-Progress");
			}
		}else {
		 	throw new SmartOfficeException("No next question");
		}
	}
	
	@GetMapping("/{id}/{questionOrder}/next-question")
	public IncidentTestQuestion nextQuestion(@PathVariable(value="id")Integer id,@PathVariable(value="questionOrder")Integer qOrder) {
		try {
			Optional<IncidentTest> incidentTest = incidentTestRepo.findById(id);
			if (incidentTest.isPresent()) {
				incidentTest.get().setRemainingTime(calculateRemainingTime(incidentTest.get().getStartTime()));
				List<IncidentTestQuestion> i = incidentTest.get().getIncidentTestQuestions().stream().filter(q -> q.getQuestionOrder().equals(qOrder+1)).collect(Collectors.toList());
				if(i.size()==0) {
					throw new SmartOfficeException("No next question");
				}
				i.get(0).setRemainingTime(calculateRemainingTime(incidentTest.get().getStartTime()));
				return i.get(0);
			}else {
			 	throw new SmartOfficeException("No next question");
			}
		}catch (SmartOfficeException soe) {
			throw soe;
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		
	}
	
	@GetMapping("/{id}/{questionOrder}/previous-question")
	public IncidentTestQuestion previousQuestion(@PathVariable(value="id")Integer id,@PathVariable(value="questionOrder")Integer qOrder) {
		Optional<IncidentTest> incidentTest = incidentTestRepo.findById(id);
		if (incidentTest.isPresent()) {
			incidentTest.get().setRemainingTime(calculateRemainingTime(incidentTest.get().getStartTime()));
			List<IncidentTestQuestion> i = incidentTest.get().getIncidentTestQuestions().stream().filter(q -> q.getQuestionOrder().equals(qOrder-1)).collect(Collectors.toList());
			return i.get(0);
		}else {
		 	throw new SmartOfficeException("No Previous question");
		}
	}
	
	@PatchMapping("/{questionId}/{questionOrder}/{userSelected}/answer")
	public IncidentTestQuestion answer(@PathVariable(value="questionId")Integer questionId,@PathVariable(value="questionOrder")Integer qOrder,@PathVariable(value="userSelected")String userSelected) {
		Optional<IncidentTestQuestion> inctestQ = incTestQueRepo.findById(questionId);
		IncidentTestQuestion i = inctestQ.get();
		i.setUserPicked(userSelected);
		if (commonUtils.isNullOrEmpty(userSelected)==false) {
			if (userSelected.equals(i.getCorrectOption())) {
				i.setPositiveMarkObtained(i.getMarkPerQuestion());
				i.setMarksAwarded(i.getMarkPerQuestion());
			}else {
				i.setNegativeMarkObtained(i.getNegativeMarks());
			}
		}
		incTestQueRepo.save(i);
		Optional<IncidentTest> incidentTest = incidentTestRepo.findById(inctestQ.get().getTIncidentTestId());
		Integer noOfQues = Integer.valueOf(incidentTest.get().getNoOfQuestions());Integer answered = 1;Integer unattended=0;
		if (commonUtils.isNullOrEmpty(incidentTest.get().getTotalUnAttended())==false) {
			Integer totalUnAttended = Integer.valueOf(incidentTest.get().getTotalUnAttended());
			if (totalUnAttended!=0) {
				unattended = totalUnAttended-answered;
			}else {
				unattended = noOfQues-answered;
			}
		} else {
			unattended = noOfQues-answered;
		}
		incidentTest.get().setTotalUnAttended(unattended.toString());
		incidentTestRepo.save(incidentTest.get());
		return nextQuestion(i.getTIncidentTestId(),qOrder);
	}
	
	@PatchMapping("/{id}/submit-test")
	public IncidentTest submit(@PathVariable(value="id")Integer id) {
		Optional<IncidentTest> incidentTest = incidentTestRepo.findById(id);
		if (incidentTest.isPresent()) {
			IncidentTest i = incidentTest.get();
			i.setEndTime(LocalDateTime.now().toString());
			i.setTestStatus("Completed");
			i = incidentTestRepo.save(i);
			return incTestReportService.intiateEvaluation(i);
		}else {
		 	throw new SmartOfficeException("No next question");
		}
	}
	
	public double calculateMedian(@PathVariable(value="incidentId")Integer incidentId)throws SmartOfficeException{
		Incident incident=incidentRepo.findById(incidentId).get();
		double d = 0;
		
		List<IncidentTest>incidentTests=incident.getIncidentTests();
		List<Integer>scoreList=new ArrayList<Integer>();
		for(IncidentTest incidentTest:incidentTests) {
			if(incidentTest.getScore()!=null&&!incidentTest.getScore().isEmpty()) {
				scoreList.add(Integer.parseInt(incidentTest.getScore()));
			}
		}
		for(int i=scoreList.size();i>0;i--) {
			int[] array = scoreList.stream().mapToInt(j->j).toArray();
			 d=CommonUtils.findMedian(array, scoreList.size());
			List<IncidentTest> incidentTestList=incidentTestRepo.findByincidentId(incidentId);
			for(IncidentTest incidentTest:incidentTestList) {
				incidentTest.setScoreMedian(String.valueOf(d));
				incidentTestRepo.save(incidentTest);
			}
		}
		return d;
	}
	
	@GetMapping("/{inputTimeString}/remainingTime")
	public String calculateRemainingTime(@PathVariable(value="inputTimeString")String inputTimeString) {
		inputTimeString = inputTimeString.replace("T", " ");
		inputTimeString = inputTimeString.substring(0,19);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startTime = LocalDateTime.parse(inputTimeString,formatter);
		LocalDateTime endTime = LocalDateTime.now();
		LocalDateTime tempDateTime = LocalDateTime.from( startTime );
		long minutes = tempDateTime.until( endTime, ChronoUnit.MINUTES);
		String remaining = Long.toString(minutes);
		return remaining;
	}
	
	@GetMapping("/{id}/heart-beat")
	public String heartBeat(@PathVariable(value="id")Integer id) {
		Optional<IncidentTest> incidentTest = incidentTestRepo.findById(id);
		if (incidentTest.isPresent()) {
			return calculateRemainingTime(incidentTest.get().getStartTime());
		}else {
		 	throw new SmartOfficeException("No data to return");
		}
	}

	
}
