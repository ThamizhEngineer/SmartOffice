import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { environment } from '../../../environments/environment';

@Injectable()
export class CalendarService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http) {
        this.baseUrl = environment.apiUrl;
    }

    getData() {
        let url = 'assets/data/calendar.json';
        return this.http.get(url).map(res => res.json());
    }
	
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }
}
