import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import { environment } from '../environments/environment';

@Injectable()
export class UserService {
	
    constructor(private http: Http) {
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
        return this.http.get(environment.assetsUrl+'/assets/menu.json').map((response: Response) => response.json());
    }
}