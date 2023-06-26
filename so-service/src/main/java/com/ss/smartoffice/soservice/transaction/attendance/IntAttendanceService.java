package com.ss.smartoffice.soservice.transaction.attendance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AttendanceForUpload;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.shift.AttendanceShift;
import com.ss.smartoffice.soservice.master.shift.AttendanceShiftRepo;

@RestController
@Service
@RequestMapping("/int/attendance")
public class IntAttendanceService {

	@Autowired 
	CommonUtils commonUtils;
	
	@Autowired
	IntAttendaceLineRepo intAttendaceLineRepo;
	
	@Autowired
	IntAttendanceHdrRepo intAttendanceHdrRepo;
	
	@Autowired
	AttendanceShiftRepo attendanceShiftRepo;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AttendanceGenerator attendanceGenerator;
	
	@PostMapping("/start")
	public List<AttendanceForUpload> postExtractedData(@RequestBody List<AttendanceForUpload> extarctedData){
		start(extarctedData);
		return extarctedData;
	}
	
	
	public void start(List<AttendanceForUpload> extarctedData) {
		Iterable<IntAttendanceLine> savedRecords = savingRecords(extarctedData);
		validate(savedRecords);
		intAttendaceLineRepo.saveAll(savedRecords);
		String iAttendanceHdrId = savedRecords.iterator().next().getAttendanceHdrId();
		attendanceGenerator.start(iAttendanceHdrId); //Attendance generation starts here
	}
	
	private Iterable<IntAttendanceLine> validate(Iterable<IntAttendanceLine> savedRecords){
		for(IntAttendanceLine attendanceLine:savedRecords) {
			try {
				List<memployee> empCode = employeeRepository.findByEmpCode(attendanceLine.getEmpCode());
				List<memployee> proxyEmpCode = employeeRepository.findByEmpCode(attendanceLine.getProxyEmpCode());
				AttendanceShift attendanceShift = attendanceShiftRepo.findDuplicate(attendanceLine.getShiftCode());
			boolean isEmpCode = checkFunc(empCode.size());
			boolean isProxyEmpCode = checkFunc(proxyEmpCode.size());
			boolean isAttendanceShift=true;
			if(attendanceShift.equals(null)||attendanceShift==null) {
				isAttendanceShift=false;
			}
			if(isEmpCode==true&&isProxyEmpCode==true&&isAttendanceShift==true) {
				attendanceLine.setEmpId(empCode.get(0).getId().toString());
				attendanceLine.setProxyEmpId(proxyEmpCode.get(0).getId().toString());
				attendanceLine.setShiftId(attendanceShift.getId().toString());
			}else if(isEmpCode==false || isProxyEmpCode==false || isAttendanceShift==false) {
				throw new SmartOfficeException("Invalid Code or Null present");
			}
			}catch (Exception e) {
				IntAttendanceHdr  intAttendanceHdr =  new IntAttendanceHdr();
				intAttendanceHdr.setIsError("Y");
				intAttendanceHdr.setErrorMessage(e.getLocalizedMessage());				
				intAttendanceHdr.setStatus("Error during validation");
				intAttendanceHdr.setModifiedBy(commonUtils.getLoggedinUserId());
				intAttendanceHdr.setModifiedDate(LocalDateTime.now());
				intAttendanceHdrRepo.save(intAttendanceHdr);
				e.printStackTrace();
			}
		}
		return savedRecords;
	}
	
	private boolean checkFunc(int size) {
		if (size==1) {
			return true;
		}else {
			return false;
		}
	}

	private Iterable<IntAttendanceLine> savingRecords(List<AttendanceForUpload> extarctedData) {
		try {				
			String docId = extarctedData.get(0).getDocId();
			IntAttendanceHdr intAttendanceHdr=formIntAttendanceHdr(docId);
			List<IntAttendanceLine> intAttendances = new ArrayList<IntAttendanceLine>();
			for(AttendanceForUpload attForUpload:extarctedData) {
				IntAttendanceLine intAttendance = formIntAttendace(intAttendanceHdr.getId().toString(),attForUpload);
				intAttendances.add(intAttendance);
			}
			return intAttendaceLineRepo.saveAll(intAttendances);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Error while saving records");
		}
	}
	
	private IntAttendanceHdr formIntAttendanceHdr(String docId) {
		IntAttendanceHdr intAttendanceHdr = new IntAttendanceHdr();
		intAttendanceHdr.setUploadDocId(docId);
		intAttendanceHdr.setUploadDate(LocalDateTime.now());
		intAttendanceHdr.setProcessDate(LocalDateTime.now());
		intAttendanceHdr.setCreatedBy(commonUtils.getLoggedinUserId());
		intAttendanceHdr.setCreatedDate(LocalDateTime.now());
		intAttendanceHdr = intAttendanceHdrRepo.save(intAttendanceHdr);
		return intAttendanceHdr;
	}
	
	private IntAttendanceLine formIntAttendace(String attendanceHdrId, AttendanceForUpload attendanceForUpload) {
		IntAttendanceLine intAtt = new IntAttendanceLine();
		intAtt.setAttendanceHdrId(attendanceHdrId);
		intAtt.setEmpCode(attendanceForUpload.getEmpCode());
		intAtt.setShiftCode(attendanceForUpload.getShiftCode());
		intAtt.setYear(attendanceForUpload.getYear());
		intAtt.setMonth(attendanceForUpload.getMonth());
		intAtt.setDate(attendanceForUpload.getDate());
		intAtt.setAttendaceStatus(attendanceForUpload.getAttendaceStatus());
		intAtt.setFirstSession(attendanceForUpload.getFirstSession());
		intAtt.setSecoundSession(attendanceForUpload.getSecoundSession());
		intAtt.setCheckIn(attendanceForUpload.getCheckIn());
		intAtt.setCheckOut(attendanceForUpload.getCheckOut());
		intAtt.setInLat(attendanceForUpload.getInLat());
		intAtt.setInLong(attendanceForUpload.getInLong());
		intAtt.setOutLat(attendanceForUpload.getOutLat());
		intAtt.setOutLong(attendanceForUpload.getOutLong());
		intAtt.setRemarks(attendanceForUpload.getRemarks());
		intAtt.setProxyEmpCode(attendanceForUpload.getProxyEmpCode());
		intAtt.setDocId(attendanceForUpload.getDocId());
		return intAtt;
	}
	
}
