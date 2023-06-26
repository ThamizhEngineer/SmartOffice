import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import { Observable } from 'rxjs/Rx'
import "rxjs/add/operator/map";
import { environment } from './../../environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthUser } from './../vo/authUser';

@Injectable()
export class AttendanceService {

        baseUrl : string = environment.baseUrl;
    constructor(private http: HttpClient) { }

    private jwt() {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		const httpHeaders = new HttpHeaders ({
			 'Authorization': currentUser.appToken
		   });
		return { headers: httpHeaders };
    }

    logging(obj) {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        obj.appToken=currentUser.appToken;
        console.log(obj)
        console.log('logging')
	return this.http.post(this.baseUrl+environment.authUrl + 'locations/log-location', obj, this.jwt()).map((response: Response) => {});
    }


        // Attendance Check-In And Check-out

        checkin(obj) {
                let currentUser = JSON.parse(localStorage.getItem('currentUser'));
                obj.appToken=currentUser.appToken;
                console.log(obj)
                return this.http.post(this.baseUrl+environment.authUrl + 'locations/check-in', obj, this.jwt());
        }
        
        checkOut(obj) {
                let currentUser = JSON.parse(localStorage.getItem('currentUser'));
                obj.appToken=currentUser.appToken;
                console.log(obj)
                return this.http.post(this.baseUrl+environment.authUrl + 'locations/check-out', obj, this.jwt());
        }

        getMyAttendance(){
                return this.http.get(this.baseUrl+environment.serviceUrl + 'my-space/my-attendances', this.jwt());   
            }
        

}

