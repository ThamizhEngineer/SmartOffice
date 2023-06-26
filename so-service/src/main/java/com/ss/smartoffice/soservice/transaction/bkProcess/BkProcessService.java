package com.ss.smartoffice.soservice.transaction.bkProcess;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
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
import com.ss.smartoffice.shared.model.bkprocess.BkProcess;
import com.ss.smartoffice.shared.model.bkprocess.BkProcessRepo;
import com.ss.smartoffice.shared.model.bkprocess.BkProcessType;
import com.ss.smartoffice.shared.model.bkprocess.BkProcessTypeRepo;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixService;
import com.ss.smartoffice.soservice.transaction.uploadpayslip.CsvMapperPayslipUploadService;

@RestController
@RequestMapping(path = "/transaction/bk-process")
@Scope("prototype")
public class BkProcessService {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	BkProcessRepo bkProcessRepo;

	@Autowired
	BkProcessTypeRepo bkProcessTypeRepo;

	@Autowired
	CsvMapperPayslipUploadService csvMapperPayslipUploadService;

	@Autowired
	SkillMatrixService skillMatrixService;

	ArrayList<String> knownActions = new ArrayList<String>(
			Arrays.asList("gap-analysis", "process-payroll", "employee-assessement"));

	@PostMapping("/{bkProcessTypeCode}/init")
	public BkProcess init(@PathVariable(value = "bkProcessTypeCode") String bkProcessTypeCode,
			@RequestParam(value = "inputReference") String inputReference, @RequestBody String inputPayload)
			throws SmartOfficeException {
		BkProcess bkProcess = new BkProcess();
		try {

			if (knownActions.contains(bkProcessTypeCode)) {

				BkProcessType processType = bkProcessTypeRepo.findByBkProcessName(bkProcessTypeCode);

				HashMap<String, String> buisnessKeys = new HashMap<>();
				String code = sequenceGenerationService.nextSeq("BK-PROCESS", buisnessKeys);
				bkProcess.setBkProcessTypeId(processType.getId().toString());
				bkProcess.setStartDt(LocalDateTime.now());
				bkProcess.setExpiryDt(LocalDateTime.now().plusMinutes(processType.getTypicalDuration()));
				bkProcess.setExpectedEndDt(LocalDateTime.now().plusMinutes(processType.getExpiryDuration()));
				bkProcess.setIsExpired("N");
				bkProcess.setProcessCode(processType.getBkProcessTypeCode() + "-" + code);
				bkProcess.setProcessStatus("init");
				bkProcess.setInputPayload(inputPayload);
				bkProcess.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				bkProcess.setCreatedDt(LocalDateTime.now());
				bkProcessRepo.save(bkProcess);
				trigger(bkProcess, bkProcessTypeCode);
			} else {
				throw new SmartOfficeException("Invalid Url");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bkProcess.setIsError("Y");
			bkProcess.setErrorMessage(e.getLocalizedMessage());
			bkProcessRepo.save(bkProcess);
			e.printStackTrace();
		}
		return bkProcess;
	}

	public void trigger(BkProcess bkProcess, String Action) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		try {
			JSONObject sample = new JSONObject(bkProcess.getInputPayload());
			switch (Action) {
			case "gap-analysis":
				skillMatrixService.initRequestInternal(bkProcess.getInputPayload(),bkProcess.getId());
				break;
			case "process-payroll":
				log.debug(sample.getString("docId"));
				csvMapperPayslipUploadService.triggerPayslipUpload(sample.getString("docId"), sample.getString("payMonth"), sample.getString("payYear"), bkProcess.getId(),loggedInUser);
				break;
			}
		} catch (Exception e) {
			throw new SmartOfficeException("Exception Occured : BkProcess-- trigger- " + e.getLocalizedMessage());
		}
	}

	@Async("asyncThreadPoolTaskExecutor")
	public void startProgress(Integer bkProcessId,String message) throws SmartOfficeException {
		try {
			BkProcess bkProcessFromDb = bkProcessRepo.findById(bkProcessId).get();
			bkProcessFromDb.setReference(message);
			bkProcessFromDb.setProcessStatus("in-progress");
			bkProcessFromDb.setErrorMessage("Testing");
			bkProcessFromDb.setModifiedDt(LocalDateTime.now());
			bkProcessRepo.save(bkProcessFromDb);

		} catch (Exception e) {
			throw new SmartOfficeException("Exception Occured : start-progress- " + e.getLocalizedMessage());
		}
	}

