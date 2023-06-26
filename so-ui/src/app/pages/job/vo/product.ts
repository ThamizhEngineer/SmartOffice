export class Product{
    id?:string;
    productCode?:string;
    productName?:string;
    mManufacturerId?:string;
    manufacturerName?:string;
    mProductFamilyId?:string;
    productFamilyName?:string;
    applicableServices:Array<ApplicableService>;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class ApplicableService{
    id?:string;
    mProductId?:string;
    serviceId?:string;
    serviceName?:string;
    serviceCode?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}