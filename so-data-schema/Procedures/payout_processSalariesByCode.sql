DROP PROCEDURE IF EXISTS `smart_office`.`payout_processSalariesByCode`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_processSalariesByCode`(in payoutCycleLineCode VARCHAR(25),in overWrite VARCHAR(1),out resultCode VARCHAR(25), out resultDesc VARCHAR(100))
BEGIN
	 
	declare payoutCycleLineId int ;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='payout_processSalariesByCode - Unknown Error';
			select resultDesc;
		END;

	SET resultCode='START';
	
	-- input validation

	IF(payoutCycleLineCode is null or payoutCycleLineCode = '') THEN 
		set resultCode = 'FAILURE';
		set resultDesc = 'Please provide payoutCycleLineCode to process';
	ELSE
		select id into payoutCycleLineId from m_comp_pay_cycle_line where comp_pay_cycle_line_code = payoutCycleLineCode;
		IF(payoutCycleLineId is null or payoutCycleLineId = '') THEN
			set resultCode = 'FAILURE';
			set resultDesc = 'Please provide valid payoutCycleLineCode';
		ELSE
			set @op1 = '',@op2 = '';
			call payout_processSalaries(payoutCycleLineId, overWrite, @op1, @op2);
			SET resultCode =  @op1;
			SET resultDesc =  @op2;
		end if;
	END IF;
	
	if(resultCode<>'FAILURE')
	THEN
		set resultCode = 'SUCCESS'; 
	end if;
	
END