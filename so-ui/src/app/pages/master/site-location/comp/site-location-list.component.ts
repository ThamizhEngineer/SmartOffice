import { Component, OnInit } from '@angular/core';
import { siteLocationService } from '../site-location.service'
import { ActivatedRoute,Params,Router } from '@angular/router';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';
import { CommonService } from '../../../../shared/common/common.service';

@Component({
    selector: '',
    templateUrl: 'site-location-list.component.html'
})

export class SiteLocationListComponent implements OnInit {
    
    siteLocations:any=[];
    siteName:string='';
    country:string='';
    endClientName:string='';
    contactName:string='';
    mobileNumber:string='';
    user:User;
    isEdit:boolean=false;

    constructor(
        private service:siteLocationService,
        private route: Router,
        private userService:UserService,
        public commonService: CommonService
    ) { }

    ngOnInit() { 
        this.user = this.userService.getCurrentUser();

        this.user.authUserRoles.forEach(element => {
            if(element.authRoleCode=='N1'||element.authRoleCode=='ADMIN'||element.authRoleCode=='OM'){
                this.isEdit=true;
            }
        });

        this.service.getAllSites().subscribe(x=>{
            this.siteLocations=x;
        })
    }

    serach(){
        this.siteLocations=[];
        this.service.searchBySites(this.siteName,this.country,this.endClientName,this.contactName,this.mobileNumber).subscribe(x=>{
            this.siteLocations=x;
        })
    }

    navigateToDetail(id){
        this.route.navigateByUrl('job/site-location/detail/'+id);
    }
}