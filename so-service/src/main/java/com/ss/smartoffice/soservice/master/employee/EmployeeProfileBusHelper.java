package com.ss.smartoffice.soservice.master.employee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.Config;
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.applicant.Applicant;
import com.ss.smartoffice.shared.model.employee.Employee;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.seed.configs.ConfigService;
import com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim;
import com.ss.smartoffice.soservice.transaction.incident.Incident;

import com.ss.smartoffice.soservice.transaction.incident.IncidentApplicantRepo;
import com.ss.smartoffice.soservice.transaction.incident.IncidentRepo;
import com.ss.smartoffice.soservice.transaction.recruitment.ApplicantRepository;
import com.ss.smartoffice.soservice.transaction.recruitment.ApplicantService;

@Service
public class EmployeeProfileBusHelper {
	@Autowired
	CommonUtils commonutils;

	@Autowired
	ConfigService configService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ApplicantService applicantService;
	
	@Autowired
	EntityManager entityManager;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@Autowired
	EmpCodeGeneration empCodeGeneration;
	@Autowired
	EmployeeProfileEventGenerator employeeProfileEventGenerator;
	@Autowired
	ApplicantRepository applicantRepo;
	@Autowired
	IncidentRepo incidentRepo;
	@Autowired
	IncidentApplicantRepo incidentApplicantRepo;
	@Autowired
	AuthUserRepository authUserRepository;
	@Value("${image.url}")
	private String imageUrl;

	public memployee createEmpProfile(memployee memployee) throws SmartOfficeException {
		memployee.setEmpTypeCode("REGULAR");
		memployee.setIsSystemUser("N");
//		memployee.setDesigName(memployee.getDesigName());
		
		HashMap<String, String> buisnessKeys = new HashMap<>();
		memployee.setInternalId(sequenceGenerationService.nextSeq("INTERNAL-ID", buisnessKeys));
		if (memployee.getInternalId() != null && !memployee.getInternalId().isEmpty()) {
			if (memployee.getEmpCode() == null || (memployee.getEmpCode()).trim().isEmpty()) {
//				memployee.setEmpCode(empCodeGeneration.empCodeGeneration(memployee));
				memployee.setEmpCode("TEMP-" + memployee.getInternalId());
			} else {
				System.out.println("employeeRepository.findByEmpCode(memployee.getEmpCode()).isEmpty()");
				System.out.println(employeeRepository.findByEmpCode(memployee.getEmpCode()).isEmpty());
				System.out.println(employeeRepository.findByEmpCode(memployee.getEmpCode()));
				if (!employeeRepository.findByEmpCode(memployee.getEmpCode()).isEmpty()) {
					throw new SmartOfficeException("EmpCode - " + memployee.getEmpCode() + " already exists in system");
				}else {
					memployee.setEmpStatus("PROFILE-CONFIRMATION-PENDING");
				}
			}
//			memployee = employeeRepository.save(memployee);
		} 
//		else {
			// TBD - need to check if internal-id is duplicate in system and throw error.
//		}
		memployee.setDesigName(memployee.getDesigName());
		if (memployee.getDesigName() != null && memployee.getDesigName().equals("Director")) {

			memployee.setEmpStatus("ACTIVE");

		}
		
		if (memployee.getOfficeId() == null || memployee.getOfficeId()<=0) {
			List<Config> config = (List<Config>) configService.getConfig("OFFICE -ID", "OFFICE-ID");
			memployee.setOfficeId(Integer.parseInt(config.get(0).getConfigDtlName()));	
		}

		memployee.setIsEnabled("Y");
		memployee.setCreatedBy(commonutils.getLoggedinEmployeeId());

		employeeRepository.save(memployee);
		return memployee;
	}

	public memployee updateEmprofile(@RequestBody memployee memployee) throws SmartOfficeException {
		updateAuthUser(memployee);
		memployee.setIsEnabled("Y");
		memployee.setModifiedBy(commonutils.getLoggedinEmployeeId());
		memployee.setModifiedDt(LocalDateTime.now());
		return employeeRepository.save(memployee);
	}

	// -----------------auth User
	// Update------------------------------------------------

	public void updateAuthUser(memployee employee) throws SmartOfficeException {

		List<AuthUser> authUsersFromDb = authUserRepository.findByEmployeeId(employee.getId().toString());
		System.out.println("authUserFromDb" + authUsersFromDb);
		for (AuthUser authUserFromDb : authUsersFromDb) {
			authUserFromDb.setFirstName(employee.getFirstName());
			authUserFromDb.setLastName(employee.getLastName());
			authUserFromDb.setEmailId(employee.getEmailId());
			authUserFromDb.setMobileNumber(employee.getContactMobileNo());
			authUserFromDb.setEmpDesignation(employee.getDesigName());
			authUserFromDb.setModifiedBy(commonutils.getLoggedinEmployeeId());
			authUserFromDb.setModifiedDt(LocalDateTime.now());
			authUserRepository.save(authUserFromDb);
		}
	}

