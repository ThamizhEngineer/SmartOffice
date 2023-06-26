import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class AttendanceReportService {
    constructor(private http: Http, private commonService: CommonService) { }

    getAllAttendanceSummary(month,year,officeId){
        return this.http.get(environment.reportApiUrl +'reports/attendance?month='+month+'&year='+year+'&officeId='+officeId, this.commonService.jwt()).map((res: Response) => res.json());
    }
    exportAttendanceSummary(month,year,officeId){
        return this.http.get(environment.reportApiUrl +'reports/attendance/export-attendance.xls?month='+month+'&year='+year+'&officeId='+officeId, this.commonService.jwtDownloadResArrayBuffer()).catch((err: Response) => Observable.throw(err.json()));
    }
    exportEmployeeSummary(month,year,empCode){
        return this.http.get(environment.reportApiUrl +'reports/attendance/by-empid/'+empCode+'.xls?month='+month+'&year='+year, this.commonService.jwtDownloadResArrayBuffer()).catch((err: Response) => Observable.throw(err.json()));
    }
    getByEmpCode(month,year,empCode){
        return this.http.get(environment.reportApiUrl +'reports/attendance/by-empid/'+empCode+'?month='+month+'&year='+year, this.commonService.jwt()).map((res: Response) => res.json());
    }
    getOffices(){
        return this.http.get(environment.serviceApiUrl + 'master/offices', this.commonService.jwt()).map((response: Response) => response.json());    
    }
}