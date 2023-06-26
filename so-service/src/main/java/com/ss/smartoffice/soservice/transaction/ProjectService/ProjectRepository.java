package com.ss.smartoffice.soservice.transaction.ProjectService;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface ProjectRepository extends CrudRepository<Project, Integer>{
}
