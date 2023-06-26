package com.ss.smartoffice.soservice.transaction.incident;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.Config;
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.employee.Employee;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.seed.configs.ConfigService;
import com.ss.smartoffice.soservice.transaction.ProfileFinderService.ProfileFinderJob;
import com.ss.smartoffice.soservice.transaction.vacancyrequest.VacancyRequest;

@RestController
@RequestMapping("transaction/incidents")
public class IncidentService {
	@Autowired
	IncidentRepo incidentRepo;
	@Autowired
	IncidentApplicantRepo incidentApplicantRepo;
	@Autowired
	ConfigService configService;
	@Autowired
	ParticipatingInstitueRepo participatingInstituteRepo;
	@Autowired
	CommonUtils commonUtils;
	@Autowired 
	EmployeeRepository employeeRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	private static Logger log = LoggerFactory.getLogger(IncidentAsyncHelper.class);

	@Autowired
	IncidentHelper incidentHelper;

	ArrayList<String> knownActions = new ArrayList<String>(Arrays.asList("create", "import-applicant", "approval",
			"shortlist", "schedule", "add-applicant", "check-test-eligibility", "schedule-test",
			"interview-eligibility", "interview-schedule", "start-test", "attended", "certificate-issue","reallocate-test","generate-template","facility-update","training-notification"));

	@Autowired
	UserGroupEmployeeMappingService userGroupEmployeeMappingService;

	@GetMapping
	public List<Incident> getIncidents() throws SmartOfficeException {
		Map<Integer,Incident> incidentMap = new HashMap<Integer, Incident>();
//		List<Incident> incidentList = new ArrayList<>();
		List<String> hr1UsrGrpIds = userGroupEmployeeMappingService.getUserGroupHrId();
		List<String> hr2UsrGrpIds = userGroupEmployeeMappingService.getUserGroupHr2Id();
		// was trying to implement in by giving two list in repo query but one by giving
		// or in in parameter query was getting error so implemented this way
//		for (String hr1UsrGrpId : hr1UsrGrpIds) {
//			incidentList = incidentRepo.findByHr1UsrGrpIdOrHr2UsrGrpId(hr1UsrGrpId, null);
////			for (String hr2UsrGrpId : hr2UsrGrpIds) {   
////				System.out.println(hr2UsrGrpId);
////				incidentList = incidentRepo.findByHr1UsrGrpIdOrHr2UsrGrpId(null, hr2UsrGrpId);
////			}
//		}
		
		
		
		for(String hr1UsrGrpId:hr1UsrGrpIds) {
			for (Incident i : incidentRepo.findByHr1UsrGrpIdOrHr2UsrGrpId(hr1UsrGrpId, null)) {
				incidentMap.put(i.getId(), i);
			} 
		}
		
		for(String hr2UsrGrpId:hr2UsrGrpIds) { 				
			for (Incident i : incidentRepo.findByHr1UsrGrpIdOrHr2UsrGrpId(null,hr2UsrGrpId)) {
				incidentMap.put(i.getId(), i);
			} 				
		}
		
		return new ArrayList<Incident>(incidentMap.values());
//		return incidentList;
	}

	@GetMapping("/event-type/emp/{type}")
	public Iterable<Incident> getIncidentsByEventType(@PathVariable(value="type")String type) throws SmartOfficeException {
		List<Incident> incident = incidentRepo.findByIncidentType(type);
		if(type.equals("Training")) {
			for(Incident inc:incident) {
				List<IncidentApplicant> incApp = new ArrayList<IncidentApplicant>();
				incApp = incidentApplicantRepo.findAlreadyExiste(commonUtils.getLoggedinEmployeeId(), inc.getId().toString());
				if(incApp.isEmpty()) {
					inc.setIsEmpApplied("N");				
				}else {
					inc.setIsEmpApplied("Y");
				}
			}	
		}		
		return incident;
	}
	
	@GetMapping("/dir-view")
	public Iterable<Incident> getDirIncidents() throws SmartOfficeException {
		return incidentRepo.findAll();

	}

