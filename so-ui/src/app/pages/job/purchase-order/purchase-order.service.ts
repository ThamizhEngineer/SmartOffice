import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class PurchaseOrderService {

    baseUrl: string;
    cachedData: any;
    dummy?:any;
    constructor(private http: Http, private commonService: CommonService) {
        this.baseUrl = environment.apiUrl;
    }

    getAllPurchaseOrders(){
        return this.http.get(environment.serviceApiUrl + 'transaction/purchase-orders', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getDocument(docId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/'+docId;
          return this.http.get(url, options).catch((err: Response) => Observable.throw(err.json()));
    }

    getPurchaseOrderById(id:any){
        return this.http.get(environment.serviceApiUrl + 'transaction/purchase-orders/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
   
    addPurchaseOrder(po:any){
        return this.http.post(environment.serviceApiUrl + 'transaction/purchase-orders',po, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updatePurchaseOrder(po:any){
        return this.http.patch(environment.serviceApiUrl + 'transaction/purchase-orders/'+po.id,po, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updatePurchaseOrderLine(po:any){
        return this.http.patch(environment.serviceApiUrl + 'transaction/purchase-orders/'+po.id+'/lines',po, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    addPurchaseOrderPayment(po:any){
        return this.http.patch(environment.serviceApiUrl + 'transaction/purchase-orders/'+po.id+'/add-payment',po, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    receivedProduct(po:any){
        return this.http.patch(environment.serviceApiUrl + 'transaction/purchase-orders/'+po.id+'/received-product',po, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    returnPurchase(po:any){
        return this.http.patch(environment.serviceApiUrl + 'transaction/purchase-orders/'+po.id+'/return-purchase',po, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    sendEmail(po:any){
        return this.http.patch(environment.serviceApiUrl + 'transaction/purchase-orders/'+po.id+'/send-email',po, this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }

    generatePdf(po:any){
        return this.http.get(environment.documentApiUrl + 'document/purchase-orders/'+po.id+'/generate-pdf', this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }

    deletePurchaseOrder(po:any){
        return this.http.delete(environment.serviceApiUrl + 'transaction/purchase-orders/'+po.id, this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }
}
