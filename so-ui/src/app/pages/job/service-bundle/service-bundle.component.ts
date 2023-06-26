import { Component, OnInit,ViewEncapsulation, ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobService } from '../job.service';
import { ServiceBundle } from '../vo/service-bundle'
import { CommonService } from '../../../shared/common/common.service';

@Component({
    selector: 'service-bundle',
    templateUrl: './service-bundle.component.html'
})export class ServiceBundleComponent  implements OnInit{
	
	@ViewChild('vdetail') vdetail: TemplateRef<any>;
	jobType = 'service-bundles';
    constructor(private router:Router, private modalService: NgbModal, private service:JobService, private commonService: CommonService){

    }

	rows:Array<ServiceBundle>;
	serviceBundle:ServiceBundle;

	familyRows = [];
	familyCount = 1;

    ngOnInit() {
		this.serviceBundle = new ServiceBundle();			
		
	
	
	
		this.getJobs();
    }
    
    getJobs(){
    	this.service.getJobs(this.jobType).subscribe( x=>{
			this.rows = x.content;
		});
    }
	
	
	
	modalReference:any = null;
	
	showDetail(id?: any){
		this.service.getJobById(this.jobType, id).subscribe(x=>{
			this.serviceBundle = x;


			this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
		});			
	}

	createNew(){
		this.serviceBundle = new ServiceBundle();
	
		this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
	}

	saveMsg: any;
	save(){
		if(this.serviceBundle.id){
			this.service.updateJob(this.jobType, this.serviceBundle).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Service bundle Updated Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
		else{
			this.service.createJob(this.jobType, this.serviceBundle).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Service bundle Created Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
	}

	deleteRow(id?: any){
		this.service.deleteJob(this.jobType, id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Service bundle Deleted Successfully"};
            this.getJobs();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
	}
}