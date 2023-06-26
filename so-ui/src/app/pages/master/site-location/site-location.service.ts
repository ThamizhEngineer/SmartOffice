import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class siteLocationService {
    constructor(
        private http: Http,
        private commonService: CommonService
    ) { }

    getAllSites(){
        return this.http.get(environment.serviceApiUrl + 'master/site-location', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    searchBySites(siteName,country,endClientName,contactName,mobileNumber){
        let Url = 'master/site-location?siteName='+siteName+'&country='+country+'&endClientName='+endClientName+'&contactName='+contactName+'&mobileNumber='+mobileNumber
        return this.http.get(environment.serviceApiUrl + Url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getBySiteId(id){
        return this.http.get(environment.serviceApiUrl + 'master/site-location/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    createAndUpdateSite(siteLocation){
        return this.http.post(environment.serviceApiUrl + 'master/site-location/',siteLocation, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    deleteSiteLocation(id){
        return this.http.delete(environment.serviceApiUrl + 'master/site-location/delete/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }

    getAllCountry() {
        return this.http.get(environment.serviceApiUrl + 'seed/countries', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getPartners(){
        return this.http.get(environment.serviceApiUrl+'master/clients',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}