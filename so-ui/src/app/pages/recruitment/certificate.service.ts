import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import{CommonService} from '../../shared/common/common.service';
import { environment } from '../../../environments/environment';

@Injectable()
export class CertificateService {

    constructor(
        private http:Http,
        private commonService:CommonService
    ) { }


getAllCertificates(){
    return this.http.get(environment.serviceApiUrl + 'transaction/certificate-trackers', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
getCertificateById(id){
    return this.http.get(environment.serviceApiUrl + 'transaction/certificate-trackers/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}

}