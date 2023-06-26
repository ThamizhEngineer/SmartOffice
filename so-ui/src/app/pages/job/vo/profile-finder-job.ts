export class ProfileFinderJob{
    id?:string;
    mProfileId?:string;
    fromDt?: string = new Date().toJSON();
    toDt?: string = new Date().toJSON();
    mapLocationId?:string;
    status?:string;
    remarks?:string;
    mEmployeeId?:string;
    mDepartmentId?:string;
    mBuId?:string;
    matchedEmployees?:Array<MatchedEmployee>;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;        
}

export class MatchedEmployee{
    id?:string
    profileFinderId?:string;
    mEmployeeId?:string;
    mEmployeeName?:string;
    mEmployeeCode?:string;
    designationName?:string;
    comptabilityScore?:string;
    prevJobId?:string;
    prevMapLocationId?:string;
    prevLats?:string;
    prevLongs?:string;
    distanceToLocation?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}