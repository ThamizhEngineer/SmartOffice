import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams,ToastController } from 'ionic-angular';
import { MySpaceService } from '../../providers/my-space.service';
import{LeaveService} from '../../services/leave.service';

import{LeavePage} from '../leave/leave.component';
import { Leave } from '../leave/vo/leave';
/**
 * Generated class for the LeaveApprovalPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-leave-approval',
  templateUrl: 'leave-approval.html',
})
export class LeaveApprovalPage {
  leave:Leave;
  saveMsg:any;
  currentUser: any;
  leaveApprovalsData:any=[];
  leaveApprovalsList:any=[];
  hideButtons:any=[];
  leaveId:any;
  constructor(public navCtrl: NavController,public leaveService:LeaveService
    , public navParams: NavParams, public toastCtrl:ToastController,public mySpaceService: MySpaceService) {
    this.getMyLeaveApprovals();

    // this.approvedLeave(this.leave);
    this.leaveId = navParams.get('param1');
   this.leave=new Leave();
    this.getLeaveById(this.leaveId);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LeaveApprovalPage');
  }

  getMyLeaveApprovals(){
    console.log('1');
    this.mySpaceService.getMyLeaveApproval().subscribe((x)=>{
     
      this.leaveApprovalsData=x.content;
    },error=>{

    });
  }
  approvedLeave(){
    if(this.leaveId!=null){
      
this.leaveService.approveLeave(this.leaveId,this.leave).subscribe(x=>{
  this.saveMsg = {'type': 'success', 'text': "Leave Approved Successfully"}

  
 
  })
}
  }
  rejectLeave(){
    if(this.leaveId!=null){
      
this.leaveService.rejectLeave(this.leaveId,this.leave).subscribe(x=>{
  this.saveMsg = {'type': 'success', 'text': "Leave Rejected "}

  
 
  })
}
  }
getLeaveById(leaveId){
  this.leaveService.getLeaveById(leaveId).subscribe(x=>{
    this.leaveApprovalsList.push(x);

  })
}
navigateToListPage(){
  this.navCtrl.push(LeavePage);
}
leaveApprovedToast() {
  const toast = this.toastCtrl.create({
    message: 'Leave Approved SucessFully',
    duration: 3000
  });
  toast.present();
}
leaveRejectedToast() {
  const toast = this.toastCtrl.create({
    message: 'Leave Rejected',
    duration: 3000
  });
  toast.present();
}
}


