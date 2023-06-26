import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';

@Injectable()
export class TarService {
	
	baseUrl: string = environment.baseUrl + environment.serviceUrl;
	
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
	
	getTarData() {
		return this.http.get(this.baseUrl + 'transaction/tars', this.jwt());
    }
	
	getTarDataById(id: any) {
		return this.http.get(this.baseUrl + 'transaction/tars/'+ id, this.jwt());
    }
	
	getCostCenters(){
		return this.http.get(this.baseUrl + 'master/cost-centers', this.jwt());
	}
	
	getJobCodes(){
		return this.http.get(this.baseUrl + 'temp/temp-job/byEmp', this.jwt());
	}
	
	getDayRanges(){
		return this.http.get(this.baseUrl + 'seed/day-range', this.jwt());
	}
	
	applyTar(tarData: any){
		return this.http.post(this.baseUrl + 'transaction/tars/apply', tarData, this.jwt());
	}
}