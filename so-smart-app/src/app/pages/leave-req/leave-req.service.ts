import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';
import { UtilsService } from '../../services/utils.service';

@Injectable({
  providedIn: 'root'
})
export class LeaveReqService {
  baseUrl: string = environment.baseUrl + environment.serviceUrl;

  constructor(private http: HttpClient, public utils: UtilsService) { }

  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this.utils.getCurrentToken(currentUser)
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': currentToken
    });
    return { headers: httpHeaders };
  }

  applyLeave(data: any) {
    return this.http.post(this.baseUrl + 'transaction/leave-applications/apply', data, this.jwt());
  }

  getLeaveTypes() {
    return this.http.get(this.baseUrl + 'seed/leave-types', this.jwt());
  }

  getLeaveData() {
    return this.http.get(this.baseUrl + 'transaction/leave-applications/my-leave-applns', this.jwt());
  }

  getLeaveDataById(id: any) {
    return this.http.get(this.baseUrl + 'transaction/leave-applications/' + id, this.jwt());
  }
}
