import { Component, OnInit } from '@angular/core';
import { LeaveReqService } from './../leave-req.service';
import { ModalController, AlertController } from '@ionic/angular';
import { Router } from '@angular/router';
import { LeaveType, LeaveApplication } from './../leave.object';

@Component({
  selector: 'app-leave-req-create',
  templateUrl: './leave-req-create.page.html',
  styleUrls: ['./leave-req-create.page.scss'],
})
export class LeaveReqCreatePage implements OnInit {
  currentDate = new Date();

  leaveType: LeaveType;
  leaveApp: LeaveApplication;
  leaveTypes: any;
  minDate: any;
  maxDate: any;
  isDisabled:boolean=false;

  constructor(private _service: LeaveReqService, private router: Router, private modalCtrl: ModalController, private alertCtrl: AlertController) { }

  ngOnInit() {
    this.minDate = "2020-03-14";
    this.maxDate = "2021-12-09";

    this.leaveType = new LeaveType();
    this.leaveApp = new LeaveApplication();
    this._service.getLeaveTypes().subscribe(x => {
      this.leaveTypes = x;
      console.log(this.leaveTypes)
    });
  }

  createLeave() {
    this.isDisabled=true
    this.leaveApp.startDt = this.leaveApp.startDt.substring(0, 10);
    this.leaveApp.endDt = this.leaveApp.endDt.substring(0, 10);
    this.leaveApp.leaveDenialAckgmt="Y";
    this.leaveApp.emgncyAvailability="Y";
    this.leaveApp.jobAckgmt="Y";
    this._service.applyLeave(this.leaveApp).subscribe(x => {
      this.showConfirmPopup();
    });
  }


  async showConfirmPopup() {
    const confirm = await this.alertCtrl.create({
      header: 'Success',
      message: '<strong>Leave Request Applied Successfully.</strong>',
      buttons: [
        {
          text: 'OK',
          handler: () => {
            this.router.navigate(['/home/leave-req']);
          }
        }
      ]
    });
    await confirm.present();
  }
}
