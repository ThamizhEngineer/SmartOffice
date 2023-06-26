import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
    selector: '',
    templateUrl: 'leave-balance.component.html'
})

export class LeaveBalanceComponent implements OnInit {
    constructor(
        private router:Router
    ) { }

    ngOnInit() { 
        this.router.navigate(['/org-settings/leave-balance']);   

    }
}