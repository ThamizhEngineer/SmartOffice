import { Component, OnInit, ViewEncapsulation, ViewChild, TemplateRef  } from '@angular/core';
import { Title }     from '@angular/platform-browser';
import { ResourceSchedulerService} from './../resource-scheduler.service';
import { Job } from '../../vo/job';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
  selector: 'resource-scheduler-list',
  templateUrl: './resource-scheduler-list.component.html'
})
export class ResourceSchedulerListComponent implements OnInit {
	jobs:Array<Job>;

	constructor(private titleService: Title,private _service:ResourceSchedulerService,private router:Router) { }

	ngOnInit() {
		this._service.getJobLogistics().subscribe(x=>{
			this.jobs = x.content; 
			console.log(this.jobs)          
		});
	}
	showDetail(jobId?: any){
		this.router.navigateByUrl("/job/resource-scheduler/detail/"+jobId);   	
	}
}
