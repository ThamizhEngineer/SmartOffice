import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { QuestionsService } from '../questions.service';


@Component({
  selector: 'question-add-list',
  templateUrl: './add-list.component.html'
})
export class QuestionsAddListComponent implements OnInit{
  questionBank:any;
    constructor(private router:Router, private _service: QuestionsService){

    }

    ngOnInit() {
      this._service.getQuestionBank().subscribe(x=>{
        this.questionBank=x;
        console.log(this.questionBank);
            });
    }
    navigateToDetailPage(){
        this.router.navigateByUrl("/knowledge-assessment/add/question-add-detail");   
       }

}