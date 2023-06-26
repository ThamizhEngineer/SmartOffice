export class Incident {
    id:                      number;
    vcId:                    string;
    vcCode:                  string;
    vcSummary:               string;
    vcCount:                 string;
    showScore:               string;
    trStartDt:               string;
    trEndDt:                 string;
    incidentType:            string;
    totalApplnCount:         string;
    shortlistedApplnCount:   string;
    incidentCreatedEmpId:    string;
    incidentStatus:          string;
    incidentCreatedDt:       string;
    approveUsrGrpId:         string;
    approverEmpId:           string;
    approvedDt:              string;
    hr1UsrGrpId:             string;
    hr2UsrGrpId:             string;
    incidentDate:            string;
    location:                string;
    hasTest:                 string;
    hasInterview:            string;
    hasTraining:             string;
    hasCertification:        string;
    isEntryLevel:            string;
    isShortListed:           string;
    isScheduled:             string;
    scheduleEmpId:           string;
    isProfessional:          string;
    testTemplateId:          string;
    testCode:                string;
    testTemplateName:        string;
    certificateTemplateId:   string;
    isEnabled:               string;
    createdBy:               string;
    createdDt:               string;
    modifiedBy:              string;
    modifiedDt:              string;
    incidentName:            string;
    incidentDesc:            string;
    mapLocId:string;
    locName:string;
    firstInterviewerId:      string;
    firstInterviewerName:    string;
    secondInterviewerId:     string;
    secondInterviewerName:   string;
    thirdInterviewerId:      string;
    thirdInterviewerName:    string;
    incidentCode:            string; 
    handlingGroupId:         string;
    handlingGroupName:       string;
    noOfDays:                string;
    faculty:                 string;
    contactNo:               string;
    noOfAttendees:           string;
    skillsAqd:               string;
    totalHeadCount:          string;
    totalAttendies:          string;
    certificateIssuedCount:  string;
    msg:                     any;

    participatingInstitutes: ParticipatingInstitute[];
    incidentApplicants:      IncidentApplicant[];
    incidentTestTemplates:   IncidentTestTemplate[];
    incidentTests:           IncidentTest[];
    applicantIdsToDelete:    string[];
    errorCode:string;
    errorDesc:string;
    errorMessage:string;
}

export class IncidentApplicant {
    id:                   number;
    incidentId:           string;
    vcId:                 string;
    source:               string;
    editApplicantId:      string ;
    arrayIndex:           string;
    referredByEmpId:      string;
    referredByEmpName:    string;
    firstName:            string;
    lastName:             string;
    mobileNumber:         string;
    email:                string;
    dob:                  string;
    gender:               string;
    currCompany:          string;
    currDesignation:      string;
    currExperience:       string;
    currLocation:         string;
    currSalary:           string;
    testEligibilityStatus:string;
    currentStatusForTest: string;
    institute:            string;
    course:               string;
    passoutYear:          string;
    marks:                string;
    historyOfArrears:     string;
    resumeDocId:          string;
    coverLetter:          string;
    applicationDt:        string;
    isShortListed:        string;
    isApplicantScheduled: string;
    isEligibleForTest:    string;
    dummyisTestScheduled: string;

    isTestScheduled:      string;
    isInterviewScheduled: any;
    isInterviewEligible: any;
    // isInterviewScheduled: string;
    // isInterviewEligible: string;
    testScheduledDt:      Date;
    shortListedDt:        string;
    slByEmpId:            string;
    firstInterviewerId:   string;
    secondInterviewerId:  string;
    thirdInterviewerId:   string;
    scheduledInterviewDt: string;
    interviewScheduledDt: string;
    secondInterviewDt:    string;
    thirdInterviewDt:     string;
    interviewId:          string;
    applicantId:          string;
    expType:              string;
    isEnabled:            string;
    createdBy:            string;
    createdDt:            string;
    modifiedBy:           string;
    modifiedDt:           string;
    finalTestStatus:      string;
    finalInterviewStatus: string;
    finalDecision:        string;
    empConversionMessage: string;
    empConversionStatus:  string;
    reject:               string;
    onhold:               string;
    approve:              string;
    testScheduledStatus:  string;    
    isAttended:           string;
    certificateIssued:    string;
    score:                string;
    reallocateMessage:    string;
    remarksFromTrainer:   string;
    skillsAcquired:       string;
    certificateIssuedDate:string;
    docId:                string;
    isReallocate:         string;
    dummyisReallocate:         string;
    select:               boolean;
    isDocUpload:boolean=false;
    isExperiencedType:any;
    isEditApplicant:boolean=false;
    isApplicantDetails:boolean;
    isviewApplicantDetails:boolean;
    degreeName:String;
    employeeCode:         string;
    employeeId:     string;
    errorCode:string;
    errorDesc:string;
    errorMessage:string;
    userName:string;
    password:string;
}

