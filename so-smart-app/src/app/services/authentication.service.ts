import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/of';
import { Observable, of } from 'rxjs'
import { HTTP } from '@ionic-native/http/ngx';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  baseUrl: string = environment.baseUrl;
  xapikey: string = environment.xapikey;
  authUrl: string = environment.authUrl;
  serviceUrl: string = environment.serviceUrl;
  applicationCode = "smart-office-mobile";

  constructor(private http: HttpClient, private ihttp: HTTP) {

  }

  private jwt() {
    let httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Cache-Control': 'no-cache'
    });
    let options = {
      headers: httpHeaders
    };
    return options;
  }

  loginUser(email: string, password: string, applicationCode: string) {
    let obj = JSON.stringify({ userName: email, password: password, appClientId: this.xapikey, applicationCode: applicationCode });
    return this.http.post(this.baseUrl + this.authUrl + 'tokens/login', obj, this.jwt())
      .do(user => {
        localStorage.setItem('currentUser', JSON.stringify(user)); 
      });
  }

  recover(email) {
    return this.http.patch(this.baseUrl + this.serviceUrl + 'master/emp-profiles/emp-forgot-username-pwd/_internal?emailId=' + email, {}, this.jwt());
  }

  validate() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser) {
      var currToken = this.getCurrentToken(currentUser);
      let obj = JSON.stringify({ userName: currentUser.userName, appToken: currToken, appClientId: this.xapikey, applicationCode: this.applicationCode });
      return this.http.post(this.baseUrl + 'tokens/validate', obj, this.jwt()).map((response: Response) => {
        let result = response.json();
        if (result) return true
        else return false;
      });
    } else return Observable.of(false);
  }

  getData() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log("currentUser-" + currentUser)
    return Observable.of(currentUser);
  }

  logout() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    localStorage.clear();
    return Observable.of(null);
  }

  getCurrentToken(currentUser): string {
    if (currentUser && currentUser.appToken) 
      return currentUser.appToken;
    else
        return '';
  }
}
