import { Component, OnInit } from '@angular/core';
import { InterviewTrackerService } from '../interview-tracker.service';
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';
import { status_css } from '../../../vo/status';


@Component({
    selector: '',
    templateUrl: 'interview-tracker-list.component.html'
})

export class InterviewTrackerListComponent implements OnInit {
    
    user:User
    interviewTrackers:any=[];
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
            this.service.getInterviewTrackers().subscribe(x=>{            
                x.content.forEach(element => {
                    this.service.getApplicantById(element.applicantId).subscribe(x=>{
                        x.isviewApplicantDetails=true,x.isApplicantDetails=false;
                        element.applicant=x;
                    })
                    if(element.finalDecision==null){
                        this.interviewTrackers.push(element);
                    }else{
                        this.completedInterview.push(element);
                    }
                });
            });
        }else{
            this.service.getByInterviewer().subscribe(x=>{
                this.isHr=false
                x.forEach(element => {
                    this.service.getApplicantById(element.applicantId).subscribe(x=>{
                        x.isviewApplicantDetails=true,x.isApplicantDetails=false;
                        element.applicant=x;
                    });
                    if(element.finalDecision==null){
                        this.interviewTrackers.push(element);
                    }else{
                        this.completedInterview.push(element);
                    }
                });
                console.log(this.completedInterview);
                console.log(this.interviewTrackers);
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