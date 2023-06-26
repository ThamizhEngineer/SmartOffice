import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import "rxjs/add/operator/map";
import { environment } from './../../../../environments/environment';
import { Observable } from 'rxjs/Rx'
import { Expense } from "../../vo/expense";
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class ExpenseApprovalService {

    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: Http,private commonService: CommonService) {
    }

    private jwt(paramArr?: any) {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json','authorization': currentUser.appToken});
		if(paramArr){
			let myParams = new URLSearchParams();
			paramArr.forEach( x => myParams.append(x.key, x.value) );
			return new RequestOptions({ headers: headers, params: myParams });
		}
        else return new RequestOptions({ headers: headers });
	}
	getDocument(docId){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/images/'+docId;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err));  
	}
	getCurrentUser() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.appToken) return currentUser;
        return null;
    }

	

	getById(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims/'+id, this.commonService.jwt()).map((response: Response) => response.json());
	}

	getAll(){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims/expense-claims-for-my-validation', this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	UpdateExpenseReq(id:any,action:any,expense:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/expense-claims/'+id+'/'+action, expense, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
}