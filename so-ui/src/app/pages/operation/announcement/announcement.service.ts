import { Injectable } from '@angular/core';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { Announcement, Office } from './vo/Announcement';

@Injectable()
export class AnnouncementService {

    constructor(private http: Http,private commonService:CommonService) { }

    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        currentUser.appToken
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }

    getOffices(){
        return this.http.get(environment.serviceApiUrl + 'master/offices', this.commonService.jwt()).map((response: Response) => response.json());    
    }
    sendAnnouncemnt(announcemnt:Announcement){
        return this.http.post(environment.serviceApiUrl + 'transaction/announcements/',announcemnt,this.commonService.jwt()).map((response: Response) => response.json());    
    }

}