	// processEmp-Profile
	public memployee processEmpProfile(Integer id, String action, memployee memployee) {

		String imgProcessUrl = imageUrl + "/" + memployee.getDocId() + "/image";
		memployee empProfileFromDb = new memployee();
		if (!(action.equals("create"))) {
			empProfileFromDb = employeeRepository.findById(id).get();
			System.out.println(empProfileFromDb);
		}
		AuthUser loggedInUser = (AuthUser) commonutils.getAuthenticatedUser().getDetails();
		System.out.println("sundar checking create New User"+loggedInUser);

		switch (action) {
		case ("create"):
			Boolean existingEmp = ((memployee.getEmpCode() != null) && !(memployee.getEmpCode().isEmpty()));
			memployee.setCreateEmpId(commonutils.getLoggedinAppToken());
			memployee.setProfileCreatedDt(LocalDateTime.now());

			if (memployee.getProfileCreateRemarks() == null || memployee.getProfileCreateRemarks().isEmpty())
				memployee.setProfileCreateRemarks("PROFILE-CREATED");
			// memployee.setEmpStatus("CREATED");
			memployee = createEmpProfile(memployee);
				if (existingEmp) {
					memployee.setEmpStatus("PROFILE-CONFIRMATION-PENDING");
					updateEmprofile(memployee);
					employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, "onboard", loggedInUser);
				} else {

					memployee.setEmpStatus("ONBOARD-APPROVAL-PENDING");
					updateEmprofile(memployee);
					employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, action, loggedInUser);
				}			

