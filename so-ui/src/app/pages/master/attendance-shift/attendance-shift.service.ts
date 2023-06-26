import { Injectable } from '@angular/core';
import {Employee,AcademicAcheiv,BankDetails,Department,PreviousEmploymentDetails,EducationalInfo,EmergencyContDetails,FamilyInfo,LanguagesKnown} from '../../vo/employee';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class AttendanceShiftService {
	
    constructor(private http: Http, private commonService: CommonService) {

    }

    getAllShift() {
        return this.http.get(environment.serviceApiUrl+'master/shift',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getShiftById(id){
        return this.http.get(environment.serviceApiUrl+'master/shift/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createShift(shift){
        return this.http.post(environment.serviceApiUrl+'master/shift/',shift, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());        
    }
    updateShift(shift){
        return this.http.patch(environment.serviceApiUrl+'master/shift/update',shift, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteShift(id){
        return this.http.delete(environment.serviceApiUrl+'master/shift/'+id, this.commonService.jwt()).map((response: Response) => response);
    }


  
	
}
