package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
@Entity
@Table(name="t_upload_payslip_line")
@Scope("prototype")
@Builder
public class UploadPayslipLine {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String employeeCode;
	private String employeeName;
	private String email;
	private String mobileNo;
	private String dob;
	private String doj;
	private String department;
	private String designation;
	private String grade;
	private String specialDesignation;
	private String skill;
	private String business;
	private String natureOfPosition;
	private String salaryMonth;
	private String salaryYear;
	private String locationOfPosition;
	private String pfNo;
	private String epsNo;
	private String esiNo;
	private String bankAccountNumber;
	private String bank;
	private String ifscCode;
	private String panNo;
	private String aadharNo;
	private String uanNo;
	private String currency;
	private String periodDays;
	private String periodHolidays;
	private String workedDays;
	private String lopAndLwop;
	private String arrearDays;
	private String lateDaysDeduction;
	private String overtimeHours;
	private String wageDays;
	@Column(name="additional_info_1")
	private String additionalInfo1;
	@Column(name="additional_info_2")
	private String additionalInfo2;
	@Column(name="additional_info_3")
	private String additionalInfo3;
	
	@Column(name="a11_name")
	private String a11Name;
	@Column(name="a11_value_earning_period")
	private String a11ValueEarningPeriod;
	@Column(name="a11_value_arrears")
	private String a11ValueArrears;
	@Column(name="a11_value_current_period")
	private String a11ValueCurrentPeriod;
	@Column(name="a11_value_ytd_earnings")
	private String a11ValueYtdEarnings;
	@Column(name="a12_name")
	private String a12Name;
	@Column(name="a12_value_earning_period")
	private String a12ValueEarningPeriod;
	@Column(name="a12_value_arrears")
	private String a12ValueArrears;
	@Column(name="a12_value_current_period")
	private String a12ValueCurrentPeriod;
	@Column(name="a12_value_ytd_earnings")
	private String a12ValueYtdEarnings;	
	@Column(name="a13_name")
	private String a13Name;
	@Column(name="a13_value_earning_period")
	private String a13ValueEarningPeriod;
	@Column(name="a13_value_arrears")
	private String a13ValueArrears;
	@Column(name="a13_value_current_period")
	private String a13ValueCurrentPeriod;
	@Column(name="a13_value_ytd_earnings")
	private String a13ValueYtdEarnings;
	@Column(name="a14_name")
	private String a14Name;
	@Column(name="a14_value_earning_period")
	private String a14ValueEarningPeriod;
	@Column(name="a14_value_arrears")
	private String a14ValueArrears;
	@Column(name="a14_value_current_period")
	private String a14ValueCurrentPeriod;
	@Column(name="a14_value_ytd_earnings")
	private String a14ValueYtdEarnings;	
	@Column(name="a15_name")
	private String a15Name;	
	@Column(name="a15_value_earning_period")
	private String a15ValueEarningPeriod;
	@Column(name="a15_value_arrears")
	private String a15ValueArrears;
	@Column(name="a15_value_current_period")
	private String a15ValueCurrentPeriod;
	@Column(name="a15_value_ytd_earnings")
	private String a15ValueYtdEarnings;	
	@Column(name="a16_name")
	private String a16Name;
	@Column(name="a16_value_earning_period")
	private String a16ValueEarningPeriod;	
	@Column(name="a16_value_arrears")
	private String a16ValueArrears;	
	@Column(name="a16_value_current_period")
	private String a16ValueCurrentPeriod;
	@Column(name="a16_value_ytd_earnings")
	private String a16ValueYtdEarnings;	
	@Column(name="a17_name")
	private String a17Name;	
	@Column(name="a17_value_earning_period")
	private String a17ValueEarningPeriod;
	@Column(name="a17_value_arrears")
	private String a17ValueArrears;	
	@Column(name="a17_value_current_period")
	private String a17ValueCurrentPeriod;
	@Column(name="a17_value_ytd_earnings")
	private String a17ValueYtdEarnings;	
	@Column(name="a18_name")
	private String a18Name;
	@Column(name="a18_value_earning_period")
	private String a18ValueEarningPeriod;
	@Column(name="a18_value_arrears")
	private String a18ValueArrears;
	@Column(name="a18_value_current_period")
	private String a18ValueCurrentPeriod;
	@Column(name="a18_value_ytd_earnings")
	private String a18ValueYtdEarnings;
	@Column(name="a19_name")
	private String a19Name;
	@Column(name="a19_value_earning_period")
	private String a19ValueEarningPeriod;
	@Column(name="a19_value_arrears")
	private String a19ValueArrears;
	@Column(name="a19_value_current_period")
	private String a19ValueCurrentPeriod;
	@Column(name="a19_value_ytd_earnings")
	private String a19ValueYtdEarnings;
	@Column(name="a21_name")
	private String a21Name;	
	@Column(name="a21_value_earning_period")
	private String a21ValueEarningPeriod;
	@Column(name="a21_value_arrears")
	private String a21ValueArrears;
	@Column(name="a21_value_current_period")
	private String a21ValueCurrentPeriod;
	@Column(name="a21_value_ytd_earnings")
	private String a21ValueYtdEarnings;	
	@Column(name="a22_name")
	private String a22Name;
	@Column(name="a22_value_earning_period")
	private String a22ValueEarningPeriod;	
	@Column(name="a22_value_arrears")
	private String a22ValueArrears;
	@Column(name="a22_value_current_period")
	private String a22ValueCurrentPeriod;
	@Column(name="a22_value_ytd_earnings")
	private String a22ValueYtdEarnings;
	@Column(name="a23_name")
	private String a23Name;
	@Column(name="a23_value_earning_period")
	private String a23ValueEarningPeriod;
	@Column(name="a23_value_arrears")
	private String a23ValueArrears;
	@Column(name="a23_value_current_period")
	private String a23ValueCurrentPeriod;
	@Column(name="a23_value_ytd_earnings")
	private String a23ValueYtdEarnings;
	@Column(name="a24_name")
	private String a24Name;
	@Column(name="a24_value_earning_period")
	private String a24ValueEarningPeriod;
	@Column(name="a24_value_arrears")
	private String a24ValueArrears;
	@Column(name="a24_value_current_period")
	private String a24ValueCurrentPeriod;	
	@Column(name="a24_value_ytd_earnings")
	private String a24ValueYtdEarnings;
	@Column(name="a25_name")
	private String a25Name;
	@Column(name="a25_value_earning_period")
	private String a25ValueEarningPeriod;
	@Column(name="a25_value_arrears")
	private String a25ValueArrears;
	@Column(name="a25_value_current_period")
	private String a25ValueCurrentPeriod;
	@Column(name="a25_value_ytd_earnings")
	private String a25ValueYtdEarnings;
	@Column(name="a26_name")
	private String a26Name;
	@Column(name="a26_value_earning_period")
	private String a26ValueEarningPeriod;
	@Column(name="a26_value_arrears")
	private String a26ValueArrears;
	@Column(name="a26_value_current_period")
	private String a26ValueCurrentPeriod;
	@Column(name="a26_value_ytd_earnings")
	private String a26ValueYtdEarnings;
	@Column(name="a27_name")
	private String a27Name;
	@Column(name="a27_value_earning_period")
	private String a27ValueEarningPeriod;
	@Column(name="a27_value_arrears")
	private String a27ValueArrears;
	@Column(name="a27_value_current_period")
	private String a27ValueCurrentPeriod;
	@Column(name="a27_value_ytd_earnings")
	private String a27ValueYtdEarnings;
	@Column(name="b11_name")
	private String b11Name;
	@Column(name="b11_value_earning_period")
	private String b11ValueEarningPeriod;
	@Column(name="b11_value_arrears")
	private String b11ValueArrears;
	@Column(name="b11_value_current_period")
	private String b11ValueCurrentPeriod;
	@Column(name="b11_value_ytd_earnings")
	private String b11ValueYtdEarnings;
	@Column(name="b12_name")
	private String b12Name;
	@Column(name="b12_value_earning_period")
	private String b12ValueEarningPeriod;
	@Column(name="b12_value_arrears")
	private String b12ValueArrears;
	@Column(name="b12_value_current_period")
	private String b12ValueCurrentPeriod;
	@Column(name="b12_value_ytd_earnings")
	private String b12ValueYtdEarnings;
	@Column(name="b13_name")
	private String b13Name;
	@Column(name="b13_value_earning_period")
	private String b13ValueEarningPeriod;
	@Column(name="b13_value_arrears")
	private String b13ValueArrears;
	@Column(name="b13_value_current_period")
	private String b13ValueCurrentPeriod;
	@Column(name="b13_value_ytd_earnings")
	private String b13ValueYtdEarnings;
	@Column(name="b14_name")
	private String b14Name;
	@Column(name="b14_value_earning_period")
	private String b14ValueEarningPeriod;
	@Column(name="b14_value_arrears")
	private String b14ValueArrears;
	@Column(name="b14_value_current_period")
	private String b14ValueCurrentPeriod;
	@Column(name="b14_value_ytd_earnings")
	private String b14ValueYtdEarnings;
	@Column(name="d11_name")
	private String d11Name;
	@Column(name="d11_value_current_period")
	private String d11ValueCurrentPeriod;
	@Column(name="d11_value_ytd_deductions")
	private String d11ValueYtdDeductions;
	@Column(name="d12_name")
	private String d12Name;
	@Column(name="d12_value_current_period")
	private String d12ValueCurrentPeriod;
	@Column(name="d12_value_ytd_deductions")
	private String d12ValueYtdDeductions;
	@Column(name="d13_name")
	private String d13Name;
	@Column(name="d13_value_current_period")
	private String d13ValueCurrentPeriod;
	@Column(name="d13_value_ytd_deductions")
	private String d13ValueYtdDeductions;
	@Column(name="d14_name")
	private String d14Name;
	@Column(name="d14_value_current_period")
	private String d14ValueCurrentPeriod;
	@Column(name="d14_value_ytd_deductions")
	private String d14ValueYtdDeductions;
	@Column(name="d15_name")
	private String d15Name;
	@Column(name="d15_value_current_period")
	private String d15ValueCurrentPeriod;
	@Column(name="d15_value_ytd_deductions")
	private String d15ValueYtdDeductions;
	@Column(name="d16_name")
	private String d16Name;
	@Column(name="d16_value_current_period")
	private String d16ValueCurrentPeriod;
	@Column(name="d16_value_ytd_deductions")
	private String d16ValueYtdDeductions;
	@Column(name="d17_name")
	private String d17Name;
	@Column(name="d17_value_current_period")
	private String d17ValueCurrentPeriod;
	@Column(name="d17_value_ytd_deductions")
	private String d17ValueYtdDeductions;
	@Column(name="leave1_name")
	private String leave1Name;
	@Column(name="leave1_value_opening")
	private String leave1ValueOpening;
	@Column(name="leave1_value_accured")
	private String leave1ValueAccured;
	
