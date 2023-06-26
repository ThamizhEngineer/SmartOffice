CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_createProcess`(in payoutCycleLineId  VARCHAR(25),in employeeId VARCHAR(25),out resultCode VARCHAR(25),out resultDesc VARCHAR(100))
BEGIN
declare processId int;
declare payCyclelineId int;
declare successCount int default 0;
declare failureCount int default 0;
declare overWrite VARCHAR(1);
DECLARE employeId int;


DECLARE v_employeeId1 varchar(100) DEFAULT "";

declare loopComplete BOOL default false;



DECLARE cur1 cursor for SELECT id v_employeeId1 from m_employee ;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET loopComplete = TRUE;



DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN
set resultCode='FAILURE';
set resultDesc='payout_createProcess - Unknown Error';
select resultDesc;
END;

SET resultCode='START';
	
	-- input validation
IF(payoutCycleLineId is null or payoutCycleLineId = '') THEN

set resultCode = 'FAILURE';
set resultDesc = 'Please provide payoutCycleLineId to process';

-- 	IF EMPLOYEE ID IS GIVEN
ELSEIF employeeId is not NULL and employeeId!='' THEN

SET resultCode='SUCCESS';
set resultDesc='Process Started for the Given Employee';
set successCount = successCount + 1;
SET overWrite='Y';
INSERT INTO t_payout_process_hdr (pay_cycle_line_id, pay_cycle_line_code, start_dt, end_dt, process_status, success_count, failure_count, over_write, created_by, modified_by, created_dt, modified_dt, is_enabled, pay_cycle_type_code) 
VALUES(payoutCycleLineId, "", now(), now(), resultCode, successCount, failureCount, overWrite, NULL, NULL, NULL, NULL, 'Y', "REGULAR-SALARY");
set processId = LAST_INSERT_ID();

select id into employeId from m_employee where id=employeeId;


INSERT INTO t_payout_process_line (t_payout_process_hdr_id, employee_id, result_code, result_desc, is_enabled, created_by, modified_by, created_dt, modified_dt) 
VALUES(processId, employeId, resultCode, resultDesc, 'Y', NULL, NULL, now(), now());



-- IF EMPLOYEEID NOT GIVEN
ELSE
set resultCode='SUCCESS';
set resultDesc='Process Started for all Employees';
set successCount = successCount + 1;
SET overWrite='N';

INSERT INTO t_payout_process_hdr (pay_cycle_line_id, pay_cycle_line_code, start_dt, end_dt, process_status, success_count, failure_count, over_write, created_by, modified_by, created_dt, modified_dt, is_enabled, pay_cycle_type_code) 
VALUES(payoutCycleLineId, "", now(), now(), resultCode, successCount, failureCount, 'N', NULL, NULL, NULL, NULL, 'Y', "REGULAR-SALARY");
set processId = LAST_INSERT_ID();

 
OPEN cur1;
-- 
read_loop: LOOP
-- 
FETCH NEXT FROM cur1 INTO v_employeeId1;

 IF loopComplete THEN 
 LEAVE  read_loop;
 END IF;

 INSERT INTO t_payout_process_line (t_payout_process_hdr_id, employee_id, result_code, result_desc, created_by, modified_by, created_dt, modified_dt, is_enabled) 
 VALUES(processId, v_employeeId1 , resultCode, resultDesc, "", "", now(), now(), 'Y');

 END LOOP read_loop ;

close cur1;
-- 


END IF;

END
