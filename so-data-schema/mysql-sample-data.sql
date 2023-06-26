INSERT INTO smart_office.m_user
(id, app_client_id, app_token, created_by, created_dt, email_id, first_name, group_id, last_name, modified_by, modified_dt, password, token_validity_dt, user_access_dt, user_name, user_type, emp_code, emp_designation)
VALUES(1, 'pothigai-power', '69e406ea-8b11-4e1c-9965-69995493ae4b', NULL, '2018-03-26 17:48:59.000', NULL, 'dinesh', NULL, 'r', NULL, '2018-03-29 20:47:12.000', 'dinesh32', '2018-03-29 21:47:12.000', NULL, 'd@s.com', NULL, 'emp001', 'ENGINEER');
INSERT INTO smart_office.m_user
(id, app_client_id, app_token, created_by, created_dt, email_id, first_name, group_id, last_name, modified_by, modified_dt, password, token_validity_dt, user_access_dt, user_name, user_type, emp_code, emp_designation)
VALUES(3, 'pothigai-power', '4b81de6d-8459-444a-a217-976069d2d00a', NULL, '2018-03-29 21:17:04.000', 'lanka@pothigai-power.com', 'Lanka', '4', ' Purnachand', NULL, '2018-03-29 22:15:10.000', 'password@123', '2018-03-29 23:15:10.000', NULL, 'lanka@pothigai-power.com', 'INTERNAL', '1002', 'ENGINEER');
INSERT INTO smart_office.m_user
(id, app_client_id, app_token, created_by, created_dt, email_id, first_name, group_id, last_name, modified_by, modified_dt, password, token_validity_dt, user_access_dt, user_name, user_type, emp_code, emp_designation)
VALUES(4, 'pothigai-power', 'ad2d4e6c-baf3-4a8c-83a4-8c380a61b017', NULL, '2018-03-29 21:18:02.000', 'hemanathan@pothigai-power.com', 'Hemanathan', '4', 'R.S', NULL, '2018-03-29 23:32:14.000', 'password@123', '2018-03-30 00:32:14.000', NULL, 'hemanathan@pothigai-power.com', 'INTERNAL', '1003', 'ENGINEER');
INSERT INTO smart_office.m_user
(id, app_client_id, app_token, created_by, created_dt, email_id, first_name, group_id, last_name, modified_by, modified_dt, password, token_validity_dt, user_access_dt, user_name, user_type, emp_code, emp_designation)
VALUES(5, 'pothigai-power', NULL, NULL, '2018-03-29 21:18:43.000', 'loganathan@pothigai-power.com', 'Loganathan', '4', 'k', NULL, '2018-03-29 21:18:43.000', 'password@123', NULL, NULL, 'loganathan@pothigai-power.com', 'INTERNAL', '1016', 'ENGINEER');
INSERT INTO smart_office.m_user
(id, app_client_id, app_token, created_by, created_dt, email_id, first_name, group_id, last_name, modified_by, modified_dt, password, token_validity_dt, user_access_dt, user_name, user_type, emp_code, emp_designation)
VALUES(6, 'pothigai-power', NULL, NULL, '2018-03-29 21:19:16.000', 'ponraj@pothigai-power.com', 'Ponraj', '4', 'M', NULL, '2018-03-29 21:19:16.000', 'password@123', NULL, NULL, 'ponraj@pothigai-power.com', 'INTERNAL', '1017', 'ENGINEER');
INSERT INTO smart_office.m_user
(id, app_client_id, app_token, created_by, created_dt, email_id, first_name, group_id, last_name, modified_by, modified_dt, password, token_validity_dt, user_access_dt, user_name, user_type, emp_code, emp_designation)
VALUES(7, 'pothigai-power', NULL, NULL, '2018-03-29 21:25:29.000', 'sathish@pothigai-power.com', 'Sathish', '4', 'Kumar', NULL, '2018-03-29 21:25:29.000', 'password@123', NULL, NULL, 'sathish@pothigai-power.com', 'INTERNAL', '1017', 'ENGINEER');
INSERT INTO smart_office.m_user
(id, app_client_id, app_token, created_by, created_dt, email_id, first_name, group_id, last_name, modified_by, modified_dt, password, token_validity_dt, user_access_dt, user_name, user_type, emp_code, emp_designation)
VALUES(8, 'pothigai-power', NULL, NULL, '2018-03-29 21:40:18.000', 'kameshwaran@pothigai-power.com', 'Kameshwaran', '4', 'K', NULL, '2018-03-29 21:40:18.000', 'password@123', NULL, NULL, 'kameshwaran@pothigai-power.com', 'INTERNAL', '1007', 'SENIOR MANAGER');
INSERT INTO smart_office.m_user
(id, app_client_id, app_token, created_by, created_dt, email_id, first_name, group_id, last_name, modified_by, modified_dt, password, token_validity_dt, user_access_dt, user_name, user_type, emp_code, emp_designation)
VALUES(9, 'pothigai-power', NULL, NULL, '2018-03-29 21:41:38.000', 'gaurav@pothigai-power.com', 'Gaurav', '4', 'Chauhan', NULL, '2018-03-29 21:41:38.000', 'password@123', NULL, NULL, 'gaurav@pothigai-power.com', 'INTERNAL', '1028', 'SENIOR MANAGER');


INSERT INTO smart_office.t_payroll_hdr (t_payroll_batch_id,t_emp_pay_adj_id,m_emp_id,m_emp_pay_structure_id,`month`,`year`,emp_code,emp_fname,emp_lname,pf_no,emp_grade,bank_account_no,designation,dob,payment_mode,bank_ifsc_code,bank_name,bank_branch_name,total_ctc,total_earnings,total_deductions,net_salary,is_enabled,created_by,created_dt,modified_by,modified_dt,remarks) VALUES 
(4,1,1000,1,7,2012,'1000','test','test','1','1','1000','ENGINEER','2018-03-29 20:47:12.000','1','1','test','test',100,100,100,100,'Y','admin','2018-03-29 20:47:12.000','','2018-03-29 20:47:12.000',NULL)
,(4,2,2000,1,7,2012,'2000','test','test','1','2','2000','ENGINEER','2018-03-29 20:47:12.000','1','1','test','test',200,200,200,200,'Y','admin','2018-04-01 19:26:14.000','','2018-04-01 19:27:47.000',NULL)
;

INSERT INTO smart_office.t_payroll_dtl (t_payroll_hdr_id,entry_type,entry_code,entry_name,ytd,entry_amount,status_code,is_enabled,created_by,created_dt,modified_by,modified_dt,remarks) VALUES 
(1,'1','100','test',1,1,'11','1','1','2018-03-29 20:47:12.000',NULL,'2018-03-29 20:47:12.000','test')
,(3,'1','100','test',2,2,'22','2','1','2018-04-01 19:26:14.000',NULL,'2018-04-01 19:27:47.000','test')
;

