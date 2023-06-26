import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx'
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

import { ClientPurchaseOrder } from '../../job/vo/ClientPurchaseOrder';
@Injectable()
export class ClientPurchaseOrderService {
    constructor(private http: Http,private commonService: CommonService) {
    }
    getClientPO(){
        return this.http.get(environment.serviceApiUrl+'transaction/client-pos/',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getClients(){
        return this.http.get(environment.serviceApiUrl+'master/clients',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());        
    }
    getClientPOs(clientId:any){
        return this.http.get(environment.serviceApiUrl+'transaction/client-pos?clientId='+clientId,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getClientPoById(id?: any){
        return this.http.get(environment.serviceApiUrl+'transaction/client-pos/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createorUpdateClientPurchaseOrders(clientPurchaseOrder){
        return this.http.post(environment.serviceApiUrl+'transaction/client-pos/',clientPurchaseOrder, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deletePurchaseOrder( id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'transaction/client-pos/'+id,this.commonService.jwt()).map((response) => response);
    }
    getDocument(docId){
        let url =environment.documentApiUrl+'dm/'+docId;
          return this.http.get(url, this.commonService.jwtDownloadResArrayBuffer()).catch((err: Response) => Observable.throw(err.json()));
    }
}