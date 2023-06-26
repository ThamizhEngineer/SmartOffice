import { Component, OnInit } from '@angular/core';
import { InterviewTrackerService } from '../interview-tracker.service';
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';
import { status_css } from '../../../vo/status';
import { Observable } from 'rxjs/Rx';

@Component({
    selector: '',
    templateUrl: 'interview-round-list.component.html'
})

export class InterviewRoundListComponent implements OnInit {
    
    user:User
    interviewTrackers:any=[];
    interviewRounds:any=[];
    completedRounds:any=[];
    isHr:boolean;
    statuses:any=status_css;
    binding:string='true';
    pageNumber = 1;
    pageSize = 10;
    completedInterview:any=[];
    page :number = 1;



    constructor(
        private service:InterviewTrackerService,
        private userService:UserService
    ) { }

    ngOnInit() { 

        this.user = this.userService.getCurrentUser();
        console.log(this.user.employeeId);
        this.user.userGroupMappings.forEach(element => {
            if(element.isHrL1=='Y'||element.isHrL2=='Y'){              
                this.isHr=true;
            }  
        });
        if(this.isHr==true){            
            this.service.getAllInterviewRounds().subscribe(x=>{ 
                x.forEach(element => {
                    this.service.getApplicantById(element.applicantId).subscribe(x=>{
                        x.isviewApplicantDetails=true,x.isApplicantDetails=false;
                        element.applicant=x;
                    })
                    if(element.decision==null || element.decision=="PENDING"){
                        this.interviewRounds.push(element);
                    }else{
                        this.completedRounds.push(element);
                    }
                });
            });
        }else{
            this.service.getInterviewRoundsByInterviewer().subscribe(x=>{ 
                x.forEach(element => {
                    this.service.getApplicantById(element.applicantId).subscribe(x=>{
                        x.isviewApplicantDetails=true,x.isApplicantDetails=false;
                        element.applicant=x;
                    })
                    if(element.decision==null || element.decision=="PENDING"){
                        this.interviewRounds.push(element);
                    }else{
                        this.completedRounds.push(element);
                    }
                });
            });
        }
    }  

    pageChanged(pN: number): void {
        this.pageNumber = pN;
      }

      changeActive(id:any){ 
        console.log(id);
        this.binding=id;
       }

}