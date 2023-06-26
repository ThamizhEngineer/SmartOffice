package com.ss.smartoffice.shared.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="t_emp_payout")



@Component
@Scope("prototype")
public class EmployeePayout extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String employeeId;
//	@Formula("(SELECT emp.emp_name FROM m_employee emp where emp.id=employee_id)")
	private String employeeName;
	
//	@Formula("(SELECT emp.emp_code FROM m_employee emp where emp.id=employee_id)")
	private String employeeCode;
	private String isActive;
	private String compPayCycleId;
	
	private String department;
@Formula("(SELECT paycycle.pay_cycle_type FROM m_comp_pay_cycle paycycle where paycycle.id=comp_pay_cycle_id)")
private String compPayCycleType;
	
	private String totalDeductionAmt;
	private String totalAllowanceAmt;
	private String totalEarnings;
	private String totalVariablePay;
	private String totalTax;
	private String grossSalary;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt,toDt;
	private String payDt;
	private String netPayAmt;
	private String remarks;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private String dPfNo;
	private String aadharNo;
	private String periodDays;
	private String periodHolidays;
	private String workedDays;
	private String lopAndLwop;
	private String arrearDays;
	private String lateDaysDeduction;
	private String overtimeHours;
	private String wageDays;
	private String epsNo;
	private String dGrade;
	private String dAccountNumber;
	private String dDesignation;
	@Column(name="additional_info_1")
	private String additionalInfo1;
	@Column(name="additional_info_2")
	private String additionalInfo2;
	@Column(name="additional_info_3")
	private String additionalInfo3;
	private String specialDesignation;
	private String buisness;
	private String natureOfPosition;
	private String dob;
	private String doj;
	private String skill;
	private String currency;
	private String dPaymentMode;
	private String dDays;
	private String dLop;
	private String uanNo;
	private String additionalFixedPay;
	private String fixedCompensation;
	private String ifscCode;
	@Column(name = "d_salary_for_month")
	private String dSalaryForMonth;
	@Column(name = "d_salary_for_year")
	private String dSalaryForYear;
	@Column(name = "d_salary_paid_month")
	private String dSalaryPaidMonth;
	@Column(name = "d_salary_paid_year")
	private String dSalaryPaidYear;
	private String dEsiNumber;
	private String dPanNumber;
	private String dMobileNumber;
	private String dEmail;
	private String dBankName;
	private String ctcAmt;
	private String esiAmt;
	private String pfAmt;
	private String gratuityAmt;
	private String annualBonusProvision;
	private String retentionBonus;
	private String ytdAllowanceAmt;
	private String ytdDeductionAmt;
	private String netPayInWords;
	private String locationOfPosition;
    private String fixedBenefits;
    private String pfAccumulationByEmployer;
	@Column(name="pf_opening_value")
	private String pfOpeningValue;
	@Column(name="pf_current_value")
	private String pfCurrentValue;
	@Column(name="pf_balance_value")
	private String pfBalanceValue;
    @Column(name="t_payout_process_line_id")
    private String payoutProcessLineId;
    @Column(name="m_emp_ctc_id")
    private String empCtcId;
    @Column(name="total_a1_value")
    private float totalA1Value;
   
    @Column(name="total_a1_arrear_amt")
    private float totalA1ArrearAmt;
    @Column(name="total_a1_current_period")
    private float totalA1CurrentPeriod;
    @Column(name="total_a1_ytd")
    private float totalA1Ytd;
    @Column(name="total_d1_value")
    private float totalD1value;
    @Column(name="total_d1_ytd")
    private float totalD1Ytd;
    @Column(name="total_a2_value")
    private float totalA2Value;
    
    @Column(name="total_a2_arrear_amt")
    private float totalA2ArrearAmt;
    @Column(name="total_a2_current_period")
    private float totalA2CurrentPeriod;
    @Column(name="total_a2_ytd")
    private float totalA2Ytd;
   
    @Column(name="total_b_value")
    private float totalBValue;
    
    @Column(name="total_b_arrear_amt")
    private float totalBArrearAmt;
    @Column(name="total_b_current_period")
    private float totalBCurrentPeriod;
    @Column(name="total_b_ytd")
    private float totalBYtd;
    private float totalArrearAmt;
    private float totalCurrentPeriod;
    private float totalYtd;
    private Integer totalLeaveOpening;
    private Integer totalAccured;
    private Integer totalAvailed;
    private Integer totalBalance;
  
    
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="m_emp_payout_id")
	private List<EmployeePayoutLine> employeePayoutLine;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="m_emp_payout_id")
	private List<EmployeePayoutLeaveBalance> employeePayoutLeaveBalances;
	
    private String payslipDocId; //store document id of the payslip
    
	public String getPayslipDocId() {
		return payslipDocId;
	}

	public void setPayslipDocId(String payslipDocId) {
		this.payslipDocId = payslipDocId;
	}

	public EmployeePayout() {
		super();
		employeePayoutLine = new ArrayList<EmployeePayoutLine>();
		employeePayoutLeaveBalances = new ArrayList<EmployeePayoutLeaveBalance>();
		// TODO Auto-generated constructor stub
	}

	public EmployeePayout(Integer id, String employeeId, String employeeName, String employeeCode, String grossSalary,
			String payDt, String netPayAmt, String dSalaryForMonth, String dSalaryForYear, String payslipDocId) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.grossSalary = grossSalary;
		this.payDt = payDt;
		this.netPayAmt = netPayAmt;
		this.dSalaryForMonth = dSalaryForMonth;
		this.dSalaryForYear = dSalaryForYear;
		this.payslipDocId = payslipDocId;
	}

	public EmployeePayout(Integer id, String employeeId, String employeeName, String employeeCode, String isActive,
			String compPayCycleId, String department, String compPayCycleType, String totalDeductionAmt,
			String totalAllowanceAmt, String totalEarnings, String totalVariablePay, String totalTax,
			String grossSalary, LocalDate fromDt, LocalDate toDt, String payDt, String netPayAmt, String remarks,
			String isEnabled, String createdBy, String modifiedBy, String dPfNo, String aadharNo, String periodDays,
			String periodHolidays, String workedDays, String lopAndLwop, String arrearDays, String lateDaysDeduction,
			String overtimeHours, String wageDays, String epsNo, String dGrade, String dAccountNumber,
			String dDesignation, String additionalInfo1, String additionalInfo2, String additionalInfo3,
			String specialDesignation, String buisness, String natureOfPosition, String dob, String doj, String skill,
			String currency, String dPaymentMode, String dDays, String dLop, String uanNo, String additionalFixedPay,
			String fixedCompensation, String ifscCode, String dSalaryForMonth, String dSalaryForYear,
			String dSalaryPaidMonth, String dSalaryPaidYear, String dEsiNumber, String dPanNumber, String dMobileNumber,
			String dEmail, String dBankName, String ctcAmt, String esiAmt, String pfAmt, String gratuityAmt,
			String annualBonusProvision, String retentionBonus, String ytdAllowanceAmt, String ytdDeductionAmt,
			String netPayInWords, String locationOfPosition, String fixedBenefits, String pfAccumulationByEmployer,
			String pfOpeningValue, String pfCurrentValue, String pfBalanceValue, String payoutProcessLineId,
			String empCtcId, float totalA1Value, float totalA1ArrearAmt, float totalA1CurrentPeriod, float totalA1Ytd,
			float totalD1value, float totalD1Ytd, float totalA2Value, float totalA2ArrearAmt,
			float totalA2CurrentPeriod, float totalA2Ytd, float totalBValue, float totalBArrearAmt,
			float totalBCurrentPeriod, float totalBYtd, float totalArrearAmt, float totalCurrentPeriod, float totalYtd,
			Integer totalLeaveOpening, Integer totalAccured, Integer totalAvailed, Integer totalBalance,
			LocalDateTime createdDt, LocalDateTime modifiedDt, List<EmployeePayoutLine> employeePayoutLine,
			List<EmployeePayoutLeaveBalance> employeePayoutLeaveBalances, String payslipDocId) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.isActive = isActive;
		this.compPayCycleId = compPayCycleId;
		this.department = department;
		this.compPayCycleType = compPayCycleType;
		this.totalDeductionAmt = totalDeductionAmt;
		this.totalAllowanceAmt = totalAllowanceAmt;
		this.totalEarnings = totalEarnings;
		this.totalVariablePay = totalVariablePay;
		this.totalTax = totalTax;
		this.grossSalary = grossSalary;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.payDt = payDt;
		this.netPayAmt = netPayAmt;
		this.remarks = remarks;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.dPfNo = dPfNo;
		this.aadharNo = aadharNo;
		this.periodDays = periodDays;
		this.periodHolidays = periodHolidays;
		this.workedDays = workedDays;
		this.lopAndLwop = lopAndLwop;
		this.arrearDays = arrearDays;
		this.lateDaysDeduction = lateDaysDeduction;
		this.overtimeHours = overtimeHours;
		this.wageDays = wageDays;
		this.epsNo = epsNo;
		this.dGrade = dGrade;
		this.dAccountNumber = dAccountNumber;
		this.dDesignation = dDesignation;
		this.additionalInfo1 = additionalInfo1;
		this.additionalInfo2 = additionalInfo2;
		this.additionalInfo3 = additionalInfo3;
		this.specialDesignation = specialDesignation;
		this.buisness = buisness;
		this.natureOfPosition = natureOfPosition;
		this.dob = dob;
		this.doj = doj;
		this.skill = skill;
		this.currency = currency;
		this.dPaymentMode = dPaymentMode;
		this.dDays = dDays;
		this.dLop = dLop;
		this.uanNo = uanNo;
		this.additionalFixedPay = additionalFixedPay;
		this.fixedCompensation = fixedCompensation;
		this.ifscCode = ifscCode;
		this.dSalaryForMonth = dSalaryForMonth;
		this.dSalaryForYear = dSalaryForYear;
		this.dSalaryPaidMonth = dSalaryPaidMonth;
		this.dSalaryPaidYear = dSalaryPaidYear;
		this.dEsiNumber = dEsiNumber;
		this.dPanNumber = dPanNumber;
		this.dMobileNumber = dMobileNumber;
		this.dEmail = dEmail;
		this.dBankName = dBankName;
		this.ctcAmt = ctcAmt;
		this.esiAmt = esiAmt;
		this.pfAmt = pfAmt;
		this.gratuityAmt = gratuityAmt;
		this.annualBonusProvision = annualBonusProvision;
		this.retentionBonus = retentionBonus;
		this.ytdAllowanceAmt = ytdAllowanceAmt;
		this.ytdDeductionAmt = ytdDeductionAmt;
		this.netPayInWords = netPayInWords;
		this.locationOfPosition = locationOfPosition;
		this.fixedBenefits = fixedBenefits;
		this.pfAccumulationByEmployer = pfAccumulationByEmployer;
		this.pfOpeningValue = pfOpeningValue;
		this.pfCurrentValue = pfCurrentValue;
		this.pfBalanceValue = pfBalanceValue;
		this.payoutProcessLineId = payoutProcessLineId;
		this.empCtcId = empCtcId;
		this.totalA1Value = totalA1Value;
		this.totalA1ArrearAmt = totalA1ArrearAmt;
		this.totalA1CurrentPeriod = totalA1CurrentPeriod;
		this.totalA1Ytd = totalA1Ytd;
		this.totalD1value = totalD1value;
		this.totalD1Ytd = totalD1Ytd;
		this.totalA2Value = totalA2Value;
		this.totalA2ArrearAmt = totalA2ArrearAmt;
		this.totalA2CurrentPeriod = totalA2CurrentPeriod;
		this.totalA2Ytd = totalA2Ytd;
		this.totalBValue = totalBValue;
		this.totalBArrearAmt = totalBArrearAmt;
		this.totalBCurrentPeriod = totalBCurrentPeriod;
		this.totalBYtd = totalBYtd;
		this.totalArrearAmt = totalArrearAmt;
		this.totalCurrentPeriod = totalCurrentPeriod;
		this.totalYtd = totalYtd;
		this.totalLeaveOpening = totalLeaveOpening;
		this.totalAccured = totalAccured;
		this.totalAvailed = totalAvailed;
		this.totalBalance = totalBalance;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.employeePayoutLine = employeePayoutLine;
		this.employeePayoutLeaveBalances = employeePayoutLeaveBalances;
		this.payslipDocId = payslipDocId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getCompPayCycleId() {
		return compPayCycleId;
	}

	public void setCompPayCycleId(String compPayCycleId) {
		this.compPayCycleId = compPayCycleId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCompPayCycleType() {
		return compPayCycleType;
	}

	public void setCompPayCycleType(String compPayCycleType) {
		this.compPayCycleType = compPayCycleType;
	}

	public String getTotalDeductionAmt() {
		return totalDeductionAmt;
	}

	public void setTotalDeductionAmt(String totalDeductionAmt) {
		this.totalDeductionAmt = totalDeductionAmt;
	}

	public String getTotalAllowanceAmt() {
		return totalAllowanceAmt;
	}

	public void setTotalAllowanceAmt(String totalAllowanceAmt) {
		this.totalAllowanceAmt = totalAllowanceAmt;
	}

	public String getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(String totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public String getTotalVariablePay() {
		return totalVariablePay;
	}

	public void setTotalVariablePay(String totalVariablePay) {
		this.totalVariablePay = totalVariablePay;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public String getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(String grossSalary) {
		this.grossSalary = grossSalary;
	}

	public LocalDate getFromDt() {
		return fromDt;
	}

	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}

	public LocalDate getToDt() {
		return toDt;
	}

	public void setToDt(LocalDate toDt) {
		this.toDt = toDt;
	}

	public String getPayDt() {
		return payDt;
	}

	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}

	public String getNetPayAmt() {
		return netPayAmt;
	}

	public void setNetPayAmt(String netPayAmt) {
		this.netPayAmt = netPayAmt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
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

	public String getdPfNo() {
		return dPfNo;
	}

	public void setdPfNo(String dPfNo) {
		this.dPfNo = dPfNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
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

	public String getEpsNo() {
		return epsNo;
	}

	public void setEpsNo(String epsNo) {
		this.epsNo = epsNo;
	}

	public String getdGrade() {
		return dGrade;
	}

	public void setdGrade(String dGrade) {
		this.dGrade = dGrade;
	}

	public String getdAccountNumber() {
		return dAccountNumber;
	}

	public void setdAccountNumber(String dAccountNumber) {
		this.dAccountNumber = dAccountNumber;
	}

	public String getdDesignation() {
		return dDesignation;
	}

	public void setdDesignation(String dDesignation) {
		this.dDesignation = dDesignation;
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

	public String getSpecialDesignation() {
		return specialDesignation;
	}

	public void setSpecialDesignation(String specialDesignation) {
		this.specialDesignation = specialDesignation;
	}

	public String getBuisness() {
		return buisness;
	}

	public void setBuisness(String buisness) {
		this.buisness = buisness;
	}

	public String getNatureOfPosition() {
		return natureOfPosition;
	}

	public void setNatureOfPosition(String natureOfPosition) {
		this.natureOfPosition = natureOfPosition;
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

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getdPaymentMode() {
		return dPaymentMode;
	}

	public void setdPaymentMode(String dPaymentMode) {
		this.dPaymentMode = dPaymentMode;
	}

	public String getdDays() {
		return dDays;
	}

	public void setdDays(String dDays) {
		this.dDays = dDays;
	}

	public String getdLop() {
		return dLop;
	}

	public void setdLop(String dLop) {
		this.dLop = dLop;
	}

	public String getUanNo() {
		return uanNo;
	}

	public void setUanNo(String uanNo) {
		this.uanNo = uanNo;
	}

	public String getAdditionalFixedPay() {
		return additionalFixedPay;
	}

	public void setAdditionalFixedPay(String additionalFixedPay) {
		this.additionalFixedPay = additionalFixedPay;
	}

	public String getFixedCompensation() {
		return fixedCompensation;
	}

	public void setFixedCompensation(String fixedCompensation) {
		this.fixedCompensation = fixedCompensation;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getdSalaryForMonth() {
		return dSalaryForMonth;
	}

	public void setdSalaryForMonth(String dSalaryForMonth) {
		this.dSalaryForMonth = dSalaryForMonth;
	}

	public String getdSalaryForYear() {
		return dSalaryForYear;
	}

	public void setdSalaryForYear(String dSalaryForYear) {
		this.dSalaryForYear = dSalaryForYear;
	}

	public String getdSalaryPaidMonth() {
		return dSalaryPaidMonth;
	}

	public void setdSalaryPaidMonth(String dSalaryPaidMonth) {
		this.dSalaryPaidMonth = dSalaryPaidMonth;
	}

	public String getdSalaryPaidYear() {
		return dSalaryPaidYear;
	}

	public void setdSalaryPaidYear(String dSalaryPaidYear) {
		this.dSalaryPaidYear = dSalaryPaidYear;
	}

	public String getdEsiNumber() {
		return dEsiNumber;
	}

	public void setdEsiNumber(String dEsiNumber) {
		this.dEsiNumber = dEsiNumber;
	}

	public String getdPanNumber() {
		return dPanNumber;
	}

	public void setdPanNumber(String dPanNumber) {
		this.dPanNumber = dPanNumber;
	}

	public String getdMobileNumber() {
		return dMobileNumber;
	}

	public void setdMobileNumber(String dMobileNumber) {
		this.dMobileNumber = dMobileNumber;
	}

	public String getdEmail() {
		return dEmail;
	}

	public void setdEmail(String dEmail) {
		this.dEmail = dEmail;
	}

	public String getdBankName() {
		return dBankName;
	}

	public void setdBankName(String dBankName) {
		this.dBankName = dBankName;
	}

	public String getCtcAmt() {
		return ctcAmt;
	}

	public void setCtcAmt(String ctcAmt) {
		this.ctcAmt = ctcAmt;
	}

	public String getEsiAmt() {
		return esiAmt;
	}

	public void setEsiAmt(String esiAmt) {
		this.esiAmt = esiAmt;
	}

	public String getPfAmt() {
		return pfAmt;
	}

	public void setPfAmt(String pfAmt) {
		this.pfAmt = pfAmt;
	}

	public String getGratuityAmt() {
		return gratuityAmt;
	}

	public void setGratuityAmt(String gratuityAmt) {
		this.gratuityAmt = gratuityAmt;
	}

	public String getAnnualBonusProvision() {
		return annualBonusProvision;
	}

	public void setAnnualBonusProvision(String annualBonusProvision) {
		this.annualBonusProvision = annualBonusProvision;
	}

	public String getRetentionBonus() {
		return retentionBonus;
	}

	public void setRetentionBonus(String retentionBonus) {
		this.retentionBonus = retentionBonus;
	}

	public String getYtdAllowanceAmt() {
		return ytdAllowanceAmt;
	}

	public void setYtdAllowanceAmt(String ytdAllowanceAmt) {
		this.ytdAllowanceAmt = ytdAllowanceAmt;
	}

	public String getYtdDeductionAmt() {
		return ytdDeductionAmt;
	}

	public void setYtdDeductionAmt(String ytdDeductionAmt) {
		this.ytdDeductionAmt = ytdDeductionAmt;
	}

	public String getNetPayInWords() {
		return netPayInWords;
	}

	public void setNetPayInWords(String netPayInWords) {
		this.netPayInWords = netPayInWords;
	}

	public String getLocationOfPosition() {
		return locationOfPosition;
	}

	public void setLocationOfPosition(String locationOfPosition) {
		this.locationOfPosition = locationOfPosition;
	}

	public String getFixedBenefits() {
		return fixedBenefits;
	}

	public void setFixedBenefits(String fixedBenefits) {
		this.fixedBenefits = fixedBenefits;
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

	public String getPfCurrentValue() {
		return pfCurrentValue;
	}

	public void setPfCurrentValue(String pfCurrentValue) {
		this.pfCurrentValue = pfCurrentValue;
	}

	public String getPfBalanceValue() {
		return pfBalanceValue;
	}

	public void setPfBalanceValue(String pfBalanceValue) {
		this.pfBalanceValue = pfBalanceValue;
	}

	public String getPayoutProcessLineId() {
		return payoutProcessLineId;
	}

	public void setPayoutProcessLineId(String payoutProcessLineId) {
		this.payoutProcessLineId = payoutProcessLineId;
	}

	public String getEmpCtcId() {
		return empCtcId;
	}

	public void setEmpCtcId(String empCtcId) {
		this.empCtcId = empCtcId;
	}

	public float getTotalA1Value() {
		return totalA1Value;
	}

	public void setTotalA1Value(float totalA1Value) {
		this.totalA1Value = totalA1Value;
	}

	public float getTotalA1ArrearAmt() {
		return totalA1ArrearAmt;
	}

	public void setTotalA1ArrearAmt(float totalA1ArrearAmt) {
		this.totalA1ArrearAmt = totalA1ArrearAmt;
	}

	public float getTotalA1CurrentPeriod() {
		return totalA1CurrentPeriod;
	}

	public void setTotalA1CurrentPeriod(float totalA1CurrentPeriod) {
		this.totalA1CurrentPeriod = totalA1CurrentPeriod;
	}

	public float getTotalA1Ytd() {
		return totalA1Ytd;
	}

	public void setTotalA1Ytd(float totalA1Ytd) {
		this.totalA1Ytd = totalA1Ytd;
	}

	public float getTotalD1value() {
		return totalD1value;
	}

	public void setTotalD1value(float totalD1value) {
		this.totalD1value = totalD1value;
	}

	public float getTotalD1Ytd() {
		return totalD1Ytd;
	}

	public void setTotalD1Ytd(float totalD1Ytd) {
		this.totalD1Ytd = totalD1Ytd;
	}

	public float getTotalA2Value() {
		return totalA2Value;
	}

	public void setTotalA2Value(float totalA2Value) {
		this.totalA2Value = totalA2Value;
	}

	public float getTotalA2ArrearAmt() {
		return totalA2ArrearAmt;
	}

	public void setTotalA2ArrearAmt(float totalA2ArrearAmt) {
		this.totalA2ArrearAmt = totalA2ArrearAmt;
	}

	public float getTotalA2CurrentPeriod() {
		return totalA2CurrentPeriod;
	}

	public void setTotalA2CurrentPeriod(float totalA2CurrentPeriod) {
		this.totalA2CurrentPeriod = totalA2CurrentPeriod;
	}

	public float getTotalA2Ytd() {
		return totalA2Ytd;
	}

	public void setTotalA2Ytd(float totalA2Ytd) {
		this.totalA2Ytd = totalA2Ytd;
	}

	public float getTotalBValue() {
		return totalBValue;
	}

	public void setTotalBValue(float totalBValue) {
		this.totalBValue = totalBValue;
	}

	public float getTotalBArrearAmt() {
		return totalBArrearAmt;
	}

	public void setTotalBArrearAmt(float totalBArrearAmt) {
		this.totalBArrearAmt = totalBArrearAmt;
	}

	public float getTotalBCurrentPeriod() {
		return totalBCurrentPeriod;
	}

	public void setTotalBCurrentPeriod(float totalBCurrentPeriod) {
		this.totalBCurrentPeriod = totalBCurrentPeriod;
	}

	public float getTotalBYtd() {
		return totalBYtd;
	}

	public void setTotalBYtd(float totalBYtd) {
		this.totalBYtd = totalBYtd;
	}

	public float getTotalArrearAmt() {
		return totalArrearAmt;
	}

	public void setTotalArrearAmt(float totalArrearAmt) {
		this.totalArrearAmt = totalArrearAmt;
	}

	public float getTotalCurrentPeriod() {
		return totalCurrentPeriod;
	}

	public void setTotalCurrentPeriod(float totalCurrentPeriod) {
		this.totalCurrentPeriod = totalCurrentPeriod;
	}

	public float getTotalYtd() {
		return totalYtd;
	}

	public void setTotalYtd(float totalYtd) {
		this.totalYtd = totalYtd;
	}

	public Integer getTotalLeaveOpening() {
		return totalLeaveOpening;
	}

	public void setTotalLeaveOpening(Integer totalLeaveOpening) {
		this.totalLeaveOpening = totalLeaveOpening;
	}

	public Integer getTotalAccured() {
		return totalAccured;
	}

	public void setTotalAccured(Integer totalAccured) {
		this.totalAccured = totalAccured;
	}

	public Integer getTotalAvailed() {
		return totalAvailed;
	}

	public void setTotalAvailed(Integer totalAvailed) {
		this.totalAvailed = totalAvailed;
	}

	public Integer getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Integer totalBalance) {
		this.totalBalance = totalBalance;
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

	public List<EmployeePayoutLine> getEmployeePayoutLines() {
		return employeePayoutLine;
	}

	public void setEmployeePayoutLines(List<EmployeePayoutLine> employeePayoutLine) {
		this.employeePayoutLine = employeePayoutLine;
	}

	public List<EmployeePayoutLeaveBalance> getEmployeePayoutLeaveBalances() {
		return employeePayoutLeaveBalances;
	}

	public void setEmployeePayoutLeaveBalances(List<EmployeePayoutLeaveBalance> employeePayoutLeaveBalances) {
		this.employeePayoutLeaveBalances = employeePayoutLeaveBalances;
	}

	@Override
	public String toString() {
		return "EmployeePayout [id=" + id + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", employeeCode=" + employeeCode + ", isActive=" + isActive + ", compPayCycleId=" + compPayCycleId
				+ ", department=" + department + ", compPayCycleType=" + compPayCycleType + ", totalDeductionAmt="
				+ totalDeductionAmt + ", totalAllowanceAmt=" + totalAllowanceAmt + ", totalEarnings=" + totalEarnings
				+ ", totalVariablePay=" + totalVariablePay + ", totalTax=" + totalTax + ", grossSalary=" + grossSalary
				+ ", fromDt=" + fromDt + ", toDt=" + toDt + ", payDt=" + payDt + ", netPayAmt=" + netPayAmt
				+ ", remarks=" + remarks + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", dPfNo=" + dPfNo + ", aadharNo=" + aadharNo + ", periodDays=" + periodDays
				+ ", periodHolidays=" + periodHolidays + ", workedDays=" + workedDays + ", lopAndLwop=" + lopAndLwop
				+ ", arrearDays=" + arrearDays + ", lateDaysDeduction=" + lateDaysDeduction + ", overtimeHours="
				+ overtimeHours + ", wageDays=" + wageDays + ", epsNo=" + epsNo + ", dGrade=" + dGrade
				+ ", dAccountNumber=" + dAccountNumber + ", dDesignation=" + dDesignation + ", additionalInfo1="
				+ additionalInfo1 + ", additionalInfo2=" + additionalInfo2 + ", additionalInfo3=" + additionalInfo3
				+ ", specialDesignation=" + specialDesignation + ", buisness=" + buisness + ", natureOfPosition="
				+ natureOfPosition + ", dob=" + dob + ", doj=" + doj + ", skill=" + skill + ", currency=" + currency
				+ ", dPaymentMode=" + dPaymentMode + ", dDays=" + dDays + ", dLop=" + dLop + ", uanNo=" + uanNo
				+ ", additionalFixedPay=" + additionalFixedPay + ", fixedCompensation=" + fixedCompensation
				+ ", ifscCode=" + ifscCode + ", dSalaryForMonth=" + dSalaryForMonth + ", dSalaryForYear="
				+ dSalaryForYear + ", dSalaryPaidMonth=" + dSalaryPaidMonth + ", dSalaryPaidYear=" + dSalaryPaidYear
				+ ", dEsiNumber=" + dEsiNumber + ", dPanNumber=" + dPanNumber + ", dMobileNumber=" + dMobileNumber
				+ ", dEmail=" + dEmail + ", dBankName=" + dBankName + ", ctcAmt=" + ctcAmt + ", esiAmt=" + esiAmt
				+ ", pfAmt=" + pfAmt + ", gratuityAmt=" + gratuityAmt + ", annualBonusProvision=" + annualBonusProvision
				+ ", retentionBonus=" + retentionBonus + ", ytdAllowanceAmt=" + ytdAllowanceAmt + ", ytdDeductionAmt="
				+ ytdDeductionAmt + ", netPayInWords=" + netPayInWords + ", locationOfPosition=" + locationOfPosition
				+ ", fixedBenefits=" + fixedBenefits + ", pfAccumulationByEmployer=" + pfAccumulationByEmployer
				+ ", pfOpeningValue=" + pfOpeningValue + ", pfCurrentValue=" + pfCurrentValue + ", pfBalanceValue="
				+ pfBalanceValue + ", payoutProcessLineId=" + payoutProcessLineId + ", empCtcId=" + empCtcId
				+ ", totalA1Value=" + totalA1Value + ", totalA1ArrearAmt=" + totalA1ArrearAmt
				+ ", totalA1CurrentPeriod=" + totalA1CurrentPeriod + ", totalA1Ytd=" + totalA1Ytd + ", totalD1value="
				+ totalD1value + ", totalD1Ytd=" + totalD1Ytd + ", totalA2Value=" + totalA2Value + ", totalA2ArrearAmt="
				+ totalA2ArrearAmt + ", totalA2CurrentPeriod=" + totalA2CurrentPeriod + ", totalA2Ytd=" + totalA2Ytd
				+ ", totalBValue=" + totalBValue + ", totalBArrearAmt=" + totalBArrearAmt + ", totalBCurrentPeriod="
				+ totalBCurrentPeriod + ", totalBYtd=" + totalBYtd + ", totalArrearAmt=" + totalArrearAmt
				+ ", totalCurrentPeriod=" + totalCurrentPeriod + ", totalYtd=" + totalYtd + ", totalLeaveOpening="
				+ totalLeaveOpening + ", totalAccured=" + totalAccured + ", totalAvailed=" + totalAvailed
				+ ", totalBalance=" + totalBalance + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", employeePayoutLine=" + employeePayoutLine + ", employeePayoutLeaveBalances="
				+ employeePayoutLeaveBalances + ", payslipDocId=" + payslipDocId + "]";
	}
	
	

	

	

	

	
}
