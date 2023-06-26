import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { TestCategoryService } from '../test-category.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TestCategory } from '../../../vo/test-category';
import { Router } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
import { Observable } from 'rxjs/Observable';



@Component({
    selector: '',
    templateUrl: 'test-category.component.html'
})

export class TestCategoryComponent implements OnInit {
    
    @ViewChild('category') category: TemplateRef<any>;
    testCategorys:any=[];
    modalReference:any = null;
    testcategory:TestCategory;
    Msg:any;
    errorMsg:boolean=false;
    pageNumber = 1;
    pageSize = 10;
    errorMessage:any;
    saveMsg:any;
    constructor(
        private service:TestCategoryService,
        private modalService: NgbModal,
        private commonService: CommonService,
        private route:Router
    ) { }

    ngOnInit() {
        this.testcategory = new TestCategory();
        this.getAllTestCategory();   
     }

     getAllTestCategory(){
        this.service.getTestCategorys().subscribe(x=>{
            this.testCategorys=x;
        })
     }

     deleteCategory(id){
        this.service.deleteTestCategory(id).subscribe(x=>{
            
        })
     }
     pageChanged(pN: number): void {
        this.pageNumber = pN;
      }

     editCategory(id){
        this.errorMsg=false;
        this.modalReference = this.modalService.open(this.category, {size: 'lg'});
         this.service.getTestCategoryById(id).subscribe(x=>{
             this.testcategory=x;
         })
     }

     categoryView(){
         this.testcategory=new TestCategory();
		this.modalReference = this.modalService.open(this.category, {size: 'lg'});		
     }

     create(){
         this.service.addTestCategory(this.testcategory).subscribe(x=>{
            this.modalReference.close(); 
            this.getAllTestCategory();
            this.errorMsg=false;
            this.saveMsg = { type: 'success', text: 'Created Successfully' }
         },error=>{
            this.errorMsg=true;
            this.errorMessage = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMessage.message }
             }) 
     }
     update(){
         this.service.updateTestCategory(this.testcategory.id,this.testcategory).subscribe(x=>{
            this.modalReference.close(); 
            this.getAllTestCategory();
            this.errorMsg=false;
            this.saveMsg = { type: 'success', text: 'Updated Successfully' }
         },error=>{
            this.errorMsg=true;
            this.errorMessage = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMessage.message }
             }) 
     }
     delete(id){
         this.service.deleteTestCategory(id).subscribe(x=>{
             this.getAllTestCategory();
             this.saveMsg = { type: 'success', text: 'Deleted Successfully' }
            },
             error => {                       
            this.errorMessage = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMessage.message }
             }) 
     }

     navigateToQuestionsPage(id){
        this.route.navigateByUrl("/recruitment/test-category/questions/"+id);
     }

     upload(event,docTypeCode:any){
        let fileList: FileList = event.target.files;
        console.log(fileList)
        if (fileList.length > 0) {  
            let files: File = fileList[0];          
			 this.commonService.uploadQues(files, docTypeCode).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {
                        // this.testCategorys.resumeDocId = data[0].docId;    
                        this.ngOnInit();                                        
                    },
                    error => {                       
                        this.errorMessage = JSON.parse(error._body);
                        this.saveMsg = { type: 'danger', text: this.errorMessage.message }
                         })                 
        }
      }
}