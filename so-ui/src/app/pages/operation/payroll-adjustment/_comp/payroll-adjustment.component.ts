
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'payroll-adjustment',
    templateUrl: './payroll-adjustment.component.html'
})
export class PayrollAdjustmentComponent  {
    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    listPayroll(){
        this.router.navigateByUrl("operation/payroll");  
      
    }
  

   
}

