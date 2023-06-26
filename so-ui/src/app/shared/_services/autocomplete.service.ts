import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../common/common.service';

@Injectable()
export class AutoCompleteService {

 constructor(private http: Http, private commonService: CommonService) {
  }

    getEmployees() {
      return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
  }
}
