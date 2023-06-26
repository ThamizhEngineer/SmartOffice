import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import "rxjs/add/operator/map";
import { environment } from './../../../../environments/environment';

@Injectable()
export class AttendanceService {

    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: Http) {
    }

    private jwt(paramArr?: any) {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
		if(paramArr){
			let myParams = new URLSearchParams();
			paramArr.forEach( x => myParams.append(x.key, x.value) );
			return new RequestOptions({ headers: headers, params: myParams });
		}
        else return new RequestOptions({ headers: headers });
    }

    checkin(coordinates) {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		
		if (currentUser) {
			let obj = JSON.stringify({ latitude: coordinates.latitude.toString(), longitude: coordinates.longitude.toString(), appToken: currentUser.appToken, appClientId: this.clientId });
			return this.http.post(this.baseUrl + 'locations/check-in', obj, this.jwt()).map((response: Response) => response.json());
		}
    }
	
	checkOut(coordinates) {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		
		if (currentUser) {
			let obj = JSON.stringify({ latitude: coordinates.latitude.toString(), longitude: coordinates.longitude.toString(), appToken: currentUser.appToken, appClientId: this.clientId });
			return this.http.post(this.baseUrl + 'locations/check-out', obj, this.jwt()).map((response: Response) => response.json());
		}
    }
	
	logging(coordinates) {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		
		if (currentUser) {
			let obj = JSON.stringify({ latitude: coordinates.latitude.toString(), longitude: coordinates.longitude.toString(), locationStatus: "start", appToken: currentUser.appToken, appClientId: this.clientId });
			return this.http.post(this.baseUrl + 'locations/log-location', obj, this.jwt()).map((response: Response) => response.json());
		}
    }
	
	getUser(){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) return currentUser;
	}
	
	getEmployees(){		
        return this.http.get(this.serviceApiUrl + 'master/employees', this.jwt()).map((response: Response) => response.json());
    }
	
	getDirectAttendance(_id, curMonth?: number, curYear?: number){
		let data = [];
		if(_id) data.push({key: 'employeeId', value: _id});
		if(curMonth) data.push({key: 'attendanceMonth', value: curMonth});
		if(curYear) data.push({key: 'attendanceYear', value: curYear});

        return this.http.get(this.serviceApiUrl + 'transaction/attendance/direct', this.jwt(data)).map((response: Response) => response.json());
    }
	
	bulkAttendances(arr: any) {
        return this.http.patch(this.serviceApiUrl + 'transaction/attendance/direct/bulk', arr, this.jwt()).map((response: Response) => response.json());
    }
}