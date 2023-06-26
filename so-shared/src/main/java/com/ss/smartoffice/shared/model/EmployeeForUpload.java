package com.ss.smartoffice.shared.model;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"empCode","firstName","lastName","status","mobileNo","DOB","DOJ","email","sex","designation",
	"n1ManagerCode","n2ManagerCode","HR1GroupCode","HR2GroupCode","Acc1GroupCode","Acc2GroupCode",
	"DIRgroupCode","PF","ESI","UAN","officeCode","departmentCode","employeeCategory","shiftCode","bankName",
	"bankAccountName","accountNumber","IFSCCode"})
public class EmployeeForUpload {
	@JsonProperty("empCode")
	private String empCode;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("status")
	private String status;
	@JsonProperty("mobileNo")
	private String mobileNo;
	@JsonProperty("DOB")
	private String DOB;
	@JsonProperty("DOJ")
	private String DOJ;
	@JsonProperty("email")
	private String email;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("designation")
	private String designation;
	@JsonProperty("n1ManagerCode")
	private String n1ManagerCode;
	@JsonProperty("n2ManagerCode")
	private String n2ManagerCode;
	@JsonProperty("HR1GroupCode")
	private String HR1GroupCode;
	@JsonProperty("HR2GroupCode")
	private String HR2GroupCode;
	@JsonProperty("Acc1GroupCode")
	private String Acc1GroupCode;
	@JsonProperty("Acc2GroupCode")
	private String Acc2GroupCode;
	@JsonProperty("DIRgroupCode")
	private String dirGroupCode;
	@JsonProperty("PF")
	private String pf;
	@JsonProperty("ESI")
	private String esi;
	@JsonProperty("UAN")
	private String uan;
	@JsonProperty("officeCode")
	private String officeCode;
	@JsonProperty("departmentCode")
	private String departmentCode;
	@JsonProperty("employeeCategory")
	private String employeeCategory;
	@JsonProperty("shiftCode")
	private String shiftCode;
	@JsonProperty("bankName")
	private String bankName;
	@JsonProperty("bankAccountName")
	private String bankAccountName;
	@JsonProperty("accountNumber")
	private String accountNumber;
	@JsonProperty("IFSCCode")
	private String ifscCode;
	@Transient
	private String docId;
	@Transient
	private String employeeHdrId;
	@Transient
	private String isValid;
	@Transient
	private String errorStatus;
}
