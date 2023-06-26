
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExpenseApprovalService } from '../expense-approval.service';
import { saveAs } from 'file-saver';
import { Expense } from '../../../vo/expense';

import { status_css } from '../../../vo/status';


@Component({
    selector: 'expense-approval',
    templateUrl: './expense-approval.component.html'
})
export class ExpenseApprovalComponent implements OnInit {

    constructor(
        private router:Router,
        private expApprovalService: ExpenseApprovalService
    ){}

    statuses: any = status_css;

    allClaims:any;

    active:any=[];
    inactive:any=[];
    binding:any='true';
    
    ngOnInit() {
        this.expApprovalService.getAll().subscribe(res=> {
            
            for(let expense of res.expenseClaimList){
                if(expense.expenseClaimStatus=="VALIDATION-PENDING"){
                    this.active.push(expense);   
                }else{
                    this.inactive.push(expense);
                }
            }
        }); 
       
    }

    changeView(value){
        this.binding=value;
    }

    navigateToDetailPage(id:number){
        this.router.navigate(['/operation/expense-approval/view', id]);   
    }
  
}


