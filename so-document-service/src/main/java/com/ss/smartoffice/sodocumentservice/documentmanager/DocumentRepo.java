package com.ss.smartoffice.sodocumentservice.documentmanager;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DocumentRepo extends CrudRepository<Document, Integer> {
	
	List<Document> findByDocHeaderId(Integer docHeaderId);
	List<Document> findByDocFolderId(Integer docFolderId);
	

}
