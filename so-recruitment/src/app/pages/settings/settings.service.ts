import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';

@Injectable()
export class SettingsService {

    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http) {
    }

    private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'Access-Control-Allow-Methods':'*' });
        return new RequestOptions({ headers: headers });
    }

    getUser(){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) return currentUser;
	}
	
	updatePassword(id, obj){
		return this.http.patch(this.baseUrl+"users/" + id + "/change-password", obj, this.jwt()).map((response: Response) => response.json());
	}
}
