export class Employee {

    id?: number;
    flowType?:string;
    empName?: string;
    firstName?:string;
    lastName?:string;
    empCode?: string;
    tempContactNo?: string;
    permContactNo?: string;
    emailId?: string;
    dob?: string;
    vDob?:string;
    gender?:string;
    curAddress?: string;
    permAddress?: string;
    maritalStatus?: string;
    isBillable?:string;
    officeName?:string;
    spouseName?: string;
    spouseOccup?: string;
    noOfChildren?: number;
    noOfDependant?: number;
    relInPodhigai?: string;
    relName?: string;
    relation?: string;
    jobOpening?: string;
    hobbies?: string;
    offences?: string;
    docUrl?:string;
    reference1?: string;
    reference2?: string;
    height?: string;
    weight?: string;
    bloodGroup?: string;
    eyePower?: string;
    eyeLeftPower?: string;
    eyeRightPower?: string;
    identifnMrk1?: string;
    identifnMrk2?: string;
    physicalChalng?: string;
    name?: string;
    empStatus?:string;
    relations?: string;
    contactNo?: string;
    monthly?: string;
    annually?: string;
    benefits?: string;
    gross?: string;
    grossOther?: string;
    takeHome?: string;
    nextIncr?: string;
    vNextIncr?: string;
    panNo?: string;
    internalId?:string;
    epfNo?: string;
    passport?: string;
    drivingLicense?: string;
    esiNo?: string;
    aadharNo?: string;
    n1EmpId?:string;
    managerName?:string;
    n2EmpId?:string;
    reviewManagerName?:string;
    hr1Id?:string;
    hr2Id?:string;
    isAcc1Id?:string;
    isAcc2Id?:string;
    officeId?:string;
    docId?:string;
    docLocation?:string;
    docName?:string;
    panDocId?:string;
    aadharDocId?:string;
    passBookDocId?:string;
    remarks?: string;
    contractorCode?:string;
    startDt?: string;
    designationId?: string;
    desigName?: string;
    endDt?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    contactMobileNo?:string;
    contactEmailId?:string;
    modifiedDt?: string;
    legalPending?:string;
    shiftId?:string;
    shiftName?:string;
    shiftCode?:string;
    empCategory?:string;
    pfNo?:string;
    uanNo?:string;
    relaContNo2?:string;
    relaEmailId?:string;
    prePfNo?:string;
    preEsiNo?:string;
    preUanNo?:string;
    drivLicDocId?:string;
    passDocId?:string;
    addrProofDocId?:string;
    dirUsrGrpId?:string;
    dirUsrGrpName?:string;
    panCheck?:string;
    adCheck?:string;
    addrPrCheck?:string;
    passCheck?:string;
    drivLicCheck?:string;
    hr1UsrGrpId?:string;
    hr2UsrGrpId?:string;
    acc1UsrGrpId?:string;
    acc2UsrGrpId?:string;
    isMiddleLevelManager?:string;
    isHighLevelManager?:string;
    deptId?:string;
    empProfilePdfId?:string;

    isIniteated:boolean;
    reIniteated:boolean;
    isAchvmt:boolean=false;
    appraisalId:number;

    department?: Array<Department>;
    languages?: Array<LanguagesKnown>;
    familyInfo?: Array<FamilyInfo>;
    educationalInfo?: Array<EducationalInfo>;
    academicAcheiv?: Array<AcademicAcheiv>;
    previousEmployDetails?: Array<PreviousEmploymentDetails>;
    bankDetails?: Array<BankDetails>;
    employeeSkills?: Array<EmployeeSkill>;
}
export class EmployeeSkill {
id?:string;
mprofileId?:number;
mproductId?:number;
skillLevelCode?:string;
isEnabled?: string;
createdBy?: string;
modifiedBy?: string;
createdDt?: Date;
modifiedDt?: Date;
productName?: string="";
serviceName?: string="";
}

export class Department {
    id?: string;
    deptName?: string;
    deptCode?: number;
    remarks?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: Date;
    modifiedDt?: Date;
}


export class LanguagesKnown {
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

export class FamilyInfo {
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

export class EmergencyContDetails {
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

export class EducationalInfo {
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


export class AcademicAcheiv {
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

export class PreviousEmploymentDetails {
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
export class BankDetails {
    id?: number;
    bankName?: string;
    branchName?: string;
    accNo?: string;
    isDefault?:string;
    accountName?:string;
    isIcici?:string;
    ifscCode?: string;
    bankAddress?: string;
    bankCountry?: string;
    startDt?: Date;
    endDt?: Date;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: Date;
    modifiedDt?: Date;
}