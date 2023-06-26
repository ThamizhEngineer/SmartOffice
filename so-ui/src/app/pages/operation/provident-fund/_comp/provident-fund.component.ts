
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'provident-fund',
    templateUrl: './provident-fund.component.html'
})
export class ProvidentFundComponent implements OnInit {

    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    
    navigateToDetailPage(){
     this.router.navigateByUrl("operation/provident-fund/add-provident-fund");   
    }
}


{
}