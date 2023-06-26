package com.ss.smartoffice.soservice.transaction.skillmatrix;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillMatrixRequestSkillsRepo extends CrudRepository<SkillMatrixRequestSkills, Integer> {
	
	@Query("Select new com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixRequestSkills(x.id,x.skillMatrixHdrKey,x.productId,x.productName,x.serviceId,"
			+ "x.serviceName,x.isEnabled,x.createdDt,x.modifiedBy,x.modifiedDt,x.expectedCount,x.availableCount,x.gapCount) "
			+ "from com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixRequestSkills x "
			+ "where x.skillMatrixHdrKey=:skillMatrixHdrKey")
	List<SkillMatrixRequestSkills> fetchByKey(String skillMatrixHdrKey);
	
	List<SkillMatrixRequestSkills> findBySkillMatrixHdrKey(String skillMatrixHdrKey);
		
	@Query("Select x "
			+ "from com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixRequestSkills x "
			+ "where x.productId=:productId and x.serviceId=:serviceId and x.skillMatrixHdrKey=:skillMatrixHdrKey")
	List<SkillMatrixRequestSkills> fetchByProdServKey(@Param("productId")String productId, @Param("serviceId")String serviceId, @Param("skillMatrixHdrKey")String skillMatrixHdrKey);
}
