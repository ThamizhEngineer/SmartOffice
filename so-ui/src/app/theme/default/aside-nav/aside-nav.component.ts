import { Component, OnInit, Input  } from '@angular/core';
import { UserService } from "./../../../auth/_services/user.service";
import { Router } from '@angular/router';
import {User} from '../../../auth/_models/user';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { SlideInOutAnimation } from './../../../shared/_models/slide.down';

declare let mLayout: any;
@Component({
    selector: "app-aside-nav",
    templateUrl: "./aside-nav.component.html",
	styleUrls:['./aside-nav.css'],
	animations: [SlideInOutAnimation]
})
export class AsideNavComponent implements OnInit {
	@Input() minibar: boolean;
	currentUser: User;
    menuData: any;
    parentState: string
	isShow: boolean[] = [];
	animationState: string[] = [];
	rows: number = 0;
	
	constructor(private router: Router, private _userService: UserService) { 

	}
	
	ngOnInit(){
		let urls = (this.router.url) ? this.router.url.split('/') : ['',''];
		this.parentState = urls[1];
		
		this.currentUser = this._userService.getCurrentUser();		
		// this._userService.getMenu().subscribe(response => {
			this.rows = this.currentUser.menu.length;
			this.menuData = this.currentUser.menu;
			let authRoles =[];
			if(this.currentUser.userStatus == "PROFILE-CONFIRMATION-PENDING") authRoles.push({'authRoleCode': 'PCP'});
			// else if (this.currentUser.authUserRoles!= undefined){
			// 	authRoles = this.currentUser.authUserRoles;
			// }
			
			// build menu based in user's roles
		// 	response.forEach(menuItem => {
		// 		if (menuItem.roles.some(role => authRoles.some(authRole => authRole.authRoleCode ==role))){
		// 			if(menuItem.children != undefined){
		// 				menuItem.children = menuItem.children.filter(child => (child.roles!=undefined)&&(child.roles.some(r => authRoles.some(authRole => authRole.authRoleCode ==r))));
		// 			}	
		// 			this.menuData.push(menuItem);
		// 		}
			   
		//    });
			this.set();
        // });
	}
	
	set(){
		for (let i = 0; i < this.rows; i++) {
			let con = (this.menuData[i] && this.parentState == this.menuData[i].state) ? 'in' : 'out';
			this.animationState.push(con);
		}
	}
	
	expandBar(e: any, ind: number) {
		for (let i = 0; i < this.rows; i++) {
			if(i != ind) this.animationState[i]= 'out';
		}
		this.animationState[ind] = this.animationState[ind] == 'in' ? 'out' : 'in';
	}
	
	menuMouseOver(ind: number): void {
		if(this.minibar) this.animationState[ind] = 'in';
	}
	
	menuMouseOut(ind: number): void {
		if(this.minibar) this.animationState[ind] = 'out';
	}
}