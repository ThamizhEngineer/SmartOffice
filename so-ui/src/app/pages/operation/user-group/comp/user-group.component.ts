import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: '',
    templateUrl: 'user-group.component.html'
})

export class UserGroupComponent implements OnInit {
    constructor(
        private router:Router
    ) { }

    ngOnInit() { 

        this.router.navigate(['/job/user-group-master']);
       
    }
}