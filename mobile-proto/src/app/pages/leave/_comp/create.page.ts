import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router} from '@angular/router';
import { ModalController, Events, AlertController } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';

import { LeaveType, LeaveApplication } from './leave.object';

import { LeaveService } from './../leave.service';
import * as moment from 'moment'

@Component({
  selector: 'app-input',
  templateUrl: './create.page.html',
  styleUrls: ['./leave.page.scss'],
})
export class LeaveCreatePage implements OnInit{
  
  constructor(private commonService: CommonService,  private router: Router, private events: Events, private modalCtrl: ModalController, private _service: LeaveService,  private alertCtrl: AlertController) { }

  leaveType : LeaveType;
  leaveApp : LeaveApplication;
  leaveTypes:any;
  data: any;
  minDate:any;
  maxDate:any;
  ngOnInit() { 
    	// setting of the 'min' and 'max' values
this.minDate = moment.utc().startOf('day').format('YYYY-MM-DD');
this.maxDate = moment.utc().add(1, 'y').format('YYYY-MM-DD');
	this.leaveType = new LeaveType();
    this.leaveApp = new LeaveApplication();
	
	this._service.getLeaveTypes().subscribe(x=>{
      this.leaveTypes = x;
      console.log(this.leaveTypes)
    });
  }

  ionViewWillEnter(){
  }

  navigateToPage(page?:any, data?:any){
    this.router.navigateByUrl(page, { state: { passedData : data } } );
  }

  ionViewWillLeave(){
    this.events.unsubscribe('child:selected');
  }
  
  createLeave(){
    this.leaveApp.startDt=this.leaveApp.startDt.substring(0,10);
    this.leaveApp.endDt=this.leaveApp.endDt.substring(0,10);


    this._service.applyLeave(this.leaveApp).subscribe(x=>{
      this.showConfirmPopup();
    });
  }

   
   async showConfirmPopup(){
     const confirm = await this.alertCtrl.create({
        header: 'Success',
        message: '<strong>Leave Request Applied Successfully.</strong>',
        buttons: [
        {
            text: 'OK',
            handler: () => {
             this.router.navigate(['/leave']);
            }
          }
        ]
      });
      await confirm.present();
     }
}
