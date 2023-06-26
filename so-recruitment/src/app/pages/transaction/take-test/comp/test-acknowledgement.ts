import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AddTestParticipantsService } from '../../../knowledge-assessment/add-test-participants/add-test-participants.service';
import { UserService } from '../../../../auth/_services';
import { Test, TestQuestion, TestQuestionOption } from '../../../knowledge-assessment/create-test/vo/Test';
import { IncidentTest,IncidentTestQuestion } from '../vo/incident-test';
import { IncidentTestService } from '../incident-test.service';
import { count } from 'rxjs/operator/count';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector: 'test-acknowledgement',
    templateUrl: 'test-acknowledgement.html'
})

export class TestAcknowledgementComponent implements OnInit {
  @ViewChild('cdetail') cdetail:TemplateRef<any>;
  @ViewChild('apply') apply: TemplateRef<any>;


  tt:any;
    tests: any;
    curQA: TestQuestion;
    questions: any
    option1: any;
    allOptions: any;
    option2: any;
    option3: any;
    option4: any;
    answer: any;
    selectedOption: any;
    incidentTest:IncidentTest;
    option={};
    userPickedList: Array<TestQuestion>;
    userPick: any;
    cur: number = 0;
    id: any;
    testStatus:any;
    timeLeft: number;
    // minutes:string;
    seconds: number=60;
    interval;
    intervals;
    dummy:any;
    testCompleted:boolean=false;
    testQue:IncidentTestQuestion;
    options: Array<TestQuestionOption>;
    auesOrder:any;totalQues:any;
    modalReference:any=null;
    modalData: any;
    constructor(private router: Router,
         private route: ActivatedRoute, 
         private testService:IncidentTestService,
         private addTestParticipantsService: AddTestParticipantsService,
          private _userService: UserService,
          private modalService:NgbModal) {

    }
    ngOnInit() {
        this.incidentTest = new IncidentTest()
        this.testQue = new IncidentTestQuestion();
        this.curQA = new TestQuestion();
        this.curQA.testQuestionOptions = new Array<TestQuestionOption>();        

        this.userPickedList = new Array<TestQuestion>()
        this.options = new Array<TestQuestionOption>()
        this.route.params.subscribe(params=>{

          this.testService.getTestById(params._id).subscribe(inc=>{
            this.incidentTest=inc;
          })

      this.testService.testType(params._id,params._value,this.dummy).subscribe(x => {
            this.testQue=x;
            console.log(this.testQue)
            console.log(this.testQue.tincidentTestId)

            let duration = Number(this.testQue.duration)-Number(this.testQue.remainingTime)
            this.timeLeft = duration;
            this.startTimer();
            })          
          })
    }
      
    startTimer() {      
        this.interval = setInterval(() => {
          if(this.timeLeft > 0) {
            this.timeLeft--;          
            if(this.timeLeft==0){          
                this.submitAnswers();        
            }
          } else {  
            this.submitAnswers();
          }
        },(60) * 1000)
    
        this.intervals  = setInterval(() => {
            if(this.seconds > 0) {
              this.seconds--;          
              if(this.seconds==0){          
                this.seconds=60;        
              }
            } else {  
              this.seconds=60;
            }
          },1000)
    }

  
    userAnswered(ans) {
      this.answer=ans;
      switch(this.answer){
        case 'option1':
          if(this.option1==false){
            this.answer=null;
          }
          this.option2=false;
          this.option3=false;
          this.option4=false;
        break;
        case 'option2':
          if(this.option2==false){
            this.answer=null;
          }
          this.option1=false;
          this.option3=false;
          this.option4=false;
        break;
        case 'option3':
          if(this.option3==false){
            this.answer=null;
          }
          this.option1=false;
          this.option2=false;
          this.option4=false;
        break;
        case 'option4':
          if(this.option4==false){
            this.answer=null;
          }
          this.option1=false;
          this.option2=false;
          this.option3=false;
        break;
      }
      this.testQue.userPicked=this.answer;                                
    }
       
    submitAns(){
      if(this.answer!=null){
        this.testService.answer(this.testQue.id,this.testQue.questionOrder,this.testQue.userPicked,this.dummy).subscribe(x=>{
          this.answer=null;
        })
      }  
    }
    nextQA() {    
      this.submitAns();                
      this.testService.nextQuestion(this.testQue.tincidentTestId,this.testQue.questionOrder).subscribe(x=>{
        this.cur++;
          this.testQue=x;          
          switch(this.testQue.userPicked){
            case 'option1':
              this.option1=true;
              this.option2=null;
              this.option3=null;
              this.option4=null;
            break;
            case 'option2':
              this.option1=null;
              this.option2=true;
              this.option3=null;
              this.option4=null;
            break;
            case 'option3':
              this.option1=null;
              this.option2=null;
              this.option3=true;
              this.option4=null;
            break;
            case 'option4':
              this.option1=null;
              this.option2=null;
              this.option3=null;
              this.option4=true;
            break;
            default:
              this.option1=null;
              this.option2=null;
              this.option3=null;
              this.option4=null;
              this.answer=null;
            break;
          }          
      },error=>{
        this.testCompleted=true;
      })
     
    }
    prevQA() {    
      this.submitAns(); 
        this.testService.previousQuestion(this.testQue.tincidentTestId,this.testQue.questionOrder).subscribe(x=>{
            this.cur--;
            this.testCompleted=false;          
            this.testQue=x;            
            switch(this.testQue.userPicked){
              case 'option1':
                this.option1=true;
                this.option2=null;
                this.option3=null;
                this.option4=null;
              break;
              case 'option2':
                this.option1=null;
                this.option2=true;
                this.option3=null;
                this.option4=null;
              break;
              case 'option3':
                this.option1=null;
                this.option2=null;
                this.option3=true;
                this.option4=null;
              break;
              case 'option4':
                this.option1=null;
                this.option2=null;
                this.option3=null;
                this.option4=true;
              break;
              default:
                this.option1=null;
                this.option2=null;
                this.option3=null;
                this.option4=null;
                this.answer=null;
              break;
            }            
        })
       
    }
    shuffleArray(array) {
        let m = array.length, t, i;
        while (m) {
            i = Math.floor(Math.random() * m--);

            t = array[m];
            array[m] = array[i];
            array[i] = t;
        }
        return array;
    }

    submitAnswers() {
        this.testService.submitTest(this.testQue.tincidentTestId,this.dummy).subscribe(x=>{  
            this.modalReference.close();              
            if(this.incidentTest.showScore=='Y'){
              this.router.navigateByUrl('/take-test/test-mark/'+this.incidentTest.id);
            }else{
              this.navigateToResulltPage();
            }
        })
    }
    navigateToResulltPage(){
      this.router.navigateByUrl('/take-test/take-test-list');
    }
    create(){      
  this.modalData = false;
      this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
  } 
  application(){
    this.modalReference.close(this.apply);
this.modalReference = this.modalService.open(this.apply, {size: 'sm'});		
}

}
