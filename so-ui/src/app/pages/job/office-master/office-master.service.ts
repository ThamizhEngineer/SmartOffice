import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { office } from './vo/office';
@Injectable()
export class OfficeMasterService {


    constructor(private http: Http, private commonService: CommonService) {
    }


   getAllOffices(){
    return this.http.get(environment.serviceApiUrl + 'master/offices' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
   }
   getOfficeById(id:string){
    return this.http.get(environment.serviceApiUrl + 'master/offices/'+id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
   }
   getAttendanceShift(){
    return this.http.get(environment.serviceApiUrl+'master/shift', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
   getAllEmployees(){
    return this.http.get(environment.serviceApiUrl + 'master/employees/' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
   }
   getAllCountry(){
    return this.http.get(environment.serviceApiUrl + 'seed/countries' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
   }
   createorUpdateOffice(office:any){
  
    return this.http.patch(environment.serviceApiUrl+'master/offices/bulk-update',office, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
createOffice(office:office){
  
    return this.http.post(environment.serviceApiUrl+'master/offices/',office, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
deleteOffice(id:string){
    return this.http.delete(environment.serviceApiUrl + 'master/offices/'+id,this.commonService.jwt()).map((response) => response);
}
}
