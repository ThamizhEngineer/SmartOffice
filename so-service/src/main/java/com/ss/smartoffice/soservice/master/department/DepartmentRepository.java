package com.ss.smartoffice.soservice.master.department;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.master.model.Department;
@Scope("prototype")
public interface DepartmentRepository extends CrudRepository<Department, Integer>{
	Department findByDeptCode(String deptCode);
}
