import { Injectable } from '@angular/core';
import{CommonService} from '../../shared/common/common.service';
import { environment } from '../../../environments/environment';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class ExpenseService {

    constructor(private http: Http,private commonService:CommonService) { }

    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        currentUser.appToken
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }

    getExpenseById(id:string){
    
        return this.http.get(environment.serviceApiUrl + 'transaction/expense-claims/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    getAllN1List(){
        return this.http.get(environment.serviceApiUrl + 'transaction/expense-claims/expense-claims-for-my-approval', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    getAllAcc2List(){
        return this.http.get(environment.serviceApiUrl + 'transaction/expense-claims/expense-claims-for-my-settlement', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

   
    UpdateExpenseReq(id:any,action:any,expense:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/expense-claims/'+id+'/'+action, expense, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }


    getDocument(docId){
		// let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        // let headers = new Headers({'authorization': currentUser.appToken});	
        // let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        // let url =environment.documentApiUrl+'dm/'+docId;
        // return this.http.get(url, options).catch((err: Response) => Observable.throw(err));  

        return this.http.get(environment.documentApiUrl+'dm/'+docId, this.commonService.jwt([{"responseType" : ResponseContentType.ArrayBuffer}]));    
	}

}
