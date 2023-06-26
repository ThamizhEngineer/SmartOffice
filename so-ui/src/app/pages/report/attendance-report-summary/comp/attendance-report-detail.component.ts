import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';

import { AttendanceReportService } from '../attendance-report.service';
import { Attendance } from '../../../vo/attendance';
@Component({
    selector: '',
    templateUrl: 'attendance-report-detail.component.html'
})

export class AttendanceReportDetailComponent implements OnInit {
    
    attendanceEmpDetail:[Attendance];
     //Month config
     mpFromConfig;
     mpSalModel;
     empCode:any;
    view:any;
    month:any;
    year:any;
    officeId:any;

    constructor(
        private service:AttendanceReportService,
        private activatedRoute:ActivatedRoute,    
        private commonService: CommonService,    
        private route:Router
    ) { }

    ngOnInit() { 
        
        this.attendanceEmpDetail=[new Attendance];
        this.activatedRoute.params.subscribe(param=>{
            console.log("HIT>....!!")
           this.empCode=param.empCode;
           this.month=param.month;
           this.year=param.year;
            // this.view=param.view;
            this.mpSalModel=new Date(param.year, param.month)
        this.service.getByEmpCode(param.month,param.year,param.empCode).subscribe(x=>{
            console.log("HIT")
            this.attendanceEmpDetail=x.attendanceList;
            this.officeId= this.attendanceEmpDetail[0].officeId;
            console.log(this.attendanceEmpDetail);
        })
        });
    }

    mpOnModelChange($event) {        
        this.attendanceEmpDetail=[new Attendance];
        const mon = parseInt($event.month, 10)
        let monthNo = mon.toString();
        let year = $event.year;
        this.service.getByEmpCode(monthNo,year,this.empCode).subscribe(x=>{
            this.attendanceEmpDetail=x.attendanceList;
            console.log(this.attendanceEmpDetail);
        })
    }

    export(){
        this.service.exportEmployeeSummary(this.month,this.year,this.empCode).subscribe(x=>{        
            this.commonService._saveFileInSystem(x.blob(), 'employee-base-summary.xls');
        })
    }

    navigateToList(){
        if(this.view=='office'){
            this.route.navigateByUrl("reports/attendance-report/"+this.view+"/"+this.month+"/"+this.year+"/"+this.officeId);
        }else{
            this.route.navigateByUrl("reports/attendance-report/"+this.view);
        }
              
    }

}