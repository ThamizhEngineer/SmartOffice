DROP PROCEDURE IF EXISTS `smart_office`.`payout_initPayoutHeader`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`payout_initPayoutHeader`(in processLineId  VARCHAR(25), out resultCode VARCHAR(100), out resultDesc VARCHAR(200), OUT payoutHeaderId varchar(100))
BEGIN
	
	declare employeeId, payCycleLineId varchar(25) default '';
	declare payCycleLineFromDt, payCycleLineToDt, payDt date;
	declare payCycleType, payCycleId varchar(25) default '';
	declare employeeCode,pfNo,esiNumber,panNumber,dob,emailId, gradeId, designationId, gradeCode, gradeName , designationName varchar(50) default '';
	declare bankName, bankAccountNumber, bankIfscCode varchar(50) default '';
	declare fixedBenefits,empCtcId varchar(25) default '';
	declare salaryForMonth varchar(25) default '';
	declare salaryForYear varchar(25) default '2018';

	DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			set resultCode='FAILURE';
			set resultDesc='payout_initPayoutHeader - Unknown Error';
			select resultDesc;
		END;
	SET resultCode='START';

	select ppl.employee_id, pph.pay_cycle_line_id 
	into employeeId, payCycleLineId  
	from t_payout_process_line ppl
	join t_payout_process_hdr pph on pph.id=ppl.t_payout_process_hdr_id where ppl.id = processLineId;
 
	select from_dt, to_dt, pay_dt, pay_cycle_type, m_comp_pay_cycle_id
	into payCycleLineFromDt, payCycleLineToDt, payDt, payCycleType, payCycleId
	from v_comp_pay_cycle_line where id = payCycleLineId;
	-- payCycleType might be used later when payout header for site-allowance and expense-claim is developed.

	SELECT  e.emp_code,e.epf_no,e.esi_no,e.pan_no,e.dob,e.email_id , e.grade_id, e.designation_id, g.grade_code, g.grade_name , d.desig_name
		 into employeeCode,pfNo,esiNumber,panNumber,dob,emailId, gradeId, designationId, gradeCode, gradeName , designationName
		from m_employee e 
		join s_grade g on g.id = e.grade_id 
		join s_designation d on d.id = e.designation_id 
		where e.id= employeeId;
	
	SELECT bank_name , b.acc_no, b.ifsc_code
		 INTO bankName, bankAccountNumber, bankIfscCode 
		 from m_emp_bank_details b WHERE m_employee_id= employeeId limit 1;

	select fixed_benefits, id into fixedBenefits, empCtcId from m_emp_ctc where employee_id=employeeId and  valid_from_dt <= payCycleLineFromDt and valid_to_dt >= payCycleLineFromDt ;

	-- fixedBenefits is necessary for any salary calculation to happen
	-- hence exit if this doesnt exist.
	if(fixedBenefits is null or fixedBenefits ='' or fixedBenefits = '0')
	THEN
		set resultCode = 'FAILURE';
		set resultDesc = 'Fixed Benefits not defined';
	end if;
	if(resultCode != 'FAILURE') then
		-- TODO - have to calculate salaryForMonth and salaryForYear
		INSERT INTO t_emp_payout(employee_id,employee_code,from_dt,to_dt,pay_dt,d_pf_no,d_account_number,d_designation,d_esi_number,
								d_pan_number,dob,d_email,comp_pay_cycle_id,d_grade,d_bank_name,
								d_salary_for_month,d_salary_for_year, created_dt, t_payout_process_line_id, fixed_benefits, m_emp_ctc_id) 
						VALUES(employeeId, employeeCode,payCycleLineFromDt, payCycleLineToDt, payDt,pfNo, bankAccountNumber,designationName,esiNumber,
							  panNumber,dob,emailId, payCycleId, gradeName, bankName,
								'','', now(), processLineId,fixedBenefits,empCtcId);
		set payoutHeaderId = LAST_INSERT_ID();	
		set resultCode = 'SUCCESS';
	end if;
END