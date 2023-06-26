


import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'add-provident-fund',
    templateUrl: './add-provident-fund.component.html'
})
export class AddProvidentFundComponent {


    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    
    listProvident(){
        this.router.navigateByUrl("operation/provident-fund/provident-fund");   
    }
}
