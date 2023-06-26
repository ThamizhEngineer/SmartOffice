package com.ss.smartoffice.shared.dm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;

@Component
public class DocInfoHelper {

	@Autowired 
	DocInfoRepository docInfoRepository;
	
	@Autowired
	DocMetadataRepository docMetadataRepository;
	@Autowired 
	CommonUtils commonUtils;
	
	public List<DocInfo> saveAll(List<DocInfo> docInfos){ 
		for (DocInfo docInfo : docInfos) {
			
			if(docInfo.getMetadataList()!=null) {
				for (DocMetadata docMetadata : docInfo.getMetadataList()) {
					
				}
			}
		}
		return (List<DocInfo>) docInfoRepository.saveAll(docInfos);
	}

	List<DocInfo> findByDocId(String docId){
		return docInfoRepository.findByDocId(docId);
	}
	List<DocInfo> findByDocName(String docName){
		return docInfoRepository.findByDocName(docName);
	}
	List<DocMetadata> findByMdCodeAndMdValue(String mdCode,String mdValue){
		return docMetadataRepository.findByMdCodeAndMdValue(mdCode, mdValue);
	}
	
	void deleteByDocId(String docId) {
		docInfoRepository.deleteByDocId(docId);
	}
	void deleteByDocInfoId(Integer docInfoId) {
		docMetadataRepository.deleteByDocInfoId(docInfoId);
	}
	
	Iterable<DocInfo> getAllDocInfo() {
		return docInfoRepository.findAll();
	}
	
	
}
