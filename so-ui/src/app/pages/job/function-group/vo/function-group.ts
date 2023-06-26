export class FunctionGroup{
 id: string;
 funGrpName:string;
 funGrpCode:string;
 remarks:string;
 deliveryType:string;
//  functions:Array<Function>;
 isEnabled:string;
 createdBy:string;
 modifiedBy:string;
 createdDt:Date;
 modifiedDt:Date;
functions:    Function[];


}



export class Function{

    id:string;
    funGrpId:string;
    deliveryType:string;
    funGrpName:string;
    funGrpDesc:string;
    funCode:string;
    funName:string;
    remarks:string;
    division:  Division[];
    isEnabled:string;
    createdBy:string;
    modifiedBy:string;
    createdDt:Date;
    modifiedDt:Date;

}

export class Division{
    id:   string;
    divisionName:string;
}
