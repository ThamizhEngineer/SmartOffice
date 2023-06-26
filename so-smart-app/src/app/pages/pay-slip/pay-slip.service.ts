import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PaySlipService {
	baseUrl: string = environment.baseUrl + environment.serviceUrl;	
	
  constructor(private http: HttpClient) { 
  }

  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log(currentUser)
		const httpHeaders = new HttpHeaders ({
			 'Content-Type': 'application/json',
			 'Authorization': currentUser.appToken
		   });
		return { headers: httpHeaders };
    }

	fetchPayouts() {
		return this.http.get(this.baseUrl + 'my-space/my-payouts',this.jwt())
	}  
}
