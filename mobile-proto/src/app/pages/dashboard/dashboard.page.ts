import { Component, OnInit ,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardService } from './/dashboard.service';
// import { Platform } from 'ionic-angular';
// import { Events } from '@ionic/angular';
import { Platform, Events } from '@ionic/angular';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.page.html',
  styleUrls: ['./dashboard.page.scss'],
})
export class DashboardPage implements OnInit {
	private resetBackButton: any;

	constructor( private router: Router, private _service: DashboardService,
		private platform: Platform,private events: Events
		) 
		{
	      this.platform.backButton.subscribeWithPriority(0, () => {
      // code that is executed when the user pressed the back button
      // and ionic doesn't already know what to do (close modals etc...)
    })
	}


	// ionViewDidEnter() {
	// 	if (this.resetBackButton) {
	// 	  this.resetBackButton();
	// 	  console.log("backbutton1")
	// 	}
	// 	this.resetBackButton = this.platform.registerBackButtonAction(null);
	//   }
	
	//   ionViewWillLeave() {
	// 	if (this.resetBackButton) {
	// 	  this.resetBackButton();
	// 	  this.resetBackButton = null;
	// 	  console.log("backbutton2")

	// 	}
	//   }

	  

	data: any;
	
	ngOnInit() {
		this._service.getDashboardData().subscribe(x=>{
		   this.data = x;
		   console.log("Dashboard starts")
		   console.log(this.data)
		});
		
	}
	
}