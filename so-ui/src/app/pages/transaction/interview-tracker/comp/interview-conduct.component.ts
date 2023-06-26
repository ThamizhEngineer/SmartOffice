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
    templateUrl: 'interview-conduct.component.html'
})

export class InterviewConductComponent implements OnInit {
    
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
            this.activedRouter.params.switchMap((params: Params) => this.service.getInterviewDetailsForInterviewer(params['id'])).
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

    // getInterviewDetailsForInterviewer(id){
    //     this.service.getInterviewDetailsForInterviewer(id).subscribe(x=>{
    //         this.interview=x;
    //     })
    // }
 
    getEmpId(id){
        console.log(id);
        this.empId=id;
    }
 
    roundComplete(action,intRoundId, rating) { 
        var interviewRound;
        this.interview.interviewRound.forEach(_round => {
            if(_round.id == intRoundId){
                interviewRound = _round;
            }
        });
        this.service.roundAction(action,intRoundId, interviewRound).subscribe(x => {
            // this.getInterviewDetailsForInterviewer(x.id); 
            // this.interview=x;
            this.route.navigateByUrl('/recruitment/interview');
        })
    }
}