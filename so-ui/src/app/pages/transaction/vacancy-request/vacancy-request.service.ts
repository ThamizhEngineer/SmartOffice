import { Injectable } from '@angular/core';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { VacancyDescription } from '../../vo/vacancy-description';
import { VacancyRequest } from '../../vo/vacancy-request';
import { Observable } from 'rxjs/Rx'
@Injectable()
export class VacancyRequestService {

    constructor(private http: Http,private commonService:CommonService) { }
   
    getVacancyrequests(){
        return this.http.get(environment.serviceApiUrl + 'transaction/vacancy-requests', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getVacancyrequestById(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/vacancy-requests/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    addVacancyrequests(vacancyRequest){
        return this.http.post(environment.serviceApiUrl + 'transaction/vacancy-requests/',vacancyRequest, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    
    updateVacancyrequests(id,action:string,vacancyRequest:VacancyRequest){
        return this.http.patch(environment.serviceApiUrl + 'transaction/vacancy-requests/'+id+'/'+action,vacancyRequest, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getVacancyDescriptions() {
        return this.http.get(environment.serviceApiUrl + 'master/vacancy-descriptions/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getVacancyDescriptionById(id) {
        return this.http.get(environment.serviceApiUrl + 'master/vacancy-descriptions/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateVacancyDescription(id:any,vacancy:VacancyDescription){
        return this.http.patch(environment.serviceApiUrl + 'master/vacancy-descriptions/'+id+'/update',vacancy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}
