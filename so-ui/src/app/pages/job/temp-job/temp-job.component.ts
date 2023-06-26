import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TempJobService } from './temp-job.service';
import { TempJob, TempJobEmp } from './vo/temp-job';
import { CommonService } from '../../../shared/common/common.service';

@Component({
    selector: 'temp-job',
    templateUrl: './temp-job.component.html'
})

export class TempJobComponent implements OnInit {
    @ViewChild('vedit') vedit:TemplateRef<any>;
    constructor(private router:Router,private modalService:NgbModal,private service:TempJobService, private commonService: CommonService) { }
  rows:Array<TempJob>;
  tempjob:TempJob;
  tempjobemp:TempJobEmp;
  tempJobEmps:Array<TempJobEmp>=[];
  jobCount=1;
  saveMsg:any;
  value:any;
  errorMsg:any;
  employeeList:any=[];
  office:any=[];

    ngOnInit() { 
        this.tempjob=new TempJob();
        this.tempjobemp=new TempJobEmp();
        let data=new TempJobEmp();
        this.tempjob.tempJobEmps.push(data);
        this.getData();
        this.getEmployees();
        this.getOffice();
    }
    getEmployees(){
        this.service.getEmployees().subscribe(x=>{
          this.employeeList=x;
        })
      }
      getOffice(){
        this.service.getOffices().subscribe(x=>{
            this.office=x;
            console.log(this.office);
        });
      }
    getData(){
        this.service.getTempJob().subscribe(x=>
            this.rows=x);
    }
    delete(id:string){
        this.service.deleteTempJob(id).subscribe(x=>{
            this.saveMsg={'type':'success','text':"Job Deleted Successfully"};
            this.getData();
        },error=>{
            this.saveMsg={'type':'danger','text':"Server Error"};
        });
    }
    modalReference:any=null;
    modalData:any;

  create(){
      this.tempjob=new TempJob();
      this.tempjobemp=new TempJobEmp();
      let data=new TempJobEmp();
      

      this.tempjob.tempJobEmps.push(data);
      this.modalReference=this.modalService.open(this.vedit,{size:'lg'});
  }  
  save(){
      this.tempjob.jobActive=(this.tempjob.jobActive?"Y":"N");
      if(this.tempjob.id!=null){
         this.service.updateTempJob(this.tempjob.id,this.tempjob).subscribe(x=>{
             this.tempjob.id=x.id;
             this.saveMsg={'type':"success",'text':"Job Updated Successfully"};
             this.getData();
             this.modalReference.close();
        });
      }
      else{
          if(this.tempjob.id==null){
          this.service.addTempJob(this.tempjob).subscribe(x=>{
              this.tempjob=x;
              this.saveMsg={'type':"success",'text':"Job Created Successfully"};
              console.log(this.tempjob);
              this.getData();
              this.modalReference.close();
          
          }, error => {
                  this.saveMsg = { 'type': 'danger', 'text': "Server Error" };
              });
      }
    }
  }
  show(id:any){
      this.service.getTempJobById(id).subscribe(x=>{
         if(x.jobActive=="N"){
             x.jobActive=null;
         }  
        this.tempjob=x;
        this.tempjobemp=x.tempJobEmps;
        this.modalReference=this.modalService.open(this.vedit,{size:'lg'});
      });

  }
  addJobRows(){
      this.jobCount++;
      let data= new TempJobEmp();
      this.tempjob.tempJobEmps.push(data);
  }
  delJobRow(i){
    console.log(i)
    this.tempjob.tempJobEmps.splice(i,1);

}
}
  
