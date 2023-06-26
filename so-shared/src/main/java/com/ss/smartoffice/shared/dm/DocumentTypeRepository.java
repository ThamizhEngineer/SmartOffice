package com.ss.smartoffice.shared.dm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.dm.DocumentType;

public interface DocumentTypeRepository extends CrudRepository<DocumentType, Integer> {
	List<DocumentType> findByDocTypeCode(String docTypeCode);
}
