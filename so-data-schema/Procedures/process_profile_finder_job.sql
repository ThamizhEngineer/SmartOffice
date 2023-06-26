CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`process_profile_finder_job`(in profileJobId VARCHAR(25) ,in userId VARCHAR(100), out resultCode VARCHAR(25),out resultDesc VARCHAR(100))
BEGIN
declare successCount int default 0;
declare failureCount int default 0;

declare employeeId varchar(100);
declare fromDt DATE;
declare toDt DATE;
declare profileId varchar(100);
declare deptId varchar(100);
declare buId varchar(100);
declare location varchar(100);
declare v_deptId varchar(100) DEFAULT "";
declare v_employeeId1 varchar(100) DEFAULT "";
declare v_pfEmpId varchar(100) DEFAULT "";
declare v_pfEmpEmpId varchar(100) DEFAULT "";
declare loopComplete BOOL default false;

DECLARE cur_empsFromDept cursor for select  e.id  from m_employee e where e.dept_id =  deptId ;
DECLARE cur_pfEmployeesForDel cursor for select tpe.id,tpe.m_employee_id  FROM t_pf_emp tpe where tpe.profile_finder_id=profileJobId;
DECLARE cur_pfEmployees cursor for select tpe.id,tpe.m_employee_id  FROM t_pf_emp tpe where tpe.profile_finder_id=profileJobId;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET loopComplete = TRUE;


DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
	set resultCode='FAILURE';
	set resultDesc='process_profile_finder_job - Unknown Error';
	GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, 
	 @errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
	SET @full_error = CONCAT("ERROR ", @errno, " (", @sqlstate, "): ", @text);
	SELECT @full_error;
	select resultDesc;
	END;
-- PROCESS_BLOCK:BEGIN
SET resultCode='START';

-- If profileJobId is null
IF(profileJobId is null or profileJobId = '') THEN
	set resultCode = 'FAILURE';
	set resultDesc = 'Please provide profileJobId to process';
-- If profileJobId is not null
ELSEIF profileJobId is not NULL and profileJobId!='' THEN
	SET resultCode='SUCCESS';
	set resultDesc='Process started for given profile id';
	set successCount = successCount + 1;

	UPDATE t_pf_job job SET job.status='INPROGRESS' where job.id=profileJobId;

	select  job.m_employee_id,job.m_department_id,job.m_profile_id,job.from_dt,job.to_dt,job.map_location_id  
	into employeeId,deptId,profileId,fromDt,toDt,location from t_pf_job job where job.id=profileJobId;

	set loopComplete=false;
	OPEN cur_pfEmployeesForDel;
	existing_emp_loop: LOOP
		FETCH NEXT FROM cur_pfEmployeesForDel INTO v_pfEmpId,v_pfEmpEmpId;
		IF loopComplete THEN 
			LEAVE  existing_emp_loop;
		END IF;
		delete from t_pf_emp_skill where match_employee_id = v_pfEmpId;
		delete from t_pf_emp_commitment where match_employee_id = v_pfEmpId;
		delete from t_pf_emp where id = v_pfEmpId;

	END LOOP existing_emp_loop ;
	CLOSE cur_pfEmployeesForDel;

end if;

-- If deptId is not null
 IF deptId is not NULL and deptId!='' THEN
	SET resultCode='SUCCESS';
	set resultDesc='Process started for given deptId ';
	OPEN cur_empsFromDept;

	set loopComplete=false;
	read_loop: LOOP
		FETCH NEXT FROM cur_empsFromDept INTO v_employeeId1;
		IF loopComplete THEN 
			LEAVE  read_loop;
		END IF;
	

		INSERT INTO t_pf_emp ( profile_finder_id, m_employee_id,   is_enabled, created_by,created_dt) 
		VALUES( profileJobId, v_employeeId1, 'Y', userId, now());
	END LOOP read_loop ;

	close cur_empsFromDept;

ELSEIF employeeId is not NULL and employeeId!='' THEN

	SET resultCode='SUCCESS';
	set resultDesc='Process started for given employee id';
	INSERT INTO t_pf_emp ( profile_finder_id, m_employee_id,   is_enabled, created_by,created_dt) 
	VALUES( profileJobId, employeeId, 'Y', userId, now());
	
ELSE
	set resultCode='ERROR';
	set resultDesc='EmployeeId or DepartmentId is mandatory’';
end if;

select 'next';
IF resultCode !='ERROR' THEN
	-- for each employee, match the skills expected in the profile
	set loopComplete=false;
	OPEN cur_pfEmployees;
	match_employee_skill_loop: LOOP
		FETCH NEXT FROM cur_pfEmployees INTO v_pfEmpId,v_pfEmpEmpId;

		IF loopComplete THEN 
		LEAVE  match_employee_skill_loop;
		END IF;
		call match_employee_skill(profileJobId,profileId, fromDt,toDt, 
			location, v_pfEmpId,v_pfEmpEmpId,userId,@exp1,@exp2);

	END LOOP match_employee_skill_loop ;
	CLOSE cur_pfEmployees;

	SET resultCode='COMPLETED';
	set resultDesc='Processed the profile finder job';
END IF;

	UPDATE t_pf_job job SET job.status=resultCode, job.remarks = resultDesc, modified_by=userId, modified_dt=now() where job.id=profileJobId;

END