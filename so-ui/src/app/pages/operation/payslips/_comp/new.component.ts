import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { PayslipsService } from '../payslips.service';
import { UploadPayslipHdr } from './../../../vo/upload-payslip';
import { DocInfo, DocMetadata } from './../../../vo/doc-info';
import { CommonService } from '../../../../shared/common/common.service';
@Component({
	selector: 'operations-payslips-new',
	templateUrl: './new.component.html'
})
export class PayslipsNewComponent implements OnInit {
	uploadPayslipHdr: UploadPayslipHdr;
	docInfo: DocInfo;
	docMetadata: DocMetadata;
	docMetadataList: Array<DocMetadata>;
	docInfos: Array<DocInfo>;
	paymonth: any;
	uploadData: any;
	attachment: any;
	salaryMonth: any;
	salaryYear: any;
	hid: any;
	hlid: any;
	years: any = [];
	date: any;
	errorMsg: any;

	progressProcessorPayload: any;
	progressProcessorConfig = { buttonName: "Process payslip" }
	progressProcessorCommand = "";
	progressResponse;

	fileDetails: any = {
		'files': [{ 'url': 'http://www.pdf995.com/samples/pdf.pdf', 'title': 'Payslip Data for June 2018', 'status': 'Approved', 'id': 'Payslip-Data-June2018' }],
		'for': 'Upload Payslip data', 'tooltip': 'Payslip data in CSV format', 'type': 'upload'
	};
	constructor(private router: Router, private activatedRoute: ActivatedRoute, private _service: PayslipsService, private commonService: CommonService) { }

	ngOnInit() {
		this.date = new Date().getFullYear() - 5;
		for (let index = 0; index < 10; index++) {
			this.years.push(this.date);
			this.date++;
		}
		this.uploadPayslipHdr = new UploadPayslipHdr();
		this.docInfo = new DocInfo();
		this.docInfo.metadataList = [new DocMetadata];
		this.docMetadata = new DocMetadata();
		this.docInfos = [new DocInfo]

		if (this.activatedRoute.params['_value']['hid']) {
			this.activatedRoute.params.subscribe(x => {
				this.hid = x.hid;
				this.hlid = x.hlid;
			});
		}

		this._service.getData().subscribe(x => {
			this.uploadData = x;
		});
	}

	progressProcessorInit() { //Progress processor starter
		if (this.progressProcessorCommand == "init") {
			var type = "process-payroll";
			var body = { docId: this.docInfo.docId, payMonth: this.salaryMonth, payYear: this.salaryYear };
			this.progressProcessorPayload = { processType: type, payloadBody: body };
		}
		else if (this.progressProcessorCommand == "check-progress") {
			this.progressProcessorPayload =  { processId: this.progressResponse.id };
			console.log("progressProcessorCommand ", this.progressProcessorCommand)
			console.log("progressProcessorPayload ",this.progressProcessorPayload);
		}
		else {
			return false;
		}
	}

	receiveResponse($event) {
		this.progressResponse = $event;
		if (this.progressResponse) {
			this.progressProcessorCommand = "check-progress";
			console.log("after receive", this.progressProcessorCommand);
			console.log(this.progressResponse)
		}
	  }

	fileChange(event) {
		let fileList: FileList = event.target.files;
		if (fileList.length > 0) {
			let file: File = fileList[0];
			this.commonService.uploadDocument(file, "PAYSLIP-USER-UPLOADED-DATA").subscribe(data => {
				this.docInfo = data[0];
				this.progressProcessorCommand = "init";
				console.log("response ", this.docInfo)
			}, error => {
				this.errorMsg = { 'type': 'danger', 'text': 'For only selected Salary Month and salary year payslip can be processed' }
				console.log("error ", error)
			});
		}
	}

	savePayslip() {
		this.router.navigateByUrl("/operation/upload-payslips/list");
	}

}
