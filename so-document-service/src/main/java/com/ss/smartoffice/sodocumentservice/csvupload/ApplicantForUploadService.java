package com.ss.smartoffice.sodocumentservice.csvupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;

@RestController
@RequestMapping("csv/incident-applicants")
public class ApplicantForUploadService {

	@Value("${incident-applicant.url}")
	private String incidentApplicantUrl;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	DocInfoRepository docInfoRepo;
	
//	----------------------------------------
	

	@PostMapping("/upload/{docTypeCode}/{incidentId}")
	public List<DocInfo> triggerApplicantUpload(@PathVariable(value = "docTypeCode") String docTypeCode,@PathVariable(value = "incidentId") String incidentId, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload)
			throws SmartOfficeException {
		List<DocInfo> docInfo =  documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
		List<IncidentApplicant> iaList = new ArrayList<IncidentApplicant>();
		for (DocInfo d : docInfo) {
			System.out.println(d.getDocId());
			if (isNullOrEmpty(d.getDocId())==false) {
				try {
					String path = d.getDocLocation()+"/"+d.getDocName();
					iaList = trigger(path,incidentId);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		return docInfo;
	}
	
    @GetMapping
    public  List<IncidentApplicant> trigger(String path,String incidentId) throws IOException {
        File input = new File(path);
        List<IncidentApplicantForUpload> data = readObjectsFromCsv(input);
        List<IncidentApplicant> ic = applyRequiredLogic(data,incidentId);
        return ic;
    }
    
    public List<IncidentApplicantForUpload> readObjectsFromCsv(File file) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema bootstrap = csvMapper.schemaFor(IncidentApplicantForUpload.class).withHeader();
        MappingIterator<IncidentApplicantForUpload> mappingIterator = csvMapper.readerFor(IncidentApplicantForUpload.class).with(bootstrap).readValues(file);
        return mappingIterator.readAll();
    }
    
    public List<IncidentApplicant> applyRequiredLogic(List<IncidentApplicantForUpload> data,String incidentId) {
    	List<IncidentApplicant> CleanIaList = new ArrayList<IncidentApplicant>();
    	List<IncidentApplicant> UncleanIaList = new ArrayList<IncidentApplicant>();

    		for (IncidentApplicantForUpload incidentApplicantForUpload : data) {
    			try {
					IncidentApplicantForUpload iau = clearRecords(incidentApplicantForUpload);
					IncidentApplicant ia = formdata(iau,incidentId);
					if (ia.getIsClean().equals("Y")) {
						CleanIaList.add(ia);
					}else {
						UncleanIaList.add(ia);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
    		postCleanIncidentApplicant(CleanIaList);
    	return UncleanIaList;
    }
    
    public IncidentApplicantForUpload clearRecords(IncidentApplicantForUpload iau) {
    	if (isNullOrEmpty(iau.getExprienceType())==false) {
    		System.out.println("ExTypeForUpload="+iau.getExprienceType());
			if (iau.getExprienceType().equals("PRO")) {
				System.out.println("isPro="+isPro(iau));
				System.out.println("isFresh="+isFresher(iau));

				if (isPro(iau)==false) {
					iau.setIsClean("N");
					iau.setRemarks("All Fields are Mandatory");
				}
				else {
					iau.setIsClean("Y");
					iau.setRemarks("Clean Records");
				}
			}else if(iau.getExprienceType().equals("FRESH")) {
				if (isFresher(iau)==false) {
					iau.setIsClean("N");
					iau.setRemarks("All Fields are Mandatory");
				}else {
					iau.setIsClean("Y");
					iau.setRemarks("Clean Records");
				}
			}
		}

    	return iau;
    }
    
    public IncidentApplicant formdata(IncidentApplicantForUpload iau,String incidentId) {
    	IncidentApplicant ia = new IncidentApplicant();
    	ia.setIncidentId(incidentId);
    	ia.setFirstName(iau.getFirstName());
    	ia.setLastName(iau.getLastName());
    	ia.setMobileNumber(iau.getMobileNumber());
    	ia.setEmail(iau.getEmail());
    	ia.setDob(iau.getDob());
    	ia.setInstitute(iau.getCollege());
    	ia.setCourse(iau.getCourse());
    	ia.setDegreeName(iau.getDegree());
    	if(iau.getExprienceType().equals("FRESH")) {ia.setExpType("Fresher");}else if(iau.getExprienceType().equals("PRO")) {ia.setExpType("Experienced");}else {ia.setExpType("");}
    	ia.setCurrCompany(iau.getCurrCompany());
    	ia.setCurrDesignation(iau.getCurrDesignation());
    	ia.setCurrExperience(iau.getCurrExprience());
    	ia.setCurrSalary(iau.getCurrSalary());
    	ia.setIsClean(iau.getIsClean());
    	ia.setCleanMessage(iau.getRemarks());
    	return ia;
    }
    
    public static boolean isPro(IncidentApplicantForUpload iau) {
        if(isNullOrEmpty(iau.getFirstName())==true || isNullOrEmpty(iau.getLastName())==true || 
    			isNullOrEmpty(iau.getMobileNumber())==true || isNullOrEmpty(iau.getEmail())==true || 
    			isNullOrEmpty(iau.getDob())==true || isNullOrEmpty(iau.getExprienceType())==true || isNullOrEmpty(iau.getCurrCompany())==true || 
    			isNullOrEmpty(iau.getCurrDesignation())==true || isNullOrEmpty(iau.getCurrSalary())==true) {
            return false;
        }
        return true;
    }
    
    public static boolean isFresher(IncidentApplicantForUpload iau) {
        if(isNullOrEmpty(iau.getFirstName())==true || isNullOrEmpty(iau.getLastName())==true || 
    			isNullOrEmpty(iau.getMobileNumber())==true || isNullOrEmpty(iau.getEmail())==true || 
    			isNullOrEmpty(iau.getDob())==true || isNullOrEmpty(iau.getCollege())==true || 
    			isNullOrEmpty(iau.getCourse())==true || isNullOrEmpty(iau.getDegree())==true) {
            return false;
        }
        return true;
    }

//	----------------------------------------
	
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
    
	private void postCleanIncidentApplicant(List<IncidentApplicant> ia)throws SmartOfficeException{
		commonUtils.getRestTemplate().postForObject(incidentApplicantUrl,ia,IncidentApplicant[].class);
	}
	
}
