import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExpenseService } from '../../expense.service';
import { status_css } from '../../../vo/status';

@Component({
    selector: '',
    templateUrl: 'acc-expense-list.component.html'
})

export class ExpenseListComponent implements OnInit {

    constructor(private router:Router,private service:ExpenseService) { }

    active:any=[];
    inactive:any=[];
    binding:any='true';
    statuses:any = status_css;

    ngOnInit() { 
        console.log('all res');
        this.service.getAllAcc2List().subscribe(res=> {
            console.log('all res',res);
            for(let expense of res.expenseClaimList){
                if(expense.expenseClaimStatus=='SETTLEMENT-PENDING'){
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
        this.router.navigate(['/my-task/acc-expense/view', id]);   
    }
}