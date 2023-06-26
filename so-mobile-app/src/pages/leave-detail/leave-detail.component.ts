import { Component } from '@angular/core';
import { NavController,ToastController, NavParams } from 'ionic-angular';
import{LeavePage} from '../leave/leave.component';
import{Leave} from '../leave/vo/leave';
import {LeaveService } from './../../services/leave.service';

@Component({
  selector: 'page-leave-detail',
  templateUrl: 'leave-detail.component.html',
})
export class LeaveDetailPage {
  leave:Leave ;
  saveMsg:any;
  currentUser: any;
  myInputList: string[]
  leavesType: string;
sickLeave:any;
  constructor(public navCtrl: NavController, public leaveService:LeaveService,public navParams: NavParams,public toastCtrl:ToastController) {
  	let user = this.currentUser = this.leaveService.getCurrentUser();
		this.leave = new Leave();
		this.leave.employeeId = user.employeeId;
    this.leavesType = navParams.get('data');
    
    console.log(this.leavesType);
    
  }
 
  navigateToListPage(){
      this.navCtrl.push(LeavePage);
  }
  leaveAppliedToast() {
    const toast = this.toastCtrl.create({
      message: 'Leave Applied SucessFully',
      duration: 3000
    });
    toast.present();
  }
  applyLeave(){
this.leave.leavesType=this.leavesType;

    this.leaveService.addLeave(this.leave).subscribe(x=>{
      this.saveMsg = {'type': 'success', 'text': "Leave Applied Successfully"}
      console.log(this.leave.leavesType);

			this.leave = new Leave();
      this.leave.employeeId = this.currentUser.employeeId;
    });
   
      }

}
