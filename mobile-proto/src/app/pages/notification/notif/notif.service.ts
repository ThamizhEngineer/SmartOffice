import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';

@Injectable()
export class NotifService {
	baseUrl: string = environment.baseUrl + environment.notfnApiUrl;

    constructor(private http: HttpClient) {
    }
    private jwt() {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		const httpHeaders = new HttpHeaders ({
			 'Content-Type': 'application/json',
			 'Authorization': currentUser.appToken
		   });
		return { headers: httpHeaders };
    }
	
	getNofifData() {
		return this.http.get(this.baseUrl + 'notfn/inapp-notfn/', this.jwt());
    }
	
}