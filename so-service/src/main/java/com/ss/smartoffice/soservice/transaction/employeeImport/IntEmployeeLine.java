package com.ss.smartoffice.soservice.transaction.employeeImport;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity

@Table(name = "i_employee_line")
public class IntEmployeeLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String employeeHdrId;  
	private String empCode;
	private String firstName;
	private String lastName;
	private String status;
	private String mobileNo;
	private String dob;
	private String doj;
	private String email;
	private String sex;
	private String designationId;
	private String designation;
	@Column(name = "n1_emp_id")
	private String n1EmpId;
	
	@Column(name = "n1_emp_code")
	private String n1EmpCode;
	
	@Column(name = "n2_emp_id")
	private String n2EmpId;
	
	@Column(name = "n2_emp_code")
	private String n2EmpCode;
	
	@Column(name = "hr1_grp_id")
	private String hr1GrpId;
	
	@Column(name = "hr1_grp_code")
	private String hr1GrpCode;
	
	@Column(name = "hr2_grp_id")
	private String hr2GrpId;
	
	@Column(name = "hr2_grp_code")
	private String hr2GrpCode;
	
	@Column(name = "acc1_grp_id")
	private String acc1GrpId;
	
	@Column(name = "acc1_grp_code")
	private String acc1GrpCode;
	
	@Column(name = "acc2_grp_id")
	private String acc2GrpId;
	
	@Column(name = "acc2_grp_code")
	private String acc2GrpCode;
	private String dirGrpId;
	private String dirGrpCode;
	private String pf;
	private String esi;
	private String uan;
	private String officeId;
	private String officeCode;
	private String departmentId;
	private String departmentCode;
	private String employeeCategory;
	private String shiftId;
	private String shiftCode;
	private String bankName;
	private String bankAccName;
	private String accNumber;
	private String ifscCode;
	private String isValid;
	private String errorStatus;
}
