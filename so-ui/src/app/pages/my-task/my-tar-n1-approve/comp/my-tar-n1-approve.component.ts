import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {User} from '../../../../auth/_models/user';
import {UserService} from '../../../../auth/_services/user.service';
import { MyTar } from '../../../vo/my-tar';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MyTarRequestService } from '../../my-tar.service';


@Component({
    selector: 'approve',
    templateUrl: 'my-tar-n1-approve.component.html'
})

export class N1ApproveComponent implements OnInit {

    @ViewChild('Approve') Approve: TemplateRef<any>;
    @ViewChild('Reject') Reject: TemplateRef<any>;

    advRequest:MyTar
    list:any=[];
    user:User;
    userName:any;
    id:any;
    flowType:any;
    modalReference:any = null;
    saveMsg:any;
    total:number;
    n1TarAprrove:boolean;
   
    travelStarus:any;
    adjMsg:any;
   
    
     constructor(
         private activatedRoute: ActivatedRoute,
         private router:Router,
         private modalService: NgbModal,
         private service:MyTarRequestService
     ) { }
 
     ngOnInit() {
         this.advRequest = new MyTar();

         this.activatedRoute.queryParams.subscribe(params=>{
             this.service.getTarById(params.id).subscribe(x=>{
                this.advRequest=x;
                if(this.advRequest.isAddlTravelInvolved=='N'){
                    this.travelStarus='No';
                   }else{
                       this.travelStarus='Yes';
                   }
                   if(this.advRequest.n1AdjustmentAmount == null){
                    this.advRequest.n1AdjustmentAmount = 0;
                }
                this.advRequest.n1TarAprrove=false;
                this.calculateExpenseTotal();   
            })
          
         })
 
        
         
      }

      isTravel(){
        if(this.advRequest.n1TarAprrove==true){
            this.advRequest.n1TarAprrove=false;
        }else{
            this.advRequest.n1TarAprrove=true;
        }
        console.log(this.advRequest.n1TarAprrove);
        this.calculateExpenseTotal(); 
      }

      calculateExpenseTotal(){
          let a:number,b:number,c:number;  
         console.log(this.advRequest.n1TarAprrove);
             
          if(this.advRequest.n1TarAprrove==false ){

            b = Number(this.advRequest.n1AdjustmentAmount);
            a = Number(this.advRequest.stayExpenseAmount);
            c = Number(a) + Number(b);
            this.total=c;
          }


          if(this.advRequest.n1TarAprrove==true){
            b = Number(this.advRequest.n1AdjustmentAmount);
            a = Number(this.advRequest.travelExpenseAmount)+Number(this.advRequest.stayExpenseAmount);
            c = Number(a) + Number(b);
            this.total=c;
          }
      this.tarAdjCheck();
    }


    tarAdjCheck(){

        this.adjMsg= {'type': 'danger', 'text': "Please Enter the Adjustment Remarks"};        
        
    }

    tarApprove(){
        if(this.advRequest.n1TarAprrove==true){
            this.advRequest.n1IsAddlTravelInvolved='Y'
        }else{
            this.advRequest.n1IsAddlTravelInvolved='N'
        }
this.service.UpdateMyTarReq(this.advRequest.id,"n1-approve",this.advRequest).subscribe(x=>{
    this.router.navigateByUrl("/my-task/my-tar-n1/list");
    this.modalReference.close();
    this.saveMsg = {'type': 'success', 'text': "TAR Approved Successfully"};
},error=>{
    this.saveMsg = {'type': 'danger', 'text': "Server Error"};
});
    }

    tarRejected(){
        this.advRequest.n1IsAddlTravelInvolved=(this.advRequest.n1IsAddlTravelInvolved?"Y":"N");
        this.service.UpdateMyTarReq(this.advRequest.id,"n1-reject",this.advRequest).subscribe(x=>{ 
            this.router.navigateByUrl("/my-task/my-tar-n1/list");
            this.modalReference.close();
            this.saveMsg = {'type': 'danger', 'text': "TAR Rejected"};
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
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
 
             this.router.navigateByUrl("/my-task/my-tar-n1/list");          
     }
 }