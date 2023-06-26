import { Component, OnInit, ViewEncapsulation, ViewChild, TemplateRef  } from '@angular/core';
import { Title }     from '@angular/platform-browser';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { saveAs } from 'file-saver';
import { EmployeePayoutService } from '../employee-payout.service';
import { CommonService } from '../../../shared/common/common.service';

const MONTHS = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

@Component({
  selector: 'payslips-view',
  templateUrl: './payslips.component.html',
  styleUrls: ['./payslips.css']
})
export class PayslipsComponent implements OnInit {

	@ViewChild('payslip') payslip: TemplateRef<any>;
	rows: any=[];
	months = MONTHS;
	pageSize :number = 10
	page :number = 1;
	
	constructor(private titleService: Title, private modalService: NgbModal,private commonService:CommonService, private service: EmployeePayoutService) { }

	ngOnInit() {
		let currentUser = this.service.getCurrentUser(); 
        if(currentUser){
        	this.service.myPayouts().subscribe(x => {  //currentUser.employeeId
				this.rows = x;
				console.log(this.rows)
	        });
		}
	}
	
	
	modalReference:any = null;
	modalData: any;
	
	payDetail: any;
	payoutLines: any = {};

	viewPayslip(id){
		console.log(id);
		this.service.getEmployeePayoutById(id).subscribe(x => { 
	        this.payDetail = x;
	        x.employeePayoutLines.forEach(x=>{
	        	this.payoutLines[x.dPayTypeCode] = x.periodAmt;
	        });
	        console.log(this.payoutLines)
	        this.modalReference = this.modalService.open(this.payslip, {size: 'lg'});
	    });				
	}
	download(docId){
		if(docId!=null&&docId!=""&&docId!=undefined){
            this.commonService.downloadDocument(docId,'');
        } 
    }
}
