import { Injectable } from '@angular/core';
import { Headers, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class CommonService {
  
  constructor(){
    //this.http._defaultOptions.headers.set('Authorization', 'token');
  }

  jwt(paramArr?: any) {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json','authorization': currentUser.appToken});
		if(paramArr){
			let myParams = new URLSearchParams();
			paramArr.forEach( x => myParams.append(x.key, x.value) );
			return new RequestOptions({ headers: headers, params: myParams });
		}
      else return new RequestOptions({ headers: headers });
	}

	formatDate(date) {
		let d = new Date(date),
				month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }
  
  getYear(date){
    return date.split("-")[0];
  }

  getMonth(date){
    return date.split("-")[1];
  }
}

