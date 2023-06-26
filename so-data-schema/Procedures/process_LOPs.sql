DROP PROCEDURE IF EXISTS `smart_office`.`process_LOPs`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`process_LOPs`(out resultCode VARCHAR(25), out resultDesc VARCHAR(100))
BEGIN
	DECLARE v_employeeId varchar(100) DEFAULT "";
	declare loopComplete BOOL default false;
	declare leaveStatus VARCHAR(200) DEFAULT"";
	declare leaveCode VARCHAR(200) DEFAULT"";
	declare leaveApplnDt VARCHAR(200) DEFAULT"";
	DECLARE absEmp cursor for select employee_id v_employeeId from t_attendance 
	where first_session ='ABS' and second_session='ABS' and attendance_dt = CURDATE();
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET loopComplete = TRUE;	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='process_LOPs- Unknown Error';
			select resultDesc;
		 END;
    OPEN absEmp;

	employees: LOOP
	FETCH NEXT FROM absEmp INTO v_employeeId;
      IF loopComplete THEN 
		LEAVE  employees;
		END IF;

      select  leave_status into leaveStatus from t_emp_leave_appln WHERE employee_id=v_employeeId;
     select v_employeeId;
	select leave_appln_dt  into leaveApplnDt from t_emp_leave_appln  where employee_id=v_employeeId and leave_appln_dt=CURDATE();

		if(leaveStatus is not null && leaveStatus='APPROVED'&&leaveApplnDt=CURDATE())THEN 
    	select s.leave_type_code into leaveCode
 		from t_emp_leave_appln l 
		join s_leave_type s  on l.leave_type_id=s.id where employee_id=v_employeeId;
    	INSERT INTO t_attendance (employee_id, attendance_dt, first_session, second_session, attendance_status, remarks, start_dt, end_dt, is_enabled, created_by, modified_by, created_dt, modified_dt, proxy_employee_id, lats, longs, attendance_month, attendance_year, check_in, check_out, total_time_logged) 
    	VALUES(v_employeeId, now(), leaveCode, leaveCode, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
	ELSE	
		INSERT INTO t_attendance (employee_id, attendance_dt, first_session, second_session, attendance_status, remarks, start_dt, end_dt, is_enabled, created_by, modified_by, created_dt, modified_dt, proxy_employee_id, lats, longs, attendance_month, attendance_year, check_in, check_out, total_time_logged) 
    		VALUES(v_employeeId, now(), 'LOP', 'LOP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
	end if;
     END LOOP employees ;



   close absEmp;

END
