import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import "rxjs/add/operator/map";
import { Observable } from 'rxjs/Rx'
import { environment } from '../environments/environment';

@Injectable()
export class AuthenticationService {

    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http) {
    }

    private jwt(paramArr?: any) {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
        if(paramArr){
			let myParams = new URLSearchParams();
			paramArr.forEach( x => myParams.append(x.key, x.value) );
			return new RequestOptions({ headers: headers, params: myParams });
		}
        else 
        return new RequestOptions({ headers: headers });
    }

    login(email: string, password: string) {
        let obj = JSON.stringify({ userName: email, password: password, appClientId: this.clientId });
        /*return this.http.post(this.baseUrl + 'tokens/login', obj, this.jwt())
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let user = response.json();
                if (user && user.appToken) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    localStorage.setItem('username', user.userName);
                    localStorage.setItem('isLoggedIn', "yes");
                }
            });*/
            return this.http.get('assets/user.json', this.jwt())
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let user = response.json();
                if (user && user.appToken) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    localStorage.setItem('username', user.userName);
                    localStorage.setItem('isLoggedIn', "yes");
                }
            });
    }
	
	validate() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		
		if (currentUser) {
			let obj = JSON.stringify({ userName: currentUser.userName, appToken: currentUser.appToken, appClientId: this.clientId });
			return this.http.post(this.baseUrl + 'tokens/validate', obj, this.jwt()).map((response: Response) => response.json());
		}else return Observable.of(null);     
    }
	
	refresh() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		
		if (currentUser) {
			let obj = JSON.stringify({ userName: currentUser.userName, appToken: currentUser.appToken, appClientId: this.clientId });
			return this.http.post(this.baseUrl + 'tokens/refresh', obj, this.jwt()).map((response: Response) => response.json());
		}else return Observable.of(null);     
    }

    logout() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) {
			let obj = JSON.stringify({ userName: currentUser.userName, appToken: currentUser.appToken, appClientId: this.clientId });
			return this.http.post(this.baseUrl + 'tokens/logout', obj, this.jwt()).map(res=>{});
		}
		else return Observable.of(null);
    }
}