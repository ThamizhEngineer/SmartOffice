import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AttendanceReportService } from '../attendance-report.service';
import { AttendanceReport } from '../vo/attendance-report';
import { FormGroup, FormBuilder, Validators,FormControl, Form } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Employee } from '../../../vo/employee';


@Component({
    selector: 'selector-name',
    templateUrl: './attendance-report.component.html'
})

export class AttendanceReportComponent implements OnInit {

    report:any=[];
    rows:Array<AttendanceReport>;
    employeeId:any;
    // n1EmpId:any=null;
    startDate:any;
    endDate:any;
    myGroup: FormGroup;
    sDate:FormControl;
    eDate:FormControl;
    emp: any=[];
    empName:string=null;
    n1EmpFName:string=null;
    N1Employee: any=[];
  n1Id: any="";
  n1Name: any="";
  page :number = 1;
  pageSize :number = 10;

    
    constructor(private router:Router,private modalService: NgbModal,private service:AttendanceReportService) { }

    ngOnInit() { 
        this.getAllCertificatesData();
        this.getEmployees();
        this.getN1Employe();
        this.rows = new Array<AttendanceReport>();
    }

    getEmployees(){
      this.service.getEmployee().subscribe(x=>{
        this.emp=x;
        console.log(this.emp)
      })
    }

    getN1Employe(){
      this.service.getN1Employees().subscribe(x=>{
        this.N1Employee=x;
        console.log(this.N1Employee)
      })
    }

    getAllCertificatesData(){
        this.service.getAllAttendance().subscribe(x=>{
          this.report=x;
          console.log(this.report);
        })
      }

      projectSelected($event) {
        this.employeeId=$event.id;
        this.empName=$event.empName;
    }

  n1Selected($event){
    this.n1Id= $event.id;
    this.n1Name=$event.empName;      
}

      search(){
        this.service.advanceSearch(this.employeeId, this.n1Id,this.startDate,this.endDate).subscribe(x=>{
          console.log(x)
        this.report=x;   
            console.log(this.report)
        })
     }

  //    reset() {
  //   this.empName = null,this.empName1 = null, this.employeeId = null; this.n1EmpId = null; this.startDate = null; this.endDate = null;
  //    this.ngOnInit();
  // }
    
     validate(){
      this.sDate = new FormControl('', [Validators.required]);
      this.eDate = new FormControl('', [Validators.required]);
  }
  createForm(){
      this.myGroup = new FormGroup({
        sDate:this.sDate,
        eDate:this.eDate
      });		
}

}

