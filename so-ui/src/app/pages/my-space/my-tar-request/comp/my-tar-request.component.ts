import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { MyTar } from '../../../vo/my-tar';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {User} from '../../../../auth/_models/user';
import {UserService} from '../../../../auth/_services/user.service';
import { MyTarRequestService } from '../my-tar-request.service';

@Component({
    selector: 'edit',
    templateUrl: 'my-tar-request.component.html'
})

export class MyTarRequestComponent implements OnInit {

    @ViewChild('vdetail') vdetail: TemplateRef<any>;

    myTar:MyTar;
    list:any=[];
    user:User;
    userName:any;
    id:any;
    dayRange:any=[];
    jobCodes:any=[];
    costCode:any=[];
    isTravel:any;
    empId:any;
    date:Date;
    modalReference:any = null;
    msg:any;

  
      
     constructor(
         private activatedRoute: ActivatedRoute,
         private userService:UserService,
         private router:Router,
         private modalService: NgbModal,
         private service:MyTarRequestService
     ) { }
 
     ngOnInit() {
         this.date = new Date()
      
         this.myTar = new MyTar();
         this.user = this.userService.getCurrentUser();
        this.userName=this.user.firstName;
        this.empId=this.user.employeeId;
        console.log(this.userName);

         this.service.getDayRange().subscribe(x=>{
            this.dayRange=x;
        })
        this.service.getCostCode().subscribe(cost=>{
            this.costCode=cost.content;
        })
        this.service.getJobCode(this.empId).subscribe(job=>{
            this.jobCodes=job;
        })
        

        if(this.myTar.tarCode==null){
            this.myTar.jobOrCc='Y';
            this.myTar.tarStatus='CREATE';
        }
    
         this.activatedRoute.queryParams.subscribe(params=>{
            this.id=params.id;
         })  
         

        if(this.id != null){
            this.getbyId(this.id);
        }
         if(this.myTar.tarSubmittedDt==null){
            this.myTar.tarSubmittedDt = new Date().toJSON();
        }

      }

      createTar(){
        this.myTar.tarSubmittedDt=null;
          if(this.myTar.jobOrCc=='Y'){
            this.myTar.costCenterId='';
        }
        if(this.myTar.jobOrCc=='N'){
            this.myTar.jobId='';
        }
       
         this.myTar.isAddlTravelInvolved=(this.myTar.isAddlTravelInvolved?"Y":"N");
         console.log(this.myTar);
          this.service.CreateMyTarReq(this.myTar).subscribe(x=>{
            this.msg = { type: 'success', text: "Created successfully" }
            this.myTar=x;
            this.router.navigate(['/my-space/my-tar-request/edit'] ,{queryParams:{flowType:"Employee-Request",id:x.id}});
          },error => { 
            this.msg = { type: 'danger', text: 'Server Error-'+error._body }
          })
      }

      applyTar(){
        this.myTar.tarSubmittedDt=null;
        if(this.myTar.jobOrCc=='Y'){
            this.myTar.costCenterId='';
        }
        if(this.myTar.jobOrCc=='N'){
            this.myTar.jobId='';
        }
        if(this.myTar.id==null){
            this.myTar.isAddlTravelInvolved=(this.myTar.isAddlTravelInvolved?"Y":"N");
        }
        this.service.ApplyMyTarReq(this.myTar).subscribe(x=>{
            this.msg = { type: 'success', text: "Applied successfully" }
            this.myTar=x;
            this.router.navigate(['/my-space/my-tar-request/edit'] ,{queryParams:{flowType:"Employee-Request",id:x.id}});
        },err=>{
            this.msg = { type: 'danger', text: 'Server Error-'+err._body }
        })

      }

      getbyId(id:any){
        this.service.getTarById(id).subscribe(x=>{
            if(x.isAddlTravelInvolved=="N"){
               this.isTravel='No';
               }
               if(x.isAddlTravelInvolved=="Y"){
                this.isTravel='Yes';
               }
            this.myTar=x;          
        })
        
      }
 
back(){
    this.modalReference.close();  
}

      cancle(){
        this.modalReference = this.modalService.open(this.vdetail, {size: 'sm'});	
      }

      cancleTar(){
          this.myTar.tarSubmittedDt=null;
          console.log(this.myTar);
          this.service.UpdateMyTarReq(this.myTar.id,"cancel",this.myTar).subscribe(x=>{
            this.router.navigateByUrl("/my-space/my-tar-request/list");
            this.modalReference.close();
        })
      }

      navigateToListPage(){
             this.router.navigateByUrl("/my-space/my-tar-request/list");   
     }
 }