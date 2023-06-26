import { Component, OnInit } from '@angular/core';

import { Title }     from '@angular/platform-browser';
import { QuestionsService } from './../questions.service';
import { UserService } from '../../../../auth/_services';
@Component({
  selector: 'take-test',
  templateUrl: './qa.component.html'
})
export class QuestionsQAComponent implements OnInit {

	questions: any;
	curQA: any;
	cur:number = -1;
	tests:any=[];
	test:any=[];
	users:any=[];
	constructor(private titleService: Title, private _service: QuestionsService,private _userService:UserService) { }

	ngOnInit() {
		this._service.getQuestions().subscribe(x=>{
			this.questions = this.shuffleArray(x);
			this.nextQA();
		});
		this._service.getTests().subscribe(x=>{
		this.tests.push(x);
		
		let currentUser=this._userService.getCurrentUser();
		
		this.users.push(currentUser);
	
		this.users.forEach(x => {
			this.tests.forEach(f=>{
			this.test.push(f);
			console.log(this.test)
			})
			
		});
		})
		
		
	}
	
	nextQA(){
		this.cur++;
		this.curQA = this.questions[this.cur];
	}
	
	prevQA(){
		this.cur--;
		this.curQA = this.questions[this.cur];
	}
	
	shuffleArray(array){
		let m = array.length, t, i;
		while (m) {
			i = Math.floor(Math.random() * m--);

			t = array[m];
			array[m] = array[i];
			array[i] = t;
		}
		return array;
	}
}
