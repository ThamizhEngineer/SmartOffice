package com.ss.smartoffice.soservice.transaction.uploadpayslip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
@Data
@JsonPropertyOrder({  "id" , "employee_code" 	

	, "employee_name"

	, "email"

	, "mobile_no"

	, "dob"

	, "doj"

	, "department"

	, "designation"

	, "grade"

	, "special_designation"

	, "skill"

	, "business"

	, "nature_of_position"

	, "salary_month"

	, "salary_year"

	, "location_of_position"

	, "pf_no"

	, "eps_no"

	, "esi_no"

	, "bank_account_number"

	, "bank"

	, "ifsc_code"

	, "pan_no"

	, "aadhar_no"

	, "uan_no"

	, "currency"

	, "period_days"

	, "period_holidays"

	, "worked_days"

	, "lop_and_lwop"

	, "arrear_days"

	, "late_days_deduction"

	, "overtime_hours"

	, "wage_days"

	, "additional_info_1"

	, "additional_info_2"

	, "additional_info_3"

	, "a11_name"

	, "a11_value_earning_period"

	, "a11_value_arrears"

	, "a11_value_current_period"

	, "a11_value_ytd_earnings"

	, "a12_name"

	, "a12_value_earning_period"

	, "a12_value_arrears"

	, "a12_value_current_period"

	, "a12_value_ytd_earnings"

	, "a13_name"

	, "a13_value_earning_period"

	, "a13_value_arrears"

	, "a13_value_current_period"

	, "a13_value_ytd_earnings"

	, "a14_name"

	, "a14_value_earning_period"

	, "a14_value_arrears"

	, "a14_value_current_period"

	, "a14_value_ytd_earnings"

	, "a15_name"

	, "a15_value_earning_period"

	, "a15_value_arrears"

	, "a15_value_current_period"

	, "a15_value_ytd_earnings"

	, "a16_name"

	, "a16_value_earning_period"

	, "a16_value_arrears"

	, "a16_value_current_period"

	, "a16_value_ytd_earnings"

	, "a17_name"

	, "a17_value_earning_period"

	, "a17_value_arrears"

	, "a17_value_current_period"

	, "a17_value_ytd_earnings"

	, "a18_name"

	, "a18_value_earning_period"

	, "a18_value_arrears"

	, "a18_value_current_period"

	, "a18_value_ytd_earnings"

	, "a19_name"

	, "a19_value_earning_period"

	, "a19_value_arrears"

	, "a19_value_current_period"

	, "a19_value_ytd_earnings"

	, "a21_name"

	, "a21_value_earning_period"

	, "a21_value_arrears"

	, "a21_value_current_period"

	, "a21_value_ytd_earnings"

	, "a22_name"

	, "a22_value_earning_period"

	, "a22_value_arrears"

	, "a22_value_current_period"

	, "a22_value_ytd_earnings"

	, "a23_name"

	, "a23_value_earning_period"

	, "a23_value_arrears"

	, "a23_value_current_period"

	, "a23_value_ytd_earnings"

	, "a24_name"

	, "a24_value_earning_period"

	, "a24_value_arrears"

	, "a24_value_current_period"

	, "a24_value_ytd_earnings"

	, "a25_name"

	, "a25_value_earning_period"

	, "a25_value_arrears"

	, "a25_value_current_period"

	, "a25_value_ytd_earnings"

	, "a26_name"

	, "a26_value_earning_period"

	, "a26_value_arrears"

	, "a26_value_current_period"

	, "a26_value_ytd_earnings"

	, "a27_name"

	, "a27_value_earning_period"

	, "a27_value_arrears"

	, "a27_value_current_period"

	, "a27_value_ytd_earnings"

	, "b11_name"

	, "b11_value_earning_period"

	, "b11_value_arrears"

	, "b11_value_current_period"

	, "b11_value_ytd_earnings"

	, "b12_name"

	, "b12_value_earning_period"

	, "b12_value_arrears"

	, "b12_value_current_period"

	, "b12_value_ytd_earnings"

	, "b13_name"

	, "b13_value_earning_period"

	, "b13_value_arrears"

	, "b13_value_current_period"

	, "b13_value_ytd_earnings"

	, "b14_name"

	, "b14_value_earning_period"

	, "b14_value_arrears"

	, "b14_value_current_period"

	, "b14_value_ytd_earnings"

	, "d11_name"

	, "d11_value_current_period"

	, "d11_value_ytd_deductions"

	, "d12_name"

	, "d12_value_current_period"

	, "d12_value_ytd_deductions"

	, "d13_name"

	, "d13_value_current_period"

	, "d13_value_ytd_deductions"

	, "d14_name"

	, "d14_value_current_period"

	, "d14_value_ytd_deductions"

	, "d15_name"

	, "d15_value_current_period"

	, "d15_value_ytd_deductions"

	, "d16_name"

	, "d16_value_current_period"

	, "d16_value_ytd_deductions"

	, "d17_name"

	, "d17_value_current_period"

	, "d17_value_ytd_deductions"

	, "leave1_name"

	, "leave1_value_opening"

	, "leave1_value_accured"

	, "leave1_availed"

	, "leave1_value_balance"

	, "leave2_name"

	, "leave2_value_opening"

	, "leave2_value_accured"

	, "leave2_availed"

	, "leave2_value_balance"

	, "leave3_name"

	, "leave3_value_opening"

	, "leave3_value_accured"

	, "leave3_availed"

	, "leave3_value_balance"

	, "leave4_name"

	, "leave4_value_opening"

	, "leave4_value_accured"

	, "leave4_availed"

	, "leave4_value_balance"

	, "remarks_name"

	, "remarks_value"

	, "net_pay_period_name"

	, "net_pay_period_value"

	, "net_pay_words_name"

	, "net_pay_words_value"

	, "pf_accumulation_by_employer"

	, "pf_opening_value"

	, "pf_closing_value"

	, "pf_balance_value" 
			   })
