package com.ss.smartoffice.soservice.transaction.employeeservicerecord;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.springframework.context.annotation.Scope;

import com.ss.smartoffice.shared.model.employee.PreviousEmploymentDetails;

import lombok.Data;

@Entity
@Table(name="t_esr_hdr")
@Data
@Scope("prototype")
public class EmployeeServiceHeader {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_emp_id")
	private String mEmpId;
	private String officeId;
	private String esrTypeCode;
	private String esrDt;
	private String recordData;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private String createdDt;
	private String modifiedDt;
	
	@Formula("(SELECT emp.emp_code FROM m_employee emp WHERE emp.id= m_emp_id)")
	private String empCode;
	@Formula("(SELECT emp.first_name FROM m_employee emp WHERE emp.id= m_emp_id)")
	private String empFname;
	@Formula("(SELECT emp.last_name FROM m_employee emp WHERE emp.id= m_emp_id)")
	private String empLname;
//	select office_name from m_office left join m_employee on employee_id="4" where internal_id="100423";
//	@Formula("(select off.office_name from m_office off left join m_employee emp on off.employee_id=emp.id where emp.internal_id=internal_id)")
//	@Formula("(SELECT off.office_name FROM m_office off WHERE off.id= office_id)")
//	@Formula("(select off.office_name from m_office off left join m_employee emp on off.employee_id=emp.id where emp.id=m_emp_id)")
//	@Formula("(select office_name from m_employee left join m_office on m_office.id=m_employee.m_office_id where m_employee.id=m_emp_id)")
	@Formula("(select off.office_name from m_office off left join m_employee emp on off.id=emp.m_office_id where emp.id=m_emp_id)")
	private String officeName;
	@Formula("(SELECT emp.internal_id FROM m_employee emp WHERE emp.id= m_emp_id)")
	private String internalId;
	@Formula("(SELECT emp.m_office_id FROM m_employee emp WHERE emp.id= m_emp_id)")
	private String mOfficeId;
	@Formula("(SELECT emp.n1_emp_id FROM m_employee emp WHERE emp.id= m_emp_id)")
	private String n1EmpId;
	@Formula("(SELECT emp.n2_emp_id FROM m_employee emp WHERE emp.id= m_emp_id)")
	private String n2EmpId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id in (select emp1.n1_emp_id from m_employee emp1 where emp1.id=m_emp_id))")
	private String n1EmpName;
	@Formula("(select emp.emp_name from m_employee emp where emp.id in (select emp1.n2_emp_id from m_employee emp1 where emp1.id=m_emp_id))")
	private String n2EmpName;
	
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_esr_hdr_id")
	private List<EmployeeServiceLine> employeeServiceLine;
}