	@Column(name="leave1_availed")
	private String leave1Availed;
	@Column(name="leave1_value_balance")
	private String leave1ValueBalance;
	@Column(name="leave2_name")
	private String leave2Name;
	@Column(name="leave2_value_opening")
	private String leave2ValueOpening;
	@Column(name="leave2_value_accured")
	private String leave2ValueAccured;
	
	@Column(name="leave2_availed")
	private String leave2Availed;
	@Column(name="leave2_value_balance")
	
	private String leave2ValueBalance;
	@Column(name="leave3_name")
	private String leave3Name;
	@Column(name="leave3_value_opening")
	private String leave3ValueOpening;
	@Column(name="leave3_value_accured")
	private String leave3ValueAccured;
	
	@Column(name="leave3_availed")
	private String leave3Availed;
	@Column(name="leave3_value_balance")
	private String leave3ValueBalance;
	@Column(name="leave4_name")
	private String leave4Name;
	@Column(name="leave4_value_opening")
	private String leave4ValueOpening;
	@Column(name="leave4_value_accured")
	private String leave4ValueAccured;
	
	@Column(name="leave4_availed")
	private String leave4Availed;
	@Column(name="leave4_value_balance")
	private String leave4ValueBalance;	
	private String remarksName;
	private String remarksValue;	
	private String netPayPeriodName;	
	private String netPayPeriodValue;	
	private String netPayWordsName;	
	private String netPayWordsValue;
	@Column(name="pf_accumulation_by_employer")
	private String pfAccumulationByEmployer;
	@Column(name="pf_opening_value")
	private String pfOpeningValue;
	@Column(name="pf_closing_value")
	private String pfClosingValue;
	@Column(name="pf_balance_value")
	private String pfBalanceValue;
	@Column(name="is_clean")
	private String isClean;
	private String status;
	@Column(name="upload_payslip_hdr_id")
	private Integer uploadPayslipHdrId;
	
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String remarks;
	
	
	public UploadPayslipLine() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UploadPayslipLine(Integer id, String employeeCode, String employeeName, String email, String mobileNo,
			String dob, String doj, String department, String designation, String grade, String specialDesignation,
			String skill, String business, String natureOfPosition, String salaryMonth, String salaryYear,
			String locationOfPosition, String pfNo, String epsNo, String esiNo, String bankAccountNumber, String bank,
			String ifscCode, String panNo, String aadharNo, String uanNo, String currency, String periodDays,
			String periodHolidays, String workedDays, String lopAndLwop, String arrearDays, String lateDaysDeduction,
			String overtimeHours, String wageDays, String additionalInfo1, String additionalInfo2,
			String additionalInfo3, String a11Name, String a11ValueEarningPeriod, String a11ValueArrears,
			String a11ValueCurrentPeriod, String a11ValueYtdEarnings, String a12Name, String a12ValueEarningPeriod,
			String a12ValueArrears, String a12ValueCurrentPeriod, String a12ValueYtdEarnings, String a13Name,
			String a13ValueEarningPeriod, String a13ValueArrears, String a13ValueCurrentPeriod,
			String a13ValueYtdEarnings, String a14Name, String a14ValueEarningPeriod, String a14ValueArrears,
			String a14ValueCurrentPeriod, String a14ValueYtdEarnings, String a15Name, String a15ValueEarningPeriod,
			String a15ValueArrears, String a15ValueCurrentPeriod, String a15ValueYtdEarnings, String a16Name,
			String a16ValueEarningPeriod, String a16ValueArrears, String a16ValueCurrentPeriod,
			String a16ValueYtdEarnings, String a17Name, String a17ValueEarningPeriod, String a17ValueArrears,
			String a17ValueCurrentPeriod, String a17ValueYtdEarnings, String a18Name, String a18ValueEarningPeriod,
			String a18ValueArrears, String a18ValueCurrentPeriod, String a18ValueYtdEarnings, String a19Name,
			String a19ValueEarningPeriod, String a19ValueArrears, String a19ValueCurrentPeriod,
			String a19ValueYtdEarnings, String a21Name, String a21ValueEarningPeriod, String a21ValueArrears,
			String a21ValueCurrentPeriod, String a21ValueYtdEarnings, String a22Name, String a22ValueEarningPeriod,
			String a22ValueArrears, String a22ValueCurrentPeriod, String a22ValueYtdEarnings, String a23Name,
			String a23ValueEarningPeriod, String a23ValueArrears, String a23ValueCurrentPeriod,
			String a23ValueYtdEarnings, String a24Name, String a24ValueEarningPeriod, String a24ValueArrears,
			String a24ValueCurrentPeriod, String a24ValueYtdEarnings, String a25Name, String a25ValueEarningPeriod,
			String a25ValueArrears, String a25ValueCurrentPeriod, String a25ValueYtdEarnings, String a26Name,
			String a26ValueEarningPeriod, String a26ValueArrears, String a26ValueCurrentPeriod,
			String a26ValueYtdEarnings, String a27Name, String a27ValueEarningPeriod, String a27ValueArrears,
			String a27ValueCurrentPeriod, String a27ValueYtdEarnings, String b11Name, String b11ValueEarningPeriod,
			String b11ValueArrears, String b11ValueCurrentPeriod, String b11ValueYtdEarnings, String b12Name,
			String b12ValueEarningPeriod, String b12ValueArrears, String b12ValueCurrentPeriod,
			String b12ValueYtdEarnings, String b13Name, String b13ValueEarningPeriod, String b13ValueArrears,
			String b13ValueCurrentPeriod, String b13ValueYtdEarnings, String b14Name, String b14ValueEarningPeriod,
			String b14ValueArrears, String b14ValueCurrentPeriod, String b14ValueYtdEarnings, String d11Name,
			String d11ValueCurrentPeriod, String d11ValueYtdDeductions, String d12Name, String d12ValueCurrentPeriod,
			String d12ValueYtdDeductions, String d13Name, String d13ValueCurrentPeriod, String d13ValueYtdDeductions,
			String d14Name, String d14ValueCurrentPeriod, String d14ValueYtdDeductions, String d15Name,
			String d15ValueCurrentPeriod, String d15ValueYtdDeductions, String d16Name, String d16ValueCurrentPeriod,
			String d16ValueYtdDeductions, String d17Name, String d17ValueCurrentPeriod, String d17ValueYtdDeductions,
			String leave1Name, String leave1ValueOpening, String leave1ValueAccured, String leave1Availed,
			String leave1ValueBalance, String leave2Name, String leave2ValueOpening, String leave2ValueAccured,
			String leave2Availed, String leave2ValueBalance, String leave3Name, String leave3ValueOpening,
			String leave3ValueAccured, String leave3Availed, String leave3ValueBalance, String leave4Name,
			String leave4ValueOpening, String leave4ValueAccured, String leave4Availed, String leave4ValueBalance,
			String remarksName, String remarksValue, String netPayPeriodName, String netPayPeriodValue,
			String netPayWordsName, String netPayWordsValue, String pfAccumulationByEmployer, String pfOpeningValue,
			String pfClosingValue, String pfBalanceValue, String isClean, String status, Integer uploadPayslipHdrId,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt, String remarks) {
		super();
		this.id = id;
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.dob = dob;
		this.doj = doj;
		this.department = department;
		this.designation = designation;
		this.grade = grade;
		this.specialDesignation = specialDesignation;
		this.skill = skill;
		this.business = business;
		this.natureOfPosition = natureOfPosition;
		this.salaryMonth = salaryMonth;
		this.salaryYear = salaryYear;
		this.locationOfPosition = locationOfPosition;
		this.pfNo = pfNo;
		this.epsNo = epsNo;
		this.esiNo = esiNo;
		this.bankAccountNumber = bankAccountNumber;
		this.bank = bank;
		this.ifscCode = ifscCode;
		this.panNo = panNo;
		this.aadharNo = aadharNo;
		this.uanNo = uanNo;
		this.currency = currency;
		this.periodDays = periodDays;
		this.periodHolidays = periodHolidays;
		this.workedDays = workedDays;
		this.lopAndLwop = lopAndLwop;
		this.arrearDays = arrearDays;
		this.lateDaysDeduction = lateDaysDeduction;
		this.overtimeHours = overtimeHours;
		this.wageDays = wageDays;
		this.additionalInfo1 = additionalInfo1;
		this.additionalInfo2 = additionalInfo2;
		this.additionalInfo3 = additionalInfo3;
		this.a11Name = a11Name;
		this.a11ValueEarningPeriod = a11ValueEarningPeriod;
		this.a11ValueArrears = a11ValueArrears;
		this.a11ValueCurrentPeriod = a11ValueCurrentPeriod;
		this.a11ValueYtdEarnings = a11ValueYtdEarnings;
		this.a12Name = a12Name;
		this.a12ValueEarningPeriod = a12ValueEarningPeriod;
		this.a12ValueArrears = a12ValueArrears;
		this.a12ValueCurrentPeriod = a12ValueCurrentPeriod;
		this.a12ValueYtdEarnings = a12ValueYtdEarnings;
		this.a13Name = a13Name;
		this.a13ValueEarningPeriod = a13ValueEarningPeriod;
		this.a13ValueArrears = a13ValueArrears;
		this.a13ValueCurrentPeriod = a13ValueCurrentPeriod;
		this.a13ValueYtdEarnings = a13ValueYtdEarnings;
		this.a14Name = a14Name;
		this.a14ValueEarningPeriod = a14ValueEarningPeriod;
		this.a14ValueArrears = a14ValueArrears;
		this.a14ValueCurrentPeriod = a14ValueCurrentPeriod;
		this.a14ValueYtdEarnings = a14ValueYtdEarnings;
		this.a15Name = a15Name;
		this.a15ValueEarningPeriod = a15ValueEarningPeriod;
		this.a15ValueArrears = a15ValueArrears;
		this.a15ValueCurrentPeriod = a15ValueCurrentPeriod;
		this.a15ValueYtdEarnings = a15ValueYtdEarnings;
		this.a16Name = a16Name;
		this.a16ValueEarningPeriod = a16ValueEarningPeriod;
		this.a16ValueArrears = a16ValueArrears;
		this.a16ValueCurrentPeriod = a16ValueCurrentPeriod;
		this.a16ValueYtdEarnings = a16ValueYtdEarnings;
		this.a17Name = a17Name;
		this.a17ValueEarningPeriod = a17ValueEarningPeriod;
		this.a17ValueArrears = a17ValueArrears;
		this.a17ValueCurrentPeriod = a17ValueCurrentPeriod;
		this.a17ValueYtdEarnings = a17ValueYtdEarnings;
		this.a18Name = a18Name;
		this.a18ValueEarningPeriod = a18ValueEarningPeriod;
		this.a18ValueArrears = a18ValueArrears;
		this.a18ValueCurrentPeriod = a18ValueCurrentPeriod;
		this.a18ValueYtdEarnings = a18ValueYtdEarnings;
		this.a19Name = a19Name;
		this.a19ValueEarningPeriod = a19ValueEarningPeriod;
		this.a19ValueArrears = a19ValueArrears;
		this.a19ValueCurrentPeriod = a19ValueCurrentPeriod;
		this.a19ValueYtdEarnings = a19ValueYtdEarnings;
		this.a21Name = a21Name;
		this.a21ValueEarningPeriod = a21ValueEarningPeriod;
		this.a21ValueArrears = a21ValueArrears;
		this.a21ValueCurrentPeriod = a21ValueCurrentPeriod;
		this.a21ValueYtdEarnings = a21ValueYtdEarnings;
		this.a22Name = a22Name;
		this.a22ValueEarningPeriod = a22ValueEarningPeriod;
		this.a22ValueArrears = a22ValueArrears;
		this.a22ValueCurrentPeriod = a22ValueCurrentPeriod;
		this.a22ValueYtdEarnings = a22ValueYtdEarnings;
		this.a23Name = a23Name;
		this.a23ValueEarningPeriod = a23ValueEarningPeriod;
		this.a23ValueArrears = a23ValueArrears;
		this.a23ValueCurrentPeriod = a23ValueCurrentPeriod;
		this.a23ValueYtdEarnings = a23ValueYtdEarnings;
		this.a24Name = a24Name;
		this.a24ValueEarningPeriod = a24ValueEarningPeriod;
		this.a24ValueArrears = a24ValueArrears;
		this.a24ValueCurrentPeriod = a24ValueCurrentPeriod;
		this.a24ValueYtdEarnings = a24ValueYtdEarnings;
		this.a25Name = a25Name;
		this.a25ValueEarningPeriod = a25ValueEarningPeriod;
		this.a25ValueArrears = a25ValueArrears;
		this.a25ValueCurrentPeriod = a25ValueCurrentPeriod;
		this.a25ValueYtdEarnings = a25ValueYtdEarnings;
		this.a26Name = a26Name;
		this.a26ValueEarningPeriod = a26ValueEarningPeriod;
		this.a26ValueArrears = a26ValueArrears;
		this.a26ValueCurrentPeriod = a26ValueCurrentPeriod;
		this.a26ValueYtdEarnings = a26ValueYtdEarnings;
		this.a27Name = a27Name;
		this.a27ValueEarningPeriod = a27ValueEarningPeriod;
		this.a27ValueArrears = a27ValueArrears;
		this.a27ValueCurrentPeriod = a27ValueCurrentPeriod;
		this.a27ValueYtdEarnings = a27ValueYtdEarnings;
		this.b11Name = b11Name;
		this.b11ValueEarningPeriod = b11ValueEarningPeriod;
		this.b11ValueArrears = b11ValueArrears;
		this.b11ValueCurrentPeriod = b11ValueCurrentPeriod;
		this.b11ValueYtdEarnings = b11ValueYtdEarnings;
		this.b12Name = b12Name;
		this.b12ValueEarningPeriod = b12ValueEarningPeriod;
		this.b12ValueArrears = b12ValueArrears;
		this.b12ValueCurrentPeriod = b12ValueCurrentPeriod;
		this.b12ValueYtdEarnings = b12ValueYtdEarnings;
		this.b13Name = b13Name;
		this.b13ValueEarningPeriod = b13ValueEarningPeriod;
		this.b13ValueArrears = b13ValueArrears;
		this.b13ValueCurrentPeriod = b13ValueCurrentPeriod;
		this.b13ValueYtdEarnings = b13ValueYtdEarnings;
		this.b14Name = b14Name;
		this.b14ValueEarningPeriod = b14ValueEarningPeriod;
		this.b14ValueArrears = b14ValueArrears;
		this.b14ValueCurrentPeriod = b14ValueCurrentPeriod;
		this.b14ValueYtdEarnings = b14ValueYtdEarnings;
		this.d11Name = d11Name;
		this.d11ValueCurrentPeriod = d11ValueCurrentPeriod;
		this.d11ValueYtdDeductions = d11ValueYtdDeductions;
		this.d12Name = d12Name;
		this.d12ValueCurrentPeriod = d12ValueCurrentPeriod;
		this.d12ValueYtdDeductions = d12ValueYtdDeductions;
		this.d13Name = d13Name;
		this.d13ValueCurrentPeriod = d13ValueCurrentPeriod;
		this.d13ValueYtdDeductions = d13ValueYtdDeductions;
		this.d14Name = d14Name;
		this.d14ValueCurrentPeriod = d14ValueCurrentPeriod;
		this.d14ValueYtdDeductions = d14ValueYtdDeductions;
		this.d15Name = d15Name;
		this.d15ValueCurrentPeriod = d15ValueCurrentPeriod;
		this.d15ValueYtdDeductions = d15ValueYtdDeductions;
		this.d16Name = d16Name;
		this.d16ValueCurrentPeriod = d16ValueCurrentPeriod;
		this.d16ValueYtdDeductions = d16ValueYtdDeductions;
		this.d17Name = d17Name;
		this.d17ValueCurrentPeriod = d17ValueCurrentPeriod;
		this.d17ValueYtdDeductions = d17ValueYtdDeductions;
		this.leave1Name = leave1Name;
		this.leave1ValueOpening = leave1ValueOpening;
		this.leave1ValueAccured = leave1ValueAccured;
		this.leave1Availed = leave1Availed;
		this.leave1ValueBalance = leave1ValueBalance;
		this.leave2Name = leave2Name;
		this.leave2ValueOpening = leave2ValueOpening;
		this.leave2ValueAccured = leave2ValueAccured;
		this.leave2Availed = leave2Availed;
		this.leave2ValueBalance = leave2ValueBalance;
		this.leave3Name = leave3Name;
		this.leave3ValueOpening = leave3ValueOpening;
		this.leave3ValueAccured = leave3ValueAccured;
		this.leave3Availed = leave3Availed;
		this.leave3ValueBalance = leave3ValueBalance;
		this.leave4Name = leave4Name;
		this.leave4ValueOpening = leave4ValueOpening;
		this.leave4ValueAccured = leave4ValueAccured;
		this.leave4Availed = leave4Availed;
		this.leave4ValueBalance = leave4ValueBalance;
		this.remarksName = remarksName;
		this.remarksValue = remarksValue;
		this.netPayPeriodName = netPayPeriodName;
		this.netPayPeriodValue = netPayPeriodValue;
		this.netPayWordsName = netPayWordsName;
		this.netPayWordsValue = netPayWordsValue;
		this.pfAccumulationByEmployer = pfAccumulationByEmployer;
		this.pfOpeningValue = pfOpeningValue;
		this.pfClosingValue = pfClosingValue;
		this.pfBalanceValue = pfBalanceValue;
		this.isClean = isClean;
		this.status = status;
		this.uploadPayslipHdrId = uploadPayslipHdrId;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.remarks = remarks;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmployeeCode() {
		return employeeCode;
	}


	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getDoj() {
		return doj;
	}


	public void setDoj(String doj) {
		this.doj = doj;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getSpecialDesignation() {
		return specialDesignation;
	}


	public void setSpecialDesignation(String specialDesignation) {
		this.specialDesignation = specialDesignation;
	}


	public String getSkill() {
		return skill;
	}


	public void setSkill(String skill) {
		this.skill = skill;
	}


	public String getBusiness() {
		return business;
	}


	public void setBusiness(String business) {
		this.business = business;
	}


	public String getNatureOfPosition() {
		return natureOfPosition;
	}


	public void setNatureOfPosition(String natureOfPosition) {
		this.natureOfPosition = natureOfPosition;
	}


	public String getSalaryMonth() {
		return salaryMonth;
	}


	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}


	public String getSalaryYear() {
		return salaryYear;
	}


	public void setSalaryYear(String salaryYear) {
		this.salaryYear = salaryYear;
	}


	public String getLocationOfPosition() {
		return locationOfPosition;
	}


	public void setLocationOfPosition(String locationOfPosition) {
		this.locationOfPosition = locationOfPosition;
	}


	public String getPfNo() {
		return pfNo;
	}


	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}


