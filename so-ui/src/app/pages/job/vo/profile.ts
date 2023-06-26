export class Profile{
    
    id?:string;
    profileCode?:string;
    profileName?:string;
    lineCount?:string;
    profileLines?:Array<ProfileLine>;

    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;

}

export class ProfileLine{

    id?:string;
    profileId?:string;
    serviceId?:string;
    productId?:string;
    skillLevel?:string='1';
    serviceName?:string="";
    productName?:string="";
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}