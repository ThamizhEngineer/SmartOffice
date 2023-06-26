import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import "rxjs/add/operator/map";
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';
@Injectable()
export class AuthenticationService {

    baseUrl: string = environment.authUrl;
    serviceUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
    healthUrl: string = environment.healthCheckUrl;

    constructor(private http: Http) {
        // this.http.get('https://api.ipify.org?format=json').subscribe(data => localStorage.setItem('clientIp', data.json().ip));
    }

    private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
        return new RequestOptions({ headers: headers });
    }

    login(email: string, password: string, applicationCode: string) {
        let obj = JSON.stringify({ userName: email, password: password, appClientId: this.clientId, applicationCode: applicationCode });
        return this.http.post(this.baseUrl + 'tokens/login', obj, this.jwt())
            .map((response: Response) => {
                let user = response.json();
                if (user.acceptedAgmt == 'Y' && user && user.appToken) {
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
            let obj = JSON.stringify({ userName: currentUser.userName, appToken: currToken, appClientId: this.clientId, applicationCode: currentUser.applicationCode });
            return this.http.post(this.baseUrl + 'tokens/validate', obj, this.jwt()).map((response: Response) => {
                let result = response.json();
                if (result) {
                    return this.checkTokenValidity(result);
                } else return false;
            });
        } else return Observable.of(false);
    }

    getCurrentToken(currentUser): string {
        if (currentUser && currentUser.appToken) 
            return currentUser.appToken;
        else
            return '';
    }

    checkTokenValidity(result): boolean {
        var isTokenValid: boolean = false
        if (result.applicationCode == 'smart-office') {
            console.log(result);

            // var res = result.authToken.filter(x => x.applicationCode === result.applicationCode);
            var res = result.authTokens.filter(x => x.applicationCode === result.applicationCode);
            console.log(res);
            if (res) {
                let d = new Date(res[0].tokenValidityDt);
                let d1 = new Date();
                if (d.valueOf() - d1.valueOf() < 0) {
                    isTokenValid = false;
                } else {
                    isTokenValid = true;
                }
            }
        } else {
            isTokenValid = false;
        }
        return isTokenValid;
    }

    refresh() {
        console.log('refresh Called')
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) {
            let obj = JSON.stringify({ userName: currentUser.userName, appToken: currentUser.appToken, appClientId: this.clientId, applicationCode: 'smart-office' });
            return this.http.post(this.baseUrl + 'tokens/refresh', obj, this.jwt()).map((response: Response) => response.json());
        } else return Observable.of(null);
    }

    logout() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) {
            var currToken = this.getCurrentToken(currentUser);
            let obj = JSON.stringify({ userName: currentUser.userName, appToken: currToken, appClientId: this.clientId, applicationCode: 'smart-office' });
            return this.http.post(this.baseUrl + 'tokens/logout', obj, this.jwt()).map(res => { });
        }
        else return Observable.of(null);
    }

    recover(email: string) {
        let obj = { userName: email };
        return this.http.patch(this.baseUrl + 'tokens/reset-password', obj, this.jwt()).map((response: Response) => response.json());
    }

    acceptTerms(user: any) {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'authorization': user.appToken });
        let jwt = new RequestOptions({ headers: headers });

        return this.http.patch(this.baseUrl + 'auth/users/accept-terms', {}, jwt).map((response: Response) => response.json());
    }
    forgotPwd(email: any) {
        return this.http.patch(this.serviceUrl + 'master/emp-profiles/emp-forgot-username-pwd/_internal?emailId=' + email, {}).map((response: Response) => response.json());
    }

    healthCheckService(){
        return this.http.get(environment.healthCheckServiceUrl).map((response: Response) => response).map(res => res.json());
    }

}