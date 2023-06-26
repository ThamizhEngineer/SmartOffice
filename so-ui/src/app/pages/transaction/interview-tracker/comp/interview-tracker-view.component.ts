import { Component, OnInit } from '@angular/core';
import { Interview, InterviewRound } from '../../../vo/interview';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { InterviewTrackerService } from '../interview-tracker.service';
import { status_css } from '../../../vo/status';
import { UserService } from '../../../../auth/_services/user.service';
import { User } from '../../../../auth/_models/user';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { IncidentApplicant } from '../../../vo/incident'


@Component({
    selector: '',
    templateUrl: 'interview-tracker-view.component.html'
})

export class InterviewTrackerViewComponent implements OnInit {

    interview: Interview;
    interviewRound: InterviewRound;
    user: User
    statuses: any = status_css;
    applicant:IncidentApplicant;
    form: FormGroup;

	round:FormControl;
	rating:FormControl;
	remarks:FormControl;

    constructor(
        private activedRouter: ActivatedRoute,
        private service: InterviewTrackerService,
        private userService: UserService,
        private route: Router
    ) { }

    ngOnInit() {

        this.user = new User();
        this.applicant= new IncidentApplicant();
        this.user = this.userService.getCurrentUser();
        this.interview = new Interview();
        this.interviewRound = new InterviewRound();
        
        this.interview.interviewRound = new Array<InterviewRound>();
        if (this.activedRouter.params['_value']['id']) {
            this.activedRouter.params.switchMap((params: Params) => this.service.InterviewTrackerId(params['id'])).
                subscribe(x => {
                    this.interview = x;
                    this.interviewRound.dummyDate = new Date().toJSON();
                    this.getApplicant();
                    console.log(this.interview);
                });
        }

        this.validate();
		this.createForm();

    }

    getApplicant(){
        this.service.getApplicantById(this.interview.applicantId).subscribe(x=>{
            x.isApplicantDetails=false,x.isviewApplicantDetails=true;
            this.applicant=x;
        }) 
    }

    validate(){
		this.round = new FormControl('', [Validators.required]);
		this.rating = new FormControl('', [Validators.required]);
		this.remarks = new FormControl('', [Validators.required]);
	}

     createForm(){
		this.form = new FormGroup({
			round:this.round,
			rating:this.rating,
			remarks:this.remarks
		});		
	}


    roundComplete(action) {
        this.interviewRound.interviewEmpId=this.user.employeeId;
        this.interviewRound.decision=action;
        this.interview.interviewRound.push(this.interviewRound);
        console.log(this.interview);
        this.service.roundAction(this.interview.id,action, this.interview).subscribe(x => {
            this.route.navigateByUrl('/recruitment/interview');
        })
    }
}