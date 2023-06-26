import { Component, OnInit } from '@angular/core';
import { Title }     from '@angular/platform-browser';
import { JobFeedbackService } from './../job-feedback.service';
import { Job } from '../../vo/job';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
  selector: 'app-feedback-list',
  templateUrl: './list.component.html',
  //styleUrls:['./timeline.scss']
})
export class JobFeedbackListComponent implements OnInit {

	visits: any;
	jobs:Array<Job>;
	pageSize :number = 10;
	page :number = 1;
	constructor(private titleService: Title, private _service: JobFeedbackService,private router:Router) { }

	ngOnInit() {
		this._service.getAllFeedbacks().subscribe(x=>{
			this.jobs = x.content;
			console.log(this.jobs)
		});
	}

	showDetail(jobId?: any){
		this.router.navigateByUrl("/job/job-feedback/detail/"+jobId);   	
	}
}
