import { Injectable } from '@angular/core';
// import {Employee,AcademicAcheiv,BankDetails,Department,PreviousEmploymentDetails,EducationalInfo,EmergencyContDetails,FamilyInfo,LanguagesKnown} from '../../vo/employee';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class AttendanceReportService {
    constructor(private http: Http, private commonService: CommonService) { }
    

    getAllAttendance(){
        return this.http.get(environment.serviceApiUrl + 'transaction/attendance/direct', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getEmployee(){
        return this.http.get(environment.serviceApiUrl + 'master/employees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getN1Employees(){
        return this.http.get(environment.serviceApiUrl + 'master/employees/FetchN1Employees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    advanceSearch(employeeId,n1EmpId,startDate,endDate) {
        var url = 'transaction/attendance/direct/advanceFilters?d=1' 

        if(employeeId!=null){
            url= url+'&&employeeId='+employeeId   
        } 
        if(n1EmpId!=null){
            url= url+'&&n1EmpId='+n1EmpId   
        }   
        if(startDate!=null){
            url= url+'&&startDate='+startDate   
        }   
        if(endDate!=null){
            url= url+'&&endDate='+endDate   
        }   
      
        return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
}