package com.ss.smartoffice.soservice.transaction.Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@Service
@RestController
@RequestMapping("/transaction/interview")
public class InterviewTrackerService {

	@Autowired
	InterviewRepository interviewRepository;

	@Autowired
	InterviewRoundRepository interviewRoundRepository;

	@Autowired
	InterviewHelper interviewHelper;

	@Autowired
	CommonUtils commonUtils;

	@GetMapping
	public Iterable<Interview> getInterviewsTracker(Model model, Pageable pageable,
			@RequestParam(value = "applicantName", required = false) String applicantName) {
		Page<Interview> pages = interviews(pageable, applicantName);
		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		model.addAttribute("materials", pages.getContent());
		return pages;
	}

	public Page<Interview> interviews(Pageable pageable, String applicantName) {
		boolean searchByApplicantName = false;

		if (applicantName != null)
			searchByApplicantName = true;

		if (searchByApplicantName) {
			return interviewRepository.findByApplicantName(pageable, applicantName);
		}
		return interviewRepository.findAll(pageable);
	}

	@GetMapping("/interviewer-view")
	public Iterable<Interview> getAllByInterviewer() throws SmartOfficeException {
//		List<Interview> interviewList = new ArrayList<Interview>();
		String id = commonUtils.getLoggedinEmployeeId();
//		for (Interview interviewSummary : interviewRepository.findByInterviewerId(id)) {
//			interviewList.add(interviewRepository.findById( interviewSummary.getId()).get());
//		}
		return interviewRepository.findByInterviewerId(id);
//		return interviewList;
		
	}
	
	@GetMapping("/hr-view/rounds")
	public Iterable<InterviewRound> getAllInterviewRounds() throws SmartOfficeException { 
		try {
			return interviewRoundRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e);
		} 
		
	}
	
	@GetMapping("/interviewer-view/rounds")
	public Iterable<InterviewRound> getInterviewRoundsForInterviewer() throws SmartOfficeException { 
		try {
			return interviewRoundRepository.findByInterviewEmpIdOrderByRoundDateTimeAsc(commonUtils.getLoggedinEmployeeId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e);
		} 
		
	}

	@GetMapping("/interviewer-view/{id}")
	public Optional<Interview> getInterviewDetailsForInterviewer(@PathVariable(value = "id") int id) {
		Interview interview= interviewRepository.findById(id).get();
		int myPosition = 0;
		for(InterviewRound intRound : interview.getInterviewRound()) {
			if(intRound.getInterviewEmpId().equals(commonUtils.getLoggedinEmployeeId())){
				if(intRound.getRoundOrder()>myPosition) myPosition =  intRound.getRoundOrder(); // assign only if a roundOrder is more than myPosition
			}
		}
		List<InterviewRound> visibleRounds = new ArrayList<InterviewRound>();
		for(InterviewRound intRound : interview.getInterviewRound()) {
			if(intRound.getRoundOrder()<=myPosition) {
				visibleRounds.add(intRound);
			}
		}
		interview.setInterviewRound(visibleRounds);		
		return Optional.of(interview);
	}
	@GetMapping("/{id}")
	public Optional<Interview> getInterviewById(@PathVariable(value = "id") int id) {
		return interviewRepository.findById(id);
	}

	@PostMapping
	public Interview addInterviewTracker(@RequestBody Interview intv) {
		return interviewRepository.save(intv);
	}

	@PatchMapping("/round/{id}/{action}")
	public Interview roundAction(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "action") String action,@RequestBody InterviewRound interviewRound) {

		return interviewHelper.roundAction(id, action, interviewRound);
	}

	@PatchMapping("/{id}/decide/{action}")
	public Interview finalDecision(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "action") String action, @RequestBody Interview interview) {
		interviewHelper.finalDecision(id, action, interview);
		return interview;
	}

	@PatchMapping("/{id}/update")
	public Interview updateInterview(@RequestBody Interview interview) {
		return interviewRepository.save(interview);
	}

	@DeleteMapping("/{id}")
	public void deleteInterview(@PathVariable(value = "id") int id) {
		interviewRepository.deleteById(id);

	}

}
