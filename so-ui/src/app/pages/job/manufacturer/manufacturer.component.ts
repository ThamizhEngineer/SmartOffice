import { Component, OnInit,ViewEncapsulation, ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobService } from '../job.service';
import { Manufacturer, ProductFamily } from '../vo/manufacturer'
import { CommonService } from '../../../shared/common/common.service';

@Component({
    selector: 'manufacturer',
    templateUrl: './manufacturer.component.html'
})export class ManufacturerComponent  implements OnInit{
	
	@ViewChild('vdetail') vdetail: TemplateRef<any>;
	jobType = 'manufacturers';
	product = 'product-families';
    constructor(private router:Router, private modalService: NgbModal, private service:JobService, private commonService: CommonService){

    }

	rows:Array<Manufacturer>;
	manufacturer:Manufacturer;
	productFamily:ProductFamily;
	familyRows = [];
	familyCount = 1;

    ngOnInit() {
		this.manufacturer = new Manufacturer();			
		
		this.productFamily = new ProductFamily();
		let copy = Object.assign({}, this.productFamily);
		this.familyRows.push(copy);
		this.getJobs();
    }
    
    getJobs(){
    	this.service.getJobs(this.jobType).subscribe( x=>{
			this.rows = x.content;
		});
	}
	getProducts(){
    	this.service.getJobs(this.product).subscribe( x=>{
			this.rows = x.content;
		});
    }
	
	addFamilyRows(){
		this.familyCount++;
		let copy = Object.assign({}, this.productFamily);
		this.familyRows.push(copy);
	}
	delFamilyRow(item,id){
		console.log(item)
	this.familyRows.splice(item,1)
	this.service.deleteJob(this.product, id).subscribe(x=>{
	
	})
}
	
		
	
	
	modalReference:any = null;
	
	showDetail(id?: any){
		this.service.getJobById(this.jobType, id).subscribe(x=>{
			this.manufacturer = x;
			this.familyRows = x.productFamilies;
			let copy = Object.assign({}, this.productFamily);
			if(this.familyRows.length == 0) this.familyRows.push(copy);

			this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
		});			
	}

	createNew(){
		this.manufacturer = new Manufacturer();
		let copy = Object.assign({}, this.productFamily);
		this.manufacturer.productFamilies.push(copy);
		this.familyRows = this.manufacturer.productFamilies;
		this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
	}

	saveMsg: any;
	save(){
		if(this.manufacturer.id){
			this.service.updateJob(this.jobType, this.manufacturer).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Manufacturer Updated Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
		else{
			this.service.createJob(this.jobType, this.manufacturer).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Manufacturer Created Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
	}

	deleteRow(id?: any){
		this.service.deleteJob(this.jobType, id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Manufacturer Deleted Successfully"};
            this.getJobs();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
	}
}