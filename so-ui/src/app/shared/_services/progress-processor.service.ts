import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { CommonService } from '../../shared/common/common.service';
import { environment } from '../../../environments/environment';

@Injectable()
export class ProgressProcessorService {
    constructor(private http: Http, private commonService: CommonService) { }

    postBkProcess(bkProcessBody, bkProcessTypeCode) {
        return this.http.post(environment.serviceApiUrl + 'transaction/bk-process/' + bkProcessTypeCode + '/init?inputReference=', bkProcessBody, 
        this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    checkStatus(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/bk-process/'+id+'/check-status', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }  

}