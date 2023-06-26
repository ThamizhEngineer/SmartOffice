import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx'
import "rxjs/add/operator/map";
import { environment } from './../environments/environment';

@Injectable()
export class AttendanceService {

    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: Http) {
    }

    private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
        return new RequestOptions({ headers: headers });
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
			return this.http.post(this.baseUrl + 'locations/log-location', obj, this.jwt()).map((response: Response) => {});
		}
    }

    loggingAfterLogout(coordinates) {
		let username = localStorage.getItem('username');
        
        if (coordinates) {
			let obj = JSON.stringify({ latitude: coordinates.latitude.toString(), longitude: coordinates.longitude.toString(), username: username, appClientId: this.clientId });
            return this.http.post(this.baseUrl + 'locations/log-location', obj, this.jwt()).map((response: Response) => {});
		}
    }
    
    multipleLogging() {
        let batches = [];
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let allPositions= JSON.parse(localStorage.getItem('offlinepositions'));

        if(currentUser && allPositions){
            for (let position of allPositions) {
                let obj = JSON.stringify({ latitude: position.latitude.toString(), longitude: position.longitude.toString() , locationStatus: "start", appToken: currentUser.appToken, appClientId: this.clientId });
                console.log("obj",obj);
                batches.push( this.http.post(this.baseUrl + 'locations/log-location', obj, this.jwt()).map((response: Response) => {}));
            }
            return Observable.forkJoin(batches);
        }
    }

    teamAttendance(currentDate){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        
		if (currentUser) {
            return this.http.get(this.serviceApiUrl + 'transaction/attendance/direct?employeeId='+currentUser.employeeId, this.jwt()).map((response: Response) => response.json());
        }
    }

    myCalendar(date){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) {
            return this.http.get(this.serviceApiUrl + 'transaction/attendance/direct?employeeId='+currentUser.employeeId+'&attendanceDt='+date, this.jwt()).map((response: Response) => response.json());
        }
    }

    currentMonthEvents(currentMonth){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) {
            return this.http.get(this.serviceApiUrl + 'transaction/attendance/direct?employeeId='+currentUser.employeeId+'&attendanceMonth='+currentMonth, this.jwt()).map((response: Response) => response.json());
        }
    }
}