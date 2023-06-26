import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { CommonService } from "./common.service";
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/of';
import { Observable } from 'rxjs'

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {

	baseUrl: string = environment.baseUrl;
	xapikey: string = environment.xapikey;
	authUrl: string = environment.authUrl;

	constructor(private http: HttpClient, private commonService: CommonService) { }

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

	loginUser(email: string, password: string) {
		let obj = JSON.stringify({ userName: email, password: password, appClientId: this.xapikey, applicationCode: 'smart-office-mobile' });

		/*return this.http.get('/assets/data/login.json').do(user => {
			localStorage.setItem('currentUser', JSON.stringify(user));
		});;*/
		return this.http.post(this.baseUrl + this.authUrl + 'tokens/login', obj, this.jwt())
			.do(user => {
				localStorage.setItem('currentUser', JSON.stringify(user));
			});

	}

	registerDevice(uuid: string, manufacturer?: string, model?: string) {

	}

	validate() {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) {
			let d = new Date(currentUser.tokenValidityDt);
			let d1 = new Date();
			if (d.valueOf() - d1.valueOf() < 0) {
				return Observable.of(null);
			}

			return Observable.of(currentUser);
		} else return Observable.of(null);
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

}
