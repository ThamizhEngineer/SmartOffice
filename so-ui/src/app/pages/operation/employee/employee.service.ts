import { Injectable } from '@angular/core';

import {Employee,AcademicAcheiv,BankDetails,Department,PreviousEmploymentDetails,EducationalInfo,EmergencyContDetails,FamilyInfo,LanguagesKnown} from '../../vo/employee';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class EmployeeService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	
    constructor(private http: Http, private commonService: CommonService) {

    }

    getEmployeeById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'master/employees/' + _id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    hr1GetEmployee(searchEmpName,searchDesigName,searchEmailId,searchContactMobileNo,
        serachOfficeName,searchEmpCode,searchEmpStatus,serachId){
           var andSplit= "&";
        var fromFilter = "empName="+searchEmpName+andSplit+"desigName="+searchDesigName+andSplit+"emailId="
        +searchEmailId+andSplit+"contactMobileNo="+searchContactMobileNo+andSplit+"officeName="+serachOfficeName+andSplit+"empCode="+searchEmpCode+andSplit+
        "empStatus="+searchEmpStatus+andSplit+"id="+serachId+"&n1EmpId=&n2EmpId=";
        console.log(fromFilter)
        return this.http.get(environment.serviceApiUrl+'master/emp-profiles/hr1-profile-validate?'+fromFilter, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getEmployees() {
        return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getDepartment() {
        return this.http.get(environment.serviceApiUrl+'master/departments', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    
    getConfig(){
        return this.http.get(environment.serviceApiUrl+'seed/configs?configHeaderCode=ATTENDANCE-CODES', this.commonService.jwt()).map(res => res.json());
    }

    getAttendanceShift(){
        return this.http.get(environment.serviceApiUrl+'master/shift', this.commonService.jwt()).map(res => res.json());
    }
    getAcadmicType(){
        return this.http.get(environment.serviceApiUrl+'master/academic-type', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	getAcadmicName(){
        return this.http.get(environment.serviceApiUrl+'master/academic-colleges', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    addEmployee(employee: Employee) {
      
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        // let options = new RequestOptions({ headers: headers });
        // let body = JSON.stringify(employee);
        // let url = environment.serviceApiUrl+'master/employees/';
        // return this.http.post(url, body, options).map((res: Response) => res.json());
        return this.http.post(environment.serviceApiUrl+'master/employees/email-event', employee, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateEmployee(employee: Employee,id:any) {
   
        return this.http.patch(environment.serviceApiUrl+'master/employees/'+id, employee, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    employeeUpdate(id:any,action:any,employee:Employee){
        return this.http.patch(environment.serviceApiUrl+'master/emp-profiles/'+id+'/'+action, employee, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    createProfile(employee:Employee){
        return this.http.post(environment.serviceApiUrl+'master/emp-profiles/create', employee, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getN1EmployeeSkill(){
        return this.http.get(environment.serviceApiUrl+'master/emp-profiles/n1-skill-validate', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getCompleteProfile(){
        return this.http.get(environment.serviceApiUrl+'master/emp-profiles/complete-profile', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
          
	getGrades(){
		return this.http.get(this.baseUrl + 'seed/grades', this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	getDesignations(){
		return this.http.get(this.baseUrl + 'seed/designations', this.commonService.jwt()).map((response: Response) => response.json());
    }
    getHrL1(){
        return this.http.get(this.baseUrl + 'seed/user-groups?isHrL1=Y', this.commonService.jwt()).map((response: Response) => response.json());  
    }
    getHrL2(){
        return this.http.get(this.baseUrl + 'seed/user-groups?isHrL2=Y', this.commonService.jwt()).map((response: Response) => response.json());  

    }
    getAcc1(){
        return this.http.get(this.baseUrl + 'seed/user-groups?isAcctL1=Y', this.commonService.jwt()).map((response: Response) => response.json());    
    }
    getAcc2(){
        return this.http.get(this.baseUrl + 'seed/user-groups?isAcctL2=Y', this.commonService.jwt()).map((response: Response) => response.json());    
    }
    getDir(){
        return this.http.get(this.baseUrl + 'seed/user-groups', this.commonService.jwt()).map((response: Response) => response.json());    
    }
    getOffices(){
        return this.http.get(this.baseUrl + 'master/offices', this.commonService.jwt()).map((response: Response) => response.json());    
    }

    checkIn(doc:any){
		return this.http.post(environment.documentApiUrl+'dm/checkin', doc, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    	
    }
    
    printEmpProfile(id:any){
        return this.http.get(environment.documentApiUrl+'document/profile/employees/'+id+'/generate-emp-profile-pdf',this.commonService.jwt()).map(res => res);
    }

    getDocument(docId){
        if(docId){
            let currentUser = JSON.parse(localStorage.getItem('currentUser'));
            let headers = new Headers({'authorization': currentUser.appToken});	
            let options = {responseType: ResponseContentType.Blob ,headers:headers};
            let url =environment.documentApiUrl+'dm/'+docId;
            return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
        }
        else{
            console.log("docId is empty");
            return Observable.empty();
        }
    }
    getDocument1(aadharDocId){
        if(aadharDocId){ 
            let currentUser = JSON.parse(localStorage.getItem('currentUser'));
            let headers = new Headers({'authorization': currentUser.appToken});	
            let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
            let url =environment.documentApiUrl+'dm/'+aadharDocId;
            return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
        }
        else{
            console.log("aadharDocId is empty");
            return Observable.empty();
        }
    }
    getDocument2(docId){
        if(docId){
            let currentUser = JSON.parse(localStorage.getItem('currentUser'));
            let headers = new Headers({'authorization': currentUser.appToken});	
            let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
            let url =environment.documentApiUrl+'dm/'+docId;
            return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
        }
        else{
            console.log("docId is empty");
            return Observable.empty();
        }
    }
    getDocument3(panDocId){
        if(panDocId){
            let currentUser = JSON.parse(localStorage.getItem('currentUser'));
            let headers = new Headers({'authorization': currentUser.appToken});	
            let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
            let url =environment.documentApiUrl+'dm/'+panDocId;
            return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
        }
        else{
            console.log("panDocId is empty");
            return Observable.empty();
        }
    }

    deleteEmployee( id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'master/employees/'+id,this.commonService.jwt()).map((response) => response);
    }

    getSkill() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=SKILL-TYPE-CODE', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getBankInfo(){
        return this.http.get(this.baseUrl+'seed/bankinfo',this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getBlood() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=BLOOD-GROUP', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
}
