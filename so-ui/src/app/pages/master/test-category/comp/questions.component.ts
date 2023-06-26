import { Component, OnInit } from '@angular/core';
import { Route,ActivatedRoute,Params,Router } from '@angular/router'
import { TestCategoryService } from '../test-category.service';
import { Question } from '../../../vo/question';
import { TestCategory } from '../../../vo/test-category';
import { status_css } from '../../../vo/status';

@Component({
    selector: '',
    templateUrl: 'questions.component.html'
})

export class QuestionComponent implements OnInit {
   
    questions:[Question];
    category:TestCategory;
    level:string;
    isAdded:boolean=false;
    status:any=status_css;
    pageNumber = 1;
    pageSize = 10;
    buttonDisabled: boolean=false;
    errorMsg:any;
    saveMsg:any;

    constructor(
        private activedRouter:ActivatedRoute,
        private service:TestCategoryService,
        private route:Router
    ) { }

    ngOnInit() { 
        this.questions = [new Question]
        this.category = new TestCategory();
        if (this.activedRouter.params['_value']['id']){
            this.activedRouter.params.switchMap((params: Params)=> this.service.getTestCategoryById(params['id'])).subscribe(x=>{
                this.category=x;
                this.level='EASY'
                this.getQuestionsByCategory(this.category.id,this.level);
            })            
        }
    }

    getQuestionsByCategory(id,level){
         this.questions = [new Question];
         this.buttonDisabled=false;
         if(level=='ALL'){
            this.service.getQuestionsByCategoryId(id).subscribe(x=>{
                this.questions=x;
            })
         }else{
            this.service.getQuestions(id,level).subscribe(x=>{
                this.questions=x;
                if(this.questions.length<1){
                    this.questions.push(new Question);
                }
                console.log(this.questions);
            })
         }
            
        
       
    }

    pageChanged(pN: number): void {
        this.pageNumber = pN;
      }

    addAndUpdate(){
        this.questions.forEach(element => {
            if(this.level!='ALL'){
            element.difficultyLevel=this.level;
            }
            element.testCategoryId=this.category.id.toString();
        });
        this.service.addAndUpdateQuestions(this.questions).subscribe(x=>{            
            this.isAdded=false;
            this.buttonDisabled=true;
            this.ngOnInit();
            this.saveMsg = { type: 'success', text: 'Saved Successfully' }
        },
        error => {                       
       this.errorMsg = JSON.parse(error._body);
       this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        }) 
    }

    addQuestionRows(){
        this.isAdded=true;
        this.buttonDisabled=false;
        let question = new Question()
        this.questions.push(question);
    }

    delQuestionRow(id,i){
        this.isAdded=false;
        if(id!=null){
            this.service.deleteQuestionById(id).subscribe(x=>{
                this.questions.splice(i,1);
                this.saveMsg = { type: 'success', text: 'Deleted Successfully' }
                this.isAdded=false;
            },
            error => {                       
           this.errorMsg = JSON.parse(error._body);
           this.saveMsg = { type: 'danger', text: this.errorMsg.message }
            }) 
        }else{
            this.questions.splice(i,1);
        }     
    }

    deleteAll(){
        this.service.deleteQuestionByCategoryId(this.category.id).subscribe(x=>{
            this.saveMsg = { type: 'success', text: 'Deleted Successfully' }
            this.route.navigateByUrl("/recruitment/test-category");
        },
         error => {                       
        this.errorMsg = JSON.parse(error._body);
        this.saveMsg = { type: 'danger', text: this.errorMsg.message }
         })  
    }
}