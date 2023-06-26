import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { HolidaysService } from './../holidays.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { Holiday } from '../../../vo/holiday';

@Component({
    selector: 'holidays-show',
    templateUrl: './show.component.html'
})
export class HolidaysShowComponent implements OnInit {

	@ViewChild('cdelete') cdelete: TemplateRef<any>;
	@ViewChild('cedit') cedit: TemplateRef<any>;
    constructor(private router:Router, private modalService: NgbModal, private _service: HolidaysService){

    }

	rows: Array<Holiday>;;
    ngOnInit() {
		this.getData();
    }
	
	getData(){
		this._service.getHolidays().subscribe(x=>this.rows=x);
	}
	
	modalReference:any = null;
	modalData: any;
	deleteFY(holiday: Holiday){
		this.modalData = holiday;
		this.modalReference = this.modalService.open(this.cdelete);
	}
	
	saveMsg: any;
	confirmDelete(data){
		
		this._service.deleteHoliday(data.id).subscribe(x=>{
			this.saveMsg = {'type': 'success', 'text': "Holiday Deleted Successfully"}
			this.getData();
			this.modalReference.close();
		}, e=> {
			this.saveMsg = {'type': 'success', 'text': "Holiday Deleted Successfully"}
			this.getData();
			this.modalReference.close();
		});
	}
	
	isEdit: boolean = false;
	editFY(holiday: Holiday){
		this.modalData = holiday;
		this.modalData.rh = holiday.restrictedHoliday == "Y" ? true: false;
		this.isEdit = true;
		if(holiday.holidayDt){
			let year = new Date(holiday.holidayDt).getFullYear();
			let month = new Date(holiday.holidayDt).getMonth() + 1;
			let day = new Date(holiday.holidayDt).getDate();
			this.sdate = {year: year, month: month, day: day}
		}
		
		this.modalReference = this.modalService.open(this.cedit);
	}
	
	newFY(){
		this.modalData = new Holiday();
		this.modalData.rh = false;
		this.isEdit = false;
		this.modalReference = this.modalService.open(this.cedit);
	}
	
	updateFY(data: Holiday){
		if(data.id){
			this._service.updateHoliday(data.id, data).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Holiday updated Successfully"};
				this.getData();
				this.modalReference.close();
			},e=> {
				this.modalReference.close();
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}else{
			this._service.createHoliday(data).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Holiday Created Successfully"};
				this.getData();
				this.modalReference.close();
			},e=> {
				this.modalReference.close();
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}
	}
	
	sdate: any;
	updateDate($e, obj, param){
		let e = new Date($e.year+'-'+$e.month+'-'+$e.day);
		obj[param] = new Date(e.getTime() - (e.getTimezoneOffset() * 60000)).toJSON();
	}
}