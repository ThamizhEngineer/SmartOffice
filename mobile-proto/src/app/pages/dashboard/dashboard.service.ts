import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';

@Injectable()
export class DashboardService {

    constructor(private http: HttpClient) {
    }
	
	getDashboardData() {
        
		return this.http.get('/assets/data/dashboard.json');
    }
}