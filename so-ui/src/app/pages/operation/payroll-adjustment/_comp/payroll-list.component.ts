
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'payroll-list',
    templateUrl: './payroll-list.component.html'
})
export class PayrollListComponent implements OnInit {

    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    navigateToDetailPage(){
        this.router.navigateByUrl("operation/payroll/detail");  
      
    }
  
    }

