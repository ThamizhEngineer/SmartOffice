export class Announcement{
    id?:string;
    announcementCode?:string;
    subject?:string;
    message?:string;
    announcementCategory?:string;
    createdBy?:string;
    modifiedBy?:string;
    offices?: Array<Office>;
}
export class Office{
    id?:string;
    officeName?:string;
    description?:string;
    countryCode?:string;
    employeeId?:string;
    empName?:string;
    countryId?:string;
    officeAbbreviation?:string;
    lastEmpSequence?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:string;
    modifiedDt?:string;
}