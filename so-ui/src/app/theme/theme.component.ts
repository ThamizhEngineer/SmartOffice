import { Component, ViewEncapsulation, OnInit } from '@angular/core';

import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { UserService } from "./../auth/_services/user.service";

@Component({
    selector: 'app-theme',
    templateUrl: './theme.component.html',
	styleUrls:['./default/default.css'],
	encapsulation: ViewEncapsulation.None,
})
export class ThemeComponent implements OnInit{
	defaultTheme:string = "skin-megna-dark";
	themeCss:string;
	minibar: boolean = false;
	
	constructor(private _userService: UserService){}
	
	cUser: any;
	ngOnInit(){
		this.cUser = this._userService.getCurrentUser();
		let theme = this._userService.getCurrentTheme(this.cUser.id);
		this.themeCss = theme ? theme : this.defaultTheme;
	}
	
	changeTheme(newTheme: string){
		this._userService.setCurrentTheme(this.cUser.id, newTheme);
		if(typeof newTheme == 'string') this.themeCss = newTheme;
	}
	
	collapsible(bool: boolean){
		this.minibar = bool;
	}
}
