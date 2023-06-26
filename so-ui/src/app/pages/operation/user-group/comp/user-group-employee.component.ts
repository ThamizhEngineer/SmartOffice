import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: '',
    templateUrl: 'user-group-employee.component.html'
})

export class UserGroupEmpComponent implements OnInit {
    constructor(
        private router:Router
    ) { }

    ngOnInit() {
        this.router.navigate(['/job/user-group-employee-mapping']);   
     }
}