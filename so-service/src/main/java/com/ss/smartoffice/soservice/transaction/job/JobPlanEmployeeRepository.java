package com.ss.smartoffice.soservice.transaction.job;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobPlanEmp;
@Scope("prototype")
public interface JobPlanEmployeeRepository extends CrudRepository<JobPlanEmp, Integer>{
List<JobPlanEmp>findByJobId(Integer jobId);

@Query("SELECT jpe from com.ss.smartoffice.soservice.transaction.model.JobPlanEmp jpe where "
		+ "(jpe.startDt BETWEEN :fromDt AND :toDt) AND jpe.employeeId=:employeeId OR (jpe.endDt BETWEEN :fromDt AND :toDt) AND jpe.employeeId=:employeeId")
List<JobPlanEmp>findByJobsBtwDates(LocalDate fromDt,LocalDate toDt,String employeeId);

@Query("SELECT jpe from com.ss.smartoffice.soservice.transaction.model.JobPlanEmp jpe where jpe.employeeId=:employeeId ORDER BY jpe.id DESC")
List<JobPlanEmp>findByEmployee(String employeeId);
}
