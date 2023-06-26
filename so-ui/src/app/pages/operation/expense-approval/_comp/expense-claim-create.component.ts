
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {User} from '../../../../auth/_models/user';
import { Expense,ExpenseClaimBill } from '../../../vo/expense';
import { ExpenseClaimService } from '../../../my-space/expense-claim/expense-claim.service';
import { environment } from '../../../../../environments/environment';
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import {UserService} from '../../../../auth/_services/user.service';
import { Observable } from 'rxjs';
import { DocInfo,DocMetadata } from '../../../vo/doc-info';
import { saveAs } from 'file-saver';
import { CommonService } from '../../../../shared/common/common.service';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

@Component({
    selector: '',
    templateUrl: './expense-claim-create.component.html'
})
export class ExpenseClaimCreateComponent implements OnInit{
    formatter = (x: {categoryName: string}) => x.categoryName;
	searchCategory = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.subCategorys.filter(v => v.subCategoryName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.categoryName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    constructor(
        private activatedRoute: ActivatedRoute,
        private router:Router,
        private userService:UserService,
        private http: Http,
        private expClaimService: ExpenseClaimService,
        private commonService: CommonService
    ){ }
    docsForCheckin: DocInfo[] = []; 
    docIdsForCheckin: DocInfo[] = []; // indices of bills for which docs were added/updated
    expense:Expense;
    expenses:[Expense];
    isDateModified: boolean = false;
    isEdit: boolean = false;
    claimCreatedDt:any;
    vClaimCreatedDt:any;
    saveMsg:any;
    employee:any=[];
    subCategorys:any=[];
    user:User;
    userName:any;
    allClaims:any;
    docInfos:Array<DocInfo>;
    costCode:any=[];
    jobCode:any=[];
    docid:number=0;
    MetaData:DocMetadata;
    documentApiUrl: string = environment.documentApiUrl;

    ngOnInit(){
        this.expense=new Expense();
        this.docInfos=[];
        this.MetaData=new DocMetadata;
        this.expense.expenseClaimBills=[new ExpenseClaimBill]
        
           
        
        this.expClaimService.getCostCode().subscribe(cost=>{
            this.costCode=cost.content;
        })     
        this.expClaimService.getAcc1GrpEmployees().subscribe(emp=>{
            console.log(emp);
            this.employee=emp;
        })
        this.expClaimService.subCategory().subscribe(x=>{
            this.subCategorys=x;
        })
       
        if(this.expense.isJobBased==null){
            this.expense.employeeFName='';
            this.expense.employeeLName='';      
            this.expense.expenseClaimBills[0].subCategoryName=null;      
            this.expense.isJobBased='Y';
        }    
        
     
    }
    expenseClaimPrintout(){
		this.expClaimService.printExpense(this.expense.id.toString()).subscribe(x=>{
		//	 this.router.navigateByUrl("/expense-claim/list/");	
		});
    }
    
    getJobCode(id){
        this.expClaimService.getJobCode(id).subscribe(job=>{
            this.jobCode=job;
        })
    }

    addBillRows() {
        this.docid++;
        console.log(this.docid);
        let bd = new ExpenseClaimBill();
        bd.subCategoryName=null;
        // bd.billDt=bd.billDt.substr(0,10);
		this.expense.expenseClaimBills.push(bd);
	}

    delBillRow(i) {
        
        this.expense.expenseClaimBills.splice(i,1);
        this.calculateExpenseTotal();
        let newList: string[]=[];

    }
    categorySelected($event,i){
        this.expense.expenseClaimBills[i].subCategoryId=$event.item.id;
        this.expense.expenseClaimBills[i].subCategoryName=$event.item.subCategoryName;
    }
 
    download(docId, docFileName){
        if(docId!=null&&docId!=""&&docId!=undefined){
            this.commonService.downloadDocument(docId,docFileName);
        } 
    }
    projectSelected1($event){
        this.expense.employeeId=$event.id;
        this.expense.employeeFName=$event.empName;
    //    this.expense.employeeLName=$event.lastName;
    }

    calculateExpenseTotal(){
        let total=0
      for(let list of this.expense.expenseClaimBills)
        if(list.billAmount!=null&&list.billAmount!=undefined){
            total=total+(list.billAmount*1);
            this.expense.expenseClaimAmount=total;
            console.log("total"+this.expense.expenseClaimAmount);
        }
    }

    navigateToListPage(){
        this.router.navigateByUrl("/operation/expense-approval/list");   
    }

      uploadDoc(billIndex, event, docTypeCode) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            
			 this.commonService.uploadDocument(fileList[0], docTypeCode).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {
                        this.expense.expenseClaimBills[billIndex].docId = data[0].docId;
                        this.docIdsForCheckin.push(data[0].docId);
                        console.log(this.docsForCheckin)
                    },
                    error => console.log(error)
                )
        }
    }

    setMetadata(docId, expClaimId, ecBilldId){
        let docInfo:DocInfo = new DocInfo();
        docInfo.docId = docId;
        docInfo.metadataList = [
            {
                isKey: 'Y',
                mdCode: 'expense-claim-id',
                mdValue: expClaimId
            },
            {
                isKey: 'Y',
                mdCode: 'expense-claim-bill-id',
                mdValue: ecBilldId
            }
        ]; 
        return docInfo;
    }

    prepareDocInfosForCheckin() {     
        this.docIdsForCheckin.forEach(docId  => {
            let ecBill = this.expense.expenseClaimBills.find(ec => ec.docId === docId );
            if(ecBill!=null  && ecBill.docId !=null){
                this.docsForCheckin.push(this.setMetadata(docId, this.expense.id, ecBill.id));
            }

        });

        
    }

    applyClaim(){
        console.log(this.expense);
        
        this.expClaimService.apply(this.expense).subscribe(_exp=>{            
            this.prepareDocInfosForCheckin();
            this.commonService.checkInDocuments(this.docsForCheckin).subscribe(docRes=>{
                this.saveMsg = {'type': 'success', 'text': "Expense claim applied successfully"}
                this.router.navigate(['/operation/expense-approval/list']);   
            },error=>{
                console.log(error);
                this.saveMsg = {'type': 'danger', 'text': "Expense applied, however documents not uploaded. Please check with admin"};
                this.router.navigate(['/operation/expense-approval/list']);   
            });
        },e=>{
            this.saveMsg = {'type': 'danger', 'text': "Error in Expense service"};
        });
    }

}