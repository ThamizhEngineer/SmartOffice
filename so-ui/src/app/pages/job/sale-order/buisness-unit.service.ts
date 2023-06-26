import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

import { BuisnessUnit } from './../vo/buisness-unit';


@Injectable()
export class BuisnessUnintService {
    constructor(private http: Http,private commonService: CommonService) {
    }
    getAllBuisnessUnit(){
        return this.http.get(environment.serviceApiUrl+'master/business-units', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
}