	@GetMapping("/{id}/incident-applicant")
	public Optional<IncidentApplicant> getIncidentApplicantById(@PathVariable(value = "id") String id)
			throws SmartOfficeException {
		return incidentApplicantRepo.findById(Integer.parseInt(id));
	}

	@GetMapping("/{id}")
	public Optional<Incident> getIncidentById(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		return incidentRepo.findById(id);
	}

	@PostMapping
	public Incident addIncidentS(@RequestBody Incident incident) throws SmartOfficeException {
		List<Config> config = (List<Config>) configService.getConfig(incident.getIncidentType(),
				incident.getIncidentType());
		if (!(config.get(0) == null)) {
			incident.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			incident.setCreatedDt(LocalDateTime.now());
			incidentRepo.save(incident);
		} else {
			throw new SmartOfficeException("Incident Type not exists");
		}
		return incident;
	}

	@PatchMapping("/{id}/update")
	public Incident updateIncident(@RequestBody Incident incident) throws SmartOfficeException {
		int applnCount = incident.getIncidentApplicants().size();
		incident.setTotalApplnCount(String.valueOf(applnCount));
		return incidentRepo.save(incident);
	}

	@PatchMapping("/{id}/update-incident-applicant")
	public IncidentApplicant updateIncidentApplicant(@RequestBody IncidentApplicant incidentApplicant)
			throws SmartOfficeException {

		return incidentApplicantRepo.save(incidentApplicant);
	}

	@DeleteMapping("/{id}/delete")
	public void deleteIncident(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		incidentRepo.deleteById(id);
	}
	
	@PostMapping("/{action}")
	@Transactional
	public Incident addIncident(@PathVariable(value = "action") String action, @RequestBody Incident incident)
			throws SmartOfficeException {
		if (!commonUtils.isHr()) {
			throw new SmartOfficeException("Not a valid user to perform this action");
		} else {
			return incidentHelper.processIncident(action, incident);
		}
	}

	@GetMapping("/advance-search")
	public Iterable<IncidentApplicant> advanceSearch(
			@RequestParam(value = "institute", required = false) String institute,
			@RequestParam(value = "course", required = false) String course,
			@RequestParam(value = "degreeName", required = false) String degreeName,
			@RequestParam(value = "passoutYear", required = false) String passoutYear,
			@RequestParam(value = "marks", required = false) String marks,
			@RequestParam(value = "historyOfArrears", required = false) String historyOfArrears,
			@RequestParam(value = "isEligibleForTest", required = false) String isEligibleForTest)
			throws SmartOfficeException {
		return incidentApplicantRepo.findByAdvanceSearch( institute, course, degreeName, passoutYear, marks, historyOfArrears,
				isEligibleForTest);
	}

	@PatchMapping("/{id}/{action}")
	@Transactional
	public Incident processIncident(@PathVariable(value = "id") String id,
			@PathVariable(value = "action") String action, @RequestBody(required = false) Incident incident)
			throws SmartOfficeException {
		if (knownActions.contains(action)) {
			return incidentHelper.processIncident(action, incident);
		} else {
			throw new SmartOfficeException("Invalid Url");
		}
	}

	@PostMapping(value = "/applicant")
	public IncidentApplicant addIncidentApplicant(@RequestBody IncidentApplicant incidentapplicant) {
		System.out.println(incidentapplicant);
		return incidentApplicantRepo.save(incidentapplicant);
	}
	
	@PostMapping(value = "/training-event/{incidentId}")
	public String addIncidentTrainingApplicant(@PathVariable(value = "incidentId") String incidentId,@RequestBody List<IncidentApplicant> applicantFromReq) {		
		return incidentHelper.trainingEventApplicant(incidentId,applicantFromReq);	
	}
	
	@PostMapping(value = "/multiadd/_internal")
	public Iterable<IncidentApplicant> multiAddApplicant(@RequestBody List<IncidentApplicant> incidentapplicants) {
		System.out.println("MultiaddService="+incidentapplicants);
		return incidentApplicantRepo.saveAll(incidentapplicants);
	}

