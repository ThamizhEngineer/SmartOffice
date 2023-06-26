package com.ss.smartoffice.soservice.transaction.employeeImport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.EmployeeForUpload;
import com.ss.smartoffice.shared.model.UserGroup;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.department.DepartmentRepository;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.model.Department;
import com.ss.smartoffice.soservice.master.offices.Office;
import com.ss.smartoffice.soservice.master.offices.OfficeRepository;
import com.ss.smartoffice.soservice.master.shift.AttendanceShift;
import com.ss.smartoffice.soservice.master.shift.AttendanceShiftRepo;
import com.ss.smartoffice.soservice.seed.designation.Designation;
import com.ss.smartoffice.soservice.seed.designation.DesignationRepository;
import com.ss.smartoffice.soservice.seed.userGroups.UserGroupRepo;


@RestController
@Service
@RequestMapping("/int/employee")
public class IntEmployeeService {

	@Autowired 
	CommonUtils commonUtils;
	
	@Autowired
	IntEmployeeHdrRepo intEmployeeHdrRepo;
	
	@Autowired
	IntEmployeeLineRepo intEmployeeLineRepo;
	
	@Autowired
	AttendanceShiftRepo attendanceShiftRepo;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DesignationRepository designationRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	OfficeRepository officeRepository;
	
	@Autowired
	UserGroupRepo userGroupRepo;
	
	@Autowired
	EmployeeGenerator employeeGenerator;
	
	@PostMapping("/start")	
	public List<EmployeeForUpload> start(@RequestBody List<EmployeeForUpload> extarctedData) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		List<IntEmployeeLine> savedRecords = (List<IntEmployeeLine>) savingRecords(extarctedData);
		validate(savedRecords,extarctedData);
		String iEmployeeHdrId = savedRecords.iterator().next().getEmployeeHdrId();
		
