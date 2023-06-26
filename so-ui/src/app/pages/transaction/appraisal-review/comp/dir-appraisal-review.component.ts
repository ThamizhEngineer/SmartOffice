import { Component, OnInit } from '@angular/core';
import { AppraisalReivewService } from '../appraisal-review.service';
import { AppraisalReview } from '../../vo/appraisal-review';

export const score={'1':'Has not met the requirements',
  '2':'Has met some but not all of the requirements',
  '3':'Has met the requirements',
  '4':'Has exceeded some of the requirements and met all others',
  '5':'Has exceeded all of the requirements'}


@Component({
    selector: '',
    templateUrl: 'dir-appraisal-review.component.html'
})

export class DirAppraisalReviewComponent implements OnInit {
    
    review:Array<AppraisalReview>;
    appraisalReview:Array<AppraisalReview>;
    view:string='pending';
    scoreCode:any=score;
    errorMsg:any;
    Alertmsg:any;
    
    constructor(
        private service:AppraisalReivewService
    ) { }

    ngOnInit() { 
        this.review = [new AppraisalReview]
        this.service.getAppraisalsReview().subscribe(x=>{
            x.forEach(element => {
             if(element.reviewCompStatus=='PENDING-DIR-APPROVAL'){
                 element.select=false;
             }else{
                element.select=true;
             }
             this.review.push(element);   
            });
            console.log(this.review);         
        })
    }

    changeView(view){
        this.view=view;
    }

    selectAllAppraisal(){
        this.appraisalReview = new Array<AppraisalReview>();
        this.review.forEach(element => {
            if(element.reviewCompStatus=='PENDING-DIR-APPROVAL'){
                element.select=true;
                this.appraisalReview.push(element);
            }
        });
        this.submit();
    }
    selectedAppraisal(){
        this.appraisalReview = new Array<AppraisalReview>();
        this.review.forEach(element => {
            if(element.reviewCompStatus=='PENDING-DIR-APPROVAL'&&element.select==true){                
                this.appraisalReview.push(element);
            }
        });
        this.submit();
    }

    get pendingApprisal(){    
        return this.review.filter(pen=>pen.reviewCompStatus=='PENDING-DIR-APPROVAL' )
     }

     get completeApprisal(){
        return this.review.filter(comp=>comp.reviewCompStatus=='APPROVED')        
     }

     submit(){
         this.service.updateAppraisalByAction(1,'dir-approve',this.appraisalReview).subscribe(x=>{
             this.ngOnInit();
         })
     }
}