CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`clear_incident_orphan_records`()
BEGIN
declare conditionDate DATETIME DEFAULT (NOW() - INTERVAL 2 HOUR);
declare incTestCount int default 0;
declare incTestQuestionCount int default 0;
declare incTtCount int default 0;
declare incTtQuestionsCount int default 0;
declare incParpInstCount int default 0;
declare incApplicantCount int default 0;

	-- Start
	INSERT INTO smart_office.t_orphan_record_clear_log
	(process_type, process_name, primary_message, secondary_message, created_by, created_dt)
	VALUES('DB', 'clear_incident_orphan_records', CONCAT(CAST(NOW() AS CHAR),' - ',CAST('Start' AS CHAR)),'process-started', 'DB', now());
	

	-- Incident test Question
	select count(*) into incTestQuestionCount from t_incident_test_question titq  where titq.t_incident_test_id is null and (titq.created_dt  is null or titq.created_dt < conditionDate);
	if incTestQuestionCount>0 then
	INSERT INTO smart_office.t_orphan_record_clear_log
	(process_type, process_name, primary_message, secondary_message, created_by, created_dt)
	VALUES('DB', 'clear_incident_orphan_records', CONCAT(CAST(NOW() AS CHAR),' - ',CAST('t_incident_test_question' AS CHAR)), CONCAT(CAST(incTestQuestionCount AS CHAR),' - ',CAST('cont' AS CHAR)), 'DB', now());
	end if;

	-- Incident test
	select count(*) into incTestCount from t_incident_test titt where titt.t_incident_id is null and (titt.created_dt  is null or titt.created_dt < conditionDate);
	if incTestCount>0 then
	INSERT INTO smart_office.t_orphan_record_clear_log
	(process_type, process_name, primary_message, secondary_message, created_by, created_dt)
	VALUES('DB', 'clear_incident_orphan_records', CONCAT(CAST(NOW() AS CHAR),' - ',CAST('t_incident_test' AS CHAR)), CONCAT(CAST(incTestCount AS CHAR),' - ',CAST('cont' AS CHAR)), 'DB', now());
	delete from t_incident_test where t_incident_id is null and (created_dt  is null or created_dt < conditionDate);
	end if;

	-- Incident test template question
	select count(*) into incTtQuestionsCount from t_incident_tt_questions ttiq where ttiq.t_incident_tt_id is null and (ttiq.created_dt  is null or ttiq.created_dt < conditionDate);
	if incTtQuestionsCount>0 then
	INSERT INTO smart_office.t_orphan_record_clear_log
	(process_type, process_name, primary_message, secondary_message, created_by, created_dt)
	VALUES('DB', 'clear_incident_orphan_records', CONCAT(CAST(NOW() AS CHAR),' - ',CAST('t_incident_tt_questions' AS CHAR)), CONCAT(CAST(incTtQuestionsCount AS CHAR),' - ',CAST('cont' AS CHAR)), 'DB', now());
	delete from t_incident_tt_questions where t_incident_tt_id is null and (created_dt  is null or created_dt < conditionDate);
	end if;

	-- Incident test template
	select count(*) into incTtCount from t_incident_tt tit where tit.t_incident_id is null and (tit.created_dt  is null or tit.created_dt < conditionDate);
	if incTtCount>0 then
	INSERT INTO smart_office.t_orphan_record_clear_log
	(process_type, process_name, primary_message, secondary_message, created_by, created_dt)
	VALUES('DB', 'clear_incident_orphan_records', CONCAT(CAST(NOW() AS CHAR),' - ',CAST('t_incident_tt' AS CHAR)), CONCAT(CAST(incTtCount AS CHAR),' - ',CAST('cont' AS CHAR)), 'DB', now());
	delete from t_incident_tt where t_incident_id is null and (created_dt  is null or created_dt < conditionDate);
	end if;

	-- Incident test participtng institute
	select count(*) into incParpInstCount from t_participtng_instite tpi where tpi.t_incident_id is NULL and (tpi.created_dt  is null or tpi.created_dt < conditionDate);
	if incParpInstCount>0 then
	INSERT INTO smart_office.t_orphan_record_clear_log
	(process_type, process_name, primary_message, secondary_message, created_by, created_dt)
	VALUES('DB', 'clear_incident_orphan_records', CONCAT(CAST(NOW() AS CHAR),' - ',CAST('t_participtng_instite' AS CHAR)), CONCAT(CAST(incParpInstCount AS CHAR),' - ',CAST('cont' AS CHAR)), 'DB', now());
	delete from t_participtng_instite where t_incident_id is NULL and (created_dt  is null or created_dt < conditionDate);
	end if;

	-- Incident applicants
	select count(*) into incApplicantCount from t_incident_applicants tia where tia.t_incident_id is null and (tia.created_dt  is null or tia.created_dt < conditionDate);
	if incApplicantCount>0 then
	INSERT INTO smart_office.t_orphan_record_clear_log
	(process_type, process_name, primary_message, secondary_message, created_by, created_dt)
	VALUES('DB', 'clear_incident_orphan_records', CONCAT(CAST(NOW() AS CHAR),' - ',CAST('t_incident_applicants' AS CHAR)), CONCAT(CAST(incApplicantCount AS CHAR),' - ',CAST('cont' AS CHAR)), 'DB', now());
	delete from t_incident_applicants where t_incident_id is null and (created_dt  is null or created_dt < conditionDate);
	end if;

	-- End
	INSERT INTO smart_office.t_orphan_record_clear_log
	(process_type, process_name, primary_message, secondary_message, created_by, created_dt)
	VALUES('DB', 'clear_incident_orphan_records', CONCAT(CAST(NOW() AS CHAR),' - ',CAST('End' AS CHAR)),'process-ended', 'DB', now());
END;

