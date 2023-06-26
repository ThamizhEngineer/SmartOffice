package com.ss.smartoffice.soservice.seed.units;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UnitRepo extends CrudRepository<Units, String> {

	@Query("select units from com.ss.smartoffice.soservice.seed.units.Units units where LOWER(units.unitCode)=:code")
	Units findByUnitCode(@Param("code")String code);
}
