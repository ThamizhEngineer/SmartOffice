import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import "rxjs/add/operator/map";
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';

@Injectable()
export class AuthenticationService {

    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
    healthUrl: string = environment.healthCheckUrl; 
    applicationCode: string = environment.applicationCode

    constructor(private http: Http) {
    }

    private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
        return new RequestOptions({ headers: headers });
    }

    login(email: string, password: string, applicationCode: string) {
        let obj = JSON.stringify({ userName: email, password: password, appClientId: this.clientId, applicationCode: applicationCode });
        return this.http.post(this.baseUrl + 'tokens/login', obj, this.jwt())
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let user = response.json();
				user.pword = password;
                if (user && user.appToken) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
                return user;
            });
    }
	
	validate() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) {
            var currToken = this.getCurrentToken(currentUser);
			let obj = JSON.stringify({ userName: currentUser.userName, appToken: currToken, appClientId: this.clientId,applicationCode: this.applicationCode });
			return this.http.post(this.baseUrl + 'tokens/validate', obj, this.jwt()).map((response: Response) => {
				let result = response.json();
				if(result) return true
				else return false;
			});
		}else return Observable.of(false);     
    }
	
	refresh() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var currToken = this.getCurrentToken(currentUser);
		if (currentUser) {
			let obj = JSON.stringify({ userName: currentUser.userName, appToken: currToken, appClientId: this.clientId, applicationCode: this.applicationCode });
			return this.http.post(this.baseUrl + 'tokens/refresh', obj, this.jwt()).map((response: Response) => response.json());
		}else return Observable.of(null);     
    }

    logout() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) {
            var currToken = this.getCurrentToken(currentUser);
			let obj = JSON.stringify({ userName: currentUser.userName, appToken: currToken, appClientId: this.clientId, applicationCode: this.applicationCode});
			return this.http.post(this.baseUrl + 'tokens/logout', obj, this.jwt()).map(res=>{});
		}
		else return Observable.of(null);
    }
	
	recover(email: string) {
        let obj = { userName: email };
        return this.http.patch(this.baseUrl + 'tokens/reset-password', obj, this.jwt()).map((response: Response) => response.json());
    }

    healthService(){
        // return this.http.get(this.healthUrl,{}).map((response: Response) => response.json());
        return this.http.get(this.healthUrl).map((response: Response) => response).map(res => res.json());

    }
 

    getCurrentToken(currentUser): string {
        if (currentUser && currentUser.appToken) 
          return currentUser.appToken;
        else
            return '';
      }
}