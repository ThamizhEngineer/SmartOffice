import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';

@Injectable()
export class AttendanceCheckService {
    
    
    constructor(private http:Http,
        private commonService:CommonService) { }


    getAttendanceByEmp(empId,year,month) {
        return this.http.get(environment.serviceApiUrl + 'transaction/attendance/find-emp/'+empId+'?month='+month+'&year='+year, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 

    getofficeCal(officeId,year,month) {
        return this.http.get(environment.serviceApiUrl + 'master/office-calendars/serach?officeId='+officeId+'&month='+month+'&year='+year, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 

    createAndUpdate(attendance){
        return this.http.post(environment.serviceApiUrl + 'transaction/attendance/',attendance, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getAttendanceShift(){
        return this.http.get(environment.serviceApiUrl+'master/shift', this.commonService.jwt()).map(res => res.json());
    }
    
}