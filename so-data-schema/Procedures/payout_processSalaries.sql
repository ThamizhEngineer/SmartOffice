DROP PROCEDURE IF EXISTS `smart_office`.`payout_processSalaries`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_processSalaries`(in payoutCycleLineId  VARCHAR(25),in overWrite VARCHAR(1),out resultCode VARCHAR(25),out resultDesc VARCHAR(100))
BEGIN
	
	declare processId int;
	declare loopComplete BOOL default false;
	declare payCycleId int;
	declare employeeId int;
	declare processFromDt date;
	declare processToDt date;
	declare successCount int default 0;
	declare failureCount int default 0;
	-- declare empProcessResult varchar(200);
	-- employees should be eligible for pay during the processing period
 	 DECLARE eligibleEmpCur CURSOR FOR SELECT e.id employeeId from m_emp_ctc ctc join m_employee e on e.id = ctc.employee_id 
				where  ctc.valid_from_dt <= processFromDt and  ctc.valid_to_dt >= processToDt and ctc.is_active = 'Y';

 	DECLARE CONTINUE HANDLER FOR NOT FOUND SET loopComplete = TRUE;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='payout_processSalaries - Unknown Error';
			select resultDesc;
		END;

	if(overWrite != 'Y')THEN
		set overWrite = 'N';
	end if;

	SET resultCode='START';
	SET resultDesc='';
	
	-- input validation
	IF(payoutCycleLineId is null or payoutCycleLineId = '') THEN
		set resultCode = 'FAILURE';
		set resultDesc = 'Please provide payoutCycleLineId to process';
	
	END IF;
	
	insert into t_payout_process_hdr(pay_cycle_line_id, start_dt,process_status, over_write, created_dt) values (payoutCycleLineId  , now(), resultCode, overWrite, now());
		set processId = LAST_INSERT_ID(); 

	if(resultCode <> 'FAILURE') THEN
		select m_comp_pay_cycle_id,from_dt,to_dt into payCycleId, processFromDt, processToDt from m_comp_pay_cycle_line where is_enabled='Y' AND id = payoutCycleLineId;
		
		OPEN eligibleEmpCur;
		eligibleEmpLoop: LOOP
		FETCH NEXT FROM eligibleEmpCur INTO employeeId;
			IF loopComplete THEN 
				LEAVE  eligibleEmpLoop;
			END IF;
			insert into t_payout_process_line(t_payout_process_hdr_id,employee_id,result_code, result_desc, created_dt) values (processId ,employeeId , 'START', '', now());
			set @op1= '',@op2= '';
			call payout_processSalary(employeeId, payoutCycleLineId, LAST_INSERT_ID(), overWrite, @op1,@op2);
			select @op1;
			if(@op1 = 'SUCCESS') THEN
				set successCount = successCount + 1;
			ELSE	
				set failureCount = failureCount + 1;				
			end if;
		END LOOP ;
		set resultCode = 'SUCCESS';
	end if;

	update t_payout_process_hdr set pay_cycle_line_id = payoutCycleLineId , end_dt=now(), process_status=resultCode, 
			success_count = successCount, failure_count = failureCount
			where id = processId;
END