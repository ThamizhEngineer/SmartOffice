import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { VacancyDescription } from '../../vo/vacancy-description';

@Injectable()
export class VacancyDecriptionService {

    baseUrl: string;
    cachedData: any;
    constructor(private http: Http, private commonService: CommonService) {
    }
    
    getVacancyDescriptions() {
        return this.http.get(environment.serviceApiUrl + 'master/vacancy-descriptions', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getVacancyDescriptionById(id) {
        return this.http.get(environment.serviceApiUrl + 'master/vacancy-descriptions/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    addVacancyDescription(vacancy:VacancyDescription){
        return this.http.post(environment.serviceApiUrl + 'master/vacancy-descriptions/',vacancy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateVacancyDescription(id:any,vacancy:VacancyDescription){
        return this.http.patch(environment.serviceApiUrl + 'master/vacancy-descriptions/'+id+'/update',vacancy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}
