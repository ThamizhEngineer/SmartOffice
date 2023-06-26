import { Component, OnInit, ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobService } from '../job.service';
import { Goods} from '../vo/goods';
@Component({
    selector: 'goods-master-list',
    templateUrl: './goods-master.component.html'
})export class GoodsMasterComponent  implements OnInit{
	
	@ViewChild('vdetail') vdetail: TemplateRef<any>;
   

    jobType = 'goods';
    constructor(private router:Router, private modalService: NgbModal, private service:JobService){

    }

	rows:Array<Goods>;
	goods:Goods;

	familyRows = [];
	familyCount = 1;

    ngOnInit() {
		this.goods = new Goods();			
		
	
	
	
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
			this.goods = x;


			this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
		});			
	}

	createNew(){
		this.goods = new Goods();
	
		this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
	}

	saveMsg: any;
	save(){
		if(this.goods.id){
			this.service.updateJob(this.jobType, this.goods).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Goods Master Updated Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
		else{
			this.service.createJob(this.jobType, this.goods).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Goods Master Created Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
	}

	deleteRow(id?: any){
		this.service.deleteJob(this.jobType, id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Goods Master Deleted Successfully"};
            this.getJobs();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
	}
}