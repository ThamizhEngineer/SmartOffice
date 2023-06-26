import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class PartnerService {

    constructor(private http: Http,private commonService: CommonService) {
    }
    getPartnerService(){
        return this.http.get(environment.serviceApiUrl+'master/clients',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getPartnerServiceById(id?: any){
        return this.http.get(environment.serviceApiUrl+'master/clients/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    
}
