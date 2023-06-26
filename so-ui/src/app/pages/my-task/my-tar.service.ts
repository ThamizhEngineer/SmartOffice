import { Injectable } from '@angular/core';
import{CommonService} from '../../shared/common/common.service';
import { environment } from '../../../environments/environment';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
@Injectable()
export class MyTarRequestService {

    constructor(private http: Http,private commonService:CommonService) { }

    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        currentUser.appToken
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }

    getTarById(id:string){
    
        return this.http.get(environment.serviceApiUrl + 'transaction/tars/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    getAllN1List(){
        return this.http.get(environment.serviceApiUrl + 'transaction/tars/n1-approval-pending', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    getAllAcc2List(){
        return this.http.get(environment.serviceApiUrl + 'transaction/tars/acc2-approval-pending', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    CreateMyTarReq(myTarReq:any){
        return this.http.post(environment.serviceApiUrl+'transaction/tars', myTarReq, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
    UpdateMyTarReq(id:any,action:any,myTarReq:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tars/'+id+'/'+action, myTarReq, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
}
