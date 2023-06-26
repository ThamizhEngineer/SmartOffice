package com.ss.smartoffice.shared.dm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocumentType;


@RestController
@RequestMapping(path="dm/document_types")
public class DocumentTypeService {
@Autowired
DocumentTypeRepository documentTypeRepository;
@Autowired
DocInfoRepository docInfoRepo;

@Autowired
CommonUtils commonutils;

//@CrossOrigin(origins="*")
@GetMapping
public Iterable<DocumentType> getAllDocumentTypes()throws SmartOfficeException{
	return documentTypeRepository.findAll();
	
}
//@CrossOrigin(origins="*")
@GetMapping("/{id}")
public Optional<DocumentType> getDocumentTypeById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return documentTypeRepository.findById(id);
	
}
//@CrossOrigin(origins="*")
@PostMapping
public DocumentType addDocumentType(@RequestBody DocumentType documentType)throws SmartOfficeException{
	return documentTypeRepository.save(documentType);
	
}
//@CrossOrigin(origins="*")
@PatchMapping("/{id}")
public DocumentType updateDocumentType(@RequestBody DocumentType documentType)throws SmartOfficeException{
	return documentTypeRepository.save(documentType);
	
}
//@CrossOrigin(origins="*")
@DeleteMapping("/{id}")
public void deleteDocumentType(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	if(docInfoRepo.findByDocTypeId(id.toString())==null || docInfoRepo.findByDocTypeId(id.toString()).isEmpty()) {
		documentTypeRepository.deleteById(id);
	}else {
		throw new SmartOfficeException("Warning : Doc info exists for docType. Cannot compelete deletion");
	}
}

@PatchMapping("/bulk-update")
public Iterable<DocumentType> bulkaddAndUpdate(@RequestBody List<DocumentType> documentType) throws SmartOfficeException{
	
	List<DocumentType> documentTypeList=new ArrayList<DocumentType>();
	System.out.println("list"+documentTypeList);
	System.out.println("ddddd"+documentType);
	for(DocumentType doc:documentType) {
		System.out.println("doc"+doc);
		if(doc.getId()>0) {
			System.out.println("doc1"+doc);

			DocumentType documentTypeFromDB=documentTypeRepository.findById(doc.getId()).orElse(new DocumentType());
			
			System.out.println(documentTypeFromDB);
			
			doc=this.matchAndRelavantFields(documentTypeFromDB,doc);	
			
			System.out.println("dtfdb"+documentTypeFromDB);
			System.out.println("doc2"+doc);

		}else {
			doc=addingNewRecord(doc);
			
			System.out.println("doc3"+doc);
		}
		doc.setIsEnabled("Y");
		documentTypeList.add(doc);
}
	
	System.out.println(documentTypeList);
	
	return (Iterable<DocumentType>)documentTypeRepository.saveAll(documentTypeList);

}
private DocumentType addingNewRecord(DocumentType documentType) {
	// this service will add new Record if there is no existing Record
	System.out.println("doct"+documentType);

	DocumentType newDocumentType=new DocumentType();
	
	System.out.println("ndoct"+newDocumentType);

	newDocumentType.setId(documentType.getId());
	newDocumentType.setDocTypeCode(documentType.getDocTypeCode());
	newDocumentType.setDocTypeName(documentType.getDocTypeName());
	newDocumentType.setDocTypeDesc(documentType.getDocTypeDesc());
	newDocumentType.setIsEnabled("Y");
	newDocumentType.setCreatedDt(LocalDateTime.now());
	return newDocumentType;
}
private DocumentType matchAndRelavantFields(DocumentType documentTypeFromDB, DocumentType documentType) {
	//only update relevant fields to the db-record
	System.out.println("doct1"+documentType);
	System.out.println("doctfdb"+documentTypeFromDB);

	documentTypeFromDB.setId(documentType.getId());
	documentTypeFromDB.setDocTypeCode(documentType.getDocTypeCode());
	documentTypeFromDB.setDocTypeName(documentType.getDocTypeName());
	documentTypeFromDB.setDocTypeDesc(documentType.getDocTypeDesc());
	return documentTypeFromDB;
}

}