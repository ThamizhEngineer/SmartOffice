DROP PROCEDURE IF EXISTS `smart_office`.`payout_execFormula`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_execFormula`(in fixedBenefits  VARCHAR(25),in formula VARCHAR(100), out payoutValue VARCHAR(100), out resultCode VARCHAR(25), out resultDesc VARCHAR(100))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='payout_execFormula - Unknown Error';
			select resultDesc;
		END;
		
	SET @output1='0';	
	set formula = replace(formula, '[FB]',fixedBenefits );
	call evalExp(formula, @output1);
	set payoutValue = @output1;

END