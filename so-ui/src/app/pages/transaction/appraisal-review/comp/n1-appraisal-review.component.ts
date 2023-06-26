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
    templateUrl: 'n1-appraisal-review.component.html'
})

export class N1AppraisalReviewComponent implements OnInit {
    
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
             if(element.reviewScoreCode==null){
                 element.reviewScoreCode=0;
             }
             this.review.push(element);   
            });
            console.log(this.review);
           //this.review=x;
        })
     }

     changeView(view){
        this.view=view;
    }

     get pendingApprisal(){    
        return this.review.filter(pen=>pen.isOverDue!='Y' && pen.reviewCompStatus=='PENDING-N1-COMPLETION'&&pen.reviewScoreCode==0 )
     }

     get submitedApprisal(){
        return this.review.filter(pen=>pen.isOverDue!='Y' && pen.reviewCompStatus=='PENDING-N1-COMPLETION'&&pen.reviewScoreCode!=0 )
     }

     get overDueApprisal(){        
       return this.review.filter(over=>over.isOverDue==='Y')
        
    }

    get completeApprisal(){
       return this.review.filter(comp=>comp.reviewScoreCode!=0&&comp.isOverDue!='Y' && comp.reviewCompStatus!='PENDING-N1-COMPLETION')        
    }

    n1Submit(){
        this.appraisalReview = new Array<AppraisalReview>();
        this.review.forEach(element => {
            if(element.reviewCompStatus=='PENDING-N1-COMPLETION'&&element.reviewScoreCode!=0){
                this.appraisalReview.push(element);
            }
        });
        this.service.updateAppraisalByAction(1,'n1-update',this.appraisalReview).subscribe(x=>{
            this.ngOnInit();
        },error=>{
            this.errorMsg=JSON.parse(error._body);            
            this.Alertmsg = { type: 'danger', text: this.errorMsg.message }  
        });
    }   

}