import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { JobFeedbackService } from './../job-feedback.service';
import { JobFeedback} from '../../vo/job-feedback';
import { Job } from '../../vo/job';
@Component({
  selector: 'app-exec-detail',
  templateUrl: './detail.component.html',
 //styleUrls:['./timeline.scss']
})
export class JobFeedbackDetailComponent implements OnInit {
	job:Job;
	visits: any;
	constructor(private titleService: Title,private router:Router, private _service: JobFeedbackService,private route: ActivatedRoute,) { }

	ngOnInit() {
		// this._service.getData().subscribe(x=>{
		// 	this.visits = x.filter(x=>x.id == 'J-18-01').pop();
		// });
this.job= new Job();
		if (this.route.params['_value']['_id']) {
    		this.route.params.switchMap((par: Params) => this._service.getAllFeedbackByJobId(par['_id'])).subscribe(x => {
				this.job = x;
				console.log(this.job)
			});
    	}
	}

	save(){
	console.log("save")
	console.log(this.job)
			this._service.updateJob(this.job).subscribe(x=>{
				console.log(x)
				this.router.navigateByUrl("/job/job-list"); 
			});	

		
	}
}
