import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{VendorService} from '../vendor.service';
import { Partner, PartnerContact } from '../../job/vo/partner';
@Component({
    selector:'vendor-profile-add',
    templateUrl:'vendor-profile-add.component.html'
})

export class VendorProfileAddComponent implements OnInit{
    partner:Partner;
    partnerContact:PartnerContact;
    msg:any;
    constructor(private router: Router,private route: ActivatedRoute,private vendorService:VendorService ){}
ngOnInit(){
   this.partner = new Partner();
   this.partner.partnerContacts=new Array<PartnerContact>();
   this.partnerContact = new PartnerContact();
}
    addVendor(){
        

    this.router.navigateByUrl('vendor/edit/');
  
    
 
 
 

 

// });



      
    
}
}