	@GetMapping("/{id}/check-status")
	public BkProcess checkStatus(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		try {
			BkProcess bkProcessFromDb = bkProcessRepo.findById(id).get();

			if (bkProcessFromDb.getExpiryDt().isBefore(LocalDateTime.now())
					&& bkProcessFromDb.getIsExpired().equals("N") && (bkProcessFromDb.getProcessStatus().equals("init")
							|| bkProcessFromDb.getProcessStatus().equals("in-progress"))) {
				bkProcessFromDb.setIsExpired("Y");
				bkProcessFromDb.setLastMessageUpdated(
						"Process taking unusually long time. please check with the administrator");
				bkProcessRepo.save(bkProcessFromDb);
			}
			return bkProcessFromDb;

		} catch (Exception e) {
			throw new SmartOfficeException("Exception Occured : BkProcess-- check-status- " + e.getLocalizedMessage());
		}
	}

	@Async("asyncThreadPoolTaskExecutor")
	public void updateProgress(Integer bkProcessId,String message) throws SmartOfficeException {
		try {
			BkProcess bkProcessFromDb = bkProcessRepo.findById(bkProcessId).get();
			bkProcessFromDb.setProcessStatus("in-progress");
			bkProcessFromDb.setModifiedDt(LocalDateTime.now());
			bkProcessFromDb.setLastMessageUpdated(message);
			bkProcessRepo.save(bkProcessFromDb);

		} catch (Exception e) {
			throw new SmartOfficeException(
					"Exception Occured : BkProcess-- update-progress- " + e.getLocalizedMessage());
		}
	}

	@Async("asyncThreadPoolTaskExecutor")
	public void completed(Integer bkProcessId,String message) throws SmartOfficeException {
		try {
			BkProcess bkProcessFromDb = bkProcessRepo.findById(bkProcessId).get();
			bkProcessFromDb.setProcessStatus("completed");
			bkProcessFromDb.setEndDt(LocalDateTime.now());
			bkProcessFromDb.setModifiedDt(LocalDateTime.now());
			bkProcessFromDb.setLastMessageUpdated(message);
			bkProcessRepo.save(bkProcessFromDb);
		} catch (Exception e) {
			throw new SmartOfficeException("Exception Occured : BkProcess-- completed- " + e.getLocalizedMessage());
		}
	}

	@Async("asyncThreadPoolTaskExecutor")
	public void error(Integer bkProcessId,String message) throws SmartOfficeException {
		try {
			BkProcess bkProcessFromDb = bkProcessRepo.findById(bkProcessId).get();
			bkProcessFromDb.setProcessStatus("error");

			bkProcessFromDb.setModifiedDt(LocalDateTime.now());
			bkProcessFromDb.setLastMessageUpdated(message);
			bkProcessRepo.save(bkProcessFromDb);
		} catch (Exception e) {
			throw new SmartOfficeException("Exception Occured :  BkProcess-- error- " + e.getLocalizedMessage());
		}
	}

	@GetMapping("/{id}/update-expired")
	public BkProcess updateExpired(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		try {
			BkProcess bkProcessFromDb = bkProcessRepo.findById(id).get();

			if (bkProcessFromDb.getStartDt().isBefore(bkProcessFromDb.getStartDt().plusHours(12))
					&& bkProcessFromDb.getIsExpired().equals("N") && bkProcessFromDb.getProcessStatus().equals("init")
					|| bkProcessFromDb.getProcessStatus().equals("in-progress")) {
				bkProcessFromDb.setIsExpired("Y");
				bkProcessFromDb.setLastMessageUpdated(
						"Process taking unusually long time. please check with the administrator");
				bkProcessRepo.save(bkProcessFromDb);
			}
			return bkProcessFromDb;
		} catch (Exception e) {
			throw new SmartOfficeException(
					"Exception Occured : BkProcess-- update-expired- " + e.getLocalizedMessage());
		}
	}

}
