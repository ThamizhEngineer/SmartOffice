export class User{
    id?:string;
    employeeId?:string;
    applicantId?:string;
    partnerId?:string;
    firstName?:string;
    lastName?:string;
    userName?:string;
    password?:string;
    emailId?:string;
    groupId?:string;
    appToken?:string;
    mobileNumber?:string;
    appClientId?:string;
    tokenValidityDt?:Date;
    userAccessDt?:Date;
    userType?:string;
    isEnabled?:string;
    createdBy?:string;
    createdDt?:Date;
    modifiedBy?:string;
    modifiedDt?:Date;
    duration?:string;
    empCode?:string;
    empDesignation?:string;
    userStatus?:string;
    latitude?:string;
    locationStatus?:string;
    longitude?:string;
    proxyEmpId?:string;
    lastLoggedDt?:Date;
    lastCheckInDt?:Date;
    lastCheckOutDt?:Date;
    acceptedAgmt?:string;
    acceptedAgmtDt?:Date;
    acceptedAgmtLocation?:string;
    isCheckedIn?:string;
    city?:string;
    state?:string;
    country?:string;
    applicationCode?:string;
    metadata?:Array<Metadata>;
    authUserRoles?:Array<AuthUserRoles>;   
    userGroupMappings?:Array<UserGroupMappings>;
    authToken?:Array<AuthToken>;
    menuItems?:Array<MenuItems>;
}

export class Metadata{
}

export class AuthUserRoles{
}

export class UserGroupMappings{
}

export class AuthToken{
}

export class MenuItems{
}