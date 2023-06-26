
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';
@Component({
    selector: 'my-salary-slip',
    templateUrl: './my-salary-slip.component.html'
})
export class MySalarySlipComponent implements OnInit {
    user:User;

    constructor(private router:Router ,private userService:UserService){

    }

    ngOnInit() {
        this.user = this.userService.getCurrentUser();
        // this.router.navigateByUrl("/operation/employee/detail/" + this.user.employeeId);
        this.router.navigate(['/operation/employee-payout/list/'] ,{queryParams:{flowType:"salaryslip",id:this.user.employeeId}});
    }
    
   
}
