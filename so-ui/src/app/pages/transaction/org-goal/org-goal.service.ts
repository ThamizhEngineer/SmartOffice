import { Injectable } from '@angular/core';
import {Employee,AcademicAcheiv,BankDetails,Department,PreviousEmploymentDetails,EducationalInfo,EmergencyContDetails,FamilyInfo,LanguagesKnown} from '../../vo/employee';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class AppraisalService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	
    constructor(private http: Http, private commonService: CommonService) {

    }

    getAllMetrics(){
        return this.http.get(environment.serviceApiUrl+'master/metrics',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getFiscalYear(){
        return this.http.get(environment.serviceApiUrl+'seed/fiscal-years',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getEmployees() {
        return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getAllOrgGoal(){
        return this.http.get(environment.serviceApiUrl+'transaction/org-services/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
    getOrgGoalByEmpId(yearId){
     let url = environment.serviceApiUrl+'transaction/org-services/emp-by-fiscal-year?fyYearId='+yearId;
        return this.http.get(url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
    createOrgGoal(orgGoal){
        return this.http.post(environment.serviceApiUrl+'transaction/org-services/',orgGoal, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
    updateOrgGoal(id,orgGoal){
        return this.http.patch(environment.serviceApiUrl+'transaction/org-services/'+id+'/add-update-lines',orgGoal, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
    deleteOrgGoalLine(id){
        return this.http.delete(environment.serviceApiUrl+'transaction/org-services/'+id+'/delete-line', this.commonService.jwt()).map((response: Response) => response);    
    }
    deleteOrgGoalheader(id){
        return this.http.delete(environment.serviceApiUrl+'transaction/org-services/'+id+'/delete-header', this.commonService.jwt()).map((response: Response) => response);    
    }
    exportOrgGoal(yearId){

        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.serviceApiUrl+'transaction/appraisal/export/export-result/'+yearId+'.xls';
        return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));                
    }

    getOperator() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=OPERATOR', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}