export class ParticipatingInstitute {
    id:         number;
    incidentId: string;
    name:       string;
    isEnabled:  string;
    createdBy:  string;
    createdDt:  string;
    modifiedBy: string;
    modifiedDt: string;
}

export class IncidentTestTemplate {
    id:                                 number;
    incidentId:                         number;
    testTemplateId:                     number;
    testTemplateName:                   string;
    description:                        string;
    duration:                           string;
    totalQuestions:                     string;
    passPercentage:                     string;
    isEnabled:                          string;
    createdBy:                          string;
    createdDt:                          string;
    modifiedBy:                         string;
    modifiedDt:                         string;
    remark:                             string;
    scheduled:                          string;
    creationStatus:                     string;
    creationMessage:                    string;
    currentlyAttending:                 string;
    completed:                          string;
    incidentApplicantTemplateQuestions: any[];
    // tIncidentId:                        number;  
}



export class IncidentTest {
    id:                        number;
    endTime:                   string;
    invigilatorComments:       string;
    invigilatorId:             string;
    isEnabled:                 string;
    noOfQuestions:             string;
    scoreMedian:               string;
    participantComment:        string;
    participantId:             number;
    passPercentage:            string;
    startTime:                 string;
    testDate:                  string;
    testStatus:                string;
    totalCorrect:              string;
    totalUnAttended:           string;
    totalWrong:                string;
    totalResult:               string;
    score:                     string;
    incidentId:                number;
    incidentName:              string;
    createdBy:                 string;
    createdDt:                 string;
    modifiedBy:                string;
    modifiedDt:                string;
    remainingTime:             string;
    duration:                  string;
    incidentTtId:              string;
    applicantId:               string;
    applicantName:             string;
    testTempLateId:            string;
    testTemplateName:          string;
    testResult:                string;
    totalPositiveMarkObtained: string;
    totalNegativeMarkObtained: string;
    incidentTestQuestions:     IncidentTestQuestion[];
}

export class IncidentTestQuestion {
    id:                   number;
    correctOption:        string;
    option1:              string;
    option2:              string;
    option3:              string;
    option4:              string;
    userPicked:           string;
    difficultyLevel:      DifficultyLevel;
    markPerQuestion:      string;
    marksAwarded:         string;
    negativeMarks:        string;
    questionId:           number;
    testStatus:           string;
    remainingTime:        string;
    duration:             string;
    createdBy:            string;
    createdDt:            string;
    modifiedBy:           string;
    modifiedDt:           string;
    questionOrder:        number;
    totalQuestions:       string;
    categoryId:           string;
    categoryName:         string;
    positiveMarkObtained: string;
    negativeMarkObtained: string;
    tincidentTestId:      number;
    question:             string;
    optionsCorrect:       string;
}

export enum DifficultyLevel {
    Easy = "EASY",
    Medium = "MEDIUM",
    Tough = "TOUGH",
}


export class IncidentApplicantPdf {
    referredByEmpName:    string="";
    firstName:            string="";
    lastName:             string="";
    mobileNumber:         string="";
    email:                string="";
    dob:                  string="";
    currCompany:          string="";
    currDesignation:      string="";
    currExperience:       string="";
    currLocation:         string="";
    currSalary:           string="";
    institute:            string="";
    course:               string="";
    passoutYear:          string="";
    marks:                string="";
    historyOfArrears:     string="";
    coverLetter:          string="";
    applicationDt:        string="";
}
