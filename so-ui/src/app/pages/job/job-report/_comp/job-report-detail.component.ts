import { Component } from '@angular/core';
import { Job, JobMaterial, JobTip, JobDoc } from '../../vo/job';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { JobService } from '../../job.service';
import { DocInfo } from './../../../vo/doc-info';
import { DatePipe } from '@angular/common';
import { CommonService } from '../../../../shared/common/common.service';

@Component({
	selector: 'job-report-detail',
	templateUrl: './job-report-detail.component.html',
	providers: [DatePipe]
})
export class JobReportDetailComponent {

	job: Job;
	docInfo: DocInfo;
	jobDoc: JobDoc;
	docInfos: Array<DocInfo>;
	confirms: any;
	customers: any;
	customer: any;
	documentTypes: any = [];
	materials = [
		{ code: "001", name: "1.5 Sqmm GREY COIL", unit: "mts", qty: 500 },
		{ code: "002", name: "2.5 Sqmm RING TYPE LUGS", unit: "pack", qty: 1500 },
		{ code: "003", name: "Wire stripper", unit: "no", qty: 2500 }
	];
	constructor(private router: Router, private route: ActivatedRoute, private datePipe: DatePipe,
		private commonService: CommonService, private _service: JobService) { }

	ngOnInit() {

		this.job = new Job();
		this.docInfo = new DocInfo();
		this.jobDoc = new JobDoc();
		this.docInfos = [new DocInfo];
		if (this.route.params['_value']['_id']) {
			this.route.params.switchMap((par: Params) => this._service.getJobReportByJobId(par['_id'])).subscribe(x => {
				this.job = x;
				this.job.jobEmployees.forEach(x => {
					if ((x.expStartDt != null && x.expStartDt != "" && x.expStartDt != undefined) && (x.expEndDt != null && x.expEndDt != "" && x.expEndDt != undefined)) {
						x.showStartDt = x.expStartDt.substring(0, 10);
						x.showEndDt = x.expEndDt.substring(0, 10);

						x.showStartDt = this.datePipe.transform(x.showStartDt, 'MMM-yy');
						x.showEndDt = this.datePipe.transform(x.showEndDt, 'MMM-yy');
					}
				})

				if (x.jobTips) {
					this.addTipsRows();
				}

				this._service.getAllDocumentType().subscribe(x => {
					x.forEach(d => {
						if (d.isJob == "Y") {
							this.documentTypes.push(d);
						}
					})


				})
			});
		}

	}
	addJobDocsRows() {
		let jobDoc = new JobDoc();
		jobDoc.jobId = this.job.id;
		this.job.jobDocs.push(jobDoc);
	}

	addMaterialRows() {
		let ei = new JobMaterial();
		ei.jobId = this.job.id;
		this.job.jobMaterials.push(ei);
	}

	delMaterialRow(i) {
		this.job.jobMaterials.splice(i, 1);
	}

	addTipsRows() {
		let ei = new JobTip();
		ei.jobId = this.job.id;
		this.job.jobTips.push(ei);
	}

	delTipsRow(i) {
		this.job.jobTips.splice(i, 1);
	}

	deleteJobDocsRow(i, jobDoc: JobDoc) {
		if (jobDoc.id != null) {
			this._service.deleteJobDocs(this.job.id, jobDoc.id).subscribe(x => {
			})
		}
		this.job.jobDocs.splice(i, 1);
	}

	save() {
		this._service.updateJobReport(this.job).subscribe(data => {
			if (data) {
				this.router.navigateByUrl("/job/job-list");
			}
		}, error => {
			console.log(error)
		})
	}

	download(docId) {
		console.log(docId);
		if (docId != null && docId != "" && docId != undefined) {
			this.commonService.downloadDocument(docId, "");
		}
	}

	fileUploadJobSummary(event, jobDoc?: JobDoc) {
		let fileList: FileList = event.target.files;
		if (fileList.length > 0) {
			let file: File = fileList[0];
			this.commonService.uploadDocument(file, jobDoc.docTypeCode).subscribe(data => {
				jobDoc.docId = data[0].docId;
				jobDoc.isSourceOnsite = "N";
			},
				error => console.log(error))
		}
	}
}