import { Component,OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExitInterviewService } from '../exit-interview.service';
import { ExitInterview } from '../vo/exit-interview';
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';
import { status_css } from '../../../vo/status';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector: '',
    templateUrl: 'exit-interview-approve.component.html'
})

export class ExitInterviewApproveComponent implements OnInit {
    exitInterview:ExitInterview;
    exitInterviews:any=[]
    view:string;
    user:User;
    displayView:string;
    modalReference:any = null;
    action:string;
    statuses:any=status_css;

    @ViewChild('approve') approve: TemplateRef<any>;

    constructor(private router:Router,
        private activedRouter: ActivatedRoute,
        private userService:UserService,
        private modalService: NgbModal,
        private exitInterviewService:ExitInterviewService){

    }
    ngOnInit() {
        this.user = this.userService.getCurrentUser();
        this.exitInterview= new ExitInterview();
        console.log(this.user);
            this.activedRouter.params.subscribe(params=>{
            this.view=params.view;
            this.exitInterviewService.exitInterviewActionView(params.view).subscribe(x=>this.exitInterviews=x);
            this.processRecord();
            });                    
     }

     processRecord(){
         switch (this.view) {
             case 'acc2':
                 this.action='acc-clearance'
               this.displayView='Account Managers'
             break;
             case 'n1':
                this.action='n1-clearance'
                this.displayView='N+1 Manager'
             break;
             case 'hr2':
                this.action='hr-clearance'
                this.displayView='HR 2 Managers'              
             break;
             default:
                 break;
         }
     }


     openApprovalScreen(exitInterview:ExitInterview){
        this.exitInterview=exitInterview;       
        this.modalReference = this.modalService.open(this.approve, {size: 'lg'});
     }

     save(){
         this.exitInterviewService.updateExitInterview(this.exitInterview.id,this.action,this.exitInterview).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit();
         })
     }



}