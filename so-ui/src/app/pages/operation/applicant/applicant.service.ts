import { Injectable } from '@angular/core';

import {Applicant} from '../../vo/applicant';
import{CommonService} from '../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';

@Injectable()
export class ApplicantService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	
	private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
		return new RequestOptions({ headers: headers });
    }

    constructor(private http: Http,private commonService:CommonService) {

    }
    getBlood() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=BLOOD-GROUP', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getApplicantById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'transaction/recruitments/applicants/' + _id,this.commonService.jwt()).map(res => res.json());
    }

    getApplicants() {
        return this.http.get(environment.serviceApiUrl+'transaction/recruitments/applicants',this.commonService.jwt()).map(res => res.json());
    }

    getConfig(){
        return this.http.get(environment.serviceApiUrl+'seed/configs?configHeaderCode=ATTENDANCE-CODES',this.commonService.jwt()).map(res => res.json());
    }
	
    addApplicant(applicant: [Applicant]) {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(applicant);
        let url = environment.serviceApiUrl+'transaction/recruitments/applicants/create-applicant';
        return this.http.post(url, body,this.commonService.jwt()).map(res => res.json());
    }

    updateApplicant(applicant: Applicant) {
        var key = applicant.id;
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
        // let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(applicant);
        let url = environment.serviceApiUrl+'transaction/recruitments/applicants/' + key;

        return this.http.put(url, body,this.commonService.jwt()).map(res => res.json() );
    }
          
    deleteApplicant( id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'transaction/recruitments/applicants/'+id+'/delete',this.commonService.jwt()).map((response) => response);
    }
    getDocument(docId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/images/'+docId;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
    }
    getIncidents(){
        return this.http.get(environment.serviceApiUrl + 'master/academic-colleges', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getColleges(){
        return this.http.get(environment.serviceApiUrl + 'master/academic-colleges', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getDegrees(){
        return this.http.get(environment.serviceApiUrl + 'master/academic-degrees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
    }
    getCourses(){
        return this.http.get(environment.serviceApiUrl + 'master/academic-courses', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
    }
    advanceSearch(contactMobileNo,contactEmailId) {
        var url = 'transaction/recruitments/applicants/advance?' 

        if(contactMobileNo!=null){
            url= url+'contactMobileNo='+contactMobileNo   
        } 
        if(contactEmailId!=null){
            url= url+'&&contactEmailId='+contactEmailId   
        }   
      
        return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
}
