export class Manufacturer {
    id?:string;
    manufacturerCode?:string;
    manufacturerName?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
    productFamilies:Array<ProductFamily> = [];
    productFamilyCount?:string;
}

export class ProductFamily{
    id?:string;
    manufacturerId?:string;
    productFamilyName?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}