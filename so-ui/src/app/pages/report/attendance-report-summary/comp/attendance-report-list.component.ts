import { Component, OnInit } from '@angular/core';
import { AttendanceReportService } from '../attendance-report.service';
import { CommonService } from '../../../../shared/common/common.service';
import { ActivatedRoute,Router } from '@angular/router';

@Component({
    selector: '',
    templateUrl: 'attendance-report-list.component.html'
})

export class AttendanceReportListComponent implements OnInit {
    
    monthNo:any;
    year:any;
    officeId:any;
    page = 0;
    pageSize = 10;
    rowSize = 0;
    //Month config
    mpFromConfig;
    mpSalModel;
    offices:any=[];
    view:string;
    isSearch:boolean=false;
    rows:any=[];
    empCode:string='';
    empName:string='';
    constructor(
        private service:AttendanceReportService,
        private commonService: CommonService,
        private activatedRoute:ActivatedRoute,     
        private route:Router
    ) { }

    ngOnInit() {
        this.isSearch=false;
        this.rows=[];
        this.service.getOffices().subscribe(x=>{
			this.offices=x;
        })
        this.activatedRoute.params.subscribe(param=>{
            console.log(param);
            this.view=param.view;
            if(param.month!=null&&param.year!=null&&param.officeId!=null){
                this.officeId=param.officeId
                this.mpSalModel=new Date(param.year, param.month)
                this.service.getAllAttendanceSummary(param.month,param.year,param.officeId).subscribe(x=>{
                    this.rows=x.attendanceSummaryList;
                    if(this.view=='office'){
                        this.isSearch=true;
                    }                   
                })
            }
        });
     }

    search(){
        this.service.getAllAttendanceSummary(this.monthNo,this.year,this.officeId).subscribe(x=>{
            this.rows=x.attendanceSummaryList;
            this.isSearch=true;
        })
    }

    navigateToDetail(empCode){
        this.route.navigateByUrl("reports/attendance-report/"+this.view+"/detail/"+empCode+"/"+this.monthNo+"/"+this.year);       
    }

    export(){
        this.service.exportAttendanceSummary(this.monthNo,this.year,this.officeId).subscribe(x=>{
            this.commonService._saveFileInSystem(x.blob(), 'office-base-summary.xls');
        })
    }

    reset(){
        this.rows=[];
        this.isSearch=false;
    }

    mpOnModelChange($event) {        
        console.log(this.mpFromConfig);
        console.log(this.mpSalModel)
        console.log($event)
        const mon = parseInt($event.month, 10)
        this.monthNo = mon.toString();
        this.year = $event.year;
    }

    projectSelected($event){      
        this.empCode=$event.empCode;     
    }
}