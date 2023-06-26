package com.ss.smartoffice.sodocumentservice.csvupload;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.IncidentApplicantForUpload;
import com.ss.smartoffice.shared.model.OfficeCalender;
import com.ss.smartoffice.shared.model.OfficeCalenderForUpload;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;

@RestController
@RequestMapping("csv/office-calendars")
public class OfficeCalenderForUploadService {
	
	@Value("${office-calendars.url}")
	private String officeCalenderUrl;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	DocInfoRepository docInfoRepo;
	
	
	@PostMapping("/upload/{docTypeCode}/{officeId}/{yearId}")
	public List<DocInfo> triggerApplicantUpload(@PathVariable(value = "docTypeCode") String docTypeCode,@PathVariable(value = "officeId") String officeId,@PathVariable(value = "yearId") String yearId, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload)
			throws SmartOfficeException {
		System.out.println("Started 1");
		List<DocInfo> docInfo =  documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
		List<OfficeCalender> officeCalender = new ArrayList<OfficeCalender>();
		System.out.println("file 1"+filesToUpload);
		System.out.println("Started 1"+docInfo);
		for (DocInfo d : docInfo) {
			System.out.println(d.getDocId());
			System.out.println("HIT 1");
			if (isNullOrEmpty(d.getDocId())==false) {
				try {
					String path = d.getDocLocation()+"/"+d.getDocName();
					officeCalender = trigger(path,officeId,yearId);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		
		return docInfo;
	}
	
	public List<OfficeCalender> trigger(String path,String officeId,String yearId) throws IOException {
		  File input = new File(path);
		  System.out.println("HIT 2");
		  List<OfficeCalenderForUpload> data = readObjectsFromCsv(input);
		  List<OfficeCalender> oc = applyRequiredLogic(data,officeId,yearId);
		return oc;
	}
	
	 public List<OfficeCalenderForUpload> readObjectsFromCsv(File file) throws IOException {
		 CsvMapper csvMapper = new CsvMapper();
	        CsvSchema bootstrap = csvMapper.schemaFor(OfficeCalenderForUpload.class).withHeader();
	        MappingIterator<OfficeCalenderForUpload> mappingIterator = csvMapper.readerFor(OfficeCalenderForUpload.class).with(bootstrap).readValues(file);
		 return mappingIterator.readAll();
	 }
	
	 public List<OfficeCalender> applyRequiredLogic( List<OfficeCalenderForUpload> data,String officeId,String yearId){
		 System.out.println("HIT 3");
		 List<OfficeCalender> cleanList = new ArrayList<OfficeCalender>();
		 
		 for(OfficeCalenderForUpload officeCalenderForUpload:data) {
			try {
				OfficeCalender oc = formdata(officeCalenderForUpload,officeId,yearId);
				if(isFilled(officeCalenderForUpload)==true) {
					cleanList.add(oc);
				}				
			}catch (Exception e) {
				e.printStackTrace();
			}
		 }		
		 
		 System.out.println(cleanList);
		 postCleanOfficeCalender(cleanList);
		 return cleanList;
	 }
	 
	 public OfficeCalender formdata(OfficeCalenderForUpload ocu,String officeId,String yearId) {
		 
		 OfficeCalender oc = new OfficeCalender();
		 oc.setOfficeId(officeId);
		 oc.setCalYearId(yearId);
		 oc.setCalDate(LocalDate.parse(ocu.getCalDate()));
		 oc.setHolidayName(ocu.getHolidayName());
		 oc.setIsRestrictedHoliday(ocu.getIsRestrictedHoliday());
		 oc.setRemarks(ocu.getRemarks());
		 oc.setDayType("holiday");
		 oc.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		 oc.setCreatedDt(LocalDateTime.now());
		 return oc;
	 }
	 
	 public static boolean isFilled(OfficeCalenderForUpload ocu) {
		 if(isNullOrEmpty(ocu.getCalDate())==true||isNullOrEmpty(ocu.getHolidayName())==true
		|| isNullOrEmpty(ocu.getIsRestrictedHoliday())==true||isNullOrEmpty(ocu.getRemarks())==true) {
			 return false;
		 }
		 return true;
	 }
	
	public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
	
	private void postCleanOfficeCalender(List<OfficeCalender> oc)throws SmartOfficeException{
		System.out.println("HIT 4");
		commonUtils.getRestTemplate().postForObject(officeCalenderUrl,oc,OfficeCalender[].class);

	}

}
