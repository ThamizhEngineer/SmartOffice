import { Component, OnInit } from '@angular/core';
import { AppraisalReview } from '../../vo/appraisal-review';
import { AppraisalReivewService } from '../appraisal-review.service';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';


@Component({
    selector: '',
    templateUrl: 'emp-appraisal-review.component.html'
})

export class EmpAppraisalReviewComponent implements OnInit {
  
    review:Array<AppraisalReview>;
    appraisalReview:Array<AppraisalReview>;
    user:User;
    errorMsg:any;
    Alertmsg:any;
    view:string='start';
    pageSize :number = 10
	page :number = 1;

    constructor(
        private service:AppraisalReivewService,
        private _userService:UserService,
    ) { }

    ngOnInit() { 
        this.user = this._userService.getCurrentUser();
        this.review = new Array<AppraisalReview>();
        this.service.getAppraisalsReview().subscribe(x=>{
            x.forEach(element => {
              if(element.empId==this.user.employeeId){
                  this.review.push(element);
              }  
            });
        })        
    }

    get reviewCreated(){
        if(this.review){
            return this.review.filter(x=>x.reviewCompStatus=='CREATED')
        }else{
            return [];
        }
       
    }
    get submitReview(){
        if(this.review){
            return this.review.filter(x=>x.reviewCompStatus=='PENDING-SELF-APPRAISAL')
        }else{
            return [];
        } 
    }
    get completeReview(){
        if(this.review){
            return this.review.filter(x=>x.reviewCompStatus!='PENDING-SELF-APPRAISAL'&&x.reviewCompStatus!='CREATED')
        }else{
            return [];
        }
    }

    changeView(view){
        this.view=view;
    }

    submit(){
        this.appraisalReview = new Array<AppraisalReview>(); 
        this.review.forEach(element => {
            if(element.reviewCompStatus=='PENDING-SELF-APPRAISAL'){
                this.appraisalReview.push(element);
            }
        });
        this.service.updateAppraisalByAction(1,'submit',this.appraisalReview).subscribe(x=>{
            this.ngOnInit()
        },error=>{
            this.errorMsg=JSON.parse(error._body);            
            this.Alertmsg = { type: 'danger', text: this.errorMsg.message }  
        })
    }

    save(){
        this.appraisalReview = new Array<AppraisalReview>(); 
        this.review.forEach(element => {
            if(element.empComments!=null&&element.empAcheivedTarget!=null&&element.reviewCompStatus=='CREATED'){
                this.appraisalReview.push(element);
            }
        });
        this.service.updateAppraisalByAction(1,'start',this.appraisalReview).subscribe(x=>{
            this.ngOnInit()
        },error=>{
            this.errorMsg=JSON.parse(error._body);            
            this.Alertmsg = { type: 'danger', text: this.errorMsg.message }  
        })
    }

}