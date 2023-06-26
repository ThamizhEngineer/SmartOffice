select * from v_user_emp_app_role_feature vuearf ;

select auth_user_id ,auth_user_name , role_name,count(*) from v_user_emp_app_role_feature vuearf
group by auth_user_id ,auth_user_name , role_name
order by auth_user_id ,auth_user_name , role_name;


/**
 * create employee with minimal info by duplicating
 * create user, user-role
 * 
 * create one admin-account with required role 
 *  1 - jenkins - su
 *  2 - su		- su
 *  3 - dinesh	- su
 *  4 - admin1	- EMPLOYEE
 *  5 - admin2	- EMPLOYEE
 * emp, n1, n2, dir
 */



select * from auth_user au where app_token ='38d5765b-0e31-4ec3-b971-be0510fd27ff';

INSERT INTO auth_token
(id, app_token, employee_id, date_time, application_code, auth_user_id, token_validity_dt, last_logged_dt, duration)
VALUES(1861, '38d5765b-0e31-4ec3-b971-be0510fd27ff', '2', '2020-04-12 10:05:20', 'smart-office-mobile', 2, '2021-04-12 11:05:20', '2020-04-12 10:08:03', '0:57');
INSERT INTO auth_token
(id, app_token, employee_id, date_time, application_code, auth_user_id, token_validity_dt, last_logged_dt, duration)
VALUES(1862, '38d5765b-0e31-4ec3-b971-be0510fd27ff', '2', '2020-04-12 10:05:20', 'async', 2, '2021-04-12 11:05:20', '2020-04-12 10:08:03', '0:57');
 


