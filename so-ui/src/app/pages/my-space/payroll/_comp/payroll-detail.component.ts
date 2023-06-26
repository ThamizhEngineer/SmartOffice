
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'payroll-detail',
    templateUrl: './payroll-detail.component.html'
})
export class PayrollDetailComponent implements OnInit {

    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    
    navigateToDetailPage(){
     this.router.navigateByUrl("master/payroll/payroll-detail");   
    }
}
