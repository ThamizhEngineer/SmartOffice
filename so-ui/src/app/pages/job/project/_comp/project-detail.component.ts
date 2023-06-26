import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{Project} from '../../vo/project';
import{ProjectService} from '../project.service';
import { JobService } from '../../job.service';
import { PartnerContact,Partner } from '../../vo/partner';
import {Observable} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import { Job } from '../../vo/job';
import { ClientPurchaseOrderService } from '../../sale-order/client-purchase-order.service';
import { CommonService } from '../../../../shared/common/common.service';
@Component({
    selector: 'project-detail',
    templateUrl: './project-detail.component.html'
})
export class ProjectDetailComponent implements OnInit {
    name:string;
    locationAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.locations.filter(v => v.locName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    location_formatter = (x: {locName: string}) => x.locName;
    project:Project;
    saveMsg: any;
    partner: any;
    partnerContact:Array<PartnerContact>;
    rows:any;
    job:Job;
    addScreen: boolean = false;
    countries :any;
    partners :any;
    locations:any=[];
    clientPos:any=[];
    mapLocationFlag:boolean=false;
    projectTypes=[
        { id: 1, name: "Project" },
        { id: 2, name: "Service" },
        { id: 3, name: "Solution" },
    ]
    values_of_client=[
        {id:1,name:"NPCIL"},
        {id:2,name:"Schneider Electric"},
        {id:3,name:"General Electric"},
        {id:4,name:"ARS Steel"},
        {id:5,name:"Seder Group"},
        {id:6,name:"LT Saudia"},
        {id:7,name:"National Contracting Company"},
        {id:8,name:"Omicron"}
    ]


    customerNames=[
        { id: 1, name: "General Electric" },
        { id: 2, name: "Schneider Electric" },
        { id: 3, name: "NPCIL" },
        { id: 4, name: "ARS Steel" },
        { id: 5, name: "Seder Group	" },
        { id: 6, name: "LT Saudia" },
        { id: 7, name: "National Contracting Company" },
        { id: 8, name: "Omicron" },


    ]
    constructor(private router:Router,private clientPurchaseOrderService:ClientPurchaseOrderService,private service: JobService,private route: ActivatedRoute,private ProjectService:ProjectService, public commonService: CommonService){

    }

    ngOnInit() {
    this.project= new Project();
    this.partner= new Partner();
    this.partnerContact = new Array<PartnerContact>();
    this.route.params.switchMap((par: Params) => this.ProjectService.getProjectById(par['_id'])).subscribe(x => {
        this.project = x;
      console.log(this.project);
    });
    this.ProjectService.getCountries().subscribe(x=>{
        this.countries=x;
       });
    this.ProjectService.getPartners().subscribe(x=>{
        this.partners=x;
   
    });
   
    
    }
    
    // clientSelected() {

	// 	// List of Client Purchase Orders
	// 	this.clientPurchaseOrderService.getClientPOs(this.project.partnerId).subscribe(x => {
	// 		this.clientPos = x.content;
	// 	});
		

	// 	// List of Gsts 
	// 	this.ProjectService.getPartnerById(this.project.partnerId).subscribe(partner => {
	// 		partner.partnerContacts.forEach(x => {
    //             this.partnerContact.push(x);
             
	// 		})
	// 	});
    // }
    // getMapLocations(){
	// 	this.service.getMapLocation().subscribe(x=>{
	// 		// console.log(x)
	// 		this.locations =x;
	// 	})
	// }
    // hide(){
	
	// 	if(this.mapLocationFlag){
	// 		this.mapLocationFlag=false;
	// 	this.getMapLocations();
	// 	}else{
	// 		this.mapLocationFlag=true;
    //     }
    // }
		
    navigateToListPage(){
        this.router.navigateByUrl("job/project/project-list"); 
          
       }
      
  save(){
    if (this.project.id) {
        this.ProjectService.updateProject(this.project).subscribe(x => {
            console.log(this.project);
            this.saveMsg = { 'type': 'success', 'text': "Project Updated Successfully" };
        });
    }
    else {

        this.ProjectService.createProject(this.project).subscribe(x => {
          
            this.saveMsg = { 'type': 'success', 'text': "Project Created Successfully" };
        });
    }
}
}