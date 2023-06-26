import { Component, OnInit, ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { JobService } from '../job.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {  Profile,ProfileLine} from '../vo/profile';
import { Manufacturer} from '../vo/manufacturer';
import { ProductFamily} from '../vo/product-family';
import { ServiceMaster} from '../vo/service-master';
import { CommonService } from '../../../shared/common/common.service';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

const familyList = ["Family 1", "Family 2", "Family 3"];
const serviceList = ["Service 1", "Service 2", "Service 3", "Testing & Commissioning", "Retro Fitting"];
@Component({
    selector: 'profile-master-list',
    templateUrl: './profile-master.component.html'
})export class ProfileMasterComponent  implements OnInit{

	searchProduct = (text$: Observable<string>) =>
	text$.pipe(
		debounceTime(200),
		distinctUntilChanged(),
		map(term => (term === '' ? this.productFamilies
			: this.productFamilies.filter(v => v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
	);
productFormatter = (x: { materialName: string }) => {
	x.materialName
};

searchSkill = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.serviceMasters
                : this.serviceMasters.filter(v => v.abilityName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    skillFormatter = (x: { abilityName: string }) => {x.abilityName};

	form: FormGroup;
	@ViewChild('vdetail') vdetail: TemplateRef<any>;
    
    jobType = 'profiles';
    constructor(private router:Router,private formBuilder: FormBuilder, private modalService: NgbModal, private service:JobService, private commonService: CommonService){

    } 
 
	rows:Array<Profile>;

	profile:Profile;

	
	profileLine:ProfileLine;
	manufactures:Array<Manufacturer>;
	productFamilies:Array<ProductFamily>;
	serviceMasters:any=[];
	profileLinesRows = [];
	profileLinesCount = 1;
	mproductId: number;
	productId:any;
	msg: any;


    ngOnInit() {
		this.profile = new Profile();	
		this.profileLine = new ProfileLine();

		
		this.getJobs();
   
		this.service.getProducts().subscribe( x=>{
			this.productFamilies = x;
			 

		});
		// this.service.getJobs("abilities").subscribe( x=>{
		// 	this.serviceMasters = x;			
		// });
		this.form = this.formBuilder.group({
			Profile: [null, [Validators.required, Validators.required]],
			
		});

		
    }
    
    getJobs(){
    	this.service.getJobs(this.jobType).subscribe( x=>{
			this.rows = x;
			console.log(this.rows);
		});
    }
	
	addProfileLinesRows(){
		this.profileLinesCount++;
		let copy = Object.assign({}, this.profileLine);
		this.profile.profileLines.push(copy);
	}
	delProfileLinesRow(item){
		let i = item;
		this.profile.profileLines.splice(i, 1);
	}
	
	get checkProfile(){
		return this.profile.profileLines.filter(x=>x.productId==null||x.serviceId==null);
	}
	
	modalReference:any = null;
	
	showDetail(id?: any){
		this.msg=null;
		this.service.getJobById('profiles', id).subscribe(x=>{
			this.profile = x;

			this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
		});			
	}

	createNew(){
		this.profile = new Profile();
		this.profile.profileLines = [new ProfileLine];
		let copy = Object.assign({}, this.profileLine);
		this.profileLinesRows.push(copy);		
		this.profileLinesRows = this.profile.profileLines;
		this.modalReference = this.modalService.open(this.vdetail, {size: 'lg'});	
	}

	saveMsg: any;
	save(){
		if(this.profile.id){
			this.service.updateJob('profiles', this.profile).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Profile Master Updated Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
		else{
			this.service.createJob('profiles', this.profile).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Profile Master Created Successfully"};
				this.getJobs();
				this.modalReference.close();
			});	
		}
	}

	deleteRow(id?: any){
		this.service.deleteJob('profiles', id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Profile Master Deleted Successfully"};
            this.getJobs();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Profile Master Error"};
        });
	}

	onProductSelect($event, item) {
        this.profile.profileLines[item].productId = $event.item.id
		this.profile.profileLines[item].productName = $event.item.materialName
		this.mproductId = $event.item.id
	}
	
	onSkillSelect($event, item){
		let productId =this.profile.profileLines[item].productId
		let serviceId = $event.item.id
        var response = this.profile.profileLines
         .filter((profileLines: ProfileLine ) => profileLines.productId === productId && profileLines.serviceId===serviceId)
         if(response.length!=0){
        this.msg = { type: 'danger', text: "Duplicate record found" }
        }else{			
        this.profile.profileLines[item].serviceId = $event.item.id
        this.profile.profileLines[item].serviceName = $event.item.abilityName
		}
	}

	
	formService(id){
        if(this.productId!=id){
            this.productId=id;
            this.service.getSkillFromProdId(id).subscribe(x=>this.serviceMasters=x.materialServices);
        }     
    }
    
}