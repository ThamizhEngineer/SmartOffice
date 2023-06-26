
export class BusinessUnit {
    id?: string;
    buName?: string;
    buCode?: string;
    noOfDivisions?: string;
    hasProducts?: any;
    hasServices?: any;
    isEnabled?: any;
    createdBy?: string;
    modifiedBy?: string;
    divisions?: [Division];
    createdDt?: string;
    modifiedDt?: string;
    divisionGoods: any;

}
export class Division {
    id?: string;
    mBuId?: string;
    mBuisnessName?: string;
    divisionName?: string;
    productNames?: [DivisionGood];
    serviceNames?: [DivisionService];
    remarks?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;
}
export class DivisionGood {
    id?: string;
    mGoodsId?: string; //Id from productList (master)
    mDivisionId?: string; //HeaderId (Division)
    materialCode?: string; //materialCode from productList (master)
    materialName?: string;
    divisionName?: string;
    goodsHandled?: string;
    remarks?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;
}
export class DivisionService {
    id?: string;
    mServiceId?: string;
    serviceCode?: string;
    serviceName?: string;
    mDivisionId?: string;
    divisionName?: string;
    serviceHandled?: string;
    remarks?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;
}




