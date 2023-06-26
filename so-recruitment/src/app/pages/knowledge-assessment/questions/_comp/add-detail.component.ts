import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { Title }     from '@angular/platform-browser';
import { QuestionsService } from './../questions.service';
import { QuestionBank, QuestionBankOption } from '../vo/question-bank';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'question-add-detail',
  templateUrl: './add-detail.component.html'
})
export class QuestionsAddDetailComponent implements OnInit {
questionbank:QuestionBank;
questionbankOption:QuestionBankOption;
addScreen: boolean = false;
modalReference: any = null;
idParam:any;
msg:any;
	constructor(private titleService: Title, private _service: QuestionsService,private route: ActivatedRoute, private modalService: NgbModal) { }

	ngOnInit() {
		this.questionbank= new QuestionBank();
		this.questionbankOption = new QuestionBankOption();
		this.questionbank.questionBankOptions= new Array<QuestionBankOption>();
		if (this.route.params['_value']['_id'] === undefined) {
			this.addScreen = true;
		}

		if (this.addScreen) {
			this.questionbank.questionBankOptions = [new QuestionBankOption];
			
		}
		else{
			this.route.params.switchMap((par:Params) => this._service.getQuestionBankById(par['_id'])).subscribe(x => {
				this.questionbank = x;
				this.idParam=x.id;
				console.log(this.questionbank);
				
			});

		}
	}
	
	count = 1;
	rows = [1];
	existingQuestionRows=[1];
	existingQuestionCount = 1;
	
	addRows(){
		this.count++;
		this.rows.push(this.count);
	}
	delRow(item){
		let i = item - 1;
		this.rows.splice(i, 1);
	}

	addExistingQuestionRows(){
		this.existingQuestionCount++;
		let ts = new QuestionBankOption();
		this.questionbank.questionBankOptions.push(ts);
    }
    
	delExistingQuestionRow(i){
		this.questionbank.questionBankOptions.splice(i, 1);
	}
	
	open(content) {
        this.modalService.open(content).result.then((result) => {
        });
	}
	saveQuestionBank(){
		if(this.questionbank.id==null){

		
		this._service.addQuestionBank(this.questionbank).subscribe(x=>{
			this.msg = { type: 'success', text: "Created successfully" 
			
		}, error => {
				this.msg = { type: 'danger', text: "Error in Updation" }
				
		
		
	
			}
		
		});
	}else{
	
		this._service.updateQuestionBank(this.questionbank,this.idParam).subscribe(x=>{
			this.msg = { type: 'success', text: "Updated successfully" 
			
		}, error => {
				this.msg = { type: 'danger', text: "Error in Updation" }
				
		
		
	
			}
		
		});
	}
}
}
