import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { UtilsService } from 'src/app/services/utils.service';

@Injectable({
  providedIn: 'root'
})
export class TarService {

  baseUrl: string = environment.baseUrl + environment.serviceUrl;

  constructor(private http: HttpClient,private _utils:UtilsService) { }

  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log(currentUser)
    var currentToken = this._utils.getCurrentToken(currentUser);
    console.log(currentToken)
		const httpHeaders = new HttpHeaders ({
			 'Content-Type': 'application/json',
			 'Authorization': currentToken
		   });
		return { headers: httpHeaders };
    }

    getTarData() {
      console.log("service call")
      console.log(this.http.get(this.baseUrl + 'transaction/tars', this.jwt()))
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
      tarData
      return this.http.post(this.baseUrl + 'transaction/tars/apply', tarData, this.jwt());
    }

    createTar(tarData: any){
      tarData
      return this.http.post(this.baseUrl + 'transaction/tars/create', tarData, this.jwt());
    }

    getJobCode(){
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get(this.baseUrl+ 'transaction/job-plans/job-emp/'+currentUser.employeeId, this.jwt()); 
  
  }
}
