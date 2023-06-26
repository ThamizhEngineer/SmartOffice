import { Component, OnInit, ViewEncapsulation, ViewChild, TemplateRef  } from '@angular/core';
import { Title }     from '@angular/platform-browser';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
// import { JobBay,JobEquip} from '../../vo/job-bay';
import { JobMilestone,JobTaskList} from '../../vo/job';
import { JobService } from '../../job.service';

@Component({
  selector: 'job-status-list',
  templateUrl: './list.component.html',
 // styleUrls: [ './job-status.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class JobStatusListComponent implements OnInit {
	jobMilestone:JobMilestone;
	jobMilestones:Array<JobMilestone>;
	@ViewChild('vdetail') vdetail: TemplateRef<any>;
	constructor(private titleService: Title, private modalService: NgbModal, private _service: JobService) { }

	ngOnInit() {
		this.jobMilestone = new JobMilestone();
		this.jobMilestones = [new JobMilestone];
		this._service.getJobStatus("job-statuses").subscribe(x=>{
			this.jobMilestones = x.content;
			console.log(this.jobMilestones)
		});
	}
	
	bayHide:string="bay";
	bayHide1:string="bay";
	bay1 : any;
	bay2 : any;
	bay3 : any;
	stages: boolean = false;
	bay: boolean = false;
	stageClick($event: Event){
		
	}
	
	modalReference:any = null;
	modalData: any;
hide(i:any){
	console.log(i)
	if(this.bayHide1==this.bayHide+i.toString()){
		this.bayHide=null;
	}else{
		this.bayHide =this.bayHide+i.toString();
		this.bayHide1=this.bayHide+i.toString();
	}
	
	console.log(this.bayHide)
}

	showDetail(id?: any){
		this._service.getJobStatusByJobId(id).subscribe(x=>{
			console.log(x)
			this.jobMilestones = x;		
			if(this.jobMilestones.length>0){
				this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});
			}	
				
		});			
	}
}
