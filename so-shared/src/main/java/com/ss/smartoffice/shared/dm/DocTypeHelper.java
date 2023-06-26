package com.ss.smartoffice.shared.dm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.dm.DocumentType;

@Component
public class DocTypeHelper {

	@Autowired 
	DocumentTypeRepository documentTypeRepository;
	@Autowired 
	CommonUtils commonUtils;
	
	public List<DocumentType> saveAll(List<DocumentType> documentTypes){
		List<DocumentType> changedDocTypes = new ArrayList<DocumentType>(); 
		for (DocumentType documentType : documentTypes) {
			documentType.setCreatedBy(commonUtils.getLoggedinUserId());
			changedDocTypes.add(documentType);
		}
		return (List<DocumentType>) documentTypeRepository.saveAll(documentTypes);
	}
	
	 public List<DocumentType> findByDocTypeCode(String docTypeCode){
		return documentTypeRepository.findByDocTypeCode(docTypeCode);
	}
	

	DocumentType findById(Integer docTypeId){
		return documentTypeRepository.findById(docTypeId).get();
	}
	
}
