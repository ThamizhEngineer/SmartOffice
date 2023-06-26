import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LeaveTypeService } from '../leave-type.service';
import { LeaveType } from '../vo/leave-type';

@Component({
    selector: 'leave-type-list',
    templateUrl: './leave-type-list.component.html'
})

export class LeaveTypeListComponent implements OnInit {
    @ViewChild('cdetail') cdetail:TemplateRef<any>;
    
    constructor(private router:Router,private modalService:NgbModal,private service:LeaveTypeService,private route:ActivatedRoute) { }
    // rows:Array<LeaveType>;
    rows:any=[];
    leavetype:LeaveType;
    saveMsg:any;
    page :number = 1;
    pageSize :number = 10;

    ngOnInit() { 
        this.getData(); 
        this.leavetype=new LeaveType();   
    }

    getData(){
        this.service.getLeaveTypes().subscribe(x=>
            this.rows=x);
    }
    delete(id:string){
        this.service.deleteLeaveTypeById(id).subscribe(x=>{
            this.saveMsg={'type':'success','text':"Leave TypeDeleted Successfully"};
            this.getData();
        },error=>{
            this.saveMsg={'type':'danger','text':"Server Error"};
        });
    }
    modalReference:any=null;
    modalData: any;
    create(){
        this.leavetype=new LeaveType();
        this.modalData = new LeaveType();
		this.modalData.lt = false;
        this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
    } 
    save(){
        this.leavetype.approvalDriven=(this.leavetype.approvalDriven?"Y":"N");
        console.log(this.leavetype.id);
        if(this.leavetype.id){
            if (this.leavetype.minDuration >= this.leavetype.maxDuration || this.leavetype.minDuration == this.leavetype.maxDuration){
                this.saveMsg = { 'type': 'danger', 'text': "min should not max duration" };
            }
            else{
                this.service.updateLeaveTypeById(this.leavetype, this.leavetype.id).subscribe(x => { 
                this.rows=x;
                this.saveMsg = { 'type': 'success', 'text': "Leave Type Updated Successfully" };
                this.getData();
                this.modalReference.close();
            } );             
         }
        }
         else{
            if (this.leavetype.minDuration >= this.leavetype.maxDuration || this.leavetype.minDuration == this.leavetype.maxDuration){
                this.saveMsg = { 'type': 'danger', 'text': "min should not max duration" };
            }
            else{
            this.service.addLeaveType(this.leavetype).subscribe(x =>{
            this.saveMsg = { 'type': 'success', 'text': "Leave Type Created Successfully" };
            this.getData();
            this.modalReference.close();
            
        });
        }
    }
        
    }
    show(id:string){
        this.service.getLeaveTypeById(id).subscribe(x=>{
            if(x.approvalDriven=="N"){
                x.approvalDriven=null;
              }
            this.leavetype=x;
          this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
        });
  
    }
}