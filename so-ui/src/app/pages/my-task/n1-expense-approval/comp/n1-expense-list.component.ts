import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExpenseService } from '../../expense.service';
import { status_css } from '../../../vo/status';

@Component({
    selector: '',
    templateUrl: 'n1-expense-list.component.html'
})

export class N1ExpenseListComponent implements OnInit {

    constructor(private router:Router,private service:ExpenseService) { }

    active:any=[];
    inactive:any=[];
    binding:any='true';
    statuses:any=status_css;

    ngOnInit() { 
        console.log('all res');
        this.service.getAllN1List().subscribe(res=> {
            console.log('all res',res);

            for(let expense of res.expenseClaimList){
                if(expense.expenseClaimStatus=='N1-APPROVAL-PENDING' || expense.expenseClaimStatus=='N2-APPROVAL-PENDING'){
                    this.active.push(expense);
                }else{
                    this.inactive.push(expense);
                }                       
            }
        });
    }

    changeManager(value:any){
		this.binding=value;
		
	}

    navigateToDetailPage(id:number){
        this.router.navigate(['/my-task/n1-expense-approval/view', id]);   
    }
}