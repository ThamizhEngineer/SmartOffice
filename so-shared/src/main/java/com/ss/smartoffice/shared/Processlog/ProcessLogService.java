package com.ss.smartoffice.shared.Processlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;


@RestController
@RequestMapping("transaction/process-logs")
public class ProcessLogService {
	@Autowired
	ProcessLogRepository processLogRepository;
	
	@Autowired
	CommonUtils commonUtils;

	@GetMapping
	public Iterable<ProcessLog>getProcessLog( @RequestBody ProcessLog processLog)throws SmartOfficeException{
		
		return processLogRepository.findByProcessLog( processLog.getProcessName(), processLog.getProcessId(), processLog.getActivityName(),processLog.getUserId(), processLog.getProcessStatus(), processLog.getRemarks(), processLog.getJobId(), processLog.getEmployeeId(), processLog.getPartnerId(), processLog.getEndClientId(), processLog.getJobBayId(), processLog.getJobEquipmentId(), processLog.getJobStageId(), processLog.getAttr1(), processLog.getAttr2(), processLog.getAttr3());
		
		
	}
	
	public ProcessLog addLog(@RequestBody ProcessLog processLog)throws SmartOfficeException{
		processLog.setUserId(commonUtils.getLoggedinUserId());
		
		return processLogRepository.save(processLog);
	}

}
