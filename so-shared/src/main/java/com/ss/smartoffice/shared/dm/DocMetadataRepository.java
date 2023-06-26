package com.ss.smartoffice.shared.dm;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.dm.DocMetadata;



public interface DocMetadataRepository extends CrudRepository<DocMetadata, Integer>{

	Iterable<DocMetadata> findByDocInfoId(Integer docInfoId);
	List<DocMetadata> findByMdCodeAndMdValue(String mdCode,String mdValue);

	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.dm.DocMetadata d where d.docInfoId= ?1")
	void deleteByDocInfoId(Integer docInfoId);
}
