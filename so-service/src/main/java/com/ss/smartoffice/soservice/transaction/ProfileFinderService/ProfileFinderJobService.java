package com.ss.smartoffice.soservice.transaction.ProfileFinderService;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path="transaction/profile-finder")
@Scope("prototype")
public class ProfileFinderJobService {
@Autowired
ProfileFinderJobRepository profileFinderJobRepository;

@Autowired
CommonUtils commonUtils;
@PersistenceContext
private EntityManager entityManager;

@GetMapping("/{id}")
public Optional<ProfileFinderJob>getProfileFinderJob(@PathVariable(value="id")Integer id)throws SmartOfficeException{
return	profileFinderJobRepository.findById(id);
}



@PostMapping
public ProfileFinderJob startProfileFinderJob(@RequestBody ProfileFinderJob profileFinderJob)throws SmartOfficeException{
	ProfileFinderJob savedProfileFinderJob=new ProfileFinderJob();
	if((profileFinderJob.getmEmployeeId()!=null&&!profileFinderJob.getmEmployeeId().isEmpty())||
	(profileFinderJob.getmDepartmentId()!=null&&!profileFinderJob.getmDepartmentId().isEmpty())
	||(profileFinderJob.getmBuId()!=null&&!profileFinderJob.getmBuId().isEmpty())) {
	profileFinderJob.setmProfileId(profileFinderJob.getmProfileId());
	profileFinderJob.setFromDt(profileFinderJob.getFromDt());
	profileFinderJob.setToDt(profileFinderJob.getToDt());
	profileFinderJob.setMapLocationId(profileFinderJob.getMapLocationId());
	profileFinderJob.setmEmployeeId(profileFinderJob.getmEmployeeId());
	profileFinderJob.setmDepartmentId(profileFinderJob.getmDepartmentId());
	profileFinderJob.setStatus("CREATED");
	profileFinderJob.setmBuId(profileFinderJob.getmBuId());
	savedProfileFinderJob=profileFinderJobRepository.save(profileFinderJob);
	}else {
		throw new SmartOfficeException("Required fields cannot be Empty");
	}
	return profileFinderJobRepository.save(profileFinderJob);
	
}

//@CrossOrigin(origins="*")
@PatchMapping("/{id}/start")
public Optional<ProfileFinderJob> processprofilefinderjobId(@PathVariable(value="id")String id)throws SmartOfficeException{
	 StoredProcedureQuery query = entityManager.createStoredProcedureQuery("process_profile_finder_job");
	 String employeeId=commonUtils.getLoggedinEmployeeId();
	 //Declare the parameters in the same order
		 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		 query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		 query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
		 query.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);
		 query.setParameter(1,id);
		 
		 query.setParameter(2,employeeId);
		 
		//Execute query
		 query.execute();
		 
		 //Get output parameters
		    
	     query.getOutputParameterValue(3);

		return profileFinderJobRepository.findById(Integer.parseInt(id));
}

}
