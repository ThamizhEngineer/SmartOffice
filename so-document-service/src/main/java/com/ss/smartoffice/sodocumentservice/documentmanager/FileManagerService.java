package com.ss.smartoffice.sodocumentservice.documentmanager;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;

@RestController
@RequestMapping(path = "/fm")
public class FileManagerService {
	
	@Autowired
	DocHeaderRepo hdrRepo;
	@Autowired
	DocFolderRepo folderRepo;
	@Autowired
	DocumentRepo docRepo;
	@Autowired
	FileManagerHelper helper;
	
	
	@CrossOrigin("*")
	@PostMapping("/create-header/{name}")
	public DocHeader createHeader(@PathVariable(value = "name")String name) {
		return helper.createNewHeader(name);
	}	
	
	@CrossOrigin("*")
	@PostMapping("/create-folder/{parentType}/{parentId}/{name}")
	public DocFolder createFolder(@PathVariable(value = "parentType")String parentType,@PathVariable(value = "parentId")Integer parentId,@PathVariable(value = "name")String name) throws IOException {
		return helper.createNewFolder(parentType, parentId, name);
	}
	
	@CrossOrigin("*")
	@PostMapping("/copy-file/{documentId}/{fromParentType}/{fromParentId}/{toParentType}/{toParentId}")
	public Document copyFile(Integer documentId,String fromParentType,Integer fromParentId,String toParentType, Integer toParentId) {
		return helper.copyFile(documentId, fromParentType, fromParentId, toParentType, toParentId);
	}
	

//	Download ------------------------------------------------
	
	@GetMapping("/{id}")
	public ResponseEntity<InputStreamResource> getFileByDocumentId(@PathVariable("id") Integer id) throws SmartOfficeException {
		return helper.getFile(id);
	}
	
//	Upload ---------------------------------------------------
	
	@PostMapping("/custom-upload/{docTypeCode}/{parentType}/{parentId}")
	public String customUpload(@PathVariable(value = "docTypeCode") String docTypeCode, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload,
			@PathVariable(value = "parentType") String parentType, @PathVariable(value = "parentId") String parentId) throws IOException {
		return helper.uploadFile(docTypeCode, filesToUpload, parentType, parentId);
	}
	
	
//	--------------Fetch---------------------------------------
	
	@GetMapping("/folders-undr-folder/{folderId}")
	public Iterable<DocFolder> fetchFoldersUnderFolder(@PathVariable(value="folderId") Integer folderId){
		return folderRepo.findByDocFolderId(folderId);
	}
	
	@GetMapping("/folders-undr-hdr/{docHeaderId}")
	public Iterable<DocFolder> fetchFoldersUnderHdr(@PathVariable(value = "docHeaderId") Integer docHeaderId){
		return folderRepo.findByDocHeaderId(docHeaderId);
	}
	
	@GetMapping("/files-undr-hdr/{docHeaderId}")
	public Iterable<Document> fetchFilesUnderHdr(@PathVariable(value = "docHeaderId") Integer docHeaderId){
		return docRepo.findByDocHeaderId(docHeaderId);
	}
	
	@GetMapping("/files-undr-folder/{folderId}")
	public Iterable<Document> fetchFilesUnderFolder(@PathVariable(value = "folderId") Integer folderId){
		return docRepo.findByDocFolderId(folderId);
	}
	
	
//	-----------------Creation------------------------------------------
	
	@PostMapping("/undr-hdr/{id}/{name}")
	public DocFolder createFolderUnderHdr(@PathVariable(value="id") Integer hdrId,@PathVariable(value = "name") String newFolderName) {
		return helper.formFolder("header",hdrId, newFolderName);
	}
	
	@PostMapping("/undr-folder/{id}/{name}")
	public DocFolder createFolderUnderFolder(@PathVariable(value="id") Integer folderId,@PathVariable(value = "name") String newFolderName) {
		return helper.formFolder("folder",folderId, newFolderName);
	}
	
	@CrossOrigin("*")
	@PostMapping("/hdr/{name}")
	public DocHeader createHdr(@PathVariable(value = "name") String name) {
		return helper.formHeader(name);
	}
	

	
//	--------------Temp-------------------------------------------------
	
	@CrossOrigin("*")
	@GetMapping("/hdrs")
	public Iterable<DocHeader> fetchAllHeaders() {
		return hdrRepo.findAll();
	}
	
	@CrossOrigin("*")
	@GetMapping("/folders")
	public Iterable<DocFolder> fetchAllFolders(){
		return folderRepo.findAll();
	}

	@CrossOrigin("*")
	@GetMapping("/documents")
	public Iterable<Document> fetchDocuments(){
		return docRepo.findAll();
	}
	
	
	
}
