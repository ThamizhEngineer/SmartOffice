
export class Contractor {
    id:                    number;
    internalId:            string;
    contractorCode:        string;
    empTypeCode:           string;
    isBillable:            string;
    firstName:             string;
    lastName:              string;
    emailId:               string;
    contactMobileNo:       string;
    managerId:             string;
    doj:                   string;
    vDoj:                   string;
    dob:                   string;
    vDob:                   string;
    officeId:              string;
    countryId:             number;
    docId:                 string;
    isEnabled:             string;
    createdBy:             string;
    modifiedBy:            string;
    createdDt:             string;
    modifiedDt:            string;
    managerName: string;
    nextIncr?: string;
    vNextIncr?: string;
    bankDetails: Array<BankDetails>;
    employeeSkills?: Array<EmployeeSkill>;
}

export class BankDetails{

     id:number;
	 bankName:string;
     branchName:string;
	 accNo:string;
	 ifscCode:string;
	 bankAddress:string;
	 bankCountry:string;
	 isEnabled:string;
	 createdBy:string;
     modifiedBy:string;
     createdDt:string;
     modifiedDt:string;
}


export class EmployeeSkill {
id?:string;
mServiceId?:string;
mProductId?:string;
skillLevelCode?:string;
isEnabled?: string;
createdBy?: string;
modifiedBy?: string;
createdDt?: Date;
modifiedDt?: Date;
}