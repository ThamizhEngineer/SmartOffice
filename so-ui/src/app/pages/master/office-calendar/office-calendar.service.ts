import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'


@Injectable()
export class OfficeCalendarService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http,
        private commonService: CommonService) {
        this.baseUrl = environment.apiUrl;
    }

    getOfficeCalendarHeaderById(id) {  
        return this.http.get(environment.serviceApiUrl + 'master/office-calendar-headers/id/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    getOfficeCalendarByYear(year) {  
        return this.http.get(environment.serviceApiUrl + 'master/office-calendar-headers/'+year, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }   
    getOffices() {  
        return this.http.get(environment.serviceApiUrl + 'master/offices', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    getOfficeById(id){
        return this.http.get(environment.serviceApiUrl + 'master/offices/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());        
    }
    getCalendarYearByid(id){
        return this.http.get(environment.serviceApiUrl + 'seed/cal-years/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    createCalendarYear(officeCal){
        return this.http.post(environment.serviceApiUrl + 'seed/cal-years/',officeCal, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    calendarSetUp(officeId,yearId,setup){
        return this.http.patch(environment.serviceApiUrl + 'master/office-calendar-headers/calendar-setup/'+officeId+'/'+yearId,setup, this.commonService.jwt()).map((response: Response) => response); 
    }

    groupByMonth(officeId,yearId){
        return this.http.get(environment.serviceApiUrl + 'master/office-calendars/group-by-month/'+officeId+'/'+yearId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getByMonth(officeId,yearId,month){
        return this.http.get(environment.serviceApiUrl + 'master/office-calendars/month/'+officeId+'/'+yearId+'/'+month, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }

    updateOfficeCalendar(id,officeId,yearId,officeCalendar){
        return this.http.patch(environment.serviceApiUrl + 'master/office-calendars/'+id+'/'+officeId+'/'+yearId,officeCalendar, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }

    completeCalendar(id,officeCalendarHeader){
        return this.http.patch(environment.serviceApiUrl + 'master/office-calendar-headers/complete-calender/'+id,officeCalendarHeader, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }

    getMultipleData(year: number) {
        let batches = [];
		batches.push(this.http.get(environment.serviceApiUrl  + 'seed/cal-years/year/'+(year-1), this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(environment.serviceApiUrl  + 'seed/cal-years/year/'+year, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(environment.serviceApiUrl  + 'seed/cal-years/year/'+(year+1), this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));		        
        return Observable.forkJoin(batches);
    }

    uploadHolidays(file: File, type: any, officeId: any,yearId:any) {
        let formData: FormData = new FormData();
        formData.append('uploadingFiles', file, file.name);
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data', 'authorization': currentUser.appToken });
        let myParams = new URLSearchParams();
        myParams.append("content-type", "application/json");
        let options = new RequestOptions({ headers: headers, params: myParams });
        let documentUrl = environment.documentApiUrl + "csv/office-calendars/upload/" + type + "/" + officeId+"/"+yearId;
        return this.http.post(documentUrl, formData, options).map((response: Response) => response)
          .map(res => res.json());
      }
}
