package com.ss.smartoffice.sodocumentservice.DocumentManagementService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.sodocumentservice.documentmanager.DocFolder;
import com.ss.smartoffice.sodocumentservice.documentmanager.DocFolderRepo;
import com.ss.smartoffice.sodocumentservice.documentmanager.DocHeader;
import com.ss.smartoffice.sodocumentservice.documentmanager.DocHeaderRepo;
import com.ss.smartoffice.sodocumentservice.documentmanager.Document;
import com.ss.smartoffice.sodocumentservice.documentmanager.DocumentRepo;

import javax.activation.FileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

@RestController
@RequestMapping(path = "/dm")
public class DocMgmtService {
    private static final Logger LOG = LoggerFactory.getLogger(DocMgmtService.class);


	@Value("${docs.folder.delimitter}")
	private String folderDelimitter;
	
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	DocFolderRepo folderRepo;
	@Autowired
	DocHeaderRepo hdrRepo;
	@Autowired
	DocumentRepo docRepo;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	private CustomResourceHttpRequestHandler handler;
	private final static File MP4_FILE = new File("/opt/so/data/docs/upload/UPLOAD-VIDEO/2020-05-12T16:14:34.926-3d5440a5-fd7b-4144-8a52-7c3458d0ad3a.mp4");

	
	@PostMapping("/upload/{docTypeCode}")
	public List<DocInfo> uploadDocAsBinary(@PathVariable(value = "docTypeCode") String docTypeCode, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload)
			throws SmartOfficeException {
		return documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
	}
	
	@PostMapping("/check/{docTypeCode}")
	public Map<String, String> checkDocAsBinary(@PathVariable(value = "docTypeCode") String docTypeCode, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload)
			throws SmartOfficeException {
		return documentManagementHelper.checkDocBinary(docTypeCode, filesToUpload);
	}
	
//	----------Custom-upload---------------------------------------------------------------------------
	
	@PostMapping("/custom-upload/{docTypeCode}/{parentType}/{parentId}")
	public String customUpload(@PathVariable(value = "docTypeCode") String docTypeCode, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload,
			@PathVariable(value = "parentType") String parentType, @PathVariable(value = "parentId") String parentId) throws IOException {
		
		String headerId = "";
		String folderId = "";
		
		if (parentType.equals("header")) {
			headerId = parentId;
		} else {
			folderId = parentId;
		}
		
		boolean isHeader = false;
		
		if (folderId!=null && !folderId.isEmpty()) {
			isHeader = false;
			Optional<DocFolder> folder = folderRepo.findById(Integer.parseInt(folderId));
			if (folder.isPresent()) {
				String path = folder.get().getLocation();
				uploadingAndMovingData(docTypeCode, filesToUpload, path, isHeader, folderId); // Pending
				
			}else {throw new SmartOfficeException("Folder refrence does not exist");}
			
		} else if(headerId!=null && !headerId.isEmpty()) {
			isHeader = true;
			Optional<DocHeader> hdr = hdrRepo.findById(Integer.parseInt(headerId));
			if (hdr.isPresent()) {
				String path = hdr.get().getLocation();
				uploadingAndMovingData(docTypeCode, filesToUpload, path, isHeader, headerId); // Pending
				
			} else {throw new SmartOfficeException("Header does not exist");}
			
			
		} else {
			throw new SmartOfficeException("Either folder/header refrence should be present");
		}
		
		return folderId;
	}

	
	public void uploadingAndMovingData(String docTypeCode, MultipartFile[] filesToUpload, String path, boolean isHdeader, String parentId) throws IOException {
				
		List<DocInfo> docInfos = documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
		for (DocInfo docInfo : docInfos) {
			checkDirAndCreate(path);
			copyFile(docInfo.getDocLocation()+"/"+docInfo.getDocName(), path+"/"+docInfo.getDocName());
			formDocument(docInfo, path, isHdeader, parentId);
		}
	}
	
	
	private void checkDirAndCreate(String path) throws IOException { // For given (input)path this function checks and creates directories
		boolean isFileExists = false;
		Path validPath = Paths.get(path);
		isFileExists = Files.exists(validPath);
		if (isFileExists) {
			System.out.println("345888 - dir exists");
		}else {
			System.out.println("345889 -  Creating dir");
			Files.createDirectories(validPath);
		}
	}
	
