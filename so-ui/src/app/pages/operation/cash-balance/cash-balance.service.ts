import { CommonService } from './../../../shared/common/common.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import {CashBalance} from '../../vo/cash-balance';
import { environment } from '../../../../environments/environment';

import 'rxjs/add/operator/map';

@Injectable()
export class CashBalanceService {

constructor(private http: Http,private commonService:CommonService) {}

private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    currentUser.appToken
    let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
    return new RequestOptions({ headers: headers });
}

getcashbalance(){
return this.http.get(environment.serviceApiUrl+'transactions/emp-cash-balance', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}

deletecashbalance(id?: any){
return this.http.delete(environment.serviceApiUrl+'transactions/emp-cash-balance/'+id,this.commonService.jwt()).map((response) => response);
}
updatecashbalance(id:any,body) {
return this.http.patch(environment.serviceApiUrl+'transactions/emp-cash-balance/'+id, body,this.commonService.jwt()).map(res => res.json() );
}

getcashbalanceById(id?:any) {
return this.http.get(environment.serviceApiUrl+'transactions/emp-cash-balance/' +id,this.commonService.jwt()).map(res => res.json());
}
addcashbalance(body?:any) {
// let body = JSON.stringify(cashbalance);
return this.http.post(environment.serviceApiUrl+'transactions/emp-cash-balance', body,this.commonService.jwt()).map(res => res.json());
}
getEmployees() {
    return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}

}