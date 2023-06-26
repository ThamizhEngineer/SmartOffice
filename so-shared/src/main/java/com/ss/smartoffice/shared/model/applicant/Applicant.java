package com.ss.smartoffice.shared.model.applicant;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="t_applicant")

@Scope("prototype")
@ToString
@Getter
@Setter
public class Applicant {
	

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Formula("(SELECT CONCAT(applicant.first_name,'',applicant.last_name) FROM t_applicant applicant WHERE applicant.id= id)")
	private String applicantName;
	private String applicantCode;
	private String docId;
	@Formula("(SELECT doc.doc_location from d_doc_info doc left join t_applicant app on app.doc_id = doc.doc_id where app.id= id)")
	private String docLocation;
	@Formula("(SELECT doc.doc_name from d_doc_info doc left join t_applicant app on app.doc_id = doc.doc_id where app.id= id)")
	private String docName;
	private String firstName;
	private String lastName;
	private String emailId;
	private String dob;
	private String isExperienced="N";
	private String collegeName;
	private String degreeName;
	private String courseName;
	private String passedOut;
	
	private String curAddress;
	private String permAddress;
	private String maritalStatus;
	private String spouseName;
	private String spouseOccup;
	private Integer noOfChildren;
	private Integer noOfDependant;
	private String relInPodhigai;
	private String relName;
	private String relation;
	private String jobOpening;
	private String hobbies;
	private String offences;
	private String reference1;
	private String reference2;

	
	private String height;
	private String weight;
	private String bloodGroup;
	private String eyePower;
	private String eyeLeftPower;
	private String eyeRightPower;
	private String identifnMrk1;
	private String identifnMrk2;
	private String physicalChalng;
	
	private String name;
	private String relations;
	private String contactNo;
	
