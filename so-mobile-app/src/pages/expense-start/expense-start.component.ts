import { Component, OnInit } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import{ExpenseDetailPage} from '../expense-detail/expense-detail.component';
import{ExpensePage} from '../expense/expense.component';
import{ExpenseCreateClaimPage} from '../expense-create-claim/expense-create-claim.component';
import{ExpenseClaimService} from '../../services/expense-claim.service';
/**
 * Generated class for the ExpenseApprovalPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-expense-start',
  templateUrl: 'expense-start.component.html',
})
export class ExpenseStartPage implements OnInit{
  expenseId:any;
  id:any;
  createdExpense:any=[];
  jobId:any;
  location:any;
  jobName:any;
  fromDt:string =new Date().toJSON();
  toDt:string =new Date().toJSON();
  constructor(public navCtrl: NavController, public navParams: NavParams,public expenseClaimService:ExpenseClaimService) {
   
      this.expenseId = navParams.get('data');
    
   
    this.id = navParams.get('param2');
    console.log(this.id)
   
   
    this.jobId = navParams.get('param');
   
  
   console.log(this.id)
  
    
  }
  ngOnInit(){
    this.createdExpenseClaim();
   
  }
 
  createdExpenseClaim(){
    if(this.id!=null){
      this.expenseClaimService.getAllExpensesById(this.id).subscribe(x=>{
        this.createdExpense.push(x);
      });
    }else{
    this.expenseClaimService.getAllExpensesById(this.expenseId).subscribe(x=>{
      this.createdExpense.push(x);
      console.log(this.createdExpense);
          })
          if(this.jobId!=null){
           this.expenseClaimService.getJobById(this.jobId).subscribe(x=>{
      this.location=x.mapLocationName;
      this.jobName=x.jobName;
      this.fromDt=x.startDt;
      this.toDt=x.endDt;
      console.log(this.location);
      console.log(this.fromDt)
      console.log(this.toDt)
  
     
    })
  }
  }

  }

 expenseDetailPage(){
  this.navCtrl.push(ExpenseDetailPage);
  this.navCtrl.push(ExpenseDetailPage,{data :this.expenseId,param2:this.id});
  
}
expenseListPage(){
  this.navCtrl.push(ExpensePage);
  }
  expenseCreateClaimPage(){
    this.navCtrl.push(ExpenseCreateClaimPage);
  }
 

}
