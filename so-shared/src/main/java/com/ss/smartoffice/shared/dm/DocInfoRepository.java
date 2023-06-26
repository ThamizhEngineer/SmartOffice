
package com.ss.smartoffice.shared.dm;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.dm.DocInfo;



public interface DocInfoRepository extends CrudRepository<DocInfo, Integer>{
	List<DocInfo> findByDocId(String docId);
	List<DocInfo> findByDocTypeId(String docTypeId);
	
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.dm.DocInfo d where d.docId= ?1")
	void deleteByDocId(String docId);
	List<DocInfo> findByDocName(String docName);
	
}
