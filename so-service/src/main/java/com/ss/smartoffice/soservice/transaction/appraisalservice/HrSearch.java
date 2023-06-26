package com.ss.smartoffice.soservice.transaction.appraisalservice;

import javax.persistence.Column;

import org.hibernate.annotations.Formula;

import lombok.Data;
@Data
public class HrSearch {
	@Column(name="m_emp_id")
	private String empId;
	@Formula("(select m.emp_name from m_employee m where m.id=m_emp_id)")
	private String empName;
	@Column(name="fy_id")
	private String fyId;
	@Column(name="n1_emp_id")
	private String n1EmpId;
	@Column(name="n2_emp_id")
	private String n2EmpId;
	@Formula("(select fyYear.fiscal_code from s_fiscal_year fyYear WHERE fyYear.id=fy_id)")
	private String fyCode;
	private String officeId;
	@Formula("(select office.office_name from m_office office where office.id=office_id)")
	private String officeName;
	@Formula("(select m.emp_name from m_employee m where m.id=n1_emp_id)")
	private String n1EmpName;
	@Formula("(select m.emp_name from m_employee m where m.id=n2_emp_id)")
	private String n2EmpName;
	private String designationId;
	@Formula("(select design.desig_name from s_designation design where design.id=designation_id)")
	private String designationName;
	private  String settleEmpId;
	@Formula("(select emp.first_name from m_employee emp where emp.id=settle_emp_id)")
	private  String settleEmpName;
	
}
