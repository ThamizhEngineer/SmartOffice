import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Contractor } from '../../vo/contractor';
import { Observable } from 'rxjs/Rx'
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class ContractorService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	
	private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
		return new RequestOptions({ headers: headers });
    }

    constructor(private http: Http,private commonService: CommonService)  {

    }

    getContractorById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'master/contractors/'+_id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getContractors() {
        return this.http.get(environment.serviceApiUrl+'master/contractors/',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    // getConfig(){
    //     return this.http.get(environment.serviceApiUrl+'seed/configs?configHeaderCode=ATTENDANCE-CODES').map(res => res.json());
    // }
	
    addContractor(contractor:Contractor ) {
      
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        // let options = new RequestOptions({ headers: headers });
        // let body = JSON.stringify(employee);
        // let url = environment.serviceApiUrl+'master/contractors/';
        // return this.http.post(url, body, options).map((res: Response) => res.json());
        return this.http.post(environment.serviceApiUrl+'master/contractors/', contractor, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateContractor(contractor: Contractor) {
        // var key = employee.id;
      
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        // let options = new RequestOptions({ headers: headers });
        // let body = JSON.stringify(employee);
        // let url = environment.serviceApiUrl+'master/contractors/' + key;

        // return this.http.patch(url, body, options).map((res: Response) => res.json());
        return this.http.post(environment.serviceApiUrl+'master/contractors/', contractor, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
          
	getGrades(){
		return this.http.get(this.baseUrl + 'seed/grades', this.jwt()).map((response: Response) => response.json());
	}
	
	getDesignations(){
		return this.http.get(this.baseUrl + 'seed/designations', this.jwt()).map((response: Response) => response.json());
	}
    
    getOffices(){
        return this.http.get(this.baseUrl + 'master/offices', this.commonService.jwt()).map((response: Response) => response.json());    
    }

    deleteContractor( id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'master/contractors/'+id,this.commonService.jwt()).map((response) => response);
    }

    getEmployees() {
        return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getSkill() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=SKILL-TYPE-CODE', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getDocument(docId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/images/'+docId;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
    }
}
