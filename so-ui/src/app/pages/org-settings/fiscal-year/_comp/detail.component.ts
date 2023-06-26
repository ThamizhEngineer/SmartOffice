import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { FiscalYearService } from './../fiscal-year.service';

import { FiscalYear, PayCycle, PayCycleLine } from '../../../vo/fiscal-year';

const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
const tabs = [
	{"name": "Salary", code: "REGULAR-SALARY", size: 12},
	{"name": "Expense Claim", code: "EXPENSE-CLAIM", size: 36},
	{"name": "Site Allowance", code: "SITE-ALLOWANCE", size: 12},
	
];

@Component({
    selector: 'fiscal-year-detail',
    templateUrl: './detail.component.html'
})
export class FiscalYearDetailComponent implements OnInit {

	constructor(private router:Router, private activatedRoute: ActivatedRoute, private _service: FiscalYearService){

    }
	
    saveMsg: any;
    saveMsg1: any;
    months = months;
	fy: FiscalYear;
	
	isFYModified: boolean = false;
	isEdit: boolean = false;
	
	payCycles: any;
	tabs: any = tabs;
	
	
    ngOnInit() {
		this.fy = new FiscalYear();
		if (this.activatedRoute.params['_value']['id']) {
			this.isEdit = true;
			
			this.activatedRoute.params.switchMap((params: Params) => this._service.getMultipleData(params['id']))
			.subscribe( x => {
				this.objModify(x[0], x[1]);			
			});
		}
    }
	
	objModify(fy: FiscalYear, pc){
		fy.vfromDt = this.frameDate(fy.fromDt);
		fy.vtoDt = this.frameDate(fy.toDt);
		
		this.fy = fy;
		pc = pc.filter(x=>x.fiscalYearId == fy.id);
		if(pc && pc.length > 0) {
			pc.forEach(x=>{
				let t = this.tabs.filter(y=>y.code.replace(/[^a-zA-Z]/g, "")==x.payCycleType.replace(/[^a-zA-Z]/g, "")).pop();
				x.companyPayCycleLines.forEach(y=>{
					y.vfromDt = this.frameDate(y.fromDt);
					y.vtoDt = this.frameDate(y.toDt);
					y.vpayDt = this.frameDate(y.payDt);
					y.vprocessedDate = this.frameDate(y.processedDate);
				});
				for(let i = x.companyPayCycleLines.length;i< t.size;i++){
					let pl = new PayCycleLine();
					pl.vfromDt = this.frameDate("");
					pl.vtoDt = this.frameDate("");
					pl.vpayDt = this.frameDate("");
					pl.vprocessedDate = this.frameDate("");
					
					x.companyPayCycleLines.push(pl);
				}
			});
			this.payCycles = pc;
		}
		else{
			this.payCycles = [];
			this.tabs.forEach(x=>{
				let p = new PayCycle();
				p.fiscalYearId = fy.id;
				p.payCycleType = x.code;
				p.companyPayCycleLines = [];
				for(let i = 0;i<x.size;i++){
					let pl = new PayCycleLine();
					pl.vfromDt = this.frameDate("");
					pl.vtoDt = this.frameDate("");
					pl.vpayDt = this.frameDate("");
					pl.vprocessedDate = this.frameDate("");
					
					p.companyPayCycleLines.push(pl);
				}
				this.payCycles.push(p);
			});
		}
		
	}
	
	updateFY(){
		if(this.isFYModified){
			this._service.updateFiscalYear(this.fy.id, this.fy).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Fiscal Year updated Successfully"};
				this.isFYModified = false;
			},e=> {
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}
		
		this._service.updatePayCycles(this.payCycles).subscribe(y=>{
			this.saveMsg1 = {'type': 'success', 'text': "Paycycles updated Successfully"};
		},e=> {
			this.saveMsg1 = {'type': 'danger', 'text': "Error in Service"};
		});
	}
	
	sdate: any;
	edate: any;
	updateDate($e, obj, param){
		let e = new Date($e.year+'-'+$e.month+'-'+$e.day);
		obj[param] = new Date(e.getTime() - (e.getTimezoneOffset() * 60000)).toJSON();
	}
	
	frameDate(str?: string, obj?: any){
		let year:any; let month:any; let day:any; let o:any;
		if(str){
			year = new Date(str).getFullYear();
			month = new Date(str).getMonth() + 1;
			day = new Date(str).getDate();
			
		}
		o = {year: year, month: month, day: day};
		
		if(obj) obj = o;
		else return o;
	}

	checkDates(){
		if(!this.fy.fromDt || !this.fy.toDt) return;
		this.fy.fiscalCode = "FY"+(new Date(this.fy.fromDt).getFullYear())+"-"+(new Date(this.fy.toDt).getFullYear());
		this.fy.fyPrefix = new Date(this.fy.toDt).getFullYear().toString().substr(-2);
	}
}