import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { PayslipsService } from '../payslips.service';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { UploadPayslipHdr} from './../../../vo/upload-payslip';
import{CommonService} from './../../../../shared/common/common.service';

import { status_css } from '../../../vo/status'


@Component({
    selector: 'operations-payslips-list',
	templateUrl: './list.component.html',
	providers:[CommonService]
})
export class PayslipsListComponent implements OnInit {

	uploadPayslipHdr: UploadPayslipHdr;
	uploadPayslipHdrList:Array<UploadPayslipHdr>;
	paymonth:any;

	month:any;
	year:any;
	isOverwriteFlag:any;
	
	advSearch:boolean=false;
	statuses:any=status_css;
	page :number = 1;
    pageSize :number = 10

	constructor(private router:Router, private activatedRoute:ActivatedRoute,
		private commonService:CommonService ,private modalService: NgbModal, private _service: PayslipsService){}	

	ngOnInit() {
		this.uploadPayslipHdrList=[new UploadPayslipHdr];
		this._service.getPaySlip().subscribe(x=>{
			this.uploadPayslipHdrList = x;

			
			this.uploadPayslipHdrList.forEach(x=>{
		
				
				
			})
			
		});
	}

	advanceSearch(){
        if(this.advSearch==false){
			this.advSearch=true;
			
        }else if(this.advSearch==true){
            this.advSearch=false;
        }
    }
	search(){
		this.uploadPayslipHdrList=[];
		this._service.getPayslipByMonthAndYearAndOverwrteFlag(this.payMonth,this.payYear,this.isOverwriteFlag).subscribe(data=>{
			this.uploadPayslipHdrList = data;
		})
	}
	payMonth:string
	payYear:string
	reset(){
		this._service.getPaySlip().subscribe(data=>{
			this.payMonth=null;
			this.payYear=null;
			this.isOverwriteFlag=null;
			this.uploadPayslipHdrList=data;
		})
	}
	navigateToDetailPage(id: number) {
	
        this.router.navigateByUrl("/operation/upload-payslips/detail/" + id);
    }
}
