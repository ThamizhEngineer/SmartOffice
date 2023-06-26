import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Expense,ExpenseClaimBill } from '../../../vo/expense';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExpenseService } from '../../expense.service';
import { saveAs } from 'file-saver';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../../../../shared/common/common.service';
import { status_css } from '../../../vo/status';

@Component({
    selector: '',
    templateUrl: 'acc-expense-view.component.html'
})

export class ExpenseViewComponent implements OnInit {
  
    @ViewChild('Approve') Approve: TemplateRef<any>;
    @ViewChild('Reject') Reject: TemplateRef<any>;
    expense:Expense;
    expenses:[Expense];
    statuses:any=status_css;
    modalReference:any;

    constructor(
        private activatedRoute: ActivatedRoute,
        private router:Router,
        private modalService: NgbModal,
        private service:ExpenseService,
        private commonService: CommonService
    ) { }

    ngOnInit() { 

        this.expense = new Expense();
        this.expense.expenseClaimBills = [new ExpenseClaimBill];

        if (this.activatedRoute.params['_value']['id']) {            
			this.activatedRoute.params.switchMap((params: Params) => this.service.getExpenseById(params['id']))
			.subscribe( x => {	
                this.expense=x.optional;
                console.log(this.expense);             
			});
		}
    }
    
    navigateToListPage(){
        this.router.navigateByUrl("/my-task/acc-expense/list");  
    }

    download(docId,docFileName){

        if(docId!=null&&docId!=""&&docId!=undefined){
            this.commonService.downloadDocument(docId,docFileName);
    } 
    }

    approveExpense(){
        this.service.UpdateExpenseReq(this.expense.id,"settle",this.expense).subscribe(x=>{
            this.modalReference.close();  
            this.router.navigateByUrl("/my-task/acc-expense/list"); 
        });  

    }

    rejectExpense(){
        this.service.UpdateExpenseReq(this.expense.id,"settlement-reject",this.expense).subscribe(x=>{
            this.modalReference.close();  
            this.router.navigateByUrl("/my-task/acc-expense/list"); 
        });
    }

    back(){
        this.modalReference.close();  
    }

    approve(){       
            this.modalReference = this.modalService.open(this.Approve, {size: 'lg'});
      }

      reject(){
            this.modalReference = this.modalService.open(this.Reject, {size: 'lg'});	
      }

}