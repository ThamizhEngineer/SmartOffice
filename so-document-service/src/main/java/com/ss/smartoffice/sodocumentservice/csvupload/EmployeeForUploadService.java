package com.ss.smartoffice.sodocumentservice.csvupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.EmployeeForUpload;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;

@RestController
@RequestMapping("csv/employee")
public class EmployeeForUploadService {

	@Value("${int-employee.url}")
	private String employeeUrl;
	
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	DocInfoRepository docInfoRepo;
	
	@PostMapping("/upload/{docTypeCode}")
	public List<EmployeeForUpload> upload(@PathVariable(value = "docTypeCode") String docTypeCode,
			@RequestParam("uploadingFiles") MultipartFile[] filesToUpload) throws SmartOfficeException {
		List<DocInfo> docInfo = documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
		List<EmployeeForUpload> extractedData = new ArrayList<EmployeeForUpload>();
		for (DocInfo d : docInfo) {
			System.out.println(d.getDocId());
			if (isNullOrEmpty(d.getDocId()) == false) {
				String path = d.getDocLocation() + "/" + d.getDocName();
				try {
					extractedData = (extractDataFromCsv(path, d.getDocId()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return extractedData;
	}
	
	private List<EmployeeForUpload>  extractDataFromCsv(String path, String docId) throws IOException {
		File input = new File(path);
		List<EmployeeForUpload> extractedData = readObjectsFromCsv(input);
		System.out.println(extractedData);
		if (extractedData != null && !extractedData.isEmpty()) {
			extractedData.get(0).setDocId(docId);
			extractedData=postExtractedDataForProcessing(extractedData);
		} else {
			throw new SmartOfficeException("No data has been extrcted");
		}
		return extractedData;
	}
	
	public List<EmployeeForUpload> readObjectsFromCsv(File file) throws IOException {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema bootstrap = csvMapper.schemaFor(EmployeeForUpload.class).withHeader();
		MappingIterator<EmployeeForUpload> mappingIterator = csvMapper.readerFor(EmployeeForUpload.class).with(bootstrap)
				.readValues(file);
		return mappingIterator.readAll();
	}
	
	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty())
			return false;
		return true;
	}
	
	public static boolean checkDuplicate(List<String> input,String type) {
		 Set tempSet = new HashSet();
	        for (String str : input) {
	            if (!tempSet.add(str)) {
	            	throw new SmartOfficeException("have Duplicate "+type+" "+str);
	            }
	        }
	        return false;
	}
	
	private List<EmployeeForUpload> postExtractedDataForProcessing(List<EmployeeForUpload> extrackedData)
			throws SmartOfficeException {
//		List<String> empCode = extrackedData.stream().map(EmployeeForUpload :: getEmpCode).collect(Collectors.toList());
//		List<String> empEmail = extrackedData.stream().map(EmployeeForUpload :: getEmail).collect(Collectors.toList());
//		List<String> empMobileNo = extrackedData.stream().map(EmployeeForUpload :: getMobileNo).collect(Collectors.toList());
//		checkDuplicate(empCode,"Employee Code");
//		checkDuplicate(empEmail,"Employee Email");
//		checkDuplicate(empMobileNo,"Employee Mobile Number");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", commonUtils.getLoggedinAppToken());
		HttpEntity<List<EmployeeForUpload>> request = new HttpEntity<List<EmployeeForUpload>>(extrackedData, headers);

		ResponseEntity<List<EmployeeForUpload>> responseObj = commonUtils.getRestTemplate().exchange(employeeUrl+"/start", HttpMethod.POST, request,
				new ParameterizedTypeReference<List<EmployeeForUpload>>() {
				});
		return responseObj.getBody();
	}
	
}
