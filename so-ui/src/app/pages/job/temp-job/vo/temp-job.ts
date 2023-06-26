export class TempJob{
    id?:number;
    jobName?:string;
    jobCode?:string;
    jobActive?:string;
    officeId?:string;
    officeName?:string;
    tempJobEmps?:Array<TempJobEmp>=[];
         
}

export class TempJobEmp{
    id?:string;
    jobId?:string;
    employeeId?:string;
    fromDt?:string;
    toDate?:string;
}