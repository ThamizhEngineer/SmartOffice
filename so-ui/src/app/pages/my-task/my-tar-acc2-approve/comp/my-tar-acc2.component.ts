import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { MyTar } from '../../../vo/my-tar';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MyTarRequestService } from '../../my-tar.service';
import {User} from '../../../../auth/_models/user';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'approve',
    templateUrl: 'my-tar-acc2.component.html'
})

export class Acc2ApproveComponent implements OnInit {

    @ViewChild('Approve') Approve: TemplateRef<any>;
    @ViewChild('Reject') Reject: TemplateRef<any>;

    advRequest:MyTar
    list:any=[];
    user:User;
    userName:any;
    id:any;
    flowType:any;
    modalReference:any = null;
 
    total:number;
    total2:number;
   
    travelStarus:any;
    adjMsg:any;
    n1TravelApprove:any;
   
    
     constructor(
         private activatedRoute: ActivatedRoute,
         private modalService: NgbModal,
         private service:MyTarRequestService,
         private router:Router
     ) { }
 
     ngOnInit() {
         this.advRequest = new MyTar();
         this.activatedRoute.queryParams
         .subscribe(params=>{
            this.service.getTarById(params.id).subscribe(x=>{
                this.advRequest=x;

             if(this.advRequest.isAddlTravelInvolved=='N'){
              this.travelStarus='No';
             }else{
                 this.travelStarus='Yes';
             }
             if(this.advRequest.n1IsAddlTravelInvolved=='Y'){
                this.n1TravelApprove='Yes';
               }else{
                   this.n1TravelApprove='No';
               }
               if(this.advRequest.tarStatus == 'ACC2-APPROVAL-PENDING'){
               this.advRequest.accEmpAdjAmount=Number(this.advRequest.n1AdjustmentAmount);
               }
               this.calculateExpenseTotal();
            })
         })

        
         
      }

      calculateExpenseTotal(){
          let a:number,b:number,c:number;
          if(this.advRequest.n1IsAddlTravelInvolved=='Y'){
            b = Number(this.advRequest.n1AdjustmentAmount);
    a = Number(this.advRequest.travelExpenseAmount)+Number(this.advRequest.stayExpenseAmount);
    
    c = Number(a)+Number(b);
    this.total2=a;
this.total=c;
this.advRequest.netAdvanceAmount=Number(a)+Number(this.advRequest.accEmpAdjAmount);
}

if(this.advRequest.n1IsAddlTravelInvolved=='N'){
    b = Number(this.advRequest.n1AdjustmentAmount);
a = Number(this.advRequest.stayExpenseAmount);

c = Number(a)+Number(b);
this.total2=a;
this.total=c;
this.advRequest.netAdvanceAmount=Number(a)+Number(this.advRequest.accEmpAdjAmount);
}
this.tarAdjCheck();
    }


    tarAdjCheck(){

        if(Number(this.advRequest.n1AdjustmentAmount)!=this.advRequest.accEmpAdjAmount){
        this.adjMsg= {'type': 'danger', 'text': "Please Enter the Adjustment Remarks"};
        }
        
    }


    acc2Approve(){
        this.service.UpdateMyTarReq(this.advRequest.id,"acc2-approve",this.advRequest).subscribe(x=>{
            this.router.navigateByUrl("/my-task/my-tar-acc2-approve/list");
            this.modalReference.close();
        })
            }
        
            acc2Rejected(){
                this.service.UpdateMyTarReq(this.advRequest.id,"acc2-reject",this.advRequest).subscribe(x=>{ 
                    this.router.navigateByUrl("/my-task/my-tar-acc2-approve/list");
                    this.modalReference.close();
                })
            }
        

            back(){
                this.modalReference.close();  
            }
            
                  approve(){
                      console.log(this.advRequest);
                    this.modalReference = this.modalService.open(this.Approve, {size: 'lg'});	
                  }
        
                  reject(){
                    this.modalReference = this.modalService.open(this.Reject, {size: 'lg'});	
                  }     
 
      navigateToListPage(){
             this.router.navigateByUrl("/my-task/my-tar-acc2-approve/list");   
      }     

 }