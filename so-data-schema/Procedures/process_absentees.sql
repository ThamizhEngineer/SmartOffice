DROP PROCEDURE IF EXISTS `smart_office`.`process_absentees`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`process_absentees`(out resultCode VARCHAR(25), out resultDesc VARCHAR(100))
BEGIN
	 
DECLARE v_employeeId varchar(100) DEFAULT "";

declare firstSession VARCHAR(200) DEFAULT"";
DECLARE secondSession VARCHAR(200) DEFAULT"";
DECLARE attendanceDt  VARCHAR(200) DEFAULT"";
declare leaveStatus VARCHAR(200) DEFAULT"";
declare leaveCode VARCHAR(200) DEFAULT"";
declare leaveApplnDt VARCHAR(200) DEFAULT"";
declare employeeId VARCHAR(200) DEFAULT"";
declare loopComplete BOOL default false;
declare leaveAppliedloop BOOL default false;
DECLARE employeeswithoutattendance cursor for select id v_employeeId from t_attendance where first_session is null and second_session is null and attendance_dt = CURDATE();
DECLARE CONTINUE HANDLER FOR NOT FOUND SET loopComplete = TRUE;	
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='process_absentees- Unknown Error';
			select resultDesc;
		 END;

  SET resultCode='START';
	
	OPEN employeeswithoutattendance;
	employees: LOOP
	FETCH NEXT FROM employeeswithoutattendance INTO v_employeeId;
		IF loopComplete THEN 
		LEAVE  employees;
		END IF;
   	
	select employee_id into employeeId from t_attendance where id=v_employeeId;
SELECT employeeId;
    select leave_appln_dt into leaveApplnDt from t_emp_leave_appln where leave_appln_dt=CURDATE();
    select  leave_status into leaveStatus from t_emp_leave_appln WHERE employee_id=employeeId;
   
    if(leaveStatus is not null && leaveStatus='APPROVED'&&leaveApplnDt=CURDATE())THEN 
    	select s.leave_type_code into leaveCode
 		from t_emp_leave_appln l 
		join s_leave_type s  on l.leave_type_id=s.id where employee_id=employeeId;
    	INSERT INTO t_attendance (employee_id, attendance_dt, first_session, second_session, attendance_status, remarks, start_dt, end_dt, is_enabled, created_by, modified_by, created_dt, modified_dt, proxy_employee_id, lats, longs, attendance_month, attendance_year, check_in, check_out, total_time_logged) 
    	VALUES(employeeId, now(), leaveCode, leaveCode, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
	ELSE
			INSERT INTO t_attendance (employee_id, attendance_dt, first_session, second_session, attendance_status, remarks, start_dt, end_dt, is_enabled, created_by, modified_by, created_dt, modified_dt, proxy_employee_id, lats, longs, attendance_month, attendance_year, check_in, check_out, total_time_logged) 
    		VALUES(employeeId, now(), 'ABS', 'ABS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
end if;

END LOOP employees ;



   close employeeswithoutattendance;
	
END
