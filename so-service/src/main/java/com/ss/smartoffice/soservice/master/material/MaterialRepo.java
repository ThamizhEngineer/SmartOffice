package com.ss.smartoffice.soservice.master.material;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Scope("prototype")
public interface MaterialRepo extends CrudRepository<Material, Integer> {

	@Query("SELECT DISTINCT x.id from com.ss.smartoffice.soservice.master.material.Material x where x.manufacturerId = :manufacturerId")
	List<String> fetchMatrialIdsByManufacturerId(@Param("manufacturerId") String manufacturerId);
	
	@Query("SELECT DISTINCT x.id from com.ss.smartoffice.soservice.master.material.Material x where x.productFamilyId = :productFamilyId")
	List<String> fetchMatrialIdsByProductFamilyId(@Param("productFamilyId") String productFamilyId);
	
	List<Material> findByManufacturerId(String manufacturerId);

	List<Material> findByProductFamilyId(String productFamilyId);

	Page<Material> findAll(Pageable pageable);

	Page<Material> findByMaterialCode(Pageable pageable, String materialCode);

	Page<Material> findByMaterialName(Pageable pageable, String materialName);

	Page<Material> findByMaterialNameAndMaterialCodeAndMaterialCategory(Pageable pageable, String materialCode, String materialName,String materialCategory);

	Page<Material> findByMaterialCategory(Pageable pageable,String materialCategory);
	
	List<Material> findByMaterialCategory(String materialCategory);
}
