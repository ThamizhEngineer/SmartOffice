import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';

@Injectable()
export class SaleOrderService {

    constructor(private http:Http,private commonService: CommonService){
    }
    saleOrderWithJob(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/sale-orders/find-job/'+id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    
    getSaleOrder(id?: any) {
        return this.http.get(environment.serviceApiUrl+'transaction/sale-orders/'+ id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    
    getJobStatusByJobId(id?: any) {
        return this.http.get(environment.serviceApiUrl + 'report/job-statuses/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
   
}
