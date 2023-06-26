import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {UserService} from '../../../auth/_services/user.service';
import {User} from '../../../auth/_models/user';
import { ClientService } from '../client.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'my-space-client',    
    templateUrl: 'my-space-client.component.html'
})

export class MySpaceClientComponent implements OnInit {

    user:User;

    constructor(private router:Router 
        ,private userService:UserService,
        private service:ClientService) { }

    ngOnInit() { 
        
        this.user = this.userService.getCurrentUser();

        this.service.getClientEmployeeById(this.user.partnerId).subscribe(x=>{
            this.router.navigateByUrl('/client/view/'+x.partnerId);  
        })
    }
}