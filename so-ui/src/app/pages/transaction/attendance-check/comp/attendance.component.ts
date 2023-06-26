import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { AttendanceCheckService } from '../attendance.service';
import { Attendance } from '../vo/attendance';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../../../../shared/common/common.service';
import { Observable } from 'rxjs/Observable';

@Component({
    selector: '',
    templateUrl: 'attendance.component.html'
})

export class AttendanceComponent implements OnInit {
    
    @ViewChild('detail') detail: TemplateRef<any>;

    month:any;
    year:any;
    empCode;
    empName:any='';
    empId:any;
    officeId:any;
    attendStatus:any=[{'status':'WBL'},{'status':'WNBL'},{'status':'SL'},{'status':'HSL'}]
    officeCals:any=[]
    attendances:any=[]
    modalReference:any = null;
    mainAttendance:[Attendance];
    attendance:Attendance;
      //Month config
      mpFromConfig;
      mpSalModel;
      shifts:any=[];
    constructor(
        private service:AttendanceCheckService,
        private commonService:CommonService,
        private modalService: NgbModal
    ) { }

    ngOnInit() {
       
        this.service.getAttendanceShift().subscribe(x=>{
			this.shifts=x;		
		})
     }

     
     reset(){
         this.empId=null;
         this.empName='';
         this.mainAttendance=[new Attendance]
         this.attendance=null;
     }

     detailView(attendance:Attendance){
        this.attendance=new Attendance();
        if(attendance.checkOut==null){
            this.attendance.checkOut='00:00:00'
        }
        this.attendance=attendance;
		this.modalReference = this.modalService.open(this.detail, {size: 'lg'});		
    }

    mpOnModelChange($event) {        
        const mon = parseInt($event.month, 10)
        this.month = mon.toString();
        this.year = $event.year;
    }

    projectSelected($event){
        this.empName=$event.empName;
        this.empCode=$event.empCode;
        this.empId=$event.id;
    }

     search(){
        this.mainAttendance=[new Attendance]
        this.service.getAttendanceByEmp(this.empId,this.year,this.month).subscribe(x=>{
            this.mainAttendance=x
        }); 
     }

     save(){
        this.service.createAndUpdate(this.attendance).subscribe(x=>{
            this.modalReference.close();
        }) 
     }

     fileChanged($event){
        let fileList: FileList = $event.target.files;        
        if (fileList.length > 0) {  
            let files: File = fileList[0];          
			 this.commonService.uploadAttendance(files).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {    
                        if(this.empId!=null&&this.year!=null&&this.month!=null){
                            this.search();
                        }                                                                                
                    },
                    error => console.log(error)
                )}     
    }
}