	private String monthlyRemun;
	private String annualRemun;
	private String benefitsRemun;
	private String grossRemun;
	private String grossOtherRemun;
	private String takeHomeRemun;
	private String nextIncrRemun;
	private String panNo;
	private String epfNo;
	private String passport;
	private String drivingLicense;
	private String esiNo;
	private	String aadharNo;
	@Column(name="hr_1_id")
	private String hr1Id;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id= hr_1_id)")
	private String hrManager1Name;
	@Column(name="hr_2_id")
	private String hr2Id;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id= hr_2_id)")
	private String hrManager2Name;
	private String remarks;	
	private String gender;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDt;
	private String contactMobileNo;
	private String contactEmailId;
	private String isEnabled;;
	private String createdBy;
	private String modifiedBy;
	private String loginUserId;
	private String appPdfId;
	private String empConversionStatus;

	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_applicant_id")
	private List<ApplicantLanguagesKnown> languages;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_applicant_id")
	private List<ApplicantFamilyInfo> familyInfo;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_applicant_id")
	private List<ApplicantEducationalInfo> educationalInfo;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_applicant_id")
	private List<ApplicantAcademicAcheiv> academicAcheiv;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_applicant_id")
	private List<ApplicantPreviousEmploymentDetails> previousEmployDetails;
	
	public Applicant() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Applicant(Integer id, String applicantName, String applicantCode, String firstName, String lastName,
			String contactNo, LocalDateTime startDt, LocalDateTime endDt, String contactMobileNo, String contactEmailId, String isEnabled, String loginUserId) {
		super();
		this.id = id;
		this.applicantName = applicantName;
		this.applicantCode = applicantCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.startDt = startDt;
		this.endDt = endDt;
		this.contactMobileNo = contactMobileNo;
		this.contactEmailId = contactEmailId;
		this.isEnabled = isEnabled;
		this.loginUserId = loginUserId;
	}



	public Applicant(Integer id, String applicantName, String applicantCode, String docId, String docLocation,
			String docName, String firstName, String lastName, String emailId, String dob, String isExperienced,
			String collegeName, String degreeName, String courseName, String passedOut, String curAddress,
			String permAddress, String maritalStatus, String spouseName, String spouseOccup, Integer noOfChildren,
			Integer noOfDependant, String relInPodhigai, String relName, String relation, String jobOpening,
			String hobbies, String offences, String reference1, String reference2, String height, String weight,
			String bloodGroup, String eyePower, String eyeLeftPower, String eyeRightPower, String identifnMrk1,
			String identifnMrk2, String physicalChalng, String name, String relations, String contactNo,
			String monthlyRemun, String annualRemun, String benefitsRemun, String grossRemun, String grossOtherRemun,
			String takeHomeRemun, String nextIncrRemun, String panNo, String epfNo, String passport,
			String drivingLicense, String esiNo, String aadharNo, String hr1Id, String hrManager1Name, String hr2Id,
			String hrManager2Name, String remarks, String gender, LocalDateTime startDt, LocalDateTime endDt,
			String contactMobileNo, String contactEmailId, String isEnabled, String createdBy, String modifiedBy,
			String loginUserId, String appPdfId, String empConversionStatus, List<ApplicantLanguagesKnown> languages,
			List<ApplicantFamilyInfo> familyInfo, List<ApplicantEducationalInfo> educationalInfo,
			List<ApplicantAcademicAcheiv> academicAcheiv,
			List<ApplicantPreviousEmploymentDetails> previousEmployDetails) {
		super();
		this.id = id;
		this.applicantName = applicantName;
		this.applicantCode = applicantCode;
		this.docId = docId;
		this.docLocation = docLocation;
		this.docName = docName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.dob = dob;
		this.isExperienced = isExperienced;
		this.collegeName = collegeName;
		this.degreeName = degreeName;
		this.courseName = courseName;
		this.passedOut = passedOut;
		this.curAddress = curAddress;
		this.permAddress = permAddress;
		this.maritalStatus = maritalStatus;
		this.spouseName = spouseName;
		this.spouseOccup = spouseOccup;
		this.noOfChildren = noOfChildren;
		this.noOfDependant = noOfDependant;
		this.relInPodhigai = relInPodhigai;
		this.relName = relName;
		this.relation = relation;
		this.jobOpening = jobOpening;
		this.hobbies = hobbies;
		this.offences = offences;
		this.reference1 = reference1;
		this.reference2 = reference2;
		this.height = height;
		this.weight = weight;
		this.bloodGroup = bloodGroup;
		this.eyePower = eyePower;
		this.eyeLeftPower = eyeLeftPower;
		this.eyeRightPower = eyeRightPower;
		this.identifnMrk1 = identifnMrk1;
		this.identifnMrk2 = identifnMrk2;
		this.physicalChalng = physicalChalng;
		this.name = name;
		this.relations = relations;
		this.contactNo = contactNo;
		this.monthlyRemun = monthlyRemun;
		this.annualRemun = annualRemun;
		this.benefitsRemun = benefitsRemun;
		this.grossRemun = grossRemun;
		this.grossOtherRemun = grossOtherRemun;
		this.takeHomeRemun = takeHomeRemun;
		this.nextIncrRemun = nextIncrRemun;
		this.panNo = panNo;
		this.epfNo = epfNo;
		this.passport = passport;
		this.drivingLicense = drivingLicense;
		this.esiNo = esiNo;
		this.aadharNo = aadharNo;
		this.hr1Id = hr1Id;
		this.hrManager1Name = hrManager1Name;
		this.hr2Id = hr2Id;
		this.hrManager2Name = hrManager2Name;
		this.remarks = remarks;
		this.gender = gender;
		this.startDt = startDt;
		this.endDt = endDt;
		this.contactMobileNo = contactMobileNo;
		this.contactEmailId = contactEmailId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.loginUserId = loginUserId;
		this.appPdfId = appPdfId;
		this.empConversionStatus = empConversionStatus;
		this.languages = languages;
		this.familyInfo = familyInfo;
		this.educationalInfo = educationalInfo;
		this.academicAcheiv = academicAcheiv;
		this.previousEmployDetails = previousEmployDetails;
	}



	


}
