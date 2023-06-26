import { Injectable } from '@angular/core';
import { environment } from './../../../environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UtilsService } from '../../services/utils.service';
@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient, public utils: UtilsService) { }

  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this.utils.getCurrentToken(currentUser)
    const httpHeaders = new HttpHeaders({
      'Authorization': currentToken
    });
    return { headers: httpHeaders };
  }

  logging(obj) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this.utils.getCurrentToken(currentUser)
    obj.appToken = currentToken;
    console.log(obj)
    console.log('logging')
    return this.http.post(this.baseUrl + environment.authUrl + 'locations/log-location', obj, this.jwt()).map((response: Response) => { });
  }


  // Attendance Check-In And Check-out

  checkin(obj) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this.utils.getCurrentToken(currentUser)
    obj.appToken = currentToken;
    console.log(obj)
    return this.http.post(this.baseUrl + environment.authUrl + 'locations/check-in', obj, this.jwt());
  }

  checkOut(obj) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this.utils.getCurrentToken(currentUser)
    obj.appToken = currentToken;
    console.log(obj)
    return this.http.post(this.baseUrl + environment.authUrl + 'locations/check-out', obj, this.jwt());
  }

  getMyAttendance() {
    return this.http.get(this.baseUrl + environment.serviceUrl + 'my-space/my-attendances', this.jwt());
  }
}
