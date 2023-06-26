import { Component, OnInit } from '@angular/core';
import { NavController, NavParams,ToastController } from 'ionic-angular';
import{ExpenseStartPage} from '../expense-start/expense-start.component';
import{ExpenseClaimService} from '../../services/expense-claim.service';
import { ExpenseClaim } from '../expense/vo/expense-claim';

/**
 * Generated class for the ExpenseApprovalPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-expense-create-claim',
  templateUrl: 'expense-create-claim.component.html',
})
export class ExpenseCreateClaimPage implements OnInit {
  currentUser: any;
  expenseClaim:ExpenseClaim ;

  jobs:any=[];
  costCenters:any=[];
  location:any;
  expenseId:any
  jobName:any;
  fromDt:string =new Date().toJSON();
  toDt:string =new Date().toJSON();
  jobCodes:any=[];
  isJob:boolean = false;
  isCostCenter:boolean = false;
  saveMsg:any;
  jobId:any;
  onChangeJobId:any;
  constructor(public navCtrl: NavController, public navParams: NavParams,public toastCtrl:ToastController,public expenseClaimService:ExpenseClaimService) {
    let user = this.currentUser = this.expenseClaimService.getCurrentUser();
    this.expenseClaim = new ExpenseClaim();
    this.expenseClaim.employeeId = user.employeeId;
   
  }
  ngOnInit(){
    this.allJobs();
    this.costCenterCombo();
    
    
  }

 expenseStartPage(){
    this.navCtrl.push(ExpenseStartPage);
    
   
 }
 
createExpense(){
  this.expenseClaim.expenseClaimBills=null;

  this.expenseClaimService.create(this.expenseClaim).subscribe(x=>{
    this.saveMsg = {'type': 'success', 'text': "Expense Created Successfully"}
   this.expenseId=x.id;
   this.navCtrl.push(ExpenseStartPage,{data :this.expenseId,param:this.jobId});
    
     
    
  })
  
  this.expenseClaim = new ExpenseClaim();
  this.expenseClaim.employeeId = this.currentUser.employeeId;
  
}



allJobs(){
  this.expenseClaimService.getAllJobs().subscribe(x=>{

this.jobs =x.content;


  })
}
costCenterCombo(){
  this.expenseClaimService.getAllCostCenter().subscribe(x=>{
this.costCenters=x.content;
  })
}

onChangeJob(id) {
 
  
    this.expenseClaimService.getJobById(id).subscribe(x=>{
this.location=x.mapLocationName;
this.jobName=x.jobName;
this.fromDt=x.startDt;
this.toDt=x.endDt;
this.jobId=id;
console.log(this.jobId);

    })
   
}

   
valueIsJobCode() {

  if (this.isJob) {
    this.isJob = false;
  } else {
    this.isJob = true;
  }

}
valueIsCostCode() {

  if (this.isCostCenter) {
    this.isCostCenter = false;
  } else {
    this.isCostCenter = true;
  }

}

expenseCreatedToast(){
  const toast = this.toastCtrl.create({
    message: 'Leave Applied SucessFully',
    duration: 3000
  });
  toast.present();
}
}

