import { Component, OnInit } from '@angular/core';
import { siteLocationService } from '../site-location.service'

import { SiteLocation } from '../../vo/site-location';
import { ActivatedRoute,Params,Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
    selector: '',
    templateUrl: 'site-location-detail.component.html'
})

export class SiteLocationDetailComponent implements OnInit {

    searchClient = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.clientProfile
                : this.clientProfile.filter(v => v.clientName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
        clientFormatter = (x: { clientName: string }) => {
        x.clientName
    };

  
    siteLocation:SiteLocation;
    user:User;
    isEdit:boolean=false;
    countries:any=[];

    clientProfile:any=[];

    myGroup: FormGroup;

    siteName:FormControl;
    siteAddress:FormControl;
    country:FormControl;
    contactName:FormControl;
    contactNumber:FormControl;
    mapLocation:FormControl;
    
  
    constructor(
        private service:siteLocationService,
        private activedRouter:ActivatedRoute,
        private route: Router,
        private commonService: CommonService,
        private userService:UserService
    ) { }

    ngOnInit() { 
        this.siteLocation = new SiteLocation();
        if(this.siteLocation.endClientName==null){
            this.siteLocation.endClientName='';
        }
        this.user = this.userService.getCurrentUser();

        this.user.authUserRoles.forEach(element => {
            if(element.authRoleCode=='N1'||element.authRoleCode=='ADMIN'||element.authRoleCode=='OM'){
                this.isEdit=true;
            }
        });

        this.service.getPartners().subscribe(x=>{
            this.clientProfile=x;
        })

        this.service.getAllCountry().subscribe(_countries=>{
            this.countries = _countries;
        });

        if (this.activedRouter.params['_value']['id']) {
            this.activedRouter.params.subscribe(params=>{

                this.service.getBySiteId(params.id).subscribe(x=>{
                    this.siteLocation=x;
                    this.validateCheck();
                })

            })
        }
        this.createFormControls();
        this.createForm();
    }

    onClientSelect($event){
        console.log($event.item);
        this.siteLocation.endClientId=$event.item.id;
        this.siteLocation.endClientName=$event.item.clientName;
    }

    validateCheck(){
        this.myGroup.patchValue({
            siteName:this.siteLocation.siteName,
            siteAddress:this.siteLocation.siteAddress,
            country:this.siteLocation.country,
            contactName:this.siteLocation.contactName,
            contactNumber:this.siteLocation.mobileNumber,
            mapLocation:this.siteLocation.mapLocationId,
        })
    }

    createFormControls(){    

		this.siteName = new FormControl('', [Validators.required]);
		this.siteAddress = new FormControl('', [Validators.required]);
		this.country = new FormControl('', [Validators.required]);
		this.contactName = new FormControl('', [Validators.required]);
		this.mapLocation= new FormControl('', [Validators.required]);
		
		this.contactNumber= new FormControl('', [Validators.required,Validators.pattern('[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}')]);
		
    }
    
    createForm() {
		this.myGroup = new FormGroup({
            siteName:this.siteName,
            siteAddress:this.siteAddress,
            country:this.country,
            contactName:this.contactName,
            mapLocation:this.mapLocation,
            contactNumber:this.contactNumber					
		});

	}

    saveSiteLocation(){
        this.service.createAndUpdateSite(this.siteLocation).subscribe(x=>{
            this.route.navigateByUrl('job/site-location/detail/'+x.id);
            this.ngOnInit();
        })
    }

    onLocationSelected($event){
        this.siteLocation.mapLocationId=$event.id;
        this.siteLocation.mapLocationName=$event.locName;
        this.siteLocation.lats=$event.lats;
        this.siteLocation.longs=$event.longs;
        this.validateCheck();
    }

    deleteSite(){
        this.service.deleteSiteLocation(this.siteLocation.id).subscribe(x=>{
            this.route.navigateByUrl('job/site-location');
        })
    }
}