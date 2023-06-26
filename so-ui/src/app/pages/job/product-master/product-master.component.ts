import { Component, OnInit, ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { JobService } from '../job.service';
import { Product,ApplicableService} from '../vo/product';
import { Manufacturer} from '../vo/manufacturer';
import { ProductFamily} from '../vo/product-family';
import { ServiceMaster} from '../vo/service-master';
const familyList = ["Family 1", "Family 2", "Family 3"];
const serviceList = ["Service 1", "Service 2", "Service 3", "Testing & Commissioning", "Retro Fitting"];
@Component({
    selector: 'product-master-list',
    templateUrl: './product-master.component.html'
})export class ProductMasterComponent  implements OnInit{
	
	@ViewChild('vdetail') vdetail: TemplateRef<any>;
    
    jobType = 'products';
    constructor(private router:Router, private modalService: NgbModal, private service:JobService){

    }

	rows:Array<Product>;
	product:Product;
	applicableService:ApplicableService;
	manufactures:Array<Manufacturer>;
	productFamilies:Array<ProductFamily>;
	serviceMasters:Array<ServiceMaster>;
	applicableServicesRows = [];
	applicableServicesCount = 1;
    ngOnInit() {
		this.product = new Product();	
		this.applicableService = new ApplicableService();

		
		this.getJobs();
        this.service.getJobs("manufacturers").subscribe( x=>{
			this.manufactures = x.content;

        });
		this.service.getJobs("products").subscribe( x=>{
			this.productFamilies = x.content;
			 

		});
		this.service.getJobs("services").subscribe( x=>{
			this.serviceMasters = x ;
			


		});
		
    }
    
    getJobs(){
    	this.service.getJobs(this.jobType).subscribe( x=>{
			this.rows = x.content;
		});
    }
	
	addApplicableServicesRows(){
		this.applicableServicesCount++;
		let copy = Object.assign({}, this.applicableService);
		this.product.applicableServices.push(copy);
	}
	delApplicableServicesRow(item){
		let i = item - 0;
		this.product.applicableServices.splice(i, 1);
	}
	
	
	modalReference:any = null;
	
	showDetail(id?: any){
		this.service.getJobById(this.jobType, id).subscribe(x=>{
			this.product = x;


			this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
		});			
	}

	createNew(){
		this.product = new Product();
		this.product.applicableServices = [new ApplicableService];
		let copy = Object.assign({}, this.applicableService);
		this.applicableServicesRows.push(copy);		
		this.applicableServicesRows = this.product.applicableServices;
		this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
	}

	saveMsg: any;
	save(){
		if(this.product.id){
			this.service.updateJob(this.jobType, this.product).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Product Master Updated Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
		else{
			this.service.createJob(this.jobType, this.product).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Product Master Created Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
	}


	deleteRow(id?: any){
		this.service.deleteJob(this.jobType, id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Product Master Deleted Successfully"};
            this.getJobs();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Product Master Error"};
        });
	}
    
}