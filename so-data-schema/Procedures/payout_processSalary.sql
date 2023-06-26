DROP PROCEDURE IF EXISTS `smart_office`.`payout_processSalary`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_processSalary`(in employeeId  VARCHAR(100),in payoutCycleLineId VARCHAR(25),in processLineId varchar(50),in overWrite VARCHAR(1), out resultCode VARCHAR(25), out resultDesc VARCHAR(100))
BEGIN
	
	declare payoutHdrId int default 0;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='payout_processSalary - Unknown Error';
			select resultDesc;
		END;
	/*begin
		GET DIAGNOSTICS CONDITION 2 @sqlstate = RETURNED_SQLSTATE, @errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
		select concat('Error ',@sqlstate,': ',@text);
	end;*/
	SET resultCode='START';
	SET resultDesc='';

	-- select employeeId, payoutCycleLineId;

	-- input validation
	IF(payoutCycleLineId is null or payoutCycleLineId = '' or employeeId is null or employeeId = '') THEN		
			set resultCode = 'FAILURE';
			set resultDesc = 'Please provide employeeId and payoutCycleLineId to start process';
	END IF;

	
	if(resultCode <>'FAILURE') THEN
		if(overWrite = 'Y') THEN
			select id into payoutHdrId from t_emp_payout where employee_id = employeeId and comp_pay_cycle_id = payoutCycleLineId;
			delete from t_emp_payout_line where m_emp_payout_id = payoutHdrId;
			delete from t_emp_payout where id = payoutHdrId;
		end if;

		-- insert basic entry in payout header
		set @opp1 = ''; 
		set @opp2 = '';
		call payout_initPayoutHeader(processLineId, @opp1,@opp2, @opp3);
		set resultCode =  @opp1; 
		set resultDesc = @opp2; 
		set payoutHdrId = @opp3;
		 select resultCode;

		select '@opp1-'||@opp1; 
			-- update the header with epfConfig, esiConfig, incomeTaxConfig data
			
			-- call to calculate totalWorkingDays, daysWorked, daysAbsent
			
			-- calculate and insert lines
		if(resultCode<>'FAILURE') THEN
			set @op1= '',@op2= ''; 
			call payout_insertPayoutLines(payoutHdrId, @op1,@op2);
			set resultCode =  @op1; set resultDesc = @op2;
		end if; 
		-- select resultCode;
 
			-- finalise payout header - update Header with totals 	 
		if(resultCode<>'FAILURE') THEN
			set @op1= '',@op2= ''; 
			call payout_finalisePayoutHeader(payoutHdrId, @op1,@op2);
			set resultCode =  @op1; set resultDesc = @op2;
		end if;
		-- select resultCode;

		
	end if;
	update t_payout_process_line set result_code = resultCode, result_desc = resultDesc, modified_dt = now() where id = processLineId;

	 -- select processResult;
END