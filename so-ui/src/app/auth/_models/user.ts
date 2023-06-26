export class User {
    appSystemId:          string;
    appUserId:            string;
    id:                   number;
    employeeId:           string;
    applicantId:          string;
    partnerId:            string;
    firstName:            string;
    lastName:             string;
    userName:             string;
    password:             string;
    emailId:              string;
    groupId:              string;
    appToken:             string;
    mobileNumber:         string;
    appClientId:          string;
    tokenValidityDt:      string;
    userAccessDt:         string;
    userType:             string;
    createdBy:            string;
    createdDt:            string;
    modifiedBy:           string;
    modifiedDt:           string;
    duration:             string;
    empCode:              string;
    empDesignation:       string;
    userStatus:           string;
    latitude:             string;
    locationStatus:       string;
    longitude:            string;
    proxyEmpId:           string;
    lastLoggedDt:         string;
    lastCheckInDt:        string;
    lastCheckOutDt:       string;
    acceptedAgmt:         string;
    acceptedAgmtDt:       string;
    acceptedAgmtLocation: string;
    isEnabled:            string;
    isEnabledFlag:        boolean;
    isCheckedIn:          string;
    city:                 string;
    state:                string;
    country:              string;
    metadata:             any[];
    authUserRoles:        AuthUserRole[];
    userGroupMappings:    UserGroupMapping[];
    menuItems:            string;
    listAuthFeatures:     string;
    applicationCode ?: string;
    menu?:Menu[];
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

export class UserGroupMapping {
    id:            number;
    userGroupId:   string;
    userGroupName: string;
    isHrL1:        string;
    isHrL2:        string;
    isAcctL1:      string;
    isAcctL2:      string;
    isDir:         string;
    isAdmin:       string;
    employeeId:    string;
    employeeName:  string;
    authUserId:    string;
    authUser:      string;
    isEnabled:     string;
    createdBy:     string;
    modifiedBy:    string;
    createdDt:     string;
    modifiedDt:    string;
}

export class Menu {
    id:        string;
    name:      string;
    state:     string;
    type:      string;
    icon:      string;
    userId:    string;
    featureId: string;
    order:     number;
    children:  Child[];
}

export class Child {
    authMenuId:    string;
    name:          string;
    state:         string;
    subState:      string;
    type:          string;
    authFeatureId: string;
    purpose:       string;
    order:         number;
}