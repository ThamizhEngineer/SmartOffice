import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, Route, ActivatedRoute, Params } from '@angular/router';
import { PayslipsService } from '../payslips.service';
import { CommonService } from './../../../../shared/common/common.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { UploadPayslipHdr, UploadPayslipLine } from './../../../vo/upload-payslip';
@Component({
	selector: 'operations-payslips-detail',
	templateUrl: './detail.component.html',
	providers: [CommonService, DatePipe]
})
export class PayslipsDetailComponent implements OnInit {

	uploadData: any;
	uploadDate: any;
	uploadPayslipHdr: UploadPayslipHdr;
	salaryMonth: any;
	statues = [{ "name": "CREATED", "value": "CREATED" },
	{ "name": "PROCESSING", "value": "PROCESSING" },
	{ "name": "COMPLETED", "value": "COMPLETED" }]

	progressProcessorCommand = "check-progress";
	progressProcessorPayload;
	progressProcessorConfig = { buttonName: "Payslip progress" }

	constructor(private router: Router,
		private activatedRoute: ActivatedRoute,
		private _service: PayslipsService,
		private datePipe: DatePipe) { }

	ngOnInit() {
		this.uploadPayslipHdr = new UploadPayslipHdr();

		if (this.activatedRoute.params['_value']['_id']) {

			this.activatedRoute.params
				.switchMap((params: Params) => this._service.getPaySlipById(params['_id']))
				.subscribe((x: UploadPayslipHdr) => {
					this.uploadPayslipHdr = x;
					this.progressProcessorPayload = { processId: this.uploadPayslipHdr.processId };
					this.uploadPayslipHdr.upload_datetime = this.uploadPayslipHdr.upload_datetime.substring(0, 10);
					this.uploadDate = this.datePipe.transform(this.uploadPayslipHdr.upload_datetime, 'dd/MM/yyyy');
				});
		}
	}


	overWrite(hid, hlid) {
		this.router.navigateByUrl("/operation/upload-payslips/overwrite/" + hid + "/" + hlid);
	}

	progressProcessorInit() {
		if (this.progressProcessorCommand == "check-progress") {
		//Related to progress processor custom component
		}
	}

	receiveResponse($event){
		console.log($event)
		//Related to progress processor custom component
	}

}