	@PatchMapping("/{id}/employee-conversion")
	public List<IncidentApplicant> statusForEmpConversion(@PathVariable(value = "id") Integer id,
			@RequestBody(required = false) List<IncidentApplicant> incidentApplicants) {
		Map<Integer, String> m = new HashMap<>();
		for (IncidentApplicant i : incidentApplicants) {
			m.put(i.getId(), i.getFinalDecision());
		}
		System.out.println("map---->" + m);
		incidentHelper.statusManupulationForEmpConversion(m, id);
		return incidentApplicants;
	}

	@GetMapping("/employee-list/{id}")
	public Iterable<memployee> getEmployeeOfTrainingEvent(@PathVariable(value="id") String id){
		List<memployee> employeeFromDb = employeeRepository.fetchManagerReportees(commonUtils.getLoggedinEmployeeId(), commonUtils.getLoggedinEmployeeId());
		for(memployee emp:employeeFromDb) {
			List<IncidentApplicant> incApp = incidentApplicantRepo.findAlreadyExiste(emp.getId().toString(), id);
			if(incApp.isEmpty()) {
				emp.setIsEmpAttend("N");
			}else {
				emp.setIsEmpAttend("Y");
			}
		}
		return employeeFromDb;
	}
	
	@PatchMapping("/{id}/on-board-employee")
	public Incident empOnBoard(@PathVariable(value = "id") Integer id, @RequestBody List<Integer> appIds) {
		Optional<Incident> i = incidentRepo.findById(id);
		if (i.isPresent()) {
			for (Integer appId : appIds) {
				IncidentApplicant ia = i.get().getIncidentApplicants().stream().filter(a -> a.getId().equals(appId))
						.collect(Collectors.toList()).get(0);
				ia.setEmpConversionStatus("in-progress");
				ia.setEmpConversionFlag("Y");
				incidentApplicantRepo.save(ia);
			}
			incidentHelper.onBoardEmployee(id);
			return incidentRepo.save(i.get());
		} else {
			throw new SmartOfficeException("No event data");
		}
	}
	
//	---Delete and Regenerate---------------------------------

	@GetMapping("/regenerate/{id}")
	@Transactional
	public CompletableFuture<Incident> startRegenaration(@PathVariable(value = "id") Integer id)
			throws SmartOfficeException {
		log.info("Regeneration started");
		Optional<Incident> i = incidentRepo.findById(id);
		if (i.isPresent()) {
			i.get().setRegenerationStatus("in-progress");
			incidentRepo.save(i.get());
			CompletableFuture<Incident> c = incidentHelper.triggerRegenration(id, i.get());
			return c;
		} else {
			throw new SmartOfficeException("Error no data");
		}

	}
	
	@GetMapping("/delete/{type}/{id}")
	@Transactional
	public String delete(@PathVariable(value = "id") Integer id,@PathVariable(value = "type") String type)
			throws SmartOfficeException {
		String res = "";
		Optional<Incident> i = incidentRepo.findById(id);
		if (i.isPresent()) {
		res = incidentHelper.deleteByType(type,i.get());
		}
		else {
			throw new SmartOfficeException("data not found");
		}
		return res;
	}
	
	@DeleteMapping("delete-test/{id}/{applicantId}")
	public void DeleteTest(@PathVariable(value="id") Integer id,@PathVariable(value="applicantId") Integer applicantId) {
		incidentHelper.deleteTest(id, applicantId);		
	}
	
	
	@GetMapping("/advance")
	public Iterable<IncidentApplicant> advanceSearch
			(@RequestParam(value = "finalTestStatus", required = false) String finalTestStatus,
			@RequestParam(value = "finalInterviewStatus", required = false) String finalInterviewStatus,
			@RequestParam(value = "finalDecision", required = false) String finalDecision)
			throws SmartOfficeException {
		return incidentApplicantRepo.fliter(finalTestStatus,finalInterviewStatus,finalDecision);
	}
//	---Clear orphan records----------------------------------------
	
	@PatchMapping("/clear-orphan-records")
	public void clearOrphanRecords() {
		 StoredProcedureQuery query = entityManager.createStoredProcedureQuery("clear_incident_orphan_records");
		 query.execute();
	}
		
}
