import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'


@Injectable()
export class ShiftChangeService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http,
        private commonService: CommonService) {
        this.baseUrl = environment.apiUrl;
    }

    getShiftChangeById(id) {  
        return this.http.get(environment.serviceApiUrl + 'transaction/shift-change/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    getShiftChangeByEmployee() {  
        return this.http.get(environment.serviceApiUrl + 'transaction/shift-change/employee-view', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }   
    getShiftChangeByManager() {  
        return this.http.get(environment.serviceApiUrl + 'transaction/shift-change/manager-view', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    getShiftChangeByHrManager() {  
        return this.http.get(environment.serviceApiUrl + 'transaction/shift-change/hr1-shift-view', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    createShiftChange(action,shiftChange){
        return this.http.post(environment.serviceApiUrl + 'transaction/shift-change/'+action,shiftChange, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());        
    }
    deleteShiftChange(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/shift-change/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }

    getByEmployeeId(id){
        return this.http.get(environment.serviceApiUrl + 'master/employees/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    getShift(){
        return this.http.get(environment.serviceApiUrl + 'master/shift', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
 
}