public class PayslipUploadLine {
	@JsonProperty("id")	
	private Integer id;
	@JsonProperty("employee_code")	
		private String employeeCode;
	@JsonProperty("employee_name")	
		private String employeeName;
	@JsonProperty("email")	
		private String email;
	@JsonProperty("mobile_no")	
		private String mobileNo;
	@JsonProperty("dob")	
		private String dob;
	@JsonProperty("doj")	
		private String doj;
	@JsonProperty("department")	
		private String department;
	@JsonProperty("designation")	
		private String designation;
	@JsonProperty("grade")	
		private String grade;
	@JsonProperty("special_designation")	
		private String specialDesignation;
	@JsonProperty("skill")	
		private String skill;
	@JsonProperty("business")	
		private String business;
	@JsonProperty("nature_of_position")	
		private String natureOfPosition;
	@JsonProperty("salary_month")	
		private String salaryMonth;
	@JsonProperty("salary_year")	
		private String salaryYear;
	@JsonProperty("location_of_position")		
	private String locationOfPosition;
	@JsonProperty("pf_no")	
		private String pfNo;
	@JsonProperty("eps_no")	
		private String epsNo;
	@JsonProperty("esi_no")	
		private String esiNo;
	@JsonProperty("bank_account_number")	
		private String bankAccountNumber;
	@JsonProperty("bank")	
		private String bank;
	@JsonProperty("ifsc_code")	
		private String ifscCode;
	@JsonProperty("pan_no")	
		private String panNo;
	@JsonProperty("aadhar_no")	
		private String aadharNo;
	@JsonProperty("uan_no")	
		private String uanNo;
	@JsonProperty("currency")	
		private String currency;
	@JsonProperty("period_days")	
		private String periodDays;
	@JsonProperty("period_holidays")	
		private String periodHolidays;
	@JsonProperty("worked_days")	
		private String workedDays;
	@JsonProperty("lop_and_lwop")	
		private String lopAndLwop;
	@JsonProperty("arrear_days")	
		private String arrearDays;
	@JsonProperty("late_days_deduction")	
		private String lateDaysDeduction;
	@JsonProperty("overtime_hours")	
		private String overtimeHours;
	@JsonProperty("wage_days")	
		private String wageDays;
		@JsonProperty("additional_info_1")
		private String additionalInfo1;
		@JsonProperty("additional_info_2")
		private String additionalInfo2;
		@JsonProperty("additional_info_3")
		private String additionalInfo3;
		
