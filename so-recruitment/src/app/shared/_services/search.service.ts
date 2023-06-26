import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';

@Injectable()
export class SearchService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http) {
        this.baseUrl = environment.apiUrl;
    }

    getSearchData() {
        let url = 'assets/data/search-data.json';
        return this.http.get(url).map(res => res.json());
    }
	
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.appToken });
        return new RequestOptions({ headers: headers });
    }
}
