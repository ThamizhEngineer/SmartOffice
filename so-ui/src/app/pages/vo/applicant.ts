export class Applicant{

  id ?:number;
  flowType?:string;
  applicantName ?:string;
  applicantCode ?:string;

  firstName ?:string;
  lastName ?:string;
docId?:string;
docLocation?:string;
  curAddress ?:string;
  permAddress ?:string;
  maritalStatus ?:string;
  spouseName ?:string;
  spouseOccup ?:string;
  noOfChildren ?:number;
  noOfDependant ?:number;
  relInPodhigai ?:string;
  relName ?:string;
  relation ?:string;
  jobOpening ?:string;
  hobbies ?:string;
  offences ?:string;
  reference1 ?:string;
  reference2 ?:string;
  dob ?:string;
  vDob?:string;
  height ?:string;
  weight ?:string;
  bloodGroup ?:string;
  eyePower ?:string;
  eyeLeftPower ?:string;
  eyeRightPower ?:string;
  identifnMrk1 ?:string;
  identifnMrk2 ?:string;
  physicalChalng ?:string;

  name ?:string;
  relations ?:string;
  contactNo ?:string;

  monthlyRemun ?:string;
  annualRemun ?:string;
  benefitsRemun ?:string;
  grossRemun ?:string;
  grossOtherRemun ?:string;
  takeHomeRemun ?:string;
  nextIncrRemun ?:string;
  vNextIncrRemun?: string;
  panNo ?:string;
  epfNo ?:string;
  passport ?:string;
  drivingLicense ?:string;
  esiNo ?:string;
 aadharNo ?:string;	
  hrManagerId ?:string;	
  hrManagerName ?:string;
  remarks ?:string;	
  gender ?:string;	
startDt ?:string;
 endDt ?:string;
  contactMobileNo ?:string;
  contactEmailId ?:string;
  isEnabled ?:string;
  modifiedBy ?:string;
  loginUserId ?:string;
  appPdfId?:string;


  emailId:string;
 
  collegeName:string;
  degreeName:string;
  courseName:string;
  passedOut:string;
  
  
  
  createdBy:string;



  languages?: Array<ApplicantLanguagesKnown>;
  familyInfo?: Array<ApplicantFamilyInfo>;
  educationalInfo?: Array<ApplicantEducationalInfo>;
  academicAcheiv?: Array<ApplicantAcademicAcheiv>;
  previousEmployDetails?: Array<ApplicantPreviousEmploymentDetails>;
  
}


export class ApplicantLanguagesKnown {
  id?: number;
  language?: string;
  langRead?: string;
  langWrite?: string;
  langSpeak?: string;
  startDt?: string;
  endDt?: string;
  isEnabled?: string;
  createdBy?: string;
  modifiedBy?: string;
  createdDt?: Date;
  modifiedDt?: Date;
}

export class ApplicantFamilyInfo {
  id?: number;
  name?: string;
  relation?: string;
  occupation?: string;
  contactNo?: string;
  email?: string;
  startDt?: Date;
  endDt?: Date;
  isEnabled?: string;
  createdBy?: string;
  modifiedBy?: string;
  createdDt?: Date;
  modifiedDt?: Date;
}

export class ApplicantEmergencyContDetails {
  id?: number;
  name?: string;
  relation?: string;
  contactNo?: string;
  startDt?: Date;
  endDt?: Date;
  isEnabled?: string;
  createdBy?: string;
  modifiedBy?: string;
  createdDt?: Date;
  modifiedDt?: Date;
}

export class ApplicantEducationalInfo {
  id?: number;
  examName?: string;
  place?: string;
  yearOfPassing?: number;
  specialization?: string;
  score?: number;
  type?: string;
  startDt?: Date;
  endDt?: Date;
  isEnabled?: string;
  createdBy?: string;
  modifiedBy?: string;
  createdDt?: Date;
  modifiedDt?: Date;
}


export class ApplicantAcademicAcheiv {
  id?: number;
  organization?: string;
  trainDetails?: string;
  fromDt?: string;
  vFromDt?: string;
  toDt?: string;
  vToDt?: string;
  startDt?: string;
  endDt?: string;
  isEnabled?: string;
  createdBy?: string;
  modifiedBy?: string;
  createdDt?: Date;
  modifiedDt?: Date;
}

export class ApplicantPreviousEmploymentDetails {
  id?: number;
  orgName?: string;
  orgPosition?: string;
  fromDt?: string;
  vFromDt?: string;
  toDt?: string;
  vToDt?: string;
  jobDesc?: string;
  startDt?: Date;
  endDt?: Date;
  isEnabled?: string;
  createdBy?: string;
  modifiedBy?: string;
  createdDt?: Date;
  modifiedDt?: Date;
}
