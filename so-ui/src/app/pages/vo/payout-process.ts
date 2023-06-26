export class PayoutProcess{
    id?:string;
    payCycleLineId?:string;
    payCycleLineCode?:string;
    processStatus?:string;
    successCount?:string;
    employeeId?:string;
    failureCount?:string;
    overWrite?:string;
    isEnabled?:string;
    month?:string;
    year?:string;
    startDt?:Date;
    endDt?:Date;
    payoutProcessLines?:Array<PayoutProcessLine>;

}
export class PayoutProcessLine{
    id?:string;
    payoutProcessHdrId?:string;
    employeeId?:string;
    empCode?:string;
    empName?:String;
    resultCode?:string;
    resultDesc?:string;
    isEnabled?:string;
}