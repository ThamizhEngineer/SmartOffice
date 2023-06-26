import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Interview,InterviewRound } from '../../../vo/interview';
import { ActivatedRoute,Params,Router } from '@angular/router';
import { InterviewTrackerService } from '../interview-tracker.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { status_css } from '../../../vo/status';
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';
import { IncidentApplicant } from '../../../vo/incident';


@Component({
    selector: '',
    templateUrl: 'interview-tracker-hr-view.component.html'
})

export class InterviewTrackerHrViewComponent implements OnInit {
    
    @ViewChild('open') open: TemplateRef<any>;
    interview:Interview;
    user:User
    interviewRound:[InterviewRound]
    statuses:any=status_css;
    modalReference:any = null;
    employees:any=[];
    applicant:IncidentApplicant;
    empId:any;
    
    constructor(
        private activedRouter:ActivatedRoute,
        private service:InterviewTrackerService,
        private userService:UserService,
        private modalService: NgbModal,
        private route: Router
    ) { }

    ngOnInit() {

        this.user = new User();
        this.applicant = new IncidentApplicant();
        this.user = this.userService.getCurrentUser();
        this.interview = new Interview();
        this.interviewRound = [new InterviewRound]
        this.interview.interviewRound =new Array<InterviewRound>();
        this.interviewRound[0].dummyDate=new Date().toJSON();
        if (this.activedRouter.params['_value']['id']){
            this.activedRouter.params.switchMap((params: Params) => this.service.InterviewTrackerId(params['id'])).
            subscribe(x=>{               
                 this.interview=x;
                 console.log(this.interview);  
                 this.service.getApplicantById(this.interview.applicantId).subscribe(x=>{
                    x.isApplicantDetails=false,x.isviewApplicantDetails=true;
                    this.applicant=x;
                })              
                this.getEmployees();
            });   
        }

       
        
     }

   
    getEmployees(){
        this.service.getEmployees().subscribe(x=>{
            x.forEach(element => {
                if(this.interview.secondInterviewerId==element.id||this.interview.thirdInterviewerId==element.id||this.interview.firstInterviewerId==element.id){
                    this.employees.push(element);
                }
            });
            console.log(this.employees);
        })
    }

    getInterview(id){
        this.service.InterviewTrackerId(id).subscribe(x=>{
            this.interview=x;
        })
    }

    addInterviewTrackerCards(){
          let interviewRound = new InterviewRound();
          interviewRound.dummyDate = new Date().toJSON();
		  this.interviewRound.push(interviewRound);
    }
    
    delInterviewTrackerCard(i){
        console.log(i);
      this.interviewRound.splice(i,1);
    }
    getEmpId(id){
        console.log(id);
        this.empId=id;
    }

    finalDec(){
        this.modalReference = this.modalService.open(this.open, {size: 'lg'});	
    }

    finalDecision(action){
        this.interview.finalDecision=action
        this.service.finalDecision(this.interview.id,action, this.interview).subscribe(x=>{
            this.route.navigateByUrl('/recruitment/interview');
        })
    }

    // conductAgain

    conductAgain(intRoundId) {
        var interviewRound;
        this.interview.interviewRound.forEach(_round => {
            if(_round.id == intRoundId){
                interviewRound = _round;
            }
        });
        this.service.roundAction("PENDING",intRoundId, interviewRound).subscribe(x => {
            // this.getInterviewDetailsForInterviewer(x.id); 
            this.interview=x;
        });
    }
}