	public String getEpsNo() {
		return epsNo;
	}


	public void setEpsNo(String epsNo) {
		this.epsNo = epsNo;
	}


	public String getEsiNo() {
		return esiNo;
	}


	public void setEsiNo(String esiNo) {
		this.esiNo = esiNo;
	}


	public String getBankAccountNumber() {
		return bankAccountNumber;
	}


	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}


	public String getBank() {
		return bank;
	}


	public void setBank(String bank) {
		this.bank = bank;
	}


	public String getIfscCode() {
		return ifscCode;
	}


	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


	public String getPanNo() {
		return panNo;
	}


	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}


	public String getAadharNo() {
		return aadharNo;
	}


	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}


	public String getUanNo() {
		return uanNo;
	}


	public void setUanNo(String uanNo) {
		this.uanNo = uanNo;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getPeriodDays() {
		return periodDays;
	}


	public void setPeriodDays(String periodDays) {
		this.periodDays = periodDays;
	}


	public String getPeriodHolidays() {
		return periodHolidays;
	}


	public void setPeriodHolidays(String periodHolidays) {
		this.periodHolidays = periodHolidays;
	}


	public String getWorkedDays() {
		return workedDays;
	}


	public void setWorkedDays(String workedDays) {
		this.workedDays = workedDays;
	}


	public String getLopAndLwop() {
		return lopAndLwop;
	}


	public void setLopAndLwop(String lopAndLwop) {
		this.lopAndLwop = lopAndLwop;
	}


	public String getArrearDays() {
		return arrearDays;
	}


	public void setArrearDays(String arrearDays) {
		this.arrearDays = arrearDays;
	}


	public String getLateDaysDeduction() {
		return lateDaysDeduction;
	}


	public void setLateDaysDeduction(String lateDaysDeduction) {
		this.lateDaysDeduction = lateDaysDeduction;
	}


	public String getOvertimeHours() {
		return overtimeHours;
	}


	public void setOvertimeHours(String overtimeHours) {
		this.overtimeHours = overtimeHours;
	}


	public String getWageDays() {
		return wageDays;
	}


	public void setWageDays(String wageDays) {
		this.wageDays = wageDays;
	}


	public String getAdditionalInfo1() {
		return additionalInfo1;
	}


	public void setAdditionalInfo1(String additionalInfo1) {
		this.additionalInfo1 = additionalInfo1;
	}


	public String getAdditionalInfo2() {
		return additionalInfo2;
	}


	public void setAdditionalInfo2(String additionalInfo2) {
		this.additionalInfo2 = additionalInfo2;
	}


	public String getAdditionalInfo3() {
		return additionalInfo3;
	}


	public void setAdditionalInfo3(String additionalInfo3) {
		this.additionalInfo3 = additionalInfo3;
	}


	public String getA11Name() {
		return a11Name;
	}


	public void setA11Name(String a11Name) {
		this.a11Name = a11Name;
	}


	public String getA11ValueEarningPeriod() {
		return a11ValueEarningPeriod;
	}


	public void setA11ValueEarningPeriod(String a11ValueEarningPeriod) {
		this.a11ValueEarningPeriod = a11ValueEarningPeriod;
	}


	public String getA11ValueArrears() {
		return a11ValueArrears;
	}


	public void setA11ValueArrears(String a11ValueArrears) {
		this.a11ValueArrears = a11ValueArrears;
	}


	public String getA11ValueCurrentPeriod() {
		return a11ValueCurrentPeriod;
	}


	public void setA11ValueCurrentPeriod(String a11ValueCurrentPeriod) {
		this.a11ValueCurrentPeriod = a11ValueCurrentPeriod;
	}


	public String getA11ValueYtdEarnings() {
		return a11ValueYtdEarnings;
	}


	public void setA11ValueYtdEarnings(String a11ValueYtdEarnings) {
		this.a11ValueYtdEarnings = a11ValueYtdEarnings;
	}


	public String getA12Name() {
		return a12Name;
	}


	public void setA12Name(String a12Name) {
		this.a12Name = a12Name;
	}


	public String getA12ValueEarningPeriod() {
		return a12ValueEarningPeriod;
	}


	public void setA12ValueEarningPeriod(String a12ValueEarningPeriod) {
		this.a12ValueEarningPeriod = a12ValueEarningPeriod;
	}


	public String getA12ValueArrears() {
		return a12ValueArrears;
	}


	public void setA12ValueArrears(String a12ValueArrears) {
		this.a12ValueArrears = a12ValueArrears;
	}


	public String getA12ValueCurrentPeriod() {
		return a12ValueCurrentPeriod;
	}


	public void setA12ValueCurrentPeriod(String a12ValueCurrentPeriod) {
		this.a12ValueCurrentPeriod = a12ValueCurrentPeriod;
	}


	public String getA12ValueYtdEarnings() {
		return a12ValueYtdEarnings;
	}


	public void setA12ValueYtdEarnings(String a12ValueYtdEarnings) {
		this.a12ValueYtdEarnings = a12ValueYtdEarnings;
	}


	public String getA13Name() {
		return a13Name;
	}


	public void setA13Name(String a13Name) {
		this.a13Name = a13Name;
	}


	public String getA13ValueEarningPeriod() {
		return a13ValueEarningPeriod;
	}


	public void setA13ValueEarningPeriod(String a13ValueEarningPeriod) {
		this.a13ValueEarningPeriod = a13ValueEarningPeriod;
	}


	public String getA13ValueArrears() {
		return a13ValueArrears;
	}


	public void setA13ValueArrears(String a13ValueArrears) {
		this.a13ValueArrears = a13ValueArrears;
	}


	public String getA13ValueCurrentPeriod() {
		return a13ValueCurrentPeriod;
	}


	public void setA13ValueCurrentPeriod(String a13ValueCurrentPeriod) {
		this.a13ValueCurrentPeriod = a13ValueCurrentPeriod;
	}


	public String getA13ValueYtdEarnings() {
		return a13ValueYtdEarnings;
	}


	public void setA13ValueYtdEarnings(String a13ValueYtdEarnings) {
		this.a13ValueYtdEarnings = a13ValueYtdEarnings;
	}


	public String getA14Name() {
		return a14Name;
	}


	public void setA14Name(String a14Name) {
		this.a14Name = a14Name;
	}


	public String getA14ValueEarningPeriod() {
		return a14ValueEarningPeriod;
	}


	public void setA14ValueEarningPeriod(String a14ValueEarningPeriod) {
		this.a14ValueEarningPeriod = a14ValueEarningPeriod;
	}


	public String getA14ValueArrears() {
		return a14ValueArrears;
	}


	public void setA14ValueArrears(String a14ValueArrears) {
		this.a14ValueArrears = a14ValueArrears;
	}


	public String getA14ValueCurrentPeriod() {
		return a14ValueCurrentPeriod;
	}


	public void setA14ValueCurrentPeriod(String a14ValueCurrentPeriod) {
		this.a14ValueCurrentPeriod = a14ValueCurrentPeriod;
	}


	public String getA14ValueYtdEarnings() {
		return a14ValueYtdEarnings;
	}


	public void setA14ValueYtdEarnings(String a14ValueYtdEarnings) {
		this.a14ValueYtdEarnings = a14ValueYtdEarnings;
	}


	public String getA15Name() {
		return a15Name;
	}


	public void setA15Name(String a15Name) {
		this.a15Name = a15Name;
	}


	public String getA15ValueEarningPeriod() {
		return a15ValueEarningPeriod;
	}


	public void setA15ValueEarningPeriod(String a15ValueEarningPeriod) {
		this.a15ValueEarningPeriod = a15ValueEarningPeriod;
	}


	public String getA15ValueArrears() {
		return a15ValueArrears;
	}


	public void setA15ValueArrears(String a15ValueArrears) {
		this.a15ValueArrears = a15ValueArrears;
	}


	public String getA15ValueCurrentPeriod() {
		return a15ValueCurrentPeriod;
	}


	public void setA15ValueCurrentPeriod(String a15ValueCurrentPeriod) {
		this.a15ValueCurrentPeriod = a15ValueCurrentPeriod;
	}


	public String getA15ValueYtdEarnings() {
		return a15ValueYtdEarnings;
	}


	public void setA15ValueYtdEarnings(String a15ValueYtdEarnings) {
		this.a15ValueYtdEarnings = a15ValueYtdEarnings;
	}


	public String getA16Name() {
		return a16Name;
	}


	public void setA16Name(String a16Name) {
		this.a16Name = a16Name;
	}


	public String getA16ValueEarningPeriod() {
		return a16ValueEarningPeriod;
	}


	public void setA16ValueEarningPeriod(String a16ValueEarningPeriod) {
		this.a16ValueEarningPeriod = a16ValueEarningPeriod;
	}


	public String getA16ValueArrears() {
		return a16ValueArrears;
	}


	public void setA16ValueArrears(String a16ValueArrears) {
		this.a16ValueArrears = a16ValueArrears;
	}


	public String getA16ValueCurrentPeriod() {
		return a16ValueCurrentPeriod;
	}


	public void setA16ValueCurrentPeriod(String a16ValueCurrentPeriod) {
		this.a16ValueCurrentPeriod = a16ValueCurrentPeriod;
	}


	public String getA16ValueYtdEarnings() {
		return a16ValueYtdEarnings;
	}


	public void setA16ValueYtdEarnings(String a16ValueYtdEarnings) {
		this.a16ValueYtdEarnings = a16ValueYtdEarnings;
	}


	public String getA17Name() {
		return a17Name;
	}


	public void setA17Name(String a17Name) {
		this.a17Name = a17Name;
	}


	public String getA17ValueEarningPeriod() {
		return a17ValueEarningPeriod;
	}


	public void setA17ValueEarningPeriod(String a17ValueEarningPeriod) {
		this.a17ValueEarningPeriod = a17ValueEarningPeriod;
	}


	public String getA17ValueArrears() {
		return a17ValueArrears;
	}


	public void setA17ValueArrears(String a17ValueArrears) {
		this.a17ValueArrears = a17ValueArrears;
	}


	public String getA17ValueCurrentPeriod() {
		return a17ValueCurrentPeriod;
	}


	public void setA17ValueCurrentPeriod(String a17ValueCurrentPeriod) {
		this.a17ValueCurrentPeriod = a17ValueCurrentPeriod;
	}


	public String getA17ValueYtdEarnings() {
		return a17ValueYtdEarnings;
	}


	public void setA17ValueYtdEarnings(String a17ValueYtdEarnings) {
		this.a17ValueYtdEarnings = a17ValueYtdEarnings;
	}


	public String getA18Name() {
		return a18Name;
	}


	public void setA18Name(String a18Name) {
		this.a18Name = a18Name;
	}


	public String getA18ValueEarningPeriod() {
		return a18ValueEarningPeriod;
	}


	public void setA18ValueEarningPeriod(String a18ValueEarningPeriod) {
		this.a18ValueEarningPeriod = a18ValueEarningPeriod;
	}


	public String getA18ValueArrears() {
		return a18ValueArrears;
	}


	public void setA18ValueArrears(String a18ValueArrears) {
		this.a18ValueArrears = a18ValueArrears;
	}


	public String getA18ValueCurrentPeriod() {
		return a18ValueCurrentPeriod;
	}


	public void setA18ValueCurrentPeriod(String a18ValueCurrentPeriod) {
		this.a18ValueCurrentPeriod = a18ValueCurrentPeriod;
	}


	public String getA18ValueYtdEarnings() {
		return a18ValueYtdEarnings;
	}


	public void setA18ValueYtdEarnings(String a18ValueYtdEarnings) {
		this.a18ValueYtdEarnings = a18ValueYtdEarnings;
	}


	public String getA19Name() {
		return a19Name;
	}


	public void setA19Name(String a19Name) {
		this.a19Name = a19Name;
	}


	public String getA19ValueEarningPeriod() {
		return a19ValueEarningPeriod;
	}


	public void setA19ValueEarningPeriod(String a19ValueEarningPeriod) {
		this.a19ValueEarningPeriod = a19ValueEarningPeriod;
	}


	public String getA19ValueArrears() {
		return a19ValueArrears;
	}


	public void setA19ValueArrears(String a19ValueArrears) {
		this.a19ValueArrears = a19ValueArrears;
	}


	public String getA19ValueCurrentPeriod() {
		return a19ValueCurrentPeriod;
	}


	public void setA19ValueCurrentPeriod(String a19ValueCurrentPeriod) {
		this.a19ValueCurrentPeriod = a19ValueCurrentPeriod;
	}


	public String getA19ValueYtdEarnings() {
		return a19ValueYtdEarnings;
	}


	public void setA19ValueYtdEarnings(String a19ValueYtdEarnings) {
		this.a19ValueYtdEarnings = a19ValueYtdEarnings;
	}


	public String getA21Name() {
		return a21Name;
	}


	public void setA21Name(String a21Name) {
		this.a21Name = a21Name;
	}


	public String getA21ValueEarningPeriod() {
		return a21ValueEarningPeriod;
	}


	public void setA21ValueEarningPeriod(String a21ValueEarningPeriod) {
		this.a21ValueEarningPeriod = a21ValueEarningPeriod;
	}


	public String getA21ValueArrears() {
		return a21ValueArrears;
	}


	public void setA21ValueArrears(String a21ValueArrears) {
		this.a21ValueArrears = a21ValueArrears;
	}


	public String getA21ValueCurrentPeriod() {
		return a21ValueCurrentPeriod;
	}


	public void setA21ValueCurrentPeriod(String a21ValueCurrentPeriod) {
		this.a21ValueCurrentPeriod = a21ValueCurrentPeriod;
	}


	public String getA21ValueYtdEarnings() {
		return a21ValueYtdEarnings;
	}


	public void setA21ValueYtdEarnings(String a21ValueYtdEarnings) {
		this.a21ValueYtdEarnings = a21ValueYtdEarnings;
	}


	public String getA22Name() {
		return a22Name;
	}


	public void setA22Name(String a22Name) {
		this.a22Name = a22Name;
	}


	public String getA22ValueEarningPeriod() {
		return a22ValueEarningPeriod;
	}


	public void setA22ValueEarningPeriod(String a22ValueEarningPeriod) {
		this.a22ValueEarningPeriod = a22ValueEarningPeriod;
	}


	public String getA22ValueArrears() {
		return a22ValueArrears;
	}


	public void setA22ValueArrears(String a22ValueArrears) {
		this.a22ValueArrears = a22ValueArrears;
	}


	public String getA22ValueCurrentPeriod() {
		return a22ValueCurrentPeriod;
	}


	public void setA22ValueCurrentPeriod(String a22ValueCurrentPeriod) {
		this.a22ValueCurrentPeriod = a22ValueCurrentPeriod;
	}


	public String getA22ValueYtdEarnings() {
		return a22ValueYtdEarnings;
	}


	public void setA22ValueYtdEarnings(String a22ValueYtdEarnings) {
		this.a22ValueYtdEarnings = a22ValueYtdEarnings;
	}


	public String getA23Name() {
		return a23Name;
	}


	public void setA23Name(String a23Name) {
		this.a23Name = a23Name;
	}


	public String getA23ValueEarningPeriod() {
		return a23ValueEarningPeriod;
	}


	public void setA23ValueEarningPeriod(String a23ValueEarningPeriod) {
		this.a23ValueEarningPeriod = a23ValueEarningPeriod;
	}


	public String getA23ValueArrears() {
		return a23ValueArrears;
	}


	public void setA23ValueArrears(String a23ValueArrears) {
		this.a23ValueArrears = a23ValueArrears;
	}


	public String getA23ValueCurrentPeriod() {
		return a23ValueCurrentPeriod;
	}


	public void setA23ValueCurrentPeriod(String a23ValueCurrentPeriod) {
		this.a23ValueCurrentPeriod = a23ValueCurrentPeriod;
	}


	public String getA23ValueYtdEarnings() {
		return a23ValueYtdEarnings;
	}


	public void setA23ValueYtdEarnings(String a23ValueYtdEarnings) {
		this.a23ValueYtdEarnings = a23ValueYtdEarnings;
	}


	public String getA24Name() {
		return a24Name;
	}


	public void setA24Name(String a24Name) {
		this.a24Name = a24Name;
	}


	public String getA24ValueEarningPeriod() {
		return a24ValueEarningPeriod;
	}


	public void setA24ValueEarningPeriod(String a24ValueEarningPeriod) {
		this.a24ValueEarningPeriod = a24ValueEarningPeriod;
	}


	public String getA24ValueArrears() {
		return a24ValueArrears;
	}


	public void setA24ValueArrears(String a24ValueArrears) {
		this.a24ValueArrears = a24ValueArrears;
	}


	public String getA24ValueCurrentPeriod() {
		return a24ValueCurrentPeriod;
	}


	public void setA24ValueCurrentPeriod(String a24ValueCurrentPeriod) {
		this.a24ValueCurrentPeriod = a24ValueCurrentPeriod;
	}


	public String getA24ValueYtdEarnings() {
		return a24ValueYtdEarnings;
	}


	public void setA24ValueYtdEarnings(String a24ValueYtdEarnings) {
		this.a24ValueYtdEarnings = a24ValueYtdEarnings;
	}


	public String getA25Name() {
		return a25Name;
	}


	public void setA25Name(String a25Name) {
		this.a25Name = a25Name;
	}


	public String getA25ValueEarningPeriod() {
		return a25ValueEarningPeriod;
	}


	public void setA25ValueEarningPeriod(String a25ValueEarningPeriod) {
		this.a25ValueEarningPeriod = a25ValueEarningPeriod;
	}


	public String getA25ValueArrears() {
		return a25ValueArrears;
	}


	public void setA25ValueArrears(String a25ValueArrears) {
		this.a25ValueArrears = a25ValueArrears;
	}


	public String getA25ValueCurrentPeriod() {
		return a25ValueCurrentPeriod;
	}


	public void setA25ValueCurrentPeriod(String a25ValueCurrentPeriod) {
		this.a25ValueCurrentPeriod = a25ValueCurrentPeriod;
	}


	public String getA25ValueYtdEarnings() {
		return a25ValueYtdEarnings;
	}


	public void setA25ValueYtdEarnings(String a25ValueYtdEarnings) {
		this.a25ValueYtdEarnings = a25ValueYtdEarnings;
	}


	public String getA26Name() {
		return a26Name;
	}


	public void setA26Name(String a26Name) {
		this.a26Name = a26Name;
	}


	public String getA26ValueEarningPeriod() {
		return a26ValueEarningPeriod;
	}


	public void setA26ValueEarningPeriod(String a26ValueEarningPeriod) {
		this.a26ValueEarningPeriod = a26ValueEarningPeriod;
	}


	public String getA26ValueArrears() {
		return a26ValueArrears;
	}


	public void setA26ValueArrears(String a26ValueArrears) {
		this.a26ValueArrears = a26ValueArrears;
	}


	public String getA26ValueCurrentPeriod() {
		return a26ValueCurrentPeriod;
	}


	public void setA26ValueCurrentPeriod(String a26ValueCurrentPeriod) {
		this.a26ValueCurrentPeriod = a26ValueCurrentPeriod;
	}


	public String getA26ValueYtdEarnings() {
		return a26ValueYtdEarnings;
	}


	public void setA26ValueYtdEarnings(String a26ValueYtdEarnings) {
		this.a26ValueYtdEarnings = a26ValueYtdEarnings;
	}


	public String getA27Name() {
		return a27Name;
	}


	public void setA27Name(String a27Name) {
		this.a27Name = a27Name;
	}


	public String getA27ValueEarningPeriod() {
		return a27ValueEarningPeriod;
	}


	public void setA27ValueEarningPeriod(String a27ValueEarningPeriod) {
		this.a27ValueEarningPeriod = a27ValueEarningPeriod;
	}


	public String getA27ValueArrears() {
		return a27ValueArrears;
	}


	public void setA27ValueArrears(String a27ValueArrears) {
		this.a27ValueArrears = a27ValueArrears;
	}


	public String getA27ValueCurrentPeriod() {
		return a27ValueCurrentPeriod;
	}


	public void setA27ValueCurrentPeriod(String a27ValueCurrentPeriod) {
		this.a27ValueCurrentPeriod = a27ValueCurrentPeriod;
	}


	public String getA27ValueYtdEarnings() {
		return a27ValueYtdEarnings;
	}


	public void setA27ValueYtdEarnings(String a27ValueYtdEarnings) {
		this.a27ValueYtdEarnings = a27ValueYtdEarnings;
	}


	public String getB11Name() {
		return b11Name;
	}


	public void setB11Name(String b11Name) {
		this.b11Name = b11Name;
	}


	public String getB11ValueEarningPeriod() {
		return b11ValueEarningPeriod;
	}


	public void setB11ValueEarningPeriod(String b11ValueEarningPeriod) {
		this.b11ValueEarningPeriod = b11ValueEarningPeriod;
	}


	public String getB11ValueArrears() {
		return b11ValueArrears;
	}


	public void setB11ValueArrears(String b11ValueArrears) {
		this.b11ValueArrears = b11ValueArrears;
	}


	public String getB11ValueCurrentPeriod() {
		return b11ValueCurrentPeriod;
	}


	public void setB11ValueCurrentPeriod(String b11ValueCurrentPeriod) {
		this.b11ValueCurrentPeriod = b11ValueCurrentPeriod;
	}


	public String getB11ValueYtdEarnings() {
		return b11ValueYtdEarnings;
	}


	public void setB11ValueYtdEarnings(String b11ValueYtdEarnings) {
		this.b11ValueYtdEarnings = b11ValueYtdEarnings;
	}


	public String getB12Name() {
		return b12Name;
	}


	public void setB12Name(String b12Name) {
		this.b12Name = b12Name;
	}


	public String getB12ValueEarningPeriod() {
		return b12ValueEarningPeriod;
	}


	public void setB12ValueEarningPeriod(String b12ValueEarningPeriod) {
		this.b12ValueEarningPeriod = b12ValueEarningPeriod;
	}


	public String getB12ValueArrears() {
		return b12ValueArrears;
	}


	public void setB12ValueArrears(String b12ValueArrears) {
		this.b12ValueArrears = b12ValueArrears;
	}


	public String getB12ValueCurrentPeriod() {
		return b12ValueCurrentPeriod;
	}


	public void setB12ValueCurrentPeriod(String b12ValueCurrentPeriod) {
		this.b12ValueCurrentPeriod = b12ValueCurrentPeriod;
	}


	public String getB12ValueYtdEarnings() {
		return b12ValueYtdEarnings;
	}


	public void setB12ValueYtdEarnings(String b12ValueYtdEarnings) {
		this.b12ValueYtdEarnings = b12ValueYtdEarnings;
	}


	public String getB13Name() {
		return b13Name;
	}


	public void setB13Name(String b13Name) {
		this.b13Name = b13Name;
	}


	public String getB13ValueEarningPeriod() {
		return b13ValueEarningPeriod;
	}


	public void setB13ValueEarningPeriod(String b13ValueEarningPeriod) {
		this.b13ValueEarningPeriod = b13ValueEarningPeriod;
	}


	public String getB13ValueArrears() {
		return b13ValueArrears;
	}


	public void setB13ValueArrears(String b13ValueArrears) {
		this.b13ValueArrears = b13ValueArrears;
	}


	public String getB13ValueCurrentPeriod() {
		return b13ValueCurrentPeriod;
	}


	public void setB13ValueCurrentPeriod(String b13ValueCurrentPeriod) {
		this.b13ValueCurrentPeriod = b13ValueCurrentPeriod;
	}


	public String getB13ValueYtdEarnings() {
		return b13ValueYtdEarnings;
	}


	public void setB13ValueYtdEarnings(String b13ValueYtdEarnings) {
		this.b13ValueYtdEarnings = b13ValueYtdEarnings;
	}


	public String getB14Name() {
		return b14Name;
	}


	public void setB14Name(String b14Name) {
		this.b14Name = b14Name;
	}


	public String getB14ValueEarningPeriod() {
		return b14ValueEarningPeriod;
	}


	public void setB14ValueEarningPeriod(String b14ValueEarningPeriod) {
		this.b14ValueEarningPeriod = b14ValueEarningPeriod;
	}


	public String getB14ValueArrears() {
		return b14ValueArrears;
	}


	public void setB14ValueArrears(String b14ValueArrears) {
		this.b14ValueArrears = b14ValueArrears;
	}


	public String getB14ValueCurrentPeriod() {
		return b14ValueCurrentPeriod;
	}


	public void setB14ValueCurrentPeriod(String b14ValueCurrentPeriod) {
		this.b14ValueCurrentPeriod = b14ValueCurrentPeriod;
	}


	public String getB14ValueYtdEarnings() {
		return b14ValueYtdEarnings;
	}


	public void setB14ValueYtdEarnings(String b14ValueYtdEarnings) {
		this.b14ValueYtdEarnings = b14ValueYtdEarnings;
	}


	public String getD11Name() {
		return d11Name;
	}


	public void setD11Name(String d11Name) {
		this.d11Name = d11Name;
	}


	public String getD11ValueCurrentPeriod() {
		return d11ValueCurrentPeriod;
	}


	public void setD11ValueCurrentPeriod(String d11ValueCurrentPeriod) {
		this.d11ValueCurrentPeriod = d11ValueCurrentPeriod;
	}


	public String getD11ValueYtdDeductions() {
		return d11ValueYtdDeductions;
	}


	public void setD11ValueYtdDeductions(String d11ValueYtdDeductions) {
		this.d11ValueYtdDeductions = d11ValueYtdDeductions;
	}


	public String getD12Name() {
		return d12Name;
	}


	public void setD12Name(String d12Name) {
		this.d12Name = d12Name;
	}


	public String getD12ValueCurrentPeriod() {
		return d12ValueCurrentPeriod;
	}


	public void setD12ValueCurrentPeriod(String d12ValueCurrentPeriod) {
		this.d12ValueCurrentPeriod = d12ValueCurrentPeriod;
	}


	public String getD12ValueYtdDeductions() {
		return d12ValueYtdDeductions;
	}


	public void setD12ValueYtdDeductions(String d12ValueYtdDeductions) {
		this.d12ValueYtdDeductions = d12ValueYtdDeductions;
	}


	public String getD13Name() {
		return d13Name;
	}


	public void setD13Name(String d13Name) {
		this.d13Name = d13Name;
	}


	public String getD13ValueCurrentPeriod() {
		return d13ValueCurrentPeriod;
	}


	public void setD13ValueCurrentPeriod(String d13ValueCurrentPeriod) {
		this.d13ValueCurrentPeriod = d13ValueCurrentPeriod;
	}


	public String getD13ValueYtdDeductions() {
		return d13ValueYtdDeductions;
	}


	public void setD13ValueYtdDeductions(String d13ValueYtdDeductions) {
		this.d13ValueYtdDeductions = d13ValueYtdDeductions;
	}


	public String getD14Name() {
		return d14Name;
	}


	public void setD14Name(String d14Name) {
		this.d14Name = d14Name;
	}


	public String getD14ValueCurrentPeriod() {
		return d14ValueCurrentPeriod;
	}


	public void setD14ValueCurrentPeriod(String d14ValueCurrentPeriod) {
		this.d14ValueCurrentPeriod = d14ValueCurrentPeriod;
	}


	public String getD14ValueYtdDeductions() {
		return d14ValueYtdDeductions;
	}


	public void setD14ValueYtdDeductions(String d14ValueYtdDeductions) {
		this.d14ValueYtdDeductions = d14ValueYtdDeductions;
	}


	public String getD15Name() {
		return d15Name;
	}


	public void setD15Name(String d15Name) {
		this.d15Name = d15Name;
	}


	public String getD15ValueCurrentPeriod() {
		return d15ValueCurrentPeriod;
	}


	public void setD15ValueCurrentPeriod(String d15ValueCurrentPeriod) {
		this.d15ValueCurrentPeriod = d15ValueCurrentPeriod;
	}


	public String getD15ValueYtdDeductions() {
		return d15ValueYtdDeductions;
	}


	public void setD15ValueYtdDeductions(String d15ValueYtdDeductions) {
		this.d15ValueYtdDeductions = d15ValueYtdDeductions;
	}


	public String getD16Name() {
		return d16Name;
	}


	public void setD16Name(String d16Name) {
		this.d16Name = d16Name;
	}


	public String getD16ValueCurrentPeriod() {
		return d16ValueCurrentPeriod;
	}


	public void setD16ValueCurrentPeriod(String d16ValueCurrentPeriod) {
		this.d16ValueCurrentPeriod = d16ValueCurrentPeriod;
	}


	public String getD16ValueYtdDeductions() {
		return d16ValueYtdDeductions;
	}


	public void setD16ValueYtdDeductions(String d16ValueYtdDeductions) {
		this.d16ValueYtdDeductions = d16ValueYtdDeductions;
	}


	public String getD17Name() {
		return d17Name;
	}


	public void setD17Name(String d17Name) {
		this.d17Name = d17Name;
	}


	public String getD17ValueCurrentPeriod() {
		return d17ValueCurrentPeriod;
	}


	public void setD17ValueCurrentPeriod(String d17ValueCurrentPeriod) {
		this.d17ValueCurrentPeriod = d17ValueCurrentPeriod;
	}


	public String getD17ValueYtdDeductions() {
		return d17ValueYtdDeductions;
	}


	public void setD17ValueYtdDeductions(String d17ValueYtdDeductions) {
		this.d17ValueYtdDeductions = d17ValueYtdDeductions;
	}


	public String getLeave1Name() {
		return leave1Name;
	}


	public void setLeave1Name(String leave1Name) {
		this.leave1Name = leave1Name;
	}


	public String getLeave1ValueOpening() {
		return leave1ValueOpening;
	}


	public void setLeave1ValueOpening(String leave1ValueOpening) {
		this.leave1ValueOpening = leave1ValueOpening;
	}


	public String getLeave1ValueAccured() {
		return leave1ValueAccured;
	}


	public void setLeave1ValueAccured(String leave1ValueAccured) {
		this.leave1ValueAccured = leave1ValueAccured;
	}


	public String getLeave1Availed() {
		return leave1Availed;
	}


	public void setLeave1Availed(String leave1Availed) {
		this.leave1Availed = leave1Availed;
	}


	public String getLeave1ValueBalance() {
		return leave1ValueBalance;
	}


	public void setLeave1ValueBalance(String leave1ValueBalance) {
		this.leave1ValueBalance = leave1ValueBalance;
	}


	public String getLeave2Name() {
		return leave2Name;
	}


	public void setLeave2Name(String leave2Name) {
		this.leave2Name = leave2Name;
	}


	public String getLeave2ValueOpening() {
		return leave2ValueOpening;
	}


	public void setLeave2ValueOpening(String leave2ValueOpening) {
		this.leave2ValueOpening = leave2ValueOpening;
	}


	public String getLeave2ValueAccured() {
		return leave2ValueAccured;
	}


	public void setLeave2ValueAccured(String leave2ValueAccured) {
		this.leave2ValueAccured = leave2ValueAccured;
	}


	public String getLeave2Availed() {
		return leave2Availed;
	}


	public void setLeave2Availed(String leave2Availed) {
		this.leave2Availed = leave2Availed;
	}


	public String getLeave2ValueBalance() {
		return leave2ValueBalance;
	}


	public void setLeave2ValueBalance(String leave2ValueBalance) {
		this.leave2ValueBalance = leave2ValueBalance;
	}


	public String getLeave3Name() {
		return leave3Name;
	}


	public void setLeave3Name(String leave3Name) {
		this.leave3Name = leave3Name;
	}


	public String getLeave3ValueOpening() {
		return leave3ValueOpening;
	}


	public void setLeave3ValueOpening(String leave3ValueOpening) {
		this.leave3ValueOpening = leave3ValueOpening;
	}


	public String getLeave3ValueAccured() {
		return leave3ValueAccured;
	}


	public void setLeave3ValueAccured(String leave3ValueAccured) {
		this.leave3ValueAccured = leave3ValueAccured;
	}


	public String getLeave3Availed() {
		return leave3Availed;
	}


	public void setLeave3Availed(String leave3Availed) {
		this.leave3Availed = leave3Availed;
	}


	public String getLeave3ValueBalance() {
		return leave3ValueBalance;
	}


	public void setLeave3ValueBalance(String leave3ValueBalance) {
		this.leave3ValueBalance = leave3ValueBalance;
	}


	public String getLeave4Name() {
		return leave4Name;
	}


	public void setLeave4Name(String leave4Name) {
		this.leave4Name = leave4Name;
	}


	public String getLeave4ValueOpening() {
		return leave4ValueOpening;
	}


	public void setLeave4ValueOpening(String leave4ValueOpening) {
		this.leave4ValueOpening = leave4ValueOpening;
	}


	public String getLeave4ValueAccured() {
		return leave4ValueAccured;
	}


	public void setLeave4ValueAccured(String leave4ValueAccured) {
		this.leave4ValueAccured = leave4ValueAccured;
	}


	public String getLeave4Availed() {
		return leave4Availed;
	}


	public void setLeave4Availed(String leave4Availed) {
		this.leave4Availed = leave4Availed;
	}


	public String getLeave4ValueBalance() {
		return leave4ValueBalance;
	}


	public void setLeave4ValueBalance(String leave4ValueBalance) {
		this.leave4ValueBalance = leave4ValueBalance;
	}


	public String getRemarksName() {
		return remarksName;
	}


	public void setRemarksName(String remarksName) {
		this.remarksName = remarksName;
	}


	public String getRemarksValue() {
		return remarksValue;
	}


	public void setRemarksValue(String remarksValue) {
		this.remarksValue = remarksValue;
	}


	public String getNetPayPeriodName() {
		return netPayPeriodName;
	}


	public void setNetPayPeriodName(String netPayPeriodName) {
		this.netPayPeriodName = netPayPeriodName;
	}


	public String getNetPayPeriodValue() {
		return netPayPeriodValue;
	}


	public void setNetPayPeriodValue(String netPayPeriodValue) {
		this.netPayPeriodValue = netPayPeriodValue;
	}


	public String getNetPayWordsName() {
		return netPayWordsName;
	}


	public void setNetPayWordsName(String netPayWordsName) {
		this.netPayWordsName = netPayWordsName;
	}


	public String getNetPayWordsValue() {
		return netPayWordsValue;
	}


	public void setNetPayWordsValue(String netPayWordsValue) {
		this.netPayWordsValue = netPayWordsValue;
	}


	public String getPfAccumulationByEmployer() {
		return pfAccumulationByEmployer;
	}


	public void setPfAccumulationByEmployer(String pfAccumulationByEmployer) {
		this.pfAccumulationByEmployer = pfAccumulationByEmployer;
	}


	public String getPfOpeningValue() {
		return pfOpeningValue;
	}


	public void setPfOpeningValue(String pfOpeningValue) {
		this.pfOpeningValue = pfOpeningValue;
	}


	public String getPfClosingValue() {
		return pfClosingValue;
	}


	public void setPfClosingValue(String pfClosingValue) {
		this.pfClosingValue = pfClosingValue;
	}


	public String getPfBalanceValue() {
		return pfBalanceValue;
	}


	public void setPfBalanceValue(String pfBalanceValue) {
		this.pfBalanceValue = pfBalanceValue;
	}


	public String getIsClean() {
		return isClean;
	}


	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getUploadPayslipHdrId() {
		return uploadPayslipHdrId;
	}


	public void setUploadPayslipHdrId(Integer uploadPayslipHdrId) {
		this.uploadPayslipHdrId = uploadPayslipHdrId;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public LocalDateTime getCreatedDt() {
		return createdDt;
	}


	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}


	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}


	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	@Override
	public String toString() {
		return "UploadPayslipLine [id=" + id + ", employeeCode=" + employeeCode + ", employeeName=" + employeeName
				+ ", email=" + email + ", mobileNo=" + mobileNo + ", dob=" + dob + ", doj=" + doj + ", department="
				+ department + ", designation=" + designation + ", grade=" + grade + ", specialDesignation="
				+ specialDesignation + ", skill=" + skill + ", business=" + business + ", natureOfPosition="
				+ natureOfPosition + ", salaryMonth=" + salaryMonth + ", salaryYear=" + salaryYear
				+ ", locationOfPosition=" + locationOfPosition + ", pfNo=" + pfNo + ", epsNo=" + epsNo + ", esiNo="
				+ esiNo + ", bankAccountNumber=" + bankAccountNumber + ", bank=" + bank + ", ifscCode=" + ifscCode
				+ ", panNo=" + panNo + ", aadharNo=" + aadharNo + ", uanNo=" + uanNo + ", currency=" + currency
				+ ", periodDays=" + periodDays + ", periodHolidays=" + periodHolidays + ", workedDays=" + workedDays
				+ ", lopAndLwop=" + lopAndLwop + ", arrearDays=" + arrearDays + ", lateDaysDeduction="
				+ lateDaysDeduction + ", overtimeHours=" + overtimeHours + ", wageDays=" + wageDays
				+ ", additionalInfo1=" + additionalInfo1 + ", additionalInfo2=" + additionalInfo2 + ", additionalInfo3="
				+ additionalInfo3 + ", a11Name=" + a11Name + ", a11ValueEarningPeriod=" + a11ValueEarningPeriod
				+ ", a11ValueArrears=" + a11ValueArrears + ", a11ValueCurrentPeriod=" + a11ValueCurrentPeriod
				+ ", a11ValueYtdEarnings=" + a11ValueYtdEarnings + ", a12Name=" + a12Name + ", a12ValueEarningPeriod="
				+ a12ValueEarningPeriod + ", a12ValueArrears=" + a12ValueArrears + ", a12ValueCurrentPeriod="
				+ a12ValueCurrentPeriod + ", a12ValueYtdEarnings=" + a12ValueYtdEarnings + ", a13Name=" + a13Name
				+ ", a13ValueEarningPeriod=" + a13ValueEarningPeriod + ", a13ValueArrears=" + a13ValueArrears
				+ ", a13ValueCurrentPeriod=" + a13ValueCurrentPeriod + ", a13ValueYtdEarnings=" + a13ValueYtdEarnings
				+ ", a14Name=" + a14Name + ", a14ValueEarningPeriod=" + a14ValueEarningPeriod + ", a14ValueArrears="
				+ a14ValueArrears + ", a14ValueCurrentPeriod=" + a14ValueCurrentPeriod + ", a14ValueYtdEarnings="
				+ a14ValueYtdEarnings + ", a15Name=" + a15Name + ", a15ValueEarningPeriod=" + a15ValueEarningPeriod
				+ ", a15ValueArrears=" + a15ValueArrears + ", a15ValueCurrentPeriod=" + a15ValueCurrentPeriod
				+ ", a15ValueYtdEarnings=" + a15ValueYtdEarnings + ", a16Name=" + a16Name + ", a16ValueEarningPeriod="
				+ a16ValueEarningPeriod + ", a16ValueArrears=" + a16ValueArrears + ", a16ValueCurrentPeriod="
				+ a16ValueCurrentPeriod + ", a16ValueYtdEarnings=" + a16ValueYtdEarnings + ", a17Name=" + a17Name
				+ ", a17ValueEarningPeriod=" + a17ValueEarningPeriod + ", a17ValueArrears=" + a17ValueArrears
				+ ", a17ValueCurrentPeriod=" + a17ValueCurrentPeriod + ", a17ValueYtdEarnings=" + a17ValueYtdEarnings
				+ ", a18Name=" + a18Name + ", a18ValueEarningPeriod=" + a18ValueEarningPeriod + ", a18ValueArrears="
				+ a18ValueArrears + ", a18ValueCurrentPeriod=" + a18ValueCurrentPeriod + ", a18ValueYtdEarnings="
				+ a18ValueYtdEarnings + ", a19Name=" + a19Name + ", a19ValueEarningPeriod=" + a19ValueEarningPeriod
				+ ", a19ValueArrears=" + a19ValueArrears + ", a19ValueCurrentPeriod=" + a19ValueCurrentPeriod
				+ ", a19ValueYtdEarnings=" + a19ValueYtdEarnings + ", a21Name=" + a21Name + ", a21ValueEarningPeriod="
				+ a21ValueEarningPeriod + ", a21ValueArrears=" + a21ValueArrears + ", a21ValueCurrentPeriod="
				+ a21ValueCurrentPeriod + ", a21ValueYtdEarnings=" + a21ValueYtdEarnings + ", a22Name=" + a22Name
				+ ", a22ValueEarningPeriod=" + a22ValueEarningPeriod + ", a22ValueArrears=" + a22ValueArrears
				+ ", a22ValueCurrentPeriod=" + a22ValueCurrentPeriod + ", a22ValueYtdEarnings=" + a22ValueYtdEarnings
				+ ", a23Name=" + a23Name + ", a23ValueEarningPeriod=" + a23ValueEarningPeriod + ", a23ValueArrears="
				+ a23ValueArrears + ", a23ValueCurrentPeriod=" + a23ValueCurrentPeriod + ", a23ValueYtdEarnings="
				+ a23ValueYtdEarnings + ", a24Name=" + a24Name + ", a24ValueEarningPeriod=" + a24ValueEarningPeriod
				+ ", a24ValueArrears=" + a24ValueArrears + ", a24ValueCurrentPeriod=" + a24ValueCurrentPeriod
				+ ", a24ValueYtdEarnings=" + a24ValueYtdEarnings + ", a25Name=" + a25Name + ", a25ValueEarningPeriod="
				+ a25ValueEarningPeriod + ", a25ValueArrears=" + a25ValueArrears + ", a25ValueCurrentPeriod="
				+ a25ValueCurrentPeriod + ", a25ValueYtdEarnings=" + a25ValueYtdEarnings + ", a26Name=" + a26Name
				+ ", a26ValueEarningPeriod=" + a26ValueEarningPeriod + ", a26ValueArrears=" + a26ValueArrears
				+ ", a26ValueCurrentPeriod=" + a26ValueCurrentPeriod + ", a26ValueYtdEarnings=" + a26ValueYtdEarnings
				+ ", a27Name=" + a27Name + ", a27ValueEarningPeriod=" + a27ValueEarningPeriod + ", a27ValueArrears="
				+ a27ValueArrears + ", a27ValueCurrentPeriod=" + a27ValueCurrentPeriod + ", a27ValueYtdEarnings="
				+ a27ValueYtdEarnings + ", b11Name=" + b11Name + ", b11ValueEarningPeriod=" + b11ValueEarningPeriod
				+ ", b11ValueArrears=" + b11ValueArrears + ", b11ValueCurrentPeriod=" + b11ValueCurrentPeriod
				+ ", b11ValueYtdEarnings=" + b11ValueYtdEarnings + ", b12Name=" + b12Name + ", b12ValueEarningPeriod="
				+ b12ValueEarningPeriod + ", b12ValueArrears=" + b12ValueArrears + ", b12ValueCurrentPeriod="
				+ b12ValueCurrentPeriod + ", b12ValueYtdEarnings=" + b12ValueYtdEarnings + ", b13Name=" + b13Name
				+ ", b13ValueEarningPeriod=" + b13ValueEarningPeriod + ", b13ValueArrears=" + b13ValueArrears
				+ ", b13ValueCurrentPeriod=" + b13ValueCurrentPeriod + ", b13ValueYtdEarnings=" + b13ValueYtdEarnings
				+ ", b14Name=" + b14Name + ", b14ValueEarningPeriod=" + b14ValueEarningPeriod + ", b14ValueArrears="
				+ b14ValueArrears + ", b14ValueCurrentPeriod=" + b14ValueCurrentPeriod + ", b14ValueYtdEarnings="
				+ b14ValueYtdEarnings + ", d11Name=" + d11Name + ", d11ValueCurrentPeriod=" + d11ValueCurrentPeriod
				+ ", d11ValueYtdDeductions=" + d11ValueYtdDeductions + ", d12Name=" + d12Name
				+ ", d12ValueCurrentPeriod=" + d12ValueCurrentPeriod + ", d12ValueYtdDeductions="
				+ d12ValueYtdDeductions + ", d13Name=" + d13Name + ", d13ValueCurrentPeriod=" + d13ValueCurrentPeriod
				+ ", d13ValueYtdDeductions=" + d13ValueYtdDeductions + ", d14Name=" + d14Name
				+ ", d14ValueCurrentPeriod=" + d14ValueCurrentPeriod + ", d14ValueYtdDeductions="
				+ d14ValueYtdDeductions + ", d15Name=" + d15Name + ", d15ValueCurrentPeriod=" + d15ValueCurrentPeriod
				+ ", d15ValueYtdDeductions=" + d15ValueYtdDeductions + ", d16Name=" + d16Name
				+ ", d16ValueCurrentPeriod=" + d16ValueCurrentPeriod + ", d16ValueYtdDeductions="
				+ d16ValueYtdDeductions + ", d17Name=" + d17Name + ", d17ValueCurrentPeriod=" + d17ValueCurrentPeriod
				+ ", d17ValueYtdDeductions=" + d17ValueYtdDeductions + ", leave1Name=" + leave1Name
				+ ", leave1ValueOpening=" + leave1ValueOpening + ", leave1ValueAccured=" + leave1ValueAccured
				+ ", leave1Availed=" + leave1Availed + ", leave1ValueBalance=" + leave1ValueBalance + ", leave2Name="
				+ leave2Name + ", leave2ValueOpening=" + leave2ValueOpening + ", leave2ValueAccured="
				+ leave2ValueAccured + ", leave2Availed=" + leave2Availed + ", leave2ValueBalance=" + leave2ValueBalance
				+ ", leave3Name=" + leave3Name + ", leave3ValueOpening=" + leave3ValueOpening + ", leave3ValueAccured="
				+ leave3ValueAccured + ", leave3Availed=" + leave3Availed + ", leave3ValueBalance=" + leave3ValueBalance
				+ ", leave4Name=" + leave4Name + ", leave4ValueOpening=" + leave4ValueOpening + ", leave4ValueAccured="
				+ leave4ValueAccured + ", leave4Availed=" + leave4Availed + ", leave4ValueBalance=" + leave4ValueBalance
				+ ", remarksName=" + remarksName + ", remarksValue=" + remarksValue + ", netPayPeriodName="
				+ netPayPeriodName + ", netPayPeriodValue=" + netPayPeriodValue + ", netPayWordsName=" + netPayWordsName
				+ ", netPayWordsValue=" + netPayWordsValue + ", pfAccumulationByEmployer=" + pfAccumulationByEmployer
				+ ", pfOpeningValue=" + pfOpeningValue + ", pfClosingValue=" + pfClosingValue + ", pfBalanceValue="
				+ pfBalanceValue + ", isClean=" + isClean + ", status=" + status + ", uploadPayslipHdrId="
				+ uploadPayslipHdrId + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt="
				+ createdDt + ", modifiedDt=" + modifiedDt + ", remarks=" + remarks + "]";
	}


	

	
	
	
	

}
