package com.ss.smartoffice.soservice.transaction.skillmatrix;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillMatrixRequestEmpRepo extends CrudRepository<SkillMatrixRequestEmp, Integer> {
	
	List<SkillMatrixRequestEmp> findBySkillMatrixHdrKey(String skillMatrixHdrKey);
	
	@Query("select new com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixRequestEmp(x.id,x.skillMatrixHdrKey,x.employeeId,"
			+ "x.departmentId,x.isEnabled,x.createdBy,x.createdDt,x.modifiedBy,x.modifiedDt,x.employeeName,x.designationId,"
			+ "x.designationName,x.n1EmpId,x.n2EmpId,x.deptName,x.deptCode) "
			+ "from com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixRequestEmp x "
			+ "where x.skillMatrixHdrKey = :skillMatrixHdrKey")
	List<SkillMatrixRequestEmp> fetchByKey(@Param(value = "skillMatrixHdrKey") String skillMatrixHdrKey);
	
// This may be needed for reference later
//	@Query("Select new com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixResultQueryObject" + "(y.id,x.employeeId,y.mProductId,"
//			+ "y.mProfileId,y.skillLevelCode,x.skillMatrixHdrKey,y.serviceName,y.productName,x.deptCode,x.deptName,x.n1EmpId,x.n2EmpId,x.designationId,x.designationName) from com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixRequestEmp x "
//			+ "inner join com.ss.smartoffice.shared.model.employee.EmployeeSkill y on x.employeeId=y.mEmployeeId "
//			+ "inner join com.ss.smartoffice.soservice.transaction.skillmatrix.SkillMatrixRequestSkills z on (y.mProductId=z.productId and y.mProfileId=z.serviceId and z.skillMatrixHdrKey=:skillMatrixHdrKey) "
//			+ "where x.skillMatrixHdrKey=:skillMatrixHdrKey")
//	List<SkillMatrixResultQueryObject> customQuery(@Param(value = "skillMatrixHdrKey") String skillMatrixHdrKey);
	
}
