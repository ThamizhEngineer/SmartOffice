
export class BuisnessUnit{
    buName?:string;
    buCode?:string;
    hasProducts?:string;
    hasServices?:string;
    isEnabled?: string;
    createdBy?: string;
    createdDt?: string;
    modifiedBy?: string;
    modifiedDt?: string;
    divisions?: Array<Division>;
}
export class Division{
    id?: string;
     mBuId?:string;
     divisionName?:string;
     isEnabled?: string;
     createdBy?: string;
     createdDt?: string;
     modifiedBy?: string;
     modifiedDt?: string;
}


 

