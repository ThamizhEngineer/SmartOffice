import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class EmployeeLocationService {
    constructor(private http: Http, private commonService: CommonService) { }

    getAllLocationByEmpId(type,name){
        return this.http.get(environment.authUrl +'locations/emp-location?'+type+'='+name, this.commonService.jwt()).map((res: Response) => res.json());
    }

    getAllSearchValue(){
        return this.http.get(environment.authUrl +'locations/loc-search', this.commonService.jwt()).map((res: Response) => res.json());
    }
    
}