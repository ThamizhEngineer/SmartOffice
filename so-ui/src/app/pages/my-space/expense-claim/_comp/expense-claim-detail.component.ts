
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {User} from '../../../../auth/_models/user';
import { Expense,ExpenseClaimBill } from '../../../vo/expense';
import { DatePipe } from '@angular/common';
import { ExpenseClaimService } from '../expense-claim.service';
import { environment } from '../../../../../environments/environment';
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import {UserService} from '../../../../auth/_services/user.service';
import { Observable } from 'rxjs';
import { DocInfo,DocMetadata } from '../../../vo/doc-info';
import { saveAs } from 'file-saver';
import { CommonService } from '../../../../shared/common/common.service';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';


@Component({
    selector: 'expense-claim-detail',
    templateUrl: './expense-claim-detail.component.html'
})
export class ExpenseClaimDetailComponent implements OnInit{
 
    formatter = (x: {subCategoryName: string}) => x.subCategoryName;
	profileAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.subCategorys : this.subCategorys.filter(v => v.subCategoryName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.categoryName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    constructor(
        private activatedRoute: ActivatedRoute,
        private router:Router,
        private userService:UserService,
        private datePipe: DatePipe,
        private http: Http,
        private expClaimService: ExpenseClaimService,
        private commonService: CommonService
    ){ }
    docsForCheckin: DocInfo[] = []; 
    docIdsForCheckin: DocInfo[] = []; // indices of bills for which docs were added/updated
    expense:Expense;
    checkinExpense:Expense;
    expenses:[Expense];
    isDateModified: boolean = false;
    isEdit: boolean = false;
    claimCreatedDt:any;
    vClaimCreatedDt:any;
    subCategorys:any=[];
    saveMsg:any;
    user:User;
    userName:any;
    allClaims:any;
    docInfos:Array<DocInfo>;
    costCode:any=[];
    empId:any;
    jobCode:any=[];
    docid:number=0;
    MetaData:DocMetadata;
    
    isbillable:boolean=true;

    documentApiUrl: string = environment.documentApiUrl;

    ngOnInit(){
        this.expense=new Expense();
        this.docInfos=[];
        this.MetaData=new DocMetadata;
        this.expense.expenseClaimBills=[new ExpenseClaimBill]
        
        this.user = this.userService.getCurrentUser();
        this.userName=this.user.firstName;
        this.empId=this.user.employeeId;
        console.log(this.userName);

        this.expClaimService.getCostCode().subscribe(cost=>{
            this.costCode=cost.content;
        })
        this.expClaimService.getJobCode(this.empId).subscribe(job=>{
            this.jobCode=job;
        })
       
        if(this.expense.isJobBased==null){
            this.expense.dummyAppliedDt=new Date().toISOString();
            this.expense.isJobBased='Y';
        }

        this.expClaimService.subCategory().subscribe(x=>{
            this.subCategorys=x;
        })

        if (this.activatedRoute.params['_value']['id']) {
            this.isEdit = true;
			this.activatedRoute.params.switchMap((params: Params) => this.expClaimService.getById(params['id']))
			.subscribe( x => {
                
                if(x.radioSelect==null){
                    x.radioSelect='jobCode';
                }
                console.log("x",x);	
                this.expense=x.optional;
               
                console.log(this.expense); 
                //  this.objModify(x);	            
            });            
        }
        if(this.expense.expenseClaimBills[0].subCategoryName==null){
            this.expense.expenseClaimBills[0].subCategoryName='';
        }
       
    }
    expenseClaimPrintout(){
		this.expClaimService.printExpense(this.expense.id.toString()).subscribe(x=>{
		//	 this.router.navigateByUrl("/expense-claim/list/");	
		});
    }
    

    addBillRows() {
        this.docid++;
        console.log(this.docid);
        let bd = new ExpenseClaimBill();
        bd.subCategoryName='';
        // bd.billDt=bd.billDt.substr(0,10);
		this.expense.expenseClaimBills.push(bd);
	}

    delBillRow(i) {
        
        this.expense.expenseClaimBills.splice(i,1);
        this.calculateExpenseTotal();
        let newList: string[]=[];

    }
        // objModify(expense:Expense){
        
    //     this.expense.expenseClaimBills[0].billDt=this.expense.expenseClaimBills[0].billDt.substr(0,10);
        
    //     this.expense=expense;
    //     this.expense.appliedDt=this.expense.appliedDt.substr(0,10);
    // }
  
    download(docId, docFileName){
        if(docId!=null&&docId!=""&&docId!=undefined){
            this.commonService.downloadDocument(docId,docFileName);
        } 
    }

    calculateExpenseTotal(){
        let total=0
      for(let list of this.expense.expenseClaimBills)
        if(list.billAmount!=null&&list.billAmount!=undefined){
            total=total+(list.billAmount);
            this.expense.expenseClaimAmount=total;
        }
    }


    navigateToListPage(){
        this.router.navigateByUrl("/my-space/expense-claim/list");   
    }

   
    // categorySelected($event,i){
    //     this.expense.expenseClaimBills[i].subCategoryId=$event.item.id;
    //     this.expense.expenseClaimBills[i].subCategoryName=$event.item.subCategoryName;
    // }

    categorySelected($event, i){
        this.expense.expenseClaimBills[i].subCategoryId=$event.item.id;
        this.expense.expenseClaimBills[i].subCategoryName=$event.item.subCategoryName;
        this.expense.expenseClaimBills[i].categoryId=$event.item.categoryId;
        this.expense.expenseClaimBills[i].categoryName=$event.item.categoryName;
        this.expense.expenseClaimBills[i].isExpBillable=$event.item.isBillable;
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

        //Loop docsForCheckin
            // find the ecBillId
            // set metadata
            // push to docInfolist 
            let _docInfoList = [];
        this.docIdsForCheckin.forEach(docId  => {
            let ecBill = this.checkinExpense.expenseClaimBills.find(ec => ec.docId === docId );
            if(ecBill!=null  && ecBill.docId !=null){
                this.docsForCheckin.push(this.setMetadata(docId, this.checkinExpense.id, ecBill.id));
            }

        });

        
    }

    applyClaim(){
        this.checkBill();
        if(this.isbillable==true){
            this.expClaimService.apply(this.expense).subscribe(_exp=>{           
                this.checkinExpense=_exp.expenseClaim;
                this.prepareDocInfosForCheckin();
                this.commonService.checkInDocuments(this.docsForCheckin).subscribe(docRes=>{
                    this.saveMsg = {'type': 'success', 'text': "Expense claim applied successfully"}
                    this.router.navigate(['/my-space/expense-claim/detail',this.checkinExpense.id]);
                },error=>{
                    console.log(error);
                    this.saveMsg = {'type': 'danger', 'text': "Expense applied, however documents not uploaded. Please check with admin"};
                    this.router.navigate(['/my-space/expense-claim/detail',this.checkinExpense.id]);
                 });
            },e=>{
                this.saveMsg = {'type': 'danger', 'text': "Error in Expense service"};
            });
        }else{
            this.saveMsg = {'type': 'danger', 'text': "Please submit bill"};
            this.isbillable=true;
        }
       
    }

    checkBill(){
        this.expense.expenseClaimBills.forEach(bill=>{
            if(bill.isExpBillable=='Y' && bill.docId==null){
                this.isbillable=false;
            }
        })
    }

}