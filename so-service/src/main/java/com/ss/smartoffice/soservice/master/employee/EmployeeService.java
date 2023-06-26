package com.ss.smartoffice.soservice.master.employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;

import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.Config;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.AcademicAcheiv;
import com.ss.smartoffice.shared.model.employee.BankDetails;
import com.ss.smartoffice.shared.model.employee.EducationalInfo;
import com.ss.smartoffice.shared.model.employee.EmployeeCc;
import com.ss.smartoffice.shared.model.employee.EmployeeSkill;
import com.ss.smartoffice.shared.model.employee.FamilyInfo;
import com.ss.smartoffice.shared.model.employee.LanguagesKnown;
import com.ss.smartoffice.shared.model.employee.PreviousEmploymentDetails;
import com.ss.smartoffice.shared.model.employee.UpdateManagerList;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
//import com.ss.smartoffice.soservice.event.EventGenerator;
import com.ss.smartoffice.soservice.master.employeeskill.EmployeeSkillRepo;
import com.ss.smartoffice.soservice.seed.configs.ConfigService;
import com.ss.smartoffice.soservice.transaction.event.*;
import com.ss.smartoffice.soservice.transaction.event.Event.EventCategory;

@RestController
@RequestMapping(path = "master/employees")
@Scope("prototype")
public class EmployeeService {
//	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	LanguageRepository languageRepository;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@Autowired
	EmpCodeGeneration empCodeGeneration;
	
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	FamilyInfoRepository familyInfoRepository;
	

	

	
	@Autowired
	ConfigService configService;

	@Autowired
	PreviousEmpDetailsRepository previousEmpDetailsRepository;

	@Autowired
	EducationalInfoRepository educationalInfoRepository;

	@Autowired
	AcademicAcheivRepository academicAcheivRepository;

	@Autowired
	BankDetailsRepository bankDetailsRepository;
	
	@Autowired
	EmployeeSkillRepo employeeSkillRepo;

//	@Autowired
//	EventGenerator eventGenerator;
	
	@Autowired
	EventPublisherService eventPublisherService;
	
	@Autowired
	EmployeeCcRepository employeeccrepository;
	

	@Autowired
	AuthUserRepository authUserRepository;

	@Autowired
	CommonUtils commonUtils;

	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<memployee> getEmployees() throws SmartOfficeException {
		
		System.out.println("In getEmployee");
		return employeeRepository.findRegEmpSummaries();

	}
	
	@GetMapping("/FetchN1Employees")
	public Iterable<memployee> getN1Employees() throws SmartOfficeException {
		return employeeRepository.fetchN1Employees();
	}
	
