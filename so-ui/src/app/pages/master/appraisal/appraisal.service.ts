import { Injectable } from '@angular/core';
import {Employee,AcademicAcheiv,BankDetails,Department,PreviousEmploymentDetails,EducationalInfo,EmergencyContDetails,FamilyInfo,LanguagesKnown} from '../../vo/employee';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class AppraisalService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	
    constructor(private http: Http, private commonService: CommonService) {

    }

    getAllUnits() {
        return this.http.get(environment.serviceApiUrl+'seed/units',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getunitsByCode(code){
        return this.http.get(environment.serviceApiUrl+'seed/units/'+code, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createUnits(unit){
        return this.http.post(environment.serviceApiUrl+'seed/units/',unit, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());        
    }
    updateUnits(code,unit){
        return this.http.patch(environment.serviceApiUrl+'seed/units/'+code,unit, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteUnits(code){
        return this.http.delete(environment.serviceApiUrl+'seed/units/'+code, this.commonService.jwt()).map((response: Response) => response);
    }


    //<-------------------metric-categrories------------------->
    
    
    getAllMetric() {
        return this.http.get(environment.serviceApiUrl+'seed/metric-categrories',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getMetricById(id){
        return this.http.get(environment.serviceApiUrl+'seed/metric-categrories/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createMetric(metric){
        return this.http.post(environment.serviceApiUrl+'seed/metric-categrories/',metric, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());        
    }
    updateMetric(id,metric){
        return this.http.patch(environment.serviceApiUrl+'seed/metric-categrories/'+id+'/update',metric, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteMetric(id){
        return this.http.delete(environment.serviceApiUrl+'seed/metric-categrories/'+id+'/delete', this.commonService.jwt()).map((response: Response) => response);
    }
	
}