		@JsonProperty("a11_name")
		private String a11Name;
		@JsonProperty("a11_value_earning_period")
		private String a11ValueEarningPeriod;
		@JsonProperty("a11_value_arrears")
		private String a11ValueArrears;
		@JsonProperty("a11_value_current_period")
		private String a11ValueCurrentPeriod;
		@JsonProperty("a11_value_ytd_earnings")
		private String a11ValueYtdEarnings;
		@JsonProperty("a12_name")
		private String a12Name;
		@JsonProperty("a12_value_earning_period")
		private String a12ValueEarningPeriod;
		@JsonProperty("a12_value_arrears")
		private String a12ValueArrears;
		@JsonProperty("a12_value_current_period")
		private String a12ValueCurrentPeriod;
		@JsonProperty("a12_value_ytd_earnings")
		private String a12ValueYtdEarnings;	
		@JsonProperty("a13_name")
		private String a13Name;
		@JsonProperty("a13_value_earning_period")
		private String a13ValueEarningPeriod;
		@JsonProperty("a13_value_arrears")
		private String a13ValueArrears;
		@JsonProperty("a13_value_current_period")
		private String a13ValueCurrentPeriod;
		@JsonProperty("a13_value_ytd_earnings")
		private String a13ValueYtdEarnings;
		@JsonProperty("a14_name")
		private String a14Name;
		@JsonProperty("a14_value_earning_period")
		private String a14ValueEarningPeriod;
		@JsonProperty("a14_value_arrears")
		private String a14ValueArrears;
		@JsonProperty("a14_value_current_period")
		private String a14ValueCurrentPeriod;
		@JsonProperty("a14_value_ytd_earnings")
		private String a14ValueYtdEarnings;	
		@JsonProperty("a15_name")
		private String a15Name;	
		@JsonProperty("a15_value_earning_period")
		private String a15ValueEarningPeriod;
		@JsonProperty("a15_value_arrears")
		private String a15ValueArrears;
		@JsonProperty("a15_value_current_period")
		private String a15ValueCurrentPeriod;
		@JsonProperty("a15_value_ytd_earnings")
		private String a15ValueYtdEarnings;	
		@JsonProperty("a16_name")
		private String a16Name;
		@JsonProperty("a16_value_earning_period")
		private String a16ValueEarningPeriod;	
		@JsonProperty("a16_value_arrears")
		private String a16ValueArrears;	
		@JsonProperty("a16_value_current_period")
		private String a16ValueCurrentPeriod;
		@JsonProperty("a16_value_ytd_earnings")
		private String a16ValueYtdEarnings;	
		@JsonProperty("a17_name")
		private String a17Name;	
		@JsonProperty("a17_value_earning_period")
		private String a17ValueEarningPeriod;
		@JsonProperty("a17_value_arrears")
		private String a17ValueArrears;	
		@JsonProperty("a17_value_current_period")
		private String a17ValueCurrentPeriod;
		@JsonProperty("a17_value_ytd_earnings")
		private String a17ValueYtdEarnings;	
		@JsonProperty("a18_name")
		private String a18Name;
		@JsonProperty("a18_value_earning_period")
		private String a18ValueEarningPeriod;
		@JsonProperty("a18_value_arrears")
		private String a18ValueArrears;
		@JsonProperty("a18_value_current_period")
		private String a18ValueCurrentPeriod;
		@JsonProperty("a18_value_ytd_earnings")
		private String a18ValueYtdEarnings;
		@JsonProperty("a19_name")
		private String a19Name;
		@JsonProperty("a19_value_earning_period")
		private String a19ValueEarningPeriod;
		@JsonProperty("a19_value_arrears")
		private String a19ValueArrears;
		@JsonProperty("a19_value_current_period")
		private String a19ValueCurrentPeriod;
		@JsonProperty("a19_value_ytd_earnings")
		private String a19ValueYtdEarnings;
		@JsonProperty("a21_name")
		private String a21Name;	
		@JsonProperty("a21_value_earning_period")
		private String a21ValueEarningPeriod;
		@JsonProperty("a21_value_arrears")
		private String a21ValueArrears;
		@JsonProperty("a21_value_current_period")
		private String a21ValueCurrentPeriod;
		@JsonProperty("a21_value_ytd_earnings")
		private String a21ValueYtdEarnings;	
		@JsonProperty("a22_name")
		private String a22Name;
		@JsonProperty("a22_value_earning_period")
		private String a22ValueEarningPeriod;	
		@JsonProperty("a22_value_arrears")
		private String a22ValueArrears;
		@JsonProperty("a22_value_current_period")
		private String a22ValueCurrentPeriod;
		@JsonProperty("a22_value_ytd_earnings")
		private String a22ValueYtdEarnings;
		@JsonProperty("a23_name")
		private String a23Name;
		@JsonProperty("a23_value_earning_period")
		private String a23ValueEarningPeriod;
		@JsonProperty("a23_value_arrears")
		private String a23ValueArrears;
		@JsonProperty("a23_value_current_period")
		private String a23ValueCurrentPeriod;
		@JsonProperty("a23_value_ytd_earnings")
		private String a23ValueYtdEarnings;
		@JsonProperty("a24_name")
		private String a24Name;
		@JsonProperty("a24_value_earning_period")
		private String a24ValueEarningPeriod;
		@JsonProperty("a24_value_arrears")
		private String a24ValueArrears;
		@JsonProperty("a24_value_current_period")
		private String a24ValueCurrentPeriod;	
		@JsonProperty("a24_value_ytd_earnings")
		private String a24ValueYtdEarnings;
		@JsonProperty("a25_name")
		private String a25Name;
		@JsonProperty("a25_value_earning_period")
		private String a25ValueEarningPeriod;
		@JsonProperty("a25_value_arrears")
		private String a25ValueArrears;
		@JsonProperty("a25_value_current_period")
		private String a25ValueCurrentPeriod;
		@JsonProperty("a25_value_ytd_earnings")
		private String a25ValueYtdEarnings;
		@JsonProperty("a26_name")
		private String a26Name;
		@JsonProperty("a26_value_earning_period")
		private String a26ValueEarningPeriod;
		@JsonProperty("a26_value_arrears")
		private String a26ValueArrears;
		@JsonProperty("a26_value_current_period")
		private String a26ValueCurrentPeriod;
		@JsonProperty("a26_value_ytd_earnings")
		private String a26ValueYtdEarnings;
		@JsonProperty("a27_name")
		private String a27Name;
		@JsonProperty("a27_value_earning_period")
		private String a27ValueEarningPeriod;
		@JsonProperty("a27_value_arrears")
		private String a27ValueArrears;
		@JsonProperty("a27_value_current_period")
		private String a27ValueCurrentPeriod;
		@JsonProperty("a27_value_ytd_earnings")
		private String a27ValueYtdEarnings;
		@JsonProperty("b11_name")
		private String b11Name;
		@JsonProperty("b11_value_earning_period")
		private String b11ValueEarningPeriod;
		@JsonProperty("b11_value_arrears")
		private String b11ValueArrears;
		@JsonProperty("b11_value_current_period")
		private String b11ValueCurrentPeriod;
		@JsonProperty("b11_value_ytd_earnings")
		private String b11ValueYtdEarnings;
		@JsonProperty("b12_name")
		private String b12Name;
		@JsonProperty("b12_value_earning_period")
		private String b12ValueEarningPeriod;
		@JsonProperty("b12_value_arrears")
		private String b12ValueArrears;
		@JsonProperty("b12_value_current_period")
		private String b12ValueCurrentPeriod;
		@JsonProperty("b12_value_ytd_earnings")
		private String b12ValueYtdEarnings;
		@JsonProperty("b13_name")
		private String b13Name;
		@JsonProperty("b13_value_earning_period")
		private String b13ValueEarningPeriod;
		@JsonProperty("b13_value_arrears")
		private String b13ValueArrears;
		@JsonProperty("b13_value_current_period")
		private String b13ValueCurrentPeriod;
		@JsonProperty("b13_value_ytd_earnings")
		private String b13ValueYtdEarnings;
		@JsonProperty("b14_name")
		private String b14Name;
		@JsonProperty("b14_value_earning_period")
		private String b14ValueEarningPeriod;
		@JsonProperty("b14_value_arrears")
		private String b14ValueArrears;
		@JsonProperty("b14_value_current_period")
		private String b14ValueCurrentPeriod;
		@JsonProperty("b14_value_ytd_earnings")
		private String b14ValueYtdEarnings;
		@JsonProperty("d11_name")
		private String d11Name;
		@JsonProperty("d11_value_current_period")
		private String d11ValueCurrentPeriod;
		@JsonProperty("d11_value_ytd_deductions")
		private String d11ValueYtdDeductions;
		@JsonProperty("d12_name")
		private String d12Name;
		@JsonProperty("d12_value_current_period")
		private String d12ValueCurrentPeriod;
		@JsonProperty("d12_value_ytd_deductions")
		private String d12ValueYtdDeductions;
		@JsonProperty("d13_name")
		private String d13Name;
		@JsonProperty("d13_value_current_period")
		private String d13ValueCurrentPeriod;
		@JsonProperty("d13_value_ytd_deductions")
		private String d13ValueYtdDeductions;
		@JsonProperty("d14_name")
		private String d14Name;
		@JsonProperty("d14_value_current_period")
		private String d14ValueCurrentPeriod;
		@JsonProperty("d14_value_ytd_deductions")
		private String d14ValueYtdDeductions;
		@JsonProperty("d15_name")
		private String d15Name;
		@JsonProperty("d15_value_current_period")
		private String d15ValueCurrentPeriod;
		@JsonProperty("d15_value_ytd_deductions")
		private String d15ValueYtdDeductions;
		@JsonProperty("d16_name")
		private String d16Name;
		@JsonProperty("d16_value_current_period")
		private String d16ValueCurrentPeriod;
		@JsonProperty("d16_value_ytd_deductions")
		private String d16ValueYtdDeductions;
		@JsonProperty("d17_name")
		private String d17Name;
		@JsonProperty("d17_value_current_period")
		private String d17ValueCurrentPeriod;
		@JsonProperty("d17_value_ytd_deductions")
		private String d17ValueYtdDeductions;
		@JsonProperty("leave1_name")
		private String leave1Name;
		@JsonProperty("leave1_value_opening")
		private String leave1ValueOpening;
		@JsonProperty("leave1_value_accured")
		private String leave1ValueAccured;
		
