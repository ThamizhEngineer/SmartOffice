package com.ss.smartoffice.soservice.transaction.uploadpayslip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.model.ManualUploadMap;

@RestController
@RequestMapping("transaction/manual-upload")
public class ManualUploadMapService {
@Autowired
ManualUploadMapRepository manualUploadMapRepository;

//@CrossOrigin(origins = "*")
@GetMapping
public Iterable<ManualUploadMap> getUploadMap()throws SmartOfficeException{
	return manualUploadMapRepository.findAll();
	
}
}
