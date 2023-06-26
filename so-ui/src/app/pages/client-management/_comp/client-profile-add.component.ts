import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientService } from '../client.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Partner, PartnerContact,PartnerEmployee,PartnerAccountInfo,PartnerDocument,PartnerGsts } from '../../job/vo/partner';
@Component({
    selector:'client-profile-add',
    templateUrl:'client-profile-add.component.html'
})

export class ClientProfileAddComponent implements OnInit{
    partner:Partner;
    partnerContact:PartnerContact;
    partnerGsts:PartnerGsts;
    msg:any;
    form: FormGroup;
    constructor(private router: Router,private route: ActivatedRoute,private clientService:ClientService,private formBuilder: FormBuilder ){}
ngOnInit(){
   this.partner = new Partner();
   this.partner.partnerContacts=new Array<PartnerContact>();
   this.partner.partnerEmployees=new Array<PartnerEmployee>();
   this.partner.partnerAccountInfos= new Array<PartnerAccountInfo>();
   this.partner.partnerDocuments=new Array<PartnerDocument>();
   this.partner.partnerGsts=new Array<PartnerGsts>();
   this.partnerContact = new PartnerContact();
   this.partnerGsts = new PartnerGsts();
   this.addPartner();
   this.form = this.formBuilder.group({
    GstNo: [null, [Validators.required, Validators.required]],
    refNo: [null, [Validators.required, Validators.required]],
    email: [null, [Validators.required, Validators.required]],
    firstName: [null, [Validators.required, Validators.required]],
    lastName: [null, [Validators.required, Validators.required]],
});
}

addPartner(){
    let ac = new PartnerEmployee();
    let bc = new PartnerAccountInfo();
    let cc = new PartnerDocument();

   this.partner.partnerEmployees.push(ac);
   this.partner.partnerAccountInfos.push(bc);
   this.partner.partnerDocuments.push(cc);
}


addClient(){
    this.partnerGsts.gstNo=this.partner.gstNo;
    this.partner.partnerGsts.push(this.partnerGsts);    
    console.log(this.partner);
    this.clientService.addClient(this.partner).subscribe(x=>{
        this.router.navigateByUrl('client/edit/'+x.id);
        this.msg = { type: 'success', text: "Client Added successfully" 
        }, error => { 
            this.msg=x;
        }
        });
}

}