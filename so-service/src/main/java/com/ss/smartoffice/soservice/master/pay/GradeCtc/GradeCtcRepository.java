package com.ss.smartoffice.soservice.master.pay.GradeCtc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface GradeCtcRepository extends CrudRepository<GradeCtc, Integer>{
	List<GradeCtc> findByGradeId(String gradeId);

}
