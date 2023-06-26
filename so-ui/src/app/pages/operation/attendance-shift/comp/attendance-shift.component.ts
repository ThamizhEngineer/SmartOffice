import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AttendanceShiftService } from '../attendance-shift.service';
import { AttendanceShfit } from '../vo/attendanceShift';

@Component({
    selector: 'attendance-shift',
    templateUrl: 'attendance-shift.component.html'
})

export class AttendanceShiftComponent implements OnInit {
    @ViewChild('vdetail') vdetail: TemplateRef<any>;
    rows: any=[];
    page :number = 1;
    pageSize :number = 10;
    modalReference: any;
    attendanceShfit: AttendanceShfit;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private router:Router,private modalService:NgbModal,private route:ActivatedRoute,private service:AttendanceShiftService) { }

    ngOnInit() { 
        this.attendanceShfit= new AttendanceShfit();
        this.getAll();
    }

    getAll(){
        this.service.getAttendanceShift().subscribe(x=>{
            this.rows=x;
            console.log(this.rows)
        })
    }

    deleteRow(id?: any){
        this.service.deleteAttendanceShiftById(id).subscribe( x=>{
            this.ngOnInit();
        });
    }

    createNew(){
        this.attendanceShfit= new AttendanceShfit();
        this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
    }

    showDetail(id?: any){
        this.service.getAttendanceShiftById(id).subscribe(x=>{
            this.attendanceShfit=x;
            this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
        })
    }
    
    save(){
     
        if(this.attendanceShfit.id){
            
                this.service.updateAttendanceShiftById(this.attendanceShfit, this.attendanceShfit.id).subscribe(x => { 
                this.saveMsg = { 'type': 'success', 'text': "DayRange Updated Successfully" };
                this.getAll();
                this.modalReference.close();
            } );             
         }
        
         else{
            this.service.addAttendanceShift(this.attendanceShfit).subscribe(x =>{
            this.saveMsg = { 'type': 'success', 'text': "Day Range Created Successfully" };
            this.getAll();
            this.modalReference.close();
            
        });
        }
    }
}