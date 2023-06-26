import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { FiscalYearService } from './../fiscal-year.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { FiscalYear } from '../../../vo/fiscal-year';

@Component({
    selector: 'fiscal-year-show',
    templateUrl: './show.component.html'
})
export class FiscalYearShowComponent implements OnInit {

	@ViewChild('cdelete') cdelete: TemplateRef<any>;
	@ViewChild('cedit') cedit: TemplateRef<any>;
    constructor(private router:Router, private modalService: NgbModal, private _service: FiscalYearService){

    }

	rows: Array<FiscalYear>;
	page :number = 1;
    pageSize :number = 10;
    ngOnInit() {
		this.getData();
    }
	
	getData(){
		this._service.getFiscalYears().subscribe(x=>this.rows=x);
	}
	
	modalReference:any = null;
	modalData: any;
	deleteFY(fy: FiscalYear){
		this.modalData = fy;
		this.modalReference = this.modalService.open(this.cdelete);
	}
	
	saveMsg: any;
	confirmDelete(data){
		
		this._service.deleteFiscalYear(data.id).subscribe(x=>{
			this.saveMsg = {'type': 'success', 'text': "Fiscal Year Deleted Successfully"}
			this.getData();
			this.modalReference.close();
		}, e=> {
			this.saveMsg = {'type': 'success', 'text': "Fiscal Year Deleted Successfully"}
			this.getData();
			this.modalReference.close();
		});
	}
	
	isEdit: boolean = false;
	editFY(fy: FiscalYear){
		this.modalData = fy;
		this.isEdit = true;
		if(fy.fromDt){
			let year = new Date(fy.fromDt).getFullYear();
			let month = new Date(fy.fromDt).getMonth() + 1;
			let day = new Date(fy.fromDt).getDate();
			this.sdate = {year: year, month: month, day: day}
		}
		if(fy.toDt){
			let year = new Date(fy.toDt).getFullYear();
			let month = new Date(fy.toDt).getMonth() + 1;
			let day = new Date(fy.toDt).getDate();
			this.edate = {year: year, month: month, day: day}
		}
		this.modalReference = this.modalService.open(this.cedit);
	}
	
	newFY(){
		this.modalData = new FiscalYear();
		this.isEdit = false;
		this.modalReference = this.modalService.open(this.cedit);
	}
	
	updateFY(data: FiscalYear){
		if(data.id){
			this._service.updateFiscalYear(data.id, data).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Fiscal Year updated Successfully"};
				this.getData();
				this.modalReference.close();
			},e=> {
				this.modalReference.close();
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}else{
			this._service.createFiscalYear(data).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Fiscal Year Created Successfully"};
				this.getData();
				this.modalReference.close();
			},e=> {
				this.modalReference.close();
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}
	}
	
	sdate: any;
	edate: any;
	updateDate($e, obj, param){
		let e = new Date($e.year+'-'+$e.month+'-'+$e.day);
		obj[param] = new Date(e.getTime() - (e.getTimezoneOffset() * 60000)).toJSON();
	}
	
	checkDates(){
		if(!this.modalData.fromDt || !this.modalData.toDt) return;
		this.modalData.fiscalCode = "FY"+(new Date(this.modalData.fromDt).getFullYear())+"-"+(new Date(this.modalData.toDt).getFullYear());
		this.modalData.fyPrefix = new Date(this.modalData.toDt).getFullYear().toString().substr(-2);
	}
}