import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ExpenseCategoryService } from '../expense-category.service';
import { ExpenseCategory } from '../vo/expense-category';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector: 'expense',
    templateUrl: 'expense-category.component.html'
})

export class ExpenseCategoryComponent implements OnInit {
    @ViewChild('cdetail') cdetail:TemplateRef<any>;
    expenseSubCategory:ExpenseCategory;
    rows: any=[];
    saveMsg: { 'type': string; 'text': string; };
    category: any=[];
    categoryName: any;
    subCategoryName: any;
    adSearch: any=[];
    page :number = 1;
    pageSize :number = 10;
    constructor(private router: Router, private service: ExpenseCategoryService, private modalService:NgbModal) { }

    ngOnInit() {
        this.expenseSubCategory=new ExpenseCategory();
        this.getData();
        this.getExpenseCategory();
     }

    getData(){
        this.service.getExpenseSubCategory().subscribe(x=>{
            this.rows=x;
            console.log(x);
        });
    }

    getExpenseCategory(){
        this.service.getCategory().subscribe(x=>{
            this.category=x;
            console.log(this.category)
        })
    }

    modalReference:any=null;
    modalData: any;

    create(){
        this.expenseSubCategory=new ExpenseCategory();
        this.modalData = new ExpenseCategory();
        this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
    } 

    show(id:string){
        this.service.getExpenseSubCategoryId(id).subscribe(x=>{
            this.expenseSubCategory=x;
            this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});

        });
    }

    save(){
     
        if(this.expenseSubCategory.id){
            
                this.service.updateExpenseSubCategory(this.expenseSubCategory, this.expenseSubCategory.id).subscribe(x => { 
                console.log(this.expenseSubCategory)
                console.log(x)
                this.saveMsg = { 'type': 'success', 'text': "Updated Successfully" };
                this.getData();
                this.modalReference.close();

            } );             
         }
        
         else{
            this.service.addExpenseSubCategory(this.expenseSubCategory).subscribe(x =>{
            this.saveMsg = { 'type': 'success', 'text': "Created Successfully" };
            console.log(this.expenseSubCategory)
            console.log(x)
            this.getData();
            this.modalReference.close();

        
        });
        }
    }

    delete(id:string){
        this.service.deleteExpenseSubCategory(id).subscribe(x=>{
            this.saveMsg={'type':'success','text':"Deleted Successfully"};
            this.getData();
        },error=>{
            this.saveMsg={'type':'danger','text':"Server Error"};
        });
    }

    search(){
        this.service.advanceSearch(this.categoryName, this.subCategoryName).subscribe(x=>{
          console.log(x)
        this.rows=x;   
            console.log(this.rows)
        })
     }

     reset(){
        this.categoryName=null; this.subCategoryName=null;
        this.search();
     }
}