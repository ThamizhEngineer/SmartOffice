import { Injectable } from '@angular/core';
import { environment } from './../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { THROW_IF_NOT_FOUND } from '@angular/core/src/di/injector';


    @Injectable()
    export class AttendanceShiftService {
    
        constructor(private http: Http, private commonService: CommonService) {
        }
    getAttendanceShift(){
        return this.http.get(environment.serviceApiUrl+'master/shift',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getAttendanceShiftById(id:string){
        return this.http.get(environment.serviceApiUrl+'master/shift/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    addAttendanceShift(shift){
        return this.http.post(environment.serviceApiUrl+'master/shift/',shift,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());

    }
    updateAttendanceShiftById(shift,id:string){
        return this.http.patch(environment.serviceApiUrl+'master/shift/'+id,shift,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteAttendanceShiftById(id:string){
        return this.http.delete(environment.serviceApiUrl + 'master/shift/'+ id, this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }
}