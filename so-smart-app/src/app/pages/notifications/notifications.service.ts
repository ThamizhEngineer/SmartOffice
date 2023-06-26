import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { HTTP } from '@ionic-native/http/ngx';
import { UtilsService } from 'src/app/services/utils.service';

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {
	baseUrl: string = environment.baseUrl + environment.notfnApiUrl;	
	
  constructor(private http: HttpClient,private ihttp: HTTP,private _utils:UtilsService) { 
  }

  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this._utils.getCurrentToken(currentUser);
		const httpHeaders = new HttpHeaders ({
			 'Content-Type': 'application/json',
			 'Authorization': currentToken
		   });
		return { headers: httpHeaders };
    }

	getNofifData() {
		return this.http.get(this.baseUrl + 'notfn/inapp-notfn/',this.jwt())
	}  
}
