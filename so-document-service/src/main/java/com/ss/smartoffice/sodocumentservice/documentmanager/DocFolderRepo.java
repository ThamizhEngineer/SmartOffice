package com.ss.smartoffice.sodocumentservice.documentmanager;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DocFolderRepo extends CrudRepository<DocFolder, Integer> {
	
	List<DocFolder> findByDocFolderId(Integer docFolderId);
	List<DocFolder> findByDocHeaderId(Integer docHeaderId);

}
