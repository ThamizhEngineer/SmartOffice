package com.ss.smartoffice.soservice.transaction.skillmatrix;

import java.util.List;import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillMatrixResultRepo extends CrudRepository<SkillMatrixResult, Integer> {
	

	List<SkillMatrixResult> findBySkillMatrixHdrKey(String skillMatrixHdrKey);

	@Query("Select new com.ss.smartoffice.soservice.transaction.skillmatrix.GapOutput" + "(x.productId,x.serviceId,count(x)) "
			+ "from com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixResult x "
			+ "where x.skillMatrixHdrKey=:skillMatrixHdrKey "
			+ "group by x.productId,x.serviceId")
	List<GapOutput> queryGrpByProdAndServ(@Param("skillMatrixHdrKey") String skillMatrixHdrKey);
	
	@Query("select count(x.skillLevelCode) from com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixResult x where "
			+ "x.skillMatrixHdrKey=:skillMatrixHdrKey AND x.skillLevelCode>1 AND productId=:productId AND serviceId=:serviceId")
	Integer findAvailableCount(@Param("skillMatrixHdrKey") String skillMatrixHdrKey,@Param("productId") String productId,@Param("serviceId") String serviceId);
	
	@Query("Select new com.ss.smartoffice.soservice.transaction.skillmatrix.RowSpanObject" + "(x.deptId,count( DISTINCT x.employeeId)) "
			+ "from com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixResult x "
			+ "where x.skillMatrixHdrKey=:skillMatrixHdrKey "
			+ "group by x.deptId")
	List<RowSpanObject> fetchRowSpan(@Param("skillMatrixHdrKey") String skillMatrixHdrKey);
	
	@Modifying
	@Query("update com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixResult x set x.rowSpan = :rowSpan where x.deptId = :deptId AND x.skillMatrixHdrKey=:skillMatrixHdrKey")
	int updateRowSpan(@Param("rowSpan") String rowSpan, 
	  @Param("deptId") String deptId,@Param("skillMatrixHdrKey") String skillMatrixHdrKey);

}
