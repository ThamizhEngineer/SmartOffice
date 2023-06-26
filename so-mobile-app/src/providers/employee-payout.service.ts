import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import "rxjs/add/operator/map";

import { environment } from './../environments/environment';
import { CommonService } from '../providers/common.service';

@Injectable()
export class EmployeePayoutService {

    subUrl: string ='transaction/pay/';

    constructor(private http: Http,  private commonService: CommonService) {
    }

    getEmployeePayoutById(_id?: string) {
        return this.http.get(environment.serviceApiUrl + this.subUrl + 'employee-payouts/' + _id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}