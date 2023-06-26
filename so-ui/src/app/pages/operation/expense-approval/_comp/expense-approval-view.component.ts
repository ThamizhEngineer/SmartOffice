import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Expense,ExpenseClaimBill } from '../../../vo/expense';
import { ExpenseApprovalService } from '../expense-approval.service';
import { saveAs } from 'file-saver';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../../../../shared/common/common.service';

@Component({
    selector: '',
    templateUrl: 'expense-approval-view.component.html'
})

export class ExpenseApprovalViewComponent implements OnInit {
 
    @ViewChild('Approve') Approve: TemplateRef<any>;
    @ViewChild('Reject') Reject: TemplateRef<any>;
    saveMsg:any;
    expense:Expense;
    expenses:[Expense];
    modalReference:any;

    constructor(private activatedRoute: ActivatedRoute,
        private router:Router,
        private modalService: NgbModal,
        private commonService: CommonService,
        private service:ExpenseApprovalService) { }

    ngOnInit() { 

        this.expense = new Expense();
        this.expense.expenseClaimBills = [new ExpenseClaimBill];

        if (this.activatedRoute.params['_value']['id']) {            
			this.activatedRoute.params.switchMap((params: Params) => this.service.getById(params['id']))
			.subscribe( x => {	
                this.expense=x.optional;
                console.log(this.expense);             
			});
		}
    }

    download(docId,docFileName){

        if(docId!=null&&docId!=""&&docId!=undefined){
            this.commonService.downloadDocument(docId,docFileName);
    } 
    }

    navigateToListPage(){
        this.router.navigateByUrl("/operation/expense-approval/list");  
    }
    approveExpense(){
        this.service.UpdateExpenseReq(this.expense.id,"validate",this.expense).subscribe(x=>{
            this.router.navigateByUrl("/operation/expense-approval/list");
            this.modalReference.close();
        });
    }

    rejectExpense(){
        this.service.UpdateExpenseReq(this.expense.id,"validation-reject",this.expense).subscribe(x=>{
            this.router.navigateByUrl("/operation/expense-approval/list");
            this.modalReference.close();
        });
    }

    back(){
        this.modalReference.close();  
    }

    calculateEntitleTotal(){
        let total=0
      for(let list of this.expense.expenseClaimBills)
        if(list.entitledAmount!=null&&list.entitledAmount!=undefined){
            total=total+(list.entitledAmount*1);
            this.expense.totalEntitledAmount=total;
        }
    }

    entity(id){
        console.log(id)
        if(this.expense.expenseClaimBills[id].billStatus=='APPROVED'){
            console.log(this.expense.expenseClaimBills[id].billAmount)
            this.expense.expenseClaimBills[id].entitledAmount=this.expense.expenseClaimBills[id].billAmount;
        }
        if(this.expense.expenseClaimBills[id].billStatus=='REJECTED'){
            this.expense.expenseClaimBills[id].entitledAmount=0;
        }
    }

    approve(){       
            this.modalReference = this.modalService.open(this.Approve, {size: 'lg'});
      }

      reject(){
            this.modalReference = this.modalService.open(this.Reject, {size: 'lg'});	
      } 

}