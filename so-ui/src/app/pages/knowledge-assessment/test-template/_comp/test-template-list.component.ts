import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import{TestTemplateService} from '../test-template.service';
import { TestPreparation } from '../vo/TestPreparation';
import { TestTemplate } from '../vo/TestTemplate';

@Component({
  selector: 'test-template-list',
  templateUrl: './test-template-list.component.html'
})
export class TestTemplateListComponent implements OnInit {
testPrep:TestPreparation
// testTemp:TestTemplate;
testTemp:any=[];
saveMsg:any;
pageSize :number = 10
page :number = 1;
	constructor(private router:Router,private testTemplateService:TestTemplateService) { }

	ngOnInit() {
           this.getData(); 
        }
    
    getData(){
        this.testTemplateService.getTestTemplates().subscribe(x=>{
            this.testTemp=x;
            console.log(this.testTemp);
        });
    }
	navigateToDetailPage(){
        this.router.navigateByUrl("/knowledge-assessment/test-template/test-template-detail");   
       }
    deleteTemplate(id:any){
           this.testTemplateService.deleteTemplateById(id).subscribe(x=>{
            console.log(x);
            this.saveMsg={'type':'success','text':"Deleted Successfully"};  
            this.getData();             
           },error=>{
               this.saveMsg={'type':'danger','text':"Server Error"};
           });   
        
   }
}



// commented section below
// 		this.testTemplateService.getAllTestPreprations().subscribe(x=>{
// this.testPrep=x;
// console.log(this.testPrep);
//     });
//    deleteRow(id:any){
    //        this.testTemplateService.deleteTestPrep(id).subscribe(x=>{
    //         console.log(x);
    //         this.saveMsg = {'type': 'success', 'text': "Client Purchase Order Deleted Successfully"};
           
    //     },error=>{
    //         this.saveMsg = {'type': 'danger', 'text': "Server Error"};
    //     });
           
    //    }