import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { InAppNotfn } from './_vo/InAppNotfn';

@Injectable()
export class InAppNotfnService {
    constructor(private http: Http, private commonService: CommonService) {
    }
    getMyInAppNotfn(){
        return this.http.get(environment.notfnApiUrl+'notfn/inapp-notfn/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}