	private void copyFile(String source, String destination) { // copy files from (input)source to (input)destination
		Path sourcePath      = Paths.get(source);
		Path destinationPath = Paths.get(destination);

		try {
		    Files.copy(sourcePath, destinationPath);
		} catch(FileAlreadyExistsException e) {
		    //destination file already exists
		} catch (IOException e) {
		    //something else went wrong
		    e.printStackTrace();
		}
	}
	
	public Document formDocument(DocInfo docInfo,String CustomLocation, boolean isHdeader, String parentId) {
		Document doc = new Document();
		doc.setName(docInfo.getDocNameFromUser());
		doc.setReadOnly("N");
		doc.setLocation(CustomLocation);
		doc.setDocId(docInfo.getDocId());
		doc.setDocTypeid(docInfo.getDocTypeId()); 
		doc.setDocTypeName("custom-upload"); //For now its hard coded
		doc.setExtension(docInfo.getDocExtension());
		doc.setCreatedBy(nullEmptyCheck(commonUtils.getLoggedinEmployeeId()));
		doc.setSystemGivenName(docInfo.getDocName());
		
		if (isHdeader) { // checking if the incoming parent id is header or folder
			doc.setDocHeaderId(Integer.parseInt(parentId));
		}else {
			doc.setDocFolderId(Integer.parseInt(parentId));
		}
		return docRepo.save(doc);
	}
	
	public String nullEmptyCheck(String str) {
		if (str != null && !str.isEmpty())
			return str;
		return "";
	}
	
	
//	--------------------------------------------------------------------------------------------------------
	
	
	@PostMapping("/upload-checkin/{docTypeCode}")
	public List<DocInfo> uploadAndCheckIn(@PathVariable(value = "docTypeCode") String docTypeCode, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload)
			throws SmartOfficeException {
		return documentManagementHelper.uploadAndCheckIn(docTypeCode, filesToUpload);
	}
	
