export class Product{
    id?:String;
    materialCategory?:String;
    materialCode?:String;
    materialName?:String;
    materialDesc?:String;
    remarks?:String;
    hasInventory?:String;
    statusCode?:String;
    unitOfMeasure?:String;
    hsnCode?:String;
    unitPrice?:String;
    manufacturerId?:String;
    productFamilyId?:String;
    assetMake?:String;
    assetModel?:String;
    assetOwner?:String;
    isEnabled?:String;
    createdBy?:String;
    modifiedBy?:String;
    createdDt?:String;
    modifiedDt?:String;
    materialServices?:Array<MaterialService>=[];
}

export class MaterialService{
id?:String;
materialId?:String;
serviceCode?:String;
serviceName?:String;
serviceDesc?:String;
isEnabled?:String;
createdBy?:String;
modifiedBy?:String;
createdDt?:String;
modifiedDt?:String;
}