import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';
import { Metric } from './metrics/vo/metric';

@Injectable()
export class AppraisalService {

    constructor(private http: Http, private commonService: CommonService) {
    }
    getAll(){
        return this.http.get(environment.serviceApiUrl+'master/metrics',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getCategories(){
        return this.http.get(environment.serviceApiUrl+'seed/metric-categrories/',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getUnits(){
        return this.http.get(environment.serviceApiUrl+'seed/units/',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getById(id){
        return this.http.get(environment.serviceApiUrl+'master/metrics/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    add(metric:any){
        return this.http.post(environment.serviceApiUrl+'master/metrics/',metric,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    update(metric:any){
        return this.http.patch(environment.serviceApiUrl+'master/metrics/products/'+1,metric,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    delete(id){
        return this.http.delete(environment.serviceApiUrl + 'master/metrics/products/'+ id, this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }

    getOperator() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=OPERATOR', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}