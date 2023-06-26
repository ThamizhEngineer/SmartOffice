package com.ss.smartoffice.soservice.transaction.ProfileFinderService;

import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name=("t_pf_emp"))

@Scope("prototype")
public class MatchedEmployee {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="profile_finder_id")
	private String profileFinderId;
	private String mEmployeeId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=m_employee_id )")
	private String mEmployeeName;
	@Formula("(select emp.emp_code from m_employee emp where emp.id=m_employee_id )")
	private String mEmployeeCode;
	private String designationName;
	private String comptabilityScore;
	private String prevJobId;
	private String prevMapLocationId;
	private String prevLats;
	private String prevLongs;

	private String distanceToLocation;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "match_employee_id")
	private List<MatchedSkill> matchedSkills;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "match_employee_id")
	private List<EmployeeCommitment> employeeCommitments;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public MatchedEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatchedEmployee(Integer id, String profileFinderId, String mEmployeeId, String mEmployeeName,
			String mEmployeeCode, String designationName, String comptabilityScore, String prevJobId,
			String prevMapLocationId, String prevLats, String prevLongs, String distanceToLocation,
			List<MatchedSkill> matchedSkills, List<EmployeeCommitment> employeeCommitments, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.profileFinderId = profileFinderId;
		this.mEmployeeId = mEmployeeId;
		this.mEmployeeName = mEmployeeName;
		this.mEmployeeCode = mEmployeeCode;
		this.designationName = designationName;
		this.comptabilityScore = comptabilityScore;
		this.prevJobId = prevJobId;
		this.prevMapLocationId = prevMapLocationId;
		this.prevLats = prevLats;
		this.prevLongs = prevLongs;
		this.distanceToLocation = distanceToLocation;
		this.matchedSkills = matchedSkills;
		this.employeeCommitments = employeeCommitments;
		this.isEnabled = isEnabled;
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
	public String getProfileFinderId() {
		return profileFinderId;
	}
	public void setProfileFinderId(String profileFinderId) {
		this.profileFinderId = profileFinderId;
	}
	public String getmEmployeeId() {
		return mEmployeeId;
	}
	public void setmEmployeeId(String mEmployeeId) {
		this.mEmployeeId = mEmployeeId;
	}
	public String getmEmployeeName() {
		return mEmployeeName;
	}
	public void setmEmployeeName(String mEmployeeName) {
		this.mEmployeeName = mEmployeeName;
	}
	public String getmEmployeeCode() {
		return mEmployeeCode;
	}
	public void setmEmployeeCode(String mEmployeeCode) {
		this.mEmployeeCode = mEmployeeCode;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getComptabilityScore() {
		return comptabilityScore;
	}
	public void setComptabilityScore(String comptabilityScore) {
		this.comptabilityScore = comptabilityScore;
	}
	public String getPrevJobId() {
		return prevJobId;
	}
	public void setPrevJobId(String prevJobId) {
		this.prevJobId = prevJobId;
	}
	public String getPrevMapLocationId() {
		return prevMapLocationId;
	}
	public void setPrevMapLocationId(String prevMapLocationId) {
		this.prevMapLocationId = prevMapLocationId;
	}
	public String getPrevLats() {
		return prevLats;
	}
	public void setPrevLats(String prevLats) {
		this.prevLats = prevLats;
	}
	public String getPrevLongs() {
		return prevLongs;
	}
	public void setPrevLongs(String prevLongs) {
		this.prevLongs = prevLongs;
	}
	public String getDistanceToLocation() {
		return distanceToLocation;
	}
	public void setDistanceToLocation(String distanceToLocation) {
		this.distanceToLocation = distanceToLocation;
	}
	public List<MatchedSkill> getMatchedSkills() {
		return matchedSkills;
	}
	public void setMatchedSkills(List<MatchedSkill> matchedSkills) {
		this.matchedSkills = matchedSkills;
	}
	public List<EmployeeCommitment> getEmployeeCommitments() {
		return employeeCommitments;
	}
	public void setEmployeeCommitments(List<EmployeeCommitment> employeeCommitments) {
		this.employeeCommitments = employeeCommitments;
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
		return "MatchedEmployee [id=" + id + ", profileFinderId=" + profileFinderId + ", mEmployeeId=" + mEmployeeId
				+ ", mEmployeeName=" + mEmployeeName + ", mEmployeeCode=" + mEmployeeCode + ", designationName="
				+ designationName + ", comptabilityScore=" + comptabilityScore + ", prevJobId=" + prevJobId
				+ ", prevMapLocationId=" + prevMapLocationId + ", prevLats=" + prevLats + ", prevLongs=" + prevLongs
				+ ", distanceToLocation=" + distanceToLocation + ", matchedSkills=" + matchedSkills
				+ ", employeeCommitments=" + employeeCommitments + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}
	
}
