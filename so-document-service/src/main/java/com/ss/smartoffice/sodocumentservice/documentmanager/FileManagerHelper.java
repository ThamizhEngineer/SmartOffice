package com.ss.smartoffice.sodocumentservice.documentmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;

@Service
public class FileManagerHelper {

	@Autowired
	FileOperationHelper operation;

	@Autowired
	DocFolderRepo folderRepo;

	@Autowired
	DocHeaderRepo hdrRepo;

	@Autowired
	DocumentRepo docRepo;
	
	@Autowired
	DocMgmtService docMgmtService;

	@Autowired
	CommonUtils commonUtils;

	private String defaultPath = "/opt/so/data/docs";

	public DocHeader createNewHeader(String newDocHeaderName) {
		DocHeader docHdr = formHeader(newDocHeaderName); // New doc header with default path will be created
		try {
			operation.createDirectories(defaultPath + "/" + newDocHeaderName); // physical directory will be created
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docHdr;
	}

	public DocFolder createNewFolder(String parentType, Integer parentId, String newFolderName) throws IOException {
		DocFolder docFolder = formFolder(parentType, parentId, newFolderName);
		if (parentType.equals("header")) {
			DocHeader hdr = hdrRepo.findById(parentId).get();
			operation.createDirectories(hdr.getLocation() + "/" + newFolderName);
			docFolder.setLocation(hdr.getLocation() + "/" + newFolderName);
			folderRepo.save(docFolder);
		} else {
			DocFolder folder = folderRepo.findById(parentId).get();
			operation.createDirectories(folder.getLocation() + "/" + newFolderName);
			docFolder.setLocation(folder.getLocation() + "/" + newFolderName);
			folderRepo.save(docFolder);
		}
		return docFolder;
	}

	public Document copyFile(Integer documentId, String fromParentType, Integer fromParentId, String toParentType,
			Integer toParentId) {
		Document toDoc = new Document();
		try {
			Document fromDoc = docRepo.findById(documentId).get();
			toDoc = formDocumentFromDocument(toParentType, toParentId, fromDoc);

			DocFolder docFolder = folderRepo.findById(toParentId).get();
			String source = fromDoc.getLocation() + "/" + fromDoc.getSystemGivenName();
			String destination = docFolder.getLocation() + "/" + fromDoc.getSystemGivenName();
			operation.copyFile(source, destination);
			toDoc.setLocation(docFolder.getLocation());
			toDoc = docRepo.save(toDoc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toDoc;
	}

	public ResponseEntity<InputStreamResource> getFile(@PathVariable("id") Integer id) throws SmartOfficeException {
		try {
			Document doc = docRepo.findById(id).get();
			File file = fetchFile(id);
			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.setContentType(MediaType.parseMediaType("application/" + doc.getExtension()));
			respHeaders.add("Content-Disposition", String.format("attachment; filename=\"" + doc.getName() +"\"")); 
			respHeaders.add("X-filename", doc.getName());
			InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
			return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
	}

	public File fetchFile(Integer id) {
		try {
			Document doc = docRepo.findById(id).get();
			File file = new File(doc.getLocation() + "/" + doc.getSystemGivenName());
			return file;
		} catch (SmartOfficeException soe) {
			throw soe;
		} catch (Exception e) {
			throw new SmartOfficeException(e.getMessage());
		}
	}
	
	//Custom-upload
	public String uploadFile(String docTypeCode,MultipartFile[] filesToUpload,
			String parentType,String parentId) throws IOException {
		return docMgmtService.customUpload(docTypeCode, filesToUpload, parentType, parentId);
	}

//	Forming data ----------------------------------------------------

	public DocFolder formFolder(String parentType, Integer parentId, String newFolderName) {
		DocFolder df = new DocFolder();
		if (parentType.equals("header")) {
			df.setDocHeaderId(parentId);
		} else {
			df.setDocFolderId(parentId);
		}
		df.setName(newFolderName);
		df.setReadOnly("N");
		df.setType("folder");
		df.setCreatedBy(nullEmptyCheck(commonUtils.getLoggedinEmployeeId()));
		return folderRepo.save(df);
	}

	public DocHeader formHeader(String name) {
		DocHeader dh = new DocHeader();
		dh.setName(name);
		dh.setReadOnly("N");
		dh.setLocation(defaultPath + "/" + name);
		dh.setCreatedBy(nullEmptyCheck(commonUtils.getLoggedinEmployeeId()));
		return hdrRepo.save(dh);
	}

	public Document formDocumentFromDocument(String parentType, Integer parentId, Document fromDoc) {
		Document toDoc = new Document();
		if (parentType.equals("header")) {
			toDoc.setDocHeaderId(parentId);
		} else {
			toDoc.setDocFolderId(parentId);
		}
		toDoc.setCreatedBy(nullEmptyCheck(commonUtils.getLoggedinEmployeeId()));
		toDoc.setName(fromDoc.getName());
		toDoc.setDocId(fromDoc.getDocId());
		toDoc.setReadOnly("N");
		toDoc.setDocTypeid(fromDoc.getDocTypeid());
		toDoc.setDocTypeName(fromDoc.getDocTypeName());
		toDoc.setExtension(fromDoc.getExtension());
		toDoc.setSystemGivenName(fromDoc.getSystemGivenName());
		return toDoc;
	}

	public Document formDocumentFromDocInfo() {
		return null;
	}

//	extras ---------------------------------------------------------------

	public String nullEmptyCheck(String str) {
		if (str != null && !str.isEmpty())
			return str;
		return "";
	}
}