			break;
		case ("official-info"):
			if (empProfileFromDb.getEmpStatus().equals("official-information-pending")) {
				memployee.setCreateEmpId(commonutils.getLoggedinAppToken());
				memployee.setProfileCreatedDt(LocalDateTime.now());
				if (memployee.getProfileCreateRemarks() == null || memployee.getProfileCreateRemarks().isEmpty())
					memployee.setEmpStatus("ONBOARD-APPROVAL-PENDING");
				updateEmprofile(memployee);
				employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, "create", loggedInUser);
			}

			break;
		case ("onboard"):
			if (empProfileFromDb.getEmpStatus().equals("ONBOARD-APPROVAL-PENDING")) {

				List<String> userGroupIds = commonutils.findLoggedInUserGroups();
				if (!userGroupIds.contains(empProfileFromDb.getDirUsrGrpId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");
				}
				memployee.setEmpCode(empCodeGeneration.empCodeGeneration(memployee));
				memployee.setEmpStatus("ONBOARDED");
				memployee.setOnboardedDt(LocalDateTime.now());
				memployee.setOnboardEmpId(empProfileFromDb.getId().toString());
				if (memployee.getOnboardRemarks() == null || memployee.getOnboardRemarks().isEmpty())
					memployee.setOnboardRemarks("ONBOARDED");
				updateEmprofile(memployee);
				memployee.setEmpStatus("PROFILE-CONFIRMATION-PENDING");
				updateEmprofile(memployee);
				employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, action, loggedInUser);

			} else {
				throw new SmartOfficeException("Profile not in onboard approval pending status");
			}
			break;
		case ("complete-profile"):
			if (empProfileFromDb.getEmpStatus().equals("PROFILE-CONFIRMATION-PENDING")) {

				if (!empProfileFromDb.getId().toString().equals(commonutils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");
				} else {
					memployee.setEmpStatus("PROFILE-COMPLETED");
					updateEmprofile(memployee);
					memployee.setEmpStatus("ACTIVE");
					updateEmprofile(memployee);
					employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, action, loggedInUser);
				}
			} else {
				throw new SmartOfficeException("Profile not in complete-profile status");
			}
			break;
		case ("profile-update"):
			if (empProfileFromDb.getEmpStatus().equals("ACTIVE")) {
				memployee imgEmp = new memployee();
				if (!empProfileFromDb.getId().toString().equals(commonutils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");
				} else {

					memployee.setProfUpdationDt(LocalDateTime.now());
					memployee.setEmpStatus("PROFILE-VALIDATION-PENDING");
					System.out.println("profile-update");
					updateEmprofile(memployee);
					employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, action, loggedInUser);
					System.out.println("image-process-starts");
					imgEmp = fetchByImgUrl(imgProcessUrl);
				}

			} else {
				throw new SmartOfficeException("Profile not yet completed first after completion updation can be done");
			}

			break;
		case ("profile-validate"):
			if (empProfileFromDb.getEmpStatus().equals("PROFILE-VALIDATION-PENDING")) {
				List<String> userGroupIds = commonutils.findLoggedInUserGroups();
				if (userGroupIds.contains(empProfileFromDb.getHr1UsrGrpId())
						|| userGroupIds.contains(empProfileFromDb.getHr2UsrGrpId())) {
					memployee.setEmpStatus("PROFILE-VALIDATED");
					memployee.setProfValidateEmpId(commonutils.getLoggedinEmployeeId());
					if (memployee.getProfValidateRemarks() == null || memployee.getProfValidateRemarks().isEmpty())
						memployee.setProfValidateRemarks("PROFILE-VALIDATED");
					memployee.setProfValidationDt(LocalDateTime.now());
					updateEmprofile(memployee);
					memployee.setEmpStatus("ACTIVE");
					updateEmprofile(memployee);
					employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, action, loggedInUser);
					fetchByImgUrl(imgProcessUrl);
				} else {
					throw new SmartOfficeException("Not a valid user to perform this action");
				}
			} else {
				throw new SmartOfficeException("Profile not in profile validation pending");
			}

			break;
		case ("skill-update"):
			if (empProfileFromDb.getEmpStatus().equals("ACTIVE")) {

				if (!empProfileFromDb.getId().toString().equals(commonutils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");
				} else {
					memployee.setEmpStatus("SKILL-VALIDATION-PENDING");
					memployee.setSkillUpdationDt(LocalDateTime.now());
					updateEmprofile(memployee);
					employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, action, loggedInUser);
				}
			} else {
				throw new SmartOfficeException("Profile  not yet  validated to update skill");
			}
			break;

		case ("skill-validate"):
			if (empProfileFromDb.getEmpStatus().equals("SKILL-VALIDATION-PENDING")) {
				if (!empProfileFromDb.getN1EmpId().equals(commonutils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");
				} else {
					memployee.setEmpStatus("SKILL-VALIDATED");
					memployee.setSkillValidateEmpId(commonutils.getLoggedinEmployeeId());
					if (memployee.getSkillValidateRemarks() == null || memployee.getSkillValidateRemarks().isEmpty())
						memployee.setSkillValidateRemarks("SKILL-VALIDATED");
					memployee.setSkillValidationDt(LocalDateTime.now());
					updateEmprofile(memployee);
					memployee.setEmpStatus("ACTIVE");
					updateEmprofile(memployee);
					employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, action, loggedInUser);
				}
			} else {
				throw new SmartOfficeException("Profile  not in skill validation pending");
			}
			break;
		case ("profile-update-by-hr"):
			if (empProfileFromDb.getEmpStatus().equals("ACTIVE")) {
				List<String> userGroupIds = commonutils.findLoggedInUserGroups();
				if (userGroupIds.contains(empProfileFromDb.getHr1UsrGrpId())
						|| userGroupIds.contains(empProfileFromDb.getHr2UsrGrpId())) {
					if (!empProfileFromDb.getOfficeId().equals(memployee.getOfficeId())) {

						memployee.setOfficeId(memployee.getOfficeId());
						// transient
						memployee.setOfficeChange("Y");

					}
					memployee.setProfUpdationDt(LocalDateTime.now());
//					memployee.setEmpStatus("PROFILE-VALIDATION-PENDING");

					System.out.println("profile-update-by-hr");
					updateEmprofile(memployee);
					employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, action, loggedInUser);
					fetchByImgUrl(imgProcessUrl);
				} else {
					throw new SmartOfficeException("Not a valid user to perform this action");
				}
			} else {
				throw new SmartOfficeException("Profile not yet completed first after completion updation can be done");
			}

			break;
		default:
			break;
		}
		return memployee;
	}

	public memployee fetchByImgUrl(String imgProcessUrl) {
		memployee imgEmp = new memployee();
		try {
			imgEmp = commonutils.getRestTemplate().getForObject(imgProcessUrl, memployee.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return imgEmp;

	}

//	-------Convert applicant from employee---------------------------------------------

	public void convertApplicant(List<com.ss.smartoffice.shared.model.IncidentApplicant> iaList, Incident i) {
		AuthUser loggedInUser = (AuthUser) commonutils.getAuthenticatedUser().getDetails();
		for (com.ss.smartoffice.shared.model.IncidentApplicant incidentApplicant : iaList) {
			try {
				if (incidentApplicant.getApplicantId() != null && incidentApplicant.getEmployeeCode() == null) {
					Applicant applicant = applicantService.updateApplicantFromIA(incidentApplicant); 
					try {
						memployee emp = employeeRepository.save(formEmployeeObjFromApplicant(applicant, i));
						incidentApplicant.setEmpConversionStatus("Created");
						incidentApplicant.setEmpConversionMessage("Success");
						incidentApplicant.setEmployeeCode(emp.getEmpCode());
						employeeProfileEventGenerator.triggerEmpCoversionEvent(emp, loggedInUser, applicant);
					} catch (Exception e) {
						incidentApplicant.setEmpConversionStatus("Error");
						incidentApplicant.setEmpConversionMessage(e.getMessage());
						System.out.println("5 - " + e.getMessage());
						e.printStackTrace();
					} finally {
						incidentApplicantRepo.save(incidentApplicant);
					} 
				} else {
					incidentApplicant.setEmpConversionStatus("ERROR");
					if (incidentApplicant.getEmployeeCode() != null) {
						incidentApplicant.setEmpConversionMessage("Employee Already Exists");
					} else {
						incidentApplicant.setEmpConversionMessage("No Applicant in master");
					}
					incidentApplicantRepo.save(incidentApplicant);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				incidentApplicant.setEmpConversionStatus("Error");
				incidentApplicant.setEmpConversionMessage(e.getMessage());
			} finally {
				incidentApplicantRepo.save(incidentApplicant);
			}

		}
	}

 
	
	public memployee formEmployeeObjFromApplicant(Applicant applicant, Incident i) {
		return memployee.builder().firstName(applicant.getFirstName()).lastName(applicant.getLastName())
				.empName(applicant.getFirstName() + " " + applicant.getLastName()).aadharNo(applicant.getAadharNo())
				.bloodGroup(applicant.getBloodGroup()).emailId(applicant.getEmailId())
				.contactMobileNo(applicant.getContactMobileNo()).contactEmailId(applicant.getContactEmailId())
				.contactNo(applicant.getContactNo()).curAddress(applicant.getCurAddress()).dob(applicant.getDob())
				.drivingLicense(applicant.getDrivingLicense()).epfNo(applicant.getEpfNo()).esiNo(applicant.getEsiNo())
				.empCode(sequenceGenerationService.generateEmployeeCode()).eyeLeftPower(applicant.getEyeLeftPower())
				.eyePower(applicant.getEyePower()).eyeRightPower(applicant.getEyeRightPower())
				.gender(applicant.getGender()).grossOther(applicant.getGrossOtherRemun())
				.gross(applicant.getGrossRemun()).height(applicant.getHeight()).hobbies(applicant.getHobbies())
				.identifnMrk1(applicant.getIdentifnMrk1()).identifnMrk2(applicant.getIdentifnMrk2())
				.jobOpening(applicant.getJobOpening()).loginUserId(null)
				.maritalStatus(applicant.getMaritalStatus()).monthly(applicant.getMonthlyRemun())
				.name(applicant.getName()).nextIncr(applicant.getNextIncrRemun())
				.noOfChildren(applicant.getNoOfChildren()).noOfDependant(applicant.getNoOfDependant())
				.offences(applicant.getOffences()).panNo(applicant.getPanNo()).passport(applicant.getPassport())
				.permAddress(applicant.getPermAddress()).physicalChalng(applicant.getPhysicalChalng())
				.reference2(applicant.getReference2()).relInPodhigai(applicant.getRelInPodhigai())
				.relName(applicant.getRelName()).relation(applicant.getRelation()).relations(applicant.getRelations())
				.remarks("Employee-conversion").isSystemUser("N").empTypeCode("REGULAR")
				.spouseName(applicant.getSpouseName()).spouseOccup(applicant.getSpouseOccup())
				.startDt(applicant.getStartDt()).takeHome(applicant.getTakeHomeRemun()).weight(applicant.getWeight())
				.docId(applicant.getDocId()).empStatus("official-information-pending").emailId(applicant.getEmailId())
				.hr1UsrGrpId(isNullOrEmpty(i.getHandlingGroupId()))
				.createdBy(isNullOrEmpty(commonutils.getLoggedinEmployeeId())).build();
	}

	public String isNullOrEmpty(String str) {
		System.out.println("isNullEmpty=" + str);
		if (str != null && !str.isEmpty())
			return str;
		return "";
	}
	
	
	public String createLoginAccessForEmp(memployee employee)throws SmartOfficeException{
		AuthUser loggedInUser = (AuthUser) commonutils.getAuthenticatedUser().getDetails();		
		employeeProfileEventGenerator.triggerEmpProfileEvents(employee, "onboard", loggedInUser);
		return "";
	}

}
