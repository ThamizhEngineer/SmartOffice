//package com.ss.smartoffice.soservice.transaction.job;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.ss.smartoffice.shared.common.CommonUtils;
//import com.ss.smartoffice.shared.model.SmartOfficeException;
//
//import com.ss.smartoffice.soservice.transaction.OnsiteJobTracking.DocInfoRepository;
//import com.ss.smartoffice.soservice.transaction.OnsiteJobTracking.DocMetadataRepository;
//import com.ss.smartoffice.soservice.transaction.OnsiteJobTracking.JobTrackBay;
//import com.ss.smartoffice.soservice.transaction.OnsiteJobTracking.JobTrackBayEquipment;
//import com.ss.smartoffice.soservice.transaction.OnsiteJobTracking.JobTrackBayEquipmentRepository;
//import com.ss.smartoffice.soservice.transaction.OnsiteJobTracking.JobTrackBayRepository;
//import com.ss.smartoffice.soservice.transaction.OnsiteJobTracking.JobTrackEquipmentStage;
//import com.ss.smartoffice.soservice.transaction.OnsiteJobTracking.JobTrackEquipmentStageRepository;
//import com.ss.smartoffice.soservice.transaction.model.DocInfo;
//import com.ss.smartoffice.soservice.transaction.model.DocMetadata;
//import com.ss.smartoffice.soservice.transaction.model.Job;
//import com.ss.smartoffice.soservice.transaction.model.JobDoc;
//@RestController
//@RequestMapping(path = "transaction/onsite-job-trackings")
//public class OnsiteJobTrackingService {
//	@Autowired
//	DocInfoRepository docInfoRepository;
//	@Autowired
//	JobDocRepository jobDocRepository;
//	@Autowired
//	JobsRepository jobsRepository;
//	
//	@Autowired
//	JobTrackBayRepository jobTrackBayRepository;
//	@Autowired
//	DocMetadataRepository docMetadataRepository;
//	
//	@Autowired
//	JobTrackBayEquipmentRepository jobTrackBayEquipmentRepository;
//	
//	@Autowired
//	JobTrackEquipmentStageRepository jobTrackEquipmentStageRepository ;
//
//	@Autowired
//	CommonUtils commonUtils;
//	
//	@PostMapping("/permit-job")
//	public Job addJobTrack(@RequestParam(value = "extraField", required = false) String extraField,
//
//			@RequestParam(value = "files", required = false) MultipartFile[] uploadfiles,
//			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files,
//			Job savedJob, JobDoc jobDoc) throws SmartOfficeException, IOException {
//
//		addDocInfoInPermLocation(extraField, uploadfiles, uploadfile, files);
//		savedJob = new Job();
//		savedJob.setPtwDt(LocalDateTime.now());
//		jobsRepository.save(savedJob);
//
//		jobDoc.setDocId(docId);
//		jobDoc.setDocName(fileName);
//		jobDoc.setUploadDt(LocalDateTime.now());
//
//		jobDoc.setJobId(savedJob.getId().toString());
//		jobDoc.setUploadUserId(commonUtils.getAuthenticatedUser().getName());
//		jobDocRepository.save(jobDoc);
//		return jobsRepository.save(savedJob);
//
//	}
//	@PostMapping("/site-survey")
//	public  Job addSitesurvey(@RequestBody Job job)throws SmartOfficeException{
//		return jobsRepository.save(job);
//	}
//	@PostMapping("/site-survey-upload-doc")
//	public Iterable<DocInfo> siteSurvey(@RequestParam(value = "extraField", required = false) String extraField,
//
//			@RequestParam(value = "files", required = false) MultipartFile[] uploadfiles,
//			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files)throws SmartOfficeException, IOException{
//
//	return 	addDocInfoInPermLocation(extraField, uploadfiles, uploadfile, files);
//		
//	}
////	
////	@PatchMapping("/{id}/job-track-bay")
////	public Job addOrUpdateJobTrackBayLines(@RequestBody Job job)throws SmartOfficeException{
////		if(job.getJobTrackBays()!=null&&!job.getJobTrackBays().isEmpty()) {
////			for(JobTrackBay jobTrackBay:job.getJobTrackBays()) {
////				jobTrackBayRepository.save(jobTrackBay);
////			}
////		}
////		return job;
////		
////	}
//	
////	@PatchMapping("/{id}/job-track-bay-equip")
////	public JobTrackBay addOrUpdateJobTrackBayEquipment(@RequestBody JobTrackBay jobTrackBay)throws SmartOfficeException{
////		if(jobTrackBay.getJobTrackBayEquipments()!=null&&!jobTrackBay.getJobTrackBayEquipments().isEmpty()) {
////			for(JobTrackBayEquipment jobTrackBayEquipment:jobTrackBay.getJobTrackBayEquipments()) {
////				jobTrackBayEquipmentRepository.save(jobTrackBayEquipment);
////			}
////		}
////		return jobTrackBay;
////		
////	}
////	@PatchMapping("/{id}/job-track-bay-equip-task")
////	public JobTrackBayEquipment addOrUpdateJobTrackBayEquipStage(@RequestBody JobTrackBayEquipment jobTrackBayEquipment)throws SmartOfficeException{
////		
////		if(jobTrackBayEquipment.getJobTrackEquipmentStages()!=null&&!jobTrackBayEquipment.getJobTrackEquipmentStages().isEmpty()) {
////			for(JobTrackEquipmentStage jobTrackEquipmentStage:jobTrackBayEquipment.getJobTrackEquipmentStages()) {
////				jobTrackEquipmentStageRepository.save(jobTrackEquipmentStage);
////			}
////		}
////		return jobTrackBayEquipment;
////		
////		
////
////		
////	}
//
//	@PostMapping("/track-progress")
//	public Job addTrackProgress(@RequestParam(value = "extraField", required = false) String extraField,
//
//			@RequestParam(value = "files", required = false) MultipartFile[] uploadfiles,
//			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files,
//			Job savedJob, JobDoc jobDoc) throws SmartOfficeException, IOException {
//		addDocInfoInPermLocation(extraField, uploadfiles, uploadfile, files);
//		savedJob = new Job();
//		savedJob.setTrackProgressDt(LocalDateTime.now());
//		jobsRepository.save(savedJob);
//		jobDoc.setDocId(docId);
//		jobDoc.setDocName(fileName);
//		jobDoc.setUploadDt(LocalDateTime.now());
//
//		jobDoc.setJobId(savedJob.getId().toString());
//		jobDoc.setUploadUserId(commonUtils.getAuthenticatedUser().getName());
//		jobDocRepository.save(jobDoc);
//
//		return jobsRepository.save(savedJob);
//
//	}
//
//	@PostMapping("/mom-draft")
//	public Job addMomDraft(@RequestParam(value = "extraField", required = false) String extraField,
//
//			@RequestParam(value = "files", required = false) MultipartFile[] uploadfiles,
//			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files,
//			Job savedJob, JobDoc jobDoc) throws SmartOfficeException, IOException {
//		addDocInfoInPermLocation(extraField, uploadfiles, uploadfile, files);
//		savedJob = new Job();
//		savedJob.setMomDraftApprovalDt(LocalDateTime.now());
//		jobsRepository.save(savedJob);
//
//		jobDoc.setDocId(docId);
//		jobDoc.setDocName(fileName);
//		jobDoc.setUploadDt(LocalDateTime.now());
//
//		jobDoc.setJobId(savedJob.getId().toString());
//		jobDoc.setUploadUserId(commonUtils.getAuthenticatedUser().getName());
//		jobDocRepository.save(jobDoc);
//
//		return jobsRepository.save(savedJob);
//
//	}
//
//	@PostMapping("/job-complete")
//	public Job addJobComplete(@RequestParam(value = "extraField", required = false) String extraField,
//
//			@RequestParam(value = "files", required = false) MultipartFile[] uploadfiles,
//			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files,
//			Job savedJob, JobDoc jobDoc) throws SmartOfficeException, IOException {
//		addDocInfoInPermLocation(extraField, uploadfiles, uploadfile, files);
//		savedJob = new Job();
//		savedJob.setJobCompleteDt(LocalDateTime.now());
//		jobsRepository.save(savedJob);
//		jobDoc.setDocId(docId);
//		jobDoc.setDocName(fileName);
//		jobDoc.setUploadDt(LocalDateTime.now());
//
//		jobDoc.setJobId(savedJob.getId().toString());
//		jobDoc.setUploadUserId(commonUtils.getAuthenticatedUser().getName());
//		jobDocRepository.save(jobDoc);
//
//		return jobsRepository.save(savedJob);
//	}
//
//	List<DocInfo> docInfos = new ArrayList<>();
//	String docId = "";
//	String fileName = "";
//
//	public Iterable<DocInfo> addDocInfoInPermLocation(
//			@RequestParam(value = "extraField", required = false) String extraField,
//			@RequestParam("files") MultipartFile[] uploadfiles,
//			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files)
//			throws SmartOfficeException, IOException {
//
//		// Get file name maximum file size can be 10Mb when exceeds throw error
//
//		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
//				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
//		if (StringUtils.isEmpty(uploadedFileName)) {
//			throw new SmartOfficeException("please select a file!");
//		}
//
//		for (MultipartFile file : files) {
//			String uploadedFolder = "/opt/so/data/docs/final/memployee-id/";
//			if (file.isEmpty()) {
//
//				throw new SmartOfficeException("Uploading file cannot be Empty- " + file.getOriginalFilename());
//
//			}
//			DocInfo docInfo = new DocInfo();
//			docInfo.setDocNameFromUser(file.getOriginalFilename());
//			docInfo.setDocName(file.getName());
//			docInfo.setDocExtension(getExtensionOfFile(file));
//			docInfo.setDocLocation(uploadedFolder);
//
//			docInfos.add(docInfo);
//			// System.out.println(docInfos);
//			Iterable<DocInfo> dIterable = docInfoRepository.saveAll(docInfos);
//			byte[] bytes = file.getBytes();
//			fileName = LocalDateTime.now() + "-" + docInfo.getId();
//			docId = docInfo.getId().toString();
//			docInfo.setDocName(fileName);
//
//			Path path = Paths.get(uploadedFolder + fileName);
//
//			Files.write(path, bytes);
//			List<DocMetadata> docmetadataList = new ArrayList<DocMetadata>();
//	
//			for (DocInfo docInfo1 : docInfos) {
//				System.out.println(docInfo1.getId());
//				// System.out.println("inside first for loop");
//				DocMetadata docmetadata3 = new DocMetadata();
//				docmetadata3.setMdCode("memployee-id");
//				docmetadata3.setMdValue("3123");
//				docmetadata3.setDocId(docInfo1.getId());
//				docmetadata3.setIsKey("Y");
//				docmetadataList.add(docmetadata3);
//				DocMetadata docmetadata1 = new DocMetadata();
//				docmetadata1.setMdCode("job-id");
//				docmetadata1.setMdValue("1234");
//				docmetadata1.setDocId(docInfo1.getId());
//				docmetadata1.setIsKey("N");
//				docmetadataList.add(docmetadata1);
//				DocMetadata docmetadata2 = new DocMetadata();
//				docmetadata2.setMdCode("project-id");
//				docmetadata2.setMdValue("34345");
//				docmetadata2.setDocId(docInfo1.getId());
//				docmetadata2.setIsKey("N");
//				docmetadataList.add(docmetadata2);
//
//				
//			
//				}
//			Iterable<DocMetadata> docMetdata = docMetadataRepository.saveAll(docmetadataList);
//			
//			
//			
//				
//		
//				
//				
//				
//		
//		}
//		
//
//		return (Iterable<DocInfo>) docInfoRepository.saveAll(docInfos);
//	}
//
//	public static String getExtensionOfFile(MultipartFile file) {
//		String fileExtension = "";
//		// Get file Name first
//		String fileName = file.getOriginalFilename();
//		// System.out.println(fileName);
//
//		// If fileName do not contain "." or starts with "." then it is not a valid file
//		if (fileName.contains(".") && fileName.lastIndexOf(".") != 0) {
//			fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
//		}
//
//		return fileExtension;
//	}
//
//}