		@JsonProperty("leave1_availed")
		private String leave1Availed;
		@JsonProperty("leave1_value_balance")
		private String leave1ValueBalance;
		@JsonProperty("leave2_name")
		private String leave2Name;
		@JsonProperty("leave2_value_opening")
		private String leave2ValueOpening;
		@JsonProperty("leave2_value_accured")
		private String leave2ValueAccured;
		
		@JsonProperty("leave2_availed")
		private String leave2Availed;
		@JsonProperty("leave2_value_balance")
		
		private String leave2ValueBalance;
		@JsonProperty("leave3_name")
		private String leave3Name;
		@JsonProperty("leave3_value_opening")
		private String leave3ValueOpening;
		@JsonProperty("leave3_value_accured")
		private String leave3ValueAccured;
		
		@JsonProperty("leave3_availed")
		private String leave3Availed;
		@JsonProperty("leave3_value_balance")
		private String leave3ValueBalance;
		@JsonProperty("leave4_name")
		private String leave4Name;
		@JsonProperty("leave4_value_opening")
		private String leave4ValueOpening;
		@JsonProperty("leave4_value_accured")
		private String leave4ValueAccured;
		
		@JsonProperty("leave4_availed")
		private String leave4Availed;
		@JsonProperty("leave4_value_balance")
		private String leave4ValueBalance;	
	@JsonProperty("remarks_name")
		private String remarksName;
	@JsonProperty("remarks_value")
		private String remarksValue;	
	@JsonProperty("net_pay_period_name")
		private String netPayPeriodName;
	@JsonProperty("net_pay_period_value")	
		private String netPayPeriodValue;
	@JsonProperty("net_pay_words_name")	
		private String netPayWordsName;	
	@JsonProperty("net_pay_words_value")
		private String netPayWordsValue;
		@JsonProperty("pf_accumulation_by_employer")
		private String pfAccumulationByEmployer;
		@JsonProperty("pf_opening_value")
		private String pfOpeningValue;
		@JsonProperty("pf_closing_value")
		private String pfClosingValue;
		@JsonProperty("pf_balance_value")
		private String pfBalanceValue;
	
		private String isClean;
	
		private String status;
		
		private Integer uploadPayslipHdrId;
	
		private String createdBy;
	    
		private String modifiedBy;
		
		
	    
		private String createdDt;
	
	  
		private String modifiedDt;
	    
		private String remarks;
}
