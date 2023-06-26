import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector:'customer-profile',
    templateUrl:'customer-profile.component.html'
})

export class CustomerProfileComponent{

    constructor(private router:Router){

    }

    editCustomerProfile(){
        this.router.navigateByUrl("master/customer/customer-profile-edit");
    }
    
}