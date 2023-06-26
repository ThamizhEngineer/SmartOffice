
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { ExpenseClaimService } from '../expense-claim.service';
import { Expense } from '../../../vo/expense';
import { UserService } from '../../../../auth/_services';
import { status_css } from '../../../vo/status';


@Component({
    selector: 'expense-claim-list',
    templateUrl: './expense-claim-list.component.html'
})
export class ExpenseClaimListComponent implements OnInit {

    constructor(
        private router: Router,
        private expClaimService: ExpenseClaimService,
        private userService: UserService
    ){}

    active:any=[];
    inactive:any=[];
    binding:any='true';
    allClaims:any=[];
    statuses=status_css;
    pageSize :number = 10
	page :number = 1;

    ngOnInit() {
        let currentUser = this.userService.getCurrentUser(); 
        if(currentUser){
			this.expClaimService.getAll().subscribe(res=> {
             for(let expense of res.expenseClaimList){
                 if(expense.expenseClaimStatus=='CREATED'||expense.expenseClaimStatus=='VALIDATION-PENDING'||expense.expenseClaimStatus=='N1-APPROVAL-PENDING'||expense.expenseClaimStatus=='N2-APPROVAL-PENDING'||expense.expenseClaimStatus=='SETTLEMENT-PENDING'){
                    this.active.push(expense);
                 }else{
                     this.inactive.push(expense);
                 }
             }
               
			});
		}
       
    }

    changeView(value){
        this.binding=value;
    }
    
    navigateToDetailPage(id:number){
        this.router.navigate(['/my-space/expense-claim/detail', id]);   
    }
}


