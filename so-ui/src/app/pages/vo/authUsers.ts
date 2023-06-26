export class AuthEmployee {
    authId:      number;
    employeeId:  string;
    employee:    string;
    emailId:     string;
    empType:     string;
    empStatus:   string;
    userName:    string;  
    isEnabled:   string;
    loginAccess: string;
    password    :string;
    isEnabledFlag:        boolean;
    authUserRoles:   AuthUserRole[];
}
export class AuthUserRole {
    appSystemId:      string;
    appUserId:        string;
    id:               number;
    authUserId:       string;
    authRoleId:       string;
    authRoleCode:     string;
    isEnabled:        string;
    createdBy:        string;
    modifiedBy:       string;
    createdDt:        string;
    modifiedDt:       string;
    listAuthFeatures: string;
}

export class AuthApplicant {
    authId:      number;
    applicantId: number;
    applicant:   string;
    emailId:     string;
    userName:    string;
    isEnabled:   string;
    isEnabledFlag:        boolean;
    loginAccess: string;
}

export class AuthClient {
    authId:      number;
    partnerId:   number;
    partnerEmpId:number;
    partnerEmpName:string;
    client:      string;
    emailId:     string;
    userName:    string;
    isEnabled:   string;
    isEnabledFlag:        boolean;
    loginAccess: string;
}

export class AuthVendor {
    authId:      number;
    partnerId:   number;
    partnerEmpId:number;
    partnerEmpName:string;
    vendor:      string;
    emailId:     string;
    userName:    string;   
    isEnabled:   string;
    isEnabledFlag:        boolean;
    loginAccess: string;
}