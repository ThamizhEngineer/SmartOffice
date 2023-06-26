DROP PROCEDURE IF EXISTS `smart_office`.`payout_startProcessById`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_startProcessById`(in payoutProcessId VARCHAR(25),in overWrite VARCHAR(1),out resultCode VARCHAR(25), out resultDesc VARCHAR(100))
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

	IF(payoutProcessId is null or payoutProcessId = '') THEN 
		set resultCode = 'FAILURE';
		set resultDesc = 'Please provide payoutProcessId to process';
	
	END IF;
	
	if(resultCode<>'FAILURE')
	THEN
		set resultCode = 'SUCCESS'; 
	end if;
	
END