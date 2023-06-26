import { Injectable } from '@angular/core';
import {Employee,AcademicAcheiv,BankDetails,Department,PreviousEmploymentDetails,EducationalInfo,EmergencyContDetails,FamilyInfo,LanguagesKnown} from '../../vo/employee';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { EmployeeServiceHeader } from './vo/record';

@Injectable()
export class EmployeeServiceService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	
    constructor(private http: Http, private commonService: CommonService) {

    }

    getOffices(){
        return this.http.get(this.baseUrl + 'master/offices', this.commonService.jwt()).map((response: Response) => response.json());    
    }
    getEmployeeProfile(){
        return this.http.get(this.baseUrl+'master/emp-profiles',this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getEmployeeService(){
        return this.http.get(this.baseUrl+'transaction/employeeServiceRecord',this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getEmployeeServiceId(id:number){
        return this.http.get(this.baseUrl+'transaction/employeeServiceRecord/'+id,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    updateEmployeeService(employeeServiceHeader:EmployeeServiceHeader,id:number){
        return this.http.patch(this.baseUrl+'transaction/employeeServiceRecord/'+id,employeeServiceHeader,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    addEmployeeService(employeeServiceHeader:EmployeeServiceHeader){
        return this.http.post(this.baseUrl+'transaction/employeeServiceRecord',employeeServiceHeader,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    deleteEmployeeService(id:string){
        return this.http.delete(this.baseUrl+'transaction/employeeServiceRecord/'+id+'/delete/header',this.commonService.jwt()).map((response:Response)=>response);
    }
    addOrUpdateEmployeeService(employeeServiceHeader:EmployeeServiceHeader){
        return this.http.post(this.baseUrl+'transaction/employeeServiceRecord/addOrUpdate',employeeServiceHeader,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    advanceSearch(internalId,empCode,empFname,officeName,n1EmpId) {
        var url = 'transaction/employeeServiceRecord/advance-filters?' 

        if(internalId!=null){
            url= url+'internalId='+internalId   
        } 
        if(empCode!=null){
            url= url+'&&empCode='+empCode   
        }   
        if(empFname!=null){
            url= url+'&&empFname='+empFname   
        } 
        if(officeName!=null){
            url= url+'&&officeName='+officeName   
        } 
        if(n1EmpId!=null){
            url= url+'&&n1EmpId='+n1EmpId   
        } 
      
        return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getType() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=ESR-TYPE-CODE', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}