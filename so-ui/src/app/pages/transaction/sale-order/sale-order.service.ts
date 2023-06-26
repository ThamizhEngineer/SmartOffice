import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { environment } from '../../../../environments/environment';
import { SaleOrder } from '../model/sale-order';
@Injectable()
export class SaleOrderService{

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http) {
        this.baseUrl = environment.serviceApiUrl;
    }

    getAllSaleOrders(){
        let url=environment.serviceApiUrl+"sale-order"
        return this.http.get(url).map(res => res.json());
    }

    getAllSaleOrderById(id:number){
        let url=environment.serviceApiUrl+"sale-order/"+id;
        return this.http.get(url).map(res => res.json());
    }

    addSaleOrder(saleOrder:SaleOrder){      
             
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let url = environment.serviceApiUrl+'sale-order';
        let body = JSON.stringify(saleOrder);
        return this.http.post(url, body, options).map((res: Response) => res);
    }

    updateSaleOrder(saleOrder:SaleOrder){      
        var key = saleOrder.id;      
        let headers = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: headers });
        let url = environment.serviceApiUrl+'sale-order/'+ key;
        let body = JSON.stringify(saleOrder);
        return this.http.put(url, body, options).map((res: Response) => res);
    }
}

