import { Component, OnInit } from '@angular/core';

import { Title }     from '@angular/platform-browser';
import { AddTestParticipantsService } from '../../../knowledge-assessment/add-test-participants/add-test-participants.service';

@Component({
  selector: 'take-test',
  templateUrl: './qa.component.html'
})
export class QuestionsQAComponent implements OnInit {

	questions: any;
	curQA: any;
	cur:number = -1;
	constructor(private titleService: Title) { }

	ngOnInit() {
	// 	this._service.getQuestions().subscribe(x=>{
	// 		this.questions = this.shuffleArray(x);
	// 		this.nextQA();
	// 	});
	// }
	
	// nextQA(){
	// 	this.cur++;
	// 	this.curQA = this.questions[this.cur];
	// }
	
	// prevQA(){
	// 	this.cur--;
	// 	this.curQA = this.questions[this.cur];
	// }
	
	// shuffleArray(array){
	// 	let m = array.length, t, i;
	// 	while (m) {
	// 		i = Math.floor(Math.random() * m--);

	// 		t = array[m];
	// 		array[m] = array[i];
	// 		array[i] = t;
	// 	}
	// 	return array;
	// }
	}
}