	@GetMapping("/empCode/{empCode}")
	public List<memployee> getEmpByEmpCode(@PathVariable(value="empCode")String empCode)throws SmartOfficeException{
		return employeeRepository.findByEmpCode(empCode);
	}
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<memployee> getEmployeeById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		return employeeRepository.findById(id);

	}
	@GetMapping("/_internal")
	public Iterable<memployee> getEmployeesInternal() throws SmartOfficeException {
		return employeeRepository.findAll();

	}
	@GetMapping("/n1EmpId")
	public List<memployee> findEmployeeByN1EmpId(String n1EmpId) throws SmartOfficeException {
		return employeeRepository.findByN1EmpId(n1EmpId);
	}

	public List<memployee> findEmployeeByN2EmpId(String n2EmpId) throws SmartOfficeException {
		return employeeRepository.findByN2EmpId(n2EmpId);
	}
	
	@GetMapping("/middle-management")
	public List<memployee> getEmployeeByEmpCategory() throws SmartOfficeException {
		List<memployee>emps=(List<memployee>) employeeRepository.findAll();
		List<memployee> empCategorys = new ArrayList<memployee>();
		for(memployee emp:emps) {
			if(emp.getEmpCategory().equals("Middle Management")) {
				empCategorys.add(emp);
			}
		}
		return empCategorys;

	}
	
	@GetMapping("/senior-management")
	public List<memployee> getEmployeeByEmpCategoryHighManagement() throws SmartOfficeException {
		List<memployee>emps=(List<memployee>) employeeRepository.findAll();
		List<memployee> empCategorys = new ArrayList<memployee>();
		for(memployee emp:emps) {
			if(emp.getEmpCategory().equals("Senior Management")) {
				empCategorys.add(emp);
			}
		}
		return empCategorys;

	}
	
	@GetMapping("/_internal/{id}")
	public Optional<memployee> getEmployeeByIdInternal(@PathVariable(value = "id") int id) throws SmartOfficeException {
		return employeeRepository.findById(id);
	}
	
	@PostMapping("/{id}/app-pdf-emp-profile")
    public memployee updateempByPdfId(@RequestBody memployee employee) throws SmartOfficeException {
		memployee memployeeFromDb=getEmployeeById(employee.getId()).get();
		memployeeFromDb.setEmpProfilePdfId(employee.getEmpProfilePdfId());
		return employeeRepository.save(memployeeFromDb);
		
	}

	//@CrossOrigin(origins = "*")
	@PostMapping
	public memployee addEmployeeWithCode(@RequestBody memployee memployee) throws SmartOfficeException {
		memployee.setEmpTypeCode("REGULAR");
		List<Config> config = (List<Config>) configService.getConfig("OFFICE -ID", "OFFICE-ID");
		memployee.setOfficeId(Integer.parseInt(config.get(0).getConfigDtlName()));
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		System.out.println(year);

		memployee.setCreatedDt(now);

		if (memployee.getEmpCode() == null || (memployee.getEmpCode()).trim().isEmpty()) {
			memployee.setEmpCode(empCodeGeneration.empCodeGeneration(memployee));
		} else {
			if(!employeeRepository.findByEmpCode(memployee.getEmpCode()).isEmpty()) {
				throw new SmartOfficeException("EmpCode - "+memployee.getEmpCode()+" already exists in system");
			}
		}
		memployee.setInternalId(sequenceGenerationService.nextSequence("INTERNAL-ID").getOutput());
		if (memployee.getInternalId() != null && !memployee.getInternalId().isEmpty()) {
			memployee = employeeRepository.save(memployee);
		}

		return memployee;

	}
	@PostMapping("/email-event")
	public memployee addEmployee(@RequestBody memployee memployee) throws SmartOfficeException {
		memployee.setEmpTypeCode("REGULAR");
	
		
//		memployee.setCreatedDt(now);
	
	
		memployee = employeeRepository.save(memployee);
		

		String appToken =commonUtils.getLoggedinAppToken();
		System.out.println(appToken);
		Event event = new Event();
		event.setAppToken(appToken);
		event.setName(Event.EventTypes.NewUserEvent);
		event.setCategory(EventCategory.BuisnessEvent);
		List<BusinessKey>businessKeys= new ArrayList<BusinessKey>();
		BusinessKey businessKey= new BusinessKey();
		businessKey.setEmployeeId(Integer.toString(memployee.getId()));
//		businessKey.setMobileNumber(memployee.getContactMobileNo());
		event.setAppToken(commonUtils.getLoggedinAppToken());
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		eventPublisherService.pushEvent(event);
		return memployee;

	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public memployee updateEmployee(@RequestBody memployee memployee,@PathVariable(value = "id") int id) throws SmartOfficeException {
		memployee updatedEmployee = new memployee();
		memployee employeeById = getEmployeeById(id).get();
		String appToken =commonUtils.getLoggedinAppToken();
		if(memployee.getId()!=null) {
			memployee.setInternalId(employeeById.getInternalId());
			if(!memployee.getOfficeId().equals(employeeById.getOfficeId())) {
				memployee.setInternalId(employeeById.getInternalId());
				memployee.setEmpCode(empCodeGeneration.empCodeGeneration(memployee));
			}else {
				memployee.setEmpCode(employeeById.getEmpCode());
			}
		 updatedEmployee = employeeRepository.save(memployee);
		if (updatedEmployee.getLoginUserId() != null&&!updatedEmployee.getLoginUserId().isEmpty()) {
			System.out.println(updatedEmployee.getId());
		
			Event event = new Event();
			
			event.setName(Event.EventTypes.EmployeeUpdateEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			List<NotificationKey>notificationKeies = new ArrayList<NotificationKey>();
			NotificationKey notificationKey= new NotificationKey();
//			notificationKey.setEmpContactMobileNo(updatedEmployee.getContactMobileNo());
//			notificationKey.setEmployeeId(Integer.toString(updatedEmployee.getId()));
//			notificationKey.setEmpLoginId(updatedEmployee.getLoginUserId());
			event.setAppToken(appToken);
			notificationKeies.add(notificationKey);
			event.setNotificationKeys(notificationKeies);
			eventPublisherService.pushEvent(event);
		} else {
			Event event = new Event();
			event.setName(Event.EventTypes.NewUserEvent);
			event.setCategory(Event.EventCategory.BuisnessEvent);
			List<BusinessKey>businessKeys= new ArrayList<BusinessKey>();
			BusinessKey businessKey= new BusinessKey();
			businessKey.setEmployeeId(Integer.toString(memployee.getId()));
//			businessKey.setMobileNumber(memployee.getContactMobileNo());
			event.setAppToken(commonUtils.getLoggedinAppToken());
			businessKeys.add(businessKey);
			event.setBusinessKeys(businessKeys);
			eventPublisherService.pushEvent(event);

		}
		}else {
			throw new SmartOfficeException("Id value not Present");
		}
		return updatedEmployee;
		

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		
		try {
			for (AuthUser authUser : authUserRepository.findByEmployeeId(id+"")) {
				authUserRepository.delete(authUser);
			}
			employeeRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw  new SmartOfficeException("Error while deleting Employee -- "+e.getMessage());
		}
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}/lines")

	public memployee addOrUpdateEmployeeLines(@RequestBody memployee memployee) throws SmartOfficeException {

		if (memployee.getLanguages() != null && !memployee.getLanguages().isEmpty())

			for (LanguagesKnown langLoop : memployee.getLanguages()) {
               
                	  
				languageRepository.save(langLoop);
  			}

		if (memployee.getFamilyInfo() != null && !memployee.getFamilyInfo().isEmpty())
			for (FamilyInfo famLoop : memployee.getFamilyInfo()) {
				
				familyInfoRepository.save(famLoop);
			}
				

		if (memployee.getEducationalInfo() != null && !memployee.getEducationalInfo().isEmpty())

			for (EducationalInfo eduLoop : memployee.getEducationalInfo()) {
            
				educationalInfoRepository.save(eduLoop);
			
		}

		if (memployee.getAcademicAcheiv() != null&&!memployee.getAcademicAcheiv().isEmpty())
			for (AcademicAcheiv acaLoop : memployee.getAcademicAcheiv()) {
				
			
				academicAcheivRepository.save(acaLoop);
			
				}

		if (memployee.getPreviousEmployDetails() != null&&!memployee.getPreviousEmployDetails().isEmpty())

			for (PreviousEmploymentDetails prevLoop : memployee.getPreviousEmployDetails()) {
				
				previousEmpDetailsRepository.save(prevLoop);
			
				}

		if (memployee.getBankDetails() != null && !memployee.getBankDetails().isEmpty())
			for (BankDetails bankLoop : memployee.getBankDetails()) {
				bankLoop.setIsDefault("Y");
				bankDetailsRepository.save(bankLoop);
			
				}
		if(memployee.getEmployeeSkills()!=null &&!memployee.getEmployeeSkills().isEmpty()) {
			for(EmployeeSkill employeeSkill:memployee.getEmployeeSkills()) {
				employeeSkillRepo.save(employeeSkill);
			}
		}
		if(memployee.getEmployeecc()!=null &&!memployee.getEmployeecc().isEmpty()) {
			for(EmployeeCc employeecc:memployee.getEmployeecc()) {
				employeeccrepository.save(employeecc);
			}
		}

		return memployee;
	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/no-access")
	public List<memployee> getNoAccessEmployee() throws SmartOfficeException {
		memployee memployee = employeeRepository.findRegEmpSummaries().get(0);
		System.out.println(memployee.getLoginUserId());
		if (memployee.getLoginUserId().isEmpty() || memployee.getLoginUserId() == null) {
			return employeeRepository.findByLoginUserId(memployee.getLoginUserId());
		}
		return employeeRepository.findRegEmpSummaries();

	}
	
	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}/header")
	public memployee updateEmployeeHeaderOnly(@PathVariable(value = "id") int id, @RequestBody memployee memployee)
			throws SmartOfficeException {

		memployee employeeById = getEmployeeById(id).get();
	
		if(memployee.getId()!=null) {
		
		memployee.setLanguages(employeeById.getLanguages());
		memployee.setFamilyInfo(employeeById.getFamilyInfo());
		memployee.setEducationalInfo(employeeById.getEducationalInfo());
		memployee.setAcademicAcheiv(employeeById.getAcademicAcheiv());
		memployee.setPreviousEmployDetails(employeeById.getPreviousEmployDetails());
		memployee.setBankDetails(employeeById.getBankDetails());
		employeeRepository.save(memployee);
		}else {
			throw new SmartOfficeException("Id value not Present");
		}

		return memployee;

	}

	//@CrossOrigin(origins = "*")
	@Transactional
	@DeleteMapping("/{id}/delete/lines")
	public void deleteEmployeeLines(@PathVariable(value = "id") int id) throws SmartOfficeException {

		memployee memployee = getEmployeeById(id).get();
		if (memployee.getLanguages() != null && !memployee.getLanguages().isEmpty()) {

			for (LanguagesKnown languagesKnown : memployee.getLanguages()) {

				languageRepository.delete(languagesKnown.getId());
			}
		}

		else if (memployee.getFamilyInfo() != null && !memployee.getFamilyInfo().isEmpty()) {
			for (FamilyInfo familyInfo : memployee.getFamilyInfo()) {
				familyInfoRepository.delete(familyInfo.getId());
			}
		} else if (memployee.getPreviousEmployDetails() != null && !memployee.getPreviousEmployDetails().isEmpty()) {
			for (PreviousEmploymentDetails prevEmpDetail : memployee.getPreviousEmployDetails()) {
				previousEmpDetailsRepository.delete(prevEmpDetail.getId());
			}

		} else if (memployee.getEducationalInfo() != null && !memployee.getEducationalInfo().isEmpty()) {
			for (EducationalInfo educationalInfo : memployee.getEducationalInfo()) {
				educationalInfoRepository.delete(educationalInfo.getId());
			}

		} else if (memployee.getAcademicAcheiv() != null && !memployee.getAcademicAcheiv().isEmpty()) {
			for (AcademicAcheiv academicAcheiv : memployee.getAcademicAcheiv()) {
				academicAcheivRepository.delete(academicAcheiv.getId());
			}

		} else if (memployee.getBankDetails() != null && !memployee.getBankDetails().isEmpty()) {
			for (BankDetails bankDetails : memployee.getBankDetails()) {
				bankDetailsRepository.delete(bankDetails.getId());
			}

		}else if (memployee.getEmployeeSkills()!=null && !memployee.getEmployeeSkills().isEmpty()) {
			for(EmployeeSkill employeeSkill:memployee.getEmployeeSkills()) {
				employeeSkillRepo.delete(employeeSkill.getId());
			}
		}
		else if (memployee.getEmployeecc()!=null && !memployee.getEmployeecc().isEmpty()) {
			for(EmployeeCc employeecc:memployee.getEmployeecc()) {
				employeeccrepository.deleteById(employeecc.getId());
			}
		}
		else {
			throw new SmartOfficeException("No memployee Specific Details Present");
		}
	}
	//@CrossOrigin(origins = "*")
	
	public List<memployee> findByEmployeeCode(String employeeCode){
		return employeeRepository.findByEmpCode(employeeCode);
	}
	
//	@GetMapping("/manager-swap/fetch-team")
//	public List<memployee> getManagerReporteeList()throws SmartOfficeException{
//		List<memployee> managerReporteeList= new ArrayList<>();
//		memployee memployee = new memployee();
//		memployee.setManagerId("1");
//		memployee.setReviewManagerId("2");
//		memployee.setId(100);
//		memployee.setEmpName("Priya");
//		memployee.setEmpCode("1004");
//		managerReporteeList.add(memployee);
//		memployee emp1 = new memployee();
//		emp1.setManagerId("1");
//		emp1.setReviewManagerId("2");
//		emp1.setId(101);
//		emp1.setEmpName("Sundar");
//		emp1.setEmpCode("1005");
//		managerReporteeList.add(emp1);
//		memployee emp2 = new memployee();
//		emp2.setManagerId("2");
//		emp2.setReviewManagerId("1");
//		emp2.setId(101);
//		emp2.setEmpName("Arun");
//		emp2.setEmpCode("1005");
//		managerReporteeList.add(emp2);
//		memployee emp3 = new memployee();
//		emp3.setManagerId("2");
//		emp3.setReviewManagerId("1");
//		emp3.setId(101);
//		emp3.setEmpName("Gautham");
//		emp3.setEmpCode("1005");
//		managerReporteeList.add(emp3);
//		return managerReporteeList;
//		
//	}
	
	@GetMapping("/manager-swap/fetch-team")
	public List<memployee> getManagerTeam(@RequestParam (value="n1EmpId",required=false)String n1EmpId){
		if(n1EmpId.isEmpty()) {			
			return employeeRepository.fetchManager();
		}else {
			return employeeRepository.fetchManagerReportees(n1EmpId,n1EmpId);
		}
}

	
	
	@PatchMapping("/batch-update")
	@Transactional
//	@Test
	public void whenUpdatingEntities_thenCreatesBatch(@RequestBody UpdateManagerList updatedEmployee) {
		String appToken =commonUtils.getLoggedinAppToken();
		
		memployee savedEmployee= new memployee();
		// manager-1 swap
		if(updatedEmployee.getM1List()!=null&&!updatedEmployee.getM1List().isEmpty()) {
			for(String empID:updatedEmployee.getM1List()) {
	    	
    	TypedQuery<memployee> empQuery = 
	    		      entityManager.createQuery("SELECT s from memployee s where id="+empID, memployee.class);
    	 List<memployee> updatedEmp = empQuery.getResultList();
 	    for (memployee emp : updatedEmp) {
 	     emp.setN1EmpId(updatedEmployee.getM1Id());
 	   
 	    savedEmployee=  employeeRepository.save(emp);
 	    }
 	   Event event = new Event();
		
		event.setName(Event.EventTypes.Manager1UpdateEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		List<NotificationKey>notificationKeies= new ArrayList<NotificationKey>();
		NotificationKey notificationKey= new NotificationKey();
		notificationKey.setSendEmail(savedEmployee.getContactEmailId());
//		notificationKey.setEmployeeId(String.valueOf(savedEmployee.getId()));
		notificationKeies.add(notificationKey);
		event.setNotificationKeys(notificationKeies);
		eventPublisherService.pushEvent(event);

			}
			
		}
		// manager-2 swap
		if(updatedEmployee.getM2List()!=null&&!updatedEmployee.getM2List().isEmpty()) {
			for(String m2EmpID:updatedEmployee.getM2List()) {
	    	
    	TypedQuery<memployee> empQuery = 
	    		      entityManager.createQuery("SELECT s from memployee s where id="+m2EmpID, memployee.class);
    	 List<memployee> updatedEmp = empQuery.getResultList();
 	    for (memployee emp : updatedEmp) {
 	   
 	    emp.setN2EmpId(updatedEmployee.getM2Id());
 	    savedEmployee=  employeeRepository.save(emp);
 	    }
		
			Event event = new Event();
		
			event.setName(Event.EventTypes.Manager2UpdateEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			List<NotificationKey>notificationKeies= new ArrayList<NotificationKey>();
			NotificationKey notificationKey= new NotificationKey();
			notificationKey.setSendEmail(savedEmployee.getContactEmailId());
//			notificationKey.setEmployeeId(String.valueOf(savedEmployee.getId()));
			notificationKeies.add(notificationKey);
			event.setNotificationKeys(notificationKeies);
			eventPublisherService.pushEvent(event);

			
//			event.setKeyValues(keyValues);
//			eventGenerator.generateNofiticationEvent(event);
		}
		}
//		// hr-1 manager swap
//		if(updatedEmployee.getHr1List()!=null&&!updatedEmployee.getHr1List().isEmpty()) {
//			for(String empId:updatedEmployee.getHr1List()) {
//				TypedQuery<memployee> empQuery = 
//		    		      entityManager.createQuery("SELECT s from memployee s where id="+empId, memployee.class);
//				List<memployee> updatedEmp = empQuery.getResultList();
//				 for(memployee emp : updatedEmp) {
//					 emp.setHr1Id(updatedEmployee.getHr1Id());
//					 savedEmployee=  employeeRepository.save(emp);
//				 }
//			}
//			Event eventStructure = new Event();
//			eventStructure.setId((int)commonUtils.getNextNumber());
//			eventStructure.setName(Event.EventTypes.Hr1UpdateEvent);
//			Map<String, String> keyValues = new HashMap<String, String>();
//			keyValues.put("email-id", savedEmployee.getContactEmailId());
//			keyValues.put("employee-id", String.valueOf(savedEmployee.getId()));
//			
////			event.setKeyValues(keyValues);
////			eventGenerator.generateNofiticationEvent(event);
//		}
//		// hr-2 manager swap
//		if(updatedEmployee.getHr2List()!=null&&!updatedEmployee.getHr2List().isEmpty()) {
//			for(String empId:updatedEmployee.getHr2List()) {
//				TypedQuery<memployee> empQuery = 
//		    		      entityManager.createQuery("SELECT s from memployee s where id="+empId, memployee.class);
//				List<memployee> updatedEmp = empQuery.getResultList();
//				 for(memployee emp : updatedEmp) {
//					 emp.setHr2Id(updatedEmployee.getHr2Id());
//					 savedEmployee=  employeeRepository.save(emp);
//				 }
//			}
//			Event eventStructure = new Event();
//			eventStructure.setId((int)commonUtils.getNextNumber());
//			eventStructure.setName(Event.EventTypes.Hr2UpdateEvent);
//			Map<String, String> keyValues = new HashMap<String, String>();
//			keyValues.put("email-id", savedEmployee.getContactEmailId());
//			keyValues.put("employee-id", String.valueOf(savedEmployee.getId()));
//			
////			event.setKeyValues(keyValues);
////			eventGenerator.generateNofiticationEvent(event);
//		}
//		//acc-1 manager swap
//		if(updatedEmployee.getAcc1List()!=null&&!updatedEmployee.getAcc1List().isEmpty()) {
//			for(String empId:updatedEmployee.getAcc1List()) {
//				TypedQuery<memployee> empQuery = 
//		    		      entityManager.createQuery("SELECT s from memployee s where id="+empId, memployee.class);
//				List<memployee> updatedEmp = empQuery.getResultList();
//				 for(memployee emp : updatedEmp) {
//					 emp.setIsAcc1Id(updatedEmployee.getIsAcc1Id());
//					 savedEmployee=  employeeRepository.save(emp);
//				 }
//			}
//			Event eventStructure = new Event();
//			eventStructure.setId((int)commonUtils.getNextNumber());
//			eventStructure.setName(Event.EventTypes.Acc1UpdateEvent);
//			Map<String, String> keyValues = new HashMap<String, String>();
//			keyValues.put("email-id", savedEmployee.getContactEmailId());
//			keyValues.put("employee-id", String.valueOf(savedEmployee.getId()));
//			
////			event.setKeyValues(keyValues);
////			eventGenerator.generateNofiticationEvent(event);
//		}
//		//acc-2 manager swap
//		if(updatedEmployee.getAcc2List()!=null&&!updatedEmployee.getAcc2List().isEmpty()) {
//			for(String empId:updatedEmployee.getAcc2List()) {
//				TypedQuery<memployee> empQuery = 
//		    		      entityManager.createQuery("SELECT s from memployee s where id="+empId, memployee.class);
//				List<memployee> updatedEmp = empQuery.getResultList();
//				 for(memployee emp : updatedEmp) {
//					 emp.setIsAcc2Id(updatedEmployee.getIsAcc2Id());
//					 savedEmployee=  employeeRepository.save(emp);
//				 }
//			}
//			Event eventStructure = new Event();
//			eventStructure.setId((int)commonUtils.getNextNumber());
//			eventStructure.setName(Event.EventTypes.Acc2UpdateEvent);
//			Map<String, String> keyValues = new HashMap<String, String>();
//			keyValues.put("email-id", savedEmployee.getContactEmailId());
//			keyValues.put("employee-id", String.valueOf(savedEmployee.getId()));
//			
////			event.setKeyValues(keyValues);
////			eventGenerator.generateNofiticationEvent(event);
//		}
	    	   
		
	}
	@GetMapping("/_internal/{docId}/docId")
	public Optional<memployee> getEmpByDocId(@PathVariable(value="docId") String docId) 
			throws SmartOfficeException{
		return employeeRepository.findByDocId(docId);	
	}
	
	@PostMapping("/_internal/{id}/header")
	public memployee updateDocByEmpId(@PathVariable(value="id") int id,
			@RequestBody memployee employee) throws SmartOfficeException{

		memployee employeeById = getEmployeeById(id).get();
		if(employee.getId()!=null) {
			employeeById.setDocId(employee.getDocId());
			 employeeRepository.save(employeeById);
		}else {
			throw new SmartOfficeException("Id value not Present");
		}
		return employeeById;
	}
}
