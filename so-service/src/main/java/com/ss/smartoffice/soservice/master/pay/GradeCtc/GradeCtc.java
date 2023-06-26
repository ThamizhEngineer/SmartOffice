package com.ss.smartoffice.soservice.master.pay.GradeCtc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="m_grade_ctc")

public class GradeCtc extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String gradeId;
	private String isActive;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate validFromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate validToDt;
	private String head1Name;
	private String head2Name;
	private String head3Name;
	private String head4Name;
	private String head5Name;
	private String head6Name;
	private String head7Name;
    private String head8Name;
    private String head9Name;
    private String head10Name;
    
    

	
	private String head1Total;
	private String head2Total;
	private String head3Total;
	private String head4Total;
	private String head5Total;
	private String head6Total;
	private String head7Total;
	private String head8Total;
	private String head9Total;
	private String head10Total;
    
    private String fixedBenefits;
    private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="m_grade_ctc_id")
	private List<GradeCtcLine> gradeCtcLine;
	public GradeCtc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GradeCtc(int id, String gradeId, String isActive, LocalDate validFromDt, LocalDate validToDt,
			String head1Name, String head2Name, String head3Name, String head4Name, String head5Name, String head6Name,
			String head7Name, String head8Name, String head9Name, String head10Name, String head1Total,
			String head2Total, String head3Total, String head4Total, String head5Total, String head6Total,
			String head7Total, String head8Total, String head9Total, String head10Total, String fixedBenefits,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt,
			List<GradeCtcLine> gradeCtcLine) {
		super();
		this.id = id;
		this.gradeId = gradeId;
		this.isActive = isActive;
		this.validFromDt = validFromDt;
		this.validToDt = validToDt;
		this.head1Name = head1Name;
		this.head2Name = head2Name;
		this.head3Name = head3Name;
		this.head4Name = head4Name;
		this.head5Name = head5Name;
		this.head6Name = head6Name;
		this.head7Name = head7Name;
		this.head8Name = head8Name;
		this.head9Name = head9Name;
		this.head10Name = head10Name;
		this.head1Total = head1Total;
		this.head2Total = head2Total;
		this.head3Total = head3Total;
		this.head4Total = head4Total;
		this.head5Total = head5Total;
		this.head6Total = head6Total;
		this.head7Total = head7Total;
		this.head8Total = head8Total;
		this.head9Total = head9Total;
		this.head10Total = head10Total;
		this.fixedBenefits = fixedBenefits;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.gradeCtcLine = gradeCtcLine;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public LocalDate getValidFromDt() {
		return validFromDt;
	}
	public void setValidFromDt(LocalDate validFromDt) {
		this.validFromDt = validFromDt;
	}
	public LocalDate getValidToDt() {
		return validToDt;
	}
	public void setValidToDt(LocalDate validToDt) {
		this.validToDt = validToDt;
	}
	public String getHead1Name() {
		return head1Name;
	}
	public void setHead1Name(String head1Name) {
		this.head1Name = head1Name;
	}
	public String getHead2Name() {
		return head2Name;
	}
	public void setHead2Name(String head2Name) {
		this.head2Name = head2Name;
	}
	public String getHead3Name() {
		return head3Name;
	}
	public void setHead3Name(String head3Name) {
		this.head3Name = head3Name;
	}
	public String getHead4Name() {
		return head4Name;
	}
	public void setHead4Name(String head4Name) {
		this.head4Name = head4Name;
	}
	public String getHead5Name() {
		return head5Name;
	}
	public void setHead5Name(String head5Name) {
		this.head5Name = head5Name;
	}
	public String getHead6Name() {
		return head6Name;
	}
	public void setHead6Name(String head6Name) {
		this.head6Name = head6Name;
	}
	public String getHead7Name() {
		return head7Name;
	}
	public void setHead7Name(String head7Name) {
		this.head7Name = head7Name;
	}
	public String getHead8Name() {
		return head8Name;
	}
	public void setHead8Name(String head8Name) {
		this.head8Name = head8Name;
	}
	public String getHead9Name() {
		return head9Name;
	}
	public void setHead9Name(String head9Name) {
		this.head9Name = head9Name;
	}
	public String getHead10Name() {
		return head10Name;
	}
	public void setHead10Name(String head10Name) {
		this.head10Name = head10Name;
	}
	public String getHead1Total() {
		return head1Total;
	}
	public void setHead1Total(String head1Total) {
		this.head1Total = head1Total;
	}
	public String getHead2Total() {
		return head2Total;
	}
	public void setHead2Total(String head2Total) {
		this.head2Total = head2Total;
	}
	public String getHead3Total() {
		return head3Total;
	}
	public void setHead3Total(String head3Total) {
		this.head3Total = head3Total;
	}
	public String getHead4Total() {
		return head4Total;
	}
	public void setHead4Total(String head4Total) {
		this.head4Total = head4Total;
	}
	public String getHead5Total() {
		return head5Total;
	}
	public void setHead5Total(String head5Total) {
		this.head5Total = head5Total;
	}
	public String getHead6Total() {
		return head6Total;
	}
	public void setHead6Total(String head6Total) {
		this.head6Total = head6Total;
	}
	public String getHead7Total() {
		return head7Total;
	}
	public void setHead7Total(String head7Total) {
		this.head7Total = head7Total;
	}
	public String getHead8Total() {
		return head8Total;
	}
	public void setHead8Total(String head8Total) {
		this.head8Total = head8Total;
	}
	public String getHead9Total() {
		return head9Total;
	}
	public void setHead9Total(String head9Total) {
		this.head9Total = head9Total;
	}
	public String getHead10Total() {
		return head10Total;
	}
	public void setHead10Total(String head10Total) {
		this.head10Total = head10Total;
	}
	public String getFixedBenefits() {
		return fixedBenefits;
	}
	public void setFixedBenefits(String fixedBenefits) {
		this.fixedBenefits = fixedBenefits;
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
	public List<GradeCtcLine> getGradeCtcLines() {
		return gradeCtcLine;
	}
	public void setGradeCtcLines(List<GradeCtcLine> gradeCtcLine) {
		this.gradeCtcLine = gradeCtcLine;
	}
	@Override
	public String toString() {
		return "GradeCtc [id=" + id + ", gradeId=" + gradeId + ", isActive=" + isActive + ", validFromDt=" + validFromDt
				+ ", validToDt=" + validToDt + ", head1Name=" + head1Name + ", head2Name=" + head2Name + ", head3Name="
				+ head3Name + ", head4Name=" + head4Name + ", head5Name=" + head5Name + ", head6Name=" + head6Name
				+ ", head7Name=" + head7Name + ", head8Name=" + head8Name + ", head9Name=" + head9Name + ", head10Name="
				+ head10Name + ", head1Total=" + head1Total + ", head2Total=" + head2Total + ", head3Total="
				+ head3Total + ", head4Total=" + head4Total + ", head5Total=" + head5Total + ", head6Total="
				+ head6Total + ", head7Total=" + head7Total + ", head8Total=" + head8Total + ", head9Total="
				+ head9Total + ", head10Total=" + head10Total + ", fixedBenefits=" + fixedBenefits + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + ", gradeCtcLine=" + gradeCtcLine + "]";
	}
	
	
}
