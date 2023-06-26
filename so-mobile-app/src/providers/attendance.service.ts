import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx'
import "rxjs/add/operator/map";

import { environment } from './../environments/environment';
import { CommonService } from '../providers/common.service';

@Injectable()
export class AttendanceService {

    constructor(private http: Http, private commonService: CommonService) { }

    getCurrentUser() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.appToken) return currentUser;
        return null;
    }

    // Attendance Check-In And Check-out

    checkin(coordinates) {
		if (this.getCurrentUser()) {
			let obj = JSON.stringify({ latitude: coordinates.latitude, longitude: coordinates.longitude, appToken: this.getCurrentUser().appToken, appClientId: environment.clientId });
			return this.http.post(environment.authUrl + 'locations/check-in', obj, this.commonService.jwt()).map((response: Response) => response.json());
		}
    }
	
	checkOut(coordinates) {
		if (this.getCurrentUser()) {
			let obj = JSON.stringify({ latitude: coordinates.latitude, longitude: coordinates.longitude, appToken: this.getCurrentUser().appToken, appClientId: environment.clientId });
			return this.http.post(environment.authUrl + 'locations/check-out', obj, this.commonService.jwt()).map((response: Response) => response.json());
		}
    }

    // Background Location Tracking 
	
	logging(coordinates) {
		if (this.getCurrentUser()) {
			let obj = JSON.stringify({ latitude: coordinates.latitude, longitude: coordinates.longitude, locationStatus: "start", appToken: this.getCurrentUser().appToken, appClientId: environment.clientId });
			return this.http.post(environment.authUrl + 'locations/log-location', obj, this.commonService.jwt()).map((response: Response) => {});
		}
    }

    loggingAfterLogout(coordinates) {
		let username = localStorage.getItem('username');
        if (coordinates) {
			let obj = JSON.stringify({ latitude: coordinates.latitude, longitude: coordinates.longitude, username: username, appClientId: environment.clientId });
            return this.http.post(environment.authUrl + 'locations/log-location', obj, this.commonService.jwt()).map((response: Response) => {});
		}
    }
    
    multipleLogging() {
        let batches = [];
        let allPositions= JSON.parse(localStorage.getItem('offlinepositions'));

        if(this.getCurrentUser() && allPositions){
            for (let position of allPositions) {
                let obj = JSON.stringify({ latitude: position.latitude, longitude: position.longitude, locationStatus: "start", appToken: this.getCurrentUser().appToken, appClientId: environment.clientId });
                batches.push( this.http.post(environment.authUrl + 'locations/log-location', obj, this.commonService.jwt()).map((response: Response) => {}));
            }
            return Observable.forkJoin(batches);
        }
    }

    attendanceSubUrl: string = 'transaction/attendance/';

    // Team Attendance

    teamAttendance(currentDate){
        if (this.getCurrentUser()) {
            return this.http.get(environment.serviceApiUrl + 'transaction/attendance/direct?employeeId='+this.getCurrentUser().employeeId, this.commonService.jwt()).map((response: Response) => response.json());
        }
    }

    // Calendar

    myCalendar(date){
        if (this.getCurrentUser()) {
            return this.http.get(environment.serviceApiUrl + 'transaction/attendance/direct?employeeId='+this.getCurrentUser().employeeId+'&attendanceDt='+date, this.commonService.jwt()).map((response: Response) => response.json());
        }
    }

    currentMonthEvents(currentMonth){
        if (this.getCurrentUser()) {
            return this.http.get(environment.serviceApiUrl + 'transaction/attendance/direct?employeeId='+this.getCurrentUser().employeeId+'&attendanceMonth='+currentMonth, this.commonService.jwt()).map((response: Response) => response.json());
        }
    }
}