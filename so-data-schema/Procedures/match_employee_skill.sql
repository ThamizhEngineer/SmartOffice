CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`match_employee_skill`(in profileFinderId VARCHAR(100),in profileId VARCHAR(100),in fromDt DATE, in toDt date ,in maplocationId VARCHAR(100),in t_pf_emp_id VARCHAR(100),in employeeId VARCHAR(100), in userId VARCHAR(100), out resultCode VARCHAR(25),out resultDesc VARCHAR(100))
BEGIN
declare successCount int default 0;
declare failureCount int default 0;
declare loopComplete BOOL default false;
declare v_employeeId varchar(100) DEFAULT "";
declare v_employeeId1 varchar(100) DEFAULT "";
declare v_jobId varchar(100) DEFAULT "";
declare empLeave varchar(100);
declare jobId varchar(100);
declare mapLocLats varchar(100);
declare mapLocLongs  varchar(100); 
declare empName  varchar(100); 
declare empCode  varchar(100);
declare desigName  varchar(100);
declare jobLats varchar(100);
declare jobLongs varchar(100);
declare prevLats varchar(100);
declare prevLongs varchar(100);
declare prevMapLocationId varchar(100);
declare prevJobId varchar(100);
declare distanceToLocation varchar(100); 
declare totalNoOfYs int default 0;
declare noOfYsPresent int default 0;
declare matchScore int default 0;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET loopComplete = TRUE;
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN
set resultCode='FAILURE';
set resultDesc='match_employee_skill - Unknown Error';
select resultDesc;

GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, 
	@errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
SET @full_error = CONCAT("ERROR ", @errno, " (", @sqlstate, "): ", @text);
SELECT @full_error;
END;

	SET resultCode='START';
	select resultCode;

	-- remove existing records
	delete from t_pf_emp_skill where match_employee_id = t_pf_emp_id;
	delete from t_pf_emp_commitment where match_employee_id = t_pf_emp_id;

	insert into t_pf_emp_skill (match_employee_id, m_profile_line_id, m_product_id,product_name, m_service_id, service_name, skill_level,  is_matched,  created_dt,  created_by,  is_enabled )
	select t_pf_emp_id as match_employee_id, p.id profile_line_id, p.product_id,p.product_name, p.service_id, p.service_name,p.skill_level,  CASE WHEN(e.m_product_id is null) then 'N' else 'Y' end matched, CURRENT_TIMESTAMP created_dt, userId created_by, 'Y' is_enabled from (select * from v_profile_skill where profile_id =profileId) p
	left join (select * from m_emp_skill where m_employee_id= employeeId  ) as e on     p.product_id  =  e.m_product_id  and p.service_id  =  e.m_service_id
	;

	-- select 'inserted into skill';

	select emp.emp_name ,emp.emp_code, designation.desig_name  
	into empName, empCode, desigName from m_employee emp
	Left join s_designation designation on designation.id=emp.designation_id
	where emp.id=employeeId;


	select COUNT(is_matched) into totalNoOfYs FROM t_pf_emp_skill skill 
	where skill.match_employee_id=t_pf_emp_id ;

	select COUNT(is_matched) into noOfYsPresent FROM t_pf_emp_skill skill 
	where skill.match_employee_id=t_pf_emp_id AND skill.is_matched='Y';

	set matchScore = (noOfYsPresent/totalNoOfYs) *100;

	-- select 'calculated score';
	SELECT location.lats, location.longs  into mapLocLats , mapLocLongs FROM m_map_location location WHERE location.id=maplocationId ;


	SELECT mapLocation.lats,mapLocation.longs  into jobLats,jobLongs FROM t_job_emp emp 
	JOIN m_map_location mapLocation 
	Left join t_job job ON emp.t_job_id=job.id where emp.employee_id= employeeId and mapLocation.id=27 
	and job.start_dt>=fromDt;



	select mapLocation.lats , mapLocation.longs into prevLats, prevLongs FROM t_job  job 
	left join m_map_location mapLocation on mapLocation.id=job.map_location_id
	order by job.start_dt<fromDt DESC LIMIT 1;



	call find_distance(mapLocLats,mapLocLongs,prevLats,prevLongs,@distanceToLocation);

	-- add any relevant leave as commitment
	insert into t_pf_emp_commitment (match_employee_id, commitment_type, from_dt,  to_dt,  leave_name,  created_dt,  created_by,  is_enabled)
	select t_pf_emp_id as match_employee_id, 'LEAVE' commitment_type, empAppln.start_dt as from_dt,empAppln.end_dt  as to_dt, leaves.leave_type_name leave_name, CURRENT_TIMESTAMP created_dt, userId created_by, 'Y' is_enabled
	from t_emp_leave_appln empAppln 
	LEFT JOIN s_leave_type leaves ON leaves.id=empAppln.leave_type_id
	where empAppln.employee_id=employeeId 
	and (empAppln.start_dt BETWEEN fromDt and  empAppln.end_dt or empAppln.end_dt BETWEEN fromDt and  empAppln.end_dt);

	-- add any relevant holiday as commitment
	insert into t_pf_emp_commitment (match_employee_id, commitment_type, from_dt,  to_dt,  leave_name,holiday_name , created_dt,  created_by,  is_enabled)
	select t_pf_emp_id as match_employee_id,'holiday' commitment_type,holiday.holiday_dt AS from_dt,holiday.holiday_dt as to_dt,'',holiday.holiday_name,CURRENT_TIMESTAMP created_dt,userId created_by, 'Y' is_enabled
	FROM s_holiday holiday 
	where holiday.holiday_dt >=fromDt and holiday.holiday_dt <= toDt  ;

	select job.map_location_id, job.id  into prevMapLocationId, prevJobId FROM t_job job 
	order by job.start_dt<fromDt DESC LIMIT 1 ;

	-- add any relevant job as commitment
	insert into t_pf_emp_commitment (match_employee_id, commitment_type, from_dt,  to_dt,  job_name, job_id,lats,longs,distance_to_location, created_dt,  created_by,  is_enabled)
	select t_pf_emp_id as match_employee_id,'JOB' commitment_type, job.start_dt as from_dt,job.end_dt as to_dt,job.job_name,job.id,jobLats,jobLongs,@distanceToLocation,now(),userId created_by,'Y' is_enabled
	FROM t_job_emp emp 
	LEFT JOIN t_job job ON  emp.t_job_id=job.id where emp.employee_id=employeeId 
	and emp.exp_start_dt>=fromDt and emp.exp_end_dt <=toDt 
	;

	-- update the match-emp record
	UPDATE t_pf_emp SET profile_finder_id=profileFinderId, m_employee_id=employeeId, designation_name=desigName, comptability_score=matchScore, distance_to_location=@distanceToLocation,  modified_by=userId, modified_dt=now(), prev_job_id=prevJobId, prev_map_location_id=prevMapLocationId, prev_lats=prevLats, prev_longs=prevLongs WHERE id=t_pf_emp_id;

END