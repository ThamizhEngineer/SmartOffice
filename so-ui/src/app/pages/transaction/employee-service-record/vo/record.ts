export class EmployeeServiceHeader {

    id: number;
    mempId?: string;
    officeId?: string;
    esrTypeCode?: string;
    esrDt?: string;
    recordData?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;
    empCode?: string;
    empFname?: string;
    empLname?: string;
    officeName?: string;
    internalId?: string;
    mOfficeId?: string;
    n1EmpId?: string;
    n2EmpId?: string;
    n1EmpName?:String;
    n2EmpName?:String;
    employeeServiceLine?: EmployeeServiceLines[]=[];


}

export class EmployeeServiceLines{

    id?: number;
    EsrHdrId?: string;
    busKey?: string;
    busValue?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;
}