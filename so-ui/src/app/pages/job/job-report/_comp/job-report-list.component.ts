import { Component , OnInit } from '@angular/core';
import { SearchService } from './../../../../shared/_services/search.service';
import { Job } from '../../vo/job';
import { JobService } from '../../job.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'job-report-list',
    templateUrl: './job-report-list.component.html'
})
export class JobReportListComponent implements OnInit {
	searchArray: any;
	filteredSearchArray: any;
	jobs:Array<Job>;
    constructor(private _service: JobService,private router:Router) { 

	}
	
	ngOnInit(){
		this._service.getJobReports().subscribe(x=>{
			this.jobs = x.content;            
		});
	}

	showDetail(jobId?: any){
		this.router.navigateByUrl("/job/job-report/job-report-detail/"+jobId);   	
	}
}
