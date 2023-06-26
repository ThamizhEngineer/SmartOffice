import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import{LeaveDetailPage} from '../leave-detail/leave-detail.component';
import {MySpaceService } from './../../providers/my-space.service';
import{LeaveApprovalPage} from '../leave-approval/leave-approval';

/**
 * Generated class for the LeavePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */


@Component({
  selector: 'page-leave',
  templateUrl: 'leave.component.html',
})
export class LeavePage {
  casualLeave:string;
  sickLeave:string;
  privilegeLeave:string;
  weddingLeave:string;
  paternetyLeave:string;
  leavesType:any;
  leavesData:any=[];
  leavesTypes:any=[];
  leavesTypeName:any=[];
  leaveBalance:any=[];
  noOfLeaves:any=[];
  noOfCasualLeave:string;
  appliedData:any=[];
  approvedData:any=[];
  leaveBalance1:any;
  leaveBalance2:any;
  leaveBalance3:any;
  leaveBalance4:any;
  leaveBalance5:any;
  constructor(public navCtrl: NavController, public navParams: NavParams, public mySpaceService: MySpaceService) {
    this.getMyLeaves();
    this.getMyLeavesApproved();
    this.myFunction();
    this.mySpaceService.getAllLeaveTypes().subscribe(x=>{
      this.leavesTypes=x;
      this.leavesTypes.forEach(x=>{
       this.leavesTypeName.push(x.leaveTypeName);
      
       this.leavesTypeName.forEach(x=>{
      
        if(x=='Casual Leave'){ 
        this.leavesType=x;
       
        }
        
        
        if(x =='Sick Leave'){
          
          this.sickLeave=x;
        }
        if(x =='Privilege Leave'){
         
          this.privilegeLeave=x;
        }
        if(x =='Wedding/Adoption Leave'){
         
          this.weddingLeave=x;
        }
        if(x =='Paternety/Maternity Leave'){
          
          this.paternetyLeave=x;
        }
        
       })
       
        
       
      
    
      })
    
     
    })
    this.mySpaceService.getAllLeaveTypes().subscribe(x=>{
      this.leavesTypes=x;
      this.leavesTypes.forEach(x=>{
        this.leaveBalance.push(x.leaveBalance);
       
      
        
         console.log(this.leaveBalance1)
         if(x.leaveTypeName=='Casual Leave'&&x.leaveBalance!=null){
          this.leaveBalance1=x.leaveBalance;
          console.log(this.leaveBalance1);
         }
         if(x.leaveTypeName=='Sick Leave'&&x.leaveBalance!=null){
          this.leaveBalance2=x.leaveBalance;
          console.log(this.leaveBalance1);
         }
         if(x.leaveTypeName=='Privilege Leave'&&x.leaveBalance!=null){
          this.leaveBalance3=x.leaveBalance;
          console.log(this.leaveBalance1);
         }
         if(x.leaveTypeName=='Wedding/Adoption Leave'&&x.leaveBalance!=null){
          this.leaveBalance4=x.leaveBalance;
          console.log(this.leaveBalance1);
         }
         if(x.leaveTypeName=='Paternety/Maternity Leave'&&x.leaveBalance!=null){
          this.leaveBalance5=x.leaveBalance;
          console.log(this.leaveBalance1);
         }
      })
    })
   
    
    
  }

  myFunction() {
  
    
    }
    mySickLeave() {
     
      
      }
      myPrivelageLeave(){
        
      }
      myWeddingLeave(){
        
      }
      myMaternityLeave(){
        
      }
  ionViewDidLoad() {
    console.log('ionViewDidLoad LeavePage');
  }

  getMyLeaves(){
    console.log('1');
    this.mySpaceService.getMyLeaves().subscribe((x)=>{
   
      this.leavesData=x.content;
      this.leavesData.forEach(x=>{
        if(x.leaveStatus!='APPROVED'){
         
          this.appliedData.push(x);
        }
        })
    },error=>{

    });
  }
  getMyLeavesApproved(){
 
    this.mySpaceService.getMyLeaves().subscribe((x)=>{
   
      this.leavesData=x.content;
      this.leavesData.forEach(x=>{
      if(x.leaveStatus=='APPROVED'){
      
        this.approvedData.push(x);
      }
      })
    },error=>{

    });
  }

  
  applyCasualLeave(){
   
    this.myFunction();
    this.navCtrl.push(LeaveDetailPage,{data : this.leavesType} );
    console.log(this.leavesType);
  }
  applySickLeave(){
   
    this.mySickLeave();
    this.navCtrl.push(LeaveDetailPage,{data : this.sickLeave});
  }
  applyPrivelageLeave(){
   
    this.myPrivelageLeave();
    this.navCtrl.push(LeaveDetailPage,{data : this.privilegeLeave});
  }
  applyWeddingLeave(){
   
    this.myWeddingLeave();
    this.navCtrl.push(LeaveDetailPage,{data : this.weddingLeave});
  }
  applyMaternityLeave(){
   
    this.myMaternityLeave();
    this.navCtrl.push(LeaveDetailPage,{data : this.paternetyLeave});
  }
  approveScreen(id:string){
this.navCtrl.push(LeaveApprovalPage,{param1 : id});
console.log(id);
  }
}
