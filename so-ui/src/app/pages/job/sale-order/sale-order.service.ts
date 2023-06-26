import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

import { SaleOrder } from './../vo/sale-order';

@Injectable()
export class SaleOrderService {

    constructor(private http: Http,private commonService: CommonService) {
    }

    getSaleOrders() {
        return this.http.get(environment.serviceApiUrl+'transaction/sale-orders' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getJobStatusByJobId(id?: any) {
        return this.http.get(environment.serviceApiUrl + 'report/job-statuses/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getSaleOrder(id?: any) {
        return this.http.get(environment.serviceApiUrl+'transaction/sale-orders/'+ id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    createSaleOrder(so: SaleOrder) {
        console.log(so);
        return this.http.post(environment.serviceApiUrl+'transaction/sale-orders/', so, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateSaleOrder(so: SaleOrder) {
        return this.http.patch(environment.serviceApiUrl+'transaction/sale-orders/'+so.id, so, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    submitForApproval(so: SaleOrder){
        return this.http.patch(environment.serviceApiUrl+'transaction/sale-orders/'+so.id+'/submit-for-approval',so,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    saveForApproval(so: SaleOrder,dummy?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/sale-orders/'+so.id+'/approved', dummy,   this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    deleteSaleOrder(id){
		return this.http.delete(environment.serviceApiUrl + 'transaction/sale-orders/'+id, this.commonService.jwt()).map((response) => response);
    }

    deleteSaleGoods(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/sale-orders/'+id+'/sale-goods', this.commonService.jwt()).map((response) => response);
    }
    deleteSaleServices(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/sale-orders/'+id+'/sale-services', this.commonService.jwt()).map((response) => response); 
    }
    
    sendSoAck(so: SaleOrder){
        return this.http.patch(environment.serviceApiUrl+'transaction/sale-orders/'+so.id+'/order-acknowledgment-processor', so, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getMaterialMasters(){
        return this.http.get(environment.serviceApiUrl+'master/materials?materialCategory=goods' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getProjects() {
        return this.http.get(environment.serviceApiUrl+'transaction/projects' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getMapLocation() {
        return this.http.get(environment.serviceApiUrl + 'map-locations/all' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    saleOrderWithJob(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/sale-orders/find-job/'+id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}
