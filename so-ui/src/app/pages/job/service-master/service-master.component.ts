import { Component, OnInit,ViewEncapsulation, ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobService } from '../job.service';
import { ServiceMaster } from '../vo/service-master'
import { CommonService } from '../../../shared/common/common.service';

@Component({
    selector: 'service-master',
    templateUrl: './service-master.component.html'
})export class ServiceMasterComponent  implements OnInit{
	
	@ViewChild('vdetail') vdetail: TemplateRef<any>;
	jobType = 'abilities';
    constructor(private router:Router, private modalService: NgbModal, private service:JobService, private commonService: CommonService){

    }

	rows:Array<ServiceMaster>;
	serviceMaster:ServiceMaster;

	familyRows = [];
	familyCount = 1;

    ngOnInit() {
		this.serviceMaster = new ServiceMaster();			
	
	
		this.getJobs();
    }
    
    getJobs(){
    	this.service.getJobs(this.jobType).subscribe( x=>{
			this.rows = x;
		});
    }
	
	
	
	modalReference:any = null;
	
	showDetail(id?: any){
		this.service.getJobById(this.jobType, id).subscribe(x=>{
			this.serviceMaster = x;


			this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
		});			
	}

	createNew(){
		this.serviceMaster = new ServiceMaster();
	
		this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
	}

	saveMsg: any;
	save(){
		if(this.serviceMaster.id){
			this.service.updateJob(this.jobType, this.serviceMaster).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Service Master Updated Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
		else{
			this.service.createJob(this.jobType, this.serviceMaster).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Service Master Created Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
	}

	deleteRow(id?: any){
		this.service.deleteJob(this.jobType, id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Service Master Deleted Successfully"};
            this.getJobs();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
	}
}