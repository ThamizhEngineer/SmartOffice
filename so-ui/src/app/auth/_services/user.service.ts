import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';

@Injectable()
export class UserService {
    
    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
    
    constructor(private http: Http,private commonService: CommonService) {
    }

	getCurrentUser() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.appToken) return currentUser;
        return null;
    }
	
	getCurrentTheme(id) {
        return JSON.parse(localStorage.getItem('so_currentTheme_'+id));
    }
	
	setCurrentTheme(id, theme){
		localStorage.setItem('so_currentTheme_'+id, JSON.stringify(theme));
	}
	
	getCurrentSize(id) {
        return JSON.parse(localStorage.getItem('so_currentSize_'+id));
    }
	
	setCurrentSize(id, size){
		localStorage.setItem('so_currentSize_'+id, JSON.stringify(size));
	}
	
    getMenu() {
        return this.http.get(this.baseUrl+'menu/',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    // getMenu() {
    //     return this.http.get(environment.assetsUrl+'/assets/menu.json').map((response: Response) => response.json());
    // }
}