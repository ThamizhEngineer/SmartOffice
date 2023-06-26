DROP PROCEDURE IF EXISTS `smart_office`.`payout_finalisePayoutHeader`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_finalisePayoutHeader`(in empPayoutHdrId  VARCHAR(25), out resultCode VARCHAR(25), out resultDesc VARCHAR(100))
BEGIN
	DECLARE totalAllowance, totalDeduction, netPayAmout, finalPayout, ytdTotalAllowance, ytdTotalDeduction double default 0;
	declare employeeId varchar(25) ;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='payout_finalisePayoutHeader - Unknown Error';
			select resultDesc;
		END;
	SET resultCode='START';
	
	select employee_id into employeeId from t_emp_payout where id = empPayoutHdrId;

	select sum(line_amt) into totalAllowance from t_emp_payout_line pl join t_emp_payout p on p.id = pl.m_emp_payout_id
		where p.id = empPayoutHdrId and pl.is_allowance='Y';

	-- TODO - this if could be replaced with a built-in null function.
		if(totalAllowance is null) 
		then 
			set totalAllowance = 0; 
		end if;

	
	select sum(line_amt) into totalDeduction from t_emp_payout_line pl join t_emp_payout p on p.id = pl.m_emp_payout_id
		where p.id = empPayoutHdrId and pl.is_allowance='N';
	-- TODO - this if could be replaced with a built-in null function.
		if(totalDeduction is null) 
		then 
			set totalDeduction = 0; 
		end if;

	select sum(total_allowance_amt) , sum(total_deduction_amt) into ytdTotalAllowance, ytdTotalDeduction from  t_emp_payout p where p.employee_id = employeeId and p.id <> empPayoutHdrId;
	-- TODO - this if could be replaced with a built-in null function.
		if(ytdTotalAllowance is null) 
		then 
			set ytdTotalAllowance = 0; 
		end if;
	
	-- TODO - this if could be replaced with a built-in null function.
		if(ytdTotalDeduction is null) 
		then 
			set ytdTotalDeduction = 0; 
		end if;

	set ytdTotalAllowance = ytdTotalAllowance + totalAllowance;
	set ytdTotalDeduction = ytdTotalDeduction + totalDeduction;
	set netPayAmout= (totalAllowance+totalDeduction);
	update t_emp_payout set total_allowance_amt = totalAllowance, total_deduction_amt= totalDeduction,net_pay_amt= netPayAmout, ytd_allowance_amt=ytdTotalAllowance, ytd_deduction_amt=ytdTotalDeduction
	where id = empPayoutHdrId;
	-- select empPayoutHdrId;
	
	 set resultCode = 'SUCCESS';
END