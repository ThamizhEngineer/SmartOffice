import { Injectable } from '@angular/core';

import {Applicant} from '../vo/applicant';
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
	
    addApplicant(applicant: Applicant) {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(applicant);
        let url = environment.serviceApiUrl+'transaction/recruitments/applicants';
        return this.http.post(url, body,this.commonService.jwt()).map(res => res.json());
    }

    updateApplicant(id,applicant: Applicant) {
        var key = applicant.id;

        return this.http.put(environment.serviceApiUrl+'transaction/recruitments/applicants/'+id,applicant,this.commonService.jwt()).map(res => res.json());
    }
          
    deleteApplicant( id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'transaction/recruitments/applicants/'+id+'/delete',this.commonService.jwt()).map((response) => response);
    }
    getDocument(docId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var currToken = this.getCurrentToken(currentUser);
        let headers = new Headers({'authorization': currToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/images/'+docId;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
    }
    getCurrentToken(currentUser): string {
        if (currentUser && currentUser.appToken) 
          return currentUser.appToken;
        else
            return '';
      }
	getApplicantFormPdfById(_id: string) {
        return this.http.get(environment.documentApiUrl+'document/recruitments/applicants/' + _id+'/generate-pdf',this.commonService.jwt()).map(res => res);
    }

    createApplicantLogin(applicant){
        return this.http.post(environment.serviceApiUrl + 'transaction/recruiment-service/new-login',applicant);
    }

    forgotUserNamePwd(emailId){
        let dummy : any;
        var url= 'transaction/recruiment-service/forgot-username-pwd?'
        if(emailId!=null){
            url= url+'emailId='+emailId
        }
        // return this.http.patch(environment.serviceApiUrl + 'transaction/recruiment-service/forgot-username-pwd/'+emailId,dummy);
        return this.http.patch(environment.serviceApiUrl + url,dummy );
    }

    // createApplicantLogin(applicant: Applicant) {
      
    //     let headers = new Headers({ 'Content-Type': 'application/json' });
    //     let options = new RequestOptions({ headers: headers });
    //     let body = JSON.stringify(applicant);
    //     let url = environment.serviceApiUrl+'transaction/recruiment-service/new-login';
    //     return this.http.post(url, body,this.commonService.jwt()).map(res => res.json());
    // }
    
}
