package com.ss.smartoffice.soservice.master.employeeskill;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.employee.EmployeeSkill;
import com.ss.smartoffice.soservice.transaction.skillmatrix.ProdAndServOutput;
import com.ss.smartoffice.soservice.transaction.skillmatrix.ProductAndServiceInput;
@Scope("prototype")
public interface EmployeeSkillRepo extends PagingAndSortingRepository<EmployeeSkill, Integer> {

	Page<EmployeeSkill> findBySkillLevelCode(Pageable pageable, String skillLevelCode);

	Page<EmployeeSkill> findById(Pageable pageable, String id);

	Page<EmployeeSkill> findByIdAndSkillLevelCode(Pageable pageable, String skillLevelCode, String id);
	
	@Query("select x from com.ss.smartoffice.shared.model.employee.EmployeeSkill x where x.mEmployeeId = :empId")
	List<EmployeeSkill> findByMEmployeeId(int empId);
	
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.employee.EmployeeSkill employeeSkills where employeeSkills.id= ?1")
	void delete(int id);
	
	@Query("SELECT DISTINCT employeeSkills.mEmployeeId from com.ss.smartoffice.shared.model.employee.EmployeeSkill employeeSkills where employeeSkills.mEmployeeId is not NULL")
	List<Integer> fetchExistingEmpWithSkills();
	
	@Query("Select new com.ss.smartoffice.soservice.transaction.skillmatrix.ProdAndServOutput" + "(x.mProductId,x.mProfileId,count(x)) "
			+ "from com.ss.smartoffice.shared.model.employee.EmployeeSkill x "
			+ "where x.mProductId in :productIds "
			+ "group by x.mProductId,x.mProfileId")
	List<ProdAndServOutput> availabeSkillsIn(@Param("productIds") List<String> productIds);
	
	@Query("Select new com.ss.smartoffice.soservice.transaction.skillmatrix.ProdAndServOutput" + "(x.mProductId,x.mProfileId,count(x)) "
			+ "from com.ss.smartoffice.shared.model.employee.EmployeeSkill x "
			+ "where x.mProductId = :productId "
			+ "group by x.mProductId,x.mProfileId")
	List<ProdAndServOutput> availabeSkillsByProduct(@Param("productId") Integer productId);
	
	@Query("select x from com.ss.smartoffice.shared.model.employee.EmployeeSkill x where x.mEmployeeId = :mEmployeeId and x.mProductId = :mProductId and x.mProfileId = :mProfileId")
	List<EmployeeSkill> fetchByProdSkillEmp(@Param("mEmployeeId") int mEmployeeId, @Param("mProductId") int mProductId, @Param("mProfileId") int mProfileId);
}
