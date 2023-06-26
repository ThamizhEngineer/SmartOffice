import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import { environment } from './../../../../environments/environment';
import { EmployeePayout } from './../vo/employee-payout';
import { Observable } from 'rxjs/Rx'
import { CommonService } from '../../../shared/common/common.service';
import { HttpClient } from '@angular/common/http'

@Injectable()
export class OrgAnalysisService {

    constructor(private http: Http,
        private readonly _httpClient: HttpClient,
        private commonService: CommonService) {         
        }


    
    getFyYears(){
        return this.http.get(environment.serviceApiUrl + 'seed/fiscal-years', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    refresh(fyYearId){
        return this.http.get(environment.serviceApiUrl + 'transaction/appraisals/'+fyYearId+'/refresh', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
    }

    getRefesh(docId): Observable<any>{

        return this.http.get(environment.documentApiUrl + 'dm/'+docId, this.commonService.jwt()).map(res => res.json()); 
    }

    getCurrentUser() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.appToken) return currentUser;
        return null;
    }
    getDocument(docId){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/images/'+docId;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err));  
	}

}