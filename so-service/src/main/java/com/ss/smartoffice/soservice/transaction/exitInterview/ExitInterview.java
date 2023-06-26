package com.ss.smartoffice.soservice.transaction.exitInterview;

import java.time.LocalDateTime;
import java.util.Date;

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

@Entity
@Table(name = "t_exit_interview")

@Scope("prototype")
public class ExitInterview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String empId;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id=emp_id)")
	private String empName;
	@Formula("(select e.emp_code from m_employee e where e.id=emp_id)")
	private String empCode;
	private String deptId;
	@Formula("(SELECT dept.dept_name FROM m_dept dept WHERE dept.id=dept_id)")
	private String deptName;
	private String status;
	private String exitType;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfInterview;
	private String empDur;
	private String isAutherJb;
	private String isNwJbComp;
	private String isNwJbAutnmy;
	private String isDissatisfctn;
	private String isHlth;
	private String isFamHlth;
	private String isEdu;
	private String isRetire;
	private String isRelocat;
	private String isOtrsVol;
	private String fac1;
	private String fac2;
	private String fac3;
	private String fac4;
	private String fac5;
	private String fac6;

	// checkList-Involuntary

	private String isLayoff;
	private String isPerfmce;
	private String isPorAttdce;
	private String isInsubord;
	private String isViolatPolicy;
	private String isElimte;
	private String isTemp;
	private String isOtrsInVol;

	// Exit Interview hr
	private String isAntherJb;
	private String newJbDesc;
	private String newTtle;
	private String prsntSal;
	private String newSal;
	private String isEmpNwPos;
	private String empNwPosDesc;
	private String isEmpSatis;
	private String empSatisDesc;
	private String handleCmpls;
	private String empShreThghts;
	private String empThnkPrgres;
	private String judgmnt;
	private String isComp;
	private String compDesc;
	@Column(name = "hr2_group_id")
	private String hr2GroupId;
	@Formula("(SELECT urgrp.user_group_name from s_user_group urgrp where urgrp.id=hr2_group_id)")
	private String hr2GrpName;
	@Column(name = "dir_group_id")
	private String dirGroupId;
	@Formula("(SELECT urgrp.user_group_name from s_user_group urgrp where urgrp.id=dir_group_id)")
	private String dirGrpName;
	@Column(name = "n1_manager_id")
	private String n1ManagerId;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id=n1_manager_id)")
	private String n1ManagerName;
	@Column(name = "n2_manager_id")
	private String n2ManagerId;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id=n2_manager_id)")
	private String n2ManagerName;
	@Column(name = "acc2_group_id")
	private String acc2GroupId;
	@Formula("(SELECT urgrp.user_group_name from s_user_group urgrp where urgrp.id=acc2_group_id)")
	private String acc2GrpName;
	private String officeId;
	@Formula("(SELECT ofice.office_name FROM m_office ofice WHERE ofice.id = office_id)")
	private String officeName;
	
	@Column(name = "n1_cleared_by")
	private String n1ClearedBy;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id=n1_cleared_by)")
	private String n1ClearedByEmpName;
	@Column(name = "n1_cleared_dt")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime n1ClearedDt;
	@Column(name = "n1_clearance_remarks")
	private String n1ClearanceRemarks;
	@Column(name = "is_n1_cleared")
	private String isN1Cleared;

	private String accClearedBy;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id=acc_cleared_by)")
	private String accClearedByEmpName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime accClearedDt;
	@Column(name = "acc_clearance_remarks")
	private String accClearanceRemarks;
	@Column(name = "is_acc_cleared")
	private String isAccCleared;

	private String hrClearedBy;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id=hr_cleared_by)")
	private String hrClearedByEmpName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime hrClearedDt;
	@Column(name = "hr_clearance_remarks")
	private String hrClearanceRemarks;
	@Column(name = "is_hr_cleared")
	private String isHrCleared;

	private String isEnable;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	public ExitInterview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExitInterview(Integer id, String empId, String empName, String empCode, String deptId, String deptName,
			String status, String exitType, Date dateOfInterview, String empDur, String isAutherJb, String isNwJbComp,
			String isNwJbAutnmy, String isDissatisfctn, String isHlth, String isFamHlth, String isEdu, String isRetire,
			String isRelocat, String isOtrsVol, String fac1, String fac2, String fac3, String fac4, String fac5,
			String fac6, String isLayoff, String isPerfmce, String isPorAttdce, String isInsubord,
			String isViolatPolicy, String isElimte, String isTemp, String isOtrsInVol, String isAntherJb,
			String newJbDesc, String newTtle, String prsntSal, String newSal, String isEmpNwPos, String empNwPosDesc,
			String isEmpSatis, String empSatisDesc, String handleCmpls, String empShreThghts, String empThnkPrgres,
			String judgmnt, String isComp, String compDesc, String hr2GroupId, String hr2GrpName, String dirGroupId,
			String dirGrpName, String n1ManagerId, String n1ManagerName, String n2ManagerId, String n2ManagerName,
			String acc2GroupId, String acc2GrpName, String officeId, String officeName, String n1ClearedBy,
			String n1ClearedByEmpName, LocalDateTime n1ClearedDt, String n1ClearanceRemarks, String isN1Cleared,
			String accClearedBy, String accClearedByEmpName, LocalDateTime accClearedDt, String accClearanceRemarks,
			String isAccCleared, String hrClearedBy, String hrClearedByEmpName, LocalDateTime hrClearedDt,
			String hrClearanceRemarks, String isHrCleared, String isEnable, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.empId = empId;
		this.empName = empName;
		this.empCode = empCode;
		this.deptId = deptId;
		this.deptName = deptName;
		this.status = status;
		this.exitType = exitType;
		this.dateOfInterview = dateOfInterview;
		this.empDur = empDur;
		this.isAutherJb = isAutherJb;
		this.isNwJbComp = isNwJbComp;
		this.isNwJbAutnmy = isNwJbAutnmy;
		this.isDissatisfctn = isDissatisfctn;
		this.isHlth = isHlth;
		this.isFamHlth = isFamHlth;
		this.isEdu = isEdu;
		this.isRetire = isRetire;
		this.isRelocat = isRelocat;
		this.isOtrsVol = isOtrsVol;
		this.fac1 = fac1;
		this.fac2 = fac2;
		this.fac3 = fac3;
		this.fac4 = fac4;
		this.fac5 = fac5;
		this.fac6 = fac6;
		this.isLayoff = isLayoff;
		this.isPerfmce = isPerfmce;
		this.isPorAttdce = isPorAttdce;
		this.isInsubord = isInsubord;
		this.isViolatPolicy = isViolatPolicy;
		this.isElimte = isElimte;
		this.isTemp = isTemp;
		this.isOtrsInVol = isOtrsInVol;
		this.isAntherJb = isAntherJb;
		this.newJbDesc = newJbDesc;
		this.newTtle = newTtle;
		this.prsntSal = prsntSal;
		this.newSal = newSal;
		this.isEmpNwPos = isEmpNwPos;
		this.empNwPosDesc = empNwPosDesc;
		this.isEmpSatis = isEmpSatis;
		this.empSatisDesc = empSatisDesc;
		this.handleCmpls = handleCmpls;
		this.empShreThghts = empShreThghts;
		this.empThnkPrgres = empThnkPrgres;
		this.judgmnt = judgmnt;
		this.isComp = isComp;
		this.compDesc = compDesc;
		this.hr2GroupId = hr2GroupId;
		this.hr2GrpName = hr2GrpName;
		this.dirGroupId = dirGroupId;
		this.dirGrpName = dirGrpName;
		this.n1ManagerId = n1ManagerId;
		this.n1ManagerName = n1ManagerName;
		this.n2ManagerId = n2ManagerId;
		this.n2ManagerName = n2ManagerName;
		this.acc2GroupId = acc2GroupId;
		this.acc2GrpName = acc2GrpName;
		this.officeId = officeId;
		this.officeName = officeName;
		this.n1ClearedBy = n1ClearedBy;
		this.n1ClearedByEmpName = n1ClearedByEmpName;
		this.n1ClearedDt = n1ClearedDt;
		this.n1ClearanceRemarks = n1ClearanceRemarks;
		this.isN1Cleared = isN1Cleared;
		this.accClearedBy = accClearedBy;
		this.accClearedByEmpName = accClearedByEmpName;
		this.accClearedDt = accClearedDt;
		this.accClearanceRemarks = accClearanceRemarks;
		this.isAccCleared = isAccCleared;
		this.hrClearedBy = hrClearedBy;
		this.hrClearedByEmpName = hrClearedByEmpName;
		this.hrClearedDt = hrClearedDt;
		this.hrClearanceRemarks = hrClearanceRemarks;
		this.isHrCleared = isHrCleared;
		this.isEnable = isEnable;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExitType() {
		return exitType;
	}

	public void setExitType(String exitType) {
		this.exitType = exitType;
	}

	public Date getDateOfInterview() {
		return dateOfInterview;
	}

	public void setDateOfInterview(Date dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}

	public String getEmpDur() {
		return empDur;
	}

	public void setEmpDur(String empDur) {
		this.empDur = empDur;
	}

	public String getIsAutherJb() {
		return isAutherJb;
	}

	public void setIsAutherJb(String isAutherJb) {
		this.isAutherJb = isAutherJb;
	}

	public String getIsNwJbComp() {
		return isNwJbComp;
	}

	public void setIsNwJbComp(String isNwJbComp) {
		this.isNwJbComp = isNwJbComp;
	}

	public String getIsNwJbAutnmy() {
		return isNwJbAutnmy;
	}

	public void setIsNwJbAutnmy(String isNwJbAutnmy) {
		this.isNwJbAutnmy = isNwJbAutnmy;
	}

	public String getIsDissatisfctn() {
		return isDissatisfctn;
	}

	public void setIsDissatisfctn(String isDissatisfctn) {
		this.isDissatisfctn = isDissatisfctn;
	}

	public String getIsHlth() {
		return isHlth;
	}

	public void setIsHlth(String isHlth) {
		this.isHlth = isHlth;
	}

	public String getIsFamHlth() {
		return isFamHlth;
	}

	public void setIsFamHlth(String isFamHlth) {
		this.isFamHlth = isFamHlth;
	}

	public String getIsEdu() {
		return isEdu;
	}

	public void setIsEdu(String isEdu) {
		this.isEdu = isEdu;
	}

	public String getIsRetire() {
		return isRetire;
	}

	public void setIsRetire(String isRetire) {
		this.isRetire = isRetire;
	}

	public String getIsRelocat() {
		return isRelocat;
	}

	public void setIsRelocat(String isRelocat) {
		this.isRelocat = isRelocat;
	}

	public String getIsOtrsVol() {
		return isOtrsVol;
	}

	public void setIsOtrsVol(String isOtrsVol) {
		this.isOtrsVol = isOtrsVol;
	}

	public String getFac1() {
		return fac1;
	}

	public void setFac1(String fac1) {
		this.fac1 = fac1;
	}

	public String getFac2() {
		return fac2;
	}

	public void setFac2(String fac2) {
		this.fac2 = fac2;
	}

	public String getFac3() {
		return fac3;
	}

	public void setFac3(String fac3) {
		this.fac3 = fac3;
	}

	public String getFac4() {
		return fac4;
	}

	public void setFac4(String fac4) {
		this.fac4 = fac4;
	}

	public String getFac5() {
		return fac5;
	}

	public void setFac5(String fac5) {
		this.fac5 = fac5;
	}

	public String getFac6() {
		return fac6;
	}

	public void setFac6(String fac6) {
		this.fac6 = fac6;
	}

	public String getIsLayoff() {
		return isLayoff;
	}

	public void setIsLayoff(String isLayoff) {
		this.isLayoff = isLayoff;
	}

	public String getIsPerfmce() {
		return isPerfmce;
	}

	public void setIsPerfmce(String isPerfmce) {
		this.isPerfmce = isPerfmce;
	}

	public String getIsPorAttdce() {
		return isPorAttdce;
	}

	public void setIsPorAttdce(String isPorAttdce) {
		this.isPorAttdce = isPorAttdce;
	}

	public String getIsInsubord() {
		return isInsubord;
	}

	public void setIsInsubord(String isInsubord) {
		this.isInsubord = isInsubord;
	}

	public String getIsViolatPolicy() {
		return isViolatPolicy;
	}

	public void setIsViolatPolicy(String isViolatPolicy) {
		this.isViolatPolicy = isViolatPolicy;
	}

	public String getIsElimte() {
		return isElimte;
	}

	public void setIsElimte(String isElimte) {
		this.isElimte = isElimte;
	}

	public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getIsOtrsInVol() {
		return isOtrsInVol;
	}

	public void setIsOtrsInVol(String isOtrsInVol) {
		this.isOtrsInVol = isOtrsInVol;
	}

	public String getIsAntherJb() {
		return isAntherJb;
	}

	public void setIsAntherJb(String isAntherJb) {
		this.isAntherJb = isAntherJb;
	}

	public String getNewJbDesc() {
		return newJbDesc;
	}

	public void setNewJbDesc(String newJbDesc) {
		this.newJbDesc = newJbDesc;
	}

	public String getNewTtle() {
		return newTtle;
	}

	public void setNewTtle(String newTtle) {
		this.newTtle = newTtle;
	}

	public String getPrsntSal() {
		return prsntSal;
	}

	public void setPrsntSal(String prsntSal) {
		this.prsntSal = prsntSal;
	}

	public String getNewSal() {
		return newSal;
	}

	public void setNewSal(String newSal) {
		this.newSal = newSal;
	}

	public String getIsEmpNwPos() {
		return isEmpNwPos;
	}

	public void setIsEmpNwPos(String isEmpNwPos) {
		this.isEmpNwPos = isEmpNwPos;
	}

	public String getEmpNwPosDesc() {
		return empNwPosDesc;
	}

	public void setEmpNwPosDesc(String empNwPosDesc) {
		this.empNwPosDesc = empNwPosDesc;
	}

	public String getIsEmpSatis() {
		return isEmpSatis;
	}

	public void setIsEmpSatis(String isEmpSatis) {
		this.isEmpSatis = isEmpSatis;
	}

	public String getEmpSatisDesc() {
		return empSatisDesc;
	}

	public void setEmpSatisDesc(String empSatisDesc) {
		this.empSatisDesc = empSatisDesc;
	}

	public String getHandleCmpls() {
		return handleCmpls;
	}

	public void setHandleCmpls(String handleCmpls) {
		this.handleCmpls = handleCmpls;
	}

	public String getEmpShreThghts() {
		return empShreThghts;
	}

	public void setEmpShreThghts(String empShreThghts) {
		this.empShreThghts = empShreThghts;
	}

	public String getEmpThnkPrgres() {
		return empThnkPrgres;
	}

	public void setEmpThnkPrgres(String empThnkPrgres) {
		this.empThnkPrgres = empThnkPrgres;
	}

	public String getJudgmnt() {
		return judgmnt;
	}

	public void setJudgmnt(String judgmnt) {
		this.judgmnt = judgmnt;
	}

	public String getIsComp() {
		return isComp;
	}

	public void setIsComp(String isComp) {
		this.isComp = isComp;
	}

	public String getCompDesc() {
		return compDesc;
	}

	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}

	public String getHr2GroupId() {
		return hr2GroupId;
	}

	public void setHr2GroupId(String hr2GroupId) {
		this.hr2GroupId = hr2GroupId;
	}

	public String getHr2GrpName() {
		return hr2GrpName;
	}

	public void setHr2GrpName(String hr2GrpName) {
		this.hr2GrpName = hr2GrpName;
	}

	public String getDirGroupId() {
		return dirGroupId;
	}

	public void setDirGroupId(String dirGroupId) {
		this.dirGroupId = dirGroupId;
	}

	public String getDirGrpName() {
		return dirGrpName;
	}

	public void setDirGrpName(String dirGrpName) {
		this.dirGrpName = dirGrpName;
	}

	public String getN1ManagerId() {
		return n1ManagerId;
	}

	public void setN1ManagerId(String n1ManagerId) {
		this.n1ManagerId = n1ManagerId;
	}

	public String getN1ManagerName() {
		return n1ManagerName;
	}

	public void setN1ManagerName(String n1ManagerName) {
		this.n1ManagerName = n1ManagerName;
	}

	public String getN2ManagerId() {
		return n2ManagerId;
	}

	public void setN2ManagerId(String n2ManagerId) {
		this.n2ManagerId = n2ManagerId;
	}

	public String getN2ManagerName() {
		return n2ManagerName;
	}

	public void setN2ManagerName(String n2ManagerName) {
		this.n2ManagerName = n2ManagerName;
	}

	public String getAcc2GroupId() {
		return acc2GroupId;
	}

	public void setAcc2GroupId(String acc2GroupId) {
		this.acc2GroupId = acc2GroupId;
	}

	public String getAcc2GrpName() {
		return acc2GrpName;
	}

	public void setAcc2GrpName(String acc2GrpName) {
		this.acc2GrpName = acc2GrpName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getN1ClearedBy() {
		return n1ClearedBy;
	}

	public void setN1ClearedBy(String n1ClearedBy) {
		this.n1ClearedBy = n1ClearedBy;
	}

	public String getN1ClearedByEmpName() {
		return n1ClearedByEmpName;
	}

	public void setN1ClearedByEmpName(String n1ClearedByEmpName) {
		this.n1ClearedByEmpName = n1ClearedByEmpName;
	}

	public LocalDateTime getN1ClearedDt() {
		return n1ClearedDt;
	}

	public void setN1ClearedDt(LocalDateTime n1ClearedDt) {
		this.n1ClearedDt = n1ClearedDt;
	}

	public String getN1ClearanceRemarks() {
		return n1ClearanceRemarks;
	}

	public void setN1ClearanceRemarks(String n1ClearanceRemarks) {
		this.n1ClearanceRemarks = n1ClearanceRemarks;
	}

	public String getIsN1Cleared() {
		return isN1Cleared;
	}

	public void setIsN1Cleared(String isN1Cleared) {
		this.isN1Cleared = isN1Cleared;
	}

	public String getAccClearedBy() {
		return accClearedBy;
	}

	public void setAccClearedBy(String accClearedBy) {
		this.accClearedBy = accClearedBy;
	}

	public String getAccClearedByEmpName() {
		return accClearedByEmpName;
	}

	public void setAccClearedByEmpName(String accClearedByEmpName) {
		this.accClearedByEmpName = accClearedByEmpName;
	}

	public LocalDateTime getAccClearedDt() {
		return accClearedDt;
	}

	public void setAccClearedDt(LocalDateTime accClearedDt) {
		this.accClearedDt = accClearedDt;
	}

	public String getAccClearanceRemarks() {
		return accClearanceRemarks;
	}

	public void setAccClearanceRemarks(String accClearanceRemarks) {
		this.accClearanceRemarks = accClearanceRemarks;
	}

	public String getIsAccCleared() {
		return isAccCleared;
	}

	public void setIsAccCleared(String isAccCleared) {
		this.isAccCleared = isAccCleared;
	}

	public String getHrClearedBy() {
		return hrClearedBy;
	}

	public void setHrClearedBy(String hrClearedBy) {
		this.hrClearedBy = hrClearedBy;
	}

	public String getHrClearedByEmpName() {
		return hrClearedByEmpName;
	}

	public void setHrClearedByEmpName(String hrClearedByEmpName) {
		this.hrClearedByEmpName = hrClearedByEmpName;
	}

	public LocalDateTime getHrClearedDt() {
		return hrClearedDt;
	}

	public void setHrClearedDt(LocalDateTime hrClearedDt) {
		this.hrClearedDt = hrClearedDt;
	}

	public String getHrClearanceRemarks() {
		return hrClearanceRemarks;
	}

	public void setHrClearanceRemarks(String hrClearanceRemarks) {
		this.hrClearanceRemarks = hrClearanceRemarks;
	}

	public String getIsHrCleared() {
		return isHrCleared;
	}

	public void setIsHrCleared(String isHrCleared) {
		this.isHrCleared = isHrCleared;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
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

	@Override
	public String toString() {
		return "ExitInterview [id=" + id + ", empId=" + empId + ", empName=" + empName + ", empCode=" + empCode
				+ ", deptId=" + deptId + ", deptName=" + deptName + ", status=" + status + ", exitType=" + exitType
				+ ", dateOfInterview=" + dateOfInterview + ", empDur=" + empDur + ", isAutherJb=" + isAutherJb
				+ ", isNwJbComp=" + isNwJbComp + ", isNwJbAutnmy=" + isNwJbAutnmy + ", isDissatisfctn=" + isDissatisfctn
				+ ", isHlth=" + isHlth + ", isFamHlth=" + isFamHlth + ", isEdu=" + isEdu + ", isRetire=" + isRetire
				+ ", isRelocat=" + isRelocat + ", isOtrsVol=" + isOtrsVol + ", fac1=" + fac1 + ", fac2=" + fac2
				+ ", fac3=" + fac3 + ", fac4=" + fac4 + ", fac5=" + fac5 + ", fac6=" + fac6 + ", isLayoff=" + isLayoff
				+ ", isPerfmce=" + isPerfmce + ", isPorAttdce=" + isPorAttdce + ", isInsubord=" + isInsubord
				+ ", isViolatPolicy=" + isViolatPolicy + ", isElimte=" + isElimte + ", isTemp=" + isTemp
				+ ", isOtrsInVol=" + isOtrsInVol + ", isAntherJb=" + isAntherJb + ", newJbDesc=" + newJbDesc
				+ ", newTtle=" + newTtle + ", prsntSal=" + prsntSal + ", newSal=" + newSal + ", isEmpNwPos="
				+ isEmpNwPos + ", empNwPosDesc=" + empNwPosDesc + ", isEmpSatis=" + isEmpSatis + ", empSatisDesc="
				+ empSatisDesc + ", handleCmpls=" + handleCmpls + ", empShreThghts=" + empShreThghts
				+ ", empThnkPrgres=" + empThnkPrgres + ", judgmnt=" + judgmnt + ", isComp=" + isComp + ", compDesc="
				+ compDesc + ", hr2GroupId=" + hr2GroupId + ", hr2GrpName=" + hr2GrpName + ", dirGroupId=" + dirGroupId
				+ ", dirGrpName=" + dirGrpName + ", n1ManagerId=" + n1ManagerId + ", n1ManagerName=" + n1ManagerName
				+ ", n2ManagerId=" + n2ManagerId + ", n2ManagerName=" + n2ManagerName + ", acc2GroupId=" + acc2GroupId
				+ ", acc2GrpName=" + acc2GrpName + ", officeId=" + officeId + ", officeName=" + officeName
				+ ", n1ClearedBy=" + n1ClearedBy + ", n1ClearedByEmpName=" + n1ClearedByEmpName + ", n1ClearedDt="
				+ n1ClearedDt + ", n1ClearanceRemarks=" + n1ClearanceRemarks + ", isN1Cleared=" + isN1Cleared
				+ ", accClearedBy=" + accClearedBy + ", accClearedByEmpName=" + accClearedByEmpName + ", accClearedDt="
				+ accClearedDt + ", accClearanceRemarks=" + accClearanceRemarks + ", isAccCleared=" + isAccCleared
				+ ", hrClearedBy=" + hrClearedBy + ", hrClearedByEmpName=" + hrClearedByEmpName + ", hrClearedDt="
				+ hrClearedDt + ", hrClearanceRemarks=" + hrClearanceRemarks + ", isHrCleared=" + isHrCleared
				+ ", isEnable=" + isEnable + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt="
				+ createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

}