		employeeGenerator.start(iEmployeeHdrId, loggedInUser);	
		int i=0;
		for(EmployeeForUpload data:extarctedData) {				
			data.setEmployeeHdrId(iEmployeeHdrId);
			data.setIsValid(savedRecords.get(i).getIsValid());
			data.setErrorStatus(savedRecords.get(i).getErrorStatus());
			i++;
		}
		System.out.println("OUTPUT....!!!!");
		System.out.println(extarctedData);
		return extarctedData;
	}
	
	@GetMapping("/{empHdrId}")
	public List<IntEmployeeLine> getEMpLineBYValid(@PathVariable(value = "empHdrId") String hdrId,@RequestParam(value = "isValid")String isValid){
		return intEmployeeLineRepo.findByEmployeeHdrIdAndIsValid(hdrId, isValid);
	}
	
	@PostMapping("/start/{hdrId}")
	public List<IntEmployeeLine> cleanUpdata(@RequestBody List<IntEmployeeLine> intEmployeeLine){
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		List<EmployeeForUpload> extarctedData = new ArrayList<EmployeeForUpload>();
		validate(intEmployeeLine,extarctedData);
		String empHdrId = intEmployeeLine.iterator().next().getEmployeeHdrId();
		employeeGenerator.start(empHdrId, loggedInUser);		
		return intEmployeeLineRepo.findByEmployeeHdrId(empHdrId);
	}
	
	private Iterable<IntEmployeeLine> savingRecords(List<EmployeeForUpload> extarctedData){
		try {
			String docId = extarctedData.get(0).getDocId();			
			IntEmployeeHdr intEmployeeHdr = formIntEmployeeHdr(docId);
			List<IntEmployeeLine> intEmployeeLines = new ArrayList<IntEmployeeLine>();
			for(EmployeeForUpload empForUpload:extarctedData) {
				IntEmployeeLine intEmployeeLine = formIntEmployeeLine(intEmployeeHdr.getId().toString(),empForUpload);
				intEmployeeLines.add(intEmployeeLine);
			}
			return intEmployeeLineRepo.saveAll(intEmployeeLines);
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Error while saving records");
		}
	}
	public	List<String> empCode;
	public	List<String> empEmail;
	public	List<String> empMobileNo;
	
	private Iterable<IntEmployeeLine> validate(Iterable<IntEmployeeLine> savedRecords,List<EmployeeForUpload> extrackedData){
		List<memployee> empployees = (List<memployee>) employeeRepository.findAll();
		empCode = extrackedData.stream().map(EmployeeForUpload :: getEmpCode).collect(Collectors.toList());
	 	empEmail = extrackedData.stream().map(EmployeeForUpload :: getEmail).collect(Collectors.toList());
	 	empMobileNo = extrackedData.stream().map(EmployeeForUpload :: getMobileNo).collect(Collectors.toList());
		empCode.addAll(empployees.stream().map(memployee :: getEmpCode).collect(Collectors.toList()));
		empEmail.addAll(empployees.stream().map(memployee :: getEmailId).collect(Collectors.toList()));
		empMobileNo.addAll(empployees.stream().map(memployee :: getContactMobileNo).collect(Collectors.toList()));
		System.out.println(empCode);
		System.out.println(empEmail);
		System.out.println(empMobileNo);
		for(IntEmployeeLine empLine:savedRecords) {
			try {							
				AttendanceShift attendanceShift = attendanceShiftRepo.findDuplicate(empLine.getShiftCode());
				UserGroup userGroupHr1 = userGroupRepo.findByUserGroupCodeAndIsHrL1(empLine.getHr1GrpCode(), "Y");
				UserGroup userGroupHr2 = userGroupRepo.findByUserGroupCodeAndIsHrL2(empLine.getHr2GrpCode(), "Y");
				UserGroup userGroupAcc1 = userGroupRepo.findByUserGroupCodeAndIsAcctL1(empLine.getAcc1GrpCode(), "Y");
				UserGroup userGroupAcc2 = userGroupRepo.findByUserGroupCodeAndIsAcctL2(empLine.getAcc2GrpCode(), "Y");
				UserGroup userGroupDir = userGroupRepo.findByUserGroupCodeAndIsDir(empLine.getDirGrpCode(), "Y");
				Office office = officeRepository.findByOfficeCode(empLine.getOfficeCode());
				Designation designation = designationRepository.findByDesigName(empLine.getDesignation());
				Department department = departmentRepository.findByDeptCode(empLine.getDepartmentCode());
				
				boolean isAttendanceShift=attendanceShift!=null;
				boolean isHr1=userGroupHr1!=null;
				boolean isHr2=userGroupHr2!=null;
				boolean isAcc1=userGroupAcc1!=null;
				boolean isAcc2=userGroupAcc2!=null;
				boolean isDir=userGroupDir!=null;
				boolean isOffice=office!=null;
				boolean isDesig=designation!=null;
				boolean isDept=department!=null;
				
				if(isAttendanceShift&&isHr1&&isHr2&&isAcc1&&isAcc2&&isDir&&isOffice&&isDesig&&isDept) {
					empLine.setShiftId(attendanceShift.getId().toString());
					empLine.setHr1GrpId(String.valueOf(userGroupHr1.getId()));
					empLine.setHr2GrpId(String.valueOf(userGroupHr2.getId()));
					empLine.setAcc1GrpId(String.valueOf(userGroupAcc1.getId()));
					empLine.setAcc2GrpId(String.valueOf(userGroupAcc2.getId()));
					empLine.setDirGrpId(String.valueOf(userGroupDir.getId()));
					empLine.setOfficeId(String.valueOf(office.getId()));
					empLine.setDesignationId(String.valueOf(designation.getId()));
					empLine.setDepartmentId(String.valueOf(department.getId()));
					empLine.setIsValid("Y");		
				}else {
					empLine.setErrorStatus("Invalid present in");
					empLine.setIsValid("N");
					if(!isAttendanceShift) {
						empLine.setErrorStatus(empLine.getErrorStatus()+",AttendanceShift Code");
					}
					if(!isHr1) {
						empLine.setErrorStatus(empLine.getErrorStatus()+",Hr1 Group Code ");
					}
					if(!isHr2) {
						empLine.setErrorStatus(empLine.getErrorStatus()+",Hr2 Group Code");
					} 
					if(!isAcc1) {
						empLine.setErrorStatus(empLine.getErrorStatus()+",Account1 Code");
					}
					if(!isAcc2) {
						empLine.setErrorStatus(empLine.getErrorStatus()+", Account2 Code");
					}
					if(!isDir) {
						empLine.setErrorStatus(empLine.getErrorStatus()+", Director Code");
					}
					if(!isOffice) {
						empLine.setErrorStatus(empLine.getErrorStatus()+",Office Code ");
					}
					if(!isDesig) {
						empLine.setErrorStatus(empLine.getErrorStatus()+",Designation Name");
					}
					if(!isDept) {						
						empLine.setErrorStatus(empLine.getErrorStatus()+",Department code");
					}										
				}
				if(empLine.getEmail()==null || empLine.getEmail()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",email");
					empLine.setIsValid("N");
				} else if(checkDuplicate(empEmail,empLine.getEmail())==true) {
					empLine.setErrorStatus(empLine.getErrorStatus()+",Duplicate Email");
					empLine.setIsValid("N");
				}
				
				if(empLine.getMobileNo()==null || empLine.getMobileNo()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",Mobile No");
					empLine.setIsValid("N");
				} else if(checkDuplicate(empMobileNo,empLine.getMobileNo())==true) {
					empLine.setErrorStatus(empLine.getErrorStatus()+",Duplicate Mobile No");
					empLine.setIsValid("N");
				}
				
				if(empLine.getEmpCode()==null || empLine.getEmpCode()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",Employee code");
					empLine.setIsValid("N");
				}else if(checkDuplicate(empCode,empLine.getEmpCode())==true) {
						empLine.setErrorStatus(empLine.getErrorStatus()+",Duplicate Employee code");
						empLine.setIsValid("N");
						
				}
				if(empLine.getN1EmpCode()==null || empLine.getN1EmpCode()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",N1 Employee code");
					empLine.setIsValid("N");
				}
				if(empLine.getN2EmpCode()==null || empLine.getN2EmpCode()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",N2 Employee code");
					empLine.setIsValid("N");
				}
				if(empLine.getDob()==null || empLine.getDob()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",DOB");
					empLine.setIsValid("N");
				}
				
				if(empLine.getFirstName()==null || empLine.getFirstName()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",First Name");
					empLine.setIsValid("N");
				}
				if(empLine.getSex()==null || empLine.getSex()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",Sex");
					empLine.setIsValid("N");
				}
				if(empLine.getPf()==null || empLine.getPf()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",PF");
					empLine.setIsValid("N");
				}
				if(empLine.getEsi()==null || empLine.getEsi()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",ESI");
					empLine.setIsValid("N");
				}
				if(empLine.getUan()==null || empLine.getUan()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",UAN");
					empLine.setIsValid("N");
				}
				if(empLine.getEmployeeCategory()==null || empLine.getEmployeeCategory()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",Employee Category");
					empLine.setIsValid("N");
				}
				if(empLine.getBankName()==null || empLine.getBankName()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",Bank Name");
					empLine.setIsValid("N");
				}
				if(empLine.getBankAccName()==null || empLine.getBankAccName()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",Bank Account Number");
					empLine.setIsValid("N");
				}
				if(empLine.getIfscCode()==null || empLine.getIfscCode()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",IFSC code");
					empLine.setIsValid("N");
				}	
				if(empLine.getDoj()==null || empLine.getDoj()=="") {
					empLine.setErrorStatus(empLine.getErrorStatus()+",DOJ");
					empLine.setIsValid("N");
				}	
				intEmployeeLineRepo.save(empLine);
			}catch (Exception e) {
				IntEmployeeHdr intEmployeeHdr = new IntEmployeeHdr();
				intEmployeeHdr.setIsError("Y");
				intEmployeeHdr.setErrorMessage(e.getLocalizedMessage());				
				intEmployeeHdr.setStatus("Error during validation");
				intEmployeeHdr.setModifiedBy(commonUtils.getLoggedinUserId());
				intEmployeeHdr.setModifiedDate(LocalDateTime.now());
				intEmployeeHdrRepo.save(intEmployeeHdr);
				e.printStackTrace();
			}
		}
		return savedRecords;
	}
	
	public static boolean checkDuplicate(List<String> input,String givenName) {		
		Integer i=0;
        for (String str : input) {        	
            if(givenName.equals(str)) {
            	i++;
            }
        }        
        if(i==1) {
        	return false;
        }else {
        	return true;
        }
	        
		
	}
	
	private IntEmployeeHdr formIntEmployeeHdr(String docId) {
		IntEmployeeHdr intEmployeeHdr = new IntEmployeeHdr();
		intEmployeeHdr.setUploadDocId(docId);
		intEmployeeHdr.setUploadDate(LocalDateTime.now());
		intEmployeeHdr.setProcessDate(LocalDateTime.now());
		intEmployeeHdr.setCreatedBy(commonUtils.getLoggedinUserId());
		intEmployeeHdr.setCreatedDate(LocalDateTime.now());
		intEmployeeHdr= intEmployeeHdrRepo.save(intEmployeeHdr);
		return intEmployeeHdr;
	}
	
	private IntEmployeeLine formIntEmployeeLine(String employeeHdrId,EmployeeForUpload e) {
		IntEmployeeLine intEmpLine = new IntEmployeeLine();
		intEmpLine.setEmployeeHdrId(employeeHdrId);
		intEmpLine.setEmpCode(e.getEmpCode());
		intEmpLine.setFirstName(e.getFirstName());
		intEmpLine.setLastName(e.getLastName());
		intEmpLine.setStatus(e.getStatus());
		intEmpLine.setMobileNo(e.getMobileNo());
		intEmpLine.setDob(e.getDOB());
		intEmpLine.setEmail(e.getEmail());
		intEmpLine.setSex(e.getSex());
		intEmpLine.setDesignation(e.getDesignation());
		intEmpLine.setN1EmpCode(e.getN1ManagerCode());
		intEmpLine.setN2EmpCode(e.getN2ManagerCode());
		intEmpLine.setHr1GrpCode(e.getHR1GroupCode());
		intEmpLine.setHr2GrpCode(e.getHR2GroupCode());
		intEmpLine.setAcc1GrpCode(e.getAcc1GroupCode());
		intEmpLine.setAcc2GrpCode(e.getAcc2GroupCode());
		intEmpLine.setDirGrpCode(e.getDirGroupCode());
		intEmpLine.setPf(e.getPf());
		intEmpLine.setEsi(e.getEsi());
		intEmpLine.setUan(e.getUan());
		intEmpLine.setOfficeCode(e.getOfficeCode());
		intEmpLine.setDepartmentCode(e.getDepartmentCode());
		intEmpLine.setEmployeeCategory(e.getEmployeeCategory());
		intEmpLine.setShiftCode(e.getShiftCode());
		intEmpLine.setBankName(e.getBankName());
		intEmpLine.setBankAccName(e.getBankAccountName());
		intEmpLine.setAccNumber(e.getAccountNumber());
		intEmpLine.setIfscCode(e.getIfscCode());
		intEmpLine.setDoj(e.getDOJ());
		return intEmpLine;
	}
	
}
