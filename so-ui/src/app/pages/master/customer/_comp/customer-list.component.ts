import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
    selector:'customer-list',
    templateUrl:'customer-list.component.html'
})

export class CustomerListComponent implements OnInit{

    constructor(private router:Router){

    }
    ngOnInit(){

    }

    navigateToDetailPage(){
        this.router.navigateByUrl("master/customer/customer-profile");   
       }


}