import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';

@Injectable()
export class DashboardService {

    serviceApiUrl: string = environment.serviceApiUrl;
    reportApiUrl: string = environment.reportApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http, public commonService: CommonService) {
    }

    getMetrics() {
        return this.http.get(this.serviceApiUrl + "transaction/dash-metrics/latest", this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getForecasts() {
        return this.http.get(this.reportApiUrl + "reports/forecasts", this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getRevenuePerCustomer(){
        return this.http.get(this.reportApiUrl + "reports/revenue-per-customer", this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getRevenuePerBu(){
        return this.http.get(this.reportApiUrl + "reports/revenue-per-bu", this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getDashBordAttendance(isManager){
        return this.http.get(this.reportApiUrl + "reports/attendance/dashboard/attendance?isManager="+isManager, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}
