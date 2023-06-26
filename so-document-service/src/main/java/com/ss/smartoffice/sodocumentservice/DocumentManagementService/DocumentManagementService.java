package com.ss.smartoffice.sodocumentservice.DocumentManagementService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocMetadataRepository;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.shared.model.EmployeePayout;

@RestController
@RequestMapping(path = "/documents")
public class DocumentManagementService {

	@Autowired
	DocInfoRepository docInfoRepository;
	@Autowired
	DocMetadataRepository docMetadataRepository;
	@Autowired
	DocumentManagementHelper documentManagementHelper;

	@Autowired
	CommonUtils commonUtils;

	@Value("${paysip.pdf.location}")
	private String payslipPdfLocation;

	@Value("${paysip.pdf.final.location}")
	private String payslipPdfFinalLocation;

	@Value("${payout.url}")
	private String payoutUrl;

	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<DocInfo> getAllDocInfo() throws SmartOfficeException {
		return docInfoRepository.findAll();

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}/doc-mgmt/docs/doc-info/_internal")
	public Optional<DocInfo> getDocInfoByIdInternal(@PathVariable(value = "id") Integer id)
			throws SmartOfficeException {
		return docInfoRepository.findById(id);
	}
	
	

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}/doc-mgmt/docs/doc-info")
	public Optional<DocInfo> getDocInfoById(@PathVariable(value = "id") Integer id) throws SmartOfficeException {
		return docInfoRepository.findById(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{docInfoId}/doc-mgmt/docs")
	public Iterable<DocMetadata> getByDocId(@PathVariable(value = "docInfoId") Integer docInfoId) throws SmartOfficeException {
		return docMetadataRepository.findByDocInfoId(docInfoId);

	}

	@CrossOrigin(origins = "*")
	@PatchMapping("/{docId}/doc-mgmt/docs/")
	public DocMetadata updateDocMetadata(@PathVariable(value = "docId",required=true) Integer docId,@RequestBody DocMetadata docMetadata) throws SmartOfficeException {
		Optional<DocInfo> doc = docInfoRepository.findById(docMetadata.getDocInfoId());
		System.out.println(doc);
		System.out.println(doc.get().getDocId());
		if(doc.get().getDocId().isEmpty() || doc.get().getDocId()==null) {
			System.out.println("in-cond");
			throw new SmartOfficeException("docId is not exist");
		}
		else {
		return docMetadataRepository.save(docMetadata);
		}
	}

	@CrossOrigin(origins = "*")
	@PatchMapping("/doc-mgmt/docs/{docId}")
	public Optional<DocInfo> updateDocInfo(@PathVariable(value = "docId") Integer docId,
			@RequestParam(value = "extraField", required = false) String extraField,
			@RequestParam("files") MultipartFile[] uploadfiles,
			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files)
			throws SmartOfficeException, IOException {
		System.out.println("docId-->  "+docId);
		Optional<DocInfo> doc = docInfoRepository.findById(docId);
		if(!doc.isPresent()) {
			System.out.println("in cond");
			throw new SmartOfficeException("docId is not exist");
		}else {
		// Get file name maximum file size can be 10Mb when exceeds throw error
		List<DocInfo> docInfos = new ArrayList<>();

		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
		if (StringUtils.isEmpty(uploadedFileName)) {
			throw new SmartOfficeException("please select a file!");
		}

		for (MultipartFile file : files) {
			String uploadedFolder = "/opt/so/data/docs/final/employee-id/";
			if (file.isEmpty()) {

				throw new SmartOfficeException("Uploading file cannot be Empty- " + file.getOriginalFilename());

			}

			byte[] bytes = file.getBytes();

			Path path = Paths.get(uploadedFolder + LocalDateTime.now() + "-" + docId);

			Files.write(path, bytes);

		}
		return docInfoRepository.findById(docId);
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/doc-mgmt/upload-doc")
	public Iterable<DocInfo> addDocInfo(@RequestParam(value = "extraField", required = false) String extraField,
			@RequestParam("files") MultipartFile[] uploadfiles,
			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files)
			throws SmartOfficeException, IOException {

		// Get file name maximum file size can be 10Mb when exceeds throw error
		List<DocInfo> docInfos = new ArrayList<>();

		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
		if (StringUtils.isEmpty(uploadedFileName)) {
			throw new SmartOfficeException("please select a file!");
		}

		for (MultipartFile file : files) {
			String uploadedFolder = "/opt/so/data/docs/init-upload/";
			if (file.isEmpty()) {

				throw new SmartOfficeException("Uploading file cannot be Empty- " + file.getOriginalFilename());

			}
			DocInfo docInfo = new DocInfo();
			docInfo.setDocNameFromUser(file.getOriginalFilename());
			docInfo.setDocName(file.getName());
			docInfo.setDocExtension(getExtensionOfFile(file));
			docInfo.setDocLocation(uploadedFolder);

			docInfos.add(docInfo);
			// System.out.println(docInfos);
			Iterable<DocInfo> dIterable = docInfoRepository.saveAll(docInfos);
			byte[] bytes = file.getBytes();
			String fileName = LocalDateTime.now() + "-" + docInfo.getId();
			docInfo.setDocName(fileName);
			Path path = Paths.get(uploadedFolder + fileName);

			Files.write(path, bytes);

		}

		return (Iterable<DocInfo>) docInfoRepository.saveAll(docInfos);
	}

	@CrossOrigin(origins = "*")
	@PatchMapping("/doc-mgmt/upload-metadata/{id}")
	public Iterable<DocMetadata> addMetadata(@RequestBody List<DocInfo> docInfos,
			@PathVariable(value = "id") Integer id) throws SmartOfficeException, IOException {
		File destinationFolder = new File("/opt/so/data/docs/final/employee-id/");
		File sourceFolder = new File("/opt/so/data/docs/init-upload/");
		// System.out.println("start");
		// System.out.println(docInfos);
		List<DocMetadata> docmetadataList = new ArrayList<DocMetadata>();
		if (!docInfos.isEmpty()) {
			for (DocInfo docInfo : docInfos) {
				// System.out.println("inside first for loop");
				DocMetadata docmetadata3 = new DocMetadata();
				docmetadata3.setMdCode("employee-id");
				docmetadata3.setMdValue("3123");
				docmetadata3.setDocInfoId(docInfo.getId());
				docmetadata3.setIsKey("N");
				docmetadataList.add(docmetadata3);
				DocMetadata docmetadata1 = new DocMetadata();
				docmetadata1.setMdCode("job-id");
				docmetadata1.setMdValue("1234");
				docmetadata1.setDocInfoId(docInfo.getId());
				docmetadata1.setIsKey("N");
				docmetadataList.add(docmetadata1);
				DocMetadata docmetadata2 = new DocMetadata();
				docmetadata2.setMdCode("project-id");
				docmetadata2.setMdValue("34345");
				docmetadata2.setDocInfoId(docInfo.getId());
				docmetadata2.setIsKey("N");
				docmetadataList.add(docmetadata2);

				Iterable<DocMetadata> docMetdata = docMetadataRepository.saveAll(docmetadataList);
				DocInfo docInfoById = getDocInfoById(docInfo.getId()).get();
				// System.out.println("docInfoById"+docInfoById);
				// System.out.println(docInfoById);
				if (docInfoById.getMetadataList() != null) {
					for (DocMetadata docMetadata : docInfoById.getMetadataList()) {
						// System.out.println("inside seconf for loop");
						int i = 0;
						docInfoById.getMetadataList().get(i).setIsKey("Y");
					}
					docInfoById.getMetadataList().get(1).setIsKey("N");
					docInfoById.getMetadataList().get(2).setIsKey("N");
					List<DocInfo> docInfos2 = new ArrayList<>();
					docInfos2.add(docInfoById);
					Iterable<DocInfo> docInfoList = docInfoRepository.saveAll(docInfos2);

					for (DocInfo docInfo2 : docInfos) {
						// System.out.println("inside third for loop");
						// System.out.println("docInfo2.getMetadataList()==>"+docInfo2.getMetadataList());
						// if(docInfo2.getMetadataList()!=null) {

						if (!destinationFolder.exists()) {
							destinationFolder.mkdirs();
						}
						if (!sourceFolder.exists()) {
							sourceFolder.mkdirs();
						}
						// System.out.println("sourceFolder.exists()==>"+sourceFolder.exists());
						// Check weather source exists and it is folder.
						if (sourceFolder.exists() && sourceFolder.isDirectory()) {
							// Get list of the files and iterate over them
							DocInfo docInfoById1 = getDocInfoById(id).get();

							File file = new File(docInfoById1.getDocName());
							// System.out.println("docInfoById1==>"+docInfoById1);
							if (docInfoById1 != null) {
								for (DocInfo docMetadataLoop : docInfoList) {
									if (docMetadataLoop.getMetadataList() != null) {
										for (DocMetadata docMetadata : docMetadataLoop.getMetadataList()) {
											if (docMetadata.getIsKey().equals("Y")) {

												FileSystem fileSys = FileSystems.getDefault();
												Path srcPath = fileSys.getPath(docInfoById1.getDocLocation() + "/"
														+ docInfoById1.getDocName());
												Path destPath = fileSys
														.getPath(destinationFolder + "/" + docInfoById1.getDocName());
												System.out.println(destPath);
												// Move files to destination folder
												Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);

												//

												file.renameTo(new File(destinationFolder + "/" + file.getName()));
												System.out.println("Hi");

											} else {
												continue;
											}
										}
									}
								}

							}

						} else {
							throw new SmartOfficeException(sourceFolder + "  Folder does not exists");
						}

					}

					// }
				} else {
					throw new SmartOfficeException("List is Empty");
				}
			}
		}
		return docmetadataList;

	}

	public static String getExtensionOfFile(MultipartFile file) {
		String fileExtension = "";
		// Get file Name first
		String fileName = file.getOriginalFilename();
		// System.out.println(fileName);

		// If fileName do not contain "." or starts with "." then it is not a valid file
		if (fileName.contains(".") && fileName.lastIndexOf(".") != 0) {
			fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		return fileExtension;
	}

	public Iterable<DocInfo> addDocInfoInPermLocation(
			@RequestParam(value = "extraField", required = false) String extraField,
			@RequestParam("files") MultipartFile[] uploadfiles,
			@RequestParam(value = "file", required = false) MultipartFile uploadfile, List<MultipartFile> files)
			throws SmartOfficeException, IOException {

		// Get file name maximum file size can be 10Mb when exceeds throw error
		List<DocInfo> docInfos = new ArrayList<>();

		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
		if (StringUtils.isEmpty(uploadedFileName)) {
			throw new SmartOfficeException("please select a file!");
		}

		for (MultipartFile file : files) {
			String uploadedFolder = "/opt/so/data/docs/final/employee-id/";
			if (file.isEmpty()) {

				throw new SmartOfficeException("Uploading file cannot be Empty- " + file.getOriginalFilename());

			}
			DocInfo docInfo = new DocInfo();
			docInfo.setDocNameFromUser(file.getOriginalFilename());
			docInfo.setDocName(file.getName());
			docInfo.setDocExtension(getExtensionOfFile(file));
			docInfo.setDocLocation(uploadedFolder);

			docInfos.add(docInfo);
			// System.out.println(docInfos);
			Iterable<DocInfo> dIterable = docInfoRepository.saveAll(docInfos);
			byte[] bytes = file.getBytes();
			String fileName = LocalDateTime.now() + "-" + docInfo.getId();
			docInfo.setDocName(fileName);
			Path path = Paths.get(uploadedFolder + fileName);

			Files.write(path, bytes);

		}

		return (Iterable<DocInfo>) docInfoRepository.saveAll(docInfos);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/doc-mgmt/upload-payslip")
	public DocInfo addEmpPayoutPayslipDocument( @RequestBody DocInfo docInfo)
			throws SmartOfficeException, IOException {
		File destinationFolder = new File("/opt/so/data/pdf/payouts/");
		String employeePayoutId="";
		if (StringUtils.isEmpty(docInfo.getDocNameFromUser())) {
			throw new SmartOfficeException("please select a file!");
		} else {
			if(docInfo.getMetadataList()!=null) {
				
				for(DocMetadata docMetadata:docInfo.getMetadataList()) {
					if(docMetadata.getMdCode().equals("employee-payout-id")) {
						employeePayoutId =docMetadata.getMdValue();
					}
				}

				
			}
			
			EmployeePayout employeePayout = commonUtils.getRestTemplate()
					.getForObject(payoutUrl + "/" + employeePayoutId, EmployeePayout.class);
			System.out.println("emp payout khizer");
			System.out.println(employeePayout);
			String finalFileName = "";
			// finalFileName="payout-e001-02-2018";
			finalFileName = "payout-" + employeePayout.getEmployeeCode() + "-" + employeePayout.getdSalaryPaidMonth()
					+ "-" + employeePayout.getdSalaryPaidYear();
			FileSystem fileSys = FileSystems.getDefault();
			Path srcPath = fileSys.getPath(payslipPdfLocation + docInfo.getDocNameFromUser());
			Path destPath = fileSys.getPath(destinationFolder + "/" + finalFileName);

			// Move files to destination folder
			Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);

			DocInfo docInfosSaved = new DocInfo();
			docInfosSaved.setDocNameFromUser(docInfo.getDocNameFromUser());
			docInfosSaved.setDocName(finalFileName);
			docInfosSaved.setDocExtension("pdf");
			docInfosSaved.setDocLocation(payslipPdfLocation);

			docInfosSaved = docInfoRepository.save(docInfosSaved);
			if(docInfo.getMetadataList()!=null) {
				
				for(DocMetadata docMetadata:docInfo.getMetadataList()) {
					docMetadata.setDocInfoId(docInfosSaved.getId());
					docMetadataRepository.save(docMetadata);
				}

				
			}
			
			return docInfosSaved;
		}

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/doc-mgmt/download-payslip/{id}")
	public ResponseEntity<InputStreamResource> downloadEmpPayoutPayslipDocument(@PathVariable("id") Integer id)
			throws FileNotFoundException {

		DocInfo docInfo = docInfoRepository.findById(id).get();

		File file = new File(payslipPdfFinalLocation + docInfo.getDocName());

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.parseMediaType("application/pdf"));

		respHeaders.setContentDispositionFormData("attachment", docInfo.getDocName());
		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);

	}

	// @CrossOrigin(origins="*")
	// @PostMapping("/doc-mgmt/upload-payslip/_internal/{fileName}")
	// public DocInfo addPayslipInternal(@PathVariable(value="fileName") String
	// fileName)throws SmartOfficeException, IOException{
	// return addPayslip(fileName);
	// }

}