	@PostMapping("/update-upload/{docId}/{docTypeCode}")
	@Transactional
	public List<DocInfo> updateUploadDocAsBinary(@PathVariable(value = "docTypeCode") String docTypeCode,@PathVariable(value = "docId") String docId, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload)
			throws SmartOfficeException {
		List<DocInfo> d = new ArrayList<DocInfo>();
		try {
			deleteDoc(docId);
			System.out.println("Deletion completed="+docId);
			d = documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
			System.out.println(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	@PostMapping("/checkin")
	public List<DocInfo> checkInDocs( @RequestBody List<DocInfo> docInfos)
			throws SmartOfficeException {
		System.out.println("i/p-"+docInfos);
		return documentManagementHelper.checkInDocs(docInfos);
	}
	
	

	@GetMapping("/{docId}")
	public ResponseEntity<InputStreamResource> getDocByDocId(@PathVariable("docId") String docId)
			throws SmartOfficeException {

		try {
			DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
			File file = documentManagementHelper.getDocByDocId(docId);
			
			if (docInfo.getDocExtension().isEmpty() || docInfo.getDocExtension().equals("") || docInfo.getDocExtension()==null) {
				docInfo.setDocExtension("jpg");
			}
			
			System.out.println("121009-"+docInfo);
			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.setContentType(MediaType.parseMediaType("application/"+docInfo.getDocExtension()));
			respHeaders.add("Content-Disposition", String.format("attachment; filename=\"" + docInfo.getDocNameFromUser() +"\""));
			respHeaders.add("X-filename", docInfo.getDocNameFromUser());
		
			InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
			return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}

	}
	
//	---------- Play video ------------------------------------------------------
	
	@GetMapping("/byterange/{docId}/_internal")
	public void byterange( HttpServletRequest request, HttpServletResponse response,@PathVariable("docId") String docId)
			throws ServletException, IOException {
		DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
		File mp4File = new File(docInfo.getDocLocation()+"/"+docInfo.getDocName());
		request.setAttribute(CustomResourceHttpRequestHandler.ATTR_FILE, mp4File);
		handler.handleRequest(request, response);
	}
	
	@GetMapping("/byterange/{docId}")
	public void byterangeStream( HttpServletRequest request, HttpServletResponse response,@PathVariable("docId") String docId, @RequestParam("authorization") String token)
			throws ServletException, IOException {
		DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
		File mp4File = new File(docInfo.getDocLocation()+"/"+docInfo.getDocName());
		request.setAttribute(CustomResourceHttpRequestHandler.ATTR_FILE, mp4File);
		handler.handleRequest(request, response);
	}
	
//	------------------------------------------------------------------------------
	
	@GetMapping("/showme/{docId}/_internal")
	public ResponseEntity<byte[]> getImage(@PathVariable("docId") String docId) throws IOException{
		System.out.println("show me");
		DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
	    File img = new File(docInfo.getDocLocation()+"/"+docInfo.getDocName());
	    return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
	}
	
	@PatchMapping("/move")
	public List<DocInfo> moveDocs( @RequestBody List<DocInfo> docInfos)
			throws SmartOfficeException {
		return documentManagementHelper.moveDocs(docInfos);
	}
	
	//TODO - addOrUpdateMetadata  (shouldnt delete metadata)
	
	@GetMapping("/docinfo/{docId}")
	public List<DocInfo> checkInDocs(@PathVariable("docId") String docId)
			throws SmartOfficeException {
		System.out.println("i/p-"+docId);
		return documentManagementHelper.getDocInfoByDocId(docId);
	}

	@GetMapping("/images/{docId}")
	public ResponseEntity<InputStreamResource> getImageDocByDocId(@PathVariable("docId") String docId)
			throws SmartOfficeException {

		try {
			DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
			File file = documentManagementHelper.getDocByDocId(docId);

			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.setContentType(MediaType.parseMediaType("image/jpeg")); 
			respHeaders.add("Content-Disposition", String.format("attachment; filename=\"" + docInfo.getDocNameFromUser() +"\""));

			InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
			return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}

	}
	@DeleteMapping("/delete/bydocId/{docId}")
	@Transactional
	public void deleteDoc(@PathVariable("docId") String docId) throws IOException {
		try {
			System.out.println("docId="+docId);
			DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
			String path = docInfo.getDocLocation()+"/"+docInfo.getDocName();
			System.out.println(path);
			boolean isExists = deleteDocument(path);
			documentManagementHelper.deleteByDocId(docInfo.getDocId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("/delete/withpath")
	public boolean deleteDocument(@RequestBody String path) throws IOException {
		boolean isFileExists = false;
	    try {
			Path fileToDeletePath = Paths.get(path);
			isFileExists = Files.exists(fileToDeletePath);
			System.out.println(isFileExists);
			if(isFileExists) {
			Files.delete(fileToDeletePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return isFileExists;
	}
	
	@DeleteMapping("/delete/orphan-records")
	@Transactional
	public void run() throws IOException {
		System.out.println("Timer");
        LOG.debug("Orphan record - Check trigerred at "+LocalDateTime.now());
		Iterable<DocInfo> docInfos = documentManagementHelper.findAllDocInfo();
		Iterator<DocInfo> itrDocInfos = docInfos.iterator();
		while (itrDocInfos.hasNext()) {
			try {
				DocInfo d = itrDocInfos.next();
				String path = d.getDocLocation()+"/"+d.getDocName();
				Path fileToDeletePath = Paths.get(path);
				boolean isExists = Files.exists(fileToDeletePath);
				if(isExists==false) {
					documentManagementHelper.deleteByDocId(d.getDocId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
