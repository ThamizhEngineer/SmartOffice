import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';

import { AttendanceShiftService } from '../attendance-shift.service';
import { AttendanceShift } from '../../vo/attendance-shift'
@Component({
    selector: '',
    templateUrl: 'attendance-shift.component.html'
})

export class AttendanceShiftComponent implements OnInit {
   
    @ViewChild('vdetail') vdetail: TemplateRef<any>;

    shifts:any=[];
    shift:AttendanceShift;
    modalReference: any = null;
    saveMsg:any;
    errorMsg:any;
    page :number = 1;
    pageSize :number = 10;
    form: FormGroup;
   
    code:FormControl;
	name:FormControl;
	fromTime:FormControl;
	midTime:FormControl;
	toTime:FormControl;
	expTime:FormControl;



    constructor(
        private service:AttendanceShiftService,
        private modalService: NgbModal
    ) { }

    ngOnInit() { 
        this.shift = new AttendanceShift();
        this.service.getAllShift().subscribe(x=>{
            this.shifts=x;
        })
        this.formControler();
        this.createForm();
    }

    formControler(){
        this.name = new FormControl('', [Validators.required]);
		this.code = new FormControl('', [Validators.required]);
		this.fromTime = new FormControl('', [Validators.required]);
		this.toTime = new FormControl('', [Validators.required]);
		this.midTime = new FormControl('', [Validators.required]);
		this.expTime = new FormControl('', [Validators.required]);
    }

    createForm(){
        this.form = new FormGroup({
            name:this.name,
            code:this.code,
            fromTime:this.fromTime,
            toTime:this.toTime,
            midTime:this.midTime,
            expTime:this.expTime
        })

    }

    createNew(){
        this.shift= new AttendanceShift();
        this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
    }

    showDetail(id?: any){
        this.service.getShiftById(id).subscribe(z=>{
            this.shift=z;
            console.log(this.shift);
            this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
        })

        this.form.patchValue({
            name:this.name,
            code:this.code,
            fromTime:this.fromTime,
            toTime:this.toTime,
            midTime:this.midTime,
            expTime:this.expTime
        })
    }
    deleteRow(id?: any){
        this.service.deleteShift(id).subscribe( x=>{
            this.ngOnInit();
        });
    }

    updateTime(){
        console.log(this.shift.fromTime.length);
        if(this.shift.fromTime.length==5){
            this.shift.fromTime=this.shift.fromTime+':01'
        }
        if(this.shift.toTime.length==5){
            this.shift.toTime=this.shift.toTime+':01'
        }
        if(this.shift.midTime.length==5){
            this.shift.midTime=this.shift.midTime+':01'
        }
        if(this.shift.attendanceExpTime.length==5){
            this.shift.attendanceExpTime=this.shift.attendanceExpTime+':01'
        }
        
       
       
    }

    save(){
        this.updateTime();
        this.service.createShift(this.shift).subscribe(x=>{
            this.modalReference.close();
            this.saveMsg = { 'type': 'success', 'text': "Attendance Shift Created Successfully" };
            this.ngOnInit();
        },error=>{
            this.errorMsg=JSON.parse(error._body);
            this.modalReference.close();
            this.saveMsg = { type: 'danger', text: this.errorMsg.message }   
          })
    }
    update(){
        this.updateTime();
        console.log(this.shift);
        this.service.updateShift(this.shift).subscribe(x=>{
            this.modalReference.close();
            this.saveMsg = { 'type': 'success', 'text': "Attendance Shift Updated Successfully" };
            this.ngOnInit();
        },error=>{
            this.errorMsg=JSON.parse(error._body);
            this.modalReference.close();
            this.saveMsg = { type: 'danger', text: this.errorMsg.message }   
          })
    }
    
}