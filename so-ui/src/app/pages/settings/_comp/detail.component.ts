import { Component, OnInit } from '@angular/core';

import { Title }     from '@angular/platform-browser';
import { SettingsService } from './../settings.service';

import { NG_VALIDATORS,Validator,Validators,AbstractControl,ValidatorFn, NgForm } from '@angular/forms';

@Component({
  selector: 'settings-detail',
  templateUrl: './detail.component.html'
})
export class SettingsDetailComponent implements OnInit {

	visits: any;
	constructor(private titleService: Title, private _service: SettingsService) { }

	currentUser: any;
	pword: string;
	newPword: string='';
	cnewPword: string;
	ngOnInit() {
		this.currentUser = this._service.getUser();
		
	}
	
	saveMsg: any;
	updatePassword(){
		let id = this.currentUser.id;
		let obj = {password: this.newPword};
		this._service.updatePassword(id, obj).subscribe(y=>{
			this.newPword = this.cnewPword = '';
			this.saveMsg = {'type': 'success', 'text': "Password updated Successfully"};
		}, e=>{this.saveMsg = {'type': 'danger', 'text': "Error in update Password"}; });
	}
}
