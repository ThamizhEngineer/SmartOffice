DROP PROCEDURE IF EXISTS `smart_office`.`payout_insertPayoutLines`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_insertPayoutLines`(in empPayoutHdrId  VARCHAR(25), out resultCode VARCHAR(25), out resultDesc VARCHAR(100))
BEGIN
	
	declare process BOOL default true;
	declare loopComplete BOOL default false; 
	declare lineOrder,lineAmt,ytdAmount double default 0;
	declare employeeId, empCtcId,payCycleId varchar(25) default '';
	declare payoutFromDate date;
	-- please note - for some wierd reason the variable 'formula' didnt hold the expression. on renaming to 'vformula' it works.
	declare isAllowance, payTypeCode, payTypeName,sPayoutTypeId, fixedBenefits, isLumpSum, unitCost, maxLimit, vformula  varchar(50)  default null ;
	
	-- employees should be eligible for pay during the processing period
 	 DECLARE applicablePayoutTypesCur CURSOR FOR 
					SELECT is_allowance, pay_type_code, pay_type_name,s_payout_type_id, fixed_benefits, is_lump_sum, 
					unit_cost, max_limit, formula 
					from v_emp_ctc_line where employee_id = employeeId and m_emp_ctc_id = empCtcId and pay_cycle_id = payCycleId order by process_order;

 	DECLARE CONTINUE HANDLER FOR NOT FOUND SET loopComplete = TRUE;
	/*
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='payout_insertPayoutLines - Unknown Error';
			select resultDesc;
		END;
		*/
	SET resultCode='START';
	SET resultDesc='';

	select 'payout_insertPayoutLines -'||resultCode;
	select employee_id,m_emp_ctc_id, comp_pay_cycle_id, from_dt INTO employeeId, empCtcId, payCycleId, payoutFromDate from t_emp_payout where id = empPayoutHdrId;
	
	set loopComplete = FALSE;
	OPEN applicablePayoutTypesCur;
	applicablePayoutTypesLoop: LOOP
	FETCH NEXT FROM applicablePayoutTypesCur INTO isAllowance, payTypeCode, payTypeName,sPayoutTypeId, fixedBenefits, isLumpSum, unitCost, maxLimit, vformula ;
		IF loopComplete THEN 
			LEAVE  applicablePayoutTypesLoop;
		END IF;
		set resultCode = '';
		-- data check
		if(isLumpSum = 'N') then
			if(fixedBenefits is null or fixedBenefits ='' or vformula is null or vformula ='') THEN
				set resultCode ='FAILURE';
				set resultDesc =payTypeCode||' - FixedBenefits or formula not set';
				select resultDesc; 
				LEAVE  applicablePayoutTypesLoop;
			end if;
		ELSE
			if(unitCost is null or unitCost ='') THEN
				set resultCode ='FAILURE';
				set resultDesc =payTypeCode||' - unitCost not set';
				select resultDesc;
				LEAVE  applicablePayoutTypesLoop;
			end if;
		end if;

		if(isLumpSum = 'N') then
			set @op1 = '',@op2 = '',@op3 = '';
			call payout_execFormula(fixedBenefits, vformula, @op1, @op2, @op3);
			set lineAmt = @op1; 
			set resultCode =  @op2; set resultDesc = @op3;
		ELSE
			set lineAmt = unitCost;
		end if;

		IF resultCode <> 'FAILURE' THEN 		
			select sum(line_amt) into ytdAmount from t_emp_payout_line pl join t_emp_payout p on p.id = pl.m_emp_payout_id
			where p.employee_id = employeeId and pl.s_payout_type_id = sPayoutTypeId and p.to_dt < payoutFromDate;
			
			-- TODO - this if could be replaced with a built-in null function.
			if(ytdAmount is null) 
			then 
				set ytdAmount = 0; 
			end if;
	
			set ytdAmount = ytdAmount + lineAmt;
	
			set lineOrder = lineOrder +1;
			insert into t_emp_payout_line( m_emp_payout_id, line_order, is_allowance, d_pay_type_code, d_pay_type_name, s_payout_type_id, created_dt, is_enabled, line_amt, ytd_amt )
									values (empPayoutHdrId,lineOrder, isAllowance, payTypeCode, payTypeName,sPayoutTypeId,now(),'Y', lineAmt, ytdAmount);
			set resultCode ='SUCCESS';
		END IF;

	END LOOP ;
	IF resultCode <> 'FAILURE' THEN 
		set resultCode = 'SUCCESS';
	END IF;
	 -- select resultCode;
END